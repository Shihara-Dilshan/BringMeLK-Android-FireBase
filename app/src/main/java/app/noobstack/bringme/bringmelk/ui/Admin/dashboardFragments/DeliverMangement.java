package app.noobstack.bringme.bringmelk.ui.Admin.dashboardFragments;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import app.noobstack.bringme.bringmelk.R;
import app.noobstack.bringme.bringmelk.model.Order;
import app.noobstack.bringme.bringmelk.ui.GetOrder;
public class DeliverMangement extends Fragment {
    private RecyclerView OrderList;
    private DatabaseReference OrdersDB;
    private Query PendingOrders;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_deliver_mangement, container, false);
        OrdersDB= FirebaseDatabase.getInstance().getReference().child("orders");
        PendingOrders = OrdersDB.orderByChild("delivered_time").equalTo("not delivered");
        OrderList = root.findViewById(R.id.deliveryREview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        OrderList.setHasFixedSize(true);
        OrderList.setLayoutManager(linearLayoutManager);
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Order, DeliverMangement.OrdersView> adapter1=new FirebaseRecyclerAdapter<Order, DeliverMangement.OrdersView>(Order.class,R.layout.orders_delivery_view, DeliverMangement.OrdersView.class,PendingOrders ) {
            @Override
            protected void populateViewHolder(DeliverMangement.OrdersView ordersView, final Order orders, int i) {
                ordersView.setBuyer_Address(orders.getBuyer_Address());
                ordersView.setBuyer_Name(orders.getBuyer_Name());
                ordersView.setItem_name(orders.getItem_name());
                ordersView.setItemQuantity(orders.getItem_count());
                ordersView.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), GetOrder.class);
                        intent.putExtra("order_Id",orders.getOrder_Id());
                        intent.putExtra("buyer_Address",orders.getBuyer_Address());
                        intent.putExtra("item_name",orders.getItem_name());
                        intent.putExtra("buyer_Mobile",orders.getBuyer_Mobile());
                        intent.putExtra("buyer_Name",orders.getBuyer_Name());
                        intent.putExtra("Item_Price",orders.getTotal_Price());
                        intent.putExtra("Quantity",orders.getItem_count());
                        startActivity(intent);
                    }
                });
            }
        };
        OrderList.setAdapter(adapter1);
    }
    public static class OrdersView extends RecyclerView.ViewHolder {
        View view;
        public OrdersView(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
        public void setBuyer_Address(String buyer_address) {
            TextView OrderTitle = view.findViewById(R.id.OrderTitle);
            OrderTitle.setText(buyer_address);
        }
       public void setBuyer_Name(String Buyer_Name) {
           TextView BuyerName = view.findViewById(R.id.OrderDescription);
            BuyerName.setText(Buyer_Name);
       }
        public void setItem_name(String title){
            TextView OrderItem = view.findViewById(R.id.ItemName);
            OrderItem.setText(title);
        }
        public void setItemQuantity(String quantity){
            TextView Quantity = view.findViewById(R.id.Quantity_);
            Quantity.setText(quantity);
        }
    }
}