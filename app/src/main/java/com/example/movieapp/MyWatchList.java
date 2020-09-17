package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import Models.FavouriteMovies;
import Models.FavouriteMoviesDatabase;
import Models.MyViewModel;

public class MyWatchList extends AppCompatActivity {

    TextView tv_empty;
    RecyclerView recyclerView;
    static FavouriteMoviesDatabase database;

    static MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_watch_list);
        tv_empty=(TextView) findViewById(R.id.textView);
        recyclerView=(RecyclerView) findViewById(R.id.recycler);

        viewModel=new ViewModelProvider(this).get(MyViewModel.class);

        viewModel.readData().observe(this, new Observer<List<FavouriteMovies>>() {
            @Override
            public void onChanged(List<FavouriteMovies> favouriteMovie) {

                if(favouriteMovie.size()==0){
                    tv_empty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                else {
                    tv_empty.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyWatchList.this));
                    recyclerView.setAdapter(new FavouriteDataAdapter(MyWatchList.this, favouriteMovie));
                }
            }
        });

    }
}