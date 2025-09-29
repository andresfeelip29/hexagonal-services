# Microservices with Spring Boot

Este proyecto implementa una arquitectura de microservicios usando **Spring Boot**, desplegados con **Docker Compose**, que simula un sistema de e-commerce con autenticación, catálogo de productos, órdenes, inventario y notificaciones.

## 📌 Arquitectura

![microservices](/devsu_test.jpg)

### Componentes principales
- **Discovery Server (Eureka)** → Registro y descubrimiento de microservicios.
- **API Gateway (Spring Cloud Gateway)** → Entrada única al sistema, con **balanceo de carga** automático hacia los microservicios registrados en Eureka.
- **Auth service (Spring security)** → Autenticación y autorización.
- **Customer service (Spring Boot + MySQL +  kafka)** → Gestión de clientes.
- **Account service (Spring Boot + PostgreSQL + kafka)** → Gestión de cuentas, resiliencia con **Resilience4j**.
- **Transaction service (Webflux + Mongo + kafka)** → Gestion de transacciones.


## 🛠️ Tecnologías usadas

- **Java 21**
- **Spring Boot 3**
- **Spring Boot Webflux**
- **Spring Cloud (Eureka, Gateway, Load Balancer)**
- **Spring Boot Actuator** (health, métricas y monitoreo)
- **Spring security (OAuth2, JWT)**
- **PostgreSQL / MySQL / MONGO**
- **Kafka**
- **Docker & Docker Compose**
- **Data base per service**
- **Hexagonal Architeure**
- **Microservices patter**
- **Resilience4j (Circuit Breaker)**

## 🐳 Docker Compose

El archivo `docker-compose.yml` levanta:

- **Bases de datos**:
    - PostgreSQL (`5431`),
    - Mongo (`27017`)
    - MySQL  (`3307`).
- **Kafka + Zookeeper** en `9092`

Ejemplo de ejecución:

```bash
docker-compose up -d
```

Verifica que los contenedores estén corriendo:

```bash
docker ps
```

## 🌍 Accesos principales

- **Eureka Server** → [http://localhost:8761](http://localhost:8761)
- **API Gateway** → [http://localhost:8080](http://localhost:8081)
- **Kafka Broker** → `localhost:9092`

## 📬 Endpoints

### Auth Service
- POST `/api/v1/auth/register` → Registro de usuario en sistema
  ```json
  
    {
    "name": "Antoni",
    "username": "anoty22",
    "password": "relx234",
    "status": true,
    "address": "ZONE 2CL 4",
    "identification": "098923949323",
    "gender": "Masculino",
    "phone": "47282948234",
    "age": 26
    }
  
  
  ```

### Products Service
- POST `/api/v1/auth/token` → generar un token
  ```json
  
  {
    "username": "admin",
    "password": "admin"
    }
  
  ```
---

## Contribution

Project oriented as a base test, for the implementation of microservices with technologies based on the spring framework
and spring boot ecosystem.