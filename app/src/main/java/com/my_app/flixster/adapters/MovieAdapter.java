package com.my_app.flixster.adapters;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my_app.flixster.R;
import com.my_app.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{
    private List<Movie> movies;
    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.title.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());
        int orientation = holder.view.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Glide.with(holder.view.getContext()).
                    load(movie.getPosterPath()).
                    into(holder.poster);
        } else {
            Glide.with(holder.view.getContext()).
                    load(movie.getBackdropPath()).
                    into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView overview;
        public ImageView poster;

        public View  view;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.tv_title);
            overview = itemView.findViewById(R.id.tv_overview);
            poster = itemView.findViewById(R.id.iv_poster);
        }
    }

}
