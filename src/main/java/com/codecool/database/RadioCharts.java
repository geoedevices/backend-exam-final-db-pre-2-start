package com.codecool.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RadioCharts {

    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASSWORD;

    public RadioCharts(String DB_URL, String DB_USER, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public String getMostPlayedSong() {
        String sql = "SELECT song FROM music_broadcast GROUP BY song ORDER BY SUM(times_aired) DESC LIMIT 1;";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getMostActiveArtist() {
        String sql = "SELECT artist FROM music_broadcast GROUP BY artist ORDER BY COUNT(DISTINCT song) DESC LIMIT 1;";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

}
