package edu.step.servlet;

import edu.step.entity.Employee;
import edu.step.entity.EmployeeModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListServlet extends HttpServlet {
//        EmployeeDao dao = new EmployeeDao();
//        final List<Employee> employeeList = dao.getAll();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeModel employeeModel = EmployeeModel.getInstance();

        final String query = req.getParameter("q");
        if (query != null) {
            search(req, resp);
        } else {
            display(req, resp);
        }

    }

    public boolean checkIfPageExists(String p, String searchQuery) {
        final EmployeeModel instance = EmployeeModel.getInstance();
        if(p != null){
            if(searchQuery != null) {
                return instance.pageExists(Integer.parseInt(p), searchQuery);
            }
            return instance.pageExists(Integer.parseInt(p));
        }
        return instance.pageExists(1);
    }

    public String getPageNotExistMessage(String p) {
        return "The requested page (" + p + ") does not exist.";
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeModel model = EmployeeModel.getInstance();
        final String searchQuery = req.getParameter("q");
        final String p = req.getParameter("p");
        int page = 1;
        boolean pageExists = this.checkIfPageExists(p, searchQuery);

        if (!pageExists) {
            req.setAttribute("wrongPageMsg", getPageNotExistMessage(p));
        }
        page = pageExists ? Integer.parseInt(p) : page;
        // extragem query
        // filtram datele dupa query
        List<Employee> filtered = model.getPage(page, searchQuery); // <10
        // returnam datele filtrate
        req.setAttribute("employees", filtered);

        req.setAttribute("totalPages", model.getTotalPages(searchQuery));
        req.setAttribute("currentPage", page);

        RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
        dispatcher.forward(req, resp);

    }

    private void display(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeModel employeeModel = EmployeeModel.getInstance();
        final String p = req.getParameter("p");
        int page = 1;
        boolean pageExists = this.checkIfPageExists(p, null);

        if (!pageExists) {
            req.setAttribute("wrongPageMsg", getPageNotExistMessage(p));
        }
        page = pageExists ? Integer.parseInt(p) : page;

        final List<Employee> employeeList = employeeModel.getPage(page);
        req.setAttribute("employees", employeeList);

        req.setAttribute("totalPages", employeeModel.getTotalPages());
        req.setAttribute("currentPage", page);

        RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // citim parametru de cautare
        final String query = req.getParameter("query");
        // afisam datele filtrate
        resp.sendRedirect("list?q=" + query);
    }
}
