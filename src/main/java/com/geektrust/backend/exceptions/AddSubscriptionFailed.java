package com.geektrust.backend.exceptions;

public class AddSubscriptionFailed extends RuntimeException{
    public AddSubscriptionFailed(){
        super();
    }

    public AddSubscriptionFailed(String message){
        super(message);
    }
}
