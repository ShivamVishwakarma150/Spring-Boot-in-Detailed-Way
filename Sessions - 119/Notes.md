**1) User Register**<br/>
Read data as JSON From POSTMAN and Store in DB

Explanation:
- User Registration is a process where users can create an account by providing their information, such as username, email, password, etc. The data is usually sent in JSON format from a client application like POSTMAN to the server.
- In the server-side, the application needs to handle the incoming JSON data and extract the relevant information (username, email, password, etc.) from it.
- The application then needs to store this user data in a database for future authentication and login purposes.
- To achieve this, the server-side application typically uses an HTTP POST request to receive the JSON data from the client, and it can use a framework like Spring Boot with Spring Data JPA to handle the data persistence to the database.

**2) User Login and Token Generation**
<br/>
Define JWT Token service to generate a token after login success

Explanation:
- User Login is a process where a registered user can authenticate themselves by providing their login credentials (username/email and password).
- Upon successful authentication, the server generates a JSON Web Token (JWT) for the user, which is a compact and digitally signed token containing user information and a signature for validation.
- The JWT is then sent back to the client and can be included in subsequent requests to authenticate and authorize the user.
- The token generation process typically involves the server-side application verifying the provided login credentials against the stored user data in the database.
- If the credentials are valid, the server generates a JWT using a secret key, including the user's identity and any necessary claims or roles.
- The server then signs the JWT using the secret key to create a digital signature, ensuring the token's integrity and authenticity.

**3) Token validation and Service**<br/>
Check Auth Header using Filter and validate using JwtUtil. If valid, then continue to service/controller code.

Explanation:
- Token validation is a critical step in securing protected resources and ensuring that only authenticated and authorized users can access them.
- When a client makes a request to a protected resource, it includes the JWT in the Authorization header (commonly using the "Bearer" scheme).
- The server application needs to intercept this request before it reaches the service/controller code to validate the token's authenticity and determine if the user has the necessary permissions to access the resource.
- To accomplish this, the server application uses a Filter, a component in the Servlet API that intercepts requests and responses.
- The Filter is responsible for extracting the JWT from the Authorization header, verifying its integrity and signature using a JwtUtil (a utility class or service dedicated to JWT operations), and checking if the token is still valid (not expired).
- If the token is valid and the user has the required permissions, the Filter allows the request to continue to the service/controller code, granting access to the protected resource.
- If the token is invalid or has expired, the Filter can deny access by returning an appropriate error response (e.g., HTTP 401 Unauthorized or HTTP 403 Forbidden).

In summary, the user registration process involves reading JSON data from the client and storing it in the database. The user login process generates a JWT upon successful authentication, which is sent back to the client for future authentication. The server-side application uses a Filter and JwtUtil to validate the JWT before allowing access to protected resources. These steps ensure secure user registration, authentication, and authorization within the application.

<br/>
<br/>
<br/>

# Let's delve into each point and provide a detailed explanation:

**1. Filter:**
- Filters in Spring Boot Security are components that allow the execution of pre-processing and post-processing logic for HTTP requests and responses.
- To create a filter that executes for every request only once, you can extend the `OncePerRequestFilter` class provided by Spring Security and implement the `doFilterInternal` method.
- Inside the `doFilterInternal` method, you can define the necessary coding steps for processing the request.
- Here's an outline of the coding steps that can be implemented within the `doFilterInternal` method:
  - Read the Authorization header from the incoming request to obtain the token.
  - Validate the token using a `JwtUtil` class or service. This typically involves verifying the token's signature, expiration, and integrity.
  - Read the subject or username from the validated token.
  - Validate the username by loading the user data from the database or any other authentication source.
  - Create a `UsernamePasswordAuthenticationToken` object to represent the authenticated user.
  - Link the authentication token to the current request and update the `SecurityContext`, which holds the authentication information and remains valid until the response is generated.

**2. Security Config:**
- In Spring Boot Security, the security configuration is responsible for defining authentication and authorization rules for your application.
- To configure the security, you can create a class annotated with `@Configuration` and `@EnableWebSecurity`.
- Within this configuration class, you can define the authentication and authorization settings.
- Stateless configuration: To create a stateless configuration, you typically disable the usage of HTTP sessions since JWT tokens are used for stateless authentication. This can be achieved by configuring the session management to be stateless using the `http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)` method.
- Registering the filter: You need to add/register the filter that should be executed before the filter chain (FC). This can be done using the `http.addFilterBefore(filter, beforeFilter)` method in the security configuration. The `filter` parameter refers to the filter instance you want to register, and the `beforeFilter` parameter specifies the filter before which the new filter should be added in the chain.

In summary, the Filter component is responsible for executing pre-processing and post-processing logic for each request. By extending the `OncePerRequestFilter` class, you can create a filter that executes only once per request. Inside the `doFilterInternal` method, you can implement the necessary steps to read the authorization header, validate the token, load user data, and establish authentication. 

The Security Config component, on the other hand, is responsible for configuring authentication and authorization rules. In a stateless configuration, you disable the usage of HTTP sessions since JWT tokens are used for authentication. Additionally, you can register your custom filter to be executed before the filter chain, allowing it to perform the necessary token validation and user authentication.

By following these steps, you can effectively integrate filters and configure security in a Spring Boot application using Spring Security.

<br/>
<br/>

# Let's go through each point and provide a detailed explanation:

**1. Create user:**
- To create a user, you send an HTTP POST request to the endpoint `http://localhost:9090/user/save`.
- The request body should contain the user information in JSON format, including the name, username, password, and roles.
- Here's an example of the request body:
```json
{
    "name" : "vikas",
    "username" : "vikas@gm.com",
    "password" : "vikas",
    "roles" : ["ADMIN","CUSTOMER"]
}
```
- The server-side application, upon receiving this request, should handle the POST request and extract the user information from the JSON body.
- The application can then save this user data to the appropriate storage, such as a database, for future reference.

**2. Login user:**
- To login a user, you send an HTTP POST request to the endpoint `http://localhost:9090/user/login`.
- The request body should contain the login credentials in JSON format, including the username and password.
- Here's an example of the request body:
```json
{
    "username" : "vikas@gm.com",
    "password" : "vikas"
}
```
- The server-side application, upon receiving this request, should handle the POST request and extract the login credentials from the JSON body.
- The application can then validate the credentials against the stored user data, typically by querying the database.
- If the credentials are valid, the application generates a JSON Web Token (JWT) to represent the authenticated user. The token is returned in the response body or as a separate response header.

**3. Check Secured Resources:**
- To access a secured resource, you send an HTTP POST request to the endpoint `http://localhost:9090/user/welcome`.
- Since this resource is protected, you need to include the JWT token in the request headers for authorization.
- In the request headers, set the `Authorization` key with the value `Bearer <token>`, where `<token>` is the JWT obtained after the successful user login.
- This can be done by adding the following header to the request:
```
Authorization: Bearer <paste your token here>
```
- The server-side application, upon receiving this request, should handle the POST request and intercept it using a security filter.
- The security filter extracts the JWT from the `Authorization` header and validates it for authenticity, integrity, and expiration.
- If the token is valid, the filter allows the request to proceed to the secured resource (in this case, the `welcome` endpoint).
- The server-side application can then process the request and return the appropriate response based on the logic implemented for that endpoint.

In summary, the user creation process involves sending an HTTP POST request with the user information in JSON format. The login process requires sending an HTTP POST request with the login credentials in JSON format, and upon successful login, a JWT token is generated. To access secured resources, you send an HTTP POST request with the JWT token included in the `Authorization` header. The server-side application validates the token and allows access to the secured resource if the token is valid.

<br/>
<br/>


**1. Principal (I):**
- The `Principal` interface, provided by Sun/Oracle, is a security contract that defines the behavior of an object that stores the current user's details.
- The `Principal` interface typically represents the user's identity and holds information such as the user's name.
- In the context of security frameworks and technologies, when implementing security concepts, the current user's details are stored inside an object that implements the `Principal` interface.
- It allows for consistent handling of user-related information across different security frameworks and technologies.
- However, note that the `Principal` interface provided by Sun/Oracle only stores the user's name. Additional details such as roles or authentication information may be stored separately.

**2. Authentication (I):**
- The `Authentication` interface, provided by Spring Security, is another security contract that extends the `Principal` interface and provides additional functionalities.
- The `Authentication` interface represents an authenticated principal, which includes the user's identity (typically represented by the username) and additional information such as roles, authorities, or authentication details.
- It is part of the Spring Security framework and is widely used for managing authentication-related operations.
- The `Authentication` interface can be implemented by various classes, but one commonly used implementation is the `UsernamePasswordAuthenticationToken` class.

**3. UsernamePasswordAuthenticationToken (C):**
- The `UsernamePasswordAuthenticationToken` class is an implementation of the `Authentication` interface provided by Spring Security.
- It represents an authentication request with the user's credentials, such as username and password.
- When a user attempts to authenticate, the `UsernamePasswordAuthenticationToken` class is commonly used to encapsulate the user's credentials before being passed to the authentication provider for validation.
- After successful authentication, an `Authentication` object (often an instance of `UsernamePasswordAuthenticationToken`) is created to represent the authenticated principal.

**4. SecurityContextHolder (C) and SecurityContext (I):**
- Spring Security provides the `SecurityContextHolder` class and the `SecurityContext` interface to manage the current user's details within the application.
- The `SecurityContextHolder` class acts as a container for holding the `SecurityContext` object, which represents the security-related information for the current user within a specific thread.
- The `SecurityContext` interface defines the contract for storing security-related information, including the `Authentication` object representing the authenticated principal.
- The `SecurityContextHolder` class allows easy access to the `SecurityContext` object and provides static methods for getting and setting the current security context.
- By storing the `SecurityContext` in the `SecurityContextHolder`, you can access the current user's details globally within the application.

In summary, the `Principal` interface provided by Sun/Oracle represents the user's identity, while the `Authentication` interface provided by Spring Security extends the `Principal` interface and includes additional authentication-related information. The `UsernamePasswordAuthenticationToken` class is a commonly used implementation of the `Authentication` interface. Spring Security also provides the `SecurityContextHolder` class and the `SecurityContext` interface to store and manage the current user's details within the application. These components play a crucial role in managing user authentication and providing access to the user's information throughout the application.

<br/>
<br/>

# Here's the breakdown of the provided code snippets, along with explanations and a conclusion:

First, let's analyze the `SecurityFilter` class:

```java
@Component
public class SecurityFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// read Token from Request Header
		String token = request.getHeader("Authorization");
		if (token != null) {
			// validate and read subject from token
			String username = jwtUtil.getUsername(token);
			// check userdetails
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// load user from DB
				UserDetails user = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						username, user.getPassword(), user.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}
}
```

Explanation:
- The `SecurityFilter` class is responsible for intercepting and processing requests.
- It extends the `OncePerRequestFilter` class, ensuring that the filter logic is executed only once per request.
- The `JwtUtil` and `UserDetailsService` are injected using `@Autowired` annotation to access JWT-related functionalities and retrieve user details from the database, respectively.
- The `doFilterInternal` method is overridden to implement the filter logic.
- It reads the token from the request header and validates it using the `JwtUtil` class.
- If the token is valid and there is no existing authentication in the `SecurityContextHolder`, it loads the user details from the database using the `UserDetailsService`.
- An `UsernamePasswordAuthenticationToken` is created using the username, password, and authorities of the user.
- The authentication token is associated with the current request using `SecurityContextHolder`.
- Finally, the filter chain is invoked to continue processing the request.

Next, let's analyze the `SecurityConfig` class:

```java
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
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
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

Explanation:
- The `SecurityConfig` class is responsible for configuring security-related settings.
- It is annotated with `@Configuration` and `@EnableWebSecurity` to enable Spring Security configuration.
- The `UserDetailsService`, `BCryptPasswordEncoder`, `AuthenticationEntryPoint`, and `SecurityFilter` are injected using `@Autowired` annotation.
- The `authenticationManager` method is defined as a `@Bean` and provides an `AuthenticationManager` instance, which is used for authentication purposes.
- The `authenticationProvider` method defines a `DaoAuthenticationProvider` bean, which configures the password encoder and the `UserDetailsService`.
- The `configureAuth` method configures the security settings using the provided `HttpSecurity` object.
- It disables CSRF protection, specifies that requests to "/user/save" and "/user/login" should be permitted without authentication, and any other request should be authenticated.
- It sets the authentication entry point and configures the session management to be stateless.
- Finally, it adds the `SecurityFilter` before the `UsernamePasswordAuthenticationFilter` in the filter chain.

Conclusion:
The provided code snippets demonstrate the implementation of security features using Spring Security in a Spring Boot application. The `SecurityFilter` class acts as a filter to intercept and process requests, validating JWT tokens and loading user details from the database. The `SecurityConfig` class configures the security settings, such as authentication providers, request authorization rules, session management, and the filter chain. These components work together to provide authentication and authorization capabilities to secure the application's resources.

Please note that the code snippets provided are only a part of the overall security implementation, and additional configurations and dependencies might be required depending on the specific requirements of your application.