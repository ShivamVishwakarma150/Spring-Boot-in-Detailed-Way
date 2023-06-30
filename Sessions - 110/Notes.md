# **Reactive Programming**

**1. NoSQL DB: MongoDB (Reactive API):**
MongoDB is a popular NoSQL database that provides a reactive API. The reactive API allows for non-blocking, event-driven interaction with the database. It enables developers to write asynchronous and reactive code that can handle a large number of concurrent requests efficiently. With the reactive API, MongoDB supports reactive programming paradigms and can integrate seamlessly with reactive frameworks and libraries.

**2. Application: Spring Webflux (Reactive API):**
Spring Webflux is a module in the Spring Framework that provides reactive programming support for building web applications. It is designed to work with reactive streams and allows developers to create non-blocking, asynchronous, and event-driven applications. Spring Webflux uses the reactive programming model to handle requests and responses. The annotations used in Spring Webflux are similar to those used in traditional REST APIs, making it easy for developers familiar with Spring to adopt reactive programming. The output types in Spring Webflux are `Mono` and `Flux`. `Mono` represents zero or one item, while `Flux` represents zero to many items.

**3. Consumer: WebClient:**
The WebClient is a part of the Spring Webflux module and provides a non-blocking, reactive HTTP client for making HTTP requests. It is designed to work seamlessly with Spring Webflux and supports the reactive programming model. With WebClient, you can perform HTTP operations asynchronously and handle responses in a reactive manner. It allows you to consume RESTful APIs or interact with other services using non-blocking calls, which is ideal for reactive applications.

**Execution order:**
To run an application that utilizes reactive programming with MongoDB, you need to follow these steps:
1. Start the MongoDB server by running the `mongod` command. This will ensure that the database is up and running.
2. Run your application, which uses the reactive API provided by MongoDB and Spring Webflux. This application acts as the producer, interacting with the MongoDB database using the reactive API.
3. The application can utilize the WebClient to make HTTP requests to other services or consume RESTful APIs in a reactive manner.

**Regarding MongoDB primary key (PK) values:**
- By default, MongoDB generates primary key values using an ObjectId. The generated value is of type `ObjectId`, which is a 12-byte identifier unique within a MongoDB instance.
- The primary key field in MongoDB is typically named `_id`, and it is automatically assigned an ObjectId value if not provided explicitly.
- However, it is possible to pass your own primary key value to MongoDB. In such cases, the value will be stored directly in the `_id` field without the ObjectId wrapper. It allows you to use custom primary key values, such as UUIDs or other string-based identifiers, instead of the generated ObjectId.


<br/>
<br/>

# Here's the code for each class with separate explanations, followed by the execution order and a conclusion:

**1. Application.properties:**
```properties
# Server Port
server.port=9090

# Database connection details
spring.data.mongodb.host=localhost
spring.data.mongodb.database=bootdb
spring.data.mongodb.port=27017
# spring.data.mongodb.username=
# spring.data.mongodb.password=
```
Explanation: In the application.properties file, we specify the server port on which the application will run (9090 in this case). We also provide the MongoDB database connection details, including the host, database name, and port. The username and password fields are commented out but can be uncommented and filled if authentication is required.

**2. Entity class / Collection class:**
```java
package com.app.shivam.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Student {

	@Id
	private String id;
	private String name;
	private Double fee;
}
```
Explanation: The Student class represents the entity or collection in the MongoDB database. It uses Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) to generate getters, setters, constructors, and other boilerplate code. The `@Document` annotation indicates that this class is mapped to a MongoDB document.

**3. Repository interface:**
```java
package com.app.shivam.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.app.shivam.entity.Student;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

}
```
Explanation: The StudentRepository interface extends the ReactiveMongoRepository interface, which provides CRUD operations for the Student entity. It inherits common database operations from ReactiveMongoRepository and also allows for reactive programming with MongoDB.

**4. Service class:**
```java
package com.app.shivam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shivam.entity.Student;
import com.app.shivam.repo.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;

	public Mono<Student> save(Student student) {
		return repo.save(student);
	}

	public Mono<Student> getOne(String id) {
		return repo.findById(id).switchIfEmpty(Mono.empty());
	}

	public Flux<Student> findAll() {
		return repo.findAll().switchIfEmpty(Flux.empty());
	}

	public Mono<Void> delete(String id) {
		return repo.deleteById(id);
	}
}
```
Explanation: The StudentService class contains methods to perform CRUD operations on Student entities using the StudentRepository. It is annotated with `@Service` to indicate that it is a service bean. The class provides methods to save a Student, retrieve a single Student by ID, fetch all Students, and delete a Student by ID. Reactive types (`Mono` and `Flux`) are used for handling asynchronous and non-blocking operations.

**5. RestController:**
```java
package com.app.shivam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.Student;
import com.app.shivam.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
public class StudentRestController {

	@Autowired
	private StudentService service;

	@PostMapping("/create")
	public Mono<Student> save(@RequestBody Student student) {
		return service.save(student);
	}

	@GetMapping("/fetch/{id}")
	public Mono<Student> getOne(@PathVariable String id) {
		return service.getOne(id);
	}

	@GetMapping("/all")
	public Flux<Student> fetchAll() {
		return service.findAll();
	}

	@DeleteMapping("/remove/{id}")
	public Mono<Void> deleteOne(@PathVariable String id) {
		return service.delete(id);
		/* service.delete(id);
		return Mono.just("Removed Id "+id); */
	}
}
```
Explanation: The StudentRestController class is annotated with `@RestController` and handles RESTful API requests related to the Student entity. It injects the StudentService bean and provides methods to create a new Student, fetch a single Student by ID, fetch all Students, and delete a Student by ID. The methods return reactive types (`Mono` and `Flux`) for handling asynchronous and non-blocking responses.

**6. Execution order:**
To run the application, follow these steps in order:
1. Start the MongoDB server by running the command `mongod` in the command prompt or terminal.
2. Run the Spring Boot application (`SpringBootReactiveProducerEx`) that contains the code described above.

**Conclusion:**
In this Spring Boot application, we have implemented a reactive producer using MongoDB as the database. The application follows a reactive programming model using Spring Webflux, ReactiveMongoRepository, and WebClient. It provides RESTful APIs to perform CRUD operations on the Student entity, utilizing reactive types like `Mono` and `Flux` for asynchronous and non-blocking operations.

