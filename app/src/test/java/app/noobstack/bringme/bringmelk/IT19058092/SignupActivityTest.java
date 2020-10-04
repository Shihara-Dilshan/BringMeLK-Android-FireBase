package app.noobstack.bringme.bringmelk.IT19058092;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.noobstack.bringme.bringmelk.SignupActivity;

import static org.junit.Assert.*;

public class SignupActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStringsInputNotNull() {
        String input = "shihara@gmail.com";
        boolean result = true;
        boolean output = SignupActivity.validate(input);

        assertEquals(result,output);

    }

    @Test
    public void testStringsInputIsEmpty() {
        String input = "";
        boolean result = false;
        boolean output = SignupActivity.validate(input);

        assertEquals(result,output);

    }

    @Test
    public void testEmailEnd() {
        String input = "something@gmail.com";
        boolean result = true;
        boolean output = SignupActivity.validateEmail(input);

        assertEquals(result,output);

    }

    @Test
    public void testEmail() {
        String input = "something@gmail";
        boolean result = false;
        boolean output = SignupActivity.validateEmail(input);

        assertEquals(result,output);

    }

}