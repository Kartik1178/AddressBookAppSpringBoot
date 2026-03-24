package com.bridgelabz.addressbookapp;

/**
 * Entry point for the Address Book Spring Boot Application.
 * Bootstraps the Spring context and starts the embedded Tomcat server.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookAppApplication {

    // Launches the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(AddressBookAppApplication.class, args);
    }
}
