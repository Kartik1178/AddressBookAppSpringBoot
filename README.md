# AddressBook Spring Boot Application

A Spring Boot REST API for managing an address book.

## Tech Stack
- Java 11
- Spring Boot 2.4.0
- MySQL
- Lombok
- Maven
- React

## Sections
- Section 1 : App Setup and REST Controller
- Section 2 : DTO, Model, Service Layer
- Section 3 : Lombok, Logging, Profiles, DB Env Vars
- Section 4 : Validators and Exception Handlers

## Running
1. Set environment variables: DB_URL, DB_USER, DB_PASS
2. mvn spring-boot:run

## CURL Test Commands
- curl localhost:8080/addressbookservice/ -w "
"
- curl localhost:8080/addressbookservice/get/1 -w "
"
- curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"Rohan\",\"phoneNumber\":\"9876543210\"}" http://localhost:8080/addressbookservice/create -w "
"
- curl -X PUT  -H "Content-Type: application/json" -d "{\"name\":\"Rohan\",\"phoneNumber\":\"9876543210\"}" http://localhost:8080/addressbookservice/update/1 -w "
"
- curl -X DELETE localhost:8080/addressbookservice/delete/1 -w "
"

## Author
Kartikeya
