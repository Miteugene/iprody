-- Задание 2: Операции с данными (DML)

-- INSERT Книги
INSERT INTO books (title, author, published_year, genre) VALUES
('1984', 'George Orwell', 1949, 'dystopia'),
('Dune', 'Frank Herbert', 1965, 'science fiction'),
('Harry Potter and the Philosophers Stone', 'J.K. Rowling', 1997, 'fantasy'),
('The Hobbit', 'J.R.R. Tolkien', 1937, 'fantasy'),
('Collected Works of Lenin', 'Vladimir Lenin', 1960, 'political theory');

-- INSERT Читатели
INSERT INTO readers (name, email, phone) VALUES
('Petr Ivanov',    'petr@example.com',    '+1234567890'),
('Ivan Sidorov',   'ivan@example.com',    '+2345678901'),
('Mikhail Petrov', 'mikhail@example.com', '+3456789012');

-- INSERT: Кто что брал
INSERT INTO borrowed_books (book_id, reader_id, borrow_date, return_date, status) VALUES
(1, 1, '2025-06-01', NULL, 'borrowed'),
(2, 1, '2025-05-15', '2025-06-01', 'returned'),
(3, 2, '2025-06-10', NULL, 'borrowed'),
(4, 3, '2025-05-20', '2025-06-10', 'returned'),
(5, 2, '2025-06-15', NULL, 'borrowed');

-- UPDATE: изменить published_year
UPDATE books
SET published_year = 1950
WHERE title = '1984';

-- UPDATE: Отмечаем что книгу вернули
UPDATE borrowed_books
SET status = 'returned', return_date = CURRENT_DATE
WHERE book_id = 1 AND reader_id = 1;

-- DELETE: Удаляем читателя
DELETE FROM readers WHERE id = 3;

-- DELETE: Удаляем все записи для конкретной книги
DELETE FROM borrowed_books WHERE book_id = 2;

-- SELECT: Проверяем все записи
SELECT * FROM books;
SELECT * FROM readers;
SELECT * FROM borrowed_books;


-- Задание 3: Работа с транзакциями (TCL)

-- ROLLBACK: Выполняем магию но без коммита
BEGIN;
INSERT INTO books (title, author, published_year, genre) VALUES ('Animal Farm', 'George Orwell', 1945, 'allegory');
INSERT INTO borrowed_books (book_id, reader_id, borrow_date, status) VALUES (6, 1, CURRENT_DATE, 'borrowed');
ROLLBACK;

-- Проверяем что ничего не добавилось
SELECT * FROM books WHERE title = 'Animal Farm';
SELECT * FROM borrowed_books WHERE book_id = 6;

-- COMMIT: То же самое но с коммитом
BEGIN;
INSERT INTO books (title, author, published_year, genre) VALUES ('Animal Farm', 'George Orwell', 1945, 'allegory');
INSERT INTO borrowed_books (book_id, reader_id, borrow_date, status) VALUES (6, 1, CURRENT_DATE, 'borrowed');
COMMIT;

-- Проверяем что все добавилось
SELECT * FROM books WHERE title = 'Animal Farm';
SELECT * FROM borrowed_books WHERE book_id = 6;


-- Задание 4: Составление запросов с JOIN, WHERE, GROUP BY и HAVING

-- INNER JOIN: Название книги, автора, идентификатор читателя и дату выдачи. Добавить условие (WHERE), чтобы выбрать только записи со статусом borrowed.
SELECT b.title, b.author, bb.reader_id, bb.borrow_date
FROM books b
INNER JOIN borrowed_books bb ON b.id = bb.book_id
WHERE bb.status = 'borrowed';

-- GROUP BY + COUNT: количество книг, выданных каждым читателем, с использованием GROUP BY и функции COUNT().
SELECT r.id AS reader_id, r.name, COUNT(bb.id) AS books_borrowed
FROM readers r
JOIN borrowed_books bb ON r.id = bb.reader_id
GROUP BY r.id, r.name;

-- HAVING: читателей, у которых количество выданных книг больше двух, с использованием HAVING.
SELECT r.id AS reader_id, r.name, COUNT(bb.id) AS books_borrowed
FROM readers r
JOIN borrowed_books bb ON r.id = bb.reader_id
GROUP BY r.id, r.name
HAVING COUNT(bb.id) > 2;

-- LEFT JOIN: список всех книг и соответствующую информацию о выдаче (если такая имеется) для книг определенного жанра
SELECT b.title, b.author, b.genre, bb.reader_id, bb.borrow_date, bb.status
FROM books b
LEFT JOIN borrowed_books bb ON b.id = bb.book_id
WHERE b.genre = 'science fiction';
