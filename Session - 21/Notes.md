
# YAML

SnakeYAML is a popular Java library used for parsing and working with YAML files. It provides APIs to read, write, and manipulate YAML data in Java.

When using Spring Boot, the YAML format is often preferred over the properties format due to its more human-readable and structured nature. However, it's important to note that if both a properties file and a YAML file with the same configuration properties exist, the properties file takes precedence. This means that the values from the properties file will override any corresponding values in the YAML file.

Here are a few examples of YAML configurations commonly used in Spring Boot applications:

1. Simple Key-Value Pair:
   ```yaml
   myKey: myValue
   ```

2. List/Array:
   ```yaml
   myList:
     - value1
     - value2
     - value3
   ```

3. Map/Properties:
   ```yaml
   myMap:
     key1: value1
     key2: value2
     key3: value3
   ```

4. Nested Structures:
   ```yaml
   myObject:
     subObject:
       subKey1: subValue1
       subKey2: subValue2
     subList:
       - subValue1
       - subValue2
   ```

5. Profiles:
   ```yaml
   spring:
     profiles:
       active: dev
   ---
   spring:
     profiles: dev
     datasource:
       url: jdbc:mysql://localhost:3306/mydb
   ---
   spring:
     profiles: prod
     datasource:
       url: jdbc:mysql://production-server/mydb
   ```

In the example above, different profiles are defined with specific configurations for each profile. The active profile determines which configuration is loaded.

These are just a few examples of how YAML can be used to configure Spring Boot applications. YAML's flexibility and readability make it a popular choice for defining application configurations.

Remember that the YAML files are parsed and loaded into the Spring Container environment, where they can be accessed and utilized by the application components.

<br/>
<br/>
<br/>

```java
====Example#1===========================
    //1. Spring Bean
    package com.app.shivam;

    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.stereotype.Component;

    @ConfigurationProperties("my.app")
    @Component
    public class EmailConfig {

        private String host;
        private int port;
        private String username;
        private String pasword;
        
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
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPasword() {
            return pasword;
        }
        public void setPasword(String pasword) {
            this.pasword = pasword;
        }
        @Override
        public String toString() {
            return "EmailConfig [host=" + host + ", port=" + port + ", username=" + username + ", pasword=" + pasword + "]";
        }
        
        
    }

    ----
    //2. application.yml
    //> right click on src/main/resources > new > file > enter name
    //> finish

    //> Right click on application.properties > Refactor > Rename (F2)
    //> Enter application.yml > finish
    -------application.yml-------------
    my:
    app:
        host: smtp.gmail.com
        port: 885
        username: sample
        pasword: vikash
    ------------------------------

    //3. Runner class
    package com.app.shivam;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.stereotype.Component;

    @Component
    public class CheckObjRunner implements CommandLineRunner {

        @Autowired
        private EmailConfig em;
        
        public void run(String... args) throws Exception {
            System.out.println(em);
        }

    }

```

Let's go through the example code and provide a detailed explanation, followed by a conclusion:

1. The `EmailConfig` class is a Spring bean annotated with `@ConfigurationProperties("my.app")`. This annotation indicates that the properties for this class should be bound to the configuration properties with the prefix "my.app". The `EmailConfig` class has properties such as `host`, `port`, `username`, and `pasword` (typo: should be `password`).

2. In the `application.yml` file, the configuration properties for the `EmailConfig` class are defined. The properties are nested under the `my.app` prefix. The `host`, `port`, `username`, and `pasword` (typo: should be `password`) properties are assigned values.

3. The `CheckObjRunner` class is a Spring bean annotated with `@Component` and implements `CommandLineRunner`. It is responsible for running the code that prints the `EmailConfig` object after it has been populated with the values from the configuration properties.

4. In the `run` method of `CheckObjRunner`, the `EmailConfig` object `em` is autowired. The Spring framework will inject the populated instance of `EmailConfig` into the `em` variable.

5. Finally, in the `run` method, the `EmailConfig` object is printed using `System.out.println(em)`. This will display the values of the properties in the `EmailConfig` object, including the bound configuration properties.

Conclusion:
In this example, the `EmailConfig` class represents a configuration object for email settings. The properties of the `EmailConfig` class are bound to the corresponding configuration properties defined in the `application.yml` file using the `@ConfigurationProperties` annotation. The `CheckObjRunner` class demonstrates how the populated `EmailConfig` object can be accessed and used within the Spring Boot application.

By following this setup, the `EmailConfig` object will be instantiated, and its properties will be populated with values from the `application.yml` file. The `CheckObjRunner` class ensures that the populated object is available and can be accessed in the `run` method.

Overall, this example showcases how YAML configuration properties can be used in a Spring Boot application to populate and utilize a configuration object. YAML provides a more readable and structured format compared to traditional properties files, making it a preferred choice in many Spring Boot applications.

<br/>
<br/>
<br/>

# Notes YAML

1. If we define a YAML file in an invalid format, the container or the SnakeYAML library used for parsing YAML will throw exceptions like `ScannerException` or `ParserException`. These exceptions indicate that there was an issue while reading or parsing the YAML file. Common causes of these exceptions include syntax errors, indentation errors, or incorrect YAML structure. It's important to ensure that the YAML file follows the correct format to avoid these exceptions.

2. In the case of a properties file, if we define a duplicate key with different values, the behavior is that the last combination of key and value is loaded. This means that the value associated with the duplicate key in the properties file will overwrite the previous value. However, in a YAML file, if we define a duplicate key, a `DuplicateKeyException` is thrown. This exception indicates that there are multiple occurrences of the same key, which is not allowed in YAML. Each key should be unique within the YAML file.

3. When a key is present in a properties file and a corresponding setter method is not found in the code, the variable associated with the key will hold its default value. In properties-based configuration, if a setter method is not found for a property, the framework will assign the default value of the corresponding variable type. This behavior allows the application to continue running even if the setter method is missing.

However, in a YAML file, if a key is present and a setter method is not found in the code, an `IllegalStateException` is thrown. This exception indicates that there is no setter method available to bind the YAML property to the corresponding variable. The presence of the key in the YAML file without a corresponding setter method indicates a misconfiguration or missing code. To resolve this, you need to provide a setter method for the property or remove the key from the YAML file if it's not needed.

In summary, YAML files have stricter rules and validations compared to properties files. YAML expects a valid syntax and structure, and it enforces unique keys. If there are issues with the YAML file format or key-value bindings, exceptions will be thrown to highlight the problems. It's important to ensure that the YAML file follows the correct format and that the necessary setter methods are available for binding the properties to the corresponding variables in the code.

<br/>
<br/>
<br/>

```java

====Ex#2=============================================================
    //1. Spring Bean
    package com.app.shivam;

    import java.util.Map;
    import java.util.Set;

    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.stereotype.Component;

    @ConfigurationProperties("my.app")
    @Component
    public class EmailConfig {

        private String host;
        private int port;
        private String username;
        private String pasword;
        
        private Set<String> protocols;
        private Map<String,String> headers;
        
        private Certificate cob;
        
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
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPasword() {
            return pasword;
        }
        public void setPasword(String pasword) {
            this.pasword = pasword;
        }
        public Set<String> getProtocols() {
            return protocols;
        }
        public void setProtocols(Set<String> protocols) {
            this.protocols = protocols;
        }
        public Map<String, String> getHeaders() {
            return headers;
        }
        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }
        public Certificate getCob() {
            return cob;
        }
        public void setCob(Certificate cob) {
            this.cob = cob;
        }
        @Override
        public String toString() {
            return "EmailConfig [host=" + host + ", port=" + port + ", username=" + username + ", pasword=" + pasword
                    + ", protocols=" + protocols + ", headers=" + headers + ", cob=" + cob + "]";
        }
        
        
        
        
    }
    ----
    package com.app.shivam;

    public class Certificate {

        private String provider;
        private String expExist;
        
        public String getProvider() {
            return provider;
        }
        public void setProvider(String provider) {
            this.provider = provider;
        }
        public String getExpExist() {
            return expExist;
        }
        public void setExpExist(String expExist) {
            this.expExist = expExist;
        }
        @Override
        public String toString() {
            return "Certificate [provider=" + provider + ", expExist=" + expExist + "]";
        }
        
        
    }


    //2. application.yml
    /* 
    my:
    app:
        cob:
        provider: GoDaddy
        expExist: INACTIVE
        host: smtp.gmail.com
        port: 885
    #    port: 990
        username: sample
        pasword: vikash
        protocols:
        - SMTP
        - SMTPS
        - SMTP2
        headers:
        modified: true
        version: 3.2A
        content: JSON 
    */
            
    3. Runner class
    package com.app.shivam;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.stereotype.Component;

    @Component
    public class CheckObjRunner implements CommandLineRunner {

        @Autowired
        private EmailConfig em;
        
        public void run(String... args) throws Exception {
            System.out.println(em);
        }

    }

```

Let's go through the code example step by step, providing the code, explanation, and conclusion:

1. `EmailConfig` Bean:
```java
@ConfigurationProperties("my.app")
@Component
public class EmailConfig {
    // Properties
}
```
Explanation:
- This is a Spring bean class annotated with `@ConfigurationProperties` and `@Component`.
- The `@ConfigurationProperties("my.app")` annotation indicates that this bean will bind properties prefixed with `my.app` from the YAML file.
- The class contains various properties such as `host`, `port`, `username`, `password`, `protocols`, `headers`, and `cob`.
- The bean has corresponding getter and setter methods for each property.

1. `Certificate` Class:
```java
public class Certificate {
    // Properties
}
```
Explanation:
- This is a simple Java class representing the `cob` property of the `EmailConfig` bean.
- It contains properties such as `provider` and `expExist`.
- The class has corresponding getter and setter methods for each property.

1. `application.yml`:
```yaml
my:
  app:
    cob:
      provider: GoDaddy
      expExist: INACTIVE
    host: smtp.gmail.com
    port: 885
    username: sample
    password: vikash
    protocols:
      - SMTP
      - SMTPS
      - SMTP2
    headers:
      modified: true
      version: 3.2A
      content: JSON
```
Explanation:
- This YAML file contains the configuration properties for the `EmailConfig` bean.
- The properties are organized under the `my.app` prefix.
- The `cob` property is a nested object with properties `provider` and `expExist`.
- Other properties such as `host`, `port`, `username`, `password`, `protocols`, and `headers` are defined directly.

1. `CheckObjRunner` Runner:
```java
@Component
public class CheckObjRunner implements CommandLineRunner {
    // Autowired EmailConfig bean
    // run method
}
```
Explanation:
- This class is a Spring component annotated with `@Component` and implements the `CommandLineRunner` interface.
- It autowires the `EmailConfig` bean to access its configured properties.
- The `run` method prints the `EmailConfig` object to the console.

Conclusion:
In this example, the `EmailConfig` bean is configured using the `@ConfigurationProperties` annotation and properties from the `application.yml` file. The YAML file defines properties for the `EmailConfig` bean, including nested objects like `cob`, as well as collections like `protocols` and `headers`. The `CheckObjRunner` class demonstrates accessing the configured `EmailConfig` bean and printing its contents.

Overall, this example showcases how to bind YAML properties to Java objects using Spring's `@ConfigurationProperties` annotation. It allows for a structured and type-safe approach to configure beans in a Spring application.

<br/>
<br/>
<br/>

# Project LOMBOK API

Certainly! Let's go through each point and provide a detailed explanation of using Project Lombok:

1. Adding Lombok Dependency:
To use Lombok in your Maven project, you need to add the Lombok dependency to your `pom.xml` file:
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```
Explanation:
- This Maven dependency specifies the Lombok library as a project dependency.
- The Lombok library provides annotations and features that help generate source code during compilation.

2. Updating Maven Project and Writing Sample Code:
After adding the Lombok dependency, you need to update your Maven project to download the Lombok JAR file. You can then start writing your code that utilizes Lombok annotations.
Explanation:
- Updating the Maven project ensures that the Lombok JAR is downloaded and available for use in your project.
- You can then proceed to write your code, utilizing the Lombok annotations to generate boilerplate code automatically.

3. Activating Lombok for Your IDE:
To use Lombok effectively, you need to activate it in your IDE. This step may vary depending on your IDE. Here's an example for Eclipse:

- Locate the Lombok JAR file on your system. It is typically stored in the `.m2/repository/org/projectlombok/lombok` directory.
- Double-click on the Lombok JAR file (`lombok-1.18.24.jar`) or execute the command `java -jar lombok-1.18.24.jar` in the command prompt.
- If the installer does not find your IDE, click on "Specify Location" and provide the path to your IDE installation directory.
- Click on "Install/Update" to integrate Lombok with your IDE.
- Once the installation is complete, quit the installer.

4. Reopening IDE and Applying Lombok Annotations:
After activating Lombok, you need to reopen your IDE and apply Lombok annotations to your code. Lombok annotations help in automatically generating the desired boilerplate code.

Explanation:
- Reopen your IDE to ensure that the Lombok integration takes effect.
- You can now start using Lombok annotations in your code. Examples of commonly used Lombok annotations include `@Getter`, `@Setter`, `@ToString`, `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`, etc.
- These annotations eliminate the need to write repetitive code like getters, setters, constructors, and toString methods. Lombok generates this code during the compilation process.

Conclusion:
Project Lombok is a useful Java open-source API that helps generate boilerplate code during compilation, reducing the need for manual code writing. By adding the Lombok dependency, activating it in your IDE, and using Lombok annotations, you can simplify and streamline your codebase. Lombok provides a variety of annotations to generate common code patterns automatically.

Remember to consult the Lombok documentation (https://projectlombok.org/features/) for a comprehensive list of available features and annotations.

<br/>
<br/>
<br/>

Certainly! Let's go through each Lombok annotation used in the provided code and provide a detailed explanation:

1. `@Setter`:
- The `@Setter` annotation generates setter methods for the fields in the class.
- When applied to a field, it automatically generates a public setter method to set the value of that field.
- In the example code, the `@Setter` annotation is applied to the `code`, `port`, and `exist` fields of the `Process` class, generating the corresponding setter methods for these fields.

2. `@Getter`:
- The `@Getter` annotation generates getter methods for the fields in the class.
- When applied to a field, it automatically generates a public getter method to retrieve the value of that field.
- In the example code, the `@Getter` annotation is applied to the `code`, `port`, and `exist` fields of the `Process` class, generating the corresponding getter methods for these fields.

3. `@ToString`:
- The `@ToString` annotation generates an overridden `toString()` method for the class.
- By default, the `toString()` method includes a string representation of all the fields in the class.
- In the example code, the `@ToString` annotation is applied to the `Process` class, generating an overridden `toString()` method that includes the values of the `code`, `port`, and `exist` fields.

4. `@EqualsAndHashCode`:
- The `@EqualsAndHashCode` annotation generates `equals()` and `hashCode()` methods for the class.
- It automatically uses all non-static fields of the class to generate the `equals()` and `hashCode()` methods.
- In the example code, the `@EqualsAndHashCode` annotation is applied to the `Process` class, generating the `equals()` and `hashCode()` methods based on the `code`, `port`, and `exist` fields.

5. `@NoArgsConstructor`:
- The `@NoArgsConstructor` annotation generates a no-argument/default constructor for the class.
- The generated constructor initializes all fields with their default values (e.g., `null` for objects, `0` for numeric types, and `false` for boolean types).
- In the example code, the `@NoArgsConstructor` annotation is applied to the `Process` class, generating a no-argument constructor.

6. `@AllArgsConstructor`:
- The `@AllArgsConstructor` annotation generates a constructor that accepts parameters for all fields in the class.
- The generated constructor initializes all fields with the provided parameter values.
- In the example code, the `@AllArgsConstructor` annotation is applied to the `Process` class, generating a constructor that accepts parameters for the `code`, `port`, and `exist` fields.

7. `@RequiredArgsConstructor` + `@NonNull`:
- The `@RequiredArgsConstructor` annotation generates a constructor that accepts parameters for specific fields marked with the `@NonNull` annotation.
- The generated constructor initializes only the specified fields with the provided parameter values.
- In the provided code, `@RequiredArgsConstructor` is not used, but it can be combined with `@NonNull` to generate a constructor for specific fields that are required.

8. `@Data`:
- The `@Data` annotation is a convenient shortcut that combines several Lombok annotations.
- It generates getter and setter methods for all fields, overrides `toString()`, `equals()`, and `hashCode()`, and generates a constructor with all fields as parameters.
- In the example code, the `@Data` annotation is not used, but it provides a concise way to generate commonly used methods and constructors.

Conclusion:
Project Lombok provides various annotations that help reduce boilerplate code in Java classes. These annotations generate getter/setter methods, override

 methods like `toString()`, `equals()`, and `hashCode()`, and generate constructors based on specific requirements. By applying the appropriate annotations, developers can focus more on business logic and reduce the amount of repetitive code.

It's important to note that while Lombok annotations simplify code development, it's crucial to understand their impact and use them judiciously. Understanding the generated code and their implications is essential for maintaining code readability and ensuring correct behavior.


<br/>
<br/>
<br/>


# Here are frequently asked questions (FAQs) related to YAML and Properties files:

**1. Q: What is the difference between YAML and Properties files?**<br/>
   A: YAML is a human-readable data serialization format, while Properties files are simple text files used to store key-value pairs. YAML allows for more complex data structures and supports indentation-based hierarchy, while Properties files are typically flat and use a simple key-value format.

**2. Q: Can YAML files be used interchangeably with Properties files in a Spring application?**<br/>
   A: Yes, Spring applications can use either YAML or Properties files for configuration. Spring Boot provides support for both formats and allows you to choose the one that suits your preference and requirements.

**3. Q: How are values in YAML files and Properties files accessed in a Spring application?**<br/>
   A: In a Spring application, values from YAML files can be accessed using `@Value` annotations or by binding the properties to Java objects using `@ConfigurationProperties`. For Properties files, you can use `@Value` annotations or the `Environment` object to retrieve the values.

**4. Q: Can YAML files have comments?**<br/>
   A: Yes, YAML files support comments. Comments in YAML start with the `#` symbol and can be used to provide additional information or explanations within the file.

**5. Q: How are arrays or lists represented in YAML files and Properties files?**<br/>
   A: In YAML files, arrays or lists can be represented using hyphen `-` followed by the values, each on a new line with appropriate indentation. In Properties files, arrays are usually represented by using the same key with different numeric indices, such as `key[0]=value1`, `key[1]=value2`, and so on.

**6. Q: Are there any limitations on the data types that can be represented in YAML and Properties files?**<br/>
   A: YAML supports a wide range of data types, including strings, numbers, booleans, arrays, objects, and even complex data structures. Properties files, on the other hand, primarily support string-based values, although some parsers may provide limited support for other data types like numbers and booleans.

**7. Q: How are hierarchical or nested configurations represented in YAML files and Properties files?**<br/>
   A: YAML files support a hierarchical structure using indentation. Each level of indentation represents a nested level in the configuration. Properties files, being flat key-value files, do not inherently support nested configurations. However, you can use naming conventions or prefixes to indicate a hierarchical structure within the keys.

**8. Q: Can environment-specific configurations be defined in YAML and Properties files?**<br/>
   A: Yes, both YAML and Properties files can include environment-specific configurations. In Spring, you can use profiles to specify different sets of configurations for different environments and activate the appropriate profile during runtime.

**9. Q: How are placeholders or variable substitutions handled in YAML and Properties files?**<br/>
   A: Properties files support variable substitutions using the `${...}` syntax, where the variable name is enclosed within curly braces. YAML files, particularly in Spring applications, can use the same `${...}` syntax for variable substitutions, allowing you to reference values from externalized configuration properties.

**10. Q: Can I mix YAML and Properties formats in the same configuration file?**<br/>
    A: No, YAML and Properties formats cannot be mixed within the same configuration file. However, in a Spring application, you can have separate YAML and Properties files and they will be automatically merged and resolved by the configuration mechanism.

# Here are some advanced questions along with their answers related to YAML and Properties files:

**1. Q: Can I include external files or references within YAML and Properties files?**<br/>
   A: In YAML files, you can use the `!include` tag to include external files or references. This allows you to split your configuration into multiple files for better organization and reuse. Properties files do not have a built-in mechanism for including external files, but you can use placeholders to reference values defined in other files or use programmatic approaches to load and merge multiple Properties files.

**2. Q: How can I handle sensitive information, such as passwords, in YAML and Properties files?**<br/>
   A: Storing sensitive information directly in configuration files is generally not recommended. Instead, you can use placeholders or environment variables to reference sensitive values and provide them externally during runtime. Additionally, frameworks like Spring provide mechanisms for encrypting and securely storing sensitive information, allowing you to keep the actual values separate from the configuration files.

**3. Q: Are there any limitations on the file size or complexity when using YAML and Properties files?**<br/>
   A: YAML files can handle larger and more complex configurations compared to Properties files. However, extremely large or deeply nested YAML files can impact readability and maintainability. It's important to strike a balance and consider breaking down complex configurations into smaller, manageable parts. Properties files are typically limited to a key-value structure, so they may not be suitable for highly complex or hierarchical configurations.

**4. Q: Can I use conditional statements or expressions in YAML and Properties files?**<br/>
   A: YAML itself does not provide built-in support for conditional statements or expressions. However, in a Spring application, you can use SpEL (Spring Expression Language) in combination with YAML or Properties files to define conditional expressions or perform dynamic evaluations based on specific conditions or values.

**5. Q: How are defaults handled for missing values in YAML and Properties files?**<br/>
   A: In both YAML and Properties files, if a value is missing for a key, Spring Boot provides default values based on the configuration properties defined in the application or its dependencies. You can also define default values explicitly within the code using annotations like `@Value` or `@ConfigurationProperties`.

**6. Q: Can I define complex data structures like maps or objects in Properties files?**<br/>
   A: Properties files have limitations when it comes to defining complex data structures. While you can represent simple key-value pairs, defining maps or objects directly within a Properties file is not straightforward. YAML files, on the other hand, have native support for representing complex data structures, allowing you to define maps, lists, and nested objects easily.

<br/>
<br/>
<br/>

# Here are some commonly asked interview questions related to Project Lombok, along with their answers:

1. Q: What is Project Lombok?<br/>
   A: Project Lombok is a Java library that helps reduce boilerplate code by automatically generating common code structures, such as getters, setters, constructors, equals, and hashCode methods.

2. Q: What are some key annotations provided by Lombok?<br/>
   A: Some key annotations provided by Lombok include `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, `@NoArgsConstructor`, `@AllArgsConstructor`, and `@Data`.

3. Q: How does Lombok help in reducing boilerplate code?<br/>
   A: Lombok uses annotations to generate code during the compilation process. By applying Lombok annotations, developers can avoid writing repetitive code, such as getter and setter methods, and let Lombok generate them automatically.

4. Q: Can Lombok annotations be used with any Java IDE?<br/>
   A: Lombok is supported by popular Java IDEs such as Eclipse, IntelliJ IDEA, and NetBeans. However, developers need to install the Lombok plugin in their IDEs for it to recognize and handle Lombok annotations properly.

5. Q: Are Lombok-generated methods customizable?<br/>
   A: Yes, Lombok-generated methods can be customized by adding additional code or annotations. Lombok provides various options and configuration settings to control the behavior and output of the generated code.

6. Q: Does Lombok introduce any runtime dependencies?<br/>
   A: No, Lombok does not introduce any runtime dependencies. The generated code is added during the compilation process and becomes part of the compiled bytecode.

7. Q: How can you handle situations where Lombok-generated code conflicts with manually written code?<br/>
   A: Lombok provides various strategies to handle code conflicts, such as using `@Accessors(chain = true)` to enable fluent chaining or using exclusion filters to prevent Lombok from generating code for specific fields or classes.

8. Q: Can Lombok be used in conjunction with other libraries or frameworks?<br/>
   A: Yes, Lombok can be used with other libraries and frameworks. However, some libraries or frameworks may not fully support Lombok-generated code, so it's important to ensure compatibility and test the behavior when using them together.

9. Q: Are there any performance implications of using Lombok?<br/>
   A: Lombok-generated code generally has minimal performance impact, as it gets compiled into regular Java bytecode. However, it's important to use Lombok judiciously and understand the generated code to ensure it aligns with your performance requirements.

10. Q: What are the advantages and disadvantages of using Lombok?<br/>
    A: The advantages of using Lombok include reduced boilerplate code, improved code readability, and increased productivity. However, some potential disadvantages include increased compile time, IDE compatibility issues, and the need for developers to be familiar with Lombok annotations and their behavior.

