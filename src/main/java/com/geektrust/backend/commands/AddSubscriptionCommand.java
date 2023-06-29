package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.entities.MusicSubscription;
import com.geektrust.backend.entities.PodcastSubscription;
import com.geektrust.backend.entities.Subscription;
import com.geektrust.backend.entities.User;
import com.geektrust.backend.entities.VideoSubscription;
import com.geektrust.backend.enums.ErrorScenario;
import com.geektrust.backend.enums.SubscriptionPlan;
import com.geektrust.backend.enums.SubscriptionType;
import com.geektrust.backend.exceptions.AddSubscriptionFailed;

public class AddSubscriptionCommand implements SubscriptionCommands{
    private User currentUser;
    private Subscription userSubscription;
    private final int INDEX_OF_SUBSCRIPTION_TYPE = 1;
    private final int INDEX_OF_SUBSCRIPTION_PLAN = 2;
    public AddSubscriptionCommand(User currentUser, List<String> inputCommands) throws AddSubscriptionFailed{
        this.currentUser = currentUser;
        if(this.currentUser==null)
            throw new AddSubscriptionFailed(ErrorScenario.INVALID_DATE.toString());
        else{
            
            SubscriptionPlan userSubscriptionPlan = SubscriptionPlan.valueOf(inputCommands.get(INDEX_OF_SUBSCRIPTION_PLAN));
            String subscriptionType = inputCommands.get(INDEX_OF_SUBSCRIPTION_TYPE);
            if(subscriptionType.equals(SubscriptionType.MUSIC.toString())){
                userSubscription = new MusicSubscription(userSubscriptionPlan);
            }else if(subscriptionType.equals(SubscriptionType.PODCAST.toString())){
                userSubscription = new PodcastSubscription(userSubscriptionPlan);
            }else if(subscriptionType.equals(SubscriptionType.VIDEO.toString())){
                userSubscription = new VideoSubscription(userSubscriptionPlan);
            }else{
                throw new AddSubscriptionFailed(ErrorScenario.INVALID_SUBSCRIPTION.toString());
            }
        }
    }

    public void execute() throws AddSubscriptionFailed{
        currentUser.addSubscription(userSubscription);
    }
}
