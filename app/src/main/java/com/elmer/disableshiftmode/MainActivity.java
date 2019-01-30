package com.elmer.disableshiftmode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements MenuAFragment.OnFragmentInteractionListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        selectFragment(bottomNavigationView.getMenu().getItem(0));

        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    selectFragment(menuItem);
                    return false;
                }
            });
        }
    }

    void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.root_layout, fragment, MenuAFragment.ARG_PARAM);
                ft.commit();
            }
        }

    }

    void selectFragment(MenuItem item) {
        item.setChecked(true);

        int itemID = item.getItemId();
        if (itemID == R.id.menu_a) {
            pushFragment(MenuAFragment.newInstance("MENU A"));
        }
        else if (itemID == R.id.menu_b) {
            pushFragment(MenuAFragment.newInstance("MENU B"));
        }
        else if (itemID == R.id.menu_c) {
            pushFragment(MenuAFragment.newInstance("MENU C"));
        }
        else if (itemID == R.id.menu_d) {
            pushFragment(MenuAFragment.newInstance("MENU D"));
        }
        else {
            pushFragment(MenuAFragment.newInstance("MENU E"));
        }

        TextView largeTextView = bottomNavigationView.findViewById(itemID)
                .findViewById(com.google.android.material.R.id.largeLabel);
        TextView smallTextView = bottomNavigationView.findViewById(itemID)
                .findViewById(com.google.android.material.R.id.smallLabel);

        smallTextView.setVisibility(View.VISIBLE);
        largeTextView.setVisibility(View.GONE);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
