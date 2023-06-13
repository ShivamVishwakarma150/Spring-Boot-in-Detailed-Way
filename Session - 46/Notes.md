# Let's break down each point and provide a detailed explanation:

**1. Tomcat is given as an Embedded Server (Default) that runs at port 8080:**
   - Tomcat is a popular open-source web server and servlet container that is used to run Java web applications. In this context, "Embedded Server" refers to the capability of Tomcat to be integrated and run within another application.
   - By default, Tomcat runs on port 8080. Ports are used to establish network connections, and port 8080 is commonly used for web servers to listen for incoming requests.

**2. Embedded Tomcat is also called a lightweight container service:**
   - In the context of web application deployment, a container provides an environment for running the application. It manages the lifecycle of the application and provides services such as request handling and resource management.
   - Tomcat, as an embedded server, can be considered a lightweight container service because it provides the necessary infrastructure to run web applications efficiently without the need for additional heavyweight components.

**3. By default, JASPER is not given; it comes with only CATALINA (Servlets):**
   - In Tomcat, CATALINA represents the core engine that handles the processing of servlets. Servlets are Java classes that are used to handle HTTP requests and generate responses dynamically.
   - JASPER, on the other hand, is the component responsible for compiling JavaServer Pages (JSP) files into servlets. JSP is a technology that allows developers to embed Java code within HTML pages to generate dynamic content.
   - The note states that by default, only CATALINA is included, meaning that Tomcat can run servlets out of the box. JASPER, which is required to run JSP files, is not included by default.

**4. If we want to use JSP, then add Tomcat-Embedded-JASPER (JSP -> Servlets):**
   - To enable support for JSP files in Tomcat, you need to include the Tomcat-Embedded-JASPER component in your application.
   - Tomcat-Embedded-JASPER provides the necessary JSP compilation and execution capabilities for Tomcat's embedded mode.
   - By adding this component, you can write JSP files in your application, and Tomcat will automatically compile them into servlets using JASPER, allowing you to run dynamic web pages powered by Java code.

In summary, the points mentioned describe the default configuration and capabilities of Tomcat as an embedded server. It serves as a lightweight container for running Java web applications, supports servlets out of the box (through the CATALINA component), and requires the addition of Tomcat-Embedded-JASPER to enable JSP support (which allows the use of Java code within HTML pages).

<br/>
<br/>

# First Web Application using Spring boot + JSP

**1. Create a Spring Boot application with the "Spring Web" dependency:**
   - This step involves setting up a basic Spring Boot application that includes the necessary dependencies for building web applications.
   - You can create a new Spring Boot project using your preferred IDE or by using the Spring Initializr website (start.spring.io). Make sure to include the "Spring Web" dependency.

**2. Add the tomcat-embed-jasper dependency for JSP page support:**
   - To enable JSP support in your Spring Boot application, you need to include the "tomcat-embed-jasper" dependency.
   - This can be done by adding the following code snippet to your project's pom.xml file:

```xml
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
```

**3. Create a folder system for UI pages:**
   - In this step, you need to create a folder structure to organize your UI pages.
   - Right-click on the "src/main" folder in your project, select "New," then choose "Folder."
   - Enter the name as "webapp" and click "Finish."

   The folder structure should look like this:
   ```
   |-src
      |-main
         |-webapp
            |-WEB-INF
               |-pages
   ```

**4. Create a JSP file under the pages folder:**
   - Right-click on the "pages" folder you created in the previous step, select "New," then choose "File."
   - Enter a name for your JSP file (e.g., UserHome.jsp) and click "Finish."
   - Add the following content to your JSP file:

```html
<html>
   <head>
      <title>WELCOME TO APP</title>
   </head>
   <body>
      <h2>WELCOME TO THE FIRST APPLICATION!</h2>
   </body>
</html>
```

**5. Configure the application.properties file:**
   - Open the application.properties file in your Spring Boot project.
   - Add the following configuration:

```properties
# Port number details
server.port=9090

# View Resolver details
# --- prefix must start with / and end with /
spring.mvc.view.prefix=/WEB-INF/pages/
spring.mvc.view.suffix=.jsp
```

**6. Create a controller class:**
   - Create a new class called UserController in the specified package (com.app.shivam.controller).
   - Annotate the class with `@Controller` to mark it as a controller component in Spring.
   - Add the following code to the class:

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
   @GetMapping("/home")
   public String showHomePage() {
      return "UserHome";
   }
}
```

**7. Run the main class and access the URL:**
   - Run the main class of your Spring Boot application.
   - Once the application is running, you can access the following URL in your web browser: http://localhost:9090/home
   - This URL maps to the "/home" endpoint defined in the UserController class, which returns the "UserHome" view (JSP page).

That's it! By following these steps, you can create a Spring Boot application with JSP page support and access the home page through the provided URL.

<br/>
<br/>
<br/>



**Q) Why do we need to add @Controller over the class instead of @Component?**

A) In Spring MVC, the role of a controller is to handle HTTP requests and generate responses. While both @Controller and @Component annotations are used to mark classes as managed components by the Spring container, they have different purposes:

- @Component is a generic annotation that indicates a class is eligible for component scanning and auto-wiring. It is a general-purpose stereotype annotation and does not provide any specific behavior related to handling HTTP requests.
- @Controller, on the other hand, is a specialized annotation that is specifically used for controller classes in Spring MVC. It combines the functionality of @Component and provides additional features related to handling HTTP operations.

By using @Controller, you not only inform the Spring container to create an object of the class but also indicate that this class is responsible for handling HTTP requests and supports the HTTP protocol.

Here's an example to illustrate the difference:

```java
// Using @Component
@Component
public class MyComponent {
    // ...
}
```

```java
// Using @Controller
@Controller
public class MyController {
    // ...
}
```

In the above example, both classes are eligible for component scanning and can be managed by the Spring container. However, the MyController class, annotated with @Controller, is more suitable for handling HTTP requests and contains methods specifically designed for that purpose.

**Q) Who will execute the controller method and when?**

A) In Spring MVC, the execution of controller methods is handled by the Front Controller (FC) of the framework. The Front Controller, which is part of the Spring MVC framework, is responsible for receiving incoming HTTP requests, routing them to the appropriate controller, and invoking the corresponding controller method.

When a request is received by the Front Controller, it examines the request details, such as the URL and the HTTP method, to determine which controller and method should handle the request. It then invokes the respective controller method, passing the request to it as a parameter.

For example, let's consider the UserController class from the previous example:

```java
@Controller
public class UserController {
   @GetMapping("/home")
   public String showHomePage() {
      return "UserHome";
   }
}
```

In this case, the `showHomePage()` method is annotated with `@GetMapping`, which means it will handle HTTP GET requests for the "/home" URL path. When a GET request is received with the "/home" path, the Front Controller will call/execute the `showHomePage()` method to generate the response.

It's important to note that for each request, Spring creates only one instance of the controller class and reuses it to handle multiple requests. So, the controller method is invoked for each individual request, allowing the application to respond dynamically to each request.

**Q) How can we map/link one Java method with Request Details (PATH/HttpMethod)?**

A) In Spring MVC, you can map/link a Java method with request details such as the path and HTTP method using annotations. The most common annotation used for this purpose is `@RequestMapping`, but there are also specific annotations available for different HTTP methods such as `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, and more.

Here's an example to demonstrate mapping a method using the `@RequestMapping` annotation:

```java
@Controller
@RequestMapping("/users")
public class UserController {
   @RequestMapping(value = "/home", method = RequestMethod.GET)
   public String showHomePage() {
      return "UserHome";
   }
}
```

In this example, the `UserController` class is mapped to the "/users" URL path using `@RequestMapping("/users")`. This means that all requests starting with

 "/users" will be handled by this controller.

The `showHomePage()` method is further mapped to the "/home" path and the HTTP GET method using `@RequestMapping(value = "/home", method = RequestMethod.GET)`. This method will be invoked when a GET request is made to the "/users/home" URL path.

Alternatively, you can use specific annotations for each HTTP method to make the code more concise. Here's the same example using `@GetMapping`:

```java
@Controller
@RequestMapping("/users")
public class UserController {
   @GetMapping("/home")
   public String showHomePage() {
      return "UserHome";
   }
}
```

In this case, the `showHomePage()` method is mapped to the same "/users/home" path and HTTP GET method, but with a more specific and readable annotation.

By using these annotations, you can easily map and link Java methods to specific request details, allowing the application to handle different requests based on their paths and HTTP methods.


<br/>
<br/>

## **If you encounter a `"White Label Error Page"` in your Spring Boot application, here are some steps you can take to troubleshoot and resolve the issue:**

1. Remove spaces at the properties file (if added):
   - Check if there are any unnecessary spaces or characters in your application.properties or application.yml file. These spaces or characters can cause configuration issues, leading to the White Label Error Page.
   - Ensure that the properties file is correctly formatted without any extra spaces, especially around property keys and values.

2. Check the folder system:
   - Verify that the folder structure for your UI pages is set up correctly.
   - Make sure you have the correct folder hierarchy with the "webapp" folder under "src/main" and the "WEB-INF/pages" folder under "webapp".
   - Ensure that your JSP files are placed in the "pages" folder under "WEB-INF".

3. UI/View page name is case-sensitive:
   - Double-check the case sensitivity of your JSP file names and ensure they match exactly with the file names used in your controller.
   - For example, if your controller returns "UserHome", make sure that the JSP file is named "UserHome.jsp" with the same capitalization.

4. Check if you added Tomcat Embedded JASPER:
   - Verify that you have included the "tomcat-embed-jasper" dependency in your project's pom.xml or build.gradle file.
   - This dependency is required for JSP support in embedded Tomcat. Make sure it is correctly added as a dependency to avoid the White Label Error Page.

5. Check the BasePackage rule for the controller class:
   - Ensure that your controller class is in the same package or a subpackage of the main class of your Spring Boot application.
   - By default, Spring Boot uses component scanning to discover controller classes within the specified base package or its subpackages.
   - If your controller class is not in the correct package, it may not be detected by the component scanning process, resulting in the White Label Error Page.

By following these steps, you can identify and address common issues that may lead to the White Label Error Page in your Spring Boot application.