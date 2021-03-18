package com.vibs.vibsmovie;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vibs.vibsmovie.util.SpacesItemDecoration;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewBindings {
    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new SpacesItemDecoration(
                        (int)recyclerView.getContext().getResources().getDimension(R.dimen._8sdp)
                )
        );
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("imageUrl")
    public static void bindRecyclerViewAdapter(ImageView imageView, String imageUrl) {
        if (imageUrl != null) {

            String fullPath = BuildConfig.POSTER_URL + imageUrl;

            if (imageView.getTag(R.id.ivMoviePhoto) == null || !imageView.getTag(R.id.ivMoviePhoto).equals(imageUrl)) {
                imageView.setImageBitmap(null);
                imageView.setTag(R.id.ivMoviePhoto, imageUrl);
                Glide.with(imageView).load(fullPath).into(imageView);
            }
        } else {
            imageView.setTag(R.id.ivMoviePhoto, null);
            imageView.setImageBitmap(null);
        }
    }
}
