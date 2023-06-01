# Intro to Spring Boot

1. Spring Framework (Spring F/w) is used to develop web/distributed applications. It provides a comprehensive programming and configuration model for Java-based enterprise applications.

   Configuration in Spring can be done using XML, Java, or annotations. Some common configurations in Spring applications include database connection setup, notifications/alerts configuration, and security/user operations configuration.

   In the example provided, the XML configuration code demonstrates how to set up a database connection using the DriverManagerDataSource bean. It specifies the driver class, URL, username, and password for the database connection.

2. Spring Boot is built on top of the Spring Framework and aims to simplify the development of Spring applications by reducing the amount of manual configuration required.

   Spring Boot includes a parent project called "Spring Boot" that provides pre-defined configuration code in the form of auto-configuration. These configurations are packaged as JAR files.

   To create a Spring Boot project, you need to create a child project and link it to the parent project. This can be done using build automation tools like Maven or Gradle.

   ```
      **Ex Spring Boot Database Connection**
      1. Child Project / Spring boot starter project
      2. Maven pom.xml / Gradle build.gradle
      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-jdbc</artifactId>
      </dependency>

      implementation 'org.springframework.boot:spring-boot-starter-jdbc:2.7.3'

      3. application.properties
      driver=Oracle
      url=jdbc:oracle:thin
      username=shivam
      password=shivamIT
   ```

   In the example provided, the Spring Boot database connection is demonstrated using Maven. The necessary dependency, `spring-boot-starter-jdbc`, is added to the project's `pom.xml` file.

   Additionally, the database connection details are specified in the `application.properties` file, which includes the driver, URL, username, and password.

1. Spring Boot provides embedded servers and databases to simplify the deployment of applications. It includes embedded servers like Tomcat, Jetty, Undertow, and even supports reactive programming with Netty in Spring Webflux.

   Similarly, Spring Boot offers embedded databases like H2, HSQL, and Derby. These embedded databases are useful for development and testing environments but are not recommended for production.

2. Unlike Spring Framework, Spring Boot does not support working with XML configurations. XML parsing and execution can be slow, so Spring Boot encourages developers to use Java and annotation-based configurations.

3. Spring Boot does not support EJBs (Enterprise JavaBeans) or .ear (Enterprise Archive) deployments. It focuses on providing a lightweight and modular approach to building applications.

4. Spring Boot includes a feature called Actuator, which provides production-ready endpoints for monitoring and managing applications. These endpoints allow you to gather information and perform actions on your application during runtime.

5. Spring Boot supports horizontal scaling (scaling up/down) to handle varying workloads. It provides features and tools to manage application instances and distribute load efficiently.

6. Spring Boot is well-suited for building the backend (APIs) of applications that utilize frontend frameworks like Angular or ReactJS. JSON (using Jackson) is the recommended data format for communication between the frontend and backend components. This allows for seamless integration and data exchange between different layers of the application.

7. Spring Boot supports both YAML and properties file formats for configuration. YAML is generally preferred due to its readability and flexibility. However, Spring Boot supports both formats, allowing developers to choose the one that suits their preferences.

   In the provided examples, both `application.properties` and `application.yml` are shown. The configurations are the same, but they use different formats.

   `application.properties`:
   ```
   spring.datasource.driver-class-name=Oracle
   spring.datasource.url=jdbc:oracle:thin:...
   spring.datasource.username=shivam
   spring.datasource.password=shivamIT
   ```

   `application.yml`:
   ```yaml
   spring:
     datasource:
       driver-class-name: Oracle
       url: dbc:oracle:thin:...
       username: shivam
       password: shivamIT
   ```

8.  Spring Boot provides a comprehensive set of pre-defined keys for configuration properties. These keys can be used to customize various aspects of the application's behavior, such as database settings, logging, caching, and more. The official Spring Boot documentation includes a detailed list of these configuration properties.<br/>
   You can refer to the provided examples and explanations to get a better understanding of the Spring Framework and Spring Boot concepts.