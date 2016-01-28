/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import applicationmanager.loginUI;
import database.userInterface;
import database.userManipulation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import logger.LoggerCode;
import logger.LoggerInterface;

/**
 *
 * @author 10122_000
 */
public final class managerUI extends javax.swing.JFrame {

    /**
     * Creates new form managerUI
     */
    public static LoggerInterface LoggerInf;
    public static JPopupMenu popupMenu;
    public static int currentRowIndex = 0;
    public static DefaultTableModel model;
    public static String strCurrentUserToken;
    public static JButton btnClone;
    
    public managerUI() {
        initComponents();
        
         // create Logger instance
        LoggerInf = new LoggerInterface("managerUI");
        LoggerInf.SetLevel(true);
        
        // set dialog position
        centreWindow();
        
        // config table view
        configTableView();
        
        // auto hide save button
        btnClone = btnSave;
        btnSave.setVisible(false);
        
        insetDataIntoTableView();
    }
    
    public void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int height = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(width, height);
        LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "managerUI > centreWindow");
    }
    
    
    public void configTableView(){
        // config popup menu
        configPopupMenu();
       
        // sets the popup menu for the table
        tableView.setComponentPopupMenu(popupMenu);
    }
    
    public void insetDataIntoTableView(){
        ArrayList<userInterface> arrUser = userManipulation.getUserManipulationInstance().QueryAllUsers();
        model = (DefaultTableModel)tableView.getModel();
        for (int i = 0; i < arrUser.size(); i++){
            userInterface user = arrUser.get(i);
             Object[] row = {i+1, user.getStrName(), 
                 user.getStrDate(), user.getStrAddress(), user.getStrUniveristy(), user.getStrPhone(), user.getStrEmail() };
            model.addRow(row);
        }
        
        arrUser.clear();
        System.gc();
    }
    
    public void ReFreshTableView( int event){
        // First of all, delete all current rows
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        if (event == managerCode.USER_EDIT_UPDATE){
            // Update view
            insetDataIntoTableView();
        }
    }
    
    public void RemoveAllUser(){
        try {
            boolean bChecker = userManipulation.getUserManipulationInstance().DeleteALLUserInformation();
            if (bChecker == true){
                    JOptionPane.showMessageDialog(null, "Delete ALL data successful!", "Notification", 
                       JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Delete ALL data FAIL!", "Notification", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
        } catch (SQLException ex) {
            Logger.getLogger(managerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public userInterface getUserInformationByRowIndex(int currRowIndex){
        userInterface user = new userInterface();
        user.setStrName((String)model.getValueAt(currRowIndex, managerCode.COLUMN_NAME));
        user.setStrDate((String)model.getValueAt(currRowIndex, managerCode.COLUMN_DATE));
        user.setStrAddress((String)model.getValueAt(currRowIndex, managerCode.COLUMN_ADDRESS));
        user.setStrUniveristy((String)model.getValueAt(currRowIndex, managerCode.COLUMN_UNIVERSITY));
        user.setStrPhone((String)model.getValueAt(currRowIndex, managerCode.COLUMN_PHONE));
        user.setStrEmail((String)model.getValueAt(currRowIndex, managerCode.COLUMN_EMAIL));
        return user;
    }
    
    public static void updateUserInformationByRowIndex( int currRowIndex, userInterface user){
         model.setValueAt((String)user.getStrName(), currRowIndex, managerCode.COLUMN_NAME);
         model.setValueAt((String)user.getStrDate(), currRowIndex, managerCode.COLUMN_DATE);
         model.setValueAt((String)user.getStrAddress(), currRowIndex, managerCode.COLUMN_ADDRESS);
         model.setValueAt((String)user.getStrUniveristy(), currRowIndex, managerCode.COLUMN_UNIVERSITY);
         model.setValueAt((String)user.getStrPhone(), currRowIndex, managerCode.COLUMN_PHONE);
         model.setValueAt((String)user.getStrEmail(), currRowIndex, managerCode.COLUMN_EMAIL);
    }
    
    public static void InsertNewUserInformation(userInterface user){
        try {
                //set user token
                //user.setStrName("Spellbinder Account " + userManipulation.getUserManipulationInstance().getCurrentUserInformationIndex());
                user.setStrToken(managerCode.getToken(6));
                user.setId( userManipulation.getUserManipulationInstance().getCurrentUserInformationIndex()+1);
                boolean bChecker = false;
                bChecker = userManipulation.getUserManipulationInstance().InsertNewUserInformationRow(user);
                if (bChecker == true){
                    //JOptionPane.showMessageDialog(null, "Insert data successful!", "Notification", 
                    //   JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Insert data FAIL!", "Notification", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(managerUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void notifyEvent(int event, userInterface user) throws InterruptedException{
        System.err.println("eventCode = " + event);
        switch(event){
            case managerCode.USER_EDIT_UPDATE:
                updateUserInformationByRowIndex(currentRowIndex, user);
                break;
                
            case managerCode.USER_EDIT_UNCHANGE:
                // auto hide save button
                btnClone.setVisible(false);
                break;
                
            case managerCode.USER_ADD_RECORD:
                InsertNewUserInformation(user);
                addNewRowByUserInformation(user);
                break;
        }
    }
    
    public static void addNewRowByUserInformation(userInterface user){
        Object[] row = { user.getId(), user.getStrName(), 
                 user.getStrDate(), user.getStrAddress(), user.getStrUniveristy(), user.getStrPhone(), user.getStrEmail() };
        model.addRow(row);
    }
    
    public void configPopupMenu(){
        popupMenu = new JPopupMenu();
        
        // edit function
        JMenuItem menuItemEdit = new JMenuItem("Edit");
        menuItemEdit.addActionListener((ActionEvent e) -> {
            currentRowIndex = tableView.getSelectedRow();
                    
            userInterface userInfo = new userInterface();
            
            String strCurrentName = (String)model.getValueAt(currentRowIndex, managerCode.COLUMN_NAME);
            userInfo.setStrName((String)strCurrentName);
            
            strCurrentUserToken = userManipulation.getUserManipulationInstance().QueryTokenByName((String)strCurrentName);
            
            userInfo.setStrDate((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_DATE));
            userInfo.setStrAddress((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_ADDRESS));
            userInfo.setStrUniveristy((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_UNIVERSITY));
            userInfo.setStrPhone((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_PHONE));
            userInfo.setStrEmail((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_EMAIL));
            
            userInformationUI userInfoUI = new userInformationUI(userInfo);
            userInfoUI.show();
            LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "managerUI > add item " + currentRowIndex);
            btnSave.setVisible(true);
        });
        
        // remove function
        JMenuItem menuItemView = new JMenuItem("View");
        menuItemView.addActionListener((ActionEvent e) -> {
            currentRowIndex = tableView.getSelectedRow();
 
            userInterface userInfo = new userInterface();
            userInfo.setStrName((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_NAME));
            userInfo.setStrDate((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_DATE));
            userInfo.setStrAddress((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_ADDRESS));
            userInfo.setStrUniveristy((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_UNIVERSITY));
            userInfo.setStrPhone((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_PHONE));
            userInfo.setStrEmail((String)model.getValueAt(currentRowIndex, managerCode.COLUMN_EMAIL));

            userInformationUI userInfoUI = new userInformationUI(userInfo, managerCode.USER_UNSED_VALUE);
            userInfoUI.show();
            LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "managerUI > view item " + currentRowIndex);
            btnSave.setVisible(false);
        });
        
        // remove function
        JMenuItem menuItemRemove = new JMenuItem("Remove");
        menuItemRemove.addActionListener((ActionEvent e) -> {
            if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Dialog",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                currentRowIndex = tableView.getSelectedRow();
                deleteUserByRowIndex(currentRowIndex);
                ReFreshTableView(managerCode.USER_EDIT_UPDATE);
                LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "managerUI > remove item " + currentRowIndex);
            } else {
                // no option
            }
        });
        
        // remove-all function
        JMenuItem menuItemRemoveAll = new JMenuItem("Remove all");
        menuItemRemoveAll.addActionListener((ActionEvent e) -> {
            if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Dialog",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                RemoveAllUser();
                ReFreshTableView(managerCode.USER_REMOVE_ALL);
                LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "managerUI > remove all item " + currentRowIndex);

            } else {
                // no option
            }
        });
        
        // add item refresh data
         JMenuItem menuItemRefresh = new JMenuItem("Refresh");
         menuItemRefresh.addActionListener((ActionEvent e) -> {
             ReFreshTableView(managerCode.USER_EDIT_UPDATE);
            LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "managerUI > refresh data " + currentRowIndex);
        });
        
        
        // add item into menu
        popupMenu.add(menuItemView);
        popupMenu.add(menuItemEdit);
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemRemoveAll);
        popupMenu.add(menuItemRefresh);
    }
    
    public void deleteUserByRowIndex(int currIndex){
        String strName = (String)model.getValueAt(currentRowIndex, managerCode.COLUMN_NAME);
        String token = userManipulation.getUserManipulationInstance().QueryTokenByName(strName);
        if (token.equals("")==false){
            try {
                boolean bChecker = userManipulation.getUserManipulationInstance().DeleteUserInformationByToken(token);
                if (bChecker == true){
                    JOptionPane.showMessageDialog(null, "Delete data successful!", "Notification", 
                       JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Delete data FAIL!", "Notification", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(managerUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.err.println("TOKEN is invalid");
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("APPLICATION MANAGER MAIN SCREEN");

        tableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "date of birth", "address", "university", "phone", "email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableView);
        if (tableView.getColumnModel().getColumnCount() > 0) {
            tableView.getColumnModel().getColumn(0).setMinWidth(25);
            tableView.getColumnModel().getColumn(0).setMaxWidth(50);
            tableView.getColumnModel().getColumn(1).setMinWidth(100);
            tableView.getColumnModel().getColumn(1).setMaxWidth(125);
            tableView.getColumnModel().getColumn(3).setMinWidth(125);
            tableView.getColumnModel().getColumn(3).setMaxWidth(130);
            tableView.getColumnModel().getColumn(4).setMinWidth(150);
            tableView.getColumnModel().getColumn(4).setMaxWidth(200);
            tableView.getColumnModel().getColumn(6).setMinWidth(150);
            tableView.getColumnModel().getColumn(6).setMaxWidth(180);
        }

        btnBack.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(248, 248, 248))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnSave)
                    .addComponent(btnAdd))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
        loginUI loginDialog;
        try {
            loginDialog = new loginUI();
            loginDialog.show();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(managerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        userInterface user = getUserInformationByRowIndex(currentRowIndex);
        boolean bChecker = false;
        bChecker = userManipulation.getUserManipulationInstance().updateUserInformationByName(strCurrentUserToken, user);
        if (bChecker == true){
            JOptionPane.showMessageDialog(null, "Your selected data is edited successful!", "Notification", 
                JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Your selected data is edited FAIL!", "Notification", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        if (user!=null){
            user = null;
            System.gc();
        }
        btnSave.setVisible(false);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        userInformationUI userInfoUI = new userInformationUI();
        userInfoUI.show();
        LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "managerUI > add new item " + currentRowIndex);
        btnSave.setVisible(false);
    }//GEN-LAST:event_btnAddActionPerformed

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
            java.util.logging.Logger.getLogger(managerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(managerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(managerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(managerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new managerUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableView;
    // End of variables declaration//GEN-END:variables
}
