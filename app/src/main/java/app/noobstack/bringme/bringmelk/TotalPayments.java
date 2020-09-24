package app.noobstack.bringme.bringmelk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_payments);

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

        FirebaseRecyclerAdapter<CompletedOrders, TotalPayments.TotalOrdersViewHolder> adapter1 = new FirebaseRecyclerAdapter<CompletedOrders, TotalPayments.TotalOrdersViewHolder>(CompletedOrders.class, R.layout.completed_payments_data, TotalPayments.TotalOrdersViewHolder.class, CompletedOrdersDB) {
            @Override
            protected void populateViewHolder(TotalOrdersViewHolder totalOrdersViewHolder, CompletedOrders completedOrders, int i) {
                totalOrdersViewHolder.setBuyerName(completedOrders.getBuyerName());
                totalOrdersViewHolder.setItemName(completedOrders.getItemName());
                totalOrdersViewHolder.setItemPrice(completedOrders.getItemPrice());
            }
        };

        recyclerViewTPayments.setAdapter(adapter1);
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
}