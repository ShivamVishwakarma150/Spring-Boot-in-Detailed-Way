## **Global Data Formats (XML/JSON)**

When working with complex data inputs and outputs like JSON and XML in Spring Boot, we can utilize MediaType annotations to handle these data formats effectively.

**1. @RequestBody:**
- The `@RequestBody` annotation reads the HTTP request body section.
- It examines the `Content-type` header of the request to determine the data format (e.g., JSON, XML).
- The annotation reads the data from the body and converts it into an object.
- The converted object is then passed as a method parameter.
- This annotation is commonly used in controller methods to receive and process data sent by the client.

**2. @ResponseBody:**
- The `@ResponseBody` annotation writes the HTTP response body section.
- If the return type of a method is a non-String type (e.g., a class or a collection), Spring Boot automatically converts the returned object into a global data format (e.g., JSON, XML).
- The converted data is set as the response body.
- Additionally, the `Content-type` header of the response is updated accordingly.
- This annotation is often used in controller methods to send data back to the client in the desired format.

By using these annotations, Spring Boot simplifies the handling of complex data formats and handles the conversions between Java objects and the specified data format (XML or JSON) automatically. This makes it easier to develop RESTful APIs that can seamlessly accept and return data in the format required by the client.


<br/>
<br/>

## **Note:**

1. The annotations `@RequestBody` and `@ResponseBody` are applicable only when working with non-String types. These annotations are commonly used when dealing with complex data structures like objects (e.g., Employee) or collections (e.g., List<Product>).

2. Data validation of the request can be performed using annotations. Spring Boot provides a variety of validation annotations, such as `@NotNull`, `@Size`, `@Pattern`, etc., that can be applied to the method parameters or object fields to enforce validation rules.

3. By default, Spring Boot supports JSON conversions using the JACKSON library. This means that if the data format is JSON, Spring Boot will automatically convert the Java objects to JSON and vice versa. However, there is no default support for XML conversions in Spring Boot. If XML conversions are required, additional configurations or libraries need to be added.

4. When the `@RestController` annotation is used, it internally includes the `@ResponseBody` annotation. The `@RestController` annotation is a convenience annotation that combines the `@Controller` and `@ResponseBody` annotations. It is commonly used to create RESTful APIs where the response from the controller methods is automatically serialized into the desired data format (e.g., JSON) and sent back as the response body.

These annotations and features provided by Spring Boot simplify the handling of data formats and validations in RESTful applications. They allow developers to focus on the business logic of their application while letting Spring Boot take care of the data conversion and validation aspects.

<br/>
<br/>

## **JSON Syntax:**

a) For one Object:
```
{
  "key1": value1,
  "key2": value2,
  "key3": value3
}
```
In JSON, an object is represented by a set of key-value pairs enclosed in curly braces {}. Each key is a string, followed by a colon :, and then the corresponding value. The key-value pairs are separated by commas.

b) For one List/Array/Set:
```
[
  element1,
  element2,
  element3
]
```
In JSON, a list or array is represented by square brackets []. Elements in the list are separated by commas.

c) For one Map/Properties:
```
{
  "key1": value1,
  "key2": value2,
  "key3": value3
}
```
In JSON, a map or properties object is similar to a regular object. It consists of key-value pairs, where the keys are strings and the values can be of any valid JSON type. The key-value pairs are enclosed in curly braces {}.

JSON is a widely used data format for representing structured data. It is lightweight, human-readable, and easy to parse and generate. JSON is commonly used for data exchange between a client and a server in web applications, including RESTful APIs.

<br/>
<br/>

#  Here's the code for the first class and its explanation:

```java
package com.app.shivam.entity;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty("user-unq-id")
    private Integer userId;

    @JsonProperty("user-first-name")
    private String userName;

    private String userRole;

    @JsonIgnore
    private String password;

    private Set<String> permissions;

    private Map<String,String> models;
}
```

Explanation:
- The `User` class represents an entity with various properties such as `userId`, `userName`, `userRole`, `password`, `permissions`, and `models`.
- The `@JsonProperty` annotation is used to specify the JSON property name for a field. In this case, `user-unq-id` is used for `userId` and `user-first-name` is used for `userName`.
- The `@JsonIgnore` annotation is used to exclude a field from JSON serialization and deserialization. Here, it is applied to the `password` field.
- The `@Data` annotation is from the Lombok library, which generates getter, setter, `equals()`, `hashCode()`, and `toString()` methods for the class.
- The `@NoArgsConstructor` and `@AllArgsConstructor` annotations are also from Lombok and generate constructors with no arguments and all arguments, respectively.

Next, here's the code for the second class and its explanation:

```java
package com.app.shivam.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.Role;
import com.app.shivam.entity.User;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @GetMapping("/one")
    public ResponseEntity<User> getOneObj() {
        User ob = new User(101, "ABC",  "Dev", "PASSWD1234",
                Set.of("P1","P2"),
                Map.of("M1","AA","M2","AB")
                );

        ResponseEntity<User> response = new ResponseEntity<>(ob, HttpStatus.OK);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = Arrays.asList(
                    new Role(1100, "ADMIN"),
                    new Role(1102, "BA"),
                    new Role(1103, "QA")
                );

        ResponseEntity<List<Role>> response = new ResponseEntity<>(roles, HttpStatus.OK);
        return response;
    }

    @GetMapping("/map")
    public ResponseEntity<Map<String,Role>> getMappedRoles() {
        Map<String,Role> rolesMap = Map.of(
                    "R1", new Role(1100, "ADMIN"),
                    "R2", new Role(1102, "BA"),
                    "R3", new Role(1103, "QA")
                );

        ResponseEntity<Map<String,Role>> response = new ResponseEntity<>(rolesMap, HttpStatus.OK);
        return response;
    }
}
```

Explanation:
- The `UserRestController` class is a Spring `RestController` responsible for handling requests related to user operations.
- It is mapped to the `/user` endpoint.
- The `getOneObj()` method returns a single `User` object as the response. The object is created with sample data and wrapped in a `ResponseEntity<User>`, which allows specifying the HTTP status code.
- The `getAllRoles()` method returns a list of `Role` objects as the response. The roles are created using sample data and wrapped in a `ResponseEntity<List<Role>>`.
- The `getMappedRoles()` method returns a map of role names to `Role` objects as the response. The map is created using sample data and wrapped in a `ResponseEntity<Map<String,Role>>`.

URLs:
- To retrieve a single user object: `http://localhost:9090/user/one`
- To retrieve a list of roles: `http://localhost:9090/user/list`
- To retrieve a mapped representation of roles: `http://localhost:9090/user/map`

In conclusion, the code provided showcases a Spring Boot application with a `User` entity class and a `UserRestController` class. The `UserRestController` handles requests related to users and returns different types of responses, including a single object, a list, and a mapped representation.

<br/>
<br/>

# Here's a detailed explanation of the notes you mentioned:

1. Use `@JsonIgnore` to avoid a variable/field in JSON operations:
   - The `@JsonIgnore` annotation is used in Java classes to exclude a specific variable or field from JSON serialization and deserialization.
   - When a field is annotated with `@JsonIgnore`, it will not be included in the JSON representation of the object during serialization.
   - Similarly, during deserialization, the field will be ignored and not mapped from the JSON input.
   - This annotation is useful when you have certain fields in your class that you don't want to expose or include in the JSON representation.
   - In the provided code example, the `password` field in the `User` class is annotated with `@JsonIgnore`, indicating that it should not be included in JSON operations.

2. Use `@JsonProperty` to give alias names to variables:
   - The `@JsonProperty` annotation is used to specify the JSON property name for a field or getter/setter method in a Java class.
   - By default, the JSON property name is derived from the variable or method name, but `@JsonProperty` allows you to provide a custom name.
   - This annotation is useful when you want to map a different name in JSON than the actual variable or method name in your Java class.
   - In the provided code example, `@JsonProperty` is used to give alias names to variables in the `User` class.
   - For example, `@JsonProperty("user-unq-id")` specifies that the `userId` field should be serialized and deserialized as `user-unq-id` in JSON, and `@JsonProperty("user-first-name")` specifies that the `userName` field should be serialized and deserialized as `user-first-name` in JSON.

By using `@JsonIgnore` and `@JsonProperty` annotations, you have more control over the JSON representation of your objects. You can exclude certain fields from serialization and deserialization using `@JsonIgnore`, and provide custom names for variables using `@JsonProperty`. These annotations help in shaping the JSON structure and mapping between Java objects and JSON data.

<br/>
<br/>

# Here are the answers the questions FAQ's:

**Q) Write one Example using Maven for JSON/JACKSON?**<br/>

```xml
<!-- Add Jackson dependency -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.4</version>
</dependency>
```

**Q) Do we need to apply @ResponseBody in Spring Boot REST?**<br/>
A) Not required. By default, the `@RestController` annotation includes `@ResponseBody` functionality. It automatically converts the method return value to the appropriate format (e.g., JSON) and includes it in the response body.

**Q) How can we avoid one field/variable in JSON operations?**<br/>
A) You can use the `@JsonIgnore` annotation to exclude a specific field or variable from JSON operations. When a field is annotated with `@JsonIgnore`, it will not be included in the JSON representation during serialization and will be ignored during deserialization.

**Q) What will happen if a variable holds null in JSON operations?**<br/>
A) If a variable holds `null`, it will be included in the JSON representation as `null`. When the JSON is deserialized, the variable will be set to `null` as well.

**Q) How can we provide alias names to variables in JSON?**<br/>
A) You can use the `@JsonProperty` annotation to provide alias names for variables in JSON. By annotating a field or getter/setter method with `@JsonProperty("aliasName")`, you specify that the variable should be serialized and deserialized using the specified `aliasName` in the JSON representation.

<br/>
<br/>

#  Here's a detailed explanation of working with XML in Spring Boot:

**Step#1 Add Dependency:**
To work with XML in Spring Boot, you need to include the Jackson XML data format library as a dependency in your project. The `jackson-dataformat-xml` library provides support for reading and writing XML using the Jackson JSON library.

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

By adding this dependency to your project's `pom.xml` file, you enable XML support in Spring Boot.

**Step#2 Set Accept Header:**
When making a request to receive an XML response, you need to include the `Accept` header with the value `application/xml`. This informs the server that you expect the response in XML format.

By setting the `Accept` header to `application/xml`, you indicate to the server that you prefer XML as the response format. If the server does not support XML or the requested resource cannot be provided in XML format, you may receive a `406 Not Acceptable` response code.

It's important to note that without adding the `jackson-dataformat-xml` dependency and setting the `Accept` header, the server may not be able to provide the response in XML format, resulting in an unsupported media type response.

These steps ensure that your Spring Boot application can handle XML requests and responses effectively.


