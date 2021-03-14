package edu.step.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Model - View - Controller
// @Singleton
public class EmployeeModel {

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
        int numberOfPages = this.employeeList.size() / 10; // 0.223226
        int rest = this.employeeList.size() % 10;
        if(rest > 0){
            numberOfPages += 1;
        }
        return numberOfPages;
    }

    public boolean pageExists(int page) {
        return getTotalPages() >= page;
    }

    public boolean add(Employee emp) {
        return this.employeeList.add(emp);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<String> list() {
        return employeeList.stream().map(user -> user.getName()).collect(Collectors.toList());
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
