# **Microservices Communication using `LoadBalancerClient`**

1. When multiple instances of the same microservice (MS#) are running on different servers, it allows for better scalability and availability to serve end customers effectively.

2. To achieve better service distribution and avoid overloading a single instance, multiple instances of a microservice are required.

3. However, if a consumer wants to communicate with a specific instance of the microservice that has a lower load factor (indicating less workload), it's not possible with the basic `DiscoveryClient` functionality. The `DiscoveryClient` returns a list of all instances of the microservice, making it difficult to choose a specific instance.

4. This is where the `LoadBalancerClient` comes into play. The `LoadBalancerClient` is an interface provided by Spring Cloud that abstracts the load balancing functionality. It internally follows a round-robin concept to distribute the load across multiple instances.

5. By using the `LoadBalancerClient`, a consumer can retrieve only one instance of a microservice from the Eureka Server, based on the load balancing algorithm implemented. This allows the consumer to communicate with a specific instance that has a lower load factor, thereby improving performance.

**Note: Instance ID**

1. If you want to run your MS# application as multiple instances, it's essential to provide a unique instance ID for each instance.

2. The instance ID can be configured using the property `eureka.instance.instance-id=_______` in the microservice's configuration. The value can be any string that uniquely identifies each instance.

3. Providing a unique instance ID is crucial for load balancing and distinguishing between multiple instances of the same microservice. It helps the load balancer identify and distribute requests to specific instances effectively.

By understanding the role of the `LoadBalancerClient` and the significance of providing a unique instance ID, you can ensure efficient communication between microservices and better load distribution across multiple instances.

<br/>
<br/>

# Here's the code and explanation for each class, followed by the conclusion.

### Eureka Server
```java
Name: SpringCloudEurekaServer
Dep: Eureka Server

Main: @EnableEurekaServer

application.properties:
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```
Explanation:
- The `SpringCloudEurekaServer` class is the main class for the Eureka Server application.
- It is annotated with `@EnableEurekaServer`, enabling it to function as a Eureka Server.
- In the `application.properties` file, we configure the server's port as 8761.
- We also set `eureka.client.register-with-eureka` and `eureka.client.fetch-registry` to `false` to disable self-registration and fetching of the registry.

### CartService
```java
Name: SpringCloudCartService
Dep: Spring Web, Eureka Discovery Client

Main: @EnableEurekaClient

application.properties:
server.port=8081
spring.application.name=CART-SERVICE
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.instance.instance-id=${spring.application.name}:${random.value}
```
Explanation:
- The `SpringCloudCartService` class is the main class for the CartService microservice.
- It is annotated with `@EnableEurekaClient`, indicating that it should register with the Eureka Server as a Eureka client.
- In the `application.properties` file, we configure the server's port as 8081 and set the application name as "CART-SERVICE".
- We provide the Eureka Server's location using `eureka.client.service-url.defaultZone`.
- The `eureka.instance.instance-id` property is set to `${spring.application.name}:${random.value}` to generate a unique instance ID for each instance of the CartService.

```java
@RestController
@RequestMapping("/cart")
public class CartRestController {

	@Value("${server.port}")
	private String port;

	@GetMapping("/info")
	public ResponseEntity<String> showMessage() {
		return ResponseEntity.ok("WELCOME TO CART SERVICE =>" + port);
	}
}
```
Explanation:
- The `CartRestController` class is a REST controller that handles requests related to the cart functionality.
- It is mapped to the "/cart" endpoint.
- The `showMessage()` method returns a response with the message "WELCOME TO CART SERVICE" concatenated with the server's port number.

### OrderService
```java
Name: SpringCloudOrderService
Dep: Spring Web, Eureka Discovery Client, Cloud LoadBalancer

Main: @EnableEurekaClient

application.properties:
server.port=9091
spring.application.name=ORDER-SERVICE
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```
Explanation:
- The `SpringCloudOrderService` class is the main class for the OrderService microservice.
- It is annotated with `@EnableEurekaClient` to enable Eureka client functionality.
- In the `application.properties` file, we configure the server's port as 9091 and set the application name as "ORDER-SERVICE".
- We provide the Eureka Server's location using `eureka.client.service-url.defaultZone`.

```java
@Component
public class CartConsumer {

	@Autowired
	private LoadBalancerClient client;

	public String getCartResponse() {
		ServiceInstance si = client.choose("CART-SERVICE");
		String url = si.getUri() + "/cart/info";
		System.out.println("*********************** " + url +" **********************");
		
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response = rt.getForEntity(url, String.class);
		return response.getBody();
	}
}
```
Explanation:
- The `CartConsumer` class is a component responsible for consuming the CartService.
- It uses the `LoadBalancerClient` to choose an instance of the "CART-SERVICE" from the Eureka Server.
- The chosen instance's URI is obtained, and the "/cart/info" endpoint is appended to it to form the complete URL.
- A `RestTemplate` is used to send a GET request to the CartService's endpoint and retrieve the response.

```java
@RestController
@RequestMapping("/order")
public class OrderRestController {

	@Autowired
	private CartConsumer consumer;

	@GetMapping("/place")
	public ResponseEntity<String> placeOrder() {
		String cartResp = consumer.getCartResponse();
		return ResponseEntity.ok("ORDER PLACED WITH => " + cartResp);
	}
}
```
Explanation:
- The `OrderRestController` class is a REST controller that handles requests related to placing orders.
- It is mapped to the "/order" endpoint.
- The `placeOrder()` method calls the `getCartResponse()` method of the `CartConsumer` to retrieve the cart response.
- The cart response is then included in the order response message.

### Conclusion
In this example, we have implemented a simple microservices architecture with a Eureka Server, CartService, and OrderService.
- The Eureka Server acts as a service registry where microservices can register and discover each other.
- The CartService registers itself with the Eureka Server using the `@EnableEurekaClient` annotation and provides its endpoint for cart-related operations.
- The OrderService, as a consumer, uses the `LoadBalancerClient` to choose an instance of the CartService from the Eureka Server and sends requests to the chosen instance.
- By leveraging Eureka and the LoadBalancerClient, microservices can communicate with each other dynamically and efficiently.

**The `execution `order for the given scenario is as follows:**

1. Run the Eureka Server.
   - This starts the Eureka Server and it will be running on port 8761.

2. Run the Cart Service.
   - Start three instances of the Cart Service, each with a different port number (e.g., 8081, 8082, 8083).
   - Each instance will register itself with the Eureka Server.

3. Run the Order Service.
   - Start one instance of the Order Service.
   - The Order Service will also register itself with the Eureka Server.

4. Access the Eureka Server dashboard.
   - Open a web browser and go to http://localhost:8761.
   - This will display the Eureka Server dashboard showing the registered services and their instances.

5. Click on the link for the Order Service.
   - On the Eureka Server dashboard, find the Order Service link and click on it.
   - This will redirect you to the Order Service's actuator info page (e.g., http://localhost:9091/actuator/info).

6. Modify the full URL of the Cart Service.
   - On the Order Service's actuator info page, modify the URL to access the Cart Service.
   - For example, change the URL to http://localhost:9091/order/place.

7. Execute the modified URL.
   - Enter the modified URL (e.g., http://localhost:9091/order/place) in the browser or use a REST client to send a GET request to the Order Service endpoint.

8. Observe the output.
   - The response will include the combined output of the Order Service and the Cart Service.
   - It will display "ORDER PLACED WITH => WELCOME TO CART SERVICE =>8083" (assuming the Cart Service instance running on port 8083 was chosen by the LoadBalancerClient).

By following these steps, you can observe the interaction between the microservices and verify that the Order Service successfully communicates with the Cart Service through the Eureka Server.

<br/>
<br/>

# **NOTE**

In the provided code, `${random.value}` is used to generate a random value for the `eureka.instance.instance-id` property in the Cart Service. This random value is generated using the `RandomValuePropertySource` class.

1. `${random.value}`: This is a placeholder in the configuration file that gets replaced with a random value during runtime. It is used to generate a unique instance ID for each instance of the Cart Service.

2. `RandomValuePropertySource`: It is a class provided by Spring Framework that generates random values. It is used to populate properties with random values at runtime. In this case, it is used to generate a random value for the `eureka.instance.instance-id` property.

By using `${random.value}` in the `eureka.instance.instance-id` property, each instance of the Cart Service will have a unique instance ID. This is important for the Eureka Server to distinguish between different instances of the same service and to maintain accurate information about the registered services.

<br/>
<br/>

# FAQ's

**Q) Why is LoadBalancerClient used?**

A) LoadBalancerClient is used to work with multiple instances of the Producer Microservices (MS# app). It acts as a client-side load balancer, allowing the consumer to distribute the requests among multiple instances of a service.

**Q) What is the old Load Balancer vendor name?**

A) The old Load Balancer vendor name is Ribbon. However, Ribbon has been removed from the Spring Cloud ecosystem, and the new default Load Balancer is called Cloud Load Balancer.

**Q) How many instances does LoadBalancerClient return for a single request from Eureka?**

A) LoadBalancerClient returns only one ServiceInstance for a single request from Eureka. It selects the instance based on a load balancing algorithm, typically using round-robin, to distribute the requests evenly among the available instances. The chosen instance will have a lower load factor compared to others.

**Q) Do we need RestTemplate if we use LoadBalancerClient?**

A) Yes, RestTemplate is still required even when using LoadBalancerClient. LoadBalancerClient interacts with the Eureka server to obtain information about the available instances of a service and select the appropriate instance for the request. However, LoadBalancerClient itself does not make the actual HTTP request to the Microservice (MS# app). RestTemplate is used to send the HTTP request to the selected instance and retrieve the response.

By using LoadBalancerClient in combination with RestTemplate, we can achieve client-side load balancing and make requests to different instances of a service without explicitly specifying the instance's URL or dealing with the load balancing logic ourselves.

# Some more Additional FAQ's



**Q1) What is the purpose of LoadBalancerClient in microservices communication?**

LoadBalancerClient is used in microservices communication to distribute the incoming requests across multiple instances of a microservice. It acts as a client-side load balancer, allowing the consumer to interact with a specific instance of a microservice without having to handle the load balancing logic manually.

**Q2) What is the difference between the old Load Balancer vendor (Ribbon) and the new Cloud Load Balancer?**

The old Load Balancer vendor, Ribbon, has been deprecated and replaced with the new Cloud Load Balancer. Ribbon was a client-side load balancing library, while the Cloud Load Balancer is an updated and integrated load balancing solution provided by Spring Cloud. It offers more advanced features and improved scalability compared to Ribbon.

**Q3) How does LoadBalancerClient handle multiple instances of a microservice application?**

LoadBalancerClient retrieves the available instances of a microservice from the service registry (e.g., Eureka server) and provides a client-side load balancing mechanism. It selects a specific instance based on a load balancing algorithm (e.g., round-robin) and returns that instance to the consumer. This allows the consumer to interact with different instances of the microservice for improved scalability and fault tolerance.

**Q4) How does LoadBalancerClient select a specific instance for a request?**

LoadBalancerClient selects a specific instance based on a load balancing algorithm, such as round-robin or weighted random selection. It considers factors like the instance's availability, health, and load factor. The exact algorithm can be configured and customized according to the specific requirements of the microservice architecture.

**Q5) Why do we need RestTemplate along with LoadBalancerClient?**

RestTemplate is required along with LoadBalancerClient to make the actual HTTP requests to the microservice instances. LoadBalancerClient handles the instance selection and provides the URL of the selected instance, and then RestTemplate performs the HTTP communication with that instance. RestTemplate handles serialization, deserialization, and other HTTP-related operations.

**Q6) Can LoadBalancerClient make HTTP requests to microservice applications?**

No, LoadBalancerClient itself does not make HTTP requests to microservice applications. Its main responsibility is to select an appropriate instance based on the load balancing algorithm. RestTemplate or any other HTTP client is required to actually perform the HTTP communication with the selected microservice instance.

**Q7) How can we ensure better/faster service to end-customers in microservices architecture?**

To ensure better and faster service to end-customers in a microservices architecture, multiple instances of a microservice should be deployed. LoadBalancerClient plays a crucial role in load balancing the incoming requests among these instances. Additionally, scaling the microservice instances based on the actual load and optimizing the communication between microservices can contribute to better performance and responsiveness.

**Q8) What is the purpose of the Eureka server in microservices communication?**

The Eureka server is a service registry and discovery server in microservices communication. It allows microservices to register themselves and provides a centralized location where the microservices can discover and locate other services. It helps in dynamic service discovery, enabling communication between microservices without hardcoding the service endpoints.

**Q9) How does the Eureka server help in service discovery and registration?**

The Eureka server allows microservices to register themselves by providing their metadata (such as service name, host, and port) to the Eureka server. Other microservices can then discover these registered services by querying the Eureka server. The Eureka server maintains an up-to-date registry of available services, enabling dynamic service discovery and enabling communication between microservices.

**Q10) How can we configure multiple instances of a microservice application?**

To configure multiple instances of a microservice application, you can deploy the application on multiple servers or virtual machines, each running on a different port. The instances should have the same application name but different instance IDs. The instance ID can be generated using a unique identifier, such as a random value, to differentiate each instance.

**Q11) What is the significance of providing an instance ID in a microservice application?**

The instance ID is significant in identifying and differentiating each instance of a microservice application. It helps in service discovery and load balancing. When a consumer requests a specific instance using LoadBalancerClient, it uses the instance ID to locate and communicate with that particular instance. The instance ID is used to ensure that requests are distributed evenly across multiple instances of the same microservice.

**Q12) How can a consumer read a specific instance of a microservice using LoadBalancerClient?**

To read a specific instance of a microservice using LoadBalancerClient, the consumer can invoke the LoadBalancerClient's `choose` method, providing the service name for which the instance is required. The LoadBalancerClient will then select an instance based on the configured load balancing algorithm and return the chosen instance. The consumer can extract the URI or URL of the instance and use it for further communication.

**Q13) What happens if a consumer requests a specific instance using DiscoveryClient instead of LoadBalancerClient?**

If a consumer requests a specific instance using DiscoveryClient instead of LoadBalancerClient, the DiscoveryClient will return a list of all instances registered with the service name. It will not perform load balancing or select a specific instance. The consumer would need to handle the instance selection and load balancing logic manually if it wants to interact with a specific instance.

**Q14) How can we verify the communication between microservices using the Eureka server?**

To verify the communication between microservices using the Eureka server, you can use the Eureka server's dashboard or REST endpoints. The Eureka server dashboard provides a visual representation of registered services and their instances. You can also use the Eureka server's REST endpoints to query the service registry and obtain information about the registered services and their instances.

**Q15) How does the order service communicate with the cart service using LoadBalancerClient?**

The order service communicates with the cart service using LoadBalancerClient in the following way:
- The order service, as a consumer, requests an instance of the cart service from the LoadBalancerClient by specifying the service name.
- The LoadBalancerClient selects a specific instance of the cart service based on the configured load balancing algorithm.
- The order service retrieves the URL of the selected cart service instance from the LoadBalancerClient.
- The order service uses RestTemplate (or any other HTTP client) to make an HTTP request to the cart service's URL and consumes its resources or data.

**Q16) What is the output of the order service when a request is made to place an order?**

When a request is made to place an order, the output of the order service will be "ORDER PLACED WITH => WELCOME TO CART SERVICE =>8083" (or a similar response). This output is generated by the order service by invoking the cart service's "/cart/info" endpoint using LoadBalancerClient and RestTemplate. The response from the cart service, which includes "WELCOME TO CART SERVICE" along with the port number (8083), is appended with the order-related information and returned as the output of the order service.
