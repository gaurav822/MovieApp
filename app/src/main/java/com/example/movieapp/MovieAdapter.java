package com.example.movieapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context myCt;
    List<Movie> myList;
    public MovieAdapter(Context ct, List<Movie> booksList) {
        myCt=ct;
        myList=booksList;
    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(myCt).inflate(R.layout.moviecard,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        final Movie b= myList.get(position);
        holder.tv_title.setText(b.getTitle());
        holder.tv_rating.setText(b.getVoteAverage());

        //Picaso or Glide. These are two third party Libraries to set image url to  the imageview

        Picasso.get().load(b.getPosterPath()).into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(myCt,MovieDescription.class);
                intent.putExtra("imagepath",b.getPosterPath());
                intent.putExtra("title",b.getTitle());
                intent.putExtra("releasedate",b.getReleaseDate());
                intent.putExtra("overview",b.getOverview());
                myCt.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_title,tv_rating;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.movieImage);
            tv_title=itemView.findViewById(R.id.movieName);
            tv_rating=itemView.findViewById(R.id.movieRating);
        }
    }
}
