/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10122_000
 */
public class LoggerInterface {
    public static Logger LOGGER; 
    public LoggerInterface( String className )
    {
        if (className.isEmpty() == false){
            //set the current class of logger
            LOGGER = Logger.getLogger(className);
        }else{
            LOGGER = Logger.getLogger(LoggerCode.DEFAULT_STRING);
        }
    }
    
    
    public void SetLevel (boolean enable)
    {
        if (enable == true){
            // enable all type of log message
            LOGGER.setLevel(Level.ALL);
        }else{
            LOGGER.setLevel(Level.OFF);
        }
    }
    
    public void Log (int levelCode, String logMessage)
    {
        switch (levelCode)
        {
            case LoggerCode.LOGGER_LEVEL_DEFAULT:
                LOGGER.log(Level.FINE, logMessage);
                break;
                
            case LoggerCode.LOGGER_LEVEL_SERVER:
                LOGGER.log(Level.SEVERE, logMessage);
                break;
                
            case LoggerCode.LOGGER_LEVEL_INFO:
                LOGGER.log(Level.INFO, logMessage);
                break;
                
            case LoggerCode.LOGGER_LEVEL_WARNING:
                LOGGER.log(Level.WARNING, logMessage);
                break;
                
            case LoggerCode.LOGGER_LEVEL_CONFIG:
                LOGGER.log(Level.CONFIG, logMessage);
                break;
        }
    }
}
