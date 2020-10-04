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

import app.noobstack.bringme.bringmelk.model.CompletedOrders;
import app.noobstack.bringme.bringmelk.model.Order;
import app.noobstack.bringme.bringmelk.ui.slideshow.SlideshowFragment;

public class TotalPayments extends AppCompatActivity {

    private RecyclerView recyclerViewTPayments;
    private DatabaseReference CompletedOrdersDB;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_payments);

        totalPrice = 0;

        recyclerViewTPayments = findViewById(R.id.TotalPaymentRecycle);
        CompletedOrdersDB = FirebaseDatabase.getInstance().getReference().child("completedOrders");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TotalPayments.this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewTPayments.setHasFixedSize(true);
        recyclerViewTPayments.setLayoutManager(linearLayoutManager);



    }

    @Override
    protected void onStart() {
        super.onStart();

        totalPrice = 0;

        FirebaseRecyclerAdapter<CompletedOrders, TotalPayments.TotalOrdersViewHolder> adapter1 = new FirebaseRecyclerAdapter<CompletedOrders, TotalPayments.TotalOrdersViewHolder>(CompletedOrders.class, R.layout.completed_payments_data, TotalPayments.TotalOrdersViewHolder.class, CompletedOrdersDB) {
            @Override
            protected void populateViewHolder(TotalOrdersViewHolder totalOrdersViewHolder, CompletedOrders completedOrders, int i) {
                totalOrdersViewHolder.setBuyerName(completedOrders.getBuyerName());
                totalOrdersViewHolder.setItemName(completedOrders.getItemName());
                totalOrdersViewHolder.setItemPrice(completedOrders.getItemPrice());
                //totalPrice += Double.parseDouble(completedOrders.getItemPrice());
                calTotal(Double.parseDouble(completedOrders.getItemPrice()));
            }
        };

        recyclerViewTPayments.setAdapter(adapter1);
    }

    public void calTotals(View view) {
        if(totalPrice > 0){
            Intent intent = new Intent(TotalPayments.this, ManageLogs.class);
            intent.putExtra("TOTAL", Double.toString(totalPrice));
            Toast.makeText(this, Double.toString(totalPrice), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else{
            Toast.makeText(this, "There are no any completed orders", Toast.LENGTH_SHORT).show();
        }

    }

    public void goback(View view) {
        startActivity(new Intent(TotalPayments.this, AdminDashboard.class));
    }

    public static class TotalOrdersViewHolder extends RecyclerView.ViewHolder {

        View myview;

        public TotalOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            this.myview = itemView;
        }

        public void setBuyerName(String BName) {
            TextView BuyerName = myview.findViewById(R.id.completed_order_bName);
            BuyerName.setText(BName);
        }

        public void setItemName(String IName) {
            TextView BuyerItem = myview.findViewById(R.id.completed_order_itemName);
            BuyerItem.setText(IName);
        }

        public void setItemPrice(String IPrice) {
            TextView BuyerItemPrice = myview.findViewById(R.id.completed_order_Tprice);
            BuyerItemPrice.setText(IPrice);
        }
    }

    public Double calTotal(Double input){
        totalPrice += input;
        return  totalPrice;
    }


    public static Double convertTODouble(String input){
        return Double.parseDouble(input);
    }

    public static String  convertTOString(Double input){
        return Double.toString(input);
    }
}