# **Spring Boot ReST : Client Application**

In a Spring Boot application, when you want to connect with other applications implemented using different technologies such as Java, .NET, Python, etc., you can use the HTTP client provided by Spring called "RestTemplate". RestTemplate is a class that supports the HTTP protocol and facilitates communication with RESTful web services.

RestTemplate acts as a client in this scenario, allowing your Spring Boot application to consume resources exposed by the other applications. It provides a convenient way to make HTTP requests and handle the corresponding responses.

By utilizing RestTemplate, you can interact with the APIs exposed by the other applications using the HTTP methods such as GET, POST, PUT, and DELETE. These methods allow you to retrieve data, create new resources, update existing resources, and delete resources respectively.

RestTemplate also allows you to include request bodies and headers when making HTTP requests. You can pass data in the request body for POST and PUT requests, enabling you to send information to the other applications. Additionally, you can set custom headers in the request, which can be useful for authentication or providing additional context to the server.

When making requests with RestTemplate, you have the flexibility to handle the response in different ways. You can read the response into a ResponseEntity object, which contains the response status, headers, and body. This allows you to access detailed information about the response and perform further processing if needed. Alternatively, you can specify the expected type of the response, such as a String or a custom object type like Employee, and RestTemplate will automatically deserialize the response into that type.

RestTemplate also simplifies the conversion of data between different formats, such as JSON or XML, and Java objects. It has built-in support for JSON and XML conversion libraries (such as Jackson and JAXB), which automatically handle the serialization and deserialization of data. This means you can work with the response data as Java objects directly, without manually parsing the JSON or XML.

In summary, when your Spring Boot application needs to communicate with other applications over the HTTP protocol, RestTemplate is a powerful tool that simplifies the process. It provides a convenient way to make HTTP requests, handle responses, and convert data between different formats. RestTemplate enables your application to act as a consumer and seamlessly integrate with other applications regardless of their underlying technology.

<br/>
<br/>


1. **RestTemplate is a class:**
   RestTemplate is a class provided by the Spring Framework that serves as a synchronous HTTP client for making HTTP requests to remote servers. It encapsulates the functionality required to interact with RESTful web services.

2. **Supports all HTTP Methods (GET/POST/PUT/DELETE):**
   RestTemplate supports all the standard HTTP methods used in RESTful applications, including GET, POST, PUT, and DELETE. This allows you to perform different types of operations on remote resources based on the HTTP method required by the API.

3. **Supports Request Body and Headers creation:**
   RestTemplate enables you to send HTTP requests with request bodies and headers. You can create a request body object and set it as the payload for POST or PUT requests. Additionally, you can set custom headers in the request to provide additional information or authentication tokens.

4. **Reads Response into ResponseEntity or DirectType(String, Employee):**
   When making an HTTP request using RestTemplate, you can specify the expected response type. RestTemplate can read the response and map it to different types of objects. You can choose to read the response into a ResponseEntity object, which provides access to the response status, headers, and body. Alternatively, you can specify a direct type, such as a String or a custom object type like Employee, and RestTemplate will attempt to deserialize the response into that type.

5. **Auto-type conversion of global data (JSON/XML --> Object):**
   RestTemplate simplifies the conversion of data between different formats such as JSON or XML and Java objects. By default, RestTemplate can automatically convert JSON or XML responses into Java objects using the Jackson or JAXB libraries, respectively. This eliminates the need for manual parsing and object creation, making it easier to work with data returned from RESTful services.

In summary, RestTemplate is a versatile class that allows your Spring Boot application to act as a consumer and interact with other applications over HTTP. It supports various HTTP methods, enables you to send request bodies and headers, and provides flexible options for reading and converting the response data. This makes it a powerful tool for integrating and communicating with external systems.

<br/>
<br/>

**Q) What is var-args and how are they different from arrays?**<br/>

A) Varargs, short for "variable-length arguments," are a feature introduced in Java 5 (JDK 1.5) that allows a method to accept a varying number of arguments of the same type. Varargs are indicated by using three dots (...) in the method declaration. Here's a detailed explanation:

Varargs allow us to pass multiple values of the same type to a method without explicitly creating an array. We can simply provide comma-separated values as arguments when invoking the method. This provides a more convenient and concise way of passing variable-length arguments compared to explicitly creating and populating an array.

The key difference between varargs and arrays is that arrays have a fixed size once they are created, while varargs can have a varying number of arguments. With arrays, we need to specify the size explicitly and initialize the array elements individually. On the other hand, varargs allow us to pass any number of arguments, including zero, without the need for explicitly creating an array.

In a method declaration, varargs must be the last parameter. This means that if a method has multiple parameters, the varargs parameter must come after all other parameters. Additionally, a method can have only one varargs parameter.

Here's an example of a method declaration using varargs:

```java
public void printValues(String... values) {
    // Method body
}
```

In the example above, the `printValues` method accepts a varying number of `String` arguments. We can call this method with any number of `String` values, including zero, separated by commas.

```java
printValues("apple", "banana", "orange");  // Method call with multiple arguments
printValues("grape");                      // Method call with a single argument
printValues();                             // Method call with no arguments
```

In summary, varargs provide a convenient way to accept a varying number of arguments of the same type in a method. They eliminate the need for explicitly creating arrays and allow for more flexible parameter passing. However, it's important to note that varargs must be the last parameter in a method, and a method can have only one varargs parameter.

<br/>

**Q) What is mean by : java.net.ConnectException: Connection refused: connect?**

A `java.net.ConnectException: Connection refused: connect` error occurs when a client application, such as your Java program, is unable to establish a connection to a server application. This error typically indicates that the server application is not running or is not reachable at the specified network address and port.

Here's a more detailed explanation:

In a client-server architecture, the client application initiates a connection to the server application to communicate and exchange data. When the client attempts to connect to the server using a specific network address and port, it expects the server to be available and accepting connections at that address.

The `Connection refused` error specifically indicates that the client's connection request was rejected or refused by the server. This can happen for several reasons, including:

1. The server application is not running: If the server application is not started or is down, it won't be able to accept incoming connections. In this case, the client will encounter a `Connection refused` error when trying to establish a connection.

2. Incorrect network address or port: The client may be using an incorrect network address or port number to connect to the server. Double-check that the address and port used by the client match the ones on which the server is listening.

3. Firewall or network configuration: A firewall or network configuration can block or restrict connections to the server. Ensure that the necessary network ports are open and accessible for the client to connect to the server.

To resolve the `Connection refused` error, you can take the following steps:

1. Check if the server application is running and properly configured. Make sure it is listening for incoming connections.

2. Verify that the client is using the correct network address and port to connect to the server. Ensure that the address and port match the server's configuration.

3. Ensure that any firewalls or network configurations allow the client to establish a connection to the server. Check if any network ports are being blocked.

4. If the server application is hosted on a remote machine, ensure that the server is accessible over the network and there are no network connectivity issues.

By investigating these factors and resolving any issues, you should be able to establish a successful connection between your client application and the server application, eliminating the `Connection refused` error.

<br/>
<br/>

# Let's break down the code and explanations for each class:

**Producer Code: 60-SpringBootRestProducerEx**

```java
// Bean class: Book.java
package com.app.shivam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer bid;
    private String bname;
    private String bauth;
    private Double bcost;
}
```

Explanation:
- The `Book` class is a simple POJO (Plain Old Java Object) representing a book entity.
- It uses Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) to automatically generate getters, setters, constructors, and other boilerplate code.

```java
// RestController: BookRestController.java
package com.app.shivam.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.Book;

@RestController
@RequestMapping("/v1/api/book")
public class BookRestController {

    @GetMapping("/showA")
    public ResponseEntity<String> showMsg1() {
        return ResponseEntity.ok("WELCOME TO FIRST CALL TYPE - GET");
    }
    
    @GetMapping("/showB/{id}/{name}")
    public ResponseEntity<String> showMsg2(
            @PathVariable("id") Integer id,
            @PathVariable("name") String name) {
        return ResponseEntity.ok("WELCOME TO FIRST CALL TYPE - GET ==> " + id + "-" + name);
    }
    
    @GetMapping("/showC")
    public ResponseEntity<Book> showMsg3() {
        return ResponseEntity.ok(new Book(101, "ABC", "RAJ", 500.0));
    }
}
```

Explanation:
- The `BookRestController` class is a Spring `@RestController` that handles HTTP requests related to books.
- It has three GET methods:
  1. `showMsg1()` returns a simple string response.
  2. `showMsg2()` takes path variables (`id` and `name`) and returns a response based on those variables.
  3. `showMsg3()` returns a `Book` object as the response.

**Consumer Code: 60-SpringBootRestConsumerEx**

```java
// Runner class: GetTestARunner.java
package com.app.shivam.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GetTestARunner implements CommandLineRunner {

    public void run(String... args) throws Exception {
        String url = "http://localhost:9090/v1/api/book/showC";

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(url, String.class);

        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode().name());
        System.out.println(response.getStatusCode().value());
    }
}
```

Explanation:
- The `GetTestARunner` class is a Spring `CommandLineRunner` component responsible for making an HTTP GET request to the Producer application.
- It uses `RestTemplate` to perform the GET request and retrieves a `ResponseEntity<String>` as the response.
- The response body, headers, and status code are printed to the console.

```java
// Runner class: GetTestARunner2.java
package com.app.shivam.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GetTestARunner2 implements CommandLineRunner {

    public void run(String... args) throws Exception {
        String url = "http://localhost:9090/v1/api/book/showB/{id}/{name}";

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(
                url,
                String.class,
                101, "ABC");

        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode().name());
        System.out.println(response.getStatusCode().value());
    }
}
```

Explanation:
- The `GetTestARunner2` class is another Spring `CommandLineRunner` component for making an HTTP GET request to the Producer application.
- It also uses `RestTemplate` to perform the GET request, but this time it includes path variables (`id` and `name`) in the URL.
- The response body, headers, and status code are printed to the console.

**Conclusion:**

In the Producer code, we have a `BookRestController` that exposes three GET methods. These methods return different types of responses: a string, a string with path variables, and a `Book` object.

In the Consumer code, we have two `CommandLineRunner` classes: `GetTestARunner` and `GetTestARunner2`. These classes use `RestTemplate` to make GET requests to the Producer application and retrieve the responses. The first runner makes a GET request to `showC` endpoint, while the second runner makes a GET request to `showB` endpoint with path variables.

Both runners print the response body, headers, status code, and other details to the console.

This setup demonstrates how a Spring Boot REST client can interact with a Spring Boot REST API by making GET requests and handling the responses.

<br/>
<br/>
<br/>

# POST METHOD CALL:

Explanation:

To make a POST request using RestTemplate, we need to provide the URL, RequestEntity (consisting of the request body and headers), and the expected response type to the `postForEntity()` method.

```java
// API
postForEntity(
  url: String,
  requestEntity: HttpEntity,
  responseType: K.class
) ResponseEntity<K>
```

Let's break down the different components and their explanations:

1. **URL**: The `url` parameter represents the URL to which the POST request will be sent.

2. **RequestEntity**: The `requestEntity` parameter is an instance of `HttpEntity`, which represents the complete HTTP request entity, including headers and body.

   - The `HttpEntity` class combines two components: HttpHeaders and the request body.
   - HttpHeaders represent the headers to be included in the request.
   - The request body represents the data that will be sent in the POST request. It can be of any type (e.g., JSON, XML, plain text).
   - By creating an instance of `HttpEntity` and setting the appropriate headers and body, we can encapsulate the complete request entity.

3. **ResponseType**: The `responseType` parameter represents the class that will be used for mapping the response body received from the server. It specifies the expected type of the response object.

4. **ResponseEntity**: The `postForEntity()` method returns a `ResponseEntity` object, which represents the complete HTTP response received from the server.

   - The `ResponseEntity` class provides access to the response body, headers, and status code.
   - It encapsulates the HTTP response and allows for convenient handling of the response details.

By using the `postForEntity()` method and providing the URL, request entity, and expected response type, we can make a POST request using RestTemplate. RestTemplate handles the HTTP communication and returns a `ResponseEntity` object containing the response data.

Please note that since the code snippet for this POST method call is not provided, this explanation focuses on the method signature and its components to give you a general understanding of how to make a POST request using RestTemplate.

<br/>


Let's break down the code and explanations for making a POST method call using RestTemplate.

```java
// Sample code
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
```

Explanation:
- In the given code snippet, we create an instance of `HttpHeaders` and assign it to the variable `headers`.
- The `HttpHeaders` class represents the headers to be sent in an HTTP request.
- We set the content type of the request to `MediaType.APPLICATION_JSON` using the `setContentType()` method. This indicates that the request body will be in JSON format.

```java
// Sample code continued
HttpEntity<RequestObject> requestEntity = new HttpEntity<>(requestObject, headers);
```

Explanation:
- Here, we create an instance of `HttpEntity`, which represents the entire HTTP request entity.
- The `HttpEntity` class combines the headers and the request body.
- The `<RequestObject>` type parameter represents the type of the request object that we want to send in the request body.
- We pass the `requestObject` (containing the request body) and the `headers` to the `HttpEntity` constructor to create the request entity.

```java
// Sample code continued
ResponseEntity<ResponseObject> responseEntity = restTemplate.postForEntity(url, requestEntity, ResponseObject.class);
```

Explanation:
- In this part of the code, we use the `postForEntity()` method of `RestTemplate` to send the POST request.
- The `postForEntity()` method takes the URL, `requestEntity`, and the `responseType` as arguments.
- The `url` parameter represents the URL to which the POST request will be sent.
- The `requestEntity` parameter represents the request entity that we created earlier, containing the headers and the request body.
- The `ResponseObject.class` parameter represents the class representing the expected response type. It is used for type conversion of the response body.
- The `postForEntity()` method returns a `ResponseEntity` object representing the response received from the server.

To summarize:
- We create an instance of `HttpHeaders` and set the desired headers, including the content type.
- We create an instance of `HttpEntity`, which combines the headers and the request body.
- We use `postForEntity()` method of `RestTemplate` to send the POST request, passing the URL, request entity, and the expected response type.
- The method returns a `ResponseEntity` object representing the response received from the server.

By following this approach, you can make a POST request using RestTemplate, providing the URL, request entity (headers + body), and the expected response type. RestTemplate handles the HTTP communication and provides access to the response details.

<br/>
<br/>

# Code Explanation

1. Producer code:
```java
package com.app.shivam.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.Book;

@RestController
@RequestMapping("/v1/api/book")
public class BookRestController {

	@PostMapping("/showD")
	public ResponseEntity<String> showMgs4(
			@RequestBody Book book
			) 
	{
		return ResponseEntity.ok("Data given is " + book);
	}
}
```

Explanation:
- The `BookRestController` class is a REST controller responsible for handling HTTP requests related to books.
- The `/v1/api/book` base URL is specified using the `@RequestMapping` annotation.
- The `showMgs4` method is annotated with `@PostMapping` to handle HTTP POST requests at the `/showD` endpoint.
- The `@RequestBody` annotation is used to bind the request body to the `Book` object.
- The method returns a `ResponseEntity<String>` with the response message containing the book data.

2. Consumer code (Runner class):
```java
package com.app.shivam.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostTestARunner implements CommandLineRunner {

	public void run(String... args) throws Exception {
		// 1. Create URL
		String url = "http://localhost:9090/v1/api/book/showD";
		
		// 2. Create Request Headers
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// 3. Create Request Body
		String body = "{\"bid\" : 101,\"bname\" : \"ABC\", \"bauth\":\"AJ\", \"bcost\":500.0}";
		
		// 2+3 Combine Both Body and Headers
		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
		
		// 4. Create RestTemplate object
		RestTemplate template = new RestTemplate();
		
		// 5. Make HTTP call and Get Response back
		ResponseEntity<String> response = template.postForEntity(url, requestEntity, String.class);
				
		// 6. Print details
		System.out.println(response.getBody());
		System.out.println(response.getHeaders());
		System.out.println(response.getStatusCode().name());
		System.out.println(response.getStatusCode().value());
	}
}
```

Explanation:
- The `PostTestARunner` class is a command-line runner responsible for making a POST request to the producer's API.
- The `run` method is executed when the application starts.
- The URL for the POST request is set to `http://localhost:9090/v1/api/book/showD`.
- Request headers are created using the `HttpHeaders` class. In this case, the content type is set to `MediaType.APPLICATION_JSON`.
- The request body is created as a JSON string representing a `Book` object.
- The request entity is created by combining the body and headers using the `HttpEntity` class.
- An instance of `RestTemplate` is created to perform the HTTP request.
- The `postForEntity()` method is used to make the POST request, passing the URL, request entity, and the expected response type (`String.class`).
- The response received from the server is stored in a `ResponseEntity<String>` object.
- The details of the response, such as the response body, headers, status code name, and value, are printed to the console.

Conclusion:
The provided code demonstrates how to make a POST request from the consumer to the producer API using RestTemplate. The consumer creates the request URL, headers, and body, combines them into an `HttpEntity`, and uses `postForEntity()` to send the POST request. The producer receives the request and returns a response containing the book data. The consumer then prints the response details to the console.

Note: Make sure that both the producer and consumer applications are running and accessible at the specified URLs for the code to work properly.