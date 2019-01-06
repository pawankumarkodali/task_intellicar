package com.pavankumar.intellicar.providers;

import android.content.SearchRecentSuggestionsProvider;

public class MySearchSuggestionProvider extends SearchRecentSuggestionsProvider {

    public static final String AUTHORITY = "com.pavankumar.intellicar.providers.MySearchSuggestionProvider";

    public MySearchSuggestionProvider(){
        setupSuggestions(AUTHORITY,DATABASE_MODE_QUERIES);
    }
}
