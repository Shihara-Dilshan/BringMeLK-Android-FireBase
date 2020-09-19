package app.noobstack.bringme.bringmelk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class startPage extends AppCompatActivity {

    private Button button;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private EditText emailField;
    private EditText passwordField;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide the top title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_start_page);

        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.editTextTextPassword2);

        mAuth = FirebaseAuth.getInstance();

        button = findViewById(R.id.loginBtn);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(startPage.this, MainActivity.class));
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        startPage.this.unregisterReceiver(mMessageReceiver);
    }

    public void unregisterReceiver(BroadcastReceiver mMessageReceiver) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        startPage.this.registerReceiver(mMessageReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Device battery is low", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthStateListener);
    }

    public void startSignIn() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Please fill out the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(startPage.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }

    public void adminLogin(View view) {
        Intent intent = new Intent(startPage.this, AdminLogin.class);
        startActivity(intent);
    }

    public void signUpPage(View view) {
        startActivity(new Intent(startPage.this, SignupActivity.class));
    }
}