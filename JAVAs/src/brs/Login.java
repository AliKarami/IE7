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
                    HttpSession session = request.getSession();
                    int userId = Integer.parseInt(id);
                    session.setAttribute("id", userId);
                    //setting session to expiry in 30 mins
                    session.setMaxInactiveInterval(30 * 60);
                    Cookie userName = new Cookie("id", id);
                    userName.setMaxAge(30 * 60);
                    response.addCookie(userName);

                    msg = "Successfuly Added.";
                    request.setAttribute("Message", msg);
                    response.sendRedirect("index.jsp");
                    return;
                } else {
                    msg = "User ID has been Already Exists";
                }
            }catch(Exception ex){System.err.println("get user error in login");}
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("SignUp.jsp").forward(request,response);

    }

}
