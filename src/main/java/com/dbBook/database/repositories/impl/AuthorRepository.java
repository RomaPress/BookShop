package com.dbBook.database.repositories.impl;

import com.dbBook.database.repositories.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements Repository {

    public int getAuthorId(String fullName) {     //Have Test
        String[] nameLine = new String[3];
        int k = 0;
        for (String i : fullName.split(" ")) {
            nameLine[k] = i;
            k++;
        }
        int id = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from dbbook.author where first_name = ? and  last_name = ? and  patronymic = ?;");
            statement.setString(1, nameLine[0]);
            statement.setString(2, nameLine[1]);
            statement.setString(3, nameLine[2]);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("author_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Integer> getAuthorId(int book_id) {         //Have Test
        List<Integer> authorS_id = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select author_id from dbbook.book_author where book_id = ?;");
            statement.setInt(1, book_id);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                authorS_id.add(rs.getInt("author_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorS_id;
    }

    public List<String> getAuthorsFullName(List<Integer> authorS_id) {   //Have Test
        List<String> authorS = new ArrayList<>();

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from dbbook.author where author_id = ?;");
            for (int i = 0; i < authorS_id.size(); i++) {
                statement.setInt(1, authorS_id.get(i));
                statement.execute();
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    StringBuilder fullName = new StringBuilder(rs.getString("first_name"));
                    fullName.append(" ").append(rs.getString("last_name")).append(" ").append(rs.getString("patronymic"));
                    authorS.add(fullName.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorS;
    }


}
