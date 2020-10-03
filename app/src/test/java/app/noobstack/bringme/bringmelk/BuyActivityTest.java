package app.noobstack.bringme.bringmelk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}