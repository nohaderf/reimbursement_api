package com.project1.reimbursementrequestapp;


import com.project1.reimbursementrequestapp.models.Manager;
import com.project1.reimbursementrequestapp.repositories.ManagerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ManagerRepositoryTests {

    @Autowired
    private ManagerRepository managerRepository;

    @Test
    public void  createManagerTest(){

        Manager manager = Manager.builder()
                .fullName("Albus Dumbledore")
                .email("dumbledore@hogwarts.com")
                .password("password")
                .build();

        Manager newManager = managerRepository.save(manager);

        assertNotNull(newManager);
    }

    @Test
    public void getManagerByIdTest(){

       Manager manager = managerRepository.findById(1).get();

        Assertions.assertEquals(1, manager.getId());
    }
//
//    @Test
//    public void getManagerByFullNameTest(){
//
//        Manager manager = managerRepository.findByFullName("Album Dumbledore").get();
//
//        Assertions.assertEquals(1, manager.getId());
//    }

    @Test
    public void getAllEmployeesTest(){

        List<Manager> managers = managerRepository.findAll();

        Assertions.assertTrue(managers.size() > 0);
    }
}
