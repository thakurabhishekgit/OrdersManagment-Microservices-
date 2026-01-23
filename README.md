
# Microservices Architecture with Spring Boot, API Gateway, JWT, Eureka, Feign, Kafka

## Overview

This project demonstrates a **production-style microservices architecture** built using **Spring Boot** and **Spring Cloud**.
It implements **centralized authentication using JWT**, **API Gateway–based routing**, **service discovery**, **inter-service communication**, and **event-driven communication using Kafka**.

The system is designed to be:

* Stateless
* Scalable
* Secure
* Decoupled

---

## Architecture Overview

### High-Level System Design

```
Client (Postman / Frontend)
        |
        |  Authorization: Bearer <JWT>
        v
+---------------------+
|     API Gateway     |
|---------------------|
| JWT Validation      |
| Route Management    |
| Load Balancing      |
+----------+----------+
           |
   -------------------------
   |                       |
   v                       v
+----------+        +--------------+
| Order    |        | Inventory    |
| Service  |        | Service      |
+----------+        +--------------+
      |
      |  OrderCreatedEvent
      v
+---------------------+
|        Kafka        |
+---------------------+
           |
           v
+---------------------+
| Notification /     |
| Consumer Service   |
+---------------------+
```

---

## Services in the System

### 1. User Service (Authentication Service)

**Responsibilities**

* User registration
* User login
* JWT token generation

**Key Features**

* Stores users in database
* Authenticates credentials
* Issues JWT signed with a secret key

**Important Point**

* This is the **only service that talks to the database for authentication**
* JWT contains userId and role

---

### 2. API Gateway

**Responsibilities**

* Single entry point for all clients
* JWT validation
* Routing requests to downstream services
* Service-to-service load balancing using Eureka

**How Authentication Works**

* Extracts JWT from `Authorization: Bearer <token>`
* Validates token signature and expiry
* Does NOT use database
* Injects user info into headers:

  * `X-User-Id`
  * `X-User-Role`

**Why No Database Is Used**

* JWT is stateless
* Signature verification proves authenticity

---

### 3. Order Service

**Responsibilities**

* Create orders
* Fetch user orders
* Communicate with Inventory Service

**Communication**

* Uses **Feign Client** to call Inventory Service
* Publishes Kafka events after order creation

**Kafka Event**

```
OrderCreatedEvent
{
  orderId,
  userId,
  productId,
  quantity
}
```

---

### 4. Inventory Service

**Responsibilities**

* Manage product stock
* Check availability
* Update inventory after order placement

**Communication**

* Synchronous REST calls from Order Service (Feign)

---

### 5. Kafka (Event Streaming Platform)

**Purpose**

* Decouple services
* Enable asynchronous processing
* Improve system scalability

**Used For**

* Publishing order events
* Triggering downstream actions like notifications, analytics, etc.

**Why Kafka Is Used**

* No tight coupling between services
* Order Service does not need to know who consumes the event
* Supports horizontal scaling

---

## Communication Patterns Used

### 1. Synchronous Communication (Feign Client)

Used when:

* Immediate response is required
* Order Service checks stock from Inventory Service

```
Order Service --> Inventory Service
```

---

### 2. Asynchronous Communication (Kafka)

Used when:

* Side effects are required
* No immediate response needed

```
Order Service --> Kafka --> Consumer Service
```

---

## JWT Authentication Flow

```
1. Client logs in
2. User Service verifies credentials
3. User Service generates JWT
4. Client sends JWT to API Gateway
5. API Gateway validates JWT using secret key
6. API Gateway forwards request to service
```

**Important Characteristics**

* No session
* No DB lookup at gateway
* Stateless authentication

---

## Service Discovery and Load Balancing

### Eureka Server

* All services register themselves
* API Gateway discovers services dynamically
* No hardcoded URLs

```
lb://order-service
lb://inventory-service
```

---

## API Gateway Routing Configuration

Example:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/v1/orders/**
```

---

## Security Design Decisions

### Why JWT at API Gateway

* Centralized authentication
* Downstream services remain simple
* No repeated security logic

### Why Same Secret Key

* JWT signature validation requires the same signing key
* No shared database required
* Cryptographically secure

---

## Kafka Integration Flow

```
Order Created
     |
     v
Order Service publishes event
     |
     v
Kafka Topic (order-created)
     |
     v
Consumer Service reacts automatically
```

**Kafka Consumer Example Use Cases**

* Email notifications
* Order analytics
* Audit logging
* Payment processing (future)

---

## Technology Stack

* Java 21
* Spring Boot
* Spring Cloud Gateway
* Spring Security
* JWT (JJWT)
* Spring Cloud Netflix Eureka
* OpenFeign
* Apache Kafka
* PostgreSQL
* Maven

---

## Key Learnings from This Project

* JWT validation does not require database access
* API Gateway is responsible for authentication, not business logic
* Microservices should be loosely coupled
* Kafka enables event-driven systems
* Secrets must be identical across JWT producer and validator
* Configuration consistency is critical in distributed systems

---

## Future Improvements

* Refresh token mechanism
* Role-based access control
* Token revocation using Redis
* API Gateway rate limiting
* Distributed tracing (Zipkin)
* Circuit breaker (Resilience4j)

---

## Conclusion

This project demonstrates a **real-world microservices architecture** with:

* Secure authentication
* Centralized gateway
* Service discovery
* Synchronous and asynchronous communication
* Event-driven design


