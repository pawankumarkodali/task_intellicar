package com.pavankumar.intellicar.ui;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.Toast;

import com.pavankumar.intellicar.R;
import com.pavankumar.intellicar.adapter.MoviesListAdapter;
import com.pavankumar.intellicar.databinding.MovieListActivityBinding;
import com.pavankumar.intellicar.providers.MySearchSuggestionProvider;
import com.pavankumar.intellicar.retrofit.model.MovieList;
import com.pavankumar.intellicar.viewmodel.MoviesListViewModel;

public class MovieListActivity extends AppCompatActivity {

    private MoviesListAdapter mAdapter;
    private MoviesListViewModel mViewModel;
    private MovieListActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.movie_list_activity);
        setSupportActionBar(binding.toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAdapter = new MoviesListAdapter();
        binding.moviesListView.setAdapter(mAdapter);
        binding.setIsLoading(false);
        binding.executePendingBindings();

        mViewModel = ViewModelProviders.of(this).
                get(MoviesListViewModel.class);

        //observerModel(mViewModel);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setIconified(false);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        return true;
    }

    private void handleIntent(Intent intent){
        if(intent.getAction() == Intent.ACTION_SEARCH){
            String query = intent.getStringExtra(SearchManager.QUERY);
            processSearchQuery(query);
        }
    }

    private void processSearchQuery(String query){
        observerModel(query,mViewModel);
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                MySearchSuggestionProvider.AUTHORITY, SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES);
        suggestions.saveRecentQuery(query, null);
    }

    private void observerModel(String title, final MoviesListViewModel model){
        binding.setIsLoading(true);
        binding.executePendingBindings();
        model.getMoviesList(title).observe(this, new Observer<MovieList>() {
            @Override
            public void onChanged(@Nullable MovieList movieList) {
                binding.setIsLoading(false);
                binding.executePendingBindings();
                if(movieList != null && movieList.getSearch() != null ) {
                    mAdapter.setMoviesList(movieList.getSearch());
                    mAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MovieListActivity.this,R.string.no_movies_found,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
