package com.geektrust.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("App Test")
class AppTest {
    private String fileLocation;
    private String[] input;
    private ByteArrayOutputStream outputStream= new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    @DisplayName("Integration Test 1")
    public void integrationTest1(){
        fileLocation = "sample_input/input1.txt";
        input = new String[]{fileLocation};
        App.main(input);
        String expected = "RENEWAL_REMINDER MUSIC 10-03-2022"+"\n"+
                        "RENEWAL_REMINDER VIDEO 10-05-2022"+"\n"+
                        "RENEWAL_REMINDER PODCAST 10-03-2022"+"\n"+
                        "RENEWAL_AMOUNT 750";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Integration Test 2 with wrong inputs")
    public void integrationTest2(){
        fileLocation = "sample_input/input2.txt";
        input = new String[]{fileLocation};
        App.main(input);
        String expected = "ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND"+"\n"+
                         "SUBSCRIPTIONS_NOT_FOUND";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Integration Test 3 with wrong inputs")
    public void integrationTest3(){
        fileLocation = "sample_input/input3.txt";
        input = new String[]{fileLocation};
        App.main(input);
        String expected = "INVALID_DATE" +  "\n"+
        "ADD_SUBSCRIPTION_FAILED INVALID_DATE" +"\n"+
        "ADD_SUBSCRIPTION_FAILED INVALID_DATE" +"\n"+
        "ADD_TOPUP_FAILED INVALID_DATE" + "\n" +
        "SUBSCRIPTIONS_NOT_FOUND";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}
