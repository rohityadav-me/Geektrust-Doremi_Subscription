package com.geektrust.backend.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.geektrust.backend.entities.MusicSubscription;
import com.geektrust.backend.entities.PodcastSubscription;
import com.geektrust.backend.entities.Subscription;
import com.geektrust.backend.entities.TopUp;
import com.geektrust.backend.entities.User;
import com.geektrust.backend.entities.UserSubscriptionTopUp;
import com.geektrust.backend.entities.VideoSubscription;
import com.geektrust.backend.enums.SubscriptionPlan;
import com.geektrust.backend.enums.TopUpPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Print Renewal Details Command Test")
public class PrintRenewalsDetailsCommandTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private User user;
    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    @DisplayName("Test with correct inputs all three subscription and top up")
    public void correctInputTestAllSubscriptions(){
        String date = "20-02-2022";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate inputDate = LocalDate.parse(date,formatter);
        user = new User(inputDate);
        Subscription musicPersonalSubscription = new MusicSubscription(SubscriptionPlan.PERSONAL);
        Subscription videoPremiumSubscription = new VideoSubscription(SubscriptionPlan.PREMIUM);
        Subscription podcastFreeSubscription = new PodcastSubscription(SubscriptionPlan.FREE);
        user.addSubscription(musicPersonalSubscription);
        user.addSubscription(videoPremiumSubscription);
        user.addSubscription(podcastFreeSubscription);
        int valididytMonths = 3;
        TopUp userTopUp = new UserSubscriptionTopUp(TopUpPlan.FOUR_DEVICE, valididytMonths);
        user.addTopUp(userTopUp);
        SubscriptionCommands userCommand = new PrintRenewalDetailsCommand(user);
        String expected = "RENEWAL_REMINDER MUSIC 10-03-2022"+"\n"+
                          "RENEWAL_REMINDER VIDEO 10-05-2022"+"\n"+
                          "RENEWAL_REMINDER PODCAST 10-03-2022"+"\n"+
                          "RENEWAL_AMOUNT 750";
        userCommand.execute();
        String actualOutput = outputStream.toString().trim();
        assertEquals(expected, actualOutput);
    }

    @Test
    @DisplayName("Test with correct inputs of two subscriptions")
    public void correctInputTestTwoSubscriptions(){
        String date = "01-02-2022";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate inputDate = LocalDate.parse(date,formatter);
        user = new User(inputDate);
        Subscription musicPersonalSubscription = new MusicSubscription(SubscriptionPlan.PERSONAL);
        Subscription videoPremiumSubscription = new VideoSubscription(SubscriptionPlan.PREMIUM);
        user.addSubscription(musicPersonalSubscription);
        user.addSubscription(videoPremiumSubscription);
        SubscriptionCommands userCommand = new PrintRenewalDetailsCommand(user);
        String expected = "RENEWAL_REMINDER MUSIC 19-02-2022"+"\n"+
                          "RENEWAL_REMINDER VIDEO 21-04-2022"+"\n"+
                          "RENEWAL_AMOUNT 600";
        userCommand.execute();
        String actualOutput = outputStream.toString().trim();
        assertEquals(expected, actualOutput);
    }
}
