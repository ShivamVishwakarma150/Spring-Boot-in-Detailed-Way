**Reactive Programming: Consumer Application**

In a reactive programming paradigm, the consumer application plays a vital role in making HTTP requests to the producer application and handling the responses in a reactive and non-blocking manner. In this context, the consumer application can utilize various tools and libraries to achieve reactive communication. Let's explore some of them:

**1. RestTemplate:**
RestTemplate is a class provided by Spring that supports synchronous communication over HTTP protocols. It acts as a client and can connect with any language producer application that exposes RESTful APIs. However, RestTemplate does not support reactive calls or non-blocking I/O operations. It is suitable for traditional, blocking communication between applications. RestTemplate has been widely used in older versions of Spring.

**2. LoadBalancerClient/OpenFeign:**
LoadBalancerClient or OpenFeign are tools that help with communication between microservices (MS#). They provide additional functionality like load balancing, service discovery, and integration with service registries. These tools are suitable for microservice architectures where inter-service communication is required. They support synchronous communication but might offer additional features like declarative APIs and client-side load balancing.

**WebClient:**
WebClient is a reactive client provided by Spring Webflux, which is a part of the Spring Boot Reactive API. It is designed specifically for reactive programming and asynchronous communication. WebClient offers a more flexible and efficient way to make HTTP requests compared to RestTemplate in reactive applications.

Here are the key aspects of using WebClient in a consumer application:

1. Define WebClient object using the base URL: To interact with the producer application, you create an instance of WebClient and provide the base URL of the producer application's API.

2. Provide call details in order: You specify the details of the HTTP request in a sequential manner, including the HTTP method (GET, POST, etc.), the path or URL with any path variables, the request body (if required), and other configuration options.

3. Execute the request: Once the request details are provided, you call the `exchange()` or `retrieve()` method on the WebClient object to execute the request asynchronously. This method returns a `Mono<ClientResponse>` or `Flux<ClientResponse>`, representing the response.

4. Convert the response to reactive types: You convert the ClientResponse to reactive types (`Mono` or `Flux`) using the appropriate methods such as `bodyToMono()` or `bodyToFlux()`. This allows you to handle the response in a reactive and non-blocking manner.

5. Subscribe to the data: To initiate the actual HTTP request and consume the response, you subscribe to the reactive types using `subscribe()`, `block()`, or other appropriate operators. This ensures that the reactive pipeline is triggered and the response is processed asynchronously.

It's important to note that when using WebClient, you need to ensure that the consumer application is built using Spring Boot Reactive API (Webflux). Additionally, make sure that the port numbers of the consumer and producer applications are not the same to avoid conflicts.

By utilizing WebClient and the Spring Boot Reactive API, the consumer application can achieve asynchronous and non-blocking communication with the producer application, enabling efficient handling of concurrent requests and better scalability.


<br/>
<br/>

# **`SpringBootReactiveConsumerEx`**

This is a Spring Boot application that serves as a consumer application to make reactive calls to the producer application using WebClient. Let's examine the code and explanations for each class:

**1. Runner class: TestProducerCallRunner**
```java
package com.app.shivam.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.shivam.entity.Student;

import reactor.core.publisher.Mono;

@Component
public class TestProducerCallRunner implements CommandLineRunner {

	public void run(String... args) throws Exception {
		//1. Create WebClient object using base URL 
		WebClient client = WebClient.create("http://localhost:9090/student");
		
		//call 
		/*
		Mono<Student> result = 
				client
				.post() //Http Method
				.uri("/create") //PATH
				.body(Mono.just(new Student("AA256", "SAM", 300.0)), Student.class) //Body
				.retrieve() // execute
				.bodyToMono(Student.class); //convert response
		
		System.out.println("FROM CONSUMER ---->");
		//access result
		result.subscribe(System.out::println);
		*/
		
		
		/*
		Flux<Student> flux =  client.get().uri("/all").retrieve().bodyToFlux(Student.class);
		flux.doOnNext(System.out::println).blockLast();
		*/
		
		Mono<Student> result = 
		client.get().uri("/fetch/AA256").retrieve() 
		.bodyToMono(Student.class);
		result.subscribe(System.out::println);
	}

}
```
**Explanation:** The TestProducerCallRunner class implements the CommandLineRunner interface, which allows us to execute code during the application startup. In the `run()` method, we define the WebClient object with the base URL of the producer application's API. We then make reactive HTTP calls using WebClient to interact with the producer application.

In the provided code, there are three different examples of WebClient calls, each commented out:
- Example 1: POST request to create a new student.
- Example 2: GET request to fetch all students.
- Example 3: GET request to fetch a student by ID.

You can uncomment one of these examples at a time to test different scenarios. Each example demonstrates the sequential flow of making a reactive HTTP request using WebClient: specifying the HTTP method, the URI, providing the request body (if required), executing the request, and converting the response to a reactive type.

**2. Entity class: Student**
```java
package com.app.shivam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private String id;
	private String name;
	private Double fee;
}
```
**Explanation:** The Student class is a simple POJO (Plain Old Java Object) representing a student entity. It uses Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) to automatically generate getters, setters, constructors, and other common methods.

**Conclusion:**
The SpringBootReactiveConsumerEx application serves as a consumer application to make reactive calls to the producer application using WebClient. By utilizing WebClient and the Spring Boot Reactive Web dependency, the consumer application can communicate asynchronously and non-blockingly with the producer application, fetching data or performing actions based on the provided API endpoints.

This example demonstrates the usage of WebClient to make reactive HTTP requests with various methods and configurations. It provides a foundation for building consumer applications in a reactive programming model, enabling efficient and scalable communication between microservices or other applications.

<br/>
<br/>

Here's the commented code in the `TestProducerCallRunner` class with explanations for each example:

```java
Mono<Student> result = 
    client
    .post() // Http Method
    .uri("/create") // PATH
    .body(Mono.just(new Student("AA256", "SAM", 300.0)), Student.class) // Body
    .retrieve() // Execute
    .bodyToMono(Student.class); // Convert response

System.out.println("FROM CONSUMER ---->");
// Access result
result.subscribe(System.out::println);
```
**Explanation:** This example demonstrates a POST request to create a new student. Here's the breakdown:
- The `post()` method specifies the HTTP method as POST.
- The `uri("/create")` method sets the PATH to the "/create" endpoint of the producer application.
- The `body()` method sets the request body using `Mono.just(new Student("AA256", "SAM", 300.0))`, which creates a new `Student` object.
- The `retrieve()` method executes the request.
- The `bodyToMono(Student.class)` method converts the response to a `Mono<Student>` type.
- The `subscribe(System.out::println)` method subscribes to the `Mono` and prints the result when it becomes available.

```java
Flux<Student> flux =  client.get().uri("/all").retrieve().bodyToFlux(Student.class);
flux.doOnNext(System.out::println).blockLast();
```
**Explanation:** This example demonstrates a GET request to fetch all students. Here's the breakdown:
- The `get()` method specifies the HTTP method as GET.
- The `uri("/all")` method sets the PATH to the "/all" endpoint of the producer application.
- The `retrieve()` method executes the request.
- The `bodyToFlux(Student.class)` method converts the response to a `Flux<Student>` type.
- The `doOnNext(System.out::println)` method logs each emitted student object to the console.
- The `blockLast()` method blocks and waits for the last element of the `Flux` to be emitted before proceeding.

```java
Mono<Student> result = 
    client.get().uri("/fetch/AA256").retrieve() 
    .bodyToMono(Student.class);
result.subscribe(System.out::println);
```
**Explanation:** This example demonstrates a GET request to fetch a student by ID. Here's the breakdown:
- The `get()` method specifies the HTTP method as GET.
- The `uri("/fetch/AA256")` method sets the PATH to the "/fetch/AA256" endpoint of the producer application, where "AA256" is the ID of the student to fetch.
- The `retrieve()` method executes the request.
- The `bodyToMono(Student.class)` method converts the response to a `Mono<Student>` type.
- The `subscribe(System.out::println)` method subscribes to the `Mono` and prints the result when it becomes available.

In each example, the WebClient sends the respective HTTP request to the producer application's API endpoint and retrieves the response asynchronously using reactive types (`Mono` or `Flux`). The `subscribe()` method is used to initiate the request and handle the response when it is available.

These explanations provide a better understanding of the commented code and showcase the usage of WebClient to make reactive HTTP requests in different scenarios within the consumer application.