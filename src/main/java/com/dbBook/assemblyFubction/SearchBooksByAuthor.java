package com.dbBook.assemblyFubction;

import com.dbBook.database.repositories.Repository;
import com.dbBook.database.repositories.impl.AuthorRepository;
import com.dbBook.database.repositories.impl.BookRepository;

import java.util.List;

public class SearchBooksByAuthor implements Repository {

    private AuthorRepository author = new AuthorRepository();
    private BookRepository book = new BookRepository();

    public List<String> searchBooksByAuthor(String input){
        return book.getBookName(book.getBooksIdFromAuthorId(author.getAuthorId(input)));
    }
}
