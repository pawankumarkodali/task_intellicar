package com.pavankumar.intellicar.retrofit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.pavankumar.intellicar.BuildConfig;
import com.pavankumar.intellicar.retrofit.model.MovieDetails;
import com.pavankumar.intellicar.retrofit.model.MovieList;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pavankumar.intellicar.Constants.BASE_URL;

public class MoviesRepo {
    private static ApiInterface sMovieClient;
    private static MoviesRepo sInstance;

    private MoviesRepo(){
        if(sMovieClient == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request actualrequest = chain.request();
                    HttpUrl actualUrl = actualrequest.url();

                    HttpUrl url = actualUrl.newBuilder().
                            addQueryParameter("apikey", BuildConfig.ApiKey).
                            build();

                    Request request = actualrequest.newBuilder().
                            url(url).
                            build();

                    return chain.proceed(request);
                }
            });

            httpClient.addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    client(httpClient.build()).
                    build();

            sMovieClient = retrofit.create(ApiInterface.class);
        }
    }

    public static MoviesRepo getRepo(){
        if(sInstance == null)
            sInstance = new MoviesRepo();
        return sInstance;
    }

    public LiveData<MovieList> getMovieList(String title){
        final MutableLiveData<MovieList> movieList = new MutableLiveData<>();

        sMovieClient.getMovieList(title).enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, retrofit2.Response<MovieList> response) {
                movieList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
        return movieList;
    }

    public LiveData<MovieDetails> getMovieDetails(String id){
        final MutableLiveData<MovieDetails> movieDetails = new MutableLiveData<>();
        sMovieClient.getMovieDetails(id).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, retrofit2.Response<MovieDetails> response) {
                movieDetails.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });
        return movieDetails;
    }

}
