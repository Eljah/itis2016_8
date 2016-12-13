package ru.itis2016;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eljah32 on 12/13/2016.
 */
@WebServlet({"/note"})
public class NotesServlet extends HttpServlet {

    static List<Note> notes = new ArrayList<Note>();

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Note note = new Note();
        note.setText(req.getParameter("text"));
        notes.add(note);

        PrintWriter pr = resp.getWriter();
        for (Note n : notes) {
            pr.write(n.getText() + "<br>");
        }


    }

    // Переопределим стандартные методы
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}