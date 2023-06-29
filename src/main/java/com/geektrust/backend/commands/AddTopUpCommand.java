package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.entities.TopUp;
import com.geektrust.backend.entities.User;
import com.geektrust.backend.entities.UserSubscriptionTopUp;
import com.geektrust.backend.enums.ErrorScenario;
import com.geektrust.backend.enums.TopUpPlan;
import com.geektrust.backend.exceptions.AddTopUpFailed;

public class AddTopUpCommand implements SubscriptionCommands{
    private User currentUser;
    private TopUp userTopUp;

    public AddTopUpCommand(User currentUser,List<String> inputCommands) throws AddTopUpFailed{
        this.currentUser = currentUser;
        if(this.currentUser == null)
            throw new AddTopUpFailed(ErrorScenario.INVALID_DATE.toString());
        else{
            int indexOfTopUpPlan = 1;
            int indexOfTopUpValidity = 2;
            TopUpPlan userTopUpPlan = TopUpPlan.valueOf(inputCommands.get(indexOfTopUpPlan));
            int topUpvalidityInMonths = Integer.parseInt(inputCommands.get(indexOfTopUpValidity));
            userTopUp = new UserSubscriptionTopUp(userTopUpPlan, topUpvalidityInMonths);
        }
    }
    public void execute() throws AddTopUpFailed{
        currentUser.addTopUp(userTopUp);
    }
}
