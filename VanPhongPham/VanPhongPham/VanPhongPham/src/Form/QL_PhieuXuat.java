package Form;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QL_PhieuXuat extends javax.swing.JFrame {
    private int MaNV;
    private DefaultTableModel modelTable_PhieuXuat;
    private DefaultTableModel modelTable_CT_PhieuXuat;
    private DefaultComboBoxModel<String> modelCbo_NhanVien_PX;
    private DefaultComboBoxModel<String> modelCbo_Kho_PX;
    private DefaultComboBoxModel<String> modelCbo_SanPham_CTPX;

    public QL_PhieuXuat(int maNV) {
        this.MaNV = maNV;
        initComponents();        
        initCbbKho_PhieuXuat();
        initCbbSanPham_ChiTietPhieuXuat();

        initTable_CT_PhieuXuat();
        loadDataToTable_CT_PhieuXuat();

        initTable_PhieuXuat();
        loadDataToTable_PhieuXuat();

        String nowDate = getDateNow();
        txt_date_PX.setText(nowDate);
        
        jTextField1.setText(getNameNhanVien(maNV));
        jTextField1.setEditable(false);
    }

    private String getNameNhanVien(int maNV) {
        String NV = "";
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT TenNV FROM NhanVien WHERE MaNV = ?");
            pstmt.setInt(1, maNV);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                NV = rs.getString("TenNV");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return NV;
    }

    private void initCbbKho_PhieuXuat() {
        try {
            modelCbo_Kho_PX = new DefaultComboBoxModel<>();
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Kho.TenKho FROM Kho");

            cbb_kho_PX.removeAllItems();

            while (rs.next()) {
                String tenkho = rs.getString("TenKho");
                cbb_kho_PX.addItem(tenkho);
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initCbbSanPham_ChiTietPhieuXuat() {
        try {
            modelCbo_SanPham_CTPX = new DefaultComboBoxModel<>();
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SanPham.TenSP FROM SanPham");

            cbb_sanpham_CTPX.removeAllItems();

            while (rs.next()) {
                String tenSP = rs.getString("TenSP");
                cbb_sanpham_CTPX.addItem(tenSP);
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initTable_PhieuXuat() {
        String[] headers = {"Mã Phiếu Xuất", "Nhân Viên", "Tên Kho", "Ngày Xuất"};
        modelTable_PhieuXuat = new DefaultTableModel(headers, 0);
        table_phieuxuat.setModel(modelTable_PhieuXuat);
    }

    private void loadDataToTable_PhieuXuat() {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select px.MaPhieuXuat, nv.TenNV, k.TenKho, px.NgayXuat from PhieuXuat px inner join NhanVien nv on px.MaNV = nv.MaNV inner join Kho k on px.MaKho = k.MaKho");

            while (rs.next()) {
                int maPhieuXuat = rs.getInt("MaPhieuXuat");
                String TenNV = rs.getString("TenNV");
                String TenKho = rs.getString("TenKho");
                Date NgayXuat = rs.getDate("NgayXuat");
                String date_format_NgayXuat = convertSQLDateToString(NgayXuat);
                modelTable_PhieuXuat.addRow(new Object[]{maPhieuXuat, TenNV, TenKho, date_format_NgayXuat});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initTable_CT_PhieuXuat() {
        String[] headers = {"Mã Phiếu Xuất", "Sản Phẩm", "Số Lượng"};
        modelTable_CT_PhieuXuat = new DefaultTableModel(headers, 0);
        table_CTphieuxuat.setModel(modelTable_CT_PhieuXuat);
    }

    private void loadDataToTable_CT_PhieuXuat() {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select ctpx.MaPhieuXuat, sp.TenSP, ctpx.SoLuong from ChiTietPhieuXuat ctpx inner join SanPham sp on ctpx.MaSP = sp.MaSP");

            while (rs.next()) {
                int maPhieuXuat = rs.getInt("MaPhieuXuat");
                String TenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SoLuong");
                modelTable_CT_PhieuXuat.addRow(new Object[]{maPhieuXuat, TenSP, soLuong});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isValidDate(String dateString) {
        String[] parts = dateString.split("/");
        if (parts.length != 3) {
            return false;
        }

        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Kiểm tra tính hợp lệ của ngày, tháng và năm
            if (month < 1 || month > 12) {
                return false;
            }
            if (day < 1) {
                return false;
            }
            int maxDays = getMaxDaysInMonth(month, year);
            if (day > maxDays) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int getMaxDaysInMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private int getIDNhanVien(String name) { //Hàm không sử dụng
        int maNV = -1;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT MaNV FROM NhanVien WHERE TenNV = ?");
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maNV = rs.getInt("MaNV");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return maNV;
    }

    private int getIDKho(String name) {
        int maKho = -1;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT MaKho FROM Kho WHERE TenKho = ?");
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maKho = rs.getInt("MaKho");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return maKho;
    }

    private int getIDProduct(String name) {
        int maSP = -1;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT MaSP FROM SanPham WHERE TenSP = ?");
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maSP = rs.getInt("MaSP");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return maSP;
    }

    private String convertDateFormat(String dateString) {
        String[] parts = dateString.split("/");
        if (parts.length != 3) {
            return dateString; // Trả về giá trị ban đầu nếu định dạng không hợp lệ
        }

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return String.format("%04d/%02d/%02d", year, month, day);
    }

    private String convertSQLDateToString(Date sqlDate) {
        // Chuyển đổi java.sql.Date sang LocalDate
        LocalDate localDate = sqlDate.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

    private String convertDateStringToFormat(String dateString) {
        // Định dạng chuỗi ngày tháng ban đầu
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Định dạng chuỗi ngày tháng mới
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển chuỗi ngày tháng sang LocalDate
        LocalDate localDate = LocalDate.parse(dateString, inputFormatter);

        // Chuyển LocalDate sang chuỗi ngày tháng mới
        return localDate.format(outputFormatter);
    }

    private Date convertStringToSqlDate(String dateString) {
        // Định dạng chuỗi ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Chuyển chuỗi ngày tháng sang LocalDate
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        // Chuyển LocalDate sang java.sql.Date
        return Date.valueOf(localDate);
    }

    private String getDateNow() {
        Date currentSQLDate = new Date(System.currentTimeMillis());

        // Chuyển đổi java.sql.Date sang java.time.LocalDate
        LocalDate currentLocalDate = currentSQLDate.toLocalDate();

        // Định dạng ngày tháng năm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentLocalDate.format(formatter);
        return formattedDate;
    }
    private void loadTable_CT_PhieuNhap_MaPN(){
        int r = table_phieuxuat.getSelectedRow();
        if (r < 0) {
            return;
        }
        int maPN = Integer.parseInt(table_phieuxuat.getValueAt(r, 0).toString());
        try {
            Connection con = Datahelper.openConnection();
            String sql = "select ctpx.MaPhieuXuat, sp.TenSP, ctpx.SoLuong from ChiTietPhieuXuat ctpx inner join SanPham sp on ctpx.MaSP = sp.MaSP where ctpx.MaPhieuXuat = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, maPN);
            ResultSet rs = pstmt.executeQuery();
            modelTable_CT_PhieuXuat.setRowCount(0);
            while (rs.next()) {
                int maPhieuXuat = rs.getInt("MaPhieuXuat");
                String TenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SoLuong");
                modelTable_CT_PhieuXuat.addRow(new Object[]{maPhieuXuat, TenSP, soLuong});
            }

            rs.close();
            pstmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_date_PX = new javax.swing.JTextField();
        cbb_kho_PX = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_phieuxuat = new javax.swing.JTable();
        btn_them_PhieuXuat_ = new javax.swing.JButton();
        btn_new_PhieuXuat = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_soluong_CTPX = new javax.swing.JTextField();
        cbb_sanpham_CTPX = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_CTphieuxuat = new javax.swing.JTable();
        btn_them_CTPN_ = new javax.swing.JButton();
        btn_new_CTPN = new javax.swing.JButton();
        btn_xoa_CTPN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("Quản Lý Phiếu Xuất");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Ngày xuất");

        txt_date_PX.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        cbb_kho_PX.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbb_kho_PX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Nhân Viên");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Kho");

        table_phieuxuat.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        table_phieuxuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Xuất", "Nhân Viên", "Tên Kho", "Ngày Xuất"
            }
        ));
        table_phieuxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_phieuxuatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_phieuxuat);

        btn_them_PhieuXuat_.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_them_PhieuXuat_.setForeground(new java.awt.Color(0, 153, 153));
        btn_them_PhieuXuat_.setText("Thêm");
        btn_them_PhieuXuat_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them_PhieuXuat_ActionPerformed(evt);
            }
        });

        btn_new_PhieuXuat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new_PhieuXuat.setForeground(new java.awt.Color(0, 153, 153));
        btn_new_PhieuXuat.setText("New");
        btn_new_PhieuXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_new_PhieuXuatActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txt_date_PX, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbb_kho_PX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1))))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_them_PhieuXuat_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_new_PhieuXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(201, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_date_PX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbb_kho_PX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btn_new_PhieuXuat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_them_PhieuXuat_)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết phiếu xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Sản phẩm");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Số lượng");

        txt_soluong_CTPX.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        cbb_sanpham_CTPX.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbb_sanpham_CTPX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        table_CTphieuxuat.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        table_CTphieuxuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Phiếu Xuất", "Sản phẩm", "Số Lượng"
            }
        ));
        table_CTphieuxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_CTphieuxuatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_CTphieuxuat);

        btn_them_CTPN_.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_them_CTPN_.setForeground(new java.awt.Color(0, 153, 153));
        btn_them_CTPN_.setText("Thêm");
        btn_them_CTPN_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them_CTPN_ActionPerformed(evt);
            }
        });

        btn_new_CTPN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new_CTPN.setForeground(new java.awt.Color(0, 153, 153));
        btn_new_CTPN.setText("New");
        btn_new_CTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_new_CTPNActionPerformed(evt);
            }
        });

        btn_xoa_CTPN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_xoa_CTPN.setForeground(new java.awt.Color(0, 153, 153));
        btn_xoa_CTPN.setText("Xóa");
        btn_xoa_CTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa_CTPNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbb_sanpham_CTPX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_soluong_CTPX))
                .addGap(12, 12, 12))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(btn_new_CTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_them_CTPN_)
                .addGap(18, 18, 18)
                .addComponent(btn_xoa_CTPN)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbb_sanpham_CTPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_soluong_CTPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them_CTPN_)
                    .addComponent(btn_new_CTPN)
                    .addComponent(btn_xoa_CTPN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(520, 520, 520))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table_phieuxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_phieuxuatMouseClicked
        int r = table_phieuxuat.getSelectedRow();
        if (r < 0) {
            return;
        }
        cbb_kho_PX.setSelectedItem(table_phieuxuat.getValueAt(r, 2));

        txt_date_PX.setText(table_phieuxuat.getValueAt(r, 3).toString());

        int maPN = Integer.parseInt(table_phieuxuat.getValueAt(r, 0).toString());
        try {
            Connection con = Datahelper.openConnection();
            String sql = "select ctpx.MaPhieuXuat, sp.TenSP, ctpx.SoLuong from ChiTietPhieuXuat ctpx inner join SanPham sp on ctpx.MaSP = sp.MaSP where ctpx.MaPhieuXuat = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, maPN);
            ResultSet rs = pstmt.executeQuery();
            modelTable_CT_PhieuXuat.setRowCount(0);
            while (rs.next()) {
                int maPhieuXuat = rs.getInt("MaPhieuXuat");
                String TenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SoLuong");
                modelTable_CT_PhieuXuat.addRow(new Object[]{maPhieuXuat, TenSP, soLuong});
            }

            rs.close();
            pstmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_table_phieuxuatMouseClicked

    private void table_CTphieuxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_CTphieuxuatMouseClicked
        int r = table_CTphieuxuat.getSelectedRow();
        if (r < 0) {
            return;
        }
        cbb_sanpham_CTPX.setSelectedItem(table_CTphieuxuat.getValueAt(r, 1));
        txt_soluong_CTPX.setText(table_CTphieuxuat.getValueAt(r, 2).toString());
    }//GEN-LAST:event_table_CTphieuxuatMouseClicked

    private void btn_them_PhieuXuat_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them_PhieuXuat_ActionPerformed
        String date_phieuxuat = txt_date_PX.getText().trim();
        String Kho = cbb_kho_PX.getSelectedItem().toString();
        if (date_phieuxuat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidDate(date_phieuxuat)) {
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ!(Ngày/Tháng/Năm)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int MaKho = getIDKho(Kho);
        String nx = convertDateFormat(date_phieuxuat);
        Date ngayXuat = convertStringToSqlDate(nx);

        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            String query = "INSERT INTO PhieuXuat (MaNV, MaKho, NgayXuat) VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, MaNV);
            pstmt.setInt(2, MaKho);
            pstmt.setDate(3, ngayXuat);
            pstmt.executeUpdate();
            con.close();

            modelTable_PhieuXuat.setRowCount(0);
            loadDataToTable_PhieuXuat();
            JOptionPane.showMessageDialog(this, "Đã thêm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            txt_date_PX.setText("");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_them_PhieuXuat_ActionPerformed

    private void btn_new_PhieuXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_new_PhieuXuatActionPerformed
        txt_date_PX.setText(getDateNow());
        table_phieuxuat.clearSelection();
    }//GEN-LAST:event_btn_new_PhieuXuatActionPerformed

    private void btn_them_CTPN_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them_CTPN_ActionPerformed
        int selectedRow = table_phieuxuat.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng ở phiếu xuất để thêm!.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int maPN = Integer.parseInt(table_phieuxuat.getValueAt(selectedRow, 0).toString());
        String sanpham = cbb_sanpham_CTPX.getSelectedItem().toString();
        String soluong = txt_soluong_CTPX.getText().trim();

        if (soluong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumeric(soluong)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int maSP = getIDProduct(sanpham);
        int soluongSP = Integer.parseInt(soluong);
        
        if(isIDProduct_Exist(maSP)){
            JOptionPane.showMessageDialog(this, "Sản phẩm đã thêm vào chi tiết phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            String query = "INSERT INTO ChiTietPhieuXuat (MaPhieuXuat, MaSP, SoLuong) VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, maPN);
            pstmt.setInt(2, maSP);
            pstmt.setInt(3, soluongSP);
            pstmt.executeUpdate();
            con.close();

            modelTable_CT_PhieuXuat.setRowCount(0);
            loadTable_CT_PhieuNhap_MaPN();
            JOptionPane.showMessageDialog(this, "Đã thêm Chi Tiết Phiếu Xuất thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            txt_soluong_CTPX.setText("");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_them_CTPN_ActionPerformed
    private boolean isIDProduct_Exist(int newIDProduct) {
        boolean exists = false;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ChiTietPhieuXuat WHERE MaSP = ?");
            pstmt.setInt(1, newIDProduct);            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                exists = true; // IDProduct exist in database ChiTietPhieuXuat
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }
    private void btn_new_CTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_new_CTPNActionPerformed
        txt_soluong_CTPX.setText("");
        table_CTphieuxuat.clearSelection();
    }//GEN-LAST:event_btn_new_CTPNActionPerformed

    private void btn_xoa_CTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa_CTPNActionPerformed
        int selectedRow = table_CTphieuxuat.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        
        int MaPX = Integer.parseInt(table_CTphieuxuat.getValueAt(selectedRow, 0).toString());
        
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "DELETE FROM ChiTietPhieuXuat WHERE MaPhieuXuat = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setInt(1, MaPX);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    modelTable_CT_PhieuXuat.setRowCount(0);
                    loadDataToTable_CT_PhieuXuat();

                    txt_soluong_CTPX.setText("1");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa Kho", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_xoa_CTPNActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QL_PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int defaultMaNV = -1;
                new QL_PhieuXuat(defaultMaNV).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_new_CTPN;
    private javax.swing.JButton btn_new_PhieuXuat;
    private javax.swing.JButton btn_them_CTPN_;
    private javax.swing.JButton btn_them_PhieuXuat_;
    private javax.swing.JButton btn_xoa_CTPN;
    private javax.swing.JComboBox<String> cbb_kho_PX;
    private javax.swing.JComboBox<String> cbb_sanpham_CTPX;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable table_CTphieuxuat;
    private javax.swing.JTable table_phieuxuat;
    private javax.swing.JTextField txt_date_PX;
    private javax.swing.JTextField txt_soluong_CTPX;
    // End of variables declaration//GEN-END:variables
}
