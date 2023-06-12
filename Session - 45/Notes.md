## **Q) In how many ways can a browser make a request in a web application?**

There are three main ways in which a browser can make a request in a web application:

a. **Entering URL in the Address bar (GET):** Users can enter a URL directly into the browser's address bar and press Enter. This typically results in a GET request being sent to the server. For example, entering "www.google.com" in the address bar and pressing Enter will send a GET request to the Google website.

b. **HTML Form Submission (GET/POST):** Web pages often contain HTML forms that allow users to input data and submit it to the server. The form can use either the GET or POST method to send the data to the server. When the user clicks the submit button, the form data is included in the request, and the browser sends the request to the server. Examples of forms include registration forms, login forms, feedback forms, comment forms, etc.

c. **Clicking on Hyperlinks ( \<a> ) (GET):** Hyperlinks, represented by the `<a>` HTML tag, allow users to navigate between different pages within a website or to external websites. When a user clicks on a hyperlink, the browser sends a GET request to the URL specified in the hyperlink's `href` attribute. Examples of hyperlinks include "Read More" links, logout links, "View Here" links, menu bar options, etc.

It's important to note that while GET requests are used for retrieving data from the server, POST requests are used for submitting data to the server for processing. The method used for a request depends on the specific requirements of the web application and the action being performed.

<br/>
<br/>
<br/>

**FrontController in Spring Boot**

The FrontController, also known as the Dispatcher Servlet, is a concept in Spring Boot that plays a crucial role in handling and dispatching incoming requests to the appropriate components within a web application. It acts as the central entry point for all incoming requests and manages the flow of the request processing.

In Spring Framework, the FrontController is typically implemented as a Servlet and is named as "DispatchServlet".In spring F/w we need to configure using web.xml and In Spring Boot, the configuration for the Dispatcher Servlet is auto-configured, eliminating the need for manual configuration through web.xml. This allows for easier and more streamlined development of web applications.

**Controller in a Web Application**

A Controller is a class in a web application that contains request processing methods. Each module within a project can have its own dedicated controller responsible for handling requests related to that specific module. For example, in a project like Gmail, you might have controllers for the User module, Inbox module, Sent module, Draft module, Settings module, Profile module, and so on. Similarly, in a project like Amazon, you could have controllers for the User module, Search module, Cart module, Payment module, Feedback module, Returns module, and more.

The purpose of a controller is to receive incoming requests, process them, and return an appropriate response. It encapsulates the business logic related to a specific module or functionality of the application. Controller methods are responsible for handling specific request types and performing the necessary operations to fulfill the request. Examples of controller methods could be `addUser`, `loginUser`, `viewMyData`, `checkStatus`, `tryRepay`, `validateOtp`, and so on.

By having dedicated controllers for different modules or functionalities, the codebase becomes more organized and modular. It allows for separation of concerns and improves the maintainability and scalability of the application. Each controller method can be tailored to handle specific types of requests and perform the required processing before returning a response.

Overall, the FrontController and Controllers are key components in a Spring Boot web application that enable effective request handling and processing, leading to the successful implementation of different modules and functionalities within the application.

<br/>
<br/>

# In the context of the projects you mentioned (Gmail and Amazon) and their respective modules, here are examples of controllers and their corresponding methods:

**Project: Gmail**
- UserController
  - addUser: This method handles the request for adding a new user to the system.
  - loginUser: This method handles the request for user login and authentication.
  - viewMyData: This method handles the request for retrieving and displaying the data specific to the logged-in user.

**Project: Amazon**
- UserController
  - addUser: This method handles the request for registering a new user in the system.
  - loginUser: This method handles the request for user login and authentication.
  - viewMyData: This method handles the request for fetching and displaying user-specific data.

- PaymentController
  - checkStatus: This method handles the request for checking the status of a payment transaction.
  - tryRepay: This method handles the request for retrying a failed payment transaction.
  - validateOtp: This method handles the request for validating an OTP (One-Time Password) during the payment process.

In both projects, the UserController is responsible for handling user-related operations such as user registration, login, and data retrieval. The PaymentController, specific to the Amazon project, handles payment-related operations such as checking payment status, retrying failed payments, and OTP validation.

By having separate controllers for different modules or functionalities, the codebase becomes more organized, maintainable, and modular. Each controller focuses on a specific area of functionality, allowing for better separation of concerns and making it easier to understand and maintain the code.

<br/>
<br/>

## **Q) Why is HandlerMapper used in Spring WEB MVC?**

A) The HandlerMapper in Spring WEB MVC is used to map incoming requests to the appropriate controller methods. It acts as a centralized component that holds all the details about the available controller methods and their corresponding request mappings.

The HandlerMapper maintains a map structure internally, where the keys represent the request paths or URLs, and the values represent the controller methods that should be invoked for handling those requests. The mapping is typically based on the HTTP method (GET, POST, etc.) and the path specified in the request.

When a request comes into the application, the HandlerMapper is responsible for determining the appropriate controller method to handle that request. It looks up the request path and method in its mapping structure and retrieves the corresponding controller method.

By using the HandlerMapper, the Spring WEB MVC framework provides a flexible and configurable way to define the mappings between incoming requests and the corresponding controller methods. It allows for a clear separation of concerns, as the mapping logic is handled separately from the actual controller methods.

Overall, the HandlerMapper plays a crucial role in the request handling process of a Spring WEB MVC application by ensuring that each incoming request is routed to the appropriate controller method for processing.

<br/>
<br/>

## **Controller Method Returns a ViewName which has no location and extension details. To be independent of UI Technologies:**

When a controller method processes a request in a Spring MVC application, it typically returns a logical view name. The view name represents the desired output view for that particular request. One of the key principles of Spring MVC is to decouple the controller layer from the specific UI technologies used, such as JSP, Thymeleaf, or HTML.

By returning a view name without any location or extension details, the controller method remains independent of the underlying UI technology. It simply specifies the logical view name that should be rendered for the request, regardless of the specific view implementation.

## **ViewResolver helps to find ViewPage by adding location and extension details:**

The ViewResolver is responsible for resolving the logical view name returned by the controller method into an actual view page. It adds the necessary location and extension details to the logical view name to determine the complete path and file name of the view page.

The ViewResolver allows for flexibility in choosing the view implementation technology and the location of the view pages. It abstracts away the specifics of where the view pages are stored and how they are represented.

In Spring Framework, you need to define a `<bean>` for the ViewResolver in the application context XML configuration file. However, in Spring Boot, the ViewResolver is autoconfigured based on certain conventions and defaults. You can customize the ViewResolver by providing properties or YAML configuration in the application properties file or `application.yml` file.

For example, you can set the prefix and suffix properties for the ViewResolver in Spring Boot as follows:

```properties
spring.mvc.view.prefix=/WEB-INF/mypages/
spring.mvc.view.suffix=.jsp
```

This configuration indicates that the view pages are located in the `/WEB-INF/mypages/` directory and have a `.jsp` extension.

## **View (UI) Page will display final result to EndUser inside a Browser:**

The view page is responsible for presenting the final output to the end user within a web browser. It is the visual representation of the response generated by the server.

The view page can include HTML, CSS, JavaScript, and any other necessary UI components to render the desired user interface. It combines the data obtained from the model with the defined view template to generate the final HTML markup that will be sent to the browser.

## **EL: Expression Language is used to read data from Model (memory):**

Expression Language (EL) is a feature provided by Java-based web frameworks, including Spring MVC. EL allows you to access and manipulate data stored in the model, which acts as a shared memory space between the controller and the view.

EL uses a specific syntax, `${keyName}`, where `keyName` represents the name of the attribute or key under which the data is stored in the model. At runtime, the expressions within the view page are replaced with the actual data values from the model.

For example, if you have a `name` attribute in the model with the value "John", the expression `${name}` within the view page will be rendered as "John" when viewed in the browser.

EL enables dynamic content rendering and provides a convenient way to access and display data from the model within the view page. It enhances the flexibility and reusability of the view templates.

By understanding these concepts, you can effectively separate the concerns of the controller, model, and view layers in your Spring MVC application, resulting in a more maintainable and flexible architecture.

<br/>
<br/>

# **Spring Boot WEB Module:**

The Spring Boot WEB module is a part of the Spring Boot framework that provides all the necessary annotations and configurations to develop web applications. It simplifies the process of creating and deploying web applications by offering out-of-the-box features and reducing the need for boilerplate code.

One of the key features of the Spring Boot WEB module is the embedded server capability. It includes an embedded server, such as Apache Tomcat (default), Eclipse Jetty, or JBoss Undertow, which eliminates the need to download and install an external server. This allows you to run your web application as a standalone application without any additional server setup.

The application package format used in Spring Boot WEB applications is typically a WAR (Web Archive) file. A WAR file is essentially an archive or a bundle that contains all the necessary files and dependencies required to deploy and run a web application. It includes the compiled classes, resources, libraries, configuration files, and other assets needed by the application.

However, it's important to note that Spring Boot also supports packaging web applications as JAR (Java Archive) files. This is particularly useful when working with the embedded server, as it allows you to package the entire application, including the server, into a single executable JAR file. This simplifies the deployment process, as you only need to run the JAR file to start the web application.

In terms of deployment options, if you intend to deploy your web application to an external server, the recommended option is to package it as a WAR file. This format is specifically designed for web applications and can be deployed to any compatible servlet container or application server.

Additionally, it's worth mentioning that the EAR (Enterprise Archive) format, which was previously used for combining EJB (Enterprise JavaBeans) and web application modules, is no longer widely used in modern Java enterprise development.

By leveraging the Spring Boot WEB module and its embedded server capabilities, you can simplify the development and deployment of web applications, making it easier to create self-contained and executable artifacts for your projects.

<br/>
<br/>

**Port Number in Web Applications:**

In web applications, a port number is a logical number assigned by the operating system (OS) to identify a specific service running on a machine. It is part of the network address used to establish a connection between a client (such as a web browser) and a server.

The range of valid port numbers is from 0 to 65535 (or 2^16). However, certain port numbers from 0 to 1024 are reserved for well-known services and protocols. These reserved ports are typically used by standard services such as HTTP (port 80) and HTTPS (port 443).

When developing a Spring Boot application, the default port number used by the embedded server (e.g., Apache Tomcat) is 8080. This means that when you run your Spring Boot application locally, it will be accessible at `http://localhost:8080`.

You have the flexibility to modify the default port number by specifying a different value in the application's configuration. In Spring Boot, you can modify the port number by adding a configuration property in the `application.properties` or `application.yml` file. The key for setting the port number is `server.port`, followed by the desired port number.

For example, if you want to change the port number to 9090, you can add the following line to your `application.properties` file:

```
server.port=9090
```

By making this configuration change, your Spring Boot application will be accessible at `http://localhost:9090` when running locally.

It's important to note that when deploying your application to a production environment or a server, you may need to consider any restrictions or guidelines provided by the hosting environment or network administrators regarding port numbers.