package brs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("Approve")
public class approveSymbol extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action =request.getParameter("action");
        String symbol_name = request.getParameter("name");
        String msg = "";

        if (action==null || symbol_name==null || action.equals("") || symbol_name.equals(""))
            msg = "fucked up error";
        else if (action.equals("Approve")) {
            Database.getDB().add_symbol();
            msg = "Deposition Approved";
        } else {
            Database.getDB().decDR(Integer.parseInt(cstmrid_),Integer.parseInt(amount_));
            msg = "Deposition Decliend";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("Approve.jsp").forward(request,response);

    }

}