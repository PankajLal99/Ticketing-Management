/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ticketcounter;
// Project Imports
import java.util.*; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
/**
 *
 * @author Pankaj
 */
public class Functions {
    public String SetDate(){
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();  
//        System.out.println(dtf.format(now));
        return(dtf.format(now));
    }
    public List<String> GetArea(Statement st){
        List<String> l = new ArrayList<>();
        String query;
        query ="select Area from area_table";
        ResultSet rs;
        try{
            rs=st.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString("Area"));
                l.add(rs.getString("Area"));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return l;
    }
}
