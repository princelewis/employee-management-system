# employee-management-system
A service that manages employee information with the capacity to operate CRUD functions

## Development

Before you can build this project, you must install and configure the following dependency on your machine:

1. [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html): We use JDK 11 to run a development web service and build the project.

After you have downloaded and installed JDK 11, Open the project in any IDE and run the application from ypur IDE or You can run the application from your terminal with:
```
mvn spring-boot:run
```
 
## Client Testing
After the application is up an running, you may call the below endpoint on your browser
```
http://localhost:8023/swagger-ui.html
```
to run a test with the expected request payloads.

## Authentication
All the CRUD endpoints are secured. To access any of the CRUD endpoints, you need to first generate a [JWT](https://jwt.io/) 
To generate token, make a `GET` request to:
```
http://localhost:8023/auth/getToken
```
Set the generated token in th request header whenever you need to make a call to any of the CRUD handling endpoints
The token type is `Bearer Token`
