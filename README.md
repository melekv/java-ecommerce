# E-commerce App

A backend e-commerce application built with Java and Spring Boot. The project is designed to practice building a modular REST API and working with databases, caching, messaging, and product search.

## Features

- Product and category management
- Customer management
- Shopping cart storage using Redis
- Order management
- Inventory and payment workflow
- Product search with Elasticsearch
- Asynchronous communication using RabbitMQ
- Database migrations with Flyway
- Integration tests

## Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Build tool:** Maven
- **Database:** PostgreSQL
- **Cache:** Redis
- **Messaging:** RabbitMQ
- **Search:** Elasticsearch
- **Database migrations:** Flyway
- **Testing:** JUnit and Testcontainers
- **Containerization:** Docker and Docker Compose

## Getting Started

### Requirements

- Docker
- Docker Compose

### 1. Clone the repository

```bash
git clone https://github.com/melekv/java-ecommerce.git
cd java-ecommerce
```

### 2. Start the application

Build and start the services using Docker Compose:

```bash
docker compose up --build
```

Wait for the containers to start. The application and its supporting services are configured through the project's Docker Compose and Spring Boot configuration.

## Project Structure

The backend source code is located in the `spring/` directory.

The application is organized into modules, including:

- `catalog` – products, categories, and product search
- `customer` – customer management
- `cart` – shopping cart functionality
- `order` – order processing
- `inventory` – stock management
- `payment` – payment workflow

## Purpose

This project was created to improve my Java and Spring Boot skills and practice building a backend application with persistence, caching, asynchronous messaging, and full-text search.

## Author

[melekv](https://github.com/melekv)
