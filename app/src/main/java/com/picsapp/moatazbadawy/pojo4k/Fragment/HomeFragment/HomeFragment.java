package com.picsapp.moatazbadawy.pojo4k.Fragment.HomeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.picsapp.moatazbadawy.pojo4k.Adapter.Viewpager.ViewpagerHomeAdapter;
import com.picsapp.moatazbadawy.pojo4k.R;
import com.google.android.material.tabs.TabLayout;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private View rootView ;
    private ViewPager viewPager;
    private TabLayout tabLayout ;
    private int limitNumberOfPages = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Change the name in Toolbar
        Toolbar();

        // View pager that will allow the user to swipe between fragments
        ViewPager();

        // Connect the tab layout with the view pager.
        TabLayout();

        // Ads method
        Ads();

        return rootView;
    }

    private void Toolbar () {
        //Set the name in toolbar "change from app name to our name that we want"
        Objects.requireNonNull(getActivity()).setTitle("Home");
    }

    private void ViewPager() {
        // view pager that will allow the user to swipe between fragments

        // Find the view pager that will allow the user to swipe between fragments
        // "ViewPager with RTL support"
        viewPager = rootView.findViewById(R.id.home_viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        final ViewpagerHomeAdapter adapter = new ViewpagerHomeAdapter(this, getActivity().getSupportFragmentManager());
        // to make the viewPager not Reload when scroll "see this https://stackoverflow.com/questions/36517035/android-stop-tablayout-reload-refresh-my-fragments"
        viewPager.setOffscreenPageLimit(limitNumberOfPages); //before setAdapter
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
    }

    private void TabLayout() {
        // Connect the tab layout with the view pager.

        // Find the tab layout that shows the tabs
        tabLayout = rootView.findViewById(R.id.home_tabs);
        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);
    }

    public void Ads() {
        // Ads method
        // This call initializes the SDK and calls back a completion listener once
        // initialization is complete (or after a 30-second timeout). Call this method
        // only once and as early as possible, ideally at app launch.
        //MobileAds.initialize(getActivity(), "ca-app-pub- + add your id here");
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = rootView.findViewById(R.id.ad_view_home);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}