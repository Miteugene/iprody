package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.BorrowedBook;
import com.example.library.models.Reader;

import java.util.ArrayList;

public class Library {

    public BorrowedBook takeBook(Reader reader, Book book) {
        BorrowedBook borrowedBook = BorrowedBook.query()
                .whereReader(reader)
                .whereBook(book)
                .first();

        if (borrowedBook != null && borrowedBook.isBorrowed()) {
            throw new RuntimeException("The book has already been taken");
        }

        borrowedBook = new BorrowedBook();
        borrowedBook.book_id = book.id;
        borrowedBook.reader_id = reader.id;
        borrowedBook.borrow_date = new java.sql.Date(System.currentTimeMillis());
        borrowedBook.status = "borrowed";

        return borrowedBook.create();
    }

    public boolean returnBook(Reader reader, Book book) {
        BorrowedBook borrowedBook = BorrowedBook.query()
                .whereReader(reader)
                .whereBook(book)
                .first();

        if (borrowedBook == null) {
            throw new RuntimeException("You don't have this book.");
        }

        if (borrowedBook.isReturned()) {
            throw new RuntimeException("The book has already been returned");
        }

        borrowedBook.status = "returned";
        borrowedBook.return_date = new java.sql.Date(System.currentTimeMillis());

        return borrowedBook.update() == 1;
    }

    public ArrayList<Book> getAllBooks() {
        return Book.query().get();
    }

    public ArrayList<Book> findBooksByTitle(String title) {
        return Book.query().whereTitleLike(title).get();
    }

    public ArrayList<Reader> getAllReaders() {
        return Reader.query().get();
    }

    public Reader getReaderById(long id) {
        return Reader.query().whereId(id).first();
    }

    public Book addBook(String title, String author, String genre, Integer publishedYear) {
        Book book = new Book();
        book.title = title;
        book.author = author;
        book.genre = genre;
        book.published_year = publishedYear;
        return book.create();
    }

    public Reader addReader(String name, String email, String phone) {
        Reader reader = new Reader();
        reader.name = name;
        reader.email = email;
        reader.phone = phone;
        return reader.create();
    }

    public ArrayList<OccupiedBook> getAllBorrowedBooksByReader(Reader reader) {
        ArrayList<OccupiedBook> result = new ArrayList<>();

        ArrayList<BorrowedBook> borrowedBooks = BorrowedBook.query()
                .whereBorrowed()
                .whereReader(reader)
                .get();

        for (BorrowedBook borrowed : borrowedBooks) {
            Book book = Book.query().where("id", "=", borrowed.book_id).first();

            if (book != null) {
                OccupiedBook occupiedBook = new OccupiedBook();
                occupiedBook.book = book;
                occupiedBook.reader = reader;
                result.add(occupiedBook);
            }
        }

        return result;
    }

    public ArrayList<OccupiedBook> getAllBorrowedBooks() {
        ArrayList<OccupiedBook> result = new ArrayList<>();

        ArrayList<BorrowedBook> borrowedBooks = BorrowedBook.query()
                .whereBorrowed()
                .get();

        for (BorrowedBook borrowed : borrowedBooks) {
            // Да, не очень красиво и лучше сделать один запрос whereIn, но я и так уже сильно усложнил код...
            // Да и понятно что скорей всего тут вообще хотели чтобы я join сделал, но я рил уже перемудрил со своей ORM
            Book book = Book.query().where("id", "=", borrowed.book_id).first();
            Reader reader = Reader.query().where("id", "=", borrowed.reader_id).first();

            if (book != null && reader != null) {
                OccupiedBook occupiedBook = new OccupiedBook();
                occupiedBook.book = book;
                occupiedBook.reader = reader;
                result.add(occupiedBook);
            }
        }

        return result;
    }
}
