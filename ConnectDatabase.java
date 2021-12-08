package ape2je;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConnectDatabase {

    public static Connection connectdb() {
            Connection con = null;
            String url = "jdbc:derby://localhost:1527/UserLogin";
            String username = "app";
            String password = "app";
        try {
            con = DriverManager.getConnection(url,username,password);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
