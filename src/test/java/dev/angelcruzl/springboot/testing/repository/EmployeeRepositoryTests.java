package dev.angelcruzl.springboot.testing.repository;

import dev.angelcruzl.springboot.testing.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = Employee.builder()
                .firstName("Angel")
                .lastName("Cruz")
                .email("me@angelcruzl.dev")
                .build();
    }

    // JUnit test fer save employee operation
    @DisplayName("JUnit test fer save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // given - precondition or setup

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
        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@doe")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDb = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDb).isNotNull();

    }

    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDb = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDb).isNotNull();
    }

    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setFirstName("Luis Angel");
        savedEmployee.setLastName("Cruz Lara");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Luis Angel");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Cruz Lara");
    }

    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        employeeRepository.delete(employee);
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

    @DisplayName("JUnit test for get employee by first name and last name operation using JPQL")
    @Test
    public void givenEmployeeFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDb = employeeRepository.findByJPQL(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(employeeDb).isNotNull();
    }

    @DisplayName("JUnit test for get employee by first name and last name operation using named params")
    @Test
    public void givenEmployeeFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDb = employeeRepository.findByJPQLNamedParams(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(employeeDb).isNotNull();
    }

    @DisplayName("JUnit test for get employee by first name and last name operation using native query")
    @Test
    public void givenEmployeeFirstNameAndLastName_whenFindByNativeQuery_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDb = employeeRepository.findByNativeQuery(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(employeeDb).isNotNull();
    }

    @DisplayName("JUnit test for get employee by first name and last name operation using named params at native query")
    @Test
    public void givenEmployeeFirstNameAndLastName_whenFindByNativeQueryNamedParams_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDb = employeeRepository.findByNativeQueryNamedParams(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(employeeDb).isNotNull();
    }

}
