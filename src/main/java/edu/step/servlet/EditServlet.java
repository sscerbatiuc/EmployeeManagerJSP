package edu.step.servlet;

import edu.step.entity.Employee;
import edu.step.entity.EmployeeModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int position = Integer.parseInt(request.getParameter("position"));
        final Employee employeeToEdit = EmployeeModel.getInstance().get(position);
        request.setAttribute("name", employeeToEdit.getName());
        // Sau transmitem obiectul complet
        //        request.setAttribute("employee", employeeToEdit);
        request.setAttribute("position", position);
        RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empName = req.getParameter("name");
        int position = Integer.parseInt(req.getParameter("position"));
        EmployeeModel employeeModel = EmployeeModel.getInstance();
        employeeModel.edit(position, empName);
        resp.sendRedirect("list");
    }
}
