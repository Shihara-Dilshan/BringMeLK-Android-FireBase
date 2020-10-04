package app.noobstack.bringme.bringmelk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import app.noobstack.bringme.bringmelk.model.Food;

public class EditFoodItemActivity extends AppCompatActivity {

    private TextView itemName;
    private EditText itemprice;
    private EditText itemDiscount;
    private EditText itemDescription;
    private ImageView itemImage;
    private Button update, cancel;
    private DatabaseReference dbref;
//    ProgressDialog progressDialog;
    private String item;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_item);

        itemName = findViewById(R.id.tvitemName);
        itemprice = findViewById(R.id.itemPrice_et);
        itemDescription= findViewById(R.id.desc_et);
        itemDiscount= findViewById(R.id.ItemDiscount_et);
        itemImage = findViewById(R.id.foodimg);

        update= findViewById(R.id.btnUpdate);
        cancel = findViewById(R.id.btnCancel);

        //getting vlues of the item through intent
        final Intent intent = getIntent();
        item = intent.getStringExtra("item");


        dbref = FirebaseDatabase.getInstance().getReference().child("foods").child(item);

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    itemName.setText(snapshot.child("title").getValue().toString());
                    itemprice.setText(snapshot.child("price").getValue().toString());
                    itemDescription.setText(snapshot.child("description").getValue().toString());
                    itemDiscount.setText(snapshot.child("discount").getValue().toString());
                    Picasso.get().load(snapshot.child("image").getValue().toString()).into(itemImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            String price = snapshot.child("price").getValue().toString();
                            String description = snapshot.child("description").getValue().toString();
                            String discount = snapshot.child("discount").getValue().toString();

                            if(TextUtils.isEmpty(itemDescription.getText().toString())){
                                Toast.makeText(EditFoodItemActivity.this, "Please Enter Description", Toast.LENGTH_SHORT ).show();
                            }
                            else if(TextUtils.isEmpty(itemprice.getText().toString())){
                                Toast.makeText(EditFoodItemActivity.this, "Please Enter a Price", Toast.LENGTH_SHORT ).show();
                            }
                            else if(TextUtils.isEmpty(itemDiscount.getText().toString())){
                                Toast.makeText(EditFoodItemActivity.this, "Please Enter Discount", Toast.LENGTH_SHORT ).show();
                            }
                            else {

                                if (!isValidDiscount(Float.parseFloat(itemDiscount.getText().toString()))) {
                                    Toast.makeText(EditFoodItemActivity.this, "Please Enter a Discount between 0 and 100", Toast.LENGTH_SHORT).show();
                                }
                                else if(!isValidPrice(Float.parseFloat(itemprice.getText().toString()))){
                                    Toast.makeText(EditFoodItemActivity.this, "Please Enter a price greater than 0", Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    if(isValueChanged(discount, itemDiscount.getText().toString())){
                                        dbref.child("discount").setValue(itemDiscount.getText().toString());
                                    }
                                    if (isValueChanged(description, itemDescription.getText().toString())){
                                        dbref.child("description").setValue(itemDescription.getText().toString());
                                    }
                                    if (isValueChanged(price,itemprice.getText().toString())){
                                        dbref.child("price").setValue(itemprice.getText().toString());
                                    }
                                    Intent i = new Intent(EditFoodItemActivity.this, ManageFoodActivity.class);
                                    startActivity(i);
                                }
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditFoodItemActivity.this, ManageFoodActivity.class);
                startActivity(i);
            }
        });


    }
    public boolean isValidDiscount(float discount){

        if(discount>=0 & discount <=100)
            return true;
        else
            return false;

    }
    public boolean isValidPrice(float price){
        if(price<0)
            return false;
        else
            return true;
    }
    public boolean isValueChanged(String oldValue,String newValue){
        if(oldValue.equals(newValue))
            return false;
        else
            return true;
    }

}