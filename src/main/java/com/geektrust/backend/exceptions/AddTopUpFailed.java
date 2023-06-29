package com.geektrust.backend.exceptions;

public class AddTopUpFailed extends RuntimeException{
    public AddTopUpFailed(){
        super();
    }

    public AddTopUpFailed(String message){
        super(message);
    }
}
