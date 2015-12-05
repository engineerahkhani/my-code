package com.example.ahmad.digiato.dataLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ahmad.digiato.model.Movie;
import com.example.ahmad.simplevolleytest.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by AHMAD on 12/3/2015.
 */
public class MovieDatabaseOpenHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "movies_db";
    private static final int DATABASE_VERSION = 1;
    /**
     * The data access object used to interact with the Sqlite database to do C.R.U.D operations.
     */
    private Dao<Movie, Integer> movieDao = null;


    private RuntimeExceptionDao<Movie, Integer> movieRuntimeDao = null;


    public MovieDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, Movie.class);


        } catch (SQLException e) {
            Log.e(MovieDatabaseOpenHelper.class.getName(), "Unable to create database");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Movie.class, true);


            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(MovieDatabaseOpenHelper.class.getName(), "Unable to create database");
        }

    }

    public Dao<Movie, Integer> getMovieDao() throws SQLException {
        if (movieDao == null)
            movieDao = getDao(Movie.class);
        return movieDao;
    }


    public RuntimeExceptionDao<Movie, Integer> getMovieRuntimeDao() {
        if (movieRuntimeDao == null)
            movieRuntimeDao = getRuntimeExceptionDao(Movie.class);
        return movieRuntimeDao;
    }


}
