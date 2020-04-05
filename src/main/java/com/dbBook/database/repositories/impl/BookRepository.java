package com.dbBook.database.repositories.impl;

import com.dbBook.database.repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookRepository implements Repository {

    public int getBookId(String input) {
        int id = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from dbbook.book where name_book = ?;");
            statement.setString(1, input);
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

    public List<Integer> getAuthorId(int book_id) {
        List<Integer> authorS_id = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select author_id from dbbook.book_author where book_id = ?;");
            statement.setInt(1, book_id);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                authorS_id.add(rs.getInt("author_id"));
            }
//            for (Integer i : authorS_id) {
//                System.out.println(i);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorS_id;
    }

    public List<String> getAuthorsFullName(List<Integer> authorS_id){
        List<String> authorS = new ArrayList<>();


        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from dbbook.author where author_id = ?;");
            for (int i = 0; i<authorS_id.size(); i++){
                statement.setInt(1, authorS_id.get(i));
                statement.execute();
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    String fullName = "";
                    fullName = rs.getString("first_name") + " ";
                    fullName += rs.getString("last_name") + " ";
                    fullName += rs.getString("patronymic")+ " ";
                    authorS.add(fullName);
//                    authorS.add(rs.getString("first_name"));
//                    authorS.add(i, rs.getString("last_name"));
//                    authorS.add(i, rs.getString("patronymic"));
                }
            }
            for (String i : authorS) {
                System.out.println(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorS;
    }

    public static void main(String[] args) {
        BookRepository q = new BookRepository();
        q.getAuthorsFullName(q.getAuthorId(q.getBookId("12 Стульев")));

    }
}
