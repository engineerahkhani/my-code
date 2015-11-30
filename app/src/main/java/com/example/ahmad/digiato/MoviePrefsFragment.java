package com.example.ahmad.digiato;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.ahmad.simplevolleytest.R;

/**
 * Created by AHMAD on 11/30/2015.
 */
public class MoviePrefsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
