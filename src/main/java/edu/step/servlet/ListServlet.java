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
        // verificam daca avem specificata pagina ca parametru la URL
        final String p = req.getParameter("p");
        int page = 1;
        if (p != null) {
            // daca pagina exista
            // determinam daca pagina e corecta
            final boolean pageExists = employeeModel.pageExists(Integer.parseInt(p));
            if (!pageExists) {
                // setam mesaj de eroare
            }
            page = pageExists ? Integer.parseInt(p) : page;
            // daca ok- extragem un bloc de informatie din lista intreaga

        }
        // daca pagina nu este corecta
        // redirectionam pe pagina 1
        // afisam un mesaj de eroare

        // returnam
        // 1. o portiune de obiecte Employee (10 pe pagina)
        final List<Employee> employeeList = employeeModel.getEmployeeList();
        req.setAttribute("employees", employeeList);

        // 2. numarul total de pagini
        req.setAttribute("totalPages", employeeModel.getTotalPages());
        // 3. numarul paginii curente
        req.setAttribute("currentPage", 5);
        // 4. mesajul de eroare daca exista

        RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
        dispatcher.forward(req, resp);

    }
}
