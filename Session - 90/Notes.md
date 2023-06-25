# **[FeignClient]**

1. **Feign Client**, also known as Open Feign, is a powerful library and a third-party API that is integrated with Spring Cloud. It provides a higher-level and more declarative approach to consume RESTful web services in microservice architectures.

2. As a third-party API integrated with Spring Cloud, **Feign Client** offers additional functionality and features beyond what is provided by the core Spring Cloud components. It simplifies the process of building RESTful service clients and improves the overall developer experience.

3. One of the key features of **Feign Client** is its ability to generate code at runtime using dynamic proxy classes. This means that you don't have to write the implementation code manually; instead, Feign Client dynamically generates the code based on the provided interface and configuration.

4. To use **Feign Client**, a programmer needs to provide the following information:

   a. **Interface with abstract methods**: The programmer defines an interface with abstract methods that represent the desired RESTful endpoints. These methods declare the request and response types and the details of the communication.

   b. **Endpoint details**: The programmer specifies the endpoint details such as the path, HTTP methods, and the service ID of the provider application. This information allows Feign Client to locate and communicate with the appropriate instances of the target microservice.

   c. **Request/Response Entities/Beans**: In Feign Client, the programmer may need to redefine the request and response entities or beans that are used for communication between the client and the provider. This allows for customization and mapping of the data exchanged between the services. This step can involve some duplication of code or boilerplate code, but it provides flexibility and control over the communication process.

5. One of the significant advantages of using **Feign Client** is that you don't need to use RestTemplate explicitly. RestTemplate is a core Spring component for making HTTP requests, but Feign Client handles the underlying HTTP calls for you. Additionally, Feign Client internally leverages the LoadBalancerClient code provided by Spring Cloud to handle client-side load balancing and service discovery seamlessly.

By using **Feign Client**, you can simplify and streamline the process of communication between microservices. It eliminates the need for manual configuration of RestTemplate, load balancing, and HTTP calls. The dynamic code generation and integration with Spring Cloud components make Feign Client a convenient and efficient choice for building RESTful service clients.

<br/>
<br/>
<br/>

# **Code with Explanation**

**Eureka Server**

The Eureka Server is responsible for service discovery in a microservices architecture. It allows services to register themselves and discover other services registered with it.

Here's the code for the Eureka Server:

```java
// SpringCloudEurekaServer.java
@EnableEurekaServer
public class SpringCloudEurekaServer {
    // Main class for Eureka Server
    // No code implementation required
}
```

Explanation:
- `@EnableEurekaServer` is an annotation used to enable the Eureka Server functionality.

**application.properties**

```properties
# Recommended Port Number
server.port=8761

# Disable Self Register
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

Explanation:
- The `server.port` property sets the port number for the Eureka Server (recommended port 8761).
- The `eureka.client.register-with-eureka` property is set to `false` to disable self-registration of the Eureka Server.
- The `eureka.client.fetch-registry` property is set to `false` to disable fetching the registry information from the Eureka Server.

Next, let's move on to the CartService.

**CartService**

The CartService is a microservice responsible for managing a user's shopping cart. It acts as a Eureka client and registers itself with the Eureka Server for service discovery.

Here's the code for the CartService:

```java
// SpringCloudCartService.java
@EnableEurekaClient
public class SpringCloudCartService {
    // Main class for CartService
    // No code implementation required
}
```

Explanation:
- `@EnableEurekaClient` is an annotation used to enable the Eureka client functionality for the CartService.

**application.properties**

```properties
# Port
server.port=8081

# ServiceId (app Name)
spring.application.name=CART-SERVICE

# Provide Eureka location
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

# Generating Instance ID
eureka.instance.instance-id=${spring.application.name}:${random.value}
```

Explanation:
- The `server.port` property sets the port number for the CartService (recommended port 8081).
- The `spring.application.name` property sets the service name as "CART-SERVICE" for identification in the Eureka Server.
- The `eureka.client.service-url.defaultZone` property provides the location of the Eureka Server.
- The `eureka.instance.instance-id` property generates a unique instance ID for the CartService.

**Cart Entity**

```java
package com.app.shivam.entity;

import lombok.Data;

@Data
public class Cart {
    private Integer cartId;
    private String cartCode;
    private Double cartCost;
}
```

Explanation:
- The `Cart` class represents a shopping cart with properties like `cartId`, `cartCode`, and `cartCost`.
- The `@Data` annotation from Lombok library generates getter, setter, and other utility methods automatically.

**CartRestController**

```java
package com.app.shivam.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.shivam.entity.Cart;

@RestController
@RequestMapping("/cart")
public class CartRestController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/info")
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("WELCOME TO CART SERVICE =>" + port);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Integer id) {
        Cart cart = new Cart();
        cart.setCartId(id);
        cart.setCartCost(2300.0);
        cart.setCartCode("TEST");
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
        return ResponseEntity.ok("ADDED TO CART => " + cart);
    }
}
```

Explanation:
- The `CartRestController` class defines REST endpoints for the CartService.
- The `showMessage()` method returns a welcome message along with the port number to indicate successful service invocation.
- The `getCartById()` method retrieves a cart based on the provided ID and returns it in the response.
- The `addToCart()` method adds an item to the cart and returns a success message.

Now, let's move on to the OrderService.

**OrderService**

The OrderService is a microservice responsible for handling order-related operations. It acts as a Eureka client and uses the Feign client to communicate with the CartService.

Here's the code for the OrderService:

```java
// SpringCloudOrderService.java
@EnableEurekaClient
@EnableFeignClients
public class SpringCloudOrderService {
    // Main class for OrderService
    // No code implementation required
}
```

Explanation:
- `@EnableEurekaClient` is an annotation used to enable the Eureka client functionality for the OrderService.
- `@EnableFeignClients` is an annotation used to enable the Feign client functionality for the OrderService.

**application.properties**

```properties
# Port
server.port=9091

# ServiceId (app Name)
spring.application.name=ORDER-SERVICE

# Provide Eureka location
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

Explanation:
- The `server.port` property sets the port number for the OrderService (recommended port 9091).
- The `spring.application.name` property sets the service name as "ORDER-SERVICE" for identification in the Eureka Server.
- The `eureka.client.service-url.defaultZone` property provides the location of the Eureka Server.

**CatConsumerFeign:**
 
```java
// CatConsumerFeign
package com.app.shivam.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.shivam.entity.Cart;

@FeignClient("CART-SERVICE")
public interface CatConsumerFeign {
    @GetMapping("/cart/info")
    public ResponseEntity<String> showMessage();

    @GetMapping("/cart/find/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Integer id);

    @PostMapping("/cart/create")
    public ResponseEntity<String> addToCart(@RequestBody Cart cart);
}
```
Explanation:
- `CatConsumerFeign` is a Feign client interface used to communicate with the CartService.
- The `@FeignClient("CART-SERVICE")` annotation specifies the name of the target service to communicate with, which is "CART-SERVICE" in this case.
- It defines three methods corresponding to the endpoints of the CartService:
  - `showMessage()` is a GET request to retrieve a message from the CartService.
  - `getCartById(Integer id)` is a GET request to retrieve a specific cart based on the provided ID.
  - `addToCart(Cart cart)` is a POST request to add a new cart item to the CartService.


**OrderRestController**

```java
package com.app.shivam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.shivam.consumer.CatConsumerFeign;
import com.app.shivam.entity.Cart;

@RestController
@RequestMapping("/order")
public class OrderRestController {
    @Autowired
    private CatConsumerFeign consumer;

    @GetMapping("/place")
    public ResponseEntity<String> placeOrder() {
        String cartResp = consumer.showMessage().getBody();
        return ResponseEntity.ok("ORDER PLACED WITH => " + cartResp);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<String> fetchOrderWithCart(@PathVariable("id") Integer id) {
        Cart cartData = consumer.getCartById(id).getBody();
        return ResponseEntity.ok("ORDER WITH CART DATA => " + cartData);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
        String cartResp = consumer.addToCart(cart).getBody();
        return ResponseEntity.ok("ORDER WITH => " + cartResp);
    }
}
```

Explanation:
- The `OrderRestController` class defines REST endpoints for the OrderService.
- The `placeOrder()` method invokes the `showMessage()` method of the CartService using the Feign client and returns the response.
- The `fetchOrderWithCart()` method retrieves an order along with its associated cart data based on the provided ID and returns it in the response.
- The `addToCart()` method adds an item to the cart by invoking the `addToCart()` method of the CartService using the Feign client and returns the response.
  



**Conclusion**

In this example, we have three projects: Eureka Server, CartService, and OrderService.
- The Eureka Server enables service discovery.
- The CartService manages shopping carts and communicates with the Eureka Server.
- The OrderService handles order-related operations and communicates with the CartService using the Feign client.

These microservices utilize Spring Cloud and Eureka for service discovery and communication, allowing them to work together seamlessly in a distributed system.


To execute the code, follow these steps:

1. Start the Eureka Server.
2. Start three instances of the Cart Service, each with a different port number.
3. Start one instance of the Order Service.
4. Open your web browser and go to http://localhost:8761 to access the Eureka Server dashboard.
5. Click on the link for the Order Service, which should be listed on the Eureka dashboard.
   It may look like http://localhost:9091/actuator/info.
6. Modify the full URL of the Cart Service in the Order Service code to match the URL of the Cart Service instance you want to communicate with.
   For example: http://localhost:9091/order/place
   This ensures that the Order Service can communicate with the appropriate Cart Service instance.

Execution Examples:

Example 1:
Make a GET request to http://localhost:9091/order/place.
This will place an order and return the combined response from the Order Service and the Cart Service.
Output: "ORDER PLACED WITH => WELCOME TO CART SERVICE =>8083"

Example 2:
Make a GET request to http://localhost:9091/order/fetch/1190.
This will fetch an order along with its associated cart data based on the provided ID.
Output: The order details with the associated cart data.

Example 3:
Make a POST request to http://localhost:9091/order/addToCart with the following JSON body:
```json
{
    "cartId": 1019,
    "cartCode": "AA",
    "cartCost": 9900.0
}
```
This will add the provided cart to the order.
Output: The order details with the updated cart information.

By following these steps and executing the examples, you can interact with the Cart Service and Order Service to perform operations such as placing orders, fetching order details with cart data, and adding items to the cart.