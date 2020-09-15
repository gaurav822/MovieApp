package com.example.movieapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import Fragments.Home;

public class MyTask extends AsyncTask<Void,Void,String>
{

    private String poster_main_path="https://image.tmdb.org/t/p/w500";

    String myUrl;
    Context ct;
    ProgressDialog pd;
    RecyclerView myRv;

    public MyTask(MainActivity mainActivity, RecyclerView recyclerView, String url) {
        ct=mainActivity;
        myRv=recyclerView;
        myUrl=url;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(ct);
        pd.setMessage("Please wait");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            URL u=new URL(myUrl);
            HttpsURLConnection connection = (HttpsURLConnection) u.openConnection();
            InputStream is= connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            String line="";
            StringBuilder builder=new StringBuilder();
            while((line=reader.readLine())!=null){

                builder.append(line);
            }

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Toast.makeText(ct, ""+s, Toast.LENGTH_SHORT).show();
        pd.dismiss();

        List<Movie> movieList = new ArrayList<>();

        try {
            JSONObject rootJsonObject=new JSONObject(s);
            JSONArray itemsJsonArray = rootJsonObject.getJSONArray("results");

            for(int i=0;i<itemsJsonArray.length();i++){

                JSONObject indexObject = itemsJsonArray.getJSONObject(i);
                String title = indexObject.optString("title");
                String id = indexObject.optString("id");
                String vote_average = indexObject.optString("vote_average");
                String vote_count = indexObject.optString("vote_count");
                String original_title = indexObject.optString("original_title");
                String popularity = indexObject.optString("popularity");
                String backdrop_path = indexObject.optString("backdrop_path");
                String overview = indexObject.optString("overview");
                String release_date = indexObject.optString("release_date");
                String poster_path = indexObject.optString("poster_path");

                String final_posterpath=poster_main_path+poster_path;


                Movie b=new Movie(id,vote_average,vote_count,original_title,title,popularity,backdrop_path,overview,release_date,final_posterpath);
                movieList.add(b);

            }

            myRv.setLayoutManager(new LinearLayoutManager(ct));
            myRv.setAdapter(new MovieAdapter(ct,movieList));



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
