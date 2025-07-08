package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import library.Library;
import library.OccupiedBook;
import library.models.Reader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/reader/*")
public class ReaderBooksServlet extends HttpServlet {

    private final Library library = new Library();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if (pathInfo == null || !pathInfo.matches("/\\d+/books")) {
            out.println("<html><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>Invalid URL. Use /reader/{id}/books</p>");
            out.println("</body></html>");
            return;
        }

        try {
            String[] parts = pathInfo.split("/");
            long readerId = Long.parseLong(parts[1]);

            Reader reader = library.getReaderById(readerId);
            if (reader == null) {
                throw new RuntimeException("Reader not found with ID: " + readerId);
            }

            ArrayList<OccupiedBook> borrowed = library.getAllBorrowedBooksByReader(reader);

            out.println("<html><body>");
            out.println("<h1>Borrowed Books for Reader: " + reader.name + "</h1>");

            if (borrowed.isEmpty()) {
                out.println("<p>No borrowed books.</p>");
            } else {
                out.println("<ul>");
                for (OccupiedBook occupiedBook : borrowed) {
                    out.println("<li>");
                    out.println("<h2>" + occupiedBook.book.title + "</h2>");
                    out.println("<p>Author: " + occupiedBook.book.author + "</p>");
                    out.println("<p>Genre: " + occupiedBook.book.genre + "</p>");
                    out.println("</li>");
                }
                out.println("</ul>");
            }

            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
