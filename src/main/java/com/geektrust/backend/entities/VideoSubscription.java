package com.geektrust.backend.entities;

import java.util.Objects;
import com.geektrust.backend.enums.SubscriptionPlan;

public class VideoSubscription implements Subscription {
    private SubscriptionPlan userSubscriptionPlan;
    private int validityInMonths;
    private int priceOfThePlan;

    public VideoSubscription(SubscriptionPlan userSubscriptionPlan){
        this.userSubscriptionPlan = userSubscriptionPlan;
        if(userSubscriptionPlan.equals(SubscriptionPlan.FREE)){
            validityInMonths = 1;
            priceOfThePlan = 0;
        }else if(userSubscriptionPlan.equals(SubscriptionPlan.PERSONAL)){
            validityInMonths = 1;
            priceOfThePlan = 200;
        }else if(userSubscriptionPlan.equals(SubscriptionPlan.PREMIUM)){
            validityInMonths = 3;
            priceOfThePlan = 500;
        }
    }

    @Override
    public SubscriptionPlan getSubscriptionPlan() {
        return userSubscriptionPlan;
    }

    @Override
    public int getValidity() {
        return  validityInMonths;
    }

    @Override
    public int getPriceOfThePlan() {
        return priceOfThePlan;
    }

    @Override
    public int hashCode(){
        return Objects.hash("Video");
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null) return true;
        return getClass() == o.getClass();
    }

    @Override
    public String toString(){
        return "VIDEO";
    }
    
}
