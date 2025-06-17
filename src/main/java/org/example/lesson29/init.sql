CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    published_year INT CHECK (published_year > 0),
    genre VARCHAR(100)
);

CREATE TABLE readers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(15) UNIQUE
);

CREATE TABLE borrowed_books (
    id SERIAL PRIMARY KEY,
    book_id INT NOT NULL,
    reader_id INT NOT NULL,
    borrow_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) CHECK (status IN ('borrowed', 'returned')),
    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
        REFERENCES books(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_reader
        FOREIGN KEY (reader_id)
        REFERENCES readers(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_books_title ON books(title);

CREATE INDEX idx_borrowed_books_reader_status
ON borrowed_books(reader_id)
WHERE status = 'borrowed';
