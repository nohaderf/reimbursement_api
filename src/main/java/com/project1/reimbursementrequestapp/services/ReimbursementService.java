package com.project1.reimbursementrequestapp.services;

import com.project1.reimbursementrequestapp.ReimbursementRequestApplication;
import com.project1.reimbursementrequestapp.dtos.ReimbursementDTO;
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
import org.springframework.stereotype.Service;
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

//    public Reimbursement getReimbursementById(int id) {
//
//        if (id <= 0 ) {
//            return new Reimbursement(-1, "No reimbursement exists", -10);
//        } else {
//            return Reimbursement;
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
        Manager manager = managerRepository.findByFullName(managerName);

        String status = dto.getStatus();

        logger.debug("Status: {}", managerRepository.findByFullName(managerName));
        logger.debug("Find manger via full name: {}", dto.getStatus());
        logger.debug("Reimbursement id: {}", id);

        reimbursementRepo.updateReimbursementById(status, manager, id);
    }
}