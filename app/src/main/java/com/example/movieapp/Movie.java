package com.example.movieapp;

import java.io.Serializable;

public class Movie implements Serializable {
    private String id;
    private String voteAverage;
    private String voteCount;
    private String originalTitle;
    private String title;
    private String popularity;
    private String backdropPath;
    private String overview;
    private String releaseDate;
    private String posterPath;

    public Movie(String id, String voteAverage, String voteCount, String originalTitle, String title, String popularity, String backdropPath, String overview, String releaseDate, String posterPath) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.originalTitle = originalTitle;
        this.title = title;
        this.popularity = popularity;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;

    }



    public Movie() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

}
