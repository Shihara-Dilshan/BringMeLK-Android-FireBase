package app.noobstack.bringme.bringmelk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.noobstack.bringme.bringmelk.ui.Admin.dashboardFragments.DeliverMangement;
import app.noobstack.bringme.bringmelk.ui.Admin.dashboardFragments.FoodMngFragment;
import app.noobstack.bringme.bringmelk.ui.Admin.dashboardFragments.SearchFragment;
import app.noobstack.bringme.bringmelk.ui.Admin.dashboardFragments.adminDashFragment;
import app.noobstack.bringme.bringmelk.ui.Admin.dashboardFragments.paymentManagement;

public class AdminDashboard extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private adminDashFragment admindashfragment;
    private SearchFragment searchFragment;
    private DeliverMangement deliverMangement;
    private FoodMngFragment foodMngFragment;
    private paymentManagement payment_management;

    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        admindashfragment = new adminDashFragment();
        searchFragment = new SearchFragment();
        deliverMangement = new DeliverMangement();
        foodMngFragment = new FoodMngFragment();
        payment_management = new paymentManagement();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = currentUser.getUid().toString();
        System.out.println(currentUserId);


        if(currentUserId.equals("ampSUboAV4U3fl5HwhrDZYamTJp1")){
             navigateFragment(deliverMangement);
        }else{
            navigateFragment(admindashfragment);
        }


        //hide the top title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        bottomNavigationView = findViewById(R.id.adminBottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home:
                        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("ampSUboAV4U3fl5HwhrDZYamTJp1")){
                            navigateFragment(deliverMangement);
                        }else{
                            navigateFragment(admindashfragment);
                        }
                        return true;
                    case R.id.Search_ico:
                        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("ampSUboAV4U3fl5HwhrDZYamTJp1")){
                            navigateFragment(deliverMangement);
                        }else{
                            navigateFragment(searchFragment);
                        }
                        return true;
                    case R.id.Cart_ico:
                        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("ampSUboAV4U3fl5HwhrDZYamTJp1")){
                            navigateFragment(deliverMangement);
                        }
                        return true;
                    case R.id.Order_ico:
                        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("ampSUboAV4U3fl5HwhrDZYamTJp1")){
                            navigateFragment(deliverMangement);
                        }else{
                            navigateFragment(foodMngFragment);
                        }
                        return true;
                    case R.id.Profile_ico:
                        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("ampSUboAV4U3fl5HwhrDZYamTJp1")){
                            navigateFragment(deliverMangement);
                        }else{
                            navigateFragment(payment_management);
                        }
                        return true;
                }
                return false;
            }
        });
    }

    public void navigateFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dashboardFrame, fragment);
        fragmentTransaction.commit();
    }

    public void adminLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(AdminDashboard.this, startPage.class);
        startActivity(intent);
    }

    public void viewTotalOrders(View view) {
        startActivity(new Intent(AdminDashboard.this, TotalPayments.class));
    }
}