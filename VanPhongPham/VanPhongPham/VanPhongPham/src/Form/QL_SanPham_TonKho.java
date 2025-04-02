package Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QL_SanPham_TonKho extends javax.swing.JFrame {
    private DefaultTableModel modelTableSPTon;
    private DefaultComboBoxModel<String> modelCbo_SP;
    private DefaultComboBoxModel<String> modelCbo_Kho;
    public QL_SanPham_TonKho() {
        initComponents();
        initCbbSanPham();
        initCbbKho();
        initTable();
        loadDataToTableSPTon();   
        cbb_kho_spTon.setEnabled(false);
        cbb_sanpham_spTon.setEnabled(false);
    }
    private void initCbbSanPham() {
        try {
            modelCbo_SP = new DefaultComboBoxModel<>();
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SanPham.TenSP FROM SanPham");

            cbb_sanpham_spTon.removeAllItems();

            while (rs.next()) {
                String tenSP = rs.getString("TenSP");
                cbb_sanpham_spTon.addItem(tenSP);
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void initCbbKho() {
        try {
            modelCbo_Kho = new DefaultComboBoxModel<>();
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Kho.TenKho FROM Kho");

            cbb_kho_spTon.removeAllItems();

            while (rs.next()) {
                String tenSP = rs.getString("TenKho");
                cbb_kho_spTon.addItem(tenSP);
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void initTable() {
        String[] headers = {"Sản Phẩm", "Kho", "Số Lượng"};
        modelTableSPTon = new DefaultTableModel(headers, 0);
        table_spTon.setModel(modelTableSPTon);
    }

    private void loadDataToTableSPTon() {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT S.TenSP, K.TenKho, SPT.SoLuongTon FROM SanPhamTon SPT INNER JOIN SanPham S ON S.MaSP = SPT.MaSP INNER JOIN Kho K ON K.MaKho = SPT.MaKho");

            while (rs.next()) {
                String TenSP = rs.getString("TenSP");
                String TenKho = rs.getString("TenKho");
                int SoLuongTon = rs.getInt("SoLuongTon");
                modelTableSPTon.addRow(new Object[]{TenSP, TenKho, SoLuongTon});
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
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_soluong_spTon = new javax.swing.JTextField();
        btn_new_spTon = new javax.swing.JButton();
        btn_them_spTon = new javax.swing.JButton();
        btn_sua_spTon = new javax.swing.JButton();
        btn_xoa_spTon = new javax.swing.JButton();
        cbb_sanpham_spTon = new javax.swing.JComboBox<>();
        cbb_kho_spTon = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_search_spTon = new javax.swing.JTextField();
        btn_search_spTon = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_spTon = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("Quản Lý Sản Phẩm Tồn Kho");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Số Lượng");

        txt_soluong_spTon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btn_new_spTon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new_spTon.setForeground(new java.awt.Color(0, 153, 153));
        btn_new_spTon.setText("New");
        btn_new_spTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_new_spTonActionPerformed(evt);
            }
        });

        btn_them_spTon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_them_spTon.setForeground(new java.awt.Color(0, 153, 153));
        btn_them_spTon.setText("Thêm");
        btn_them_spTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them_spTonActionPerformed(evt);
            }
        });

        btn_sua_spTon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_sua_spTon.setForeground(new java.awt.Color(0, 153, 153));
        btn_sua_spTon.setText("Sửa");
        btn_sua_spTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua_spTonActionPerformed(evt);
            }
        });

        btn_xoa_spTon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_xoa_spTon.setForeground(new java.awt.Color(0, 153, 153));
        btn_xoa_spTon.setText("Xóa");
        btn_xoa_spTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa_spTonActionPerformed(evt);
            }
        });

        cbb_sanpham_spTon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbb_sanpham_spTon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbb_kho_spTon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbb_kho_spTon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Kho");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Sản phẩm");

        txt_search_spTon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btn_search_spTon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_search_spTon.setForeground(new java.awt.Color(0, 153, 153));
        btn_search_spTon.setText("Tìm kiếm");
        btn_search_spTon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_spTonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Tìm kiếm sản phẩm");

        table_spTon.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        table_spTon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sản Phẩm", "Kho", "Số lượng"
            }
        ));
        table_spTon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_spTonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_spTon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(cbb_sanpham_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cbb_kho_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_soluong_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search_spTon)))
                .addGap(165, 165, 165))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(535, 535, 535)
                        .addComponent(btn_new_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_them_spTon)
                        .addGap(18, 18, 18)
                        .addComponent(btn_sua_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_xoa_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1402, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_spTon)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbb_sanpham_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbb_kho_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_soluong_spTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them_spTon)
                    .addComponent(btn_sua_spTon)
                    .addComponent(btn_xoa_spTon)
                    .addComponent(btn_new_spTon))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(491, 491, 491)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_new_spTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_new_spTonActionPerformed
        table_spTon.clearSelection();
        txt_soluong_spTon.setText("");
        cbb_kho_spTon.setEnabled(true);
        cbb_sanpham_spTon.setEnabled(true);
    }//GEN-LAST:event_btn_new_spTonActionPerformed
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
    private int getIDProduct(String name){ //Hàm không sử dụng
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
    private int getIDKho(String name){ //Hàm không sử dụng
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
    private boolean isIDProduct_IDKho_Exist(int newIDProduct, int newIDKho) {
        boolean exists = false;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM SanPhamTon WHERE MaSP = ? AND MaKho = ?");
            pstmt.setInt(1, newIDProduct);
            pstmt.setInt(2, newIDKho);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                exists = true; // IDProduct exist in database
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }
    private void addDataToDatabaseSanPhamTon( int MaSP, int MaKho, int SoLuongTon) {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();           
            String query = "INSERT INTO SanPhamTon (MaSP, MaKho, SoLuongTon) VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, MaSP);
            pstmt.setInt(2, MaKho);
            pstmt.setInt(3, SoLuongTon);
            pstmt.executeUpdate();
            con.close();

            modelTableSPTon.setRowCount(0);
            loadDataToTableSPTon();
            JOptionPane.showMessageDialog(this, "Đã thêm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            
            txt_soluong_spTon.setText("");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void btn_them_spTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them_spTonActionPerformed

        String SoLuong = txt_soluong_spTon.getText().trim();
        String SanPham = cbb_sanpham_spTon.getSelectedItem().toString();
        String Kho = cbb_kho_spTon.getSelectedItem().toString();
        if (SoLuong.isEmpty() || SanPham.isEmpty() || Kho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumeric(SoLuong)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng tồn là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int MaSP = getIDProduct(SanPham);
        int MaKho = getIDKho(Kho);
        int soluongTon = Integer.parseInt(SoLuong);
        
        if(isIDProduct_IDKho_Exist(MaSP, MaKho)){
            JOptionPane.showMessageDialog(this, "Sản phẩm và Kho đã thêm vào hệ thống, vui lòng chỉnh sửa số lượng trên bảng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        addDataToDatabaseSanPhamTon(MaSP, MaKho, soluongTon);
    }//GEN-LAST:event_btn_them_spTonActionPerformed

    private void btn_sua_spTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua_spTonActionPerformed

        int selectedRow = table_spTon.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sanPham = table_spTon.getValueAt(selectedRow, 0).toString();
        int MaSP = getIDProduct(sanPham);
        
        String soluong = txt_soluong_spTon.getText().trim();
        int soluongTon = Integer.parseInt(soluong);
        
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật số lượng sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "UPDATE SanPhamTon SET SoLuongTon = ? WHERE MaSP = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setInt(1,  soluongTon);
                pstmt.setInt(2, MaSP);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã cập nhật số lượng sản phẩm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    //modelTableSPTon = (DefaultTableModel) table_spTon.getModel();
                    modelTableSPTon.setValueAt(soluongTon, selectedRow, 2);

                    txt_soluong_spTon.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật số lượng sản phẩm không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật số lượng sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_sua_spTonActionPerformed

    private void btn_xoa_spTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa_spTonActionPerformed

        int selectedRow = table_spTon.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        
        String sanPham = table_spTon.getValueAt(selectedRow, 0).toString();
        int MaSP = getIDProduct(sanPham);
        
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "DELETE FROM SanPhamTon WHERE MaSP = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setInt(1, MaSP);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    modelTableSPTon.setRowCount(0);
                    loadDataToTableSPTon();

                    txt_soluong_spTon.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa Kho", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_xoa_spTonActionPerformed

    private void table_spTonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_spTonMouseClicked
        int r = table_spTon.getSelectedRow();
        if (r < 0) {
            return;
        }
        cbb_sanpham_spTon.setSelectedItem(table_spTon.getValueAt(r, 0));
        cbb_kho_spTon.setSelectedItem(table_spTon.getValueAt(r, 1));
        txt_soluong_spTon.setText(table_spTon.getValueAt(r, 2).toString());
        cbb_kho_spTon.setEnabled(false);
        cbb_sanpham_spTon.setEnabled(false);
        //cbb_kho_spTon.setcolo;
        cbb_sanpham_spTon.setEnabled(false);
    }//GEN-LAST:event_table_spTonMouseClicked

    private void btn_search_spTonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_spTonActionPerformed
        searchAndUpdateTableSanPhamTon(txt_search_spTon.getText().trim());
    }//GEN-LAST:event_btn_search_spTonActionPerformed
    private void searchAndUpdateTableSanPhamTon(String tieuDe) {
        try {
            Connection con = Datahelper.openConnection();
            String sql = "SELECT S.TenSP, K.TenKho, SPT.SoLuongTon FROM SanPhamTon SPT INNER JOIN SanPham S ON S.MaSP = SPT.MaSP INNER JOIN Kho K ON K.MaKho = SPT.MaKho WHERE S.TenSP LIKE ?";            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + tieuDe + "%");
            ResultSet rs = pstmt.executeQuery();

            modelTableSPTon.setRowCount(0);

            while (rs.next()) {
                String TenSP = rs.getString("TenSP");
                String TenKho = rs.getString("TenKho");
                int SoLuongTon = rs.getInt("SoLuongTon");
                modelTableSPTon.addRow(new Object[]{TenSP, TenKho, SoLuongTon});
            }
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
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
            java.util.logging.Logger.getLogger(QL_SanPham_TonKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_SanPham_TonKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_SanPham_TonKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_SanPham_TonKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_SanPham_TonKho().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_new_spTon;
    private javax.swing.JButton btn_search_spTon;
    private javax.swing.JButton btn_sua_spTon;
    private javax.swing.JButton btn_them_spTon;
    private javax.swing.JButton btn_xoa_spTon;
    private javax.swing.JComboBox<String> cbb_kho_spTon;
    private javax.swing.JComboBox<String> cbb_sanpham_spTon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_spTon;
    private javax.swing.JTextField txt_search_spTon;
    private javax.swing.JTextField txt_soluong_spTon;
    // End of variables declaration//GEN-END:variables
}
