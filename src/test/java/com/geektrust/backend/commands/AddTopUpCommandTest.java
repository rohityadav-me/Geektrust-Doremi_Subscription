package com.geektrust.backend.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.entities.MusicSubscription;
import com.geektrust.backend.entities.Subscription;
import com.geektrust.backend.entities.TopUp;
import com.geektrust.backend.entities.User;
import com.geektrust.backend.enums.CLICommands;
import com.geektrust.backend.enums.ErrorScenario;
import com.geektrust.backend.enums.SubscriptionPlan;
import com.geektrust.backend.enums.TopUpPlan;
import com.geektrust.backend.exceptions.AddTopUpFailed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Add Top Up Command Test")
public class AddTopUpCommandTest {
    User currentUser;
    TopUp userTopUp;
    List<String> inputCommands;
    SubscriptionCommands userCommands;
    Subscription userSubscription;
    String cliCommand =  CLICommands.ADD_TOPUP.toString();

    @Test
    @DisplayName("Test with correct inputs")
    public void correctInputTest(){
        String date =  "03-03-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate inputDate = LocalDate.parse(date,formatter);
        currentUser = new User(inputDate);
        String topUpPlan = TopUpPlan.FOUR_DEVICE.toString();
        String monthsValidity = "4";
        inputCommands =  Arrays.asList(cliCommand,topUpPlan,monthsValidity);
        userSubscription = new MusicSubscription(SubscriptionPlan.PERSONAL);
        currentUser.addSubscription(userSubscription);
        userCommands = new AddTopUpCommand(currentUser, inputCommands);
        userCommands.execute();
        int expectedPrice = 300;
        assertEquals(expectedPrice, currentUser.getTotalSubscriptionAmout());
    }

    @Test
    @DisplayName("Test to add duplicate top up")
    public void duplicateTopUpTest(){
        String date =  "03-03-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate inputDate = LocalDate.parse(date,formatter);
        currentUser = new User(inputDate);
        String topUpPlan = TopUpPlan.FOUR_DEVICE.toString();
        String monthsValidity = "4";
        inputCommands =  Arrays.asList(cliCommand,topUpPlan,monthsValidity);
        userSubscription = new MusicSubscription(SubscriptionPlan.PERSONAL);
        currentUser.addSubscription(userSubscription);
        userCommands = new AddTopUpCommand(currentUser, inputCommands);
        userCommands.execute();
        topUpPlan = TopUpPlan.TEN_DEVICE.toString();
        inputCommands = Arrays.asList(cliCommand,topUpPlan,monthsValidity);
        userCommands = new AddTopUpCommand(currentUser,inputCommands);
        AddTopUpFailed exception = assertThrows(AddTopUpFailed.class, ()->{
            userCommands.execute();
        });
        assertEquals(ErrorScenario.DUPLICATE_TOPUP.toString(), exception.getMessage());
    }
}
