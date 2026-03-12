
# Fee Service

This service handles student fee payments and generates payment receipts.  
Before processing a payment, it calls the **Student Service** to fetch and validate student information.

The project is built using **Spring Boot** and follows a simple microservices approach where each service has a clearly defined responsibility.

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Swagger / OpenAPI
- Resilience4j (Circuit Breaker)
- Apache PDFBox (PDF generation)

---

## Prerequisites

Make sure the following tools are installed:

- Java 17+
- Maven
- Git
- Postman (optional for API testing)

---

## How to Run the Service

This service depends on **Student Service**, so start that first.

### Step 1 — Start Student Service

cd student-service  
mvn spring-boot:run

Student Service will run on:

http://localhost:8081

---

### Step 2 — Start Fee Service

cd fee-service  
mvn spring-boot:run

Fee Service will run on:

http://localhost:8082

---

## API Documentation (Swagger)

Swagger UI:

http://localhost:8082/swagger-ui.html

OpenAPI specification:

http://localhost:8082/v3/api-docs

---

## Postman Collection

A Postman collection is included in the repository for testing APIs.

Typical workflow:

1. Create a student using **Student Service**
2. Call **Fee Service** to collect fee
3. Retrieve the receipt
4. Download the receipt as PDF

---

### Collect Fee

POST /fee

Example request body:

{
  "studentId": 1,
  "cardNumber": "1234-5678-1236-0081",
  "cardType": "MASTERCARD",
  "feeDescription": "Tuition Fees",
  "amount": 2500
}

---

### Get Receipt

GET /receipts/{id}

---

### Download Receipt PDF

GET /receipts/{id}/pdf

---

## H2 Database Console

http://localhost:8082/h2-console

JDBC URL:

jdbc:h2:mem:feedb

---

## Error Handling

Global exception handling is implemented using `@RestControllerAdvice`.

Example error response:

{
  "message": "Student service is temporarily unavailable",
  "status": 503
}

---

## Circuit Breaker

A circuit breaker protects calls to the **Student Service**.  
If the student service becomes unavailable, repeated calls are stopped temporarily and a fallback response is returned.


