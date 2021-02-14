package com.picsapp.moatazbadawy.pojo4k.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.picsapp.moatazbadawy.pojo4k.Fragment.SettingFragment.AboutFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.SettingFragment.HelpFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.SettingFragment.ThemeFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.RepoFragment.CategoriesFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.RepoFragment.SavedFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.EditorChoicesFragment.EditorChoicesFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.HomeFragment.HomeFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.PcFragment.PcFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.W4KFragment.Wallpapers4kFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.IslamicFragment.IslamicWallFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.OthersFragment.PanoramaFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.OthersFragment.VideosWallFragment;
import com.google.android.material.navigation.NavigationView;
import com.picsapp.moatazbadawy.pojo4k.R;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private static final int PERMISSION_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Make the status bar white with black icons */
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        /* Make the app support only English */
        ViewCompat.setLayoutDirection(getWindow().getDecorView(), ViewCompat.LAYOUT_DIRECTION_LTR);

        /* To ask the user get allow the app for storage */
        requestPermission();

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //drawer.openDrawer(Gravity.LEFT);
        toggle.syncState();

        if (savedInstanceState == null) {
            // start fragment when app lunch
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_pc:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new PcFragment()).commit();
                break;
            case R.id.nav_4k:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new Wallpapers4kFragment()).commit();
                break;
            case R.id.nav_editor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new EditorChoicesFragment()).commit();
                break;
            case R.id.nav_videos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new VideosWallFragment()).commit();
                break;
            case R.id.nav_panorama:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new PanoramaFragment()).commit();
                break;
            case R.id.nav_islamic:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new IslamicWallFragment()).commit();
                break;
            case R.id.nav_categories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new CategoriesFragment()).commit();
                break;
            case R.id.nav_saved:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new SavedFragment()).commit();
                break;
            case R.id.nav_theme:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new ThemeFragment()).commit();
                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new HelpFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                        new AboutFragment()).commit();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        // If navigation drawer is close, open it.
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    private void requestPermission() {
        // Allow the app for storage
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toasty.normal(MainActivity.this, "please allow the app to save images in settings", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
}