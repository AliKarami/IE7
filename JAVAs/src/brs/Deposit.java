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

        String amount = request.getParameter("amount");

        if (amount==null) {
            msg = "Mismatched Parameters.";
        }
        else if(amount==""){
            msg = "Some Parameter are Empty.";
        }
        else if (Database.getDB().add2DepReqs(Database.getDB().LoggedInID,Integer.parseInt(amount))) {
            msg = "Deposit Request Successfuly Added for Admin Approvement.";
        }
        else {
            msg = "User ID was incorrect";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("DepositWithdraw.jsp").forward(request,response);

    }

}