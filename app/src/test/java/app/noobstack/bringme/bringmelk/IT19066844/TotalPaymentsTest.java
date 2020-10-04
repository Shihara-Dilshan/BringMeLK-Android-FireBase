package app.noobstack.bringme.bringmelk.IT19066844;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.noobstack.bringme.bringmelk.TotalPayments;

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

    @Test
    public void calTotalBiggerValues() {
        TotalPayments totalPayments = new TotalPayments();
        double input = 121212112;
        double output = totalPayments.calTotal(input);
        double expected = 121212112;
        double delta = 0.1;

        assertEquals(expected, output, delta);
    }

    @Test
    public void TotalNullable() {
        TotalPayments totalPayments = new TotalPayments();
        double input = 121212112;
        double output = totalPayments.calTotal(input);

        assertNotNull(output);
    }

    @Test
    public void convertDouble() {
        String input = "2323232";
        double output = TotalPayments.convertTODouble(input);
        double expected = 2323232;
        double delta = 0.1;

        assertEquals(expected, output, delta);
    }

    @Test
    public void convertString() {
        Double input = 2323232.0;
        String output = TotalPayments.convertTOString(input);
        String  expected = "2323232.0";

        assertEquals(expected, output);
    }

    @Test
    public void convertStringNullable() {
        Double input = 2323232.0;
        String output = TotalPayments.convertTOString(input);

        assertNotNull(output);
    }



}