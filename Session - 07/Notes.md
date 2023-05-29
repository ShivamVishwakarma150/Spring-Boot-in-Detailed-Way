# @Value Annotation

The `@Value` annotation in Spring is used to provide values to variables or dependencies. It offers several use cases, including hardcoding values, reading data from resources, and using Spring Expression Language (SpEL). Let's explore each use case in detail:

1. Hardcoding Variables/Dependencies:
```java
@Component
public class MyClass {
    @Value("Hello, World!")
    private String message;
    
    // ...
}
```
In this example, the `@Value` annotation is used to hardcode the value "Hello, World!" into the `message` variable of the `MyClass` bean.

2. Reading Data from Resources:
```java
@Component
public class MyClass {
    @Value("${my.property}")
    private String propertyValue;
    
    // ...
}
```
Here, the `@Value` annotation is used to read the value of the `my.property` key from a resource file (e.g., `.properties`, `.yml`, or `.xml`). The actual value will be resolved from the resource file based on the Spring environment configuration.

3. Providing Spring Expression Language (SpEL):
```java
@Component
public class MyClass {
    @Value("#{myBean.calculateValue()}")
    private int calculatedValue;
    
    // ...
}
```
In this case, the `@Value` annotation is used to assign a value to the `calculatedValue` variable using Spring Expression Language (SpEL). The SpEL expression `#{myBean.calculateValue()}` invokes the `calculateValue()` method of the `myBean` bean to dynamically calculate the value.

These examples demonstrate different scenarios where the `@Value` annotation can be used to provide data to variables or dependencies in Spring. It offers flexibility and allows you to externalize configuration, provide dynamic values, or use expressions for complex calculations.

Note: To use the `@Value` annotation, make sure you have the necessary Spring dependencies and appropriate configuration (e.g., property files, SpEL expressions) in your project.

<br/>
<br/>
<br/>


# Note

1. @Value:
The `@Value` annotation in Spring is used for Value-based Injection, also known as Direct Injection or Field Injection. It allows you to inject values directly into variables or dependencies without the need for setter methods. By using `@Value`, you can provide constant values, read values from external resources, or use Spring Expression Language (SpEL) for more advanced scenarios.

1. Annotation Configuration vs. XML:
Annotation-based configuration is generally faster compared to XML configuration in Spring. This is because annotation-based configuration avoids the overhead of reading and parsing additional XML files. Annotations are processed at runtime, so the configuration information is readily available without the need for XML parsing.

1. Usage for Our Classes:
Annotation-based configuration is typically used for configuring our own classes, meaning classes that we develop in our application. We can annotate our classes with relevant annotations to define beans, dependencies, and configurations. This approach is not suitable for pre-defined classes provided by external libraries or frameworks, as we cannot directly annotate those classes.

1. Default Single Object:
Annotation-based configuration is commonly used when we want to configure a default single object for a specific class. For example, when configuring a database connection or a view resolver, we often need a single instance of the respective class with specific configurations. Annotations allow us to define and configure these objects easily and concisely.

Overall, annotation-based configuration in Spring offers a more streamlined and convenient approach for configuring and managing dependencies and configurations in our application. It promotes a code-centric configuration style and helps improve development efficiency. However, it's worth noting that XML configuration still has its place in certain scenarios, especially when dealing with complex or dynamic configurations that may benefit from the flexibility and modularity of XML files.

<br/>
<br/>
<br/>

# Some important Questions and Their Answer


Q1) What is varargs and how is it different from an array? Provide all rules to work with varargs.

```java
public void printValues(String... values) {
    for (String value : values) {
        System.out.println(value);
    }
}
```

In this example, `printValues` is a method that uses varargs. It can accept a variable number of String arguments. You can call this method with any number of String arguments, or even pass an array of String as an argument.

```java
printValues("Hello", "World");
printValues("Welcome");
printValues("Open", "AI", "GPT", "3");
```

The main differences between varargs and arrays are:
- Syntax: Varargs use three dots (...) after the parameter type, while arrays use square brackets ([]).
- Calling: When calling a varargs method, you can pass arguments directly without creating an array explicitly. For arrays, you need to create and pass an array explicitly.
- Empty Argument List: You can call a varargs method with an empty argument list. For arrays, you need to pass an empty array explicitly.

Rules for working with varargs:
1. Varargs must be the last parameter in the method signature.
2. You can have only one varargs parameter in a method.
3. The varargs parameter is treated as an array inside the method.
4. You can pass zero or more arguments of the specified type to the varargs parameter.
5. You can call a varargs method with individual arguments or pass an array.
6. Varargs can be combined with other parameters in a method.

Q2) What is casting (upcasting and downcasting)?

```java
class Animal {
    public void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    public void eat() {
        System.out.println("Dog is eating");
    }

    public void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();  // Upcasting
        animal.eat();  // Output: "Dog is eating"
        
        Dog dog = (Dog) animal;  // Downcasting
        dog.eat();  // Output: "Dog is eating"
        dog.bark();  // Output: "Dog is barking"
    }
}
```

In this example, we have a superclass `Animal` and a subclass `Dog`. We create an instance of `Dog` and assign it to a reference variable of type `Animal` (upcasting). Since the `Dog` class extends the `Animal` class, it is allowed.

We can then downcast the `Animal` reference back to a `Dog` reference using explicit casting `(Dog)`. With the downcasted reference, we can access both the overridden method `eat()` and the additional method `bark()` specific to the `Dog` class.

Q3) What is overloading? What are all the rules we need to follow?

```java
class Calculator {
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public double add(double num1, double num2) {
        return num1 + num2;
    }

    public String concatenate(String str1, String str2) {
        return str1 + str2;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int sum = calculator.add(5, 10);  // Invokes the int version of add()


        double total = calculator.add(3.14, 2.5);  // Invokes the double version of add()
        String result = calculator.concatenate("Hello", "World");  // Invokes the concatenate() method
    }
}
```

In this example, we have a class `Calculator` that demonstrates method overloading. The class has multiple methods with the same name but different parameter types or a different number of parameters.

Rules for overloading:
1. Method Name: The overloaded methods must have the same name.
2. Parameter List: The parameter list of the overloaded methods must be different in terms of the number of parameters and/or the parameter types. The order of the parameters is also considered.
3. Return Type: The return type of the methods can be the same or different. It is not considered for method overloading.
4. Access Modifier: The access modifiers can be the same or different for the overloaded methods.
5. Exceptions: The overloaded methods can throw different exceptions or the same exceptions in a different order. However, the exceptions alone cannot differentiate between overloaded methods.
6. Overloading does not depend on the method's return type or exceptions thrown; it only considers the method name and parameter list.

Q4) What is dynamic polymorphism?

Dynamic polymorphism, also known as runtime polymorphism or late binding, is a feature in object-oriented programming languages like Java. It allows a subclass object to be treated as an object of its superclass. The actual method that gets executed is determined at runtime based on the type of the object.

Example:
```java
class Animal {
   public void makeSound() {
      System.out.println("Animal is making a sound");
   }
}

class Dog extends Animal {
   @Override
   public void makeSound() {
      System.out.println("Dog is barking");
   }
}

public class Main {
   public static void main(String[] args) {
      Animal animal = new Dog();  // Upcasting
      animal.makeSound();  // Output: "Dog is barking"
   }
}
```

In the example, the `makeSound()` method is overridden in the `Dog` class. When the `makeSound()` method is called on the `animal` object, which is of type `Animal` but refers to a `Dog` instance, the method in the `Dog` class is executed dynamically based on the actual object type.

Q5) What is method overriding?

Method overriding is a feature in Java that allows a subclass to provide a specific implementation of a method that is already defined in its superclass. The method in the subclass has the same name, return type, and parameter list as the method in the superclass.

Rules for method overriding:

1. Inheritance: Method overriding is only applicable in an inheritance relationship between classes or interfaces.
2. Method Signature: The method in the subclass must have the same method signature (name, return type, and parameter list) as the method in the superclass.
3. Access Modifier: The overridden method in the subclass cannot have a more restrictive access modifier compared to the method in the superclass. It can have the same or a less restrictive access modifier.
4. Exception Handling: The overridden method in the subclass can throw the same or narrower checked exceptions, or no exceptions at all. It cannot throw broader checked exceptions or new checked exceptions that are not declared in the superclass.
5. Annotation: The methods can have different annotations, but it does not affect method overriding.
6. `@Override` Annotation: It is a good practice to use the `@Override` annotation when overriding a method. It helps to prevent errors by indicating that the method is intended to override a superclass method.

Method overriding allows a subclass to provide its own implementation of a method, enabling polymorphic behavior when the method is called on a superclass reference variable, but the actual subclass implementation is invoked at runtime. This allows for runtime polymorphism, where different objects of related classes can be treated uniformly through their common superclass interface, but exhibit different behaviors based on their specific implementations.


<br/>
<br/>
<br/>

# Let's go through the code example step by step:

1. Spring Bean + Annotation Configuration

```java
package my.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("hob")
public class HandlerInfo {
    @Value("data")
    private String resolverCode;
    @Value("temp")
    private String pathToStore;
    
    public HandlerInfo() {
        super();
    }

    @Override
    public String toString() {
        return "HandlerInfo [resolverCode=" + resolverCode + ", pathToStore=" + pathToStore + "]";
    }
}
```

In this example, we have a class `HandlerInfo` that is annotated with `@Component`. This annotation marks the class as a Spring bean. The name of the bean is specified as "hob" using the `@Component("hob")` annotation. 

The class has two private instance variables, `resolverCode` and `pathToStore`, which are annotated with `@Value`. The `@Value` annotation is used to inject values into these variables. In this case, the values "data" and "temp" are directly assigned to the variables.

The `toString()` method is overridden to provide a string representation of the `HandlerInfo` object.

2. Test Class

```java
package my.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        // 1. Create an empty container
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        // 2. Scan for classes from the given base package (my.app and its sub-packages)
        ac.scan("my.app");
        // 3. Refresh the Spring container
        ac.refresh();
        // 4. Read the object
        HandlerInfo ob = ac.getBean("hob", HandlerInfo.class);
        // 5. Print the object
        System.out.println(ob); // Calls ob.toString()
    }
}
```

In this example, we have a test class `Test` that demonstrates how to use the Spring container to create and retrieve the `HandlerInfo` bean.

The `AnnotationConfigApplicationContext` class is used to create an empty container. Then, the `scan()` method is called to scan for classes from the base package "my.app" and its sub-packages. This allows Spring to discover and register the `HandlerInfo` bean.

The container is refreshed using the `refresh()` method, which initializes and prepares the Spring container.

The `getBean()` method is used to retrieve the `HandlerInfo` bean by its name ("hob") and type (`HandlerInfo.class`).

Finally, the `HandlerInfo` object is printed using `System.out.println(ob)`, which implicitly calls the `toString()` method of the `HandlerInfo` class to display the object's string representation.

Overall, this example demonstrates the creation of a Spring bean using annotations and how to retrieve and use the bean from the Spring container using the `AnnotationConfigApplicationContext` class.

<br/>
<br/>
<br/>

# Important Concept related to base-Package and sub-Packages @ComponentScan

In Spring Annotation configuration, the basePackage is the input that needs to be provided to the container so that it can scan and find our classes. The container will search for classes in the specified basePackage and its subpackages.

To specify the basePackage(s) to scan, we use the `@ComponentScan` annotation. It allows us to provide one or more basePackages from which the container will scan for classes. Here are a few examples:

Example 1: Scan a single basePackage
```java
@ComponentScan("com.app")
```
or
```java
@ComponentScan(basePackages = "com.app")
```
In this example, the container will scan the "com.app" package and its subpackages to find classes annotated with `@Component` or other stereotype annotations.

Example 2: Scan multiple basePackages
```java
@ComponentScan({"com.app", "test.one", "abc.xyz"})
```
or
```java
@ComponentScan(basePackages = {"com.app", "test.one", "abc.xyz"})
```
In this example, the container will scan the specified basePackages and their subpackages to find classes annotated with `@Component` or other stereotype annotations.

It's important to note that if a class is annotated with `@Component` but is not located within the specified basePackage(s), the container will not be able to find that class during scanning.

To use the `@ComponentScan` annotation effectively, it's recommended to define a separate configuration class and annotate it with `@ComponentScan` to specify the basePackage(s). This allows for better organization and separation of concerns within the application.

Here's an example of a configuration class with `@ComponentScan`:

```java
@Configuration
@ComponentScan(basePackages = "com.app")
public class AppConfig {
    // Additional configuration or bean definitions can be added here
}
```

By using `@ComponentScan` and specifying the basePackage(s) correctly, we ensure that the container can find and manage our classes during the Spring Annotation configuration process.


<br/>
<br/>
<br/>

# Example#2

Let's go through the code step by step and provide explanations for each section:

1. Spring Bean:

```java
package com.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("eobj")
public class EmailConfig {
	
	@Value("gmail")
	private String host;
	
	@Value("852")
	private Integer port;
	
	@Override
	public String toString() {
		return "EmailConfig [host=" + host + ", port=" + port + "]";
	}
}
```

- The `EmailConfig` class is annotated with `@Component("eobj")`, indicating that it is a Spring bean with the name "eobj".
- The `@Value` annotation is used to inject values into the `host` and `port` fields of the bean.
- The `toString()` method is overridden to provide a string representation of the bean.

2. MyAppConfig:

```java
package com.app;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.app","my.one"})
public class MyAppConfig {
}
```

- The `MyAppConfig` class is used as the configuration class.
- The `@ComponentScan` annotation is used to specify the basePackages for component scanning.
- In this example, two basePackages are specified: "com.app" and "my.one". It means the component scanning will search for beans in these packages and their sub-packages.

3. MyCacheData:

```java
package com.app.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("cob")
public class MyCacheData {
	
	@Value("redis")
	private String provider;
	
	@Override
	public String toString() {
		return "MyCacheData [provider=" + provider + "]";
	}
}
```

- The `MyCacheData` class is annotated with `@Component("cob")`, indicating that it is a Spring bean with the name "cob".
- The `@Value` annotation is used to inject the value "redis" into the `provider` field of the bean.
- The `toString()` method is overridden to provide a string representation of the bean.

4. ViewResolver:

```java
package my.one.format;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("vr")
public class ViewResolver {
	
	@Value("pages")
	private String prefix;
	
	@Value("jsp")
	private String suffix;
	
	@Override
	public String toString() {
		return "ViewResolver [prefix=" + prefix + ", suffix=" + suffix + "]";
	}
}
```

- The `ViewResolver` class is annotated with `@Component("vr")`, indicating that it is a Spring bean with the name "vr".
- The `@Value` annotation is used to inject the values "pages" and "jsp" into the `prefix` and `suffix` fields of the bean.
- The `toString()` method is overridden to provide a string representation of the bean.

5. Test:

```java
package com.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyAppConfig.class);
		Object ob3 = ac.getBean("vr");
		System.out.println(ob3);
	}
}
```

- The `Test` class is the main class where the application is executed.
- An `AnnotationConfigApplicationContext` is created with the `MyAppConfig` class passed as an argument. It initializes the Spring application context.
- The `ac.getBean("vr")` retrieves the bean with the name "vr" from the application context.
- The retrieved bean is printed using `System.out.println()`.



When running the `Test` class, the Spring application context is created based on the `MyAppConfig` configuration class. The component scanning takes place in the specified basePackages ("com.app" and "my.one"), searching for beans in these packages and their sub-packages. The beans defined within these packages are registered in the application context. In this example, the `ViewResolver` bean is retrieved from the application context using `getBean("vr")` and then printed using `System.out.println()`, displaying the injected values.

This code demonstrates the usage of Spring bean configuration and component scanning with annotations. The `@Component` annotation marks the classes as Spring beans, the `@ComponentScan` annotation specifies the basePackages for component scanning, and the `@Value` annotation is used to inject values into the beans' fields.

The code provided demonstrates the usage of Spring bean configuration and component scanning using annotations. Let's go through each part of the code and explain it in detail:

1. Spring Bean:
   - The `EmailConfig` class is annotated with `@Component` to indicate that it is a Spring bean.
   - The `@Component("eobj")` annotation specifies the bean name as "eobj".
   - The `@Value` annotation is used to inject values into the fields of the bean.
   - The `toString()` method is overridden to provide a string representation of the bean.

2. MyAppConfig:
   - The `MyAppConfig` class is used as the configuration class.
   - The `@ComponentScan` annotation is used to specify the basePackages for component scanning.
   - In this example, two basePackages are specified: "com.app" and "my.one".

3. MyCacheData:
   - The `MyCacheData` class is another Spring bean annotated with `@Component`.
   - The `@Component("cob")` annotation specifies the bean name as "cob".
   - The `@Value` annotation is used to inject values into the fields of the bean.
   - The `toString()` method is overridden to provide a string representation of the bean.

4. ViewResolver:
   - The `ViewResolver` class is another Spring bean annotated with `@Component`.
   - The `@Component("vr")` annotation specifies the bean name as "vr".
   - The `@Value` annotation is used to inject values into the fields of the bean.
   - The `toString()` method is overridden to provide a string representation of the bean.

5. Test:
   - The `Test` class is the main class where the application is executed.
   - An `AnnotationConfigApplicationContext` is created with the `MyAppConfig` class passed as an argument.
   - The `MyAppConfig` class is used as the configuration class for the application context.
   - The `ac.getBean("vr")` retrieves the bean with the name "vr" from the application context.
   - The retrieved bean is printed using `System.out.println()`.

When running the `Test` class, the application context is created based on the `MyAppConfig` configuration class. The component scanning takes place in the specified basePackages ("com.app" and "my.one"). The beans defined within these packages are registered in the application context. In this example, the `ViewResolver` bean is retrieved from the application context and printed, showing the injected values.

This example demonstrates how component scanning and bean configuration can be done using annotations in Spring. The `@Component` annotation marks the classes as Spring beans, and the `@ComponentScan` annotation specifies the basePackages to scan for beans. The `@Value` annotation is used to inject values into the beans' fields.

<br/>
<br/>
<br/>

