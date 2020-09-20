package app.noobstack.bringme.bringmelk;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


import java.time.LocalDateTime;
import java.util.UUID;

import app.noobstack.bringme.bringmelk.model.Order;

public class BuyActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView buyImageView;
    private TextView buyTextTitle;
    private TextView buyTextDesc;
    private TextView buyTextPrice;
    private TextView buyTextDiscount;
    private String id;
    private FirebaseUser currentUser;
    private String currentUserId;
    private String currentUserEmail;
    private String imageUrl;
    private String item_name;

    private EditText buyerName;
    private EditText buyerMobile;
    private EditText buyerAddress;
    private int count;
    private double total;
    private double discount;
    private double oneUnitPrice;
    private Button counterTag;
    private Button totalPriceTag;
    private Button incrementBtn, decrementBtn, confirmBtn;

    private DatabaseReference OrderDB;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.increment:
                if (count < 20) {
                    count++;
                    String initialCounterTag = count + " UNITS";
                    counterTag.setText(initialCounterTag);
                    total = (count * oneUnitPrice) - (count * oneUnitPrice * discount / 100.0);

                    String initialTotalTag = "TOTAL PRICE " + total;
                    totalPriceTag.setText(initialTotalTag);
                } else {
                    Toast.makeText(this, "Maximum 20 units can be delivered at a time", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.decrement:
                if (count > 1) {
                    count--;
                    String initialCounterTag = count + " UNITS";
                    counterTag.setText(initialCounterTag);
                    total = (count * oneUnitPrice) - (count * oneUnitPrice * discount / 100.0);

                    String initialTotalTag = "TOTAL PRICE " + total;
                    totalPriceTag.setText(initialTotalTag);
                } else {
                    Toast.makeText(this, "You should order at least 1 unit", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.confirmBtn:
                final String buyer_Name = buyerName.getText().toString().trim();
                final String buyer_Mobile = buyerMobile.getText().toString().trim();
                final String buyer_Address = buyerAddress.getText().toString().trim();
                final String requested_Time = LocalDateTime.now().toString();
                final String image_url = imageUrl;
                final String payment_status = "pending";
                final String user_Id = currentUserId;
                final String driver_Id = "not assigned";
                final String prepared_Time = "not prepared";
                final String delivered_time = "not delivered";
                final String Total_Price = Double.toString(total);
                final String order_Id = UUID.randomUUID().toString();
                final String item_count = Integer.toString(count);
                final String itemName = item_name;

                if (!TextUtils.isEmpty(buyer_Name) && !TextUtils.isEmpty(buyer_Mobile) && !TextUtils.isEmpty(buyer_Address)) {
                    if (buyer_Mobile.length() != 10 || !buyer_Mobile.startsWith("0")) {
                        Toast.makeText(this, "Enter a valid mobile number", Toast.LENGTH_SHORT).show();
                    } else {


                        AlertDialog alertDialog = new AlertDialog.Builder(BuyActivity.this)

                                .setIcon(android.R.drawable.ic_dialog_alert)

                                .setTitle("Place the order?")

                                .setMessage("You will be charged Rs "+ total + " plus additional for the delivery charge")

                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //set what would happen when positive button is clicked
                                        Order newOrder = new Order();
                                        newOrder.setBuyer_Name(buyer_Name);
                                        newOrder.setBuyer_Mobile(buyer_Mobile);
                                        newOrder.setBuyer_Address(buyer_Address);
                                        newOrder.setRequested_Time(requested_Time);
                                        newOrder.setPayment_status(payment_status);
                                        newOrder.setUser_Id(user_Id);
                                        newOrder.setDriver_Id(driver_Id);
                                        newOrder.setPrepared_Time(prepared_Time);
                                        newOrder.setDelivered_time(delivered_time);
                                        newOrder.setTotal_Price(Total_Price);
                                        newOrder.setOrder_Id(order_Id);
                                        newOrder.setImage(image_url);
                                        newOrder.setItem_count(item_count);
                                        newOrder.setItem_name(itemName);

                                        OrderDB.child(order_Id).setValue(newOrder);

                                        Toast.makeText(BuyActivity.this, "Order has been placed", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(BuyActivity.this, MainActivity.class));
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

                } else {
                    Toast.makeText(this, "Fill out the fields", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;


        }
    }

    public BuyActivity() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUserId = currentUser.getUid();
        currentUserEmail = currentUser.getEmail();
        count = 1;
        total = 1.00;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        buyImageView = findViewById(R.id.buy_food_image);
        buyTextTitle = findViewById(R.id.buy_food_title);
        buyTextDesc = findViewById(R.id.buy_food_desc);
        buyTextPrice = findViewById(R.id.buy_food_price);
        buyTextDiscount = findViewById(R.id.buy_food_discount);

        buyerName = findViewById(R.id.buyerName);
        buyerMobile = findViewById(R.id.buyerContactNo);
        buyerAddress = findViewById(R.id.buyerAddress);
        incrementBtn = findViewById(R.id.increment);
        decrementBtn = findViewById(R.id.decrement);
        counterTag = findViewById(R.id.TotalUnitTag);
        totalPriceTag = findViewById(R.id.TotalPriceTag);
        confirmBtn = findViewById(R.id.confirmBtn);

        String initialCounterTag = count + " UNITS";
        counterTag.setText(initialCounterTag);

        incrementBtn.setOnClickListener(this);
        decrementBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);

        Intent intent = getIntent();

        OrderDB = FirebaseDatabase.getInstance().getReference().child("orders");

        String buy_food_title = intent.getStringExtra("ITEM_TITLE").toString();
        String buy_food_desc = intent.getStringExtra("ITEM_DESC").toString();
        String buy_food_price = intent.getStringExtra("ITEM_PRICE").toString();
        String buy_food_discount = intent.getStringExtra("ITEM_DISCOUNT").toString();
        final String buy_food_image = intent.getStringExtra("ITEM_IMAGE").toString();
        id = intent.getStringExtra("ITEM_ID").toString();

        imageUrl = buy_food_image;
        item_name = buy_food_title;

        buyTextTitle.setText(buy_food_title);
        buyTextDesc.setText(buy_food_desc);
        buyTextPrice.setText(buy_food_price);
        buyTextDiscount.setText(buy_food_discount);

        discount = Double.parseDouble(buy_food_discount);
        oneUnitPrice = Double.parseDouble(buy_food_price);

        total = oneUnitPrice - (oneUnitPrice * discount / 100.0);

        String initialTotalTag = "TOTAL PRICE " + total;
        totalPriceTag.setText(initialTotalTag);


        Picasso.get().load(buy_food_image).networkPolicy(NetworkPolicy.OFFLINE).into(buyImageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(buy_food_image).into(buyImageView);
            }
        });


    }
}