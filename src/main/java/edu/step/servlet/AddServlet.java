package edu.step.servlet;

import edu.step.entity.Employee;
import edu.step.entity.EmployeeModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        RequestDispatcher dispatcher = req.getRequestDispatcher("add.jsp");
        dispatcher.forward(req, resp);

        // redirectionez utilizatorul pe pagina add
        // resp.sendRedirect("https://www.facebook.com");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        Employee employee = new Employee(name);
        EmployeeModel.getInstance().add(employee);

        resp.sendRedirect("list");
    }
}
