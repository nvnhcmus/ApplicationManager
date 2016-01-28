/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10122_000
 */
public class userManipulation {
    private static accountInterface loginAccount;
    private static Connection mysqlConnector = null;
    
    private static userManipulation instance = null;
    public userManipulation(){
        // Exists only to defeat instantiation.
    }
    
    public static userManipulation getUserManipulationInstance(){
        if(instance == null) {
            instance = new userManipulation();
        }
        return instance;
    }
    
    public void setUserAccount ( accountInterface login ){
        loginAccount = login;
    }
    
    public void setDatabaseConnection (Connection mysql){
        mysqlConnector = mysql;
    }
    
    public int getCurrentIndex() throws SQLException{
        int currentIndex = 0;
        if (mysqlConnector!= null){
            String queryInfo = "SELECT * FROM login";
        
            Statement statement = mysqlConnector.createStatement();
            // check the account
            ResultSet resultSet = statement.executeQuery(queryInfo);
            if (resultSet != null)   
            {  
                resultSet.beforeFirst();  
                resultSet.last();  
                currentIndex = resultSet.getRow();  
            }  
        }
        return currentIndex;
    }
    
    
    public boolean isUserValid () throws SQLException{
        boolean result = false;
        if (mysqlConnector!= null){
            String queryInfo = "SELECT * FROM login";
        
            Statement statement = mysqlConnector.createStatement();
            // check the account
            try (ResultSet resultSet = statement.executeQuery(queryInfo)) {
                // check the account
                while (resultSet.next()){
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                
                    if (username.equals(loginAccount.getStrUsername())
                        && password.equals(loginAccount.getStrPassword())){
                        result = true;
                        System.out.println("database.userManipulation.isUserValid(): OK");
                        break;
                    }
                }
            }
        }else{
            System.err.println("null connection");
        }
        return result;
    }
    
    public boolean InsertNewUserInformation( accountInterface userAccount) throws SQLException{
        boolean result = false;
        if (mysqlConnector!=null){
            // the mysql insert statement
            String query = " INSERT INTO login (id, username, password)" + " values (?, ?, ?)";
            
            try{
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = mysqlConnector.prepareStatement(query);
                int index = getCurrentIndex();
                preparedStmt.setInt(1,index+1);
                preparedStmt.setString(2, userAccount.getStrUsername());
                preparedStmt.setString(3, userAccount.getStrPassword());
         
                // execute the preparedstatement
                preparedStmt.execute();
                result = true;
            }catch (Exception ex){
                Logger.getLogger(userManipulation.class.getName()).log(Level.SEVERE, null, ex);
                result = false;
            }
        }else{
            System.out.println("null connection");
        }
        return result;
    }
    
    
    public ArrayList<userInterface> QueryAllUsers(){
        ArrayList<userInterface> arrayUser = new ArrayList<>();
        if (mysqlConnector!= null){
            try {
                String queryInfo = "SELECT * FROM users";
                
                Statement statement = mysqlConnector.createStatement();
                // check the account
                try (ResultSet resultSet = statement.executeQuery(queryInfo)) {
                    // check the account
                    while (resultSet.next()){
                        int id = resultSet.getInt("id");
                        String username = resultSet.getString("name");
                        String date = resultSet.getString("date");
                        String address = resultSet.getString("address");
                        String university = resultSet.getString("university");
                        String phone = resultSet.getString("phone");
                        String email = resultSet.getString("email");
                        
                        userInterface user = new userInterface(id, phone, 
                                            username, date, address, university, phone, email);
                        arrayUser.add(user);
                    }
                }
            }   catch (SQLException ex) {
                Logger.getLogger(userManipulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.err.println("null connection");
        }
        return arrayUser;
    }
    
    public String QueryTokenByName(String name ){
        String strToken = "";
        if (mysqlConnector!= null){
            try {
                String queryInfo = "SELECT * FROM users";
                
                Statement statement = mysqlConnector.createStatement();
                // check the account
                try (ResultSet resultSet = statement.executeQuery(queryInfo)) {
                    // check the account
                    while (resultSet.next()){
                        String strUserName = resultSet.getString("name");
                        String strUserToken = resultSet.getString("token");
                        if (strUserName.equals(name)){
                            strToken = strUserToken;
                            break;
                        }
                    }
                }
            }   catch (SQLException ex) {
                Logger.getLogger(userManipulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.err.println("null connection");
        }
        return strToken;
    }
    
    public boolean updateUserInformationByName(String token, userInterface user){
        boolean result = false;
        if (mysqlConnector!=null){
            try {
                // create the java mysql update preparedstatement
                String query = "update users set name=?, date=?, address=?, university=?, phone=?, email=? where token=?";
                PreparedStatement preparedStmt = mysqlConnector.prepareStatement(query);
                preparedStmt.setString(1, user.getStrName());
                preparedStmt.setString(2, user.getStrDate());
                preparedStmt.setString(3, user.getStrAddress());
                preparedStmt.setString(4, user.getStrUniveristy());
                preparedStmt.setString(5, user.getStrPhone());
                preparedStmt.setString(6, user.getStrEmail());
                preparedStmt.setString(7, token);
                
                // execute the java preparedstatement
                preparedStmt.executeUpdate();
                result = true;
                if (token!=null){
                    token = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(userManipulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.err.println("null connection");
        }
        return result;
    }
    
    
    public int getCurrentUserInformationIndex() throws SQLException{
        int currentIndex = 0;
        if (mysqlConnector!= null){
            String queryInfo = "SELECT * FROM users";
        
            Statement statement = mysqlConnector.createStatement();
            // check the account
            ResultSet resultSet = statement.executeQuery(queryInfo);
            if (resultSet != null)   
            {  
                resultSet.beforeFirst();  
                resultSet.last();  
                currentIndex = resultSet.getRow();  
            } 
        }
        return currentIndex;
    }
    
    
    public boolean isUniqueEmail( String email) throws SQLException{
        boolean result = true;
        if (mysqlConnector!= null){
            String queryInfo = "SELECT email FROM users";
        
            Statement statement = mysqlConnector.createStatement();
            // check the email
            ResultSet resultSet = statement.executeQuery(queryInfo);
            while (resultSet.next())
            {
                String strEmail = resultSet.getString("email");
                if (email.equals(strEmail)==true){
                    result = false;
                    break;
                }
            }
        }else{
            System.err.println("null connection");
        }
        return result;
    }
    
    public boolean isUniqueUsername( String userName) throws SQLException{
        boolean result = true;
        if (mysqlConnector!= null){
            String queryInfo = "SELECT username FROM login";
        
            Statement statement = mysqlConnector.createStatement();
            // check the username
            ResultSet resultSet = statement.executeQuery(queryInfo);
            while (resultSet.next())
            {
                String strUsername = resultSet.getString("username");
                if (userName.equals(strUsername)==true){
                    result = false;
                    break;
                }
            }
        }else{
            System.err.println("null connection");
        }
        return result;
    }
    
    public boolean InsertNewUserInformationRow( userInterface user) throws SQLException{
        boolean result = false;
        if (mysqlConnector!=null){
            // the mysql insert statement
            String query = " INSERT INTO users (id, token, name, date, address, university, phone, email)" + 
                    " values (?, ?, ?, ?, ?, ?, ?, ?)";
            try{
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = mysqlConnector.prepareStatement(query);
                preparedStmt.setInt(1,user.getId());
                preparedStmt.setString(2, user.getStrToken());
                preparedStmt.setString(3, user.getStrName());
                preparedStmt.setString(4, user.getStrDate());
                preparedStmt.setString(5, user.getStrAddress());
                preparedStmt.setString(6, user.getStrUniveristy());
                preparedStmt.setString(7, user.getStrPhone());
                preparedStmt.setString(8, user.getStrEmail());
         
                // execute the preparedstatement
                preparedStmt.execute();
                result = true;
            }catch (Exception ex){
                Logger.getLogger(userManipulation.class.getName()).log(Level.SEVERE, null, ex);
                result = false;
            }
        }else{
            System.out.println("null connection");
        }
        return result;
    }
    
    
    public boolean DeleteUserInformationByToken(String token) throws SQLException{
        boolean result = false;
        if (mysqlConnector!=null){
            // the mysql insert statement
            String query = " DELETE FROM users WHERE token = ?";
            try{
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = mysqlConnector.prepareStatement(query);
                preparedStmt.setString(1, token);
                // execute the preparedstatement
                preparedStmt.execute();
                if (token!=null){
                    token = null;
                }
                result = true;
            }catch (Exception ex){
                Logger.getLogger(userManipulation.class.getName()).log(Level.SEVERE, null, ex);
                result = false;
            }
        }else{
            System.out.println("null connection");
        }
        return result;
    }
    
    
    public boolean DeleteALLUserInformation() throws SQLException{
        boolean result = false;
        if (mysqlConnector!=null){
            // the mysql insert statement
            String query = " DELETE FROM users";
            try{
                // create the mysql insert preparedstatement
                Statement Statement = mysqlConnector.createStatement();
                
                // execute the statement!
                Statement.execute(query);
                result = true;
            }catch (Exception ex){
                Logger.getLogger(userManipulation.class.getName()).log(Level.SEVERE, null, ex);
                result = false;
            }
        }else{
            System.out.println("null connection");
        }
        return result;
    }
}
