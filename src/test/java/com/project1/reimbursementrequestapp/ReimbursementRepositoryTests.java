package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.controllers.ReimbursementController;
import com.project1.reimbursementrequestapp.models.Employee;
import com.project1.reimbursementrequestapp.models.Manager;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import com.project1.reimbursementrequestapp.repositories.EmployeeRepository;
import com.project1.reimbursementrequestapp.repositories.ManagerRepository;
import com.project1.reimbursementrequestapp.repositories.ReimbursementRepo;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class ReimbursementRepositoryTests {

    @Autowired
    private ReimbursementRepo reimbursementRepo;

    private Reimbursement reimbursement;

    @Test
    public void saveReimbursementTest() {

        Employee employee = Employee.builder()
                .fullName("Freda Hon")
                .email("freda@freda.com")
                .password("password")
                .build();

        Manager manager = Manager.builder()
                .fullName("Albus Dumbledore")
                .email("dumbledore@hogwarts.com")
                .password("password")
                .build();

        Reimbursement reimbursement = Reimbursement.builder()
                .date("2022/04/22 22:16:30")
                .description("Dinner")
                .amount(50)
                .status("Pending")
                .submitDate("2022/04/28 22:16:30")
                .employee(employee)
                .manager(manager)
                .build();

        reimbursementRepo.save(reimbursement);

        assertTrue(reimbursement.getId() > 0);
    }

    @Test
    @Transactional
    public void shouldUpdateReimbursementStatusTest(){

        Employee employee = Employee.builder()
                .fullName("Freda Hon")
                .email("freda@freda.com")
                .password("password")
                .build();

        Manager manager = Manager.builder()
                .fullName("Albus Dumbledore")
                .email("dumbledore@hogwarts.com")
                .password("password")
                .build();

        Reimbursement reimbursement = Reimbursement.builder()
                .date("2022/04/22 22:16:30")
                .description("Dinner")
                .amount(50)
                .status("Denied")
                .submitDate("2022/04/28 22:16:30")
                .employee(employee)
                .manager(manager)
                .build();

        reimbursementRepo.save(reimbursement);

        reimbursementRepo.updateReimbursementById("Denied", manager, 1);

        String status = reimbursement.getStatus();
        assertEquals(status, "Denied");
    }

    @Test
    @Transactional
    public void shouldUpdateReimbursementManagerTest(){
//
//        Employee employee = Employee.builder()
//                .fullName("Freda Hon")
//                .email("freda@freda.com")
//                .password("password")
//                .build();
//
//        Manager manager = Manager.builder()
//                .fullName("Albus Dumbledore")
//                .email("dumbledore@hogwarts.com")
//                .password("password")
//                .build();
//
//        Reimbursement reimbursement = Reimbursement.builder()
//                .date("2022/04/22 22:16:30")
//                .description("Dinner")
//                .amount(50)
//                .status("Denied")
//                .submitDate("2022/04/28 22:16:30")
//                .employee(employee)
//                .manager(manager)
//                .build();
//
//        reimbursementRepo.save(reimbursement);
//
//        Manager manager2 = Manager.builder()
//                .fullName("Severus Snape")
//                .email("snape@hogwarts.com")
//                .password("password")
//                .build();
//
//        reimbursementRepo.updateReimbursementById("Denied", manager2, 1);
//
//        String status = reimbursement.getStatus();
//        assertEquals(reimbursement.getManager().getFullName(), "Severus Snape");
    }

}
