/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ticketcounter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Pankaj
 */
public class Calculation {
    public int CountArea(Statement st){
        String query;
        int count=0;
        query = "select * from area_table";
        ResultSet rs;
        try{
            rs=st.executeQuery(query);
            while(rs.next()){
                count++;
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return count;
    }
}
