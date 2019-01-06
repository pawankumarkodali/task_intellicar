package com.pavankumar.intellicar.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

public class MyBindingAdapter {

    @BindingAdapter({"loadImage"})
    public static void loadImage(ImageView view, String poster){
        Glide.with(view.getContext())
                .load(poster)
                .into(view);
    }

    @BindingAdapter({"isLoading"})
    public static void isLoading(View view, boolean isLoading){
        view.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}
