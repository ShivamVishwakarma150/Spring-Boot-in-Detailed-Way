# Database (JDBC):

Database:
A database is a storage type that is used to organize and store data. In a database, data is typically organized into tables, which consist of rows and columns. Each row represents a record, and each column represents a specific attribute or field of the data. Databases are used to manage and manipulate data efficiently, allowing for structured storage and retrieval.

JDBC (Java DataBase Connection):
JDBC is an API (Application Programming Interface) provided by Sun Microsystems (now Oracle Corp.) for Java applications to interact with databases. It enables Java programs to perform various database operations such as querying data (SELECT), inserting data (INSERT), updating data (UPDATE), deleting data (DELETE), and more.

The JDBC API includes a set of interfaces, such as Connection, Statement, PreparedStatement, CallableStatement, and ResultSet, which define methods for interacting with databases. These interfaces provide a standardized way to communicate with different database systems.

It's important to note that the JDBC API acts as a bridge between the Java application and the specific database being used. Different database vendors (such as Oracle, MySQL, PostgreSQL) provide JDBC drivers (JAR files) that implement the JDBC interfaces and handle the communication between Java and their respective database systems.

When working with JDBC, developers need to include the appropriate JDBC driver for the specific database they are using in their project. The driver is responsible for establishing the connection, executing SQL queries, and processing the results.

If you decide to switch from one database system to another, the Java code remains largely the same. However, you would need to modify certain properties such as the driver class, URL, username, and password to connect to the new database. Additionally, some SQL queries may need to be modified to accommodate the differences in syntax or supported features between database systems.

By using the JDBC API, Java developers can build robust and database-driven applications that can interact with various databases in a consistent and standardized manner.

For more detailed information about the JDBC API and its usage, you can refer to the official documentation provided by Oracle:
https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/

<br/>
<br/>
<br/>

# `JPA` Intro

JPA (Java Persistence API):
JPA is an Object-Relational Mapping (ORM) technology that allows developers to perform database operations using Object-Oriented Programming (OOP) concepts. It is a specification provided by Sun Microsystems (now Oracle) that defines a set of rules and interfaces for mapping Java objects to relational database tables.

With JPA, developers work with objects and classes rather than writing SQL queries directly. The JPA implementation, such as Hibernate, EclipseLink, or others, handles the mapping of Java objects to the underlying database tables and performs the necessary CRUD (Create, Read, Update, Delete) operations transparently.

The key concept behind ORM is to provide a bridge between the object-oriented world of Java and the relational world of databases. JPA defines how Java objects can be stored, retrieved, updated, and deleted from the database without the need to write complex SQL statements explicitly.

Some important points about JPA and ORM:

- ORM Approach: JPA follows the ORM approach, where each class in the Java application corresponds to a table in the database. Each variable or field in the class represents a column in the table, and each object of the class represents a row in the table.

- Database Independence: JPA promotes database independence by providing a standard set of interfaces and annotations that work across different database systems. This means that the same JPA-based application can be used with different databases, and the underlying SQL syntax and queries are handled by the JPA implementation.

- JPA as a Specification: JPA itself is a specification or standard that defines the rules and interfaces for implementing ORM in Java applications. Various vendors, such as Hibernate, EclipseLink, and others, provide implementations of JPA that adhere to the standard. These implementations handle the mapping of Java objects to the database and provide additional features and optimizations.

- Vendor Independence: When using JPA, developers write their Java application code based on the JPA specification. This allows for vendor independence, meaning that the code can be easily switched from one JPA implementation to another without significant code changes. Only the configuration and properties specific to the chosen JPA implementation need to be modified.

- Simplified Database Operations: JPA simplifies database operations by providing high-level APIs and annotations that handle most of the CRUD operations automatically. Developers can focus on working with Java objects and their relationships, and the JPA implementation takes care of translating these operations into the corresponding SQL statements.

By using JPA, developers can build database-driven applications using OOP principles and abstract away the complexities of dealing with SQL queries and database interactions.

It's important to note that while JPA provides a standard set of interfaces and annotations, the actual implementation used in a project, such as Hibernate, EclipseLink, or others, may provide additional features, optimizations, and configuration options specific to that implementation.

# `ORM`

Object-Relational Mapping (ORM) is an approach used in JPA to bridge the gap between the object-oriented world of Java and the relational world of databases. The key idea behind ORM is to perform database operations using Java objects and classes, abstracting away the need to write SQL queries directly.

1) Perform all Operations over class/object:
With ORM, developers can perform all database operations, such as creating, reading, updating, and deleting data, using Java objects and their corresponding classes. Instead of writing low-level SQL statements, developers interact with the objects in their application and let the ORM framework handle the translation of these operations into database-specific SQL queries.

1) One class is equals to one table:
In ORM, each Java class in the application is typically mapped to a corresponding table in the database. The structure and attributes of the class define the columns and data types of the table. The ORM framework handles the mapping between the class and the table, ensuring that data is persisted correctly.

1) One variable is equals to one column:
Within a Java class, each variable or field represents a column in the corresponding database table. The ORM framework maps the variable names, types, and relationships to the columns in the table, allowing developers to work with Java objects directly without worrying about the underlying database structure.

1) One object is equals to one row:
Each object of a Java class represents a row in the database table. When an object is persisted or retrieved from the database, its attributes are stored or loaded into the corresponding columns of the table. The ORM framework manages this translation between objects and rows, ensuring data consistency.

1) It is Database Independent (no SQL recommended):
ORM, including JPA, aims to be database independent, meaning that developers can write their application code without being tied to a specific database system. The ORM framework handles the generation of database-specific SQL queries, allowing the same application code to work with different databases. Developers can switch between databases by modifying the configuration properties, without needing to change the application's code or SQL queries.

ORM provides a level of abstraction that reduces the dependency on database-specific details and allows developers to focus more on the object-oriented design and logic of their applications. By eliminating the need to write SQL queries manually, developers can work with familiar OOP concepts and leverage the benefits of database independence offered by the ORM framework.

<br/>
<br/>
<br/>

# Some Imp FAQ's

Here's an explanation of the points you mentioned about the differences between JDBC and JPA, and the distinction between ORM, JPA, and Hibernate:

Q) **What is the main difference between JDBC and JPA?**<br/>
A) 
- JDBC works with SQL concepts and requires manual SQL queries to perform database operations.
- JPA works with object-oriented concepts and performs database operations through Object-Relational Mapping (ORM). SQL queries are automatically generated by the JPA framework based on the object-oriented operations.

Q) **What is the difference between ORM, JPA, and Hibernate?**<br/>
A)
- ORM: ORM (Object-Relational Mapping) is a concept or theory that aims to bridge the gap between object-oriented programming and relational databases.
- JPA: JPA (Java Persistence API) is a Java specification provided by Sun/Oracle that defines a standard set of interfaces and rules for ORM in Java applications. It is a code-level standard that allows developers to write database-agnostic code using ORM concepts.
- Hibernate: Hibernate is an implementation of the JPA specification. It is a vendor-specific implementation of JPA, providing additional features and functionality beyond the standard defined by JPA. Hibernate is one of the most popular ORM frameworks that developers can use to implement JPA in their applications.

Q) **Can we write an application using Hibernate without JPA?**<br/>
A) Yes, it is possible to write an application using Hibernate without explicitly using JPA. In the past, Hibernate existed as a standalone ORM framework before the JPA specification was introduced. However, it is generally recommended to use JPA along with Hibernate or any other ORM framework to ensure code portability and avoid vendor lock-in. By using JPA, developers can write code that adheres to the standard, making it easier to switch between different ORM implementations if needed.

Q) **What are the advantages of JPA over JDBC?**<br/>
A)
- No manual SQL queries for CRUD operations: With JPA, developers work with objects and perform CRUD (Create, Read, Update, Delete) operations directly on the objects. The framework automatically generates the required SQL queries, eliminating the need to write manual SQL statements.
- Automated schema creation: JPA can automatically generate the database schema based on the object mappings, reducing the manual effort required for database schema management.
- No checked exceptions (SQLException): JPA abstracts away the low-level database exceptions, such as SQLException, by providing a unified exception hierarchy. This simplifies error handling and reduces the code clutter caused by checked exceptions.
- Operations on objects (ORM): JPA allows developers to work with objects and their relationships, abstracting away the complexities of relational databases. This enables a more object-oriented approach to database operations.
- JPQL for custom queries: JPA provides JPQL (Java Persistence Query Language), which is a database-independent query language. Developers can write custom queries using JPQL instead of database-specific SQL, making the application more portable across different database systems.
- Primary key requirement: JPA encourages the use of primary keys for object identification and mapping. This promotes best practices and ensures data integrity in the application.

Using JPA over JDBC provides higher-level abstractions and simplifies database operations by leveraging ORM concepts, automated query generation, and a standardized approach to persistence in Java applications.

<br/>
<br/>
<br/>

# Notes

1. Entity (class): An entity class is a Java class that is mapped to a database table using JPA annotations. The entity class represents the structure and data of the table.

- @Entity: This annotation is used to mark a Java class as an entity, indicating that it will be mapped to a database table.
- @Id: This annotation is used to specify the primary key column of the entity. It identifies a unique identifier for each record in the table.
- @Table: This annotation is optional and is used to specify the details of the table associated with the entity. If not specified, the default table name will be inferred from the class name.
- @Column: This annotation is optional and is used to specify the details of the column associated with a variable in the entity. If not specified, the default column name will be inferred from the variable name.

By using these annotations, the entity class defines the mapping between the object-oriented representation of data and the corresponding database table.

2. Configuration File: The configuration file is used to provide the necessary configuration details for the JPA implementation (such as Hibernate) to connect to the database and perform the mapping.

- The configuration file can be in various formats, such as XML, properties, or even a Java class-based configuration.
- It typically includes information such as the database connection URL, driver class, username, password, and other properties required to establish the database connection.

The configuration file allows developers to customize the behavior of the JPA implementation and specify the specific database settings for their application.

3. Operation Code (Test class): The operation code refers to the code that interacts with the JPA entities and performs CRUD (Create, Read, Update, Delete) operations on the database.

- In this code, you can use the entity classes and their associated methods to interact with the database.
- This typically involves creating instances of the entity classes, setting their properties, and using JPA methods or queries to persist the data to the database or retrieve data from it.

The operation code allows developers to utilize the power of JPA and Hibernate to perform database operations using object-oriented concepts, without having to write explicit SQL statements.

Overall, the combination of the entity classes, configuration file, and operation code in JPA with Hibernate provides a powerful and convenient way to map Java objects to database tables, configure the database connection, and perform database operations using object-oriented paradigms.

<br/>
<br/>

## **The code you provided represents an example of an entity class in JPA with Hibernate. Let's break down the code and explain each part:**

```java
@Data
@Entity
@Table(name="emptab")
public class Employee {
  @Id
  @Column(name="eid")
  private Integer empId;
  @Column(name="ename")
  private String empName;
  @Column(name="esal")
  private Double empSal;
}
```

1. `@Data`: This annotation is not specific to JPA or Hibernate. It is from the Lombok library, which is a utility library that helps reduce boilerplate code. The `@Data` annotation automatically generates getter and setter methods, `toString()`, `equals()`, and `hashCode()` methods for the class.

2. `@Entity`: This JPA annotation is used to mark the `Employee` class as an entity. It indicates that instances of this class will be persisted to the database table. In other words, it maps the class to a database table.

3. `@Table(name="emptab")`: This JPA annotation specifies the name of the table in the database to which the `Employee` class is mapped. In this example, the table name is "emptab". If this annotation is not provided, the default table name will be inferred from the class name (in this case, "Employee").

4. `@Id`: This JPA annotation is used to specify the primary key of the entity. In the `Employee` class, it is applied to the `empId` field, indicating that it serves as the primary key for the table. The `@Id` annotation marks this field as the unique identifier for each record in the table.

5. `@Column(name="eid")`: This JPA annotation is used to map a field or property of the entity to a specific column in the table. In this example, it maps the `empId` field to the "eid" column in the table.

6. `private Integer empId;`: This line declares a private instance variable `empId` of type `Integer`. It represents the empId column in the database table.

7. `private String empName;`: This line declares a private instance variable `empName` of type `String`. It represents the empName column in the database table.

8. `private Double empSal;`: This line declares a private instance variable `empSal` of type `Double`. It represents the empSal column in the database table.

With these annotations and variables, the `Employee` class is mapped to the "emptab" table in the database. The empId, empName, and empSal variables correspond to the respective columns in the table. The `@Id` annotation indicates that empId is the primary key.


<br/>
<br/>
<br/>

### **In JPA with Hibernate, the configuration file can be in different formats such as XML, properties, or Java configuration. It contains key-value pairs that provide essential configuration details for the persistence unit. Let's explore the different formats and their key-value configurations:**

1. XML Configuration:
   The XML configuration file is typically named `persistence.xml` and is placed in the `META-INF` directory of the classpath. It contains configuration settings for the persistence unit. Here's an example:

```xml
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">
    <persistence-unit name="myPersistenceUnit" transaction-type="RESOURCE_LOCAL">
        <class>com.example.Employee</class>
        <properties>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/mydb"/>
            <property name="javax.persistence.jdbc.user" value="root"/>
            <property name="javax.persistence.jdbc.password" value="password"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
        </properties>
    </persistence-unit>
</persistence>
```

2. Properties Configuration:
   The properties configuration file can be a standard `.properties` file that contains key-value pairs. Here's an example:

```properties
javax.persistence.jdbc.driver=com.mysql.jdbc.Driver
javax.persistence.jdbc.url=jdbc:mysql://localhost:3306/mydb
javax.persistence.jdbc.user=root
javax.persistence.jdbc.password=password
hibernate.show_sql=true
hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.hbm2ddl.auto=update
```

3. Java Configuration:
   Instead of using an external configuration file, you can configure the persistence unit programmatically using Java configuration. Here's an example:

```java
Properties properties = new Properties();
properties.setProperty("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
properties.setProperty("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/mydb");
properties.setProperty("javax.persistence.jdbc.user", "root");
properties.setProperty("javax.persistence.jdbc.password", "password");
properties.setProperty("hibernate.show_sql", "true");
properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
properties.setProperty("hibernate.hbm2ddl.auto", "update");

EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit", properties);
```

In all these configuration formats, you provide the necessary information such as the database driver class, URL, username, password, and additional Hibernate-specific properties. These properties help establish the database connection and configure Hibernate's behavior, such as SQL logging, dialect, and schema generation strategy (`hbm2ddl.auto`).

The configuration file or properties are used to configure the persistence unit and its associated EntityManagerFactory, allowing JPA with Hibernate to establish a connection to the database and perform ORM operations.


<br/>
<br/>
<br/>

# **Let's dive deeper into the additional configuration properties mentioned:**

1. Dialect:
   The dialect property is responsible for generating SQL statements specific to the chosen database. Each database has its own SQL syntax and peculiarities, so the dialect class ensures that the generated SQL statements are compatible with the targeted database. Examples of dialect classes are `Oracle10gDialect`, `MySQL8Dialect`, and so on. By specifying the appropriate dialect, Hibernate will generate the SQL statements accordingly.

2. show_sql:
   The `show_sql` property controls whether Hibernate will log the generated SQL statements. When set to `true`, Hibernate will output the SQL statements in the console log, allowing you to see the exact SQL being executed. This can be helpful for debugging and understanding the queries being sent to the database. Setting it to `false` disables the logging of SQL statements.

3. hbm2ddl.auto:
   The `hbm2ddl.auto` property determines how Hibernate handles the database schema during application startup. It provides several options:

   - `create`: This option creates a new table schema when the application starts. If the table already exists, it will be dropped and recreated.
   - `create-drop`: This option creates a new table schema when the application starts and drops the table schema when the application shuts down. It is useful for testing or temporary databases.
   - `update`: This option updates the existing table schema if changes are detected, such as adding new columns or modifying existing ones. It ensures that the schema matches the annotated entities. Existing data is preserved.
   - `validate`: This option validates the table schema against the annotated entities but does not perform any modifications. It is useful for production environments where you want to ensure the schema is in sync with the entities. You need to handle schema creation manually.

These properties provide fine-grained control over how Hibernate interacts with the database. By configuring the dialect, show_sql, and hbm2ddl.auto properties, you can ensure that the generated SQL statements are appropriate for the targeted database, control SQL statement logging for debugging purposes, and handle the schema creation and modification during application startup.

It's important to choose the appropriate dialect for your database and set the other properties according to your requirements to ensure smooth interaction between JPA with Hibernate and the database.

<br/>
<br/>
<br/>

## **Indeed! The operation code, also known as the test class, is responsible for performing various database operations using JPA. Here are the key aspects of the operation code:**

1. Prepare setup/Environment:
   To establish a connection with the database and manage the persistence context, the operation code utilizes the `EntityManagerFactory`. The `EntityManagerFactory` is responsible for loading the appropriate JDBC driver, creating and managing `EntityManager` instances, and providing access to the persistence unit.

2. Create setup for operations:
   The operation code uses the `EntityManager` to perform CRUD (Create, Read, Update, Delete) operations on the database. The `EntityManager` acts as a gateway to interact with the underlying database through JPA. It provides methods for persisting entities, retrieving entities, updating entities, and removing entities.

3. Transaction Management:
   The operation code handles transaction management using the `EntityTransaction` interface. JPA supports transactional operations, which ensure that a group of database operations is executed atomically. The `EntityTransaction` allows you to begin a transaction, commit changes to the database (using `commit()`), or roll back changes (using `rollback()`) in case of any failure. Transaction management ensures data consistency and integrity.

By utilizing the EntityManagerFactory, EntityManager, and EntityTransaction, the operation code can perform database operations efficiently and manage transactions effectively. These components provided by JPA abstract away the low-level details of database connectivity, SQL execution, and transaction management, allowing developers to focus on working with entities and object-oriented operations rather than dealing with manual JDBC coding and transaction handling.


<br/>
<br/>
<br/>

# Here's the full code you provided:

1. pom.xml:
```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>
<dependencies>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.11.Final</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.23</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.24</version>
    </dependency>
</dependencies>
```

2. Entity class (Employee.java):
```java
package com.app.shivam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="emptab")
public class Employee {
  @Id
  @Column(name="eid")
  private Integer empId;
  @Column(name="ename")
  private String empName;
  @Column(name="esal")
  private Double empSal;
}
```

3. XML Configuration (persistence.xml):
```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
        http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
    <persistence-unit name="AppDB">
        <properties>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/boot" />
            <property name="javax.persistence.jdbc.user" value="root" />
            <property name="javax.persistence.jdbc.password" value="Shivam@123" />
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.hbm2ddl.auto" value="create" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect" />
        </properties>
    </persistence-unit>   
</persistence>
```

4. Test class (TestOperations.java):
```java
package com.app.shivam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestOperations {
    public static void main(String[] args) {
        EntityTransaction tx = null;
        try {
            // 1. (Loads Driver, Creates DB Connections, Prepare stmt)
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("AppDB");
            System.out.println(emf.getClass().getName());

            // 2. (TODO Operations--insert, update,....)
            EntityManager em = emf.createEntityManager();
            System.out.println(em.getClass().getName());

            // 3. create Transaction
            tx = em.getTransaction();
            System.out.println(tx.getClass().getName());
            // 4. Start Transaction
            tx.begin();

            // 5. Perform operation
            // save data into DB
            Employee emp = new Employee();
            emp.setEmpId(10);
            emp.setEmpName("ABC");
            emp

.setEmpSal(500.0);

            em.persist(emp); // SQL: INSERT INTO ...

            // 6. commit
            tx.commit();

            // 7. close
            emf.close();
        } catch (Exception e) {
            // rollback if any problem
            tx.rollback();
            e.printStackTrace();
        }
    }
}
```

Explanation:
1. The `pom.xml` file contains the project dependencies, including Hibernate, MySQL Connector, and Lombok. These dependencies are necessary for JPA and database connectivity.

2. The `Employee` class is an entity class annotated with `@Entity` and `@Table(name="emptab")`, which maps the class to the "emptab" table in the database. It includes member variables annotated with `@Id` and `@Column`, representing the primary key and column mappings, respectively. The `@Data` annotation provided by Lombok generates the getters, setters, `toString()`, and other methods automatically.

3. The `persistence.xml` file is the XML configuration file for JPA. It specifies the database connection details, such as the driver, URL, username, and password. It also configures Hibernate-specific properties like `hibernate.show_sql` (to display generated SQL statements), `hibernate.hbm2ddl.auto` (to handle schema creation), and `hibernate.dialect` (to specify the appropriate SQL dialect for the database).

4. The `TestOperations` class serves as the test class to demonstrate JPA operations. It establishes the EntityManagerFactory using the persistence unit name "AppDB" defined in `persistence.xml`. It then creates an EntityManager and starts a transaction using the EntityTransaction interface. Inside the transaction, it creates an instance of the `Employee` class, sets its values, and persists it using the `persist()` method of the EntityManager. Finally, the transaction is committed, and the EntityManagerFactory is closed.

Conclusion:
The provided code showcases the usage of JPA with Hibernate as the implementation. It demonstrates how to define an entity class, configure the database connection and Hibernate-specific properties, and perform database operations using JPA. By utilizing JPA's annotations, XML configuration, and EntityManager, developers can easily map Java objects to database tables and perform CRUD operations without writing explicit SQL queries.

Note: The provided code assumes a MySQL database with the appropriate configuration. Make sure to have a MySQL server running, create a database named "boot7am," and adjust the connection details accordingly in `persistence.xml`.