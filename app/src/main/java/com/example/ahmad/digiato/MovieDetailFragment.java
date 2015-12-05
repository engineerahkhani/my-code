package com.example.ahmad.digiato;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.ahmad.digiato.app.AppController;
import com.example.ahmad.digiato.model.Movie;
import com.example.ahmad.simplevolleytest.R;

public class MovieDetailFragment extends Fragment {
    Movie movie;

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(Movie.MOVIE_TITLE)) {
            movie = new Movie(bundle);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);


        //set image using volley
        NetworkImageView imageView = (NetworkImageView) rootView.findViewById(R.id.imageView);
        String url = movie.getThumbnailUrl().trim();

        ImageLoader imageLoader = AppController.getInstance()
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);

        //end of volley
        TextView txtTilte = (TextView) rootView.findViewById(R.id.txtMovieTitle);
        txtTilte.setText(movie.getTitle());

        TextView txtYear = (TextView) rootView.findViewById(R.id.txtMovieYear);
        txtYear.setText(Integer.toString(movie.getYear()));

        TextView txtRating = (TextView) rootView.findViewById(R.id.txtMovieRating);
        String string = Double.toString(movie.getRating());
        txtRating.setText(string);

        TextView txtGenre = (TextView) rootView.findViewById(R.id.txtMovieGenre);
       // txtGenre.setText(movie.getGanre().get(1));
        for (int i = 0; i < movie.getGanre().size(); i++) {
            txtGenre.append(movie.getGanre().get(i));
            txtGenre.append("\n");
        }

        return rootView;
    }
}
