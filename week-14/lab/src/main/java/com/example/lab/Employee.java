package com.example.labrestserver;

public class Employee {
    private String name;
    private int salary;

    // Default Constructor
    public Employee() {
    }

    // Two Parameter Constructor
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    // toString Override
    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + "}";
    }
}
