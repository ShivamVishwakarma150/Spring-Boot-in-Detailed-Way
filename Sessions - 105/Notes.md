# Here's a detailed explanation of filters in Spring Cloud API Gateway:

**Filters in API Gateway:**
Filters in API Gateway allow you to modify the request and response data at the gateway level. They provide a way to intercept and modify the incoming request before it is forwarded to the microservices and the outgoing response before it is sent back to the client.

There are two types of filters available in Spring Cloud API Gateway:

1. Pre-Filter:
   - A pre-filter is executed before the request is forwarded to the microservice.
   - It can add or modify data in the request headers, body, or any other request-related information.
   - Pre-filters are typically used for tasks such as authentication, request validation, or adding custom headers to the request.

2. Post-Filter:
   - A post-filter is executed after the request has been processed by the microservice and before the response is sent back to the client.
   - It can add or modify data in the response headers, body, or any other response-related information.
   - Post-filters are commonly used for tasks such as logging, response transformation, or adding custom headers to the response.

**Usage of Filters in Routing Configuration:**
Filters can be applied in the routing configuration of the API Gateway. You can use the `filters()` method to add filters to the routes. Here's an example of adding request and response headers using filters:

```java
.filters(f ->
    f.addRequestHeader("MyToken", "Basic " + UUID.randomUUID().toString())
     .addResponseHeader("Service Mode", "Active")
)
```

In this example, a pre-filter is used to add a custom request header called "MyToken" with a randomly generated UUID value. This header can be used for authentication or tracking purposes. Additionally, a post-filter is used to add a response header called "Service Mode" with the value "Active". This header can provide information about the service mode of the microservice.

By using filters, you can customize and manipulate the request and response data at the API Gateway level, allowing for additional functionality and control over the communication between clients and microservices.

**Conclusion:**
Filters in Spring Cloud API Gateway provide a flexible and powerful way to modify the request and response data at the gateway level. Pre-filters and post-filters can be used to add, modify, or remove data in the request and response headers, body, or any other request/response-related information. This enables you to implement various functionalities such as authentication, request/response transformation, logging, and more, enhancing the capabilities of your API Gateway.

<br/>
<br/>

# Here are the code changes along with their explanations for the API Gateway and CartRestController:

**1. API Gateway - MyRouteConfig:**
```java
package com.app.shivam.config;

import java.util.UUID;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRouteConfig {

    @Bean
    public RouteLocator configureRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("cartId", r -> r.path("/cart/**")
                        .filters(f -> f.addRequestHeader("MyToken", "Basic " + UUID.randomUUID().toString())
                                .addResponseHeader("Service-Mode", "Active"))
                        .uri("lb://CART-SERVICE"))
                .route("orderId", r -> r.path("/order/**").uri("lb://ORDER-SERVICE"))
                .build();
    }
}
```

**Explanation:**
In the `MyRouteConfig` class, the `configureRoutes()` method configures the routing table for the API Gateway. The `/cart/**` path is routed to the `CART-SERVICE` microservice, and a pre-filter is applied to this route. The pre-filter adds a request header named "MyToken" with a randomly generated UUID value using `UUID.randomUUID().toString()`. Additionally, a post-filter is applied to all routes and adds a response header named "Service-Mode" with the value "Active".

**2. CartRestController:**
```java
@RestController
@RequestMapping("/cart")
@RefreshScope
public class CartRestController {

    @Value("${my.app.title}")
    private String title;

    @GetMapping("/info")
    public ResponseEntity<String> showMessage(@RequestHeader("MyToken") String token) {
        return ResponseEntity.ok("WELCOME TO CART SERVICE => " + token);
    }
    // ...
}
```

**Explanation:**
In the `CartRestController` class, a new endpoint `/cart/info` is added to handle the GET request. The `@RequestHeader("MyToken")` annotation is used to retrieve the value of the "MyToken" header from the request. This value is then included in the response along with a welcome message. The `@Value("${my.app.title}")` annotation is used to inject the value of the `my.app.title` property from the configuration.

**Conclusion:**
The API Gateway configuration in the `MyRouteConfig` class adds filters to the `/cart/**` route, which modify the request and response headers. The pre-filter adds the "MyToken" header with a random UUID value, and the post-filter adds the "Service-Mode" header with the value "Active". The CartRestController includes an endpoint `/cart/info` that retrieves the "MyToken" header value from the request and includes it in the response along with a welcome message. These changes demonstrate how filters can be used in the API Gateway to modify request and response data.

<br/>
<br/>

# Let's go through the execution order and the YAML configuration for the API Gateway:

**Execution Order:**

1. Start the Config Server, Eureka Server, and Admin Server by accessing their respective URLs: 
   - Config Server: http://localhost:8888/actuator/refresh
   - Eureka Server: http://localhost:8761
   - Admin Server: http://localhost:9999/applications

2. Start multiple instances of the Cart and Order microservices:
   - Cart MS# - Run on ports 8081, 8082, and 8084 (3 instances)
   - Order MS# - Run on ports 9091, 9092, and 9094 (3 instances)

3. Start the API Gateway once.

4. Access the URL `http://192.168.0.2/cart/info` to make a request to the API Gateway. The IP and PORT of the API Gateway are `192.168.0.2:80`.

5. Check the IP configuration using the appropriate command based on your operating system:
   - Windows: `ipconfig`
   - Linux/Mac: `ifconfig` or `ip addr show`

**YAML Configuration for API Gateway:**

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
server:
  port: 80
spring:
  application:
    name: API-GATEWAY
  cloud:
    gateway:
      routes:
      - id: cartId
        uri: lb://CART-SERVICE
        predicates:
        - Path=/cart/**
        filters:
        - AddRequestHeader=MyToken,BasicTEST
        - AddResponseHeader=Service-Mode, Active
      - id: orderId
        uri: lb://ORDER-SERVICE
        predicates:
        - Path=/order/**
```

**Explanation:**

In the YAML configuration for the API Gateway, we define the following:

- `eureka.client.service-url.defaultZone`: Specifies the URL of the Eureka Server to register with.
- `server.port`: Sets the port of the API Gateway to 80.
- `spring.application.name`: Sets the name of the API Gateway application to "API-GATEWAY".
- `spring.cloud.gateway.routes`: Defines the routes for the API Gateway.

  - `cartId`: Represents a route with the ID "cartId" that matches requests with the path "/cart/**". It forwards the requests to the "CART-SERVICE" microservice using load balancing.
    - `predicates`: Uses the "Path" predicate to match requests with the "/cart/**" path.
    - `filters`: Adds two filters to the route. The "AddRequestHeader" filter adds a request header named "MyToken" with the value "BasicTEST", and the "AddResponseHeader" filter adds a response header named "Service-Mode" with the value "Active".
    
  - `orderId`: Represents a route with the ID "orderId" that matches requests with the path "/order/**". It forwards the requests to the "ORDER-SERVICE" microservice using load balancing.
    - `predicates`: Uses the "Path" predicate to match requests with the "/order/**" path.

**Conclusion:**

The provided execution order outlines the steps to start the Config Server, Eureka Server, Admin Server, multiple instances of the microservices, and the API Gateway. The YAML configuration demonstrates how to configure the API Gateway using Spring Cloud Gateway. It defines routes for "/cart/**" and "/order/**", applies filters to modify the request and response headers, and uses Eureka for service discovery.

