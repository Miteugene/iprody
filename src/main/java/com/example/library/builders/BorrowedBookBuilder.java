package com.example.library.builders;

import com.example.library.models.Book;
import com.example.library.models.BorrowedBook;
import com.example.library.models.Reader;

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
