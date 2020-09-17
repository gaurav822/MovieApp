package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import Models.FavouriteMovies;

public class FavouriteDataAdapter extends RecyclerView.Adapter<FavouriteDataAdapter.MyViewHolder> {
    Context myct;

    List<FavouriteMovies> myList;

    public FavouriteDataAdapter(MyWatchList myWatchList, List<FavouriteMovies> favouriteMovie) {
        myct=myWatchList;
        myList=favouriteMovie;
    }

    @NonNull
    @Override
    public FavouriteDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(myct).inflate(R.layout.favouritemoviescard,parent,false);

        return new FavouriteDataAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteDataAdapter.MyViewHolder holder, int position) {

        final FavouriteMovies favouriteMovies=myList.get(position);
        holder.tv_title.setText(favouriteMovies.getTitle());
        holder.tv_releasedate.setText(favouriteMovies.getReleasedate());
        holder.tv_overview.setText(favouriteMovies.getOverview());

        holder.heart_button.setLiked(true);

        Picasso.get().load(favouriteMovies.getPosterpath()).into(holder.iv);

        holder.heart_button.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                MyWatchList.viewModel.delete(favouriteMovies);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_title,tv_overview,tv_releasedate;
        LikeButton heart_button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=(ImageView) itemView.findViewById(R.id.posterimage);
            tv_title=(TextView) itemView.findViewById(R.id.movietitle);
            tv_overview=(TextView) itemView.findViewById(R.id.moviedescription);
            tv_releasedate=(TextView) itemView.findViewById(R.id.releasedate);
            heart_button= (LikeButton) itemView.findViewById(R.id.heart_button);


        }
    }
}
