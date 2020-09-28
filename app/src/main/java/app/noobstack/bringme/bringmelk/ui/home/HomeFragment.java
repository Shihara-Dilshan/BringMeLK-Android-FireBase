package app.noobstack.bringme.bringmelk.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


import app.noobstack.bringme.bringmelk.AdminDashboard;
import app.noobstack.bringme.bringmelk.R;
import app.noobstack.bringme.bringmelk.model.Data;
import app.noobstack.bringme.bringmelk.model.Food;
import app.noobstack.bringme.bringmelk.BuyActivity;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewCategory;
    private RecyclerView recyclerViewFoodLatest;
    private RecyclerView recyclerViewFoodPrice;
    private RecyclerView recyclerViewFood;
    private DatabaseReference CategoryDB;
    private DatabaseReference FoodDB;
    private FirebaseUser currentUser;
    private Query queryLatest;
    private Query queryPrice;

    private ViewFlipper viewFlipper;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        CategoryDB = FirebaseDatabase.getInstance().getReference().child("categories");
        FoodDB = FirebaseDatabase.getInstance().getReference().child("foods");
        queryLatest = FirebaseDatabase.getInstance().getReference().child("foods").orderByChild("title");
        queryPrice = FirebaseDatabase.getInstance().getReference().child("foods").orderByChild("price");


        recyclerViewCategory = root.findViewById(R.id.categoryRecycle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);


        recyclerViewFood = root.findViewById(R.id.foodRecycle);

        LinearLayoutManager linearLayoutManagerFood = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManagerFood.setReverseLayout(true);
        linearLayoutManagerFood.setStackFromEnd(true);
        recyclerViewFood.setHasFixedSize(true);
        recyclerViewFood.setLayoutManager(linearLayoutManagerFood);

        recyclerViewFoodLatest = root.findViewById(R.id.foodRecycleOrderBy);

        LinearLayoutManager linearLayoutManagerLatest = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManagerLatest.setReverseLayout(true);
        linearLayoutManagerLatest.setStackFromEnd(true);
        recyclerViewFoodLatest.setHasFixedSize(true);
        recyclerViewFoodLatest.setLayoutManager(linearLayoutManagerLatest);

        recyclerViewFoodPrice = root.findViewById(R.id.foodRecycleOrderByPrice);

        LinearLayoutManager linearLayoutManagerPrice = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManagerPrice.setReverseLayout(true);
        linearLayoutManagerPrice.setStackFromEnd(true);
        recyclerViewFoodPrice.setHasFixedSize(true);
        recyclerViewFoodPrice.setLayoutManager(linearLayoutManagerPrice);


        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = currentUser.getUid().toString();
        System.out.println(currentUserId);

        viewFlipper = root.findViewById(R.id.v_flipper);

        int image[] = {R.drawable.imsgeslider1, R.drawable.imsgeslider2, R.drawable.imsgeslider3, R.drawable.imsgeslider4};

        for(int i=0; i<image.length; i++){
            flipperImage(image[i]);
        }

        for(int images: image){
            flipperImage(images);
        }


        if(currentUserId.equals("k3IEFP3100VtJeBD0K2hiUPOwzB3") || currentUserId.equals("ampSUboAV4U3fl5HwhrDZYamTJp1")){
            startActivity(new Intent(getActivity(), AdminDashboard.class));
        }

        return root;
    }

    public void flipperImage(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }

    @Override
    public void onStart() {
        super.onStart();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = currentUser.getUid().toString();
        System.out.println(currentUserId);


        if(currentUserId.equals("k3IEFP3100VtJeBD0K2hiUPOwzB3") || currentUserId.equals("ampSUboAV4U3fl5HwhrDZYamTJp1")){
            startActivity(new Intent(getActivity(), AdminDashboard.class));
        }

        FirebaseRecyclerAdapter<Data,CategoryViewHolder>adapter1=new FirebaseRecyclerAdapter<Data, CategoryViewHolder>(Data.class,R.layout.category_data, CategoryViewHolder.class,CategoryDB ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder categoryViewHolder, Data data, int i) {
                categoryViewHolder.setTitle(data.getTitle());
                categoryViewHolder.setDescription(data.getDescription());
                categoryViewHolder.setImage(data.getImage());
            }
        };

        recyclerViewCategory.setAdapter(adapter1);

        FirebaseRecyclerAdapter<Food,FoodViewHolder>adapter2=new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.food_data, FoodViewHolder.class,FoodDB ) {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, final Food food, int i) {
                foodViewHolder.setTitle(food.getTitle());
                foodViewHolder.setDescription(food.getDescription());
                foodViewHolder.setImage(food.getImage());
                foodViewHolder.setPrice(food.getPrice());
                foodViewHolder.setDiscount(food.getDiscount());

                foodViewHolder.FoodView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), BuyActivity.class);
                        intent.putExtra("ITEM_ID", food.getId());
                        intent.putExtra("ITEM_TITLE", food.getTitle());
                        intent.putExtra("ITEM_DESC", food.getDescription());
                        intent.putExtra("ITEM_IMAGE", food.getImage());
                        intent.putExtra("ITEM_PRICE", food.getPrice());
                        intent.putExtra("ITEM_DISCOUNT", food.getDiscount());

                        startActivity(intent);
                    }
                });
            }
        };

        recyclerViewFood.setAdapter(adapter2);


        FirebaseRecyclerAdapter<Food,FoodViewHolder>adapter3=new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.food_data, FoodViewHolder.class,queryLatest ) {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, final Food food, int i) {
                foodViewHolder.setTitle(food.getTitle());
                foodViewHolder.setDescription(food.getDescription());
                foodViewHolder.setImage(food.getImage());
                foodViewHolder.setPrice(food.getPrice());
                foodViewHolder.setDiscount(food.getDiscount());

                foodViewHolder.FoodView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), BuyActivity.class);
                        intent.putExtra("ITEM_ID", food.getId());
                        intent.putExtra("ITEM_TITLE", food.getTitle());
                        intent.putExtra("ITEM_DESC", food.getDescription());
                        intent.putExtra("ITEM_IMAGE", food.getImage());
                        intent.putExtra("ITEM_PRICE", food.getPrice());
                        intent.putExtra("ITEM_DISCOUNT", food.getDiscount());

                        startActivity(intent);
                    }
                });
            }
        };

        recyclerViewFoodLatest.setAdapter(adapter3);

        FirebaseRecyclerAdapter<Food,FoodViewHolder>adapter4=new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.food_data, FoodViewHolder.class,queryPrice ) {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, final Food food, int i) {
                foodViewHolder.setTitle(food.getTitle());
                foodViewHolder.setDescription(food.getDescription());
                foodViewHolder.setImage(food.getImage());
                foodViewHolder.setPrice(food.getPrice());
                foodViewHolder.setDiscount(food.getDiscount());

                foodViewHolder.FoodView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), BuyActivity.class);
                        intent.putExtra("ITEM_ID", food.getId());
                        intent.putExtra("ITEM_TITLE", food.getTitle());
                        intent.putExtra("ITEM_DESC", food.getDescription());
                        intent.putExtra("ITEM_IMAGE", food.getImage());
                        intent.putExtra("ITEM_PRICE", food.getPrice());
                        intent.putExtra("ITEM_DISCOUNT", food.getDiscount());

                        startActivity(intent);
                    }
                });
            }
        };

        recyclerViewFoodPrice.setAdapter(adapter4);



    }

    @Override
    public void onResume() {
        super.onResume();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = currentUser.getUid().toString();
        System.out.println(currentUserId);


        if(currentUserId.equals("k3IEFP3100VtJeBD0K2hiUPOwzB3") || currentUserId.equals("ampSUboAV4U3fl5HwhrDZYamTJp1")){
            startActivity(new Intent(getActivity(), AdminDashboard.class));
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        View myview;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.myview = itemView;
        }

        public void setTitle(String title){
            TextView categoryTitle = myview.findViewById(R.id.category_title);
            categoryTitle.setText(title);
        }

        public void setDescription(String description){
            TextView categoryDesc = myview.findViewById(R.id.category_desc);
            categoryDesc.setText(description);
        }

        public void setImage(final String image){
            final ImageView categoryImage = myview.findViewById(R.id.category_image);

            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(categoryImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).into(categoryImage);
                }
            });
        }
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder{
        View FoodView;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            this.FoodView = itemView;
        }

        public void setTitle(String title){
            TextView foodTitle = FoodView.findViewById(R.id.food_title);
            foodTitle.setText(title);
        }

        public void setPrice(String price){
            TextView foodPrice = FoodView.findViewById(R.id.food_price);
            foodPrice.setText(price);
        }

        public void setDiscount(String discount){
            TextView foodDiscount = FoodView.findViewById(R.id.food_discount);
            foodDiscount.setText(discount);
        }

        public void setDescription(String description){
            TextView foodDesc = FoodView.findViewById(R.id.food_desc);
            foodDesc.setText(description);
        }

        public void setImage(final String image){
            final ImageView foodImage = FoodView.findViewById(R.id.food_image);

            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(foodImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).into(foodImage);
                }
            });
        }
    }
}