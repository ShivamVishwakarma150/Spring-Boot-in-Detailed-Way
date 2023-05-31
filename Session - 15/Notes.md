# SpEL (Spring Expression Language)

SpEL (Spring Expression Language) is a powerful expression language that is used in the Spring Framework for evaluating and manipulating values at runtime. It provides a flexible and concise way to express complex expressions and perform operations on objects.

SpEL expressions are created using a combination of operators, variables, class/objects, method calls, and literals. These expressions can be used in various contexts within the Spring Framework, such as in annotations, XML configurations, and programmatic evaluations.

Here are some categories of operators that can be used in SpEL expressions:

1. **Arithmetic Operators:** SpEL supports arithmetic operators such as addition (+), subtraction (-), multiplication (*), division (/), modulus (%), exponentiation (^), and their corresponding aliases (div for division and mod for modulus).

2. **Relational Operators:** SpEL allows you to perform relational operations like less than (<), greater than (>), equal to (==), not equal to (!=), less than or equal to (<=), greater than or equal to (>=), and their corresponding aliases (lt, gt, eq, ne, le, ge).

3. **Logical Operators:** SpEL provides logical operators like logical AND (and, &&), logical OR (or, ||), and logical NOT (not, !). These operators can be used to combine and evaluate multiple conditions.

4. **Conditional Operator:** SpEL supports the conditional operator (?:) which allows you to perform conditional evaluations. It works similar to the ternary operator in Java and allows you to choose between two values based on a condition.

SpEL expressions can be used for various purposes within the Spring Framework, such as defining bean dependencies, specifying property values, conditional bean creation, and dynamic method invocations.

Overall, SpEL provides a flexible and expressive way to work with values and perform operations within the Spring Framework, enabling dynamic and configurable behavior in your applications.

<br/>
<br/>
<br/>

# `@Value` Annotation

The `@Value` annotation in Spring is used to inject values into variables, fields, or constructor parameters. It provides a convenient way to assign values to properties without the need for XML configuration.

Here are the different use cases for the `@Value` annotation:

1. **Hardcoding value**: You can directly assign a hardcoded value to a variable using the `@Value` annotation. For example: `@Value("someValue")`. This is useful when you have a constant value that you want to inject.

2. **Reading data from external resources**: You can use the `@Value` annotation to read values from external configuration files or property sources. The value can be specified using placeholders and resolved at runtime. For example: `@Value("${propertyName}")`. This allows you to externalize the configuration and change the values without modifying the source code.

3. **SpEL (Spring Expression Language)**: You can use SpEL expressions within the `@Value` annotation to execute dynamic expressions and evaluate complex logic. This is denoted by `#{}`. For example: `@Value("#{expression}")`. SpEL expressions can include operators, variables, method calls, and even references to other beans in the container.

4. **Linking objects using SpEL**: SpEL expressions can be used to link objects within the `@Value` annotation. This is useful when you want to reference another bean in the container. For example: `@Value("#{childObjectName}")`. This allows you to establish a relationship between objects and retrieve values from other beans.

By using the `@Value` annotation with different approaches, you can provide values to your Spring components in a flexible and dynamic manner. It allows you to inject values from various sources and even execute complex expressions using SpEL. This promotes configurability and reusability in your application.

<br/>
<br/>
<br/>

# Points Regarding SpEL

1. When using SpEL within the `@Value` annotation, the expression should be enclosed within `#{}`. This indicates that the value is a SpEL expression and should be evaluated at runtime.

2. If you want to specify a string value within the SpEL expression, you can use single quotes to enclose the string. For example: `'hello'`. This ensures that the value is treated as a string literal.

3. When calling an instance method within a SpEL expression, you need to provide the object or reference on which the method should be called. For example, if you have a bean named `sob` with a method `getId()`, you can call it within a SpEL expression using `#{sob.getId()}`. This allows you to access instance methods and retrieve dynamic values.

Example:
```java
@Component("sob")
public class Sample {
   @Value("88")
   private int id;
}
```
In another class, you can use the `@Value` annotation to retrieve the value of `id` using SpEL:
```java
@Value("#{sob.id % 10}")  // Equivalent to sob.getId()
private int calculatedValue;
```
Here, the SpEL expression `#{sob.id % 10}` will call the `getId()` method on the `sob` bean and perform a modulo operation on the returned value.

4. To call a static method within a SpEL expression, you need to specify that it is a type using `T(ClassName).methodName()`. For example, if you have a static method `someStaticMethod()` in the `MyUtils` class, you can call it within a SpEL expression using `#{T(MyUtils).someStaticMethod()}`.

Using SpEL with the `@Value` annotation provides powerful capabilities to perform dynamic calculations, retrieve values from beans, and even call static methods. It allows you to configure and customize your application based on runtime conditions and external resources.

<br/>
<br/>
<br/>

# Here's the code with explanations:

1. Spring Beans
```java
@Component
public class ModelData {
    @Value("TEST")
    private String model;

    // Getter, setter, and toString methods
    // ...
}
```
In this code, the `ModelData` class is a Spring bean annotated with `@Component`. It has a `model` field annotated with `@Value("TEST")`, which sets the value of the `model` field to the string "TEST".

```java
@Component
public class Process {
    @Value("#{modelData.model.toLowerCase()}")
    private String code;

    @Value("#{5 % 2 > 0 and 2 + 3 le 5}")
    private boolean isPresent;

    // Getter and toString methods
    // ...
}
```
The `Process` class is another Spring bean annotated with `@Component`. It has a `code` field annotated with `@Value("#{modelData.model.toLowerCase()}")`, which uses SpEL to call the `toLowerCase()` method on the `model` field of the `modelData` bean. This converts the value of `model` to lowercase and assigns it to the `code` field.

The `isPresent` field is annotated with `@Value("#{5 % 2 > 0 and 2 + 3 le 5}")`, which uses SpEL to perform logical and arithmetic operations. It checks if the remainder of dividing 5 by 2 is greater than 0 and if the sum of 2 and 3 is less than or equal to 5. The result of this expression is assigned to the `isPresent` field.

2. Spring Config code
```java
@ComponentScan("com.app.shivam")
public class AppConfig {
    // Configuration class with component scanning
}
```
The `AppConfig` class is the Spring configuration class annotated with `@ComponentScan` to enable component scanning in the `com.app.shivam` package.

3. Test class
```java
public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Process p = ac.getBean("process", Process.class);
        System.out.println(p);
    }
}
```
The `Test` class is the entry point of the application. It creates an instance of `AnnotationConfigApplicationContext` with the `AppConfig` class and retrieves a `Process` bean from the application context using the bean name "process". Finally, it prints the `Process` object, which will display the values of the `code` and `isPresent` fields.

When you run the `Test` class, the `Process` object will be created, and the values of the `code` and `isPresent` fields will be determined by the SpEL expressions specified in the `@Value` annotations.

Overall, this example demonstrates the usage of SpEL expressions within the `@Value` annotation to dynamically assign values to bean fields based on calculations, method invocations, and references to other beans.

<br/>
<br/>
<br/>

# Here are the SpEL expressions for the given concepts:

1. String to char[]:
```java
@Value("#{modelData.model.toCharArray()}")
private char[] data;

@Value("#{'Hello Users!'.toCharArray()}")
private char[] data;
```
The first expression converts the value of `model` field from the `modelData` bean to a `char` array using the `toCharArray()` method. The result is assigned to the `data` field. The second expression converts the string "Hello Users!" to a `char` array using the `toCharArray()` method.

2. String to byte[]:
```java
@Value("#{modelData.model.getBytes()}")
private byte[] arr;

@Value("#{'Hello Users!'.getBytes()}")
private byte[] arr;
```
The first expression converts the value of `model` field from the `modelData` bean to a `byte` array using the `getBytes()` method. The result is assigned to the `arr` field. The second expression converts the string "Hello Users!" to a `byte` array using the `getBytes()` method.

3. Any type to Stream:
```java
@Value("#{modelData.model.lines()}")
private Stream<String> stream;
```
The expression `modelData.model.lines()` converts the value of the `model` field from the `modelData` bean to a `Stream<String>`. The `lines()` method splits the string into lines and returns a stream of lines. The result is assigned to the `stream` field.

These SpEL expressions demonstrate the conversion of data types using various methods available in Java, such as `toCharArray()`, `getBytes()`, and `lines()`.

<br/>
<br/>
<br/>

# Circular dependency

Circular dependency occurs when two or more classes have dependencies on each other. In the context of Spring Framework, circular dependency refers to a situation where beans have dependencies on each other in a way that creates an infinite loop.

To handle circular dependencies, Spring uses a mechanism called "constructor injection with proxies". The steps involved in resolving circular dependencies are as follows:

1. Spring first creates the object of one of the classes involved in the circular dependency using the default constructor.
2. Then, it tries to set the dependencies of that object using the setter methods or field injection.
3. When it encounters a circular dependency, instead of throwing an error, Spring creates a proxy object that represents the dependency and injects it into the current object.
4. Spring continues the object creation process for the remaining classes, resolving the circular dependency by using the proxy object.
5. Finally, the fully initialized objects are returned by the Spring container, and the circular dependency is resolved.

However, if you have overridden the `toString()` method in both classes A and B and you try to print the objects or references, it may lead to an error. This is because the `toString()` method internally calls the `toString()` method of the referenced object, which can result in an infinite loop in the case of circular dependencies. To avoid this, it's recommended to be cautious when implementing the `toString()` method in classes involved in circular dependencies.

By default, Spring uses lazy initialization for circular dependencies, which means the dependencies are only resolved when they are actually needed. This helps to prevent any potential issues related to circular dependencies during the object initialization process.

Overall, Spring's handling of circular dependencies ensures that objects are created and linked in the correct order, allowing the application to function properly without getting stuck in an infinite loop.

<br/>
<br/>

# Explain This

```
1. Create objects using default constructor
2. Call set methods to link Objects.
ex:
 class A{  B ob; }
 class B{ A oa; }

Then container will do
  A a1 = new A();
  B b1 = new B();
  a1.setOb(b1);
  b1.setOa(a1);
```

Yes, you've described the steps that the Spring container takes to handle circular dependencies. Let's break down the process:

1. Create objects using the default constructor:
   - The Spring container first creates an instance of class A using its default constructor. This creates an object `a1` of type A.
   - Similarly, the container creates an instance of class B using its default constructor. This creates an object `b1` of type B.

2. Call setter methods to link objects:
   - The Spring container then calls the setter method `setOb()` of object `a1`, passing the object `b1` as an argument. This links object `a1` to object `b1`.
   - Next, the container calls the setter method `setOa()` of object `b1`, passing the object `a1` as an argument. This links object `b1` to object `a1`.

By following these steps, the circular dependency between objects of class A and class B is resolved. Object `a1` has a reference to object `b1`, and object `b1` has a reference to object `a1`. This allows both objects to interact with each other as needed.

It's important to note that this process is handled by the Spring container behind the scenes. As a developer, you don't need to manually perform these steps. Instead, you define the dependencies using annotations or XML configuration, and the container takes care of creating and linking the objects correctly.

By utilizing constructor injection and proxies, Spring ensures that circular dependencies are resolved effectively, allowing the application to function without any issues related to the circular references between objects.


<br/>
<br/>
<br/>



# Here's the breakdown of each class code and its explanation:

1. `Employee` Class:
```java
package com.app.shivam;

public class Employee {
    private String name;
    private Project pob;
    
    public Employee() {
        super();
        System.out.println("EMPLOYEE OBJ");
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Project getPob() {
        return pob;
    }
    
    public void setPob(Project pob) {
        this.pob = pob;
        System.out.println("EMPLOYEE.setProject ");
    }
    
    @Override
    public String toString() {
        return "Employee [name=" + name + ", pob=" + pob + "]";
    }
}
```

Explanation:
- The `Employee` class represents an employee with a name and a project.
- It has a default constructor that prints a message when an `Employee` object is created.
- The `name` and `pob` (project object) properties are defined with their respective getter and setter methods.
- The `setPob()` method is responsible for injecting the `Project` object into the `Employee` class. It also includes a print statement to indicate when this method is called.
- The `toString()` method is overridden to provide a string representation of the `Employee` object.

2. `Project` Class:
```java
package com.app.shivam;

public class Project {
    private String pcode;
    private Employee eob;
    
    public Project() {
        super();
        System.out.println("PROJECT OBJ");
    }
    
    public String getPcode() {
        return pcode;
    }
    
    public void setPcode(String pcode) {
        this.pcode = pcode;
    }
    
    public Employee getEob() {
        return eob;
    }
    
    public void setEob(Employee eob) {
        this.eob = eob;
        System.out.println("PROJECT.setEmployee ");
    }
    
    @Override
    public String toString() {
        return "Project [pcode=" + pcode + ", eob=" + eob + "]";
    }
}
```

Explanation:
- The `Project` class represents a project with a project code and an employee assigned to it.
- It has a default constructor that prints a message when a `Project` object is created.
- The `pcode` and `eob` (employee object) properties are defined with their respective getter and setter methods.
- The `setEob()` method is responsible for injecting the `Employee` object into the `Project` class. It also includes a print statement to indicate when this method is called.
- The `toString()` method is overridden to provide a string representation of the `Project` object.

3. XML Configuration:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans 
        http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="e1" class="com.app.shivam.Employee">
        <property name="name" value="RAJU" />
        <property name="pob">
            <ref bean="p1" />
        </property>
    </bean>
    
    <bean id="p1" class="com.app.shivam.Project">
        <property name="pcode" value="HTED" />
        <property name

="eob">
            <ref bean="e1" />
        </property>
    </bean>
</beans>
```

Explanation:
- The XML configuration file (`config.xml`) defines the beans and their dependencies.
- The `<bean>` tag is used to define the `Employee` bean (`e1`) and the `Project` bean (`p1`).
- Inside the `Employee` bean definition, the `name` property is set to "RAJU", and the `pob` property is set using the `<ref>` tag, which refers to the `Project` bean (`p1`).
- Inside the `Project` bean definition, the `pcode` property is set to "HTED", and the `eob` property is set using the `<ref>` tag, which refers to the `Employee` bean (`e1`).

4. Test Class:
```java
package com.app.shivam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("config.xml");
        Employee e = ac.getBean("e1", Employee.class);
        System.out.println(e);
    }
}
```

Explanation:
- The `Test` class is used to test the functionality of the Spring container and the circular dependency resolution.
- It initializes the Spring container by creating an `ApplicationContext` using the `ClassPathXmlApplicationContext` class and providing the `config.xml` file as the configuration.
- The `Employee` bean (`e1`) is retrieved from the container using the `getBean()` method, specifying the bean id and the class type.
- The `toString()` method is called on the `Employee` object (`e`) to print its details.

When the `Test` class is executed, the Spring container creates the `Employee` and `Project` objects using their default constructors. Then, it sets the dependencies by calling the respective setter methods (`setPob()` and `setEob()`). The circular dependency is resolved successfully, and the output will show the details of the `Employee` object, including the linked `Project` object.

The provided code demonstrates a circular dependency between the `Employee` and `Project` classes. Here's how the circular dependency is handled by the Spring container:

1. `Employee` Class:
   - The `Employee` class has a property `pob` of type `Project`.
   - The `setPob()` method is used to inject the `Project` object into the `Employee` class.
   - In the `setPob()` method, a print statement is added to indicate when the method is called.

2. `Project` Class:
   - The `Project` class has a property `eob` of type `Employee`.
   - The `setEob()` method is used to inject the `Employee` object into the `Project` class.
   - In the `setEob()` method, a print statement is added to indicate when the method is called.

3. XML Configuration:
   - In the `config.xml` file, the circular dependency is resolved by using `<ref>` tags.
   - The `Employee` bean (`e1`) is defined first and has a property `pob` that references the `Project` bean (`p1`).
   - The `Project` bean (`p1`) is defined second and has a property `eob` that references the `Employee` bean (`e1`).

4. Test Class:
   - In the `Test` class, the Spring container is initialized with the `config.xml` file.
   - The `Employee` bean (`e1`) is retrieved from the container using the `getBean()` method.
   - The `toString()` method is called on the `Employee` object to print its details.

When the `Test` class is executed, the Spring container handles the circular dependency between `Employee` and `Project` objects. It follows the steps mentioned earlier, creating the objects and linking them by calling the setter methods. The output will show that the circular dependency is successfully resolved, and the objects are properly linked.

Note that circular dependencies should be used cautiously, as they can lead to potential design issues. However, if required, Spring's circular dependency resolution mechanism helps handle such scenarios.


Modifying the `toString` method alone will not resolve the circular dependency between `Employee` and `Project` classes. The stack overflow error occurs during the object creation phase, before the `toString` method is called.

However, modifying the `toString` method can help to avoid the stack overflow error when printing the objects for debugging purposes. By excluding the circularly dependent field from the `toString` method, the printing process will not trigger the infinite loop of object creation.

Here's the modified code with the `toString` method adjusted to avoid the stack overflow error during printing:

1. `Employee` Class:
```java
package com.app.shivam;

public class Employee {
    private String name;
    private Project pob;
    
    public Employee() {
        super();
        System.out.println("EMPLOYEE OBJ");
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Project getPob() {
        return pob;
    }
    
    public void setPob(Project pob) {
        this.pob = pob;
        System.out.println("EMPLOYEE.setProject ");
    }
    
    @Override
    public String toString() {
        return "Employee [name=" + name + "]";
    }
}
```

2. `Project` Class:
```java
package com.app.shivam;

public class Project {
    private String pcode;
    private Employee eob;
    
    public Project() {
        super();
        System.out.println("PROJECT OBJ");
    }
    
    public String getPcode() {
        return pcode;
    }
    
    public void setPcode(String pcode) {
        this.pcode = pcode;
    }
    
    public Employee getEob() {
        return eob;
    }
    
    public void setEob(Employee eob) {
        this.eob = eob;
        System.out.println("PROJECT.setEmployee ");
    }
    
    @Override
    public String toString() {
        return "Project [pcode=" + pcode + "]";
    }
}
```

3. XML Configuration remains the same as before.

By modifying the `toString` methods as shown above, the circular dependency will not cause a stack overflow error during printing. However, it is important to note that the circular dependency itself is not resolved by modifying the `toString` method. It is still necessary to use constructor injection or setter injection to establish the proper dependency relationship between the classes.