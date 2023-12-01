package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String overview;
    private String posterPath;
    private String backdropPath;

    public Movie(JSONObject movieObject) throws JSONException {
        title = movieObject.getString("title");
        overview = movieObject.getString("overview");
        posterPath = movieObject.getString("poster_path");
        backdropPath = movieObject.getString("backdrop_path");
    }

    public static List<Movie> fromJsonArray(JSONArray movieArray) throws JSONException {
        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i< movieArray.length(); i++){
            movieList.add(new Movie(movieArray.getJSONObject(i)));
        }

        return movieList;
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
}

