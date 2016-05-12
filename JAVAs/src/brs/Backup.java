package brs;

import java.io.*;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Backup")
public class Backup extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = "";

        try {
            Vector<String> log = Database.getDB().getLog();

            FileWriter writer = new FileWriter( getServletContext().getRealPath("Backup.csv"));

            for (String l : log) {
                writer.append(l + "\n");
            }

            writer.flush();
            writer.close();

            msg = "Backup Created.";
        } catch (Exception ex) {
            msg = "Somthing wrong";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

}
