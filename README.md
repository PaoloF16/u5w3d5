# Event Management REST API

A Spring Boot RESTful API designed for managing event registrations, bookings, and user roles. Built with robust role-based security to distinguish between Event Organizers and regular Users.

---
<img width="1376" height="664" alt="graphQL" src="https://github.com/user-attachments/assets/a33fc438-a846-43b1-a08f-ed25a53da65a" />

## 🚀 Features

*   **Authentication & Security**: Secure user registration and authentication powered by **Spring Security** and **JWT (JSON Web Tokens)**. Passwords are securely hashed using **BCrypt**.
*   **Role-Based Access Control**:
    *   `ORGANIZER`: Can create, update, and delete their own events.
    *   `USER`: Can browse available events, book seats, and view or cancel their active reservations.
*   **Automated Seat Management**: Booking an event automatically decrements its available seats. Canceling a booking dynamically returns the seat back to the event capacity.
*   **Data Integrity & Validation**: Strict payload validation using Jakarta Constraints and reliable relational mapping with Hibernate/JPA.

---

## 🛠️ Tech Stack

*   **Backend**: Java 17, Spring Boot, Spring Security
*   **Database**: PostgreSQL, Spring Data JPA (Hibernate)
*   **Token Security**: JJWT (Java JWT)
*   **Utilities**: Lombok, Jakarta Validation

---

## 🛣️ API Endpoints Summary

### Authentication (`/auth`)
*   `POST /auth/register` - Register a new user (`USER` or `ORGANIZER`)
*   `POST /auth/login` - Authenticate credentials and receive a Bearer JWT Token

### Events (`/events`)
*   `GET /events` - Retrieve all scheduled events (All authenticated users)
*   `GET /events/{eventId}` - Fetch a specific event by ID
*   `POST /events` - Create a new event (`ORGANIZER` only)
*   `PUT /events/{eventId}` - Update an existing event (`ORGANIZER` owner only)
*   `DELETE /events/{eventId}` - Remove an event (`ORGANIZER` owner only)

### Bookings (`/bookings`)
*   `GET /bookings/me` - List all active reservations for the authenticated session
*   `POST /bookings/{eventId}` - Secure a seat for a specific event
*   `DELETE /bookings/{bookingId}` - Cancel an existing reservation and release the seat

---

