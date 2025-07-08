package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import library.Library;
import library.models.Book;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/book/add")
public class BookAddServlet extends HttpServlet {

    private final Library library = new Library();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String genre = req.getParameter("genre");
        Integer year = Integer.parseInt(req.getParameter("year"));

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            Book book = library.addBook(title, author, genre, year);

            out.println("<html><body>");
            out.println("<h1>Book Added</h1>");
            out.println("<p>ID: " + book.id + "</p>");
            out.println("<p>Title: " + book.title + "</p>");
            out.println("<p>Author: " + book.author + "</p>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
