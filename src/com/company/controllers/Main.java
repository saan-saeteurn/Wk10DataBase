package com.company.controllers;


import com.company.models.Movie;
import com.company.views.MovieCritic;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        DBConnect db = new DBConnect("movies.db");
        db.createMoviesTable();

        MovieCritic movieCritic = new MovieCritic();
        HashMap<String, String> movieInfo = movieCritic.addMovie();
        db.addData(movieInfo.get("title"), movieInfo.get("releaseDate"), movieInfo.get("rating"));

        ArrayList<Movie> theMovies = db.getData();
        movieCritic.displayMovies(theMovies);
    }
}