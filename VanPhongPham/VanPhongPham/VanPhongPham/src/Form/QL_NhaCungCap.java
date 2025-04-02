
package Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QL_NhaCungCap extends javax.swing.JFrame {
    private DefaultTableModel modeTableNCC = new DefaultTableModel();
    public QL_NhaCungCap() throws SQLException {
        initComponents();
        initTable();
        loadDataToTableNCC();
    }
    private void initTable() {
        String[] headers = {"Mã Nhà Cung Cấp","Tên Nhà Cung Cấp", "Số Điện Thoại", "Email", "Địa Chỉ"};
        modeTableNCC = new DefaultTableModel(headers, 0);
        table_ncc.setModel(modeTableNCC);
    }
    private void loadDataToTableNCC() throws SQLException {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NCC");
            while (rs.next()) {
                int maNCC = rs.getInt("MaNCC");
                String tenNCC = rs.getString("TenNCC");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                String diachi = rs.getString("DiaChi");
                modeTableNCC.addRow(new Object[]{maNCC, tenNCC, sdt, email, diachi});
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_name_ncc = new javax.swing.JTextField();
        txt_email_ncc = new javax.swing.JTextField();
        txt_diachi_ncc = new javax.swing.JTextField();
        txt_sdt_ncc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_ncc = new javax.swing.JTable();
        btn_xoa_ncc = new javax.swing.JButton();
        btn_sua_ncc = new javax.swing.JButton();
        btn_them_ncc = new javax.swing.JButton();
        btn_new_ncc = new javax.swing.JButton();
        btn_search_ncc = new javax.swing.JButton();
        txt_search_NCC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Tên Nhà Cung Cấp");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Số điện thoại");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Địa chỉ");

        txt_name_ncc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txt_email_ncc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txt_diachi_ncc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txt_sdt_ncc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        table_ncc.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        table_ncc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Số Điện Thoại", "Email", "Địa Chỉ"
            }
        ));
        table_ncc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_nccMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_ncc);

        btn_xoa_ncc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_xoa_ncc.setForeground(new java.awt.Color(0, 153, 153));
        btn_xoa_ncc.setText("Xóa");
        btn_xoa_ncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa_nccActionPerformed(evt);
            }
        });

        btn_sua_ncc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_sua_ncc.setForeground(new java.awt.Color(0, 153, 153));
        btn_sua_ncc.setText("Sửa");
        btn_sua_ncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua_nccActionPerformed(evt);
            }
        });

        btn_them_ncc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_them_ncc.setForeground(new java.awt.Color(0, 153, 153));
        btn_them_ncc.setText("Thêm");
        btn_them_ncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them_nccActionPerformed(evt);
            }
        });

        btn_new_ncc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new_ncc.setForeground(new java.awt.Color(0, 153, 153));
        btn_new_ncc.setText("New");
        btn_new_ncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_new_nccActionPerformed(evt);
            }
        });

        btn_search_ncc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_search_ncc.setForeground(new java.awt.Color(0, 153, 153));
        btn_search_ncc.setText("Tìm kiếm nhà cung cấp");
        btn_search_ncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_nccActionPerformed(evt);
            }
        });

        txt_search_NCC.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Nhà Cung Cấp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 460, Short.MAX_VALUE)
                        .addComponent(btn_new_ncc)
                        .addGap(18, 18, 18)
                        .addComponent(btn_them_ncc)
                        .addGap(18, 18, 18)
                        .addComponent(btn_sua_ncc)
                        .addGap(18, 18, 18)
                        .addComponent(btn_xoa_ncc)
                        .addGap(513, 513, 513))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_search_NCC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_search_ncc))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_name_ncc)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_sdt_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_email_ncc))
                                    .addComponent(txt_diachi_ncc))))))
                .addGap(85, 85, 85))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_search_ncc)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_search_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_name_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_sdt_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_email_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_diachi_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them_ncc)
                    .addComponent(btn_sua_ncc)
                    .addComponent(btn_xoa_ncc)
                    .addComponent(btn_new_ncc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("Quản Lý Nhà Cung Cấp");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(532, 532, 532)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private boolean checkEmail(String email)
    {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {            
            return false;
        }
        return true;
    }
    private boolean isEmailExistNCC(String newEmail) {
        boolean exists = false;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM NCC WHERE Email = ?");
            pstmt.setString(1, newEmail);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                exists = true; // Email exist in database
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }
    private boolean isPhoneExistNCC(String newPhone) {
        boolean exists = false;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM NCC WHERE SDT = ?");
            pstmt.setString(1, newPhone);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                exists = true; // Phone exist in database
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }
    private boolean isNumericAndTenDigits(String str) {
        return str.matches("\\d{10}");
    }
    private void btn_them_nccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them_nccActionPerformed
        String teNCC = txt_name_ncc.getText().trim();
        String sdt = txt_sdt_ncc.getText().trim();
        String email = txt_email_ncc.getText().trim();
        String diachi = txt_diachi_ncc.getText().trim();       

        if (teNCC.isEmpty() || diachi.isEmpty() || sdt.isEmpty()| sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!isNumericAndTenDigits(sdt))
        {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại là số và đủ 10 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (isPhoneExistNCC(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã có trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        
        if (!checkEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ, vui lòng nhập lại!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (isEmailExistNCC(email)) {
            JOptionPane.showMessageDialog(this, "Email đã có trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection con = Datahelper.openConnection();
            String sql = "INSERT INTO NCC (TenNCC, SDT, Email, DiaChi) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, teNCC);
            pstmt.setString(2, sdt);
            pstmt.setString(3, email);
            pstmt.setString(4, diachi);
            pstmt.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            modeTableNCC.setRowCount(0);
            
            loadDataToTableNCC();

            txt_search_NCC.setText("");
            txt_name_ncc.setText("");
            txt_sdt_ncc.setText("");
            txt_email_ncc.setText("");
            txt_diachi_ncc.setText("");
            
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_them_nccActionPerformed

    private void btn_new_nccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_new_nccActionPerformed
        txt_search_NCC.setText("");
        txt_name_ncc.setText("");
        txt_sdt_ncc.setText("");
        txt_email_ncc.setText("");
        txt_diachi_ncc.setText("");
        table_ncc.clearSelection();
    }//GEN-LAST:event_btn_new_nccActionPerformed

    private void btn_search_nccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_nccActionPerformed
        searchAndUpdateTableNCC(txt_search_NCC.getText().trim());
    }//GEN-LAST:event_btn_search_nccActionPerformed
    private void searchAndUpdateTableNCC(String tieuDe) {
        try {
            Connection con = Datahelper.openConnection();
            String sql = "SELECT * FROM NCC WHERE TenNCC LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + tieuDe + "%");
            ResultSet rs = pstmt.executeQuery();

            modeTableNCC.setRowCount(0);

            while (rs.next()) {
                int maNCC = rs.getInt("MaNCC");
                String tenNCC = rs.getString("TenNCC");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                String diachi = rs.getString("DiaChi");
                modeTableNCC.addRow(new Object[]{maNCC, tenNCC, sdt, email, diachi});
            }
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    private void table_nccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_nccMouseClicked

        int r = table_ncc.getSelectedRow();
        if (r < 0) {
            return;
        }        
        txt_name_ncc.setText(table_ncc.getValueAt(r, 1).toString());
        txt_sdt_ncc.setText(table_ncc.getValueAt(r, 2).toString());
        txt_email_ncc.setText(table_ncc.getValueAt(r, 3).toString());
        txt_diachi_ncc.setText(table_ncc.getValueAt(r, 4).toString());
    }//GEN-LAST:event_table_nccMouseClicked

    private void btn_sua_nccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua_nccActionPerformed
        
        int selectedRow = table_ncc.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String maNCC = table_ncc.getValueAt(selectedRow, 0).toString();
        
        String tenNCC = txt_name_ncc.getText().trim();
        String sdt = txt_sdt_ncc.getText().trim();
        String email = txt_email_ncc.getText().trim();
        String diachi = txt_diachi_ncc.getText().trim(); 
        
        if (tenNCC.isEmpty() || diachi.isEmpty() || sdt.isEmpty()| sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!isNumericAndTenDigits(sdt))
        {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại là số và đủ 10 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }               
        
        if (!checkEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ, vui lòng nhập lại!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
                

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật nhà cung cấp này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "UPDATE NCC SET TenNCC = ?, SDT = ?, Email = ?, DiaChi = ? WHERE MaNCC = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setString(1, tenNCC);
                pstmt.setString(2, sdt);
                pstmt.setString(3, email);
                pstmt.setString(4, diachi);
                pstmt.setString(5, maNCC);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã cập nhật nhà cung cấp thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    modeTableNCC = (DefaultTableModel) table_ncc.getModel();
                    modeTableNCC.setValueAt(tenNCC, selectedRow, 1);
                    modeTableNCC.setValueAt(sdt, selectedRow, 2);
                    modeTableNCC.setValueAt(email, selectedRow, 3);
                    modeTableNCC.setValueAt(diachi, selectedRow, 4);                    
                
                    txt_search_NCC.setText("");
                    txt_name_ncc.setText("");
                    txt_sdt_ncc.setText("");
                    txt_email_ncc.setText("");
                    txt_diachi_ncc.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_sua_nccActionPerformed

    private void btn_xoa_nccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa_nccActionPerformed
        // TODO add your handling code here:
        int selectedRow = table_ncc.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String maMCC = table_ncc.getValueAt(selectedRow, 0).toString();

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "DELETE FROM NCC WHERE MaNCC = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setString(1, maMCC);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    DefaultTableModel model = (DefaultTableModel) table_ncc.getModel();
                    model.removeRow(selectedRow);
                    
                    txt_name_ncc.setText("");
                    txt_sdt_ncc.setText("");
                    txt_email_ncc.setText("");
                    txt_diachi_ncc.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa Kho", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_xoa_nccActionPerformed

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
            java.util.logging.Logger.getLogger(QL_NhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_NhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_NhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_NhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new QL_NhaCungCap().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(QL_NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_new_ncc;
    private javax.swing.JButton btn_search_ncc;
    private javax.swing.JButton btn_sua_ncc;
    private javax.swing.JButton btn_them_ncc;
    private javax.swing.JButton btn_xoa_ncc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_ncc;
    private javax.swing.JTextField txt_diachi_ncc;
    private javax.swing.JTextField txt_email_ncc;
    private javax.swing.JTextField txt_name_ncc;
    private javax.swing.JTextField txt_sdt_ncc;
    private javax.swing.JTextField txt_search_NCC;
    // End of variables declaration//GEN-END:variables
}
