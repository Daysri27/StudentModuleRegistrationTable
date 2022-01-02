/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ape2je;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class RegisterModule extends javax.swing.JFrame {

    Connection con = ConnectDatabase.connectdb();
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    LoginPage lp = new LoginPage();
    String uid = lp.getUserID();
    WelcomePage wp = new WelcomePage();
    String module = wp.getModule();
    String credit = wp.getCredit();
    Moduledetails md = new Moduledetails();
    String occurence = md.getOcc();
    String cap = md.getCap();
    String activity = "";
    String activity2 = "";
    String module2 = "";
    String occurence2 = "";
    String day = "";

    /**
     * Creates new form RegisterModule
     */
    public RegisterModule() {
        ConnectDatabase.connectdb();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public boolean checkModule() {
        String q1 = "SELECT * FROM USERMODULE WHERE USERID = ? AND R_MODULE = ?";
        try {
            ps = con.prepareStatement(q1);
            ps.setString(1, uid);
            ps.setString(2, module);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    public boolean checkCapacity() {
        try {
            String q2 = "SELECT DISTINCT USERID FROM APP.USERMODULE INNER JOIN APP.VALIDMODULES2 ON USERMODULE.R_MODULE = VALIDMODULES2.MODULES AND USERMODULE.R_OCCURENCE = VALIDMODULES2.OCCURENCE WHERE MODULES = '" + module + "' AND OCCURENCE = " + occurence;
            int j = 0;
            ps = con.prepareStatement(q2);
            rs = ps.executeQuery();
                while (rs.next()) {
                    j++;
                }
            int i = Integer.parseInt(cap);
            if (i >= j) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkTime() {
        String q1 = "SELECT * FROM APP.USERMODULE INNER JOIN APP.VALIDMODULES2 ON USERMODULE.R_MODULE = VALIDMODULES2.MODULES WHERE USERMODULE.R_OCCURENCE = VALIDMODULES2.OCCURENCE AND USERMODULE.USERID = '" + uid + "'";
        String q2 = "SELECT * FROM APP.VALIDMODULES2 WHERE MODULES = '" + module + "' AND OCCURENCE = " + occurence;
        try {
            String TIMESTART = "";
            String TIMEEND = "";
            String TIMESTART2 = "";
            String TIMEEND2 = "";
            String DAY2 = "";
            String CAP = "";
            ps = con.prepareStatement(q2);
            rs = ps.executeQuery();
            while (rs.next()) {
                DAY2 = rs.getString("DAY");
                TIMESTART2 = rs.getString("TIMESTART");
                TIMEEND2 = rs.getString("TIMEEND");
                activity = rs.getString("ACTIVITYTYPE");
                LocalTime compareStart = LocalTime.parse(TIMESTART2);
                LocalTime compareEnd = LocalTime.parse(TIMEEND2);
                ps2 = con.prepareStatement(q1);
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    day = rs.getString("DAY");
                    TIMESTART = rs2.getString("TIMESTART");
                    TIMEEND = rs2.getString("TIMEEND");
                    activity2 = rs2.getString("ACTIVITYTYPE");
                    module2 = rs2.getString("MODULES");
                    occurence2 = rs2.getString("OCCURENCE");
                    LocalTime targetStart = LocalTime.parse(TIMESTART);
                    LocalTime targetEnd = LocalTime.parse(TIMEEND);
                    if (day == null ? DAY2 == null : day.equals(DAY2)) {
                        
                        boolean NoClashClassAfterEnd = (targetStart.isAfter(compareEnd) || targetStart.equals(compareEnd));
                        boolean NoClashClassBeforeStart = (targetEnd.isBefore(compareStart) || targetEnd.equals(compareStart));
                        JOptionPane.showMessageDialog(null, NoClashClassAfterEnd + " " + NoClashClassBeforeStart);
                        if (NoClashClassAfterEnd || NoClashClassBeforeStart) {
                            
                            continue;
                        } else {
                            
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 36)); // NOI18N
        jLabel1.setText("Confrim registration for:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Module : "+ module);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Occurence : " + occurence);

        jButton1.setText("YES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("NO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //String q1 = "CREATE TABLE IF NOT EXISTS USERMODULE (USERID VARCHAR(45) NOT NULL, R_MODULE VARCHAR(45) NOT NULL, R_OCCURENCE INTEGER DEFAULT 0  NOT NULL)";
        String q2 = "INSERT INTO APP.USERMODULE (USERID, R_MODULE, R_OCCURENCE) VALUES ('" + uid + "', '" + module + "', " + occurence + ")";
        try {

            boolean MExist = checkModule();
            boolean Clash = checkTime();
            boolean Full = checkCapacity();
            //if(MExist){
            //JOptionPane.showMessageDialog(null, "Module already exist");
            //}
            if (Clash) {
                JOptionPane.showMessageDialog(null, "THE " + activity + " FOR " + module + " OCCURENCE " + occurence + " CLASHES WITH " + activity2 + " FOR " + module2 + " OCCURENCE " + occurence2 + " ON " + day + ".");
            } else if (Full) {
                JOptionPane.showMessageDialog(null, "This occurence is full");
            } else {
                ps = con.prepareStatement(q2);
                ps.executeUpdate();
                dispose();
                new WelcomePage().setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterModule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
