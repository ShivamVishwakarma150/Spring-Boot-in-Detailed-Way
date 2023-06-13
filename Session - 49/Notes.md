# Rules to work with Controller class:

1. We must provide the annotation `@Controller`: 
   - The `@Controller` annotation is used to indicate that a class serves as a controller in Spring MVC.
   - Controllers handle user requests and define methods to process those requests.

2. Every method in the controller class must be connected with a Path and HttpMethod:
   - To map a method to a specific URL and HTTP method, we use annotations such as `@RequestMapping`, `@GetMapping`, and `@PostMapping`.
   - `@RequestMapping` is a generic mapping annotation that can be used with any HTTP method.
   - `@GetMapping` is a specialized form of `@RequestMapping` for handling GET requests.
   - `@PostMapping` is a specialized form of `@RequestMapping` for handling POST requests.

3. Path (URL) is case-sensitive:
   - Paths in Spring MVC are case-sensitive. For example, `/show`, `/SHOW`, and `/Show` are considered different paths.

4. No Duplicate Paths are allowed:
   - It is not allowed to have duplicate paths for different methods in the same controller class.
   - If two methods have the same path, an `IllegalStateException` will be thrown indicating an ambiguous mapping.

5. Same Path can be used with different HttpMethod combinations:
   - It is allowed to have multiple methods with the same path but different HTTP methods.
   - For example, you can have a `@GetMapping("/show")` and a `@PostMapping("/show")` method in the same controller class.

6. Multi-Level Path is allowed at the method level:
   - You can define paths with multiple levels for methods using slashes, such as `@GetMapping("/find/name/data")`.

7. Even only '/' is a valid Path:
   - A path of just '/' represents the root path or the welcome page of the application.
   - For example, `@GetMapping("/")` defines a method that handles requests to the root path.

8. HTTP Protocol default port number is '80':
   - The default port number for HTTP protocol is '80'.
   - If you set `server.port=80` in the application's properties, the URL will be `http://localhost:80`, which is equivalent to `http://localhost`.

9. Path can be provided at the class level (Controller level):
   - You can specify a common path prefix for all the methods in a controller class using the `@RequestMapping` annotation at the class level.
   - For example, `@RequestMapping("/emp")` defines a base path of `/emp` for all methods in the `EmployeeController` class.

10. We can define multiple controllers in a project:
    - In a Spring MVC project, you can define multiple controller classes to handle different sets of requests and business logic.
    - Each controller class can have its own set of methods and request mappings.

These points cover the basic rules and conventions related to working with a controller class in Spring MVC. Understanding these rules will help you define and map your controller methods correctly to handle different requests from clients.

<br/>
<br/>
<br/>

# Here's the revised format with separate code sections, their explanations, and a conclusion:

1. Name: SpringBootWebMvcMultiControllerEx
   Dep: Web, Tomcat Embedded Jasper

**EmployeController**

```java
package com.app.shivam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp")
public class EmployeController {

	@GetMapping("/home")
	public String showEmp() {
		return "EmpHome";
	}
}
```

Explanation:
- The `EmployeController` class is a controller that handles requests related to employees.
- It is annotated with `@Controller` to indicate that it is a Spring MVC controller.
- The `@RequestMapping("/emp")` annotation sets a base path of `/emp` for all the methods in this controller.
- The `@GetMapping("/home")` annotation maps the `showEmp()` method to handle GET requests to the `/emp/home` path.
- The method returns the logical view name "EmpHome", which will be resolved to the corresponding JSP page.

**ProductController**

```java
package com.app.shivam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prod")
public class ProductController {

	@GetMapping("/home")
	public String showProd() {
		return "ProdData";
	}
}
```

Explanation:
- The `ProductController` class is another controller that handles requests related to products.
- It is also annotated with `@Controller`.
- The `@RequestMapping("/prod")` annotation sets a base path of `/prod` for all the methods in this controller.
- The `@GetMapping("/home")` annotation maps the `showProd()` method to handle GET requests to the `/prod/home` path.
- The method returns the logical view name "ProdData", which will be resolved to the corresponding JSP page.

**UI Pages**

**EmpHome.jsp**

```html
<html>
	<head>
		<title>WELCOME TO APP</title>
	</head>
	<body>
		<pre>
		<h2>WELCOME TO EMP HOME</h2>
		</pre>
	</body>
</html>
```

**ProdData.jsp**

```html
<html>
	<head>
		<title>WELCOME TO APP</title>
	</head>
	<body>
		<pre>
		<h2>WELCOME TO PRODUCT DATA</h2>
		</pre>
	</body>
</html>
```

Explanation:
- `EmpHome.jsp` is the JSP page that will be displayed when the `/emp/home` path is requested.
- It contains a simple HTML structure with a heading "WELCOME TO EMP HOME".
- `ProdData.jsp` is the JSP page that will be displayed when the `/prod/home` path is requested.
- It also contains a simple HTML structure with a heading "WELCOME TO PRODUCT DATA".

**application.properties**

```properties
#Port number details
server.port=9090

#View Resolver details
#---prefix must start with / and end with /
spring.mvc.view.prefix=/WEB-INF/pages/
spring.mvc.view.suffix=.jsp
```

Explanation:
- The `application.properties` file contains configuration properties for the application.
- `server.port=9090` sets the port number to 9090.
- `spring.mvc.view.prefix=/WEB-INF/pages/` configures the prefix for the JSP pages.
- `spring.mvc.view.suffix=.jsp` configures the suffix for the JSP pages.

**Rules to work with Controller class:**

Explanation:
1. We must provide the annotation `@Controller`: This ensures that the class is recognized as a controller component that handles HTTP requests.
2. Every method in the controller class must be connected with a Path and HttpMethod: The `@RequestMapping`, `@GetMapping`, and `@PostMapping` annotations are used to map methods to specific paths and HTTP methods.
3. Path (URL) is case-sensitive: Paths are case-sensitive in Spring MVC, so `/show`, `/SHOW`, and `/Show` are considered different paths.
4. No Duplicate Paths are allowed: Two methods in the same controller class cannot have the same path as it leads to an ambiguous mapping error.
5. Same Path can be used with different HttpMethod combinations: Methods can have the same path but different HTTP methods, allowing them to handle different types of requests.
6. Multi-Level Path is allowed at the method level: Methods can have paths with multiple levels, separated by slashes, to handle more specific requests.
7. Even only '/' is a valid Path: The root path or the welcome page of the application can be defined using the `/` path.
8. HTTP Protocol default port number is '80': The default port number for HTTP is '80', but it can be configured using `server.port` in the application properties.
9. Path can be provided at the class level (Controller level): A base path can be set for all methods in a controller using the `@RequestMapping` annotation at the class level.
10. We can define multiple controllers in a project: Multiple controller classes can be defined to handle different sets of requests and business logic.

**Conclusion:**
In this example, we have two controllers: `EmployeController` and `ProductController`. Each controller handles requests related to employees and products, respectively. The methods in these controllers are mapped to specific paths using annotations like `@GetMapping` and `@RequestMapping`. The JSP pages `EmpHome.jsp` and `ProdData.jsp` provide the UI for the respective paths. The application is configured to run on port 9090, and the view resolver is set to use JSP pages located in the `/WEB-INF/pages/` directory.

<br/>
<br/>
<br/>



**Q) How can you provide multiple paths and/or HTTP methods for a single controller method?**

A) By using `@RequestMapping` with an array format, you can specify multiple paths and/or HTTP methods for a single controller method.

Example:

```java
@RequestMapping(
	value = {"/login", "/home"},
	method = {RequestMethod.GET, RequestMethod.POST}
)
public String showEmp() {
	// Method logic goes here
}
```

Explanation:
- The `@RequestMapping` annotation is used to map the method to multiple paths and/or HTTP methods.
- The `value` attribute takes an array of strings representing the paths.
- In this example, the method is mapped to two paths: `/login` and `/home`.
- The `method` attribute takes an array of `RequestMethod` values representing the HTTP methods.
- In this example, the method is mapped to both GET and POST methods.
- The method logic goes inside the method body.

Alternatively, if you only want to provide multiple paths with one HTTP method type, you can use the specific annotations `@GetMapping` or `@PostMapping`:

```java
@GetMapping({"/info", "/data"})
public String showEmp() {
	// Method logic goes here
}
```

Explanation:
- The `@GetMapping` annotation is used to map the method to multiple paths for GET requests.
- The `@PostMapping` annotation is used to map the method to multiple paths for POST requests.
- In this example, the method is mapped to two paths: `/info` and `/data`.
- The method logic goes inside the method body.

In summary:
- To provide multiple paths and/or HTTP methods for a single controller method, you can use `@RequestMapping` with an array format.
- If you only want to provide multiple paths with one HTTP method type, you can use `@GetMapping` or `@PostMapping` with an array of paths.

This allows you to define flexible mappings for your controller methods, catering to different paths and HTTP methods as needed.

<br/>
<br/>

## **Q) Can we define one controller method without any path and HTTP method?**

A) Yes, we can define a controller method without specifying any path or HTTP method. However, such a method will not be executed for any request. Typically, these methods are used to encapsulate common code that needs to be shared among multiple methods within the same controller.

Here's an example to illustrate this concept:

```java
void commonMethod() {
    // Common code goes here
}

@GetMapping("/a")
String method1() {
    // Code specific to method1
    commonMethod();
    // Code specific to method1
}

@GetMapping("/b")
String method2() {
    commonMethod();
    // Code specific to method2
    commonMethod();
    // Code specific to method2
}
```

Explanation:
- In this example, `commonMethod()` is a method that contains common code that needs to be shared by both `method1()` and `method2()`.
- `method1()` is annotated with `@GetMapping` and has a specific path of `/a`. It also calls `commonMethod()` to execute the shared code.
- Similarly, `method2()` is annotated with `@GetMapping` and has a specific path of `/b`. It also calls `commonMethod()` twice to execute the shared code at different points.
- Since `commonMethod()` is not annotated with any path or HTTP method, it cannot be directly accessed by any request.

In summary:
- Yes, it is possible to define a controller method without specifying any path or HTTP method.
- Such methods are typically used to encapsulate common code that needs to be shared among multiple methods within the same controller.
- They cannot be directly executed by any request, but they can be called from other methods to execute the shared code.

This approach allows for code reuse and helps in maintaining cleaner and more modular controller implementations.

## **Q) What happens if I provide only `@Component` instead of `@Controller` for a class?**

A) When you provide the `@Component` annotation instead of `@Controller` for a class, an object of that class will be created by the Spring framework. However, this class will not be automatically executed for any request.

Explanation:
- The `@Component` annotation is a generic annotation used to indicate that a class is a Spring bean and should be managed by the Spring container.
- When you annotate a class with `@Component`, an instance of that class is created and registered as a bean in the Spring application context.
- However, the class itself is not specifically intended to handle HTTP requests like a controller. It is more of a generic component or service class.
- Without the `@Controller` annotation, the class will not be automatically mapped to any request paths or HTTP methods.
- As a result, even though the object of the class is created, it will not be executed for any request.

In summary:
- If you provide only `@Component` instead of `@Controller` for a class, the object of that class will be created as a Spring bean.
- However, the class will not be automatically executed for any request as it lacks the specific mapping of request paths and HTTP methods provided by the `@Controller` annotation.
- The `@Component` annotation is more suitable for generic beans or service classes that are not directly responsible for handling HTTP requests.

It's important to use the appropriate annotation (`@Controller`, `@RestController`, etc.) based on the intended purpose of the class to ensure that it functions correctly within the Spring MVC framework.

<br/>
<br/>

```
task:
1. Define one Student Controller and display HomePage if we enter URL
  /student/home (GET type)

2. Write UserController and display LoginPage, if we enter /login or
   /home or / (all GET Type only).
   
```

1. Define a Student Controller to display the HomePage when accessing the URL "/student/home" with the GET method.

```java
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/home")
    public String showHomePage() {
        return "HomePage";
    }
}
```

2. Write a UserController to display the LoginPage when accessing the URLs "/login", "/home", or "/" with the GET method.

```java
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @GetMapping(value = {"/login", "/home", "/"})
    public String showLoginPage() {
        return "LoginPage";
    }
}
```

Note:
- In both cases, the controllers are annotated with `@Controller` to indicate that they are Spring MVC controllers.
- The `@RequestMapping` annotation is used to specify the base URL path for the StudentController ("/student") and no explicit path is provided for the UserController.
- In the StudentController, the `@GetMapping` annotation is used to map the "/home" URL with the GET method to the `showHomePage()` method.
- In the UserController, the `@GetMapping` annotation is used with an array of paths ("/login", "/home", "/") to map multiple URLs with the GET method to the `showLoginPage()` method.
- The methods in both controllers return the name of the view to be displayed ("HomePage" and "LoginPage" in this case). The actual view files need to be created in the appropriate directory (e.g., "HomePage.jsp" and "LoginPage.jsp") with the configured view resolver.
- Remember to configure the view resolver and set the appropriate prefix and suffix in the application.properties file to map the view names to the corresponding JSP files.

<br/>
<br/>
<br/>

## **Q) What is DevTools why it is used ?**

DevTools is a module provided by Spring Boot that enhances the development experience by providing various features for faster development and improved productivity. It is used during the development phase of an application.

Some key features and benefits of using DevTools are:

1. Automatic Restart: DevTools automatically detects changes in the application's classpath and triggers a restart of the application. This eliminates the need for manually stopping and starting the application after code changes, allowing developers to see the changes immediately without any manual intervention.

2. Fast Application Startup: DevTools optimizes the application startup process by caching the classpath and configuration metadata. This helps in reducing the application's startup time, making the development cycle faster and more efficient.

3. LiveReload: DevTools includes a LiveReload server that monitors changes in static resources (HTML, CSS, JavaScript, etc.) and triggers a browser refresh automatically. This enables developers to see the updated changes in the browser without manually refreshing the page.

4. Remote Developer Support: DevTools provides support for remote developers by enabling the automatic restart and LiveReload functionality even when the application is running on a remote server. This allows developers to make changes locally and see the results on the remote server without the need for manual deployment.

5. Property Defaults and Overrides: DevTools allows developers to define default properties that apply during development and can be overridden in different environments (e.g., production). This simplifies the management of configuration properties across different environments.

To use DevTools in a Spring Boot application, you need to include the appropriate dependency in your project's configuration file (usually the pom.xml for Maven projects). The dependency coordinates are:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
```

By adding this dependency, DevTools will be included in your application's classpath, and you can start using its features to enhance your development workflow.

Note: It's important to mention that DevTools should not be included in production-ready applications as it is meant for development purposes only.