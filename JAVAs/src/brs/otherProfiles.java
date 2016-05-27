package brs;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/OtherProfile")
public class otherProfiles extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = "";

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            ResultSet rs = Database.getDB().get_user(id);
            if (rs.next()) {
                request.setAttribute("id",request.getParameter("id"));
                request.getRequestDispatcher("Profile.jsp").forward(request,response);
                return;
            }else{
                msg = "there is no such user";
            }

        }catch(Exception ex){
            System.err.println("get user error in login");
            msg = "server Error for Profile";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("OtherProfile.jsp").forward(request,response);

    }

}

