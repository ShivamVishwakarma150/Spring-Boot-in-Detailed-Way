# Circuit Breakers

In a system where multiple microservices (MS#) are involved in processing a request in a specific order, they form a chain or circuit. This chain of microservices is responsible for executing different steps of the request processing.

However, there can be scenarios where one of the microservices encounters issues while processing a request. This can be due to exceptions, service downtime, resource problems, timeouts, or any other failure. When such a failure occurs, it can lead to a cascade effect, where subsequent microservices in the chain may also fail, resulting in an invalid response or poor client experience.

To mitigate this problem, the concept of Circuit Breakers is introduced. A Circuit Breaker stops the execution of a microservice if it continuously fails and instead provides a dummy response or an error message to the client. This allows the admin or support team to investigate and fix the issue while preventing further failures and ensuring that clients receive a meaningful response.

The Circuit Breaker mechanism is triggered based on a threshold. There are two common types of thresholds:

**1. Count-based threshold:** If a microservice fails for a certain number of requests, the Circuit Breaker is activated. For example, if a microservice fails for 20 consecutive requests, the Circuit Breaker is triggered.

**2. Time-based threshold:** If a microservice does not respond or throws exceptions for a specified period, the Circuit Breaker is activated. For example, if a microservice does not respond or throws exceptions for the last 30 minutes, the Circuit Breaker is triggered.

The Circuit Breaker can be in one of the following states:

**1. CLOSED:** In this state, the connection between two microservices is established and requests are allowed to pass through. It indicates that the microservice is functioning properly.

**2. OPEN:** In this state, the connection between two microservices is broken, and requests are not allowed to pass through. It indicates that the microservice has encountered a failure and needs to be temporarily bypassed.

**3. HALF_OPEN:** This state occurs after a certain period when the Circuit Breaker allows a test request to pass through to the failed microservice to check if the issues or exceptions still persist. If the test request succeeds, the Circuit Breaker transitions back to the CLOSED state, indicating that the microservice is functioning properly again. If the test request fails, the Circuit Breaker remains in the OPEN state.

To implement Circuit Breakers in a Spring Boot application, the Hystrix library was commonly used in the past. However, as of now, the recommended library is "circuitbreaker-resilience4j," which provides a reactive API.

To configure Circuit Breakers in a Spring Boot application using resilience4j, you need to add the following dependencies: actuator, web, resilience4j, and AOP. These dependencies enable the necessary components to monitor and control the Circuit Breaker behavior.

Additionally, you need to define a fallback method that gets executed when the Circuit Breaker is in the OPEN state. The fallback method provides an alternative response to the client instead of invoking the actual microservice, thus avoiding cascading failures. This helps maintain a better client experience while the failed microservice is being fixed.

In summary, Circuit Breakers are a crucial component in a distributed system that helps prevent cascading failures. By monitoring the status of microservices and applying appropriate thresholds, Circuit Breakers can dynamically manage the flow of requests, allowing failed microservices to recover while ensuring a stable and responsive system for clients.

<br/>
<br/>

# Code Explanation

**MS#1 Controller:**
```java
package com.app.shivam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class OrderController {

    private static final String ORDER_SERVICE = "orderService";

    @GetMapping("/order")
    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "orderFallback")
    public ResponseEntity<String> createOrder() {
        String response = new RestTemplate().getForObject("http://localhost:8081/item", String.class);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> orderFallback(Exception e) {
        return new ResponseEntity<String>("SERVICE IS DOWN!! TRY AFTER SOME TIME", HttpStatus.OK);
    }
}
```

Explanation:
- This class represents the controller for the first microservice (MS#1).
- The `OrderController` class contains an endpoint `/order`, which is responsible for creating an order.
- The `@CircuitBreaker` annotation is provided by the resilience4j library. It specifies that circuit breaker functionality should be applied to the `createOrder` method.
- The `name` attribute in `@CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "orderFallback")` defines the name of the circuit breaker instance, which is "orderService".
- If the circuit breaker is triggered due to failures in the `createOrder` method, the fallback method `orderFallback` is invoked.
- Within the `createOrder` method, a `RestTemplate` is used to send a GET request to `http://localhost:8081/item` to retrieve the item details.
- The response is then returned as a `ResponseEntity<String>`.

**MS#2 Controller:**
```java
package com.app.shivam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/item")
    public ResponseEntity<String> createOrder() {
        return new ResponseEntity<String>("HELLO USERS!!", HttpStatus.OK);
    }
}
```

Explanation:
- This class represents the controller for the second microservice (MS#2).
- The `OrderController` class contains an endpoint `/item`, which is responsible for retrieving item details.
- When a GET request is made to `/item`, it returns a `ResponseEntity<String>` with the message "HELLO USERS!!" and an HTTP status of OK (200).

**Conclusion:**
- The provided code demonstrates the usage of a Circuit Breaker in a microservice architecture.
- The `OrderController` class of the first microservice (`MS#1`) utilizes the resilience4j library to implement the Circuit Breaker pattern.
- When a request is made to `/order`, it calls the `createOrder` method, which internally invokes the `getForObject` method of `RestTemplate` to send a GET request to the second microservice (`MS#2`) at `http://localhost:8081/item`.
- If the second microservice fails or encounters an exception, the Circuit Breaker kicks in and invokes the `orderFallback` method, which returns a fallback response "SERVICE IS DOWN!! TRY AFTER SOME TIME".
- This approach helps avoid cascading failures and provides a meaningful response to the client when the second microservice is not available.
- The second microservice (`MS#2`) simply returns a message "HELLO USERS!!" when a GET request is made to `/item`.
- The provided URLs `http://localhost:8080/order` and `http://localhost:8080/actuator/health` can be used to test the functionality of the Circuit Breaker and check the health status of the application.

By following this structure, I have provided the code and explanations for each class separately, followed by a conclusion summarizing the concept and functionality.

<br/>
<br/>

The provided YAML configuration file contains properties related to the Circuit Breaker functionality provided by the resilience4j library and the management endpoints in a Spring Boot application. Let's go through each section and explain its purpose:

```yaml
resilience4j:
  circuitbreaker:
    instances:
      orderService:
        registerHealthIndicator: true
        eventConsumerBufferSize: 10
        automaticTransitionFromOpenToHalfOpenEnabled: true
        failureRateThreshold: 50
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowSize: 10
        waitDurationInOpenState: 5s
        slidingWindowType: COUNT_BASED
```

Explanation:
- The `resilience4j` section is used to configure the Circuit Breaker functionality.
- Under `circuitbreaker.instances`, we define an instance named `orderService` (the same name used in the `@CircuitBreaker` annotation in the code).
- `registerHealthIndicator: true` indicates that a health indicator should be registered for this Circuit Breaker instance.
- `eventConsumerBufferSize: 10` specifies the buffer size for consuming events related to the Circuit Breaker.
- `automaticTransitionFromOpenToHalfOpenEnabled: true` enables automatic transition from the OPEN state to the HALF_OPEN state after a specified time.
- `failureRateThreshold: 50` sets the threshold for the failure rate percentage. If the failure rate exceeds this value, the Circuit Breaker will open.
- `minimumNumberOfCalls: 5` defines the minimum number of calls required before the Circuit Breaker can calculate the failure rate.
- `permittedNumberOfCallsInHalfOpenState: 3` specifies the maximum number of calls allowed in the HALF_OPEN state. If this limit is reached, the Circuit Breaker will transition back to the OPEN state.
- `slidingWindowSize: 10` sets the size of the sliding window used to record the outcome of calls and calculate the failure rate.
- `waitDurationInOpenState: 5s` defines the duration the Circuit Breaker remains in the OPEN state before allowing a test request in the HALF_OPEN state.
- `slidingWindowType: COUNT_BASED` indicates that the sliding window should be based on the number of calls.

```yaml
management:
  health:
    circuitbreakers:
      enabled: true
  endpoints:
    web:
      exposure:
        include: health
  endpoint:
    health:
      show-details: always
```

Explanation:
- The `management` section is used to configure the management endpoints in the Spring Boot application.
- `management.health.circuitbreakers.enabled: true` enables exposing Circuit Breaker health information.
- `management.endpoints.web.exposure.include: health` includes the health endpoint in the exposed endpoints.
- `management.endpoint.health.show-details: always` ensures that detailed health information is always shown when accessing the health endpoint.

In summary, the YAML configuration provided sets up the Circuit Breaker properties using resilience4j and configures the management endpoints to expose the health information related to Circuit Breakers. This allows monitoring and managing the Circuit Breaker functionality of the application.