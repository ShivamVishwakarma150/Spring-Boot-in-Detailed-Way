# **Spring Boot Security using ORM**

1. **Application setup and User Register:**
   - Start by setting up a Spring Boot application with the necessary dependencies for Spring Security and ORM (Object-Relational Mapping) integration, such as Hibernate or JPA (Java Persistence API).
   - Configure the database connection properties in your application's configuration file (e.g., `application.properties` or `application.yml`).
   - Create an entity class (e.g., `User`) that represents the user data and annotate it with the appropriate ORM annotations (e.g., `@Entity`, `@Table`, etc.).
   - Implement a repository interface (e.g., `UserRepository`) that extends a relevant ORM repository (e.g., `JpaRepository`) to perform CRUD (Create, Read, Update, Delete) operations on the `User` entity.
   - Implement a service class (e.g., `UserService`) that provides methods for user registration, validation, and any additional business logic related to user management.
   - Expose the necessary endpoints (e.g., RESTful APIs) for user registration, such as `/register` or `/api/users`, that interact with the `UserService` to handle user registration requests.

2. **User Login and Password Encoder:**
   - Configure Spring Security to enable authentication and authorization in your application. This can be done through configuration classes or annotations, depending on your preference.
   - Implement a custom `UserDetailsService` that retrieves user information from the database and returns a `UserDetails` object. This includes loading the user's details (e.g., username, password, roles) and any additional attributes required for authentication and authorization.
   - Configure the password encoder (e.g., `BCryptPasswordEncoder`) to securely hash and store user passwords. This ensures that passwords are not stored in plain text and provides a level of protection against unauthorized access.
   - Implement a custom authentication provider (e.g., `DaoAuthenticationProvider`) that uses the `UserDetailsService` and password encoder to perform authentication.
   - Expose an endpoint (e.g., `/login` or `/api/login`) for user login, where users can provide their credentials. This endpoint should handle authentication using the configured authentication provider and generate a token or session for subsequent requests.
   
3. **Session Management and Internal Process:**
   - Configure session management options to control how user sessions are managed and maintained in your application. This includes setting session timeout, enabling concurrent session control, and configuring session fixation protection.
   - Implement additional security measures like CSRF (Cross-Site Request Forgery) protection, which prevents unauthorized requests from malicious websites.
   - Define access control rules and configure authorization settings using annotations or configuration files. This allows you to restrict access to certain endpoints or resources based on user roles or other criteria.
   - Implement any necessary security filters or interceptors to perform additional security-related tasks, such as logging, auditing, or custom authorization checks.
   - Handle authentication and authorization exceptions appropriately by providing custom error messages or redirecting users to the appropriate error pages.
   - Implement mechanisms for password reset, account lockout, or any other security-related features specific to your application's requirements.

Remember, the above steps provide a high-level overview of setting up Spring Boot Security using ORM. The implementation details can vary based on your specific application requirements and choice of technologies. It's recommended to consult the official Spring Security documentation and relevant tutorials for more in-depth guidance.

<br/>
<br/>

Certainly! I apologize for the confusion earlier. Here's the updated format with separate code snippets, explanations, and a conclusion.

**Stage #1: Application setup and User Register**

In this stage, we will define a Spring Boot web MVC application that handles the CRUD process. It will read the registration form data and store it in two tables: the `User` table and the `Roles` table.

**S#1: Define a Spring Boot Application**
- Create a new Spring Boot application named `03SpringBootSecurityOrmEx`.
- Add the following dependencies in your `pom.xml` file:
```xml
<dependencies>
    <!-- Other dependencies -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- Add other dependencies such as Lombok, Spring Data JPA, MySQL driver, DevTools, Thymeleaf -->
</dependencies>
```

**S#2: Comment Spring Security Dependencies in `pom.xml`**

**S#3: `application.properties`**
Configure the database connection properties and other relevant settings in your `application.properties` file:
```properties
server.port=9090

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/boot
spring.datasource.username=root
spring.datasource.password=Shivam@123

spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=create
```

**S#4: Entity class `User`**
Create the `User` entity class with the necessary attributes and annotations:
```java
package com.app.shivam.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usertab")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uid")
    private Integer userId;

    @Column(name="uname")
    private String userName;

    @Column(name="umail")
    private String userEmail;

    @Column(name="upwd")
    private String userPwd;

    @ElementCollection
    @CollectionTable(name="roles_tab",
        joinColumns = @JoinColumn(name="uid")
    )
    @Column(name="urole")
    private Set<String> userRoles;
}
```
Explanation:
- The `User` class represents the user entity with attributes such as `userId`, `userName`, `userEmail`, `userPwd`, and `userRoles`.
- It is annotated with `@Entity` to mark it as a persistent entity and `@Table(name="usertab")` to specify the table name as `usertab`.
- The `@Id` annotation marks the `userId` field as the primary key.
- The `@ElementCollection` and `@CollectionTable` annotations are used to map the `userRoles` attribute to a separate table called `roles_tab`, which is joined to the `User` table using the `uid` column.

**S#5: Repository interface `UserRepository`**
Create the `UserRepository` interface that extends the `JpaRepository` interface:
```java
package com.app.shivam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserEmail(String

 userEmail);
}
```
Explanation:
- The `UserRepository` interface extends the `JpaRepository` interface, which provides built-in methods for CRUD operations on the `User` entity.
- It also includes a custom method `findByUserEmail` to retrieve a user by their email.

**S#6: Service interface and implementation**
Create the `IUserService` interface and its implementation class `UserServiceImpl`:
```java
package com.app.shivam.service;

import java.util.Optional;

import com.app.shivam.entity.User;

public interface IUserService {
    Integer saveUser(User user);
    Optional<User> getOneUser(Integer id);
}
```
```java
package com.app.shivam.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shivam.entity.User;
import com.app.shivam.repo.UserRepository;
import com.app.shivam.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository repo;

    public Integer saveUser(User user) {
        return repo.save(user).getUserId();
    }

    public Optional<User> getOneUser(Integer id) {
        return repo.findById(id);
    }
}
```
Explanation:
- The `IUserService` interface declares methods for saving a user and retrieving a user by their ID.
- The `UserServiceImpl` class implements these methods using the `UserRepository` to interact with the database.

**S#7: Controller class `UserController`**
Create the `UserController` class that handles user registration requests:
```java
package com.app.shivam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.shivam.entity.User;
import com.app.shivam.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping("/register")
    public String showReg() {
        return "UserRegister";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, Model model) {
        Integer id = service.saveUser(user);
        String message = "User '" + id + "' created!";
        model.addAttribute("message", message);
        return "UserRegister";
    }
}
```
Explanation:
- The `UserController` class is a Spring MVC controller that handles user-related requests.
- The `showReg` method maps to the GET request for the `/user/register` URL and returns the view `UserRegister.html` to display the registration form.
- The `saveUser` method maps to the POST request for the `/user/save` URL and saves the user data received from the form. It also adds a success message to the model to be displayed in the view.

**S#8: View Page `UserRegister.html`**
Create the Thymeleaf view page that renders the registration form:
```html
<!-- UserRegister.html -->
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>SECURITY USING ORM</title>
</head>
<body>
    <h3>WELCOME TO THE REGISTER PAGE</h3>
    <form th:action="@{/user/save}" method="POST">
        <pre>
            NAME   : <input type="text" name="userName"/>
            EMAIL  : <input type="text" name="userEmail"/>
            PASSWD : <input type="password" name="userPwd"/>
            ROLES :
                <input type="checkbox" name="userRoles" value

="ADMIN"/> ADMIN
                <input type="checkbox" name="userRoles" value="CUSTOMER"/> CUSTOMER
                <input type="submit" value="Create User"/>
        </pre>
    </form>
    <div th:text="${message}"></div>
</body>
</html>
```
Explanation:
- The `UserRegister.html` file contains an HTML form that collects user registration data.
- The form's `action` attribute is set to `/user/save`, which maps to the `saveUser` method in the `UserController`.
- The form inputs collect the user's name, email, and password. Additionally, checkboxes are provided for selecting user roles.
- The success message is displayed using Thymeleaf's `th:text` attribute.

**Conclusion:**
In this stage, we have set up the Spring Boot application and implemented the user registration functionality. The `User` entity is defined with the necessary attributes and mappings, and the `UserRepository` provides methods to interact with the database. The `UserController` handles the registration requests and communicates with the `IUserService` implementation to save the user data. The `UserRegister.html` view page displays the registration form and shows the success message upon user creation.