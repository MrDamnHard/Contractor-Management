package com.rentr.contractormanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.rentr.contractormanagement.entity.WorkOrder;
import com.rentr.contractormanagement.enums.WorkOrderStatus;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    List<WorkOrder> findByJobId(Long jobId);
    List<WorkOrder> findByJobIdAndStatus(Long jobId, WorkOrderStatus status);
    List<WorkOrder> findByContractorIdAndStatus(Long contractorId, WorkOrderStatus status);
    Optional<WorkOrder> findByJobIdAndContractorId(Long jobId, Long contractorId);


}
