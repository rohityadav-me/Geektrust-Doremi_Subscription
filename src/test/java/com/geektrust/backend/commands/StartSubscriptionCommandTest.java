package com.geektrust.backend.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.entities.User;
import com.geektrust.backend.enums.CLICommands;
import com.geektrust.backend.enums.ErrorScenario;
import com.geektrust.backend.exceptions.AddSubscriptionFailed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Start Subscription Command Test")
public class StartSubscriptionCommandTest {
    SubscriptionCommands subscriptionCommandsInterface;
    User currentUser;
    LocalDate testingDate;
    List<String> testingCommands;
    String command = CLICommands.START_SUBSCRIPTION.toString();
    String date;
    List<User> userList;
    @Test
    @DisplayName("Test with correct date inputs")
    public void correctDatesTest(){
        
        date = "17-03-2023";
        testingCommands = Arrays.asList(command,date);
        userList = Arrays.asList(currentUser);
        subscriptionCommandsInterface = new StartSubscriptionCommand(userList, testingCommands);
        int indexOfUserCreated = 0;
        currentUser = userList.get(indexOfUserCreated);
        Assertions.assertNotEquals(null,currentUser);
    }

    @Test
    @DisplayName("Test with incorrect date inputs")
    public void incorrectDatesTest(){
        date = "35-03;2022";
        testingCommands = Arrays.asList(command,date);
        userList = Arrays.asList(currentUser);
        subscriptionCommandsInterface = new StartSubscriptionCommand(userList, testingCommands);
        int indexOfUserCreated = 0;
        currentUser = userList.get(indexOfUserCreated);
        AddSubscriptionFailed exception = Assertions.assertThrows(AddSubscriptionFailed.class, ()->{
            subscriptionCommandsInterface.execute();
        });
        assertEquals(ErrorScenario.INVALID_DATE.toString(), exception.getMessage());
        assertEquals(null, currentUser);
    }
}
