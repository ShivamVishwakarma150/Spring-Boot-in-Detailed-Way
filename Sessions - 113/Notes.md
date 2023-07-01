# Authorization Configuration in Spring Security: Example Code Samples

Here are the example code samples for different authorization scenarios using `antMatchers` in Spring Security:

1. Accessed by everyone: `/welcome`
```java
.antMatchers("/welcome").permitAll()
```
In this scenario, the `/welcome` URL is accessible to everyone without requiring authentication or any specific role.

2. Access URL only after login: `/profile`
```java
.antMatchers("/profile").authenticated()
```
With this configuration, the `/profile` URL can only be accessed by users who have been authenticated (logged in).

3. Access URL after login with role ADMIN: `/mydata`
```java
.antMatchers("/mydata").hasAuthority("ADMIN")
```
This rule ensures that only users with the role "ADMIN" can access the `/mydata` URL.

4. Access URL after login with role MANAGER or CUSTOMER: `/checkBal`
```java
.antMatchers("/checkBal").hasAnyAuthority("MANAGER", "CUSTOMER")
```
In this case, the `/checkBal` URL can be accessed by users who have either the "MANAGER" role or the "CUSTOMER" role.

5. Default authorization for remaining URLs
```java
.anyRequest().permitAll()
// or
.anyRequest().authenticated()
// or
.anyRequest().hasAuthority("ADMIN")
```
These configurations handle all other URLs that are not explicitly mentioned above. 
- `.anyRequest().permitAll()` allows access to all URLs without authentication.
- `.anyRequest().authenticated()` requires authentication for all URLs.
- `.anyRequest().hasAuthority("ADMIN")` restricts access to URLs to users with the "ADMIN" role.

These examples demonstrate how you can configure the authorization rules using `antMatchers` in Spring Security. You can customize these rules based on your application's specific requirements by specifying the URLs, authentication status, and required roles for accessing each URL.

<br/>
<br/>

# **Spring Boot Security : `InMemoryAuthentication`**

In the provided code snippet, we are configuring Spring Security for InMemoryAuthentication. This means that user details are stored in RAM or temporary memory instead of a database. Here is a breakdown of the code:

1. **Configuration:** We define the security configuration class to enable Spring Security using the `@EnableWebSecurity` annotation.

2. **Authorization:** We use the `antMatchers` method to define URL patterns and their corresponding access permissions. In this example, the URLs `/home` and `/login` are permitted for all users (permitAll), while the `/hello` URL requires authentication (authenticated).

3. **User Details:** We create a user with the username "sam", password "sam", and role "ADMIN". This user is stored in memory using the `InMemoryUserDetailsManager` provided by Spring Security.

4. **UserDetailsService:** We configure the `UserDetailsService` bean to return the user details for authentication. In this case, we use the `InMemoryUserDetailsManager` initialized with the user details created earlier.

5. **Additional Security Features:** Spring Security provides additional features such as user validation on login, session management, and session invalidation and logout logic. These features are automatically handled by Spring Security based on the configuration.

By using InMemoryAuthentication, we can quickly set up a simple authentication mechanism for testing or development purposes without the need for a persistent database. However, it is important to note that InMemoryAuthentication is not recommended for production environments, as user data is not persisted and may be lost upon application restart.

Overall, this configuration provides basic authentication and authorization for the specified URLs, allowing access to certain URLs without authentication and requiring authentication for others based on the defined rules.

<br/>
<br/>

# Here's the code and explanation provided for each class separately:


1. Controller
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
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
}
```

- The `HomeController` is a simple Spring MVC controller that defines three endpoints: `/home`, `/hello`, and `/login`.
- The `/home` endpoint returns the `home` view template.
- The `/hello` endpoint returns the `hello` view template.
- The `/login` endpoint returns the `login` view template.

2. SecurityConfig
```java
package com.app.shivam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain configurePaths(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				request -> request.antMatchers("/","/home").permitAll()
				.anyRequest().authenticated()
				)
			.formLogin( form -> form.loginPage("/login").permitAll() )
			.logout( logout -> logout.permitAll() );
			
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("sam").password("sam").roles("ADMIN")
				.build();
		
		UserDetails user2 = User.withDefaultPasswordEncoder()
				.username("ram").password("ram").roles("CUSTOMER")
				.build();
		
		return new InMemoryUserDetailsManager(user, user2);
	}
}
```

- The `SecurityConfig` class is responsible for configuring Spring Security.
- We use the `@EnableWebSecurity` annotation to enable web security.
- The `configurePaths` method configures URL-based authorization rules using `HttpSecurity`.
- The `/` and `/home` URLs are permitted to all users (permitAll), while all other URLs require authentication (authenticated).
- The `formLogin` method configures the login page URL as `/login` and permits access to it for all users.
- The `logout` method configures the logout functionality and allows access to it for all users.
- The `userDetailsService` method configures an `InMemoryUserDetailsManager` with two users: "sam" with the role "ADMIN" and "ram" with the role "CUSTOMER".

3. UI/View Pages
a) home.html
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

- The `home.html` is a Thymeleaf template that displays a welcome message and a link to the `/hello` endpoint.

b) hello.html
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

- The `hello.html` is a Thymeleaf template that displays a "Hello world!" message and a form for logging out.

c) login.html
```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
    <head>
        <title>Spring Security Example</title>
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

- The `login.html` is a Thymeleaf template that displays a login form.
- If the `error` parameter is present in the URL, it indicates an invalid username or password.
- If the `logout` parameter is present in the URL, it indicates a successful logout.

To run the application, you can enter the URL `http://localhost:8080/home` in your browser. This will display the home page with the welcome message. Clicking on the link will take you to the hello page, where you can see the "Hello world!" message and a sign-out button.

# Conclusion

In this simple security application, we implemented Spring Security with in-memory authentication. The application consists of a controller, a security configuration class, and several Thymeleaf view templates.

The `HomeController` defines three endpoints: `/home`, `/hello`, and `/login`. The `/home` endpoint displays a welcome message and a link to the `/hello` page. The `/hello` endpoint shows a "Hello world!" message and a sign-out button. The `/login` endpoint presents a login form where users can enter their username and password.

The `SecurityConfig` class is responsible for configuring Spring Security. It uses the `HttpSecurity` object to define URL-based authorization rules. In this case, the `/home` and `/` URLs are permitted to all users, while all other URLs require authentication. The login page is set to `/login` and is accessible to all users. The logout functionality is also configured to be accessible to all users.

For authentication, we used an in-memory user details service (`InMemoryUserDetailsManager`) to store two users with their usernames, passwords, and roles. One user has the role "ADMIN", and the other has the role "CUSTOMER".

The Thymeleaf view templates (`home.html`, `hello.html`, `login.html`) provide the UI for the application. They display the respective content and handle form submissions.

By running the application and accessing `http://localhost:8080/home`, users can navigate through the different pages, log in using the provided credentials, and log out using the sign-out button.

This simple application demonstrates the basic setup of Spring Security with in-memory authentication, allowing users to access certain URLs based on their authentication status and role.