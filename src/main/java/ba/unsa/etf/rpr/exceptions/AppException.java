package ba.unsa.etf.rpr.exceptions;

public class AppException extends Exception{

    public AppException(String msg, Exception reason){
        super(msg, reason);
    }

    public AppException(String msg){
        super(msg);
    }
}