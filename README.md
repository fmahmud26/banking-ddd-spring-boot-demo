# Banking DDD Spring Boot Demo

[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.x-blue.svg)](https://maven.apache.org/)

A demonstration banking application built with **Domain-Driven Design (DDD)** and **Hexagonal Architecture** principles using Spring Boot. This project showcases clean architecture, separation of concerns, and rich domain models.

## 📋 Table of Contents

- [Features](#-features)
- [Architecture](#-architecture)
- [Technology Stack](#-technology-stack)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [Running the Application](#-running-the-application)
- [API Documentation](#-api-documentation)
- [Project Structure](#-project-structure)
- [Design Patterns](#-design-patterns)
- [Database](#-database)
- [Testing](#-testing)

## ✨ Features

- **Account Management**: Create and manage bank accounts
- **Money Operations**: Deposit, withdraw, and transfer money between accounts
- **Domain-Driven Design**: Rich domain models with business logic encapsulation
- **Hexagonal Architecture**: Clean separation between domain, application, and infrastructure layers
- **RESTful API**: REST endpoints for all banking operations
- **In-Memory Database**: H2 database for easy development and testing

## 🏗️ Architecture

This project follows **Domain-Driven Design (DDD)** principles with **Hexagonal Architecture** (also known as Ports and Adapters). The architecture is organized into four distinct layers:

```
┌─────────────────────────────────────────────────────────┐
│                    Interfaces Layer                     │
│              (REST Controllers, DTOs)                   │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│                 Application Layer                       │
│              (Use Cases, Services)                      │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│                   Domain Layer                          │
│        (Entities, Value Objects, Domain Logic)          │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│              Infrastructure Layer                       │
│    (Persistence, External Services, Adapters)           │
└─────────────────────────────────────────────────────────┘
```

### Layer Responsibilities

1. **Domain Layer** (`domain/`): Contains the core business logic, entities, value objects, and repository interfaces. This layer is framework-agnostic and contains no dependencies on external libraries.

2. **Application Layer** (`application/`): Orchestrates domain objects to perform application-specific tasks. Contains use cases that coordinate domain operations.

3. **Infrastructure Layer** (`infrastructure/`): Implements technical concerns such as persistence (JPA), external API clients, and framework-specific adapters.

4. **Interfaces Layer** (`interfaces/`): Handles external communication, including REST controllers, web interfaces, and API contracts.

## 🛠️ Technology Stack

- **Java**: 25
- **Spring Boot**: 4.0.1
- **Spring Data JPA**: For data persistence
- **H2 Database**: In-memory database for development
- **Maven**: Build tool and dependency management
- **JUnit 5**: Testing framework (via Spring Boot Test)

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 25 or higher
- **Maven**: Version 3.6+ (or use the included Maven Wrapper)
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code (recommended)

## 🚀 Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd banking-ddd-spring-boot-demo
```

### Build the Project

Using Maven Wrapper (recommended):

```bash
./mvnw clean install
```

Or using Maven directly:

```bash
mvn clean install
```

## ▶️ Running the Application

### Using Maven Wrapper

```bash
./mvnw spring-boot:run
```

### Using Maven

```bash
mvn spring-boot:run
```

### Using Java directly

```bash
java -jar target/banking-ddd-spring-boot-demo-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080` by default.

### Access H2 Console

Once the application is running, you can access the H2 database console at:

```
http://localhost:8080/h2
```

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:bankdb`
- Username: `sa`
- Password: (leave empty)

## 📚 API Documentation

### Base URL

```
http://localhost:8080/accounts
```

### Endpoints

#### 1. Create Account

Creates a new bank account with the specified account number.

```http
POST /accounts
Content-Type: application/x-www-form-urlencoded
```

**Parameters:**
- `accountNumber` (String, required): 13-digit account number

**Example:**
```bash
curl -X POST "http://localhost:8080/accounts?accountNumber=1234567890123"
```

#### 2. Deposit Money

Deposits money into an existing account.

```http
POST /accounts/{accNo}/deposit
Content-Type: application/x-www-form-urlencoded
```

**Parameters:**
- `accNo` (String, path): Account number (13 digits)
- `amount` (BigDecimal, required): Amount to deposit (must be positive)

**Example:**
```bash
curl -X POST "http://localhost:8080/accounts/1234567890123/deposit?amount=1000.00"
```

#### 3. Withdraw Money

Withdraws money from an existing account.

```http
POST /accounts/{accNo}/withdraw
Content-Type: application/x-www-form-urlencoded
```

**Parameters:**
- `accNo` (String, path): Account number (13 digits)
- `amount` (BigDecimal, required): Amount to withdraw (must not exceed balance)

**Example:**
```bash
curl -X POST "http://localhost:8080/accounts/1234567890123/withdraw?amount=500.00"
```

#### 4. Transfer Money

Transfers money from one account to another.

```http
POST /accounts/transfer
Content-Type: application/x-www-form-urlencoded
```

**Parameters:**
- `fromAccount` (String, required): Source account number (13 digits)
- `toAccount` (String, required): Destination account number (13 digits)
- `amount` (BigDecimal, required): Amount to transfer

**Example:**
```bash
curl -X POST "http://localhost:8080/accounts/transfer?fromAccount=1234567890123&toAccount=9876543210987&amount=200.00"
```

### Business Rules

- **Account Number**: Must be exactly 13 digits
- **Balance**: Cannot be negative
- **Deposit**: Must be a positive amount
- **Withdrawal**: Cannot exceed the current balance
- **Transfer**: Both accounts must exist, and the source account must have sufficient balance

## 📁 Project Structure

```
banking-ddd-spring-boot-demo/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/banking/
│   │   │   ├── application/
│   │   │   │   └── usecase/
│   │   │   │       ├── CreateAccountUseCase.java
│   │   │   │       ├── DepositMoneyUseCase.java
│   │   │   │       ├── WithdrawMoneyUseCase.java
│   │   │   │       └── SendMoneyUseCase.java
│   │   │   │
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   ├── Account.java          # Aggregate Root
│   │   │   │   │   ├── AccountNumber.java   # Value Object
│   │   │   │   │   └── Balance.java         # Value Object
│   │   │   │   └── repository/
│   │   │   │       └── AccountRepository.java  # Repository Interface
│   │   │   │
│   │   │   ├── infrastructure/
│   │   │   │   └── persistence/
│   │   │   │       ├── AccountEntity.java              # JPA Entity
│   │   │   │       ├── JpaAccountRepository.java       # Repository Implementation
│   │   │   │       └── SpringDataAccountRepository.java # Spring Data Interface
│   │   │   │
│   │   │   ├── interfaces/
│   │   │   │   └── rest/
│   │   │   │       └── AccountController.java  # REST Controller
│   │   │   │
│   │   │   └── BankingApplication.java  # Main Application Class
│   │   │
│   │   └── resources/
│   │       └── application.yaml  # Application Configuration
│   │
│   └── test/
│       └── java/com/example/banking/
│           └── BankingApplicationTests.java
│
├── pom.xml                    # Maven Configuration
├── mvnw                       # Maven Wrapper (Unix)
├── mvnw.cmd                   # Maven Wrapper (Windows)
└── README.md                  # This File
```

## 🎨 Design Patterns

This project implements several important design patterns:

### 1. Repository Pattern
- **Interface**: `AccountRepository` in the domain layer
- **Implementation**: `JpaAccountRepository` in the infrastructure layer
- **Purpose**: Abstracts data access logic and decouples domain from persistence

### 2. Use Case Pattern
- Each use case is a separate service class with a single `execute()` method
- Encapsulates application-specific workflows
- Examples: `CreateAccountUseCase`, `DepositMoneyUseCase`, etc.

### 3. Value Object Pattern
- **AccountNumber**: Immutable record with validation
- **Balance**: Immutable class enforcing business rules
- Ensures data integrity and prevents invalid states

### 4. Aggregate Pattern
- **Account** is the aggregate root
- Encapsulates related entities and value objects
- Maintains consistency boundaries

### 5. Dependency Inversion Principle
- Domain layer defines interfaces (ports)
- Infrastructure layer implements them (adapters)
- Dependencies point inward toward the domain

### 6. Hexagonal Architecture (Ports & Adapters)
- Clear separation between business logic and technical concerns
- Easy to swap implementations (e.g., change database, add new interfaces)

## 💾 Database

The application uses **H2**, an in-memory database, which is perfect for development and testing.

### Database Schema

The `AccountEntity` table structure:

| Column        | Type       | Description                    |
|---------------|------------|--------------------------------|
| accountNumber | VARCHAR    | Primary Key (13 digits)        |
| balance       | DECIMAL    | Account balance (non-negative) |

### Database Configuration

Configuration is defined in `application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:bankdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  h2:
    console:
      enabled: true
      path: /h2
```

**Note**: Data is stored in memory and will be lost when the application stops. For production, configure a persistent database (PostgreSQL, MySQL, etc.).

## 🧪 Testing

### Running Tests

```bash
./mvnw test
```

### Test Structure

The project includes:
- **Unit Tests**: Test individual components in isolation
- **Integration Tests**: Test the full application context

### Example Test

```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=BankingApplicationTests
```

### Code Style

- Follow Java naming conventions
- Maintain the layered architecture structure
- Write meaningful commit messages
- Add tests for new features

## 🙏 Acknowledgments

- Built with [Spring Boot](https://spring.io/projects/spring-boot)
- Inspired by Domain-Driven Design principles by Eric Evans
- Architecture based on Hexagonal Architecture (Ports & Adapters) by Alistair Cockburn

**Happy Coding! 🚀**
