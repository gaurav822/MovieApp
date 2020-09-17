package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import Models.FavouriteMovies;
import Models.FavouriteMoviesDatabase;
import Models.MyViewModel;

public class MovieDescription extends AppCompatActivity {

    ImageView poster_imageview;
    TextView tv_title,tv_releasedate,tv_description;
    Intent intent;

    LikeButton likeButton;
    FavouriteMoviesDatabase database;

    List<FavouriteMovies> totalfavouriteMovies;

    static MyViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        poster_imageview=(ImageView) findViewById(R.id.posterimage);
        tv_title=(TextView) findViewById(R.id.movietitle);
        tv_releasedate=(TextView) findViewById(R.id.releasedate);
        tv_description=(TextView) findViewById(R.id.moviedescription);
        likeButton=(LikeButton) findViewById(R.id.heart_button);

        intent=getIntent();

        final String img_path=intent.getStringExtra("imagepath");
        final String movie_title=intent.getStringExtra("title");
        final String releasedate=intent.getStringExtra("releasedate");
        final String overview=intent.getStringExtra("overview");

        tv_title.setText(movie_title);
        tv_releasedate.setText(releasedate);
        tv_description.setText(overview);

        Picasso.get().load(img_path).into(poster_imageview);

//        database = Room.databaseBuilder(this, FavouriteMoviesDatabase.class,"MYDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
//
//        favouritemovies= database.myDao().readData();

        viewModel=new ViewModelProvider(this).get(MyViewModel.class);

        viewModel.readData().observe(this, new Observer<List<FavouriteMovies>>() {
            @Override
            public void onChanged(List<FavouriteMovies> favouriteMovies) {
                totalfavouriteMovies=favouriteMovies;
                for (int i=0;i<favouriteMovies.size();i++){
                    Toast.makeText(MovieDescription.this, ""+favouriteMovies.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {


                FavouriteMovies favouriteMovies=new FavouriteMovies();
                favouriteMovies.setTitle(tv_title.getText().toString());
                favouriteMovies.setOverview(tv_description.getText().toString());
                favouriteMovies.setPosterpath(img_path);
                favouriteMovies.setReleasedate(tv_releasedate.getText().toString());

                MyWatchList.viewModel.insert(favouriteMovies);
                Toast.makeText(MovieDescription.this, "Added to watchlist", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void unLiked(LikeButton likeButton) {

                Toast.makeText(MovieDescription.this, "Unliked", Toast.LENGTH_SHORT).show();

            }
        });


    }
}