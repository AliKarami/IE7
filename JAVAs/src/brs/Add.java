package brs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SignUp")
public class Add extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String msg;

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String family = request.getParameter("family");

        if (id==null || name==null || family==null) {
                msg = "Mismatched Parameters.";
        }
        else if(id=="" || name=="" || family==""){
                msg = "Some Parameter are Empty.";
        }
        else if (Database.getDB().add_customer(Integer.parseInt(id),name,family)) {
                msg = "Successfuly Added.";
        }
        else {
                msg = "User ID has been Already Exists";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("SignUp.jsp").forward(request,response);

    }

}
