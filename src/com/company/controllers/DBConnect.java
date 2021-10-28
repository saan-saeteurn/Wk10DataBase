package com.company.controllers;

import com.company.models.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;

public class DBConnect {

    private String dbName;
    private String url;

    public DBConnect(String dbName) {
        this.dbName = dbName;
        this.url = "jdbc:sqlite:/Users/Saan/Projects/java/libs/sqlite/db/" + dbName;
    }

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(url);
    }

    public void createMoviesTable(){

        String sql = "CREATE TABLE IF NOT EXISTS movies (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	title text NOT NULL,\n"
                + "	releaseDate text NOT NULL,\n"
                + " rating text NOT NULL\n"
                + ");";

        try {
            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addData(String title, String releaseDate, String rating){

        String sql = "INSERT INTO movies(title, releaseDate, rating) VALUES ('" + title + "', '" + releaseDate + "', '" + rating + "');";

        try {
            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute(sql);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movie> getData(){
        String sql = "SELECT id, title, releaseDate, rating FROM movies";
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try {
            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            ResultSet movies = statement.executeQuery(sql);
            while(movies.next())
            {
                int id = movies.getInt("id");
                String title = movies.getString("title");
                String releaseDate = movies.getString("releaseDate");
                String rating = movies.getString("rating");
                Movie movie = new Movie(id, title, releaseDate, rating);
                movieList.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieList;
    }

    public ArrayList<Movie> searchMovie(String searchTerm) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        PreparedStatement preparedStmt = null;
        String query = "SELECT id, title, releaseDate, rating FROM movies WHERE title = ?";

        try {
            Connection conn = getConnection();
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, searchTerm);
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String releaseDate = rs.getString("releaseDate");
                String rating = rs.getString("rating");
                Movie movie = new Movie(id, title, releaseDate, rating);
                movieList.add(movie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieList;
    }
}
