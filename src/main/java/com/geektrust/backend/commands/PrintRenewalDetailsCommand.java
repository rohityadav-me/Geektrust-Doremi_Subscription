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
    private final int DAYS_TO_SUBTRACT = 10;
    private final int NUMBER_OF_MONTHS_IN_YEAR = 12;
    private final int DECEMBER_MONTH_NUMBER = 0;
    private final int EXPECTED_DAY_MONTH_FORMAT_LENGTH = 10;
    private final int ZERO_SUBSCRIPTIONS = 0;
    private final String ZERO_APPEND = "0";
    private final int SUBSCRIPTION_PLAN_PERSONAL_FREE = 1;
    private final int SUBSCRIPTION_PLAN_PREMIUM = 3;
    
    public PrintRenewalDetailsCommand(User currentUser) throws SubscriptionsNotFound{
        this.currentUser = currentUser;
        if(this.currentUser == null)
            throw new SubscriptionsNotFound(ErrorScenario.SUBSCRIPTIONS_NOT_FOUND.toString());
    }

    private String calculateRenewalReminderDate(LocalDate currentDate, SubscriptionPlan userPlan){
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year =  currentDate.getYear();
        String newDate = adjustDate(day, month, year, userPlan);
        LocalDate afterParsing = parseGivenStringDate(newDate);
        afterParsing = afterParsing.minusDays(DAYS_TO_SUBTRACT);
        return afterParsing.format(formatter);
    }
    private String adjustDate(int day, int month, int year,SubscriptionPlan userPlan){
        int initialMonth = month;
        int monthToAdd = checkSubscriptionPlan(userPlan);
        month += monthToAdd;
        month %= NUMBER_OF_MONTHS_IN_YEAR;
        if(month==DECEMBER_MONTH_NUMBER)
            month = NUMBER_OF_MONTHS_IN_YEAR;
        else if(month<initialMonth)
            year++;
        return createAdjustedDate(day, month, year);
    }

    private String createAdjustedDate(int day, int month, int year){
        String monthInString = String.valueOf(month);
        String dayInString = String.valueOf(day);
        if(month<EXPECTED_DAY_MONTH_FORMAT_LENGTH)
            monthInString = ZERO_APPEND + monthInString;
        if(day<EXPECTED_DAY_MONTH_FORMAT_LENGTH)
            dayInString = ZERO_APPEND + dayInString;
        String newDate = dayInString+"-"+monthInString+"-"+String.valueOf(year);
        return newDate;
    }

    private int checkSubscriptionPlan(SubscriptionPlan userPlan){
        int monthToAdd = 0;
        if(userPlan.equals(SubscriptionPlan.FREE) || userPlan.equals(SubscriptionPlan.PERSONAL)){
            monthToAdd = SUBSCRIPTION_PLAN_PERSONAL_FREE;
        }else{
            monthToAdd = SUBSCRIPTION_PLAN_PREMIUM;
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
        LocalDate subscriptionStartDate = currentUser.getSubscriptionStartDate();
        if(numberOfSubscriptions==ZERO_SUBSCRIPTIONS){
            throw new SubscriptionsNotFound(ErrorScenario.SUBSCRIPTIONS_NOT_FOUND.toString());
        }else{
            for(Subscription currentSubscription : usersSubscriptions){
                System.out.println("RENEWAL_REMINDER " + currentSubscription + " "+calculateRenewalReminderDate(subscriptionStartDate, currentSubscription.getSubscriptionPlan()));
            }
            System.out.println("RENEWAL_AMOUNT "+currentUser.getTotalSubscriptionAmout());
        }
    }
}
