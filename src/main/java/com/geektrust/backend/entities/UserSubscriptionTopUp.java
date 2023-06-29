package com.geektrust.backend.entities;

import com.geektrust.backend.enums.TopUpPlan;

public class UserSubscriptionTopUp implements TopUp {
    private TopUpPlan userTopUpPlan;
    private int numberOfDevicesSupported;
    private int topUpPrice;
    private int topUpvalidityInMonths;

    public UserSubscriptionTopUp(TopUpPlan userTopUpPlan, int topUpvalidityInMonths){
        this.userTopUpPlan = userTopUpPlan;
        this.topUpvalidityInMonths = topUpvalidityInMonths;
        if(userTopUpPlan.equals(TopUpPlan.FOUR_DEVICE)){
            numberOfDevicesSupported = 4;
            topUpPrice = 50 * topUpvalidityInMonths;
        }else{
            numberOfDevicesSupported = 10;
            topUpPrice =  100 * topUpvalidityInMonths;
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
