package ru.itis2016;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/calculator"})
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        session.setAttribute("mathAction", null);
        session.setAttribute("lastAction", null);
        session.setAttribute("digit", null);
        session.setAttribute("currentResult", null);
        session.setAttribute("operand", null);
        session.setAttribute("lastexpr", null);


        //!!!! -> calculator1.jsp
        req.getRequestDispatcher("calculator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("operand") == null) {
            session.setAttribute("currentResult", null);
        }
        String divide = "";

        if (req.getParameter("mathAction") != null) {
            String digit = req.getParameter("digit");
            Double operand = Double.parseDouble(digit);

            if (session.getAttribute("mathAction") == null) {
                session.setAttribute("operand", operand);
                session.setAttribute("mathAction", req.getParameter("mathAction"));
            } else if (session.getAttribute("mathAction").equals("add")) {
                session.setAttribute("operand", (Double) session.getAttribute("operand") + operand);
                session.setAttribute("mathAction", req.getParameter("mathAction"));
            } else if (session.getAttribute("mathAction").equals("subtract")) {
                session.setAttribute("operand", (Double) session.getAttribute("operand") - operand);
                session.setAttribute("mathAction", req.getParameter("mathAction"));
            } else if (session.getAttribute("mathAction").equals("multiply")) {
                session.setAttribute("operand", (Double) session.getAttribute("operand") * operand);
                session.setAttribute("mathAction", req.getParameter("mathAction"));
            } else if (session.getAttribute("mathAction").equals("divide")) {
                if (operand == 0) {
                    divide = "Cant divide by zero";
                } else {
                    divide = "";
                    session.setAttribute("operand", (Double) session.getAttribute("operand") / operand);
                    session.setAttribute("mathAction", req.getParameter("mathAction"));
                }
            }
            session.setAttribute("currentResult", session.getAttribute("operand"));

            if (session.getAttribute("mathAction").equals("result")) {
                session.setAttribute("mathAction", null);
                session.setAttribute("lastAction", null);
                session.setAttribute("digit", null);
                session.setAttribute("operand", null);
            }

            if (session.getAttribute("mathAction") == null) {
                session.setAttribute("lastAction", null);
            } else if (session.getAttribute("mathAction").equals("add")) {
                session.setAttribute("lastAction", "+");
            } else if (session.getAttribute("mathAction").equals("subtract")) {
                session.setAttribute("lastAction", "-");
            } else if (session.getAttribute("mathAction").equals("divide")) {
                session.setAttribute("lastAction", "/");
            } else if (session.getAttribute("mathAction").equals("multiply")) {
                session.setAttribute("lastAction", "*");
            }
        }
        Double currentResult = (Double) session.getAttribute("currentResult");
        String lastAction = (String) session.getAttribute("lastAction");

        if (lastAction == null) {
            lastAction = "";
        }

        session.setAttribute("lastexpr", String.valueOf(currentResult + " " + lastAction));
        resp.setContentType("text/html");
        resp.getWriter().write(divide + " ");
        resp.getWriter().write(String.valueOf(session.getAttribute("lastexpr")));
    }
}
