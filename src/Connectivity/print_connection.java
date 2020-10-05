/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connectivity;

/**
 *
 * @author Pankaj
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class print_connection {
    
    static Connection con;
    static Statement st;
        public static Connection Connection()
        {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/madaiticketmanagement","root","5cIliY7ZpvwDMffs");
            
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return con;
        } 
        
} 