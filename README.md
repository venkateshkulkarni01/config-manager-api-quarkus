# Configuration Manager API

## Overview
The Configuration Manager API is a RESTful service designed to manage configuration values for applications. It allows for the externalization of configuration data, transitioning from traditional properties files to a more dynamic and manageable system. This API supports CRUD operations on configuration entries stored in a PostgreSQL database.

## Features
- Manage configuration values with ease.
- Support for multiple environments.
- RESTful API endpoints for seamless integration.
- Built with Quarkus for high performance and scalability.

## Project Structure
```
config-manager-api
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── controller
│   │   │           │   └── ConfigurationController.java
│   │   │           ├── service
│   │   │           │   └── ConfigurationService.java
│   │   │           ├── repository
│   │   │           │   └── ConfigurationRepository.java
│   │   │           └── model
│   │   │               └── Configuration.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── db
│   │           └── migration
│   │               └── V1__init_config_table.sql
├── pom.xml
└── README.md
```

## API Endpoints
- **GET /configurations**: Retrieve a list of all configurations, optionally filtered by name or environment.
- **POST /configurations**: Add a new configuration entry.
- **PUT /configurations/{id}**: Update an existing configuration entry by ID.
- **DELETE /configurations/{id}**: Remove a configuration entry by ID.
- **GET /configurations/by-name-env**: Retrieve configurations by name and environment.

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd config-manager-api
   ```
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Configure the database connection in `src/main/resources/application.properties`.
5. Run the application:
   ```
   mvn quarkus:dev
   ```

## Database Migration
The initial database schema can be set up using the SQL script located at `src/main/resources/db/migration/V1__init_config_table.sql`. This script creates the necessary table for storing configuration values.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.