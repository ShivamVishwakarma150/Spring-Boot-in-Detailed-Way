#  **Here's a detailed explanation of unit testing using JUnit:**

**Unit Testing Overview**:
Unit testing is a software testing technique where individual units or components of a software system are tested in isolation to ensure that they behave as expected. In unit testing, developers write test cases to verify the correctness of their code and ensure that each unit (part of the code/module/task) functions as intended.

**Manual Testing vs. Automated Testing**:
Manual testing involves manually executing test cases, which can be time-consuming and repetitive. Automated testing, on the other hand, involves writing code to execute test cases automatically. This allows developers to run the same tests repeatedly, saving time and effort.

**JUnit Framework**:
JUnit is an open-source Java framework for unit testing. It provides a set of annotations, APIs, and tools to write and execute tests for Java applications. JUnit allows developers to define test cases, verify expected results, and report the success or failure of the tests.

**JUnit Annotations**:
JUnit provides annotations that help define test cases and control their execution. Some commonly used JUnit annotations include:

- `@Test`: This annotation is used to mark a method as a test method. Test methods are responsible for executing a specific unit of code and verifying its behavior.

**JUnit Assert API**:
JUnit provides an Assert API that includes a set of assertion methods. These methods are used to validate expected results against actual results during test execution. Some commonly used assertion methods include:

- `assertEquals(expected, actual)`: This method compares the expected value with the actual value and asserts that they are equal.
- `assertTrue(condition)`: This method asserts that the given condition is true.
- `assertFalse(condition)`: This method asserts that the given condition is false.
- `assertNull(object)`: This method asserts that the given object is null.
- `assertNotNull(object)`: This method asserts that the given object is not null.

**Writing JUnit Test Cases**:
To write a JUnit test case, you need to define a class that executes the unit tests. This class is commonly referred to as the test case. Inside the test case class, you define methods annotated with `@Test`. These annotated methods represent individual test cases that verify specific units of code.

Here's an example of a JUnit test case class:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SampleTest {
  
  @Test
  void testSave() {
    // Test code and assertions go here
  }
}
```

In this example, the `SampleTest` class is a JUnit test case. It contains a single test method `testSave()`, which is annotated with `@Test`. Inside this method, you can write the test code and add assertions using the JUnit Assert API to verify the expected behavior of the code being tested.

**Conclusion**:
Unit testing plays a crucial role in software development, allowing developers to verify the correctness of their code at the individual unit level. JUnit, as a widely used unit testing framework, provides annotations and assertion methods to facilitate the writing and execution of tests. By writing JUnit test cases, developers can ensure that their units of code behave as expected and catch any potential issues early in the development process.

<br/>
<br/>

**1. JUnit 5:**
JUnit 5 is the latest version of the JUnit testing framework for Java. It introduces several new features and enhancements over JUnit 4, making it a powerful tool for unit testing in the Java ecosystem.

**2. JUnit Platform:**
The JUnit Platform is a foundation for launching testing frameworks on the Java Virtual Machine (JVM). It provides a runtime environment for executing tests and supports various testing frameworks, including JUnit Jupiter and JUnit Vintage.

The JUnit Platform includes the TestEngine API, which allows developers to build their own testing frameworks that can run on the JUnit platform. This API provides the necessary interfaces and classes to implement custom test engines.

**3. JUnit Jupiter:**
JUnit Jupiter is the next-generation programming and extension model for writing tests in JUnit 5. It provides a more modern and flexible approach compared to JUnit 4.

JUnit Jupiter introduces new annotations like `@BeforeEach`, `@AfterEach`, `@BeforeAll`, and `@AfterAll`, which allow you to define setup and teardown methods for each test or the entire test class. These annotations make it easier to manage test dependencies and reduce code duplication.

JUnit Jupiter also introduces parameterized tests, dynamic tests, and nested tests. Parameterized tests allow you to run the same test with different sets of input parameters. Dynamic tests allow you to generate tests at runtime based on certain conditions or data. Nested tests allow you to group related tests together, providing better organization and readability.

**4. JUnit Vintage:**
JUnit Vintage is a subproject of JUnit 5 that provides support for executing tests written in JUnit 3 and JUnit 4 on the JUnit 5 platform. It enables developers to migrate their existing tests gradually to JUnit 5 without having to rewrite everything at once.

By using JUnit Vintage, you can run your legacy tests alongside new JUnit 5 tests, providing backward compatibility and a smooth transition to the latest version of JUnit.

**5. JUnit Maven Dependencies:**
To use JUnit 5 in a Maven project, you need to add the following dependency to the `pom.xml` file:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.7.2</version>
    <scope>test</scope>
</dependency>
```

This dependency includes the JUnit Jupiter API, which provides the necessary annotations, assertions, and other utilities for writing JUnit 5 tests.

Additionally, you may need to include the JUnit Jupiter engine and JUnit Vintage engine dependencies if you want to run tests using the JUnit platform:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.7.2</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.junit.vintage</groupId>
    <artifactId>junit-vintage-engine</artifactId>
    <version>5.7.2</version>
    <scope>test</scope>
</dependency>
```

These dependencies allow you to run JUnit 5 tests and execute JUnit 3/4 tests using the JUnit platform.

In addition to JUnit 5, you mentioned Mockito 4. Mockito is a popular mocking framework for Java that is commonly used in conjunction with JUnit for writing unit tests.

However, as of my knowledge cutoff in September 2021, Mockito 4 has not been released yet. The latest version of Mockito available at that time was Mockito 3.x. Mockito 3.x provides powerful mocking capabilities and integrates well with JUnit 5 for creating effective unit tests.

Please note that if Mockito 4 has been released after my knowledge cutoff, you may want to refer to the official Mockito documentation or online resources to learn about the specific features and enhancements introduced in that version.

<br/>
<br/>


**JUnit Annotations:**

1. **@Test:**
The `@Test` annotation is used to mark a method as a test method. JUnit will identify and execute all methods annotated with `@Test` as individual test cases. It is the fundamental annotation used for writing tests in JUnit.

1. **@DisplayName:**
The `@DisplayName` annotation allows you to declare a custom display name for a test class or test method. It is useful for providing more descriptive and meaningful names for your tests, which can improve the readability of test reports.

1. **@BeforeEach:**
The `@BeforeEach` annotation is used to mark a method that should be executed before each test method in a test class. It is typically used to set up the test environment or perform common setup tasks that are required before running each test case.

1. **@AfterEach:**
The `@AfterEach` annotation is used to mark a method that should be executed after each test method in a test class. It is typically used to perform cleanup tasks or tear down the test environment after running each test case.

1. **@BeforeAll:**
The `@BeforeAll` annotation is used to mark a method that should be executed once before all the test methods in a test class. It is commonly used for expensive setup operations or initialization that is shared among all test cases.

1. **@AfterAll:**
The `@AfterAll` annotation is used to mark a method that should be executed once after all the test methods in a test class have been run. It is typically used for performing cleanup tasks or releasing resources that were acquired during test execution.

1. **@Disabled:**
The `@Disabled` annotation is used to disable a test class or a specific test method. When a test class or method is disabled, it will be ignored by the test runner, and the results will not be considered during test execution.

1. **@Tag:**
The `@Tag` annotation is used to declare tags for filtering tests. Tags can be used to categorize tests and selectively execute a subset of tests based on the specified tags. This is helpful when you have a large test suite and want to run specific groups of tests based on different criteria.

1. **@ExtendWith:**
The `@ExtendWith` annotation is used to register custom extensions in JUnit 5. Extensions provide additional capabilities and behavior to the testing framework. By using `@ExtendWith`, you can apply custom extensions to your test classes and leverage their functionalities during test execution.

**JUnit Assertions:**

1. **assertEquals(expected, actual):**
The `assertEquals` assertion compares the expected value with the actual value and fails the test if they are not equal. It is commonly used to verify that a particular value matches the expected result.

2. **assertFalse(expression):**
The `assertFalse` assertion verifies that the given expression is false. If the expression evaluates to true, the test fails.

3. **assertNull(actual):**
The `assertNull` assertion checks if the actual value is null. If the actual value is not null, the test fails.

4. **assertNotNull(actual):**
The `assertNotNull` assertion checks if the actual value is not null. If the actual value is null, the test fails.

5. **assertAll():**
The `assertAll` assertion groups multiple assertions together and ensures that all of them are executed. It is useful when you want to assert multiple conditions and want to see the results of all assertions, even if one or more of them fail.

6. **assertTrue(expression):**
The `assertTrue` assertion verifies that the given expression is true. If the expression evaluates to false, the test fails.

7. **assertThrows():**
The `assertThrows` assertion is used to test that a specific exception is thrown by the code under test. It allows you to specify the expected exception type and the code that is expected to throw the exception. If the expected exception is not thrown, the test fails.

These annotations and assertions provide the foundation for writing effective unit tests in JUnit. By using these constructs, you can define the test cases, perform setup and teardown operations, and verify the expected behavior of your code.

<br/>
<br/>



# Example 1  Code + Explanation

```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.8.2</version>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>4.5.1</version>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>4.5.1</version>
    </dependency>

</dependencies>
```
**pom.xml**:
In the pom.xml file, the dependencies for JUnit and Mockito are specified. JUnit Jupiter is included with the artifactId `junit-jupiter` and version 5.8.2. Mockito is included with the artifactId `mockito-core` and version 4.5.1. Additionally, the `mockito-junit-jupiter` dependency is included, which provides integration between Mockito and JUnit Jupiter.


```java
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcessTest {
    
    @BeforeAll
    public static void firstSetup() {
        System.out.println("ONLY ONCE AT BEGINNING");
    }
    
    @BeforeEach
    public void setup() {
        System.out.println("FROM BEFORE");
    }
    
    @Order(30)
    @Test
    @DisplayName("FROM A TEST")
    public void testA() {
        System.out.println("FROM TEST#A METHOD");
    }
    
    @Test
    @Disabled
    public void testB() {
        System.out.println("FROM TEST#B METHOD");
    }
    
    @Order(20)
    @Test
    @DisplayName("FROM C TEST")
    public void testC() {
        System.out.println("FROM TEST#C METHOD");
    }
    
    @AfterEach
    public void clean() {
        System.out.println("FROM AFTER");
    }
    
    @AfterAll
    public static void lastCleanup() {
        System.out.println("ONLY ONCE AT END");
    }
}
```

**Explanation**:

- `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`: This annotation specifies that the test methods should be executed in the order defined by the `@Order` annotation. It uses the `OrderAnnotation` class as the method orderer.

- `@BeforeAll`: This annotation is used on a static method to indicate that it should be executed once before all test methods in the class. In this example, the `firstSetup()` method is annotated with `@BeforeAll` and will be executed only once at the beginning. It prints "ONLY ONCE AT BEGINNING" to the console.

- `@BeforeEach`: This annotation is used on a method to indicate that it should be executed before each test method. In this example, the `setup()` method is annotated with `@BeforeEach` and will be executed before every test method. It prints "FROM BEFORE" to the console.

- `@Order`: This annotation is used to specify the order in which test methods should be executed. The methods are ordered based on the specified value. In this example, the `testA()` method is annotated with `@Order(30)` and will be executed with an order of 30, while the `testC()` method is annotated with `@Order(20)` and will be executed with an order of 20.

- `@Test`: This annotation is used to mark a method as a test method. In this example, `testA()`, `testB()`, and `testC()` are annotated with `@Test`.

- `@DisplayName`: This annotation allows you to provide a custom display name for the test method. In this example, `testA()` and `testC()` have custom display names.

- `@Disabled`: This annotation is used to disable a test method. In this example, `testB()` is annotated with `@Disabled` and will not be executed during the test run.

- `@AfterEach`: This annotation is used on a method to indicate that it should be executed after each test method. In this example, the `clean()` method is annotated with `@AfterEach` and will be executed after every test method. It prints "FROM AFTER" to the console.

- `@AfterAll`: This annotation is used on a static method to indicate that it should be executed once after all test methods in the class. In this example, the `lastCleanup()` method is annotated with `@AfterAll` and will be executed only once at the end. It prints "ONLY ONCE AT END" to the console.

**Conclusion**:
The `ProcessTest` class demonstrates how to write JUnit test cases using various annotations such as `@BeforeAll`, `@BeforeEach`, `@Order`, `@Test`, `@Disabled`, `@AfterEach`, and `@AfterAll`. These annotations allow you to define the setup and cleanup methods, specify the order of execution, disable certain tests, and provide custom display names. Understanding these annotations and their usage can help you effectively write and organize your JUnit test cases.

<br/>
<br/>
<br/>


**@Order Annotation:**
The `@Order` annotation is used to specify the execution order of test methods within a test class. By default, JUnit runs the test methods in a random order to ensure test independence. However, in certain cases, you may want to control the order in which the tests are executed.

To enable the `@Order` annotation, you need to add the `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)` annotation at the class level. This annotation indicates that the test methods will be ordered based on the `@Order` annotation values.

For example:

```java
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MyTestClass {

    @Test
    @Order(1)
    void firstTestMethod() {
        // Test logic here
    }

    @Test
    @Order(2)
    void secondTestMethod() {
        // Test logic here
    }
}
```

In this example, the `firstTestMethod()` will be executed before the `secondTestMethod()` because it has a lower `@Order` value.

**Naming Conventions:**
When writing test classes and test methods, it is recommended to follow certain naming conventions to improve code readability and maintain consistency. The commonly followed naming conventions are:

- Class Name: The class name should end with "Test" to indicate that it contains test methods. For example, `UserControllerTest`.

- Method Name: Test method names should start with "test" to clearly indicate that they are test cases. For example, `testSave()`. Starting the method name with "test" helps in easily identifying and distinguishing test methods from regular methods.

Following naming conventions makes it easier for developers to understand the purpose of the class and its methods, especially when working with large codebases or collaborating with other team members.

By adhering to these naming conventions, it becomes clear that the class `UserControllerTest` contains tests related to the `UserController`, and the `testSave()` method is a specific test case within that class.

These naming conventions improve code readability and make it easier for others (including future you) to understand and maintain the tests.

Using meaningful and consistent names for classes and methods is essential for writing maintainable and self-explanatory tests.

<br/>
<br/>

# Unit testing of Simple Multiplication code:

**Code: Calculator.java**
```java
package com.app.shivam;

public class Calculator {

    public int multiply(int a, int b) {
        return a * b;
    }

}
```

**Explanation:**
The `Calculator` class represents a simple calculator that has a `multiply` method. The `multiply` method takes two integers as input parameters and returns their multiplication result.

---

**Code: CalculatorTest.java**
```java
package com.app.shivam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

	Calculator c;
	int a, b, actual, expected;

	@BeforeEach
	public void setup() {
		c = new Calculator();
		a = 10;
		b = 20;
		expected = 200;
	}

	@Test
	@Order(10)
	@DisplayName("TESTING NULL CHECK")
	public void testObjCreated() {
		assertNotNull(c);
	}
	
	@Order(20)
	@Test
	@DisplayName("TESTING MULTIPLY OPERATION")
	public void testMultiply() {
		actual = c.multiply(a, b);
		assertEquals(expected, actual);
	}

	@AfterEach
	public void clean() {
		c = null;
	}

}
```

**Explanation:**
The `CalculatorTest` class is a JUnit test class that contains test cases for the `Calculator` class.

- The `setup` method is annotated with `@BeforeEach` and is executed before each test method. It creates a new instance of the `Calculator` class, initializes input values (`a`, `b`), and sets the expected result (`expected`).

- The `testObjCreated` method is annotated with `@Test` and `@Order(10)`. It tests whether the `Calculator` object is created successfully by asserting that it is not null using the `assertNotNull` assertion.

- The `testMultiply` method is annotated with `@Test` and `@Order(20)`. It tests the `multiply` method of the `Calculator` class by asserting that the actual result obtained by invoking the method with the given inputs (`a`, `b`) matches the expected result (`expected`) using the `assertEquals` assertion.

- The `clean` method is annotated with `@AfterEach` and is executed after each test method. It sets the `Calculator` object to null, allowing it to be garbage collected and freeing up resources.

---

**Conclusion:**
In this example, we have a `Calculator` class representing a simple calculator with a `multiply` method. The `CalculatorTest` class contains two test methods: `testObjCreated` and `testMultiply`. 

- `testObjCreated` checks if the `Calculator` object is created successfully.
- `testMultiply` tests the `multiply` method by verifying that the actual result matches the expected result.

By using JUnit's assertions and annotations, we can ensure that our code behaves as expected and meets the desired specifications. Unit tests help in identifying bugs and regressions early in the development cycle, providing confidence in the correctness of the code.

<br/>
<br/>

# Singleton Database Connection Testing 

**Code: ConnectionUtil.java**
```java
package com.app.shivam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static Connection con = null;

    static {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/boot", "root", "Shivam@123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
```

**Explanation:**
The `ConnectionUtil` class provides a utility for obtaining a database connection. It establishes a connection to a MySQL database using the JDBC driver. The connection URL, username, and password are provided to the `DriverManager.getConnection()` method.

---

**Code: ConnectionUtilTest.java**
```java
package com.app.shivam;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConnectionUtilTest {

    Connection con1, con2;
    
    @BeforeEach
    public void setup() {
        con1 = ConnectionUtil.getConnection();
        con2 = ConnectionUtil.getConnection();
    }
    
    @Test
    @Disabled
    @DisplayName("CHECKING NOT NULL")
    @Order(1)
    public void testNotNull() {
        assertAll(
                () -> assertNotNull(con1),
                () -> assertNotNull(con2)
        );
    }
    
    @Test
    @DisplayName("CHECKING SAME REF")
    @Order(2)
    public void testSameRef() {
        assertSame(con1, con2);
    }
    
    @AfterEach
    public void clean() {
        con1 = con2 = null;
    }
}
```

**Explanation:**
The `ConnectionUtilTest` class is a JUnit test class that contains test cases for the `ConnectionUtil` class.

- The `setup` method is annotated with `@BeforeEach` and is executed before each test method. It calls the `getConnection` method of the `ConnectionUtil` class to obtain two instances of the `Connection` object (`con1` and `con2`).

- The `testNotNull` method is annotated with `@Test`, `@Disabled`, `@DisplayName("CHECKING NOT NULL")`, and `@Order(1)`. It verifies that both `con1` and `con2` are not null using the `assertNotNull` assertion wrapped in the `assertAll` assertion. The test is disabled with `@Disabled` to exclude it from test execution.

- The `testSameRef` method is annotated with `@Test`, `@DisplayName("CHECKING SAME REF")`, and `@Order(2)`. It checks if both `con1` and `con2` refer to the same object using the `assertSame` assertion.

- The `clean` method is annotated with `@AfterEach` and is executed after each test method. It sets `con1` and `con2` to null, allowing them to be garbage collected and freeing up resources.

---

**Conclusion:**
In this example, we have a `ConnectionUtil` class that provides a utility for obtaining a database connection. The `ConnectionUtilTest` class contains two test methods: `testNotNull` and `testSameRef`.

- `testNotNull` verifies that the `getConnection` method of `ConnectionUtil` returns a non-null `Connection` object for both `con1` and `con2`.
- `testSameRef` ensures that both `con1` and `con2` refer to the same `Connection` object.

Through these tests, we can verify that the `ConnectionUtil` class correctly establishes a database connection and that multiple calls to `getConnection` return the same connection object.

Unit tests are crucial for ensuring the correctness and reliability of our code. By testing utility classes like `ConnectionUtil`, we can catch potential issues early and ensure the stability of our database interactions.

<br/>
<br/>
<br/>

**Q) How can we confirm that two references are pointed to same Object?**

In JUnit, you can use the `assertSame(expected, actual)` assertion to confirm that two references are pointing to the same object. 

The `assertSame` assertion checks if the `expected` and `actual` references are referring to the exact same object in memory. If they are the same object, the assertion passes. If they are different objects or if one of the references is null, the assertion fails.

Here's an example of using `assertSame`:

```java
Object obj1 = new Object();
Object obj2 = obj1;

assertSame(obj1, obj2);
```

In this example, `obj1` and `obj2` are two references pointing to the same `Object` instance. The `assertSame(obj1, obj2)` assertion passes because the references are pointing to the same object.

If `obj2` was assigned a new instance of `Object` (e.g., `obj2 = new Object()`), the `assertSame` assertion would fail because the references would no longer point to the same object in memory.

Using `assertSame` is a useful way to verify that two references are indeed referring to the same object, which is important in certain scenarios when object identity needs to be checked.