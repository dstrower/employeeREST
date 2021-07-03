package com.employee.data;

import com.employee.objects.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDelegate {

    public static List<Employee> getEmployee(String id) {
        List<Employee> employeeList = new ArrayList<>();
        String connection = "jdbc:mysql://airtrafficcontrol.cpm6gbpcuwyo.us-east-1.rds.amazonaws.com:3306/employee?user=masterUserName&password=masterAtcs!";
        try {
            Connection connect = DriverManager.getConnection(connection);
            Statement statement = connect.createStatement();
            System.out.println("Made connection");
            String sql = null;
            if (id == null) {
                sql = "select * from employee";
            } else {
                sql = "select * from employee where id = " + id;
            }
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int idFromDB = rs.getInt("id");
                String name = rs.getString("name");
                String office = rs.getString("office");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String role = rs.getString("role");
                Employee employee = new Employee();
                employee.setId(idFromDB);
                employee.setName(name);
                employee.setOffice(office);
                employee.setEmail(email);
                employee.setPhone(phone);
                employee.setRole(role);
                employeeList.add(employee);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employeeList;
    }
}
