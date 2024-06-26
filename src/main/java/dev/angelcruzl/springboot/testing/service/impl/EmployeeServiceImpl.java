package dev.angelcruzl.springboot.testing.service.impl;

import dev.angelcruzl.springboot.testing.exception.ResourceNotFoundException;
import dev.angelcruzl.springboot.testing.model.Employee;
import dev.angelcruzl.springboot.testing.repository.EmployeeRepository;
import dev.angelcruzl.springboot.testing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()) {
            throw new ResourceNotFoundException("Employee with email " + employee.getEmail() + " already exists");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee updatedEmployee) {
        Optional<Employee> employee = employeeRepository.findById(updatedEmployee.getId());
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException("Employee with id " + updatedEmployee.getId() + " not found");
        }

        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }

        employeeRepository.deleteById(id);
    }
}
