package app.noobstack.bringme.bringmelk.IT19055404;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.noobstack.bringme.bringmelk.EditFoodItemActivity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EditFoodItemActivityTest {
    boolean validPrice;
    boolean validDiscount;
    boolean value;
    static EditFoodItemActivity food;



    @Before
    public void createObject(){
        food = new EditFoodItemActivity();
    }

    @Test
    public void valueNotChanged(){
        value = food.isValueChanged("150", "150");
        assertFalse(value);
    }
    @Test
    public void valueChanged(){
        value = food.isValueChanged("150", "300");
        assertTrue(value);
    }

    @Test
    public void isValidDiscount(){
        validDiscount = food.isValidDiscount(20);
        assertTrue(validDiscount);

    }
    @Test
    public void isInvalidDiscount(){
        validDiscount = food.isValidDiscount(-1);
        assertFalse(validDiscount);
    }
    @Test
    public void isInvalidDiscount2(){
        validDiscount = food.isValidDiscount(101);
        assertFalse(validDiscount);
    }

    @Test
    public void isInvalidPrice(){
        validPrice = food.isValidPrice(-2);
        assertFalse(validPrice);
    }
    @Test
    public void isValidPrice(){
        validPrice = food.isValidPrice(500);
        assertTrue(validPrice);
    }

    @After
    public void deleteObject(){
        food = null;
    }


}
