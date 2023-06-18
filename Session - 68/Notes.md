# **RestTemplate (Http Client)**

The `RestTemplate` class in Spring Framework provides a versatile method called `exchange()` that can be used to send HTTP requests using various methods such as GET, POST, PUT, DELETE, etc. This method offers flexibility in handling different HTTP methods and allows customization of request headers, request bodies, and response handling.

The signature of the `exchange()` method is as follows:

```java
public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) throws RestClientException
```

Let's break down the parameters of the `exchange()` method:

- `url`: The URL to which the HTTP request is sent.
- `method`: The HTTP method to be used, such as `HttpMethod.GET`, `HttpMethod.POST`, etc.
- `requestEntity`: An instance of `HttpEntity` that represents the request body and headers. It can be used to set custom headers, add a request body, or both.
- `responseType`: The expected response type, represented by a `Class` object. This is used to specify how the response body should be deserialized.

The `exchange()` method returns a `ResponseEntity<T>`, where `T` represents the response body type. It encapsulates the entire HTTP response, including the response status, headers, and body. You can access these details using methods like `getBody()`, `getHeaders()`, `getStatusCode()`, etc.

By using the `exchange()` method, you can perform various HTTP operations with RestTemplate and handle the responses in a customized manner.

<br/>

```java
    exchange(
    url,
    HttpMethod,
    HttpEntity(request),
    ResponseType.class,
    pathVariables...
    ): ResponseEntity<T>
```

The `exchange()` method in `RestTemplate` allows you to make HTTP requests with flexible parameters and supports all HTTP methods. Here is the signature of the `exchange()` method:

```java
public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) throws RestClientException
```

Let's go through the parameters in detail:

- `url`: The URL to which the HTTP request is sent.
- `method`: The HTTP method to be used, such as `HttpMethod.GET`, `HttpMethod.POST`, etc.
- `requestEntity`: An instance of `HttpEntity` that represents the request body and headers. It can be used to set custom headers, add a request body, or both.
- `responseType`: The expected response type, represented by a `Class` object. This is used to specify how the response body should be deserialized.
- `uriVariables` (optional): Path variables to be substituted in the URL, if any.

The `exchange()` method returns a `ResponseEntity<T>`, where `T` represents the response body type. It encapsulates the entire HTTP response, including the response status, headers, and body. You can access these details using methods like `getBody()`, `getHeaders()`, `getStatusCode()`, etc.

Here's an example usage of the `exchange()` method:

```java
ResponseEntity<User> response = restTemplate.exchange(
  "https://api.example.com/users/{id}",
  HttpMethod.GET,
  null,
  User.class,
  123
);
```

In this example, a GET request is made to the URL `https://api.example.com/users/{id}`, where `{id}` is a path variable. The response is expected to be deserialized into a `User` object. The `exchange()` method returns a `ResponseEntity<User>`, which can be used to access the response data.

By using the `exchange()` method, you have more control over the HTTP request and response, including specifying the HTTP method, setting request headers and body, defining the response type, and handling path variables.

<br/>
<br/>
<br/>

## **Introduction:**
When working with Spring Boot applications that consume RESTful APIs, the `RestTemplate` class is commonly used as an HTTP client. It provides convenient methods to make HTTP requests and handle responses. In certain scenarios, you may need more flexibility in terms of specifying the HTTP method, request body, headers, and response type. In such cases, the `exchange()` method of `RestTemplate` can be used.

**Code 1: Replacing `postForEntity()` with `exchange()` for a POST request:**

```java
// Old Line
ResponseEntity<String> response = template.postForEntity(url, requestEntity, String.class);

// New Line
ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, requestEntity, String.class);
```

Explanation:
Instead of using the `postForEntity()` method, the code is now using the `exchange()` method to perform a POST request. The `exchange()` method allows you to explicitly specify the HTTP method as `HttpMethod.POST`. The `requestEntity` parameter contains the request body and headers, while `String.class` specifies the response type as `String`. This change provides more flexibility in handling different HTTP methods.

**Code 2: Replacing `getForEntity()` with `exchange()` for a GET request:**

```java
// Old Line
ResponseEntity<String> response = template.getForEntity(url, String.class, 101, "ABC");

// New Line
ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, null, String.class, 101, "ABC");
```

Explanation:
Similar to the POST request, the code is now using the `exchange()` method to perform a GET request. The `exchange()` method is called with the URL, `HttpMethod.GET`, `null` (since there is no request body), `String.class` as the response type, and the path variables (`101` and `"ABC"`). This change allows you to explicitly specify the HTTP method and handle GET requests in a more flexible way.

Conclusion:
By using the `exchange()` method of `RestTemplate`, you have more control over the HTTP method, request body, headers, and response type. It provides greater flexibility in handling different types of requests and responses within your consumer application. Additionally, in cases where there are no direct methods for PUT and DELETE requests that return `ResponseEntity`, the `exchange()` method can be used to handle these operations effectively.

<br/>
<br/>
<br/>

The `RestTemplate` class in Spring does not provide direct methods for PUT and DELETE requests that return `ResponseEntity`. However, you can still perform PUT and DELETE requests using the `exchange()` method, which offers more flexibility in handling these operations.

When making a PUT or DELETE request using `RestTemplate`, you can use the `exchange()` method with the appropriate HTTP method (`HttpMethod.PUT` or `HttpMethod.DELETE`) and provide the necessary parameters. Here's an example:

```java
// PUT request
ResponseEntity<String> response = template.exchange(url, HttpMethod.PUT, requestEntity, String.class);

// DELETE request
ResponseEntity<String> response = template.exchange(url, HttpMethod.DELETE, null, String.class);
```

In the PUT request example, you provide the URL, `HttpMethod.PUT`, the `requestEntity` containing the request body and headers, and the response type (`String.class`). This allows you to perform a PUT request and receive a `ResponseEntity` with the desired response information.

In the DELETE request example, you provide the URL, `HttpMethod.DELETE`, `null` (since there is no request body), and the response type (`String.class`). This enables you to perform a DELETE request and obtain a `ResponseEntity` for further processing.

By using the `exchange()` method for PUT and DELETE requests, you have the flexibility to handle the requests and responses according to your specific requirements, even though there are no direct methods available in `RestTemplate` for these operations.

<br/>
<br/>

# Here's the code for the producer application and consumer application, along with their explanations:

**1. Producer Application:**

```java
@RestController
@RequestMapping("/v1/api/book")
public class BookRestController {

	@PutMapping("/showE")
	public ResponseEntity<String> showMsg5(@RequestBody Book book) {
		return ResponseEntity.ok("Data FROM PUT is " + book);
	}
	
	@DeleteMapping("/showF/{id}")
	public ResponseEntity<String> showMsg6(@PathVariable("id") Integer id) {
		return ResponseEntity.ok("DELETE MAPPING " + id);
	}
}
```

In the producer application, we have a `BookRestController` class with two additional methods, `showMsg5` and `showMsg6`, annotated with `@PutMapping` and `@DeleteMapping`, respectively. These methods handle PUT and DELETE requests for the specified URLs.

**2. Consumer Application:**

```java
public class PutTestARunner implements CommandLineRunner {

	public void run(String... args) throws Exception {
		String url = "http://localhost:8080/v1/api/book/showE";
		String body = "{\"bid\" : 101,\"bname\" : \"ABC\", \"bauth\":\"AJ\", \"bcost\":500.0}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
		System.out.println(response.getBody());
		System.out.println(response.getHeaders());
		System.out.println(response.getStatusCode().name());
		System.out.println(response.getStatusCode().value());
	}
}

public class DeleteTestARunner implements CommandLineRunner {

	public void run(String... args) throws Exception {
		String url = "http://localhost:8080/v1/api/book/showF/{id}";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class, 101);
		System.out.println(response.getBody());
		System.out.println(response.getHeaders());
		System.out.println(response.getStatusCode().name());
		System.out.println(response.getStatusCode().value());
	}
}
```

In the consumer application, we have two runner classes. The `PutTestARunner` class performs a PUT request using the `exchange()` method of `RestTemplate`. It creates the necessary URL, headers, and body for the request and sends it to the producer application. The response is then printed to the console.

The `DeleteTestARunner` class performs a DELETE request using the `exchange()` method. It also creates the URL with a path variable for the request and sends it to the producer application. The response is printed to the console as well.

Overall, these examples demonstrate how to use the `exchange()` method in the consumer application to handle PUT and DELETE requests when there are no direct methods available in `RestTemplate` for these operations.

In conclusion, the `exchange()` method provides a flexible way to perform PUT and DELETE requests and retrieve `ResponseEntity` objects. It allows you to specify the URL, HTTP method, request entity (including body and headers), response type, and any path variables if required. This versatility makes it suitable for handling various scenarios where direct methods are not available or additional customization is needed.

<br/>
<br/>

# Implicit Type Conversion

In the context of using `RestTemplate`, implicit type conversion refers to the ability to automatically convert the response body, which can be in various formats such as JSON, XML, HTML, etc., into a specific Java object type.

By default, when making a request using `RestTemplate`, you can store the response content in a `String` object, regardless of the actual content type. This means that the response body, regardless of whether it is JSON, XML, or any other format, will be treated as a string and can be accessed as such.

However, `RestTemplate` provides additional flexibility by allowing you to specify a specific Java object type to which you want the response body to be converted. Instead of using `String.class`, you can specify a custom class, such as `Employee.class`, that matches the structure of the response content.

When you provide a specific type, such as `Employee.class`, `RestTemplate` internally performs a comparison between the keys (property names) in the response body and the fields in the `Employee` class. If the keys match the field names in the class, `RestTemplate` automatically converts the response body (e.g., JSON or XML) into an instance of the specified Java class (`Employee` in this case).

This implicit type conversion simplifies the process of parsing and deserializing the response body into a Java object. It eliminates the need to manually parse the response content and map it to the desired object structure. Instead, `RestTemplate` handles the conversion process based on the matching field names, allowing you to work directly with the converted object.

Note that for this implicit type conversion to work correctly, the field names in the Java class (`Employee` in this example) should match the corresponding keys in the response body. If there is a mismatch in the names, the conversion may not be successful, and you may encounter errors or unexpected behavior.

Overall, implicit type conversion in `RestTemplate` allows you to seamlessly convert the response body into a specific Java object type, simplifying the process of working with structured data returned from API endpoints.

<br/>
<br/>

# Here's the code and its explanation for the producer and consumer applications:

**Producer Application:**
```java
@RestController
@RequestMapping("/v1/api/book")
public class BookRestController {

	@GetMapping("/showC")
	public ResponseEntity<Book> showMsg3() {
	   return ResponseEntity.ok(new Book(101, "ABC", "RAJ", 500.0));
	}
}

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
In the producer application, we have a `BookRestController` class that handles HTTP requests related to books. Inside this class, there is a `showMsg3()` method annotated with `@GetMapping` and `/showC` mapping. This method returns a `ResponseEntity<Book>`, indicating that it will produce a response with the `Book` object as the body. In this case, the method simply creates a new `Book` object with some sample values and returns it within a `ResponseEntity` with an HTTP 200 (OK) status.

The `Book` class is a simple POJO (Plain Old Java Object) representing a book. It has fields for `bid` (book ID), `bname` (book name), `bauth` (book author), and `bcost` (book cost). The class is annotated with Lombok annotations `@Data`, `@NoArgsConstructor`, and `@AllArgsConstructor` to generate getter/setter methods, constructors, and other boilerplate code automatically.

**Consumer Application:**
```java
@Component
public class GetTestBRunner implements CommandLineRunner {

	public void run(String... args) throws Exception {
		String url = "http://localhost:8080/v1/api/book/showC";
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<BookAtConsumer> response = template.exchange(url, HttpMethod.GET, null, BookAtConsumer.class); 

		System.out.println(response.getBody());
		System.out.println(response.getHeaders());
		System.out.println(response.getStatusCode().name());
		System.out.println(response.getStatusCode().value());	
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAtConsumer {
	private Integer bid;
	private String bname;
	private String bauth;
	private Double bcost;
}
```
Explanation:
In the consumer application, we have a `GetTestBRunner` class annotated with `@Component` to be managed as a Spring bean. This class implements `CommandLineRunner`, allowing us to execute code when the application starts. In the `run()` method, we perform an HTTP GET request to the `/showC` endpoint of the producer application.

We create a `RestTemplate` object to make the HTTP request. Using the `exchange()` method, we specify the URL, HTTP method (GET), request entity (null as there is no request body), and the desired response type (`BookAtConsumer.class`). This method returns a `ResponseEntity<BookAtConsumer>`.

We can access the response body, headers, and status code from the `ResponseEntity`. In this example, we simply print them to the console.

The `BookAtConsumer` class in the consumer application is similar to the `Book` class in the producer application. The fields in `BookAtConsumer` match the fields in `Book`, allowing RestTemplate to perform implicit type conversion from the response body (in JSON format) to the `BookAtConsumer` object.

Conclusion:
The producer application exposes an endpoint `/showC` that returns a `Book` object as the response body. The consumer application uses `RestTemplate` to make an HTTP GET request to this endpoint and retrieve the response. By specifying the desired response type (`BookAtConsumer.class`), RestTemplate automatically converts the response body (in JSON format) to the `BookAtConsumer` object, allowing us to work with it directly.

This demonstrates how RestTemplate handles implicit type conversion, enabling seamless conversion of response content to specific Java object types, simplifying the process of working with structured data in RESTful API communication.

<br/>
<br/>

# **Note**

in the consumer application, the class name doesn't need to be the same as the producer application. However, it's important that the variable names in the consumer class match the variable names in the producer class. This is necessary for RestTemplate to perform the implicit type conversion correctly.

RestTemplate uses Java Bean naming conventions to map the JSON properties to the corresponding fields in the Java class. It relies on the getter and setter methods provided by the class to access and set the field values.

For example, in the consumer application's `BookAtConsumer` class, the variable names (`bid`, `bname`, `bauth`, `bcost`) should match the variable names in the producer's `Book` class (`bid`, `bname`, `bauth`, `bcost`). Additionally, the `BookAtConsumer` class should have the necessary getter and setter methods for these variables.

By ensuring that the variable names and getter/setter methods match, RestTemplate can accurately convert the response body from JSON to the desired Java object, allowing seamless integration between the producer and consumer applications.