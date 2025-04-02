package Form;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class SanPham extends javax.swing.JFrame {

    public SanPham() throws SQLException {
        initComponents();
        initTable();
        initCBOLoaiSP();
        initCBONCC();
        loadDataToTable();
        txtMaSP.setDisabledTextColor(Color.black);
    }

    DefaultComboBoxModel<String> modelCbo;

    private void initCBOLoaiSP() {
        modelCbo = new DefaultComboBoxModel<>();
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TenLoai FROM LoaiSP");
            while (rs.next()) {
                String tenLoaiSanPham = rs.getString("TenLoai");
                modelCbo.addElement(tenLoaiSanPham);
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        cboLoaiSP.setModel(modelCbo);
    }

    private void initCBONCC() {
        modelCbo = new DefaultComboBoxModel<>();
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TenNCC FROM NCC");
            while (rs.next()) {
                String tenLoaiSanPham = rs.getString("TenNCC");
                modelCbo.addElement(tenLoaiSanPham);
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        cboNCC.setModel(modelCbo);
    }

    private DefaultTableModel model = new DefaultTableModel();

    private void initTable() {
        String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Giá bán", "Nhà cung cấp"};
        model = new DefaultTableModel(headers, 0);
        tblSanPham.setModel(model);
    }

    private void loadDataToTable() throws SQLException {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SP.MaSP, SP.TenSP, LSP.TenLoai, SP.GiaBan, NCC.TenNCC "
                    + "FROM SanPham SP "
                    + "INNER JOIN LoaiSP LSP ON SP.MaLoai = LSP.MaLoai "
                    + "INNER JOIN NCC ON SP.MaNCC = NCC.MaNCC");
            model.setRowCount(0);
            while (rs.next()) {
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                String loaiSP = rs.getString("TenLoai");
                String giaBan = rs.getString("GiaBan");
                String tenNCC = rs.getString("TenNCC");
                model.addRow(new Object[]{maSP, tenSP, loaiSP, giaBan, tenNCC});
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lb = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        btnThem2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        btnSua2 = new javax.swing.JButton();
        cboLoaiSP = new javax.swing.JComboBox<>();
        cboNCC = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        btnUpLoad = new javax.swing.JButton();
        btnThemLoai = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        lblImagePath = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("QUẢN LÍ SẢN PHẨM");

        lb.setBackground(new java.awt.Color(255, 255, 255));
        lb.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 2, 18))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Mã sản phẩm");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Tên sản phẩm");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Giá bán");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Loại sản phẩm");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Nhà cung cấp");

        txtMaSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtMaSP.setEnabled(false);

        txtTenSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtGiaBan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btnThem2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem2.setForeground(new java.awt.Color(0, 153, 153));
        btnThem2.setText("Thêm");
        btnThem2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnXoa2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa2.setForeground(new java.awt.Color(0, 153, 153));
        btnXoa2.setText("Xóa");
        btnXoa2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        btnSua2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua2.setForeground(new java.awt.Color(0, 153, 153));
        btnSua2.setText("Sửa");
        btnSua2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        cboLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboNCC.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cboNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Ảnh sản phẩm");

        btnUpLoad.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnUpLoad.setText("Upload");
        btnUpLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpLoadActionPerformed(evt);
            }
        });

        btnThemLoai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThemLoai.setForeground(new java.awt.Color(0, 153, 153));
        btnThemLoai.setText("Thêm Loại Sản Phẩm");
        btnThemLoai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnThemLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(0, 153, 153));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        lblImagePath.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblImagePath.setText("Ảnh sản phẩm");

        javax.swing.GroupLayout lbLayout = new javax.swing.GroupLayout(lb);
        lb.setLayout(lbLayout);
        lbLayout.setHorizontalGroup(
            lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(34, 34, 34)
                .addGroup(lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagePath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(lbLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(lbLayout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(lbLayout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(112, 112, 112)
                .addGroup(lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(112, 112, 112)
                .addComponent(btnThemLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(472, 472, 472))
        );
        lbLayout.setVerticalGroup(
            lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem2)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemLoai))
                .addGap(29, 29, 29)
                .addGroup(lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa2)
                    .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua2)
                    .addComponent(jLabel19)
                    .addComponent(btnUpLoad))
                .addGroup(lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnLamMoi))
                    .addGroup(lbLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblImagePath)))
                .addContainerGap(197, Short.MAX_VALUE))
        );

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

        txt_search.setBorder(null);

        jLabel3.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jLabel3.setText("Tìm kiếm");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(241, 241, 241)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(33, 33, 33)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        String TenNCC = cboNCC.getSelectedItem().toString();
        String TenLoai = cboLoaiSP.getSelectedItem().toString();

        String GiaBan = txtGiaBan.getText();
        String TenSP = txtTenSP.getText();
        String imagepath = lblImagePath.getText();

        if (GiaBan.isEmpty() || TenSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection con = Datahelper.openConnection();

            String sqlMaNCC = "SELECT MaNCC FROM NCC WHERE TenNCC = ?";
            PreparedStatement pstmtMaNCC = con.prepareStatement(sqlMaNCC);
            pstmtMaNCC.setString(1, TenNCC);
            ResultSet rsMaNCC = pstmtMaNCC.executeQuery();
            int MaNCC = 0;
            if (rsMaNCC.next()) {
                MaNCC = rsMaNCC.getInt("MaNCC");
            }

            String sqlMaLoai = "SELECT MaLoai FROM LoaiSP WHERE TenLoai = ?";
            PreparedStatement pstmtMaLoai = con.prepareStatement(sqlMaLoai);
            pstmtMaLoai.setString(1, TenLoai);
            ResultSet rsMaLoai = pstmtMaLoai.executeQuery();
            int MaLoai = 0;
            if (rsMaLoai.next()) {
                MaLoai = rsMaLoai.getInt("MaLoai");
            }
            String sql = "INSERT INTO SanPham (MaNCC, MaLoai, GiaBan, TenSP,ImageSP) VALUES (?, ?, ?, ?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, MaNCC);
            pstmt.setInt(2, MaLoai);
            pstmt.setString(3, GiaBan);
            pstmt.setString(4, TenSP);
            pstmt.setString(5, imagepath);

            pstmt.executeUpdate();

            con.close();

            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            txtTenSP.setText("");
            txtGiaBan.setText("");

            loadDataToTable();
        } catch (SQLException | ClassNotFoundException ex) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        model.fireTableDataChanged();
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String masanpham = tblSanPham.getValueAt(selectedRow, 0).toString();

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "DELETE FROM SanPham WHERE MaSP = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setString(1, masanpham);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã xóa sản phẩm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int r = tblSanPham.getSelectedRow();
        if (r < 0) {
            return;
        }
        txtMaSP.setText(tblSanPham.getValueAt(r, 0).toString());
        txtTenSP.setText(tblSanPham.getValueAt(r, 1).toString());
        cboLoaiSP.setSelectedItem(tblSanPham.getValueAt(r, 2));
        txtGiaBan.setText(tblSanPham.getValueAt(r, 3).toString());
        //txtDiaChi.setText(tblNhanVien.getValueAt(r, 4).toString());
        cboNCC.setSelectedItem(tblSanPham.getValueAt(r, 4));

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        // TODO add your handling code here:
        String TenNCC = cboNCC.getSelectedItem().toString();
        String TenLoai = cboLoaiSP.getSelectedItem().toString();

        String GiaBan = txtGiaBan.getText();
        String TenSP = txtTenSP.getText();

        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String maLoai = tblSanPham.getValueAt(selectedRow, 0).toString();

        try {
            Connection con = Datahelper.openConnection();

            String sqlMaNCC = "SELECT MaNCC FROM NCC WHERE TenNCC = ?";
            PreparedStatement pstmtMaNCC = con.prepareStatement(sqlMaNCC);
            pstmtMaNCC.setString(1, TenNCC);
            ResultSet rsMaNCC = pstmtMaNCC.executeQuery();
            int MaNCC = 0;
            if (rsMaNCC.next()) {
                MaNCC = rsMaNCC.getInt("MaNCC");
            }

            String sqlMaLoai = "SELECT MaLoai FROM LoaiSP WHERE TenLoai = ?";
            PreparedStatement pstmtMaLoai = con.prepareStatement(sqlMaLoai);
            pstmtMaLoai.setString(1, TenLoai);
            ResultSet rsMaLoai = pstmtMaLoai.executeQuery();
            int MaLoai = 0;
            if (rsMaLoai.next()) {
                MaLoai = rsMaLoai.getInt("MaLoai");
            }
            // SQL statement to update the SanPham table
            String sql = "UPDATE SanPham SET TenSP = ?, MaNCC = ?, MaLoai = ?, GiaBan = ?, ImageSP = ? WHERE MaSP = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

// Set the parameters for the prepared statement
            pstmt.setString(1, txtTenSP.getText()); // Set the product name
            pstmt.setInt(2, MaNCC); // Set the supplier ID
            pstmt.setInt(3, MaLoai); // Set the category ID
            pstmt.setBigDecimal(4, new BigDecimal(txtGiaBan.getText())); // Set the price
            String imagePath = null;
            pstmt.setString(5, imagePath);  // Set the image path
            pstmt.setInt(6, Integer.parseInt(txtMaSP.getText())); // Set the product ID (primary key)

// Execute the update
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Đã cập nhật sản phẩm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
                model.setValueAt(txtTenSP.getText(), selectedRow, 1);
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            con.close();
            loadDataToTable();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void btnThemLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiActionPerformed
        LoaiSanPham loaiSanPhamForm = null;
        try {
            loaiSanPhamForm = new LoaiSanPham();
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        loaiSanPhamForm.setVisible(true);
    }//GEN-LAST:event_btnThemLoaiActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        initTable();
        initCBOLoaiSP();
        initCBONCC();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnUpLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpLoadActionPerformed
        // Tạo hộp thoại chọn tệp
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Hiển thị hộp thoại và lấy kết quả
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();  // Lấy tệp đã chọn
            File destinationDir = new File("src/images");
            if (!destinationDir.exists()) {
                destinationDir.mkdir();  // Tạo thư mục nếu chưa tồn tại
            }

            // Tạo tệp đích trong thư mục images
            File destinationFile = new File(destinationDir, selectedFile.getName());
            try {
                // Sao chép tệp đã chọn vào thư mục đích
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Hiển thị đường dẫn của ảnh đã tải lên trên nhãn
                lblImagePath.setText(destinationFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi tải ảnh lên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpLoadActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        String searchText = txt_search.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm để tìm kiếm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            try {
                loadDataToTable();
            } catch (SQLException ex) {
                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        try {
            Connection con = Datahelper.openConnection();
            String query = "SELECT SP.MaSP, SP.TenSP, LSP.TenLoai, SP.GiaBan, NCC.TenNCC "
                    + "FROM SanPham SP "
                    + "INNER JOIN LoaiSP LSP ON SP.MaLoai = LSP.MaLoai "
                    + "INNER JOIN NCC ON SP.MaNCC = NCC.MaNCC "
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
                // int soLuongTon = rs.getInt("SoLuongTon"); // Uncomment if needed
                model.addRow(new Object[]{maSP, tenSP, loaiSP, giaBan, tenNCC});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_search.setText("");
    }//GEN-LAST:event_jLabel3MouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SanPham().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThemLoai;
    private javax.swing.JButton btnUpLoad;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lb;
    private javax.swing.JLabel lblImagePath;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
