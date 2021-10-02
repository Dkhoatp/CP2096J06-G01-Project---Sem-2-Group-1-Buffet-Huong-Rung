/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huongrungbuffet;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
// khoa: "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=HuongRungBuffet", "sa", "khoahihi"
public class MyConnection {
    public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
            java.sql.Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=HuongRungBuffet", "sa", "khoahihi");
        return con;
    }
}
