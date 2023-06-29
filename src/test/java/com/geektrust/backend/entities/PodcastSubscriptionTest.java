package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.geektrust.backend.enums.SubscriptionPlan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Podcast Subscription Test")
public class PodcastSubscriptionTest {
    private SubscriptionPlan userSubscriptionPlan;
    private int validityInMonthsActual;
    private int validityInMonthsExpected;
    private int priceOfThePlanActual;
    private int priceOfThePlanExpected;

    @Test
    @DisplayName("Test to check for FREE Podcast Subscription")
    public void freePodcastSubscriptionTest(){
        userSubscriptionPlan = SubscriptionPlan.FREE;
        Subscription podcastSubscription = new PodcastSubscription(userSubscriptionPlan);
        validityInMonthsExpected = 1;
        priceOfThePlanExpected = 0;
        validityInMonthsActual = podcastSubscription.getValidity();
        priceOfThePlanActual = podcastSubscription.getPriceOfThePlan();
        assertEquals(validityInMonthsExpected,validityInMonthsActual);
        assertEquals(priceOfThePlanExpected, priceOfThePlanActual);
    }

    @Test
    @DisplayName("Test to check for PERSONAL Podcast Subscription")
    public void personalPodcastSubscriptionTest(){
        userSubscriptionPlan = SubscriptionPlan.PERSONAL;
        Subscription podcastSubscription = new PodcastSubscription(userSubscriptionPlan);
        validityInMonthsExpected = 1;
        priceOfThePlanExpected = 100;
        validityInMonthsActual = podcastSubscription.getValidity();
        priceOfThePlanActual = podcastSubscription.getPriceOfThePlan();
        assertEquals(validityInMonthsExpected,validityInMonthsActual);
        assertEquals(priceOfThePlanExpected, priceOfThePlanActual);
    }

    @Test
    @DisplayName("Test to check for PREMIUM Podcast Subscription")
    public void premiumPodcastSubscriptionTest(){
        userSubscriptionPlan = SubscriptionPlan.PREMIUM;
        Subscription podcastSubscription = new PodcastSubscription(userSubscriptionPlan);
        validityInMonthsExpected = 3;
        priceOfThePlanExpected = 300;
        validityInMonthsActual = podcastSubscription.getValidity();
        priceOfThePlanActual = podcastSubscription.getPriceOfThePlan();
        assertEquals(validityInMonthsExpected,validityInMonthsActual);
        assertEquals(priceOfThePlanExpected, priceOfThePlanActual);
    }
}
