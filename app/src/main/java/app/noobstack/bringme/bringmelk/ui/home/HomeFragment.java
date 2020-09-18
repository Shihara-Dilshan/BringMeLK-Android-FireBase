package app.noobstack.bringme.bringmelk.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import app.noobstack.bringme.bringmelk.R;
import app.noobstack.bringme.bringmelk.model.Data;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewCategory;
    private RecyclerView recyclerViewFood;
    private DatabaseReference CategoryDB;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        CategoryDB = FirebaseDatabase.getInstance().getReference().child("categories");

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

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,CategoryViewHolder>adapter1=new FirebaseRecyclerAdapter<Data, CategoryViewHolder>(Data.class,R.layout.category_data, CategoryViewHolder.class,CategoryDB ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder categoryViewHolder, Data data, int i) {
                categoryViewHolder.setTitle(data.getTitle());
                categoryViewHolder.setDescription(data.getDescription());
                categoryViewHolder.setImage(data.getImage());
            }
        };

        recyclerViewCategory.setAdapter(adapter1);

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
}