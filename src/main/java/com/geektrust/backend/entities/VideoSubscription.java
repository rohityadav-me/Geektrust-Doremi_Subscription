package com.geektrust.backend.entities;

import java.util.Objects;
import com.geektrust.backend.enums.SubscriptionPlan;
import com.geektrust.backend.enums.SubscriptionType;

public class VideoSubscription implements Subscription {
    private SubscriptionPlan userSubscriptionPlan;
    private int validityInMonths;
    private int priceOfThePlan;
    private final int SUBSCRIPTION_PLAN_VALIDITY_FREE_AND_PERSONAL = 1;
    private final int SUBSCRIPTION_PLAN_VALIDITY_PREMIUM = 3;
    private final int SUBSCRIPTION_PLAN_FREE_PRICE = 0;
    private final int SUBSCRIPTION_PLAN_PERSONAL_PRICE = 200;
    private final int SUBSCRIPTION_PLAN_PREMIUM_PRICE = 500;

    public VideoSubscription(SubscriptionPlan userSubscriptionPlan){
        this.userSubscriptionPlan = userSubscriptionPlan;
        if(userSubscriptionPlan.equals(SubscriptionPlan.FREE)){
            validityInMonths = SUBSCRIPTION_PLAN_VALIDITY_FREE_AND_PERSONAL;
            priceOfThePlan = SUBSCRIPTION_PLAN_FREE_PRICE;
        }else if(userSubscriptionPlan.equals(SubscriptionPlan.PERSONAL)){
            validityInMonths = SUBSCRIPTION_PLAN_VALIDITY_FREE_AND_PERSONAL;
            priceOfThePlan = SUBSCRIPTION_PLAN_PERSONAL_PRICE;
        }else if(userSubscriptionPlan.equals(SubscriptionPlan.PREMIUM)){
            validityInMonths = SUBSCRIPTION_PLAN_VALIDITY_PREMIUM;
            priceOfThePlan = SUBSCRIPTION_PLAN_PREMIUM_PRICE;
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
        return Objects.hash(SubscriptionType.VIDEO.toString());
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null) return true;
        return getClass() == o.getClass();
    }

    @Override
    public String toString(){
        return SubscriptionType.VIDEO.toString();
    }
    
}
