# Here's a detailed explanation of each point you mentioned regarding the Spring Cloud API Gateway:

**1. Routing:**
- Routing in the API Gateway is the process of mapping an incoming request to a microservice (MS#) based on the requested path.
- The routing table, which contains the mappings between paths and service URIs, needs to be configured by the programmer.
- Each entry in the routing table consists of an ID, PATH, and URI.
- The PATH represents the requested path pattern, and the URI specifies the service or load balancer to which the request should be forwarded.
- The URI can be specified as a service ID (using `lb://` prefix) or as the direct IP:PORT or hostname of the target service.
- For example, the `/cart/**` path is mapped to the `CART-SERVICE` using load balancing.

**2. Load Balancing:**
- Load balancing is an essential feature of the API Gateway that distributes incoming requests to multiple instances of a microservice.
- When a request arrives at the API Gateway, it selects one instance of the target microservice based on a load-balancing algorithm (such as Round Robin) that considers the instances' load factors.
- The load factor (LF) represents the current load or capacity of each microservice instance.
- By distributing requests among multiple instances, load balancing helps in achieving scalability, high availability, and efficient resource utilization.

**3. Filters:**
- Filters in the API Gateway allow for modifying the request and response in various ways.
- Filters can be applied at different stages of the request processing pipeline, such as pre-processing (pre-filters) and post-processing (post-filters).
- Pre-filters can modify the request before it is forwarded to the microservice, enabling actions like request authentication, encryption, or header modification.
- Post-filters can modify the response returned by the microservice before sending it back to the client, allowing actions like response transformation or adding custom headers.

**Routing Table and Routes:**
- The routing table in the API Gateway is a collection of routes.
- Each route represents a mapping between a specific path and a microservice instance.
- The routing table can be dynamically updated as microservices register or unregister with Eureka (the service discovery component).
- Each route consists of an ID, PATH, URI, and optionally one or more filters.
- An ID uniquely identifies each route.
- The PATH specifies the request path pattern that matches the route.
- The URI represents the target service or load balancer where the request should be forwarded.
- The filters associated with a route can modify the request or response as needed.

**Eureka Service Instances:**
- Eureka is a service discovery component in Spring Cloud that maintains a registry of available microservices.
- Each microservice instance registers itself with Eureka, providing information such as service ID, instance ID, IP address, port, and load factor.
- The API Gateway uses the Eureka registry to retrieve the available instances of microservices when routing requests.

**Importance of Configuration:**
- The routing table must be configured by the programmer to define how requests are routed to microservices.
- If a microservice is not configured in the API Gateway's routing table, it cannot be accessed from the outside world through the gateway.
- This provides control and security by allowing only the configured microservices to be accessible.

By understanding the concepts of routing, load balancing, and filters in the API Gateway, developers can effectively route incoming requests to the appropriate microservices, balance the load across instances, and modify the requests and responses as required. The configuration of the routing table ensures that only the configured microservices can be accessed through the gateway, providing control and security.

<br/>
<br/>

# Here's the breakdown of the code you provided:

**Step #1: Define Application for API Gateway with Dependencies**

```java
// Application.java

package com.app.shivam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

- In this step, you define the main application class for the API Gateway.
- It is annotated with `@SpringBootApplication` to enable Spring Boot auto-configuration.
- `@EnableDiscoveryClient` is used to enable service registration and discovery with Eureka.

**Step #2: Configuration in application.properties**

```
# application.properties

server.port=80
spring.application.name=API-GATEWAY

# Register with Eureka
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

- In this step, you configure the properties for the API Gateway application.
- `server.port` defines the port on which the API Gateway application will run.
- `spring.application.name` sets the name of the application to be registered with Eureka.
- `eureka.client.service-url.defaultZone` specifies the URL of the Eureka server for service registration and discovery.

**Step #3: Define Java Configuration for Routing Table**

```java
// MyRouteConfig.java

package com.app.shivam.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRouteConfig {

    @Bean
    public RouteLocator configureRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("cartId", r -> r.path("/cart/**").uri("lb://CART-SERVICE"))
                .route("orderId", r -> r.path("/order/**").uri("lb://ORDER-SERVICE"))
                .build();
    }

}
```

- In this step, you define the Java configuration class for configuring the routing table.
- The class is annotated with `@Configuration` to indicate that it provides bean configurations.
- The `configureRoutes` method is annotated with `@Bean` and returns a `RouteLocator` object.
- The `RouteLocatorBuilder` is used to build the routing table.
- Two routes are defined using the `route` method:
  - The "cartId" route maps requests with "/cart/**" path to the "CART-SERVICE" using load balancing.
  - The "orderId" route maps requests with "/order/**" path to the "ORDER-SERVICE" using load balancing.

**Conclusion:**
By following the provided steps, you can set up an API Gateway application with Spring Cloud Gateway and Eureka Discovery Client dependencies. The application is registered with Eureka for service registration and discovery. The routing table is configured using the Java configuration class, which defines routes based on the requested paths and maps them to the corresponding microservices using load balancing.

<br/>
<br/>

# Here's a detailed explanation of the execution order and the steps involved:

**Step 1: Config Server, Eureka Server, and Admin Server**
- Config Server: The Config Server is responsible for serving configuration properties to other applications. It runs on port 8888.
- Eureka Server: The Eureka Server provides service registration and discovery capabilities. It runs on port 8761.
- Admin Server: The Admin Server is used for monitoring and managing the applications. It runs on port 9999.

You can access the following URLs to verify the status and functionality of each server:
- Config Server: `http://localhost:8888/actuator/refresh`
- Eureka Server: `http://localhost:8761`
- Admin Server: `http://localhost:9999/applications`

**Step 2: Multiple Instances of MS# Apps (Cart, Order)**
- The Cart MS# app is started on ports 8081, 8082, and 8084 (3 instances).
- The Order MS# app is started on ports 9091, 9092, and 9094 (3 instances).

These multiple instances allow for load balancing and high availability of the microservices.

**Step 3: Start the API Gateway**
- The API Gateway is started once and listens on port 80.
- The IP and PORT of the API Gateway are `192.168.0.3:80`.

**Step 4: Accessing the API Gateway**
- To access the API Gateway, you can use URLs like:
  - `http://192.168.76.116:9600/cart/find/101`
  - `http://192.168.76.116:9600/order/fetch/33`

These URLs are routed through the API Gateway, and based on the path ("/cart/**" or "/order/**"), the requests are forwarded to the corresponding microservices using load balancing.

**Additional Information:**
- To find the IP address of your machine, you can use the following commands:
  - Windows: `ipconfig`
  - Linux/Unix: `ifconfig` or `ip addr show`

**Conclusion:**
By following the mentioned execution order, you can ensure the proper functioning of the Config Server, Eureka Server, Admin Server, multiple instances of the MS# apps (Cart, Order), and the API Gateway. This setup allows for centralized configuration, service registration and discovery, monitoring, load balancing, and routing requests to the appropriate microservices.
