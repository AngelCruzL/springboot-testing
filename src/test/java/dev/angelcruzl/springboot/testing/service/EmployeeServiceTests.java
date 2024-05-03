package dev.angelcruzl.springboot.testing.service;

import dev.angelcruzl.springboot.testing.model.Employee;
import dev.angelcruzl.springboot.testing.repository.EmployeeRepository;
import dev.angelcruzl.springboot.testing.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTests {
    private EmployeeRepository repository;
    private EmployeeService service;

    @BeforeEach
    public void setUp() {
        repository = mock(EmployeeRepository.class);
        service = new EmployeeServiceImpl(repository);
    }

    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Angel")
                .lastName("Cruz")
                .email("me@angelcruzl.dev")
                .build();

        BDDMockito.given(repository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

        BDDMockito.given(repository.save(employee))
                .willReturn(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = service.saveEmployee(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }
}
