package com.dbBook.assemblyFubction;

import com.dbBook.database.repositories.Repository;
import com.dbBook.database.repositories.impl.BookRepository;
import com.dbBook.database.repositories.impl.GenreRepository;

import java.util.List;

public class SearchGenresByBook implements Repository {
    private BookRepository book = new BookRepository();
    private GenreRepository genre = new GenreRepository();

    public List<String> searchGenresByBook(String input){
        return genre.getGenreName(genre.getGenreId(book.getBookId(input)));
    }
}
