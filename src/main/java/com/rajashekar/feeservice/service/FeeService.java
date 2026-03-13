package com.rajashekar.feeservice.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajashekar.feeservice.dto.CollectFeeRequest;
import com.rajashekar.feeservice.dto.StudentResponse;
import com.rajashekar.feeservice.entity.Receipt;
import com.rajashekar.feeservice.repository.ReceiptRepository;

@Service
public class FeeService {

    private static final Logger logger = LogManager.getLogger(FeeService.class);

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private StudentClientService studentClientService;

    public Receipt collectFee(CollectFeeRequest request) {

        logger.info("Fee collection started for studentId: {}", request.getStudentId());

        StudentResponse student = studentClientService.getStudentById(request.getStudentId());

        logger.info("Student fetched successfully: {}", student.getStudentName());

        Receipt receipt = new Receipt();
        receipt.setStudentId(student.getStudentId());
        receipt.setStudentName(student.getStudentName());
        receipt.setSchoolName(student.getSchoolName());
        receipt.setGrade(student.getGrade());

        receipt.setCardNumber(request.getCardNumber());
        receipt.setCardType(request.getCardType());
        receipt.setFeeDescription(request.getFeeDescription());
        receipt.setAmount(request.getAmount());
        receipt.setTotal(request.getAmount());

        receipt.setReferenceNumber(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
        receipt.setDateTime(LocalDateTime.now());
        receipt.setStatus("SUCCESS");

        logger.info("Saving receipt for studentId: {}", student.getStudentId());

        Receipt savedReceipt = receiptRepository.save(receipt);

        logger.info("Receipt generated successfully. ReferenceNumber: {}", savedReceipt.getReferenceNumber());

        return savedReceipt;
    }
}