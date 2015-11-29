package com.example.ahmad.digiato.model;

import android.os.Bundle;

import java.util.ArrayList;

public class Movie {
	private String title, thumbnailUrl;
	private int year;
	private double rating;
	private ArrayList<String> genre;

	//	constants for field references
	public static final String MOVIE_TITLE = "title";
	public static final String THUMBNAILURL = "thumbnailUrl";
	public static final String YEAR = "year";
	public static final String RATING = "rating";
	public static final String GENRE = "GENRE";
	public Movie() {
	}

	public Movie(String name, String thumbnailUrl, int year, double rating,
			ArrayList<String> genre) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.year = year;
		this.rating = rating;
		this.genre = genre;
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

	public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}
	//	Create from a bundle
	public Movie(Bundle b) {
		if (b != null) {
			this.title = b.getString(MOVIE_TITLE);
			this.thumbnailUrl = b.getString(THUMBNAILURL);
			this.year = b.getInt(YEAR);
			this.rating = b.getDouble(RATING);
			this.genre = b.getStringArrayList(GENRE);
		}
	}

	//	Package data for transfer between activities
	public Bundle toBundle() {
		Bundle b = new Bundle();
		b.putString(MOVIE_TITLE, this.title);
		b.putString(THUMBNAILURL, this.thumbnailUrl);
		b.putDouble(RATING, this.rating);
		b.putInt(YEAR, this.year);
		b.putStringArrayList(RATING,this.genre);
		return b;
	}

	//	Output flower data
	@Override
	public String toString() {
		return title;
	}
}
