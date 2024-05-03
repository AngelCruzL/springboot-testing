package dev.angelcruzl.springboot.testing.repository;

import dev.angelcruzl.springboot.testing.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test fer save employee operation
//    @DisplayName("JUnit test fer save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Angel")
                .lastName("Cruz")
                .email("me@angelcruzl.dev")
                .build();

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .firstName("Angel")
                .lastName("Cruz")
                .email("me@angelcruzl.dev")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@doe")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

}
