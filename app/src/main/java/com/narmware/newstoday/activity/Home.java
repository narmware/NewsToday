package com.narmware.newstoday.activity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.narmware.newstoday.MyApplication;
import com.narmware.newstoday.R;
import com.narmware.newstoday.adapter.HomeFragmentPagerAdapter;

import com.narmware.newstoday.fragment.HomeFragment;
import com.narmware.newstoday.fragment.MainFragment;
import com.narmware.newstoday.fragment.NewsFragment;

import com.narmware.newstoday.adapter.HomeMenuAdapter;
import com.narmware.newstoday.helpers.SupportFunctions;
import com.narmware.newstoday.pojo.Category;
import com.narmware.newstoday.pojo.CategoryNews;
import com.narmware.newstoday.pojo.HomeMenu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainFragment.OnFragmentInteractionListener,HomeFragment.OnFragmentInteractionListener,NewsFragment.OnFragmentInteractionListener {



  private  ListView mListView;
    private ArrayList<HomeMenu> mMenuList =new ArrayList<>();
   public static DrawerLayout drawer;
    public static ViewPager viewPager;
    RequestQueue mVolleyRequest;
    ArrayList<Category> categories=new ArrayList<>();
    HomeFragmentPagerAdapter tabAdapter;
    TabLayout tabLayout;
    HomeMenuAdapter homeMenuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mVolleyRequest = Volley.newRequestQueue(Home.this);

        GetAllCategories();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.container);

        tabAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),Home.this);
        tabAdapter.addFragment(new HomeFragment(),"Home");

        // Find the view pager that will allow the user to swipe between fragments
        viewPager.setAdapter(tabAdapter);

        tabLayout =findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        //viewPager.setCurrentItem(2);

        HomeMenu mHomeMenu=new HomeMenu("Home",R.drawable.ic_menu_gallery);

        mMenuList.add(mHomeMenu);

        homeMenuAdapter=new HomeMenuAdapter(this,mMenuList);
        mListView=findViewById(R.id.mListView);
        mListView.setAdapter(homeMenuAdapter);
    }

    @Override
    public void onBackPressed() {
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

    private void GetAllCategories() {
        final ProgressDialog dialog = new ProgressDialog(Home.this);
        dialog.setMessage("getting details ...");
        dialog.setCancelable(false);
        //dialog.show();

        String url= MyApplication.URL_ALL_CATEGORIES;
        Log.e("Url",url);

        JsonArrayRequest obreq = new JsonArrayRequest(Request.Method.GET,MyApplication.URL_ALL_CATEGORIES,null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONArray>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONArray response) {

                        try
                        {
                            //getting test master array
                            // testMasterDetails = testMasterArray.toString();

                            Log.e("Category Json_string",response.toString());
                            Gson gson = new Gson();

                            JSONArray jsonArray=response;

                           /* for(CategoryNews item:jsonArray) {

                            }*/

                            for(int i=0;i<jsonArray.length();i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.e("Category obj", jsonObject.toString());

                                Category category= gson.fromJson(jsonObject.toString(),Category.class);
                                Log.e("Json Category",category.getName()+"");

                                if(category.getCount()!=0) {
                                    tabAdapter.addFragment(NewsFragment.newInstance(category.getId()), category.getName());

                                    HomeMenu homeMenu = new HomeMenu(category.getName(), R.drawable.ic_menu_gallery);
                                    mMenuList.add(homeMenu);
                                }
                            }
                            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                            tabAdapter.notifyDataSetChanged();
                            homeMenuAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                            //dialog.dismiss();
                        }
                       // dialog.dismiss();
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Test Error");

                        //dialog.dismiss();

                    }
                }
        );
        mVolleyRequest.add(obreq);
    }


}
