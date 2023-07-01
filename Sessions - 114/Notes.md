# **Spring Boot Security - using JDBC**

Certainly! Here's the response with proper formatting and headings:

**InMemoryAuthentication:**
This feature is provided by Spring Security for defining user details in-memory, mainly used for testing purposes or in development environments. However, it is not recommended for production use.

**Storing/Fetching User Details using JDBC API:**
JDBC (Java Database Connectivity) is a Java API that offers a standardized way to interact with databases. In the context of Spring Security, the JDBC API is used to store and retrieve user details from a database. This enables leveraging the capabilities of a database management system for user authentication and authorization.

**Two Tables for User Data:**
To store user data, two tables are created: "users" and "authorities." The "users" table typically contains columns like "username," "password," and "enabled." It stores basic user information such as the username, encoded password, and the account's enabled status. The "authorities" table is used to associate user roles or authorities with the corresponding users, containing columns like "username" and "authority."

**PasswordEncoder:**
Using a PasswordEncoder is highly recommended to encode and store passwords securely in the database tables. Storing passwords in plain text is considered insecure. The PasswordEncoder interface provided by Spring Security allows for password encoding using algorithms like bcrypt, PBKDF2, or Argon2. By encoding passwords, they are hashed or encrypted before being stored, enhancing security. During authentication, the provided password is encoded using the same algorithm, and the encoded values are compared.

To summarize, the notes emphasize the usage of Spring Boot Security with JDBC for authentication and authorization. It advises against using in-memory authentication in production and suggests storing user details in a database. Two tables, namely "users" and "authorities," are created to store user information and roles, respectively. The importance of using a PasswordEncoder to securely store and compare passwords is highlighted.

<br/>
<br/>

## **Database Setup:**

```sql
create table users (
    username varchar(50) not null primary key,
    password varchar(500) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username, authority);
```

**Explanation:**

The provided SQL script sets up the necessary database tables and constraints for user authentication and authorization. Let's break down each part:

1. **`users` table:**
   - This table stores user information such as username, password, and enabled status.
   - The `username` column is defined as the primary key, ensuring uniqueness.
   - The `password` column stores the encrypted password.
   - The `enabled` column indicates whether the user account is enabled or disabled.

2. **`authorities` table:**
   - This table is used to store user roles or authorities.
   - The `username` column references the `username` column in the `users` table, establishing a foreign key relationship.
   - The `authority` column holds the specific authority or role assigned to a user.

3. **`ix_auth_username` index:**
   - This unique index is created on the `authorities` table.
   - It ensures that the combination of `username` and `authority` remains unique.

The database setup provides a structured schema to store user information and their associated roles for authentication and authorization purposes.

**Conclusion:**

By following the provided SQL script, you can create the necessary tables and constraints in your database. The `users` table stores user details, while the `authorities` table associates users with their respective roles. This setup allows for effective user authentication and authorization within your application.

<br/>
<br/>

# the code snippets and explanations for each class separately, starting with the `HomeController` class.

1. HomeController:

```java
package com.app.shivam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/home","/"})
    public String showHome() {
        return "home";
    }

    @GetMapping("/hello")
    public String showHello() {
        return "hello";
    }

    @GetMapping("/admin")
    public String showAdmin() {
        return "admin";
    }

    @GetMapping("/customer")
    public String showCustomer() {
        return "customer";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}
```

Explanation:
The `HomeController` class is a Spring MVC controller that handles different HTTP requests. It defines several handler methods annotated with `@GetMapping` to map specific URLs to corresponding views.

- The `showHome()` method handles requests for the "/home" and "/" URLs and returns the "home" view.
- The `showHello()` method handles requests for the "/hello" URL and returns the "hello" view.
- The `showAdmin()` method handles requests for the "/admin" URL and returns the "admin" view.
- The `showCustomer()` method handles requests for the "/customer" URL and returns the "customer" view.
- The `showLogin()` method handles requests for the "/login" URL and returns the "login" view.

Now let's move on to the `SecurityConfig` class.

2. SecurityConfig:

```java
package com.app.shivam.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configureAuth(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                request -> request.antMatchers("/home","/").permitAll()
                        .antMatchers("/admin").hasAuthority("ADMIN")
                        .antMatchers("/customer").hasAuthority("CUSTOMER")
                        .anyRequest().authenticated()
            )
            .formLogin(
                form -> form.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/hello", true)
            )
            .logout(logout -> logout.permitAll());
        
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource datasource) {
        UserDetails user1 = User.withUsername("sam")
            .password("$2a$10$TD7ldmKUQw3EHFxVivyA8OUrzy7butY9QDRnltnBS/b9aI0j6reYq")
            .authorities("ADMIN").build();

        UserDetails user2 = User.withUsername("ram")
            .password("$2a$10$dEm8gdOC0R2S7IgXSnBKFOSeeKbCNuVeMC/hP24eY7zdADlUif4n.")
            .authorities("CUSTOMER").build();

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(datasource);
       
        users.createUser(user1);
        users.createUser(user2);

        return users;
    }
}
```

Explanation:
The `SecurityConfig` class is responsible for configuring Spring Security settings and handling authentication and authorization.

- The `passwordEncoder()` method configures the password encoder bean using the BCrypt algorithm.
- The `configureAuth(HttpSecurity http)` method defines the security rules and access permissions.
  - The `.authorizeHttpRequests()` method configures the access rules based on URL patterns.
    - Requests to "/home" and "/" are allowed for all users.
    - Requests to "/admin" require the "ADMIN" authority.
    - Requests to "/customer" require the "CUSTOMER" authority.
    - Any other requests require authentication.
  - The `.formLogin()` method configures the login process.
    - The login page is set to "/login".
    - The login page is accessible by all users.
    - After successful login, the user is redirected to "/hello".
  - The `.logout()` method configures the logout process, allowing all users to log out.
- The `userDetailsService(DataSource datasource)` method configures the user details service using JDBC authentication.
  - Two users, "sam" (ADMIN) and "ram" (CUSTOMER), are created with their encoded passwords and authorities.
  - The `JdbcUserDetailsManager` is used to manage the users and their details.

Next, let's look at the view pages.

3. View pages:

a) admin.html:

```html
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
    <head>
        <title>Hello World!</title>
    </head>
    <body>
        <h1>HELLO ADMIN PAGE!!</h1>
        <form th:action="@{/logout}" method="post">
            <input type="submit" value="Sign Out"/>
        </form>
    </body>
</html>
```

b) customer.html:

```html
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
    <head>
        <title>Hello World!</title>
    </head>
    <body>
        <h1>HELLO CUSTOMER PAGE!!</h1>
        <form th:action="@{/logout}" method="post">
            <input type="submit" value="Sign Out"/>
        </form>
    </body>
</html>
```

c) hello.html:

```html
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
    <head>
        <title>Hello World!</title>
    </head>
    <body>
        <h1>Hello world!</h1>
        <form th:action="@{/logout}" method="post">
            <input type="submit" value="Sign Out"/>
        </form>
    </body>
</html>
```

d) home.html:

```html
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
    <head>
        <title>Spring Security Example</title>
    </head>
    <body>
        <h1>Welcome!</h1>

        <p>Click <a th:href="@{/hello}">here</a> to see a greeting.</p>
    </body>
</html>
```

e) login.html:

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
    <head>
        <title>Spring Security Example </title>
    </head>
    <body>
        <div th:if="${param.error}">
            Invalid username and password.
        </div>


        <div th:if="${param.logout}">
            You have been logged out.
        </div>
        <br/>
        <form th:action="@{/login}" method="post">
            <div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
        </form>
    </body>
</html>
```

Finally, the `application.properties` file contains the database connection configuration.

4. application.properties:

```
# DB Connection
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/boot
spring.datasource.username=root
spring.datasource.password=Shivam@123
```

Conclusion:
The provided code demonstrates a basic Spring Boot application with Spring Security for JDBC authentication and authorization. It includes a controller for handling different URLs, a security configuration class for defining access rules, and several view pages for different user roles. The application uses Thymeleaf as the templating engine for rendering the views.

<br/>
<br/>

To run the application and test it, follow these steps:

1. Make sure you have a MySQL database set up and running.
2. Execute the following SQL queries to delete any existing data from the `authorities` and `users` tables:

```sql
DELETE FROM authorities;
DELETE FROM users;
```

3. Run the Spring Boot application.
4. Open a web browser and enter the URL `http://localhost:8080`.
5. The application will load the home page (`home.html`), which displays a welcome message and a link to the greeting page.
6. Click on the link to the greeting page (`hello.html`).
7. If you're not logged in, you'll be redirected to the login page (`login.html`).
8. Enter the username as "sam" and the password associated with the "sam" user.
9. After successful login, you'll be redirected to the greeting page (`hello.html`) and see the "Hello world!" message.
10. From the greeting page, you can click on the "Sign Out" button to log out.
11. Repeat steps 6-10 with the username "ram" and the associated password to test the customer role and page (`customer.html`).
12. If you try to access the customer page (`/customer`) with the "sam" user (who has the admin role), you should receive a "403 Error Forbidden" message indicating that access is not allowed.

Note: Make sure to replace the database connection details in the `application.properties` file with your own MySQL database credentials.

By following these steps, you can test the Spring Boot application with different user roles and verify the authentication and authorization functionality.