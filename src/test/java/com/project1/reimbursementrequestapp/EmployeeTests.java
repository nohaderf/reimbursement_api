package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.models.Employee;
import com.project1.reimbursementrequestapp.models.Manager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTests {

    Employee employee = Employee.builder()
            .id(1)
            .fullName("Freda Hon")
            .email("freda@freda.com")
            .build();

    @Test
    void shouldGetId() {
        assertEquals(1, employee.getId());
    }

    @Test
    void shouldGetFullName() {
        assertEquals("Freda Hon", employee.getFullName());;
    }

    @Test
    void shouldGetEmail() {
        assertEquals("freda@freda.com", employee.getEmail());
    }

    @Test
    void shouldSetId() {
        employee.setId(2);

        assertEquals(2, employee.getId());
    }

    @Test
    void shouldSetFullName() {
        employee.setFullName("Harry Potter");

        assertEquals("Harry Potter", employee.getFullName());
    }

    @Test
    void shouldSetEmail() {
        employee.setEmail("harry.potter@hogwarts.com");

        assertEquals("harry.potter@hogwarts.com", employee.getEmail());
    }

    @Test
    void testEquals() {
        assertEquals(false, employee.equals("hello"));
    }

    @Test
    void testHashCode() {
        assertEquals(-1121270007, employee.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Employee(id=1, fullName=Freda Hon, email=freda@freda.com, password=password)", employee.toString());
    }
}