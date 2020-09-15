package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;

import Fragments.Home;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager manager;
    FragmentTransaction transaction;

    ImageView imageView_srchbutton;
    EditText et_keyword;

    RecyclerView recyclerView;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView=(NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle drawerToggle=
                new ActionBarDrawerToggle(this,
                        drawerLayout,toolbar,
                        0,0);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));

        navigationView.setNavigationItemSelectedListener(this);

        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();


        et_keyword=(EditText)findViewById(R.id.movieKeyword);

        imageView_srchbutton=(ImageView)findViewById(R.id.srchmoviebutton);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);

        String myApiKey = "6b4bb558fd10a413f64d1c811ba20a0b";
        String url="https://api.themoviedb.org/3/discover/movie?api_key="+myApiKey+"&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";

        MyTask task=new MyTask(MainActivity.this,recyclerView,url);
        task.execute();

        imageView_srchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie_keyword=et_keyword.getText().toString();
                pd=new ProgressDialog(MainActivity.this);
                pd.setMessage("Please wait");
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();

                String url="https://api.themoviedb.org/3/search/movie?api_key=6b4bb558fd10a413f64d1c811ba20a0b&query="+movie_keyword;
                MyTask task=new MyTask(MainActivity.this,recyclerView,url);
                task.execute();
                pd.dismiss();
            }
        });

        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}