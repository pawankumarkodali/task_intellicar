package com.pavankumar.intellicar.retrofit;

import com.pavankumar.intellicar.retrofit.model.MovieList;
import com.pavankumar.intellicar.retrofit.model.MovieDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(".")
    Call<MovieList> getMovieList(@Query("s") String title);

    @GET(".")
    Call<MovieDetails> getMovieDetails(@Query("i") String id);

}
