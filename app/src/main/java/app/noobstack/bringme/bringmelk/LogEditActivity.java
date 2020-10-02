package app.noobstack.bringme.bringmelk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.noobstack.bringme.bringmelk.model.Log;
import app.noobstack.bringme.bringmelk.model.Order;
import app.noobstack.bringme.bringmelk.ui.slideshow.SlideshowFragment;

public class LogEditActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference logDBReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_edit);

        recyclerView = findViewById(R.id.logsRecycleView);

        logDBReference = FirebaseDatabase.getInstance().getReference().child("Logs");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LogEditActivity.this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Log, LogEditActivity.LogViewHolder> adapter1 = new FirebaseRecyclerAdapter<Log, LogEditActivity.LogViewHolder>(Log.class, R.layout.log_data, LogEditActivity.LogViewHolder.class, logDBReference) {
            @Override
            protected void populateViewHolder(LogEditActivity.LogViewHolder orderViewHolder, final Log log, int i) {
                orderViewHolder.setLogDate(log.getLogDate());
                orderViewHolder.setPrice(log.getPrice());
            }

        };

        recyclerView.setAdapter(adapter1);

    }

    public void clearLog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(LogEditActivity.this)

                .setIcon(android.R.drawable.ic_dialog_alert)

                .setTitle("Delete all logs?")

                .setMessage("Are you sure want to delete all the logs?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        logDBReference.removeValue();
                        Toast.makeText(LogEditActivity.this, "Logs have been cleared", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LogEditActivity.this, AdminDashboard.class));
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

    public static class LogViewHolder extends RecyclerView.ViewHolder {
        View myview;
        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            this.myview = itemView;
        }

        public void setLogDate(String date) {
            TextView Order_item_date = myview.findViewById(R.id.log_date);
            Order_item_date.setText(date);
        }

        public void setPrice(String price) {
            TextView Order_item_price = myview.findViewById(R.id.total_income);
            Order_item_price.setText(price);
        }
    }
}