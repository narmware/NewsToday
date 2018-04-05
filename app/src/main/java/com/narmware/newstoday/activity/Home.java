package com.narmware.newstoday.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.narmware.newstoday.R;
import com.narmware.newstoday.adapter.HomeFragmentPagerAdapter;

import com.narmware.newstoday.fragment.HomeFragment;
import com.narmware.newstoday.fragment.MainFragment;
import com.narmware.newstoday.fragment.NewsFragment;

import com.narmware.newstoday.adapter.HomeMenuAdapter;
import com.narmware.newstoday.fragment.MainFragment;
import com.narmware.newstoday.pojo.HomeMenu;


import java.util.ArrayList;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainFragment.OnFragmentInteractionListener,HomeFragment.OnFragmentInteractionListener,NewsFragment.OnFragmentInteractionListener {



  private  ListView mListView;
    private ArrayList<HomeMenu> mMenuList =new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager = findViewById(R.id.container);

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),Home.this);
        adapter.addFragment(new HomeFragment(),"Home");
        adapter.addFragment(NewsFragment.newInstance(1),"News");
        adapter.addFragment(NewsFragment.newInstance(2),"Sports");
        adapter.addFragment(NewsFragment.newInstance(1),"Political");

        // Find the view pager that will allow the user to swipe between fragments
        viewPager.setAdapter(adapter);

        TabLayout tabLayout =findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);





        HomeMenu mHomeMenu=new HomeMenu("Home",R.drawable.ic_menu_gallery);
        HomeMenu mStaticMenu1=new HomeMenu("Notification",R.drawable.ic_notifications);
        HomeMenu mStaticMenu2=new HomeMenu("Login",R.drawable.ic_lock);
        HomeMenu mStaticMenu3=new HomeMenu("Settings",R.drawable.ic_settings);


        mMenuList.add(mHomeMenu);
        mMenuList.add(mStaticMenu1);
        mMenuList.add(mStaticMenu2);
        mMenuList.add(mStaticMenu3);




        HomeMenuAdapter mAdapter=new HomeMenuAdapter(this,mMenuList);
        mListView=findViewById(R.id.mListView);
        mListView.setAdapter(mAdapter);







    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
    /*    int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
