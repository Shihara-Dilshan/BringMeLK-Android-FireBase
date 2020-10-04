package app.noobstack.bringme.bringmelk.IT19028606;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import app.noobstack.bringme.bringmelk.R;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
@RunWith(AndroidJUnit4.class)
public class OrderDetailsTest {
    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void user_can_enter_distance() throws Exception{
        onView(withId(R.id.Distance)).perform(typeText("12"));
    }
    @After
    public void tearDown() throws Exception {
    }
}