package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHelper {

    private static final String username = "root";
    private static final String password = "";
    private static final String DB = "projectpbo";
    private static final String myConn = "jdbc:mysql://localhost/" + DB;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(myConn, username, password);
            System.out.println("Connection berhasil !");
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection tidak berhasil !");
        }
        return conn;
    }
    
}