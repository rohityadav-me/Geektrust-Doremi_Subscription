package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.geektrust.backend.enums.SubscriptionPlan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Video Subscription Test")
public class VideoSubscriptionTest {
    private SubscriptionPlan userSubscriptionPlan;
    private int validityInMonthsActual;
    private int validityInMonthsExpected;
    private int priceOfThePlanActual;
    private int priceOfThePlanExpected;

    @Test
    @DisplayName("Test to check for FREE Video Subscription")
    public void freeVideoSubscriptionTest(){
        userSubscriptionPlan = SubscriptionPlan.FREE;
        Subscription userSubscription = new VideoSubscription(userSubscriptionPlan);
        validityInMonthsExpected = 1;
        priceOfThePlanExpected = 0;
        validityInMonthsActual = userSubscription.getValidity();
        priceOfThePlanActual = userSubscription.getPriceOfThePlan();
        assertEquals(validityInMonthsExpected, validityInMonthsActual);
        assertEquals(priceOfThePlanExpected, priceOfThePlanActual);
    }

    @Test
    @DisplayName("Test to check for PERSONAL Video Subscription")
    public void personalVideoSubscriptionTest(){
        userSubscriptionPlan = SubscriptionPlan.PERSONAL;
        Subscription userSubscription = new VideoSubscription(userSubscriptionPlan);
        validityInMonthsExpected = 1;
        priceOfThePlanExpected = 200;
        validityInMonthsActual = userSubscription.getValidity();
        priceOfThePlanActual = userSubscription.getPriceOfThePlan();
        assertEquals(validityInMonthsExpected, validityInMonthsActual);
        assertEquals(priceOfThePlanExpected, priceOfThePlanActual);
    }

    @Test
    @DisplayName("Test to check for PREMIUM Video Subscription")
    public void premiumVideoSubscriptionTest(){
        userSubscriptionPlan = SubscriptionPlan.PREMIUM;
        Subscription userSubscription = new VideoSubscription(userSubscriptionPlan);
        validityInMonthsExpected = 3;
        priceOfThePlanExpected = 500;
        validityInMonthsActual = userSubscription.getValidity();
        priceOfThePlanActual = userSubscription.getPriceOfThePlan();
        assertEquals(validityInMonthsExpected, validityInMonthsActual);
        assertEquals(priceOfThePlanExpected, priceOfThePlanActual);
    }
}

