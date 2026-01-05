package com.rentr.contractormanagement.controller;

import com.rentr.contractormanagement.entity.Invoice;
import com.rentr.contractormanagement.enums.InvoiceStatus;
import com.rentr.contractormanagement.service.InvoiceService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@CrossOrigin(origins = "http://localhost:3000")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Contractor submits invoice
    @PostMapping
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        invoice.setStatus(InvoiceStatus.SUBMITTED);
        return invoiceService.saveInvoice(invoice);
    }

    // Agent views all invoices (read-only)
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
}
