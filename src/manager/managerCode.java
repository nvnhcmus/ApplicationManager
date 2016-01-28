/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.Random;

/**
 *
 * @author 10122_000
 */
public class managerCode {
    public static final int COLUMN_NAME         = 1;
    public static final int COLUMN_DATE         = 2;
    public static final int COLUMN_ADDRESS      = 3;
    public static final int COLUMN_UNIVERSITY   = 4;
    public static final int COLUMN_PHONE        = 5;
    public static final int COLUMN_EMAIL        = 6;
    
    
    public static final int USER_EDIT_UPDATE         = 0;
    public static final int USER_EDIT_UNCHANGE       = 1;
    public static final int USER_ADD_RECORD          = 2;
    public static final int USER_REMOVE_ALL          = 3;
    
    public static final int USER_UNSED_VALUE        = 9999;
    
    
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";

    public static String getToken(int length) {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return token.toString();
    }
}
