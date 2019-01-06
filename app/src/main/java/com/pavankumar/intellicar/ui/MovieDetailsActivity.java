package com.pavankumar.intellicar.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pavankumar.intellicar.R;
import com.pavankumar.intellicar.databinding.MovieDetailsActivityBinding;
import com.pavankumar.intellicar.retrofit.model.MovieDetails;
import com.pavankumar.intellicar.viewmodel.MovieDetailsViewModel;

import static com.pavankumar.intellicar.Constants.EXTRA_MOVIE_ID;

public class MovieDetailsActivity extends AppCompatActivity {

    private MovieDetailsActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieDetailsViewModel model = ViewModelProviders.of(this).
                get(MovieDetailsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.movie_details_activity);
        binding.setIsLoading(true);
        observeModel(model);
        //binding.setViewModel(new MyBindingAdapter());
    }

    private void observeModel(MovieDetailsViewModel model){
        String id = getIntent().getStringExtra(EXTRA_MOVIE_ID);

        model.getMovieDetails(id).observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(@Nullable MovieDetails movieDetails) {
                binding.setIsLoading(false);
                binding.setMovieDetails(movieDetails);
            }
        });
    }
}
