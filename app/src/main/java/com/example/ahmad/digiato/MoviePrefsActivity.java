package com.example.ahmad.digiato;

import android.app.Activity;
import android.os.Bundle;

import com.example.ahmad.simplevolleytest.R;

public class MoviePrefsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_movie_prefs);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MoviePrefsFragment())
                .commit();
    }
}
