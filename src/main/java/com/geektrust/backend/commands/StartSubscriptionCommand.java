package com.geektrust.backend.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import com.geektrust.backend.entities.User;
import com.geektrust.backend.enums.ErrorScenario;
import com.geektrust.backend.exceptions.AddSubscriptionFailed;

public class StartSubscriptionCommand implements SubscriptionCommands {
    private LocalDate subscriptionStartDate;
    public StartSubscriptionCommand(List<User> modifyUser, List<String> commandExecuted){
        int dateIndexInCommand = 1;
        String date = commandExecuted.get(dateIndexInCommand);
        if(isValidDate(date)){
            int indexOfuser = 0;
            modifyUser.set(indexOfuser, new User(subscriptionStartDate));
        }
    }
    private boolean isValidDate(String date){
        try{
            String dateFormat = "dd-MM-yyyy";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            subscriptionStartDate = LocalDate.parse(date,formatter);
            return true;
        }catch(DateTimeParseException e){
            return false;
        }
    }
    public void execute() throws AddSubscriptionFailed{
        if(subscriptionStartDate==null)
            throw new AddSubscriptionFailed(ErrorScenario.INVALID_DATE.toString());
    }
}
