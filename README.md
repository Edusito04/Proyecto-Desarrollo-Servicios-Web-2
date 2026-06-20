# StayBook - Sistema de Gestión Hotelera basado en Microservicios

## Descripción

StayBook es un sistema desarrollado para el curso **Desarrollo de Servicios Web II**, cuyo objetivo es gestionar operaciones básicas de un hotel mediante una arquitectura basada en microservicios.

La solución permite administrar usuarios, clientes, habitaciones, reservas y pagos utilizando servicios web REST seguros y persistencia de datos en PostgreSQL.

## Tecnologías Utilizadas

* Java 17
* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring Security
* JWT (JSON Web Token)
* BCryptPasswordEncoder
* Lombok
* PostgreSQL
* Docker
* Maven
* Postman

## Arquitectura de Microservicios

El proyecto está compuesto por tres microservicios independientes:

### 1. Auth Service (Puerto 8081)

Encargado de la autenticación y autorización de usuarios.

Funciones principales:

* Registro de usuarios.
* Inicio de sesión.
* Generación y validación de tokens JWT.
* Cifrado de contraseñas mediante BCrypt.

### 2. Hotel Service (Puerto 8082)

Encargado de la gestión operativa del hotel.

Funciones principales:

* Gestión de clientes.
* Gestión de habitaciones.
* Gestión de reservas.
* Operaciones CRUD (GET, POST, PUT y DELETE).

### 3. Payment Service (Puerto 8083)

Encargado de la administración de pagos asociados a las reservas.

Funciones principales:

* Registro de pagos.
* Consulta de pagos.
* Actualización de pagos.
* Eliminación de pagos.
* Consulta de pagos por reserva.

## Funcionalidades Implementadas

* Autenticación segura mediante JWT.
* Contraseñas almacenadas de forma cifrada.
* Servicios REST para operaciones CRUD.
* Persistencia de datos en PostgreSQL.
* Arquitectura basada en microservicios.
* Validaciones de datos.
* Seguridad mediante Spring Security.
* Pruebas utilizando Postman.

## Requisitos para Ejecutar el Proyecto

* Java 17 o superior.
* Docker Desktop.
* Maven.
* PostgreSQL (o Docker Compose).
* Postman.

## Ejecución del Proyecto

1. Iniciar PostgreSQL mediante Docker Compose.
2. Levantar el microservicio Auth Service.
3. Levantar el microservicio Hotel Service.
4. Levantar el microservicio Payment Service.
5. Utilizar Postman para consumir los endpoints REST.
