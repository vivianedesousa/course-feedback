# Course Feedback Platform

Cloud-native platform for collecting anonymous course feedback, generating management reports, and notifying administrators about critical evaluations. The solution was developed using **Java 17**, **Quarkus**, **Docker**, **GraalVM Native Image**, and **Amazon Web Services (AWS)**, following a cloud-native and serverless architecture.

---

# Overview

Course Feedback Platform enables students to anonymously evaluate academic courses while providing administrators with tools to manage courses, monitor submitted feedback, generate reports, and receive notifications whenever critical feedback is identified.

The solution follows a microservices-oriented architecture composed of one REST API and two serverless applications that communicate through AWS cloud services.

---

# Project Structure

```
course-feedback
│
├── course-feedback-api
├── course-feedback-notification
└── course-feedback-weekly-report
```

---

# Applications

## course-feedback-api

Main REST API responsible for the business logic of the platform.

### Features

- User registration
- JWT authentication
- Administrator authentication
- Course management
- Anonymous feedback submission
- Feedback consultation
- Report generation
- Critical feedback classification
- Integration with Amazon DynamoDB
- Publication of notification events to Amazon SNS

### Technologies

- Java 17
- Quarkus
- REST API
- JWT Authentication
- Docker
- GraalVM Native Image
- Amazon DynamoDB
- Amazon SNS

---

## course-feedback-notification

Serverless application executed through **AWS Lambda**.

This component is responsible for processing notifications generated whenever a student submits critical feedback.

The REST API publishes an event to an Amazon SNS topic. The Lambda function consumes the message, processes the notification, and sends an email to the platform administrator.

### Responsibilities

- Consume Amazon SNS messages
- Process notification events
- Send email notifications
- Execute asynchronously without impacting API performance

### Technologies

- Java 17
- Quarkus
- AWS Lambda
- Amazon SNS

---

## course-feedback-weekly-report

Serverless application responsible for generating weekly feedback reports.

The function is automatically executed through Amazon EventBridge Scheduler. Generated reports can be stored in Amazon S3 for future consultation and download.

### Responsibilities

- Generate weekly reports
- Calculate feedback statistics
- Aggregate feedback information
- Store reports in Amazon S3
- Execute automatically through scheduled events

### Technologies

- Java 17
- Quarkus
- AWS Lambda
- Amazon EventBridge Scheduler
- Amazon S3

---

# AWS Services

The platform integrates the following AWS cloud services:

- Amazon API Gateway
- Amazon ECS Fargate
- Amazon Elastic Container Registry (ECR)
- Amazon DynamoDB
- Amazon SNS
- AWS Lambda
- Amazon EventBridge Scheduler
- Amazon S3
- Amazon CloudWatch

---

# Main Features

- JWT authentication and authorization
- Role-based access control (Administrator and Student)
- Course management
- Anonymous feedback submission
- Automatic feedback criticality classification
- Critical feedback notifications
- Weekly report generation
- RESTful API
- OpenAPI (Swagger) documentation
- Docker containerization
- Native compilation using GraalVM
- Serverless event-driven processing

---

# Project Architecture

The platform follows a cloud-native architecture composed of one REST API and two independent serverless applications.

1. Clients access the platform through Amazon API Gateway.
2. The REST API is executed inside Docker containers deployed on Amazon ECS Fargate.
3. Application data is stored in Amazon DynamoDB.
4. Whenever a critical feedback is submitted, the API publishes an event to Amazon SNS.
5. Amazon SNS triggers the Notification Lambda, which processes the message and sends an email notification to the administrator.
6. Amazon EventBridge Scheduler periodically invokes the Weekly Report Lambda.
7. Weekly reports are generated and stored in Amazon S3.
8. Amazon CloudWatch collects logs and execution metrics for monitoring the platform.

---

# Technologies

- Java 17
- Quarkus
- Maven
- Docker
- GraalVM Native Image
- JWT
- REST API
- OpenAPI (Swagger)
- Amazon Web Services (AWS)

---

# Repository Structure

```
course-feedback
│
├── course-feedback-api                 # REST API
├── course-feedback-notification        # Notification Lambda
└── course-feedback-weekly-report       # Weekly Report Lambda
```

---

# Running the Project

Each module is an independent Maven project.

Clone the repository:

```bash
git clone https://github.com/vivianedesousa/course-feedback.git
```

Navigate to the desired module:

```bash
cd course-feedback/course-feedback-api
```

Run the application:

```bash
./mvnw quarkus:dev
```

The same process applies to the Notification and Weekly Report modules.

---

# API Documentation

The REST API provides OpenAPI (Swagger) documentation.

After starting the application, the documentation is available at:

```
http://localhost:8080/q/swagger-ui
```

---

# Academic Project

This project was developed for academic purposes as part of a Cloud Computing and Serverless Application Development study. It demonstrates the integration of Java, Quarkus, Docker, GraalVM Native Image, REST APIs, and AWS cloud services using a cloud-native and event-driven architecture.

---

# License

This project is intended exclusively for educational and academic purposes.
