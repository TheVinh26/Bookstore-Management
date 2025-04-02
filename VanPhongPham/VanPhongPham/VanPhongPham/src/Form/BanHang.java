package Form;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class BanHang extends javax.swing.JFrame {

    private int maNV;
    private int maKH;

    public BanHang(int maNV) throws SQLException {
        this.maNV = maNV;
        initComponents();
        initTable();
        loadDataToTable();
        initTableCT();
        txt_MaNV.setText(String.valueOf(maNV));
        txt_MaNV.setDisabledTextColor(Color.black);
        txt_MaKH.setDisabledTextColor(Color.black);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
        txt_MaKH.setText(String.valueOf(maKH));
    }

    private DefaultTableModel model = new DefaultTableModel();

    private void initTable() {
        String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Giá bán", "Nhà cung cấp", "Số lượng tồn"};
        model = new DefaultTableModel(headers, 0);
        tblSanPham.setModel(model);
    }

    private void initTableCT() {
        String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "số lượng", "Đơn giá", "Thành tiền"};
        model = new DefaultTableModel(headers, 0);
        tblChiTietHoaDon.setModel(model);
    }

    private void loadDataToTable() throws SQLException {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SP.MaSP, SP.TenSP, LSP.TenLoai, SP.GiaBan, NCC.TenNCC, SPT.SoLuongTon "
                    + "FROM SanPham SP "
                    + "INNER JOIN LoaiSP LSP ON SP.MaLoai = LSP.MaLoai "
                    + "INNER JOIN NCC ON SP.MaNCC = NCC.MaNCC "
                    + "LEFT JOIN SanPhamTon SPT ON SP.MaSP = SPT.MaSP");
            model.setRowCount(0);
            while (rs.next()) {
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                String loaiSP = rs.getString("TenLoai");
                String giaBan = rs.getString("GiaBan");
                String tenNCC = rs.getString("TenNCC");
                int soLuongTon = rs.getInt("SoLuongTon");
                model.addRow(new Object[]{maSP, tenSP, loaiSP, giaBan, tenNCC, soLuongTon});
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void resetCTSP() {

        TableColumnModel columnModel = tblChiTietHoaDon.getColumnModel();
        while (columnModel.getColumnCount() > 0) {
            columnModel.removeColumn(columnModel.getColumn(0));
        }
        initTableCT();
    }

    //Tính tổng tiền : 
    private double tinhTongTien() {
        double tongTien = 0;
        DefaultTableModel chiTietHoaDonModel = (DefaultTableModel) tblChiTietHoaDon.getModel();

        int rowCount = chiTietHoaDonModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tongTien += (double) chiTietHoaDonModel.getValueAt(i, 4); // Cột "Thành Tiền" là cột thứ 5 (chỉ số 4)
        }

        return tongTien;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txt_MaNV = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_MaSP = new javax.swing.JTextField();
        txt_TenSP = new javax.swing.JTextField();
        txt_GiaBan = new javax.swing.JTextField();
        txt_SoLuongTon = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        btnThemDonHang = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietHoaDon = new javax.swing.JTable();
        btnTaoHoaDon = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        btn_xoaCTDH = new javax.swing.JButton();
        btn_new_ = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_MaKH = new javax.swing.JTextField();
        btnNhapKhachHang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel3.setText("Mã nhân viên");

        txt_MaNV.setEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 24))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel4.setText("Mã sản phẩm");

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel5.setText("Tên sản phẩm");

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel6.setText("Giá bán");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel7.setText("Số lượng tồn ");

        txt_MaSP.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txt_MaSP.setEnabled(false);

        txt_TenSP.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txt_TenSP.setEnabled(false);

        txt_GiaBan.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txt_GiaBan.setEnabled(false);

        txt_SoLuongTon.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txt_SoLuongTon.setEnabled(false);
        txt_SoLuongTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoLuongTonActionPerformed(evt);
            }
        });

        txt_SoLuong.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txt_SoLuong.setText("1");

        btnThemDonHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThemDonHang.setForeground(new java.awt.Color(0, 153, 153));
        btnThemDonHang.setText("Thêm");
        btnThemDonHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnThemDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDonHangActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel10.setText("Số lượng ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_MaSP))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_SoLuongTon)
                            .addComponent(txt_SoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenSP)
                            .addComponent(txt_GiaBan))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(207, Short.MAX_VALUE)
                    .addComponent(btnThemDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(135, 135, 135)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_SoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(328, Short.MAX_VALUE)
                    .addComponent(btnThemDonHang)
                    .addGap(23, 23, 23)))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel9.setText("Tìm sản phẩm");

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 153, 153));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txt_search.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 18))); // NOI18N

        tblSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblSanPham.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.setFillsViewportHeight(true);
        tblSanPham.setRowHeight(24);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1413, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 18))); // NOI18N

        tblChiTietHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tblChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblChiTietHoaDon);

        btnTaoHoaDon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnTaoHoaDon.setForeground(new java.awt.Color(0, 153, 153));
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel8.setText("Tổng tiền :");

        lbTongTien.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lbTongTien.setEnabled(false);

        btn_xoaCTDH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_xoaCTDH.setForeground(new java.awt.Color(0, 153, 153));
        btn_xoaCTDH.setText("Xóa dòng");
        btn_xoaCTDH.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_xoaCTDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaCTDHActionPerformed(evt);
            }
        });

        btn_new_.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new_.setForeground(new java.awt.Color(0, 153, 153));
        btn_new_.setText("New");
        btn_new_.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_new_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_new_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_new_, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_xoaCTDH, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon)
                    .addComponent(jLabel8)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoaCTDH)
                    .addComponent(btn_new_))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel11.setText("Mã khách hàng");

        txt_MaKH.setEnabled(false);

        btnNhapKhachHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNhapKhachHang.setForeground(new java.awt.Color(0, 153, 153));
        btnNhapKhachHang.setText("Nhập khách hàng");
        btnNhapKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNhapKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(180, 180, 180)
                                .addComponent(btnNhapKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnNhapKhachHang))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_SoLuongTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoLuongTonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoLuongTonActionPerformed

    private void btnThemDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDonHangActionPerformed
        if (txt_MaKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập thông tin khách hàng !", "Lỗi", JOptionPane.ERROR_MESSAGE);

            try {
                KhachHang kh = new KhachHang(maNV);
                kh.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
            return;

        }
        String maHD = txt_MaNV.getText();
        String maSP = txt_MaSP.getText();
        String tenSP = txt_TenSP.getText();
        String giaBan = txt_GiaBan.getText();
        String soLuong = txt_SoLuong.getText();
        // Tính tổng tiền
        double donGia = Double.parseDouble(giaBan);
        int soLuongInt = Integer.parseInt(soLuong);
        double thanhTien = donGia * soLuongInt;

        // Thêm dữ liệu vào bảng chi tiết hóa đơn
        DefaultTableModel chiTietHoaDonModel = (DefaultTableModel) tblChiTietHoaDon.getModel();
        chiTietHoaDonModel.addRow(new Object[]{maSP, tenSP, soLuongInt, donGia, thanhTien});

        txt_SoLuong.setText("1");
        double tongTien = tinhTongTien();
        lbTongTien.setText(String.valueOf(tongTien));
    }//GEN-LAST:event_btnThemDonHangActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchText = txt_search.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm để tìm kiếm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            Connection con = Datahelper.openConnection();
            String query = "SELECT SP.MaSP, SP.TenSP, LSP.TenLoai, SP.GiaBan, NCC.TenNCC, SPT.SoLuongTon "
                    + "FROM SanPham SP "
                    + "INNER JOIN LoaiSP LSP ON SP.MaLoai = LSP.MaLoai "
                    + "INNER JOIN NCC ON SP.MaNCC = NCC.MaNCC "
                    + "LEFT JOIN SanPhamTon SPT ON SP.MaSP = SPT.MaSP "
                    + "WHERE SP.TenSP LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%" + searchText + "%");
            ResultSet rs = pstmt.executeQuery();

            model.setRowCount(0);
            while (rs.next()) {
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                String loaiSP = rs.getString("TenLoai");
                String giaBan = rs.getString("GiaBan");
                String tenNCC = rs.getString("TenNCC");
                int soLuongTon = rs.getInt("SoLuongTon");
                model.addRow(new Object[]{maSP, tenSP, loaiSP, giaBan, tenNCC, soLuongTon});
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm nào.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_search.setText("");
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int r = tblSanPham.getSelectedRow();
        if (r < 0) {
            return;
        }
        txt_MaSP.setText(tblSanPham.getValueAt(r, 0).toString());
        txt_TenSP.setText(tblSanPham.getValueAt(r, 1).toString());
        txt_GiaBan.setText(tblSanPham.getValueAt(r, 3).toString());
        txt_SoLuongTon.setText(tblSanPham.getValueAt(r, 5).toString());
        txt_MaSP.setDisabledTextColor(Color.black);
        txt_TenSP.setDisabledTextColor(Color.black);
        txt_GiaBan.setDisabledTextColor(Color.black);
        txt_SoLuongTon.setDisabledTextColor(Color.black);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed

        String maKHText = txt_MaKH.getText();
        String tongTienText = lbTongTien.getText();

        try {
            Connection conn = Datahelper.openConnection();
            conn.setAutoCommit(false);

            String sqlHoaDon = "INSERT INTO HoaDon (MaNV, MaKH, TongTien) OUTPUT INSERTED.MaHoaDon VALUES (?, ?, ?)";
            PreparedStatement pstmtHoaDon = conn.prepareStatement(sqlHoaDon);

            double tongTien = Double.parseDouble(tongTienText);

            pstmtHoaDon.setInt(1, maNV);
            pstmtHoaDon.setInt(2, maKH);
            pstmtHoaDon.setDouble(3, tongTien);

            ResultSet rs = pstmtHoaDon.executeQuery();
            int maHoaDon = 0;
            if (rs.next()) {
                maHoaDon = rs.getInt(1);
            }

            String sqlChiTietHoaDon = "INSERT INTO ChiTietHoaDon (MaHoaDon, MaSP, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmtChiTietHoaDon = conn.prepareStatement(sqlChiTietHoaDon);

            DefaultTableModel chiTietHoaDonModel = (DefaultTableModel) tblChiTietHoaDon.getModel();
            int rowCount = chiTietHoaDonModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                int maSP = Integer.parseInt(chiTietHoaDonModel.getValueAt(i, 0).toString());
                int soLuong = Integer.parseInt(chiTietHoaDonModel.getValueAt(i, 2).toString());
                double donGia = Double.parseDouble(chiTietHoaDonModel.getValueAt(i, 3).toString());

                pstmtChiTietHoaDon.setInt(1, maHoaDon);
                pstmtChiTietHoaDon.setInt(2, maSP);
                pstmtChiTietHoaDon.setInt(3, soLuong);
                pstmtChiTietHoaDon.setDouble(4, donGia);
                pstmtChiTietHoaDon.addBatch();
            }
            pstmtChiTietHoaDon.executeBatch();

            conn.commit(); // Commit giao dịch

            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            txt_MaKH.setText("");
            lbTongTien.setText("0");

            resetCTSP();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tạo hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btn_xoaCTDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaCTDHActionPerformed

        DefaultTableModel chiTietHoaDonModel = (DefaultTableModel) tblChiTietHoaDon.getModel();

        int selectedRow = tblChiTietHoaDon.getSelectedRow();

        if (selectedRow != -1) {
            chiTietHoaDonModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
        }
        double tongTien = tinhTongTien();
        lbTongTien.setText(String.valueOf(tongTien));
    }//GEN-LAST:event_btn_xoaCTDHActionPerformed

    private void btnNhapKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapKhachHangActionPerformed
        try {
            KhachHang kh = new KhachHang(maNV);
            kh.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnNhapKhachHangActionPerformed

    private void btn_new_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_new_ActionPerformed
        model.setRowCount(0);
    }//GEN-LAST:event_btn_new_ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int defaultMaNV = -1; // Giá trị mặc định cho maNV
                    new BanHang(defaultMaNV).setVisible(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNhapKhachHang;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThemDonHang;
    private javax.swing.JButton btn_new_;
    private javax.swing.JButton btn_xoaCTDH;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JTable tblChiTietHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txt_GiaBan;
    private javax.swing.JTextField txt_MaKH;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_MaSP;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_SoLuongTon;
    private javax.swing.JTextField txt_TenSP;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
