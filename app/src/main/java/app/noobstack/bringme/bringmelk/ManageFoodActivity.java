package app.noobstack.bringme.bringmelk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import app.noobstack.bringme.bringmelk.model.Food;

public class ManageFoodActivity extends AppCompatActivity {

    private RecyclerView foodItemList;
    private DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_food);
        dbref = FirebaseDatabase.getInstance().getReference().child("foods");

        foodItemList = findViewById(R.id.foodList);
        foodItemList.setHasFixedSize(true);
        foodItemList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Food, FoodItemViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Food, FoodItemViewHolder>(
                Food.class,
                R.layout.food_item,
                FoodItemViewHolder.class,
                dbref
        ) {
            @Override
            protected void populateViewHolder(FoodItemViewHolder foodItemViewHolder, final Food food, final int i) {
                foodItemViewHolder.setItemName(food.getTitle());
                foodItemViewHolder.setIDescription(food.getDescription());
                foodItemViewHolder.setIPrice(food.getPrice());
                foodItemViewHolder.setIDiscount(food.getDiscount());
                foodItemViewHolder.setiImage(food.getImage());
                //final String postkey = getRef(i).toString();
                final DatabaseReference itemref = getRef(i);
                final String itemkey = itemref.getKey();




                foodItemViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String foodName = food.getTitle();
                        AlertDialog.Builder builder = new AlertDialog.Builder(ManageFoodActivity.this);
                        builder.setTitle("Item Delete Alert")
                                .setMessage("Are you sure, you want to remove "+foodName+" from the food list")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        itemref.addListenerForSingleValueEvent(new ValueEventListener() {

                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                itemref.removeValue();
                                                Toast.makeText(ManageFoodActivity.this,foodName + " has been removed from the food list",Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(ManageFoodActivity.this,"The item was not deleted",Toast.LENGTH_SHORT).show();
                                    }
                                });
                        //Creating dialog box
                        AlertDialog dialog  = builder.create();
                        dialog.show();



                    }
                });

                foodItemViewHolder.editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(ManageFoodActivity.this, EditFoodItemActivity.class);
                        i.putExtra("item", itemkey);
                        startActivity(i);

                    }
                });

            }
        };
        foodItemList.setAdapter(firebaseRecyclerAdapter);
    }
    public void addFoodItem(View view){
        Intent intent = new Intent (ManageFoodActivity.this, AddNewFood.class);
        startActivity(intent);
    }

    public void editFoodItem(View view){
        Intent intent = new Intent (ManageFoodActivity.this, EditFoodItemActivity.class);
        startActivity(intent);
    }
    public static class FoodItemViewHolder extends RecyclerView.ViewHolder{

        View view;
        Button editBtn;
        Button deleteButton;


        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            deleteButton = view.findViewById(R.id.btnDeleteItem);
            editBtn = view.findViewById(R.id.btnEditItem);
        }
        public void setItemName(String name){
            TextView iname = view.findViewById(R.id.tvItemName);
            iname.setText(name);
        }
        public void setIDescription(String description){
            TextView iDes = view.findViewById(R.id.tv_itemDes);
            iDes.setText(description);
        }
        public void setIPrice(String price){
            TextView iprice = view.findViewById(R.id.tvitemprice);
            iprice.setText(price);
        }
        public void setIDiscount(String discount){
            TextView idis = view.findViewById(R.id.tvitemDiscount);
            idis.setText(discount);
        }
        public void setiImage( String image){
            ImageView iImage = view.findViewById(R.id.itemImage);
            Picasso.get().load(image).into(iImage);


        }
    }
}