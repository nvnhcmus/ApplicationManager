/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author 10122_000
 */
public class accountInterface {
    private int userID;
    private String strUsername;
    private String strPassword;
    
    public accountInterface(){
        userID = 0;
        strUsername = "";
        strPassword = "";
    }
    
    public accountInterface(int id, String username, String password){
        userID = id;
        strUsername = username;
        strPassword = password;           
    }

    public int getUserID() {
        return userID;
    }

    public String getStrUsername() {
        return strUsername;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    } 
}
