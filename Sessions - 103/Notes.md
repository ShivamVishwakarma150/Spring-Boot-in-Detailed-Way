**API Gateway in Spring Cloud:**

The API Gateway serves as a single entry and exit point for our microservices (MS#) application. It provides a centralized access point for clients to interact with the various microservices within the system.

Key Points:

1. Multiple Microservices Instances: In a microservices architecture, there can be multiple instances of microservices running to handle the workload and ensure scalability. These instances can be distributed across different servers or containers.

2. Eureka Server: The details of all the running microservice instances are registered and stored in the Eureka Server. The Eureka Server acts as a registry or directory that keeps track of the available microservices and their locations.

3. Eureka Server Role: The Eureka Server is responsible for storing the information of microservices, such as their IP addresses, port numbers, and service names. It does not make any HTTP calls to the microservices themselves.

4. Gateway as Single Entry and Exit Point: Exposing all the IP addresses and port numbers of the individual microservices directly to clients is not practical. Instead, clients interact with the API Gateway, which acts as a single entry and exit point for the entire microservices system.

5. Gateway IP and Port: The API Gateway has a single IP address and port number that clients use to communicate with the microservices. Clients send their requests to the gateway, which then routes the requests to the appropriate microservices based on the request path or other defined rules.

6. Client-Facing Interface: The API Gateway provides a client-facing interface where clients can send their requests. It abstracts the internal details of the microservices and provides a unified API for clients to access different functionalities.

By using an API Gateway, clients do not need to be aware of the underlying microservices or their locations. The gateway handles the routing and forwarding of requests to the appropriate microservices based on the configured rules or routing table.

In summary, the API Gateway plays a crucial role in managing the entry and exit points for a microservices architecture. It simplifies the interaction between clients and microservices by providing a centralized access point and abstracting the complexities of the underlying system. The Eureka Server acts as a registry for the microservices, while the API Gateway ensures a streamlined and controlled access mechanism for clients.

<br/>
<br/>

**1. Routing:**
In a microservices architecture, the gateway acts as a router for client requests. It receives incoming requests and dispatches them to the appropriate microservice application based on the request's path, headers, or other criteria. The routing functionality allows clients to communicate with different microservices through a single entry point, simplifying the client-side configuration.

**2. Load Balancing:**
As part of its responsibilities, the gateway performs load balancing across the instances of microservices registered with the Eureka Server. It uses load balancing algorithms, such as round-robin or weighted round-robin, to distribute incoming requests evenly among the available instances. This ensures optimal resource utilization and scalability of the microservices.

For every incoming request, the gateway selects the most suitable instance based on the current load factor or other metrics obtained from the Eureka Server.

**3. Filters:**
The gateway supports various filters that can modify the request or response details. Filters are implemented as pre-filters and post-filters.

- Pre-filters: These filters intercept the incoming request before it is forwarded to the microservice. They can perform tasks such as request validation, authentication, authorization, request transformation, or encryption. Pre-filters help enforce security measures and ensure the incoming request meets the required criteria.

- Post-filters: These filters intercept the outgoing response from the microservice before it is sent back to the client. They can modify the response, add additional headers, transform the data format, or perform other post-processing tasks. Post-filters enable customization and manipulation of the response according to the gateway's requirements or the client's expectations.

**Dynamic Routing | Dynamic Dispatching:**
Dynamic routing refers to the ability of the gateway to dynamically determine the destination microservice based on the incoming request. This includes both routing and load balancing aspects.

- Routing Table: The gateway requires a routing table, which is configured by developers, to determine the appropriate microservice for a given request. The routing table typically maps request paths or patterns to specific microservices or service IDs.

- Predicate: The gateway uses predicates to compare the request path (or other criteria) with the entries in the routing table. If a predicate matches, it indicates which microservice should handle the request. Predicates play a vital role in dynamic dispatching, allowing the gateway to make intelligent routing decisions based on the request's attributes.

- Pre-Filter and Post-Filter: In addition to routing and load balancing, the gateway's pre-filters and post-filters can be used to modify the request and response details. These filters provide a way to customize the behavior of the gateway and apply additional processing logic before forwarding the request to the microservice or sending the response back to the client.

**Gateway as a Microservice:**
The gateway itself is considered a microservice and needs to be registered with the Eureka Server. It is responsible for handling incoming requests and forwarding them to the appropriate microservices. To communicate with the microservices, the gateway uses a proxy client, such as Feign, which is generated specifically for the gateway. This allows the gateway to make requests to the registered microservices using a simplified and streamlined approach.

The combination of routing, load balancing, filters, dynamic dispatching, and gateway-as-a-microservice capabilities make the API gateway an essential component in a microservices architecture. It provides a unified entry point for clients, simplifies client-side configuration, ensures proper load distribution, and allows for flexible customization and processing of requests and responses.

<br/>
<br/>

# Spring Cloud API Gateway Routing(or Config) can be defined in two ways
**1. YAML/Properties file:**
In Spring Cloud API Gateway, routing or configuration can be defined using a YAML or properties file. This approach allows you to configure the routing rules in a separate configuration file without modifying the source code. The YAML or properties file typically contains a set of routes defined by their paths and corresponding URIs.

**2. Java-based Config:**
Another way to define routing in Spring Cloud API Gateway is through Java-based configuration. Instead of using a YAML or properties file, you can create a configuration class in Java that defines the routing rules programmatically. This approach provides more flexibility and allows for dynamic routing based on runtime conditions or custom logic.

Example Routing Table:
```
PATH             URI
/order/**        lb://ORDER-SERVICE
/cart/**         lb://CART-SERVICE
```

In the example routing table above, we have defined two routes:
1. If the request URL contains `/order` in the path, such as `http://localhost:80/order/find/10`, the API Gateway will select the `ORDER-SERVICE` based on load balancing and fetch one instance of the `ORDER-SERVICE` from the Eureka Server. It will then forward the request to the selected instance for the path `/order/find/10`.

2. If the request URL contains `/cart` in the path, such as `http://localhost:80/cart/update/5`, the API Gateway will select the `CART-SERVICE` based on load balancing and fetch one instance of the `CART-SERVICE` from the Eureka Server. It will then forward the request to the selected instance for the path `/cart/update/5`.

These routing rules allow the API Gateway to route incoming requests to the appropriate microservice based on the requested path.

By defining the routing rules in either a YAML/properties file or Java-based configuration, you can easily configure the behavior of the API Gateway without modifying the source code. This separation of routing configuration from the application code provides flexibility and maintainability, allowing you to modify the routing rules as needed without recompiling or redeploying the application.

<br/>
<br/>

# Here's the breakdown of the code and explanation for each part:

**1. Config class for Routing table:**
```java
package com.app.shivam.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRouteConfig {

	@Bean
	public RouteLocator configRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
			.route("cartRoutingId", r -> r.path("/cart/**").uri("lb://CART-SERVICE"))
			.route("orderRoutingId", r -> r.path("/order/**").uri("lb://ORDER-SERVICE"))
			.build();
	}
}
```
Explanation:
- This is a Java configuration class `MyRouteConfig` responsible for defining the routing table.
- It is annotated with `@Configuration` to indicate that it provides configuration for the application.
- The `configRoutes` method is annotated with `@Bean` and returns a `RouteLocator` object.
- `RouteLocatorBuilder` is used to define the routing rules using a builder pattern.
- In this example, two routes are defined:
  - The `cartRoutingId` route maps requests with a path starting with `/cart/**` to the `CART-SERVICE` using load balancing.
  - The `orderRoutingId` route maps requests with a path starting with `/order/**` to the `ORDER-SERVICE` using load balancing.
- The routes are built using the `builder.routes().route(...)` syntax.
- Finally, the routing table is built and returned.

**2. Properties file:**
```
#server.port=80
server.port=9600

# Register with Eureka
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```
Explanation:
- This is a sample properties file where additional configurations for the application are specified.
- The `server.port` property sets the port for the API Gateway to listen on. In this example, it is set to `9600`.
- The `eureka.client.service-url.defaultZone` property specifies the URL for the Eureka Server, where the API Gateway will register itself for service discovery. In this example, it is set to `http://localhost:8761/eureka`.

**Conclusion:**
In this example, we have a Java configuration class `MyRouteConfig` that defines the routing table using `RouteLocatorBuilder`. Two routes are configured to route requests starting with `/cart/**` and `/order/**` to the corresponding services using load balancing. The properties file specifies the port for the API Gateway and the Eureka Server URL for service discovery. By combining the Java configuration and properties file, the API Gateway can effectively route incoming requests to the appropriate microservices based on the defined routing rules.
