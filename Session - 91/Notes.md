# **Spring Cloud Config Server**

1. One Application can have multiple Microservices (MS) implemented using Spring REST and Spring Cloud components:
   - In a microservices architecture, an application is composed of multiple independent services, each responsible for a specific business capability.
   - These services are developed and deployed independently, but they can still be part of the same overall application.
   - Each microservice can be implemented using Spring REST, which allows building RESTful APIs using the Spring framework.
   - Spring Cloud provides additional components and features to support the development of microservices, such as service discovery, load balancing, and distributed configuration.

2. Every MS application contains an `application.properties` file:
   - In a Spring Boot application, the `application.properties` file is used to define configuration properties for the application.
   - Each microservice in the application can have its own `application.properties` file.
   - These files contain key-value pairs that configure various aspects of the microservice, such as database connection settings, logging configurations, and external service URLs.

3. These files contain duplicate key-value pairs:
   - It is common for multiple microservices within an application to share some common configuration properties.
   - These shared properties might include common database connection details, API keys, or feature flags.
   - As a result, there may be duplicate key-value pairs across the `application.properties` files of different microservices.

4. Config Server can be used to store duplicate pairs outside of all microservices:
   - The Spring Cloud Config Server is a centralized configuration management solution.
   - It allows you to store the configuration properties of multiple microservices in a single location.
   - The Config Server acts as a remote configuration repository where all the `application.properties` files can be stored.
   - Microservices can then retrieve their configuration properties from the Config Server rather than having duplicate properties within each microservice.
   - By centralizing the configuration, the Config Server simplifies the management and updating of configuration properties.
   - Changes to the configuration can be made in the Config Server, and the microservices can dynamically fetch the updated configuration at runtime.

In summary, the Spring Cloud Config Server provides a centralized location to store and manage the configuration properties of multiple microservices within an application. It helps avoid duplication of configuration properties and simplifies the management and updating of configurations. Microservices can retrieve their configurations from the Config Server, allowing for dynamic configuration updates without the need for redeployment.

<br/>
<br/>

# **[Config Server]**


1. To externalize all duplicate key-value pairs from each microservice into one common location (e.g., GitHub) and connect with every microservice at runtime using a Config Server running at port 8888:
   - In a microservices architecture, it is common to have duplicate configuration properties across multiple microservices.
   - Externalizing these properties to a centralized location helps avoid duplication and simplifies configuration management.
   - The Config Server serves as the central hub for storing and serving configuration properties to microservices.
   - The configuration properties can be stored in a version-controlled repository such as GitHub, allowing for easy management and versioning of configurations.
   - Microservices can connect to the Config Server to fetch their respective configurations at runtime.

2. Every microservice should have the Config Client dependency and the URL of the Config Server:
   - In order for a microservice to fetch its configuration from the Config Server, it needs to include the Config Client dependency in its build configuration.
   - The Config Client library provides the necessary functionality to interact with the Config Server.
   - Additionally, the microservice needs to specify the URL of the Config Server so that it knows where to fetch the configuration from.

3. The recommended port number for the Config Server is 8888:
   - By convention, the default port number for the Config Server is 8888.
   - However, you can configure the Config Server to run on a different port if desired.
   - Microservices need to be aware of the port number on which the Config Server is running in order to connect to it.

4. The Config Server loads all key-value pairs into the Environment (I):
   - When a microservice connects to the Config Server, it fetches the configuration properties specific to that microservice.
   - The Config Server loads these properties into the Spring `Environment` object, which is a runtime representation of the configuration properties.
   - The microservice can then access these properties from the `Environment` to configure its behavior accordingly.

In summary, the Spring Cloud Config Server acts as a centralized repository for storing and serving configuration properties to microservices. It enables the externalization of duplicate configuration properties from individual microservices to a common location. Microservices can connect to the Config Server using the Config Client dependency and fetch their respective configurations at runtime. The Config Server loads the properties into the `Environment`, allowing microservices to access and utilize the configurations as needed. The default port for the Config Server is 8888, but it can be customized if required.

<br/>
<br/>
<br/>

# Setting up Spring Cloud Config Server and Integrating with Microservices

Sure! Here's a detailed explanation of the stages you mentioned for setting up the Spring Cloud Config Server and integrating it with microservices:

**Stage 1: Create Github Properties file**
1. Go to https://github.com/signup?source=login and register for a new account using the provided email (e.g., shivam2023sample@gmail.com) and password (e.g., Model@12034).
2. After registering, click on the "+" symbol and create a new repository with a name like "SpringCloudConfigServerEx".
3. Inside the repository, create a new file named "application.properties" and add some key-value pairs as the configuration properties for your microservices.
4. Commit the changes to the repository.
5. Make a note of the Git link, which should look like this: https://github.com/shivamvishwakarma150/SpringCloudConfigServerEx.git.

**Stage 2: Create Config Server Application**
1. Create a new Spring Boot application named "SpringCloudConfigServerEx" with a dependency on "Config Server".
2. In the main class of the application, add the `@EnableConfigServer` annotation to enable the Config Server functionality.
3. Configure the application.properties file for the Config Server with the following properties:
   - `server.port=8888`: Set the port number for the Config Server.
   - `spring.cloud.config.server.git.uri`: Set the Git URI to the repository created in Stage 1.
   - `spring.cloud.config.server.git.username`: Set the username for accessing the Git repository.
   - `spring.cloud.config.server.git.password`: Set the password for accessing the Git repository.
   - `spring.cloud.config.server.git.default-label`: Set the default branch or label of the Git repository (e.g., "main").
4. Run the main class of the Config Server application.
5. You can test the Config Server by accessing the URL http://localhost:8888/actuator/refresh, which triggers a refresh of the configuration.

**Stage 3: Integrate Every Microservice with Config Server**
1. Add the Config Client dependency to each microservice project.
   - Right-click on the project and select "Spring Tools" > "Add Starter" > "Config Client".
   - Select the checkbox for the pom.xml file and click "Next" and then "Finish" to add the dependency.
2. In the application.properties file of each microservice, add the following property to specify the location of the Config Server:
   - `spring.config.import=optional:configserver:http://localhost:8888`

**Execution Order:**
1. Run the Config Server and Eureka Server.
2. Run the microservice applications.
3. Access the URL http://localhost:8084/cart/info (replace "8084" with the appropriate port of your microservice) to test the integration with the Config Server.

By following these stages, you'll be able to set up the Spring Cloud Config Server, store the configuration properties in a GitHub repository, and integrate the microservices with the Config Server to fetch their respective configurations at runtime.

**`Explanation:`**

The message "ConfigServerConfigDataLoader: Fetching config from server at: http://localhost:8888" indicates that the Cart Service or Order Service, which are both configured as Config Clients, are attempting to fetch configuration properties from the Spring Cloud Config Server.

When the Cart Service or Order Service applications start up, they make a request to the Config Server specified by the URL "http://localhost:8888" to retrieve their respective configuration data. This data includes key-value pairs defined in the application.properties file stored in the GitHub repository configured for the Config Server.

The ConfigServerConfigDataLoader class is responsible for handling the retrieval of configuration data from the Config Server. It initiates a request to the specified URL and retrieves the configuration properties for the respective service.

This log message indicates that the Cart Service or Order Service successfully connected to the Config Server and fetched the necessary configuration properties for their runtime environment. The retrieved properties will be used to configure the behavior and settings of the respective microservices.

Overall, this log message confirms that the Cart Service or Order Service is properly integrated with the Spring Cloud Config Server and can dynamically fetch configuration properties at runtime.