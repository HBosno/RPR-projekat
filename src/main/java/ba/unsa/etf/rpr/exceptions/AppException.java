package ba.unsa.etf.rpr.exceptions;

/**
 * User defined exception. Used to catch errors for different SQL queries.
 * @author Hamza Bosno
 */
public class AppException extends Exception{

    public AppException(String msg, Exception reason){
        super(msg, reason);
    }

    public AppException(String msg){
        super(msg);
    }
}