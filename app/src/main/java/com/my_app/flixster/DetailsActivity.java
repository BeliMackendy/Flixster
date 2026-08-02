package com.my_app.flixster;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.my_app.flixster.models.Movie;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailsActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvOverview;
    RatingBar rtBar;

    YouTubePlayerView youTubePlayerView;

    public static final String Movie_Url = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rtBar = findViewById(R.id.rtBar);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        rtBar.setRating(movie.getVoteAverage().floatValue());

        String transitionName = "poster_" + movie.getMovieId();
        ViewCompat.setTransitionName(rtBar, transitionName);

        youTubePlayerView = findViewById(R.id.player);
        getLifecycle().addObserver(youTubePlayerView);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(String.format(Movie_Url, movie.getMovieId()), new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;

                try {
                    JSONArray results = jsonObject.getJSONArray("results");

                    for (int j = 0; j < results.length(); j++) {
                        JSONObject object = results.getJSONObject(j);
                        if((object.getString("type").equals("Trailer")) && (object.getString("site").equals("YouTube")))
                        {
                            String videokey= results.getJSONObject(j).getString("key");
                            InitialVideo(videokey);
                            break;
                        }
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });
    }

    private void InitialVideo(String videokey) {
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(videokey, 0f);
            }
        });
    }
}