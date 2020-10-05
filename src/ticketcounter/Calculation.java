/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ticketcounter;
import static java.lang.Math.round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Pankaj
 */
public class Calculation {
    public int CountTicket(Statement st){
        String query;
        int count=0;
        query = "select * from main_table";
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
        return count+1;
    }
    
    public int Six_Boat(double hr){
        int base=2400;
        base = (int)(base*hr);
        return base;
    }
    public int Eight_boat(double hr){
        int base=2700;
        base = (int)(base*hr);
        return base;
    }
    public int Ten_boat(double hr){
        int base=3000;
        base = (int)(base*hr);
        return base;
    }
    public int Cannoning(double Tcount){
        int base=900;
        Tcount=(round(Tcount/2));
        return (int) ((int) base*Tcount);
    }
    public int Walking(int Person){
        int base=250;
        return base*Person;
    }
    public double JoyRide(int Person,double hr){
        int base=2000;
        int amt = base*Person;
        return amt*hr;
    }
    public int Ladga_Online(int Person){
        int EntryFee=0,gypsy=2700,boat=50,guide=480;
        boat*=Person;
        int amt=EntryFee+gypsy+boat+guide;
        return amt;
    }
    public int Ladga(int Person,int EntryFee){
        int gypsy=2700,boat=50,guide=480;
        boat*=Person;
        int amt=EntryFee+gypsy+boat+guide;
        return amt;
    }
    public int Keriya_Online(int Person){
        int EntryFee=0,gypsy=2000,boat=50,guide=480;
        boat*=Person;
        int amt=EntryFee+gypsy+boat+guide;
        return amt;
    }
    public int Keriya(int Person,int EntryFee){
        int gypsy=2000,boat=50,guide=480;
        boat*=Person;
        int amt=EntryFee+gypsy+boat+guide;
        return amt;
    }
    public int Mahal(int Person,int EntryFee){
        int gypsy=3300,boat=50,guide=480;
        boat*=Person;
        int amt=EntryFee+gypsy+boat+guide;
        return amt;
    }
        public int Mahal_Online(int Person){
        int EntryFee=0,gypsy=3300,boat=50,guide=480;
        boat*=Person;
        int amt=EntryFee+gypsy+boat+guide;
        return amt;
    }
    public int Dev(int Person,int EntryFee){
        int gypsy=4000,boat=50,guide=480;
        boat*=Person;
        int amt=EntryFee+gypsy+boat+guide;
        return amt;
    }
        public int Dev_online(int Person){
        int EntryFee=0,gypsy=4000,boat=50,guide=480;
        boat*=Person;
        int amt=EntryFee+gypsy+boat+guide;
        return amt;
    }
}
