package app.noobstack.bringme.bringmelk.IT19058092;


import junit.extensions.ActiveTestSuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import app.noobstack.bringme.bringmelk.BuyActivity;

import static org.junit.Assert.*;


public class BuyActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void buyTotal() {
        double input = 1000.0;
        double discount = 5.0;
        double output = BuyActivity.BuyTotal(input,discount);
        double expected = 950.0;
        double delta = 0.1;

        assertEquals(expected, output, delta);
    }

    @Test
    public void testUUIDGeneratorValid(){
        int UUIDLength = 36;
        int output = UUIDLength;

        assertEquals(output, BuyActivity.generateUUIDString().length());
    }

    @Test
    public void testUUIDGeneratorInvalid(){
        int UUIDLength = 12;
        int output = UUIDLength;

        assertNotEquals(output, BuyActivity.generateUUIDString().length());
    }

    @Test
    public void testUUIDGenerator(){
        assertNotNull(BuyActivity.generateUUIDString());
    }

    @Test
    public void testStringEmpty(){
        String input = "value";
        boolean expectedResult = false;
        boolean output = BuyActivity.checkEmptyString(input);

        assertEquals(expectedResult, output);
    }

    @Test
    public void testStringEmptyTest2(){
        String input = "";
        boolean expectedResult = true;
        boolean output = BuyActivity.checkEmptyString(input);

        assertEquals(expectedResult, output);
    }

    @Test
    public void testMobileValidation(){
        String input = "0750935556";
        boolean expectedResult = true;
        boolean output = BuyActivity.mobileValidator(input);

        assertEquals(expectedResult, output);
    }

    @Test
    public void testMobileValidation2(){
        String input = "0750gjj556";
        boolean expectedResult = false;
        boolean output = BuyActivity.mobileValidator(input);

        assertEquals(expectedResult, output);
    }

}