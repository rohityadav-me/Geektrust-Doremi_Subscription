package com.geektrust.backend.entities;

import java.util.Objects;
import com.geektrust.backend.enums.SubscriptionPlan;
import com.geektrust.backend.enums.SubscriptionType;
import com.geektrust.backend.services.SubscriptionService;

public class PodcastSubscription implements Subscription {

    private final SubscriptionService SUBSCRIPTION_SERVICE;

    public PodcastSubscription(SubscriptionPlan userSubscriptionPlan){
        int subscriptionPlanValidityFreeAndPersonal = 1;
        int subscriptionPlanValidityPremium = 3;
        int subscriptionPlanFreePrice = 0;
        int subscriptionPlanPersonalPrice = 100;
        int subscriptionPlanPremiumPrice = 300;
        SUBSCRIPTION_SERVICE = new SubscriptionService(userSubscriptionPlan, subscriptionPlanValidityFreeAndPersonal, subscriptionPlanValidityPremium, subscriptionPlanFreePrice, subscriptionPlanPersonalPrice, subscriptionPlanPremiumPrice);
    }

    @Override
    public SubscriptionPlan getSubscriptionPlan() {
        return SUBSCRIPTION_SERVICE.getSubscriptionPlan();
    }

    @Override
    public int getValidity() {
        return SUBSCRIPTION_SERVICE.getValidity();
    }

    @Override
    public int getPriceOfThePlan() {
        return SUBSCRIPTION_SERVICE.getPriceOfThePlan();
    }

    @Override
    public int hashCode(){
        return Objects.hash(SubscriptionType.PODCAST.toString());
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null) return true;
        return getClass() == o.getClass();
    }

    @Override
    public String toString(){
        return SubscriptionType.PODCAST.toString();
    }
}
