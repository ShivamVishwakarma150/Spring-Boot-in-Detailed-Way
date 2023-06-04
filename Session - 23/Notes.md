# Application Environment

In software development, the application environment refers to the infrastructure and configuration necessary to run an application. This includes the various components such as databases, servers, configurations, and dependencies required to support the application. Different environments are typically set up to serve different purposes in the software development life cycle. Here are some commonly used application environments:

1. Development (Dev) Environment:
The development environment is where developers write, test, and debug their code. It is typically set up on their local machines or a shared development server. The environment may include a local database, a development server, and specific configurations tailored for development purposes.

2. Quality Assurance (QA) Environment:
The QA environment is used for testing the application to ensure its quality before releasing it to production. It mimics the production environment as closely as possible and includes a separate database and server for testing purposes. QA testers perform functional, performance, and regression testing in this environment.

3. User Acceptance Testing (UAT) Environment:
The UAT environment is where clients or end-users test the application to validate its functionality and usability. It is a replica of the production environment and is used for final testing and sign-off before the application goes live. UAT may have a separate database and server, and it allows clients to verify that the application meets their requirements.

4. Production (Prod) Environment:
The production environment is the live environment where the application is accessible to end-users. It requires a robust and highly available infrastructure, including production-grade databases, load balancers, and multiple servers for redundancy. The production environment is carefully managed to ensure stability, security, and performance.

5. Production Support (ProdSup) Environment:
The production support environment is used by the operations team or support staff to monitor and maintain the production application. It may include additional monitoring tools, debugging utilities, and access to production logs to diagnose and fix any issues that may arise in the live environment.

Other environments, such as staging, pre-production, or disaster recovery, may also be used depending on the specific needs of the application and the organization. Each environment serves a distinct purpose and ensures that the application progresses through the development life cycle in a controlled and systematic manner.

# Spring Boot Profiles

Let's break down each point and provide a detailed explanation of Spring Boot profiles and how they are used.

1. If we move our application from one Env. to another Env, the code remains the same (at max), changes come at the input file (properties/yaml):
This means that when you deploy your application to a different environment (e.g., from development to production), the actual codebase remains unchanged. The application's behavior is controlled by the input file, which can be in either properties format (.properties) or YAML format (.yml). This separation allows you to configure the application based on the specific environment's requirements without modifying the code itself.

1. Use one properties/yaml file for one environment:
To maintain clarity and organization, it's recommended to have separate properties or YAML files for each environment. This approach keeps the configuration for different environments isolated and makes it easier to manage and maintain.

1. File name must be: application-{profilename}.properties or application-{profilename}.yml:
To associate a properties or YAML file with a specific environment, the naming convention should follow the pattern: "application-{profilename}.properties" or "application-{profilename}.yml". The {profilename} represents the name of the profile or environment (e.g., "qa" or "prod"). Spring Boot uses this naming convention to automatically load the correct configuration file based on the active profile.

1. Loading the input file based on profilename is taken care of by the Spring container:
Spring Boot's container is responsible for managing the application's configuration and loading the appropriate properties or YAML file based on the active profile. When the application starts, it detects the active profile (which can be set through various means, such as command-line arguments, environment variables, or configuration files) and loads the corresponding configuration file automatically.

1. Examples:
Let's consider the following examples:

- application.properties (default profile):
This is the default configuration file that contains the common settings for the application. It serves as the fallback configuration when no specific profile is active.

- application-qa.properties (qa profile):
This file contains the configuration specific to the QA (Quality Assurance) environment. It can include properties such as database connection details, logging settings, or any other environment-specific configurations needed for QA testing.

- application-prod.properties (prod profile):
This file contains the configuration specific to the production environment. It would typically have different settings compared to the QA environment, such as production database connection details, caching configurations, or performance-related settings optimized for production use.

By using different properties or YAML files for each environment, you can easily switch between environments without modifying the code, ensuring consistent behavior while adapting to specific environment requirements.

Overall, Spring Boot profiles and the separation of configuration files based on environments provide flexibility, maintainability, and ease of deployment across different environments.

<br/>
<br/>
<br/>

# Code Explanation

Let's go through the code and then explain each component separately.

1. Spring Bean: MyDbConnection.java
```java
package com.app.shivam.dbcon;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties("my.db")
public class MyDbConnection {

	private String driver;
	private String url;
	private String user;
	private String pwd;
	
}
```
This code defines a Spring Bean called `MyDbConnection`. It is annotated with `@Component`, which indicates that it should be managed by the Spring container as a bean. The `@Data` annotation is provided by Lombok, which automatically generates getter/setter methods, `equals()`, `hashCode()`, and `toString()` methods for the class.

The `@ConfigurationProperties("my.db")` annotation maps the properties defined in the configuration files to the fields of this class. It specifies that the properties starting with `my.db` should be bound to the corresponding fields in this class. For example, `my.db.driver` in the properties file will be mapped to the `driver` field in this class.

2. Runner: TestDataRunner.java
```java
package com.app.shivam.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.dbcon.MyDbConnection;

@Component
public class TestDataRunner implements CommandLineRunner {

	@Autowired
	private MyDbConnection con;
	
	public void run(String... args) throws Exception {
		System.out.println(con);
	}

}
```
This code defines a `TestDataRunner` class, which implements the `CommandLineRunner` interface. The `CommandLineRunner` interface provides a run method that gets executed when the Spring Boot application starts.

The `TestDataRunner` class is annotated with `@Component`, making it a managed bean. It is also injected with an instance of `MyDbConnection` using the `@Autowired` annotation. This allows us to access the `MyDbConnection` bean within the `run` method.

In the `run` method, we simply print out the `con` object, which is an instance of `MyDbConnection`. This will invoke the `toString()` method of `MyDbConnection` to display the values of its fields.

3. Properties Files:
application.properties:
```
my.db.driver=Oracle
my.db.url=jdbc-oracle
my.db.user=vikash
my.db.pwd=shivam
```
application-qa.properties:
```
my.db.driver=MySQL
my.db.url=jdbc-mysql
my.db.user=vikash
my.db.pwd=shivam
```
application-prod.properties:
```
my.db.driver=Postgress
my.db.url=jdbc-PSG
my.db.user=vikash
my.db.pwd=shivam
```
These properties files contain the configurations for different environments. Each file is associated with a specific profile (default, qa, prod), and the properties within these files specify the database driver, URL, username, and password for the corresponding environment.

Explanation:
- The `MyDbConnection` class is a Spring Bean annotated with `@Component`, allowing it to be managed by the Spring container. The `@ConfigurationProperties` annotation binds the properties defined in the configuration files to the fields of this class.
- The `TestDataRunner` class is annotated with `@Component` and implements `CommandLineRunner`. It is injected with an instance of `MyDbConnection` using `@Autowired`.
- In the `run` method of `TestDataRunner`, we print the `MyDbConnection` object to display its values.
- The properties files contain environment-specific configurations

 for the database connection, with each file associated with a particular profile.

Conclusion:
In this example, we have created a Spring Bean, `MyDbConnection`, to hold the database connection properties. We have used Spring Boot profiles and the `@ConfigurationProperties` annotation to automatically load the appropriate configuration based on the active profile. The `TestDataRunner` class demonstrates the usage of the `MyDbConnection` bean within a Spring Boot application. By switching the active profile, we can easily change the database connection properties without modifying the code, facilitating easy deployment across different environments.

<br/>
<br/>
<br/>

# We can run in 3 ways

Let's go through each point and provide a detailed explanation of the three ways to run a Spring Boot application with different profiles.

**1. Using Spring Boot Tab:**
To run the application with a specific profile using the Spring Boot tab, follow these steps:
- Right-click on the Main class of your Spring Boot application.
- Go to "Run as" and select "Run Configuration."
- In the Run Configuration dialog, navigate to the "Spring Boot" tab.
- Under "Profiles," choose the desired profile from the dropdown options.
- Click "Apply" to save the configuration.
- Click "Run" to start the application with the selected profile.

This method allows you to configure the active profile directly within the Run Configuration for your application in the development environment.

**2. Using External Arguments:**
To run the application with a specific profile using external arguments, follow these steps:
- Right-click on the Main class of your Spring Boot application.
- Go to "Run as" and select "Run Configuration."
- In the Run Configuration dialog, navigate to the "Arguments" tab.
- In the "Program Arguments" section, enter the following argument: --spring.profiles.active=qa
  (Replace "qa" with the desired profile)
- Click "Apply" to save the configuration.
- Click "Run" to start the application with the specified profile.

This method allows you to pass the active profile as an external argument to the application when running it. It is useful when you want to dynamically change the profile without modifying the Run Configuration.

**3. Build Application (Code -> .jar/.war):**
To build and run the application as a packaged .jar or .war file with a specific profile, follow these steps:
- Right-click on your project.
- Go to "Run as" and select "Maven Clean" (to clean the project build) and then "Maven Install" (to build the project and package it into a .jar or .war file).
- Once the build is successful, go to the "target" folder of your project.
- Right-click on the generated .jar or .war file, choose "Properties," and copy the location of the file.
- Open a command prompt or terminal and navigate to the location where the .jar or .war file is located.
- Execute the following command:
  ```
  java -jar <jarname>.jar --spring.profiles.active=qa
  ```
  or
  ```
  java -jar <warname>.war --spring.profiles.active=qa
  ```
  (Replace "<jarname>" or "<warname>" with the actual name of your packaged file)

This method allows you to build the Spring Boot application and run it as a standalone executable .jar or .war file with the desired profile.

Important Note:
- When running the application with a specific profile, Spring Boot looks for the properties specific to that profile. If a property is not found in the current profile, it falls back to the default profile. Additionally, if duplicate key-value pairs exist in the current profile, they can be removed or commented out, as they will be loaded from the default profile.

These three methods provide different options for running a Spring Boot application with different profiles based on your specific requirements and development environment.


<br/>
<br/>
<br/>

# Important FAQ's

Certainly! Let's go through each point and provide a detailed explanation:

**1. Which profile is loaded if we did not specify any while running the app?**<br/>
If you do not specify any profile while running the application, the default profile is loaded. In this case, the configuration properties are read from the `application.properties` file. The `application.properties` file serves as the fallback configuration file when no specific profile is active.

**2. Activating a profile that is not present: `--spring.profiles.active=sample`**
If you specify a profile that does not exist*, such as `sample`, Spring Boot will still load the default profile (`application.properties`). The application will use the fallback configuration specified in `application.properties` because the specified profile does not have a corresponding properties file.

**3. Handling missing keys in current profiles:**
If certain keys are not found in the current profile (e.g., `prod` is the active profile), Spring Boot will attempt to load those keys from the default profile (`application.properties`). This behavior ensures that the application can still function properly by using default values or fallback configurations when specific keys are not available in the current profile.

**4. Providing properties files from outside the project or jar file:**
To provide properties files from outside the project or jar file, you can use External Config, specifically Spring Cloud Config Server. Spring Cloud Config Server is a centralized configuration management tool that allows you to store and manage configuration files separately from your application. By using Spring Cloud Config Server, you can externalize your configuration files to a remote server, making them accessible to multiple applications. This enables dynamic configuration management and allows you to update properties without redeploying the application.

In summary:
- If no specific profile is specified, the default profile (`application.properties`) is loaded.
- Activating a profile that is not present still falls back to the default profile.
- When certain keys are not found in the current profile, Spring Boot loads them from the default profile.
- To provide properties files from outside the project or jar file, you can use External Config with Spring Cloud Config Server.

These points help ensure that your application can handle different profiles and fallback to default configurations when necessary. Additionally, using External Config provides flexibility and centralization for managing properties files across multiple applications.

<br/>
<br/>
<br/>


# Profiles using YAML

Let's go through each point and provide a detailed explanation:

1. Using Multiple YAML Files:
In Spring Boot, you can use multiple YAML files to define profiles. Each YAML file corresponds to a specific profile, and its filename follows the pattern `application-{profile}.yml`. For example, you can have `application-dev.yml`, `application-qa.yml`, and `application-prod.yml`. Each file contains the configuration specific to its corresponding profile. During application startup, the YAML file corresponding to the active profile is loaded, and the configuration properties defined in that file take precedence.

1. Using Single YAML File (with `---`):
In addition to using multiple YAML files, you can also define profiles within a single YAML file. To separate profiles in a single YAML file, you can use the `---` (three dash symbols) as a delimiter. Each profile's configuration is defined between the `---` delimiters. For example:
```
-------application.yml---------
key: val

---
key:val

---
key:val
--------------------------------
```
In this example, there are three profiles defined within the `application.yml` file. Each profile's configuration is specified between the `---` delimiters. The properties defined within each profile take precedence over properties defined in the previous profiles or the default profile.

Now, let's answer your additional questions:

Q) What are VM/JVM arguments (or -Dkey=val) in Java?
VM (Virtual Machine) or JVM (Java Virtual Machine) arguments are command-line options passed to the Java Virtual Machine when running a Java application. These arguments are prefixed with `-D` and allow you to set system properties for your application. For example, `-Dspring.profiles.active=qa` is a JVM argument that sets the `spring.profiles.active` system property to `qa`, activating the `qa` profile for your Spring Boot application.

Q) What are application arguments / command line args / main method inputs / String[]?
Application arguments, command-line arguments, main method inputs, or `String[]` represent the arguments passed to the `main` method of a Java application. These arguments are provided when executing the application from the command line. They allow you to pass inputs or parameters to your application at runtime. For example, `java MyApp arg1 arg2` would execute the `MyApp` Java application with two command-line arguments, which can be accessed within the `main` method through the `String[]` parameter.

In summary:
- Using multiple YAML files with `application-{profile}.yml` allows you to define separate configurations for different profiles.
- A single YAML file with `---` delimiters can be used to define profiles within the same file, separating their configurations.
- VM/JVM arguments (`-Dkey=val`) are used to set system properties for the Java application, such as activating a specific profile (`-Dspring.profiles.active=qa`).
- Application arguments, or `String[]` in the `main` method, represent the inputs or parameters passed to the application when executing it from the command line.

Understanding these concepts will help you effectively manage profiles and configurations in your Spring Boot application and pass necessary arguments during runtime.

<br/>
<br/>
<br/>

```yaml
    ==============code===========================
    // classes are same as before

    -YAML Files-

    1. application.yml
    my:
    db:
        driver: Oracle
        pwd: shivam
        url: jdbc-oracle
        user: vikash

    2. application-qa.yml
    my:
    db:
        driver: MySQL
        pwd: shivam
        url: jdbc-mysql
        user: vikash

    3. application-prod.yml
    my:
    db:
        driver: Postgress
        url: jdbc-PSG


    --- all in one yaml File---------------

        application.yml

    my:
    db:
        driver: Oracle
        pwd: shivam
        url: jdbc-oracle
        user: vikash
    ---
    spring:
    profiles: qa
        
    my:
    db:
        driver: MySQL
        pwd: shivam
        url: jdbc-mysql
        user: vikash
    ---
    spring:
    profiles: prod
    
    my:
    db:
        driver: Postgress
        pwd: shivam
        url: jdbc-psg
        user: vikash    


```

Let's go through the provided code and YAML configurations and provide a detailed explanation:

1. Code:
The code consists of two classes: `MyDbConnection` and `TestDataRunner`. `MyDbConnection` is a Spring bean that represents a database connection, and `TestDataRunner` is a command-line runner that retrieves and prints the database connection information.

1. YAML Files:
a. `application.yml`:
The `application.yml` file contains the configuration properties for the default profile. It defines the properties for the `my.db` namespace, including the driver, password, URL, and username for the database connection.

b. `application-qa.yml`:
The `application-qa.yml` file contains the configuration properties for the `qa` profile. It overrides the driver property with a different value, while keeping the other properties the same as the default profile.

c. `application-prod.yml`:
The `application-prod.yml` file contains the configuration properties for the `prod` profile. It overrides the driver and URL properties with different values, while keeping the other properties the same as the default profile.

3. All-in-One YAML File:
The all-in-one YAML file combines multiple profiles within a single file, separated by `---` delimiters. Each profile contains its own set of configuration properties.

a. The default profile configuration is the same as the `application.yml` file.<br/><br/>
b. The `qa` profile configuration begins with the `spring.profiles` property set to `qa`, indicating the active profile. It then overrides the driver, password, URL, and username properties for the `my.db` namespace.<br/><br/>
c. The `prod` profile configuration begins with the `spring.profiles` property set to `prod`, indicating the active profile. It then overrides the driver, password, URL, and username properties for the `my.db` namespace.<br/><br/>

In conclusion:
The provided code demonstrates how to configure different profiles using YAML files. You can define separate YAML files for each profile or use a single YAML file with multiple profiles separated by `---` delimiters. Each profile can override specific configuration properties while keeping the rest of the properties intact. The Spring Boot application will load the appropriate profile based on the active profile configuration specified during runtime or through the application's configuration. This approach provides flexibility in managing different configurations for various environments or deployment scenarios.

<br/>
<br/>
<br/>

**Q) Can we define multiple profiles data in a single properties file like YAML file?**

A. No, as of now, it is not possible to define multiple profiles' data in a single properties file in the same way as you can in a YAML file. YAML supports the use of `---` delimiters to separate profiles within a single file, allowing you to define different configurations for each profile. However, properties files do not have a built-in mechanism to handle multiple profiles within a single file.

In properties files, each key-value pair represents a configuration property, and there is no direct way to specify profiles. To define profiles in properties files, you typically create separate files for each profile, such as `application-dev.properties`, `application-qa.properties`, and `application-prod.properties`, and each file contains the configuration properties specific to its corresponding profile.

YAML provides a more structured and hierarchical format for configuration, making it easier to define multiple profiles within a single file. It offers a clearer organization of properties and allows for better readability and maintainability. If you need to define multiple profiles with distinct configurations, using YAML files would be a more suitable choice.

However, it's important to note that you can still use properties files for each profile and achieve the same result as YAML. It's just that properties files would be separated into different files rather than being combined into a single file like YAML.

# Here are 15 frequently asked questions (FAQs) related to Spring Boot profiles, along with their answers and explanations:

**Q1: What are Spring Boot profiles?**<br/>
A1: Spring Boot profiles allow you to configure and manage different environments or deployment scenarios within a single application by providing a way to define and activate different sets of configuration properties.

**Q2: How do Spring Boot profiles work?**<br/>
A2: Spring Boot profiles work by segregating configuration properties based on different profiles. During application startup, the active profile is determined, and the corresponding set of configuration properties is loaded and used by the application.

**Q3: How can I define a profile in Spring Boot?**<br/>
A3: Profiles can be defined in Spring Boot by using different configuration files, such as `application-{profile}.properties` or `application-{profile}.yml`, where `{profile}` represents the profile name.

**Q4: How can I activate a specific profile in Spring Boot?**<br/>
A4: Profiles can be activated in several ways, such as setting the `spring.profiles.active` property in the `application.properties` file, passing the `--spring.profiles.active` command-line argument, or using JVM system properties.

**Q5: What is the default profile in Spring Boot?**<br/>
A5: The default profile in Spring Boot is the profile that is used when no specific profile is activated. By convention, it is usually defined in the `application.properties` file.

**Q6: How can I override specific properties for a profile?**<br/>
A6: To override properties for a specific profile, you can create a profile-specific configuration file (e.g., `application-{profile}.properties` or `application-{profile}.yml`) and define the overridden properties in that file. These properties will take precedence over the ones defined in the default configuration.

**Q7: Can I have multiple active profiles in Spring Boot?**<br/>
A7: No, in Spring Boot, only one profile can be active at a time. However, you can define a hierarchy of profiles, where one profile extends another, allowing you to inherit and override configuration properties.

**Q8: How can I define a profile hierarchy in Spring Boot?**<br/>
A8: Profile hierarchies can be defined by using the `spring.profiles.include` property in the `application.properties` file. By specifying the profiles to include, you can inherit properties from one profile to another.

**Q9: How can I set default values for properties in profiles?**<br/>
A9: Default values for properties can be set in the `application.properties` or `application.yml` file. These values will be used if the corresponding property is not defined in the active profile.

**Q10: Can I use conditional annotations based on profiles?**<br/>
A10: Yes, Spring Boot provides conditional annotations, such as `@ConditionalOnProperty` or `@Profile`, which allow you to conditionally enable or disable beans based on the active profiles or specific properties.

**Q11: How can I access profile-specific properties in my code?**<br/>
A11: You can access profile-specific properties by using the `@Value` annotation or by injecting the `Environment` object and retrieving the properties using the `getProperty()` method.

**Q12: Can I have different database configurations for different profiles?**<br/>
A12: Yes, you can define separate database configurations for different profiles by providing profile-specific properties for the database connection, such as the URL, username, and password.

**Q13: How can I dynamically change the active profile at runtime?**<br/>
A13: The active profile can be dynamically changed at runtime by modifying the `spring.profiles.active` property, either by updating the `application.properties` file or by using the appropriate API to modify system properties.

**Q14: Can I use profiles in integration testing?**<br/>
A14: Yes, profiles can be used in integration testing to provide different configurations for each test environment. You can activate a specific profile for your tests and define the corresponding configuration properties.

**Q15: Are profiles limited to configuration properties only?**<br/>
A15: No, profiles can be used to configure various aspects of your Spring Boot application, including properties, component scanning, logging levels, and more. Profiles provide a way to customize and adapt the application's behavior based on the active environment.
