#  Here's a detailed explanation of the mentioned points about Project Lombok:

1. Project Lombok:
   - Project Lombok is an open-source library that acts as a source code generator API.
   - It aims to reduce boilerplate code in Java classes by automatically generating common code constructs such as setters, getters, toString, equals, constructors, and more.

2. Setters and Getters:
   - The `@Setter` annotation is used to automatically generate a setter method for a class variable.
   - The `@Getter` annotation is used to automatically generate a getter method for a class variable.
   - By annotating class variables with `@Setter` and `@Getter`, Lombok generates the corresponding setter and getter methods at compile-time, eliminating the need to write them manually.

3. @ToString:
   - The `@ToString` annotation is used to override the `toString` method of the Object class.
   - By annotating a class with `@ToString`, Lombok generates a `toString` method that prints the current object's data.
   - This eliminates the need to manually implement the `toString` method, making the code more concise and readable.

4. Lombok's Intermediate Source Code:
   - When you use Lombok annotations in your code, Lombok generates an intermediate source code file during compilation.
   - This intermediate code contains the boilerplate code that Lombok generates based on the annotations.
   - Once the code is compiled, the intermediate source code is transformed into bytecode, and Lombok is no longer required at runtime.

5. Usage during Application Development:
   - Lombok is primarily used during the development phase of an application.
   - It helps reduce the amount of code that needs to be written, improving development productivity and code readability.
   - However, it's important to note that Lombok is not a runtime dependency, and the generated code becomes part of the compiled application.

Project Lombok provides various other annotations and features that simplify Java development and reduce repetitive code. It aims to improve code quality and maintainability by reducing the verbosity of Java classes.




<br/>
<br/>
<br/>

# Q) What is the contract between equals() and hashCode()?

The contract between the `equals()` and `hashCode()` methods in Java defines a set of rules that must be followed to ensure consistency and correctness when working with hash-based data structures. Here are the key points of the contract:

1. Consistency:
   - If two objects are equal according to the `equals()` method, they must have the same hash code.
   - In other words, if `a.equals(b)` returns `true`, then `a.hashCode()` and `b.hashCode()` must return the same value.
   - It's important to maintain this consistency to ensure that objects that are considered equal produce the same hash code, allowing them to be properly stored and retrieved from hash-based collections.

2. Unequal Objects:
   - It is not required for two unequal objects to have different hash codes.
   - In other words, if `a.equals(b)` returns `false`, the hash codes of `a` and `b` may or may not be different.
   - However, having different hash codes for unequal objects can help improve the performance of hash-based collections by reducing collisions.

3. Hash Code Distribution:
   - Ideally, the `hashCode()` method should distribute hash codes uniformly across the range of possible values.
   - This helps to minimize collisions and improve the efficiency of hash-based collections.
   - However, it is not a strict requirement for the contract between `equals()` and `hashCode()`, but it is a best practice to aim for a good distribution of hash codes.

4. Equal Objects in Hash-Based Collections:
   - When storing objects in hash-based collections like `HashMap` or `HashSet`, the `hashCode()` method is used to determine the initial bucket or index where the object should be placed.
   - Once an object is found in a bucket, the `equals()` method is used to check for actual equality among objects in the same bucket (due to potential hash code collisions).
   - Therefore, if two objects are equal according to the `equals()` method, they should be found in the same bucket in a hash-based collection.

By following the contract between `equals()` and `hashCode()`, you ensure that objects are properly stored, retrieved, and compared in hash-based collections. Violating this contract can lead to unexpected behavior and incorrect results when working with such collections.


<br/>
<br/>
<br/>

#  Here's the code you provided:

```java
package com.app.shivam;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Process {
	private String code;
	private Integer port;
}
```

```java
package com.app.shivam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataRunner implements CommandLineRunner {

	@Autowired
	private Process pob;
	
	public void run(String... args) throws Exception {
		pob.setCode("AA");
		pob.setPort(985);
		System.out.println(pob);
		System.out.println(pob.getCode());
	}
}
```

Let's go through the code step by step:

1. The `Process` class is a POJO (Plain Old Java Object) with two private fields: `code` of type `String` and `port` of type `Integer`. The class is annotated with Lombok annotations `@Setter`, `@Getter`, `@ToString`, and `@EqualsAndHashCode`. These annotations generate the respective setter and getter methods, a useful `toString()` implementation, and an `equals()` and `hashCode()` implementation based on the fields.

2. The `TestDataRunner` class is a command-line runner component, annotated with `@Component`, and implementing the `CommandLineRunner` interface. It has a dependency on the `Process` class, which is injected using `@Autowired`.

3. In the `run()` method, we set the `code` of the `pob` object to "AA" and the `port` to 985. Then, we print the `pob` object, which will invoke the `toString()` method generated by Lombok. We also print the value of `getCode()`, which is the getter method generated by Lombok for the `code` field.

In conclusion, the code demonstrates the usage of Lombok annotations to reduce boilerplate code. The `@Setter` and `@Getter` annotations eliminate the need to manually write setter and getter methods. The `@ToString` annotation generates a useful `toString()` implementation that prints the values of the object's fields. The `@EqualsAndHashCode` annotation generates `equals()` and `hashCode()` methods based on the fields, allowing proper equality comparisons and usage in hash-based data structures.


<br/>
<br/>
<br/>


@EqualsAndHashCode is an annotation from Lombok that generates and overrides the `equals()` and `hashCode()` methods for the annotated class based on the fields.

Explanation of @EqualsAndHashCode:
- The `@EqualsAndHashCode` annotation is used to generate implementations of the `equals()` and `hashCode()` methods for the annotated class.
- When applied to a class, Lombok analyzes the fields of the class and generates the `equals()` and `hashCode()` methods based on those fields.
- The generated `equals()` method compares the fields of two objects for equality, while the generated `hashCode()` method generates a hash code for an object based on its fields.
- The generated implementations consider all non-static fields by default, but you can customize this behavior using Lombok's additional annotations or configuration options.

Regarding the default constructor in Java:
- If a Java class does not define any constructors explicitly, the Java compiler will automatically generate a default constructor.
- The default constructor has no parameters and initializes the object with default values.
- It is generated by the compiler only if there are no other constructors defined in the class.
- The default constructor is useful when you want to create an object without passing any initial values.

In summary, Lombok's `@EqualsAndHashCode` annotation saves you from writing boilerplate code by generating and overriding the `equals()` and `hashCode()` methods based on the fields of the annotated class. The Java compiler automatically generates a default constructor if no constructors are defined in the class.

<br/>

# Let's explore the concepts and annotations related to constructors in Java and Lombok.

1. Default Constructor in Java:
   - In Java, if a class does not explicitly define any constructors, the Java compiler automatically generates a default constructor with no parameters.
   - The default constructor is a special constructor that initializes the object with default values or performs any necessary setup operations.
   - It is created implicitly by the compiler only if there are no other constructors defined in the class.

2. @NoArgsConstructor:
   - The `@NoArgsConstructor` annotation from Lombok generates a zero-parameter constructor for the annotated class.
   - When you apply `@NoArgsConstructor` to a class, Lombok generates a constructor that initializes the object with default values for all fields.
   - This annotation is useful when you want to provide a convenient way to create an instance of the class without specifying any initial values.

3. @AllArgsConstructor:
   - The `@AllArgsConstructor` annotation from Lombok generates a constructor that accepts parameters for all fields in the annotated class.
   - When you apply `@AllArgsConstructor` to a class, Lombok generates a constructor that takes arguments for each field in the class and initializes the object with those values.
   - This annotation is helpful when you want to provide a constructor that allows setting all fields of the class at once.

It is good coding practice to include a default constructor (`@NoArgsConstructor`) in your classes, even if you later add other constructors. This ensures consistency and provides a way to create objects without specifying initial values for the fields.

In conclusion, Lombok's `@NoArgsConstructor` and `@AllArgsConstructor` annotations help reduce boilerplate code by generating constructors automatically. The `@NoArgsConstructor` annotation generates a zero-parameter constructor, and the `@AllArgsConstructor` annotation generates a constructor with parameters for all fields.


<br/>
<br/>
<br/>
<br/>

# Here's the code example with detailed explanations:

Code example:
```java
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Process {
    @NonNull
    private Integer port;
    @NonNull
    private String code;
    private Double cost;
    private String type;
}
```

Explanation:

1. `@NoArgsConstructor`: This annotation generates a no-argument constructor for the class. In the case of the `Process` class, it generates the following default constructor:
```java
public Process() {
    super();
}
```
This constructor initializes the object with default values for each field.

2. `@AllArgsConstructor`: This annotation generates a constructor that accepts all the fields of the class as arguments. In the case of the `Process` class, it generates the following constructor:
```java
public Process(Integer port, String code, Double cost, String type) {
    super();
    this.port = port;
    this.code = code;
    this.cost = cost;
    this.type = type;
}
```
This constructor allows you to initialize all the fields of the class in a single constructor call.

3. `@RequiredArgsConstructor`: This annotation generates a constructor that accepts only the fields marked with `@NonNull` as arguments. In the case of the `Process` class, it generates the following constructor:
```java
public Process(Integer port, String code) {
    super();
    this.port = port;
    this.code = code;
}
```
This constructor is useful when you want to ensure that certain fields are provided with non-null values during object initialization. Other fields that are not marked with `@NonNull` are not included in this constructor.

In summary, the Lombok annotations `@NoArgsConstructor`, `@AllArgsConstructor`, and `@RequiredArgsConstructor` help in generating constructors for the class based on different requirements. These annotations save you from writing repetitive boilerplate code and provide convenient ways to initialize objects with default values, all fields, or selected fields based on the `@NonNull` annotation.


<br/>
<br/>
<br/>

#  Here's the code example with detailed explanations:

1. If we add `@RequiredArgsConstructor` without any `@NonNull` variable, Lombok generates a default constructor:
```java
@RequiredArgsConstructor
public class Process {
    private Integer port;
}
```
Generated Source code:
```java
public class Process {
    private Integer port;
    public Process() {
       super();
    }
}
```
In this case, since there are no `@NonNull` variables, Lombok generates a default constructor.

2. If we add `@AllArgsConstructor` over a class which has zero variables, Lombok generates a default constructor:
```java
@AllArgsConstructor
public class Process {
}
```
Generated Source code:
```java
public class Process {
    public Process() {
       super();
    }
}
```
In this case, even though there are no variables in the class, Lombok still generates a default constructor.

3. Lombok generates source code based on annotation definitions, and the compiler validates the generated source code. If multiple constructor annotations are used, Lombok will generate multiple constructors without considering whether they are duplicates or not.

Example:
```java
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Process {
    
}
```
Generated Source Code:
```java
public class Process {
    public Process() {
       super();
    }
    public Process() {
       super();
    }
    public Process() {
       super();
    }
}
```
In this case, Lombok generates three constructors without checking if they are duplicates.

4. With `@AllArgsConstructor` and `@RequiredArgsConstructor`, Lombok generates constructors based on the variables in the class. The `@NonNull` annotation is used to indicate which variables are mandatory in the constructor.

Example:
```java
@AllArgsConstructor
@RequiredArgsConstructor
public class Process {
    @NonNull
    private String code;
}
```
Generated Source Code:
```java
public class Process {
    private String code;
    public Process(String code) {
       super();
       this.code = code;
    }
    public Process(String code) {
       super();
       this.code = code;
    }
}
```
In this case, Lombok generates two constructors. The first constructor accepts a `String code` argument, while the second constructor accepts a `String code` argument annotated with `@NonNull`. Its Results Compile Time Error. The `@NonNull` annotation ensures that the variable is mandatory.

In conclusion, Lombok provides convenient annotations like `@AllArgsConstructor`, `@NoArgsConstructor`, and `@RequiredArgsConstructor` to generate constructors with different configurations based on the variables in the class. These annotations help reduce boilerplate code and improve code readability.

<br/>
<br/>
<br/>


#  Here's the explanation of the remaining features:

1. `@Builder`:
```java
@Builder
public class Process {
    private int id;
    private String type;
    private String code;
}
```
Usage:
```java
Process p1 = Process.builder()
                    .code("AA")
                    .id(88)
                    .type("MODEL")
                    .build();
```
The `@Builder` annotation generates a builder pattern implementation for the class. It provides a fluent API for constructing objects. In the example, we can chain method calls on the builder to set the values of the object's fields. Finally, we call the `build()` method to create an instance of the `Process` class with the provided values.

2. `@Data`:
```java
@Data
public class Process {
    private int id;
}
```
The `@Data` annotation is a convenient shortcut that combines several other annotations (`@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`) along with a default constructor (`@NoArgsConstructor`). It generates the getters and setters for the class fields, overrides the `toString()` method to provide a string representation of the object, and generates the `equals()` and `hashCode()` methods for comparison.

3. `@Cleanup`:
```java
@Cleanup
public void exampleMethod() {
    // Code that uses a resource, like a file or stream
}
```
The `@Cleanup` annotation generates cleanup code for the annotated variable or resource. It is used in conjunction with try-with-resources statements to automatically close resources after they are no longer needed. In the example, the `@Cleanup` annotation ensures that the resource represented by the annotated variable is closed when the method execution reaches its end.

In conclusion, Lombok provides several powerful annotations like `@Builder`, `@Data`, and `@Cleanup` to simplify common programming tasks. `@Builder` generates a builder pattern for easy object creation, `@Data` combines multiple annotations for common class operations, and `@Cleanup` generates cleanup code for resource handling. These features help reduce boilerplate code and improve code readability.



# Here's a detailed explanation of `@Builder` in Project Lombok:

The `@Builder` annotation provided by Project Lombok is used to automatically generate a builder pattern for a class. The builder pattern simplifies the creation of complex object instances by providing a fluent API for setting values.

When you annotate a class with `@Builder`, Lombok generates a builder class within the annotated class. The generated builder class provides methods to set values for each property of the annotated class and ultimately constructs the final object using a build method.

Here's an example to illustrate the usage of `@Builder`:

```java
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Person {
    private String name;
    private int age;
    private String address;
}
```

In the above example, the `Person` class is annotated with `@Builder`. After compiling the code, Lombok generates a `PersonBuilder` class within the `Person` class.

You can then use the generated builder to create instances of the `Person` class in a more readable and concise way:

```java
Person person = Person.builder()
                     .name("John Doe")
                     .age(30)
                     .address("123 Main Street")
                     .build();
```

The builder provides fluent methods to set the values of each property. The `build()` method is used to construct the final `Person` object.

The `@Builder` annotation also provides additional customization options. For example, you can use the `@Singular` annotation to handle collection properties in a more convenient way:

```java
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

@Getter
@Setter
@Builder
public class Team {
    private String name;
    @Singular private List<String> members;
}
```

In this example, the `members` property is annotated with `@Singular`, which allows you to add individual members to the team using a singular method:

```java
Team team = Team.builder()
               .name("Awesome Team")
               .member("John")
               .member("Jane")
               .member("Alex")
               .build();
```

The `@Builder` annotation is a powerful feature provided by Project Lombok that simplifies object creation and reduces boilerplate code, making your code more concise and readable.