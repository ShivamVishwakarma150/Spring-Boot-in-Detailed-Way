# `@Autowired` Annotation

@Autowired is an annotation used in Java's Spring framework to automatically wire or link objects together by injecting dependencies into dependent classes. It helps in achieving inversion of control and loose coupling in an application.

Here's how @Autowired behaves in different scenarios:

1. If exactly one child object (dependency bean) is found in the container:
   - The dependency will be automatically injected or linked to the dependent class.

2. If no child object (dependency bean) is found in the container:
   - By default, an exception is thrown because the dependency is required for the dependent class.
   - However, you can use the @Autowired(required=false) option to indicate that the dependency is optional.
   - In this case, if no matching bean is found, the variable will hold a null value.

3. If multiple child objects (dependency beans) are found in the container:
   - An exception called NoUniqueBeanDefinitionException is thrown because the container cannot determine which bean to inject due to ambiguity.
   - You'll need to provide additional information or use qualifiers to specify the exact bean to be injected.

In summary, @Autowired simplifies the process of wiring dependencies by automatically linking objects together. It ensures that dependencies are properly resolved, but it also provides flexibility for handling scenarios where dependencies are not available or when multiple candidates exist.

<br/>
<br/>
<br/>

## Q: Is there a default package name provided for the basePackage attribute in the `ComponentScan` annotation?
A: No, in Spring applications, there is no default package name given for the basePackage attribute in the @ComponentScan annotation. The programmer is responsible for providing the base package externally during the annotation configuration. 

However, in the case of Spring Boot, there is a default behavior for determining the base package. The package where the main class or the starter class is located is automatically considered as the base package for component scanning. This means that Spring Boot will scan for components starting from the package of the main class.

For example, if the main class is located in the package "com.example.myapp", Spring Boot will scan for components within that package and its sub-packages.

It is important to note that while Spring applications require the programmer to explicitly provide the base package, Spring Boot simplifies this process by setting the default base package based on the location of the main class or the starter class.

<br/>
<br/>

# Important Notes 

Note A:
The @Autowired annotation in Spring internally utilizes a boolean attribute called "required" with a default value of true. This attribute indicates that the child or dependency object being injected is mandatory. If the container cannot find a suitable object for injection, it will throw a NoSuchBeanDefinitionException. This exception signifies that the required dependency bean is not available in the container, and the injection cannot be performed successfully.

Note B:
In certain scenarios, it might be acceptable for a dependency to be optional, and rather than throwing an exception, we can request the container to provide a null value as the default. This can be achieved by specifying the attribute "required" as false when using the @Autowired annotation. By setting required = false, we indicate to the container that the dependency is not mandatory, and if no suitable bean is found, the injected variable will hold a null value instead of throwing an exception.

By using the "required = false" option, we can gracefully handle situations where a dependency may or may not be present, allowing more flexibility in the application's logic. It enables us to write code that can handle both the presence and absence of a particular dependency, depending on the specific requirements of our application.

<br/>
<br/>
<br/>

# Here's the code followed by the explanation:

```java
// MyService.java
package com.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    @Value("SAMPLE DATA")
    private String code;

    @Override
    public String toString() {
        return "MyService [code=" + code + "]";
    }

}
```

```java
// MyController.java
package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.service.MyService;

@Component
public class MyController {

    @Autowired(required = false)
    private MyService service;

    @Override
    public String toString() {
        return "MyController [service=" + service + "]";
    }

}
```

```java
// MyAppConfig.java
package com.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.app")
public class MyAppConfig {

}
```

```java
// TestApp.java
package com.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.app.config.MyAppConfig;
import com.app.controller.MyController;

public class TestApp {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MyAppConfig.class);
        MyController mc = ac.getBean("myController", MyController.class);
        System.out.println(mc);
    }

}
```

Explanation:

1. `MyService` class:
   - This class is annotated with `@Component` to make it a Spring bean.
   - It has a field named `code` which is annotated with `@Value("SAMPLE DATA")`. This annotation sets the value "SAMPLE DATA" to the `code` field.
   - The `toString()` method is overridden to provide a string representation of the `MyService` object.

2. `MyController` class:
   - This class is annotated with `@Component` to make it a Spring bean.
   - It has a field named `service` of type `MyService` which is annotated with `@Autowired(required = false)`. This indicates that the `MyService` dependency is optional, and if no matching bean is found, the `service` field will be set to `null`.
   - The `toString()` method is overridden to provide a string representation of the `MyController` object.

3. `MyAppConfig` class:
   - This class is annotated with `@Configuration` to mark it as the Spring configuration class.
   - It uses `@ComponentScan` to enable component scanning in the package "com.app". This allows Spring to scan for annotated beans in that package.

4. `TestApp` class:
   - This class is used to test the Spring configuration and dependency injection.
   - It creates an instance of `AnnotationConfigApplicationContext` and passes `MyAppConfig.class` as the configuration class. This initializes the Spring container with the specified configuration.
   - The `getBean` method is called on the `ApplicationContext` to retrieve an instance of `MyController` using the bean name "myController" and the class type `MyController.class`.
   - The `toString()` method is called on the `MyController` instance, and the resulting string representation is printed to the console.

The code demonstrates the usage of `@Autowired(required = false)` in the `MyController` class, making the dependency on `MyService` optional. If a matching `MyService` bean is found, it will be injected into the `service` field. If no matching bean is found, the `service` field will be set to `null`.

<br/>
<br/>
<br/>

# `Spring's` lifecycle methods:

In Spring, lifecycle methods are special methods that are automatically called by the Spring container or framework during the lifecycle of a bean. These methods allow you to perform initialization and cleanup tasks for your beans without the need for manual invocation.

In Advanced Java (Servlets), there are three lifecycle methods that are mandatory for servlets:

1. **init()**:
   - The init() method is called by the container when the servlet is first created.
   - It is used to perform any one-time initialization tasks required by the servlet, such as opening a database connection or loading configuration data.
   - The init() method is invoked before any client requests are serviced by the servlet.

2. **service()**:
   - The service() method is called by the container for each client request to the servlet.
   - It is responsible for processing the client's request and generating the appropriate response.
   - The service() method typically delegates the request handling to other methods within the servlet, such as doGet() or doPost(), based on the HTTP request method.

3. **destroy()**:
   - The destroy() method is called by the container when the servlet is about to be removed or the container is being shut down.
   - It allows the servlet to perform any necessary cleanup tasks, such as closing database connections or releasing resources held by the servlet.
   - The destroy() method is invoked after all client requests have been serviced and before the servlet is taken out of service.

In contrast, Spring provides two optional lifecycle methods that can be implemented in your beans:

1. **Init method**:
   - The init method is called by the Spring container after the bean has been instantiated and its dependencies have been injected.
   - It allows you to perform any initialization tasks or setup operations that your bean requires.
   - To define an init method in your bean, you can use the `@PostConstruct` annotation on a method or implement the `InitializingBean` interface and override the `afterPropertiesSet()` method.
   - The init method is commonly used to connect to external resources, initialize data structures, or set up necessary configurations.

2. **Destroy method**:
   - The destroy method is called by the Spring container when the bean is being removed from the container or when the container itself is being shut down.
   - It allows you to perform any cleanup or resource release operations before the bean is destroyed.
   - To define a destroy method in your bean, you can use the `@PreDestroy` annotation on a method or implement the `DisposableBean` interface and override the `destroy()` method.
   - The destroy method is typically used to release any resources held by the bean, close database connections, stop background threads, or perform necessary cleanup operations.

It's important to note that the init and destroy methods in Spring are optional. You can choose to implement them in your beans if you have specific initialization or cleanup requirements. If you don't provide these methods, the container will still manage the lifecycle of your beans, but you won't have the opportunity to execute custom initialization or cleanup logic.

To summarize, lifecycle methods in Spring, such as the init and destroy methods, allow you to hook into the bean's lifecycle and perform custom initialization and cleanup tasks. These methods are automatically called by the Spring container, relieving you from the need to manually invoke them. The init method is used for initialization tasks, while the destroy method is used for cleanup tasks, particularly when connecting to external resources or performing resource cleanup.

<br/>
<br/>
<br/>

# Here's a detailed explanation of the three ways to configure the init and destroy methods in Spring:

A) **Using XML Configuration**:
   - In XML-based configuration, you can specify the init and destroy methods for a bean using the `<bean>` element's `init-method` and `destroy-method` attributes.
   - To configure the init method, use the `init-method` attribute and provide the name of the method that should be called during initialization.
   - To configure the destroy method, use the `destroy-method` attribute and provide the name of the method that should be called during bean destruction.
   - Example:
     ```xml
     <bean id="myBean" class="com.example.MyBean" init-method="initMethod" destroy-method="destroyMethod">
     </bean>
     ```

B) **Using Pre-defined Interfaces given by Spring Framework**:
   - Spring provides two pre-defined interfaces that you can implement in your beans to define the init and destroy methods.
   - Implement the `InitializingBean` interface and override the `afterPropertiesSet()` method to define the init method. This method will be called after the bean's dependencies have been injected.
   - Implement the `DisposableBean` interface and override the `destroy()` method to define the destroy method. This method will be called before the bean is destroyed.
   - Example:
     ```java
     import org.springframework.beans.factory.DisposableBean;
     import org.springframework.beans.factory.InitializingBean;

     public class MyBean implements InitializingBean, DisposableBean {

         @Override
         public void afterPropertiesSet() throws Exception {
             // Initialization logic here
         }

         @Override
         public void destroy() throws Exception {
             // Cleanup logic here
         }
     }
     ```

C) **JSR Annotations**:
   - Spring supports the JSR-250 annotations `@PostConstruct` and `@PreDestroy` for defining the init and destroy methods respectively.
   - Annotate a method with `@PostConstruct` to define the init method. This method will be called after the bean's dependencies have been injected.
   - Annotate a method with `@PreDestroy` to define the destroy method. This method will be called before the bean is destroyed.
   - Example:
     ```java
     import javax.annotation.PostConstruct;
     import javax.annotation.PreDestroy;

     public class MyBean {

         @PostConstruct
         public void initMethod() {
             // Initialization logic here
         }

         @PreDestroy
         public void destroyMethod() {
             // Cleanup logic here
         }
     }
     ```

It's important to note that these three approaches are not mutually exclusive. You can choose the approach that best suits your project and configure the init and destroy methods accordingly.

These methods allow you to define custom initialization and cleanup logic for your beans. The init method is used to perform any necessary setup tasks, while the destroy method is used for releasing resources or performing cleanup operations before the bean is destroyed.

<br/>
<br/>
<br/>

# Let's go through the code and explain the lifecycle methods using XML configuration:

1. **Spring Bean** (ExcelExportService):
```java
package com.app.service;

public class ExcelExportService {

	private String fileName;
	private String mode;

	public void setup() {
		// Lot of code...
		System.out.println("FROM INIT METHOD");
	}

	public void clean() {
		// Some code..
		System.out.println("FROM DESTROY METHOD");
	}

	// Constructor, getters, setters, and toString method...
}
```
- The `ExcelExportService` class represents a Spring bean that performs Excel export functionality.
- It has properties for `fileName` and `mode`, along with the corresponding getters and setters.
- The `setup()` method is the custom init method, and the `clean()` method is the custom destroy method.
- In the init and destroy methods, a simple message is printed to demonstrate their execution.

2. **XML Configuration**:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="eobj" class="com.app.service.ExcelExportService" init-method="setup" destroy-method="clean">
        <property name="fileName" value="Sample" />
        <property name="mode" value="CSV FORMAT" />
    </bean>
</beans>
```
- The XML configuration file defines a bean with the id "eobj" and the class "com.app.service.ExcelExportService".
- The `init-method` attribute is set to "setup" to configure the custom init method.
- The `destroy-method` attribute is set to "clean" to configure the custom destroy method.
- Two properties, `fileName` and `mode`, are set using the `<property>` element.

3. **Test Class**:
```java
package com.app.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Object ob = ac.getBean("eobj");
		System.out.println(ob);
		ac.close(); // Must be called to properly shut down the Spring container
	}
}
```
- The test class creates a `ClassPathXmlApplicationContext` by providing the XML configuration file name "beans.xml".
- It retrieves the bean with the id "eobj" using the `getBean()` method.
- The bean is printed to the console using `System.out.println()`.
- Finally, the `close()` method is called on the application context to shut down the Spring container gracefully.

**Explanation:**
- When the application starts, the Spring container reads the XML configuration file and creates an instance of the `ExcelExportService` bean.
- During the bean creation process, the `setup()` method is automatically called due to the `init-method="setup"` configuration in the XML file.
- The `setup()` method prints "FROM INIT METHOD" to the console.
- The bean is then configured with the property values: `fileName` is set to "Sample" and `mode` is set to "CSV FORMAT".
- The bean is retrieved in the test class and printed to the console.
- When the application is shutting down, the Spring container calls the `clean()` method of the bean due to the `destroy-method="clean"` configuration in the XML file.
- The `clean()` method prints "FROM DESTROY METHOD" to the console

.
- Finally, the Spring container is closed using the `close()` method, ensuring a proper shutdown of the application.

The XML configuration allows you to define the init and destroy methods for a bean and have them automatically executed by the Spring container during the bean's lifecycle. This provides a convenient way to perform initialization and cleanup tasks for your beans.

<br/>
<br/>
<br/>

# Let's go through the code and explain the lifecycle methods using interfaces:

1. **Spring Bean** (ExcelExportService):
```java
package com.app.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class ExcelExportService implements InitializingBean, DisposableBean {

	private String fileName;
	private String mode;

	public void afterPropertiesSet() throws Exception {
		// Lot of code...
		System.out.println("FROM INIT METHOD");
	}

	public void destroy() throws Exception {
		// Some code..
		System.out.println("FROM DESTROY METHOD");
	}

	// Constructor, getters, setters, and toString method...
}
```
- The `ExcelExportService` class implements two interfaces provided by Spring: `InitializingBean` and `DisposableBean`.
- The `afterPropertiesSet()` method is the initialization method defined in the `InitializingBean` interface.
- The `destroy()` method is the destruction method defined in the `DisposableBean` interface.
- In these methods, a simple message is printed to demonstrate their execution.

2. **XML Configuration**:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="eobj" class="com.app.service.ExcelExportService">
        <property name="fileName" value="Sample" />
        <property name="mode" value="CSV FORMAT" />
    </bean>
</beans>
```
- The XML configuration file defines a bean with the id "eobj" and the class "com.app.service.ExcelExportService".
- Two properties, `fileName` and `mode`, are set using the `<property>` element.

3. **Test Class**:
```java
package com.app.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Object ob = ac.getBean("eobj");
		System.out.println(ob);
		ac.close();
	}
}
```
- The test class creates a `ClassPathXmlApplicationContext` by providing the XML configuration file name "beans.xml".
- It retrieves the bean with the id "eobj" using the `getBean()` method.
- The bean is printed to the console using `System.out.println()`.
- Finally, the `close()` method is called on the application context to shut down the Spring container gracefully.

**Explanation:**
- When the application starts, the Spring container reads the XML configuration file and creates an instance of the `ExcelExportService` bean.
- During the bean creation process, the Spring container detects that the bean implements the `InitializingBean` interface and automatically calls the `afterPropertiesSet()` method.
- The `afterPropertiesSet()` method prints "FROM INIT METHOD" to the console.
- The bean is then configured with the property values: `fileName` is set to "Sample" and `mode` is set to "CSV FORMAT".
- The bean is retrieved in the test class and printed to the console.
- When the application is shutting down, the Spring container calls the `destroy()` method of the bean as it implements the `DisposableBean` interface.
- The `destroy()` method prints "FROM DESTROY METHOD" to the console.
- Finally, the Spring container is closed using the `close()` method, ensuring a proper shutdown of the application.

The `InitializingBean` and `DisposableBean` interfaces

 provide a way to define initialization and destruction methods for beans without explicitly specifying them in the XML configuration. Spring automatically invokes these methods based on the implemented interfaces, allowing you to perform custom initialization and cleanup tasks.

<br/>
<br/>
<br/>

# Let's go through the code and explain the lifecycle methods using JSR-250 annotations:

1. **Spring Bean** (ExcelExportService):
```java
package com.app.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ExcelExportService {

	private String fileName;
	private String mode;
	
	@PostConstruct
	public void setup() throws Exception {
		// Lot of code...
		System.out.println("FROM INIT METHOD");
	}
	
	@PreDestroy
	public void clean() throws Exception {
		// Some code...
		System.out.println("FROM DESTROY METHOD");
	}

	// Constructor, getters, setters, and toString method...
}
```
- The `ExcelExportService` class defines two methods annotated with `@PostConstruct` and `@PreDestroy`.
- The `@PostConstruct` annotation indicates that the `setup()` method should be executed after the bean has been constructed and its dependencies have been injected.
- The `@PreDestroy` annotation indicates that the `clean()` method should be executed before the bean is destroyed.

2. **XML Configuration**:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans 
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

    <bean id="eobj" class="com.app.service.ExcelExportService">
        <property name="fileName" value="Sample" />
        <property name="mode" value="CSV FORMAT" />
    </bean>
</beans>
```
- The XML configuration file includes the `context` namespace and the `annotation-config` element, enabling annotation-based configuration.
- The `ExcelExportService` bean is defined with the id "eobj" and the class "com.app.service.ExcelExportService".
- Property values for `fileName` and `mode` are set using the `<property>` element.

3. **Test Class**:
```java
package com.app.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Object ob = ac.getBean("eobj");
		System.out.println(ob);
		ac.close();
	}
}
```
- The test class creates a `ClassPathXmlApplicationContext` by providing the XML configuration file name "beans.xml".
- It retrieves the bean with the id "eobj" using the `getBean()` method.
- The bean is printed to the console using `System.out.println()`.
- Finally, the `close()` method is called on the application context to shut down the Spring container gracefully.

**Explanation:**
- In this example, the lifecycle methods `setup()` and `clean()` are annotated with `@PostConstruct` and `@PreDestroy`, respectively.
- When the Spring container initializes the bean, it detects these annotations and automatically invokes the corresponding methods.
- The `setup()` method is executed after the bean has been constructed and its dependencies have been injected. It prints "FROM INIT METHOD" to the console.
- The bean is then configured with the property values: `fileName` is set to "Sample" and `mode` is set to "CSV FORMAT".
- The bean is retrieved in the test class and printed to the console.
- When

 the application context is closed, the `clean()` method is called before the bean is destroyed. It prints "FROM DESTROY METHOD" to the console.

This way, using JSR-250 annotations, you can define lifecycle methods directly in the bean class, and Spring will automatically invoke them at the appropriate times during the bean's lifecycle.

<br/>
<br/>
<br/>


**Lifecycle methods supported by Spring Framework:**
Spring Framework supports two lifecycle methods for beans: `init` and `destroy`.

**Ways to configure lifecycle methods:**
There are three ways to configure lifecycle methods in Spring:

1. **XML Configuration:**
   - Use the `init-method` attribute to specify the init method for a bean.
   - Use the `destroy-method` attribute to specify the destroy method for a bean.
   - Example:
     ```xml
     <bean id="myBean" class="com.example.MyBean" init-method="initMethod" destroy-method="destroyMethod" />
     ```

2. **Spring Pre-defined Interfaces:**
   - Implement the `InitializingBean` and `DisposableBean` interfaces in your bean class.
   - Implement the `afterPropertiesSet()` method for initialization logic.
   - Implement the `destroy()` method for cleanup logic.
   - Example:
     ```java
     public class MyBean implements InitializingBean, DisposableBean {
         public void afterPropertiesSet() {
             // Initialization logic
         }
         public void destroy() {
             // Cleanup logic
         }
     }
     ```

3. **JSR-250 Annotations:**
   - Use the `@PostConstruct` annotation to specify the initialization method.
   - Use the `@PreDestroy` annotation to specify the destroy method.
   - Example:
     ```java
     public class MyBean {
         @PostConstruct
         public void initMethod() {
             // Initialization logic
         }
         @PreDestroy
         public void destroyMethod() {
             // Cleanup logic
         }
     }
     ```

**Activating JSR Annotations in XML Configuration:**
To activate JSR-250 annotations in XML configuration, you need to include the `<context:annotation-config/>` element in your XML file. Additionally, you need to include the `javax.annotation-api` dependency in your project's POM.xml file.
Example:
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans 
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context.xsd">
        
    <context:annotation-config/>
    
    <!-- Bean definitions -->

</beans>
```

By including the `<context:annotation-config/>` element, Spring will scan for and activate JSR-250 annotations in your beans.

# Some Imp `FAQ's`

**Q: How many lifecycle methods are supported by the Spring Framework?**
A: The Spring Framework supports two lifecycle methods: `init` and `destroy`.

**Q: In how many ways can we configure lifecycle methods in Spring?**
A: There are three ways to configure lifecycle methods in Spring:
   1. XML Configuration: By using the `init-method` and `destroy-method` attributes in the bean definition.
   2. Spring Pre-defined Interfaces: By implementing the `InitializingBean` and `DisposableBean` interfaces and defining the `afterPropertiesSet()` and `destroy()` methods.
   3. JSR-250 Annotations: By using the `@PostConstruct` and `@PreDestroy` annotations on methods in the bean class.

**Q: How can we activate JSR-250 annotations in XML configuration?**
A: To activate JSR-250 annotations in XML configuration, you need to include the `<context:annotation-config/>` element in your XML file. Additionally, you need to include the `javax.annotation-api` dependency in your project's POM.xml file.

**Q: How do we configure lifecycle methods using XML configuration?**
A: You can configure lifecycle methods using XML configuration by specifying the `init-method` and `destroy-method` attributes in the bean definition. For example:
```xml
<bean id="myBean" class="com.example.MyBean" init-method="initMethod" destroy-method="destroyMethod" />
```

**Q: How do we configure lifecycle methods using Spring pre-defined interfaces?**
A: You can configure lifecycle methods using Spring pre-defined interfaces by implementing the `InitializingBean` and `DisposableBean` interfaces in your bean class and providing the initialization and destruction logic in the `afterPropertiesSet()` and `destroy()` methods, respectively.

**Q: How do we configure lifecycle methods using JSR-250 annotations?**
A: You can configure lifecycle methods using JSR-250 annotations by using the `@PostConstruct` annotation for the initialization method and the `@PreDestroy` annotation for the destruction method. Annotate the respective methods in the bean class with these annotations. For example:
```java
public class MyBean {
    @PostConstruct
    public void initMethod() {
        // Initialization logic
    }
    
    @PreDestroy
    public void destroyMethod() {
        // Destruction logic
    }
}
```

<br/>

**Q: What is the purpose of the initialization and destruction lifecycle methods in Spring?**
A: The initialization and destruction lifecycle methods provide a way to perform additional setup and cleanup tasks for a bean. The initialization method is called after the bean has been instantiated and all its dependencies have been injected, allowing you to perform any necessary initialization logic. The destruction method is called when the bean is being destroyed, giving you an opportunity to release resources or perform cleanup operations.

**Q: Can we have multiple initialization and destruction methods for a single bean?**
A: Yes, you can have multiple initialization and destruction methods for a single bean. However, when using XML configuration or Spring pre-defined interfaces, you can only specify one initialization method and one destruction method. With JSR-250 annotations, you can have multiple methods annotated with `@PostConstruct` and `@PreDestroy` respectively.

**Q: How does Spring determine the order of execution for multiple initialization and destruction methods?**
<br/>A: When using JSR-250 annotations, the order of execution for multiple initialization and destruction methods is not guaranteed. If you need to control the order of execution, you can use the `@Order` annotation along with `@PostConstruct` and `@PreDestroy` annotations to specify the desired order.

**Q: What happens if a bean does not have an explicit initialization or destruction method configured?**
<br/> A: If a bean does not have an explicit initialization or destruction method configured, the corresponding lifecycle method will not be called by the Spring container. The bean will still be created and destroyed, but without any additional custom initialization or destruction logic.

**Q: Can we use both XML configuration and annotations for configuring lifecycle methods in Spring?**
<br/>A: Yes, you can use both XML configuration and annotations for configuring lifecycle methods in Spring. However, if both methods are used for the same bean, the annotation-based configuration takes precedence over the XML configuration.

**Q: Is it necessary to call the `close()` method on the application context to trigger destruction methods?**
<br/>A: Yes, it is necessary to call the `close()` method on the application context to trigger the destruction methods defined for the beans. The `close()` method ensures that all registered shutdown hooks and destruction callbacks are executed properly, allowing beans to perform their cleanup operations before the application context is closed.

**Q: Can we define initialization and destruction methods for prototype-scoped beans in Spring?**
<br/>A: Yes, you can define initialization and destruction methods for prototype-scoped beans in Spring. However, the Spring container does not manage the complete lifecycle of prototype beans. It is the responsibility of the client code to handle the creation, initialization, and destruction of prototype beans explicitly. The initialization and destruction methods defined for prototype beans will be called, but the Spring container will not keep track of their lifecycle.