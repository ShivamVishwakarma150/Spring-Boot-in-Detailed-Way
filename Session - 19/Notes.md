# SpringBoot Runners

Spring Boot provides a feature called "ApplicationRunners" and "CommandLineRunners" that allows you to execute custom logic once when the Spring container starts up. These runners are interfaces provided by the Spring Boot framework that you can implement in your application to perform specific tasks during application startup.

1. Testing Objects (Created or not?)
   You can use an ApplicationRunner or CommandLineRunner to perform tests on objects that are created during application startup. For example, you can check if certain beans or components have been created successfully and verify their state or behavior.

2. Loading Database Scripts (Create, alter, etc.)
   ApplicationRunners and CommandLineRunners can be used to load and execute database scripts during application startup. You can write code to read SQL or other database scripts from files or other sources, and then use a database connection or an ORM framework to execute those scripts and set up the database schema or perform data migrations.

3. Start Batch Processing (e.g., CSV/Excel File to Database)
   If you have batch processing tasks to perform, such as reading data from CSV or Excel files and storing it in a database, you can utilize ApplicationRunners or CommandLineRunners. In the run() method of the runner, you can write the logic to read the input files, process the data, and persist it in the database.

4. Root User Creation/Roles Setup
   During application startup, you can use ApplicationRunners or CommandLineRunners to create a root user or set up roles and permissions in the application. This can involve creating initial records in the database, setting up security configurations, or initializing an authentication provider.

To use ApplicationRunners or CommandLineRunners in your Spring Boot application, follow these steps:

1. Implement the ApplicationRunner or CommandLineRunner interface in a class.
2. Override the run() method in the implemented class.
3. Write the logic inside the run() method that you want to execute on application startup.
4. Spring Boot will automatically detect these runner beans and execute the run() method when the application starts up.

Here's an example of an ApplicationRunner implementation:

```java
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Custom logic to be executed on application startup
        System.out.println("ApplicationRunner executed!");
    }
}
```

In this example, the `run()` method will be executed when the application starts up. You can add your specific logic inside the `run()` method.

Similarly, you can implement CommandLineRunner using the CommandLineRunner interface and override the run() method to execute specific logic during application startup.

By using ApplicationRunners or CommandLineRunners, you can ensure that certain tasks are performed automatically and only once when the Spring container starts up, enabling you to set up the application's initial state or execute specific initialization logic.

<br/>
<br/>
<br/>

# Notes

In Spring Boot, the `CommandLineRunner` interface is provided by the `org.springframework.boot` package. It allows you to define custom logic that will be executed when the application starts up. 

Here are the steps to use `CommandLineRunner`:

1. Define a class and annotate it with `@Component`:
   Create a class and annotate it with `@Component` to make it a Spring bean and allow it to be detected by the Spring container.

2. Implement `CommandLineRunner` and override the `run()` method:
   Implement the `CommandLineRunner` interface in your class and override the `run(String... args)` method. This method will be executed when the application starts up.

3. Define your logic inside the `run()` method:
   Write the custom logic that you want to execute during application startup inside the `run()` method. You can perform any initialization tasks, configuration setups, or other actions necessary for your application.

Here's an example:

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Custom logic to be executed on application startup
        System.out.println("CommandLineRunner executed!");
    }
}
```

In this example, the `run()` method will be called when the application starts up. You can add your specific logic inside the `run()` method.

Notes:

1. Multiple runners in one application:
   You can define multiple classes that implement `CommandLineRunner` in your application. All these runners will be executed in the order specified by their names.

2. Execution order:
   By default, the runners are executed in the order of their names. If you want to provide a specific execution order, you can use the `@Order(int)` annotation on your runner classes. The lower the value of the order, the earlier the runner will be executed. If no `@Order` annotation is provided, the default order is `Integer.MAX_VALUE` (2147483647).

3. Naming rule for runners with the same order:
   If multiple runners have the same order value, the naming rule is applied. The runner with the lower alphabetical name will be executed first.

By using `CommandLineRunner`, you can easily define and execute custom logic during the startup of your Spring Boot application. This allows you to perform specific tasks or initialization steps necessary for your application's functionality.

<br/>
<br/>
<br/>


```
    ========Code ==============================================
1. Create application
> File > new > Spring Starter Project
Name : 01SpringBootCoreRunnerEx
Package : com.app.shivam
> Next > Finish

2. Spring Bean classes
package com.app.shivam.runner;

//ctrl+shift+O
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class MessageRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("MESSAGE RUNNER.");
	}
}

----
package com.app.shivam.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(15)
public class DatabaseConRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DATABASE CON RUNNER");
	}
}
---
package com.app.shivam.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class EmailConfigRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("EMAIL CONFIG.");
	}
}
```

Let's go through the provided code and explain each point in detail:

1. Create application:
   This step involves creating a Spring Boot application using your IDE or any other method. In the example, it mentions creating a Spring Starter Project with the name "01SpringBootCoreRunnerEx" and the package "com.app.shivam".

2. Spring Bean classes:
   This step involves creating the Spring bean classes that implement the `CommandLineRunner` interface. The provided code includes three runner classes: `MessageRunner`, `DatabaseConRunner`, and `EmailConfigRunner`.

   - `MessageRunner`:
     This class implements the `CommandLineRunner` interface and is annotated with `@Component`. It also has an `@Order(5)` annotation, which specifies the order in which the runner should be executed. The `run()` method is overridden with custom logic that prints "MESSAGE RUNNER" when executed.

   - `DatabaseConRunner`:
     This class is similar to `MessageRunner` but has an `@Order(15)` annotation, indicating that it should be executed after the `MessageRunner`. The `run()` method is overridden with custom logic that prints "DATABASE CON RUNNER" when executed.

   - `EmailConfigRunner`:
     This class is also similar to the previous runners but has an `@Order(10)` annotation, indicating that it should be executed before the `DatabaseConRunner`. The `run()` method is overridden with custom logic that prints "EMAIL CONFIG" when executed.

   Note that the `@Order` annotation is used to specify the execution order of the runners. Lower values indicate earlier execution. If no `@Order` annotation is provided, the runners will be executed in the order of their names.

By creating these runner classes and implementing the `run()` method, you can define custom logic that will be executed on application startup. The logic can vary based on your specific requirements, such as initializing components, loading configurations, or performing other tasks necessary for your application's functionality.

When you run the Spring Boot application, the `CommandLineRunner` beans will be automatically detected by the Spring container, and the `run()` method of each runner will be executed in the specified order. In the provided code, the output will be:

```
MESSAGE RUNNER.
EMAIL CONFIG.
DATABASE CON RUNNER
```

he runners will execute in the specified order based on the @Order annotation. The MessageRunner will be executed first, followed by the EmailConfigRunner, and finally the DatabaseConRunner.

By using `CommandLineRunner` and the `@Order` annotation, you can control the execution order of your custom logic during application startup in a Spring Boot application.

<br/>
<br/>
<br/>


```
    Ex:
ARunner  -- @Order(12)
BRunner  -- @Order(44)
CRunner  -- @Order(-98)
DRunner  -- @Order(-101)
ERunner  --  No External Order value is provided
FRunner  --  No External Order value is provided

Execution Order of Above Runners:-
D  C  A  B  E  F
```

Based on the provided `@Order` annotations, the execution order of the runners will be as follows:

1. DRunner (Order: -101)
2. CRunner (Order: -98)
3. ARunner (Order: 12)
4. BRunner (Order: 44)
5. ERunner (No external Order value provided)
6. FRunner (No external Order value provided)

Please note that runners without an external `@Order` value will be assigned the default value of `Integer.MAX_VALUE` (2147483647), which means they will be executed last.

Therefore, the execution order of the runners will be: DRunner, CRunner, ARunner, BRunner, ERunner, and FRunner.

Keep in mind that the ordering is determined by the `@Order` annotation or the default ordering rules if no explicit order is provided.

<br/>
<br/>
<br/>

1. `@Component`: The `@Component` annotation is used to mark a class as a Spring component. It indicates that the class should be managed by the Spring IoC container and treated as a bean. When the Spring application context starts up, it will create an instance of the class and register it in the container. The component can be auto-detected using component scanning or explicitly defined in the configuration.

2. `@Value("${key}")`: The `@Value` annotation is used to inject values from external sources, such as property files, into Spring beans. It allows you to specify a placeholder expression `${key}` to retrieve the value associated with the given key from a configured property source. In the example you provided, the value is read from the `application.properties` file using the specified key.

3. `@Autowired`: The `@Autowired` annotation is used to auto-wire dependencies in Spring beans. When Spring encounters this annotation, it will automatically search for a bean of the required type in the application context and inject it into the annotated field, constructor, or method parameter. It enables automatic dependency injection and saves you from manually wiring the dependencies.

4. `@PropertySource`: In Spring Core, the `@PropertySource` annotation is used to specify the properties file to be loaded by the Spring environment. It is typically used in combination with `@Configuration` classes to load external properties into the Spring environment. However, in the example you provided, there is no need to use `@PropertySource` because Spring Boot automatically loads the `application.properties` file without requiring explicit configuration. Spring Boot follows convention-over-configuration, where sensible defaults and auto-configuration are applied, reducing the need for manual configuration.

Overall, these annotations play important roles in Spring and Spring Boot applications. `@Component` marks a class as a Spring component, `@Value` retrieves values from property files, `@Autowired` injects dependencies, and `@PropertySource` is used to specify the properties file to be loaded in Spring Core (not needed in Spring Boot).

<br/>
<br/>
<br/>

```
    =========Spring Boot Core Example # 2 (Properties file ) ======
1. Spring Bean
package com.app.shivam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCon {
	
	@Value("${my.db.driver}")
	private String driver;
	
	@Value("${my.db.url}")
	private String url;
	
	@Value("${my.db.usr}")
	private String user;
	
	@Value("${my.db.pwd}")
	private String password;
	
	@Override
	public String toString() {
		return "DatabaseCon [driver=" + driver + ", url=" + url + ", user=" + user + ", password=" + password + "]";
	}
	
	
}

2. application.properties
#Our own keys
my.db.driver=Oracle
my.db.url=jdbc:oracle:thin:XYZ
my.db.usr=root
my.db.pwd=sample

3. Runner class
package com.app.shivam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestObjRunner implements CommandLineRunner {
	
	@Autowired
	private DatabaseCon con;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(con);
	}
}
```

In this example, we have a Spring Boot application that demonstrates how to read values from a properties file and inject them into a Spring bean using the `@Value` annotation.

1. `DatabaseCon` class:
   - This is a Spring bean represented by the `@Component` annotation.
   - It has instance variables annotated with `@Value("${key}")`, which inject values from the properties file.
   - The `toString()` method is overridden to display the values of the instance variables.

2. `application.properties` file:
   - This file contains key-value pairs for configuring the database connection.
   - The keys are defined as `my.db.driver`, `my.db.url`, `my.db.usr`, and `my.db.pwd`.
   - The corresponding values are the configuration details for the database driver, URL, username, and password.

3. `TestObjRunner` class:
   - This class is a Spring bean represented by the `@Component` annotation.
   - It implements the `CommandLineRunner` interface, which allows us to run custom code when the application starts.
   - The `DatabaseCon` bean is autowired into this class using `@Autowired`.
   - In the `run()` method, the `DatabaseCon` bean is accessed and printed, which triggers the `toString()` method to display the configured values.

When the application starts, the `TestObjRunner` bean is created by the Spring container. It automatically detects the `@Component` annotation and executes the `run()` method due to the `CommandLineRunner` interface implementation. The `DatabaseCon` bean is injected into the `TestObjRunner` bean using `@Autowired`. The values from the properties file are read and assigned to the corresponding variables in the `DatabaseCon` bean using `@Value("${key}")`. Finally, the `toString()` method is called, and the configured database connection details are printed.

By using the `@Value` annotation and the `application.properties` file, we can externalize the configuration of the database connection and easily modify it without changing the code. This approach provides flexibility and allows us to centralize the configuration in a properties file, which can be customized for different environments or deployment scenarios.

<br/>
<br/>
<br/>

# Notes

In Spring Boot, we have the option to use `@ConfigurationProperties` annotation to load multiple keys into matching variables at once, instead of using `@Value` annotation for each individual key-value pair.

Here are some key points to understand about `@ConfigurationProperties`:

1. Usage: `@ConfigurationProperties` is typically used at the class level to bind a group of related properties to a single class.

2. Loading Properties: By using `@ConfigurationProperties`, we can load multiple properties with their respective values into matching variables in a single step.

3. Prefix: To bind the properties, we need to specify a prefix that matches the common prefix of the properties we want to bind. The prefix is added as an argument to the `@ConfigurationProperties` annotation.

4. No Exceptions: Unlike `@Value`, `@ConfigurationProperties` will not throw exceptions if the key name or variable name doesn't match exactly, or if the prefix is not found. In such cases, the corresponding variable will hold the default value (null, 0, 0.0, false, etc.).

5. Case Sensitivity: The matching of keys and variables in `@ConfigurationProperties` is case-insensitive. If an exact case match is not found, it will try to find the nearest case match. For example, if the variable name is `password`, it will first look for `my.app.password`, and if not found, it will check for `my.app.PASSWORD`.

Using `@ConfigurationProperties` provides a convenient way to bind multiple properties to a class and simplifies the code by reducing the number of `@Value` annotations. It also provides flexibility in handling properties that may not exist or have default values.


<br/>
<br/>
<br/>

```
    ====Code========================================================
1. Spring Bean
package com.app.shivam;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my.app")
public class DatabaseCon {

	private String driver;
	private String url;
	private String username;
	private String password;

	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "DatabaseCon [driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password
				+ "]";
	}
	
	
}

2. Runner class
package com.app.shivam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDbConRunner implements CommandLineRunner {

	@Autowired
	private DatabaseCon con;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(con);
	}

}

3. application.properties
my.app.driver=OracleDriver
my.app.url=jdbc:oracle
my.app.username=system
my.app.password=testabc
#my.app.PASSWORD=shivam

```

Let's explain the code you provided in detail:

1. EmailConfig Bean:
   - The `EmailConfig` class is annotated with `@Component`, indicating that it is a Spring Bean.
   - It is also annotated with `@ConfigurationProperties(prefix = "my.app.email")`, which binds the properties with the given prefix to the corresponding fields in the class.
   - The `EmailConfig` class has fields for `host`, `port`, `active`, and `usr`, along with their getter and setter methods.
   - The `toString()` method is overridden to provide a string representation of the `EmailConfig` object.

2. Runner class:
   - The `TestEmailConfigRunner` class is annotated with `@Component`, indicating that it is a Spring Bean.
   - It implements the `CommandLineRunner` interface, which allows the class to execute logic on application startup.
   - It is autowired with the `EmailConfig` bean to access its properties.
   - The `run()` method is overridden, and when executed, it prints the `EmailConfig` object.

3. application.properties:
   - This file contains properties that are used to configure the `EmailConfig` bean.
   - The properties are prefixed with `my.app.email` to match the prefix defined in the `EmailConfig` class.
   - The properties specify the values for `host`, `port`, `active`, and `usr`.

Overall, this code demonstrates the usage of `@ConfigurationProperties` to bind properties from the `application.properties` file to a Spring Bean. The `EmailConfig` bean is created with the specified properties, and the `TestEmailConfigRunner` class retrieves and prints the values of the `EmailConfig` object on application startup.


<br/>
<br/>
<br/>

```
    TASK: EmailConfig (class)
       host (String)
       port (int)
       active (boolean)
       usr (String)

-> provide data in properties file using syntax: prefix.variable=val
-> Load them using : @ConfigurationProperties(prefix="___")
-> Define one runner class to read emailConfig object and print
```
Here's the updated code to fulfill your task:

1. EmailConfig Bean:
```java
package com.app.shivam;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my.app.email")
public class EmailConfig {
    private String host;
    private int port;
    private boolean active;
    private String usr;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    @Override
    public String toString() {
        return "EmailConfig [host=" + host + ", port=" + port + ", active=" + active + ", usr=" + usr + "]";
    }
}
```

2. Runner class:
```java
package com.app.shivam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestEmailConfigRunner implements CommandLineRunner {

    @Autowired
    private EmailConfig emailConfig;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(emailConfig);
    }
}
```

3. application.properties:
```
my.app.email.host=smtp.example.com
my.app.email.port=587
my.app.email.active=true
my.app.email.usr=sampleuser
```

In this updated code, a new `EmailConfig` bean is created with properties for `host` (String), `port` (int), `active` (boolean), and `usr` (String). These properties are configured in the `application.properties` file using the `my.app.email` prefix. The `TestEmailConfigRunner` class is responsible for printing the `EmailConfig` object, which is retrieved using autowiring. When the application starts, the `run()` method is executed, and it prints the `EmailConfig` object containing the configured properties.

Please note that the property names in the `application.properties` file should match the variable names in the `EmailConfig` class to establish the correct binding.

