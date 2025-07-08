package com.example.library.models;

import com.example.library.builders.BorrowedBookBuilder;

import java.sql.Date;

public class BorrowedBook extends BaseModel {
    public Integer id;
    public Integer book_id;
    public Integer reader_id;
    public Date borrow_date;
    public Date return_date;
    public String status; // По-хорошему бы сделать enum но мне лень кастить при работе с БД пока

    public BorrowedBook() {
        this.table = "borrowed_books";
    }

    public static BorrowedBookBuilder query() {
        return new BorrowedBookBuilder();
    }

    public boolean isReturned() {
        return "returned".equalsIgnoreCase(status);
    }

    public boolean isBorrowed() {
        return "borrowed".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", reader_id=" + reader_id +
                ", borrow_date=" + borrow_date +
                ", return_date=" + return_date +
                ", status='" + status + '\'' +
                '}';
    }
}
