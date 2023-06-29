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
        int daysToSubtract = 10;
        String newDate = adjustDate(day, month, year, userPlan);
        LocalDate afterParsing = parseGivenStringDate(newDate);
        afterParsing = afterParsing.minusDays(daysToSubtract);
        return afterParsing.format(formatter);
    }
    private String adjustDate(int day, int month, int year,SubscriptionPlan userPlan){
        int initialMonth = month;
        int numberOfMonthsInYear = 12;
        int decemberMonthNumber = 0;
        int monthToAdd = checkSubscriptionPlan(userPlan);
        month += monthToAdd;
        month %= numberOfMonthsInYear;
        if(month==decemberMonthNumber)
            month = numberOfMonthsInYear;
        else if(month<initialMonth)
            year++;
        return createAdjustedDate(day, month, year);
    }

    private String createAdjustedDate(int day, int month, int year){
        String monthInString = String.valueOf(month);
        String dayInString = String.valueOf(day);
        int expectedDayMonthFormatLength = 10;
        String zeroAppend = "0";
        if(month<expectedDayMonthFormatLength)
            monthInString = zeroAppend + monthInString;
        if(day<expectedDayMonthFormatLength)
            dayInString = zeroAppend + dayInString;
        String newDate = dayInString+"-"+monthInString+"-"+String.valueOf(year);
        return newDate;
    }

    private int checkSubscriptionPlan(SubscriptionPlan userPlan){
        int monthToAdd = 0;
        if(userPlan.equals(SubscriptionPlan.FREE) || userPlan.equals(SubscriptionPlan.PERSONAL)){
            monthToAdd = 1;
        }else{
            monthToAdd = 3;
        }
        return monthToAdd;
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
