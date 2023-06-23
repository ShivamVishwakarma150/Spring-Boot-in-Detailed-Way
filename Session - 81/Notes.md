# Spring Boot - Redis Cache



- **Problem**:
When the number of network calls between the server (application) and the database increases, it can result in a decrease in application performance. This is because each network call introduces latency and consumes resources, causing the application to take more time to execute all the network calls.

1. **Cache**:
Cache is a mechanism used to store frequently accessed data on the server side, reducing the number of network calls required to retrieve that data from the database. It acts as an intermediary between the application and the database, storing a copy of the data in memory for quick access.

1. **Cache Exist at server side**:
The cache is maintained on the server side, typically within the application's memory or a separate caching system like Redis. Storing the cache on the server side allows for fast access to the data without the need to make repetitive network calls to the database.

1. **Cache is also one type of database**:
While a cache is not a traditional database, it can be considered a specialized type of database that focuses on providing fast access to frequently accessed data. Unlike a regular database, a cache typically has a smaller capacity and is optimized for read operations rather than complex querying or data manipulation.

1. **Cache reduces network calls**:
By storing frequently accessed data in the cache, the number of network calls required to retrieve that data from the database is reduced. Instead of fetching the data from the database every time, the application can first check the cache. If the data is found in the cache, it can be directly retrieved from there, saving network round trips and reducing latency.

1. **Cache can store any type of objects**:
Caches are flexible and can store various types of objects, such as product data, email lists, user posts, or any other type of data that is commonly accessed by the application. The data stored in the cache is usually represented as key-value pairs, where the key is used to identify the data and the value is the actual cached data.

1. **Cache is handled by operations**:
To interact with the cache, operations such as `getOne`, `updateOne`, and `deleteOne` are commonly used. These operations allow the application to retrieve a specific piece of data from the cache, update the cached data when it changes in the database, or remove data from the cache when it is no longer valid.

1. **Cache and DB must be in sync**:
To ensure data consistency, it's important to keep the cache and the database in sync. Whenever data in the database is modified (e.g., updated or deleted), corresponding changes should be made to the cache as well. This can be done by invalidating or updating the cache entry associated with the modified data, so that subsequent reads fetch the updated information.

1. **Cache should never be used to store all DB Data**:
Caches are not meant to store all the data present in the database. Caches are most effective when used to store frequently accessed or commonly used data. Operations like `findAll()` or `save()` that deal with large sets of data should not rely heavily on the cache, as it may lead to increased memory usage and cache invalidation challenges.

Overall, caching is a powerful technique to improve application performance by reducing the number of network calls to the database. It enables faster data access and better responsiveness. However, it's important to carefully select what data to cache, keep the cache and database in sync, and ensure proper cache management to reap the benefits of caching effectively.

<br/>
<br/>


# **Redis Cache**

1. **Redis** **`(NoSQL Database/Cache/Message Queues)`**:
Redis is an open-source, in-memory data structure store that can be used as a NoSQL database, cache, and message broker. It provides high performance, scalability, and a rich set of data structures, making it popular for various use cases.

1. **Redis Cache in Spring Boot**:
To use Redis as a cache in a Spring Boot application, you need to add the `spring-boot-starter-data-redis` dependency to your project. This dependency includes the necessary libraries and configurations to integrate Redis with Spring Boot.

1. **Running Redis Server**:
To use Redis, you need to have a running Redis server. You can download the Redis server from the provided link. After downloading, extract the files to a folder and locate the `redis-server` executable. By double-clicking on the `redis-server` executable, you can start the Redis server on your local machine.

1. **Connecting to Redis in Spring Boot**:
In your Spring Boot application, you need to provide the connection details to connect to the Redis server. This typically includes the following information:
   - Type: Specify that you are using Redis as a cache.
   - IP: Provide the IP address of the Redis server. For a local Redis server, you can use `localhost`. For a Redis server hosted in the cloud, you would use the corresponding IP address.
   - Port: Specify the port number on which the Redis server is listening. The default port for Redis is `6379`.

By configuring these details in your Spring Boot application, it will establish a connection to the Redis server and enable caching functionality.

Using Redis as a cache in a Spring Boot application brings several advantages:
- Redis is an in-memory cache, providing fast data access.
- It offers various data structures and operations, allowing for flexible caching solutions.
- Redis can handle high traffic and large data sets effectively.
- The integration with Spring Boot simplifies the setup and usage of Redis caching.

However, it's important to note that running a Redis server locally is suitable for development or testing purposes. In production environments, you would typically use a Redis server hosted on a cloud platform or managed Redis service to ensure reliability and scalability.

Overall, integrating Redis as a cache in a Spring Boot application enables efficient data caching, improving application performance and reducing the load on the underlying database.

<br/>
<br/>

# **Coding PART**

1. **@EnableCaching**: 
`@EnableCaching` is an annotation in Spring Boot that enables the caching functionality in your application. You typically add this annotation to your main class, which serves as the entry point of your Spring Boot application. By enabling caching, you allow Spring to intercept method calls and cache the results, reducing the need for expensive computations or database queries.

1. **@Cacheable**:
`@Cacheable` is an annotation used to mark a method for caching. When you apply this annotation to a method, Spring will first check if the method's result is already present in the cache based on the provided cache name and key. If the result is found in the cache, it will be returned directly without executing the method. If the result is not found, the method will be invoked, and its return value will be stored in the cache for future use.

1. **@CachePut**:
`@CachePut` is an annotation used to update the cached value associated with a specific key. When you apply this annotation to a method, it ensures that the method's result is stored or updated in the cache, regardless of whether the cache entry already exists or not. This is useful when you want to update the cache with the latest value for a specific key.

1. **@CacheEvict**:
`@CacheEvict` is an annotation used to remove a specific entry from the cache. When you apply this annotation to a method, it removes the corresponding cache entry based on the provided cache name and key. This is useful when you want to evict or invalidate a cached value to ensure that the subsequent method invocations fetch the latest data from the underlying source.

1. **Namespace and Key**:
When using caching annotations, you need to provide a namespace and key to identify the cached objects. The namespace is typically the name of the cache, which groups related objects together. The key is used to uniquely identify each object within the cache. The combination of the namespace and key allows Spring to store, retrieve, and remove objects from the cache accurately.

1. **Implementing Serializable**:
To store objects in the cache, they must be serializable, which means they need to implement the `Serializable` interface. Serialization is the process of converting an object into a byte stream that can be sent over the network or stored in a file. By implementing the `Serializable` interface, objects can be serialized and deserialized, enabling their storage and retrieval from the cache.

By using these caching annotations and configurations, you can leverage the caching capabilities provided by Spring Boot and Redis. It allows you to cache method results, update cache entries, and evict specific entries when needed, improving the performance and efficiency of your application by reducing redundant computations and database queries.

<br/>
<br/>

# Here's the code for each class along with its explanation:

**1. SpringBootRestRedisCacheEx (Main Class)**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootRestRedisCacheEx {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestRedisCacheEx.class, args);
	}

}
```

Explanation:
- `@SpringBootApplication` is the main annotation for Spring Boot applications, which combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
- `@EnableCaching` enables the caching functionality in the application.

**2. application.properties**

```properties
# Server 
server.port=9090

# Cache details
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379

# Database Connection
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/boot7am
spring.datasource.username=root
spring.datasource.password=root

# JPA Details
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=create
```

Explanation:
- This configuration file contains various properties related to the server, cache, database connection, and JPA settings.
- The cache details specify that Redis is used as the cache type, and it is running on `localhost` with port `6379`.
- The database connection details are provided for connecting to the MySQL database.
- The JPA settings specify the SQL logging, database platform, and schema creation mode.

**3. User Entity Class**

```java
package com.app.shivam.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String role;
}
```

Explanation:
- The `User` class is an entity class representing the user entity in the database.
- It implements the `Serializable` interface to support object serialization for caching purposes.
- The `@Data` annotation from Lombok generates the getter, setter, equals, hashCode, and toString methods for the class.
- The `@Entity` annotation indicates that this class is a JPA entity, and it will be mapped to a database table.
- The class has fields for `id`, `name`, and `role`, along with their respective getters and setters.

**4. UserRepository Interface**

```java
package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.shivam.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
```

Explanation:
- The `UserRepository` interface extends the `JpaRepository` interface provided by Spring Data JPA.
- It is responsible for performing CRUD operations on the `User` entity.
- The interface doesn't require any implementation as Spring Data JPA generates the necessary implementations at runtime.

**5. UserService Class**

```java
package com.app.shivam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.app.shivam.entity.User;
import com.app.shivam.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public Integer saveUser(User user) {
		return repo.save(user).getId();
	}

	@Cacheable(value = "users", key = "#userId")
	public User getOneUser(Integer userId) {
		return repo.findById(userId).get();
	}

	@CachePut(value = "users", key = "#userId")
	public void updateUser(Integer userId, User user) {
		User userDb = repo.findById(userId).get();
		userDb.setName(user.getName());
		userDb.setRole(user.getRole());
		repo.save(userDb);
	}

	@CacheEvict(value = "users", allEntries = true)
	public void deleteUser(Integer userId) {
		repo.deleteById(userId);
	}
}
```

Explanation:
- The `UserService` class is a service component that handles business logic related to user operations.
- It is annotated with `@Service` to indicate that it is a Spring-managed service bean.
- The class has a dependency on the `UserRepository` interface, which is autowired using the `@Autowired` annotation.
- The `saveUser()` method saves a new user by invoking the `save()` method from the repository and returns the generated user id.
- The `getOneUser()` method retrieves a user by their id. It is annotated with `@Cacheable` to enable caching for this method. The `value` attribute specifies the cache name, and the `key` attribute specifies the cache key as `userId`.
- The `updateUser()` method updates an existing user by their id. It is annotated with `@CachePut` to update the cache with the modified user object. The `value` and `key` attributes are used similar to the `getOneUser()` method.
- The `deleteUser()` method deletes a user by their id. It is annotated with `@CacheEvict` to remove the user from the cache. The `value` attribute specifies the cache name, and the `allEntries` attribute is set to `true` to evict all entries from the cache.

**6. UserRestController Class**

```java
package com.app.shivam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.User;
import com.app.shivam.service.UserService;

@RestController
@RequestMapping("/v1/api/users")
public class UserRestController {

	@Autowired
	private UserService service;
	
	@PostMapping("/create")
	public String createUser(@RequestBody User user) {
		Integer id = service.saveUser(user);
		return "User '" + id + "' created!";
	}
	
	@GetMapping("/find/{id}")
	public User findOneUser(@PathVariable Integer id) {
		return service.getOneUser(id);
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteUser(@PathVariable Integer id) {
		service.deleteUser(id);
		return "User Deleted!";
	}
	
	@PutMapping("/modify")
	public String updateUser(@RequestBody User user) {
		service.updateUser(user.getId(), user);
		return "User Updated!";
	}
}
```

Explanation:
- The `UserRestController` class is a REST controller that exposes endpoints for user-related operations.
- It is annotated with `@RestController` to indicate that it is a RESTful controller that handles HTTP requests and returns responses.
- The base request mapping for all endpoints is set to "/v1/api/users" using the `@RequestMapping` annotation.
- The class has a dependency on the `UserService` bean, which is autowired using the `@Autowired` annotation.
- The `createUser()` method is mapped to the HTTP POST request at "/create" and accepts a JSON payload representing a user. It invokes the `saveUser()` method from the `UserService` to create a new user and returns a success message.
- The `findOneUser()` method is mapped to the HTTP GET request at "/find/{id}" and retrieves a user by their id. It invokes the `getOneUser()` method from the `UserService` and returns the user object.
- The `deleteUser()` method is mapped to the HTTP DELETE request at "/remove/{id}" and deletes a user by their id. It invokes the `deleteUser()` method from the `UserService` and returns a success message.
- The `updateUser()` method is mapped to the HTTP PUT request at "/modify" and updates an existing user. It invokes the `updateUser()` method from the `UserService` to update the user and returns a success message.

These classes form the basic structure of a Spring Boot application with Redis caching. The `User` entity represents a user object, the `UserRepository` provides database access for user operations, the `UserService` handles the business logic, and the `UserRestController` exposes REST endpoints for user interactions. By using caching annotations such as `@Cacheable`, `@CachePut`, and `@CacheEvict`, Redis caching is integrated into the application to improve performance by reducing database calls for commonly accessed data.

**Conclusion**

In this Spring Boot application, we have implemented Redis caching to improve performance by reducing network calls to the database. Here is a summary of the key components and their functionalities:

1. **Redis**: Redis is a NoSQL database that also functions as a cache. It provides high-performance data storage and retrieval.

2. **Spring Boot Dependencies**: The application includes dependencies for web support, Lombok for simplified Java coding, DevTools for development convenience, Data JPA for database access, MySQL for the database, and Redis for caching.

3. **Entity Class**: The `User` class represents a user entity in the application. It is annotated with `@Entity` and implements the `Serializable` interface to enable caching.

4. **Repository**: The `UserRepository` interface extends `JpaRepository` to provide database access operations for the `User` entity.

5. **Service**: The `UserService` class handles the business logic for user operations. It is annotated with `@Service` to mark it as a service bean. It utilizes the `UserRepository` to interact with the database.

6. **RestController**: The `UserRestController` class is a REST controller that exposes endpoints for user interactions. It is annotated with `@RestController` and handles HTTP requests such as creating, finding, updating, and deleting users.

7. **Caching Annotations**: The caching annotations `@Cacheable`, `@CachePut`, and `@CacheEvict` are used in the `UserService` class to enable caching for specific methods. `@Cacheable` caches the result of a method call, `@CachePut` updates the cache, and `@CacheEvict` removes an entry from the cache.

By integrating Redis caching into the Spring Boot application, we can reduce the number of network calls to the database for commonly accessed data. This improves application performance and reduces response times.

Note: To ensure that Redis caching works correctly, make sure to start the Redis server and configure the Redis connection details in the `application.properties` file.

<br/>
<br/>
<br/>

# Here are the steps to execute the given Postman requests:

1. **CREATE ONE ROW**:
   - Method: POST
   - URL: `http://localhost:9090/v1/api/users/create`
   - Body:
     - Select `raw` option
     - Set the content type to `JSON (application/json)`
     - Enter the following JSON data:
       ```json
       {
           "name": "RAJ",
           "role": "HR"
       }
       ```
   - Send the request.

2. **FETCH ONE ROW MULTIPLE TIMES**:
   - Method: GET
   - URL: `http://localhost:9090/v1/api/users/find/1`
   - Send the request multiple times to see the cached result for the same user ID.

These requests will create a user with the name "RAJ" and role "HR" and then fetch the same user multiple times. The caching mechanism will store the user object in the cache upon creation, and subsequent fetch requests will retrieve the user from the cache instead of hitting the database.

<br/>
<br/>

**Q) What is the use of Cache?**<br/>
A) The use of cache is to improve performance and reduce the number of network calls between the application and the database. It stores commonly accessed data in a faster and easily accessible storage, such as memory, so that subsequent requests for the same data can be served from the cache instead of going to the database.

**Q) Can we implement cache for all operations/findAll?**<br/>
A) No, it is not recommended to implement cache for all operations or for the findAll operation. Caching should be used selectively for operations that involve fetching individual entities, updating entities, or deleting entities. Caching the findAll operation can consume a significant amount of memory if there are a large number of entities, and it may not provide significant performance benefits as the entire data set is typically fetched.

**Q) What are caching annotations given by Spring Boot?**<br/>
A) Spring Boot provides several caching annotations that can be used to control caching behavior. Some of the commonly used annotations are:
- `@Cacheable`: This annotation is used to cache the result of a method. If the same method is called again with the same arguments, the cached result is returned instead of executing the method.
- `@CachePut`: This annotation is used to update the cache with the result of a method. It always executes the method and updates the cache with the returned value.
- `@CacheEvict`: This annotation is used to evict or remove entries from the cache. It can be used to remove a specific entry or clear the entire cache.

**Q) Which Cache vendor did you implement in the application?**<br/>
A) The cache vendor implemented in the application is Redis. Redis is a popular open-source in-memory data structure store that can be used as a cache, database, or message broker. It provides fast read and write operations, supports various data structures, and can be easily integrated with Spring Boot for caching purposes.

<br/>
<br/>

# Notes

By default, once an object/data is cached, it will remain in the cache until explicitly removed using the `@CacheEvict` annotation or any other cache eviction mechanism. This ensures that the cached data remains available for subsequent requests until it is intentionally invalidated or updated.

However, Redis also provides a way to set a time-to-live (TTL) for cached objects. The `spring.cache.redis.time-to-live` property allows you to specify the time duration in milliseconds after which an object should be automatically removed from the cache. In your example, `spring.cache.redis.time-to-live=60000` sets the TTL to 60,000 milliseconds (1 minute).

With this configuration, any object stored in the cache will automatically expire and be removed after 1 minute. This can be useful in scenarios where you want to ensure that the cached data is periodically refreshed from the database or to prevent stale data from being served indefinitely.

It's important to note that setting a TTL for cached objects is optional and depends on your specific caching requirements. If you don't specify a TTL or set it to a very large value, the objects will remain in the cache until explicitly evicted or until the cache is cleared.

