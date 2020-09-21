package app.noobstack.bringme.bringmelk.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import app.noobstack.bringme.bringmelk.R;

public class GetOrder extends AppCompatActivity {
    private DatabaseReference OrdersDB;
    private String Orderid;
    private String buyerName;
    private String buyerAddress;
    private String buyerMobile;
    private String itemname;
    private TextView buyer_Address;
    private TextView item_name;
    private TextView buyer_Mobile;
    private TextView buyer_Name;
    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private String currentUserId = currentUser.getUid();
    private Button GetOrderButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_order);
        OrdersDB = FirebaseDatabase.getInstance().getReference().child("orders");
        buyer_Name =findViewById(R.id.CusName);
        buyer_Address=findViewById(R.id.address);
        item_name=findViewById(R.id.ItemName);
        buyer_Mobile=findViewById(R.id.CusPhn);
        GetOrderButton=findViewById(R.id.getorder);

        Intent intent = getIntent();
        String buyer_Address_ = intent.getStringExtra("buyer_Address").toString();
        String item_name_ = intent.getStringExtra("item_name").toString();
        String buyer_Mobile_ = intent.getStringExtra("buyer_Mobile").toString();
        String buyer_Name_ = intent.getStringExtra("buyer_Name").toString();
        Orderid = intent.getStringExtra("order_Id").toString();

        buyer_Name.setText(buyer_Name_);
        buyer_Address.setText(buyer_Address_);
        item_name.setText(item_name_);
        buyer_Mobile.setText(buyer_Mobile_);

        GetOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("driver_Id",currentUserId);
                hashMap.put("delivered_time","Driver Assigned");
                OrdersDB.child(Orderid).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(GetOrder.this, "Assigned", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}