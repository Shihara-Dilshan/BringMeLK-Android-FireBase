package app.noobstack.bringme.bringmelk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TotalPaymentsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calTotal() {
        TotalPayments totalPayments = new TotalPayments();
        double input = 112;
        double output = totalPayments.calTotal(input);
        double expected = 112;
        double delta = 0.1;

        assertEquals(expected, output, delta);
    }
}