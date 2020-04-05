package com.dbBook.database.repositories.impl;

import com.dbBook.database.repositories.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository implements Repository {

    public int getGenreId(String nameGenre) {               //Have Test
        int id = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from dbbook.genre where name_genre = ?;");
            statement.setString(1, nameGenre);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("genre_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Integer> getGenreId(int book_id) {          //Have Test
        List<Integer> genreS_id = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select author_id from dbbook.book_genre where book_id = ?;");
            statement.setInt(1, book_id);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                genreS_id.add(rs.getInt("genre_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genreS_id;
    }

    public List<String> getGenreName(List<Integer> genreS_id) {   //Have Test
        List<String> genreS = new ArrayList<>();

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from dbbook.genre where genre_id = ?;");
            for (int i = 0; i < genreS_id.size(); i++) {
                statement.setInt(1, genreS_id.get(i));
                statement.execute();
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    genreS.add(rs.getString("name_genre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genreS;
    }

}
