package com.example.amank.email2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public WebView wv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //for web view

       final WebView wv = (WebView) findViewById(R.id.wv);
        iv = (ImageView) findViewById(R.id.imageLoading1);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.loadUrl("http://www.swachhbharaturban.in");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                findViewById(R.id.imageLoading1).setVisibility(View.GONE);
                //show webview
                wv.setVisibility(View.VISIBLE);
            }
        });




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

//       for handeling web view back button
//
//        if(wv.canGoBack())
//        {
//
//            wv.goBack();
//
//        }
//        else
//        {
//            super.onBackPressed();
//
//
//        }


    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.clean) {
            Intent ci=new Intent(HomePage.this,CleanIssue.class);
            startActivity(ci);
            // Handle the camera action
        } else if (id == R.id.water) {
            Intent wi=new Intent(HomePage.this,WaterIssue.class);
            startActivity(wi);
        } else if (id == R.id.electric) {
            Intent ei=new Intent(HomePage.this,ElectricIssue.class);
            startActivity(ei);
        }
        else if (id==R.id.women)
        {
            Intent wsi=new Intent(HomePage.this,WomanIssue.class);
            startActivity(wsi);
        }
        else if(id==R.id.sanitation)
        {
            Intent si=new Intent(HomePage.this,SanitationIssue.class);
            startActivity(si);
        }
        else if(id==R.id.food)
        {
            Intent fi=new Intent(HomePage.this,FoodIssue.class);
            startActivity(fi);
        }
        else if(id==R.id.food)
        {
            Intent fi=new Intent(HomePage.this,FoodIssue.class);
            startActivity(fi);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
