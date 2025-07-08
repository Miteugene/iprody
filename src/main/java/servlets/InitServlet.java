package servlets;

import com.github.javafaker.Faker;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.Library;
import library.OccupiedBook;
import library.models.Book;
import library.models.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/init")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random random = new Random();

        Faker faker = new Faker();
        Library library = new Library();

        int bookCount = 10;
        int readerCount = 5;
        int borrowAttempts = 5;
        int returnAttempts = 15;

        List<Book> books = new ArrayList<>();
        List<Reader> readers = new ArrayList<>();

        // ---------------------------------------------------------------------
        // Создание книг
        for (int i = 0; i < bookCount; i++) {
            Book book = new Book();
            book.title = faker.book().title();
            book.author = faker.book().author();
            book.genre = faker.book().genre();
            book.published_year = faker.number().numberBetween(1950, 2024);
            book.create();
            books.add(book);
        }

        System.out.println("Created Books:");
        for (Book book : books) {
            System.out.println("- " + book.title + " by " + book.author);
        }


        // ---------------------------------------------------------------------
        // Создание читателей
        for (int i = 0; i < readerCount; i++) {
            Reader reader = new Reader();
            reader.name = faker.name().fullName();
            reader.email = faker.internet().emailAddress();
            reader.phone = faker.phoneNumber().cellPhone();
            reader.create();
            readers.add(reader);
        }

        System.out.println("\nCreated Readers:");
        for (Reader reader : readers) {
            System.out.println("- " + reader.name + " (" + reader.email + ")");
        }

        // ---------------------------------------------------------------------
        // Читатели берут книги случайным образом
        System.out.println("\nBorrowing books:");
        for (int i = 0; i < borrowAttempts; i++) {
            Reader reader = readers.get(random.nextInt(readers.size()));
            Book book = books.get(random.nextInt(books.size()));

            try {
                library.takeBook(reader, book);
                System.out.println(reader.name + " took the book: " + book.title);
            } catch (RuntimeException e) {
                System.out.println(reader.name + " failed to take " + book.title + ": " + e.getMessage());
            }
        }

        // ---------------------------------------------------------------------
        // Читатели возвращают книги случайным образом
        System.out.println("\nReturning books:");
        for (int i = 0; i < returnAttempts; i++) {
            Reader reader = readers.get(random.nextInt(readers.size()));
            Book book = books.get(random.nextInt(books.size()));

            try {
                library.returnBook(reader, book);
                System.out.println(reader.name + " return the book: " + book.title);
            } catch (RuntimeException e) {
                System.out.println(reader.name + " failed to return " + book.title + ": " + e.getMessage());
            }
        }

        // ---------------------------------------------------------------------
        // Поиск книги по названию
        String bookTitle = books.get(0).title;
        System.out.println("\nBooks with '"+bookTitle+"' in title:");
        ArrayList<Book> foundBooks = library.findBooksByTitle(bookTitle);
        for (int i = 0; i < foundBooks.size(); i++) {
            Book book = foundBooks.get(i);
            System.out.println(i + " - " + book.title);
        }

        // ---------------------------------------------------------------------
        // Список книг
        System.out.println("\nAll Book list:");
        ArrayList<Book> allBooks = library.getAllBooks();
        for (int i = 0; i < allBooks.size(); i++) {
            Book book = allBooks.get(i);
            System.out.println(i + " - " + book.title);
        }

        // ---------------------------------------------------------------------
        // Список выданных книг с инфой кто их взял
        System.out.println("\nOccupied Books:");
        ArrayList<OccupiedBook> occupiedBooks = library.getAllBorrowedBooks();
        for (int i = 0; i < occupiedBooks.size(); i++) {
            OccupiedBook occupiedBook = occupiedBooks.get(i);
            System.out.println(i + " - Book: " + occupiedBook.book.title + " Reader: " + occupiedBook.reader.name);
        }
    }
}
