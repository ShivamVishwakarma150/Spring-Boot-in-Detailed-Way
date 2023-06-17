# ReturnType in RestController # Methods

In a RestController, the standard return type for methods is `ResponseEntity`. It represents the entire HTTP response, including the status code, header parameters, and response body. Let's delve into the details:

1. `ResponseEntity<T>`: It is a generic class where `T` represents the data type of the response body. You can specify the desired data type within the angle brackets.

2. Status Code: The status code indicates the result of the HTTP request. It can be set explicitly using the `HttpStatus` enum, which provides a range of predefined status codes such as `HttpStatus.OK`, `HttpStatus.NOT_FOUND`, `HttpStatus.INTERNAL_SERVER_ERROR`, etc.

3. Header Parameters: The header parameters provide additional information about the response. These can include security-related data, token information, certificates, date and time, cookies, and more. You can set and customize the header parameters as needed.

4. Response Body: The response body contains the actual data that is sent back to the client. The data type of the response body can vary depending on the application's requirements. Commonly used types include `String`, custom class types, collections (e.g., `List`, `Map`), and even the wildcard character `?` if the type is determined at runtime.

5. Direct Return Type: While using `ResponseEntity` allows you to provide explicit control over the status code and headers, you can also return the response body directly without wrapping it in a `ResponseEntity`. In this case, the status code and headers will be considered part of the body itself, and you won't have the flexibility to customize them separately.

Using `ResponseEntity` as the return type gives you more control and flexibility in handling the response. You can set specific status codes, customize headers, and encapsulate the response body in a structured manner. However, if you only need to return the response body without modifying the status code or headers, returning the body directly is a simpler approach.

It's important to note that when using `ResponseEntity`, it's considered a best practice to provide the appropriate HTTP status code and header information to ensure a standardized and well-formed response for the clients consuming the API.

<br/>
<br/>

**Q: What is a MultiValueMap and how is it different from a Map?**<br/>
A: A MultiValueMap is a specialized data structure in Spring that extends the functionality of a regular Map. It is used to store key-value pairs where the key is a simple type (such as a primitive) and the value is a collection type, typically a List. The main difference between a MultiValueMap and a regular Map is that a MultiValueMap allows multiple values to be associated with a single key, whereas a regular Map allows only one value per key.

**Q: When should we use ResponseEntity<> and when should we use a direct return type?**<br/>
A: ResponseEntity<> should be used when we want to return a custom HTTP status code, headers, and body. This allows us to have full control over the HTTP response. On the other hand, if we only need to return the body without any custom status code or headers, we can simply return a direct class, String, or collection type, indicating that it represents the response body.

**Q: Can we create a ResponseEntity object without specifying the HttpStatus?**<br/>
A: No, the HttpStatus is a mandatory parameter when creating a ResponseEntity object. It cannot be null or empty because it represents the status code that will be sent in the HTTP response.

**Q: Can we create a ResponseEntity object without specifying the body?**<br/>
A: Yes, the body parameter in a ResponseEntity object can be null or empty. This is useful in cases where we want to send a response with no content, but still need to specify the HTTP status code and headers.

**Q: Can we create a ResponseEntity object without specifying the headers?**<br/>
A: Yes, the headers parameter in a ResponseEntity object can be null or empty. This means that no custom headers will be included in the HTTP response. However, it is possible to include custom headers by providing a non-empty HttpHeaders object when creating the ResponseEntity.

In summary, ResponseEntity allows us to customize the entire HTTP response, including the status code, headers, and body. It provides flexibility in handling different types of responses in Spring REST applications.


<br/>
<br/>

# **Here is the code and its explanation for each class:**

**Code 1: `SpringBootRestReturnTypeEx`**

Entity class - Book.java:
```java
package com.app.shivam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private Integer bookId;
	private String bookName;
	private Double bookCost;
}
```

RestController class - BookRestController.java:
```java
package com.app.shivam.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.Book;

@RestController
@RequestMapping("/book")
public class BookRestController {
	
	@GetMapping("/a")
	public String justBody() {
		return "Hello Book Data!";
	}
	
	@GetMapping("/b")
	public Book justBook() {
		return new Book(10,"AA",200.0);
	}

	@GetMapping("/data")
	public ResponseEntity<String> showInfo() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("MyAppOne", "ACTIVATED-CLIENT");
		String body = "WELCOME TO APP";
		ResponseEntity<String> response = new ResponseEntity<>(body, headers, HttpStatus.OK);
		
		return response;
	}
}
```

Explanation:

- The `Book` class is a simple entity class with three properties: `bookId`, `bookName`, and `bookCost`. It uses Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) for generating getters, setters, constructors, and other common methods.

- The `BookRestController` class is a Spring RestController that handles requests related to books. It is mapped to the `/book` path.

- The `justBody()` method returns a plain string as the response body.

- The `justBook()` method returns a `Book` object as the response body.

- The `showInfo()` method demonstrates the usage of `ResponseEntity`. It creates an instance of `HttpHeaders` and adds a custom header ("MyAppOne") to it. It sets a string ("WELCOME TO APP") as the response body. Finally, it returns a `ResponseEntity` object with the custom headers, body, and HTTP status code (200 OK).

Code 2: BookRestController (Usecase with PathVariable)

```java
package com.app.shivam.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.Book;

@RestController
@RequestMapping("/book")
public class BookRestController {
	
	@GetMapping("/obj/{id}")
	public ResponseEntity<?> findBookById(@PathVariable Integer id) 
	{
		ResponseEntity<?> response = null;
		HttpHeaders headers = new HttpHeaders();
		
		if(id == 501) {
			headers.add("found", "yes");
			
			response = new ResponseEntity<Book>(
					new Book(id, "DUMMY", 500.0),
					headers,
					HttpStatus.OK);
		} else {
			headers.add("found", "no");
			response = new ResponseEntity<String>(
					"Sorry! No Book Found",
					headers,
					HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
}
```

Explanation:

- The `BookRestController` class contains a method named `findBookById` which accepts an `id` as a path variable.

- The method checks if the `id` is equal to 501. If it is, it creates a `ResponseEntity` with a `Book` object as the response body, custom headers ("found" with the value "yes"), and an HTTP status code of 200 OK.

- If the `id` is not equal to 501, it creates a `ResponseEntity` with a string ("Sorry! No Book Found") as the response body, custom headers ("found" with the value "no"), and an HTTP status code of 400 BAD_REQUEST.

- The method returns the created `ResponseEntity` object.

Conclusion:

In the provided code, the `BookRestController` demonstrates the usage of `ResponseEntity` to customize the HTTP response. It showcases different scenarios such as returning a plain string, returning an object as the response body, and customizing headers and HTTP status codes. `ResponseEntity` provides flexibility in constructing and returning custom responses in a Spring Boot REST application.