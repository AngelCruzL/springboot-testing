package dev.angelcruzl.springboot.testing.service;

import dev.angelcruzl.springboot.testing.exception.ResourceNotFoundException;
import dev.angelcruzl.springboot.testing.model.Employee;
import dev.angelcruzl.springboot.testing.repository.EmployeeRepository;
import dev.angelcruzl.springboot.testing.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = Employee.builder()
                .id(1L)
                .firstName("Angel")
                .lastName("Cruz")
                .email("me@angelcruzl.dev")
                .build();
    }

    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        // given - precondition or setup
        given(repository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

        given(repository.save(employee)).willReturn(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = service.saveEmployee(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for save employee operation when employee already exists")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenThrowResourceNotFoundException() {
        // given - precondition or setup
        given(repository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

        // when - action or the behaviour that we are going test
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.saveEmployee(employee);
        });

        // then - verify the output
        verify(repository, never()).save(any(Employee.class));
    }

    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() {
        // given - precondition or setup
        Employee employee2 = Employee.builder()
                .id(2L)
                .firstName("John")
                .lastName("Doe")
                .email("john@doe")
                .build();
        given(repository.findAll()).willReturn(List.of(employee, employee2));

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = service.getAllEmployees();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for get all employees operation when no employees")
    @Test
    public void givenNoEmployees_whenGetAllEmployees_thenReturnEmptyList() {
        // given - precondition or setup
        given(repository.findAll()).willReturn(List.of());
        given(repository.findAll()).willReturn(Collections.emptyList());

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = service.getAllEmployees();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(0);
    }

    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() {
        // given - precondition or setup
        given(repository.findById(employee.getId())).willReturn(Optional.of(employee));

        // when - action or the behaviour that we are going test
        Employee employeeDb = service.getEmployeeById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDb).isNotNull();
    }

}
