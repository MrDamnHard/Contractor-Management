package com.rentr.contractormanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rentr.contractormanagement.entity.Invoice;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByWorkOrderId(Long workOrderId);

}
