package com.employee.impl;

import com.employee.objects.Employee;
import com.employee.objects.Result;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class EmployeeImplTest {



   private EmployeeImpl employeeImpl = new EmployeeImpl();


    @Test
    public void validateOfficeTest() {
        String office = "100A";
        boolean status = employeeImpl.validateOfficeNumber(office);
        assertTrue(status);
        office = "599F";
        status = employeeImpl.validateOfficeNumber(office);
        assertTrue(status);
        //Lowercase office letter should be allowed.
        office = "599f";
        status = employeeImpl.validateOfficeNumber(office);
        assertTrue(status);
        office = "ABCD";
        status = employeeImpl.validateOfficeNumber(office);
        assertFalse(status);
        office = "600A";
        status = employeeImpl.validateOfficeNumber(office);
        assertFalse(status);
        office = "599G";
        status = employeeImpl.validateOfficeNumber(office);
        assertFalse(status);
    }

    @Test
    public void happyPathTest() {
        Employee employee = createEmployee();
        Result result = employeeImpl.validateEmployee(employee);
        assertFalse(result.isError());
    }

    @Test
    public void missingIdTest() {
        Employee employee = createEmployee();
        employee.setId(0);
        Result result = employeeImpl.validateEmployee(employee);
        assertTrue(result.isError());
    }


    @Test
    public void missingNameTest() {
        Employee employee = createEmployee();
        employee.setName("");
        Result result = employeeImpl.validateEmployee(employee);
        assertTrue(result.isError());
    }

    @Test
    public void missingOfficeTest() {
        Employee employee = createEmployee();
        employee.setOffice("");
        Result result = employeeImpl.validateEmployee(employee);
        assertTrue(result.isError());
    }

    @Test
    public void missingEmailTest() {
        Employee employee = createEmployee();
        employee.setEmail("");
        Result result = employeeImpl.validateEmployee(employee);
        assertTrue(result.isError());
    }

    @Test
    public void missingPhoneTest() {
        Employee employee = createEmployee();
        employee.setPhone("");
        Result result = employeeImpl.validateEmployee(employee);
        assertTrue(result.isError());
    }

    @Test
    public void missingRoleTest() {
        Employee employee = createEmployee();
        employee.setRole("");
        Result result = employeeImpl.validateEmployee(employee);
        assertTrue(result.isError());
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setId(100);
        employee.setName("Fred Smith");
        employee.setOffice("100B");
        employee.setEmail("fred.smith@gmail.com");
        employee.setPhone("(501)227-1234");
        employee.setRole("Developer");
        return employee;
    }
}
