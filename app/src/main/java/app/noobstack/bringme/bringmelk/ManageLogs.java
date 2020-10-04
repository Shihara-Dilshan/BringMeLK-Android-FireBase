package app.noobstack.bringme.bringmelk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import app.noobstack.bringme.bringmelk.model.Log;
import app.noobstack.bringme.bringmelk.model.Order;

public class ManageLogs extends AppCompatActivity {

    private TextView textView;
    private DatabaseReference databaseReference;
    private DatabaseReference completedOrdersDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_logs);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Logs");
        completedOrdersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("completedOrders");

        textView = findViewById(R.id.totalTag);
        textView.setText(getIntent().getStringExtra("TOTAL"));

    }

    public void addToLog(View view) {
        final UUID randomUUID = UUID.randomUUID();
        final Log log = new Log();
        log.setLogId(randomUUID.toString());
        log.setPrice(getIntent().getStringExtra("TOTAL"));
        log.setLogDate(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));

        AlertDialog alertDialog = new AlertDialog.Builder(ManageLogs.this)

                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Add new log?")
                .setMessage("This will delete older order data and add a new log data instead")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        databaseReference.child(randomUUID.toString()).setValue(log);
                        completedOrdersDatabaseReference.removeValue();
                        Toast.makeText(ManageLogs.this, "Log has been added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ManageLogs.this, AdminDashboard.class));
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked

                    }
                })
                .show();



    }

    public static String createDate(){
        String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return date;
    }

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}