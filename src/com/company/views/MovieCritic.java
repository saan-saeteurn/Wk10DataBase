package com.company.views;

import com.company.models.Movie;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Provide user options to add or search for movies.
 *
 * @author Saan Saeteurn
 * @since 2021-10-17
 */
public class MovieCritic {

    public int getMovieOptions() {
        System.out.println("Welcome to Movie Reviews!");
        System.out.println("Enter 1 to ADD a new movie.");
        System.out.println("Enter 2 to to SEARCH for a movie.");
        System.out.println("Enter ANY OTHER key to exit.\n");
        System.out.print("Enter your option listed above: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public String searchMovie() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a movie title to search: ");
        return input.nextLine();
    }

    public void notFound(String searchTerm) {
        System.out.println("Sorry, " + searchTerm + " was not found in the database.");
    }

    public void badInput() {
        System.out.println("Sorry, you have an invalid search input.");
    }

    public void exit() {
        System.out.println("Thank you for the review, exiting program.");
    }

    // Key: database table column or field.
    // Value: User input for each movie detail.
    public HashMap getNewMovieInfo() {
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

    public void displayMovies(ArrayList<Movie> movies, @NotNull Boolean fromSearch) {
        if (fromSearch)
            System.out.println("Here is the result of movie(s) from your search:");
        else
            System.out.println("Here are all the movies in the database:");

        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }
}
