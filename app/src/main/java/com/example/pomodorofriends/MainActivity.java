package com.example.pomodorofriends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pomodorofriends.fragments.AddFragment;
import com.example.pomodorofriends.fragments.BasketFragment;
import com.example.pomodorofriends.fragments.ProfileFragment;
import com.example.pomodorofriends.fragments.TimerActionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    final FragmentManager fragmentManager = getSupportFragmentManager();
    // define your fragments here
    final Fragment addFragment = new AddFragment();
    final Fragment basketFragment = new BasketFragment();
    final Fragment profileFragment = new ProfileFragment();
    final Fragment timerFragment = new TimerActionFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        fragment = timerFragment;
                        break;
                    default:
                        fragment = basketFragment;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
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
                fragmentManager.beginTransaction().replace(R.id.flContainer, addFragment).commit();
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
}