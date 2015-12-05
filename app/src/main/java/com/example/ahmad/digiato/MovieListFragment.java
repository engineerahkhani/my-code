package com.example.ahmad.digiato;

import android.app.Activity;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ahmad.digiato.adater.CustomListAdapter;
import com.example.ahmad.digiato.adater.GridViewImageAdapter;
import com.example.ahmad.digiato.app.AppController;
import com.example.ahmad.digiato.dataLayer.MovieDatabaseOpenHelper;
import com.example.ahmad.digiato.model.Movie;
import com.example.ahmad.simplevolleytest.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends ListFragment {



    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    // Movies json url
    private static final String url = "http://api.androidhive.info/json/movies.json";
    private Callbacks callbacks;
    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListFragment listFragmentView;
    private CustomListAdapter adapter;

    public MovieListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//slide with view pager
        GridViewImageAdapter gridViewImageAdapter = new GridViewImageAdapter(getActivity().getApplicationContext());

        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(gridViewImageAdapter);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        //datahelper init
        MovieDatabaseOpenHelper movieDatabaseOpenHelper = OpenHelperManager.getHelper(getActivity().getApplicationContext(), MovieDatabaseOpenHelper.class);
        //movieDao init
        RuntimeExceptionDao<Movie, Integer> movieDao = movieDatabaseOpenHelper.getMovieRuntimeDao();

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        adapter = new CustomListAdapter(getActivity(), movieList);
        setListAdapter(adapter);
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data

           //Toast.makeText(this.getActivity(), adapter.toString(), Toast.LENGTH_LONG).show();


            // Creating volley request obj
            JsonArrayRequest movieReq = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {


                                 //   Toast.makeText(getActivity(),"db.init",Toast.LENGTH_LONG).show();
                                    JSONObject obj = response.getJSONObject(i);
                                    Movie movie = new Movie();
                                    movie.setTitle(obj.getString("title"));
                                    movie.setThumbnailUrl(obj.getString("image"));
                                    movie.setRating(((Number) obj.get("rating"))
                                            .doubleValue());
                                    movie.setYear(obj.getInt("releaseYear"));

                                    // Genre is json array
                                    JSONArray genreArry = obj.getJSONArray("genre");
                                    ArrayList<String> genre = new ArrayList<String>();
                                    for (int j = 0; j < genreArry.length(); j++) {
                                        genre.add((String) genreArry.get(j));
                                    }
                                     movie.setGanre(genre);
                                    //tbl movie constructor:

//                                    Movie movie1 = new Movie(obj.getString("title"),
//                                            obj.getString("image"),
//                                            obj.getInt("releaseYear"),
//                                            obj.getDouble("rating"),
//                                            genre);
//								movieDao.create(movie1);

                                    // adding movie to movies array
                                    movieList.add(movie);
                                  //  Toast.makeText(getActivity(),movieDao.queryForId(2).getTitle().toString(),Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }


                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();

                }
            });

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(movieReq);


        } else {
            for (Movie movie: movieDao) {
                Movie movie1 = new Movie(movie.getTitle(),
                       movie.getThumbnailUrl(),
                       movie.getYear(),
                       movie.getRating(),
                        movie.getGanre());
                movieList.add(movie1);
            }

            adapter.notifyDataSetChanged();
            hidePDialog();

            Toast.makeText(getActivity(), R.string.is_onlie_false_error, Toast.LENGTH_LONG).show();
        }
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Movie movie = movieList.get(position);
        callbacks.OnItemSelected(movie);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.callbacks = (Callbacks) activity;

    }

    public interface Callbacks {

        public void OnItemSelected(Movie movie);
    }

}
