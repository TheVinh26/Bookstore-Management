package Form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test_Connect {

    public static void main(String[] args) {
        try (
                // mo ket noi .-- ket noi toi CSDl
                Connection con = Datahelper.openConnection();
                Statement stmt = con.createStatement();) {
            // viet thuc cau SQL lay DL// thong thanh cong chua
            System.out.println(" Connect Success");
            // lay du lieu len

        } catch (Exception e) {
            System.out.println("kết nối ko thành công");
            e.printStackTrace();
        }
    }
}
