# Section 1: Architecture summary

## System Overview

Smart Clinic Management System Spring Boot application implements a hybrid architecture combining traditional MVC patterns with modern REST APIs. The system is designed to support a healthcare management platform with distinct user interfaces for different stakeholders:

- **Web Interface**: Thymeleaf-based server-side rendering for Admin and Doctor dashboards, providing secure and feature-rich administrative capabilities

- **API Interface**: RESTful endpoints for Patient Portal, Mobile Applications, and third-party integrations

## Database Architecture

The application employs a polyglot persistence strategy with two specialized databases:

1. **MySQL Database** (Relational)
   - Stores structured data with complex relationships
   - Manages core domain entities: Patients, Doctors, Appointments, Admin users
   - Leverages JPA/Hibernate for ORM with transaction support
   - Ensures ACID compliance for critical healthcare data

2. **MongoDB Database** (Document-oriented)
   - Stores semi-structured prescription data
   - Provides flexible schema for varying prescription formats
   - Enables efficient storage of medical documents and attachments
   - Scales horizontally for growing prescription volumes

## Application Layers

The system follows a clean layered architecture:

1. **Presentation Layer**
   - Thymeleaf templates for web interfaces. Models are passed from the controller to Thymeleaf templates, where they are rendered as dynamic HTML for the browser.
   - REST API clients. Models are serialized into JSON and sent back to the client as part of an HTTP response.
   - Request validation and error handling

2. **Service Layer**
   - REST API controllers
   - Business logic implementation
   - Transaction management
   - Cross-cutting concerns (logging, security)
   - Integration with external systems

3. **Data Access Layer**
   - JPA repositories for MySQL entities
   - MongoDB repositories for document models
   - Custom query implementations
   - Data mapping and transformation


# Section 2: Numbered flow of data and control

![architecture-diagram](architecture-diagram.png)

1. User accesses AdminDashboard or Appointment pages - The user interacts with the system through either the web interface (Thymeleaf-based) for administrative functions or through the mobile applications using REST APIs. 
This is the entry point for all system interactions.

2. The action is routed to the appropriate Thymeleaf or REST controller - Depending on the access method, the request is directed to either a Thymeleaf controller (for web interface) or a REST controller (for API requests). The controllers handle the initial request processing and validation.

3. The controller calls the service layer - Controllers delegate business logic processing to the service layer. The service layer contains the core business logic and orchestrates the operations needed to fulfill the request, including transaction management.

4. Service layer processes business logic and calls repositories to retrive or persist data - The service layer implements business rules, performs validations, and coordinates data access operations. It interacts with the appropriate repositories to retrieve or modify data from the databases.

5. Repositories interact with the appropriate database - The system uses a polyglot persistence strategy with two specialized databases:
    - JPA repositories interact with MySQL for structured relational data (patients, doctors, appointments)

    - MongoDB repositories interact with MongoDB for semi-structured prescription data and medical documents

6. Data is processed and transformed by appropriate data models - Repositories use domain-specific data models to abstract the underlying database implementation. These models decouple the application from the persistence layer. This abstraction allows for seamless transformation between database entities and business objects, enabling consistent data handling regardless of the storage mechanism (MySQL or MongoDB).

7. Data persisited to the database using database specific objects - tables for relational database like MySQL or documents for MongoDB

