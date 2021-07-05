package com.employee.restservice;

import com.employee.impl.EmployeeImpl;
import com.employee.objects.Employee;
import com.employee.objects.ErrorResponse;
import com.employee.objects.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeImpl employeeImpl = new EmployeeImpl();

    /**
     * This method will either return a List of Employees
     * or it will return one employee.
     *
     * @param id This is the id of the employee object to be returned.
     *           Leave blank to retrieve all employees.
     * @return List of employee objects. Can be a list of one.
     */
    @GetMapping(value = "/employee", consumes = "application/json", produces = "application/json")
    public List<Employee> getOneEmployee(@RequestParam(required = false) String id) {
        List<Employee> employeeList = employeeImpl.getEmployee(id);
        return employeeList;
    }

    @PostMapping(
            value = "/createEmployee", consumes = "application/json", produces = "application/json")
    public HttpStatus createPerson(@RequestBody Employee employee) {
        Result result = employeeImpl.createEmployee(employee);
        if (!result.isError()) {
            return HttpStatus.ACCEPTED;
        } else {
            throw new IllegalArgumentException(result.getReason());
        }
    }

    @PutMapping(value = "/employee", consumes = "application/json", produces = "application/json")
    public HttpStatus updateEmployee(@RequestBody Employee employee) {
        Result result = employeeImpl.updateEmployee(employee);
        if (!result.isError()) {
            return HttpStatus.ACCEPTED;
        } else {
            throw new IllegalArgumentException(result.getReason());
        }
    }

    @DeleteMapping(value = "/employee", consumes = "application/json", produces = "application/json")
    public HttpStatus deleteEmployee(@RequestParam(required = true) String id) {
        Result result = employeeImpl.deleteEmployee(Integer.parseInt(id));
        if (!result.isError()) {
            return HttpStatus.ACCEPTED;
        } else {
            throw new IllegalArgumentException(result.getReason());
        }
    }


    @ExceptionHandler
    ErrorResponse handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setResponseMessage(e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return errorResponse;
    }
}
