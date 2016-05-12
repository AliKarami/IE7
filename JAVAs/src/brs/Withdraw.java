package brs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
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
        else switch (Database.getDB().withdraw_customer(Integer.parseInt(id),Integer.parseInt(amount))) {
                case 0:
                    msg = "Withdraw Successfuly done.";
                    break;
                case -1:
                    msg = "Not Enough Money.";
                    break;
                case -2:
                    msg = "Unknown User id.";
                    break;
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("DepositWithdraw.jsp").forward(request,response);

    }

}