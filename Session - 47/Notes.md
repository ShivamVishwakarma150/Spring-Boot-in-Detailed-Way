
# Http Methods

In the context of HTTP methods, it is technically possible to use only the POST method for all types of operations in a web application. However, it is not recommended to do so, as it goes against the principles and conventions of the HTTP protocol. 

The HTTP protocol defines different methods with specific purposes to perform operations on resources. Each method has its own semantics and intended use. Here's a brief explanation of the commonly used HTTP methods:

1. GET: This method is used to retrieve a representation of a resource from the server. It is considered safe and idempotent, meaning that multiple identical GET requests should have the same effect as a single request. GET requests should not have any side effects on the server or modify the state of the application.

2. POST: This method is used to submit data to the server to create a new resource. It is not idempotent, meaning that multiple identical POST requests may create multiple resources on the server. POST requests can have side effects on the server, such as modifying the application's state or saving data to a database.

3. PUT: This method is used to update an existing resource on the server. It is idempotent, meaning that multiple identical PUT requests should have the same effect as a single request. PUT requests are used to completely replace the resource with the new data provided in the request.

4. DELETE: This method is used to delete a resource from the server. It is idempotent, meaning that multiple identical DELETE requests should have the same effect as a single request. DELETE requests are used to remove the specified resource from the server.

While it is technically possible to handle all types of operations using only the POST method, it violates the semantics and conventions defined by the HTTP protocol. It can make the application's API less intuitive and harder to understand for other developers. It is generally recommended to follow the standard HTTP methods and use them appropriately based on their intended purpose.

In the context of web MVC frameworks, such as Spring MVC, GET and POST are the most commonly used methods because they align with the typical use cases of retrieving and submitting data in a web application. Other methods like PUT and DELETE may be used in specific scenarios where updating and deleting resources are required.

By adhering to the HTTP protocol and using the appropriate methods, you ensure that your application follows industry-standard practices and can be easily understood and consumed by other developers and clients.

<br/>
<br/>

**Q) Can't we use only POST for all Types of Operations?**

A) Yes, technically speaking, you can use only the POST method for all types of operations in your web application. However, it is important to understand that the HTTP protocol has established a set of rules and conventions for providers and consumers to access services over the internet.

The HTTP protocol defines different methods, such as GET, POST, PUT, DELETE, etc., with distinct semantics and intended uses. These methods provide a standardized way to interact with resources on the web. Each method conveys a specific intention and purpose, and using the appropriate method for each operation helps maintain clarity, consistency, and interoperability in web communications.

While it is possible to design an application that solely relies on the POST method for all operations, it goes against the established conventions and best practices of the HTTP protocol. By using POST for all operations, you would lose the specific semantics and benefits that other methods provide.

Using the appropriate HTTP methods has several advantages:

1. **Semantics**: Each method has a clear purpose and meaning. By adhering to these semantics, you communicate your intent to both the server and other developers who may consume your API. It makes your code more understandable and maintainable.

2. **Caching and Optimization**: The HTTP protocol allows for caching mechanisms that can significantly improve performance. By using the appropriate methods, you enable clients and intermediaries (such as caching servers) to optimize the caching and retrieval of resources.

3. **Idempotence and Safety**: Certain methods, such as GET, have idempotent and safe properties. Idempotent methods can be safely retried without unintended side effects, while safe methods guarantee that they will not modify the state of the server or the resources.

4. **Uniform Interface**: Adhering to the HTTP protocol's methods provides a uniform interface that enables interoperability and simplifies integration with other systems and services.

While using only the POST method may seem like a shortcut, it can lead to confusion, inefficiency, and non-compliance with the established HTTP standards. It is generally recommended to follow the HTTP protocol's conventions and use the appropriate methods for the specific operations you are performing in your application.

<br/>

## **When we are working with WEB MVC, our app supports/uses only GET and POST.**

In the context of web MVC (Model-View-Controller) frameworks, such as Spring MVC, it is common for applications to primarily use the GET and POST methods. This is because GET and POST align with the typical use cases and operations involved in web-based applications.

1. GET method: GET requests are used to retrieve data or resources from the server. In a web MVC application, GET requests are commonly used for operations such as fetching a web page, retrieving data from a database, or retrieving specific resources. When a user visits a URL or clicks on a link, the browser typically sends a GET request to retrieve the corresponding web page.

2. POST method: POST requests are used to submit data to the server to create or update resources. In a web MVC application, POST requests are commonly used when submitting forms, sending user input, or creating new resources. For example, when a user fills out a registration form on a website and clicks the "Submit" button, the browser sends a POST request to the server to create a new user account.

While GET and POST are the most commonly used HTTP methods in web MVC, it is worth noting that web MVC frameworks also provide support for other HTTP methods such as PUT and DELETE. These methods are used for updating and deleting resources, respectively. However, due to limitations in HTML forms and certain browser implementations, using PUT and DELETE directly from HTML forms is not as straightforward. Therefore, in web MVC applications, PUT and DELETE operations are often simulated or performed using alternative techniques, such as using hidden form fields or using JavaScript to make AJAX requests with the appropriate methods.

In summary, when working with web MVC frameworks, it is common to primarily use the GET and POST methods for retrieving data and submitting data, respectively. However, these frameworks do support other HTTP methods, such as PUT and DELETE, which can be used for updating and deleting resources when needed.

<br/>

**Model (I)**:
- The Model interface in Spring MVC is used to send data from the controller to the view (UI).
- Internally, the Model interface is implemented as a Map, where data is stored as key-value pairs.
- The key is of type String, and the value can be any object.
- The Model interface provides methods to add data to the model using the addAttribute(key, value) method.
- In the context of Spring MVC, the Model interface is used to share data from the controller to the view at runtime.
- Spring container creates the model and manages it, so we can simply add data to the model in the controller and access it in the view using Expression Language (EL) expressions like ${key}.

**Map (I)**:
- The Map interface in Java is a collection of key-value pairs.
- In the context of Spring MVC, the Model interface is implemented using a Map.
- A map allows you to store and retrieve data based on a unique key.
- The key is of type String, and the value can be any object.
- With the Model interface, you can add data to the map using the addAttribute(key, value) method and retrieve it in the view using EL expressions.

**ModelMap(C)**:
- ModelMap is a class in Spring MVC that is a specific implementation of the Model interface.
- The ModelMap class is a legacy implementation, and it follows the loose coupling principle of programming.
- According to the loose coupling principle, it is better to work with interfaces rather than concrete classes. This allows for flexibility in case a better implementation class becomes available in the future.
- When you use ModelMap or Model as the parameter in your controller method, the Spring container internally uses the BindingAwareModelMap class for implementing the model.
- The BindingAwareModelMap is an enhanced implementation class that provides additional features related to data binding and form submission in Spring MVC.

In summary, the Model interface, Map interface, and ModelMap class in Spring MVC are used to send data from the controller to the view. They provide a way to store data in key-value pairs, where the key is a string and the value can be any object. The Spring container manages the model, and we can add data to it using the provided methods. The use of ModelMap follows the loose coupling principle, allowing for flexibility in choosing the implementation class.

<br/>
<br/>
<br/>

# Code Explanation

**SpringBootWebMvcModelEx.java**
```java
// Imports omitted for brevity

@SpringBootApplication
public class SpringBootWebMvcModelEx {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMvcModelEx.class, args);
    }
}
```

**User.java**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String userRole;
}
```

**UserController.java**
```java
@Controller
public class UserController {
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String showUserPage(Model model) {
        model.addAttribute("uname", "AJ");
        model.addAttribute("cname", "SBCDE");
        return "UserHome";
    }
    
    @RequestMapping(value = "/obj", method = RequestMethod.GET)
    public String showUserOb(Model model) {
        User user = new User(10, "AJAY RAJ!", "DEV");
        model.addAttribute("obj", user);
        
        List<User> list = Arrays.asList(
            new User(10, "AA", "XYZ"),
            new User(11, "AB", "XYZ"),
            new User(12, "AC", "MNO"),
            new User(13, "AD", "MNO")
        );
        model.addAttribute("listObj", list);
        
        return "UserData";
    }
}
```

**UserHome.jsp**
```jsp
<html>
<head>
    <title>WELCOME TO APP</title>
</head>
<body>
    <h2>WELCOME TO FIRST APPLICATION! </h2>
    <p>
        Hello : ${uname} ! <br/>
        Your course is : ${cname}.
    </p>
</body>
</html>
```

**UserData.jsp**
```jsp
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>WELCOME TO APP</title>
</head>
<body>
    <h2>WELCOME TO APPLICATION! </h2>
    <p>
        Id  : ${obj.userId} <br/>
        Name: ${obj.userName} <br/>
        Role : ${obj.userRole} <br/>
        Full Data : ${obj}
    </p>
    <table border=1>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>ROLE</th>
        </tr>
        <c:forEach var="ob" items="${listObj}">
            <tr>
                <td>${ob.userId}</td>
                <td>${ob.userName}</td>
                <td>${ob.userRole}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

**application.properties**
```
# Port number details
server.port=9090

# View Resolver details
# ---prefix must start with / and end with /
spring.mvc.view.prefix=/WEB-INF/pages/
spring.mvc.view.suffix=.jsp
```

**Explanation:**

The provided code is a simple Spring Boot web application that demonstrates the use of models in Spring MVC. Here's a breakdown of each component:

1. **SpringBootWebMvcModelEx.java**: This is the main class annotated with `@SpringBootApplication`. It is responsible for bootstrapping the Spring Boot application.

2. **User.java**: This class represents a User object with `userId`, `userName`, and `userRole` properties. It uses Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) to automatically generate getters, setters, constructors, and other common methods.

3. **UserController

.java**: This is the controller class that handles HTTP requests. It contains two request mapping methods:
   - `showUserPage`: Handles the GET request for the "/info" path. It adds "uname" and "cname" attributes to the model and returns the "UserHome" view.
   - `showUserOb`: Handles the GET request for the "/obj" path. It creates a User object, adds it to the model as "obj", creates a list of User objects, adds it to the model as "listObj", and returns the "UserData" view.

4. **UserHome.jsp**: This JSP page displays a welcome message along with the "uname" and "cname" attributes obtained from the model.

5. **UserData.jsp**: This JSP page displays the details of a User object ("obj") obtained from the model. It also iterates over a list of User objects ("listObj") and displays them in a table using the JSTL forEach tag.

6. **application.properties**: This properties file contains the configuration for the Spring Boot application, including the server port and view resolver settings.

In conclusion, this code demonstrates how to use models in Spring MVC to pass data from the controller to the view. The controller methods add data to the model, and the JSP views retrieve and display that data using EL expressions.