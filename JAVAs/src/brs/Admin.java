package brs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/requests")
public class Admin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action =request.getParameter("action");
        String cstmrid_ = request.getParameter("cstmrid_");
        String amount_ = request.getParameter("amount_");
        String msg = "";

        if (action==null || cstmrid_==null || amount_==null || action.equals("") || cstmrid_.equals("") || amount_.equals(""))
            msg = "";
        else if (action.equals("Approve")) {
            Database.getDB().appDR(Integer.parseInt(cstmrid_),Integer.parseInt(amount_));
            msg = "Deposition Approved";
        } else {
            Database.getDB().decDR(Integer.parseInt(cstmrid_),Integer.parseInt(amount_));
            msg = "Deposition Decliend";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("../Admin.jsp").forward(request,response);

    }

}