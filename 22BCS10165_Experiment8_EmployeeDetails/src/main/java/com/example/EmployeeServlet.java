package com.example;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchEmployee")
public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Employee emp = EmployeeDAO.getEmployeeById(id);

            if (emp == null) {
                req.setAttribute("error", "Employee not found with ID: " + id);
            } else {
                req.setAttribute("employee", emp);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("search.jsp");
            dispatcher.forward(req, res);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid ID format.");
            req.getRequestDispatcher("search.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
    }
}
