package com.rajashekar.feeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rajashekar.feeservice.entity.Receipt;
import com.rajashekar.feeservice.service.ReceiptService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/receipts")
@Tag(name = "Receipts API", description = "Operations related to Receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

  
    @GetMapping
    @Operation(summary = "Get All Students Receipts")
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Receipt by id")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable("id") Long receiptId) {
        return ResponseEntity.ok(receiptService.getReceiptById(receiptId));
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get Receipt by Student id")
    public ResponseEntity<List<Receipt>> getReceiptsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(receiptService.getReceiptsByStudentId(studentId));
    }
    @GetMapping("/{id}/pdf")
    @Operation(summary = "Download Reciept in PDF")
    public ResponseEntity<byte[]> downloadReceiptPdf(@PathVariable("id") Long receiptId) {
        byte[] pdfBytes = receiptService.generateReceiptPdf(receiptId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.attachment()
                        .filename("receipt-" + receiptId + ".pdf")
                        .build()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}