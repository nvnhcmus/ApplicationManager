/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logger.LoggerCode;
import logger.LoggerInterface;
import signup.signupUI;

/**
 *
 * @author 10122_000
 */
public class mysqlConnector {
    public String strUsername;
    public String strPassword;
    
    private Connection mysqlConnection = null;
    public static LoggerInterface LoggerInf;
    
    private boolean isConnected = false;
    
    public mysqlConnector(){
        //self-contructor
        strUsername = mysqlCode.DEFAULT_USER;
        strPassword = mysqlCode.DEFAUT_PASS;
        
         // create Logger instance
        LoggerInf = new LoggerInterface("mysqlConector");
        LoggerInf.SetLevel(true);
    }
    public mysqlConnector(String username, String password){
        strUsername = username;
        strPassword = password;
    }
    
    public Connection getConnection(){
        return mysqlConnection;
    }
    
    public void setDatabaseInformation (String username, String password){
        strUsername = username;
        strPassword = password;
    }
    
    public boolean connect() throws ClassNotFoundException, SQLException{
        if (isConnected == false){
            if (mysqlConnection == null){
                // Register JDBC driver
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                }catch (Exception ex){
                    Logger.getLogger(mysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
        
                try{
                // Open a connection
                mysqlConnection = DriverManager.getConnection(mysqlCode.DB_URL,
                        mysqlCode.DEFAULT_USER, mysqlCode.DEFAUT_PASS);
                }
                catch (Exception ex){
                    Logger.getLogger(mysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                if (mysqlConnection == null){
                    LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "mysql > connect: FAIL");
                    isConnected = false;
                    return false;
                }
                
                LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "mysql > connect: OK");
                isConnected = true;
            }
        }
        return true;
    }
    
    public void close() throws SQLException{
        LoggerInf.Log(LoggerCode.LOGGER_LEVEL_INFO, "mysql > close");
        if (mysqlConnection != null){
            isConnected = false;
            mysqlConnection.close();
        }
    }
}
