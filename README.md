# Happy Paws - Pet Adoption

Welcome to Happy Paws, your one-stop shop for happy pets. This project is a web application for pet adoption built using Java, Spring Boot, and Maven for the backend, and HTML, CSS, and JavaScript for the frontend.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Installation

### Prerequisites

- Java 11 or higher
- Maven
- Node.js and npm
- PostgreSQL

### Steps

1. **Clone the repository:**
   ```sh
   git clone https://github.com/YourUsername/YourRepositoryName.git
   cd YourRepositoryName

Set up the database:  
Create a PostgreSQL database named HappyPaws.
Update the database configuration in src/main/resources/application.properties with your PostgreSQL credentials.

Build the project:  
mvn clean install

Run the application:  
mvn spring-boot:run

Install frontend dependencies:  
cd src/main/resources/static
npm install

Usage
Open your browser and navigate to http://localhost:9007 to access the Happy Paws application.
Use the provided forms to enlist pets, view recommendations, and read reviews.
API Endpoints
POST /api/pets: Enlist a new pet.
GET /api/pets: Retrieve a list of enlisted pets.
GET /api/recommendations: Retrieve app recommendations.
GET /api/reviews: Retrieve user reviews.

Configuration
The application configuration is managed in the src/main/resources/application.properties file. Key configurations include:  
server.port: The port on which the application runs.
spring.datasource.url: The URL for the PostgreSQL database.
spring.datasource.username: The database username.
spring.datasource.password: The database password.
app.jwtSecret: The secret key for JWT authentication.
app.jwtExpirationInMs: The expiration time for JWT tokens.

Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.  
