# Let's break down each point about the JpaRepository interface and provide a detailed explanation:

1. JpaRepository extends PaginationAndSortingRepository:
The JpaRepository interface extends the PaginationAndSortingRepository interface. This means that JpaRepository inherits all the methods defined in PaginationAndSortingRepository and adds additional functionality specific to JPA (Java Persistence API) repositories.

2. find() method returns List<T>:
In JpaRepository, the find() method returns a List<T> type. This is different from the CrudRepository interface, where the find() method returns an Iterable<T> type. The use of List<T> allows for more flexibility and convenience when working with the query results.

3. Mainly works for SQL Database and is faster:
JpaRepository is primarily designed to work with SQL databases. It leverages the power of JPA and Hibernate, which are popular technologies for interacting with relational databases. JpaRepository provides efficient mechanisms for executing queries and managing database operations, making it faster when compared to other repository interfaces.

4. Custom Queries and Data JPA findBy methods:
JpaRepository allows you to define custom queries using JPA's query language, called JPQL (Java Persistence Query Language), or by leveraging native SQL queries. You can annotate your custom query methods in the repository interface with the `@Query` annotation to specify the query string. Additionally, JpaRepository provides several convenience methods, such as findByProperty(), that generate queries based on the method name, saving you from writing custom queries for common operations.

5. Find object by Example:
JpaRepository supports finding objects by example. This means you can create an example object with specific properties set, and JpaRepository will find all entities that match the provided properties. This feature is helpful when you want to search for entities based on specific attributes without writing custom queries.

In summary, the JpaRepository interface builds upon the functionality of PaginationAndSortingRepository, offering additional features such as custom queries, convenient methods for querying by example, and improved performance for SQL databases. It serves as a powerful tool when working with JPA and SQL-based persistence layers in Java applications.

<br/>
<br/>

# Let's break down each point about the `@GeneratedValue` annotation and the `@Temporal` annotation with `TemporalType`:

1. `@GeneratedValue`:
The `@GeneratedValue` annotation is used to specify how the primary key values for entities should be generated when they are saved into the database. It is typically used in conjunction with the `@Id` annotation, which marks a field as the primary key of an entity.

- Use: SEQUENCE for Oracle, IDENTITY for MySQL, AUTO for default DB-based selection:
   - `SEQUENCE` strategy: When using an Oracle database, the `SEQUENCE` strategy can be specified with `@GeneratedValue(strategy = GenerationType.SEQUENCE)`. This strategy relies on a database sequence to generate unique primary key values for each new entity. 
   - `IDENTITY` strategy: When using a MySQL database, the `IDENTITY` strategy can be specified with `@GeneratedValue(strategy = GenerationType.IDENTITY)`. This strategy utilizes an auto-incremented column to generate unique primary key values.
   - `AUTO` strategy: If you don't specify a specific strategy, the `AUTO` strategy is used by default. It allows the underlying database to determine the appropriate strategy based on its capabilities. This is a DB-based selection where the database vendor chooses the appropriate strategy.

2. `@Temporal` with `TemporalType`:
The `@Temporal` annotation is used to specify the type of temporal data (date and time) that will be stored in a field of type `java.util.Date` or `java.util.Calendar`. It is often used in conjunction with the `@Column` annotation to define the database column's data type.

- Default is DATEANDTIME (TIMESTAMP), but you can also use DATE, TIME:
   - `DATEANDTIME` (TIMESTAMP): By default, if you don't specify the `TemporalType`, it assumes `TemporalType.TIMESTAMP`, which represents both date and time. It is the most common choice when you need to store both the date and time components.
   - `DATE`: You can specify `TemporalType.DATE` to indicate that only the date component should be stored in the database, without the time portion. This is useful when you don't need to store the time information and only care about the date.
   - `TIME`: Alternatively, you can use `TemporalType.TIME` to store only the time component, excluding the date. This can be useful when you want to track specific time values without considering the date.

In summary, the `@GeneratedValue` annotation allows you to specify how the primary key values are generated when saving entities, with different strategies based on the database being used. The `@Temporal` annotation, on the other hand, is used to specify the type of temporal data (date and time) to be stored in a `java.util.Date` field, providing flexibility to store the entire date and time, just the date, or only the time. These annotations enhance the control and customization of entity mappings in JPA applications.


<br/>
<br/>
<br/>

# Let's break down each point about `SimpleDateFormat`, `findBy(Example)`, and JDK 1.8 features:

1. **`SimpleDateFormat`:**
`SimpleDateFormat` is a class in Java that allows you to format and parse dates in a specific pattern. It is part of the `java.text` package and provides methods for converting dates to strings (`format()`) and parsing strings into date objects (`parse()`). The class uses patterns with various symbols to represent different components of a date, such as year, month, day, hour, minute, and second. You can refer to the official Oracle documentation for a detailed explanation of the available patterns and symbols.

2. **`IDENTITY`** creates a table with auto_increment for PK concept:
The `IDENTITY` strategy is used in databases like MySQL to automatically generate values for the primary key (PK) column when inserting new records. When a table is created with the `IDENTITY` strategy, the database assigns a unique value to the PK column automatically. The assigned values typically start from 1 and increment by 1 for each new record inserted.

3. **`findBy(Example)`:**
The `findBy(Example)` method is provided by Spring Data JPA repositories. It allows you to fetch data from the database based on an example object. An example object is an instance of an entity class where you have set specific properties with non-null values. The method dynamically generates a SQL query based on the non-null fields of the example object and retrieves entities that match those field values. This is useful when you want to perform a query based on specific attributes without writing a custom SQL query.

Example usage:
```java
Example<Student> example = Example.of(studentObject);
List<Student> students = repository.findAll(example);
students.forEach(System.out::println);
```
In the above code snippet, `studentObject` is an instance of the `Student` class with certain properties set. The `Example.of()` method creates an example object, and `repository.findAll()` fetches all entities that match the non-null properties of the example object.

4. JDK 1.8 features:
In JDK 1.8, Java introduced several new features, including the ability to define static and default methods in interfaces. Before JDK 1.8, interfaces could only have abstract method declarations, but with JDK 1.8, interfaces can also contain static methods with a default implementation. Static methods in interfaces are accessible using the interface name itself, while default methods provide a default implementation that can be overridden by implementing classes. These features enable interfaces to have more functionality and reduce code duplication in implementing classes.

In summary, `SimpleDateFormat` is a class used for formatting and parsing dates in Java. The `findBy(Example)` method in Spring Data JPA allows you to query the database based on an example object, dynamically generating SQL queries. Additionally, JDK 1.8 introduced the ability to define static and default methods in interfaces, providing more flexibility and code reuse.


<br/>
<br/>
<br/>

**`QBE (Query by Example)` : It is a feature provided by JpaRepository that allows you to query entities based on an example object. It dynamically generates a SQL query using the non-null fields of the example object. Here's an example of how to use QBE in JpaRepository:**

1. Define the Entity:
Let's assume we have an entity class called `Employee` with attributes such as `id`, `firstName`, `lastName`, and `department`.

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String department;

    // Getters and setters, constructors, etc.
}
```

2. Create the Repository Interface:
Define a repository interface by extending `JpaRepository` and specifying the entity type (`Employee`) and the ID type (`Long`).

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
```

3. Use QBE in JpaRepository:
In your application code, you can utilize QBE to perform queries based on an example object.

```java
@Autowired
private EmployeeRepository employeeRepository;

public void searchEmployees() {
    Employee exampleEmployee = new Employee();
    exampleEmployee.setDepartment("IT");

    Example<Employee> example = Example.of(exampleEmployee);
    List<Employee> employees = employeeRepository.findAll(example);

    employees.forEach(System.out::println);
}
```

In the above example, we create an instance of `Employee` called `exampleEmployee` and set the desired search criteria. In this case, we set the department to "IT". We then create an `Example` object using `Example.of(exampleEmployee)`.

By calling `findAll(example)` on the `EmployeeRepository`, the JpaRepository will generate a query based on the non-null fields of the example object and return a list of matching employees.

Note: QBE in JpaRepository is not limited to a single attribute. You can set multiple attributes on the example object to perform more complex searches based on various criteria.

Using QBE in JpaRepository simplifies the process of building dynamic queries based on example objects, avoiding the need to write explicit queries manually.

<br/>
<br/>

# I will provide the code snippets along with their explanations and a conclusion. Let's go through each section:

**1. Entity**

```java
package com.app.shivam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="stdtab")
public class Student {
    @Id
    @Column(name="sid")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL (Auto increment)
    private Integer stdId;

    @Column(name="sname")
    private String stdName;
    
    @Column(name="sfee")
    private Double stdFee;
    
    @Column(name="sdoj")
    @Temporal(TemporalType.TIMESTAMP) // Stores both date and time (default)
    private Date stdDoj;
}
```

Explanation:
- The `Student` class is an entity class that represents the structure of a student entity to be stored in the database.
- It is annotated with `@Entity` to indicate that it is a JPA entity.
- The `@Table` annotation specifies the name of the table in the database where the entity will be stored.
- The `@Id` annotation marks the `stdId` field as the primary key of the entity.
- The `@GeneratedValue` annotation with `GenerationType.IDENTITY` specifies that the primary key values will be generated automatically using the MySQL auto-increment feature.
- The `@Column` annotation is used to map the entity's fields to the corresponding columns in the database.
- The `@Temporal` annotation with `TemporalType.TIMESTAMP` is used to store both the date and time components in the `stdDoj` field.

**2. Repository Interface**

```java
package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
```

Explanation:
- The `StudentRepository` interface extends the `JpaRepository` interface, which provides CRUD operations for the `Student` entity.
- By extending `JpaRepository<Student, Integer>`, the repository interface becomes capable of managing `Student` entities with `Integer` as the type of the primary key.

**3. Runner class**

```java
package com.app.shivam.runner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Student;
import com.app.shivam.repo.StudentRepository;

@Component
public class StudentTestRunner implements CommandLineRunner {

    @Autowired
    private StudentRepository repo;
    
    public void run(String... args) throws Exception {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yy");
        String s = sdf.format(new Date());
        System.out.println(s);
        
        Student s1 = new Student();
        s1.setStdName("SAM");
        s1.setStdFee(400.0);
        s1.setStdDoj(new Date());
        repo.save(s1);
        
        List<Student> list = repo.findAll();
        System.out.println(list.getClass().getName());
        list.forEach(System.out::println);
        
        Student sob = new Student();
        sob.setStdFee(400.0);
        sob.setStdName("AJAY");
        Example<Student> prob = Example.of(sob);
        repo.findAll(prob).forEach(System.out::println);
    }
}
```

Explanation:
- The `StudentTestRunner` class is a command-line runner component that executes code when the Spring Boot application starts.
- The `StudentRepository` is autowired for accessing the database operations related to the `Student` entity.
- In the `run()` method, a `SimpleDateFormat` is used to format the current date.
- An instance of the `Student` entity is created and its properties are set.
- The `repo.save(s1)` method saves the `Student` object to the database.
- The `repo.findAll()` method retrieves all `Student` entities from the database and prints them.
- An example object (`sob`) is created with certain properties set, and the `repo.findAll(prob)` method fetches the `Student` entities that match the non-null properties of the example object.

**4. application.yml**

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/boot
    username: root
    password: Shivam@123
    
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create
```

Explanation:
- The `application.yml` file contains the configuration for the database connection and JPA settings.
- The `datasource` section provides the URL, username, and password for the MySQL database.
- The `jpa` section configures JPA-related properties such as showing SQL queries and setting the Hibernate dialect.
- The `hibernate.ddl-auto` property is set to "create" to automatically create the database tables based on the entity mappings.

**Conclusion:**
In this code example, we defined an entity class `Student` with annotations for JPA mapping. We created a repository interface `StudentRepository` by extending `JpaRepository`, allowing us to perform CRUD operations on the `Student` entity. The `StudentTestRunner` class demonstrates how to save entities, fetch them using different methods, and use the `Example` feature to query based on an example object. The `application.yml` file provides the database and JPA configurations for the Spring Boot application.

<br/>
<br/>

# Let's dive into the details of custom queries in Spring Boot Data JPA:

1. Custom Queries Overview:
Custom queries in Spring Boot Data JPA allow programmers to define their own queries to perform database operations. These queries can be used for SELECT, DELETE, and UPDATE operations, but INSERT operations are not supported through custom queries.

There are two main approaches for defining custom queries:
- `findBy` method naming convention: This approach is used for simple SELECT operations and is based on method naming rules. By defining a specific method name in the repository interface, Spring Data JPA generates the corresponding query dynamically.
- `@Query` annotation: This approach provides more flexibility and supports both SELECT (default) and non-SELECT operations. You can define the query explicitly using the `@Query` annotation on the repository method.

2. findBy___:
The `findBy` method naming convention is used for SELECT operations with a WHERE condition. The method name follows specific syntax rules, allowing the generation of the WHERE condition based on the method name. This approach eliminates the need to write the query explicitly.

Syntax: `<Output> <methodName>(<params>)`

- `<Output>`: The return type of the method. It can be `List` (or any collection type), `Optional<T>`, or `Object[]` depending on the query result.
- `<methodName>`: The method name following the naming conventions. You can use specific prefixes such as `findBy`, `getBy`, `readBy`, or `queryBy` followed by the entity properties separated by camel case.

Example:
```java
List<Student> findByStdName(String name);
```
In the above example, the `findByStdName` method retrieves a list of `Student` objects based on the `stdName` property.

3. @Query:
The `@Query` annotation allows you to define custom queries explicitly using JPQL (Java Persistence Query Language) or native SQL queries. It provides more flexibility in defining complex queries and supports both SELECT and non-SELECT operations (e.g., DELETE or UPDATE).

Example:
```java
@Query("SELECT s FROM Student s WHERE s.stdName = :name")
List<Student> findByName(@Param("name") String name);
```
In the above example, the `@Query` annotation is used to define a SELECT query explicitly. The `:name` placeholder is used to represent a named parameter that can be passed through the method's `@Param` annotation. This query fetches a list of `Student` objects where the `stdName` property matches the provided name.

4. Conclusion:
Custom queries in Spring Boot Data JPA provide flexibility in defining queries beyond the basic CRUD operations. They can be defined using the `findBy` method naming convention for simple SELECT queries or using the `@Query` annotation for more complex queries. These custom queries enable you to retrieve data based on specific conditions and perform non-SELECT operations as well.

Please note that the actual method signatures and query syntax may vary depending on your entity class and database schema.

Certainly! Here are the code snippets along with their explanations for custom queries in Spring Boot Data JPA:

**1. Using findBy method naming convention:**

```java
// Repository interface
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByStdName(String name);
}
```

Explanation:
- In the `StudentRepository` interface, we define a method `findByStdName` following the `findBy` naming convention.
- This method retrieves a list of `Student` objects based on the `stdName` property.
- Spring Data JPA dynamically generates the query based on the method name, so we don't need to write the query explicitly.
- The returned list contains all the `Student` objects whose `stdName` matches the provided name.

**2. Using @Query annotation:**

```java
// Repository interface
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.stdName = :name")
    List<Student> findByName(@Param("name") String name);
}
```

Explanation:
- In the `StudentRepository` interface, we use the `@Query` annotation to define a custom SELECT query explicitly.
- The query selects the `Student` objects (`s`) from the `Student` entity where the `stdName` property matches the provided `name` parameter.
- The `:name` placeholder is used to represent the named parameter in the query.
- The `@Param("name")` annotation is used to map the `name` parameter in the method signature to the named parameter in the query.
- The returned list contains all the `Student` objects that satisfy the given condition.

These code snippets demonstrate two different approaches to define custom queries in Spring Boot Data JPA. The first approach uses the `findBy` method naming convention to generate the query dynamically, while the second approach uses the `@Query` annotation to define the query explicitly.

Please note that you need to ensure the correct entity class and property names in your code.

<br/>
<br/>
<br/>

# Here are the code snippets and their explanations for the custom queries in the provided code:

**1. Entity: Book**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="booktab")
public class Book {
	@Id
	@Column(name="bid")
	private Integer bookId;
	
	@Column(name="bname")
	private String bookName;

	@Column(name="bauth")
	private String author;
	
	@Column(name="bcost")
	private Double bookCost;
	
	@Column(name="btype")
	private String bookType;
}
```
Explanation:
- The `Book` class is an entity representing the "booktab" table in the database.
- It includes fields such as `bookId`, `bookName`, `author`, `bookCost`, and `bookType`.
- The class is annotated with `@Entity` to indicate that it is a persistent entity.
- Field annotations such as `@Id` and `@Column` specify the mapping between entity properties and database columns.
- Lombok annotations `@Data`, `@NoArgsConstructor`, and `@AllArgsConstructor` generate getters, setters, constructors, and other boilerplate code.

**2. Repository Interface: BookRepository**
```java
public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findByAuthor(String author);
	List<Book> findByauthorIs(String author);
	List<Book> findByAuthorEquals(String author);
	List<Book> findByBookType(String bookType);
}
```
Explanation:
- The `BookRepository` interface extends `JpaRepository<Book, Integer>` to inherit basic CRUD operations from Spring Data JPA.
- The interface includes several custom query methods using the `findBy` method naming convention:
  - `findByAuthor`: Finds books by matching the `author` property.
  - `findByauthorIs`: Same as `findByAuthor`.
  - `findByAuthorEquals`: Same as `findByAuthor`.
  - `findByBookType`: Finds books by matching the `bookType` property.

**3. Runner class: TestDataRunner**
```java
@Component
public class TestDataRunner implements CommandLineRunner {
	@Autowired
	private BookRepository repo;
	
	public void run(String... args) throws Exception {
		repo.saveAll(Arrays.asList(
			new Book(101, "SBMS", "shivam", 300.0, "Backend"),
			new Book(102, "CORE", "shivam", 200.0, "Backend"),
			new Book(103, "ADV", "vikash", 400.0, "Backend"),
			new Book(104, "REACT", "vikash", 500.0, "Frontend"),
			new Book(105, "HTML", "shivam", 600.0, "Frontend")
		));
		
		repo.findByBookType("Backend");
	}
}
```
Explanation:
- The `TestDataRunner` class is a Spring `CommandLineRunner` that executes code on application startup.
- It is annotated with `@Component` to be detected as a Spring bean.
- In the `run` method, the `BookRepository` is autowired for database operations.
- A list of `Book` objects is created and saved to the database using the `saveAll` method.
- The `findByBookType` method is called to retrieve books with the "Backend" book type.

**4. YAML File: application.yml**
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/boot
    username: root
    password: Shivam
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create
```
Explanation:
- The YAML configuration file sets up the database connection and Hibernate properties.
- It specifies the database

 URL, username, password, and other JPA-related properties.

Conclusion:
- The provided code showcases the usage of custom queries in Spring Boot Data JPA.
- The `BookRepository` interface demonstrates the use of custom query methods using the `findBy` method naming convention.
- The `TestDataRunner` class demonstrates the execution of custom queries by calling the appropriate methods in the repository.
- The YAML configuration file sets up the database connection and Hibernate properties.

