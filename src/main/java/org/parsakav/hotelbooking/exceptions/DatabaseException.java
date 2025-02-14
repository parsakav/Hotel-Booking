package org.parsakav.hotelbooking.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String msg,Throwable th){
       super(msg,th);
    }
}
