package dev.angelcruzl.springboot.testing.controller;

import dev.angelcruzl.springboot.testing.model.Employee;
import dev.angelcruzl.springboot.testing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
        return service.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,
                                                   @RequestBody Employee updatedEmployee) {
        return service.getEmployeeById(employeeId)
                .map(employee -> {
                    updatedEmployee.setId(employee.getId());
                    return ResponseEntity.ok(service.updateEmployee(updatedEmployee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId) {
        service.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee with id " + employeeId + " deleted successfully");
    }
}
