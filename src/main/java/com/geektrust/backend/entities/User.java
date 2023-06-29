package com.geektrust.backend.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.enums.ErrorScenario;
import com.geektrust.backend.exceptions.AddSubscriptionFailed;
import com.geektrust.backend.exceptions.AddTopUpFailed;

public class User {
    private List<Subscription> mySubscriptions;
    private LocalDate subscriptionStartDate;
    private TopUp myTopUp;
    private int totalAmount;

    public User(LocalDate subscriptionStartDate){
        this.subscriptionStartDate = subscriptionStartDate;
        mySubscriptions = new ArrayList<>();
        myTopUp = null;
        totalAmount = 0;
    }

    public void addSubscription(Subscription subscriptionToAdd) throws AddSubscriptionFailed{
        if(mySubscriptions.contains(subscriptionToAdd)){
            throw new AddSubscriptionFailed(ErrorScenario.DUPLICATE_CATEGORY.toString());
        }else{
            mySubscriptions.add(subscriptionToAdd);
            totalAmount += subscriptionToAdd.getPriceOfThePlan();
        }
    }
    public void addTopUp(TopUp topUpToAdd) throws AddTopUpFailed{
        if(myTopUp != null){
            throw new AddTopUpFailed(ErrorScenario.DUPLICATE_TOPUP.toString());
        }else if(mySubscriptions.size()==0){
            throw new AddTopUpFailed(ErrorScenario.SUBSCRIPTIONS_NOT_FOUND.toString());
        }else{
            myTopUp = topUpToAdd;
            totalAmount += myTopUp.getTopUpPrice();
        }
    }

    public int getTotalSubscriptionAmout(){
        return totalAmount;
    }

    public LocalDate getSubscriptionStartDate(){
        return subscriptionStartDate;
    }

    public List<Subscription> getUsersActiveSubscriptions(){
        return  mySubscriptions;
    }
}
