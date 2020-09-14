package app.noobstack.bringme.bringmelk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.noobstack.bringme.bringmelk.ui.Admin.dashboardFragments.SearchFragment;
import app.noobstack.bringme.bringmelk.ui.Admin.dashboardFragments.adminDashFragment;

public class AdminDashboard extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private adminDashFragment admindashfragment;
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        admindashfragment = new adminDashFragment();
        searchFragment = new SearchFragment();

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
                        Toast.makeText(AdminDashboard.this, "search fragment clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.Cart_ico:
                        Toast.makeText(AdminDashboard.this, "cart fragment clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.Order_ico:
                        Toast.makeText(AdminDashboard.this, "order fragment clicked", Toast.LENGTH_SHORT).show();
                    case R.id.Profile_ico:
                        Toast.makeText(AdminDashboard.this, "profile fragment clicked", Toast.LENGTH_SHORT).show();
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
}