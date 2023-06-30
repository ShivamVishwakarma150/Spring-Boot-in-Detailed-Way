**Authentication:**
1. Authentication is the process of validating the identity of a user or entity.
2. It involves verifying the user's login details, such as username and password.
3. The purpose of authentication is to ensure that only authorized users can access the application.
4. Authentication is essential for maintaining the security and integrity of the system.
5. In JAAS, authentication is typically performed by validating user credentials against a trusted source, such as a database or an LDAP server.

**Authorization:**
1. Authorization is the process of determining what actions or resources a user or entity is allowed to access.
2. It involves validating the user's role or permissions to ensure they have the necessary privileges.
3. Authorization ensures that users can only access the resources they are entitled to based on their role or permissions.
4. It helps protect sensitive data and restricts unauthorized access to critical functionality.
5. In JAAS, authorization is typically implemented using roles and authorities assigned to users.
6. Roles represent different categories or levels of access within the application, while authorities represent specific permissions associated with roles.

**Bank Application Example:**
1. The Bank Application example demonstrates how authentication and authorization can be applied in a specific context.
2. Various URLs or paths are defined, such as "/home", "/login", "/checkBal", "/logout", "/approveLoan", "/openFD", etc.
3. Each URL may have different authorization requirements based on the user's role.

**Authorization Levels:**
1. `permitAll`:
   - URLs at this level can be accessed by anyone without authentication or a specific role requirement.
   - These URLs typically include public pages or resources that are accessible to all users.

2. `Authenticated`:
   - URLs at this level require users to be authenticated (logged in), but any role is considered valid.
   - Examples include URLs for displaying the logout link, the first login page, or changing passwords.

3. `hasAuthority`:
   - URLs at this level require users to be authenticated and have a specific role or authority assigned to them.
   - Access to these URLs is restricted to users with the specified role or authority.

**Role and Authority:**
1. Roles:
   - Roles represent different categories or levels of access within the application.
   - Examples of roles include "Customer", "Manager", or "Clerk".
   - Roles help define the level of authorization required to access certain resources or perform specific operations.

2. Authority:
   - Authority represents a specific permission or privilege associated with a role.
   - It allows for fine-grained control over access based on specific permissions within a role.
   - Authorities can be assigned to users to grant them access to specific resources or operations.

By understanding and implementing authentication and authorization mechanisms, you can ensure the security and controlled access of your application's resources. JAAS provides a flexible and customizable framework to handle these aspects effectively.

<br/>
<br/>

## **Authentication Types:**

**1. InMemoryAuthentication:**
- InMemoryAuthentication is a simple authentication mechanism where user details are stored in temporary memory (RAM).
- This approach is mainly used for testing purposes and is not recommended for production environments.
- User credentials, such as usernames and passwords, are configured directly in the application's configuration files.
- During the authentication process, the user's provided credentials are matched against the predefined in-memory user details.
- If the credentials match, the user is considered authenticated and granted access.

**2. JDBCAuthentication:**
- JDBCAuthentication is an authentication mechanism that utilizes a database to store user data and roles.
- User details, including usernames, passwords, and associated roles, are stored in one or two database tables.
- During the authentication process, the user's credentials are checked against the stored data using SQL queries.
- The SQL queries are manually defined to retrieve the user details and perform the necessary authentication checks.
- If the credentials match and the user has the required roles, authentication is successful, and access is granted.

**3. ORM Authentication/UserDetailsService:**
- ORM Authentication, also known as UserDetailsService authentication, utilizes Object-Relational Mapping (ORM) frameworks such as Spring Data JPA.
- User details are stored in a database table, and the framework handles the conversion between database records and object representations.
- This approach provides a higher level of abstraction compared to JDBCAuthentication.
- Instead of writing SQL queries manually, the framework takes care of mapping the user details from the database to user objects.
- The UserDetailsService interface is implemented to retrieve user details from the database and perform authentication checks.
- The framework handles the conversion of the retrieved user data into object format when required.

By understanding the different authentication types, you can choose the most suitable approach for your application based on factors such as scalability, security, and ease of implementation. In production environments, it is recommended to use more robust authentication mechanisms like JDBCAuthentication or ORM Authentication/UserDetailsService, where user details are securely stored and managed in a database.

<br/>
<br/>

**PasswordEncoder:**

Storing passwords in plain text format inside a database is highly insecure and not recommended. To enhance security, it is essential to encode passwords before storing them. Spring Boot provides a PasswordEncoder interface and various implementations to handle password encoding and comparison.

**1. Password Encoding:**
- Password encoding is the process of converting a plain text password into an unreadable format.
- The purpose of encoding is to protect the password in case of a data breach or unauthorized access to the database.
- By encoding passwords, even if someone gains access to the stored password values, they won't be able to retrieve the original passwords.

**2. PasswordEncoder Interface:**
- Spring Boot provides the PasswordEncoder interface as part of its security framework.
- This interface defines methods for encoding and comparing passwords.
- The PasswordEncoder interface is implemented by different classes that provide specific password encoding algorithms.

**3. Password Encoding Workflow:**
- When a user registers or sets a password, the entered plain text password is encoded using the selected password encoder algorithm.
- The encoded password is then stored in the database.
- During the login process, the entered password is again encoded using the same algorithm.
- The encoded password is compared with the stored encoded password in the database.
- If the two encoded passwords match, the user is considered authenticated, and further actions can be taken, such as granting access to protected resources.

**4. No Password Decoding:**
- Spring Boot does not provide a password decoding method. Once a password is encoded, it cannot be decoded back to its original plain text form.
- This one-way nature of password encoding adds an extra layer of security, as even if the encoded passwords are somehow exposed, it is extremely difficult to retrieve the original passwords.

By utilizing the PasswordEncoder interface and appropriate password encoding techniques, you can enhance the security of your application by storing passwords in an unreadable format. This significantly reduces the risk of unauthorized access and protects user credentials in case of a data breach.

<br/>
<br/>

# **Notes**

1. In Spring Security, we typically need to define a configuration class specifically for security-related settings. This class is commonly named `SecurityConfig` or any other name of your choice. It serves as the main configuration class for handling security within the application.

2. The `SecurityConfig` class contains two main methods:

   a. Authentication: This method is responsible for configuring the authentication mechanism. There are three common approaches to authentication:
      - InMemory: Storing user details directly in memory (RAM) without using a database. This approach is suitable for testing purposes but not recommended for production.
      - JDBC: Storing user details in a database and using SQL queries to validate login credentials and retrieve user roles.
      - UserDetailsService: Utilizing an ORM (Object-Relational Mapping) framework like Spring Data JPA to store user data in a database table and automatically convert it into object format when required.

   b. Authorization: This method handles the authorization logic, defining which resources or paths are accessible to different user roles. It specifies access rules based on URL patterns, HTTP methods, and user authorities. For example, you can use the `permitAll()` configuration to allow access to certain URLs without authentication, or the `authenticated()` configuration to restrict access to authenticated users. Additionally, it configures the login form and logout link.

3. **Starting from Spring Boot 2.7 or later versions**, it is not required for the `SecurityConfig` class to extend the `WebSecurityConfigurerAdapter` class explicitly. In earlier versions, such as Spring Boot 2.6 or before, extending `WebSecurityConfigurerAdapter` was necessary to inherit the default security configurations and provide customizations. However, in the newer versions, the `SecurityConfig` class can function as a configuration class for security without explicitly extending any specific class.

In conclusion, the `SecurityConfig` class is a central configuration class for handling security in a Spring application. It defines authentication and authorization mechanisms, allows customization of access rules, and provides options for different authentication approaches. The specific implementation of the class may vary based on the version of Spring Boot being used.

<br/>
<br/>

# Here is the code from the `SecurityConfig.java` file:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/user/register","/user/save","/user/login").permitAll()
			.antMatchers("/home").permitAll()
			.antMatchers("/admin").hasAuthority("ADMIN")
			.antMatchers("/emp").hasAnyAuthority("ADMIN","EMPLOYEE")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/user/login")  //def: /login, GET
				.loginProcessingUrl("/login") 
				.defaultSuccessUrl("/profile",true)
				.failureUrl("/user/login?error")//def: /login?error
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
				.logoutSuccessUrl("/user/login?logout") //def: /login?logout
			.and()
			.exceptionHandling()
				.accessDeniedPage("/denied");
	}
}
```

# here is the code from the `WebSecurityConfig.java` file:

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
			 	.username("admin")
				.password("admin")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
```

Now, let's explain each configuration separately:

**SecurityConfig.java:**
- The `SecurityConfig` class is annotated with `@Configuration` and `@EnableWebSecurity` to indicate that it is a configuration class for Spring Security.
- It extends `WebSecurityConfigurerAdapter` to inherit its functionality for configuring security.
- The `configure(AuthenticationManagerBuilder auth)` method configures the authentication manager to use the `userDetailsService` and `passwordEncoder`.
- The `configure(HttpSecurity http)` method configures the HTTP security settings, specifying the access rules, login/logout configurations, and exception handling.
- The `userDetailsService` is autowired to retrieve user details.
- The `passwordEncoder` is autowired for encoding passwords.
- The access rules are defined using the `antMatchers()` method, allowing or restricting access based on the requested paths and authorities.
- The login and logout configurations are specified using the `formLogin()` and `logout()` methods.
- The `exceptionHandling()` method sets the custom access denied page.

**WebSecurityConfig.java:**
- The `WebSecurityConfig` class is annotated with `@Configuration` and `@EnableWebSecurity` to indicate that it is a configuration class for Spring Security.
- It defines a `securityFilterChain` bean using the `securityFilterChain(HttpSecurity http)` method.
- The `securityFilterChain` bean configures the HTTP security

 settings using the `authorizeHttpRequests()` method, specifying the access rules for various paths.
- The `formLogin()` method configures the login page and allows access to it.
- The `logout()` method configures the logout functionality and allows access to it.
- The `userDetailsService()` method creates a user with default password encoding and assigns the role "ADMIN".
- In this configuration, an in-memory user details manager is used to store the user details.

Now, let's compare the two configurations:

1. Authentication and Authorization:
   - Both configurations provide authentication and authorization functionality.
   - `SecurityConfig.java` uses a user details service to retrieve user details from a database, while `WebSecurityConfig.java` uses an in-memory user details manager with a single user.
   - `SecurityConfig.java` uses the `BCryptPasswordEncoder` to encode passwords, whereas `WebSecurityConfig.java` uses the default password encoder.
   - Both configurations specify access rules based on paths and authorities.

2. Access Rules:
   - In `SecurityConfig.java`, access rules are defined using the `antMatchers()` method, allowing or restricting access based on requested paths and authorities.
   - In `WebSecurityConfig.java`, access rules are defined directly in the `securityFilterChain` bean using the `authorizeHttpRequests()` method.

3. Login and Logout:
   - In `SecurityConfig.java`, login and logout configurations are specified using the `formLogin()` and `logout()` methods.
   - In `WebSecurityConfig.java`, login and logout configurations are specified directly in the `securityFilterChain` bean.

4. Exception Handling:
   - `SecurityConfig.java` provides custom exception handling by setting the access denied page using the `accessDeniedPage()` method.
   - `WebSecurityConfig.java` does not specify custom exception handling.

In conclusion, both configurations provide authentication and authorization functionality, but they differ in the way access rules, login/logout configurations, and exception handling are defined. `SecurityConfig.java` is more comprehensive, using a user details service and password encoding, while `WebSecurityConfig.java` uses a simpler in-memory user details manager with a default password encoder. The choice between the two configurations depends on the specific requirements and complexity of the application.

<br/>
<br/>

# **Notes**

in order to use Spring Security in your application, you need to add the corresponding dependency to your `pom.xml` file. The dependency you need to include is `spring-boot-starter-security`. 

Here's an example of how you can add the Spring Security dependency to your `pom.xml` file:

```xml
<dependencies>
    <!-- Other dependencies -->
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <!-- Other dependencies -->
</dependencies>
```

By adding this dependency, you are pulling in all the necessary Spring Security libraries and configurations into your project. This enables you to use Spring Security features and components in your application, such as authentication, authorization, and securing your endpoints.

Remember to update your Maven project after adding the dependency to download the necessary dependencies. You can do this by running the `mvn clean install` command or using your IDE's Maven integration to refresh the project.

Adding the Spring Security dependency allows you to leverage the powerful security features provided by Spring Security framework and customize them according to your application's requirements.