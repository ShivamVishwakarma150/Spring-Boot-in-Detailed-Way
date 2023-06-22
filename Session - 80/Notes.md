# **Spring Boot Unit Testing**

In Spring Boot, unit testing is an essential part of the development process to ensure the correctness and reliability of the application. When testing Spring Boot applications, the `MvcResult` class plays a significant role in capturing the details of the request, response, and any exceptions that may occur during the test execution.

**MvcResult:**
`MvcResult` is a wrapper object that encapsulates the information related to the execution of an HTTP request and its corresponding HTTP response in the unit tests. It provides access to the following components:

**Request:** The `MockHttpServletRequestBuilder` represents the HTTP request being sent as part of the unit test. It includes details such as the request method, headers, parameters, and request body. It allows you to construct and customize the request before it is executed.

**Response:** The `MockHttpServletResponseBuilder` represents the HTTP response received after executing the request. It provides access to information such as the HTTP status code, content type, headers, and response body. You can use it to verify the expected behavior of the application based on the received response.

**Exception:** The `Throwable` type represents any exception that may occur during the execution of the request. If an exception is thrown by the application code or during request processing, it can be captured and inspected through the `MvcResult`.

**Retrieving Response Details:**
Once the request is executed and the response is obtained, you can extract various details from the `MvcResult` object to perform assertions and validate the expected behavior of your application. Some commonly accessed response details include:

**HTTP Status:** The HTTP status code returned by the application can be obtained using the `getStatus()` method. It allows you to verify if the response status matches the expected value (e.g., 200 for success, 404 for not found, etc.).

**Content-Type:** The content type of the response can be retrieved using the `getResponse().getContentType()` method. This information is helpful when validating the format of the response, such as JSON, XML, or plain text.

**Content Size:** The size of the response content can be obtained using the `getResponse().getContentLength()` method. This can be useful in asserting the expected size of the response payload.

**Other Output:** Apart from the above details, you can access and assert any other relevant information present in the response, such as specific messages, the emptiness of lists, or any custom output specific to your application's logic.

In summary, `MvcResult` in Spring Boot unit testing is a wrapper object that captures the details of the request, response, and exceptions (if raised) during the execution of unit tests. It provides access to the request, response, and exception components, allowing you to retrieve and validate various aspects such as HTTP status, content type, content size, and other output. This helps in ensuring the expected behavior of your application during unit testing.

<br/>
<br/>

# **test class full code with their explanation**

**Test: testCreateStudent()**

This test method is responsible for testing the save operation of a student. It sends a POST request to the URL "http://localhost:9690/v1/api/student/create" with the student details as JSON content. The request is then executed, and the result, which includes the request, response, and exceptions (if any), is obtained. The response is validated by asserting that the HTTP status code is 201 (CREATED) and the response content is not null. If the response content does not contain the expected "CREATED" message, the test fails.

```java
@Test
@DisplayName("TESTING STUDENT SAVE OPERATIONS")
@Order(1)
public void testCreateStudent() throws Exception {
    // 1. CREATE MOCKED REQUEST
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post("http://localhost:9690/v1/api/student/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"stdName\":\"AJAY\",\"stdGen\":\"Male\",\"stdCourse\":\"JAVA\",\"stdAddr\":\"HYD IND\"}");

    // 2. EXECUTE IT AND READ RESULT(REQUEST + RESPONSE + EXCEPTION)
    MvcResult result = mockMvc.perform(request).andReturn();

    // 3. READ RESPONSE FROM RESULT
    MockHttpServletResponse response = result.getResponse();

    // 4. ASSERT RESULT USING JUNIT
    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    assertNotNull(response.getContentAsString());
    if (!response.getContentAsString().contains("CREATED")) {
        fail("STUDENT NOT CREATED!!");
    }
}
```

**Test: testGetAllStudents()**

This test method verifies the retrieval of all students. It sends a GET request to the URL "http://localhost:9690/v1/api/student/all" and executes the request. The response is then validated by asserting that the HTTP status code is 200 (OK) and the response content is not null.

```java
@Test
@Order(2)
@DisplayName("GET ALL STUDENTS")
public void testGetAllStudents() throws Exception {
    // 1. create request
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:9690/v1/api/student/all");

    // 2. execute it
    MvcResult result = mockMvc.perform(request).andReturn();

    // 3. read response
    MockHttpServletResponse response = result.getResponse();

    // 4. assert result
    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertNotNull(response.getContentAsString());
}
```

**Test: testGetOneStudent()**

This test method tests the retrieval of a single student by their ID. It sends a GET request to the URL "http://localhost:9690/v1/api/student/find/{id}" with the ID parameter provided. The request is executed, and the response is validated by asserting that the HTTP status code is 200 (OK), the response content is not null, and the content type is set to JSON.

```java
@Test
@Order(3)
@DisplayName("GET ONE STUDENT BY ID")
public void testGetOneStudent() throws Exception {
    // 1. create request
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(
            "http://localhost:9690/v1/api/student/find/{id}",1);

    // 2. execute it
    MvcResult result = mockMvc.perform(request).andReturn();

    // 3. read response
    MockHttpServletResponse response = result.getResponse();

    // 4. assert result
    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertNotNull(response.getContentAsString());
    assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
}
```

**Test: testupdateStudent()**

This test method verifies the update operation of a student. It sends a PUT request to the URL "http://localhost:9690/v1/api/student/modify" with the updated student details as JSON content. The request is executed, and the response is validated by asserting that the HTTP status code is 200 (OK) and the response content is not null. If the response content does not contain the expected "UPDATED" message, the test fails.

```java
@Test
@DisplayName("TESTING STUDENT UPDATE OPERATIONS")
@Order(4)
public void testupdateStudent() throws Exception {
    // 1. CREATE MOCKED REQUEST
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .put("http://localhost:9690/v1/api/student/modify")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"stdId\":1,\"stdName\":\"AA\",\"stdGen\":\"Male\",\"stdCourse\":\"JAVA\",\"stdAddr\":\"HYD IND\"}");

    // 2. EXECUTE IT AND READ RESULT(REQUEST + RESPONSE + EXCEPTION)
    MvcResult result = mockMvc.perform(request).andReturn();

    // 3. READ RESPONSE FROM RESULT
    MockHttpServletResponse response = result.getResponse();

    // 4. ASSERT RESULT USING JUNIT
    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertNotNull(response.getContentAsString());
    if (!response.getContentAsString().contains("UPDATED")) {
        fail("STUDENT NOT UPDATED!!");
    }
}
```

**Test: testDeleteStudent()**

This test method tests the deletion of a student by their ID. It sends a DELETE request to the URL "http://localhost:9690/v1/api/student/remove/{id}" with the ID parameter provided. The request is executed, and the response is validated by asserting that the HTTP status code is 200 (OK) and the response content is not null.

```java
@Test
@Order(5)
@DisplayName("TEST DELETE STUDENT BY ID")
public void testDeleteStudent() throws Exception {
    // 1. create request
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(
            "http://localhost:9690/v1/api/student/remove/{id}",1);

    // 2. execute it
    MvcResult result = mockMvc.perform(request).andReturn();

    // 3. read response
    MockHttpServletResponse response = result.getResponse();

    // 4. assert result
    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertNotNull(response.getContentAsString());
}
```

**Conclusion:**

In this test class, various unit tests are implemented to ensure the functionality of the Spring Boot application related to CRUD operations on students. Each test method focuses on a specific operation and validates the corresponding request and response. By executing these tests, we can verify if the application behaves as expected, ensuring the correctness and reliability of the implemented functionality.

<br/>
<br/>

# **Embedded Databases: H2, Apache Derby, HSQL**

Embedded databases are in-memory databases provided by Spring Boot for development and testing purposes. They are not recommended for use in production environments. Spring Boot offers several embedded databases, including H2, Apache Derby, and HSQL (Hyper SQL).

To use an embedded database in your Spring Boot application, you need to include the corresponding dependency. For H2, you can add the following dependency to your project's Maven or Gradle configuration:

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

No additional connection or pooling configuration is required when using an embedded database. The necessary configurations, such as driver class, URL, username, etc., are provided by default in the `H2ConsoleProperties` and `H2ConsoleAutoConfiguration` classes.

By default, the H2 console is available at '/h2-console' path, and the database is accessible at the JDBC URL 'jdbc:h2:mem:testdb'. The H2 console is a web-based database management tool that allows you to interact with the embedded database during development and testing.

You can modify the default configurations by adding custom properties to your `application.properties` file. For example:

```properties
server.port=9090

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
```

In the above configuration, we have set the server port to 9090 and enabled the H2 console at the '/h2-console' path. We also specified the datasource URL as 'jdbc:h2:mem:testdb'.

After starting the application, you can access the H2 console using the URL 'http://localhost:9090/h2-console'. The default database schema will be created when the application starts, and it will be destroyed when the server is stopped. The default `ddl-auto` setting is 'create-drop', which means all the database tables will be created on application startup and dropped when the server is stopped.

In summary, embedded databases provide a lightweight and convenient option for development and testing purposes in Spring Boot applications. They eliminate the need for manual database installation and configuration, allowing developers to focus on application logic without worrying about external dependencies. The H2 database is a popular choice for embedded databases, and its integration with Spring Boot simplifies the setup process.

<br/>
<br/>

# **Code: SpringBootRestH2Ex**

**1. Application Properties:**
```properties
server.port=9090

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
```
In the application properties file, we configure the server port, enable the H2 console, and set the path for accessing the console. We also set the JDBC URL for the H2 in-memory database.

**2. Entity Class: Book**
```java
package com.app.shivam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bid;
    private String bname;
    private String bauth;
}
```
The `Book` class represents an entity in the application. It is annotated with `@Entity` to indicate that it is a persistent entity. The `@Id` annotation marks the `bid` field as the primary key, and the `@GeneratedValue` annotation specifies the strategy for generating the primary key.

**3. Repository Interface: BookRepository**
```java
package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
```
The `BookRepository` interface extends the `JpaRepository` interface, which provides CRUD operations for the `Book` entity. It allows us to perform database operations on the `Book` entity without writing explicit queries.

**4. Rest Controller: BookRestController**
```java
package com.app.shivam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.Book;
import com.app.shivam.repo.BookRepository;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    private BookRepository repo;

    @PostMapping("/create")
    public String createBook(@RequestBody Book book) {
        repo.save(book);
        return "BOOK CREATED";
    }
}
```
The `BookRestController` class is a REST controller that handles HTTP requests related to the `Book` entity. It is annotated with `@RestController` and defines a base request mapping of "/book". The `createBook` method handles a POST request at "/create" and saves the book entity using the `BookRepository`.

**5. Postman Request**
Send a POST request to `http://localhost:9090/book/create` with the following request body:
```json
{
    "bname" : "JAVA",
    "bauth" : "TEST"
}
```
This request creates a new book with the name "JAVA" and author "TEST".

**6. Accessing H2 Console**
To access the H2 console, navigate to `http://localhost:9090/h2-console` in your web browser. In the JDBC URL field, modify the URL to `jdbc:h2:mem:testdb`. This connects the console to the in-memory H2 database used in the application.

**Explanation:**
The code demonstrates a simple Spring Boot application with an embedded H2 database. The application allows creating books by sending a POST request to "/book/create" endpoint with the book details in the request body. The book entity is persisted using the `BookRepository` interface, which extends the `JpaRepository`.

The application.properties file configures the H2 database by enabling the H2 console, setting its path, and defining the datasource URL. The Book entity is defined as a JPA entity with the necessary annotations, and the BookRepository provides CRUD operations for the Book entity.

The BookRestController class handles the HTTP request to create a book and saves it using the repository. The Postman request demonstrates creating a book using a POST request. Finally, the H2 console is accessed using the provided URL and the modified JDBC URL.

**Conclusion:**
The provided code showcases the usage of an embedded H2 database in a Spring Boot application. It covers the configuration, entity definition, repository interface, and REST controller for creating books. The H2 console allows monitoring and managing the database during development and testing.