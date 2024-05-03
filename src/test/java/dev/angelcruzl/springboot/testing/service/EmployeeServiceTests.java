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

}
