package com.employee.data;

import com.employee.objects.Employee;
import com.employee.objects.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataDelegate {
    private static final String connection = "jdbc:mysql://airtrafficcontrol.cpm6gbpcuwyo.us-east-1.rds.amazonaws.com:3306/employee?user=masterUserName&password=masterAtcs!";

    /**
     * This method calls the mysql database to insert a row
     * @param employee The object to insert
     * @return The results to determine if the insertion was successfull or not.
     */
    public  Result createEmployee(Employee employee) {
        if(!idIsUnique(employee.getId())) {
            Result result = new Result();
            result.setError(true);
            result.setReason("The employee id must be unique.");
            return result;
        }
        Result result = insertEmployee(employee);
        return result;
    }



    /**
     * This method determines if the id is unique in the database
     * @param id
     * @return
     */
    protected  boolean idIsUnique(int id) {
        List<Employee> employeeList = getEmployee(String.valueOf(id));
        if(employeeList != null && employeeList.size() == 1) {
            Employee employee = employeeList.get(0);
            if(employee.getId() == id) {
                return false;
            }
        }
        return true;
    }



    /**
     * This method returns a List of employees.
     * @param id - This can be null to indicate that the client wants all Employees.
     * If the id is not null, then this method searches for that id.
     * If the id is not found, a blank list is returned.
     * @return a List of the Employee object.
     */
    public  List<Employee> getEmployee(String id) {
        List<Employee> employeeList = new ArrayList<>();

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

    private Result insertEmployee(Employee employee) {
        Result result = new Result();

        Connection connect = null;
        try {
            connect = DriverManager.getConnection(connection);
            Statement statement = connect.createStatement();
            String sql = "insert into employee values(";
            sql = sql +  employee.getId() +",";
            sql = sql + "'" + employee.getName() + "',";
            sql = sql + "'" + employee.getOffice() + "',";
            sql = sql + "'" + employee.getEmail() + "',";
            sql = sql + "'" + employee.getPhone() + "',";
            sql = sql + "'" + employee.getRole() + "')";
            System.out.println("sql = " + sql);
            statement.execute(sql);
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
            result.setError(true);
            result.setReason(sqlError.getMessage());
        }

        return result;
    }

    public Result updateEmployee(Employee employee) {
        //First see if this employee id is found
        List<Employee> employeeList = getEmployee(String.valueOf(employee.getId()));
        if(employeeList == null || employeeList.size() == 0) {
            Result result = new Result();
            result.setError(true);
            result.setReason("Cannot find the employee with id of '" + employee.getId() + "'");
            return result;
        }
        Employee foundEmployee = employeeList.get(0);
        if(foundEmployee.getId() != employee.getId()) {
            Result result = new Result();
            result.setError(true);
            result.setReason("Cannot find the employee with id of '" + employee.getId() + "'");
            return result;
        }
        Result result = new Result();
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(connection);
            Statement statement = connect.createStatement();
            String sql = "update employee set name='" + employee.getName() + "',";
            sql = sql + "office = '" + employee.getOffice() +"',";
            sql = sql + "email = '" + employee.getEmail() + "',";
            sql = sql + "phone = '" + employee.getPhone() + "',";
            sql = sql + "role = '" + employee.getRole() + "' ";
            sql = sql + "where id = " +employee.getId();
            System.out.println("sql = " + sql);
            statement.execute(sql);
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
            result.setError(true);
            result.setReason(sqlError.getMessage());
        }
        return result;
    }

    public Result deleteEmployee(int id) {
        //First see if this employee id is found
        List<Employee> employeeList = getEmployee(String.valueOf(id));
        if(employeeList == null || employeeList.size() == 0) {
            Result result = new Result();
            result.setError(true);
            result.setReason("Cannot find the employee with id of '" + id + "'");
            return result;
        }
        Employee foundEmployee = employeeList.get(0);
        if(foundEmployee.getId() != id) {
            Result result = new Result();
            result.setError(true);
            result.setReason("Cannot find the employee with id of '" + id + "'");
            return result;
        }
        Result result = new Result();
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(connection);
            Statement statement = connect.createStatement();
            String sql = "delete from employee where id = " + id;
            System.out.println("sql = " + sql);
            statement.execute(sql);
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
            result.setError(true);
            result.setReason(sqlError.getMessage());
        }
        return result;
    }
}
