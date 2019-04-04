package com.ardovic.zimadtestcase;

import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator {

    public static final String TAB_IDX = "TAB_IDX";
    public static final String VERTICAL_POS = "VERTICAL_POS";

    public static final int CAT = 0;
    public static final int DOG = 1;

    TabLayout tabLayout;

    ListFragment catsFragment;
    ListFragment dogsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Cats"));
        tabLayout.addTab(tabLayout.newTab().setText("Dogs"));

        catsFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(ListFragment.CAT);
        dogsFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(ListFragment.DOG);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (tab.getPosition()) {
                    case CAT:
                        if (getSupportFragmentManager().findFragmentByTag(ListFragment.CAT) == null) {
                            transaction.add(R.id.container, catsFragment, ListFragment.CAT);
                        } else {
                            transaction.attach(catsFragment);
                        }
                        transaction.detach(dogsFragment);
                        break;
                    case DOG:
                        if (getSupportFragmentManager().findFragmentByTag(ListFragment.DOG) == null) {
                            transaction.add(R.id.container, dogsFragment, ListFragment.DOG);
                        } else {
                            transaction.attach(dogsFragment);
                        }
                        transaction.detach(catsFragment);
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (savedInstanceState != null) {
            tabLayout.getTabAt(savedInstanceState.getInt(TAB_IDX)).select();
            //scroll to vertical pos
        } else {
            tabLayout.getTabAt(0).select();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showFullImage(String imageURL) {

    }
}
