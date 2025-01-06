# Curd-Operations-Spring-Boot-Angular

This is a full-stack web application that demonstrates basic CRUD (Create, Read, Update, Delete) operations with a **Spring Boot** backend and **Angular** frontend. The application allows users to perform CRUD operations on a dataset, typically involving resources such as "Users" or "Products", by interacting with a RESTful API created with Spring Boot.

## Project Structure Overview

### Backend (Spring Boot)

- **/src/main/java/com/yourpackage**: Contains the Java files for your Spring Boot application, organized by the following structure:
  - **Controllers**: Handles HTTP requests from the frontend (Angular) and interacts with services to fetch or update data.
  - **Services**: Contains the core business logic of the application. Services handle the requests from controllers and perform necessary operations using repositories.
  - **Repositories**: Interfaces to interact with the database using Spring Data JPA. Handles CRUD operations for entities.
  - **Models**: Represents the data structure. For example, a `User` model might contain properties like `id`, `name`, `email`, etc.
  
- **/src/main/resources/application.properties**: Configuration file for the Spring Boot application. It contains:
  - Database connection details (URL, username, password, etc.).
  - Other application-specific configurations such as server port, logging, etc.
  
- **/pom.xml**: Maven dependency management file for the backend project, containing dependencies for Spring Boot, JPA, MySQL, and other required libraries.

### Frontend (Angular)

- **/src/app**: The folder contains the Angular components, services, and models:
  - **/components**: Angular components for rendering data, such as lists and forms for user interaction.
  - **/services**: Angular services responsible for making HTTP calls to the Spring Boot backend and managing data in the frontend.
  - **/models**: Angular models that represent the data structure and facilitate interaction with the backend.
  
- **/src/environments**: Contains environment-specific configuration files (e.g., `environment.ts` for development, `environment.prod.ts` for production).
  
- **/angular.json**: The configuration file for Angular, including project settings, build configurations, and other Angular-specific settings.

## Data Flow

The data flow between the frontend and backend is as follows:

### 1. **User Interaction (Frontend - Angular)**

- The user interacts with the frontend interface (e.g., via forms or buttons for CRUD operations).
- Components handle user actions and pass the relevant data to Angular services.

### 2. **Frontend Request (Angular Services)**

- Angular services are responsible for making HTTP requests to the backend API endpoints.
  - **GET requests** fetch data from the backend.
  - **POST requests** create new data in the backend.
  - **PUT requests** update existing data.
  - **DELETE requests** remove data.
  
- Example: A user submits a form to create a new user, the form data is passed to an Angular service, which sends a `POST` request to the backend.

### 3. **Backend Handling (Spring Boot Controllers and Services)**

- The Spring Boot **controller** receives HTTP requests from the frontend (Angular).
- The controller passes the request to the corresponding **service** class, where business logic is applied.
  - For example, in the case of a `POST` request to create a new user, the service layer will save the new user to the database using the **repository**.

### 4. **Database Interaction (Spring Boot Repositories)**

- Spring Boot uses **Spring Data JPA** repositories to handle database operations. Repositories manage interactions with the database (e.g., creating, reading, updating, and deleting records).
  - The repository is an interface that extends `JpaRepository` or `CrudRepository`.
  
### 5. **Response to Frontend (Spring Boot Controllers)**

- After the service layer performs the required operation (e.g., saving, retrieving, updating, or deleting data), the backend controller sends a response back to the frontend in JSON format.
  
### 6. **Data Update in Frontend (Angular)**

- The Angular service receives the response from the backend.
- The frontend (Angular component) receives the data and updates the user interface to reflect the latest changes (e.g., showing a success message or updating a list of users).

### Example Data Flow: **Create User**

1. **Frontend**: User fills out a form to create a new user.
2. **Frontend**: Angular component calls a service to send the user data to the backend.
3. **Backend**: Spring Boot controller receives the `POST` request and calls the service layer to save the user.
4. **Backend**: Service saves the user data to the MySQL database using the repository.
5. **Backend**: The controller sends a success response (e.g., HTTP 200 OK or 201 Created) back to Angular.
6. **Frontend**: Angular component receives the success response and updates the UI (e.g., by showing a success message or refreshing the user list).

## Technologies Used

- **Spring Boot**: Backend framework to build RESTful APIs.
- **Angular**: Frontend framework to build dynamic, single-page applications.
- **Spring Data JPA**: A part of Spring Boot to interact with relational databases.
- **MySQL**: Relational database management system used to store application data.
- **Maven**: Dependency management and build automation tool for Spring Boot.
- **Node.js & NPM**: Used for managing Angular project dependencies and running the Angular development server.

## Setup Instructions

### Backend Setup (Spring Boot)

1. Clone the repository:

   ```bash
   git clone https://github.com/Juttu-Venkatesh/Curd-Operations-Spring-Boot-Angular.git
