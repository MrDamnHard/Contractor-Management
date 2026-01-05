package com.rentr.contractormanagement.entity;

import jakarta.persistence.*;
import com.rentr.contractormanagement.enums.WorkOrderStatus;

@Entity
@Table(name = "work_orders")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Job job;

    @ManyToOne
    private User contractor;

    private String proposalNote;

    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getContractor() {
        return contractor;
    }

    public void setContractor(User contractor) {
        this.contractor = contractor;
    }

    public String getProposalNote() {
        return proposalNote;
    }

    public void setProposalNote(String proposalNote) {
        this.proposalNote = proposalNote;
    }

    public WorkOrderStatus getStatus() {
        return status;
    }

    public void setStatus(WorkOrderStatus status) {
        this.status = status;
    }
}
