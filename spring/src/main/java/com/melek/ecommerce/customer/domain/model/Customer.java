package com.melek.ecommerce.customer.domain.model;

public class Customer {

    private final CustomerId id;

    private String firstName;

    private String lastName;

    private String email;

    public Customer(
        CustomerId id,
        String firstName,
        String lastName,
        String email
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }

        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Customer first name cannot be null or empty");
        }

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Customer last name cannot be null or empty");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Customer email cannot be null or empty");
        }

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public CustomerId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void changeFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Customer first name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public void changeLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Customer last name cannot be null or empty");
        }
        this.lastName = lastName;
    }

    public void changeEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Customer email cannot be null or empty");
        }
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
