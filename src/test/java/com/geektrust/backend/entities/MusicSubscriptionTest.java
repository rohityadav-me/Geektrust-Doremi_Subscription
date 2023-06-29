package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.geektrust.backend.enums.SubscriptionPlan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Music Subscription Test")
public class MusicSubscriptionTest {
    private SubscriptionPlan userSubscriptionPlan;
    private int validityInMonthsExpected;
    private int priceOfThePlanExpected;
    private int validityInMonthsActual;
    private int priceOfThePlanActual;

    @Test
    @DisplayName("Test to check for FREE Music Subscription")
    public void freeMusicSubscriptionTest(){
        userSubscriptionPlan = SubscriptionPlan.FREE;
        Subscription musicSubscription = new MusicSubscription(userSubscriptionPlan);
        validityInMonthsExpected = 1;
        priceOfThePlanExpected = 0;
        validityInMonthsActual = musicSubscription.getValidity();
        priceOfThePlanActual = musicSubscription.getPriceOfThePlan();
        assertEquals(validityInMonthsExpected, validityInMonthsActual);
        assertEquals(priceOfThePlanExpected, priceOfThePlanActual);
    }

    @Test
    @DisplayName("Test to check for PERSONAL Music Subscription")
    public void personalMusicSubscriptionTest(){
        userSubscriptionPlan = SubscriptionPlan.PERSONAL;
        Subscription musicSubscription = new MusicSubscription(userSubscriptionPlan);
        validityInMonthsExpected = 1;
        priceOfThePlanExpected = 100;
        validityInMonthsActual = musicSubscription.getValidity();
        priceOfThePlanActual = musicSubscription.getPriceOfThePlan();
        assertEquals(validityInMonthsExpected, validityInMonthsActual);
        assertEquals(priceOfThePlanExpected, priceOfThePlanActual);
    }

    @Test
    @DisplayName("Test to check for PREMIUM Music Subscription")
    public void premiumMusicSubscriptionTest(){
        userSubscriptionPlan = SubscriptionPlan.PREMIUM;
        Subscription musicSubscription = new MusicSubscription(userSubscriptionPlan);
        validityInMonthsExpected = 3;
        priceOfThePlanExpected = 250;
        validityInMonthsActual = musicSubscription.getValidity();
        priceOfThePlanActual = musicSubscription.getPriceOfThePlan();
        assertEquals(validityInMonthsExpected, validityInMonthsActual);
        assertEquals(priceOfThePlanExpected, priceOfThePlanActual);
    }
}
