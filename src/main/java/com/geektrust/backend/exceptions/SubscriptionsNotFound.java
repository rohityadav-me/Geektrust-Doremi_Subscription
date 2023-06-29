package com.geektrust.backend.exceptions;

public class SubscriptionsNotFound extends RuntimeException{
    public SubscriptionsNotFound(){
        super();
    }
    public SubscriptionsNotFound(String message){
        super(message);
    }
}
