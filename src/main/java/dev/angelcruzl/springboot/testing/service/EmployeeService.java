package dev.angelcruzl.springboot.testing.service;

import dev.angelcruzl.springboot.testing.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();
}
