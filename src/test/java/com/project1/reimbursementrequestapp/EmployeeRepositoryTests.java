package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.models.Employee;
import com.project1.reimbursementrequestapp.repositories.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@DataJpaTest // only tests persistence layer
@SpringBootTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void  createEmployeeTest(){

        Employee employee = Employee.builder()
                .fullName("Freda Hon")
                .email("freda@freda.com")
                .password("password")
                .build();

        Employee newEmployee = employeeRepository.save(employee);

        assertNotNull(newEmployee);
    }

    @Test
    public void getEmployeeTest(){

        Employee employee = employeeRepository.findById(1).get();

        Assertions.assertEquals(1, employee.getId());
    }

    @Test
    public void getAllEmployeesTest(){

        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertTrue(employees.size() > 0);
    }
}
