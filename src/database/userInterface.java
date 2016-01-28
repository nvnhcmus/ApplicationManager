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
public class userInterface {
    private int id;
    private String strToken;
    private String strName;
    private String strDate;
    private String strAddress;
    private String strUniveristy;
    private String strPhone;
    private String strEmail;
    
    public userInterface(){
        // Exists only to defeat instantiation.
    }

    public userInterface(int id, String strToken, String strName, String strDate, String strAddress, String strUniveristy, String strPhone, String strEmail) {
        this.id = id;
        this.strToken = strToken;
        this.strName = strName;
        this.strDate = strDate;
        this.strAddress = strAddress;
        this.strUniveristy = strUniveristy;
        this.strPhone = strPhone;
        this.strEmail = strEmail;
    }

    public int getId() {
        return id;
    }

    public String getStrToken() {
        return strToken;
    }

    public String getStrName() {
        return strName;
    }

    public String getStrDate() {
        return strDate;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public String getStrUniveristy() {
        return strUniveristy;
    }

    public String getStrPhone() {
        return strPhone;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStrToken(String strToken) {
        this.strToken = strToken;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public void setStrUniveristy(String strUniveristy) {
        this.strUniveristy = strUniveristy;
    }

    public void setStrPhone(String strPhone) {
        this.strPhone = strPhone;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }
    
    public boolean isEqual(userInterface newUser){
        boolean result = false;
        if (        this.strName.equals(newUser.getStrName()) &&
                    this.strDate.equals(newUser.getStrDate()) &&
                    this.strAddress.equals(newUser.getStrAddress()) &&
                    this.strUniveristy.equals(newUser.getStrUniveristy()) &&
                    this.strPhone.equals(newUser.getStrPhone()) &&
                    this.strEmail.equals(newUser.getStrEmail())){
            result = true;
        }
        return result;
    }
}
