package org.example.lesson31.library.builders;

import org.example.lesson31.library.models.Book;
import org.example.lesson31.library.models.BorrowedBook;
import org.example.lesson31.library.models.Reader;

public class BorrowedBookBuilder extends BaseBuilder<BorrowedBook> {
    public BorrowedBookBuilder() {
        super(new BorrowedBook());
    }

    public BorrowedBookBuilder whereBook(Book book) {
        return (BorrowedBookBuilder) this.where("book_id", "=", book.id);
    }

    public BorrowedBookBuilder whereReader(Reader reader) {
        return (BorrowedBookBuilder) this.where("reader_id", "=", reader.id);
    }

    public BorrowedBookBuilder whereBorrowed() {
        return (BorrowedBookBuilder) this.where("status", "=", "borrowed");
    }

    public BorrowedBookBuilder whereReturned() {
        return (BorrowedBookBuilder) this.where("status", "=", "returned");
    }
}
