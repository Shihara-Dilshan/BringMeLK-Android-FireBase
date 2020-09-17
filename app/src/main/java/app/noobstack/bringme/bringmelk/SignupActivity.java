package app.noobstack.bringme.bringmelk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText Email;
    private EditText password;
    private EditText Cpassword;
    private Button button;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //hide the top title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        Email = findViewById(R.id.signupemailField);
        password = findViewById(R.id.editSignupTextTextPassword2);
        Cpassword = findViewById(R.id.editTextTextConfirmPassword2);
        button = findViewById(R.id.signUpBtnAdd);

        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignUp();
            }
        });

    }


    public void startSignUp() {
        String newEmail = this.Email.getText().toString();
        String newPassword = this.password.getText().toString();
        String newCPassword = this.Cpassword.getText().toString();

        if (newEmail.equals("") || newPassword.equals("") || newCPassword.equals("")) {
            Toast.makeText(this, "Please fill out the fields Correctly", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(newEmail, newPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });



    }
}