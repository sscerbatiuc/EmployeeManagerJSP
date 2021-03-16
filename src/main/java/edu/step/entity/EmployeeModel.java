package edu.step.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeModel {

    private final static int rowsPerPage = 10;

    private static EmployeeModel instance = new EmployeeModel();

    public static EmployeeModel getInstance() {
        return instance;
    }

    private List<Employee> employeeList;

    private EmployeeModel() {
        this.employeeList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            this.employeeList.add(new Employee(i, "name " + i));
        }
    }

    public int getTotalPages() {
        int numberOfPages = getTotal() / rowsPerPage; // 0.223226
        int rest = getTotal() % rowsPerPage;
        if (rest > 0) {
            numberOfPages += 1;
        }
        return numberOfPages;
    }
    //
    public int getTotalPages(String searchQuery){
        final List<Employee> filtered = this.search(searchQuery);
        int numberOfPages = filtered.size() / rowsPerPage;
        int rest = filtered.size() % rowsPerPage;
        if(rest > 0){
            numberOfPages += 1;
        }
        return numberOfPages;
    }

    public boolean pageExists(int page) {
        return getTotalPages() >= page;
    }

    public boolean pageExists(int page, String searchQuery) {
        return getTotalPages(searchQuery) >= page;
    }


    public boolean add(Employee emp) {
        return this.employeeList.add(emp);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public int getTotal() {
        return this.employeeList.size();
    }

    public List<Employee> search(String query) {
        final List<Employee> filtered = this.employeeList.stream()
                .filter(employee -> employee.getName().contains(query))
                .collect(Collectors.toList());
        return filtered;
    }

    public List<Employee> getPage(int page) {
        int to = page * rowsPerPage;
        int from = to - rowsPerPage;
        to = Math.min(to, getTotal());
        return this.employeeList.subList(from, to);
    }

    public List<Employee> getPage(int page, String query){
        final List<Employee> filtered = this.search(query);
        int to = page * rowsPerPage;
        int from = to - rowsPerPage;
        to = Math.min(to, filtered.size());
        return filtered.subList(from, to);
    }

    public void deleteEmployee(int position) {
        this.employeeList.remove(position);
    }

    public Employee get(int position) {
        return this.employeeList.get(position);
    }

    public void edit(int position, String newName) {
        this.employeeList.get(position).setName(newName);
    }
}
