package app.noobstack.bringme.bringmelk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class BuyActivity extends AppCompatActivity{

    private ImageView buyImageView;
    private TextView buyTextTitle;
    private TextView buyTextDesc;
    private TextView buyTextPrice;
    private TextView buyTextDiscount;
    private String id;
    private FirebaseUser currentUser;
    private String currentUserId;
    private String currentUserEmail;

    public BuyActivity() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUserId = currentUser.getUid();
        currentUserEmail = currentUser.getEmail();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        buyImageView = findViewById(R.id.buy_food_image);
        buyTextTitle = findViewById(R.id.buy_food_title);
        buyTextDesc = findViewById(R.id.buy_food_desc);
        buyTextPrice = findViewById(R.id.buy_food_price);
        buyTextDiscount = findViewById(R.id.buy_food_discount);

        Intent intent =getIntent();

        String buy_food_title = intent.getStringExtra("ITEM_TITLE").toString();
        String buy_food_desc = intent.getStringExtra("ITEM_DESC").toString();
        String buy_food_price = intent.getStringExtra("ITEM_PRICE").toString();
        String buy_food_discount = intent.getStringExtra("ITEM_DISCOUNT").toString();
        final String buy_food_image = intent.getStringExtra("ITEM_IMAGE").toString();
        id = intent.getStringExtra("ITEM_ID").toString();

        buyTextTitle.setText(buy_food_title);
        buyTextDesc.setText(buy_food_desc);
        buyTextPrice.setText(buy_food_price);
        buyTextDiscount.setText(buy_food_discount);

        Picasso.get().load(buy_food_image).networkPolicy(NetworkPolicy.OFFLINE).into(buyImageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(buy_food_image).into(buyImageView);
            }
        });

        Toast.makeText(this, currentUserId, Toast.LENGTH_SHORT).show();

    }
}