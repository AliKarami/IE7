package brs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.PriorityQueue;

@WebServlet("/Status")
public class Status extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action =request.getParameter("action");
        String instrument = request.getParameter("instrument");
        PriorityQueue<Request> qu = new PriorityQueue<Request>();

        if (action==null || instrument==null || action.equals("") || instrument.equals(""))
            ;
        else if (action.equals("Buyers")) {
            qu = Database.getDB().get_symbol(instrument).getBuyer();
        } else {
            qu = Database.getDB().get_symbol(instrument).getSeller();
        }

        request.setAttribute("SB",action);
        request.setAttribute("Queue",qu);
        request.setAttribute("instrument",instrument);
        request.getRequestDispatcher("Queue.jsp").forward(request,response);

    }

}