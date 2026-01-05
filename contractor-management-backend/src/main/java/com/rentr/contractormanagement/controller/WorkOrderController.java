package com.rentr.contractormanagement.controller;

import org.springframework.web.bind.annotation.*;

import com.rentr.contractormanagement.dto.ApplyWorkOrderRequest;
import com.rentr.contractormanagement.entity.User;
import com.rentr.contractormanagement.entity.WorkOrder;
import com.rentr.contractormanagement.service.WorkOrderService;
import java.util.List;

@RestController
@RequestMapping("/work-orders")
@CrossOrigin(origins = "http://localhost:3000")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    // Contractor applies for a job
    // Contractor applies for a job
    @PostMapping("/apply")
    public WorkOrder applyForJob(@RequestBody ApplyWorkOrderRequest request) {

        User contractor = new User();
        contractor.setId(request.getContractorId());

        return workOrderService.applyForJob(
                request.getJobId(),
                contractor,
                request.getNote()
        );
    }

    @GetMapping
    public java.util.List<WorkOrder> getAllWorkOrders() {
        return workOrderService.getAllWorkOrders();
    }

    @GetMapping("/contractor/{contractorId}/approved")
    public List<WorkOrder> getApprovedWorkOrdersForContractor(
            @PathVariable Long contractorId) {

        return workOrderService.getApprovedWorkOrdersForContractor(contractorId);
    }

    // Agent approves a work order
    @PutMapping("/{id}/approve")
    public String approveWorkOrder(@PathVariable Long id) {
        workOrderService.approveWorkOrder(id);
        return "Work order approved";
    }
}
