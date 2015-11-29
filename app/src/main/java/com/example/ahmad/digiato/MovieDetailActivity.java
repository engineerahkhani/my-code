package com.example.ahmad.digiato;

import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;

import com.example.ahmad.simplevolleytest.R;

public class MovieDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);


        if(savedInstanceState == null){

            MovieDetailFragment fragment= new MovieDetailFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.frame_movie_detail, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
