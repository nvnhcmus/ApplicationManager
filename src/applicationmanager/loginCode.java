/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmanager;

/**
 *
 * @author 10122_000
 */
public class loginCode {
    public static int APPLICATION_EXIT_CODE = 0;
    
    // Loggin error code
    public static final int LOGGIN_ERROR_EMPTY_USERNAME     = 1;
    public static final int LOGGIN_ERROR_EMPTY_PASSWORD     = 2;
    public static final int LOGGIN_ERROR_WRONG_PARAMETER    = 3;
    public static final int LOGGIN_OK                       = 4;
    
    // login error string
    public static final String LOGGIN_STRING_EMPTY_USERNAME = "Username is empty!";
    public static final String LOGGIN_STRING_EMPTY_PASSWORD = "Password is empty!";
    public static final String LOGGIN_STRING_WRONG_PARAMETER = "Wrong parameter!";
    
    // test account information
    public static final String LOGGIN_TEST_USERNAME = "nvnhcmus";
    public static final String LOGGIN_TEST_PASSWORD = "123456";
}
