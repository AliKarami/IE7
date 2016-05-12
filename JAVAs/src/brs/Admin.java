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
        String ReqNum = request.getParameter("ReqNum");
        String msg = "";
        if (action==null || ReqNum==null || action.equals("") || ReqNum.equals(""))
            msg = "";
        else if (action.equals("Approve")) {
            Database.getDB().appDR(Integer.parseInt(ReqNum));
            msg = "Deposition Approved";
        } else {
            Database.getDB().decDR(Integer.parseInt(ReqNum));
            msg = "Deposition Decliend";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("../Admin.jsp").forward(request,response);

    }

}