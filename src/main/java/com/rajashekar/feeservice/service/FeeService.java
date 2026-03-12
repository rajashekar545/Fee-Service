package com.rajashekar.feeservice.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajashekar.feeservice.dto.CollectFeeRequest;
import com.rajashekar.feeservice.dto.StudentResponse;
import com.rajashekar.feeservice.entity.Receipt;
import com.rajashekar.feeservice.repository.ReceiptRepository;

@Service
public class FeeService {

    @Autowired
    private ReceiptRepository receiptRepository;  
    
    @Autowired
    private StudentClientService studentClientService;    

    public Receipt collectFee(CollectFeeRequest request) {

    	StudentResponse student = studentClientService.getStudentById(request.getStudentId());
    	
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

        return receiptRepository.save(receipt);
    }
    
   
    
}