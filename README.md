# Lead CRM System | Sample Spring Boot Project

This application showcasing sample api and workflow for a Lead Management system.

## How to Run

### Prerequisites 

- Java: Version 17 or 21 (Tested in 21)
- MySQL
- GIT
- Maven
- IDE (Recommended: InteliJ)

### Steps to Run via Command Line

#### Clone the git repository using command line.

```
git clone https://github.com/PasanBhanu/assignment-azbow-tl-2025.git
```

#### Import the database to MySQL

The initial DDM/DML can be found in [documentation](documentation/DB%20Import%20Script.sql) folder.

Start your MySQL server on localhost with port 3306

Create a new database named `azbow`

Import the DDL/DML to the database

#### Update Properties

Update `src/main/resources/application.properties` with your database credentials as required.

```
spring.datasource.url=jdbc:mysql://localhost:3306/azbow     # Database URL:PORT/DATABASE_NAME
spring.datasource.username=root                             # Database USERNAME
spring.datasource.password=                                 # Database PASSWORD
```

#### Run the App

Navigate to the folder and execute below command to build the app. Assuming your JAVA path variable is already setup with recommended java version.

```
mvn clean install
mvn spring-boot:run
```

## How to Test



## System Design

The system design documents are located in [documentation](documentation) folder.

ER Diagram: [View ER Diagram](documentation/README.md#er-diagram)

Data Flow Diagram: 

Process Flow Diagram: 
