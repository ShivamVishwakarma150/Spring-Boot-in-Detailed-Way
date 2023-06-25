
# **MS# Communication:**
In microservices architecture, when one microservice (MS#) needs to send a request and receive a response from another microservice, it is referred to as microservices communication. This communication is essential for building complex systems by combining the functionalities of different microservices.

**All MS# are internally Spring Boot REST apps. So, we can use RestTemplate. But hardcoding the URL of MS# is not a good approach.**

In microservices architecture, each microservice is typically implemented as a Spring Boot application, exposing its functionality through REST APIs. One way to communicate between microservices is by using the RestTemplate class provided by Spring.

However, hardcoding the URL of the target microservice in the RestTemplate code is not considered a good practice due to the following reasons:

1. Dynamic Infrastructure: Microservices instances may be running on different systems or containers with different IP addresses. Hardcoding a specific IP address or hostname would lead to a tightly coupled architecture, making it difficult to scale or deploy microservices flexibly.

2. Load Balancing: In order to distribute the load and improve performance, multiple instances of a microservice may be created and deployed. Hardcoding a single URL would bypass the load balancing mechanism and always target a specific instance, potentially leading to uneven distribution of requests and affecting scalability and fault tolerance.

To address these challenges, Spring Cloud provides a solution by integrating with service discovery and load balancing mechanisms.

Service discovery allows microservices to register themselves with a registry server (e.g., Eureka) and obtain a unique identifier. Each microservice instance registers its own location (IP and port) with the registry server.

Load balancing mechanisms, such as Ribbon, provided by Spring Cloud, can then be used to dynamically select an available instance from the registered microservices.

By leveraging service discovery and load balancing, microservices can communicate without the need for hardcoded URLs. Instead, the communication is facilitated through the service names, and the underlying load balancer resolves the appropriate instance at runtime.

This approach provides flexibility, scalability, and fault tolerance in the microservices architecture, as the communication can adapt to the dynamic nature of the infrastructure and handle load balancing efficiently.

Certainly! Here's the response with headings included:

**Microservices Communication**

*MS# Communication: If one MS# wants to send a request and get a response from another MS#, then it is called MS# communication.*

*All MS# are internally Spring Boot REST apps. So, we can use RestTemplate. But hardcoding the URL of MS# is not a good approach.*

**Why?**

-> MS# instances may run on different systems (IP may be different in real-time)
-> MS# multiple instances may be created for Load Balancing.

**Spring Cloud Client APIs for Microservices Communication**

Spring Cloud has provided client APIs for facilitating communication between microservices. These APIs help in linking two MS# apps for communication purposes. Let's explore them:

**1. DiscoveryClient (Legacy):**

The DiscoveryClient is a legacy client API provided by Spring Cloud. It allows microservices to interact with a service registry (such as Eureka or Consul) and obtain information about registered services. The DiscoveryClient provides a list of ServiceInstance objects, which contain details about each instance of a specific service, including its hostname, port, and other metadata. Microservices can use this information to communicate with other microservices directly.

However, using the DiscoveryClient directly for service-to-service communication requires additional manual implementation, such as load balancing and handling failover scenarios. Therefore, Spring Cloud provides more advanced client APIs to simplify these tasks.

**2. LoadBalancerClient:**

The LoadBalancerClient is a client API provided by Spring Cloud that builds upon the DiscoveryClient. It enhances the service discovery capabilities by incorporating load balancing algorithms. Instead of dealing directly with the ServiceInstance list, the LoadBalancerClient abstracts the process of selecting an appropriate instance from the available instances based on a load balancing strategy.

The LoadBalancerClient encapsulates the logic to choose an instance for a particular request, considering factors like load balancing algorithms (e.g., round-robin, weighted load balancing) and the health status of instances. This allows microservices to communicate with other services through the LoadBalancerClient, which handles the selection of a suitable instance transparently.

**3. Feign Client (OpenFeign):**

Feign is a declarative web service client developed by Netflix and integrated into Spring Cloud as Feign Client. It provides a higher-level abstraction for building RESTful web service clients. With Feign, you can define an interface and annotate its methods with annotations that describe the HTTP request details, such as the URL, headers, request parameters, and body.

The Feign Client simplifies the communication between microservices by generating the necessary REST API calls based on the annotated interface. Under the hood, it utilizes the LoadBalancerClient for service discovery and load balancing. Feign abstracts away much of the boilerplate code required to communicate with RESTful services, making the codebase cleaner and more maintainable.

By using the Feign Client, you can write client code that looks similar to writing a local method call, abstracting away the intricacies of service discovery, load balancing, and constructing HTTP requests.

Overall, these client APIs provided by Spring Cloud simplify and streamline the process of microservices communication by abstracting away the complexities of service discovery, load balancing, and REST API invocations. They provide higher-level abstractions and integrations with other Spring Cloud components to enhance the developer experience and improve the resilience and scalability of microservices-based systems.

<br/>
<br/>

# **[DiscoveryClient]**

The DiscoveryClient is a client API provided by Spring Cloud that allows microservices to fetch details from the service registry (such as Eureka or Consul) based on the ServiceId (application name) of the client microservice. It provides a convenient way to obtain information about registered services and their instances.

**Usage of DiscoveryClient#getInstances(serviceId)**

When a microservice needs to communicate with another microservice, it can use the DiscoveryClient's `getInstances(serviceId)` method to retrieve the instances of the desired service. The result of this method is a list of ServiceInstance objects.

**ServiceInstance and its details**

Each ServiceInstance represents the details of a specific instance of a microservice. It contains information such as the serviceId (application name), instanceId (a unique identifier for the instance), the URI (combination of HOST and PORT), load factor (LF), and other metadata.

**Handling single instance vs. multiple instances**

If the client microservice is running only once (a single instance), the result of `getInstances(serviceId)` will be a List with only one ServiceInstance object at index #0. In this case, you can directly access the ServiceInstance and read its URI (IP+PORT). Then, you can create the URL by adding the fixed path (which is determined by the code logic).

**Making a request using RestTemplate**

Once you have obtained the URL, you can pass it to the RestTemplate to make the HTTP request to the consumer microservice. The RestTemplate is a Spring class that simplifies making HTTP requests and handling responses. You can use it to send the request to the specified URL and get a response in the form of a ResponseEntity<T>.

By utilizing the DiscoveryClient and fetching the ServiceInstance details, microservices can dynamically discover and communicate with other services without hardcoding specific URLs. This allows for a more flexible and scalable architecture, as the service instances may run on different systems and multiple instances can be created for load balancing purposes.

<br/>
<br/>

**Q) Why DiscoveryClient?**<br/>
A) The DiscoveryClient is used to fetch microservice details from the Eureka Server at runtime based on the serviceId (application name) of the client microservice. It provides a convenient way to dynamically retrieve information about registered services and their instances. By utilizing the DiscoveryClient, microservices can discover and communicate with other services without the need for hardcoding specific URLs.

**Q) Can DiscoveryClient make requests to MS# application?**<br/>
A) No, the DiscoveryClient does not make HTTP requests to microservice applications. Its primary purpose is to connect to the Eureka Server and retrieve service details from it. It acts as a client to the service registry and does not handle the actual communication between microservices.

**Q) Can Eureka Server make HTTP requests to MS# application?**<br/>
A) No, the Eureka Server does not make HTTP requests to microservice applications. It serves as a registry that stores the details of microservice instances. Its main role is to provide the information about registered services when requested by other microservices or clients.

**Q) What is ServiceInstance?**<br/>
A) A ServiceInstance represents the details of a specific instance of a microservice. It contains essential information such as the serviceId (application name), instanceId (a unique identifier for the instance), the URI (combination of IP and PORT), and other metadata. The ServiceInstance object provides a convenient way to access and utilize the specific details of a microservice instance.

**Q) What is the difference between URI, URL, Protocol, and ResourcePath?**<br/>
A) In the context of a URL like `http://192.168.10.11:8086/myapp/employee/find/101`:

- Protocol: Refers to the communication protocol used, such as HTTP in the example.
- IP: Represents the IP address of the server where the microservice is running, in this case, 192.168.10.11.
- Port: Specifies the port number on which the microservice is listening for requests, in this case, 8086.
- ContextPath: Denotes the context path or the project name of the microservice, which is `/myapp` in the example (the default is typically `/` in Spring Boot).
- ResourcePath: Represents the dynamic path specific to the microservice's functionality, such as `/employee/find/101` in the example.

- URI: Refers to the combination of the IP and PORT, representing the base address of the microservice instance.
- URL: Refers to the complete address including the protocol, URI, context path, and resource path. It represents the full location of the resource or service.

<br/>
<br/>

# Here's the code for each class and its explanation, followed by the next class and its explanation:

**Eureka Server - SpringCloudEurekaServer**

```java
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaServerApplication.class, args);
    }
}
```

Explanation:
- This class represents the Eureka Server application.
- `@SpringBootApplication` enables Spring Boot features and auto-configuration.
- `@EnableEurekaServer` configures the application as a Eureka Server.

**CartService - SpringCloudCartService**

```java
@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/cart")
public class CartServiceApplication {

    @GetMapping("/info")
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("WELCOME TO CART SERVICE");
    }

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }
}
```

Explanation:
- This class represents the CartService microservice.
- `@SpringBootApplication` enables Spring Boot features and auto-configuration.
- `@EnableEurekaClient` registers the application with the Eureka Server.
- `@RestController` indicates that this class handles REST requests.
- `@RequestMapping("/cart")` specifies the base URL for the cart-related endpoints.
- The `showMessage()` method returns a ResponseEntity with the message "WELCOME TO CART SERVICE".

**OrderService - SpringCloudOrderService**

```java
@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/order")
public class OrderServiceApplication {

    @Autowired
    private CartConsumer consumer;

    @GetMapping("/place")
    public ResponseEntity<String> placeOrder() {
        String cartResp = consumer.getCartResponse();
        return ResponseEntity.ok("ORDER PLACED WITH => " + cartResp);
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
```

Explanation:
- This class represents the OrderService microservice.
- `@SpringBootApplication` enables Spring Boot features and auto-configuration.
- `@EnableEurekaClient` registers the application with the Eureka Server.
- `@RestController` indicates that this class handles REST requests.
- `@RequestMapping("/order")` specifies the base URL for the order-related endpoints.
- The `placeOrder()` method calls the `getCartResponse()` method of the `CartConsumer` to retrieve the cart response and returns a ResponseEntity with the order placement message.

**CartConsumer**

```java
@Component
public class CartConsumer {

    @Autowired
    private DiscoveryClient client;

    public String getCartResponse() {
        List<ServiceInstance> list = client.getInstances("CART-SERVICE");
        ServiceInstance si = list.get(0);
        URI uri = si.getUri();
        String url = uri + "/cart/info";
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.getForEntity(url, String.class);
        return response.getBody();
    }
}
```

Explanation:
- This class represents the CartConsumer component, responsible for making requests to the CartService.
- `@Component` marks this class as a Spring component for dependency injection.
- The `getCartResponse()` method uses the `DiscoveryClient` to retrieve the details of the "CART-SERVICE" from the Eureka Server.
- It gets the URI of the service instance, appends the specific path ("/cart/info"), and makes an HTTP GET request to the CartService using `RestTemplate`.
- The response body is returned as a String.

**Explanation and Conclusion:**

In this code, we have three projects: Eureka Server, CartService, and OrderService.

The Eureka Server is a standalone application that acts as a service registry for all the microservices. It runs on port 8761 and is configured not to register or fetch services from itself.

The CartService is a microservice that exposes a single endpoint ("/cart/info") and returns a "WELCOME TO CART SERVICE" message when accessed.

The OrderService is another microservice that depends on the CartService. It has an endpoint ("/order/place") to place an order. It utilizes the `CartConsumer` component to fetch the response from the CartService and includes it in the order placement message.

The `CartConsumer` component uses the `DiscoveryClient` to retrieve the details of the "CART-SERVICE" from the Eureka Server. It then constructs the URL for the "/cart/info" endpoint of the CartService using the obtained service instance details. It makes an HTTP GET request to the CartService and retrieves the response.

In conclusion, the OrderService relies on the Eureka Server and the DiscoveryClient to dynamically discover and communicate with the CartService without hardcoding its URL. This allows for flexibility and scalability in a microservices architecture.

<br/>
<br/>

# **Execution Order:**

1. Run the Eureka Server.
2. Run the Cart Service.
3. Run the Order Service.
4. Open your web browser and go to http://localhost:8761 to access the Eureka Server dashboard.
5. On the Eureka Server dashboard, locate and click on the link for the Order Service. It may look like http://localhost:8094/actuator/info.
6. Modify the full URL in the browser's address bar to http://localhost:8094/order/place and hit Enter.
   This will trigger the placeOrder() method in the OrderRestController, which internally calls the getCartResponse() method in the CartConsumer to fetch the response from the CartService.
   
Output:
The response will be displayed on the browser screen and will consist of the Order Service response concatenated with the Cart Service response. It will look like:
"ORDER PLACED WITH => WELCOME TO CART SERVICE"

This output confirms that the Order Service successfully communicated with the Cart Service and fetched the expected response.