package com.company.views;

import com.company.models.Movie;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class MovieCritic {

    public HashMap addMovie() {
        HashMap<String, String> movieInfo = new HashMap<String, String>();
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a movie title: ");
        String movieTitle = input.nextLine();
        movieInfo.put("title", movieTitle);

        System.out.print("Enter a release year: ");
        String releaseDate = input.nextLine();
        movieInfo.put("releaseDate", releaseDate);

        System.out.print("Enter a rating: ");
        String rating = input.nextLine();
        movieInfo.put("rating", rating);

        return movieInfo;
    }

    public void displayMovies(ArrayList<Movie> movies) {
        for(Movie movie : movies){
            System.out.println(movie.toString());
        }

    }
}
