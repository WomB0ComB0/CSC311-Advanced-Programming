package com.example.labrestserver;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

 @RestController @RequestMapping("/api/employees")
// Decorate to allow requests from other origins (Step from Page 1)
 @CrossOrigin(origins = "*")
public class EmployeeController {

    private List<Employee> employeeList;

    // Default Constructor initializing data
    public EmployeeController() {
        this.employeeList = new ArrayList<>();
        
        // Adding the specific data requested in the lab
        // Replaced "(your name, 100)" with a placeholder
        this.employeeList.add(new Employee("Student Name", 100)); 
        this.employeeList.add(new Employee("Azza", 40));
        this.employeeList.add(new Employee("Wei", 30));
        this.employeeList.add(new Employee("Julia", 50));
    }

    // Handle HTTP GET request - returns the list
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeList;
    }

    // Handle HTTP POST request - adds a new employee
    // Using @RequestParam as per the "Update EmployeeController to do a POST" instructions
    // and the lecture slides (Page 14) strategy.
    @PostMapping
    public Employee addEmployee( @RequestParam String name, @RequestParam int salary) {
        Employee newEmployee = new Employee(name, salary);
        employeeList.add(newEmployee);
        return newEmployee;
    }
}
