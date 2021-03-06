/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmanager;

import database.accountInterface;
import database.mysqlConnector;
import database.userManipulation;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import logger.LoggerCode;
import logger.LoggerInterface;
import manager.managerUI;
import signup.signupUI;
import singleton.singletonUI;

/**
 *
 * @author 10122_000
 */
public final class loginUI extends javax.swing.JFrame {

    /**
     * Creates new form loginUI
     */
    
    public static LoggerInterface LoggerInf;
    public static String strUsername = "";
    public static String strPassword = "";
    
    public static KeyEventDispatcher keyEventDispatcher;
    public static mysqlConnector mysqlConnector;
    
    public loginUI() throws ClassNotFoundException, SQLException {
        initComponents();
        // create Logger instance
        LoggerInf = new LoggerInterface("loginUI");
        LoggerInf.SetLevel(true);
       
         // set the window in the center of sreen
        centreWindow(); 
       
        //singleton
        singletonUI.getInstance().setLoginInstance(this);
        
        // connect to database
        mysqlConnector = new mysqlConnector();
        
        try{
            boolean bChecker = mysqlConnector.connect();
            if (bChecker == false){
                JOptionPane.showMessageDialog(null, "Connect to mySQL server fail!", 
                    "Application Database", JOptionPane.INFORMATION_MESSAGE);
            }else{
                userManipulation.getUserManipulationInstance().setDatabaseConnection(mysqlConnector.getConnection());
            }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(loginUI.class.getName()).log(Level.SEVERE, null, ex);
 
        }
        
    }
    
    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int height = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(width, height);
        LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "loginUI > centreWindow");
    }
    
    
    public void setCustomeWindow(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    }
    
    
    public int checkLoginInformation() throws SQLException{
        int errorCode = -1;
        strUsername = txtUsername.getText();
        strPassword = txtPassword.getText();
        
        if (strUsername.isEmpty() == true){
            errorCode = loginCode.LOGGIN_ERROR_EMPTY_USERNAME;
        }else if (strPassword.isEmpty() == true){
            errorCode = loginCode.LOGGIN_ERROR_EMPTY_PASSWORD;
        }else{
            accountInterface loginAccount = new accountInterface();
            loginAccount.setStrPassword(strPassword);
            loginAccount.setStrUsername(strUsername);
            
            userManipulation.getUserManipulationInstance().setUserAccount(loginAccount);
            boolean bChecker = userManipulation.getUserManipulationInstance().isUserValid();
            if (bChecker == true){
              errorCode = loginCode.LOGGIN_OK;
            }else{
               errorCode = loginCode.LOGGIN_ERROR_WRONG_PARAMETER;
            }
            
            if (loginAccount!=null){
                loginAccount = null;
            }
            System.gc();
        }
        return errorCode;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnSignIn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnSignUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                onLoginDialogClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("APPLICATION MANAGER LOGIN SCREEN");

        btnSignIn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnSignIn.setText("Sign in");
        btnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignInActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Username");

        txtUsername.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Password");

        txtPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnSignUp.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnSignUp.setText("Sign up");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSignUp)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSignIn)
                                        .addGap(16, 16, 16)))))
                        .addGap(68, 68, 68))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSignIn)
                    .addComponent(btnSignUp))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignInActionPerformed
        
        try {
            int errorCode = checkLoginInformation();
            if (errorCode == loginCode.LOGGIN_OK){
                // hide the loginUI
                this.dispose();
                System.gc();
                
                // Show main dialog
                managerUI mainDialog = new managerUI();
                mainDialog.show();
            }else{
                switch (errorCode){
                    case loginCode.LOGGIN_ERROR_EMPTY_USERNAME:
                        JOptionPane.showMessageDialog(null, loginCode.LOGGIN_STRING_EMPTY_USERNAME,
                                "Application Login", JOptionPane.INFORMATION_MESSAGE);
                        break;
                        
                    case loginCode.LOGGIN_ERROR_EMPTY_PASSWORD:
                        JOptionPane.showMessageDialog(null, loginCode.LOGGIN_STRING_EMPTY_PASSWORD,
                                "Application Login", JOptionPane.INFORMATION_MESSAGE);
                        break;
                        
                    case loginCode.LOGGIN_ERROR_WRONG_PARAMETER:
                        JOptionPane.showMessageDialog(null, loginCode.LOGGIN_STRING_WRONG_PARAMETER,
                                "Application Login", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSignInActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        // Show signup account
        this.dispose();
        System.gc();
        
        signupUI signupDialog = new signupUI();
        signupDialog.show();
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void onLoginDialogClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onLoginDialogClosing
        try {
            // close mysql connector
            mysqlConnector.close();
        } catch (SQLException ex) {
            Logger.getLogger(loginUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_onLoginDialogClosing

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
            java.util.logging.Logger.getLogger(loginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new loginUI().setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(loginUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSignIn;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
