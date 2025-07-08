package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import library.Library;
import library.models.Book;
import library.models.Reader;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {

    private final Library library = new Library();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long readerId = Long.parseLong(req.getParameter("reader_id"));
        long bookId = Long.parseLong(req.getParameter("book_id"));

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            Reader reader = library.getReaderById(readerId);
            Book book = library.getAllBooks().stream()
                    .filter(b -> b.id == bookId)
                    .findFirst()
                    .orElse(null);

            if (reader == null || book == null) {
                throw new RuntimeException("Invalid reader or book ID");
            }

            library.takeBook(reader, book);

            out.println("<html><body>");
            out.println("<h1>Book Reserved</h1>");
            out.println("<p>Reader ID: " + reader.id + "</p>");
            out.println("<p>Book ID: " + book.id + "</p>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
