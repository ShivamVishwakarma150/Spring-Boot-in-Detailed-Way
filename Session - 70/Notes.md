# **Introduction: Spring Bean Validator API**
The Spring Bean Validator API allows you to validate input data in your backend application using annotations. By using these annotations, you can enforce various validation rules and constraints on the input data. To enable this feature in your Spring Boot application, you need to add the Spring Bean Validator (Hibernate Validator API) dependency in your project.

**Adding Spring Bean Validator Dependency:**
To use the Spring Bean Validator API, you need to add the following dependency to your `pom.xml` file:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
This dependency includes the necessary libraries for validation.

**Validation Annotations:**
The validation annotations provided by the Validation API (javax.validation) allow you to define validation rules and constraints for your input data. Here are some commonly used validation annotations:

1. `@NotNull`: This annotation ensures that the annotated element must not be null.

Examples:
```java
@NotNull
private String name;
```

2. `@NotEmpty`: This annotation ensures that the annotated string must not be null or empty.

Examples:
```java
@NotEmpty
private String email;
```

3. `@NotBlank`: This annotation ensures that the annotated string must not be null, empty, or contain only whitespace characters.

Examples:
```java
@NotBlank
private String address;
```

4. `@Size`: This annotation allows you to specify the minimum and maximum size for a string.

Examples:
```java
@Size(min = 5, max = 10)
private String password;
```

5. `@Min` and `@Max`: These annotations specify the minimum and maximum values for numeric types (int, double, etc.).

Examples:
```java
@Min(18)
private int age;

@Max(100)
private double weight;
```

6. `@Pattern`: This annotation allows you to specify a regular expression pattern that the annotated string must match.

Examples:
```java
@Pattern(regexp = "[A-Za-z0-9]+")
private String username;
```

7. `@Past` and `@Future`: These annotations are used for Date types and ensure that the date is in the past or future, respectively.

Examples:
```java
@Past(message = "DOB is invalid")
private Date dateOfBirth;

@Future(message = "Event date is invalid")
private Date eventDate;
```

8. `@AssertTrue` and `@AssertFalse`: These annotations are used for boolean types and expect the value to be true or false, respectively.

Examples:
```java
@AssertTrue
private boolean agreedToTerms;

@AssertFalse
private boolean declinedMarketing;
```

9. `@Email`: This annotation validates that the annotated string is a valid email address.

Example:
```java
@Email
private String email;
```

**@Valid Annotation:**
The `@Valid` annotation is used to activate the validation API and execute the above annotations in order. It is typically used to validate nested objects or collections.

Example:
```java
@Valid
private Address address;
```

If any of the validation rules fail, Spring will return an HTTP status code 400 (Bad Request) indicating that the input data is invalid.

By using these validation annotations, you can enforce data integrity and ensure that your application receives valid input. This helps in preventing errors and maintaining the consistency and reliability of your application.

<br/>
<br/>

## **Here is the updated code snippet for the `Student` entity class and the `StudentRestController` in the RestController:**

1. **Updated `Student` Entity Class:**
```java
package com.app.shivam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@NotBlank(message="STUDENT NAME CAN NOT BE EMPTY")
	@Size(min = 2, max = 6, message = "NAME MUST BE 2-6 CHARS ONLY")
	@Pattern(regexp = "[A-Za-z]{2,6}", message = "Only A-Z/a-z are allowed")
	private String stdName;
	
	@Column(name="sgen")
	@NotBlank(message="STUDENT GENDER CAN NOT BE EMPTY")
	private String stdGen;
	
	@Column(name="scourse")
	@NotBlank(message="STUDENT COURSE MUST BE SELECTED")
	private String stdCourse;
	
	@Column(name="saddr")
	@Pattern(regexp = "[A-Za-z0-9\\.\\-\\?]{10,250}", message = "INVALID ADDRESS DETAILS")
	private String stdAddr; 
}
```

In the updated `Student` entity class, we have added validation annotations to enforce constraints on the student data. Here is a breakdown of the annotations used:

- `@NotBlank`: This annotation ensures that the `stdName`, `stdGen`, and `stdCourse` fields must not be empty or contain only whitespace characters.
- `@Size`: This annotation specifies that the `stdName` field must have a length between 2 and 6 characters.
- `@Pattern`: This annotation validates that the `stdName` field only contains alphabetic characters (both lowercase and uppercase).
- `@Pattern`: This annotation validates that the `stdAddr` field follows a specific pattern using a regular expression. It allows alphanumeric characters, dots, dashes, and question marks and must have a length between 10 and 250 characters.

2. **Updated `StudentRestController` in RestController:**
```java
package com.app.shivam.rest;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.shivam.entity.Student;

@RestController
@RequestMapping("/v1/api/student")
public class StudentRestController {

	@PostMapping("/create")
	public ResponseEntity<String> createStudent(@RequestBody @Valid Student student) {
		// Your code for saving the student goes here
		return new ResponseEntity<String>("STUDENT CREATED", HttpStatus.CREATED);
	}
}
```

In the `StudentRestController`, we have added the `@Valid` annotation to the `student` parameter of the `createStudent` method. This annotation is used to activate the validation process and apply the validation rules specified in the `Student` entity class.

**Explanation:**

By adding validation annotations to the `Student` entity class, we define the constraints and rules for the input data. These annotations enable the validation process to check if the provided data meets the defined criteria. Here's an explanation of the validation annotations used:

- `@NotBlank` ensures that the specified fields (`stdName`, `stdGen`, and `stdCourse`) must not be empty or contain only whitespace characters.
- `@Size` specifies that the `stdName` field must have a length between 2 and 6 characters.
- `@Pattern` validates that the `stdName` field only contains alphabetic characters (both lowercase and uppercase) using the specified regular expression.
- `@Pattern` validates that the `stdAddr` field matches the specified pattern, allowing alphanumeric characters, dots, dashes, and question marks. The field must have a length between 10 and 250 characters.

In the `StudentRestController` class, when the `createStudent` method is called, the `@Valid` annotation triggers the validation process for the `student` object. If any of the validation rules fail, an exception will be thrown automatically by Spring, and the appropriate error response will be returned.

**Conclusion:**

By using the Spring Bean Validator API and the validation annotations provided by the Validation API, you can enforce data integrity and ensure that the input data meets the defined constraints. With these annotations, you can validate and handle input errors effectively, improving the overall reliability and stability of your application.

Remember to handle the validation exceptions appropriately to provide meaningful error responses to the client. By utilizing validation annotations, you can minimize the manual validation effort and leverage the power of Spring's validation framework.

<br/>
<br/>

**The difference between the old code and the new code in the RestController is the addition of the `@Valid` annotation. Let's understand what this change means:**

1. **Old Code**:
```java
@RequestBody Student student
```
In the old code, the `@RequestBody` annotation is used to bind the HTTP request body to the `Student` object in the method parameter. It allows you to extract the request payload and convert it into an instance of the `Student` class.

2. **New Code**:
```java
@RequestBody @Valid Student student
```
In the new code, the `@Valid` annotation is added in addition to `@RequestBody`. This change indicates that the `Student` object should be validated based on the validation constraints defined in the `Student` class. The `@Valid` annotation triggers the validation process for the `Student` object.

By using `@Valid`, you enable the validation of the `Student` object against any validation rules defined using annotations such as `@NotNull`, `@Size`, `@Pattern`, etc., on the properties of the `Student` class. This helps to enforce data integrity and ensure that the received data meets the specified constraints.

For example, if the `Student` class has a property annotated with `@NotNull`, and the request payload doesn't provide a value for that property or provides a null value, the validation will fail, and an appropriate validation error will be returned to the client.

Adding the `@Valid` annotation is a good practice to ensure that the incoming data is validated before processing it further in the controller method. It helps in maintaining data consistency and avoiding potential issues due to invalid or unexpected data.

Note: To enable validation in your Spring application, make sure you have the necessary dependencies (such as Hibernate Validator) on your classpath, and the required configuration is in place.

If you have any more questions or need further clarification, feel free to ask!