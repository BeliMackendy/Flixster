package com.my_app.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class Movie {
    private String title;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private Double voteAverage;
    private int movieId;

    public Movie() {
    }

    public Movie(JSONObject movie) throws JSONException {
        this.title = movie.getString("title");
        this.overview = movie.getString("overview");
        this.posterPath = movie.getString("poster_path");
        this.backdropPath = movie.getString("backdrop_path");
        this.voteAverage = movie.getDouble("vote_average");
        this.movieId = movie.getInt("id");
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }
    public Double getVoteAverage() {
        return voteAverage;
    }


    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public int getMovieId() {
        return movieId;
    }
}
