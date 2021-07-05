package com.employee.impl;

import com.employee.data.DataDelegate;
import com.employee.objects.Employee;
import com.employee.objects.Result;

import java.util.List;
import java.util.Locale;

public class EmployeeImpl {

    private DataDelegate dataDelegate = new DataDelegate();

    public Result deleteEmployee(int id) {
        Result result = dataDelegate.deleteEmployee(id);
        return result;
    }

    public Result createEmployee(Employee employee) {
        Result result = dataDelegate.createEmployee(employee);
        return result;
    }

    public Result updateEmployee(Employee employee) {
        Result result = validateEmployee(employee);
        if(result.isError()) {
            return result;
        }
        result = dataDelegate.updateEmployee(employee);
        return result;
    }

    /**
     * This method returns a List of employees.
     * @param id - This can be null to indicate that the client wants all Employees.
     * If the id is not null, then this method searches for that id.
     * If the id is not found, a blank list is returned.
     * @return a List of the Employee object.
     */
    public List<Employee> getEmployee(String id) {
        return dataDelegate.getEmployee(id);
    }

    /**
     * This method validates that all the fields are valid
     *
     * @param employee - This is the employee object to validate
     * @return true if the employee object is valid
     */
    protected  Result validateEmployee(Employee employee) {
        Result result = new Result();
        if (!(employee.getId() > 0)) {
            result.setError(true);
            result.setReason("The employee id must be positive.");
            return result;
        }
        if (employee.getName().isEmpty()) {
            result.setError(true);
            result.setReason("The employee name is required.");
            return result;
        }
        if (employee.getOffice().isEmpty()) {
            result.setError(true);
            result.setReason("The employee office is required.");
            return result;
        } else if (!validateOfficeNumber(employee.getOffice())) {
            result.setError(true);
            result.setReason("The employee office must be  in the range '100A' to '599F'.");
            return result;
        }
        if(employee.getEmail().isEmpty()) {
            result.setError(true);
            result.setReason("The employee email is required.");
            return result;
        }
        if(employee.getPhone().isEmpty()) {
            result.setError(true);
            result.setReason("The employee phone number is required.");
            return result;
        }
        if(employee.getRole().isEmpty()) {
            result.setError(true);
            result.setReason("The employee role is required.");
            return result;
        }
        return result;
    }

    /**
     * This method validates the the office number is in the range '100A' to '599F'
     *
     * @param office The office of type String to validate.
     * @return true of false. True is the officeNumber is valid
     */
    protected  boolean validateOfficeNumber(String office) {
        if (office.length() != 4) {
            return false;
        }
        String firstThreeNumbers = office.substring(0, 3);
        String lastLetter = office.substring(3, 4).toUpperCase(Locale.ROOT);
        int number = 0;
        try {
            number = Integer.parseInt(firstThreeNumbers);
        } catch (NumberFormatException nfe) {
            number = 0;
        }
        if (number < 100 || number > 599) {
            return false;
        }
        if ("A".equals(lastLetter) || "B".equals(lastLetter) || "C".equals(lastLetter) || "D".equals(lastLetter) || "E".equals(lastLetter) || "F".equals(lastLetter)) {
            return true;
        } else {
            return false;
        }
    }
}
