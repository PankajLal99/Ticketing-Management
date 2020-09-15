/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connectivity;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Pankaj
 */
public class Link {
    static Connection con;
    static Statement st;
        public static Statement Connection()
        {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3308/ticket_management","root","");
            st=con.createStatement();
            JOptionPane.showMessageDialog(null, "Connection success...");
            }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            }
    return st;
    }  
}
