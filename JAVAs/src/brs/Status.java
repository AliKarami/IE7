package brs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.PriorityQueue;

@WebServlet("/Status")
public class Status extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action =request.getParameter("action");
        String instrument = request.getParameter("instrument");
//        PriorityQueue<Request> qu = new PriorityQueue<Request>();
        ResultSet qu = null;

        try {
            if (action==null || instrument==null || action.equals("") || instrument.equals(""))
                ;
            else if (action.equals("Buyers")) {
                ResultSet tmp = Database.getDB().get_symbol(instrument);
                if (tmp.next()) {
                    qu = Database.getDB().getBuyer(tmp.getString("symb_name"));
                } else
                    qu = null;
            } else {
                ResultSet tmp = Database.getDB().get_symbol(instrument);
                if (tmp.next()) {
                    qu = Database.getDB().getSeller(tmp.getString("symb_name"));
                } else
                    qu = null;
            }
        } catch (Exception ex) {System.err.println("err on Status");}



        request.setAttribute("SB",action);
        request.setAttribute("Queue",qu);
        request.setAttribute("instrument",instrument);
        request.getRequestDispatcher("Queue.jsp").forward(request,response);

    }

}