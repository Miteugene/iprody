package org.example.lesson31.library.builders;

import org.example.lesson31.library.models.Book;
import org.example.lesson31.library.models.Reader;

public class BookBuilder extends BaseBuilder<Book> {
    public BookBuilder() {
        super(new Book());
    }

    public BookBuilder whereTitleLike(String title) {
        return (BookBuilder) this.where("title", "LIKE", "%"+title+"%");
    }
}