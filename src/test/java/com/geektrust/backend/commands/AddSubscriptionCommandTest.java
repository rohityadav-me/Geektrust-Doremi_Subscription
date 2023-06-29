package com.geektrust.backend.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.entities.User;
import com.geektrust.backend.enums.CLICommands;
import com.geektrust.backend.enums.ErrorScenario;
import com.geektrust.backend.enums.SubscriptionPlan;
import com.geektrust.backend.enums.SubscriptionType;
import com.geektrust.backend.exceptions.AddSubscriptionFailed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Add Subscription Command Test")
public class AddSubscriptionCommandTest {
    User currentUser;
    List<String> inputCommands;
    String cliCommand = String.valueOf(CLICommands.ADD_SUBSCRIPTION);
    String subscriptionPlan;
    String subscriptionType;
    SubscriptionCommands addSubscriptionCommands;
    
    @Test
    @DisplayName("Testing With Correct Inputs")
    public void correctInputTests(){
        String date = "03-03-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDate = LocalDate.parse(date,formatter);
        currentUser = new User(parsedDate);
        subscriptionType = String.valueOf(SubscriptionType.MUSIC);
        subscriptionPlan = String.valueOf(SubscriptionPlan.PREMIUM);
        inputCommands = Arrays.asList(cliCommand,subscriptionType, subscriptionPlan);
        addSubscriptionCommands = new AddSubscriptionCommand(currentUser, inputCommands);
        addSubscriptionCommands.execute();
        int expected = 250;
        assertEquals(expected, currentUser.getTotalSubscriptionAmout());
        subscriptionType = String.valueOf(SubscriptionType.VIDEO);
        subscriptionPlan = String.valueOf(SubscriptionPlan.PREMIUM);
        inputCommands = Arrays.asList(cliCommand,subscriptionType,subscriptionPlan);
        addSubscriptionCommands = new AddSubscriptionCommand(currentUser, inputCommands);
        addSubscriptionCommands.execute();
        expected = 750;
        assertEquals(expected, currentUser.getTotalSubscriptionAmout());
    }

    @Test
    @DisplayName("Testing with incorrect input when User = null")
    public void incorrectTestInputUserNull(){
        currentUser = null;
        subscriptionType = String.valueOf(SubscriptionType.MUSIC);
        subscriptionPlan = String.valueOf(SubscriptionPlan.PREMIUM);
        inputCommands = Arrays.asList(cliCommand,subscriptionType, subscriptionPlan);
        AddSubscriptionFailed exception = assertThrows(AddSubscriptionFailed.class,() -> {
            addSubscriptionCommands = new AddSubscriptionCommand(currentUser, inputCommands);
        });
        assertEquals(ErrorScenario.INVALID_DATE.toString(), exception.getMessage());
    }

    @Test
    @DisplayName("Testing when same subscriptions are added")
    public void sameSubscriptionAddedTest(){
        String date = "03-03-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDate = LocalDate.parse(date,formatter);
        currentUser = new User(parsedDate);
        subscriptionType = String.valueOf(SubscriptionType.MUSIC);
        subscriptionPlan = String.valueOf(SubscriptionPlan.PREMIUM);
        inputCommands = Arrays.asList(cliCommand,subscriptionType, subscriptionPlan);
        addSubscriptionCommands = new AddSubscriptionCommand(currentUser, inputCommands);
        addSubscriptionCommands.execute();
        subscriptionPlan = String.valueOf(SubscriptionPlan.PERSONAL);
        inputCommands = Arrays.asList(cliCommand,subscriptionType,subscriptionPlan);
        addSubscriptionCommands = new AddSubscriptionCommand(currentUser, inputCommands);
        AddSubscriptionFailed exception = assertThrows(AddSubscriptionFailed.class, () ->{
            addSubscriptionCommands.execute();
        });
        assertEquals(ErrorScenario.DUPLICATE_CATEGORY.toString(), exception.getMessage());
    }
}
