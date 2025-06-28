package org.example.lesson31.library.models;

import org.example.lesson31.library.builders.BookBuilder;

public class Book extends BaseModel {
    public Integer id;
    public String title;
    public String author;
    public Integer published_year;
    public String genre;

    public Book() {
        this.table = "books";
    }

    public static BookBuilder query() {
        return new BookBuilder();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published_year=" + published_year +
                ", genre='" + genre + '\'' +
                '}';
    }
}
