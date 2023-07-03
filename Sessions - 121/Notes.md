# Registering Client with OAuth

1. Registering a Client with Auth Server (Facebook in this case):
   - Go to https://developers.facebook.com/.
   - Click on MyApps and choose Consumer, then click Next.
   - Enter the app name and click Next.
   - Click on the App name (top left corner) and create a test app. Finish the setup.
   - Click on Settings and then Basics. Copy the client-id and secret.

2. Application Configuration (application.yml):
   ```yaml
   spring:
     security:
       oauth2:
         client:
           registration:
             facebook:
               client-id: 838612877842728
               client-secret: a02c4ee2148d1a519e4b896ed02b4c0e
   ```

3. Security Configuration (SecurityConfig.java):
   ```java
   package com.app.shivam.config;

   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.security.config.annotation.web.builders.HttpSecurity;
   import org.springframework.security.web.SecurityFilterChain;

   @Configuration
   public class SecurityConfig {

       // authentication ...

       // authorization
       @Bean
       public SecurityFilterChain configure(HttpSecurity http) throws Exception {
           http.antMatcher("/**")
               .authorizeRequests()
               .antMatchers("/", "/login**").permitAll()
               .anyRequest().authenticated()
               .and()
               .oauth2Login();

           return http.build();
       }
   }
   ```

4. User Controller (UserController.java):
   ```java
   package com.app.shivam.rest;

   import java.security.Principal;

   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.GetMapping;

   @Controller
   public class UserController {

       @GetMapping("/secure")
       public String showHome(Principal p) {
           System.out.println(p);
           return "Secure";
       }

       @GetMapping("/")
       public String showLogin() {
           return "Index";
       }
   }
   ```

5. Index HTML (Index.html):
   ```html
   <html xmlns:th="https://www.thymeleaf.org/">
   <head>
       <title>WELCOME TO LOGIN</title>
   </head>
   <body>
       <h2>Spring Security using OAuth2.x</h2>
       <a href="secure">Login</a>
   </body>
   </html>
   ```

6. Secure HTML (Secure.html):
   ```html
   <html xmlns:th="https://www.thymeleaf.org/">
   <head>
       <title>WELCOME TO LOGIN</title>
   </head>
   <body>
       <h2>WELCOME</h2>
       <span th:text="${#authentication.name}"></span>
   </body>
   </html>
   ```

Explanation:
1. The code demonstrates the registration process with Facebook as an authentication provider.
2. The `application.yml` file contains the OAuth2 client configuration for Facebook, including the client ID and client secret obtained during registration.
3. The `SecurityConfig` class is responsible for configuring security settings. In this case, it enables OAuth2 login and sets up authorization rules.
4. The `UserController` class defines two endpoints: `/secure` and `/`. The `/secure` endpoint requires authentication, and the `/` endpoint displays the login page.
5. The `Index.html` file represents the login page, which includes a link to the `/secure` endpoint.
6. The `Secure.html` file is the secure page displayed after successful authentication, showing

 a welcome message with the authenticated user's name.

Conclusion:
The provided code demonstrates the configuration and setup of a Spring Security application using OAuth2 with Facebook as the authentication provider. The `SecurityConfig` class defines the authorization rules, and the `UserController` class handles the endpoints for login and the secure page. The HTML files represent the login and secure pages.