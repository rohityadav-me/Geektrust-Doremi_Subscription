package com.geektrust.backend.exceptions;

public class NoSuchCommand extends RuntimeException{
    public NoSuchCommand(){
        super();
    }
    public NoSuchCommand(String message){
        super(message);
    }
}
