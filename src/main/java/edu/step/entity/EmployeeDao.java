package edu.step.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {


    private String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test";
    private String DB_USER = "postgres";
    private String DB_PASS = "aozhnl";

    public EmployeeDao() {
        try{
            Class.forName("org.postgresql.Driver"); // com.mysql.jdbc.Driver
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Connection getConnection() throws SQLException {
        System.out.println("Opening connnection!");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        System.out.println("Connection established successfully");
        return conn;
    }


    public List<Employee> getAll() {
        try(Connection conn = getConnection()){
            String sql = "select * from app.employee";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Employee> employees = new ArrayList<>();
            while(rs.next()){
                final Employee employee = new Employee(rs.getString(2));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException ex){
            System.out.println("EROARE! " + ex.getMessage());
            return null;
        }
    }



}
