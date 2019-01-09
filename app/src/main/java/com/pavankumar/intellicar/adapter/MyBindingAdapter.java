package com.pavankumar.intellicar.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MyBindingAdapter {

    @BindingAdapter({"loadImage"})
    public static void loadImage(ImageView view, String poster){
        RequestOptions options = new RequestOptions().
                error(android.R.drawable.ic_menu_report_image)
                .placeholder(android.R.drawable.ic_menu_report_image);
        Glide.with(view.getContext())
                .load(poster)
                .apply(options)
                .into(view);
    }

    @BindingAdapter({"isLoading"})
    public static void isLoading(View view, boolean isLoading){
        view.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}
