package com.vibs.vibsmovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vibs.vibsmovie.BuildConfig;
import com.vibs.vibsmovie.R;
import com.vibs.vibsmovie.models.ResultsItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<ResultsItem> movies;
    private Context context;

    public MovieAdapter(ArrayList<ResultsItem> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return MovieViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position), context);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMovieName;
        private ImageView ivMoviePhoto;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMovieName = itemView.findViewById(R.id.tvMovieName);
            ivMoviePhoto = itemView.findViewById(R.id.ivMoviePhoto);
        }

        public static MovieViewHolder from(ViewGroup parent) {
            return new MovieViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_movie, parent, false));
        }

        public void bind(ResultsItem resultsItem, Context context) {
            tvMovieName.setText(resultsItem.getTitle()!= null ? resultsItem.getTitle() : "");

            String fullPath = "";
            if (resultsItem.getPosterPath() != null && !resultsItem.getPosterPath().isEmpty()) {

                fullPath = BuildConfig.POSTER_URL + resultsItem.getPosterPath();
            } else  {
                fullPath = "";
            }

            Glide.with(context)
                    .load(fullPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.mipmap.ic_launcher)
                    .into(ivMoviePhoto);
        }

    }
}
