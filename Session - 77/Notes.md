# **Unit Test: Testing A part of Application/Module implemented by a Programmer**

Jenkins (CI/CD) --> Read Source Code from Github --> Download Jars -> Compile -> Unit Test --> Build (.jar/.war) --> Deploy (server)

**Test Application Using Code**

Testing an application or module involves writing code specifically designed to validate its functionality. These tests are written to ensure that the implemented code behaves as expected and meets the desired requirements.

**JUnit [Test Framework] and Mockito [Mocking Framework]**

JUnit is a popular testing framework for Java applications. It provides annotations, assertions, and test runners to write and execute unit tests. With JUnit, you can define test cases, execute them, and verify the expected outcomes.

On the other hand, Mockito is a mocking framework that allows you to create mock objects. Mocking is a technique used to simulate the behavior of dependencies or external components that an application interacts with during testing. Mockito helps in creating these mock objects, allowing you to isolate and test individual units of code.

**JUnit-5 Used to Verify Actual Code Functionality**

JUnit 5 is the latest version of the JUnit testing framework. It is used to verify the functionality of your actual code by writing test cases. These test cases execute specific code paths, validate expected results, and check if the code is working as intended. JUnit 5 offers various annotations, assertions, and lifecycle hooks to facilitate test-driven development and test automation.

**Mocking Dependencies Using Mockito**

In testing, there may be scenarios where your code depends on external components or resources that are not available or not fully implemented. Mockito helps in creating mock objects that simulate the behavior of these dependencies. By using Mockito, you can define the expected behavior of these mock objects, allowing you to test your code in isolation.

**Mocking Can Be Done Over Various Components**

Mockito can be used to mock various components such as DB connections, server setups, requests, responses, container runtimes, collections, classes, and objects. Mocking allows you to replace these components with mock implementations that emulate their behavior. This enables focused testing of specific units without relying on the complete functionality of external systems.

In summary, testing an application or module involves writing code-based tests using frameworks like JUnit, which allows you to verify the actual code's functionality. Mockito, a mocking framework, helps in creating mock objects to simulate dependencies or external components during testing. These tools enable developers to isolate and test specific units of code, even when certain dependencies are not fully implemented or available.

<br/>
<br/>

# **JUnit contains Test Methods, which are implemented using:**

**JUnit Annotations: @Test, @BeforeEach, @AfterEach, @Ignore, etc.**

- **@Test**: This annotation is used to mark a method as a test method. JUnit executes methods annotated with `@Test` to perform specific tests on the code under test. Each test method typically focuses on a specific aspect or functionality of the code being tested.

- **@BeforeEach**: This annotation is used to mark a method that should be executed before each test method. It is often used to set up the necessary preconditions or initialize resources that are required for the test. The method annotated with `@BeforeEach` runs before each `@Test` method.

- **@AfterEach**: This annotation is used to mark a method that should be executed after each test method. It is commonly used to clean up or release resources that were used during the test. The method annotated with `@AfterEach` runs after each `@Test` method.

- **@Ignore**: This annotation is used to temporarily disable a test method. When a test method is annotated with `@Ignore`, JUnit will skip executing that particular test method. It is useful when a test is not yet implemented or when you want to exclude certain tests from the test suite temporarily.

**JUnit Assert API: assertEquals, assertTrue, assertNotNull, etc.**

The JUnit Assert API provides a set of assertion methods that allow you to compare expected results with the actual output of your code under test. Some commonly used assertion methods include:

- **assertEquals**: This method compares the expected value with the actual value. It checks whether the two values are equal. If they are not equal, the test fails and JUnit reports a failure. The `assertEquals` method is used to perform equality checks on objects, arrays, or primitive types.

- **assertTrue**: This method checks whether a given condition is true. If the condition evaluates to false, the test fails. It is often used to verify certain conditions or boolean expressions in your code.

- **assertNotNull**: This method checks whether a given object reference is not null. If the object reference is null, the test fails. It ensures that the expected object or value is not null and is properly initialized.

**JUnit's Test Result Comparison**

JUnit compares the expected result (defined in your test) with the actual output of the code under test. If the expected result matches the actual output, the test passes. If they do not match, the test fails. JUnit provides detailed information about the test results, including which assertions failed and the expected versus actual values.

When executing tests, JUnit examines the assertions within each `@Test` method. If all assertions pass, JUnit reports the test as passed. If any assertion fails, JUnit reports the test as failed, indicating the specific assertion that did not meet the expected result.

In summary, JUnit uses annotations like `@Test`, `@BeforeEach`, `@AfterEach`, and `@Ignore` to structure and control the execution of test methods. The Assert API, including methods like `assertEquals`, `assertTrue`, and `assertNotNull`, is used to compare expected results with the actual output of the code being tested. JUnit evaluates these assertions and determines whether a test passes or fails based on the comparison of expected and actual results.

<br/>
<br/>

# Here's the code for the application class `MathService`, followed by an explanation, and then the code for the JUnit test case `MathServiceTest` with its explanation:

**1. MathService - Application Code:**

```java
package com.app.shivam;

public class MathService {

    public int add(int a, int b) {
        return a + b;
    }

    public boolean isEven(int a) {
        return a % 2 == 0;
    }
}
```

**Explanation:**
The `MathService` class is a simple class that provides two methods: `add` and `isEven`. The `add` method takes two integers as parameters and returns their sum. The `isEven` method takes an integer and checks if it is even, returning a boolean value accordingly.

**2. MathServiceTest - JUnit Test Case:**

```java
package com.app.shivam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MathServiceTest {

    MathService ms = null;
    int expected;

    @BeforeEach
    public void setup() {
        ms = new MathService();
        expected = 30;
    }

    @Test
    public void testAdd() {
        int actual = ms.add(10, 20);
        assertEquals(expected, actual);
        boolean result = ms.isEven(51);
        assertTrue(result);
    }

    @AfterEach
    public void clean() {
        ms = null;
    }
}
```

**Explanation:**
The `MathServiceTest` class is a JUnit test case that verifies the functionality of the `MathService` class. It includes three methods marked with JUnit annotations:

- `@BeforeEach` is an annotation indicating a setup method that is executed before each test method. In this case, it initializes an instance of the `MathService` class and sets the `expected` value to 30.
- `@Test` is an annotation marking a test method. The `testAdd` method tests the `add` and `isEven` methods of the `MathService` class. It invokes the `add` method with parameters 10 and 20, then asserts that the actual result is equal to the expected result of 30. It also checks if the `isEven` method returns `true` for the value 51 using the `assertTrue` assertion.
- `@AfterEach` is an annotation indicating a cleanup method that is executed after each test method. In this case, it nullifies the instance of the `MathService` class.

**Conclusion:**
The provided code demonstrates a basic JUnit test case for the `MathService` class. It verifies the correctness of the `add` method and checks if the `isEven` method returns the expected result. By using JUnit annotations and assertion methods, you can structure and execute tests to validate the behavior of your code.

<br/>
<br/>

# **Mocking**

**Mocking Creates Environment with Dummy Objects, Logic, and Requests**

Mocking is a technique used in testing to create a controlled environment for testing code. It involves creating dummy objects, dummy logic, and dummy requests to simulate the behavior of real objects and components. By mocking these dependencies, you can isolate the code under test and focus on specific scenarios without relying on the actual implementations of those dependencies.

**@Mock: Creating Dummy Objects**

The `@Mock` annotation is used in Mockito to create dummy objects for the classes or interfaces that you want to mock. It instructs Mockito to create a mock object that simulates the behavior of the real object. Mock objects are used to replace real objects during testing, allowing you to define the expected behavior and responses of these objects without invoking the actual implementation.

**@InjectMocks: Resolving Required Objects for Current Class**

The `@InjectMocks` annotation works in conjunction with `@Mock` to automatically inject the mock objects into the class under test. When you annotate a field or constructor parameter with `@InjectMocks`, Mockito will attempt to find the required mock objects and inject them into the instance of the class being tested. This helps in setting up the test environment by automatically resolving and injecting the necessary dependencies.

**Defining Dummy Implementations and Behaviors with Mockito**

Mockito provides methods to define the behavior of mock objects during testing. Some commonly used methods include:

- `when(methodCall()).thenReturn(output)`: This syntax is used to define a dummy implementation for a method call. It specifies that when the method is called during testing, it should return the specified output value. This allows you to control the behavior of the mock object and define the expected response.

- `when(methodCall()).thenThrow(exceptionObj)`: This syntax is used to define that a method should throw the specified exception when called. It allows you to simulate error conditions and exceptions in your test environment to verify how your code handles such scenarios.

**Activating Mocking Environment with @ExtendWith(MockitoExtension.class)**

To activate the mocking environment and enable the usage of Mockito annotations, you need to annotate your test class with `@ExtendWith(MockitoExtension.class)`. This annotation integrates Mockito with JUnit and allows you to use Mockito's mocking capabilities within your test methods.

In summary, mocking creates a controlled environment for testing by providing dummy objects, logic, and requests. The `@Mock` annotation is used to create dummy objects, while `@InjectMocks` helps in resolving and injecting those objects into the class under test. Mockito allows you to define dummy implementations and behaviors for these mock objects using methods like `thenReturn` and `thenThrow`. By activating the mocking environment with `@ExtendWith(MockitoExtension.class)`, you can use Mockito's mocking capabilities in your JUnit tests.


<br/>
<br/>

# Here's a detailed explanation of the provided code:

**1. Repository - Application Code:**

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

The `Repository` class represents a data repository. It provides a method `getData()` that is expected to retrieve a list of strings. In this example, the method returns `null`, but in a real implementation, it would fetch data from a database or another data source.

**2. Service - Application Code:**

```java
package com.app.shivam;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<String> getDataByLen() {
        try {
            return repo.getData().stream()
                    .filter(d -> d.length() < 5)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            return Arrays.asList();
        }
    }
}
```

The `Service` class utilizes the `Repository` to provide a method `getDataByLen()`. This method retrieves data from the repository, filters the strings with a length less than 5, and returns the filtered list. If an exception occurs during the data retrieval, an empty list is returned.

**3. ServiceTest - JUnit Test Case:**

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

    @Mock
    private Repository repository;

    @InjectMocks
    private Service service;

    @Test
    public void testSuccess() {
        try {
            when(repository.getData()).thenReturn(Arrays.asList("A", "B", "shivam", "12345", "1234"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> actual = service.getDataByLen();
        assertNotNull(actual);
        assertEquals(3, actual.size());
    }

    @Test
    public void testException() {
        try {
            when(repository.getData()).thenThrow(new SQLException("CONNECTION ISSUE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> actual = service.getDataByLen();
        assertNotNull(actual);
        assertEquals(0, actual.size());
    }
}
```

The `ServiceTest` class is a JUnit test case for the `Service` class. It demonstrates two test methods: `testSuccess` and `testException`.

- `@Mock` annotation creates a mock object for the `Repository` class, which is used to provide dummy data for testing purposes.
- `@InjectMocks` annotation injects the mock `Repository` object into the `Service` instance being tested.
- `testSuccess` method sets up the mock behavior using `when(repository.getData())` to define the return value when `getData()` is called. In this case, a list of strings is returned.
- The `service.getDataByLen()` method is called, and assertions are made using `assertEquals` and `assertNotNull` to verify that the returned list is not null and has the expected size.
- `testException` method sets up the mock behavior to throw a `SQLException` when `getData()` is called.
- The `service.getDataByLen()` method is called, and assertions are made to ensure that an empty list is returned.

In summary, the provided code demonstrates the usage of JUnit and Mockito for testing the `Service` class. Mocking is utilized to provide dummy data and simulate different scenarios, such as successful data retrieval or an exception being thrown. Assertions are then used to verify the behavior of the `Service` class under these different scenarios.

<br/>
<br/>

