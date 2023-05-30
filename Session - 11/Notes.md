@Autowired is used in Spring to automatically wire dependencies between objects in the container. It helps in linking two objects by injecting one object into another.

1. If a single dependency object is found in the container, it will be automatically linked to the current class object. This is the most common scenario.

2. If no dependency object is found, a NoSuchBeanDefinitionException is thrown. To handle this situation, you can use the `@Autowired(required=false)` annotation. This allows the dependency to be null if it is not found in the container.

3. Handling multiple dependency beans:

   3.1 If multiple beans or objects of the same type are found in the container, a NoUniqueBeanDefinitionException is thrown, indicating that multiple beans were found but only one was expected.

   3.2 To resolve this, the container can compare the reference variable name (where the dependency will be injected) with the bean name. If they match, the dependency is linked. If not, a NoSuchBeanDefinitionException is thrown.

   3.3 You can manually select a specific bean for injection using the `@Qualifier("objNameToBeInjected")` annotation. The qualifier value should match the bean name. If the specified bean name is not found, a NoSuchBeanDefinitionException is thrown.

   3.4 Additionally, you can use the `@Primary` annotation on one of the beans to indicate it as the default bean for injection. If multiple beans are found and one has the `@Primary` annotation, it will be given priority. However, you can still override the primary bean selection by using the `@Qualifier` annotation to specify a different bean.

It's important to note that `@Autowired` can be used with constructor injection, setter injection, or field injection, depending on the specific use case.

When explaining `@Autowired` to an interviewer, make sure to cover these points and provide clear examples to illustrate each scenario. Emphasize the flexibility and convenience it brings to dependency injection in Spring applications.

<br/>
<br/>

Here's an explanation of the three types of dependency injection in Spring using `@Autowired` along with code examples:

1. Constructor Injection:
Constructor injection involves providing dependencies through a class constructor. With `@Autowired`, the Spring container will automatically resolve and inject the dependencies when creating an instance of the class.

Example:
```java
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Other methods...
}
```
In this example, the `UserService` class has a dependency on the `UserRepository` class. The constructor is annotated with `@Autowired`, indicating that the `UserRepository` instance should be injected during object creation.

2. Setter Injection:
Setter injection involves providing dependencies through setter methods in the class. The `@Autowired` annotation can be used on the setter method to enable automatic dependency injection.

Example:
```java
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Other methods...
}
```
In this example, the `ProductService` class has a dependency on the `ProductRepository` class. The setter method `setProductRepository` is annotated with `@Autowired`, indicating that the `ProductRepository` instance should be injected by the Spring container.

3. Field Injection:
Field injection involves directly injecting dependencies into class fields using the `@Autowired` annotation. This approach does not require explicit setter or constructor methods.

Example:
```java
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    // Other methods...
}
```
In this example, the `OrderService` class has a field `orderRepository`, which is annotated with `@Autowired`. The Spring container will inject the `OrderRepository` instance directly into the field.

It's worth noting that while field injection is convenient, it can make testing and mocking dependencies more challenging compared to constructor or setter injection.

When explaining these types of dependency injection to an interviewer, emphasize the flexibility they offer in managing dependencies and how `@Autowired` simplifies the process by automatically wiring the dependencies for you.

<br/>
<br/>
<br/>

# In Spring, there are two different concepts: Java Bean and `@Bean`.

Java Bean:
A Java Bean is a plain Java class that adheres to specific conventions. It typically has private fields, public getter and setter methods (accessors and mutators), a default no-argument constructor, and implements the `Serializable` interface. Java Beans are used to encapsulate data and provide access to it through standardized methods.

Example of a Java Bean:
```java
public class Person {
    private String name;
    private int age;
    
    // Constructors, getters, and setters
    
    // Default no-argument constructor
    public Person() {
    }
    
    // Getters and setters for name and age fields
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
```

`@Bean`:
`@Bean` is an annotation provided by the Spring Framework that is used to declare a method as a bean definition method. It is typically used within a configuration class (`@Configuration`) to define and configure beans explicitly. When a method is annotated with `@Bean`, the return value of that method is registered as a bean within the Spring application context.

Example of `@Bean` declaration:
```java
@Configuration
public class AppConfig {
    
    @Bean
    public Person personBean() {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);
        return person;
    }
}
```

In the above example, the `personBean()` method is annotated with `@Bean`, indicating that it will create and configure a `Person` object. The method returns the created `Person` instance, which will be registered as a bean in the Spring container.

Differences between Java Bean and `@Bean`:
1. Java Bean is a concept referring to a class that adheres to certain conventions, whereas `@Bean` is an annotation used to declare a method as a bean definition method.
2. Java Beans are typically used for encapsulating data and providing access to it, while `@Bean` is used for explicitly declaring and configuring beans within the Spring application context.
3. Java Beans have conventions for getter and setter methods, while `@Bean` methods can perform custom logic to create and configure the bean.
4. Java Beans can be used with or without the Spring Framework, whereas `@Bean` is specific to Spring and is used for managing dependencies and creating objects within the Spring container.

When explaining these concepts to an interviewer, you can highlight the distinction between Java Beans and `@Bean` annotations, emphasizing that Java Beans are a general concept for encapsulating data, while `@Bean` is a Spring-specific annotation used for bean declaration and configuration within the Spring framework.

<br/>
<br/>
<br/>

#  Here is the code with detailed explanations:

1. Spring Bean:

```java
package com.app;

public class MyRepository {
    private String code;

    public MyRepository() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MyRepository [code=" + code + "]";
    }
}
```

The `MyRepository` class represents a simple Spring bean. It has a private `code` field, along with getter and setter methods. This class is a plain Java class and does not have any Spring-specific annotations.

2. Spring Component:

```java
package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {
    @Autowired
    private MyRepository repo;

    @Override
    public String toString() {
        return "MyService [repo=" + repo + "]";
    }
}
```

The `MyService` class is annotated with `@Component`, which makes it a Spring component. It is another Spring bean that uses dependency injection. It has a field `repo` of type `MyRepository`, which is annotated with `@Autowired`. This allows Spring to automatically inject an instance of `MyRepository` into the `repo` field.

3. Spring Config class:

```java
package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.app")
public class MyAppConfig {
    @Bean
    @Primary
    public MyRepository m1() {
        MyRepository m1 = new MyRepository();
        m1.setCode("A");
        return m1;
    }

    @Bean
    public MyRepository m2() {
        MyRepository m2 = new MyRepository();
        m2.setCode("B");
        return m2;
    }
}
```

The `MyAppConfig` class is annotated with `@Configuration`, indicating that it is a Spring configuration class. It is responsible for defining and configuring beans. In this example, it defines two `MyRepository` beans: `m1` and `m2`. The `m1` bean is marked with `@Primary`, which means it is the primary bean when multiple beans of the same type are present. The `m2` bean does not have any additional annotations. The `@ComponentScan` annotation is used to scan and automatically register beans within the specified package.

4. Test class:

```java
package com.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MyAppConfig.class);
        Object ob = ac.getBean("myService");
        System.out.println(ob);
    }
}
```

The `Test` class is the entry point of the application. It creates an instance of `AnnotationConfigApplicationContext` and passes `MyAppConfig.class` as an argument, which represents the Spring configuration class. It then retrieves the bean with the name "myService" from the application context and prints its string representation.

In summary, the example demonstrates how Spring manages beans using annotations. The `MyRepository` and `MyService` classes are Spring beans, and their dependencies are automatically injected by Spring. The `MyAppConfig` class defines and configures the beans, and the `Test` class showcases how to use the Spring application context to retrieve and utilize the beans.

<br/>
<br/>
<br/>

# Spring Bean Scope

Scope in the context of Spring refers to the lifespan and accessibility of a bean managed by the Spring container. The scope determines how long an object remains in memory and how it can be accessed by other parts of the application. Spring provides several built-in scopes, each serving a specific purpose. Let's explore them in more detail:

1. Singleton (default scope):
   - Only one instance of the bean is created by the Spring container per configuration.
   - It is the default scope if no other scope is specified.
   - All requests for the bean will return the same instance.
   - Example:
     ```xml
     <bean id="myBean" class="com.app.MyBean" />
     ```
     ```java
     @Bean
     public MyBean myBean() {
         return new MyBean();
     }
     ```
     ```java
     @Component
     class MyBean { }
     ```

2. Prototype:
   - A new instance of the bean is created each time it is requested.
   - It provides a unique instance per request, ensuring independence.
   - Example:
     ```xml
     <bean id="myBean" class="com.app.MyBean" scope="prototype" />
     ```
     ```java
     @Bean
     @Scope("prototype")
     public MyBean myBean() {
         return new MyBean();
     }
     ```

3. Request (Web application scope):
   - A new instance of the bean is created for each HTTP request.
   - It is used in web applications and associates the bean's lifecycle with the current HTTP request.
   - Example:
     ```xml
     <bean id="myBean" class="com.app.MyBean" scope="request" />
     ```

4. Session (Web application scope):
   - A new instance of the bean is created for each user session.
   - It is used in web applications and associates the bean's lifecycle with the user session.
   - Example:
     ```xml
     <bean id="myBean" class="com.app.MyBean" scope="session" />
     ```

5. Application/Context (Web application scope):
   - Only one instance of the bean is created for the entire application context.
   - It is used in web applications and associates the bean's lifecycle with the application context.
   - Example:
     ```xml
     <bean id="myBean" class="com.app.MyBean" scope="application" />
     ```

To specify the scope in different configurations, you can use:
- XML Config: Use the `scope` attribute within the `<bean>` tag.
- Java Config: Use the `@Scope` annotation along with `@Bean`.
- Annotation Config: Use the `@Scope` annotation on the class level.

It's important to choose the appropriate scope for your beans based on their purpose and usage in the application.

**Q) Are singleton scope and singleton class the same?**

A) No, they are not the same.

- Singleton Class: Singleton class is a design pattern that ensures that only one instance of a class is created and provides a global point of access to that instance. It is a software design principle and can be implemented in any programming language. The responsibility of implementing the singleton pattern lies with the programmer, and it is not specific to the Spring framework.

- Singleton Scope: Singleton scope, on the other hand, is a scope provided by the Spring framework. It is one of the bean scopes available in Spring's container. When a bean is defined with singleton scope, the Spring container creates and manages a single instance of that bean for the entire lifecycle of the application. All subsequent requests for the bean will return the same instance.

**Q) Which one is the default scope in the Spring framework?**

A) The default scope in the Spring framework is singleton. If no explicit scope is specified for a bean, it is assumed to have a singleton scope. This means that by default, Spring creates a single instance of the bean and returns that instance for all requests for that bean.

It's important to differentiate between the singleton pattern and the singleton scope in Spring. The singleton pattern is a design concept, while the singleton scope is a specific behavior provided by the Spring framework for managing bean instances.

<br/>
<br/>
<br/>

# Here are code samples demonstrating the different scopes in Spring:

1. XML Config:
```xml
<bean id="myBean" class="com.example.MyBean" scope="singleton">
   <!-- Bean configuration -->
</bean>
```
In this XML configuration, the `scope` attribute is set to "singleton" to specify the singleton scope for the bean.

2. Java Config:
```java
@Configuration
public class MyAppConfig {
   @Bean
   @Scope("prototype")
   public MyBean myBean() {
      // Bean configuration
      return new MyBean();
   }
}
```
In this Java-based configuration, the `@Scope` annotation is used to specify the scope of the bean. In this example, the scope is set to "prototype" to create a new instance of the bean for each request.

3. Annotation Config:
```java
@Component
@Scope("request")
public class MyBean {
   // Bean implementation
}
```
In this annotation-based configuration, the `@Scope` annotation is applied directly to the class declaration. Here, the scope is set to "request", indicating that a new instance of the bean will be created for each HTTP request in a web application.

These code samples demonstrate how to define the scope of a bean in different ways using XML configuration, Java-based configuration, and annotation-based configuration. You can replace the placeholders with the appropriate values for your specific application.