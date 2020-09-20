package app.noobstack.bringme.bringmelk.ui.slideshow;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import app.noobstack.bringme.bringmelk.R;
import app.noobstack.bringme.bringmelk.model.Data;
import app.noobstack.bringme.bringmelk.model.Order;
import app.noobstack.bringme.bringmelk.ui.home.HomeFragment;

public class SlideshowFragment extends Fragment {


    private RecyclerView recyclerViewOrders;
    private DatabaseReference OrderDB;
    private Query userOrders;
    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private String currentUserId = currentUser.getUid();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        OrderDB = FirebaseDatabase.getInstance().getReference().child("orders");
        userOrders = OrderDB.orderByChild("user_Id").equalTo(currentUserId);

        recyclerViewOrders = root.findViewById(R.id.categoryRecycle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewOrders.setHasFixedSize(true);
        recyclerViewOrders.setLayoutManager(linearLayoutManager);



        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Order, SlideshowFragment.OrderViewHolder> adapter1=new FirebaseRecyclerAdapter<Order, SlideshowFragment.OrderViewHolder>(Order.class,R.layout.order_data, SlideshowFragment.OrderViewHolder.class,userOrders ) {
            @Override
            protected void populateViewHolder(SlideshowFragment.OrderViewHolder orderViewHolder, Order order, int i) {
                orderViewHolder.setImage(order.getImage());
                orderViewHolder.setItem_name(order.getItem_name());
                orderViewHolder.setOrder_Id(order.getOrder_Id());
                orderViewHolder.setTotal_Price(order.getTotal_Price());
                orderViewHolder.setRequested_Time(order.getRequested_Time());
                orderViewHolder.setItem_count(order.getItem_count());
                orderViewHolder.setPrepared_Time(order.getPrepared_Time());
                orderViewHolder.setDelivered_time(order.getDelivered_time());
                orderViewHolder.setPayment_status(order.getPayment_status());
            }
        };

        recyclerViewOrders.setAdapter(adapter1);

    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        View myview;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.myview = itemView;
        }

        public void setItem_name(String title){
            TextView Order_item_Title = myview.findViewById(R.id.order_item_title);
            Order_item_Title.setText(title);
        }

        public void setRequested_Time(String requested_time){
            TextView Order_item_requested_time = myview.findViewById(R.id.order_item_request_date);
            Order_item_requested_time.setText(requested_time);
        }

        public void setTotal_Price(String total_price){
            TextView order_iem_total_price = myview.findViewById(R.id.order_item_ptice);
            order_iem_total_price.setText(total_price);
        }

        public void setOrder_Id(String order_id){
            TextView order_id_no = myview.findViewById(R.id.order_id);
            order_id_no.setText(order_id);
        }

        public void setPrepared_Time(String prepared_time){
            TextView order_order_prepared_time = myview.findViewById(R.id.order_item_order_status);
            order_order_prepared_time.setText(prepared_time);
        }

        public void setItem_count(String itemCount){
            TextView order_item_count = myview.findViewById(R.id.order_item_count);
            order_item_count.setText(itemCount);
        }

        public void setDelivered_time(String delivered_time){
            TextView order_item_delivered_Time = myview.findViewById(R.id.order_item_deliver_status);
            order_item_delivered_Time.setText(delivered_time);
        }

        public void setPayment_status(String payment_status){
            TextView order_item_payment_status = myview.findViewById(R.id.order_item_payment_status);
            order_item_payment_status.setText(payment_status);
        }

        public void setImage(final String image){
            final ImageView order_iem_image = myview.findViewById(R.id.order_item_image);

            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(order_iem_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).into(order_iem_image);
                }
            });
        }


    }

}