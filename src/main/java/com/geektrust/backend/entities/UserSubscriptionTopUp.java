package com.geektrust.backend.entities;

import com.geektrust.backend.enums.TopUpPlan;

public class UserSubscriptionTopUp implements TopUp {
    private TopUpPlan userTopUpPlan;
    private int numberOfDevicesSupported;
    private int topUpPrice;
    private int topUpvalidityInMonths;
    private final int TOP_UP_FOUR_DEVICE_PRICE = 50;
    private final int TOP_UP_FOUR_DEVICE_SUPPORTED = 4;
    private final int TOP_UP_TEN_DEVICE_SUPPORTED = 10;
    private final int TOP_UP_TEN_DEVICE_PRICE = 100;
    public UserSubscriptionTopUp(TopUpPlan userTopUpPlan, int topUpvalidityInMonths){
        this.userTopUpPlan = userTopUpPlan;
        this.topUpvalidityInMonths = topUpvalidityInMonths;
        if(userTopUpPlan.equals(TopUpPlan.FOUR_DEVICE)){
            numberOfDevicesSupported = TOP_UP_FOUR_DEVICE_SUPPORTED;
            topUpPrice = TOP_UP_FOUR_DEVICE_PRICE * topUpvalidityInMonths;
        }else{
            numberOfDevicesSupported = TOP_UP_TEN_DEVICE_SUPPORTED;
            topUpPrice =  TOP_UP_TEN_DEVICE_PRICE * topUpvalidityInMonths;
        }
    }

    @Override
    public TopUpPlan getTopUpPlan() {
        return userTopUpPlan;
    }

    @Override
    public int getNumberOfDevicesSupported() {
        return numberOfDevicesSupported;
    }

    @Override
    public int getTopUpPrice() {
        return topUpPrice;
    }

    @Override
    public int getTopUpValidity() {
        return topUpvalidityInMonths;
    }
}
