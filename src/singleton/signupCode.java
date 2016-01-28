/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

/**
 *
 * @author 10122_000
 */
public class signupCode {
    
    // error code
    public static final int SIGNUP_ERROR_EMPTY_USERNAME         = 0;
    public static final int SIGNUP_ERROR_EMPTY_PASSWORD         = 1;
    public static final int SIGNUP_ERROR_EMPTY_REPASSWORD       = 2;
    public static final int SIGNUP_ERROR_MISMATCH_PASSWORD      = 3;
    public static final int SIGNUP_ERROR_TOO_SHORT_USERNAME     = 4;
    public static final int SIGNUP_ERROR_TOO_SHORT_PASSWORD     = 5;
    public static final int SIGNUP_OK                           = 6;
    
    public static final int MINIMUM_LENGTH  = 6;  
    
    // error string
    public static final String SIGNUP_STRING_EMPTY_USERNAME     = "Username is empty!";
    public static final String SIGNUP_STRING_EMPTY_PASSWORD     = "Password is empty!";
    public static final String SIGNUP_STRING_EMPTY_REPASSWORD   = "Repassword is empty!";
    public static final String SIGNUP_STRING_MISMATCH_PASSWORD  = "Mismatching between password and repassword!";
    public static final String SIGNUP_STRING_TOO_SHORT_USERNAME = "User name must be at least 6 characters!";
    public static final String SIGNUP_STRING_TOO_SHORT_PASSWORD = "Password must be at least 6 characters!";
    public static final String SIGNUP_STRING_UNKNOWN_CODE       = "Unknown code!";
    
    // avoid 25 well-known passwords (Ref: http://www.slate.com/blogs/future_tense/2014/01/22/_25_worst_passwords_what_your_terrible_common_password_says_about_you.html)
}
