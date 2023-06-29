package com.geektrust.backend.entities;

import java.util.Objects;
import com.geektrust.backend.enums.SubscriptionPlan;

public class MusicSubscription implements Subscription {
    private SubscriptionPlan userSubscriptionPlan;
    private int validityInMonths;
    private int priceOfThePlan;

    public MusicSubscription(SubscriptionPlan userSubscriptionPlan){
        this.userSubscriptionPlan = userSubscriptionPlan;
        if(userSubscriptionPlan.equals(SubscriptionPlan.FREE)){
            validityInMonths = 1;
            priceOfThePlan = 0;
        }else if(userSubscriptionPlan.equals(SubscriptionPlan.PERSONAL)){
            validityInMonths = 1;
            priceOfThePlan = 100;
        }else if(userSubscriptionPlan.equals(SubscriptionPlan.PREMIUM)){
            validityInMonths = 3;
            priceOfThePlan = 250;
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
        return Objects.hash("Music");
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null) return true;
        return getClass() == o.getClass();
    }

    @Override
    public String toString(){
        return "MUSIC";
    }
}
