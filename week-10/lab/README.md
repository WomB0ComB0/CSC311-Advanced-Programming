# Spring Boot Lab - Database and Thymeleaf

A complete Spring Boot application demonstrating database integration with JPA and web interface using Thymeleaf template engine.

## Features

- **Spring Boot 3.5.7** with Java 17
- **Spring Data JPA** for database operations
- **H2 Database** (in-memory) for easy testing
- **Thymeleaf** template engine for server-side rendering
- **CRUD Operations** - Create, Read, Update, Delete students
- **RESTful architecture** with MVC pattern
- **Bootstrap-like styling** (custom CSS)

## Project Structure

```
src/main/java/com/example/lab/
├── LabApplication.java           # Main application entry point
├── config/
│   └── DataInitializer.java      # Sample data loader
├── controller/
│   ├── HomeController.java       # Root endpoint
│   └── StudentController.java    # Student CRUD endpoints
├── model/
│   └── Student.java               # JPA Entity
├── repository/
│   └── StudentRepository.java    # Data access layer
└── service/
    └── StudentService.java        # Business logic layer

src/main/resources/
├── application.properties         # Configuration
└── templates/
    └── students/
        ├── list.html              # Student list view
        └── form.html              # Add/Edit student form
```

## Technologies Used

- **Spring Boot Starter Web** - REST API and MVC
- **Spring Boot Starter Data JPA** - Database ORM
- **Spring Boot Starter Thymeleaf** - Template engine
- **H2 Database** - In-memory database
- **Spring Boot DevTools** - Hot reload for development

## How to Run

### Option 1: Using Maven Wrapper (Recommended)

```bash
./mvnw spring-boot:run
```

### Option 2: Using Maven

```bash
mvn spring-boot:run
```

### Option 3: Build and Run JAR

```bash
./mvnw clean package
java -jar target/lab-0.0.1-SNAPSHOT.jar
```

## Accessing the Application

Once the application is running, open your browser and navigate to:

- **Main Application**: http://localhost:8080
- **Student List**: http://localhost:8080/students
- **H2 Console**: http://localhost:8080/h2-console

### H2 Console Login

- **JDBC URL**: `jdbc:h2:mem:labdb`
- **Username**: `sa`
- **Password**: (leave empty)

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Redirects to student list |
| GET | `/students` | View all students |
| GET | `/students/new` | Show form to add new student |
| GET | `/students/edit/{id}` | Show form to edit student |
| POST | `/students/save` | Save student (create/update) |
| GET | `/students/delete/{id}` | Delete student |

## Sample Data

The application automatically loads sample students on startup:
- John Doe (Computer Science)
- Jane Smith (Data Science)
- Bob Johnson (Software Engineering)
- Alice Williams (Information Systems)

## Database Configuration

The H2 database configuration is in `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:labdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

To use a different database (MySQL, PostgreSQL), update the configuration and add the appropriate driver dependency.

## Development

The project uses Spring Boot DevTools for automatic restart during development. Any changes to the code will automatically restart the application.

## Testing

Run the tests with:

```bash
./mvnw test
```

## Building for Production

```bash
./mvnw clean package
```

The JAR file will be created in the `target/` directory.

## Screenshots

### Student List
The main page displays all students in a table with Edit and Delete actions.

### Add/Edit Form
A clean form interface for adding new students or editing existing ones.

## Learning Objectives

This lab demonstrates:
1. Setting up a Spring Boot project
2. Configuring JPA with H2 database
3. Creating Entity, Repository, Service, and Controller layers
4. Using Thymeleaf for server-side rendering
5. Implementing CRUD operations
6. MVC design pattern
7. Database initialization with sample data

## Author

Lab assignment for CSC329 - Data Structures and Algorithms II

## License

Educational use only.
