package com.project1.reimbursementrequestapp.repositories;

import com.project1.reimbursementrequestapp.models.Manager;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementRepo extends JpaRepository<Reimbursement, Integer> {

    /**
     * Finds all reimbursements associated with the manager with given ID
     * @param id - the manager's ID
     * @return the list of reimbursements associated with this manager
     */
    List<Reimbursement> findAllByManagerId(@Param("id") int id);

    /**
     * Finds all reimbursements associated with the employee with given ID
     * @param id - the empployee's ID
     * @return the list of reimbursements associated with this employee
     */
    List<Reimbursement> findAllByEmployeeId(@Param("id") int id);

    /**
     * Updates the reimbursement instance based on the query
     * @param status - status of the reimbursement request
     * @param manager - manager the reimbursement request has been assigned to
     * @param id - id of the reimbursement request
     */
    @Modifying
    @Query("update Reimbursement r set r.status = ?1, r.manager = ?2 where r.id = ?3")
    void updateReimbursementById(String status, Manager manager, int id);
}
