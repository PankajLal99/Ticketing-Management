/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ticketcounter;

import Connectivity.print_connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Pankaj
 */
public class ReportFrame extends javax.swing.JFrame {

    /**
     * Creates new form ReportFrame
     */
    public ReportFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        end = new com.toedter.calendar.JDateChooser();
        start = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton9.setText("GET DETAILED REPORT");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("START");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("END");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("Report");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel30)
                .addContainerGap(164, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel30)
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jButton9)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startdate=sdf.format(start.getDate());
        String enddate=sdf.format(end.getDate());
        System.out.println(enddate);
        System.out.println(startdate);
        Connection pconn;
        print_connection p = new print_connection();
        pconn=print_connection.Connection();
        //        SELECT *,nation, EntryMode, SUM(MaleCount), SUM(FemaleCount), SUM(ChildCount), SUM(EntryCharge),SUM(TrackingCharge),SUM(EntryCharge+TrackingCharge+CampingCharge+VidCharge+GuideCharge+OtherCharge) as income,SUM(CampingCharge),SUM(VidCharge),SUM(GuideCharge),SUM(EntryCharge+TrackingCharge+CampingCharge+VidCharge+OtherCharge) AS pdf,count(GuideNum)
        //,COUNT(TicketNo),SUM(OtherCharge) FROM main_table WHERE GuideNum>=0 GROUP BY EntryMode, Nation
        try{
            //            InputStream inp = new FileInputStream(new File("G:\\COLLEGE\\java\\MyProject\\src\\myproject\\Bill_1.jrxml"));
            //            InputStream inp = new FileInputStream(new File("D:\\Study Material\\SEM VII\\Java\\PachmarhiTicketCounter\\src\\pachmarhiticketcounter\\DetailedReport.jrxml"));
            InputStream inp = new FileInputStream(new File("F:\\Madhai Software\\reports\\MadhaiReport.jrxml"));
            JasperDesign jd =JRXmlLoader.load(inp);
//            SELECT DATETIME,Nationality,Activity,COUNT(TicketNo),SUM(MaleCount),SUM(FemaleCount),SUM(ChildrenCount),SUM(GuideCount),SUM(MaleCount+FemaleCount+ChildrenCount) as TouristCount,SUM(EntryFee),SUM(EntryFee+BoatFee+TawaBoatFee+JoyFee+ConopyFee+WalkFee+RoomFee) as PDF,SUM(GypsyFee),SUM(BoatFee),SUM(GuideFee),SUM(TawaBoatFee),SUM(JoyFee),SUM(ConopyFee),SUM(WalkFee),SUM(RoomFee),SUM(OtherFee),SUM(TotalFee) FROM main_table GROUP BY Nationality,Activity ORDER BY Nationality
            
            String sql="SELECT DATETIME,Nationality,Activity,COUNT(TicketNo),SUM(MaleCount),SUM(FemaleCount)"
                    + ",SUM(ChildrenCount),SUM(GuideCount),SUM(MaleCount+FemaleCount+ChildrenCount) as TouristCount,"
                    + "SUM(EntryFee),SUM(EntryFee+BoatFee+TawaBoatFee+JoyFee+ConopyFee+WalkFee+RoomFee) as PDF,SUM(GypsyFee),"
                    + "SUM(BoatFee),SUM(GuideFee),SUM(TawaBoatFee),SUM(JoyFee),SUM(ConopyFee),SUM(WalkFee),SUM(RoomFee),"
                    + "SUM(OtherFee),SUM(TotalFee) FROM main_table WHERE DateTime BETWEEN '"+startdate+"' AND '"+enddate+"' GROUP BY Nationality,Activity ORDER BY Nationality";
            
            
//            String sql ="SELECT DATETIME,Nationality,Activity,COUNT(TicketNo),SUM(MaleCount),SUM(FemaleCount),SUM(ChildrenCount),SUM(GuideCount),SUM(MaleCount + FemaleCount + ChildrenCount) AS TouristCount,"
//                    + "SUM(EntryFee),SUM(EntryFee+BoatFee+TawaBoatFee+JoyFee+ConopyFee+WalkFee) as PDF,SUM(RoomFee),SUM(GypsyFee),SUM(BoatFee),SUM(GuideFee),SUM(TawaBoatFee),SUM(JoyFee),SUM(ConopyFee),SUM(WalkFee),SUM(OtherFee),SUM(TotalFee)\n" +
//"FROM main_table  GROUP BY Nationality,Activity ORDER BY Nationality";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr =JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();

            JasperPrint j = JasperFillManager.fillReport(jr,para,pconn);
            JasperViewer.viewReport(j,false);
            //            OutputStream os = new FileOutputStream(new File("DD:\\Study Material\\SEM VII\\Java\\PachmarhiTicketCounter\\reports\\dr.jrxml"));
            OutputStream os = new FileOutputStream(new File("F:\\Madhai Software\\reports\\dr.jrxml"));
            JasperExportManager.exportReportToPdfStream(j,os);

        }
        catch(FileNotFoundException | JRException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser end;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser start;
    // End of variables declaration//GEN-END:variables
}
