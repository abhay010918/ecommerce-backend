package com.ecommerce.backend.controller;

import com.ecommerce.backend.service.services.InvoiceService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

        Path path = new ClassPathResource("templates/invoice.html").getFile().toPath();
        String html = Files.readString(path, StandardCharsets.UTF_8);
        invoiceService.generateInvoice(html, "invoice/invoice.pdf");
        return ResponseEntity.ok("Invoice generated: invoice/invoice.pdf");
    }
}
