# S.4.1-Spring Boot Introduction

# 📄 Description - Exercise Statement

This project is an introductory exercise to Spring Boot and REST API development.  The main objective is to build a functional API that receives and returns data in JSON format, applying best practices and evolving through three levels of complexity:

- **Level 1:** Environment configuration and creation of a basic "Health Check" endpoint.

- **Level 2:** Management of an in-memory user list directly within the controller (CRUD operations: Create, Read, Filter).

- **Level 3:** Refactoring to a Layered Architecture.  This involves separating responsibilities into Controller, Service, and Repository layers using Dependency Injection (IoC).  It also includes the implementation of Unit and Integration tests using JUnit 5, Mockito, and MockMvc, following a TDD approach for business logic validation (e.g., preventing duplicate emails).

## 💻 Technologies Used

- Java 21
- Spring Boot 3.x (Spring Web)
- Maven (Dependency Management & Build)
- JUnit 5 (Testing)
- Mockito (Mocking & TDD)
- Spring Boot Test (MockMvc)
- Postman (Manual API Testing)

## 📋 Requirements

- Java JDK: v21
- Maven: v3.6+
- IDE: IntelliJ IDEA 
- Port: 9000 (Must be free)

## 🛠️ Installation

1. Clone this repository:
   ```bash
   git clone [repository-url]
   ```

2. Access the project directory:
   ```bash
   cd UserApi
   ```

3. Install dependencies:
   ```bash
   mvn clean install
   ```

## ▶️ Execution

1.  Configure the port (if not already set):
	Ensure `src/main/resources/application.properties` contains:
   ```properties
   server.port=9000
   ```

2.  Run the application via Maven:
   ```bash
   mvn spring-boot:run
   ```

Or build and run the JAR file:
   ```bash
   mvn clean package
   java -jar target/userapi-0.0.1-SNAPSHOT.jar
   ```

3. Verify the server is running:
   Access `http://localhost:9000/health` in your browser or postman workspace.

## 🌐 API Endpoints

The API is configured to run on port 9000.

| Method | Endpoint | Description | Request Body / Params |
|--------|----------|-------------|-----------------------|
| GET | `/health` | Checks API status (Returns JSON `{"status": "OK"}`) | None |
| GET | `/users` | Lists all users | None |
| GET | `/users` | Filter users by name | Query Param: `?name=[value]` |
| GET | `/users/{id}` | Get user by UUID | Path Variable: `id` |
| POST | `/users` | Create a new user | JSON: `{"name": "...", "email": "... "}` |
| GET | `/users/check-email` | Check if email exists | Query Param: `?email=[value]` |

## 🧪 Testing

To execute the automated test suite (Unit and Integration tests) and check coverage:

```bash
mvn test
```

### Types of Tests Included:

- **Integration Tests:** Located in `UserControllerTest`.  These utilize `@SpringBootTest` and `@AutoConfigureMockMvc` to verify the flow from the Controller down to the Repository, ensuring the layers interact correctly.

- **Unit Tests:** Located in `UserServiceImplTest`. These utilize `@ExtendWith(MockitoExtension.class)` to test business logic in isolation (e.g., throwing an exception when an email is duplicated) without loading the Spring context. 

![img](https://images.unsplash.com/photo-1526344966-89049886b28d?q=80&w=1074&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D)