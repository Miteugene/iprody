package com.example.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import com.example.library.Library;
import com.example.library.OccupiedBook;
import com.example.library.models.Book;
import com.example.library.models.Reader;

import java.util.ArrayList;

@WebService
public class LibraryWebService {

    private final Library library = new Library();

    @WebMethod
    public Reader addReader(
            @WebParam(name = "name") String name,
            @WebParam(name = "email") String email,
            @WebParam(name = "phone") String phone
    ) {
        return library.addReader(name, email, phone);
    }

    @WebMethod
    public Book addBook(
            @WebParam(name = "title") String title,
            @WebParam(name = "author") String author,
            @WebParam(name = "genre") String genre,
            @WebParam(name = "year") int year
    ) {
        return library.addBook(title, author, genre, year);
    }

    @WebMethod
    public String takeBook(
            @WebParam(name = "readerId") long readerId,
            @WebParam(name = "bookId") long bookId
    ) {
        Reader reader = library.getReaderById(readerId);
        if (reader == null) {
            return "Error: Reader not found with ID: " + readerId;
        }

        Book book = library.getAllBooks().stream()
                .filter(b -> b.id == bookId)
                .findFirst()
                .orElse(null);

        if (book == null) {
            return "Error: Book not found with ID: " + bookId;
        }

        try {
            library.takeBook(reader, book);
            return "Book reserved successfully!";
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }

    @WebMethod
    public ArrayList<OccupiedBook> getBorrowedBooksByReader(
            @WebParam(name = "readerId") long readerId
    ) {
        Reader reader = library.getReaderById(readerId);
        if (reader == null) {
            return new ArrayList<>();
        }
        return library.getAllBorrowedBooksByReader(reader);
    }
}
