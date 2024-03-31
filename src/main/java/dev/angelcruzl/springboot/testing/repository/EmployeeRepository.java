package dev.angelcruzl.springboot.testing.repository;

import dev.angelcruzl.springboot.testing.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
