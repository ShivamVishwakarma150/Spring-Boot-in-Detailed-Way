# Spring Boot - Exception Handling Process

In Spring Boot, the process of handling exceptions and errors is streamlined through the use of the pre-defined class called "BasicErrorController". This class is responsible for handling errors and exceptions that occur within your RestController or Controller classes when they are not explicitly handled using try/catch blocks.

Here's a detailed explanation of the exception handling process in Spring Boot:

1. Exception Occurs: When an exception occurs within a RestController or Controller method, it can be due to various reasons such as invalid input, business logic errors, or system failures.

2. Exception Propagation: By default, when an exception is thrown from a RestController or Controller method, it is propagated up the call stack until it reaches a suitable exception handler.

3. Exception Handling: If the exception is not explicitly handled within the method using try/catch blocks, it will be caught by the BasicErrorController class. This class acts as a centralized handler for unhandled exceptions and errors in your application.

4. BasicErrorController: The BasicErrorController is a pre-defined class provided by Spring Boot. It contains a method called error() that is responsible for handling the unhandled exceptions and errors.

5. Error Response: When an exception is caught by the BasicErrorController, it generates an error response based on the exception type and other relevant information. The error response typically includes details such as the error message, error code, and any additional information configured for the application.

6. HTTP Status Code: The BasicErrorController sets an appropriate HTTP status code for the error response based on the type of exception or error encountered. For example, a 404 status code is set for resource not found errors, and a 500 status code is set for internal server errors.

7. Customization: You can customize the error handling process by creating your own ErrorController and defining custom error handling methods. This allows you to provide more specific error responses, perform additional logging, or handle exceptions in a tailored manner.

By relying on the BasicErrorController, Spring Boot simplifies the exception handling process by providing a centralized mechanism to handle unhandled exceptions and generate appropriate error responses. This helps to ensure consistent error handling across your application and simplifies the development of robust and user-friendly RESTful APIs.

<br/>
<br/>

```json
Ex:
    {
        "timestamp": "2022-11-21T01:42:22.666+00:00",
        "status": 500,
        "error": "Internal Server Error",
        "trace": "java.lang.RuntimeException: Product Not Found 5586\r\n\tat com.app.shivam.rest.ProductRestController.getProductById(ProductRestController.java:24)\r\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)\r\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:568)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1071)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:964)\r\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n\tat org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:670)\r\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:779)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)\r\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\r\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:893)\r\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1789)\r\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.base/java.lang.Thread.run(Thread.java:833)\r\n",
        "message": "Product Not Found 5586",
        "path": "/product/one/5586"
    }
```

The error() method in the BasicErrorController class returns a pre-defined response format when an exception occurs and is not handled within a RestController or Controller method. The response format typically includes the following information:

1. timestamp: The timestamp when the error occurred. It provides the date and time in the format "yyyy-MM-dd'T'HH:mm:ss.SSSXXX".

2. status: The HTTP status code indicating the error. For example, in the provided response format, the status is 500, which represents an "Internal Server Error".

3. error: A short description of the error. In the example response format, the error is "Internal Server Error".

4. trace: The stack trace of the exception that occurred. It provides information about the exception type, the line numbers in the code where the exception was thrown, and the method calls leading to the exception. The stack trace helps in diagnosing the cause of the error. In the provided response format, the stack trace shows a RuntimeException with the message "Product Not Found 5586" and the corresponding stack trace elements.

5. message: A detailed error message describing the specific error condition. In the example response format, the message is "Product Not Found 5586".

6. path: The request path that triggered the error. It indicates the URL path of the endpoint that resulted in the exception. In the provided response format, the path is "/product/one/5586".

This pre-defined response format helps in standardizing error responses and provides useful information for debugging and troubleshooting purposes. The information contained in the response can be customized by implementing custom error handling logic in your application, allowing you to provide more specific error messages or additional details based on your application's requirements.

<br/>
<br/>

# Here's a detailed explanation of the provided sample code:

1. RestController code:
```java
package com.app.shivam.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @GetMapping("/one/{id}")
    public ResponseEntity<String> getProductById(
            @PathVariable("id") Integer id
    ) {
        if (id == 150) {
            return new ResponseEntity<>("Product Exist", HttpStatus.OK);
        } else
            throw new RuntimeException("Product Not Found " + id);
    }
}
```
The `ProductRestController` class is a RESTful controller responsible for handling HTTP requests related to products. It contains one method, `getProductById()`, which accepts a path variable `id` of type `Integer`. Here's the explanation of the method:
- The `@GetMapping("/one/{id}")` annotation maps the method to the endpoint `/product/one/{id}` where `{id}` is a placeholder for the actual product id.
- Inside the method, there's an if-else condition:
  - If the `id` is equal to 150, it returns a `ResponseEntity` with the message "Product Exist" and the HTTP status code OK (200). This indicates that the product with the given id exists.
  - Otherwise, it throws a `RuntimeException` with the message "Product Not Found {id}", where `{id}` is the actual value of the `id` path variable. This simulates an error condition where the product with the given id is not found.

2. application.properties:
```
server.port=9090
```
This configuration sets the server port to 9090, indicating that the application will listen for incoming requests on that port.

3. POSTMAN URLs:
```
GET http://localhost:9090/product/one/158
GET http://localhost:9090/product/one/150
```
These are the URLs that can be used to test the functionality of the REST API:
- The first URL sends a GET request to `/product/one/158` endpoint, where `158` is the product id.
- The second URL sends a GET request to `/product/one/150` endpoint, where `150` is the product id.

In conclusion, the provided code demonstrates a simple RESTful API that handles a GET request for retrieving a product by its id. If the requested product id is 150, the API responds with a success message "Product Exist" and HTTP status code 200. For any other product id, it throws a runtime exception with an error message "Product Not Found {id}", and the exception is handled by the error handling mechanism provided by Spring Boot, generating an appropriate error response.

<br/>
<br/>

# **Custom Exception Handling**

Here's a detailed explanation of custom exception handling using `@RestControllerAdvice` and `@ExceptionHandler` annotations:

1. `@RestControllerAdvice`:
- The `@RestControllerAdvice` annotation is used to define a global class for exception handling in Spring Boot.
- It serves as a centralized place to handle exceptions across multiple controllers in the application.
- This annotation is available in Spring 4.3 and newer versions.
- When an exception occurs within a controller, the `@RestControllerAdvice`-annotated class intercepts and handles the exception instead of the default `BasicErrorController`.

2. `@ExceptionHandler`:
- The `@ExceptionHandler` annotation is used to define methods within the `@RestControllerAdvice`-annotated class that handle specific types of exceptions.
- Each method annotated with `@ExceptionHandler` corresponds to a specific exception type.
- When an exception occurs, Spring compares the exception type with the types defined in the `@ExceptionHandler` methods and executes the matching method.
- If no matching `@ExceptionHandler` method is found, Spring redirects the exception handling back to the default `BasicErrorController`.

By combining `@RestControllerAdvice` and `@ExceptionHandler`, you can define custom exception handling logic in your application.

Here's an example of using these annotations:

```java
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Custom exception handling logic
        String errorMessage = "An error occurred: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

In this example:
- The `CustomExceptionHandler` class is annotated with `@RestControllerAdvice`, indicating that it handles exceptions globally.
- The `handleRuntimeException()` method is annotated with `@ExceptionHandler(RuntimeException.class)`, specifying that it handles exceptions of type `RuntimeException`.
- Inside the method, you can define your custom exception handling logic. In this case, it creates an error message using the exception message and returns a `ResponseEntity` with the error message and HTTP status code `500 Internal Server Error`.

Using `@RestControllerAdvice` and `@ExceptionHandler`, you can define multiple exception handler methods for different exception types, allowing you to customize the response format and behavior based on the encountered exceptions.

Note: It's important to handle exceptions appropriately and provide meaningful error responses to clients to ensure a good user experience and facilitate troubleshooting during application development and maintenance.

<br/>
<br/>

When using `@RestControllerAdvice` for custom exception handling, you can return a custom error response class to provide a structured JSON or XML output. The `@RestControllerAdvice` annotation internally uses the `@ResponseBody` annotation, which is responsible for converting the returned object into the desired response format.

Here's an example of returning a custom error response class:

```java
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        // Custom exception handling logic
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

In this example:
- The `handleRuntimeException()` method handles exceptions of type `RuntimeException`.
- Instead of returning a `ResponseEntity<String>`, it returns a `ResponseEntity<ErrorResponse>`, where `ErrorResponse` is a custom error response class.
- The `ErrorResponse` class could be defined as follows:

```java
public class ErrorResponse {

    private String error;
    private String message;

    // Constructor, getters, and setters
}
```

By returning a custom error response class, you can structure the error information in a meaningful way, including fields such as the error message, error code, timestamp, or any other relevant details. This allows the clients to understand and process the error response more effectively.

Keep in mind that the actual structure and fields of the custom error response class will depend on your application's requirements and the information you want to convey to the clients.

<br/>
<br/>

# CASE-1 Code with Explanation

Here's the breakdown of the provided code:

1. RestController:
```java
@RestController
@RequestMapping("/product")
public class ProductRestController {

	@GetMapping("/one/{id}")
	public ResponseEntity<String> getProductById(@PathVariable("id") Integer id) {
		if (id == 150) {
			return new ResponseEntity<>("Product Exist", HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Product Not Found " + id);
		}
	}
}
```
- This is a `ProductRestController` class with a single `getProductById()` method.
- The method handles a GET request at the endpoint `/product/one/{id}`.
- If the `id` parameter is equal to 150, it returns a response with "Product Exist" and HTTP status OK.
- Otherwise, it throws a `ProductNotFoundException` with a message "Product Not Found" concatenated with the `id`.

1. Handler class:
```java
@RestControllerAdvice
public class MyCustomExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> showCustomErrorMsg(ProductNotFoundException pnfe) {
		return new ResponseEntity<String>(pnfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
```
- This is the `MyCustomExceptionHandler` class annotated with `@RestControllerAdvice`.
- It handles the `ProductNotFoundException` using the `@ExceptionHandler` annotation.
- The `showCustomErrorMsg()` method takes a `ProductNotFoundException` parameter and returns a `ResponseEntity<String>`.
- Inside the method, it creates a `ResponseEntity` with the exception's message and HTTP status INTERNAL_SERVER_ERROR.

In conclusion, the provided code demonstrates custom exception handling using `@RestControllerAdvice` and `@ExceptionHandler`. If the `ProductNotFoundException` occurs in the `ProductRestController`, it will be caught by the `MyCustomExceptionHandler`, which returns an appropriate error response with the exception's message and HTTP status code. This allows you to handle specific exceptions and provide customized error messages to clients.

<br/>
<br/>

# CASE-2 Code with Explanation

Here's the breakdown of the provided code:

1. Entity/Beans:
- `Product` class:
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private Integer pid;
	private String pcode;
}
```
- This is a simple entity class representing a product with `pid` and `pcode` fields.

- `MyErrorResponse` class:
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyErrorResponse {
	private String date;
	private String status;
	private int code;
	private String message;
}
```
- This class represents a custom error response with fields for date, status, code, and message.

2. Custom Exception class:
```java
public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(String message) {
		super(message);
	}
}
```
- This is a custom exception class that extends the `RuntimeException` class.
- It provides a constructor to set the exception message.

3. Service class:
```java
@Service
public class ProductService {
	public Product getOneProductById(Integer id) {
		if (id == 150)
			return new Product(id, "DUMMY");
		else
			throw new ProductNotFoundException("PRODUCT '" + id + "' NOT EXIST");
	}
}
```
- This is a service class that simulates fetching a product by ID.
- If the ID is 150, it returns a dummy product.
- Otherwise, it throws a `ProductNotFoundException` with a message indicating that the product does not exist.

4. RestController:
```java
@RestController
@RequestMapping("/product")
public class ProductRestController {
	@Autowired
	private ProductService service;

	@GetMapping("/one/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		ResponseEntity<Product> response = null;
		try {
			Product pob = service.getOneProductById(id);
			response = new ResponseEntity<Product>(pob, HttpStatus.OK);
		} catch (ProductNotFoundException pnfe) {
			pnfe.printStackTrace();
			throw pnfe; // call handler
		}
		return response;
	}
}
```
- This is the updated `ProductRestController` class.
- It injects an instance of `ProductService` using `@Autowired`.
- The `getProductById()` method handles a GET request at the endpoint `/product/one/{id}`.
- It tries to fetch a product using the `ProductService`.
- If the `ProductNotFoundException` occurs, it prints the stack trace and rethrows the exception to be handled by the exception handler.

5. RestControllerAdvice:
```java
@RestControllerAdvice
public class MyCustomExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<MyErrorResponse> showCustomErrorMsg(ProductNotFoundException pnfe) {
		return new ResponseEntity<MyErrorResponse>(
				new MyErrorResponse(new Date().toString(), "EXCEPTION IN PROCESS", 500, pnfe.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
```
- This is the updated `MyCustomExceptionHandler` class annotated with `@RestControllerAdvice`.
- It handles the `ProductNotFoundException` using the `@ExceptionHandler` annotation.
- The `showCustomErrorMsg()` method takes a `ProductNotFoundException` parameter and returns a `ResponseEntity<MyErrorResponse>`.
- Inside the method, it creates a `MyErrorResponse` object with the current date, a status message, code, and the exception message.
- It returns a `ResponseEntity` with the error response and HTTP status INTERNAL_SERVER_ERROR.

In conclusion, the updated code includes a custom `MyErrorResponse` class to represent error responses and a modified `ProductRestController` that throws a `ProductNotFoundException` if the product is not found. The `MyCustomExceptionHandler` handles this exception and returns a custom error response containing the exception message, along with the date, status, and code. This allows for more meaningful and structured error handling in the application.

<br/>
<br/>

**Q: How do you handle exceptions in your RestApplications?**<br/>
A: Exceptions in REST applications are handled using a combination of service layer exception handling, exception propagation to the REST controller, and a global exception handler.

**Q: What is the role of the service layer in exception handling?**<br/>
A: The service layer of the application is responsible for catching any exceptions that occur during its operations and handling them appropriately. It may choose to handle exceptions internally, perform error recovery, or propagate them to the caller.

**Q: How are exceptions propagated to the REST controller?**<br/>
A: When an exception occurs in the service layer, it is propagated to the REST controller. The REST controller, which is responsible for handling incoming HTTP requests and generating responses, can catch the exception or let it propagate further.

**Q: What is the purpose of a global exception handler in a REST application?**<br/>
A: The global exception handler provides centralized exception handling and ensures consistent error responses across the application. It is implemented as a component annotated with `@ControllerAdvice` or `@RestControllerAdvice`. The handler contains methods annotated with `@ExceptionHandler` to handle specific exception types.

**Q: How does the global exception handler work?**<br/>
A: When an exception occurs and reaches the global exception handler, it compares the exception type with the defined `@ExceptionHandler` methods. If a match is found, the corresponding method is invoked to generate a custom error response. The custom error response can include details such as the error message, timestamp, status code, and additional information relevant to the exception. The global exception handler then returns this custom error response as an HTTP response to the client.

**Q: What are the benefits of using a global exception handler?**<br/>
A: By implementing a global exception handler, exceptions are handled consistently throughout the application. It centralizes exception handling logic, making the code more maintainable and reducing duplication. Additionally, it ensures that clients receive meaningful error messages and appropriate status codes in response to exceptions.

<br/>
<br/>
<br/>

```
Request->FC->HM->RestController->call service -> call Repo
-> No Data -> Service throw exception -> catch in RestController -> throw exception obj -> Exception Handler -> compare type -> Return custom message.
```

Here's a detailed explanation of the flow in your RestApplication for the scenario when there is no data in the repository:

1. Request: The client sends a request to the RestApplication.
2. Front Controller (FC): The Front Controller receives the request and acts as the central entry point for all incoming requests.
3. Handler Method (HM): The request is routed to the appropriate handler method in the RestController based on the specified endpoint.
4. RestController: The RestController handles the request and calls the corresponding service method.
5. Service Layer: The service layer performs business logic and interacts with the repository to fetch the required data. In this case, the service layer queries the repository to retrieve the data.
6. Repository: The repository performs database operations to fetch the requested data.
7. No Data: In this scenario, the repository does not find any data matching the query criteria.
8. Service Layer Exception: The service layer, upon receiving an empty result from the repository, throws a custom exception to indicate that no data was found.
9. RestController Exception Handling: The RestController catches the exception thrown by the service layer using a try-catch block.
10. Rethrow Exception: After catching the exception, the RestController rethrows it.
11. Global Exception Handler: The RestApplication has a global exception handler, annotated with `@RestControllerAdvice`, which intercepts and handles exceptions thrown from RestControllers.
12. Exception Type Comparison: The global exception handler compares the type of the caught exception with the `@ExceptionHandler` methods it has defined.
13. Matching Exception Handler: If a matching `@ExceptionHandler` method is found for the caught exception type, that method is executed.
14. Custom Error Message: Within the matching `@ExceptionHandler` method, a custom error message or response object is created. It includes details such as the error message, timestamp, status code (e.g., 404 - Not Found), and any additional relevant information.
15. Return Response: The global exception handler returns the custom error message or response as an HTTP response to the client.
16. Client: The client receives the custom error message or response, which indicates that no data was found in the repository.

In summary, when there is no data in the repository, the service layer throws an exception, which is caught by the RestController. The RestController rethrows the exception, and the global exception handler intercepts it, compares its type, and returns a custom error message or response to the client. This provides information about the encountered exception and the fact that no data was found.
