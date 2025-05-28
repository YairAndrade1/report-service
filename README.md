# Report Service

A Spring Boot microservice that calls the **Exam Service** to fetch and aggregate exam anomalies over a given period, then exposes a single “report” endpoint.  
Built with Java 17, Spring Boot 3.5.0 and WebFlux.

---

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [Build & Run](#build--run)
- [Endpoints](#endpoints)
- [Data Models](#data-models)
- [Testing](#testing)
- [Observability](#observability)

---

## Features

- Fetches “recent anomalies” from Exam Service via reactive HTTP (WebClient)
- Filters and groups anomalies by exam type
- Returns a JSON report with counts and patient IDs
- Enforces a 2 s timeout (ASR-compliant)
- Exposes Actuator endpoints for health and metrics

---

## Prerequisites

- Java 17 +
- Maven 3.6 +
- A running **Exam Service** instance reachable at HTTP
- (Optional) Docker, if you wish to containerize

---

## Configuration

All configuration goes in `src/main/resources/application.properties` (or as environment variables).

```properties
# Service name
spring.application.name=report-service

# Base URL of your Exam Service (no trailing slash)
exam.service.url=http://<EXAM_SERVICE_HOST>:8080/examen

# Server port (default 8080)
server.port=8080

# (Optional) Reactor thread-pool tuning, log levels, etc.
logging.level.org.springframework.web=INFO
