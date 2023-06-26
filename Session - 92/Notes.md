
**Problem Statement:**
When we start microservice (MS#) applications integrated with Eureka and Config Server, any modifications or updates made to the key-value pairs in the Config Server's Git properties file do not take effect in the MS# apps until they are restarted. This limitation hampers the dynamic configuration capabilities of the Config Server.

**Solution Statement:**
To address this problem, we can implement the "Refresh Scope" mechanism in the MS# apps. By doing so, any changes made to the key-value pairs in the Config Server's Git properties file will be propagated to the MS# apps without requiring a restart. This allows for dynamic updating of configurations at runtime.

**Detailed Explanation:**

**1. Add Dependencies:**
   - To enable the Refresh Scope functionality in the MS# app, we need to include the appropriate dependencies. In this case, we need to add the `spring-cloud-starter-config` and `spring-boot-starter-actuator` dependencies to the MS# app's pom.xml file.

**2. Enable Refresh Scope:**
   - To enable the Refresh Scope for a specific component or bean in the MS# app, we can annotate it with `@RefreshScope`.
   - For example, if we have a configuration class or a bean that depends on the configuration properties fetched from the Config Server, we can annotate that class or bean with `@RefreshScope`.

**3. Implement Refresh Endpoint:**
   - To trigger the refresh process and update the configuration properties in the MS# app, we need to expose an endpoint that can receive the refresh request.
   - This can be done by adding the `@RestController` and `@RefreshScope` annotations to a controller class, along with a method annotated with `@RefreshScope` and `@PostMapping("/refresh")`.
   - When a POST request is made to this endpoint, the `@RefreshScope`-annotated components in the MS# app will be refreshed, and the updated configuration properties will take effect.

**4. Trigger Refresh:**
   - To initiate the refresh process, we can use the Actuator's `/refresh` endpoint of the MS# app.
   - For example, by making a POST request to `http://localhost:<port>/actuator/refresh`, the MS# app will fetch the updated configuration properties from the Config Server and apply them without requiring a restart.

By implementing the Refresh Scope mechanism, we enable the MS# app to dynamically update its configuration properties without the need for a full restart. This allows for more flexible and agile configuration management in distributed systems.

**Note:** It's important to consider the implications and potential impacts of dynamically updating configuration properties at runtime. Care should be taken to handle the refresh process gracefully and ensure that the updated configurations are applied correctly throughout the application.

<br/>
<br/>


**Coding Steps:**

1. **Add Actuator Dependency at MS#:**
   - To enable the Actuator endpoints in the MS# app, we need to include the Actuator dependency. This can be done by adding the `spring-boot-starter-actuator` dependency to the MS# app's pom.xml file.
   - Right-click on the MS# project, go to "Spring" > "Add Starters," and choose "Actuator." Proceed to the next step and select the pom.xml checkbox before finishing.

2. **Activate Actuator by adding a key=val in properties:**
   - In order to expose all Actuator endpoints, including the `/refresh` endpoint, we need to activate them by adding a key-value pair to the MS# app's properties file.
   - Add the following property to the `application.properties` file: `management.endpoints.web.exposure.include=*`.
   - This configuration allows all Actuator endpoints to be exposed.

3. **Add `@RefreshScope` annotation to RestController (or main class):**
   - To enable the Refresh Scope functionality in the MS# app, we need to annotate the RestController (or main class) with `@RefreshScope`.
   - This annotation indicates that the components or beans within the annotated class should be refreshed when a refresh event occurs.

4. **Start all apps in order (Eureka, Config Server, MS# apps):**
   - Before testing the refresh functionality, ensure that all required applications are started in the proper order.
   - Start the Eureka Server, followed by the Config Server, and then the MS# apps.

5. **Modify value at GitHub and make a POSTMAN request:**
   - Make changes to the properties file hosted on GitHub (the Config Server's Git repository). Modify the desired key-value pairs.
   - Use a tool like POSTMAN to send a POST request to the `/actuator/refresh` endpoint of the MS# app.
   - Send a POST request to `http://localhost:<port>/actuator/refresh`, where `<port>` is the port number of the MS# app.
   - This request triggers the refresh process, causing the MS# app to fetch the updated configuration properties from the Config Server.

6. **Implement POST call using RestTemplate and a Scheduler Service:**
   - To automate the refresh process, we can implement a Scheduler Service that periodically sends a POST request to the `/actuator/refresh` endpoint using RestTemplate.
   - By doing so, the MS# app will always have the latest configuration properties without requiring manual intervention.
   - The Scheduler Service can be implemented using Spring's `@Scheduled` annotation, allowing us to define the interval at which the refresh request should be sent.

By following these coding steps, the MS# app can benefit from the Refresh Scope functionality, allowing it to dynamically update its configuration properties without the need for a full restart. The Actuator's `/refresh` endpoint, along with the RestTemplate and Scheduler Service, automate the refresh process, ensuring that the app always has the latest configuration properties.

**Note:** When using the Refresh Scope, it's important to consider the impact of configuration updates on the runtime behavior of the application. Care should be taken to handle any potential inconsistencies or issues that may arise from dynamically updating the configuration properties at runtime.

<br/>
<br/>


# **Class: SpringCloudSchedulerService**
```java
package com.app.shivam.scheduler;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FetchLatestDataFromProps {

	@Scheduled(cron = "10 * * * * *")
	public void fetch() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>("{}", headers);
		
		RestTemplate rt = new RestTemplate();
		String output = rt.postForEntity(
				"http://localhost:8082/actuator/refresh", entity, String.class)
				.getBody();
		System.out.println(output);
	}
}
```

**Explanation:**
This class `SpringCloudSchedulerService` is responsible for scheduling a task to fetch the latest data from the properties file hosted on the Config Server. It uses the `@Scheduled` annotation to define the scheduling interval. In this case, the task is scheduled to run every 10 seconds.

The `fetch()` method is invoked at the specified interval. It prepares the HTTP headers with the content type set to JSON and creates an empty HTTP entity. It then creates a `RestTemplate` object to perform the HTTP POST request.

The `RestTemplate` sends a POST request to the `/actuator/refresh` endpoint of the MS# app running on `localhost:8082`. It includes the empty HTTP entity and expects a `String` response. The response body is retrieved and printed to the console.

This code ensures that the MS# app periodically fetches the latest configuration properties from the Config Server without manual intervention. It helps to keep the app up-to-date with any changes made to the properties file.

**Conclusion:**
By implementing the `SpringCloudSchedulerService` class, we have created a scheduler service that automatically fetches the latest data from the properties file hosted on the Config Server. The scheduled task runs at a specified interval and sends an HTTP POST request to the `/actuator/refresh` endpoint, triggering the refresh of the MS# app's configuration properties.

This approach eliminates the need for manual intervention to refresh the configuration and ensures that the MS# app always has the most up-to-date properties.

<br/>
<br/>
<br/>

# Note

**Explanation:**

1. `ConfigServerConfigDataLoader`:
The `ConfigServerConfigDataLoader` class is provided by the Spring Cloud Config Client library. It is responsible for fetching the latest key-value pairs from the Config Server. It internally utilizes the `Environment` interface to process the property sources and load the configuration data.

2. Fetching Data from Config Server:
The `ConfigServerConfigDataLoader` fetches the configuration data from the Config Server using the provided URL and credentials. It communicates with the Config Server to retrieve the latest key-value pairs defined in the properties files.

3. PropertySource Process:
Once the data is fetched from the Config Server, the `ConfigServerConfigDataLoader` processes the data and stores it in the `Environment` instance of the MS# app. The `Environment` interface represents the application's environment and provides methods to access and manage configuration properties.

4. Key Priority:
If a key is present both in the MS# app's local properties file and in the properties file hosted on the Config Server, the value from the Config Server takes precedence. This means that the properties defined in the Config Server will override any duplicate properties in the local properties file of the MS# app.

This behavior allows for centralized configuration management. The Config Server acts as a single source of truth for the configuration properties, and the MS# app fetches the latest values from the server, ensuring consistency across multiple instances of the app.

By utilizing the `ConfigServerConfigDataLoader` and the configuration provided by Spring Cloud Config Client, the MS# app can easily fetch the latest key-value pairs from the Config Server and handle property overrides based on the priority defined by the Config Server.