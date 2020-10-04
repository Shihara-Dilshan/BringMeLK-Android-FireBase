package app.noobstack.bringme.bringmelk.IT19055404;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.noobstack.bringme.bringmelk.AddNewFood;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddNewFoodTest {

    boolean validPrice;
    boolean validDiscount;
    static AddNewFood newFood;



    @Before
    public void createObject(){
        newFood = new AddNewFood();
    }

    @Test
    public void isValidDiscount(){
        validDiscount = newFood.isValidDiscount(20);
        assertTrue(validDiscount);

    }
    @Test
    public void isInvalidDiscount(){
        validDiscount = newFood.isValidDiscount(-1);
        assertFalse(validDiscount);
    }
    @Test
    public void isInvalidDiscount2(){
        validDiscount = newFood.isValidDiscount(101);
        assertFalse(validDiscount);
    }

    @Test
    public void isInvalidPrice(){
        validPrice = newFood.isValidPrice(-2);
        assertFalse(validPrice);
    }
    @Test
    public void isValidPrice(){
        validPrice = newFood.isValidPrice(500);
        assertTrue(validPrice);
    }

    @After
    public void deleteObject(){
        newFood = null;
    }



}

