/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ticketcounter;
// Project Imports
import Connectivity.Link;
import Connectivity.print_connection;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.*;
// DB Imports
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

// Project Imports
/**
 *
 * @author Pankaj
 */
//    My classes

public class MainFrame extends javax.swing.JFrame {
    // Object for Functions
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    Functions func = new Functions();
    Calculation cal = new Calculation();
    Link db = new Link();
    Statement st;
    ResultSet rs;
    
    
    void printmain(){
        Connection pconn;
       print_connection p = new print_connection();
       pconn=print_connection.Connection();
        int t=Integer.parseInt(TicketCount.getText());
        try{
          //InputStream inp = new FileInputStream(new File("G:\\COLLEGE\\java\\MyProject\\src\\myproject\\Bill_1.jrxml"));
          InputStream inp = new FileInputStream(new File("F:\\Madhai Software\\reports\\MadhaiTicket.jrxml"));
            JasperDesign jd =JRXmlLoader.load(inp);
            String sql ="select * from main_table where TicketNo="+t+"";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr =JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            JasperPrint j = JasperFillManager.fillReport(jr,para,pconn);
            JasperViewer.viewReport(j,false);
            OutputStream os = new FileOutputStream(new File("F:\\Madhai Software\\reports\\abc.jrxml"));
            JasperExportManager.exportReportToPdfStream(j,os);
            clear();
           }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    void savedata(){
        String round="-";
        st=Link.Connection();
        String Query;
        int tno,mc,fc,cc,gc,ef,gf,bf,guf,tbf,wf,jf,cf,room=0,govt=0;
        String lead,addr,nation,activity,gn,query;
        double hr,total;
        String idn;
        tno=Integer.parseInt(TicketCount.getText());
        lead=leader.getText();
        addr=address.getText();
        nation=String.valueOf(Nation.getSelectedItem());
        mc=Integer.parseInt(mcount.getText());
        fc=Integer.parseInt(fcount.getText());
        cc=Integer.parseInt(ccount.getText());
        gn=String.valueOf(g1.getSelectedItem())+','+
                String.valueOf(g2.getSelectedItem())+','
                +String.valueOf(g3.getSelectedItem())+','
                    +String.valueOf(g4.getSelectedItem());
        gc=Integer.parseInt(guide_count.getText());
        idn=idnum.getText();
        
//        Gypsy_Safari, Boating, Cannoing, Walking, Joy_Ride, Tawa_Boat_Fee, VIP_Room, Room
//              0         1       2          3        4          5            6         7
        if (ActivityList.getSelectedIndex()==0){
            if (ladga.isSelected() || chutki.isSelected() || mahal.isSelected() || choorna.isSelected() || keriya.isSelected()){
                activity = String.valueOf(ActivityList.getSelectedItem());
                ef=Integer.parseInt(Entryfee.getText());
                gf=Integer.parseInt(gypsefees.getText());
                bf=Integer.parseInt(boatfees.getText());
                hr=Double.parseDouble(t_hours.getText());
                guf=Integer.parseInt(guidefees.getText());
                total=Double.parseDouble(totalamt.getText());
                round=String.valueOf(getSafariname());
                double days=Double.parseDouble(cdayf.getText());
                String mist=othertext.getText();
                int oamt=Integer.parseInt(otheramt.getText());
                try{
                    
//INSERT INTO `main_table` (`TicketNo`, `DateTime`, `GroupLeader`, `Address`,
//`Nationality`, `MaleCount`, `FemaleCount`, `ChildrenCount`, `Activity`, `GuideCount`, 
//`Days`, `EntryFee`, `GypsyFee`, `Roomday`, `BoatFee`, `GuideFee`, `GuideNumber`, `Hours`, 
//`TawaBoatFee`, `WalkFee`, `JoyFee`, `ConopyFee`, `RoomFee`, `RoomCount`, `NoofRoom`, `RoomType`,
//`GovtFee`, `TotalFee`, `OtherFee`, `OtherRemark`) VALUES (NULL, CURRENT_TIMESTAMP,
//        '', '', '', '', '', '0', '', NULL, '0', '0', '0', NULL, '0', '0', '', '0', '0', '0', '0', '0',
//        '0', '0', NULL, NULL, '0', '', '0', NULL)                   
//                        query="insert into main_table (GroupLeader,Address,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
//                                + "GuideCount,GuideNumber,GuideFee,TotalFee) values ('"+lead+"','"+addr+"','"+nation+"',"
//                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+total+")";
                        query="insert into main_table (GroupLeader,Address,Round,Nationality,IdNum,MaleCount,FemaleCount,ChildrenCount,Activity,"
                                + "GuideCount,GuideNumber,GuideFee,GA,Days,EntryFee,GypsyFee,BoatFee,Hours,TotalFee,OtherFee,OtherRemark) values ('"+lead+"','"+addr+"','"+round+"','"+nation+"',"
                                + "'"+idn+"',"+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+",'"+gova+"',"+days+","+ef+","+gf+","
                                + ""+bf+","+hr+","+total+","+oamt+",'"+mist+"')";
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "RECORD INSERTED");
                        
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, e);
                    }
            }
        }
        else if (ActivityList.getSelectedIndex()==1){
            if(s6.isSelected() || s8.isSelected() || s10.isSelected()){
                round=String.valueOf(getBoatName());
                activity = String.valueOf(ActivityList.getSelectedItem());
                bf=Integer.parseInt(boatfees.getText());
                guf=Integer.parseInt(guidefees.getText());
                hr=Double.parseDouble(t_hours.getText());
                String remark=otherremarks.getText();
                double misamt=Double.parseDouble(miscfee.getText());
                total=Double.parseDouble(totalamt.getText());
                
                try
                    {
//INSERT INTO `main_table` (`TicketNo`, `DateTime`, `GroupLeader`, `Address`,
//`Nationality`, `MaleCount`, `FemaleCount`, `ChildrenCount`, `Activity`, `GuideCount`, 
//`Days`, `EntryFee`, `GypsyFee`, `Roomday`, `BoatFee`, `GuideFee`, `GuideNumber`, `Hours`, 
//`TawaBoatFee`, `WalkFee`, `JoyFee`, `ConopyFee`, `RoomFee`, `RoomCount`, `NoofRoom`, `RoomType`,
//`GovtFee`, `TotalFee`, `OtherFee`, `OtherRemark`) VALUES (NULL, CURRENT_TIMESTAMP,
//        '', '', '', '', '', '0', '', NULL, '0', '0', '0', NULL, '0', '0', '', '0', '0', '0', '0', '0',
//        '0', '0', NULL, NULL, '0', '', '0', NULL)                   
//                        query="insert into main_table (GroupLeader,Address,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
//                                + "GuideCount,GuideNumber,GuideFee,TotalFee) values ('"+lead+"','"+addr+"','"+nation+"',"
//                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+total+")";
                        query="insert into main_table (GroupLeader,Address,Round,IdNum,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
                                + "GuideCount,GuideNumber,GuideFee,BoatFee,Hours,TotalFee,OtherFee,OtherRemark) values ('"+lead+"','"+addr+"','"+round+"','"+idn+"','"+nation+"',"
                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","
                                + ""+bf+","+hr+","+total+","+misamt+",'"+remark+"')";
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "RECORD INSERTED");
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, e);
                    }
            }
        }
        else if (ActivityList.getSelectedIndex()==2){
            activity = String.valueOf(ActivityList.getSelectedItem());
            String remark=otherremarks.getText();
            double misamt=Double.parseDouble(miscfee.getText());
            cf=Integer.parseInt(canofees.getText());
            guf=Integer.parseInt(guidefees.getText());
            total=Double.parseDouble(totalamt.getText());
            
            try
                    {
//INSERT INTO `main_table` (`TicketNo`, `DateTime`, `GroupLeader`, `Address`,
//`Nationality`, `MaleCount`, `FemaleCount`, `ChildrenCount`, `Activity`, `GuideCount`, 
//`Days`, `EntryFee`, `GypsyFee`, `Roomday`, `BoatFee`, `GuideFee`, `GuideNumber`, `Hours`, 
//`TawaBoatFee`, `WalkFee`, `JoyFee`, `ConopyFee`, `RoomFee`, `RoomCount`, `NoofRoom`, `RoomType`,
//`GovtFee`, `TotalFee`, `OtherFee`, `OtherRemark`) VALUES (NULL, CURRENT_TIMESTAMP,
//        '', '', '', '', '', '0', '', NULL, '0', '0', '0', NULL, '0', '0', '', '0', '0', '0', '0', '0',
//        '0', '0', NULL, NULL, '0', '', '0', NULL)                   
//                        query="insert into main_table (GroupLeader,Address,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
//                                + "GuideCount,GuideNumber,GuideFee,TotalFee) values ('"+lead+"','"+addr+"','"+nation+"',"
//                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+total+")";
                        query="insert into main_table (GroupLeader,Address,IdNum,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
                                + "GuideCount,GuideNumber,GuideFee,ConopyFee,TotalFee,OtherFee,OtherRemark) values ('"+lead+"','"+addr+"','"+idn+"','"+nation+"',"
                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+cf+","
                                + ""+total+","+misamt+",'"+remark+"')";
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "RECORD INSERTED");
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, e);
                    }
        }
        else if (ActivityList.getSelectedIndex()==3){
            activity = String.valueOf(ActivityList.getSelectedItem());
            String remark=otherremarks.getText();
            double misamt=Double.parseDouble(miscfee.getText());
            wf=Integer.parseInt(walkfee.getText());
            guf=Integer.parseInt(guidefees.getText());
            bf=Integer.parseInt(boatfees.getText());
            total=Double.parseDouble(totalamt.getText());
                        try
                    {
//INSERT INTO `main_table` (`TicketNo`, `DateTime`, `GroupLeader`, `Address`,
//`Nationality`, `MaleCount`, `FemaleCount`, `ChildrenCount`, `Activity`, `GuideCount`, 
//`Days`, `EntryFee`, `GypsyFee`, `Roomday`, `BoatFee`, `GuideFee`, `GuideNumber`, `Hours`, 
//`TawaBoatFee`, `WalkFee`, `JoyFee`, `ConopyFee`, `RoomFee`, `RoomCount`, `NoofRoom`, `RoomType`,
//`GovtFee`, `TotalFee`, `OtherFee`, `OtherRemark`) VALUES (NULL, CURRENT_TIMESTAMP,
//        '', '', '', '', '', '0', '', NULL, '0', '0', '0', NULL, '0', '0', '', '0', '0', '0', '0', '0',
//        '0', '0', NULL, NULL, '0', '', '0', NULL)                   
//                        query="insert into main_table (GroupLeader,Address,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
//                                + "GuideCount,GuideNumber,GuideFee,TotalFee) values ('"+lead+"','"+addr+"','"+nation+"',"
//                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+total+")";
                        query="insert into main_table (GroupLeader,Address,IdNum,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
                                + "GuideCount,GuideNumber,GuideFee,WalkFee,BoatFee,TotalFee,OtherFee,OtherRemark) values ('"+lead+"','"+addr+"','"+idn+"','"+nation+"',"
                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+wf+","+bf+","
                                + ""+total+","+misamt+",'"+remark+"')";
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "RECORD INSERTED");
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, e);
                    }
        }
        else if (ActivityList.getSelectedIndex()==4){
            activity = String.valueOf(ActivityList.getSelectedItem());
            String remark=otherremarks.getText();
            double misamt=Double.parseDouble(miscfee.getText());
            jf=Integer.parseInt(joyfee.getText());
            bf=Integer.parseInt(boatfees.getText());
            total=Double.parseDouble(totalamt.getText());
                        try
                    {
//INSERT INTO `main_table` (`TicketNo`, `DateTime`, `GroupLeader`, `Address`,
//`Nationality`, `MaleCount`, `FemaleCount`, `ChildrenCount`, `Activity`, `GuideCount`, 
//`Days`, `EntryFee`, `GypsyFee`, `Roomday`, `BoatFee`, `GuideFee`, `GuideNumber`, `Hours`, 
//`TawaBoatFee`, `WalkFee`, `JoyFee`, `ConopyFee`, `RoomFee`, `RoomCount`, `NoofRoom`, `RoomType`,
//`GovtFee`, `TotalFee`, `OtherFee`, `OtherRemark`) VALUES (NULL, CURRENT_TIMESTAMP,
//        '', '', '', '', '', '0', '', NULL, '0', '0', '0', NULL, '0', '0', '', '0', '0', '0', '0', '0',
//        '0', '0', NULL, NULL, '0', '', '0', NULL)                   
//                        query="insert into main_table (GroupLeader,Address,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
//                                + "GuideCount,GuideNumber,GuideFee,TotalFee) values ('"+lead+"','"+addr+"','"+nation+"',"
//                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+total+")";
                        query="insert into main_table (GroupLeader,Address,IdNum,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
                                + "GuideCount,GuideNumber,BoatFee,JoyFee,TotalFee,OtherFee,OtherRemark) values ('"+lead+"','"+addr+"','"+idn+"','"+nation+"',"
                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+bf+","+jf+","
                                + ""+total+","+misamt+",'"+remark+"')";
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "RECORD INSERTED");
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, e);
                    }
        }
        else if (ActivityList.getSelectedIndex()==5){
            activity = String.valueOf(ActivityList.getSelectedItem());
            String remark=otherremarks.getText();
            double misamt=Double.parseDouble(miscfee.getText());
            tbf=Integer.parseInt(tawa.getText());
            total=Double.parseDouble(totalamt.getText());
                    try
                    {
//INSERT INTO `main_table` (`TicketNo`, `DateTime`, `GroupLeader`, `Address`,
//`Nationality`, `MaleCount`, `FemaleCount`, `ChildrenCount`, `Activity`, `GuideCount`, 
//`Days`, `EntryFee`, `GypsyFee`, `Roomday`, `BoatFee`, `GuideFee`, `GuideNumber`, `Hours`, 
//`TawaBoatFee`, `WalkFee`, `JoyFee`, `ConopyFee`, `RoomFee`, `RoomCount`, `NoofRoom`, `RoomType`,
//`GovtFee`, `TotalFee`, `OtherFee`, `OtherRemark`) VALUES (NULL, CURRENT_TIMESTAMP,
//        '', '', '', '', '', '0', '', NULL, '0', '0', '0', NULL, '0', '0', '', '0', '0', '0', '0', '0',
//        '0', '0', NULL, NULL, '0', '', '0', NULL)                   
//                        query="insert into main_table (GroupLeader,Address,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
//                                + "GuideCount,GuideNumber,GuideFee,TotalFee) values ('"+lead+"','"+addr+"','"+nation+"',"
//                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+total+")";
                        query="insert into main_table (GroupLeader,Address,IdNum,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
                                + "TotalFee,TawaBoatFee,OtherFee,OtherRemark) values ('"+lead+"','"+addr+"','"+idn+"','"+nation+"',"
                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+total+","+tbf+","+misamt+",'"+remark+"')";
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "RECORD INSERTED");
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, e);
                    }
        }

        else if (ActivityList.getSelectedIndex()==6){
            String ga="No";
            activity = String.valueOf(ActivityList.getSelectedItem());
            String remark=otherremarks.getText();
            double misamt=Double.parseDouble(miscfee.getText());
            if (source.isSelected())
                ga="Yes";
                
            room=Integer.parseInt(roomfee.getText());
            int rcount=Integer.parseInt(String.valueOf(nroom.getSelectedItem()))+Integer.parseInt(String.valueOf(vroom.getSelectedItem()));
            String rdetails=roomdetails.getText();
            int rdays=Integer.parseInt(day.getText());
            total=Double.parseDouble(totalamt.getText());
            
            try
                    {
//INSERT INTO `main_table` (`TicketNo`, `DateTime`, `GroupLeader`, `Address`,
//`Nationality`, `MaleCount`, `FemaleCount`, `ChildrenCount`, `Activity`, `GuideCount`, 
//`Days`, `EntryFee`, `GypsyFee`, `Roomday`, `BoatFee`, `GuideFee`, `GuideNumber`, `Hours`, 
//`TawaBoatFee`, `WalkFee`, `JoyFee`, `ConopyFee`, `RoomFee`, `RoomCount`, ``, `RoomType`,
//`GovtFee`, `TotalFee`, `OtherFee`, `OtherRemark`) VALUES (NULL, CURRENT_TIMESTAMP,
//        '', '', '', '', '', '0', '', NULL, '0', '0', '0', NULL, '0', '0', '', '0', '0', '0', '0', '0',
//        '0', '0', NULL, NULL, '0', '', '0', NULL)                   
//                        query="insert into main_table (GroupLeader,Address,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
//                                + "GuideCount,GuideNumber,GuideFee,TotalFee) values ('"+lead+"','"+addr+"','"+nation+"',"
//                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+total+")";
                        query="insert into main_table (GroupLeader,Address,IdNum,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
                                + "TotalFee,GA,RoomFee,RoomCount,RoomType,Days,OtherFee,OtherRemark) values ('"+lead+"','"+addr+"','"+idn+"','"+nation+"',"
                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+total+",'"+ga+"',"+room+","+rcount+",'"+rdetails+"',"+rdays+","+misamt+",'"+remark+"')";
                        
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "RECORD INSERTED");
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, e);
                    }
        }
        else if (ActivityList.getSelectedIndex()==7){
            String remark=otherremarks.getText();
            double misamt=Double.parseDouble(miscfee.getText());
            total=Double.parseDouble(totalamt.getText());
            activity = String.valueOf(ActivityList.getSelectedItem());
             try
                    {
//INSERT INTO `main_table` (`TicketNo`, `DateTime`, `GroupLeader`, `Address`,
//`Nationality`, `MaleCount`, `FemaleCount`, `ChildrenCount`, `Activity`, `GuideCount`, 
//`Days`, `EntryFee`, `GypsyFee`, `Roomday`, `BoatFee`, `GuideFee`, `GuideNumber`, `Hours`, 
//`TawaBoatFee`, `WalkFee`, `JoyFee`, `ConopyFee`, `RoomFee`, `RoomCount`, ``, `RoomType`,
//`GovtFee`, `TotalFee`, `OtherFee`, `OtherRemark`) VALUES (NULL, CURRENT_TIMESTAMP,
//        '', '', '', '', '', '0', '', NULL, '0', '0', '0', NULL, '0', '0', '', '0', '0', '0', '0', '0',
//        '0', '0', NULL, NULL, '0', '', '0', NULL)                   ,
//                        query="insert into main_table (GroupLeader,Address,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
//                                + "GuideCount,GuideNumber,GuideFee,TotalFee) values ('"+lead+"','"+addr+"','"+nation+"',"
//                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+gc+",'"+gn+"',"+guf+","+total+")";
                        query="insert into main_table (GroupLeader,Address,IdNum,Nationality,MaleCount,FemaleCount,ChildrenCount,Activity,"
                                + "TotalFee,OtherFee,OtherRemark) values ('"+lead+"','"+addr+"','"+idn+"','"+nation+"',"
                                + ""+mc+","+fc+","+cc+",'"+activity+"',"+total+","+misamt+",'"+remark+"')";
                        
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "RECORD INSERTED");
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, e);
                    }
        
        }
    }
    
    void visibility(){
        safari_modes.setVisible(false);
        safari_types.setVisible(false);
        boat_types.setVisible(false);
        jhr.setVisible(false);
        jhrl.setVisible(false);
        roompanel.setVisible(false);  
        online.setVisible(false);
        othertext.setVisible(false);
        otheramt.setVisible(false);

    }    
    void SetActivity(){
        model = (DefaultComboBoxModel) ActivityList.getModel();
        List <String> l = new ArrayList<>();
        l=func.GetActivity(st);
        for(int i=0;i<l.size();i++){
            model.insertElementAt(l.get(i),i);
        }
        ActivityList.setModel(model);
    }
    
    String getSafariname(){
        if (ladga.isSelected()){
            return "LADGA";
        }
        else if(chutki.isSelected()){
            return "CHUTKIDEV";
        }
        else if(mahal.isSelected()){
            return "JHINJHINI MAHAL";
        }
        else if(choorna.isSelected()){
            return "CHOORNA";
        }
        else{
            return null;
        }
    }
    
    String getBoatName(){
        if (s6.isSelected()){
                return "6 Seater";
        }
        else if (s8.isSelected()){
                return "8 Seater";
        }
        else if (s10.isSelected()){
                return "10 Seater";
        }
        else{
                return null;
        }
    }
    void clear(){
        leader.setText("");
        address.setText("");
        idnum.setText("0");
        mcount.setText("0");
        fcount.setText("0");
        ccount.setText("0");
        Nation.setSelectedIndex(0);
        gc.setSelectedIndex(-1);
        g1.setSelectedIndex(0);
        g2.setSelectedIndex(0);
        g3.setSelectedIndex(0);
        g4.setSelectedIndex(0);
        ActivityList.setSelectedIndex(-1);
        buttonGroup1.clearSelection();
        cdayf.setText("0");
        otheramt.setText("0");
        othertext.setText("");
        paidamt.setSelectedIndex(0);
        online.setSelected(false);
        gsafari.setSelected(false);
        buttonGroup2.clearSelection();
        boathr.setText("0");
        nroom.setSelectedIndex(0);
        vroom.setSelectedIndex(0);
        roomdetails.setText("Room : ");
        day.setText("0");
        source.setSelected(false);
        totalamt.setText("");
        touristcount.setText("0");
        guidefees.setText("0");
        gypsefees.setText("0");
        guide_count.setText("0");
        Entryfee.setText("0");
        walkfee.setText("0");
        tawa.setText("0");
        boatfees.setText("0");
        hrs.setText("0");
        canofees.setText("0");
        joyfee.setText("0");
        roomfee.setText("0");
        miscfee.setText("0");
        otherremarks.setText("NA");
        setComponents();
        visibility();
    }
    
    void setComponents(){
        st=db.Connection();
        TicketCount.setText(String.valueOf(cal.CountTicket(st)));
        showDate.setText(func.SetDate());
    }
    
    public MainFrame() {
        initComponents();
        setComponents();
        visibility();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        Heading = new javax.swing.JLabel();
        SecondFrame = new javax.swing.JPanel();
        TicketCount = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        leader = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        Nation = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        mcount = new javax.swing.JTextField();
        fcount = new javax.swing.JTextField();
        ccount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        showDate = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        gc = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        g1 = new javax.swing.JComboBox();
        g2 = new javax.swing.JComboBox();
        g3 = new javax.swing.JComboBox();
        g4 = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        idnum = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ThirdFrame = new javax.swing.JPanel();
        roompanel = new javax.swing.JPanel();
        day = new javax.swing.JTextField();
        dayl = new javax.swing.JLabel();
        source = new javax.swing.JCheckBox();
        nroom = new javax.swing.JComboBox();
        vroom = new javax.swing.JComboBox();
        roomdetails = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ActivityList = new javax.swing.JComboBox();
        boat_types = new javax.swing.JPanel();
        s6 = new javax.swing.JRadioButton();
        s8 = new javax.swing.JRadioButton();
        s10 = new javax.swing.JRadioButton();
        hrs = new javax.swing.JLabel();
        boathr = new javax.swing.JTextField();
        safari_modes = new javax.swing.JPanel();
        online = new javax.swing.JRadioButton();
        paidamt = new javax.swing.JComboBox();
        gsafari = new javax.swing.JCheckBox();
        off = new javax.swing.JLabel();
        safari_types = new javax.swing.JPanel();
        ladga = new javax.swing.JRadioButton();
        mahal = new javax.swing.JRadioButton();
        chutki = new javax.swing.JRadioButton();
        choorna = new javax.swing.JRadioButton();
        cday = new javax.swing.JLabel();
        othersafari = new javax.swing.JRadioButton();
        keriya = new javax.swing.JRadioButton();
        cdayf = new javax.swing.JTextField();
        othertext = new javax.swing.JTextField();
        otheramt = new javax.swing.JTextField();
        jhrl = new javax.swing.JLabel();
        jhr = new javax.swing.JTextField();
        prevticket = new javax.swing.JButton();
        prevticket1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        totalamt = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        joyfee = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        canofees = new javax.swing.JLabel();
        t_hours = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        boatfees = new javax.swing.JLabel();
        tawa = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        roomfee = new javax.swing.JLabel();
        walkfee = new javax.swing.JLabel();
        Entryfee = new javax.swing.JLabel();
        guide_count = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        guidefees = new javax.swing.JLabel();
        touristcount = new javax.swing.JLabel();
        gypsefees = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        miscfee = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        otherremarks = new javax.swing.JTextField();

        Heading.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        Heading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Heading.setText("Satpuda Forest Reserve Project");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SecondFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TicketCount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        SecondFrame.add(TicketCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 220, 26));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Ticket Number :");
        SecondFrame.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 23));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Address :");
        SecondFrame.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 126, 26));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Group Leader :");
        SecondFrame.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 126, 34));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Nationality :");
        SecondFrame.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 126, 26));

        leader.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        SecondFrame.add(leader, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 150, -1));

        address.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        SecondFrame.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 110, -1));

        Nation.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Nation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Indian", "Foreigner" }));
        SecondFrame.add(Nation, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 217, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Male Count :");
        SecondFrame.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 119, 32));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Children Count :");
        SecondFrame.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 178, 29));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Female Count :");
        SecondFrame.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, 26));

        mcount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        mcount.setText("0");
        SecondFrame.add(mcount, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 90, -1));

        fcount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        fcount.setText("0");
        SecondFrame.add(fcount, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 110, -1));

        ccount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ccount.setText("0");
        ccount.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        SecondFrame.add(ccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 90, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Date :");
        SecondFrame.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 62, 26));

        showDate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        SecondFrame.add(showDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 120, 26));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Guide Count:");
        SecondFrame.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 110, 20));

        gc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        gc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        gc.setSelectedIndex(-1);
        SecondFrame.add(gc, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 100, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Guide Number ");
        SecondFrame.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 110, 30));

        g1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        g1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35" }));
        SecondFrame.add(g1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 60, 30));

        g2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        g2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35" }));
        SecondFrame.add(g2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 60, 30));

        g3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        g3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35" }));
        SecondFrame.add(g3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 60, 30));

        g4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        g4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35" }));
        SecondFrame.add(g4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 60, 30));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel29.setText("ID No.");
        SecondFrame.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 119, 32));

        idnum.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        idnum.setText("0");
        SecondFrame.add(idnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 162, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("TOTAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        SecondFrame.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 270, 200, 30));

        getContentPane().add(SecondFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 980, 320));

        ThirdFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roompanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        day.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        day.setText("0");
        roompanel.add(day, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 70, 20));

        dayl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        dayl.setText("DAYS");
        roompanel.add(dayl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 20));

        source.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        source.setText("Govt.");
        roompanel.add(source, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 70, -1));

        nroom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        nroom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));
        roompanel.add(nroom, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 107, -1));

        vroom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        vroom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2" }));
        roompanel.add(vroom, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 110, -1));

        roomdetails.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        roomdetails.setText("Room :");
        roompanel.add(roomdetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 110, 30));

        ThirdFrame.add(roompanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 140, 210));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Select Activity :");
        ThirdFrame.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 30));

        ActivityList.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ActivityList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gypsy_Safari", "Boating", "Cannoing", "Walking", "Joy_Ride", "Tawa_Boat_Fee", "Room", "OTHER" }));
        ActivityList.setSelectedIndex(-1);
        ActivityList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivityListActionPerformed(evt);
            }
        });
        ThirdFrame.add(ActivityList, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 230, 23));

        boat_types.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup2.add(s6);
        s6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        s6.setText("6 SEATER");
        boat_types.add(s6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        buttonGroup2.add(s8);
        s8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        s8.setText("8 SEATER");
        boat_types.add(s8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        buttonGroup2.add(s10);
        s10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        s10.setText("10 SEATER");
        boat_types.add(s10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        hrs.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        hrs.setText("HOURS :");
        boat_types.add(hrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        boathr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        boathr.setText("0");
        boat_types.add(boathr, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 90, -1));

        ThirdFrame.add(boat_types, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 180, 150));

        online.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        online.setText("Online");

        paidamt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4" }));

        gsafari.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        gsafari.setText("GA");

        off.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        off.setText("OFFLINE");

        javax.swing.GroupLayout safari_modesLayout = new javax.swing.GroupLayout(safari_modes);
        safari_modes.setLayout(safari_modesLayout);
        safari_modesLayout.setHorizontalGroup(
            safari_modesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(safari_modesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(safari_modesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paidamt, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gsafari, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(off, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(online))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        safari_modesLayout.setVerticalGroup(
            safari_modesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(safari_modesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gsafari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(online)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(off, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paidamt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ThirdFrame.add(safari_modes, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 150, 140));

        buttonGroup1.add(ladga);
        ladga.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ladga.setText("LAGDA");
        ladga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ladgaActionPerformed(evt);
            }
        });

        buttonGroup1.add(mahal);
        mahal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        mahal.setText("JHINJHINI MAHAL");
        mahal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mahalActionPerformed(evt);
            }
        });

        buttonGroup1.add(chutki);
        chutki.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        chutki.setText("CHUTKIDEV");
        chutki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chutkiActionPerformed(evt);
            }
        });

        buttonGroup1.add(choorna);
        choorna.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        choorna.setText("CHOORNA");
        choorna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choornaActionPerformed(evt);
            }
        });

        cday.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cday.setText("DAYS :");

        buttonGroup1.add(othersafari);
        othersafari.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        othersafari.setText("Other");
        othersafari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                othersafariActionPerformed(evt);
            }
        });

        buttonGroup1.add(keriya);
        keriya.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        keriya.setText("KERIYA");
        keriya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keriyaActionPerformed(evt);
            }
        });

        cdayf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cdayf.setText("0");
        cdayf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdayfActionPerformed(evt);
            }
        });

        othertext.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        othertext.setText("NA");

        otheramt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        otheramt.setText("0");

        javax.swing.GroupLayout safari_typesLayout = new javax.swing.GroupLayout(safari_types);
        safari_types.setLayout(safari_typesLayout);
        safari_typesLayout.setHorizontalGroup(
            safari_typesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(safari_typesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(safari_typesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chutki)
                    .addComponent(mahal)
                    .addComponent(choorna)
                    .addGroup(safari_typesLayout.createSequentialGroup()
                        .addComponent(ladga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(keriya)))
                .addGap(34, 34, 34))
            .addGroup(safari_typesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(safari_typesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(othersafari, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(safari_typesLayout.createSequentialGroup()
                        .addComponent(cday)
                        .addGap(18, 18, 18)
                        .addComponent(cdayf, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(safari_typesLayout.createSequentialGroup()
                        .addComponent(othertext, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(otheramt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        safari_typesLayout.setVerticalGroup(
            safari_typesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(safari_typesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(safari_typesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ladga)
                    .addComponent(keriya, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(mahal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chutki)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(choorna)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(safari_typesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cday, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdayf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(othersafari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(safari_typesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(othertext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otheramt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ThirdFrame.add(safari_types, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, -1));

        jhrl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jhrl.setText("HOURS :");
        ThirdFrame.add(jhrl, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 70, 30));

        jhr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jhr.setText("0");
        ThirdFrame.add(jhr, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 90, 30));

        prevticket.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        prevticket.setText("VIEW TICKETS");
        prevticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevticketActionPerformed(evt);
            }
        });
        ThirdFrame.add(prevticket, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, 200, 30));

        prevticket1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        prevticket1.setText("PREV. TICKETS");
        prevticket1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevticket1ActionPerformed(evt);
            }
        });
        ThirdFrame.add(prevticket1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 200, 30));

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton6.setText("GET REPORT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        ThirdFrame.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 210, 200, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setText("TOTAL :");
        ThirdFrame.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, -1, 20));

        totalamt.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        ThirdFrame.add(totalamt, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 250, 120, 40));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("SAVE AND PRINT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        ThirdFrame.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 200, 30));

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setText("CLEAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        ThirdFrame.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 200, 30));

        getContentPane().add(ThirdFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 990, 300));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel28.setText("SATPUDA TIGER RESERVE, HOSHANGABAD (CORE ZONE MADHAI)");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1240, 70));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setText("Joy Fees: ");

        joyfee.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        joyfee.setText("0");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setText("Canopy Fees: ");

        canofees.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        canofees.setText("0");

        t_hours.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        t_hours.setText("0");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("Hours :");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("Boat Fees :");

        boatfees.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        boatfees.setText("0");

        tawa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tawa.setText("0");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText("Tawa BoatFees :");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setText("Room Fees");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setText("Walk Fees: ");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Guide Count :");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Entry Fees: ");

        roomfee.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        roomfee.setText("0");

        walkfee.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        walkfee.setText("0");

        Entryfee.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Entryfee.setText("0");

        guide_count.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        guide_count.setText("0");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setText("Gypse Fees :");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Total Tourist :");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setText("Guide Fees :");

        guidefees.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        guidefees.setText("0");

        touristcount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        touristcount.setText("0");

        gypsefees.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        gypsefees.setText("0");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel26.setText("Other Amt.");

        miscfee.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        miscfee.setText("0");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setText("Remarks");

        otherremarks.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        otherremarks.setText("NA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(touristcount, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(guidefees, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(gypsefees, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(guide_count, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(Entryfee, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(walkfee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tawa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(canofees, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(boatfees, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(joyfee, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roomfee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(51, 51, 51)
                                    .addComponent(miscfee, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(otherremarks, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(touristcount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guidefees, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gypsefees, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guide_count, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Entryfee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(walkfee, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tawa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boatfees, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(canofees, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(joyfee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomfee, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(miscfee, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(otherremarks, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 70, 270, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
String gova="No";
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int male,female,child,Vtotal,Cal_total;
        male=Integer.parseInt(mcount.getText());
        female=Integer.parseInt(fcount.getText());
        child=Integer.parseInt(ccount.getText());
        Cal_total=male+female;
        Vtotal=male+female+child;
        
        int guide=0,boat=0,Entryfees=0,gypsy=0,amt=0,tamt=0;;
        double hr=0;

        if (ladga.isSelected()==true){
            
            if (online.isSelected()==true){
                if(Cal_total>6){
                    JOptionPane.showMessageDialog(null,"Maximum 6 Allowed in a Ticket");
                }
                else{
                    Entryfees=0;
                    gypsy=2700;
                    boat=50*Cal_total;
                    guide=480;
                    int mis=Integer.parseInt(miscfee.getText());
                    guide_count.setText(String.valueOf(gc.getSelectedItem()));
                    boatfees.setText(String.valueOf(boat));
                    gypsefees.setText(String.valueOf(gypsy));
                    Entryfee.setText(String.valueOf(Entryfees));
                    totalamt.setText(String.valueOf(cal.Ladga_Online(Cal_total)+mis));
                    
                }
            }
            else{
                if(Cal_total>6){
                    JOptionPane.showMessageDialog(null,"Maximum 6 Allowed in a Ticket");
                }
                else{
                    if (gsafari.isSelected()){
                        gova="Yes";
                        Entryfees=0;
                    }
                    else
                        Entryfees=1500;
                    gypsy=2700;
                    boat=50*Cal_total;
                    guide=480;
                    int mis=Integer.parseInt(miscfee.getText());
                    guide_count.setText(String.valueOf(gc.getSelectedItem()));
                    boatfees.setText(String.valueOf(boat));
                    gypsefees.setText(String.valueOf(gypsy));
                    Entryfee.setText(String.valueOf(Entryfees));
                    totalamt.setText(String.valueOf(cal.Ladga(Cal_total,Entryfees)+mis));
                    
                }
            }
        }
        else if (keriya.isSelected()==true){
            
            if (online.isSelected()==true){
                if(Cal_total>6){
                    JOptionPane.showMessageDialog(null,"Maximum 6 Allowed in a Ticket");
                }
                else{
                    Entryfees=0;
                    gypsy=2000;
                    boat=50*Cal_total;
                    guide=480;
                    int mis=Integer.parseInt(miscfee.getText());
                    guide_count.setText(String.valueOf(gc.getSelectedItem()));
                    boatfees.setText(String.valueOf(boat));
                    gypsefees.setText(String.valueOf(gypsy));
                    Entryfee.setText(String.valueOf(Entryfees));
                    totalamt.setText(String.valueOf(cal.Keriya_Online(Cal_total)+mis));
                    
                }
            }
            else{
                if(Cal_total>6){
                    JOptionPane.showMessageDialog(null,"Maximum 6 Allowed in a Ticket");
                }
                else{
                    if (gsafari.isSelected()){
                        gova="Yes";
                        Entryfees=0;
                    }
                    else
                        Entryfees=1500;
                    gypsy=2000;
                    boat=50*Cal_total;
                    guide=480;
                    int mis=Integer.parseInt(miscfee.getText());
                    guide_count.setText(String.valueOf(gc.getSelectedItem()));
                    boatfees.setText(String.valueOf(boat));
                    gypsefees.setText(String.valueOf(gypsy));
                    Entryfee.setText(String.valueOf(Entryfees));
                    totalamt.setText(String.valueOf(cal.Keriya(Cal_total,Entryfees)+mis));
                    
                }
            }
        }
        else if (othersafari.isSelected()){
            gypsefees.setText(String.valueOf(otheramt.getText()));
            totalamt.setText(String.valueOf(otheramt.getText()));
        }
        else if (mahal.isSelected()==true){
            
            if (online.isSelected()==true){
                if(Cal_total>6){
                    JOptionPane.showMessageDialog(null,"Maximum 6 Allowed in a Ticket");
                }
                else{
                    Entryfees=0;
                    gypsy=3300;
                    boat=50*Cal_total;
                    guide=480;
                    int mis=Integer.parseInt(miscfee.getText());
                    guide_count.setText(String.valueOf(gc.getSelectedItem()));
                    boatfees.setText(String.valueOf(boat));
                    gypsefees.setText(String.valueOf(gypsy));
                    Entryfee.setText(String.valueOf(Entryfees));
                    totalamt.setText(String.valueOf(cal.Mahal_Online(Cal_total)+mis));
                    
                }
            }
            else{
                if(Cal_total>6){
                    JOptionPane.showMessageDialog(null,"Maximum 6 Allowed in a Ticket");
                }
                else{
                    if (gsafari.isSelected()){
                        gova="Yes";
                        Entryfees=0;
                    }
                    else
                        Entryfees=1500;
                    gypsy=3300;
                    boat=50*Cal_total;
                    guide=480;
                    int mis=Integer.parseInt(miscfee.getText());
                    guide_count.setText(String.valueOf(gc.getSelectedItem()));
                    boatfees.setText(String.valueOf(boat));
                    gypsefees.setText(String.valueOf(gypsy));
                    Entryfee.setText(String.valueOf(Entryfees));
                    totalamt.setText(String.valueOf(cal.Mahal(Cal_total,Entryfees)+mis));
                    
                }
            }
        }
        
        else if (chutki.isSelected()==true){
            
            if (online.isSelected()==true){
                if(Cal_total>6){
                    JOptionPane.showMessageDialog(null,"Maximum 6 Allowed in a Ticket");
                }
                else{
                    Entryfees=0;
                    gypsy=4000;
                    boat=50*Cal_total;
                    guide=480;
                    int mis=Integer.parseInt(miscfee.getText());
                    guide_count.setText(String.valueOf(gc.getSelectedItem()));
                    boatfees.setText(String.valueOf(boat));
                    gypsefees.setText(String.valueOf(gypsy));
                    Entryfee.setText(String.valueOf(Entryfees));
                    totalamt.setText(String.valueOf(cal.Dev_online(Cal_total)+mis));
                    
                }
            }
            else{
                if(Cal_total>6){
                    JOptionPane.showMessageDialog(null,"Maximum 6 Allowed in a Ticket");
                }
                else{
                    if (gsafari.isSelected()){
                        gova="Yes";
                        Entryfees=0;
                    }
                    else
                        Entryfees=1500;
                    int mis=Integer.parseInt(miscfee.getText());
                    gypsy=4000;
                    boat=50*Cal_total;
                    guide=480;
                    guide_count.setText(String.valueOf(gc.getSelectedItem()));
                    boatfees.setText(String.valueOf(boat));
                    gypsefees.setText(String.valueOf(gypsy));
                    Entryfee.setText(String.valueOf(Entryfees));
                    totalamt.setText(String.valueOf(cal.Dev(Cal_total,Entryfees)+mis));
                    
                }
            }
        }
//        Doubtfull work 
        else if (choorna.isSelected()==true){
            double d=0;
            
            if(Cal_total>6){
                    JOptionPane.showMessageDialog(null,"Maximum 6 Allowed in a Ticket");
            }
            else{
                    int paid;
                    int mis=Integer.parseInt(miscfee.getText());
                    Entryfees=1500*Integer.parseInt(String.valueOf(paidamt.getSelectedItem()));
                    gypsy=5500;
                    boat=50*Cal_total;
                    guide=960;
                    d=Double.parseDouble(cdayf.getText());
                    boatfees.setText(String.valueOf(boat));
                    if (d>1){
                        Entryfees*=d;
                        gypsy*=d;
                        guide*=d;;
                    }
                    guide_count.setText(String.valueOf(gc.getSelectedItem()));
                    gypsefees.setText(String.valueOf(gypsy));
                    Entryfee.setText(String.valueOf(Entryfees));
                    totalamt.setText(String.valueOf(gypsy+Entryfees+boat+guide+mis));
                    
                }
            }
        
// Chooorna 
        else if (s6.isSelected()==true){
            int mis=Integer.parseInt(miscfee.getText());
            boat=1200;
            guide=480;
            hr=Double.parseDouble(boathr.getText());
            amt=cal.Six_Boat(hr);
            tamt+=guide+amt;
            guide_count.setText(String.valueOf(gc.getSelectedItem()));
            t_hours.setText(String.valueOf(hr));
            boatfees.setText(String.valueOf(amt));
            totalamt.setText(String.valueOf(tamt+mis));
           
        }
        else if (s8.isSelected()==true){
            int mis=Integer.parseInt(miscfee.getText());
            boat=1350;
            guide=480;
            hr=Double.parseDouble(boathr.getText());
            amt=cal.Eight_boat(hr);
            tamt+=guide+amt;
            guide_count.setText(String.valueOf(gc.getSelectedItem()));
            t_hours.setText(String.valueOf(hr));
            boatfees.setText(String.valueOf(amt));
            totalamt.setText(String.valueOf(tamt+mis));
           
        }
        else if (s10.isSelected()==true){
            boat=1500;
            int mis=Integer.parseInt(miscfee.getText());
            guide=480;
            hr=Double.parseDouble(boathr.getText());
            amt=cal.Ten_boat(hr);
            tamt+=guide+amt;
            guide_count.setText(String.valueOf(gc.getSelectedItem()));
            t_hours.setText(String.valueOf(hr));
            boatfees.setText(String.valueOf(amt));
            totalamt.setText(String.valueOf(tamt+mis));
           
        }
        else if (ActivityList.getSelectedIndex()==2){
            Entryfees=900;
            int mis=Integer.parseInt(miscfee.getText());
            guide=600*Integer.parseInt(String.valueOf(gc.getSelectedItem()));
            amt=cal.Cannoning(Cal_total);
            tamt+=guide+amt;
            guide_count.setText(String.valueOf(gc.getSelectedItem()));
            canofees.setText(String.valueOf(amt));
            totalamt.setText(String.valueOf(tamt+mis));
            
        }
        else if (ActivityList.getSelectedIndex()==3){
            Entryfees=250;
            int mis=Integer.parseInt(miscfee.getText());
            guide=700*Integer.parseInt(String.valueOf(gc.getSelectedItem()));
            boat=150;
            amt=cal.Walking(Cal_total);
            boat*=Cal_total;
            tamt+=guide+amt+boat;
            guide_count.setText(String.valueOf(gc.getSelectedItem()));
            boatfees.setText(String.valueOf(boat));
            walkfee.setText(String.valueOf(amt));
            totalamt.setText(String.valueOf(tamt+mis));
            
        }
        else if (ActivityList.getSelectedIndex()==4){
            int mis=Integer.parseInt(miscfee.getText());
            Entryfees=1000;
            boat=150*Cal_total;
            hr=Double.parseDouble(jhr.getText());
            amt=(int) cal.JoyRide(Cal_total,hr);
            tamt=amt+boat;
            boatfees.setText(String.valueOf(boat));
            joyfee.setText(String.valueOf(amt));
            t_hours.setText(String.valueOf(hr));
            totalamt.setText(String.valueOf(tamt+mis));
            
        }
        else if (ActivityList.getSelectedIndex()==5){
            int mis=Integer.parseInt(miscfee.getText());
            int tawa_trip=1100;
            tawa.setText(String.valueOf(tawa_trip));
            totalamt.setText(String.valueOf(tawa_trip+mis));
            
        }
        else if (ActivityList.getSelectedIndex()==6){
            int mis=Integer.parseInt(miscfee.getText());
            int gn=400,gv=600;
            int cn=0,cv=0;
            int n=2000,v=3000;
            int days=Integer.parseInt(day.getText());
            int rate,total=0;
            cn=Integer.parseInt(String.valueOf(nroom.getSelectedItem()));
            cv=Integer.parseInt(String.valueOf(vroom.getSelectedItem()));
            if (source.isSelected()){
                total=cn*gn*days;
                total+=cv*gv*days;
                roomfee.setText(String.valueOf(total));
                totalamt.setText(String.valueOf(total+mis));
                
            }
            else{
                total=cn*n*days;
                total+=cv*v*days;
                roomfee.setText(String.valueOf(total));
                totalamt.setText(String.valueOf(total+mis));
            }
            
        }
        else if (ActivityList.getSelectedIndex()==7){
            int mis=Integer.parseInt(miscfee.getText());
            totalamt.setText(String.valueOf(mis));
            
        }
        touristcount.setText(String.valueOf(Vtotal));     
        guidefees.setText(String.valueOf(guide));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ActivityListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivityListActionPerformed
        // TODO add your handling code here:
        int male,female,child,Vtotal,Cal_total;
        male=Integer.parseInt(mcount.getText());
        female=Integer.parseInt(fcount.getText());
        child=Integer.parseInt(ccount.getText());
        Cal_total=male+female;
        Vtotal=male+female+child;
        touristcount.setText(String.valueOf(Vtotal));
        if(ActivityList.getSelectedIndex()==0){
            online.setVisible(true);
//            onlinepaid.setVisible(true);
//            chamt.setVisible(true);
            safari_modes.setVisible(true);
            safari_types.setVisible(true);
            boat_types.setVisible(false);
        }
        else if(ActivityList.getSelectedIndex()==0){
            online.setVisible(false);
//            onlinepaid.setVisible(false);
//            chamt.setVisible(false);
            safari_modes.setVisible(true);
            safari_types.setVisible(true);
            boat_types.setVisible(false);
        }
        else if (ActivityList.getSelectedIndex()==1){
            safari_modes.setVisible(false);
            safari_types.setVisible(false);
            boat_types.setVisible(true);
        }
        else if (ActivityList.getSelectedIndex()==4){
            jhr.setVisible(true);
            jhrl.setVisible(true);
        }
        else if (ActivityList.getSelectedIndex()==6){
            roompanel.setVisible(true);
        }
        else{
            visibility();
        }
    }//GEN-LAST:event_ActivityListActionPerformed

    private void choornaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choornaActionPerformed
        // TODO add your handling code here:
        online.setVisible(false);
        paidamt.setVisible(true);
        off.setVisible(true);
        gsafari.setVisible(false);
    }//GEN-LAST:event_choornaActionPerformed

    private void ladgaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ladgaActionPerformed
        // TODO add your handling code here:
        online.setVisible(true);
        paidamt.setVisible(false);
        off.setVisible(false);
        gsafari.setVisible(true);
    }//GEN-LAST:event_ladgaActionPerformed

    private void mahalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mahalActionPerformed
        // TODO add your handling code here:
        online.setVisible(true);
        paidamt.setVisible(false);
        off.setVisible(false);
        gsafari.setVisible(true);
    }//GEN-LAST:event_mahalActionPerformed

    private void chutkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chutkiActionPerformed
        // TODO add your handling code here:
        online.setVisible(true);
        paidamt.setVisible(false);
        off.setVisible(false);
        gsafari.setVisible(true);
    }//GEN-LAST:event_chutkiActionPerformed

    private void othersafariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_othersafariActionPerformed
        // TODO add your handling code here:
        othertext.setVisible(true);
        otheramt.setVisible(true);
    }//GEN-LAST:event_othersafariActionPerformed

    private void prevticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevticketActionPerformed
        // TODO add your handling code here:
               this.dispose();
               Connection pconn;
       print_connection p = new print_connection();
       pconn=print_connection.Connection();
        
        try{
          //InputStream inp = new FileInputStream(new File("G:\\COLLEGE\\java\\MyProject\\src\\myproject\\Bill_1.jrxml"));
          InputStream inp = new FileInputStream(new File("F:\\Madhai Software\\reports\\MadhaiTicket.jrxml"));
            JasperDesign jd =JRXmlLoader.load(inp);
            String sql ="select * from main_table";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr =JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            JasperPrint j = JasperFillManager.fillReport(jr,para,pconn);
            JasperViewer.viewReport(j,false);
            OutputStream os = new FileOutputStream(new File("F:\\Madhai Software\\reports\\abc.jrxml"));
            JasperExportManager.exportReportToPdfStream(j,os);
           }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_prevticketActionPerformed

    private void keriyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keriyaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keriyaActionPerformed

    private void cdayfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdayfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cdayfActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new ReportFrame().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void prevticket1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevticket1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
                new PrevFrame().setVisible(true);
    }//GEN-LAST:event_prevticket1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        savedata();
        printmain();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ActivityList;
    private javax.swing.JLabel Entryfee;
    private javax.swing.JLabel Heading;
    private javax.swing.JComboBox Nation;
    private javax.swing.JPanel SecondFrame;
    private javax.swing.JPanel ThirdFrame;
    private javax.swing.JLabel TicketCount;
    private javax.swing.JTextField address;
    private javax.swing.JPanel boat_types;
    private javax.swing.JLabel boatfees;
    private javax.swing.JTextField boathr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JLabel canofees;
    private javax.swing.JTextField ccount;
    private javax.swing.JLabel cday;
    private javax.swing.JTextField cdayf;
    private javax.swing.JRadioButton choorna;
    private javax.swing.JRadioButton chutki;
    private javax.swing.JTextField day;
    private javax.swing.JLabel dayl;
    private javax.swing.JTextField fcount;
    private javax.swing.JComboBox g1;
    private javax.swing.JComboBox g2;
    private javax.swing.JComboBox g3;
    private javax.swing.JComboBox g4;
    private javax.swing.JComboBox gc;
    private javax.swing.JCheckBox gsafari;
    private javax.swing.JLabel guide_count;
    private javax.swing.JLabel guidefees;
    private javax.swing.JLabel gypsefees;
    private javax.swing.JLabel hrs;
    private javax.swing.JTextField idnum;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jhr;
    private javax.swing.JLabel jhrl;
    private javax.swing.JLabel joyfee;
    private javax.swing.JRadioButton keriya;
    private javax.swing.JRadioButton ladga;
    private javax.swing.JTextField leader;
    private javax.swing.JRadioButton mahal;
    private javax.swing.JTextField mcount;
    private javax.swing.JTextField miscfee;
    private javax.swing.JComboBox nroom;
    private javax.swing.JLabel off;
    private javax.swing.JRadioButton online;
    private javax.swing.JTextField otheramt;
    private javax.swing.JTextField otherremarks;
    private javax.swing.JRadioButton othersafari;
    private javax.swing.JTextField othertext;
    private javax.swing.JComboBox paidamt;
    private javax.swing.JButton prevticket;
    private javax.swing.JButton prevticket1;
    private javax.swing.JTextField roomdetails;
    private javax.swing.JLabel roomfee;
    private javax.swing.JPanel roompanel;
    private javax.swing.JRadioButton s10;
    private javax.swing.JRadioButton s6;
    private javax.swing.JRadioButton s8;
    private javax.swing.JPanel safari_modes;
    private javax.swing.JPanel safari_types;
    private javax.swing.JLabel showDate;
    private javax.swing.JCheckBox source;
    private javax.swing.JLabel t_hours;
    private javax.swing.JLabel tawa;
    private javax.swing.JLabel totalamt;
    private javax.swing.JLabel touristcount;
    private javax.swing.JComboBox vroom;
    private javax.swing.JLabel walkfee;
    // End of variables declaration//GEN-END:variables

}
