# **Here's an explanation of Spring bean scopes with code snippets:**

**Singleton Scope:**
```java
@Component
@Scope("singleton")
public class SingletonBean {
    // Bean implementation
}
```
The above code snippet defines a singleton-scoped bean using the `@Scope` annotation. The `singleton` scope is the default scope, so specifying it explicitly is not necessary.

**Prototype Scope:**
```java
@Component
@Scope("prototype")
public class PrototypeBean {
    // Bean implementation
}
```
The code snippet above defines a prototype-scoped bean using the `@Scope` annotation. Each time the bean is requested from the container, a new instance will be created.

**Request Scope (Web App Only):**
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedBean {
    // Bean implementation
}
```
The above code snippet demonstrates a bean with request scope in a web application. The `@Scope` annotation specifies the scope as `WebApplicationContext.SCOPE_REQUEST`, and the `proxyMode` attribute is set to `ScopedProxyMode.TARGET_CLASS`. This proxy mode is required to manage the request scope correctly.

**Session Scope (Web App Only):**
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean {
    // Bean implementation
}
```
The code snippet above defines a bean with session scope in a web application. The `@Scope` annotation specifies the scope as `WebApplicationContext.SCOPE_SESSION`, and the `proxyMode` attribute is set to `ScopedProxyMode.TARGET_CLASS` to handle the session scope correctly.

**XML Configuration:**
```xml
<bean id="singletonBean" class="com.example.SingletonBean" scope="singleton" />
<bean id="prototypeBean" class="com.example.PrototypeBean" scope="prototype" />
```
In XML configuration, you can define bean scopes using the `scope` attribute within the `<bean>` element.

**Java Configuration:**
```java
@Configuration
public class AppConfig {
    @Bean
    @Scope("singleton")
    public SingletonBean singletonBean() {
        return new SingletonBean();
    }

    @Bean
    @Scope("prototype")
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }
}
```
In Java configuration, you can specify the bean scopes using the `@Scope` annotation on the bean methods within a configuration class annotated with `@Configuration`. The desired scope is passed as a parameter to the `@Scope` annotation.

By understanding and using these different bean scopes, you can manage the lifecycle and availability of your Spring beans effectively.

<br/>
<br/>

# **XML Based example with code explanation:**

**1. pom.xml**
```xml
<properties>
	<maven.compiler.source>11</maven.compiler.source>
	<maven.compiler.target>11</maven.compiler.target>
</properties>

<dependencies>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-context</artifactId>
		<version>5.3.23</version>
	</dependency>
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<version>1.18.24</version>
	</dependency>
</dependencies>
```

Explanation:
- The `pom.xml` file is a Maven configuration file.
- It includes the necessary dependencies for the Spring framework and Lombok.
- The `spring-context` dependency is required for Spring bean configuration and application context management.
- The `lombok` dependency provides annotations like `@Getter`, `@Setter`, and `@ToString` to automatically generate boilerplate code.

**2. Product Bean**
```java
package com.app.shivam.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
	private Integer pid;
	private String pcode;
}
```

Explanation:
- The `Product` class is a simple POJO (Plain Old Java Object) representing a product.
- It includes private fields `pid` (product ID) and `pcode` (product code).
- Lombok annotations `@Getter`, `@Setter`, and `@ToString` are used to automatically generate getter, setter, and `toString()` methods for the class.

**3. Spring Config File (config.xml)**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans 
        http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="pobj" class="com.app.shivam.bean.Product" scope="singleton">
		<property name="pid" value="101"/>
		<property name="pcode" value="ABC"/>
	</bean>
</beans>
```

Explanation:
- The `config.xml` file is an XML-based configuration file for Spring beans.
- It begins with the `<beans>` root element and includes the necessary XML namespaces and schema locations.
- Inside the `<bean>` tag, we define a bean with the id "pobj" and specify its class as "com.app.shivam.bean.Product".
- The bean is scoped as "singleton" using the `scope` attribute, meaning only one instance of the bean will be created.
- Two `<property>` tags are used to set the values of the `pid` and `pcode` properties of the `Product` bean.

**4. Test Class**
```java
package com.app.shivam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.shivam.bean.Product;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("config.xml");
		Product p1 = ac.getBean("pobj", Product.class);
		Product p2 = ac.getBean("pobj", Product.class);

		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p1 == p2);
	}
}
```

Explanation:


- The `Test` class contains the main method for running the application.
- We create an `ApplicationContext` using `ClassPathXmlApplicationContext` and provide the path to the `config.xml` file.
- We retrieve two instances of the `Product` bean from the application context using the `getBean` method. Both instances are assigned to `p1` and `p2`.
- We print the hash codes of `p1` and `p2` to verify that they point to the same object.
- Finally, we compare `p1` and `p2` using the equality operator (`==`) and observe that they are indeed the same object.

**Conclusion:**
This code demonstrates XML-based Spring bean configuration. The `config.xml` file defines a singleton-scoped `Product` bean with specific property values. The `Test` class retrieves instances of the bean from the application context and verifies that they refer to the same object by comparing their hash codes and using the equality operator.

<br/>
<br/>

# **Java/Annotation config example with code explanation**


**1. Spring Bean**
```java
package com.app.shivam.bean;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.ToString;

@ToString
@Component("tok")
@Scope("prototype")
public class Token {

	private int token;

	public Token() {
		super();
		token = new Random().nextInt(9999);
		System.out.println("TOKEN IS CREATED");
	}
}
```

Explanation:
- The `Token` class is a Spring bean that represents a token.
- It is annotated with `@Component` to indicate that it is a component managed by Spring.
- The `@Component("tok")` annotation provides the bean with a unique identifier "tok".
- The `@Scope("prototype")` annotation specifies that a new instance of the bean should be created each time it is requested.
- The `token` field holds the value of the token, which is randomly generated during object creation.
- The `toString()` method is overridden to provide a string representation of the token.

**2. Spring Config class**
```java
package com.app.shivam.config;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.app.shivam")
public class AppConfig {

}
```

Explanation:
- The `AppConfig` class is a Spring configuration class.
- It is annotated with `@ComponentScan("com.app.shivam")`, which enables component scanning in the specified package.
- Component scanning allows Spring to automatically discover and register components, such as beans, within the specified package.

**3. Test Class**
```java
package com.app.shivam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.app.shivam.bean.Token;
import com.app.shivam.config.AppConfig;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		Token t1 = ac.getBean("tok", Token.class);
		System.out.println(t1);
		
		Token t2 = ac.getBean("tok", Token.class);
		System.out.println(t2);
		
		Token t3 = ac.getBean("tok", Token.class);
		System.out.println(t3);
	}
}
```

Explanation:
- The `Test` class contains the main method for running the application.
- We create an `ApplicationContext` using `AnnotationConfigApplicationContext` and provide the `AppConfig` class as a parameter.
- We retrieve three instances of the `Token` bean from the application context using the `getBean` method. All instances are assigned to variables `t1`, `t2`, and `t3`.
- We print the string representations of `t1`, `t2`, and `t3`, which display the generated token values.
- Since the bean scope is set to "prototype", each time we retrieve the bean, a new instance is created, resulting in different token values for each instance.

**Conclusion:**
This code demonstrates Java-based Spring bean configuration using annotations. The `Token` class is annotated with `@Component` and `@Scope` to define it as a prototype-scoped bean. The `AppConfig` class is annotated with `@ComponentScan` to enable component scanning. The `Test` class retrieves multiple instances of the `Token` bean from the application context and verifies that each instance has a different token value, indicating the prototype scope.

<br/>
<br/>

**Q) Can two objects have the same hashcode in Java?**

A) Yes, it is possible for two objects to have the same hashcode in Java. However, it depends on how the `hashCode()` and `equals()` methods are implemented for the objects.

In Java, the `hashCode()` method returns an integer value representing the hashcode of an object. The `equals()` method is used to compare two objects for equality. According to the general contract of these methods, if two objects are equal (as per the `equals()` method), their hashcodes should also be equal. However, it is not a strict requirement for hashcodes to be unique for different objects.

If the `hashCode()` and `equals()` methods are not overridden in a class, the default implementations provided by the `Object` class are used. In this case, each object will have a unique hashcode, even if their content is the same.

To make two objects have the same hashcode, the `hashCode()` method needs to be overridden in a way that produces the same hashcode for objects with equal content. This ensures that two objects that are considered equal based on the `equals()` method will also have the same hashcode.

**Q) Which one is the default scope in Spring?**

A) The default scope in Spring is the singleton scope. When a bean is defined in Spring without explicitly specifying a scope, it is considered to have the singleton scope by default.

In the singleton scope, only a single instance of the bean is created within the container. This single instance is shared by all the components that request the bean. Whenever a component requests the bean, the same instance is returned, ensuring that all components operate on the same shared object.

Singleton scope is suitable for beans that are stateless or have shared state among multiple components. It is the most commonly used scope in Spring because it provides efficiency and consistency by reusing the same object instance.

**Q) How can we get a new object from the container every time?**

A) To get a new object from the container every time, we can use the prototype scope in Spring. The prototype scope ensures that a new instance of the bean is created whenever it is requested from the container.

Unlike the singleton scope, where a single instance is shared, the prototype scope creates a new instance of the bean every time it is requested. Each instance is independent and has its own state, allowing different components to work with separate instances of the bean.

To configure a bean with the prototype scope, we need to explicitly specify the scope as "prototype" either in XML configuration or using annotations. This tells Spring to create a new instance of the bean for every request.

By using the prototype scope, we can obtain fresh object instances from the container whenever needed, allowing greater flexibility and isolation between components.

<br/>
<br/>

**Q) What is EAGER and LAZY Loading in Containers/Servers?**

A) EAGER loading and LAZY loading are two different approaches to object initialization and retrieval in containers or servers.

1. EAGER Loading:
   In EAGER loading, object initialization and creation occur at the same time as the container or server starts up. This means that all necessary objects are created and loaded into memory before any request or access is made to the application.

   EAGER loading ensures that all required objects are immediately available for use, eliminating any delay or overhead in object creation during runtime. It is suitable for scenarios where the application requires immediate access to all objects or when objects have dependencies that need to be resolved upfront.

   However, EAGER loading can lead to increased startup time and resource consumption, especially if the application has a large number of objects or complex object graphs. It may not be efficient if certain objects are rarely or selectively used during runtime.

2. LAZY Loading:
   In LAZY loading, object initialization and creation are deferred until the first request or access is made to the application. Instead of loading all objects upfront, the container or server creates objects on-demand, as they are needed during runtime.

   LAZY loading helps optimize resource usage and improve startup time by only creating objects that are actually required by the application. It is particularly useful when dealing with large applications or objects with expensive initialization processes.

   With LAZY loading, objects are instantiated and loaded into memory when they are accessed for the first time. Subsequent requests for the same object reuse the already created instance. This approach allows for more efficient memory usage and better performance, especially in scenarios where not all objects are needed at the start or the application has varying usage patterns.

   It's important to note that the concept of EAGER and LAZY loading is not limited to containers or servers. It is also applicable in various other contexts, such as database query execution or ORM frameworks, where objects are fetched from the database only when needed (LAZY loading) or retrieved upfront along with the initial query (EAGER loading).

In summary, EAGER loading initializes and creates objects at the same time as the container or server starts up, while LAZY loading defers object creation until the first request or access is made. The choice between EAGER and LAZY loading depends on the specific requirements and usage patterns of the application.

<br/>
<br/>

**Eager vs. Lazy Loading in Containers/Servers:**

1. Making Object Creation Lazy:
   When object creation is made lazy, it means that the objects are not created and initialized immediately at the startup of the application. Instead, they are created on-demand, typically when they are first accessed or requested by the application. This approach helps reduce the startup time of the application and conserves memory since only the necessary objects are instantiated.

2. Default Eager Loading for Singleton Scope:
   In Spring, the default scope for beans is singleton, which means that by default, the objects are eagerly loaded. Eager loading ensures that a single instance of the object is created at the startup of the application and is available throughout its lifecycle. This default behavior is suitable for objects that are commonly shared and accessed by multiple components or requests.

3. Default Lazy Loading for Prototype Scope:
   The prototype scope in Spring is designed for objects that need to be created on-demand and do not have shared state. Unlike singleton scope, prototype-scoped objects are lazily loaded by default. Each time a prototype bean is requested, a new instance of the object is created. This lazy loading behavior is beneficial when you want to conserve resources by creating objects only when needed.

4. Using @Lazy Annotation:
   Spring provides the `@Lazy` annotation to explicitly make a bean lazy, regardless of its scope. By annotating a bean with `@Lazy`, you instruct the container to create and initialize the bean only when it is first accessed. This annotation can be used with any scope, including singleton and prototype.

Example:
```java
@Component("tok")
@Lazy
public class Token {
   // Bean code
}
```

In the above example, the `Token` bean is marked with `@Lazy`, indicating that its initialization should be deferred until the bean is requested for the first time. This can help improve application startup time and memory consumption, especially if the `Token` object is not immediately required.

5. Unable to Make Lazy to Eager:
   It's important to note that once a bean is configured for lazy loading, it cannot be changed to eager loading. Once a lazy-initialized bean is created and accessed, its instance remains in memory, and subsequent requests for the bean reuse the same instance. Switching from lazy to eager loading would require destroying and recreating the bean, which is not supported by Spring's default behavior.

In summary, making object creation lazy reduces application startup time and memory usage. Singleton-scoped beans are eagerly loaded by default, while prototype-scoped beans are lazily loaded. The `@Lazy` annotation can be used to explicitly make a bean lazy, regardless of its scope. However, once a bean is marked as lazy, it cannot be changed to eager loading without destroying and recreating the bean.