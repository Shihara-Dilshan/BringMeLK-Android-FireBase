package app.noobstack.bringme.bringmelk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class LoadingActivity extends AppCompatActivity {
    private static  int SPLASH_TIME_OUT = 2000;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        progressBar = findViewById(R.id.progressLoad);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<10; i++){
                    progressBar.incrementProgressBy(10);
                    SystemClock.sleep(500);
                }
            }
        });

        thread.start();

        //hide the top title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startIntent = new Intent(LoadingActivity.this, startPage.class);
                startActivity(startIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}