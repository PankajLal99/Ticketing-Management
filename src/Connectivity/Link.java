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
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/madaiticketmanagement","root","5cIliY7ZpvwDMffs");
            st=con.createStatement();
//            JOptionPane.showMessageDialog(null, "Connection success...");
            }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            }
    return st;
    }  

    public Statement connection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
