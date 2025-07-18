from pathlib import Path

# Content of the updated README file
readme_content = """
# 📦 Microservices-based Excel Importer System

This project is a **Spring Boot Microservices Architecture** for securely importing Excel files from AWS S3 into a MySQL database using **Spring Batch**, with full support for **JWT-based authentication**, **API Gateway**, **Service Discovery**, and more.

---

## 📚 Project Overview

This system is built using the **Spring Boot Microservices pattern**, split into dedicated services to promote scalability, modularity, and ease of maintenance.

### 🧩 Microservices in this project:

| Service               | Description                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| `auth-service`         | Handles user authentication and issues JWT tokens.                         |
| `api-gateway`          | Central entry point — **forwards requests only**, does **not** validate JWT.|
| `file-service`         | Handles file uploads/downloads to AWS S3 or MinIO.                         |
| `metadata-service`     | Stores metadata info about uploaded files.                                 |
| `excel-import-service` | Downloads Excel from S3/MinIO and imports data into MySQL via Spring Batch.|
| `discovery-server`     | Service discovery for all microservices.                                   |

---

## 🔐 Authentication Flow (JWT)

- Users log in via `auth-service`, and receive a JWT.
- The `api-gateway` only forwards requests to internal services.
- Each service (`file-service`, `excel-import-service`, etc.) **independently validates** JWTs using its own `JwtAuthFilter` and `SecurityConfig`.

---

## ✅ Features

- ✅ Microservices Architecture with Spring Boot
- ✅ Spring Cloud Gateway + Eureka Discovery
- ✅ JWT Authentication handled per-service
- ✅ File Upload & Storage to AWS S3 or MinIO
- ✅ Excel File Processing with Spring Batch
- ✅ MySQL Integration with Spring Data JPA
- ✅ Clean and Modular Code Structure
- ✅ Import Excel (.xlsx) files
- ✅ Use Spring Batch for efficient chunk processing
- ✅ Connect with AWS S3 / MinIO for file storage
- ✅ Store data into a relational DB (MySQL)
- ✅ JWT-based security — **handled locally in each service**
- ✅ Microservice-compatible — fully integrated with service discovery and API gateway

---

## 🧱 Tech Stack

- Java 17+
- Spring Boot 3
- Spring Batch
- Spring Security (JWT)
- MySQL
- MinIO or AWS S3 (configurable)
- Spring Web / REST
- Spring Data JPA
- Lombok
- Eureka Discovery Client (optional)

---

## 🧩 Key Components

| Component               | Description                               |
|------------------------|-------------------------------------------|
| `S3ExcelReaderService` | Downloads and parses Excel from S3/MinIO  |
| `ExcelRowItemReader`   | Reads rows into Java objects              |
| `ExcelRowItemWriter`   | Saves data using `ExcelRowRepository`     |
| `BatchConfig`          | Spring Batch job + step configuration     |
| `SecurityConfig`       | Configures JWT authentication locally     |
| `JwtAuthFilter`        | Intercepts and validates JWT tokens       |
| `JwtUtil`              | Extracts and verifies token claims        |

---

