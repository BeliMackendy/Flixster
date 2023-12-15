package com.example.flixster.adapters;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.Target;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        view.setBackgroundColor(ContextCompat.getColor(parent.getContext(),R.color.navy));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvTitle.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.white));
            tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
            tvTitle.setTypeface(ResourcesCompat.getFont(itemView.getContext(),R.font.barrio));
            tvOverview.setText(movie.getOverview());
            tvOverview.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.white));

            String image;
            int orientation = itemView.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                image = movie.getBackdropPath();
                Glide
                        .with(itemView.getContext())
                        .load(image)
                        .override(320)
                        .placeholder(R.drawable.television_placeholder)
                        .transition(DrawableTransitionOptions.withCrossFade(4000))
                        .transform(new RoundedCorners(20))
                        .into(ivPoster);
            } else {
                image = movie.getPosterPath();

                Glide
                        .with(itemView.getContext())
                        .load(image)
                        .override(180)
                        .placeholder(R.drawable.television_placeholder)
                        .transition(DrawableTransitionOptions.withCrossFade(4000))
                        .transform(new RoundedCorners(20))
                        .into(ivPoster);
            }
        }
    }
}
