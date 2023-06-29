package com.geektrust.backend.entities;

import com.geektrust.backend.enums.SubscriptionPlan;

public interface Subscription {
    
    public SubscriptionPlan getSubscriptionPlan();
    public int getValidity();
    public int getPriceOfThePlan();
}
