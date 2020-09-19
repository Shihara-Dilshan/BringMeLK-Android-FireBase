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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        admindashfragment = new adminDashFragment();
        searchFragment = new SearchFragment();
        deliverMangement = new DeliverMangement();
        foodMngFragment = new FoodMngFragment();
        payment_management = new paymentManagement();


        navigateFragment(admindashfragment);
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
                        navigateFragment(admindashfragment);
                        return true;
                    case R.id.Search_ico:
                        navigateFragment(searchFragment);
                        return true;
                    case R.id.Cart_ico:
                        navigateFragment(deliverMangement);
                        return true;
                    case R.id.Order_ico:
                        navigateFragment(foodMngFragment);
                        return true;
                    case R.id.Profile_ico:
                        navigateFragment(payment_management);
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
}