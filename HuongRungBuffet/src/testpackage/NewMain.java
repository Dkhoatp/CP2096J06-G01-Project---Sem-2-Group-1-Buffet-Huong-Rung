/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import huongrungbuffet.MyConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            java.sql.Connection con = MyConnection.getConnection();
            if(con != null){
                System.out.println("success");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("eror");
        }
    }
    
}
