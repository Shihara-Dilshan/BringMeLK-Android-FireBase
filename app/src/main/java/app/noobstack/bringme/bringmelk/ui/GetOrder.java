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
    private String Buyer_Address_;
    private String Item_name_;
    private String Buyer_Mobile_;
    private String Buyer_Name_;
    private String Item_Price_;
    private String Quantity;
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
        Item_Price_= intent.getStringExtra("Item_Price").toString();
        Quantity=intent.getStringExtra("Quantity").toString();
        Orderid = intent.getStringExtra("order_Id").toString();

        buyer_Name.setText(buyer_Name_);
        buyer_Address.setText(buyer_Address_);
        item_name.setText(item_name_);
        buyer_Mobile.setText(buyer_Mobile_);

        Buyer_Address_=buyer_Address_;
        Item_name_=item_name_;
        Buyer_Mobile_=buyer_Mobile_;
        Buyer_Name_=buyer_Name_;


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
                openOrderDetails();
            }

        });
    }
    public void  openOrderDetails(){
        Intent intent= new Intent(this,OrderDetails.class);
        intent.putExtra("orderId",Orderid);
        intent.putExtra("buyerAddress",Buyer_Address_);
        intent.putExtra("itemname",Item_name_);
        intent.putExtra("buyerMobile",Buyer_Mobile_);
        intent.putExtra("buyerName",Buyer_Name_);
        intent.putExtra("ItemPrice" ,Item_Price_);
        intent.putExtra("quantity" ,Quantity);
        startActivity(intent);


    }

}