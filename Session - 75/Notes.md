## **`POSTMAN:`**

POSTMAN is a versatile tool used for testing and debugging applications by making HTTP requests. It provides a user-friendly interface where you can manually enter various details to interact with APIs. Let's dive into the different components that can be configured in POSTMAN:

1. Method Type: It refers to the HTTP method or verb to be used for the request, such as GET, POST, PUT, DELETE, etc. Each method has a specific purpose and determines how the server should handle the request.

2. URL: This is the Uniform Resource Locator that specifies the address of the API endpoint you want to interact with. It includes the protocol (e.g., HTTP, HTTPS), the domain or IP address, and the path to the specific resource on the server.

3. Headers: HTTP headers carry additional information about the request or response. You can manually add headers like Authorization, Content-Type, Accept, etc. These headers provide context and instructions to the server or client regarding the request/response handling.

4. Body: The request body contains data that needs to be sent to the server, usually in JSON or XML format. It is used for sending information when creating, updating, or deleting resources. POSTMAN allows you to enter the body content and specify the content type.

5. Type: This refers to the type of request, such as raw, form-data, x-www-form-urlencoded, binary, etc. It determines how the request body or parameters are structured and transmitted to the server.

## **`Swagger:`**
Swagger is an open-source framework used to document APIs. It provides a convenient way to describe the details of API endpoints, request/response formats, authentication requirements, and other related information. Here's a breakdown of Swagger's characteristics:

1. One-time configuration: Swagger requires a one-time setup where developers define the API specifications using Swagger's syntax. This involves writing code to describe the structure and behavior of the API endpoints, including input/output models, parameters, response codes, and more.

2. Documentation: Swagger automatically generates documentation based on the provided configuration. This documentation includes details about each endpoint, such as the URL, HTTP methods, parameters, response formats, and even example requests and responses. It simplifies the process of documenting the API and keeps it in sync with the actual implementation.

## **`OpenAPI:`**
OpenAPI is a specification that evolved from Swagger and provides a standardized format for describing RESTful APIs. Let's explore the key aspects of OpenAPI:

1. No configuration: Unlike Swagger, OpenAPI does not require any additional code-based configuration. Instead, you can directly enter the API details into the OpenAPI specification. This makes it more user-friendly and reduces the setup effort.

2. Endpoint documentation: OpenAPI allows you to document all the endpoint details, such as the URL, HTTP methods, request/response schemas, authentication requirements, and more. With this information, various tools can automatically generate comprehensive documentation, client SDKs, and server stubs, saving time and effort.

In summary, POSTMAN is a tool primarily used for testing and debugging APIs by manually entering request details. Swagger and OpenAPI, on the other hand, focus on documenting APIs. Swagger requires a one-time configuration by writing code to describe the API, while OpenAPI allows you to directly enter API details into the specification without additional coding. Both Swagger and OpenAPI enable the generation of comprehensive API documentation and related artifacts.

<br/>
<br/>

# **The steps mentioned for `Swagger `configuration:**

1. Add Two Dependencies: To use Swagger in your Spring Boot application, you need to add two dependencies in your project's build file (e.g., pom.xml for Maven). These dependencies are "springfox-swagger2" and "springfox-swagger-ui". These libraries provide the necessary classes and resources for Swagger integration.

2. Exception Handling: If you are using Spring Boot 2.7.x, you may encounter a NullPointerException related to "org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.toString()". To resolve this issue, you can add the following configuration in your application.properties file: "spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER". This configuration sets the matching strategy for URL paths.

   Note: If you are using Spring Boot 2.6.x or earlier versions, you will not encounter this exception.

3. Swagger UI URL: Once you have added the dependencies and resolved any exceptions, you can access the Swagger UI by visiting the following URL: "http://localhost:9690/swagger-ui.html". This URL provides a web interface where you can interact with your API documentation.

4. Docket Configuration: In your Spring Boot application, you need to configure the Docket bean, which is the main interface for Swagger integration. Here's an explanation of the different configuration steps within the Docket:

   - Full Document Display: By specifying "DocumentationType.SWAGGER_2" in the Docket bean configuration, you indicate that you want to generate a complete API documentation webpage.

   - RestController Discovery: The ".select()" method is used to specify the API controllers to include in the Swagger documentation. The "RequestHandlerSelectors.basePackage" method allows you to define the base package where your RestControllers are located. Swagger will scan this package to find the RestControllers and include them in the generated documentation.

   - Common Path Prefix: The ".paths(PathSelectors.regex())" method is used to filter the RestControllers based on the paths they handle. In this case, it selects RestControllers with paths that start with "/v1/api/". You can adjust the regular expression to match the desired common path prefix of your APIs.

   - Path Mapping: The ".pathMapping()" method sets the base path mapping for your API endpoints. It specifies the URL path prefix to be applied to all API paths displayed in the Swagger documentation. In this case, the base path is set to "/" (root path).

   - Metadata Display: The ".apiInfo()" method allows you to provide metadata about your API documentation. This includes details such as the API title, description, version, terms of service, contact information, and license. You can use the "apiInfo()" method to pass an instance of the "ApiInfo" class with the relevant metadata.

By following these steps, you can configure Swagger in your Spring Boot application to generate API documentation based on your RestControllers. The Swagger UI provides an interactive interface where you can explore and test your APIs.

<br/>
<br/>

# **Here's the breakdown of the coding steps for Swagger and OpenAPI:**

**`Swagger:`**

Step 1: pom.xml

```xml
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger2</artifactId>
  <version>2.9.2</version>
</dependency>

<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger-ui</artifactId>
  <version>2.9.2</version>
</dependency>
```

Step 2: Swagger Config class

```java
package com.app.shivam.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket createDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.app.shivam.rest"))
        .paths(PathSelectors.regex("/v1/api.*"))
        .build()
        .pathMapping("/")
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("STUDENT APP",
        "SAMPLE APP",
        "3.2GA",
        "http://sample.com",
        new Contact("shivam", "http://github.com/javabyshivam", "javabyshivam@gmail.com"),
        "APACHE",
        "http://apache.com",
        Collections.emptyList());
  }
}
```

Step 3: Properties file

```
spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER
```

Step 4: Run App and Enter URL

```
http://localhost:9690/swagger-ui.html
```

**`OpenAPI:`**

Step 1: pom.xml

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-ui</artifactId>
  <version>1.6.13</version>
</dependency>
```

Step 2: Annotation at Starter class

```java
@OpenAPIDefinition
```

Step 3: Enter URL

```
http://localhost:9690/swagger-ui/index.html
```

Now, let's provide a detailed explanation for each step:

**Swagger:**

Step 1: In your project's pom.xml file, you need to add the dependencies for springfox-swagger2 and springfox-swagger-ui. These dependencies provide the required classes and resources for Swagger integration.

Step 2: Create a Swagger configuration class, typically named SwaggerConfig. Annotate the class with @Configuration and @EnableSwagger2 to enable Swagger and make it a bean in the Spring context. Inside the configuration class, create a Docket bean using the Docket builder. Configure the Docket with the necessary options, such as selecting the base package for scanning RestControllers, specifying API paths using regex, and setting the path mapping. Also, define the API information using the ApiInfo class.

Step 3: In the application.properties or application.yml file, add the configuration "spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER" to resolve any potential NullPointerException issues related to path matching.

Step 4: Run your Spring Boot application and access the Swagger UI using the provided URL. This URL allows you to explore and interact with your API documentation.

**OpenAPI:**

Step 1: Add the dependency for springdoc-openapi-ui in your pom.xml file. This dependency provides the necessary classes and resources for OpenAPI integration.

Step 2: Annotate your application's starter class (the class annotated with @SpringBootApplication or @EnableAutoConfiguration) with @OpenAPIDefinition. This annotation marks the class as the starting point for OpenAPI documentation generation.

Step 3: Access the OpenAPI UI using the provided URL. This URL allows you to view the auto-generated OpenAPI documentation for your API.

In conclusion, both Swagger and OpenAPI provide ways to generate API documentation. Swagger requires a separate configuration class where you define the Docket bean and its settings. OpenAPI, on the other hand, only requires the annotation at the starter class. Both approaches offer an interactive UI for exploring and testing your API documentation.