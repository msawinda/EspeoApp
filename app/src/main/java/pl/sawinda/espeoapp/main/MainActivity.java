package pl.sawinda.espeoapp.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import pl.sawinda.espeoapp.R;
import pl.sawinda.espeoapp.contact.ContactFragment;
import pl.sawinda.espeoapp.experience.ExperienceFragment;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private MainPresenter mainPresenter;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainPresenter = new MainPresenter(this);
        fragmentManager = getSupportFragmentManager();

        final ContactFragment contactFragment = new ContactFragment();
        final ExperienceFragment experienceFragment = new ExperienceFragment();
        mainPresenter.changeFragment(contactFragment);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()){
                    case R.id.contact:
                        mainPresenter.changeFragment(contactFragment);
                        return true;
                    case R.id.experience:
                        mainPresenter.changeFragment(experienceFragment);
                        return true;
                    case R.id.education:
                        mainPresenter.prepareMap();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Something went Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer, R.string.close_drawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }




}
