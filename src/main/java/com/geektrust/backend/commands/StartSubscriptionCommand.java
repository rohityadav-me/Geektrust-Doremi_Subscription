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
    private final int DATE_INDEX_IN_COMMAND = 1;
    private final String DATE_FORMAT = "dd-MM-yyyy";
    private final int INDEX_OF_USER = 0;
    public StartSubscriptionCommand(List<User> modifyUser, List<String> commandExecuted){
        String date = commandExecuted.get(DATE_INDEX_IN_COMMAND);
        if(isValidDate(date)){
            modifyUser.set(INDEX_OF_USER, new User(subscriptionStartDate));
        }
    }
    private boolean isValidDate(String date){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
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
