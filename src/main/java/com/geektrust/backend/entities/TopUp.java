package com.geektrust.backend.entities;

import com.geektrust.backend.enums.TopUpPlan;

public interface TopUp {

    public TopUpPlan getTopUpPlan();

    public int getNumberOfDevicesSupported();

    public int getTopUpPrice();

    public int getTopUpValidity();
}
