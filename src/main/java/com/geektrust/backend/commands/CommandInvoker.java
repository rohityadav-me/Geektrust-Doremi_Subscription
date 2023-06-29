package com.geektrust.backend.commands;

import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.entities.User;
import com.geektrust.backend.enums.CLICommands;
import com.geektrust.backend.exceptions.AddSubscriptionFailed;
import com.geektrust.backend.exceptions.AddTopUpFailed;
import com.geektrust.backend.exceptions.NoSuchCommand;
import com.geektrust.backend.exceptions.SubscriptionsNotFound;

public class CommandInvoker {
    private User currentUser;
    private SubscriptionCommands executingCommands;

    public void execute(List<String> inputCommands) throws NoSuchCommand{
        int commandIndex = 0;
        String command = inputCommands.get(commandIndex).toUpperCase();

        if(command.equals(CLICommands.START_SUBSCRIPTION.toString())){
            try{
                List<User> modifyUser = Arrays.asList(currentUser);
                executingCommands = new StartSubscriptionCommand(modifyUser, inputCommands);
                executingCommands.execute();
                int indexOfUserCreated = 0;
                currentUser = modifyUser.get(indexOfUserCreated);
            }catch(AddSubscriptionFailed e){
                System.out.println(e.getMessage());
            }
        }else if(command.equals(CLICommands.ADD_SUBSCRIPTION.toString())){
            try{
                executingCommands = new AddSubscriptionCommand(currentUser, inputCommands);
                executingCommands.execute();
            }catch(AddSubscriptionFailed e){
                System.out.println("ADD_SUBSCRIPTION_FAILED "+e.getMessage());
            }
        }else if(command.equals(CLICommands.ADD_TOPUP.toString())){
            try{
                executingCommands = new AddTopUpCommand(currentUser, inputCommands);
                executingCommands.execute();
            }catch(AddTopUpFailed e){
                System.out.println("ADD_TOPUP_FAILED "+e.getMessage());
            }
        }else if(command.equals(CLICommands.PRINT_RENEWAL_DETAILS.toString())){
            try{
                executingCommands = new PrintRenewalDetailsCommand(currentUser);
                executingCommands.execute();
            }catch(SubscriptionsNotFound e){
                System.out.println(e.getMessage());
            }
        }else{
            throw new NoSuchCommand("Entered command is not valid");
        }
    }
}
