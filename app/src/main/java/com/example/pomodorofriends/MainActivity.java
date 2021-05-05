package com.example.pomodorofriends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pomodorofriends.fragments.AddFragment;
import com.example.pomodorofriends.fragments.BasketFragment;
import com.example.pomodorofriends.fragments.ProfileFragment;
import com.example.pomodorofriends.fragments.TimerActionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    BottomNavigationView bottomNavigationView;

    final FragmentManager fragmentManager = getSupportFragmentManager();
    // define your fragments here
    final Fragment addFragment = new AddFragment();
    final Fragment basketFragment = new BasketFragment();
    final Fragment profileFragment = new ProfileFragment();
    Fragment timerFragment = new TimerActionFragment();

    private Fragment currentFragment;


    private boolean timerSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Allow switching between fragments while keeping them alive


        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_basket:
                        fragment = basketFragment;
                        break;
                    case R.id.action_profile:
                        fragment = profileFragment;
                        break;
                    case R.id.action_current:
                        if(timerSelected) {
                            fragment = timerFragment;
                            break;
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Select Timer", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    default:
                        fragment = basketFragment;
                        break;
                }

                loadFragment(fragment);
                return true;
            }

        });
        bottomNavigationView.setSelectedItemId(R.id.action_basket);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;
            case R.id.action_add:
                loadFragment(addFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        ParseUser.logOut();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private boolean loadFragment (Fragment fr) {
        if (fr != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer, fr)
                    .commit();
            return true;
        }
        return false;
    }


    public void setCurrentTimer(Timer timer) {
        Bundle timerArgs = new Bundle();
        timerArgs.putInt("activityTime", timer.getActivityTimer());
        timerArgs.putInt("breakTime", timer.getBreakTimer());
        timerArgs.putInt("period", timer.getPeriod());
        timerArgs.putString("caption", timer.getCaption());

        timerFragment = new TimerActionFragment();

        timerFragment.setArguments(timerArgs);

        loadFragment(timerFragment);
        bottomNavigationView.setSelectedItemId(R.id.action_current);
        timerSelected = true;
    }
}