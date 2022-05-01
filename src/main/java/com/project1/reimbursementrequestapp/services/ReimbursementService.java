package com.project1.reimbursementrequestapp.services;

import com.project1.reimbursementrequestapp.ReimbursementRequestApplication;
import com.project1.reimbursementrequestapp.dtos.ReimbursementDTO;
import com.project1.reimbursementrequestapp.models.Email;
import com.project1.reimbursementrequestapp.models.Employee;
import com.project1.reimbursementrequestapp.models.Manager;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import com.project1.reimbursementrequestapp.repositories.EmployeeRepository;
import com.project1.reimbursementrequestapp.repositories.ManagerRepository;
import com.project1.reimbursementrequestapp.repositories.ReimbursementRepo;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class ReimbursementService {

    final Logger logger = LoggerFactory.getLogger(ReimbursementService.class);

    private ReimbursementRepo reimbursementRepo;

    @Setter(onMethod =@__({@Autowired}) )
    private EmployeeRepository employeeRepository;

    @Setter(onMethod =@__({@Autowired}) )
    private ManagerRepository managerRepository;

    public ReimbursementService(ReimbursementRepo reimbursementRepo) {
        super();
        this.reimbursementRepo = reimbursementRepo;
    }

//    public void findById(int id) {
//        if (id <= 0 ) {
//            return "No reimbursement exists";
//        } else {
//            reimbursementRepo.findById(id);
//        }
//    }

    /**
     * Converts and saves the incoming dto into a reimbursement instance
     * @param dto - the reimbursement data transfer object
     * @return reimbursement instance
     */
    public void convertToEntity(ReimbursementDTO dto) {

        String employeeName = dto.getEmployee_fullName();
        String managerName = dto.getManager_fullName();

        logger.debug("Get all employee via full name: {}", employeeRepository.findByFullName(employeeName));
        logger.debug("Get all manager via full name: {}", employeeRepository.findByFullName(managerName));
//
//        System.out.println(req.getEmployee_fullName());
//        System.out.println(req.getManager_fullName());

        Employee employee = employeeRepository.findByFullName(employeeName);
        Manager manager = managerRepository.findByFullName(managerName);

        String date = dto.getDate();
        String description = dto.getDescription();
        double amount = dto.getAmount();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = formatter.format(now);

        Reimbursement newReimbursement = new Reimbursement(0, date, description, amount, "Pending", dateTime, employee, manager);

        reimbursementRepo.save(newReimbursement);

        // create new email
        Email email = new Email(
                0,
                dateTime,
                manager.getEmail(),
                employee.getEmail(),
                "New Reimbursement Request",
                newReimbursement.toString()
        );
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Email> newEmail = restTemplate.postForEntity(
                "http://localhost:9091/emails/new",
                email,
                Email.class
        );
        System.out.println(newEmail.getBody());
//
//        System.out.println(email);
//        boolean success = reimbursementRepo.save(newReimbursement);
//
//        if (success == false) {
//            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Hmm...Something isn't right.");
//        }
    }

    /**
     * Converts and updates the incoming dto into a reimbursement instance
     * @param dto - the reimbursement data transfer object
     */
    public void updateEntity(ReimbursementDTO dto, int id) {

        String managerName = dto.getManager_fullName();
        String employeeName = dto.getEmployee_fullName();
        Manager manager = managerRepository.findByFullName(managerName);
        Employee employee = employeeRepository.findByFullName(employeeName);

        String status = dto.getStatus();

        logger.debug("Status: {}", managerRepository.findByFullName(managerName));
        logger.debug("Find manger via full name: {}", dto.getStatus());
        logger.debug("Reimbursement id: {}", id);

        reimbursementRepo.updateReimbursementById(status, manager, id);

        // create new email
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = formatter.format(now);

        String content = "A recent change has been made on your reimbursement request, ID: " + id;

        Email email = new Email(
                0,
                dateTime,
                employee.getEmail(),
                manager.getEmail(),
                "Update on Reimbursement Request",
                content
        );
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Email> newEmail = restTemplate.postForEntity(
                "http://localhost:9091/emails/new",
                email,
                Email.class
        );
        System.out.println(newEmail.getBody());
    }
}