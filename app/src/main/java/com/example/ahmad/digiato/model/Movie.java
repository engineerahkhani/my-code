package com.example.ahmad.digiato.model;

import android.os.Bundle;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@DatabaseTable(tableName = "tblMovie")
public class Movie {
    //	constants for field references
    public static final String MOVIE_TITLE = "title";
    public static final String THUMBNAILURL = "thumbnailUrl";
    public static final String YEAR = "year";
    public static final String RATING = "rating";
    public static final String GANRE = "Ganre";
    //TODO after compelite code work on id filed
    @DatabaseField(generatedId = true, index = true)
    private int id;
    @DatabaseField()
    private String title;
    @DatabaseField()
    private String thumbnailUrl;
    @DatabaseField()
    private int year;
    @DatabaseField()
    private double rating;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> ganre;

    public Movie() {
    }

    public Movie(String name, String thumbnailUrl,
                 int year, double rating,
                 ArrayList<String> ganre) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.year = year;
        this.rating = rating;
        this.ganre = ganre;
    }

    //	Create from a bundle
    public Movie(Bundle b) {
        if (b != null) {
            this.title = b.getString(MOVIE_TITLE);
            this.thumbnailUrl = b.getString(THUMBNAILURL);
            this.year = b.getInt(YEAR);
            this.rating = b.getDouble(RATING);
            this.ganre = b.getStringArrayList(GANRE);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getGanre() {
        return ganre;
    }

    public void setGanre(ArrayList<String> ganre) {
        this.ganre = ganre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", ganre=" + ganre +
                '}';
    }


    //	Package data for transfer between activities
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(MOVIE_TITLE, this.title);
        b.putString(THUMBNAILURL, this.thumbnailUrl);
        b.putDouble(RATING, this.rating);
        b.putInt(YEAR, this.year);
        b.putStringArrayList(GANRE, this.ganre);
        return b;
    }
}
