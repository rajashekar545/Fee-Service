package com.rajashekar.feeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajashekar.feeservice.dto.CollectFeeRequest;
import com.rajashekar.feeservice.entity.Receipt;
import com.rajashekar.feeservice.service.FeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fee")
@Tag(name = "Fee API", description = "Operations related to fee Collection")
public class FeeController {
 
	 	@Autowired
	    private FeeService feeService;

	    @PostMapping
	    @Operation(summary = "Collect fee")
	    public ResponseEntity<Receipt> collectFee(@Valid @RequestBody CollectFeeRequest request) {
	        Receipt receipt = feeService.collectFee(request);
	        return ResponseEntity.status(HttpStatus.CREATED).body(receipt);
	    }

}
