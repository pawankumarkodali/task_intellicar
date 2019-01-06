package com.pavankumar.intellicar.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pavankumar.intellicar.R;
import com.pavankumar.intellicar.adapter.MoviesListAdapter.MoviesListHolder;
import com.pavankumar.intellicar.callbacks.OnClickCallback;
import com.pavankumar.intellicar.databinding.MovieListItemBinding;
import com.pavankumar.intellicar.retrofit.model.Search;

import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListHolder> {

    List<? extends Search> mMoviesList;

    public void setMoviesList(List<Search> list){
        mMoviesList = list;
    }

    @NonNull
    @Override
    public MoviesListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MovieListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext())
                , R.layout.movie_list_item,viewGroup,false);
        binding.setCallback(new OnClickCallback());
        return new MoviesListHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListHolder moviesListHolder, int i) {
        moviesListHolder.binding.setMovieItem(mMoviesList.get(i));
        moviesListHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mMoviesList == null ? 0 : mMoviesList.size();
    }

    static class MoviesListHolder extends ViewHolder{
        MovieListItemBinding binding;

        public MoviesListHolder(MovieListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
