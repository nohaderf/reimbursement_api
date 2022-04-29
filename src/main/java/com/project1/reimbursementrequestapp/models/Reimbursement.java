package com.project1.reimbursementrequestapp.models;

import com.project1.reimbursementrequestapp.repositories.EmployeeRepository;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Reimbursements")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reimbursement {

    @Id // treat this state as the primary key
    @Column(name="id", columnDefinition = "AUTO_INCREMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.AUTO
    private int id;

    @Column(name = "date", nullable = false) // column in DB table, can't be null
    private String date;
    private String description;
    private double amount;
    private String status;
    private String submitDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="manager_id", referencedColumnName = "id")
    private Manager manager;
}
