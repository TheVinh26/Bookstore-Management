package Form;

import static Form.QL_PhieuXuat.isValidDate;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class NhanVien extends javax.swing.JFrame {

    private DefaultTableModel modelTableNhanVien = new DefaultTableModel();
    DefaultComboBoxModel<String> modelCbo;

    public NhanVien() throws SQLException {
        initComponents();
        initTable();
        loadDataToTable();
        innitCBO();
    }

    private void innitCBO() {
        modelCbo = new DefaultComboBoxModel<>();
        modelCbo.addElement("Quản lý");
        modelCbo.addElement("Nhân viên bán hàng");
        modelCbo.addElement("Nhân viên kho");

        cboChucVu.setModel(modelCbo);
    }

    private void initTable() {
        String[] headers = {"Mã Nhân Viên", "Tên Nhân viên", "Ngày sinh", "Chức vụ", "Địa chỉ", "Số điện thoại"};
        modelTableNhanVien = new DefaultTableModel(headers, 0);
        tblNhanVien.setModel(modelTableNhanVien);
    }

    private void loadDataToTable() throws SQLException {
        try {
            Connection con = Datahelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NhanVien");
            modelTableNhanVien.setRowCount(0);
            while (rs.next()) {
                String maNV = rs.getString("MaNV");
                String tenNV = rs.getString("TenNV");
                Date ngaySinh = rs.getDate("NgaySinh");
                String chucVu = rs.getString("ChucVu");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SDT");
                String date_format_ngaySinh = convertSQLDateToString(ngaySinh);
                modelTableNhanVien.addRow(new Object[]{maNV, tenNV, date_format_ngaySinh, chucVu, diaChi, soDienThoai});
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    private String convertSQLDateToString(Date sqlDate) {
        // Chuyển đổi java.sql.Date sang LocalDate
        LocalDate localDate = sqlDate.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        cboChucVu = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("QUẢN LÍ NHÂN VIÊN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 2, 18))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Tên nhân viên");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Ngày sinh");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Chức vụ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Địa chỉ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Số điện thoại");

        txtTenNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNVActionPerformed(evt);
            }
        });

        txtNgaySinh.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtSoDienThoai.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 153, 153));
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 153, 153));
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 153, 153));
        btnSua.setText("Sửa");
        btnSua.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        cboChucVu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(155, 155, 155)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(208, 208, 208))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(btnThem)
                            .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua))))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        tblNhanVien.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
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

    private boolean isPhoneExistNhanVien(String newPhone) {
        boolean exists = false;
        try {
            Connection con = Datahelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM NhanVien WHERE SDT = ?");
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
    private boolean isNumericAndTenDigits(String str) {
        return str.matches("\\d{10}");
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        String tenNV = txtTenNV.getText();
        String ngaySinh = txtNgaySinh.getText();
        String chucVu = cboChucVu.getSelectedItem().toString();
        String diaChi = txtDiaChi.getText();
        String soDienThoai = txtSoDienThoai.getText();

        if (tenNV.isEmpty() || ngaySinh.isEmpty() || chucVu.isEmpty() || soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidDate(ngaySinh)) {
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ!(Ngày/Tháng/Năm)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumericAndTenDigits(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại là số và đủ 10 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (isPhoneExistNhanVien(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã có trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String ns = convertDateFormat(ngaySinh);
        Date ngaysinh = convertStringToSqlDate(ns);

        try {
            Connection con = Datahelper.openConnection();

            String sql = "INSERT INTO NhanVien (TenNV, ChucVu, DiaChi, SDT, NgaySinh) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, tenNV);
            pstmt.setString(2, chucVu);
            pstmt.setString(3, diaChi);
            pstmt.setString(4, soDienThoai);
            pstmt.setDate(5, ngaysinh);

            pstmt.executeUpdate();

            con.close();
            modelTableNhanVien.setRowCount(0);
            loadDataToTable();
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            txtTenNV.setText("");
            txtNgaySinh.setText("");
            txtDiaChi.setText("");
            txtSoDienThoai.setText("");

            loadDataToTable();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int r = tblNhanVien.getSelectedRow();
        if (r < 0) {
            return;
        }
        txtTenNV.setText(tblNhanVien.getValueAt(r, 1).toString());
        txtNgaySinh.setText(tblNhanVien.getValueAt(r, 2).toString());
        cboChucVu.setSelectedItem(tblNhanVien.getValueAt(r, 3));
        txtDiaChi.setText(tblNhanVien.getValueAt(r, 4).toString());
        txtSoDienThoai.setText(tblNhanVien.getValueAt(r, 5).toString());

    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = tblNhanVien.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String maNV = tblNhanVien.getValueAt(selectedRow, 0).toString();

        // Hiển thị hộp thoại xác nhận xóa
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "DELETE FROM NhanVien WHERE MaNV = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setString(1, maNV);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã xóa nhân viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa nhân viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int selectedRow = tblNhanVien.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int maNV = Integer.parseInt(tblNhanVien.getValueAt(selectedRow, 0).toString());
        String tenNV = txtTenNV.getText();
        String ngaySinh = txtNgaySinh.getText();
        String chucVu = cboChucVu.getSelectedItem().toString();
        String diaChi = txtDiaChi.getText();
        String soDienThoai = txtSoDienThoai.getText();

        if (tenNV.isEmpty() || ngaySinh.isEmpty() || chucVu.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidDate(ngaySinh)) {
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ!(Ngày/Tháng/Năm)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumericAndTenDigits(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại là số và đủ 10 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String ns = convertDateFormat(ngaySinh);
        Date ngaysinh = convertStringToSqlDate(ns);

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                Connection con = Datahelper.openConnection();

                String sql = "UPDATE NhanVien SET TenNV = ?, ChucVu = ?,DiaChi = ?, SDT = ?, NgaySinh = ? WHERE MaNV = ?";

                PreparedStatement pstmt = con.prepareStatement(sql);

                pstmt.setString(1, tenNV);
                pstmt.setString(2, chucVu);
                pstmt.setString(3, diaChi);
                pstmt.setString(4, soDienThoai);
                pstmt.setDate(5, ngaysinh);
                pstmt.setInt(6, maNV);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Đã cập nhật nhân viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    modelTableNhanVien= (DefaultTableModel) tblNhanVien.getModel();
                    modelTableNhanVien.setValueAt(tenNV, selectedRow, 1);
                    modelTableNhanVien.setValueAt(ngaySinh, selectedRow, 2);
                    modelTableNhanVien.setValueAt(chucVu, selectedRow, 3);
                    modelTableNhanVien.setValueAt(diaChi, selectedRow, 4);
                    modelTableNhanVien.setValueAt(soDienThoai, selectedRow, 5);

                    txtTenNV.setText("");
                    txtNgaySinh.setText("");
                    txtDiaChi.setText("");
                    txtSoDienThoai.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật nhân viên không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                con.close();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật nhân viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNVActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NhanVien().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables

}
