# Mockito | Mocking

1. We are Dev-3 working on Module-3:
This statement implies that you are a developer working on Module-3, which has dependencies on other modules or services.

1. I want to Code for Module-3 and test but it is internally dependent on Module-2:
When developing and testing Module-3, you encounter a situation where Module-3 depends on functionality provided by Module-2. However, Module-2 might not be fully developed or available for testing yet.

1. So, I'll Create Dummy Implementation of Module-2 for Develop and Test Module-3. This is called mocking:
To proceed with the development and testing of Module-3, you can create a dummy or simulated implementation of Module-2. This process of creating a substitute or simulated version of a dependent module or service is known as mocking.

1. Mocking can be done for even Database Connections, Server setup, Request object creation, etc.:
Mocking is not limited to module dependencies. It can be applied to various scenarios, such as simulating database connections, setting up a mock server, creating dummy request objects, or any other dependencies that might hinder the development or testing process.

Now, let's look at the code example you provided:

```java
interface ProductService {
  Integer saveProduct();
}

class ProductController {
  ProductService ps;
}
```

In this code, there is an interface `ProductService` representing the functionality of the service related to products. The `ProductController` class has a dependency on `ProductService`.

To mock the `ProductService` and create a dummy implementation for testing purposes, you can use Mockito. Here's an example of mocking the `ProductService` using Mockito:

```java
ProductService ps = mock(ProductService.class);
```

In this line, `mock(ProductService.class)` creates a mock implementation of the `ProductService` interface. The `ps` object now represents the mocked implementation, which can be used in the testing of `ProductController` without relying on the actual `ProductService` implementation.

You can then define behavior for the mocked `ProductService` using Mockito's `when().thenReturn()` syntax. For example:

```java
when(ps.saveProduct()).thenReturn(100);
```

This line states that when the `saveProduct()` method is called on the mocked `ProductService` (`ps`), it should return `100`. This allows you to define specific behavior for the mocked service to simulate different scenarios during testing.

Additionally, Mockito provides flexibility in defining the behavior of mocked methods. For example, you can use `Mockito.anyInt()` to specify that the return value should be any integer:

```java
when(ps.saveProduct()).thenReturn(Mockito.anyInt());
```

With this line, the `saveProduct()` method of the mocked `ProductService` will return any integer value when called during testing.

Mocking allows you to isolate the module being tested from its dependencies, enabling effective testing and development even when certain dependencies are unavailable or under development.

Mockito is a powerful mocking framework in Java that simplifies the process of creating mock implementations and defining their behavior. It is widely used in unit testing to facilitate test-driven development and enable testing of complex scenarios.

<br/>
<br/>

# Here's the separate code, explanation, and conclusion for the two Mockito examples:

**Example 1:**

**Code: ProcessInfo.java**
```java
package com.app.shivam;

public interface ProcessInfo {
    Integer getCode(String code);
}
```

**Explanation:**
The `ProcessInfo` interface represents a service that provides a method `getCode()` to retrieve an integer code based on a given input.

---

**Code: TestMockProcessInfo.java**
```java
package com.app.shivam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class TestMockProcessInfo {

    @Test
    public void testInfo() {
        ProcessInfo ps = mock(ProcessInfo.class);
        when(ps.getCode("ABC")).thenReturn(100);

        assertEquals(ps.getCode("ABC"), 100);
    }
}
```

**Explanation:**
The `TestMockProcessInfo` class is a JUnit test class that uses Mockito to create a mock implementation of the `ProcessInfo` interface.

- In the `testInfo()` method, the `ProcessInfo` interface is mocked using `mock(ProcessInfo.class)`, creating a mock implementation of the interface.

- The behavior of the `getCode()` method is defined using `when(ps.getCode("ABC")).thenReturn(100)`. It states that when the `getCode()` method is called with the input "ABC" on the mock object `ps`, it should return `100`.

- The `assertEquals` assertion verifies that the mocked `getCode("ABC")` call indeed returns `100`.

Mockito allows you to create mock implementations of interfaces or classes and define their behavior to simulate different scenarios during testing. In this example, the `ProcessInfo` interface is mocked, and its behavior is defined to return a specific value for a given input.

---

**Example 2:**

**Code: Repository.java**
```java
package com.app.shivam;

import java.sql.SQLException;
import java.util.List;

public class Repository {

    public List<String> getData() throws SQLException {
        return null;
    }
}
```

**Code: Service.java**
```java
package com.app.shivam;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public List<String> getDataLen() {
        try {
            return repository.getData().stream()
                    .filter(data -> data.length() < 5)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            return Arrays.asList();
        }
    }
}
```

**Explanation:**
The code consists of a `Repository` class that interacts with a database and a `Service` class that performs some operations on the data obtained from the repository.

- The `Repository` class provides a method `getData()` that is expected to retrieve data from the database. In this example, the implementation is simplified, returning `null` as a placeholder.

- The `Service` class depends on the `Repository` class and has a method `getDataLen()` that retrieves data from the repository and filters it based on a length condition.

---

**Code: ServiceTest.java**
```java
package com.app.shivam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock //creates stub for Repository (Dummy Impl code)
    private Repository repo;

    @InjectMocks //creates actual object of service with dummy Repo object injected
    private Service sob;

    @Test
    public void testGetDataLen() throws SQLException {
        //provide dummy impl for method getData(), that returns Array of 5 elements
        when(repo.getData())
                .thenReturn(Arrays.asList("A", "B", "TEST", "WELCOME", "FORMAT"));

        List<String> list = sob.getDataLen();
        assertNotNull(list);
        assertEquals(list.size(), 3);

    }
}
```

**Explanation:**
The `ServiceTest` class is a JUnit test class that uses Mockito to test the `Service` class while mocking the `Repository` dependency.

- The `@Mock` annotation is used to create a mock/stub for the `Repository` class. This allows us to define the behavior of the mocked `Repository` methods.

- The `@InjectMocks` annotation is used to inject the mocked `Repository` object into the actual `Service` object being tested.

- In the `testGetDataLen()` method, the behavior of the `getData()` method of the mocked `Repository` is defined using `when(repo.getData()).thenReturn(...)`. It specifies that when `getData()` is called, it should return a list of dummy data.

- The `sob.getDataLen()` method is then tested, and assertions are made to verify that the expected results are obtained.

Mockito and annotations like `@Mock` and `@InjectMocks` help simplify the process of mocking dependencies and setting up test scenarios. They allow for effective unit testing by isolating the behavior of the tested class from its dependencies.

---

**Conclusion:**
In the first example, Mockito is used to mock the `ProcessInfo` interface, allowing the behavior of the mocked method to be defined and tested. This demonstrates how Mockito can be used to simulate and verify specific behavior in unit tests.

In the second example, Mockito is used to mock the `Repository` dependency of the `Service` class. The test verifies that the `Service` class correctly filters data from the repository based on a length condition. This showcases how Mockito simplifies the testing of classes with dependencies by allowing the creation of mock objects and defining their behavior.

Mockito is a powerful mocking framework that facilitates the testing of code with dependencies. By creating mock implementations, you can simulate different scenarios, define expected behavior, and verify the interactions between objects in your tests.

<br/>
<br/>
<br/>

**Mockito Overview:**
Mockito is a Java-based mocking framework that is commonly used in conjunction with testing frameworks like JUnit. It allows the creation of mock objects, which are dummy implementations of dependent code. Mockito internally uses the Java Reflection API to create these objects and provides a way to test the functionality of a class in isolation.

**What is Mocking?**
Mocking is the process of creating a dummy implementation, called a mock object, for the dependent code. It allows testing the functionality of a class without relying on external dependencies such as database connections, properties files, or servers. Mock objects simulate the behavior of real services by returning predefined dummy data corresponding to specific input scenarios.

**Benefits of Mocking:**
1. No handwriting: Mockito eliminates the need for manually writing mock objects. It provides a simplified API for creating mock objects, reducing code overhead.
2. Safe refactoring: When refactoring the code, such as renaming methods or changing parameter orders, the test code does not need to be modified. Mockito creates mock objects at runtime, ensuring that the test code remains intact.
3. Exception support: Mockito supports handling exceptions. It leverages the stack trace to identify the cause of an exception, making it easier to debug and troubleshoot issues.
4. Annotation support: Mockito offers annotations like `@Mock` to simplify the creation of mock objects. These annotations automatically create the mock objects, reducing boilerplate code.
5. Order support: Mockito provides the ability to verify the order of method calls on mock objects. This is useful when testing interactions between objects that have a specific sequence of method invocations.

**Creating Mock Objects:**
In Mockito, you can create a mock object for a class using the following syntax:
```java
T obj = mock(T.class);
```
Here, `T` represents the class/interface for which you want to create a mock object.

**Providing Implementation to Mock Objects:**
To define the behavior of a mock object, Mockito provides methods like `thenReturn()` and `thenThrow()`.

- To specify a return value for a method call on a mock object, you can use the `thenReturn()` method. For example:
```java
when(obj.method()).thenReturn(output);
```
Here, `obj` is the mock object, `method()` is the method to be mocked, and `output` is the predefined value that the method should return.

- If you want to simulate an exception being thrown by a method call on a mock object, you can use the `thenThrow()` method. For example:
```java
when(obj.method()).thenThrow(exceptionObj);
```
Here, `obj` is the mock object, `method()` is the method to be mocked, and `exceptionObj` is the exception object that should be thrown.

**Mocking Void Methods:**
Mockito also supports mocking void methods. To mock a void method, you can use the `doNothing()` method. For example:
```java
doNothing().when(instance).methodName();
```
Here, `instance` is the object on which the method is called, and `methodName()` is the void method to be mocked.

Mockito provides a wide range of capabilities to simplify the mocking process and create effective unit tests. It allows for the creation of mock objects, defining their behavior, and verifying the interactions between objects in the test code. Mockito's API and annotations streamline the process of mocking, making it a popular choice for testing Java applications.

<br/>
<br/>


# Here's the breakdown of the two Mockito examples you provided:

**Example 1: Mocking with Methods**

**Service Code:**
```java
package com.app.shivam;

public interface ProcessInfo {
    Integer getCode(String code);
}
```

**Mocking with Methods Code:**
```java
package com.app.shivam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class ProcessInfoTest {
    @Test
    void testgetCode() {
        ProcessInfo p = mock(ProcessInfo.class);
        when(p.getCode("Hello")).thenReturn(5);

        System.out.println(p.getCode("Hello"));
        assertEquals(p.getCode("Hello"), 5);
    }
}
```

**Explanation:**
- The `ProcessInfo` interface declares a single method `getCode()` that returns an `Integer` based on a given input code.
- In the `ProcessInfoTest` class, we create a mock object of `ProcessInfo` using `mock(ProcessInfo.class)`.
- Using `when(p.getCode("Hello")).thenReturn(5)`, we specify that when the `getCode()` method is called with the input "Hello" on the mock object `p`, it should return `5`.
- The test asserts that the mocked `getCode("Hello")` call indeed returns `5` using `assertEquals(p.getCode("Hello"), 5)`.

**Example 2: Annotations-based**

**Service Code:**
```java
package com.app.shivam;

import java.sql.SQLException;
import java.util.List;

public class Repository {
    public List<String> getData() throws SQLException {
        return null;
    }
}
```

**Service Code:**
```java
package com.app.shivam;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public List<String> getDataLen() {
        try {
            return repository.getData().stream()
                    .filter(data -> data.length() < 5)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            return Arrays.asList();
        }
    }
}
```

**Test Class:**
```java
package com.app.shivam;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceTest {
    @Mock
    Repository repository;

    @InjectMocks
    Service service;

    @Test
    void testSuccess() {
        // Setup mock scenario
        try {
            Mockito.when(repository.getData()).thenReturn(
                    Arrays.asList("A", "B", "CDEFGHIJK", "12345", "1234"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Execute the service that uses the mocked repository
        List<String> data = service.getDataLen();

        // Validate the response
        Assertions.assertNotNull(data);
        Assertions.assertEquals(3, data.size());
    }

    @Test
    void testException() {
        // Setup mock scenario
        try {
            Mockito.when(repository.getData()).thenThrow(
                    new SQLException("Connection Exception"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Execute the service that uses the mocked repository
        List<String> data = service.getDataLen();

        // Validate the response
        Assertions.assertNotNull(data);
        Assertions.assertEquals(0, data.size());
    }
}
```

**Explanation:**
- The `Repository` class represents a data repository with a method `getData()` that returns a list of strings. In this example, it returns null, but in a real implementation, it would retrieve data from a data source like a database.
- The `Service` class depends on the `Repository` class and provides a method `getDataLen()` that filters the data obtained from the repository based on a length condition.
- The `ServiceTest` class is the test class for the `Service` class, utilizing Mockito annotations and features.
- The `@Mock` annotation is used to create a mock object of the `Repository` class, and the `@InjectMocks` annotation is used to inject the mock object into the `Service` object being tested.
- In the `testSuccess()` method, we set up a mock scenario using `Mockito.when(repository.getData()).thenReturn(...)`. It specifies that when the `getData()` method is called on the mock `repository`, it should return a list of dummy data.
- Similarly, in the `testException()` method, we set up a mock scenario where the `getData()` method throws an exception.
- The tests validate the response by asserting the expected size of the data list and its non-nullness.

**Conclusion:**
Mockito is a powerful mocking framework that simplifies the process of creating mock objects and defining their behavior during unit testing. It allows you to mock dependencies, simulate various scenarios, and test your code in isolation. In the first example, Mockito is used to create a mock object of the `ProcessInfo` interface and define its behavior. In the second example, Mockito annotations like `@Mock` and `@InjectMocks` are used to create mock objects and inject them into the tested class. These examples demonstrate how Mockito enhances unit testing by enabling the testing of code with dependencies and facilitating the simulation of different scenarios.

<br/>
<br/>

# SPRING BOOT TESTING EXAMPLES

## EXAMPLE#3 Spring Boot Data JPA JUnit with Mock Example  Dependencies: Data JPA, H2, Lombok

Sure! Here's the breakdown of the Spring Boot testing examples:

**Example #3: Spring Boot Data JPA JUnit with Mock Example**

**Entity:**
```java
package com.app.shivam.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "moviestab")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gener;
    private LocalDate releaseDate;
}
```

**Repository:**
```java
package com.app.shivam.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.shivam.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGener(String gener);
}
```

**Test Case:**

**Code:**
```java
package com.app.shivam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.shivam.entity.Movie;
import com.app.shivam.repo.MovieRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Order(1)
    @Test
    @DisplayName("IT SHOULD SAVE THE MOVIE TO THE DATABASE")
    void save() {
        Movie avatarMovie = new Movie();
        avatarMovie.setName("Avatar");
        avatarMovie.setGener("Action");
        avatarMovie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 23));

        Movie newMovie = movieRepository.save(avatarMovie);
        assertNotNull(newMovie);
        assertThat(newMovie.getId()).isNotEqualTo(null);
    }

    @Order(2)
    @Test
    @DisplayName("IT SHOULD RETURN THE MOVIES LIST WITH SIZE OF 2")
    void getAllMovies() {
        Movie rrr = new Movie();
        rrr.setName("RRR");
        rrr.setGener("Action");
        rrr.setReleaseDate(LocalDate.of(2022, Month.MARCH, 24));
        movieRepository.save(rrr);

        Movie kantara = new Movie();
        kantara.setName("KANTARA");
        kantara.setGener("Thriller");
        kantara.setReleaseDate(LocalDate.of(2022, Month.SEPTEMBER, 30));
        movieRepository.save(kantara);

        List<Movie> list = movieRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(2, list.size());
    }

    @Order(3)
    @Test
    @DisplayName("IT SHOULD RETURN THE MOVIE BY ITS ID")
    void getMovieById() {
        Movie rrr = new Movie();
        rrr.setName("RRR");
        rrr.setGener("Action");
        rrr.setReleaseDate(LocalDate.of(2022, Month.MARCH, 24));
        movieRepository.save(rrr);

        Movie newMovie = movieRepository.findById(rrr.getId()).get();

        assertNotNull(newMovie);
        assertEquals("Action", newMovie.getGener());
        assertThat(newMovie.getReleaseDate()).isBefore(LocalDate.of(2022, Month.APRIL, 24));
    }

    @Order(4)
    @Test
    @DisplayName("IT SHOULD RETURN THE MOVIES LIST WITH GENER THRILLER")
    void getMoviesByGenera() {
        Movie rrr = new Movie();
        rrr.setName("RRR");
        rrr.setGener("Action");
        rrr.setReleaseDate(LocalDate.of(2022, Month.MARCH, 24));
        movieRepository.save(rrr);

        Movie kantara = new Movie();
        kantara.setName("KANTARA");
        kantara.setGener("Thriller");
        kantara.setReleaseDate(LocalDate.of(2022, Month.SEPTEMBER, 30));
        movieRepository.save(kantara);

        List<Movie> list = movieRepository.findByGener("Thriller");

        assertNotNull(list);
        assertThat(list.size()).isEqualTo(1);
    }

    @Order(5)
    @Test
    @DisplayName("IT SHOULD UPDATE THE MOVIE GENERA WITH FANTACY")
    void updateMovie() {
        Movie rrr = new Movie();
        rrr.setName("RRR");
        rrr.setGener("Action");
        rrr.setReleaseDate(LocalDate.of(2022, Month.MARCH, 24));
        movieRepository.save(rrr);

        Movie existingMovie = movieRepository.findById(rrr.getId()).get();
        existingMovie.setGener("Fantacy");
        Movie updatedMovie = movieRepository.save(existingMovie);

        assertEquals("Fantacy", updatedMovie.getGener());
        assertEquals("RRR", updatedMovie.getName());
    }

    @Order(6)
    @Test
    @DisplayName("IT SHOULD DELETE THE EXISTING MOVIE")
    void deleteMovie() {
        Movie kantara = new Movie();
        kantara.setName("KANTARA");
        kantara.setGener("Thriller");
        kantara.setReleaseDate(LocalDate.of(2022, Month.SEPTEMBER, 30));
        movieRepository.save(kantara);
        Long id = kantara.getId();

        Movie rrr = new Movie();
        rrr.setName("RRR");
        rrr.setGener("Action");
        rrr.setReleaseDate(LocalDate.of(2022, Month.MARCH, 24));
        movieRepository.save(rrr);

        movieRepository.delete(kantara);

        List<Movie> list = movieRepository.findAll();

        Optional<Movie> exitingMovie = movieRepository.findById(id);

        assertEquals(1, list.size());
        assertThat(exitingMovie).isEmpty();
    }
}
```

**Explanation:**
- The `MovieRepositoryTest` class is a JUnit test class for testing the functionalities of the `MovieRepository` interface.
- The `@DataJpaTest` annotation is used to configure the Spring Data JPA infrastructure for testing, providing an in-memory database and EntityManager.
- The `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)` annotation is used to define the execution order of the test methods based on the specified order value using `@Order`.
- Each test method focuses on a specific functionality and is annotated with `@Test` and `@Order` to define the execution order.
- `save()` method saves a new movie to the database using the `movieRepository.save()` method. It asserts that the saved movie is not null and has a non-null ID.
- `getAllMovies()` method tests the retrieval of all movies from the database using the `movieRepository.findAll()` method. It asserts that the returned list is not null, is not empty, and has a size of 2.
- `getMovieById()` method tests the retrieval of a movie by its ID using the `movieRepository.findById()` method. It asserts that the retrieved movie is not null, has the correct genre, and the release date is before a specific date.
- `getMoviesByGenera()` method tests the retrieval of movies by genre using the custom method `findByGener()` in the `MovieRepository` interface. It asserts that the returned list is not null, has a size of 1, and contains the correct movie.
- `updateMovie()` method tests the update of a movie's genre using the `movieRepository.save()` method. It asserts that the updated movie has the correct genre and name.
- `deleteMovie()` method tests the deletion of a movie from the database using the `movieRepository.delete()` method. It asserts that the movie is deleted successfully, and the list size decreases by 1.

These test methods cover various aspects of the `MovieRepository` interface, ensuring that the CRUD operations and custom queries function correctly. The assertions validate the expected behavior and help

in verifying the correctness of the repository operations. By using the `@DataJpaTest` annotation, the tests are executed in an isolated environment with an in-memory database, allowing for efficient and reliable testing of the repository.

In conclusion, the `MovieRepositoryTest` class demonstrates how to effectively test Spring Boot Data JPA repositories using JUnit and Mockito. It showcases how to write test methods for saving, retrieving, updating, and deleting entities, as well as testing custom query methods. These tests help ensure the reliability and accuracy of the repository implementation, providing confidence in the data access layer of the application.

**Conclusion:**
The provided example demonstrates how to write unit tests for Spring

Boot Data JPA repositories using JUnit and Mockito. It covers testing CRUD operations, custom queries, and verifying the behavior of repository methods. By using annotations like `@DataJpaTest`, the tests are executed in an isolated environment with an in-memory database. The assertions ensure the expected results are obtained from the repository operations.

Overall, Spring Boot testing examples showcase the importance of testing in ensuring the correctness and reliability of application components. These examples demonstrate how to write effective tests using popular frameworks and techniques, allowing developers to confidently build and maintain high-quality software.