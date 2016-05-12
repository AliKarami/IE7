package brs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.lang.reflect.*;

@WebServlet("/Buy")
public class Buy extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = "";

        String id = request.getParameter("id");
        String instrument = request.getParameter("instrument");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String type = request.getParameter("type");

        if (id==null || instrument==null || price==null || quantity==null || type==null) {
            msg = "Mismatched Parameters.";
        }
        else if(id=="" || instrument=="" || price=="" || quantity=="" || type==""){
            msg = "Some Parameter are Empty.";
        }
        else {
            try{
//                Class <? extends Type> TypeClass = Class.forName(type).asSubclass(Type.class);
//                Object obj = TypeClass.newInstance();
//                Method mtd = TypeClass.getDeclaredMethod("Buy", new Class[]{Integer.TYPE, String.class, Integer.TYPE, Integer.TYPE, Database.class});
//                msg = (String)mtd.invoke(obj,Integer.parseInt(id), instrument, Integer.parseInt(price), Integer.parseInt(quantity), Database.getDB());

                if (type.equals("GTC")) {
                    msg = Database.getDB().gtc.Buy(Integer.parseInt(id),instrument,Integer.parseInt(price),Integer.parseInt(quantity),Database.getDB());
                } else if (type.equals("MPO")) {
                    msg = Database.getDB().mpo.Buy(Integer.parseInt(id),instrument,Integer.parseInt(price),Integer.parseInt(quantity),Database.getDB());
                } else if (type.equals("IOC")) {
                    msg = Database.getDB().ioc.Buy(Integer.parseInt(id),instrument,Integer.parseInt(price),Integer.parseInt(quantity),Database.getDB());
                }

            } catch (Exception ex ){
                msg = "Invalid Type";
            }
        }

        request.setAttribute("Message",msg);
        request.getRequestDispatcher("BuySell.jsp").forward(request,response);

    }

}
