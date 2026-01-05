package com.rentr.contractormanagement.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.rentr.contractormanagement.entity.Job;
import com.rentr.contractormanagement.entity.User;
import com.rentr.contractormanagement.entity.WorkOrder;

import com.rentr.contractormanagement.enums.JobStatus;
import com.rentr.contractormanagement.enums.WorkOrderStatus;

import com.rentr.contractormanagement.repository.JobRepository;
import com.rentr.contractormanagement.repository.WorkOrderRepository;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final JobRepository jobRepository;

    public WorkOrderService(WorkOrderRepository workOrderRepository,
                            JobRepository jobRepository) {
        this.workOrderRepository = workOrderRepository;
        this.jobRepository = jobRepository;
    }
    public List<WorkOrder> getAllWorkOrders() {
        return workOrderRepository.findAll();
    }

    public List<WorkOrder> getApprovedWorkOrdersForContractor(Long contractorId) {
        return workOrderRepository.findByContractorIdAndStatus(
                contractorId,
                WorkOrderStatus.APPROVED
        );
    }

    // Contractor applies for a job
    public WorkOrder applyForJob(Long jobId, User contractor, String note) {

        Optional<WorkOrder> existing =
                workOrderRepository.findByJobIdAndContractorId(jobId, contractor.getId());

        if (existing.isPresent()) {
            throw new IllegalStateException("Contractor has already applied for this job");
        }

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        WorkOrder workOrder = new WorkOrder();
        workOrder.setJob(job);
        workOrder.setContractor(contractor);
        workOrder.setProposalNote(note);
        workOrder.setStatus(WorkOrderStatus.SUBMITTED);

        return workOrderRepository.save(workOrder);
    }


    // Agent approves a contractor
    public void approveWorkOrder(Long workOrderId) {
        WorkOrder approved = workOrderRepository.findById(workOrderId)
                .orElseThrow(() -> new RuntimeException("WorkOrder not found"));

        Job job = approved.getJob();

        // approve selected one
        approved.setStatus(WorkOrderStatus.APPROVED);
        workOrderRepository.save(approved);

        // reject others
        List<WorkOrder> others =
                workOrderRepository.findByJobIdAndStatus(job.getId(), WorkOrderStatus.SUBMITTED);

        for (WorkOrder wo : others) {
            wo.setStatus(WorkOrderStatus.REJECTED);
            workOrderRepository.save(wo);
        }

        // update job status
        job.setStatus(JobStatus.ASSIGNED);
        jobRepository.save(job);
    }
}
