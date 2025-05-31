# 🛠️ Daily Helpers Service

A Spring Boot-based backend application that allows users to register, log in, and book local helpers like painters, cooks, and more. Designed for simplicity and functionality, this service is ideal for daily task booking and management.



## 🚀 Features

- 👤 User Registration & Login (Customer or Helper)
- 🧾 Helper Enrollment & Availability Tracking
- 📅 Booking System with Completion Flow
- 🔐 Basic Authentication Logic (no JWT yet)
- 📦 PostgreSQL-backed persistence with JPA



## 🧰 Tech Stack

- **Backend**: Java 17, Spring Boot 3.5
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA, Hibernate
- **Build Tool**: Maven
- **Deployment Ready**: Easily deployable to any cloud (Heroku, Railway, etc.)






## ⚙️ Getting Started

### Prerequisites

- Java 17+
- PostgreSQL (running locally or remotely)
- Maven 3+

### Setup Steps

1. **Clone the repository**

```bash
git clone https://github.com/yourusername/daily-helper-service.git
cd daily-helper-service
```

2. **Configure PostgreSQL**

Update src/main/resources/application.yml or application.properties:
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/your_database
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

3. **Run the application**

```
./mvnw spring-boot:run
```
App runs on: http://localhost:8080 (or another port if configured)



## 📬 API Endpoints & Usage

- Register
```
curl -X POST http://localhost:8080/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"username":"john_doe", "password":"pass123", "role":"CUSTOMER"}'
```

- Login
``` 
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john_doe", "password":"pass123"}'
```

- Enroll a Helper
``` 
curl -X POST http://localhost:8080/helpers/enroll \
  -H "Content-Type: application/json" \
  -d '{"userId": 1, "serviceType": "PAINTER", "price": 1000.0}'
```

- Get Available Helpers
```
curl http://localhost:8080/helpers/PAINTER
```

- Book a Helper
```
curl -X POST "http://localhost:8080/booking/book?customerId=1&helperId=2"
```

- Complete a Booking
```
curl -X POST "http://localhost:8080/booking/complete?bookingId=3"
```



## 🧑‍💻 Contribution Guide
- Fork the repo

- Create your feature branch (git checkout -b feature/awesome-feature)

- Commit your changes (git commit -am 'Add awesome feature')

- Push to the branch (git push origin feature/awesome-feature)

- Open a Pull Request



## ✨ Author
**Mrudul Vajpayee**   
[GitHub Profile](https://github.com/mrudulvajpayee4935)  
[LinkedIn](https://www.linkedin.com/in/mrudul-vajpayee-18a7291a3/)
