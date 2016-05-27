package brs;

import java.io.*;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Backup")
public class Backup extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = "";

        try {
            Vector<String> log = Database.getDB().getLog();

            FileWriter writer = new FileWriter( getServletContext().getRealPath("Backup.csv"));

            for (String l : log) {
                writer.append(l + "\n");
            }

            writer.flush();
            writer.close();

            msg = "Backup Created.";
        } catch (Exception ex) {
            msg = "Somthing wrong";
        }

        String Path = getServletContext().getRealPath("Backup.csv");
        File downloadFile = new File(Path);
        FileInputStream inStream = new FileInputStream(downloadFile);
        ServletContext context = getServletContext();
        String mimeType = context.getMimeType(Path);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();


        request.setAttribute("Message",msg);
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

}
