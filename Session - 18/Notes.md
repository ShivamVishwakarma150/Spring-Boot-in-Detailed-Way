Let's go through each point mentioned in the notes and provide a detailed explanation:

1. **@Component**: It is a Spring annotation used to mark a class as a component. Components are auto-detected by Spring's component scanning feature and registered in the application context. They are typically used to annotate classes representing beans in the application.

2. **@Value**: This annotation is used to inject values into bean properties from external sources, such as property files or environment variables. It can be applied to fields, methods, or constructor parameters. The values are resolved at runtime and can be customized based on the environment or configuration.

3. **@ComponentScan**: It is used to enable component scanning in Spring applications. Component scanning allows Spring to automatically detect and register beans in the application context based on certain packages or classes. By default, it scans the current package and its sub-packages.

4. **@Autowired**: This annotation is used for automatic dependency injection in Spring. It can be applied to fields, constructors, or methods. When Spring encounters an autowired field or parameter, it tries to find a matching bean from the application context and injects it automatically.

5. **@Scope**: It is used to define the scope of a bean in the Spring application context. Scopes define the lifecycle and visibility of beans. Some commonly used scopes are "singleton" (a single instance shared across the application), "prototype" (a new instance created whenever requested), "request" (a new instance per HTTP request), and "session" (a new instance per user session).

6. **@Configuration**: This annotation is used to indicate that a class defines Spring bean configurations. It is typically used in combination with `@Bean` methods to provide custom bean definitions. Classes annotated with `@Configuration` are processed by Spring to generate bean definitions at runtime.

7. **@Bean**: It is used to declare a method as a bean producer in Spring. The return value of the method represents the bean, and its name is derived from the method name by default. Beans created using `@Bean` methods are registered in the application context and can be injected into other beans.

8. **@PropertySource**: This annotation is used to specify the location of a property file containing key-value pairs. It is typically used in combination with `@Configuration` to load external properties into the Spring environment. The properties can be accessed using the `@Value` annotation or by using the `Environment` object.

9. Spring Boot:
   - AutoConfiguration: Spring Boot provides auto-configuration, where it automatically configures the application based on classpath dependencies and predefined conventions. It eliminates the need for manual configuration in many cases.
   - No XML Configuration: Spring Boot promotes a code-centric approach and discourages XML-based configuration. Instead, it favors Java-based configuration and annotations.
   - Frontend and Backend: Spring Boot is well-suited for developing the backend (APIs) of applications that utilize frontend frameworks like Angular or ReactJS. It enables the development of RESTful web services and microservices.
   - Spring Cloud: Spring Cloud is a framework built on top of Spring Boot that provides tools and libraries for building and deploying microservices. It offers features like service discovery, distributed configuration, circuit breakers, and more.
   - AWS Cloud: AWS Cloud refers to using Amazon Web Services to deploy Spring Boot applications on the cloud infrastructure provided by Amazon. It allows for scalability, reliability, and easy management of applications.
   - MVC Support: Spring Boot supports the MVC (Model-View-Controller) architecture for building web applications. It provides integrations with popular view technologies like JSP and Thymeleaf, allowing for the development of dynamic UIs.
   - Tools: Maven and Gradle are build automation tools commonly used with Spring Boot projects. They manage dependencies, build the project, and handle other tasks related to the project's lifecycle.
   - Embedded Servers: Spring Boot provides embedded servers like Tomcat, Jetty, and Undertow. These servers are included in the application's JAR file and can be started with a simple `main` method, making it easy to deploy and run applications.
   - Embedded Databases: For development and testing purposes, Spring Boot offers embedded databases such as H2, HSQLDB, and Derby. These databases run within the application and do not require a separate database server to be set up.
   - Packing/Build Models: Spring Boot applications can be packaged as JAR (Java Archive) files or WAR (Web Archive) files. JAR files are standalone executable files that include the application and its dependencies, while WAR files are used for deploying applications to external servlet containers.
   - Parent Project: Each Spring Boot application has a parent project called `spring-boot-starter-parent`. This parent project manages the versions of dependencies used in the application, providing consistent and compatible versions for various libraries and frameworks.
   - Maven Plugins: Maven plugins are used to extend the capabilities of the Maven build system. In Spring Boot projects, plugins can be configured to perform tasks like compiling code, packaging the application, running tests, generating documentation, and more.

<br/>

# The Spring Boot application files mentioned in the notes are as follows:

1. **Starter Class**: The starter class is the entry point of a Spring Boot application. It is a Java class with a `main` method. This class creates the Spring container, known as the application context, and sets up all the required objects and configurations. The actual logic of the application is typically present in the `main` method or other components that are invoked from it. The `SpringApplication.run()` method is used to start the application and bootstrap the Spring Boot framework.

2. **Input Files**: These files are used to provide configuration properties to the Spring container. There are two commonly used formats:

   a. `application.properties`: This is a key-value pair file where properties are specified in the format `key=value`. It allows for customization of various aspects of the application, such as server configuration, database settings, logging, and more. For example, `server.port=9898` sets the server port to 9898.

   b. `application.yml` (YAML format): This file also contains key-value pairs but uses a more structured YAML syntax. It provides a more readable and expressive way to configure properties. For example, the equivalent configuration in YAML format would be:
   
      ```yaml
      server:
        port: 9898
      ```

   Both formats support pre-defined keys provided by Spring Boot, as well as programmer-defined or user-defined keys specific to the application. Pre-defined keys are documented in the Spring Boot reference guide.

3. **Build File**: The build file, such as `pom.xml` for Maven or `build.gradle` for Gradle, is used to define the project's build configuration. It includes the following details:

   - Parent Project Details: The parent project, typically `spring-boot-starter-parent`, manages the versions of dependencies used in the application. It ensures that the dependencies are compatible and consistent.

   - Our Project Details: This includes the project's name, version, description, and other relevant information.

   - Properties: Properties section allows specifying various configuration properties related to the project, such as the Java version, Spring Cloud version, and more.

   - Dependencies: Dependencies section lists the dependencies required by the project. These dependencies are resolved by the build system and downloaded from the configured repositories.

   - Plugins: Plugins section configures additional plugins that extend the functionality of the build system. These plugins can perform tasks such as compiling code, packaging the application, running tests, generating documentation, and more.

By configuring these files, you can customize and control the behavior of your Spring Boot application, specify its dependencies, and define various properties and settings.

<br/>
<br/>
<br/>

The provided steps outline the process of creating a new Spring Boot application using the Spring Tool Suite (STS) IDE. Here is a detailed explanation of each step:

1. Open the IDE and navigate to "File > New > Spring Starter Project":
   This menu option allows you to create a new project in STS specifically for Spring Boot.

2. Provide project details:
   You need to specify the details for your project, such as its name, version, and package. In this example:
   - Name: SpringBootFirstApp
   - Version: 1.0
   - Package: com.app.shivam

3. Select dependencies:
   At this step, you choose the dependencies that your project requires. In this case, you select the "Spring web" dependency, which provides the necessary components for building web applications using Spring Boot.

4. Click "Next" and then "Finish":
   After selecting the dependencies, proceed to the next steps by clicking "Next" and then "Finish". This will initiate the project creation process.

5. Project setup using start.spring.io:
   When you click the "Finish" button, STS makes a request to the start.spring.io website. This website generates a Spring Boot project structure with the specified configuration and dependencies.

   The example request URL provided includes various parameters that define the project's details, such as the name, groupId, artifactId, version, description, and package name. It also specifies additional information like the project type (maven-project), packaging format (jar), Java version (17), language (Java), and the Spring Boot version (2.7.3).

   The start.spring.io website generates a ZIP file containing the project structure and required files based on the provided parameters.

6. Test the application:
   Once the project is created, you can begin testing it. Here are the steps:
   - Create a new application within the project.
   - Choose the "Spring web" dependency again.
   - Configure the application properties by creating an `application.properties` file with the content `server.port=9696`. This sets the server port to 9696.
   - Open the main class (typically annotated with `@SpringBootApplication`) and right-click on it.
   - Select "Run As" and choose "Spring Boot Application". This will run the Spring Boot application.

7. Access the application:
   After running the application, you can access it through a web browser using the provided URL: http://localhost:9696/. This URL corresponds to the server port specified in the `application.properties` file. By accessing this URL, you can test and interact with the Spring Boot application.

These steps provide a basic overview of creating and testing a Spring Boot application using the Spring Tool Suite. You can further customize and enhance your application based on your specific requirements and business logic.