package com.geektrust.backend.services;

import com.geektrust.backend.enums.SubscriptionPlan;

public class SubscriptionService{
    private SubscriptionPlan userSubscriptionPlan;
    private int validityInMonths;
    private int priceOfThePlan;
    private final int SUBSCRIPTION_PLAN_VALIDITY_FREE_AND_PERSONAL;
    private final int SUBSCRIPTION_PLAN_VALIDITY_PREMIUM;
    private final int SUBSCRIPTION_PLAN_FREE_PRICE;
    private final int SUBSCRIPTION_PLAN_PERSONAL_PRICE;
    private final int SUBSCRIPTION_PLAN_PREMIUM_PRICE;

    public SubscriptionService(SubscriptionPlan userSubscriptionPlan,int subscriptionPlanValidityFreeAndPersonal, int subscriptionPlanValidityPremium, int subscriptionPlanFreePrice, int subscriptionPlanPersonalPrice, int subscriptionPlanPremiumPrice){
        this.userSubscriptionPlan = userSubscriptionPlan;
        SUBSCRIPTION_PLAN_VALIDITY_FREE_AND_PERSONAL = subscriptionPlanValidityFreeAndPersonal;
        SUBSCRIPTION_PLAN_VALIDITY_PREMIUM = subscriptionPlanValidityPremium;
        SUBSCRIPTION_PLAN_FREE_PRICE = subscriptionPlanFreePrice;
        SUBSCRIPTION_PLAN_PERSONAL_PRICE = subscriptionPlanPersonalPrice;
        SUBSCRIPTION_PLAN_PREMIUM_PRICE = subscriptionPlanPremiumPrice;

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

    public SubscriptionPlan getSubscriptionPlan() {
        return userSubscriptionPlan;
    }

    public int getValidity() {
        return  validityInMonths;
    }

    public int getPriceOfThePlan() {
        return priceOfThePlan;
    }

}
