# FocusNest

FocusNest is a lightweight productivity and knowledge management REST API built with Java and Spring Boot.

The project combines task management, notes, and focus sessions into one application. It is being developed as a portfolio project with a focus on clean architecture, REST APIs, validation, testing, and database integration.

> FocusNest is currently under active development.

## Features

### Tasks

* Create tasks
* Get all tasks
* Get a task by ID
* Update tasks
* Delete tasks
* Task status and priority
* Due dates

### API & Backend

* RESTful API built with Spring Boot
* PostgreSQL database integration
* Spring Data JPA
* Input validation
* Global exception handling
* Structured JSON error responses
* Custom `TaskNotFoundException`

### Planned features

* Notes
* Focus sessions
* Dashboard
* Search
* Tags
* Statistics
* Authentication and users
* Frontend application
* Automated test coverage

## Tech Stack

* **Java 21**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **PostgreSQL**
* **Maven**
* **Jakarta Validation**
* **JUnit 5**
* **Mockito**
* **Git & GitHub**

## Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

### Controller

Handles HTTP requests and exposes the REST API.

### Service

Contains the application's business logic and coordinates operations between controllers and repositories.

### Repository

Uses Spring Data JPA to communicate with the database.

### Model

Contains the application's domain entities and enums.

### Exception handling

The API uses a global exception handler to return structured JSON responses for validation errors and missing resources.

## API

### Health check

```http
GET /api/health
```

Returns:

```text
FocusNest is running!
```

### Tasks

| Method | Endpoint          | Description   |
| ------ | ----------------- | ------------- |
| GET    | `/api/tasks`      | Get all tasks |
| GET    | `/api/tasks/{id}` | Get a task    |
| POST   | `/api/tasks`      | Create a task |
| PUT    | `/api/tasks/{id}` | Update a task |
| DELETE | `/api/tasks/{id}` | Delete a task |

### Example task

```json
{
  "title": "Build the Task API",
  "description": "Implement CRUD operations for tasks",
  "status": "TODO",
  "priority": "HIGH",
  "dueDate": "2026-10-10"
}
```

## Validation

Tasks currently include validation for:

* Required title
* Maximum title length
* Maximum description length
* Required status
* Required priority

Validation errors are returned as structured JSON responses.

Example:

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "title": "Title is required"
  }
}
```

## Getting Started

### Prerequisites

You will need:

* Java 21
* Maven
* PostgreSQL

### Clone the repository

```bash
git clone git@github.com:johannesswenson/focusnest.git
cd focusnest
```

### Configure the database

Create a PostgreSQL database named:

```text
focusnest
```

The application uses environment variables for the database configuration:

```text
DB_USERNAME
DB_PASSWORD
```

The database URL defaults to:

```text
jdbc:postgresql://localhost:5432/focusnest
```

### Run the application

Using the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

## Running Tests

Run the test suite with:

```bash
./mvnw test
```

## Project Status

FocusNest is being developed incrementally.

The current implementation contains the foundation of the application, including:

* Spring Boot setup
* PostgreSQL integration
* Task domain model
* Task CRUD API
* Validation
* Global exception handling
* Git version control
* GitHub repository

The next development stages will focus on expanding the domain with notes and focus sessions, improving test coverage, and eventually adding authentication and a frontend.

## Why FocusNest?

FocusNest is a portfolio project designed to demonstrate practical backend development with Java and Spring Boot.

The goal is not only to build features, but also to practice:

* Clean code
* Layered architecture
* REST API design
* Database persistence
* Validation
* Exception handling
* Automated testing
* Git workflows
* Documentation
* Continuous integration
* Containerization
