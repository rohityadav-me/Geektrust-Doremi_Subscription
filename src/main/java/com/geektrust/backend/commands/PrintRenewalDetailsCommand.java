package com.geektrust.backend.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.geektrust.backend.entities.Subscription;
import com.geektrust.backend.entities.User;
import com.geektrust.backend.enums.ErrorScenario;
import com.geektrust.backend.enums.SubscriptionPlan;
import com.geektrust.backend.exceptions.SubscriptionsNotFound;

public class PrintRenewalDetailsCommand implements SubscriptionCommands{
    private User currentUser;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public PrintRenewalDetailsCommand(User currentUser) throws SubscriptionsNotFound{
        this.currentUser = currentUser;
        if(this.currentUser == null)
            throw new SubscriptionsNotFound(ErrorScenario.SUBSCRIPTIONS_NOT_FOUND.toString());
    }

    private String calculateRenewalReminderDate(LocalDate currentDate, SubscriptionPlan userPlan){
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year =  currentDate.getYear();
        int monthToAdd = 0;
        int numberOfMonthsInYear = 12;
        int decemberMonthNumber = 12;
        int daysToSubtract = 10;
        if(userPlan.equals(SubscriptionPlan.FREE) || userPlan.equals(SubscriptionPlan.PERSONAL)){
            monthToAdd = 1;
        }else{
            monthToAdd = 3;
        }
        month += monthToAdd;
        month %= numberOfMonthsInYear;
        if(month==0)
            month = decemberMonthNumber;
        else if(month<currentDate.getMonthValue())
            year++;
        String monthInString = String.valueOf(month);
        String dayInString = String.valueOf(day);
        int expectedDayMonthFormatLength = 10;
        String zeroAppend = "0";
        if(month<expectedDayMonthFormatLength)
            monthInString = zeroAppend + monthInString;
        if(day<expectedDayMonthFormatLength)
            dayInString = zeroAppend + dayInString;
        String newDate = dayInString+"-"+monthInString+"-"+String.valueOf(year);
        LocalDate afterParsing = parseGivenStringDate(newDate);
        afterParsing = afterParsing.minusDays(daysToSubtract);
        return afterParsing.format(formatter);
    }
    private LocalDate parseGivenStringDate(String date){
        LocalDate parsedDate = LocalDate.parse(date,formatter);
        return parsedDate;
    }
    public void execute() throws SubscriptionsNotFound{
        List<Subscription> usersSubscriptions = currentUser.getUsersActiveSubscriptions();
        int numberOfSubscriptions = usersSubscriptions.size();
        int zeroSubscriptions = 0;
        LocalDate subscriptionStartDate = currentUser.getSubscriptionStartDate();
        if(numberOfSubscriptions==zeroSubscriptions){
            throw new SubscriptionsNotFound(ErrorScenario.SUBSCRIPTIONS_NOT_FOUND.toString());
        }else{
            for(Subscription currentSubscription : usersSubscriptions){
                System.out.println("RENEWAL_REMINDER " + currentSubscription + " "+calculateRenewalReminderDate(subscriptionStartDate, currentSubscription.getSubscriptionPlan()));
            }
            System.out.println("RENEWAL_AMOUNT "+currentUser.getTotalSubscriptionAmout());
        }
    }
}
