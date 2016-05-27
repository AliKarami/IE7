package brs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SymbolAdding")
public class addNewSymbol extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg;

        String symbol_name = request.getParameter("name");

        if (symbol_name==null) {
            msg = "Mismatched Parameters.";
        }
        else if(symbol_name=="" ){
            msg = "Some Parameter are Empty.";
        }
        else if (Database.getDB().add_symbol(symbol_name)) {
            msg = "Successfully Added.";
        }
        else {
            msg = "Symbol has been Already Exists";
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("SymbolAdding.jsp").forward(request,response);

    }

}
