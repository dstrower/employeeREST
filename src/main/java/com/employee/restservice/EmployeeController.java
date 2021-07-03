package com.employee.restservice;

import com.employee.data.DataDelegate;
import com.employee.objects.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {

    /**
     * This method will either return a List of Employees
     * or it will return one employee.
     * @param id This is the id of the employee object to be returned.
     *           Leave blank to retrieve all employees.
     * @return List of employee objects. Can be a list of one.
     */
    @GetMapping("/employee")
    public List<Employee> getOneEmployee(@RequestParam(required = false) String id) {
        List<Employee> employeeList = DataDelegate.getEmployee(id);
        return employeeList;
    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());

    }
}
