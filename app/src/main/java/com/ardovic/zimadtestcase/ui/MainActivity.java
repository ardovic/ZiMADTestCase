package com.ardovic.zimadtestcase.ui;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ardovic.zimadtestcase.R;
import com.ardovic.zimadtestcase.data.Image;
import com.ardovic.zimadtestcase.ui.list.ListFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator {

    private static final String TAB = "TAB";
    private static final int CAT = 0;
    private static final int DOG = 1;

    @BindView(R.id.rootContainer)
    ConstraintLayout rootContainer;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private Fragment catsFragment;
    private Fragment dogsFragment;
    private int selectedTab = CAT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tabLayout.addTab(tabLayout.newTab().setText("Cats"), CAT);
        tabLayout.addTab(tabLayout.newTab().setText("Dogs"), DOG);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (tab.getPosition()) {
                    case CAT:
                        if ((catsFragment = getSupportFragmentManager().findFragmentByTag(ListFragment.CAT)) != null)
                            transaction.show(catsFragment);
                        else
                            transaction.add(R.id.tabContainer, catsFragment = ListFragment.newInstance(ListFragment.CAT), ListFragment.CAT);
                        if ((dogsFragment = getSupportFragmentManager().findFragmentByTag(ListFragment.DOG)) != null)
                            transaction.hide(dogsFragment);
                        break;
                    case DOG:
                        if ((dogsFragment = getSupportFragmentManager().findFragmentByTag(ListFragment.DOG)) != null)
                            transaction.show(dogsFragment);
                        else
                            transaction.add(R.id.tabContainer, dogsFragment = ListFragment.newInstance(ListFragment.DOG), ListFragment.DOG);
                        if ((catsFragment = getSupportFragmentManager().findFragmentByTag(ListFragment.CAT)) != null)
                            transaction.hide(catsFragment);
                }
                selectedTab = tab.getPosition();
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // do nothing
            }
        });
        if (savedInstanceState != null)
            selectedTab = savedInstanceState.getInt(TAB, CAT);
        else
            getSupportFragmentManager().beginTransaction().add(R.id.tabContainer,
                    catsFragment = ListFragment.newInstance(ListFragment.CAT), ListFragment.CAT).commit();
    }

    @Override
    protected void onDestroy() {
        tabLayout.clearOnTabSelectedListeners();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> tabLayout.getTabAt(selectedTab).select(), 100);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(TAB, selectedTab);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showFullImage(Image image) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootContainer, PreviewFragment.newInstance(image), PreviewFragment.TAG)
                .addToBackStack(PreviewFragment.TAG)
                .commit();
    }

    @Override
    public void showNetworkError() {
        Snackbar.make(rootContainer, getResources().getString(R.string.message_network_error), Snackbar.LENGTH_LONG).show();
    }
}