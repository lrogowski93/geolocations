# GeolocationApi

## Description

Geolocation API is used to collect and keep in database list of geographical coordinates.

## Built with
- Java 17
- Spring Boot 2.7.6:
    - Web
    - Data JPA
    - Validation
    - OAuth2 Resource Server
    - H2 Database
    - Liquibase

## Installation

1. Clone repository.

2. Provide valid database credentials in [application.properties](/src/main/resources/application.properties) file (or leave default H2 in-memory database).

    ```properties
    spring.datasource.url = your_database_url
    spring.datasource.username = your_database_username
    spring.datasource.password = your_database_password
    ```

3. Database table will be created automatically with Liquibase SQL schema (src/main/resources/database/2022-12-21/). Delete file '02-geolocation-data.sql' if you don't need dummy data.

## Usage

1. Authentication
Application uses 2 types of authentication.
    -endpoint '/login' : request method: POST, using basic auth to authorize and get JWT. Sending basic auth base64 encoded header with credentials (for purpose of this demo app hardcoded with inMemoryUserDetailsManager as 'user/password').
    -endpoint '/geolocations' using JWT authentication. Every call needs authorization header with bearer token provided in '/login' endpoint. Token is valid for 1 hour.

2. Adding new geolocation data.
    resources:
  '/geolocations' : request method: POST, data type: JSON, authorization header with bearer token required
Data format:
deviceId: long,
longitute: double (values between -90 and 90),
latitude: double (values between -180 and 180),
created: LocalDateTime (OPTIONAL)
    e.g.
    ```
    {
        "deviceId": 8,
        "latitude": -90,
        "longitude": -180,
        "created": "2022-12-20T20:04:34.245587266"
    }
    ```
Newly created object will be returned as response.

## Additional informations

Application has 2 controllers: AuthController - used for authentication, and GeolocationController used for adding new geolocations data.
GeolocationController has addGeolocation method and requires valid GeolocationRequestDto parameter. GeolocationService handles mapping Dto object from body request to entity and persist new Geolocation in GeolocationRepository.

Application is using Liquibase library for managing database changes. This makes easy to migrate from in-memory H2 database to any external database with simple configuration.

Application for demonstration purposes is using InMemoryUserDetailsManager to authenticate user at '/login' endpoint. After successful authentication user gets bearer token in response header. Token is valid for 1 hour and is required for adding new geolocation data.
JWT is created with OAuth2 Resource Server library and self-signed RSA keys signed with OpenSSL (src/main/resources/certs/).

//Łukasz Rogowski 2022


