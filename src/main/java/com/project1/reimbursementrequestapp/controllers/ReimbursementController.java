package com.project1.reimbursementrequestapp.controllers;

import com.project1.reimbursementrequestapp.dtos.ReimbursementDTO;
import com.project1.reimbursementrequestapp.models.Email;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import com.project1.reimbursementrequestapp.repositories.ReimbursementRepo;
import com.project1.reimbursementrequestapp.services.ReimbursementService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("reimbursements")
public class ReimbursementController {

    final Logger logger = LoggerFactory.getLogger(ReimbursementController.class);

    @Setter(onMethod =@__({@Autowired}) )
    private ReimbursementRepo reimbursementRepo;

    @Autowired
    private ReimbursementService reimbursementService;

    /**
     * Get all reimbursements ✅
     * @return all reimbursement instances
     */
    @GetMapping
    public ResponseEntity<List<Reimbursement>> getAllReimbursements() {
        logger.debug("Get all reimbursements: {}", reimbursementRepo.findAll());
        return ResponseEntity.ok(reimbursementRepo.findAll());
    }

    /**
     * Get reimbursement by id ✅
     * @param id
     * @return reimbursement instance
     */

    @GetMapping(path="{id}") //reimbursements/{id}
    public ResponseEntity getReimbursementWithId(@PathVariable Integer id) {
        logger.debug("Get reimbursement with ID: {}", reimbursementRepo.findById(id));
        return ResponseEntity.ok(reimbursementRepo.findById(id));
    }

    /**
     * Get all reimbursement requests associated with manager id ✅
     * @param id
     * @return All reimbursement requests for manager with given id
     */
    @GetMapping(path="requests/managers/{id}")
    public ResponseEntity getAllReimbursementsWithManagerId(@PathVariable Integer id) {
        logger.debug("Get reimbursements associated with manager ID: {}", reimbursementRepo.findAllByManagerId(id));
        return ResponseEntity.ok(reimbursementRepo.findAllByManagerId(id));
    }

    /**
     * Get all reimbursement requests associated with employee id ✅
     * @param id
     * @return All reimbursement requests from employee with given id
     */
    @GetMapping(path="requests/employees/{id}")
    public ResponseEntity getAllReimbursementsWithEmployeeId(@PathVariable Integer id) {
        System.out.println(reimbursementRepo.findAllByEmployeeId(id));
        logger.debug("Get all reimbursements associated with Employee ID: {}", reimbursementRepo.findAllByEmployeeId(id));
        return ResponseEntity.ok(reimbursementRepo.findAllByEmployeeId(id));
    }

    /**
     * Create a new reimbursement -- by employee ✅
     * @param dto - the data transfer object
     */
    // post request also creates a new email
    // telling manager and employee that a new reimbursement request has been submitted
    @PostMapping("new")
    public void newReimbursement(@RequestBody ReimbursementDTO dto) {
        reimbursementService.convertToEntity(dto);
    }

    /**
     * Update reimbursement -- by manager ✅
     * @param dto - the data transfer object
     */
    // put request creates a new email
    // telling manager and employee that a new reimbursement request has been submitted
    @PutMapping("requests/{reimbursementId}")
    public void updateReimbursement(
            @RequestBody ReimbursementDTO dto,
            @PathVariable int reimbursementId
    ) {
        reimbursementService.updateEntity(dto, reimbursementId);
    }
}
