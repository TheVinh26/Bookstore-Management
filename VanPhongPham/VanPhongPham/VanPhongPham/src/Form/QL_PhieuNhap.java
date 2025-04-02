package Form;

import java.awt.Color;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class QL_PhieuNhap extends javax.swing.JFrame {

    private int MaNV;
    private DefaultTableModel modelTableCTPhieuNhap;
    private DefaultComboBoxModel<String> modelCbb_NCC;
    private DefaultComboBoxModel<String> modelCbb_Kho;
    private DefaultComboBoxModel<String> modelCbb_SanPham;

    public QL_PhieuNhap(int MaNV) {
        this.MaNV = MaNV;
        initComponents();
        initCbbNCC();
        initCbbSanPham();
        initCbbKho();
        initTable();

        txt_nhanvien.setText(getNameNhanVien(MaNV));
        txt_nhanvien.setEditable(false);

        String nowDate = getDateNow();
        txt_ngaylap.setText(nowDate);
        txt_ngaylap.setEditable(false);

        txt_tongtien_PN.setEditable(false);

        txt_soluong.setText("1");
    }

    private void initCbbNCC() {
        try {
            modelCbb_NCC = new DefaultComboBoxModel<>();
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NCC.TenNCC FROM NCC");

            cbb_ncc.removeAllItems();

            while (rs.next()) {
                String tenSP = rs.getString("TenNCC");
                cbb_ncc.addItem(tenSP);
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initCbbSanPham() {
        try {
            modelCbb_SanPham = new DefaultComboBoxModel<>();
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SanPham.TenSP FROM SanPham");

            cbb_sanpham.removeAllItems();

            while (rs.next()) {
                String tenSP = rs.getString("TenSP");
                cbb_sanpham.addItem(tenSP);
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initCbbKho() {
        try {
            modelCbb_Kho = new DefaultComboBoxModel<>();
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Kho.TenKho FROM Kho");

            cbb_kho.removeAllItems();

            while (rs.next()) {
                String tenSP = rs.getString("TenKho");
                cbb_kho.addItem(tenSP);
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initTable() {
        String[] headers = {"Sản Phẩm", "Số Lượng", "Giá Nhập", "Thành Tiền"};
        modelTableCTPhieuNhap = new DefaultTableModel(headers, 0);
        table_CTPN.setModel(modelTableCTPhieuNhap);
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
    private int getID_NCC(String name) {
        int maNCC = -1;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT MaNCC FROM NCC WHERE TenNCC = ?");
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maNCC = rs.getInt("MaNCC");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return maNCC;
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

    private String getDateNow() {
        Date currentSQLDate = new Date(System.currentTimeMillis());

        // Chuyển đổi java.sql.Date sang java.time.LocalDate
        LocalDate currentLocalDate = currentSQLDate.toLocalDate();

        // Định dạng ngày tháng năm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentLocalDate.format(formatter);
        return formattedDate;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbb_ncc = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbb_kho = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txt_ngaylap = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_tongtien_PN = new javax.swing.JTextField();
        txt_nhanvien = new javax.swing.JTextField();
        btn_TaoPhieuNhap = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_CTPN = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbb_sanpham = new javax.swing.JComboBox<>();
        txt_soluong = new javax.swing.JTextField();
        txt_gianhap = new javax.swing.JTextField();
        btn_them_CTPN = new javax.swing.JButton();
        btn_xoa_CTPN = new javax.swing.JButton();
        btn_sua_CTPN_ = new javax.swing.JButton();
        btn_new_CTPN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nhân viên");

        cbb_ncc.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbb_ncc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Nhà cung cấp");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Kho");

        cbb_kho.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbb_kho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Tổng tiền");

        txt_ngaylap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Ngày lập");

        txt_tongtien_PN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_tongtien_PN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tongtien_PNActionPerformed(evt);
            }
        });

        txt_nhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btn_TaoPhieuNhap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_TaoPhieuNhap.setForeground(new java.awt.Color(0, 153, 153));
        btn_TaoPhieuNhap.setText("Tạo phiếu nhập");
        btn_TaoPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoPhieuNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nhanvien, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbb_ncc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbb_kho, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tongtien_PN)
                            .addComponent(txt_ngaylap))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(217, Short.MAX_VALUE)
                .addComponent(btn_TaoPhieuNhap)
                .addGap(227, 227, 227))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbb_kho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cbb_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_ngaylap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tongtien_PN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_TaoPhieuNhap)
                .addGap(64, 64, 64))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("Quản Lý Phiếu Nhập");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        table_CTPN.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        table_CTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sản Phẩm", "Số Lượng", "Giá Nhập", "Tổng Tiền"
            }
        ));
        table_CTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_CTPNMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_CTPN);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Sản phẩm");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Số lượng");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Giá nhập");

        cbb_sanpham.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbb_sanpham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txt_soluong.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txt_gianhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btn_them_CTPN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_them_CTPN.setForeground(new java.awt.Color(0, 153, 153));
        btn_them_CTPN.setText("Thêm");
        btn_them_CTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them_CTPNActionPerformed(evt);
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

        btn_sua_CTPN_.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_sua_CTPN_.setForeground(new java.awt.Color(0, 153, 153));
        btn_sua_CTPN_.setText("Sửa");
        btn_sua_CTPN_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua_CTPN_ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txt_gianhap))
                            .addComponent(cbb_sanpham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(btn_new_CTPN)
                .addGap(18, 18, 18)
                .addComponent(btn_them_CTPN)
                .addGap(18, 18, 18)
                .addComponent(btn_xoa_CTPN)
                .addGap(18, 18, 18)
                .addComponent(btn_sua_CTPN_)
                .addContainerGap(195, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_sanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(txt_gianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them_CTPN)
                    .addComponent(btn_xoa_CTPN)
                    .addComponent(btn_sua_CTPN_)
                    .addComponent(btn_new_CTPN))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(477, 477, 477)
                        .addComponent(jLabel1)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tongtien_PNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tongtien_PNActionPerformed

    }//GEN-LAST:event_txt_tongtien_PNActionPerformed

    private void btn_them_CTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them_CTPNActionPerformed
        String sanpham = cbb_sanpham.getSelectedItem().toString();
        String soluong = txt_soluong.getText().trim();
        String giaNhap_s = txt_gianhap.getText().trim();

        if (soluong.isEmpty() || giaNhap_s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin Chi Tiết Phiếu Nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumeric(soluong)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumeric(giaNhap_s)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá nhập là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int soLuong = Integer.parseInt(soluong);
        int giaNhap = Integer.parseInt(giaNhap_s);
        int tongTien_CTPN = soLuong * giaNhap;

        if (sanPhamDaTonTai(sanpham, modelTableCTPhieuNhap)) {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đã có trong bảng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Thêm dữ liệu vào bảng chi tiết phiếu nhập
        modelTableCTPhieuNhap = (DefaultTableModel) table_CTPN.getModel();
        modelTableCTPhieuNhap.addRow(new Object[]{sanpham, soLuong, giaNhap, tongTien_CTPN});

        txt_soluong.setText("1");
        int tongTien = tinhTongTien();;
        txt_tongtien_PN.setText(String.valueOf(tongTien));
    }//GEN-LAST:event_btn_them_CTPNActionPerformed
    private int tinhTongTien() {
        int tongTien = 0;
        modelTableCTPhieuNhap = (DefaultTableModel) table_CTPN.getModel();

        int rowCount = modelTableCTPhieuNhap.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tongTien += (int) modelTableCTPhieuNhap.getValueAt(i, 3); // Cột "Thành Tiền" là cột thứ 5 (chỉ số 4)
        }

        return tongTien;
    }

    private boolean sanPhamDaTonTai(String tenSanPham, DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            String sanPhamTrongBang = model.getValueAt(i, 0).toString();
            if (sanPhamTrongBang.equals(tenSanPham)) {
                return true; // Sản phẩm đã tồn tại trong bảng
            }
        }
        return false; // Sản phẩm chưa tồn tại trong bảng
    }
    private void btn_xoa_CTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa_CTPNActionPerformed
        modelTableCTPhieuNhap = (DefaultTableModel) table_CTPN.getModel();

        int selectedRow = table_CTPN.getSelectedRow();

        if (selectedRow != -1) {
            modelTableCTPhieuNhap.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa.");
        }
        double tongTien = tinhTongTien();
        txt_tongtien_PN.setText(String.valueOf(tongTien));
    }//GEN-LAST:event_btn_xoa_CTPNActionPerformed
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
    private Date convertStringToSqlDate(String dateString) {
        // Định dạng chuỗi ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Chuyển chuỗi ngày tháng sang LocalDate
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        // Chuyển LocalDate sang java.sql.Date
        return Date.valueOf(localDate);
    }
    private void btn_TaoPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoPhieuNhapActionPerformed
        if (isTableEmpty(table_CTPN)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin chi tiết phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String NhaCungCap =  cbb_ncc.getSelectedItem().toString();
        String ngayLap = txt_ngaylap.getText();
        String tongTienText = txt_tongtien_PN.getText();
        String Kho =  cbb_kho.getSelectedItem().toString();
        
        int ID_NCC = getID_NCC(NhaCungCap);
        String nl = convertDateFormat(ngayLap);
        Date NgayLap = convertStringToSqlDate(nl);
        int tongTien = Integer.parseInt(tongTienText);
        int ID_Kho = getIDKho(Kho);
        
        try {
            Connection conn = Datahelper.openConnection();
            conn.setAutoCommit(false);

            String sqlPhieuNhap = "INSERT INTO PhieuNhap (MaNV, MaNCC, NgayLap, TongTien, MaKho) OUTPUT INSERTED.MaPhieuNhap VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlPhieuNhap);

            pstmt.setInt(1, MaNV);
            pstmt.setInt(2, ID_NCC);
            pstmt.setDate(3, NgayLap);
            pstmt.setDouble(4, tongTien);
            pstmt.setInt(5, ID_Kho);
            
            ResultSet rs = pstmt.executeQuery();
            int maPhieuNhap = 0;
            if (rs.next()) {
                maPhieuNhap = rs.getInt(1);
            }

            String sqlChiTietHoaDon = "INSERT INTO ChiTietPhieuNhap(MaPhieuNhap, MaSP, SoLuong, GiaNhap) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmtChiTietPhieuNhap = conn.prepareStatement(sqlChiTietHoaDon);

            modelTableCTPhieuNhap = (DefaultTableModel) table_CTPN.getModel();
            int rowCount = modelTableCTPhieuNhap.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                
                String sanPham = modelTableCTPhieuNhap.getValueAt(i, 0).toString();
                int maSP = getIDProduct(sanPham);
                
                int soLuong = Integer.parseInt(modelTableCTPhieuNhap.getValueAt(i, 1).toString());
                int giaNhap = Integer.parseInt(modelTableCTPhieuNhap.getValueAt(i, 2).toString());
                //int tongTien_CTPN = Integer.parseInt(modelTableCTPhieuNhap.getValueAt(i, 3).toString());

                pstmtChiTietPhieuNhap.setInt(1, maPhieuNhap);
                pstmtChiTietPhieuNhap.setInt(2, maSP);
                pstmtChiTietPhieuNhap.setInt(3, soLuong);
                pstmtChiTietPhieuNhap.setInt(4, giaNhap);
                //pstmtChiTietPhieuNhap.setInt(5, tongTien_CTPN);
                pstmtChiTietPhieuNhap.addBatch();
            }
            pstmtChiTietPhieuNhap.executeBatch();

            conn.commit();

            JOptionPane.showMessageDialog(this, "Tạo Phiếu Nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            txt_tongtien_PN.setText("0");
            txt_soluong.setText("1");
            txt_gianhap.setText("");

            resetCTPN();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tạo Phiếu Nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_TaoPhieuNhapActionPerformed
    private void resetCTPN() {

        TableColumnModel columnModel = table_CTPN.getColumnModel();
        while (columnModel.getColumnCount() > 0) {
            columnModel.removeColumn(columnModel.getColumn(0));
        }
        initTable();
    }
    private void table_CTPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_CTPNMouseClicked
        int r = table_CTPN.getSelectedRow();
        if (r < 0) {
            return;
        }
        cbb_sanpham.setSelectedItem(table_CTPN.getValueAt(r, 0));
        cbb_sanpham.setEnabled(false);
        cbb_sanpham.setForeground(Color.black);
        txt_soluong.setText(table_CTPN.getValueAt(r, 1).toString());
        txt_gianhap.setText(table_CTPN.getValueAt(r, 2).toString());
    }//GEN-LAST:event_table_CTPNMouseClicked

    private void btn_sua_CTPN_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua_CTPN_ActionPerformed
        int selectedRow = table_CTPN.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sanpham = cbb_sanpham.getSelectedItem().toString();
        String soluong = txt_soluong.getText().trim();
        String giaNhap_s = txt_gianhap.getText().trim();

        if (soluong.isEmpty() || giaNhap_s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin Chi Tiết Phiếu Nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumeric(soluong)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumeric(giaNhap_s)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá nhập là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int soLuong = Integer.parseInt(soluong);
        int giaNhap = Integer.parseInt(giaNhap_s);
        int tongTien_CTPN = soLuong * giaNhap;

        //modelTableCTPhieuNhap.setValueAt(sanpham, selectedRow, 0);
        modelTableCTPhieuNhap.setValueAt(soLuong, selectedRow, 1);
        modelTableCTPhieuNhap.setValueAt(giaNhap, selectedRow, 2);
        modelTableCTPhieuNhap.setValueAt(tongTien_CTPN, selectedRow, 3);

        JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        int tongTien = tinhTongTien();
        txt_tongtien_PN.setText(String.valueOf(tongTien));

    }//GEN-LAST:event_btn_sua_CTPN_ActionPerformed

    private void btn_new_CTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_new_CTPNActionPerformed
        table_CTPN.clearSelection();
        txt_soluong.setText("");
        txt_gianhap.setText("");
        cbb_sanpham.setEnabled(true);
        modelTableCTPhieuNhap.setRowCount(0);
    }//GEN-LAST:event_btn_new_CTPNActionPerformed
    private boolean isTableEmpty(JTable table) {
        return table != null && table.getModel() != null && table.getModel().getRowCount() == 0;
    }

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
            java.util.logging.Logger.getLogger(QL_PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int defaultMaNV = -1; // Giá trị mặc định cho maNV
                new QL_PhieuNhap(defaultMaNV).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_TaoPhieuNhap;
    private javax.swing.JButton btn_new_CTPN;
    private javax.swing.JButton btn_sua_CTPN_;
    private javax.swing.JButton btn_them_CTPN;
    private javax.swing.JButton btn_xoa_CTPN;
    private javax.swing.JComboBox<String> cbb_kho;
    private javax.swing.JComboBox<String> cbb_ncc;
    private javax.swing.JComboBox<String> cbb_sanpham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_CTPN;
    private javax.swing.JTextField txt_gianhap;
    private javax.swing.JTextField txt_ngaylap;
    private javax.swing.JTextField txt_nhanvien;
    private javax.swing.JTextField txt_soluong;
    private javax.swing.JTextField txt_tongtien_PN;
    // End of variables declaration//GEN-END:variables
}
