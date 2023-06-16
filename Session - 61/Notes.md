# Spring Boot Restful Webservices

*) By Using Spring Boot Restful webservices we can develop both Producer and Consumer applications.

## **Producer application work flow:**

**Workflow of the Producer Application in Spring Boot Restful Webservices**

1. **Request Handling by FrontController (FC) - DispatcherServlet**:
   In Spring Boot Restful webservices, the DispatcherServlet acts as the FrontController (FC) for incoming requests. It is a central servlet that receives all the HTTP requests and acts as the entry point for request processing. The DispatcherServlet maps the incoming request to the appropriate handler based on the request details such as the request path and HTTP method.

2. **Mapping Request Details to RestController Methods by HandlerMapper**:
   The HandlerMapper in Spring Boot Restful webservices is responsible for mapping the request details, such as the request path and HTTP method, to the corresponding RestController and its specific method. The RestController is a specialized controller in Spring MVC that is specifically designed for handling RESTful requests. The RestController class is annotated with `@RestController`, and its methods are annotated with specific HTTP method annotations such as `@GetMapping`, `@PostMapping`, etc.

3. **Execution of RestController Method and Generating ResponseEntity**:
   When the RestController method is executed, it processes the request and generates a response. The response can be in various formats such as JSON, XML, or plain text. In Spring Boot Restful webservices, the response is wrapped in a ResponseEntity<T> object. The ResponseEntity<T> represents the complete HTTP response including status code, headers, and the response body. The generic type parameter <T> represents the type of the response body, which can be a String, a custom class, or a collection.

4. **Returning ResponseEntity to FrontController and Sending Response to Client App**:
   After the RestController method has processed the request and created the ResponseEntity object, it is returned to the DispatcherServlet (FrontController). The DispatcherServlet then takes this ResponseEntity and sends it back as the response to the client application that made the initial request. The client application can be a web browser, a mobile app, or any other consumer that is interacting with the Producer application's RESTful API.

In summary, the workflow of the Producer application in Spring Boot Restful webservices involves the DispatcherServlet receiving requests, the HandlerMapper mapping the requests to the appropriate RestController methods, the RestController processing the requests and generating a ResponseEntity, and finally, the DispatcherServlet sending the ResponseEntity back as the response to the client application. This workflow allows for the development of the Producer application, which exposes RESTful APIs, and can be consumed by client applications.

<br/>
<br/>

## **Additional Notes on Spring Boot Restful Webservices:**

1. **No User Interface (UI) in Producer App**:
   In the context of webservices, the Producer application is focused on providing data and functionality through RESTful APIs, rather than rendering user interfaces. Therefore, there is no need to develop UI components in the Producer application. Its main purpose is to handle incoming requests and generate appropriate responses in the desired data formats.

2. **Supported Data Formats: Text, JSON, XML**:
   Spring Boot Restful webservices support various data formats for request and response payloads. These formats include plain text, JSON (JavaScript Object Notation), and XML (eXtensible Markup Language). The choice of data format depends on the requirements of the application and the preferences of the clients consuming the APIs.

3. **No ViewResolver Configuration**:
   Unlike traditional Spring Boot applications that use the Spring MVC framework for rendering views, Restful webservices do not require ViewResolver configuration. Since there is no UI component involved, the focus is solely on processing the incoming requests and generating appropriate responses.

4. **Common Dependency for Spring Boot WEB MVC and Restful Webservices**:
   Both Spring Boot WEB MVC and Restful webservices require the same dependency, which is "Spring Boot WEB." This dependency provides the necessary components and configurations for running a web application. By default, it includes the Tomcat server with the default port set to 8080. However, this port can be configured as per the application's requirements.

5. **Testing with POSTMAN**:
   While web browsers support making GET and POST requests, they have limitations when it comes to testing various HTTP methods and handling request payloads. To overcome these limitations, a tool like POSTMAN is commonly used for testing Restful webservices. POSTMAN allows developers to simulate different types of requests (GET, POST, PUT, DELETE, etc.) and provides features for managing headers, request bodies, and response inspection. It simplifies the testing process by providing a more comprehensive environment for working with webservices.

These additional notes highlight some important aspects of Spring Boot Restful webservices, including the absence of a UI in the Producer application, support for multiple data formats, the lack of ViewResolver configuration, the common dependency for WEB MVC and Restful webservices, and the use of tools like POSTMAN for testing purposes.

<br/>
<br/>



**Controller in Web MVC Apps**
In traditional web MVC (Model-View-Controller) applications, the controller is responsible for handling user interactions, processing requests, and providing responses. It is connected with the view layer, which represents the user interface (UI). The controller receives input from the UI, interacts with the model layer (data/business logic), and prepares the data to be displayed in the view. The data exchanged between the UI and the controller is typically in the form of objects or model attributes.

**RestController in Rest-Based Apps**
In Restful applications, the RestController is used instead of the traditional controller. RestController is a specialized version of the controller that is specifically designed for building REST-based APIs. Unlike web MVC apps, Restful apps do not have a view layer or UI concept. Instead, they focus on providing services or resources to be consumed by client applications.

The RestController is responsible for handling incoming requests and returning responses in a format suitable for RESTful APIs, such as XML or JSON. It maps HTTP methods (GET, POST, PUT, DELETE) to specific methods in the controller, allowing clients to perform CRUD (Create, Read, Update, Delete) operations on resources.

The data exchanged in Restful APIs is typically in XML or JSON format, making it easy for client applications to consume the services by parsing the data received from the server.

**Differences Between Controller and RestController**
1. **Connected with View vs. No View Concept**: In web MVC apps, the controller is connected with the view layer, which represents the UI. RestControllers, on the other hand, do not have a view concept as they focus on providing services/resources for client consumption.

2. **Data Exchange**: In web MVC apps, the data is exchanged between the UI and the controller using objects or model attributes. In Restful apps, data is exchanged with client applications using XML or JSON formats, allowing for more flexible and standardized data representation.

3. **Full Web-App Development vs. Service Provider**: Web MVC apps are designed as full-fledged web applications that handle user interactions and provide UI components. Restful apps, on the other hand, act as service providers that offer services/resources to be consumed by client applications. They are not limited to a specific language and can be used by clients developed in any language.

By using RestControllers in Restful applications, developers can build robust APIs that follow the principles of REST and enable easy integration and consumption by client applications.

<br/>
<br/>

# **Spring Boot Rest Annotations:**

1. **@RestController:**
   - Applied at the class level to indicate that the class is a RestController.
   - Combines the functionality of both @Controller and @ResponseBody annotations.
   - Handles incoming HTTP requests and returns the response in the desired format (JSON, XML, etc.).
   - Eliminates the need for explicit use of @ResponseBody for each method in the controller.

2. **@RequestMapping:**
   - Can be applied at the class and method levels.
   - Defines the base URL or endpoint for mapping incoming HTTP requests to specific methods.
   - Helps in organizing and structuring the RESTful API endpoints.
   - Allows for additional parameters like method, headers, and more to further narrow down the mapping.

3. **@GetMapping:**
   - Applied at the method level to handle HTTP GET requests.
   - Maps a specific URL endpoint to the annotated method.
   - Provides a convenient way to define read-only operations, such as retrieving data from a server.

4. **@PostMapping:**
   - Applied at the method level to handle HTTP POST requests.
   - Maps a specific URL endpoint to the annotated method.
   - Used for creating new resources or performing data submission to the server.

5. **@PutMapping:**
   - Applied at the method level to handle HTTP PUT requests.
   - Maps a specific URL endpoint to the annotated method.
   - Typically used for updating existing resources on the server.

6. **@PatchMapping:**
   - Applied at the method level to handle HTTP PATCH requests.
   - Maps a specific URL endpoint to the annotated method.
   - Used for making partial updates to a resource on the server.

7. **@DeleteMapping:**
   - Applied at the method level to handle HTTP DELETE requests.
   - Maps a specific URL endpoint to the annotated method.
   - Used for deleting a resource from the server.

8. **@PathVariable:**
   - Used to extract dynamic values from the URL path.
   - Specifies that a method parameter should be bound to a path variable in the URL.
   - Enables the handling of variable parts of a URL, such as IDs or names.

9. **@RequestHeader:**
   - Used to read the value of a specific HTTP request header.
   - Specifies that a method parameter should be bound to the value of a request header.
   - Provides access to information present in the headers, such as authorization tokens or content types.

10. **@RequestBody:**
    - Used to bind the request body (usually JSON or XML) to a method parameter.
    - Specifies that the method parameter should be populated with the contents of the HTTP request body.
    - Allows for easy extraction of data from the incoming request payload.

11. **@ResponseBody:**
    - Applied at the method level to indicate that the return value of the method should be serialized and sent as the response body.
    - Converts the return object (such as a POJO or a collection) to the desired format (JSON, XML, etc.).
    - Allows for direct serialization of the return value to the response body without the need for a separate view.

These annotations provide a powerful and convenient way to build RESTful APIs in Spring Boot by mapping incoming HTTP requests to specific methods, handling different types of requests (GET, POST, etc.), extracting values from URLs and headers, and serializing/deserializing request and response data in various formats.

<br/>
<br/>

# **First Application: Spring Boot Rest Producer Application**

In this example, we will create a Spring Boot Rest Producer application that exposes various endpoints for performing CRUD operations on a product resource.

**Step 1: Create the Spring Boot Application**
- Create a new Spring Boot application named "SpringBootRestProducerFirstEx".
- Add the following dependencies:
  - Spring Web: Provides support for building RESTful APIs.
  - Devtools: Optional dependency for development-time features.

**Step 2: Create the RestController**
- Create a new package named "com.app.shivam.controller".
- Create a class named "ProductRestController" in this package.
- Annotate the class with `@RestController` and `@RequestMapping("/product")` to specify the base URL for all endpoints in this controller.

**Step 3: Define Endpoints**
- Create the following methods in the `ProductRestController` class:

1. Fetch Resource (GET)
   - Annotate the method with `@GetMapping("/fetch")` to map it to the "/product/fetch" URL endpoint.
   - The method returns a `ResponseEntity<String>` object, which represents the HTTP response.
   - Return a new `ResponseEntity` object with the desired response body and HTTP status code (HttpStatus.OK).

2. Create Resource (POST)
   - Annotate the method with `@PostMapping("/save")` to map it to the "/product/save" URL endpoint.
   - Similar to the previous method, return a `ResponseEntity<String>` object with the response body and HttpStatus.OK.

3. Modify Resource (PUT)
   - Annotate the method with `@PutMapping("/update")` to map it to the "/product/update" URL endpoint.
   - Again, return a `ResponseEntity<String>` object with the response body and HttpStatus.OK.

4. Remove Resource (DELETE)
   - Annotate the method with `@DeleteMapping("/remove")` to map it to the "/product/remove" URL endpoint.
   - Return a `ResponseEntity<String>` object with the response body and HttpStatus.OK.

5. Partial Update of Resource (PATCH)
   - Annotate the method with `@PatchMapping("/updatecost")` to map it to the "/product/updatecost" URL endpoint.
   - Return a `ResponseEntity<String>` object with the response body and HttpStatus.OK.

**Step 4: Run the Application and Test Endpoints**
- Run the main class of the Spring Boot application.
- Use a tool like POSTMAN to test the endpoints:
  - Send a GET request to "http://localhost:9696/product/fetch".
  - Send a POST request to "http://localhost:9696/product/save".
  - Send a PUT request to "http://localhost:9696/product/update".
  - Send a DELETE request to "http://localhost:9696/product/remove".
  - Send a PATCH request to "http://localhost:9696/product/updatecost".

Each request should return a response with the corresponding operation mentioned in the response body.

This example demonstrates the basic setup of a Spring Boot Rest Producer application with various HTTP methods mapped to specific endpoints.

Here's the method-wise explanation of the code:

```java
//1. Fetch Resource
@GetMapping("/fetch")
public ResponseEntity<String> getProduct() {
    ResponseEntity<String> response = new ResponseEntity<>(
        "FROM GET OPERATION",  //body
        HttpStatus.OK);        //status (OK/200)
    
    return response;
}
```
This method is mapped to the GET request at "/product/fetch" endpoint. It returns a `ResponseEntity<String>` object, which represents the HTTP response. The response body is set as "FROM GET OPERATION", and the status code is set as HttpStatus.OK (200).

```java
//2. Create Resource
@PostMapping("/save")
public ResponseEntity<String> createProduct() {
    ResponseEntity<String> response = new ResponseEntity<>(
        "FROM POST OPERATION",
        HttpStatus.OK
    );
    return response;
}
```
This method is mapped to the POST request at "/product/save" endpoint. It returns a `ResponseEntity<String>` object with the response body set as "FROM POST OPERATION" and the status code set as HttpStatus.OK (200).

```java
//3. Modify Resource
@PutMapping("/update")
public ResponseEntity<String> updateProduct() {
    ResponseEntity<String> response = new ResponseEntity<>(
        "FROM PUT OPERATION",
        HttpStatus.OK
    );
    return response;
}
```
This method is mapped to the PUT request at "/product/update" endpoint. It returns a `ResponseEntity<String>` object with the response body set as "FROM PUT OPERATION" and the status code set as HttpStatus.OK (200).

```java
//4. Remove Resource
@DeleteMapping("/remove")
public ResponseEntity<String> deleteProduct() {
    ResponseEntity<String> response = new ResponseEntity<>(
        "FROM DELETE OPERATION",
        HttpStatus.OK
    );
    return response;
}
```
This method is mapped to the DELETE request at "/product/remove" endpoint. It returns a `ResponseEntity<String>` object with the response body set as "FROM DELETE OPERATION" and the status code set as HttpStatus.OK (200).

```java
//5. Partial Update of Resource
@PatchMapping("/updatecost")
public ResponseEntity<String> modifyProductCost() {
    ResponseEntity<String> response = new ResponseEntity<>(
        "FROM PATCH OPERATION",
        HttpStatus.OK
    );
    return response;
}
```
This method is mapped to the PATCH request at "/product/updatecost" endpoint. It returns a `ResponseEntity<String>` object with the response body set as "FROM PATCH OPERATION" and the status code set as HttpStatus.OK (200).

These methods demonstrate different HTTP methods (GET, POST, PUT, DELETE, PATCH) mapped to specific endpoints in the `ProductRestController` class. Each method returns a response with the corresponding operation mentioned in the response body and an HTTP status code of 200 (OK).


<br/>
<br/>

# Task - Write one simple REST API For Student that will display simple messages for GET, POST, PUT, DELETE, PATCH.

Here's a simple example of a REST API for a student resource:

```java
@RestController
@RequestMapping("/students")
public class StudentController {

    // GET request to retrieve student details
    @GetMapping("/{id}")
    public ResponseEntity<String> getStudent(@PathVariable String id) {
        String message = "Retrieving student with ID: " + id;
        return ResponseEntity.ok(message);
    }

    // POST request to create a new student
    @PostMapping
    public ResponseEntity<String> createStudent() {
        String message = "Creating a new student";
        return ResponseEntity.ok(message);
    }

    // PUT request to update an existing student
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable String id) {
        String message = "Updating student with ID: " + id;
        return ResponseEntity.ok(message);
    }

    // DELETE request to delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        String message = "Deleting student with ID: " + id;
        return ResponseEntity.ok(message);
    }

    // PATCH request to partially update a student
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchStudent(@PathVariable String id) {
        String message = "Partially updating student with ID: " + id;
        return ResponseEntity.ok(message);
    }
}
```

In this example, the `StudentController` class is a REST controller that handles requests related to student resources. The class is mapped to the base path "/students".

- The `getStudent` method handles the GET request to retrieve student details. It takes the student ID as a path variable and returns a message indicating that the student with the given ID is being retrieved.

- The `createStudent` method handles the POST request to create a new student. It returns a message indicating that a new student is being created.

- The `updateStudent` method handles the PUT request to update an existing student. It takes the student ID as a path variable and returns a message indicating that the student with the given ID is being updated.

- The `deleteStudent` method handles the DELETE request to delete a student. It takes the student ID as a path variable and returns a message indicating that the student with the given ID is being deleted.

- The `patchStudent` method handles the PATCH request to partially update a student. It takes the student ID as a path variable and returns a message indicating that the student with the given ID is being partially updated.

Each method returns a `ResponseEntity<String>` object with the appropriate message and an HTTP status code of 200 (OK).

<br/>
<br/>

## Q) how can we switch to some other servers from tomcat?
## How can we remove embedded tomcat from Spring Boot application?

To switch to a different server from Tomcat in a Spring Boot application, you need to remove the embedded Tomcat dependency and add the dependency for the desired server. Here are the steps:

Step 1: Remove embedded Tomcat

In your `pom.xml` file, locate the `spring-boot-starter-web` dependency and exclude the `spring-boot-starter-tomcat` dependency as shown below:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

This will remove the embedded Tomcat server from your project.

Step 2: Add the desired server dependency

Next, add the dependency for the server you want to use. For example, if you want to switch to Undertow, add the following dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-undertow</artifactId>
</dependency>
```

Alternatively, if you want to switch to Jetty, add the following dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

Make sure to update your Maven dependencies after making these changes.

By following these steps, you can remove the embedded Tomcat server from your Spring Boot application and switch to the desired server such as Undertow or Jetty.