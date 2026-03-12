package com.rajashekar.feeservice.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rajashekar.feeservice.dto.StudentResponse;

public interface StudentClient {

    @GetMapping("/students/{id}")
    StudentResponse getStudentById(@PathVariable Long id);
}