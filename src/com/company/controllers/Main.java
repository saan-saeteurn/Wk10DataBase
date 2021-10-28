package com.company.controllers;

import com.company.models.Movie;
import com.company.views.MovieCritic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        DBConnect db = new DBConnect("movies.db");
        db.createMoviesTable();
        ArrayList<Movie> movieList;
        int option = 0;
        MovieCritic movieCritic = new MovieCritic();

        // Try to handle bad user input option other than 1 and 2.
        try {
            option = movieCritic.getMovieOptions();
        } catch (InputMismatchException e) {
            option = 0;
        }

        if(option == 1) {
            HashMap<String, String> movieInfo = movieCritic.getNewMovieInfo();
            db.addData(movieInfo.get("title"), movieInfo.get("releaseDate"), movieInfo.get("rating"));
            movieList = db.getData();
            movieCritic.displayMovies(movieList, false);
        } else if(option == 2) {
            String searchTerm = movieCritic.searchMovie();
            if (searchTerm != null) {
                movieList = db.searchMovie(searchTerm);
                if (movieList.size() != 0)
                    movieCritic.displayMovies(movieList, true);
                else
                    movieCritic.notFound(searchTerm);
            } else
                movieCritic.badInput();
        } else
            movieCritic.exit();
    }
}