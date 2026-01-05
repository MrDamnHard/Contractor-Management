package com.rentr.contractormanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rentr.contractormanagement.entity.Invoice;
import com.rentr.contractormanagement.repository.InvoiceRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice saveInvoice(Invoice invoice) {

        Long workOrderId = invoice.getWorkOrder().getId();

        boolean alreadyExists =
                invoiceRepository.findByWorkOrderId(workOrderId).isPresent();

        if (alreadyExists) {
            throw new IllegalStateException("Invoice already submitted for this work order");
        }

        return invoiceRepository.save(invoice);
    }

}
