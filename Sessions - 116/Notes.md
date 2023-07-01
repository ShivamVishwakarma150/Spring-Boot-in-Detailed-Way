# **2. User Login and Password Encoder**

1. **User Login and Password Encoder**

- User data is stored in database tables `usertab` and `rolestab`.
- To load user data from these tables into the `User` entity class object, we can use Data JPA.
- However, Spring Security requires a `User` object of type `Security` for authentication and authorization purposes.
- To convert the `User` entity class object to a Spring Security `User` object, we need to implement a custom `UserDetailsService` class.

2. **Custom UserDetailsService (ORM)**

- The `UserDetailsService` interface provides a method called `loadUserByUsername(String username)` that converts an entity class `User` object to a Spring Security `User` object.
- The `UserDetailsService` implementation class is responsible for loading user-specific data and converting it into a format that can be understood by Spring Security.
- The implementation class retrieves the user details from the database and creates a `User` object with the required information.
- The `User` object is then returned to Spring Security for authentication and authorization processes.

3. **PasswordEncoder**

- The `PasswordEncoder` interface is used to encode passwords before storing them in the database.
- It provides methods for both encoding and verifying passwords.
- Spring Security recommends using strong password encoding algorithms like BCryptPasswordEncoder or Pbkdf2PasswordEncoder.
- The password encoder ensures that passwords are securely stored and not stored in plain text format.

4. **UserDetails**

- The `UserDetails` interface represents a user's core security information.
- It contains methods for retrieving the user's username, password, authorities (roles), account status, and more.
- Spring Security's `User` class implements the `UserDetails` interface, providing default implementations for these methods.

5. **GrantedAuthority**

- Roles in Spring Security are internally stored as `GrantedAuthority` objects.
- `GrantedAuthority` is an interface that represents an authority granted to an authentication object (user).
- It is implemented by the `SimpleGrantedAuthority` class, which takes a String parameter representing the role/authority name.
- Roles allocated to a user are stored as a list of `GrantedAuthority` objects.

6. **AuthenticationManager**

- The `AuthenticationManager` interface is responsible for authenticating a user during the login process.
- It performs the authentication process based on the provided credentials.
- Spring Security provides default implementations of `AuthenticationManager`, but it can also be customized as per application requirements.

7. **Configure DaoAuthenticationManager**

- The `DaoAuthenticationManager` is an implementation of `AuthenticationManager` that uses a `UserDetailsService` and `PasswordEncoder` to authenticate a user.
- It is responsible for validating the user's credentials and checking if the user exists in the database.
- The `DaoAuthenticationManager` requires a `UserDetailsService` implementation and a `PasswordEncoder` bean to be configured.

By configuring these beans and implementing the necessary classes and interfaces, we can achieve user login functionality with password encoding and authentication in Spring Security.

<br/>
<br/>

# Here's the code and explanation for each class:

1. **Add Security Dependencies in pom.xml**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Explanation:
- These dependencies are added to enable Spring Security and integrate it with Thymeleaf for web-based security features.

2. **Configure Bean Password Encoder**

```java
@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

Explanation:
- The `AppConfig` class is annotated with `@Configuration` to indicate that it contains bean configurations.
- The `BCryptPasswordEncoder` bean is created using the `passwordEncoder()` method and returned.
- This bean will be used to encode passwords before storing them in the database.

3. **Encode Password Before Save Operation**

```java
@Autowired
private BCryptPasswordEncoder passwordEncoder;

@Autowired
private UserRepository repo;

public Integer saveUser(User user) {
    String encPwd = passwordEncoder.encode(user.getUserPwd());
    user.setUserPwd(encPwd);
    return repo.save(user).getUserId();
}
// Other methods...
```

Explanation:
- The `BCryptPasswordEncoder` bean is autowired to the `UserServiceImpl` class.
- Before saving the user, the password is encoded using the `passwordEncoder` bean.
- The encoded password is then set in the user object.
- The user is saved in the repository.

4. **Security Config and Login Pages**

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
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
        http.authorizeHttpRequests(request -> request
                .antMatchers("/home", "/", "/user/**").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/customer").hasAuthority("CUSTOMER")
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/hello", true))
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
```

Explanation:
- The `SecurityConfig` class is annotated with `@Configuration` and `@EnableWebSecurity` to enable Spring Security configuration.
- The `BCryptPasswordEncoder` bean and `UserDetailsService` bean are autowired into the class.
- The `authenticationManager()` bean is created to provide the authentication manager.
- The `authenticationProvider()` bean configures the `DaoAuthenticationProvider` with the password encoder and user details service.
- The `configureAuth(HttpSecurity http)` method configures the security rules and settings.
  - It allows access to certain URLs without authentication (`/home`, `/`, `/user/**`).
  - It requires the "ADMIN" role for the `/admin` URL and the "CUSTOMER" role for the `/customer` URL.
  - It sets up form-based login with a login page (`/login`) and a default success URL (`/hello`) after successful authentication.
  - It permits all requests for the logout URL.
- The `SecurityFilterChain` is returned to complete the configuration.

Conclusion:
In this code, we added the necessary dependencies for Spring Security and Thymeleaf, configured a bean for password encoding, implemented password encoding before saving a user, and set up the security configuration for URL access control and form-based login.

These steps lay the foundation for user authentication and authorization using Spring Security, ensuring secure password storage and controlled access to different parts of the application based on user roles.

<br/>
<br/>
<br/>

Here showing the contents of the `usertab` and `roles_tab` tables in the MySQL database. Here's the breakdown of the data:

`usertab` table:
| uid | umail       | uname | upwd                                                         |
|-----|-------------|-------|--------------------------------------------------------------|
| 1   | ajay@gm.com | AJAY  | $2a$10$qZoopSxnSw1H.ZvnahqNiOqfnQFzNpSfzI2Y.BeGqioCUWhIcHwDW |

This table contains user data. There is one user with the following details:
- uid: 1
- umail: ajay@gm.com
- uname: AJAY
- upwd: The password is hashed and stored in the `upwd` column.

`roles_tab` table:
| uid | urole    |
|-----|----------|
| 1   | ADMIN    |
| 1   | CUSTOMER |

This table contains role data for users. The `uid` column refers to the user's ID in the `usertab` table, and the `urole` column represents the user's role. In this case, the user with `uid` 1 has two roles: ADMIN and CUSTOMER.

Next, you performed some database operations:
- Selected the rows from `roles_tab` where `urole` is 'CUSTOMER'. It returned the row where `urole` is 'CUSTOMER' for the user with `uid` 1.
- Committed the changes made to the database.
- Deleted the row from `roles_tab` where `urole` is 'CUSTOMER'. This removed the CUSTOMER role for the user with `uid` 1.
- Committed the changes again.

These operations modify the data in the `roles_tab` table by deleting the CUSTOMER role for the user with `uid` 1.

<br/>
<br/>

# Let's break down the code and provide a detailed explanation:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
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
        http.authorizeHttpRequests(request -> request
                .antMatchers("/home", "/", "/user/**").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/customer").hasAuthority("CUSTOMER")
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/hello", true))
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}
```

1. `@Configuration` indicates that this class is a configuration class and will provide bean definitions.
2. `@EnableWebSecurity` enables Spring Security for the application.
3. `BCryptPasswordEncoder` is autowired as a dependency, which will be used for password encoding and decoding.
4. `UserDetailsService` is autowired as a dependency, which will be used to load user details for authentication.
5. `@Bean` annotation on the `authenticationManager` method provides a bean of type `AuthenticationManager`, which is responsible for authentication.
6. `@Bean` annotation on the `authenticationProvider` method provides a bean of type `DaoAuthenticationProvider`, which is responsible for authentication using the user details service and password encoder.
7. The `configureAuth` method configures the security rules and settings using the `HttpSecurity` object.
8. The `http.authorizeHttpRequests` defines the authorization rules for different URLs. It permits access to "/home", "/", and "/user/**" for all users, requires "ADMIN" authority for "/admin", requires "CUSTOMER" authority for "/customer", and authenticates all other requests.
9. The `http.formLogin` configures the form login settings. It specifies the login page as "/login", permits access to the login page for all users, and sets the default success URL after successful login to "/hello".
10. The `http.logout` configures the logout settings and permits access to the logout URL for all users.
11. The `return http.build()` statement returns the built `SecurityFilterChain` object.

In summary, this configuration class sets up the authentication and authorization rules for the application. It configures the authentication manager, authentication provider, and defines the security rules for different URLs. It also specifies the login and logout settings. The `UserDetailsService` is responsible for loading user details, and the `BCryptPasswordEncoder` is used for password encoding and decoding.

