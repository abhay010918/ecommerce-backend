package com.ecommerce.backend.controller;

import com.ecommerce.backend.repository.ReviewRepository;
import com.ecommerce.backend.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateInvoice() throws IOException {

        String html = Files.readString(Path.of("src/main/resources/templates/invoice.html"));
        invoiceService.generateInvoice(html, "invoice/invoice.pdf");
        return ResponseEntity.ok("invoice generated successfully");
    }
}
