package com.example.ahmad.digiato;

import android.os.Bundle;
import android.app.Activity;

import com.example.ahmad.simplevolleytest.R;

public class MovieDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
