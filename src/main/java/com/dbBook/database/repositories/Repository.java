package com.dbBook.database.repositories;


import java.sql.Connection;
import com.dbBook.database.MyConnectionFactory;

public interface Repository {
    default Connection getConnection() {
        return MyConnectionFactory.getConnection();
    }
}
