package com.example.library.builders;

import com.example.library.models.Book;

public class BookBuilder extends BaseBuilder<Book> {
    public BookBuilder() {
        super(new Book());
    }

    public BookBuilder whereTitleLike(String title) {
        return (BookBuilder) this.where("title", "LIKE", "%"+title+"%");
    }
}