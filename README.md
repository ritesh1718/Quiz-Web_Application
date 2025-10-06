# 🧩 Quiz Application

A full-featured **Quiz Management System** built using **Spring Boot**, **Thymeleaf**, and **PostgreSQL**.  
It allows admins to create, edit, and manage quizzes, questions, and categories, while users can take quizzes interactively.

---

## 🚀 Features

### 👨‍💻 Admin
- Create, edit, and delete quizzes
- Manage questions and options for each quiz
- Assign categories to quizzes
- Control quiz active/inactive status

### 🙋‍♂️ User
- View and attempt available quizzes
- View quiz results and feedback

### ⚙️ Technical Features
- Spring Boot MVC architecture
- PostgreSQL database integration
- Spring Data JPA (ORM)
- Thymeleaf templates for dynamic views
- Spring Security authentication
- REST + MVC hybrid structure

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-------------|
| Backend | Java 17+, Spring Boot |
| Frontend | Thymeleaf, Bootstrap |
| Database | PostgreSQL |
| ORM | Hibernate / JPA |
| Build Tool | Maven |
| IDE | IntelliJ IDEA / VS Code / STS |

---

## ⚙️ Configuration Setup

Before running the project, configure your database credentials safely.

1. Go to `src/main/resources/`
2. Duplicate the file:
application-template.properties → application.properties

bash

3. Open `application.properties` and update your PostgreSQL username & password:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/quizdb
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
⚠️ Do not commit your real credentials to GitHub.
The .gitignore file already ignores application.properties for security reasons.

🧰 Run the Application
Using STS / IntelliJ IDEA
Import the project as a Maven Project

Run the main class:

com.quizapp.QuizAppApplication
Open your browser and visit:


http://localhost:8080
Using Command Line


mvn spring-boot:run
🗂️ Project Structure

quiz-app/
│
├── src/main/java/com/quizapp/
│   ├── controller/        # Web and admin controllers
│   ├── entity/            # JPA entity classes
│   ├── repository/        # Spring Data JPA interfaces
│   ├── service/           # Business logic
│   └── QuizAppApplication.java
│
├── src/main/resources/
│   ├── templates/         # Thymeleaf HTML pages
│   ├── static/            # CSS, JS, and image assets
│   ├── application-template.properties
│   └── application.properties (ignored in .gitignore)
│
├── pom.xml
└── README.md
🧑‍💻 Developer Notes

Database tables are automatically managed by Hibernate (spring.jpa.hibernate.ddl-auto=update)

Use application-template.properties as a reference config

Modify categories, quiz logic, or UI templates as per requirements

🛡️ Security
Passwords are not stored in plain text

.gitignore ensures no sensitive data is committed

Authentication handled via Spring Security


📄 License
This project is developed for educational purposes and can be used for portfolio or internship submissions.

👤 Author
Ritesh Ananda Gaikwad
Final Year B.Tech — Computer Science & Engineering
📧 [riteshgaikwad2020@gmail.com]

