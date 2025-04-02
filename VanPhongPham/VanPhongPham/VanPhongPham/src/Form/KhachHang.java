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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KhachHang extends javax.swing.JFrame {

    private int maNV;
    private int maKH;
    
    public KhachHang(int maNV) throws SQLException {
        this.maNV = maNV;
        initComponents();
        initTable();
        loadDataToTableKhachHang();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    private DefaultTableModel modeTablelKhachHang = new DefaultTableModel();
    
    private void initTable() {
        String[] headers = {"Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số điện thoại", "Email"};
        modeTablelKhachHang = new DefaultTableModel(headers, 0);
        table_KhachHang.setModel(modeTablelKhachHang);
    }
    
    private void loadDataToTableKhachHang() throws SQLException {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM KhachHang");
            while (rs.next()) {
                int maKH = rs.getInt("MaKH");
                String tenKH = rs.getString("TenKH");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                modeTablelKhachHang.addRow(new Object[]{maKH, tenKH, diaChi, sdt, email});
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_TenKH = new javax.swing.JTextField();
        txt_DiaChi_KH = new javax.swing.JTextField();
        txt_sdt_KH = new javax.swing.JTextField();
        txt_email_KH = new javax.swing.JTextField();
        btn_Them_KH = new javax.swing.JButton();
        btn_Xoa_KH = new javax.swing.JButton();
        btn_Sua_KH = new javax.swing.JButton();
        btn_new_KH = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_KhachHang = new javax.swing.JTable();
        btnChonKhachHang = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        btn_search_KH = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("QUẢN LÍ KHÁCH HÀNG");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 2, 18))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Địa chỉ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Số điện thoại");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Email");

        txt_TenKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txt_DiaChi_KH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txt_sdt_KH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txt_email_KH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btn_Them_KH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Them_KH.setForeground(new java.awt.Color(0, 153, 153));
        btn_Them_KH.setText("Thêm");
        btn_Them_KH.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_Them_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them_KHActionPerformed(evt);
            }
        });

        btn_Xoa_KH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Xoa_KH.setForeground(new java.awt.Color(0, 153, 153));
        btn_Xoa_KH.setText("Xóa");
        btn_Xoa_KH.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_Xoa_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xoa_KHActionPerformed(evt);
            }
        });

        btn_Sua_KH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_Sua_KH.setForeground(new java.awt.Color(0, 153, 153));
        btn_Sua_KH.setText("Sửa");
        btn_Sua_KH.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_Sua_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sua_KHActionPerformed(evt);
            }
        });

        btn_new_KH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new_KH.setForeground(new java.awt.Color(0, 153, 153));
        btn_new_KH.setText("New");
        btn_new_KH.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_new_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_new_KHActionPerformed(evt);
            }
        });

        table_KhachHang.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        table_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số điện thoại", "Email"
            }
        ));
        table_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_KhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_KhachHang);

        btnChonKhachHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnChonKhachHang.setForeground(new java.awt.Color(0, 153, 153));
        btnChonKhachHang.setText("chọn Khách hàng");
        btnChonKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnChonKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1430, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnChonKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btn_new_KH)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Them_KH)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Sua_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Xoa_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_email_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(82, 82, 82)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_DiaChi_KH, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                                    .addComponent(txt_sdt_KH))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_sdt_KH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_DiaChi_KH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_email_KH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them_KH)
                    .addComponent(btn_new_KH)
                    .addComponent(btn_Sua_KH)
                    .addComponent(btn_Xoa_KH)
                    .addComponent(btnChonKhachHang))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txt_search.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });

        btn_search_KH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_search_KH.setForeground(new java.awt.Color(0, 153, 153));
        btn_search_KH.setText("Tìm kiếm");
        btn_search_KH.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_search_KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_KHActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Tìm kiếm khách hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search_KH)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_KH)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private boolean isNumericAndTenDigits(String str) {
        return str.matches("\\d{10}");
    }
    
    private boolean checkEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    
    private boolean isEmailExistKhachHang(String newEmail) {
        boolean exists = false;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM KhachHang WHERE Email = ?");
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
    
    private boolean isPhoneExistKhachHang(String newPhone) {
        boolean exists = false;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM KhachHang WHERE SDT = ?");
            pstmt.setString(1, newPhone);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }
    private void btn_Them_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them_KHActionPerformed
        
        String tenKH = txt_TenKH.getText().trim();
        String diaChi = txt_DiaChi_KH.getText().trim();
        String sdt = txt_sdt_KH.getText().trim();
        String email = txt_email_KH.getText().trim();
        
        if (tenKH.isEmpty() || diaChi.isEmpty() || sdt.isEmpty() | email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!isNumericAndTenDigits(sdt)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại là số và đủ 10 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (isPhoneExistKhachHang(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã có trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!checkEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ, vui lòng nhập lại!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (isEmailExistKhachHang(email)) {
            JOptionPane.showMessageDialog(this, "Email đã có trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Connection con = Datahelper.openConnection();
            String sql = "INSERT INTO KhachHang (TenKH, DiaChi, SDT, Email) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, tenKH);
            pstmt.setString(2, diaChi);
            pstmt.setString(3, sdt);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            modeTablelKhachHang.setRowCount(0);
            
            loadDataToTableKhachHang();
            
            txt_TenKH.setText("");
            txt_DiaChi_KH.setText("");
            txt_sdt_KH.setText("");
            txt_email_KH.setText("");
            
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_Them_KHActionPerformed

    private void btn_Xoa_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xoa_KHActionPerformed
        int selectedRow = table_KhachHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int maKH = Integer.parseInt(table_KhachHang.getValueAt(selectedRow, 0).toString());
        
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();
                
                String sql = "DELETE FROM KhachHang WHERE MaKH = ?";
                
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                pstmt.setInt(1, maKH);
                
                int affectedRows = pstmt.executeUpdate();
                
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã xóa khách hàng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    
                    DefaultTableModel model = (DefaultTableModel) table_KhachHang.getModel();
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa khách hàng không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa nhân viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_Xoa_KHActionPerformed

    private void table_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_KhachHangMouseClicked
        int r = table_KhachHang.getSelectedRow();
        if (r < 0) {
            return;
        }
        txt_TenKH.setText(table_KhachHang.getValueAt(r, 1).toString());
        txt_DiaChi_KH.setText(table_KhachHang.getValueAt(r, 2).toString());
        txt_sdt_KH.setText(table_KhachHang.getValueAt(r, 3).toString());
        txt_email_KH.setText(table_KhachHang.getValueAt(r, 4).toString());
    }//GEN-LAST:event_table_KhachHangMouseClicked

    private void btn_Sua_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sua_KHActionPerformed
        int selectedRow = table_KhachHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int maKH = Integer.parseInt(table_KhachHang.getValueAt(selectedRow, 0).toString());
        
        String tenKH = txt_TenKH.getText().trim();
        String sdt = txt_sdt_KH.getText().trim();
        String email = txt_email_KH.getText().trim();
        String diachi = txt_DiaChi_KH.getText().trim();
        
        
        if (tenKH.isEmpty() || email.isEmpty() || sdt.isEmpty() | diachi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!isNumericAndTenDigits(sdt)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại là số và đủ 10 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        
        if (!checkEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ, vui lòng nhập lại!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();
                
                String sql = "UPDATE KhachHang SET TenKH = ?, DiaChi = ?, SDT = ?, Email = ? WHERE MaKH = ?";
                
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                pstmt.setString(1, tenKH);
                pstmt.setString(2, diachi);
                pstmt.setString(3, sdt);
                pstmt.setString(4, email);
                pstmt.setInt(5, maKH);
                
                int affectedRows = pstmt.executeUpdate();
                
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã cập nhật khách hàng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    
                    modeTablelKhachHang = (DefaultTableModel) table_KhachHang.getModel();
                    modeTablelKhachHang.setValueAt(tenKH, selectedRow, 1);
                    modeTablelKhachHang.setValueAt(diachi, selectedRow, 2);
                    modeTablelKhachHang.setValueAt(sdt, selectedRow, 3);
                    modeTablelKhachHang.setValueAt(email, selectedRow, 4);
                    
                    txt_TenKH.setText("");
                    txt_DiaChi_KH.setText("");
                    txt_email_KH.setText("");
                    txt_sdt_KH.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật khách hàng không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_Sua_KHActionPerformed

    private void btn_new_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_new_KHActionPerformed
        
        txt_TenKH.setText("");
        txt_DiaChi_KH.setText("");
        txt_email_KH.setText("");
        txt_sdt_KH.setText("");
        table_KhachHang.clearSelection();
    }//GEN-LAST:event_btn_new_KHActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void btn_search_KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_KHActionPerformed
        searchAndUpdateTableKhachHang(txt_search.getText());
    }//GEN-LAST:event_btn_search_KHActionPerformed
    
    
    private void btnChonKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangActionPerformed
        int selectedRow = table_KhachHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int maKH = Integer.parseInt(table_KhachHang.getValueAt(selectedRow, 0).toString());
        try {
            BanHang bh= new BanHang(maNV);
            bh.setMaKH(maKH);
            bh.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }      

        this.dispose();
    }//GEN-LAST:event_btnChonKhachHangActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//           try {
//            BanHang bh= new BanHang(maNV);
//            //bh.setMaKH(maKH);
////            banHangForm.setVisible(true);
//        } catch (SQLException ex) {
//            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
//        } 
    }//GEN-LAST:event_formWindowClosed
    private void searchAndUpdateTableKhachHang(String tieuDe) {
        try {
            Connection con = Datahelper.openConnection();
            String sql = "SELECT * FROM KhachHang WHERE TenKH LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + tieuDe + "%");
            ResultSet rs = pstmt.executeQuery();
            
            modeTablelKhachHang.setRowCount(0);
            
            while (rs.next()) {
                String maKH = rs.getString("MaKH");
                String tenKH = rs.getString("TenKH");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                modeTablelKhachHang.addRow(new Object[]{maKH, tenKH, diaChi, sdt, email});
            }
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int defaultMaNV = -1;
                    new KhachHang(defaultMaNV).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btn_Sua_KH;
    private javax.swing.JButton btn_Them_KH;
    private javax.swing.JButton btn_Xoa_KH;
    private javax.swing.JButton btn_new_KH;
    private javax.swing.JButton btn_search_KH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_KhachHang;
    private javax.swing.JTextField txt_DiaChi_KH;
    private javax.swing.JTextField txt_TenKH;
    private javax.swing.JTextField txt_email_KH;
    private javax.swing.JTextField txt_sdt_KH;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
