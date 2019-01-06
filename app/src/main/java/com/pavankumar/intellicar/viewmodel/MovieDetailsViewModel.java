package com.pavankumar.intellicar.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pavankumar.intellicar.retrofit.MoviesRepo;
import com.pavankumar.intellicar.retrofit.model.MovieDetails;

public class MovieDetailsViewModel extends AndroidViewModel {


    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MovieDetails> getMovieDetails(String id){
        return MoviesRepo.getRepo().getMovieDetails(id);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String poster){
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(android.R.drawable.ic_menu_report_image)
                .error(android.R.drawable.ic_menu_report_image);

        Glide.with(view.getContext())
                .load(poster)
                .apply(requestOptions)
                .into(view);
    }



}
