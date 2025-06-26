package org.example.lesson31.library;

import org.example.lesson31.library.models.Book;
import org.example.lesson31.library.models.BorrowedBook;
import org.example.lesson31.library.models.Reader;

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
