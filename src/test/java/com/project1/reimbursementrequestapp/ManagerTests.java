package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.models.Manager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerTests {

    Manager manager = Manager.builder()
            .id(1)
            .fullName("Albus Dumbledore")
            .email("dumbledore@hogwarts.com")
            .build();

    @Test
    void shouldGetId() {
        assertEquals(1, manager.getId());
    }

    @Test
    void shouldGetFullName() {
        assertEquals("Albus Dumbledore", manager.getFullName());
    }

    @Test
    void shouldGetEmail() {
        assertEquals("dumbledore@hogwarts.com", manager.getEmail());
    }

    @Test
    void shouldSetId() {
        manager.setId(2);

        assertEquals(2, manager.getId());
    }

    @Test
    void shouldSetFullName() {
        manager.setFullName("Severus Snape");

        assertEquals("Severus Snape", manager.getFullName());
    }

    @Test
    void shouldSetEmail() {
        manager.setEmail("snape@hogwarts.com");

        assertEquals("snape@hogwarts.com", manager.getEmail());
    }

    @Test
    void testEquals() {
        assertEquals(false, manager.equals("hello"));
    }

    @Test
    void testHashCode() {
        assertEquals(-1434201263, manager.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Manager(id=1, fullName=Albus Dumbledore, email=dumbledore@hogwarts.com)", manager.toString());
    }
}