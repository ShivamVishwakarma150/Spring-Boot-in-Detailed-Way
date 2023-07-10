# Apache Camel Overview

**1. Integrate two different machines/Systems and transfer data:** Apache Camel is a software framework that facilitates the integration of different systems and allows for seamless data transfer between them. It provides a lightweight and flexible architecture for connecting disparate systems and enabling communication and data exchange.

**2. Open Source API, used to transfer data between systems:** Apache Camel is an open-source integration framework that offers a rich set of components and connectors to facilitate data transfer between various systems. It provides an extensive collection of pre-built connectors and APIs for interacting with different protocols, data formats, and systems.

**3. Supports different protocols HTTP, FTP, FILE, JMS, JDBC, etc.:** Apache Camel supports a wide range of communication protocols, making it versatile for integrating diverse systems. It includes built-in components for protocols like HTTP, FTP, FILE (local file system), JMS (Java Message Service), JDBC (Java Database Connectivity), and many others. These components allow Camel to interact with systems using these protocols seamlessly.

**4. Supports integration with different languages Hadoop, Python, etc.:** Apache Camel supports integration with different programming languages, enabling developers to leverage their preferred language for implementing integration logic. It provides various components and libraries for integrating with languages such as Java, Python, Hadoop, and others, making it easier to connect and exchange data with systems implemented in those languages.

**5. Supports different data formats processing/filtering, XML, JSON, text, etc.:** Apache Camel is designed to handle a variety of data formats, making it flexible for processing, filtering, and transforming data between systems. It provides built-in support for popular data formats like XML, JSON, plain text, CSV, and more. Camel's data transformation capabilities allow you to convert data from one format to another, perform filtering based on specific criteria, and apply transformations or calculations to the data as needed.

## **Camel software supports three types of operations:**

A. **Routing:** Camel's primary function is routing, which involves transferring data from a source system to a destination system. It provides a routing engine that allows you to define routes, which specify the source, destination, and any intermediate processing steps required for data transfer. Camel routes are typically configured using a domain-specific language (DSL) or XML-based configuration.

B. **Filtering:** Camel allows you to apply filters to data during routing to exclude or include specific data based on conditions. Filters in Camel help you define rules or criteria to determine which data should be processed or forwarded to the destination. This filtering capability allows you to handle only the relevant data and ignore unwanted or irrelevant data during integration.

C. **Processing:** Apache Camel supports data processing operations, enabling you to modify, transform, or perform calculations on the data being transferred. You can define processors within Camel routes to manipulate the data as it flows through the integration. Processors can be written in various languages, including Java, Groovy, or using scripting languages like JavaScript or Python, depending on your preference.

**Camel uses EIP (Enterprise Integration Patterns) concept to implement the above operations:** Apache Camel is based on the principles of Enterprise Integration Patterns (EIP), which are proven patterns for solving common integration problems. EIP provides a set of reusable patterns and best practices for designing and implementing integration solutions. Camel leverages these patterns to simplify the development of integration solutions by providing pre-built components and abstractions that align with the EIP concepts. This makes it easier to understand and implement complex integration scenarios using Camel.

By combining the capabilities of Apache Camel with its support for various protocols, data formats, languages, and the utilization of EIP patterns, developers can efficiently integrate different systems, transfer data seamlessly, and perform necessary transformations and processing operations along the way.

<br/>
<br/>

**Spring Boot supports Integration concept using Camel:**
Spring Boot, a popular Java framework, provides support for integrating Apache Camel. This means you can leverage Camel's capabilities within your Spring Boot applications to facilitate seamless integration between different systems and components.

**To use Camel in a Spring Boot project, you need to add the Apache Camel dependency in the pom.xml file:**
In your project's pom.xml file, you need to include the Apache Camel dependency to utilize Camel's features and functionality. The following dependency entry can be added:

```xml
<dependency>
    <groupId>org.apache.camel.springboot</groupId>
    <artifactId>camel-spring-boot-starter</artifactId>
    <version>3.20.2</version>
</dependency>
```

This ensures that the required Camel libraries are included in your project.

**#1 We need to define one class for Routing that implements RouteBuilder(AC) contains one abstract method:**
To define a Camel route in your Spring Boot application, you need to create a class that implements the RouteBuilder interface. This class should include the following abstract method that you need to override:

```java
public abstract void configure() throws Exception;
```

Inside this method, you define your Camel routes and configure the necessary integration components and processors.

**#2 Define EIP inside the configure() method:**
Inside the configure() method of your RouteBuilder class, you define the Enterprise Integration Patterns (EIP) that represent the desired integration flow. EIPs are a set of widely used patterns for designing and implementing integration solutions. Camel provides a rich set of EIPs that you can use to define the routing and transformation logic of your integration flows. Examples of EIPs include routing patterns like content-based routing, filtering patterns like message filtering, and transformation patterns like message transformation.

By utilizing EIPs, you can easily implement complex integration scenarios and define the desired behavior of your Camel routes.

**#3 When we stop the main class/app, also stop routing:**
By default, when you start a Spring Boot application with Camel integration, the Camel routes continue running until the application is manually stopped. To enable automatic stopping of Camel routes when the main class or application is stopped, you can add the following property to your application.properties or application.yml file:

```
camel.springboot.main-run-controller=true
```

Enabling this property ensures that when the main class or application is stopped, Camel routes are gracefully shut down and stopped as well.

By following these steps and leveraging Spring Boot's integration with Camel, you can seamlessly incorporate Camel's routing capabilities into your Spring Boot application, define integration flows using EIPs, and have control over the lifecycle of your Camel routes within the application. This enables you to build robust and efficient integration solutions using Camel and Spring Boot.

<br/>
<br/>

# Code and Steps Explanation


**#1 Routing Files from Source to Destination:**

```java
package com.app.shivam.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFilesRouting extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:D:\\source").to("file:D:\\desti");
	}
}
```

In the above code snippet, we have a Camel route that transfers files from the source directory `D:\\source` to the destination directory `D:\\desti`.

**#2 Avoid Overriding Files by Adding "?noop=true":**

```java
from("file:D:\\source?noop=true").to("file:D:\\desti");
```

By adding `?noop=true` to the source file endpoint, we ensure that new files with the same name do not overwrite existing files at the destination. The `noop=true` option stands for "no operation" and prevents the override behavior.

**#3 Reading Locations from Properties File:**

--application.properties--

```properties
my.app.source=file:D:\\source?noop=true
my.app.desti=file:D:\\desti
```

--configure method--

```java
from("{{my.app.source}}").to("{{my.app.desti}}");
```

In this example, we externalize the source and destination locations by defining them in the `application.properties` file. We use `{{key}}` notation to reference the properties in the Camel route's `configure()` method. This allows flexibility in changing the locations without modifying the code.

**#4 Integrating ActiveMQ with File:**

A) In pom.xml, we include the necessary dependencies for ActiveMQ and Camel:

```xml
<!-- Apache Camel and ActiveMQ -->
<dependency>
    <groupId>org.apache.camel.springboot</groupId>
    <artifactId>camel-spring-boot-starter</artifactId>
    <version>3.20.2</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>

<dependency>
    <groupId>org.apache.activemq</groupId>
    <artifactId>activemq-pool</artifactId>
</dependency>

<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-jms</artifactId>
    <version>3.20.2</version>
</dependency>
```

B) In the properties file, we configure the ActiveMQ connection details:

```properties
# When we stop the main class/app, also stop routing
camel.springboot.main-run-controller=true

# Apache ActiveMQ
spring.activemq.broker-url=tcp://localhost:61616
spring.activemq.user=admin
spring.activemq.password=admin
```

C) Router class to route messages from ActiveMQ to a file:

```java
package com.app.shivam.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyMqFileRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jms:queue:indata").to("file:D:/desti?fileName=info.txt");
	}
}
```

D) Start ActiveMQ and create a queue named "indata".
E) Click on "sendTo" and enter a message, then click "send".

**#5 Connecting to a Database: JDBC**

In the pom.xml file, we add the necessary dependencies for Camel and JDBC:

```xml
<!-- Camel and JDBC -->
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-jdbc</artifactId>
    <version>3.20.2</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

We configure the database connection details in the `AppConfig` class:

```java
package com.app.shivam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/cameldb");
        ds.setUsername("root");
        ds.setPassword("Shivam@123");
        return ds;
    }
}
```

By providing the appropriate JDBC URL, username, and password, we establish a connection with the MySQL database.

In conclusion, the code snippets above demonstrate various integration scenarios using Apache Camel. We have explored routing files, avoiding file overrides, reading locations from properties files, integrating ActiveMQ with file transfer, and connecting to a database. These examples showcase the power and flexibility of Apache Camel in simplifying integration tasks and enabling seamless data transfer between systems.

<br/>
<br/>


**MySQL Commands:**
```sql
mysql> create database cameldb;
Query OK, 1 row affected (0.02 sec)

mysql> use cameldb;
Database changed

mysql> create table users(uid int, uname varchar(20), urole varchar(20));
Query OK, 0 rows affected (0.06 sec)

mysql> insert into users values(10,'A','ADMIN');
Query OK, 1 row affected (0.01 sec)

mysql> insert into users values(11,'B','CUSTOMER');
Query OK, 1 row affected (0.05 sec)

mysql> commit;
Query OK, 0 rows affected (0.00 sec)
```

These MySQL commands create a database called `cameldb` and a table called `users`. Some sample data is inserted into the `users` table for demonstration purposes.

**Router Class for JDBC to File Integration:**
```java
package com.app.shivam.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyJdbcToFileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:/timer1?repeatCount=1")
        .setBody(constant("select * from users"))
        .to("jdbc:dataSource")
        .process(ex -> {
            String s = ex.getMessage().getBody(String.class);
            System.out.println(s);
            ex.getMessage().setBody(s);
        })
        .to("file:d:/desti?fileName=aa.txt");
    }
}
```

In the above code snippet, we have a Camel route that integrates JDBC (Java Database Connectivity) with file transfer. Here's a breakdown of the route:

1. The route starts with a timer component that triggers the route execution once (`repeatCount=1`).
2. The `setBody` method is used to set a constant SQL query as the body of the message. In this case, we're selecting all records from the `users` table.
3. The message is then sent to the `jdbc` component, which executes the SQL query using the configured `dataSource` bean.
4. A processor (`process`) is used to manipulate the body of the message. In this example, we simply print the body to the console and set it back as the message body.
5. Finally, the message is written to a file in the `d:/desti` directory with the filename `aa.txt`.

**Conclusion:**
In this example, we demonstrated how to integrate JDBC with file transfer using Apache Camel. The route retrieves data from a MySQL database table (`users`) using the JDBC component, performs a simple processing step, and then writes the data to a file. This showcases the flexibility and ease of integration that Apache Camel provides, allowing seamless interaction between databases and other systems.

By following the provided MySQL commands and implementing the router class, you can integrate your Spring Boot application with a MySQL database, retrieve data, and perform various operations based on your requirements.