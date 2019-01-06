package com.pavankumar.intellicar.callbacks;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.pavankumar.intellicar.retrofit.model.Search;
import com.pavankumar.intellicar.ui.MovieDetailsActivity;

import static com.pavankumar.intellicar.Constants.EXTRA_MOVIE_ID;

public class OnClickCallback {
    public void onClick(View view, Search search){
        Context context = view.getContext();
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID,search.getImdbID());
        context.startActivity(intent);

    }
}
