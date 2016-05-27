package brs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.lang.reflect.*;

@WebServlet("/Sell")
public class Sell extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = "";

        String instrument = request.getParameter("instrument");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String type = request.getParameter("type");

        if (instrument==null || price==null || quantity==null || type==null) {
            msg = "Mismatched Parameters.";
        }
        else if(instrument=="" || price=="" || quantity=="" || type==""){
            msg = "Some Parameter are Empty.";
        }
        else {
            try{
                if (type.equals("GTC")) {
                    msg = Database.getDB().gtc.Sell(Database.getDB().LoggedInID,instrument,Integer.parseInt(price),Integer.parseInt(quantity),Database.getDB());
                } else if (type.equals("MPO")) {
                    //msg = Database.getDB().mpo.Sell(Database.getDB().LoggedInID,instrument,Integer.parseInt(price),Integer.parseInt(quantity),Database.getDB());
                } else if (type.equals("IOC")) {
                    //msg = Database.getDB().ioc.Sell(Database.getDB().LoggedInID,instrument,Integer.parseInt(price),Integer.parseInt(quantity),Database.getDB());
                }

            } catch (Exception ex ){
                msg = "Invalid Type";
            }
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("BuySell.jsp").forward(request,response);

    }

}
