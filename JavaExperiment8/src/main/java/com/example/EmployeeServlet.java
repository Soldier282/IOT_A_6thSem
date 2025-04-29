package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("search".equalsIgnoreCase(action)) {
            // Search Employee
            try {
                int id = Integer.parseInt(req.getParameter("searchId"));
                Employee emp = EmployeeDAO.getEmployeeById(id);

                if (emp != null) {
                    req.setAttribute("searchedEmployee", emp);
                } else {
                    req.setAttribute("notFound", "No employee found with ID: " + id);
                }

                req.getRequestDispatcher("search.jsp").forward(req, res);

            } catch (Exception e) {
                req.setAttribute("notFound", "Invalid ID format.");
                req.getRequestDispatcher("search.jsp").forward(req, res);
            }

        } else {
            // List All Employees
            List<Employee> empList = EmployeeDAO.getAllEmployees();
            req.setAttribute("employeeList", empList);
            req.getRequestDispatcher("list.jsp").forward(req, res);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
