# Spring scope in detail along with code snippets. Let's go through each scope one by one:

1. Singleton Scope:
The singleton scope is the default scope in Spring and it creates a single instance of a bean per container. The bean instance created in the singleton scope is shared across multiple requests and is cached in memory. This means that whenever a bean is requested, the same instance is returned if it already exists in the container.

```java
@Component
@Scope("singleton")
public class MySingletonBean {
    // Bean code here
}
```

2. Prototype Scope:
The prototype scope creates a new instance of a bean every time it is requested from the container. Unlike the singleton scope, each instance of a bean in the prototype scope is independent and doesn't share state with other instances.

```java
@Component
@Scope("prototype")
public class MyPrototypeBean {
    // Bean code here
}
```

3. Request Scope (Spring Web):
The request scope is specific to web applications and it creates a new instance of a bean for each HTTP request. The bean instance is created when the request is received and is destroyed when the response is sent back.

```java
@Controller
@Scope("request")
public class MyRequestScopedController {
    // Controller code here
}
```

4. Session Scope (Spring Web):
The session scope is also specific to web applications and it creates a new instance of a bean for each user session. The bean instance is created when a user logs in and is destroyed when the user logs out.

```java
@Controller
@Scope("session")
public class MySessionScopedController {
    // Controller code here
}
```

To use these scopes, you need to configure the appropriate scope for your beans in the Spring configuration file (e.g., XML or Java configuration). Here's an example of how you can configure scopes using Java configuration:

```java
@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    public MySingletonBean singletonBean() {
        return new MySingletonBean();
    }

    @Bean
    @Scope("prototype")
    public MyPrototypeBean prototypeBean() {
        return new MyPrototypeBean();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MyRequestScopedController requestScopedController() {
        return new MyRequestScopedController();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MySessionScopedController sessionScopedController() {
        return new MySessionScopedController();
    }
}
```

In the case of request and session scopes, you may notice the use of `ScopedProxyMode.TARGET_CLASS` to handle the proxying of the scoped beans. This is necessary to ensure proper handling of the scoped instances in the web context.

Remember, the choice of scope depends on your application's requirements. Singleton scope is suitable for stateless beans, while prototype, request, and session scopes are useful for maintaining stateful instances specific to each request or user session.

<br/>
<br/>
<br/>

# Notes regarding Spring scopes:

1. Scope names are reserved words and they are case-sensitive: When specifying the scope for a bean in Spring, it's important to use the correct scope names, as they are case-sensitive. The commonly used scope names are `singleton`, `prototype`, `request`, and `session`.

2. XML Configuration: If you're using XML configuration in Spring, you need to provide the scope attribute within the `<bean>` tag for each bean. For example:

```xml
<bean id="myBean" class="com.example.MyBean" scope="singleton">
    <!-- Bean configuration here -->
</bean>
```

3. Annotation/Java Config: If you're using annotations or Java configuration in Spring, you can use the `@Scope` annotation to specify the scope for a bean. For example:

```java
@Component
@Scope("prototype")
public class MyBean {
    // Bean code here
}
```

4. Scope Definition: It's important to note that the scope definition should be provided only once for each bean configuration. Providing multiple scope definitions for the same bean can lead to unexpected behavior.

5. Choosing the Right Scope: The choice of scope depends on your application's requirements. Here's a brief overview of the commonly used scopes:

   - Singleton: Creates a single instance of the bean per container. It's suitable for stateless beans that can be shared across multiple requests.
   - Prototype: Creates a new instance of the bean every time it is requested. It's suitable for stateful beans that need to maintain independent state.
   - Request (Spring Web): Creates a new instance of the bean for each HTTP request. It's suitable for beans that need to maintain request-specific data.
   - Session (Spring Web): Creates a new instance of the bean for each user session. It's suitable for beans that need to maintain session-specific data.

Remember to choose the appropriate scope based on the lifecycle and state requirements of your beans.

Overall, understanding and properly configuring Spring scopes is crucial for managing the lifetime and accessibility of your beans within the Spring container.

<br/>
<br/>
<br/>

# XML EXAMPLE -- Spring Bean Scope 

Sure! Let's go through the provided example step by step and explain it in detail.

1. Spring Bean (Token class):
The `Token` class is a simple POJO (Plain Old Java Object) representing a token. It has a `code` property and corresponding getter and setter methods.

```java
package com.app.shivam;

public class Token {
    private String code;

    public Token() {
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
        return "Token [code=" + code + "]";
    }
}
```

2. Spring XML Config File (beans.xml):
The XML configuration file defines the Spring beans and their scopes. In this example, the `Token` bean is defined with a prototype scope (`scope="prototype"`) and an initial value for the `code` property using the `<property>` tag.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean 
        id="t1" 
        class="com.app.shivam.Token"
        scope="prototype">
        <property name="code" value="SAMPLE" />
    </bean>
</beans>
```

3. Test Class (Test class):
The `Test` class demonstrates how to retrieve beans from the Spring container using the ApplicationContext. It retrieves two instances of the `Token` bean and prints their hash codes.

```java
package com.app.shivam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("/myconfig/beans.xml");
        
        // Reading object from the container
        Token tob1 = ac.getBean("t1", Token.class);
        System.out.println(tob1.hashCode());

        // Reading object from the container
        Token tob2 = ac.getBean("t1", Token.class);
        System.out.println(tob2.hashCode());
    }
}
```

In the `main` method, an `ApplicationContext` is created using the `ClassPathXmlApplicationContext` and it loads the XML configuration file (`beans.xml`). Then, the `Token` bean is retrieved twice using the `getBean` method, passing the bean id ("t1") and the bean class (`Token.class`) as arguments. The `hashCode` method is called on each instance to print their hash codes.

Since the scope of the `Token` bean is defined as prototype, a new instance is created each time `getBean` is called. Hence, you will see different hash codes printed for `tob1` and `tob2`.

Overall, this example demonstrates how to define a bean with the prototype scope in Spring XML configuration and retrieve multiple instances of the bean from the container.

<br/>
<br/>
<br/>

# Spring Bean Scope Annotation Config

Let's go through the provided example of Spring bean scope using annotations in detail.

1. Spring Bean (Token class):
The `Token` class is a simple POJO (Plain Old Java Object) representing a token. It is annotated with `@Component` to indicate that it is a Spring-managed bean. The `@Scope` annotation is used to define the scope of the bean. In this example, the scope is set to prototype using `@Scope("prototype")`. The `code` property is generated randomly in the constructor.

```java
package com.app.shivam;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("t1")
@Scope("prototype")
public class Token {
    private int code;

    public Token() {
        super();
        code = Math.abs(new Random().nextInt());
    }

    @Override
    public String toString() {
        return "Token [code=" + code + "]";
    }
}
```

2. Spring Config Class (AppConfig class):
The `AppConfig` class is the Spring configuration class, annotated with `@ComponentScan` to specify the package to scan for Spring components. In this example, it scans the `com.app.shivam` package for components.

```java
package com.app.shivam;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.app.shivam")
public class AppConfig {
}
```

3. Test Class (Test class):
The `Test` class demonstrates how to retrieve beans from the Spring container using the `ApplicationContext` created from the `AnnotationConfigApplicationContext`. It retrieves two instances of the `Token` bean and prints their details.

```java
package com.app.shivam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        
        Token ta1 = ac.getBean("t1", Token.class);
        System.out.println(ta1);
        
        Token ta2 = ac.getBean("t1", Token.class);
        System.out.println(ta2);
    }
}
```

In the `main` method, an `ApplicationContext` is created using `AnnotationConfigApplicationContext` and the `AppConfig` class is provided as the argument. The `getToken` method is used to retrieve instances of the `Token` bean. Since the scope of the `Token` bean is defined as prototype, a new instance is created each time `getBean` is called. Therefore, you will see different instances with different codes printed for `ta1` and `ta2`.

Overall, this example demonstrates how to define a bean with the prototype scope using annotations in Spring. It also shows how to use the `ApplicationContext` to retrieve instances of the bean from the container.

<br/>
<br/>
<br/>

# Spring bean -- Java Config Example

Let's walk through the provided example of Spring bean configuration using Java configuration in detail.

1. Spring Bean (Token class):
The `Token` class is a simple POJO (Plain Old Java Object) representing a token. It has a `code` property that is randomly generated in the constructor. In this example, the `@Component` and `@Scope` annotations are commented out since we're using Java configuration instead.

```java
package com.app.shivam;

import java.util.Random;

public class Token {
    private int code;

    public Token() {
        super();
        code = Math.abs(new Random().nextInt());
    }

    @Override
    public String toString() {
        return "Token [code=" + code + "]";
    }
}
```

2. Spring Config Class (AppConfig class):
The `AppConfig` class is the Spring configuration class, annotated with `@Configuration`. It defines a method, `t1()`, which serves as a factory method for creating the `Token` bean. The `@Bean` annotation indicates that the return value of the method should be registered as a bean. The `@Scope("prototype")` annotation sets the scope of the bean to prototype.

```java
package com.app.shivam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public Token t1() {
        return new Token();
    }
}
```

3. Test Class (Test class):
The `Test` class demonstrates how to retrieve beans from the Spring container using the `ApplicationContext` created from the `AnnotationConfigApplicationContext`. It retrieves two instances of the `Token` bean and prints their details.

```java
package com.app.shivam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        
        Token ta1 = ac.getBean("t1", Token.class);
        System.out.println(ta1);
        
        Token ta2 = ac.getBean("t1", Token.class);
        System.out.println(ta2);
    }
}
```

In the `main` method, an `ApplicationContext` is created using `AnnotationConfigApplicationContext` and the `AppConfig` class is provided as the argument. The `getToken` method is used to retrieve instances of the `Token` bean. Since the scope of the `Token` bean is defined as prototype, a new instance is created each time `getBean` is called. Therefore, you will see different instances with different codes printed for `ta1` and `ta2`.

Overall, this example demonstrates how to define a bean with the prototype scope using Java configuration in Spring. The `@Configuration` annotation marks the class as the configuration class, and the `@Bean` annotation is used to define the bean creation method.

<br/>
<br/>
<br/>

# -------FAQs-------

Let's go through each question one by one and provide detailed explanations along with coding examples where applicable.

1. **What is the default scope in Spring Framework?** <br/>
   Answer: The default scope in Spring Framework is `singleton`. If the scope is not explicitly specified for a bean, it is assumed to be a singleton bean.

2. **Is the Spring container EAGER/LAZY by default in Bean Creation?** <br/>
   Answer: By default, Spring container follows eager loading for singleton beans. It creates singleton beans during the initialization of the container. However, for prototype beans, lazy loading is followed, which means the bean is created only when it is requested using the `getBean` method.

3. **How can we force the container to behave lazily for singleton beans?** <br/>
   Answer: To make a singleton bean behave lazily, we can use the `@Lazy` annotation in conjunction with the bean definition.
   
   Example with annotations:
   ```java
   @Component("t1")
   @Lazy
   public class Token {
      // ...
   }
   ```

   Example with XML configuration:
   ```xml
   <bean id="t1" class="com.app.shivam.Token" scope="singleton" lazy-init="true">
      <!-- ... -->
   </bean>
   ```

4. **When should we go for singleton/prototype?** <br/>
   Answer: The choice between singleton and prototype scope depends on the requirement of the application. Use singleton scope when you want a single instance of the bean throughout the application. Use prototype scope when you need a new instance of the bean each time it is requested.

   Example:
   - Use singleton for beans like database connections or configuration objects that can be shared across the application.
   - Use prototype for beans like user sessions or request-specific objects that need to be unique for each user or request.

5. **When should we go for lazy loading?** <br/>
   Answer: Lazy loading is useful when you want to defer the creation of a bean until it is actually needed, thereby saving memory and improving performance. It is especially beneficial for singleton beans that are not always required immediately upon application startup.

   Example:
   - Use lazy loading for beans like email services or background processing tasks that are triggered on demand rather than at application startup.

6. **How can we convert prototype loading to eager loading?** <br/>
   Answer: It is not possible to convert the prototype scope or lazy loading to eager loading in Spring. Eager loading is the default behavior for singleton beans, and there is no specific annotation or configuration option to change this behavior for prototype-scoped beans.

7. **What is "load-on-startup" in Servlets? Explain eager and lazy loading in Servlets.** <br/>
   Answer: In Servlets, the "load-on-startup" element in the servlet configuration specifies that the servlet should be loaded and initialized when the application starts, rather than waiting for the first request to trigger the initialization. This corresponds to eager loading in the context of Spring.

   On the other hand, lazy loading in Servlets means that the servlet is only loaded and initialized when the first request is made to it. This can help improve application startup time and reduce memory usage if not all servlets are required immediately upon application startup.

8. How can we provide two scopes for the same bean? <br/>
   Answer: It is not possible to provide multiple scopes for the same bean. Each bean definition can have only one scope. If multiple scopes are specified, it will result in an error during application context initialization.

9. **Is the below code valid? How many objects are created? (Using both Java and Annotation Config at the same time)** <br/>

   ```java
   @Component("oa")
   public class A { 
      @Value(20)
      int code;
   }
   ```
   ```java


   @Configuration
   public class AppConfig {
      @Bean
      public A oa() {
         A a1 = new A();
         a1.setCode(10);
         return a1;
      }
   }
   ```

   Answer: The code is valid, but it will result in creating two separate instances of the `A` bean. The first instance is created by the `@Component` annotation, and the second instance is created by the `@Bean` method in the `AppConfig` class. The `@Value(20)` annotation on the `code` field is not effective since the `A` bean instances are created manually.

10. **Try the above code with different scopes (singleton/prototype).** <br/>

   Answer:
   To apply scopes to the bean instances, you can modify the `@Component` annotation or the `@Bean` method in `AppConfig` class as follows:

   Example with singleton scope:
   ```java
   @Component("oa")
   @Scope("singleton")
   public class A {
      // ...
   }
   ```
   ```java
   @Configuration
   public class AppConfig {
      @Bean
      @Scope("singleton")
      public A oa() {
         // ...
      }
   }
   ```

   Example with prototype scope:
   ```java
   @Component("oa")
   @Scope("prototype")
   public class A {
      // ...
   }
   ```
   ```java
   @Configuration
   public class AppConfig {
      @Bean
      @Scope("prototype")
      public A oa() {
         // ...
      }
   }
   ```

