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
    - Lombok

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
   -endpoint '/login' : request method: POST, using basic auth to authorize and get JWT. Sending basic auth base64
   encoded header with credentials (for purpose of this demo app hardcoded with inMemoryUserDetailsManager as '
   user/password').
   -endpoint '/geolocations' using JWT authentication. Every call needs authorization header with bearer token provided
   in '/login' endpoint. Token is valid for 1 hour.

2. Adding new geolocation data.\
   resources:\
   '/geolocations' : request method: POST, data type: JSON, authorization header with bearer token required

```java
Data format:
        deviceId:long,
        longitute:double(values between-90and 90),
        latitude:double(values between-180and 180),
        created:LocalDateTime(OPTIONAL)
 ```

e.g.

```JSON
    {
        "deviceId": 8,
        "latitude": -90,
        "longitude": -180,
        "created": "2022-12-20T20:04:34.245587266"
    }
```

Newly created object will be returned as response.

3. Viewing geolocation data.\
   resources:\
   '/geolocations' : request method: GET, authorization header with bearer token required.\

Input parameters:

 ```java
   sortDirection:ASC/DESC(OPTIONAL)
        page:int(OPTIONAL)
```

request example:

```http
GET http://localhost:8080/geolocations?sortDirection=DESC&page=2
```

response (JSON):

```JSON
[
  {
    "id": 900,
    "deviceId": 19,
    "latitude": 42.905236256,
    "longitude": 131.216967882,
    "created": "2022-12-17T08:14:09"
  },
  {
    "id": 899,
    "deviceId": 7,
    "latitude": -19.017922256,
    "longitude": -91.825569819,
    "created": "2022-11-06T07:56:18"
  }
]
  ```

## Additional information

Application for demonstration purposes is using InMemoryUserDetailsManager to authenticate user at '/login' endpoint.
After successful authentication user gets bearer token in response header. Token is valid for 1 hour and is required for
handling geolocation data.
JWT is created with OAuth2 Resource Server library and self-signed with OpenSSL RSA keys (src/main/resources/certs/).

//Łukasz Rogowski 2022


