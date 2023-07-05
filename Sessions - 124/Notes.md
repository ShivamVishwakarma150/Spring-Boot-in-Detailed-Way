# Apache Maven 

**1. Development: ___.java**
In the context of Apache Maven, this refers to the source code files written in the Java programming language. The ".java" extension is used for Java source files. These files contain the actual code that developers write to build their applications.

**2. Compile: ___.class**
After writing the Java source code, it needs to be compiled into bytecode that can be executed by the Java Virtual Machine (JVM). The Java compiler converts the ".java" files into ".class" files, which contain the compiled bytecode. These files are platform-independent and can be executed on any system with a compatible JVM.

**3. Build: ___.jar / __.war / ___.ear**
Building refers to the process of packaging the compiled Java code, along with any required dependencies and resources, into a distributable format. Apache Maven supports different types of build artifacts: JAR, WAR, and EAR.
<br/>

- JAR (Java Archive): A JAR file is a package format that bundles multiple compiled Java class files, along with associated resources such as images, configuration files, and libraries, into a single file. JAR files are commonly used for libraries, modules, or standalone Java applications.

- WAR (Web Archive): A WAR file is a package format specifically designed for web applications. It contains all the necessary components of a web application, including HTML, CSS, JavaScript, servlets (Java classes that handle HTTP requests and responses), JSP files, images, and libraries. A WAR file can be deployed on a web server to run the web application.

- EAR (Enterprise Archive): An EAR file is an archive format used for enterprise applications. It typically includes multiple components, such as EJBs (Enterprise JavaBeans) for business logic, web modules (WAR files) for the presentation layer, and libraries used by the application. EAR files are used in Java EE (Enterprise Edition) applications.

**1. JAR is a collection of .class files**
As mentioned earlier, a JAR file is a format that contains compiled Java class files. It serves as a container for multiple ".class" files, along with any associated resources. JAR files are widely used for packaging and distributing reusable Java libraries or applications.

**2. Dependency: Required JAR files for the application are called dependencies**
In software development, a dependency refers to a requirement or reliance on external components or libraries. In the context of Apache Maven, a dependency is a JAR file (or any other artifact) that a project needs to compile, build, or run successfully. Maven manages these dependencies automatically by downloading them from remote repositories and incorporating them into the project's build.

**3. Dependency Chain:** One JAR can internally use other JAR classes; those JARs are called a dependency chain
A dependency chain in Maven refers to a series of interdependent dependencies. It occurs when a JAR file depends on other JAR files, which in turn may depend on further JAR files, forming a chain of dependencies. Maven resolves and manages these dependencies recursively, ensuring that all required JARs are downloaded and included in the build process. This allows applications to utilize functionality from external libraries without the need to manually manage and include each dependency individually.

By understanding these concepts, you can effectively develop, compile, and build Java applications using Apache Maven, manage dependencies, and utilize external libraries to enhance your projects.

Sure! Let's dive into each point and provide a detailed explanation:

**1. Project Folder System:**
Maven follows a specific folder structure convention that helps organize the project's source code and resources. The main folders are:

- src/main/java: This folder contains the development files for your project, typically written in Java. It holds the ".java" files where you write the code for your application's functionalities.

- src/main/resources: In this folder, you can place non-Java development files such as XML, YAML, or configuration files that are required by your application. These files are often used for storing application settings, database configurations, or other resources needed during runtime.

- src/test/java: Maven also includes a dedicated folder for unit tests. Here, you write test code in Java to verify the functionality of your application's components. The ".java" files in this folder focus on testing rather than actual application development.

- src/test/resources: Similar to the main resources folder, this folder contains non-Java test resources like XML or configuration files specifically used during the unit testing process.

**2. All Required Dependencies [Dependency Chain]:**
Dependencies are external libraries or modules that your project relies on to function correctly. Maven manages these dependencies, and it can handle complex dependency chains where one library depends on another, and so on. By specifying the required dependencies in the project's configuration file (pom.xml), Maven automatically downloads and includes them in the build process. This ensures that your project has all the necessary dependencies available during compilation, testing, and execution.

**3. Compile, Test Code:**
Maven provides a built-in mechanism to compile and test your code. When you run the appropriate Maven commands, it compiles the Java source code (located in src/main/java) into bytecode, which is executed by the Java Virtual Machine (JVM). Additionally, it executes the unit tests written in the src/test/java folder to verify the correctness of your code. Maven generates comprehensive test reports, allowing you to identify any failures or issues.

**4. Build Our Project (.jar/.war):**
Building a project in Maven refers to the process of packaging the compiled code and resources into a distributable format, such as a JAR or WAR file. The build output is placed in the target folder by default. The naming convention for the built artifacts follows the format: `<artifactId>-<version>.<extension>`. For example, if your project's artifactId is "myapp" and the version is "1.0," the resulting JAR file will be named "myapp-1.0.jar." This artifact can then be distributed and deployed to a server or used as a library in other projects.

Maven Folder System:
The Maven folder system, as mentioned, organizes your project's source code and resources into specific directories. It follows a convention that simplifies project management and build processes.

pom.xml [Project Object Model]:
The pom.xml file is a crucial part of Maven. It defines the project's configuration and provides information on how the project should be built, packaged, and deployed. It specifies details such as the project's groupId (which typically corresponds to the organization's domain name), artifactId (the unique identifier for the project), version, dependencies, plugins, and other build-related settings. The pom.xml file serves as a central configuration file that Maven refers to when executing various commands.

Types of Maven Applications:
Maven supports different types of applications based on specific project archetypes or templates. The two main types are:

1. Simple Maven Project (Stand-alone):
This type refers to a basic Maven project structure that can be used for any type of standalone application, such as a command-line tool or a small utility program. It provides the necessary folders and configuration files to get started with a minimal setup.

2. Archetype Project (Web, template, etc.):
An archetype is a project template that provides a pre-configured structure for a specific type of application. Maven offers various archetypes for different purposes, such as web applications, enterprise applications, or specific frameworks (e.g., Spring). These archetypes provide a starting point with pre-defined configurations and dependencies tailored to the chosen application type.

By understanding and utilizing the Maven folder system, managing dependencies, configuring the pom.xml file, and exploring different project types, you can effectively leverage Maven as a powerful project management tool for building Java applications.

<br/>
<br/>

# Here's the code with explanations:

1. `pom.xml`:
```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>

<dependencies>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.32</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.9.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
Explanation: 
- The `<properties>` section sets the source and target compiler versions to Java 11.
- The `<dependencies>` section includes two dependencies:
  - MySQL Connector/J: It is a dependency for connecting to a MySQL database. The groupId is "com.mysql," artifactId is "mysql-connector-java," and version is "8.0.32."
  - JUnit Jupiter Engine: It is a dependency for the JUnit 5 testing framework. The groupId is "org.junit.jupiter," artifactId is "junit-jupiter-engine," version is "5.9.1," and it is marked with the "test" scope, indicating it is used for testing purposes.

2. `DatabaseConnection.java`:
```java
package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/boot7am",
                "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return con;
    }
}
```
Explanation:
- The `DatabaseConnection` class represents a utility for establishing a database connection.
- The `getConnection()` method tries to establish a connection to a MySQL database using the `DriverManager.getConnection()` method.
- The connection URL specifies the database as "boot7am" on localhost at port 3306, with the username and password set to "root."
- If an exception occurs during the connection process, it is printed to the console.

3. `TestDatabaseConnection.java`:
```java
package com.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

public class TestDatabaseConnection {
    
    @Test
    public void testGetConnection() {
        DatabaseConnection dbc = new DatabaseConnection();
        Connection con = dbc.getConnection();
        assertNotNull(con);
    }
}
```
Explanation:
- The `TestDatabaseConnection` class contains a unit test for the `DatabaseConnection` class using JUnit Jupiter.
- The `testGetConnection()` method is annotated with `@Test` to indicate that it is a test case.
- Inside the test method, an instance of `DatabaseConnection` is created.
- The `getConnection()` method is called to obtain a Connection object.
- The `assertNotNull()` method is used to verify that the obtained Connection object is not null, indicating a successful connection.

By running the Maven build, which includes compiling the code, executing the test, and packaging the project, you can ensure that the database connection utility works correctly and the unit test passes.

This code example demonstrates the use of Maven to manage dependencies, including external libraries (such as the MySQL Connector/J) and testing frameworks (JUnit Jupiter). It showcases how Maven simplifies the build process by handling dependencies, compiling code, executing tests, and packaging the project.