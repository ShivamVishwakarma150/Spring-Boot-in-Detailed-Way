**Plan -> Document -> Development -> Build -> Test -> Release -> Monitor**

**Monitoring Tools:**

1. **Admin Server (+Actuator):**
   - **Overview:** 
     - Admin Server is a monitoring tool that provides a graphical user interface (GUI) for checking the current status of the applications or microservices.
     - It integrates with Actuator to display production-ready endpoints and services.
   - **Functionality:**
     - Health Checking: Admin Server allows users to view health checks for the applications or microservices.
     - Beans Details: It provides detailed information about the beans used in the application, including their configurations and dependencies.
     - URL Mapping Information: Admin Server displays the mapping information, including URLs and HTTP methods, for the endpoints exposed by the applications or microservices.
     - Cache Details: It gives insights into the caching mechanism and cache statistics.
     - Heap Information: Admin Server provides information about the application's heap usage and memory allocation.
     - Thread Dumps: It allows users to obtain thread dumps, which are useful for diagnosing performance issues and identifying bottlenecks.
     - Other Features: Admin Server may offer additional features such as logging, metrics, and more.

2. **Zipkin and Sleuth:**
   - **Overview:**
     - Zipkin and Sleuth are distributed tracing systems designed for monitoring and debugging microservices architectures.
     - They provide insights into the flow of requests between microservices, enabling developers to identify performance bottlenecks, latency issues, and dependencies between services.
   - **Functionality:**
     - Distributed Tracing: Zipkin and Sleuth track the path of requests as they propagate through multiple microservices, capturing timing information and context.
     - Performance Analysis: They help analyze the performance of individual microservices and identify any issues affecting the overall system performance.
     - Latency Monitoring: By measuring the time taken by requests at each microservice, Zipkin and Sleuth enable the identification of services causing latency problems.
     - Dependency Mapping: These tools create a dependency graph of the microservices, allowing developers to visualize the relationships between services and identify potential points of failure or bottlenecks.
     - Debugging and Troubleshooting: Zipkin and Sleuth facilitate debugging and troubleshooting by providing detailed information about request flows, including logs and tracing data.

**Explanation:**

1. **Plan:**
The planning phase involves defining the overall strategy, goals, and requirements for the development and deployment of the application or microservices (MS#). It includes determining the monitoring requirements and selecting the appropriate monitoring tools.

2. **Document:**
In the documentation phase, the monitoring requirements and the selected monitoring tools are documented. This includes specifying the desired functionality and features of the monitoring tools, as well as any specific configuration or setup instructions.

3. **Development:**
During the development phase, the application or microservices are implemented. This involves writing the code, configuring the necessary components, and integrating any required dependencies.

4. **Build:**
The build phase involves compiling the source code, running automated tests, and packaging the application or microservices into deployable artifacts, such as JAR or WAR files. The build process ensures that the code is executable and ready for deployment.

5. **Test:**
In the testing phase, the application or microservices are tested to verify their functionality, performance, and reliability. This includes running unit tests, integration tests, and any other required tests to ensure that the monitoring tools and features are working correctly.

6. **Release:**
The release phase involves deploying the application or microservices to the production environment. This can be done manually or through automated deployment processes. The release process ensures that the application is available for users to access and interact with.

7. **Monitor:**
Monitoring tools such as Admin Server, Actuator, Zipkin, and Sleuth are used to monitor the running applications or microservices.

- **Admin Server:**
Admin Server is a monitoring tool that provides a graphical user interface (GUI) for checking the current status of the applications or microservices. It integrates with Actuator to display production-ready endpoints and services. It allows users to view health checks, detailed bean information, URL mappings, cache details, heap information, and thread dumps, among other features.

- **Actuator:**
Actuator is a set of production-ready endpoints that provide commonly used services for applications or microservices. These endpoints expose various management and monitoring capabilities, such as health checking, metrics, logging, and more. Actuator allows developers and operations teams to gain insights into the application's health, performance, and other runtime aspects.

- **Zipkin and Sleuth:**
Zipkin and Sleuth are distributed tracing systems that help monitor and debug microservices architectures. They provide insights into the flow of requests between microservices, enabling developers to identify performance bottlenecks, latency issues, and dependencies between services.

By integrating these monitoring tools into the application or microservices, developers and operations teams can gain visibility into the system's health, performance, and other runtime aspects. This enables proactive monitoring, debugging, and troubleshooting to ensure the smooth operation of the applications in the production environment.

<br/>
<br/>

# **Steps to Activate Actuator Services:**

1. **Add Actuator Dependency in MS#:**
To activate Actuator services in your MS# application, you need to add the Actuator dependency to your project. Follow these steps:
- Right-click on your MS# project.
- Select "Add Starter" and choose "Actuator".
- Click "Next" and make sure the checkbox for the pom.xml file is selected.
- Click "Finish" to add the Actuator dependency to your project's pom.xml file.

Example pom.xml configuration:
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

2. **Enable Services:**
To enable Actuator services, you need to configure the management endpoints in your application's properties file. Follow these steps:
- Open the application.properties or application.yml file.
- Add the following configuration to enable all services:
  ```
  management.endpoints.web.exposure.include=*
  ```
  Alternatively, you can specify specific services to be enabled by listing them comma-separated. For example:
  ```
  management.endpoints.web.exposure.include=health,beans,env
  ```

3. **Access Actuator Endpoints:**
Once you have added the Actuator dependency and enabled the services, you can access the Actuator endpoints to check their details. Follow these steps:
- Open a web browser.
- Enter the URL in the following format:
  ```
  http://IP:PORT/actuator
  ```
  Replace "IP" with the IP address or hostname of your application's server and "PORT" with the port number on which your application is running. For example:
  ```
  http://localhost:8082/actuator
  ```
- After accessing the Actuator base URL, you can append the specific service URL to retrieve its details. For example:
  - To check the details of the "beans" service:
    ```
    http://localhost:8082/actuator/beans
    ```
  - To check the details of the "env" service:
    ```
    http://localhost:8082/actuator/env
    ```

**Note:**
1. By default, if no include services are specified, only the "health" service is activated. In older versions up to 2.4.x, both "health" and "info" services were activated by default.
2. You can customize the list of included services by modifying the `management.endpoints.web.exposure.include` property in the application.properties or application.yml file.

By following these steps, you can activate Actuator services in your MS# application and access the various endpoints to retrieve information about your application's health, beans, environment, and more.

<br/>
<br/>

# **Spring Cloud Admin Server:**

Spring Cloud Admin Server is a central server that provides a monitoring dashboard to view and monitor the Actuator results of multiple microservices (MS#) connected to it via Admin Clients. The Admin Server acts as a centralized hub to collect and display information from various microservices.

Key Points:

1. **MS# Connecting to Admin Server:**
   Each microservice (MS#) needs to connect to the Admin Server using an Admin Client. The Admin Client is responsible for sending Actuator information and metrics from the microservice to the Admin Server for monitoring and display.

2. **Enabling Actuator at MS#:**
   To enable monitoring of a microservice through the Admin Server, the Actuator module must be enabled in the microservice. The Actuator provides production-ready endpoints that expose various management and monitoring capabilities. These endpoints include health checks, metrics, logging, and more. By enabling Actuator in the microservice, the Admin Client can read and send this information to the Admin Server for monitoring.

3. **Admin Server Dashboard:**
   The Admin Server provides a monitoring dashboard that displays the collected Actuator results from the connected microservices. The dashboard provides a consolidated view of the health, metrics, and other relevant information of the microservices. It allows administrators and developers to monitor the status and performance of the microservices in a centralized and easily accessible manner.

4. **Benefits of Admin Server:**
   - Centralized Monitoring: The Admin Server allows for centralized monitoring of multiple microservices, providing a unified view of their status and metrics.
   - Streamlined Management: It simplifies the management of microservices by providing a single dashboard to monitor and track the health and performance of the entire system.
   - Real-time Updates: The Admin Server receives real-time updates from the connected microservices, ensuring that the displayed information is always up to date.
   - Customizable Dashboard: The Admin Server dashboard can be customized to display specific metrics and information that are relevant to the application's monitoring requirements.

By connecting microservices to the Admin Server using Admin Clients and enabling Actuator in the microservices, developers and administrators gain a centralized monitoring solution that provides insights into the health, metrics, and other relevant information of the microservices. This facilitates effective management and troubleshooting of the distributed system.

<br/>
<br/>
<br/>

# **Coding Steps:**

1. **Admin Server**
   - Create a new project named "SpringCloudAdminServer" and add the dependency for Admin Server.
   - In the main class, annotate it with `@EnableAdminServer` to enable Admin Server functionality.
   - Set the server port in the `application.properties` file using `server.port=9999`.

2. **Microservice (MS#)**
   - Add two dependencies to the microservice: Actuator and Admin Client.
   - In the microservice's properties file, configure the following:
     - Activate Actuator by including all endpoints: `management.endpoints.web.exposure.include=*`.
     - Connect the microservice to the Admin Server by specifying the Admin Server's URL: `spring.boot.admin.client.url=http://localhost:9999`.

**Execution Order:**
1. Start the Eureka Server, Config Server, and Admin Server.
   - Eureka Server is responsible for service discovery and registration.
   - Config Server provides the configuration for the microservices.
   - Admin Server acts as the central monitoring server.
2. Start the Microservice (MS#) applications.
   - Create multiple instances of the microservices for testing purposes.

**Endpoints to Check:**
- Eureka Server: Access the Eureka Server dashboard to view the registered microservices: `http://localhost:8761/`
- Config Server Refresh: Refresh the configuration from the Config Server to apply any changes made: `http://localhost:8888/actuator/refresh`
- Admin Server Applications: Access the Admin Server's application dashboard to view the monitored microservices: `http://localhost:9999/applications`

By following these steps and executing the applications in the specified order, you will have an Admin Server that can monitor and display information about the registered microservices. The microservices will connect to the Admin Server using the Admin Client, and the Actuator endpoints will provide the necessary information for monitoring.