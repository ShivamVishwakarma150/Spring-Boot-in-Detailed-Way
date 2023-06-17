# Notes

1. Request Parameter in URL: Request parameters are used to pass data from the client to the server as part of the URL. In the URL, parameters are specified as key-value pairs, separated by ampersands (&).

2. Example URL: The provided example URL is `http://localhost:9090/employee/find?id=10&name=A`. It includes two request parameters: `id` with a value of `10` and `name` with a value of `A`.

3. Servlet Example: In plain Java Servlets, you can retrieve request parameters using the `request.getParameter("key")` method. This method returns the value of the specified parameter as a String. To convert the String value to the desired data type, such as an integer, you can use appropriate conversion methods like `Integer.parseInt()`.

4. Spring @RequestParam: In Spring Boot, you can use the `@RequestParam` annotation to bind request parameters to method parameters in a controller method. The `@RequestParam` annotation provides a way to specify the name of the request parameter and the data type of the corresponding method parameter.

5. Syntax: The syntax for using `@RequestParam` is as follows:
   ```java
   @RequestMapping("/endpoint")
   public ResponseEntity<String> methodName(@RequestParam("key") DataType variableName) {
       // Method implementation
   }
   ```

6. Example Usage: Taking your example into consideration, here's how you can use `@RequestParam`:
   ```java
   @GetMapping("/find")
   public ResponseEntity<String> findEmployee(
       @RequestParam("id") int id,
       @RequestParam("name") String name
   ) {
       // Process the request parameters
       // ...
       return ResponseEntity.ok("Employee ID: " + id + ", Employee Name: " + name);
   }
   ```

7. Binding Parameters: With `@RequestParam`, the request parameters are bound to the method parameters based on their names. In the example, the `id` parameter will be assigned the value of the `id` request parameter, and the `name` parameter will be assigned the value of the `name` request parameter.

8. Data Type Conversion: The `@RequestParam` annotation automatically performs type conversion for the method parameters. In the example, the `id` parameter is defined as an `int`, so Spring will attempt to convert the corresponding request parameter value to an integer.

9. Importing Classes: To use `@RequestParam`, make sure to import the necessary classes, such as `org.springframework.web.bind.annotation.RequestParam` and `org.springframework.http.ResponseEntity`, in your code.

By following these guidelines, you can easily retrieve request parameters using `@RequestParam` in Spring Boot and handle them in your controller methods.

<br/>
<br/>

**PathVariable: Sending data along with URL as Path**

- PathVariable Usage:
  - Path variables are used to send data along with the URL as part of the path itself.
  - They provide a clean and meaningful way to structure URLs and send data without using symbols like `?` and `&`.

- Clean URLs:
  - Incorporating data directly into the URL path makes it more descriptive and human-readable.
  - Enhances the overall user experience by providing clean URLs.

- URL Length and Size:
  - Using path variables can help reduce the length and size of URLs compared to query strings.
  - Beneficial in cases where there are restrictions on URL length imposed by browsers or server configurations.

- No Overloaded Symbols:
  - Path variables eliminate the need for overloaded symbols like `?` and `&` to separate query parameters.
  - Reduces the burden on the server when parsing the URL and makes it easier to interpret the data.

- Data/Path Sending Order:
  - Path variables follow a specific order for sending data based on the structure of the path.
  - The order is determined by the sequence of dynamic parts in the path.

**Path Creation:**

- To create a path with path variables, use the `@GetMapping` (or other appropriate) annotation.
- Combine static and dynamic parts to form the path, with dynamic parts denoted by curly braces `{}`.

**Data Reading:**

- Use the `@PathVariable` annotation to bind the values of path variables to method parameters.
- Values are extracted from the corresponding parts of the URL path based on the variable names specified in the annotation.

**Data Type Conversion:**

- Automatic type conversion is performed for method parameters annotated with `@PathVariable`.
- Spring attempts to convert the corresponding path variable values to the specified parameter types.

**Importing Classes:**

- Make sure to import the necessary classes, such as `org.springframework.web.bind.annotation.PathVariable` and `org.springframework.http.ResponseEntity`, to use `@PathVariable` and handle the response, respectively.

By leveraging path variables, you can send data along with the URL in a structured and clean manner, improving the readability and maintainability of your RESTful APIs.

<br/>
<br/>

====================code============================================
**Name : SpringBootRestPathVariableEx**
**Dep  : Web, Lombok, Devtools**
**Code: ProductRestController**

```java
package com.app.shivam.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductRestController {

	// Using RequestParam
	// .../dataa?pid=10&pname=A
	@GetMapping("/dataa")
	public ResponseEntity<String> showDataA(
			@RequestParam("pid") Integer id,
			@RequestParam("pname") String name
			)
	{
		System.out.println("PID " + id +" PNAME "+ name);
		return new ResponseEntity<>("PID " + id +" PNAME "+ name,HttpStatus.OK);
	}

	// Using PathVariable
	// .../datab/10/A
	@GetMapping("/datab/{pid}/{pname}")
	public ResponseEntity<String> showDataB(
			@PathVariable("pid") Integer id,
			@PathVariable("pname") String name
			) 
	{
		System.out.println("PID " + id +" PNAME "+ name);
		return new ResponseEntity<>("PID " + id +" PNAME "+ name,HttpStatus.OK);
	}
}
```

**Explanation:**

1. `ProductRestController` is a REST controller class responsible for handling requests related to products.
2. It is annotated with `@RestController` to indicate that it's a RESTful controller providing web services.
3. The base URL for this controller is set to `/product` using the `@RequestMapping` annotation.

**Endpoint: showDataA**
- This endpoint uses the `@GetMapping` annotation to handle GET requests at the `/dataa` path under the `/product` base URL.
- It accepts two request parameters, `pid` and `pname`, using the `@RequestParam` annotation.
- The values of these parameters are bound to the `id` and `name` method parameters, respectively.
- The method prints the received values and returns a `ResponseEntity` with the response message.

**Endpoint: showDataB**
- This endpoint uses the `@GetMapping` annotation to handle GET requests at the `/datab/{pid}/{pname}` path under the `/product` base URL.
- It accepts two path variables, `pid` and `pname`, using the `@PathVariable` annotation.
- The values of these path variables are bound to the `id` and `name` method parameters, respectively.
- The method prints the received values and returns a `ResponseEntity` with the response message.

**Request URLs and Outputs:**

1. Request URL: `http://localhost:9090/product/datab/101/MNO`
   - Output: `PID 101 PNAME MNO`

2. Request URL: `http://localhost:9090/product/datab/222/111`
   - Output: `PID 222 PNAME 111`

3. Request URL: `http://localhost:9090/product/datab/MNO/888`
   - Output: `PID __ PNAME __` (Values not provided for path variables)

4. Request URL: `http://localhost:9090/product/dataa?pid=10&pname=A`
   - Output: `PID 10 PNAME A`

5. Request URL: `http://localhost:9090/product/dataa?pname=AB&pid=101`
   - Output: `PID 101 PNAME AB`

**Conclusion:**

The `ProductRestController` class demonstrates the usage of path variables and request parameters in Spring Boot applications. Path variables provide a clean and structured way to send data as part of the URL path, while request parameters allow sending data in a query string format. Both approaches offer flexibility and convenience for handling different types of requests.

<br/>
<br/>

**Q) Which one is better to use, PathVariable or RequestParam?**

A) PathVariable is generally considered better to use over RequestParam in certain scenarios. Here are some reasons why PathVariable is preferred:

1. Clean URL: PathVariables allow for cleaner URLs without symbols like "?" and "&". The data is directly embedded in the URL path, making it more readable and user-friendly.

2. URL Length and Size: PathVariables help reduce the length and size of the URL. With RequestParam, the parameters are appended to the URL as a query string, which can make the URL unnecessarily long and bulky.

3. No Processing Time: When using PathVariables, there is no processing time required to parse the query string and extract the parameter values. The values are directly available in the method's parameters, resulting in faster processing.

4. Follows Data/Path Sending Order: PathVariables follow the natural order of the data being sent. For example, if you have a URL like `/employee/find/{id}/{name}`, the path clearly indicates that `id` comes before `name`. This improves readability and ensures consistency in the data being sent.

However, it's important to note that the choice between PathVariable and RequestParam depends on the specific use case and requirements of your application. RequestParam can still be useful when dealing with optional or variable parameters, or when you need to handle complex query strings. Ultimately, you should consider the context and purpose of your application to determine the most appropriate approach to use.

<br/>
<br/>


**JAVA EE : Supports only RequestParam, No PathVariable.It exist in Spring Boot apps :**

In Java EE, the traditional Java Enterprise Edition platform, the use of PathVariable is not available natively. The primary way to handle request parameters in Java EE is through the use of RequestParam. RequestParam allows you to retrieve query parameters from the URL's query string using the HttpServletRequest object.

However, it's important to note that in modern Java frameworks like Spring Boot, which is built on top of Java EE, the support for PathVariable is available. Spring Boot provides a more convenient and flexible way to handle RESTful APIs, including the use of PathVariable to extract values from the URL path directly.

<br/>
<br/>


**In fullstack/Rest based apps PathVariable is used :**

In full-stack and REST-based applications, it is common to use PathVariable. This is because RESTful principles advocate for clean and meaningful URLs that represent resources and their identifiers. PathVariable allows you to embed the data directly into the URL path, making it more readable, logical, and easier to understand.

By using PathVariable, you can create URLs that reflect the structure and hierarchy of your application's resources. It provides a more intuitive and expressive way to define endpoints and retrieve specific data by mapping them to corresponding path segments.

In summary, while Java EE primarily supports RequestParam for handling request parameters, modern frameworks like Spring Boot extend the capabilities by providing PathVariable for more efficient and cleaner URL handling. In full-stack and REST-based applications, PathVariable is often preferred due to its advantages in terms of clean URLs, improved readability, and adherence to RESTful principles.

<br/>
<br/>

# Note

In Spring Boot, you can use both PathVariable and RequestParam to handle request parameters based on your specific needs.

RequestParam allows you to retrieve query parameters from the URL's query string. These parameters are typically appended to the URL after the question mark (`?`) and separated by ampersands (`&`). The values of the parameters are used to provide additional information to the server. In the example URL you provided:

```
https://www.google.com/search?q=sachin+tendulkar&sxsrf=ALiCzsZBVp76CI2yhnmxjbUGyPbMNMyotQ%3A1668650417775&source=hp&ei=sZV1Y6bJLMe3z7sPvtWYqAM&iflsig=AJiK0e8AAAAAY3Wjwf8qaKKi4GIF9p
```

The query parameters are `q`, `sxsrf`, `source`, `ei`, and `iflsig`, and their respective values are `sachin+tendulkar`, `ALiCzsZBVp76CI2yhnmxjbUGyPbMNMyotQ%3A1668650417775`, `hp`, `sZV1Y6bJLMe3z7sPvtWYqAM`, and `AJiK0e8AAAAAY3Wjwf8qaKKi4GIF9p`.

On the other hand, PathVariable allows you to extract values directly from the URL path itself. This is useful when you want to include dynamic values as part of the URL structure. PathVariable is commonly used for RESTful APIs, where the URL represents resources and their identifiers.

In Spring Boot, you can choose to use either RequestParam or PathVariable based on the requirements of your application. If you need to retrieve query parameters from the URL, RequestParam is the appropriate choice. If you want to extract values from the URL path, you can use PathVariable.

Both options are supported in Spring Boot, allowing you to handle request parameters in a flexible and convenient manner.

<br/>
<br/>

# **Header Params**

HeaderParams, also known as HTTP headers, are used to exchange instructions or additional data between the browser and server during HTTP communication. They contain key-value pairs and provide essential information related to security, authentication, content negotiation, and more. Here are some important points to understand about HeaderParams:

- Format: HeaderParams consist of a key-value pair, where both the key and value are strings. The key represents the name of the header, and the value contains the associated data.

- Pre-defined headers: HTTP defines various standard headers that serve specific purposes. These headers include information such as Accept, Content-Type, Authorization, Cookies, Date and Time, Cache-Control, User-Agent, and many more. You can refer to the Mozilla Developer Network (MDN) documentation for a comprehensive list of HTTP headers and their descriptions.

- Custom headers: In addition to the pre-defined headers, you can also include your own custom headers in the HTTP request or response. This allows you to pass specific information that is relevant to your application or domain.

- Reading header data: In Spring Boot, you can retrieve the values of header parameters using the `@RequestHeader` annotation. By specifying the key of the header as the annotation value and providing the desired data type for the variable, you can access the corresponding header value within your controller method.

Here are a few examples of commonly used headers:

1. Authorization Header: Used to send authentication credentials or tokens, such as JWT (JSON Web Tokens), to authorize and authenticate the user making the request.

2. Accept Header: Indicates the preferred content type(s) that the client can accept in the response. It allows the server to choose the appropriate representation, such as JSON, XML, or plain text, based on the client's preferences.

3. Content-Type Header: Specifies the format of the request or response payload. It informs the server about the data format being sent in the request body or the format expected in the response.

4. Cookies Header: Contains HTTP cookies that are sent by the server and stored by the client. Cookies allow the server to maintain stateful information and track user sessions.

Using HeaderParams, you can enhance the communication between the client (browser) and the server by providing additional instructions, security-related data, content preferences, and more. It offers flexibility in exchanging information beyond the standard URL and request body parameters.

<br/>
<br/>

# Here's the code for the `ProductRestController` class:

```java
package com.app.shivam.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @PostMapping("/details")
    public ResponseEntity<String> showHeaders(
            @RequestHeader("Content-Type") String type,
            @RequestHeader("Content-Length") String len,
            @RequestHeader("Authorization") String auth,
            HttpServletRequest req
    ) {
        System.out.println(auth);
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String hdr = headerNames.nextElement();
            System.out.println(hdr + " " + req.getHeader(hdr));
        }
        System.out.println("Data " + type + " - " + len);
        return new ResponseEntity<>("CHECK", HttpStatus.OK);
    }
}
```

Explanation:
- The `ProductRestController` class is annotated with `@RestController` to indicate that it is a REST controller responsible for handling HTTP requests.
- The base path for all the endpoints defined in this controller is set as `/product` using the `@RequestMapping` annotation at the class level.
- The `showHeaders` method is mapped to the `/details` endpoint using the `@PostMapping` annotation, indicating that it handles HTTP POST requests to that URL.

Inside the `showHeaders` method:
- The method takes several `@RequestHeader` parameters to retrieve specific header values from the request. In this example, we are extracting the values of headers such as `Content-Type`, `Content-Length`, and `Authorization`.
- The `HttpServletRequest` parameter is used to access the entire request object and retrieve all the headers using the `getHeaderNames` method.
- The method then iterates over the header names using a `while` loop and retrieves the corresponding header values using `req.getHeader(hdr)`.
- Finally, the method prints the extracted `Authorization` header value, all the headers, and the values of `Content-Type` and `Content-Length` to the console.

This code demonstrates how to retrieve and work with header parameters in a Spring Boot application. It shows how to access specific headers using the `@RequestHeader` annotation and how to access all headers using the `HttpServletRequest` object. The retrieved header values can be used for various purposes, such as authentication, content negotiation, or additional processing based on specific header values.

<br/>
<br/>

## **Dynamic ReturnType Selection based on workflow (condition) Generics `(Java 1.5)` -` wild card character (?)`**

The provided code demonstrates the usage of generics and the wild card character (`?`) in the return type of the `showInfo()` method. Let's break it down:

```java
public ResponseEntity<?> showInfo() {
    if (new Random().nextInt() > 0) {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    } else {
        return new ResponseEntity<Integer>(101, HttpStatus.OK);
    }
}
```

In this code:
- The `showInfo()` method returns a `ResponseEntity<?>`, where the question mark (`?`) represents a wildcard type. This allows the method to have a flexible return type based on the condition inside the method.
- Inside the method, there is an `if` statement that generates a random number. If the random number is greater than 0, the method returns a `ResponseEntity<String>` containing the string `"OK"` with an HTTP status code of `HttpStatus.OK`.
- If the random number is less than or equal to 0, the method returns a `ResponseEntity<Integer>` containing the integer value `101` with an HTTP status code of `HttpStatus.OK`.
- The use of the wildcard type (`?`) in the return type allows the method to return different types of `ResponseEntity` objects based on the condition. This provides flexibility in selecting the return type dynamically.

This approach can be useful when the return type of a method depends on certain conditions or workflows. It allows you to have different return types without explicitly specifying them in the method signature. The actual return type will be determined at runtime based on the condition evaluated within the method.

