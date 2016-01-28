/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import applicationmanager.loginUI;
import signup.signupUI;

/**
 *
 * @author 10122_000
 */
public class singletonUI {
    
    public static loginUI loginDialog;
    public static signupUI signupDialog;
    
    private static singletonUI instance = null;
    
    public singletonUI(){
        // Exists only to defeat instantiation.
    }
    
    public void setLoginInstance( loginUI login ){
        loginDialog = login;
    }
    public loginUI getLoginInstance(){
        return loginDialog;
    }
    
    public void setSignupInstance( signupUI signup ){
        signupDialog = signup;
    }
    public signupUI getSignupInstance(){
        return signupDialog;
    }
    
    public static singletonUI getInstance() {
        if(instance == null) {
            instance = new singletonUI();
      }
      return instance;
   }
    
}
