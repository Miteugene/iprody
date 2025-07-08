package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import library.Library;
import library.models.Reader;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reader/add")
public class ReaderAddServlet extends HttpServlet {

    private final Library library = new Library();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            Reader reader = library.addReader(name, email, phone);

            out.println("<html><body>");
            out.println("<h1>Reader Added</h1>");
            out.println("<p>ID: " + reader.id + "</p>");
            out.println("<p>Name: " + reader.name + "</p>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
