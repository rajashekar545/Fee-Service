package com.rajashekar.feeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rajashekar.feeservice.dto.StudentResponse;
import com.rajashekar.feeservice.exception.ResourceNotFoundException;
import com.rajashekar.feeservice.exception.StudentServiceUnavailableException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class StudentClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${student.service.url}")
    private String studentServiceUrl;

    @CircuitBreaker(name = "studentService", fallbackMethod = "studentServiceFallback")
    public StudentResponse getStudentById(Long studentId) {
        StudentResponse student = restTemplate.getForObject(
                studentServiceUrl + "/" + studentId,
                StudentResponse.class
        );

        if (student == null) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }

        return student;
    }

    public StudentResponse studentServiceFallback(Long studentId, Throwable t) {
        throw new StudentServiceUnavailableException(
                "Student service is temporarily unavailable for student id: " + studentId
        );
    }
}