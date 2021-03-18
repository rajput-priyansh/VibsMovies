package com.vibs.vibsmovie.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vibs.vibsmovie.BR;
import com.vibs.vibsmovie.MovieViewModel;
import com.vibs.vibsmovie.models.ResultsItem;

import java.util.ArrayList;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private int layoutId;
    private ArrayList<ResultsItem> movies;

    //@Inject
    MovieViewModel viewModel;

    public MovieAdapter(@LayoutRes int layoutId, MovieViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new MovieViewHolder(binding);
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public void setMovies(ArrayList<ResultsItem> movies) {
        this.movies = movies;
    }

    public ArrayList<ResultsItem> getMovies() {
        return movies;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public MovieViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieViewModel movieViewModel, int position) {
            binding.setVariable(BR.viewModel, movieViewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}
