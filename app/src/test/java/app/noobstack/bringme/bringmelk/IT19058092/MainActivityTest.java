package app.noobstack.bringme.bringmelk.IT19058092;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.noobstack.bringme.bringmelk.MainActivity;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void httpsCheckerValid() {
        String validUrl = "https://wwww.facebook.com";
        boolean ExpectedOutput = true;
        boolean output = MainActivity.httpsChecker(validUrl);

        assertEquals(ExpectedOutput, output);

    }

    @Test
    public void httpsCheckerUnsecured() {
        String invalidUrl = "wwww.facebook.com";
        boolean ExpectedOutput = false;
        boolean output = MainActivity.httpsChecker(invalidUrl);

        assertEquals(ExpectedOutput, output);

    }
}