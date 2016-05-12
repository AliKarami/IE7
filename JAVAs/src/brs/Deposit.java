package brs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = "";

        String id = request.getParameter("id");
        String amount = request.getParameter("amount");

        if (id==null || amount==null) {
            msg = "Mismatched Parameters.";
        }
        else if(id=="" || amount==""){
            msg = "Some Parameter are Empty.";
        }
        else if (Database.getDB().add2DepReqs(Integer.parseInt(id),Integer.parseInt(amount))) {
            msg = "Deposit Request Successfuly Added for Admin Approvement.";
        }
        else {
            msg = "User ID was incorrect";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("DepositWithdraw.jsp").forward(request,response);

    }

}