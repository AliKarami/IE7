package brs;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/Profile")
public class showProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = "";

        int id = Database.getDB().LoggedInID;

        try {
                ResultSet rs = Database.getDB().get_user(id);
                if (rs.next()) {
                    request.setAttribute("name",rs.getString("name"));
                    request.setAttribute("family",rs.getString("family"));
                    request.setAttribute("fund",rs.getString("fund"));
                    request.setAttribute("role",rs.getString("role"));
                }

        }catch(Exception ex){
            System.err.println("get user error in login");
            msg = "server Error for Profile";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("Profile.jsp").forward(request,response);

    }

}


