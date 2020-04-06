package com.dbBook.assemblyFubction;

import com.dbBook.database.repositories.Repository;
import com.dbBook.database.repositories.impl.AuthorRepository;
import com.dbBook.database.repositories.impl.BookRepository;

import java.util.List;

public class SearchAuthorsByBook implements Repository {

    private AuthorRepository author = new AuthorRepository();
    private BookRepository book = new BookRepository();

    public List<String> searchAuthorsByBook(String input) {
        return author.getAuthorsFullName(author.getAuthorId(book.getBookId(input)));
    }
}
