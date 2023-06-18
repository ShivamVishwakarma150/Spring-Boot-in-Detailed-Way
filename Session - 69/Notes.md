# Here's the code and the corresponding explanations for each class:

**1. Application Properties (application.properties):**

```
# Port number
server.port=9690

# Database config
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/boot
spring.datasource.username=root
spring.datasource.password=Shivam@123

# JPA Configuration
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

Explanation:
- This file contains the configuration properties for the Spring Boot application.
- `server.port` specifies the port number on which the application will run.
- The `spring.datasource` properties define the database connection details, including the driver class, URL, username, and password.
- `spring.jpa` properties configure the JPA (Java Persistence API) settings. Here, `show-sql` enables displaying SQL statements in the console, `database-platform` sets the Hibernate dialect for MySQL 8, and `ddl-auto` configures the database schema generation strategy.

**2. Entity Class (Student.java):**

```java
package com.app.shivam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="stdtab")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sid")
	private Integer stdId;

	@Column(name="sname")
	private String stdName;
	
	@Column(name="sgen")
	private String stdGen;
	
	@Column(name="scourse")
	private String stdCourse;
	
	@Column(name="saddr")
	private String stdAddr;
}
```

Explanation:
- This class represents the `Student` entity, which will be mapped to the corresponding database table (`stdtab`).
- The `@Entity` annotation specifies that this class is an entity and should be managed by the JPA provider.
- The `@Table` annotation defines the table name in the database.
- Each instance variable is mapped to a column in the table using the `@Column` annotation.
- The `@Id` annotation marks the `stdId` field as the primary key, and the `@GeneratedValue` annotation configures automatic generation of the primary key values using the `GenerationType.IDENTITY` strategy.
- The `@Data` annotation from Lombok library generates getters, setters, `toString()`, and other utility methods automatically.

**3. Repository Interface (StudentRepository.java):**

```java
package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
```

Explanation:
- This interface extends the `JpaRepository` interface provided by Spring Data JPA.
- The `StudentRepository` interface provides CRUD (Create, Read, Update, Delete) operations for the `Student` entity.
- The type parameters `<Student, Integer>` indicate that it manages instances of the `Student` entity and uses `Integer` as the type for the primary key.

**4. Service Interface (IStudentService.java):**

```java
package com.app.shivam.service;

import java.util.List;

import com.app.shivam.entity.Student;

public interface IStudentService {

	Integer saveStudent(Student s);
	void updateStudent(Student s);
	void deleteStudent(Integer id);
	Student getOneStudent(Integer id);
	List<Student> getAllStudents();
}
```

Explanation:
- The `IStudentService` interface defines the contract for the student service layer.
- It declares methods for CRUD operations and retrieving students.


- The methods take `Student` objects or IDs as parameters and return `Student` objects or other appropriate types.

**5. Service Implementation (StudentServiceImpl.java):**

```java
package com.app.shivam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shivam.entity.Student;
import com.app.shivam.exception.StudentNotFoundException;
import com.app.shivam.repo.StudentRepository;
import com.app.shivam.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository repo;
	
	public Integer saveStudent(Student s) {
		s = repo.save(s);
		return s.getStdId();
	}

	public void updateStudent(Student s) {
		if (s.getStdId() == null || !repo.existsById(s.getStdId()))
			throw new StudentNotFoundException("STUDENT '" + s.getStdId() + "' NOT EXIST");
		else
			repo.save(s);
	}

	public void deleteStudent(Integer id) {
		repo.delete(getOneStudent(id));
	}

	public Student getOneStudent(Integer id) {
		return repo.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("STUDENT '" + id + "' NOT EXIST"));
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> list = repo.findAll();
		return list;
	}
}
```

Explanation:
- The `StudentServiceImpl` class implements the `IStudentService` interface.
- It is annotated with `@Service` to indicate that it is a service component.
- The `@Autowired` annotation injects an instance of the `StudentRepository` interface, allowing interaction with the database.
- The methods are implemented as per the contract defined in the service interface.
- The `saveStudent` method saves a new student and returns the generated student ID.
- The `updateStudent` method checks if a student with the given ID exists before updating the student record. If the student doesn't exist, it throws a `StudentNotFoundException`.
- The `deleteStudent` method deletes a student by ID after obtaining the student using the `getOneStudent` method.
- The `getOneStudent` method retrieves a student by ID and throws a `StudentNotFoundException` if the student doesn't exist.
- The `getAllStudents` method retrieves a list of all students from the repository.

**6. Custom Exception Class (StudentNotFoundException.java):**

```java
package com.app.shivam.exception;

public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StudentNotFoundException() {
		super();
	}
	
	public StudentNotFoundException(String message) {
		super(message);
	}
}
```

Explanation:
- The `StudentNotFoundException` class is a custom exception class that extends the `RuntimeException` class.
- It represents an exception that occurs when a student is not found.
- The class provides two constructors: a default constructor and a parameterized constructor that accepts a message to be displayed when the exception is thrown.

**7. REST Controller (StudentRestController.java):**

```java
package com.app.shivam.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.Student;
import com.app.shivam.exception.StudentNotFoundException;
import com.app.shivam.service.IStudentService

;

@RestController
@RequestMapping("/v1/api/student")
public class StudentRestController {

	@Autowired
	private IStudentService service;

	@PostMapping("/create")
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		Integer id = service.saveStudent(student);
		String message = "STUDENT '" + id + "' CREATED";
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> list = service.getAllStudents();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Student> getOneStudent(@PathVariable("id") Integer id) {
		ResponseEntity<Student> response = null;
		try {
			Student s = service.getOneStudent(id);
			response = ResponseEntity.ok(s);
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
		ResponseEntity<String> response = null;
		try {
			service.deleteStudent(id);
			response = ResponseEntity.ok("STUDENT '" + id + "' REMOVED");
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}

	@PutMapping("/modify")
	public ResponseEntity<String> updateStudent(@RequestBody Student student) {
		ResponseEntity<String> response = null;
		try {
			service.updateStudent(student);
			response = ResponseEntity.ok("STUDENT '" + student.getStdId() + "' UPDATED");
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}
}
```

Explanation:
- The `StudentRestController` class handles the REST API endpoints for student operations.
- It is annotated with `@RestController` to indicate that it is a controller component that handles REST requests.
- The base request mapping is set to `/v1/api/student` for all endpoints.
- The `@Autowired` annotation injects an instance of the `IStudentService` interface into the controller.
- Each endpoint is mapped to a specific HTTP method (`POST`, `GET`, `PUT`, `DELETE`) and a corresponding URL pattern.
- The `@RequestBody` annotation is used to bind the request body to the `Student` object in the `createStudent` and `updateStudent` methods.
- The `@PathVariable` annotation is used to extract the student ID from the URL in the `getOneStudent` and `deleteStudent` methods.
- The methods handle the requests, call the corresponding service methods, and return appropriate `ResponseEntity` objects with the response body and HTTP status.

**8. Global Exception Handler (MyCustomExceptionHandler.java):**

```java
package com.app.shivam.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.shivam.exception.StudentNotFoundException;

@RestControllerAdvice
public class MyCustomExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String> showStudentNotFoundError(StudentNotFoundException snfe) {
		return new ResponseEntity<>(snfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
```

Explanation:
- The `MyCustomExceptionHandler` class is a global exception handler for the application.
- It is annotated with `@RestControllerAdvice` to indicate that it provides advice to the REST controllers.
- The `@ExceptionHandler` annotation is used to specify the type of exception to handle.
- In this case, it handles the `StudentNotFoundException` and returns a `ResponseEntity` with an appropriate error message and HTTP status code.

**Conclusion:**
In this example, we have a Spring Boot application that manages student records. It includes entity classes, a repository interface, service interfaces and implementations, a REST controller, and a global exception handler. These components work together to provide CRUD operations for students via RESTful APIs, interacting with a MySQL database.

<br/>
<br/>
<br/>

# Here's the code and separate explanations for each method in the `StudentRestController` class:

**1. Create One Student**

```java
@PostMapping("/create")
public ResponseEntity<String> createStudent(@RequestBody Student student) {
    Integer id = service.saveStudent(student);
    String message = "STUDENT '" + id + "' CREATED";
    return new ResponseEntity<String>(message, HttpStatus.CREATED);
}
```

Explanation:
- This method handles the `POST` request to create a new student.
- The `@RequestBody` annotation binds the request body to the `Student` object.
- The `saveStudent` method from the `IStudentService` interface is called to save the student in the database.
- The ID of the created student is returned, and a success message is created.
- The message and HTTP status code `201 CREATED` are returned in a `ResponseEntity` object.

**2. Fetch All Students**

```java
@GetMapping("/all")
public ResponseEntity<List<Student>> getAllStudents() {
    List<Student> list = service.getAllStudents();
    return ResponseEntity.ok(list);
}
```

Explanation:
- This method handles the `GET` request to fetch all students.
- The `getAllStudents` method from the `IStudentService` interface is called to retrieve all students from the database.
- The list of students is returned in a `ResponseEntity` object with an HTTP status code `200 OK`.

**3. Fetch One by ID**

```java
@GetMapping("/find/{id}")
public ResponseEntity<Student> getOneStudent(@PathVariable("id") Integer id) {
    ResponseEntity<Student> response = null;
    try {
        Student s = service.getOneStudent(id);
        response = ResponseEntity.ok(s);
    } catch (StudentNotFoundException e) {
        e.printStackTrace();
        throw e;
    }
    return response;
}
```

Explanation:
- This method handles the `GET` request to fetch a single student by ID.
- The ID of the student is extracted from the URL path using the `@PathVariable` annotation.
- The `getOneStudent` method from the `IStudentService` interface is called to retrieve the student from the database.
- If the student is found, it is returned in a `ResponseEntity` object with an HTTP status code `200 OK`.
- If the student is not found, a `StudentNotFoundException` is thrown and propagated with an HTTP status code `500 INTERNAL_SERVER_ERROR`.

**4. Remove One by ID**

```java
@DeleteMapping("/remove/{id}")
public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
    ResponseEntity<String> response = null;
    try {
        service.deleteStudent(id);
        response = ResponseEntity.ok("STUDENT '" + id + "' REMOVED");
    } catch (StudentNotFoundException e) {
        e.printStackTrace();
        throw e;
    }
    return response;
}
```

Explanation:
- This method handles the `DELETE` request to remove a student by ID.
- The ID of the student is extracted from the URL path using the `@PathVariable` annotation.
- The `deleteStudent` method from the `IStudentService` interface is called to delete the student from the database.
- If the student is successfully deleted, a success message is returned in a `ResponseEntity` object with an HTTP status code `200 OK`.
- If the student is not found, a `StudentNotFoundException` is thrown and propagated with an HTTP status code `500 INTERNAL_SERVER_ERROR`.

**5. Update Student**

```java
@PutMapping("/modify")
public ResponseEntity<String> updateStudent(@RequestBody Student student) {
    ResponseEntity<String> response = null;
    try {
        service.updateStudent(student);
        response = ResponseEntity.ok("STUDENT '" + "' UPDATED");
    } catch (StudentNotFoundException e) {
        e.printStackTrace();
        throw e;
    }
    return response;
}
```

Explanation:
- This method handles the `PUT` request to update a student.
- The `@RequestBody` annotation binds the request body to the `Student` object.
- The `updateStudent` method from the `IStudentService` interface is called to update the student in the database.
- If the student is successfully updated, a success message is returned in a `ResponseEntity` object with an HTTP status code `200 OK`.
- If the student is not found, a `StudentNotFoundException` is thrown and propagated with an HTTP status code `500 INTERNAL_SERVER_ERROR`.

These methods provide the necessary endpoints for creating, fetching, updating, and deleting student records through the RESTful API.

<br/>
<br/>

# Let's go through each point and provide a detailed explanation:

**1. 200-OK Request is processed without any exception (no failure/success):**
The HTTP status code `200 OK` indicates that the request has been successfully processed by the server. It is a standard response for a successful HTTP request that does not require any additional actions or information. This status code implies that the request has been understood, accepted, and processed without encountering any errors or exceptions. It indicates a successful outcome for the client's request.

**2. 201-CREATED -- new resource is created at producer side (Used for POST operations):**
The HTTP status code `201 CREATED` is used to indicate that a new resource has been successfully created as a result of the request. It is typically used for POST operations where a new entity or resource is being created and added to the system. The server includes a `Location` header in the response, specifying the URI of the newly created resource.

When the `createStudent` method is called, it saves the provided student entity in the database using the `saveStudent` method from the `IStudentService` interface. If the student is successfully created and saved, the method returns a `ResponseEntity` with the HTTP status code `201 CREATED`. This indicates that the student resource has been successfully created on the server side.

Similarly, when the `deleteStudent` method is called, it deletes the student with the specified ID from the database using the `deleteStudent` method from the `IStudentService` interface. If the deletion is successful, the method returns a `ResponseEntity` with the HTTP status code `200 OK`. This indicates that the student has been successfully removed from the system.

In summary, the `201 CREATED` status code is used when a new resource is created, while the `200 OK` status code is used when a request is processed successfully without any exceptions or failures.


**Based on the provided code,Here provides examples of JSON formats for testing each REST API method along with their expected outputs. Please note that the actual data in the responses may vary depending on the specific data you provide or the data present in your database. Here are the examples:**

**1. Create One Student - POST /v1/api/student/create**
JSON Request Body:
```json
{
  "stdName": "John Doe",
  "stdGen": "Male",
  "stdCourse": "Computer Science",
  "stdAddr": "123 Street, City"
}
```
Expected Output (HTTP Status Code: 201 Created):
```json
"STUDENT '1' CREATED"
```
This response indicates that a new student with ID 1 has been successfully created.

**2. Fetch All Students - GET /v1/api/student/all**
Expected Output (HTTP Status Code: 200 OK):
```json
[
  {
    "stdId": 1,
    "stdName": "John Doe",
    "stdGen": "Male",
    "stdCourse": "Computer Science",
    "stdAddr": "123 Street, City"
  },
  {
    "stdId": 2,
    "stdName": "Jane Smith",
    "stdGen": "Female",
    "stdCourse": "Mathematics",
    "stdAddr": "456 Road, Town"
  }
]
```
This response includes an array of all the student objects present in the database.

**3. Fetch One Student by ID - GET /v1/api/student/find/{id}**
Replace `{id}` with the actual student ID in the URL path.

Example URL: `/v1/api/student/find/1`

Expected Output (HTTP Status Code: 200 OK):
```json
{
  "stdId": 1,
  "stdName": "John Doe",
  "stdGen": "Male",
  "stdCourse": "Computer Science",
  "stdAddr": "123 Street, City"
}
```
This response includes the student object with the specified ID.

**4. Remove One Student by ID - DELETE /v1/api/student/remove/{id}**
Replace `{id}` with the actual student ID in the URL path.

Example URL: `/v1/api/student/remove/1`

Expected Output (HTTP Status Code: 200 OK):
```json
"STUDENT '1' REMOVED"
```
This response indicates that the student with the specified ID has been successfully removed.

**5. Update Student - PUT /v1/api/student/modify**
JSON Request Body:
```json
{
  "stdId": 1,
  "stdName": "John Doe",
  "stdGen": "Male",
  "stdCourse": "Computer Science",
  "stdAddr": "789 Avenue, Village"
}
```
Expected Output (HTTP Status Code: 200 OK):
```json
"STUDENT '1' UPDATED"
```
This response indicates that the student with the specified ID has been successfully updated.

Please note that these examples assume that the data is correctly mapped to the Java objects and stored in the database. The actual output may vary based on the specific data present in your application.

I hope these examples help you understand the JSON formats and expected outputs for testing your REST APIs. Let me know if you have any further questions!