package Form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datahelper {

    public static Connection openConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=Bookstore_Management";
            String username = "sa";
            String password = "thevinh123";
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
