package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.geektrust.backend.enums.TopUpPlan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Subscription Top Up Test")
public class UserSubscriptionTopUpTest {
    private TopUpPlan userTopUpPlan;
    private int numberOfDevicesSupportedExpected;
    private int topUpPriceExpected;
    private int topUpvalidityInMonthsExpected;
    private int numberOfDevicesSupportedActual;
    private int topUpPriceActual;
    private int topUpvalidityInMonthsActual;

    @Test
    @DisplayName("User Subscription Four Device Top Up of 2 Months Test")
    public void fourDeviceTopUpTwoMonthsTest(){
        userTopUpPlan = TopUpPlan.FOUR_DEVICE;
        TopUp userTopUp = new UserSubscriptionTopUp(userTopUpPlan, 2);
        numberOfDevicesSupportedExpected = 4;
        topUpPriceExpected =  100;
        topUpvalidityInMonthsExpected = 2;
        numberOfDevicesSupportedActual = userTopUp.getNumberOfDevicesSupported();
        topUpPriceActual = userTopUp.getTopUpPrice();
        topUpvalidityInMonthsActual = userTopUp.getTopUpValidity();
        assertEquals(numberOfDevicesSupportedExpected, numberOfDevicesSupportedActual);
        assertEquals(topUpPriceExpected, topUpPriceActual);
        assertEquals(topUpvalidityInMonthsExpected, topUpvalidityInMonthsActual);
    }

    @Test
    @DisplayName("User Subscription Ten Device Top Up of 4 Months Test")
    public void tenDeviceTopUpFourMonthsTest(){
        userTopUpPlan = TopUpPlan.TEN_DEVICE;
        TopUp userTopUp = new UserSubscriptionTopUp(userTopUpPlan, 4);
        numberOfDevicesSupportedExpected = 10;
        topUpPriceExpected =  400;
        topUpvalidityInMonthsExpected = 4;
        numberOfDevicesSupportedActual = userTopUp.getNumberOfDevicesSupported();
        topUpPriceActual = userTopUp.getTopUpPrice();
        topUpvalidityInMonthsActual = userTopUp.getTopUpValidity();
        assertEquals(numberOfDevicesSupportedExpected, numberOfDevicesSupportedActual);
        assertEquals(topUpPriceExpected, topUpPriceActual);
        assertEquals(topUpvalidityInMonthsExpected, topUpvalidityInMonthsActual);
    }
}
