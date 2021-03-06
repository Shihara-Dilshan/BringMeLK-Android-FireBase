package app.noobstack.bringme.bringmelk.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import app.noobstack.bringme.bringmelk.AdminDashboard;
import app.noobstack.bringme.bringmelk.R;
import app.noobstack.bringme.bringmelk.model.CompletedOrders;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class OrderDetails extends AppCompatActivity {
    private static String OrderId;
    private String buyerName;
    private String buyerAddress;
    private String mobile;
    private String itemname;
    private String itemprice;
    private String quantity;
    private TextView buyer__Address;
    private TextView item__name;
    private TextView buyer__Mobile;
    private TextView buyer__Name;
    private TextView Quantity;
    private TextView Item__Price;
    private static TextView Dcalculation;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
    private static DatabaseReference orderdetailsDB;
    private DatabaseReference CompleteOrder;
    private EditText distance;
    private Button calculate;
    private Button BtnOrderComplete;
    private Button BtnRemoveDriver;
    private static String Fcharge;
    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private String currentUserId = currentUser.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        orderdetailsDB = FirebaseDatabase.getInstance().getReference().child("orders");
        CompleteOrder = FirebaseDatabase.getInstance().getReference().child("completedOrders");
        button1 = findViewById(R.id.radio_button_1);
        button2 = findViewById(R.id.radio_button_2);
        button3 = findViewById(R.id.radio_button_3);
        button4 = findViewById(R.id.radio_button_4);
        BtnRemoveDriver = findViewById(R.id.RemoveDriver);
        distance = findViewById(R.id.Distance);
        calculate = findViewById(R.id.Calculate);
        BtnOrderComplete = findViewById(R.id.BtnCompelete);
        buyer__Name = findViewById(R.id.Cusname);
        buyer__Address = findViewById(R.id.Address);
        item__name = findViewById(R.id.Itemname);
        buyer__Mobile = findViewById(R.id.Cusphn);
        Quantity = findViewById(R.id.Quantity);
        Item__Price = findViewById(R.id.Itemprice);
        Dcalculation = findViewById(R.id.calculation);
        Intent intent = getIntent();
        buyerAddress = intent.getStringExtra("buyerAddress").toString();
        buyerName = intent.getStringExtra("buyerName").toString();
        mobile = intent.getStringExtra("buyerMobile").toString();
        quantity = intent.getStringExtra("quantity").toString();
        itemprice = intent.getStringExtra("ItemPrice").toString();
        itemname = intent.getStringExtra("itemname").toString();
        OrderId = intent.getStringExtra("orderId").toString();
        buyer__Name.setText(buyerName);
        buyer__Address.setText(buyerAddress);
        item__name.setText(itemname);
        buyer__Mobile.setText(mobile);
        Quantity.setText(quantity);
        Item__Price.setText(itemprice);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("delivered_time", "Food is making");
                orderdetailsDB.child(OrderId).updateChildren(hashMap);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("delivered_time", "driver pickd up");
                hashMap.put("prepared_Time", "prepared");
                orderdetailsDB.child(OrderId).updateChildren(hashMap);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("delivered_time", "On the location");
                orderdetailsDB.child(OrderId).updateChildren(hashMap);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnOrderComplete.setVisibility(View.VISIBLE);
                HashMap hashMap = new HashMap();
                hashMap.put("delivered_time", "Delivered");
                orderdetailsDB.child(OrderId).updateChildren(hashMap);
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnRemoveDriver.setVisibility(View.INVISIBLE);
                String VDistance = distance.getText().toString();
                Boolean ValidDistance = checkNumbers(VDistance);
                Boolean CheackLenZero = checkLength(VDistance);
                Boolean CheckLen=checkValueZero(VDistance);
                if (ValidDistance == FALSE) {
                    Toast.makeText(OrderDetails.this, "Please enter Valid Distance", Toast.LENGTH_SHORT).show();
                } else if (CheackLenZero == TRUE) {
                    Toast.makeText(OrderDetails.this, "Please enter Distance", Toast.LENGTH_SHORT).show();
                } else if(CheckLen==TRUE){
                    Toast.makeText(OrderDetails.this, "Please enter distance grater than zero ", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (distance.getText().toString().length() == 0) {
                        Toast.makeText(OrderDetails.this, "Please enter distance", Toast.LENGTH_SHORT).show();
                    }
                    double Distance = Double.parseDouble(distance.getText().toString());
                    double fchargeINT = calculateDcharge(Distance);
                    String fchargeStr = String.valueOf(fchargeINT);
                    Dcalculation.setText("RS :" + fchargeStr + "/=");
                    HashMap hashMap = new HashMap();
                    hashMap.put("deliverCharge", fchargeStr);
                    orderdetailsDB.child(OrderId).updateChildren(hashMap);
                }
            }
        });
        BtnOrderComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCompleteOrder();
                HashMap hashMap = new HashMap();
                hashMap.put("delivered_time", "delivered");
                hashMap.put("payment_status", "paid");
                orderdetailsDB.child(OrderId).updateChildren(hashMap);
                startActivity(new Intent(OrderDetails.this, AdminDashboard.class));
            }
        });
        BtnRemoveDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("delivered_time", "not delivered");
                hashMap.put("driver_Id", "not Assigned");
                orderdetailsDB.child(OrderId).updateChildren(hashMap);
                Toast.makeText(OrderDetails.this, "You removed from the order", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OrderDetails.this, AdminDashboard.class));
            }
        });
    }

    public static double calculateDcharge(double Distance) {
        double chargers = 50;
        double DeliveryChargers = chargers * Distance;
        return DeliveryChargers;
    }

    public void addCompleteOrder() {
        String id = CompleteOrder.push().getKey();
        CompletedOrders completedOrders = new CompletedOrders(buyerAddress, Fcharge, itemname, itemprice, mobile, quantity, buyerName, currentUserId);
        CompleteOrder.child(id).setValue(completedOrders);
        Toast.makeText(this, "Order Completed", Toast.LENGTH_SHORT).show();
    }

    public static Boolean checkNumbers(String VVDistance) {
        for (char i : VVDistance.toCharArray()) {
            if (i == '0' || i == '1' || i == '2' || i == '3' || i == '4' || i == '5' || i == '6' || i == '7' || i == '8' || i == '9' || i == '.') {
                //System.out.println("Valid");
            } else {
                return FALSE;
            }
        }
        return TRUE;
    }
    public static Boolean checkLength(String Zdistance) {
        char[] zdistance = Zdistance.toCharArray();
        if (zdistance.length == 0) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
    public static Boolean checkValueZero(String ZDistance) {
        for (char i : ZDistance.toCharArray()) {
            if (i == '0') {
                //System.out.println("Valid");
            } else {
                return FALSE;
            }

        }
        return TRUE;
    }
}
