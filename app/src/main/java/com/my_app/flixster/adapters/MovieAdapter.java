package com.my_app.flixster.adapters;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.my_app.flixster.DetailsActivity;
import com.my_app.flixster.R;
import com.my_app.flixster.databinding.ItemMovieStartBinding;
import com.my_app.flixster.models.Movie;

import com.bumptech.glide.request.target.Target;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Movie> movies;
    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == 1) {
            View movieView = inflater.inflate(R.layout.item_movie_start, parent, false);
            return new MovieHolder2(movieView);
        }
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        String uniqueTransitionName = "poster_" + movie.getMovieId();


        if(holder.getItemViewType() == 0){
            MovieHolder holder1 = (MovieHolder) holder;

            ViewCompat.setTransitionName(holder1.poster, uniqueTransitionName);
            holder1.title.setText(movie.getTitle());
            holder1.overview.setText(movie.getOverview());
            int orientation = holder1.view.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Glide.with(holder1.view.getContext()).
                    load(movie.getPosterPath()).
                    placeholder(R.drawable.placeholdermovie).
                    override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).
                    transition(DrawableTransitionOptions.withCrossFade(5000)).
                    into(holder1.poster);
            } else {
            Glide.with(holder1.view.getContext()).
                    load(movie.getBackdropPath()).
                    placeholder(R.drawable.placeholdermovie).
                    override(900).
                    transition(DrawableTransitionOptions.withCrossFade(5000)).
                    into(holder1.poster);
            }
            holder1.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder1.view.getContext(), movie.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(holder1.view.getContext(), DetailsActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) holder1.view.getContext(), holder1.poster, uniqueTransitionName);

                    holder1.view.getContext().startActivity(i,options.toBundle());
                }
            });
        }
        if(holder.getItemViewType() == 1) {

            MovieHolder2 holder2 = (MovieHolder2) holder;

            ViewCompat.setTransitionName(holder2.binding.ivBackdrop, uniqueTransitionName);
            Glide.with(holder2.itemView.getContext()).
                    load(movie.getBackdropPath()).
                    placeholder(R.drawable.placeholdermovie).
                    override(900).
                    transition(DrawableTransitionOptions.withCrossFade(5000)).
                    into(holder2.binding.ivBackdrop);
            holder2.binding.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder2.itemView.getContext(), movie.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(holder2.itemView.getContext(), DetailsActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) holder2.itemView.getContext(), holder2.binding.ivBackdrop, uniqueTransitionName);

                    holder2.itemView.getContext().startActivity(i,options.toBundle());
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(movies.get(position).getVoteAverage() > 5)
        {
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class MovieHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView overview;
        public ImageView poster;

        RelativeLayout container;

        public View  view;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.tv_title);
            overview = itemView.findViewById(R.id.tv_overview);
            poster = itemView.findViewById(R.id.iv_poster);
            container = itemView.findViewById(R.id.container);
        }
    }
    public class MovieHolder2 extends RecyclerView.ViewHolder {
//        public ImageView poster;
//        public View  view;
//        RelativeLayout container;

        ItemMovieStartBinding binding;
        public MovieHolder2(@NonNull View itemView) {
            super(itemView);
            binding = ItemMovieStartBinding.bind(itemView);
//            poster = itemView.findViewById(R.id.iv_backdrop);
//            view = itemView;
//            container = itemView.findViewById(R.id.container);
        }
    }

}
