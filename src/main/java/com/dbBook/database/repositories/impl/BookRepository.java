package com.dbBook.database.repositories.impl;

import com.dbBook.database.repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookRepository implements Repository {

    public int getBookId(String nameBook) {  //Have Test
        int id = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from dbbook.book where name_book = ?;");
            statement.setString(1, nameBook);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("book_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Integer> getBooksIdFromAuthorId(int author_id) {  //Have Test
        List<Integer> bookS_id = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select book_id from dbbook.book_author where author_id = ?;");
            statement.setInt(1, author_id);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                bookS_id.add(rs.getInt("book_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookS_id;
    }

    public List<Integer> getBooksIdFromGenreId(int genre_id) {  //Have Test
        List<Integer> bookS_id = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select book_id from dbbook.book_genre where genre_id = ?;");
            statement.setInt(1, genre_id);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                bookS_id.add(rs.getInt("book_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookS_id;
    }

    public List<String> getBookName(List<Integer> bookS_id) {   //Have Test
        List<String> bookS = new ArrayList<>();

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select name_book from dbbook.book where book_id = ?;");
            for (int i = 0; i < bookS_id.size(); i++) {
                statement.setInt(1, bookS_id.get(i));
                statement.execute();
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    bookS.add(rs.getString("name_book"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookS;
    }
}
