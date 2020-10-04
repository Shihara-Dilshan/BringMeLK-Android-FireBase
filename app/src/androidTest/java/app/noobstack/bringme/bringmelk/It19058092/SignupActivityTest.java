package app.noobstack.bringme.bringmelk.It19058092;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import app.noobstack.bringme.bringmelk.BuyActivity;
import app.noobstack.bringme.bringmelk.R;
import app.noobstack.bringme.bringmelk.SignupActivity;

import static org.junit.Assert.*;

public class SignupActivityTest {

    @Rule
    public ActivityTestRule<SignupActivity> signupActivityActivityTestRule = new ActivityTestRule<>(SignupActivity.class);

    private SignupActivity signupActivity = null;
    @Before
    public void setUp() throws Exception {
        signupActivity = signupActivityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        signupActivity = null;
    }

    @Test
    public void testUi() {
        View emailField = signupActivity.findViewById(R.id.signupemailField);
        View password = signupActivity.findViewById(R.id.editSignupTextTextPassword2);
        View cpassword = signupActivity.findViewById(R.id.editTextTextConfirmPassword2);

        assertNotNull(emailField);
        assertNotNull(password);
        assertNotNull(cpassword);
    }


}
