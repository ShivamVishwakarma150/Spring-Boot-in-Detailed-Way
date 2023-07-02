# Here's the revised response with the code and explanations organized as requested:

1. `Application` class:
```java
package com.app.shivam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```
The `Application` class serves as the entry point for the Spring Boot application.

2. `AppConfig` class:
```java
package com.app.shivam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
```
The `AppConfig` class is a configuration class that defines a `BCryptPasswordEncoder` bean, which is used for password encoding in the application.

3. `InvalidUserAuthenticationEntryPoint` class:
```java
package com.app.shivam.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class InvalidUserAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "authentication failed");
	}
}
```
The `InvalidUserAuthenticationEntryPoint` class implements the `AuthenticationEntryPoint` interface and provides custom handling for authentication failures. It sends an unauthorized error response when authentication fails.

4. `SecurityConfig` class:
```java
package com.app.shivam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.shivam.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration
			) throws Exception 
	{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public SecurityFilterChain configureAuth(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/user/save","/user/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
```
The `SecurityConfig` class is responsible for configuring the security settings of the application. It defines an authentication manager, authentication provider, authentication entry point, and security filter. It also configures the URL patterns that require authentication and session management.

5. `User` class:
```java
package com.app.shivam.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "usrs_tab")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String username;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
}
```
The `User` class represents the user entity in the application. It contains fields such as id, name, username, password, and roles. The `ElementCollection` annotation is used to define a collection of roles for each user.

6. `SecurityFilter` class:
```java
package com.app.shivam.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.shivam.util.JwtUtil;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Read Token from Request Header
		String token = request.getHeader("Authorization");
		if (token != null) {

			// Validate and read subject from token
			String username = jwtUtil.getUsername(token);

			// Check user details
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// Load user from DB
				UserDetails user = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
						user.getPassword(), user.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}
}
```
The `SecurityFilter` class is a custom filter that intercepts requests and validates the JWT token. It retrieves the token from the request header, validates it using the `JwtUtil` class, and loads the corresponding user details from the database. It sets the authentication object in the `SecurityContextHolder` if the token is valid.

7. `UserRequest` class:
```java
package com.app.shivam.payload;

import lombok.Data;

@Data
public class UserRequest {

	private String username;
	private String password;
}
```
The `UserRequest` class is a data transfer object (DTO) used for receiving user login requests. It contains the username and password fields.

8. `UserResponse` class:
```java
package com.app.shivam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	private String token;
	private String note;
}
```
The `UserResponse` class is a DTO used for sending user login responses. It contains a token field, which represents the generated JWT token, and a note field, which can be used to include additional information in the response.

9. `UserRepository` interface:
```java
package com.app.shivam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
}
```
The `UserRepository` interface extends the `JpaRepository` interface and provides CRUD operations for the `User` entity. It includes a method `findByUsername` to find a user by their username.

10. `UserRestController` class:
```java
package com.app.shivam.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.User;
import com.app.shivam.payload.UserRequest;
import com.app.shivam.payload.UserResponse;
import com.app.shivam.service.IUserService;
import com.app.shivam.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService service;
	
	@Autowired
	private JwtUtil util;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(
			@RequestBody User user) 
	{
		service.saveUser(user);
		return ResponseEntity.ok("User created");
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userRequest.getUsername(), 
						userRequest.getPassword()
						)
					);
		String token = util.generateToken(userRequest.getUsername());
		
		return ResponseEntity.ok(new UserResponse(token, "Generated By SHIVAM"));
	}
	
	@PostMapping("/welcome")
	public ResponseEntity<String> showUserData(Principal p) {
		System.out.println(p.getClass().getName());
		return ResponseEntity.ok("Hello: " + p.getName());
	}
}
```
The `UserRestController` class is a REST controller that handles user-related requests such as user creation, user login, and retrieving user data. It uses the `IUserService` interface, `AuthenticationManager`, and `JwtUtil` for handling user operations.

11. `IUserService` interface:
```java
package com.app.shivam.service;

import com.app.shivam.entity.User;

public interface IUserService {

	public Integer saveUser(User user);
	public User findByUsername(String username);
}
```
The `IUserService` interface defines the contract for user-related operations. It includes methods for saving a user and finding a user by their username.

12. `UserServiceImpl` class:
```java
package com.app.shivam.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.shivam.entity.User;
import com.app.shivam.repo.UserRepository;
import com.app.shivam.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Integer saveUser(User user) {
		String pwd

 = user.getPassword();
		String encPwd = encoder.encode(pwd);
		user.setPassword(encPwd);
		return repo.save(user).getId();
	}
	
	public User findByUsername(String username) {
		Optional<User> optional = repo.findByUsername(username);
		return optional.orElse(null);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}
}
```
The `UserServiceImpl` class implements the `IUserService` interface and extends the `UserDetailsService`. It provides the implementation for saving a user, finding a user by their username, and loading user details for authentication and authorization purposes.

This revised response provides a more organized and structured view of the components involved in the Spring Boot JWT authentication and authorization example. Each class and its purpose are explained, making it easier to understand the overall flow and functionality of the application.

<br/>
<br/>

The provided code represents a simple implementation of JWT-based authentication and authorization in a Spring Boot application. It includes the necessary components such as entities, repositories, services, controllers, and utility classes to handle user registration, login, and data retrieval.

The main components of the code are as follows:

1. `User` entity: Represents a user with attributes like `id`, `username`, `password`, and `roles`. The entity is persisted in the database using JPA.

2. `UserRepository` interface: Extends the `JpaRepository` interface and provides CRUD operations for the `User` entity. It includes a method to find a user by their username.

3. `UserRestController` class: Acts as a REST controller and handles user-related requests. It includes methods for user creation (`saveUser`), user login (`loginUser`), and retrieving user data (`showUserData`).

4. `IUserService` interface: Defines the contract for user-related operations. It includes methods for saving a user and finding a user by their username.

5. `UserServiceImpl` class: Implements the `IUserService` interface and extends the `UserDetailsService`. It provides the implementation for saving a user, finding a user by their username, and loading user details for authentication and authorization purposes. It also uses the `BCryptPasswordEncoder` for password encryption.

6. `AuthenticationManager` and `JwtUtil`: These classes are used for user authentication and token generation/verification.

In conclusion, the provided code demonstrates a basic implementation of JWT authentication and authorization in a Spring Boot application. It showcases the usage of various components like entities, repositories, services, controllers, and utility classes to handle user operations. However, it's important to note that this code may be just a part of a larger application, and there may be additional components and configurations required for a fully functional and secure authentication system.