# TaskFlow

A secure **Task Management REST API** built with **Java 21**, **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, **Hibernate**, and **H2 Database**.

TaskFlow demonstrates enterprise backend development practices such as layered architecture, RESTful APIs, authentication & authorization, DTO pattern, exception handling, and project-task relationships.

---

## ✨ Features

- 🔐 JWT Authentication
- 👤 User Registration & Login
- 🛡️ Role-Based Authorization (ADMIN / USER)
- 📋 CRUD Operations for Tasks
- 📁 Project Management
- 🔄 Move Tasks Between Projects
- ✅ Request Validation
- ⚠️ Global Exception Handling
- 💾 Spring Data JPA + Hibernate
- 🗄️ H2 Database
- 🧪 REST APIs tested with Postman

---

# 🛠️ Tech Stack

| Technology | Version |
|------------|----------|
| Java | 21 |
| Spring Boot | 3.x |
| Spring Security | Latest |
| Spring Data JPA | Latest |
| Hibernate | Latest |
| JWT | jjwt |
| H2 Database | In-Memory |
| Maven | Build Tool |

---

# Project Structure

```
src
└── main
    ├── java
    │   └── com.codewithsuraj.taskflow
    │       ├── controller
    │       ├── dto
    │       ├── entity
    │       ├── exception
    │       ├── repository
    │       ├── security
    │       ├── service
    │       └── TaskflowApplication.java
    │
    └── resources
        ├── application.yml
        
```

---

# Architecture

```mermaid
flowchart TD

Client[Postman / Frontend]

Client --> Controller

subgraph Spring Boot

Controller --> Service

Service --> Repository

Repository --> H2[(H2 Database)]

end

subgraph Security

JWTFilter[JWT Authentication Filter]

UserDetailsService

JwtService

AuthenticationManager

end

Client --> JWTFilter

JWTFilter --> AuthenticationManager

AuthenticationManager --> UserDetailsService

JWTFilter --> Controller

Service --> JwtService
```

---

# Security Architecture

```mermaid
flowchart LR

Request --> JwtAuthFilter

JwtAuthFilter --> JwtService

JwtService --> ValidateToken

ValidateToken --> UserDetailsService

UserDetailsService --> UserRepository

UserRepository --> H2

JwtAuthFilter --> SecurityContext

SecurityContext --> Controller
```

---

# REST APIs

## Authentication

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /auth/register | Register User |
| POST | /auth/login | Login & Generate JWT |

---

## Tasks

| Method | Endpoint |
|---------|-----------|
| GET | /tasks |
| GET | /tasks/{id} |
| POST | /tasks |
| PUT | /tasks/{id} |
| DELETE | /tasks/{id} |

---

# JWT Authentication

Example Header

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

# Running the Project

Clone the repository

```bash
git clone https://github.com/<your-username>/taskflow.git
```

Navigate to the project

```bash
cd taskflow
```

Run

```bash
mvn spring-boot:run
```

Application starts at

```
http://localhost:8080
```

---

# H2 Console

```
http://localhost:8080/h2-console
```

JDBC URL

```
jdbc:h2:mem:taskflow
```

Username

```
sa
```

Password

```
(blank)
```
