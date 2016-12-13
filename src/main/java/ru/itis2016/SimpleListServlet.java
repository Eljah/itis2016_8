package ru.itis2016;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eljah32 on 12/13/2016.
 */
@WebServlet({"/simple"})
public class SimpleListServlet  extends HttpServlet
{

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text / html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<html><head><title>Groups</title><script src=\"js/ajax.js\"></script><script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.js\"></script></head>");
        pw.println("<body><B> Список групп</B>");
        pw.println("<table border = 1>");
        pw.println("<script language=\"javascript\" src=\"gwt/gwt.nocache.js\"></script>\n" +
                "    \n" +
                "    <!-- Include a history iframe to enable full GWT history support -->\n" +
                "    <!-- (the id must be exactly as shown)                           -->\n" +
                "    <iframe src=\"javascript:''\" id=\"__gwt_historyFrame\" style=\"width:0;height:0;border:0\"></iframe>");


        pw.println("</table></body></html>");
    }
    }
