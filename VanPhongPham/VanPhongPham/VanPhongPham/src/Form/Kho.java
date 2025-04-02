package Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Kho extends javax.swing.JFrame {

    private DefaultTableModel modelTableKho;

    public Kho() {
        initComponents();
        initTable();
        loadDataToTableKho();       
    }

    private void initTable() {
        String[] headers = {"Mã Kho", "Tên Kho", "Địa Chỉ"};
        modelTableKho = new DefaultTableModel(headers, 0);
        table_kho.setModel(modelTableKho);
    }

    private void loadDataToTableKho() {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Kho");

            while (rs.next()) {
                int MaKho = rs.getInt("MaKho");
                String tenKho = rs.getString("TenKho");
                String diaChi = rs.getString("DiaChi");
                modelTableKho.addRow(new Object[]{MaKho, tenKho, diaChi});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

//    private void initCbbSanPham() {
//        try {
//            modelCbo_SP = new DefaultComboBoxModel<>();
//            Connection con = Datahelper.openConnection();
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT SanPham.TenSP FROM SanPham");
//
//            cbb_sanpham_Kho.removeAllItems();
//
//            while (rs.next()) {
//                String tenSP = rs.getString("TenSP");
//                cbb_sanpham_Kho.addItem(tenSP);
//            }
//
//            con.close();
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_tenkho = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_diachi_Kho = new javax.swing.JTextField();
        btn_new_Kho = new javax.swing.JButton();
        btn_them_Kho = new javax.swing.JButton();
        btn_sua_Kho = new javax.swing.JButton();
        btn_xoa_Kho = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_kho = new javax.swing.JTable();
        btn_search_kho = new javax.swing.JButton();
        txt_search_kho = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin kho", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tên Kho");

        txt_tenkho.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Địa chỉ");

        txt_diachi_Kho.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btn_new_Kho.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new_Kho.setForeground(new java.awt.Color(0, 153, 153));
        btn_new_Kho.setText("New");
        btn_new_Kho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_new_KhoActionPerformed(evt);
            }
        });

        btn_them_Kho.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_them_Kho.setForeground(new java.awt.Color(0, 153, 153));
        btn_them_Kho.setText("Thêm");
        btn_them_Kho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them_KhoActionPerformed(evt);
            }
        });

        btn_sua_Kho.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_sua_Kho.setForeground(new java.awt.Color(0, 153, 153));
        btn_sua_Kho.setText("Sửa");
        btn_sua_Kho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua_KhoActionPerformed(evt);
            }
        });

        btn_xoa_Kho.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_xoa_Kho.setForeground(new java.awt.Color(0, 153, 153));
        btn_xoa_Kho.setText("Xóa");
        btn_xoa_Kho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa_KhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_tenkho, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(581, 581, 581)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_diachi_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_new_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_them_Kho)
                .addGap(18, 18, 18)
                .addComponent(btn_sua_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_xoa_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(486, 486, 486))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(txt_tenkho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_diachi_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them_Kho)
                    .addComponent(btn_sua_Kho)
                    .addComponent(btn_xoa_Kho)
                    .addComponent(btn_new_Kho))
                .addGap(19, 19, 19))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("QUẢN LÝ KHO");

        table_kho.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        table_kho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Kho", "Tên Kho", "Địa chỉ"
            }
        ));
        table_kho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_khoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_kho);

        btn_search_kho.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_search_kho.setForeground(new java.awt.Color(0, 153, 153));
        btn_search_kho.setText("Tìm kiếm");
        btn_search_kho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_khoActionPerformed(evt);
            }
        });

        txt_search_kho.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_search_kho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search_khoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Tìm kiếm kho");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(582, 582, 582)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search_kho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search_kho)))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search_kho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_kho)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table_khoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_khoMouseClicked
        // TODO add your handling code here:
        int r = table_kho.getSelectedRow();
        if (r < 0) {
            return;
        }        
        txt_tenkho.setText(table_kho.getValueAt(r, 1).toString());
        txt_diachi_Kho.setText(table_kho.getValueAt(r, 2).toString());
    }//GEN-LAST:event_table_khoMouseClicked
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
    private boolean isIDProductExist(int newIDProduct) {
        boolean exists = false;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Kho WHERE MaSP = ?");
            pstmt.setInt(1, newIDProduct);
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
    private void btn_them_KhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them_KhoActionPerformed
        // TODO add your handling code here:
        String tenKho = txt_tenkho.getText().trim();
        String diachi = txt_diachi_Kho.getText().trim();

        if (tenKho.isEmpty() || diachi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        addDataToDatabaseKho(tenKho, diachi);
    }//GEN-LAST:event_btn_them_KhoActionPerformed

    private void addDataToDatabaseKho( String tenKho, String diaChi) {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();           
            String query = "INSERT INTO Kho (TenKho, DiaChi) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, tenKho);
            pstmt.setString(2, diaChi);
            pstmt.executeUpdate();
            con.close();

            modelTableKho.setRowCount(0);
            loadDataToTableKho();
            JOptionPane.showMessageDialog(this, "Đã thêm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            
            txt_tenkho.setText("");
            txt_diachi_Kho.setText("");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void btn_sua_KhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua_KhoActionPerformed

        int selectedRow = table_kho.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String maKho = table_kho.getValueAt(selectedRow, 0).toString();       
        String tenKho = txt_tenkho.getText().trim();
        String diaChi = txt_diachi_Kho.getText().trim();
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "UPDATE Kho SET TenKho = ?, DiaChi =? WHERE MaKho = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setString(1, tenKho);
                pstmt.setString(2, diaChi);
                pstmt.setString(3, maKho);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã cập nhật dòng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    modelTableKho = (DefaultTableModel) table_kho.getModel();
                    modelTableKho.setValueAt(tenKho, selectedRow, 1);                    
                    modelTableKho.setValueAt(diaChi, selectedRow, 2);
                
                    txt_tenkho.setText("");
                    txt_diachi_Kho.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật Kho không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật số lượng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_sua_KhoActionPerformed
    private void btn_xoa_KhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa_KhoActionPerformed

        int selectedRow = table_kho.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String maKho = table_kho.getValueAt(selectedRow, 0).toString();

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "DELETE FROM Kho WHERE MaKho = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setString(1, maKho);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    DefaultTableModel model = (DefaultTableModel) table_kho.getModel();
                    model.removeRow(selectedRow);
                    
                    txt_tenkho.setText("");
                    txt_diachi_Kho.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa Kho", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_xoa_KhoActionPerformed

    private void btn_new_KhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_new_KhoActionPerformed

        txt_search_kho.setText("");
        txt_tenkho.setText("");
        txt_diachi_Kho.setText("");
        table_kho.clearSelection();
    }//GEN-LAST:event_btn_new_KhoActionPerformed

    private void txt_search_khoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search_khoActionPerformed

    }//GEN-LAST:event_txt_search_khoActionPerformed

    private void btn_search_khoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_khoActionPerformed
        searchAndUpdateTableKho(txt_search_kho.getText());
    }//GEN-LAST:event_btn_search_khoActionPerformed
    private void searchAndUpdateTableKho(String tieuDe) {
        try {
            Connection con = Datahelper.openConnection();
            String sql = "SELECT * FROM Kho WHERE TenKho LIKE ?";            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + tieuDe + "%");
            ResultSet rs = pstmt.executeQuery();

            modelTableKho.setRowCount(0);

            while (rs.next()) {
                int MaKho = rs.getInt("MaKho");
                String tenKho = rs.getString("TenKho");
                String diaChi = rs.getString("DiaChi");
                modelTableKho.addRow(new Object[]{MaKho, tenKho, diaChi});
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
            java.util.logging.Logger.getLogger(Kho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kho().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_new_Kho;
    private javax.swing.JButton btn_search_kho;
    private javax.swing.JButton btn_sua_Kho;
    private javax.swing.JButton btn_them_Kho;
    private javax.swing.JButton btn_xoa_Kho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_kho;
    private javax.swing.JTextField txt_diachi_Kho;
    private javax.swing.JTextField txt_search_kho;
    private javax.swing.JTextField txt_tenkho;
    // End of variables declaration//GEN-END:variables
}
