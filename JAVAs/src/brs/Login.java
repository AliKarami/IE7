package brs;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/SignIn")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = "";

        String id = request.getParameter("id");

        if (id==null) {
            msg = "Mismatched Parameters.";
        }
        else if(id==""){
            msg = "Some Parameter are Empty.";
        }
        else {
            try {
                if (Database.getDB().get_user(Integer.parseInt(id)).next()) {
                    Database.getDB().logInUser(Integer.parseInt(id));
                    msg = "Logged In Successfully";
                } else {
                    msg = "User ID has been Already Exists";
                }
            }catch(Exception ex){System.err.println("get user error in login");}
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

}
