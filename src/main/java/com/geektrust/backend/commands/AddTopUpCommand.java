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
    private final int INDEX_OF_TOP_UP_PLAN = 1;
    private final int INDEX_OF_TOP_UP_VALIDITY = 2;
    public AddTopUpCommand(User currentUser,List<String> inputCommands) throws AddTopUpFailed{
        this.currentUser = currentUser;
        if(this.currentUser == null)
            throw new AddTopUpFailed(ErrorScenario.INVALID_DATE.toString());
        else{
            TopUpPlan userTopUpPlan = TopUpPlan.valueOf(inputCommands.get(INDEX_OF_TOP_UP_PLAN));
            int topUpvalidityInMonths = Integer.parseInt(inputCommands.get(INDEX_OF_TOP_UP_VALIDITY));
            userTopUp = new UserSubscriptionTopUp(userTopUpPlan, topUpvalidityInMonths);
        }
    }
    public void execute() throws AddTopUpFailed{
        currentUser.addTopUp(userTopUp);
    }
}
