#  Here's a detailed explanation of using the `@Query` annotation in Spring Data JPA with JPQL/HQL queries:

1. `@Query` annotation:
   - The `@Query` annotation is used to define a custom query manually in JPQL (Java Persistence Query Language) or HQL (Hibernate Query Language).
   - By using this annotation, you can write complex queries that are not easily expressed using the method naming conventions of Spring Data JPA.
   - It allows you to directly specify the query string as part of the annotation.

2. SELECT and NON-SELECT operations:
   - `@Query` annotation supports both SELECT and NON-SELECT operations.
   - For SELECT operations, the query is expected to return a result set.
   - For NON-SELECT operations (e.g., UPDATE, DELETE), the query is executed for its side effects without returning any data.

3. ReturnType of Query method:
   - The return type of the query method determines how the results are returned.
   - If the query returns all columns of an entity, the return type can be `List<T>` where `T` is the entity class.
   - If the query returns only one column (e.g., a single attribute), the return type can be `List<DataType>`, where `DataType` is the type of that column.
   - If the query returns multiple columns but not all columns, the return type can be `List<Object[]>`, where each element of the list represents a row with multiple values as an array.

Here's an example to illustrate the usage:

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e.name FROM Employee e WHERE e.department = :dept")
    List<String> findEmployeeNamesByDepartment(@Param("dept") String department);
    
    @Query("SELECT e.salary FROM Employee e WHERE e.department = :dept")
    List<Integer> findEmployeeSalariesByDepartment(@Param("dept") String department);
    
    @Query("SELECT e.name, e.salary FROM Employee e WHERE e.department = :dept")
    List<Object[]> findEmployeeNamesAndSalariesByDepartment(@Param("dept") String department);
    
    @Modifying
    @Query("UPDATE Employee e SET e.salary = e.salary + :raiseAmount WHERE e.department = :dept")
    void giveSalaryRaiseByDepartment(@Param("raiseAmount") int raiseAmount, @Param("dept") String department);
    
}
```

In the above example:
- The first query returns a `List<String>` of employee names based on the provided department.
- The second query returns a `List<Integer>` of employee salaries based on the provided department.
- The third query returns a `List<Object[]>` with each row containing an employee name and salary based on the provided department.
- The fourth query performs a non-select operation by updating the salaries of employees in a specific department.

By utilizing the `@Query` annotation, you have the flexibility to write custom queries in JPQL/HQL and handle different return types based on the specific needs of your application.

<br/>
<br/>
<br/>

# Here's a detailed explanation of the differences between SQL and JPQL/HQL in terms of their syntax and case sensitivity:

SQL:
- SQL (Structured Query Language) is a standard language used to interact with relational databases.
- SQL queries are created using table and column names.
- SQL is case insensitive, which means that keywords like SELECT, WHERE, FROM, and column names can be written in any case (upper or lower).

JPQL/HQL:
- JPQL (Java Persistence Query Language) is a query language specific to Java Persistence API (JPA) and is used to query entity objects stored in a relational database.
- HQL (Hibernate Query Language) is a similar query language specific to the Hibernate ORM (Object-Relational Mapping) framework.
- In JPQL/HQL, the table names are replaced with the corresponding class names, and column names are replaced with the variable names defined in the entity class.
- JPQL/HQL is partially case-sensitive:
  - Java words such as class names and variable names are case-sensitive.
  - SQL keywords used in JPQL/HQL (e.g., SELECT, WHERE, FROM) are case-insensitive, meaning they can be written in any case.
  - Variable names in JPQL/HQL are case-sensitive, similar to how Java variables are case-sensitive.

Here's an example to illustrate the differences:

SQL query:
```
SELECT employee_id, first_name, last_name FROM employees WHERE department_id = 10;
```

Equivalent JPQL/HQL query:
```java
SELECT e.employeeId, e.firstName, e.lastName FROM Employee e WHERE e.departmentId = 10
```

In the above example:
- In SQL, the table name is `employees`, and the column names are `employee_id`, `first_name`, and `last_name`.
- In JPQL/HQL, the table name is replaced with the corresponding entity class name `Employee`, and the column names are replaced with the variable names `employeeId`, `firstName`, and `lastName`.
- The SQL keywords (SELECT, WHERE) are case-insensitive in JPQL/HQL and can be written in any case.
- The variable names (`e.employeeId`, `e.firstName`, `e.lastName`, `e.departmentId`) in JPQL/HQL are case-sensitive, just like Java variables.

By understanding these differences, you can write JPQL/HQL queries that closely resemble SQL queries while taking into account the specific rules and case sensitivity of the JPQL/HQL syntax.


<br/>
<br/>
<br/>

# Here are the Examples:

1. SQL: SELECT * FROM EMPTAB

JPQL/HQL:
```java
// Valid JPQL/HQL queries
String jpqlQuery1 = "SELECT e1 FROM com.app.entity.Employee e1";
String jpqlQuery2 = "SELECT e1 FROM Employee e1";
String jpqlQuery3 = "FROM com.app.entity.Employee e1";
```

2. SQL: SELECT EID, ENAME FROM EMPTAB;
   
JPQL/HQL: 
```java
String jpqlQuery = "SELECT empId, empName FROM Employee";
```

3. SQL: SELECT ENAME FROM EMPTAB;
   
JPQL/HQL:
```java
String jpqlQuery = "SELECT empName FROM Employee";
```

Explanation:
- Example 1 demonstrates different valid JPQL/HQL queries for selecting all columns from the "Employee" entity class. The queries use either the fully qualified class name or the shorthand notation. However, using "*" to select all columns is not allowed in JPQL/HQL.
- Example 2 shows a valid JPQL/HQL query for selecting specific columns "EID" and "ENAME" from the "Employee" entity class.
- Example 3 illustrates a valid JPQL/HQL query for selecting the "ENAME" column from the "Employee" entity class.

In JPQL/HQL, the table name is represented by the entity class name, and the column names are represented by the variable names within the entity class. The queries are case-sensitive for Java words like class names and variable names, but case-insensitive for SQL keywords like SELECT, FROM, and WHERE.

Conclusion:
JPQL/HQL provides a more object-oriented approach to querying databases compared to SQL. It allows you to write queries using entity class names and variable names, making it easier to work with Java objects. By understanding the syntax and mapping between JPQL/HQL and SQL, you can effectively query your data using Spring Boot Data JPA and Hibernate.



<br/>
<br/>
<br/>


# FAQ's

**Q) Why can't we apply @Component or equal stereotype for the Entity class?**

A) The Entity class in JPA/Hibernate represents a database table and its rows. It is not intended to be treated as a Spring Bean or managed component by the Spring framework. The purpose of the Entity class is to provide a mapping between the Java object representation and the database table.

Each instance of the Entity class represents a single row in the database table. These instances are managed by JPA/Hibernate and are not created or managed by Spring's component scanning or dependency injection mechanisms. Therefore, applying stereotypes like @Component or similar annotations is not meaningful or necessary for Entity classes.

Entity classes are annotated with JPA annotations such as @Entity, @Table, and others to define their mapping to the database. They serve as a blueprint for creating, updating, and querying database records using JPA/Hibernate.

**Q) What are the possible return types in JPQL/HQL custom queries?**

A) In JPQL/HQL custom queries, the return type depends on the query and the desired result. The two common return types are:

1. List<T>: This return type is used when the query is expected to return multiple rows with all columns of the entity. The result is a list of instances of the Entity class, where each instance represents a row from the database table.

2. Optional<T>: This return type is used when the query is expected to return a single row or no rows at all. It is commonly used when querying by a unique identifier or when you want to retrieve a single result. The result is wrapped in an Optional object, which can be used to handle the presence or absence of the result gracefully.

For example, if you have a SQL query like "SELECT * FROM emptab WHERE eid=?", which returns one row with all columns, the equivalent JPQL/HQL custom query would have a return type of Optional<T>.

Understanding the possible return types allows you to handle the query results appropriately in your code, whether it involves processing multiple rows or handling the absence of a result.

By considering these points, you can effectively utilize JPQL/HQL custom queries in your application, retrieve the desired data from the database, and handle the query results accordingly.

<br/>
<br/>
<br/>

# Here's the code with separate explanations for each class:

1. Entity

```java
package com.app.shivam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	@Column(name="edept")
	private String empDept;
}
```

Explanation: 
- The `Employee` class is an entity class annotated with `@Entity` to indicate that it is mapped to a database table.
- It has fields representing the columns of the table, with corresponding annotations such as `@Column` and `@Id` for primary key mapping.
- Lombok annotations like `@Data`, `@NoArgsConstructor`, and `@AllArgsConstructor` are used to generate getter, setter, and constructor methods.

2. Repository

```java
package com.app.shivam.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.shivam.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e.empName FROM Employee e")
	List<String> getAllEmpNames();
	
	@Query("SELECT e FROM Employee e")
	List<Employee> fetchAllEmps();
	
	@Query("SELECT e.empId, e.empName FROM Employee e")
	List<Object[]> fetchIdAndNames();
	
	@Query("SELECT e.empName FROM Employee e WHERE e.empId=:id")
	Optional<String> getEmpNameById(Integer id);
	
	@Query("SELECT e FROM Employee e WHERE e.empId=:id")
	Optional<Employee> fetchEmployeeById(Integer id);
}
```

Explanation:
- The `EmployeeRepository` interface extends `JpaRepository` and provides custom query methods.
- `@Query` annotation is used to define JPQL/HQL queries for specific database operations.
- Various query methods are defined to fetch employee names, fetch all employees, fetch specific columns, fetch data based on employee ID, and fetch an employee by ID.

Here's the code with explanations for each query separately:

```java
// Query 1: getAllEmpNames()
@Query("SELECT e.empName FROM Employee e")
List<String> getAllEmpNames();
```
Explanation:
- This query selects the `empName` column from the `Employee` entity.
- The return type is `List<String>` because we are fetching only the `empName` column values.
- This query will return a list of employee names.

```java
// Query 2: fetchAllEmps()
@Query("SELECT e FROM Employee e")
List<Employee> fetchAllEmps();
```
Explanation:
- This query selects the entire `Employee` entity.
- The return type is `List<Employee>` because we are fetching all columns from the `Employee` entity.
- This query will return a list of `Employee` objects.

```java
// Query 3: fetchIdAndNames()
@Query("SELECT e.empId, e.empName FROM Employee e")
List<Object[]> fetchIdAndNames();
```
Explanation:
- This query selects the `empId` and `empName` columns from the `Employee` entity.
- The return type is `List<Object[]>` because we are fetching multiple columns but not all columns.
- This query will return a list of object arrays where each array contains the `empId` and `empName` values.

```java
// Query 4: getEmpNameById(Integer id)
@Query("SELECT e.empName FROM Employee e WHERE e.empId=:id")
Optional<String> getEmpNameById(Integer id);
```
Explanation:
- This query selects the `empName` column from the `Employee` entity based on the provided `id`.
- The return type is `Optional<String>` because we are fetching only the `empName` column value.
- This query will return an optional string value representing the employee name.

```java
// Query 5: fetchEmployeeById(Integer id)
@Query("SELECT e FROM Employee e WHERE e.empId=:id")
Optional<Employee> fetchEmployeeById(Integer id);
```
Explanation:
- This query selects the entire `Employee` entity based on the provided `id`.
- The return type is `Optional<Employee>` because we are fetching all columns for a single row.
- This query will return an optional `Employee` object representing the employee with the given id.

Conclusion:
The `EmployeeRepository` interface provides several custom query methods using `@Query` annotations. Each method corresponds to a specific JPQL/HQL query and returns the appropriate result type based on the selected columns. These methods allow you to retrieve employee names, fetch all employees, retrieve specific columns, and fetch single employee records based on different conditions.


3. Runner class

```java
package com.app.shivam.runner;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Employee;
import com.app.shivam.repo.EmployeeRepository;

@Component
public class TestEmployeeASaveRunner implements CommandLineRunner {

	@Autowired
	private EmployeeRepository repo;
	
	public void run(String... args) throws Exception {
		// Saving employee records using the repository's saveAll() method
		repo.saveAll(Arrays.asList(
				new Employee(101, "AA", 200.0, "DEV"),
				new Employee(102, "BA", 500.0, "DEV"),
				new Employee(103, "AC", 200.0, "QA"),
				new Employee(104, "DD", 400.0, "QA"),
				new Employee(105, "EF", 600.0, "DEV")
		));
		
		// Fetching employee ids and names using the fetchIdAndNames() method
		repo.fetchIdAndNames()
			.stream()
			.map(obj -> obj[0] + " : " + obj[1])
			.forEach(System.out::println);

		// Retrieving the employee name by id using the getEmpNameById() method
		Optional<String> opt = repo.getEmpNameById(101);
		if (opt.isPresent()) {
			System.out.println(opt.get());
		} else {
			System.out.println("No Data");
		}

		// Fetching an employee by id using the fetchEmployeeById() method
		Employee e = repo.fetchEmployeeById(1040)
				.orElseThrow(() -> new RuntimeException("Not Found"));
		System.out.println(e);
	}
}
```

Explanation:
1. The `TestEmployeeASaveRunner` class is a Spring component annotated with `@Component` and implements the `CommandLineRunner` interface. This allows the code to be executed when the application starts.
2. The `EmployeeRepository` is autowired to access the repository methods.
3. In the `run` method, we save a list of `Employee` objects using the `saveAll()` method of the repository.
4. We use the `fetchIdAndNames()` method to retrieve employee ids and names. The returned `Object[]` array is streamed, mapped to a formatted string, and printed to the console.
5. Next, we retrieve the employee name for a specific id using the `getEmpNameById()` method. We check if the optional value is present and print the name if available.
6. Finally, we fetch an employee by id using the `fetchEmployeeById()` method. If the optional value is empty, we throw a `RuntimeException`. Otherwise, we print the employee details.

Conclusion:
The `TestEmployeeASaveRunner` class demonstrates the usage of the repository methods defined in the `EmployeeRepository`. It saves employee records, retrieves specific data using custom queries, and handles optional values appropriately.
4. YAML File

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
- The YAML configuration file specifies the database connection details and JPA properties.
- It configures the datasource URL, username, and password.
- JPA properties are configured to show SQL statements and set the Hibernate dialect and DDL generation mode.

Conclusion:
The provided code includes an entity class `Employee` representing a database table, a repository interface `EmployeeRepository` with custom query methods, and a runner class `TestEmployeeASaveRunner` to demonstrate the usage of these methods. By using JPA annotations and custom queries, you can perform various database operations and retrieve data from the database table.

<br/>
<br/>
<br/>

# Here are some frequently asked questions related to the code you provided along with their answers:

**Q1. What is the purpose of the `@Component` annotation in the `TestEmployeeASaveRunner` class?**<br/>
A1. The `@Component` annotation is used to mark the class as a Spring component, allowing it to be automatically detected and instantiated as a bean by the Spring container.

**Q2. What is the purpose of the `@Query` annotation in the `EmployeeRepository` interface?**<br/>
A2. The `@Query` annotation is used to define custom queries in Spring Data JPA. It allows you to write JPQL (Java Persistence Query Language) or HQL (Hibernate Query Language) queries directly in your repository interface.

**Q3. What are the possible return types for JPQL/HQL custom queries?**<br/>
A3. The possible return types for JPQL/HQL custom queries are:
   - `List<T>`: When retrieving multiple rows where `T` represents the entity class or its projection.
   - `List<DataType>`: When retrieving a single column value where `DataType` is the data type of the column.
   - `List<Object[]>`: When retrieving multiple columns but not all columns, resulting in an array of objects.

**Q4. Why can't we apply the `@Component` or similar stereotype for the entity class?**<br/>
A4. The entity class represents a database table and is not a Spring bean. It is related to JPA/Hibernate and represents a single row in the table. Stereotypes like `@Component` are used for Spring beans, not for entity classes.

**Q5. How is JPQL/HQL different from SQL?**<br/>
A5. Here are the key differences between JPQL/HQL and SQL:
   - SQL is created using table and column names, whereas JPQL/HQL uses the class and variable names.
   - SQL is case-insensitive, while JPQL/HQL is partially case-sensitive (Java words are case-sensitive, SQL words are case-insensitive).
   - SQL supports the use of asterisk (*) to select all columns, but JPQL/HQL does not allow it.

**Q6. How can we fetch a single column and row using a JPQL/HQL query?**<br/>
A6. To fetch a single column and row, you can use a JPQL/HQL query that selects only the desired column and use the `Optional` return type in the repository method declaration.

**Q7. What is the purpose of the `run` method in the `TestEmployeeASaveRunner` class?**<br/>
A7. The `run` method is part of the `CommandLineRunner` interface and is automatically executed when the Spring Boot application starts. It contains the code that demonstrates the usage of the repository methods and performs various operations on the employee records.

**Q8. What is the purpose of the `saveAll` method in the `TestEmployeeASaveRunner` class?**<br/>
A8. The `saveAll` method is used to save a list of employee objects to the database. It utilizes the `EmployeeRepository`'s `saveAll` method, which internally performs the necessary operations to persist the entities.

**Q9. How does the `Optional` class help in handling query results in the `TestEmployeeASaveRunner` class?**<br/>
A9. The `Optional` class is used to handle query results that may or may not contain a value. It allows you to check if the value is present using the `isPresent` method and retrieve the value using the `get` method. If the value is not present, you can handle the situation accordingly.

These are some common interview questions related to the code provided. You can use them as a starting point for your interview preparation and expand upon them with more specific questions based on your understanding of the code and the concepts discussed.


<br/>
<br/>


# some More FAQ's 

**Q1. What does the `ddl-auto: create` property in the YAML file do?** <br/>
A1. The `ddl-auto: create` property in the YAML file tells Hibernate to automatically create the database schema based on the entity classes. It generates the necessary tables, columns, and constraints during application startup.

<br/>

**Q2. Can you explain the difference between SQL and JPQL/HQL queries?** <br/>
A2. SQL queries are written using actual table and column names and are case-insensitive. On the other hand, JPQL/HQL queries use entity class and variable names, partially case-sensitive (Java words are case-sensitive), and are converted to SQL by the database dialect.

<br/>

**Q3. What are the possible return types for JPQL/HQL custom queries?** <br/>
A3. For multiple rows, the return type can be `List<T>` (all columns), `List<DataType>` (one column), or `List<Object[]>` (multiple columns but not all). For a single row, the return type can be `Optional<T>` representing the entity or a specific column value.

<br/>

**Q4. Why can't we apply `@Component` or similar stereotypes for an entity class?** <br/>
A4. Entity classes are not Spring beans but are related to database tables. Each row is converted into an instance of the entity class, and their lifecycle and management are handled by JPA/Hibernate.

<br/>

**Q5. What is the purpose of the `CommandLineRunner` interface in Spring Boot?** <br/>
A5. The `CommandLineRunner` interface provides a way to execute code during the application's startup after the Spring context is initialized. It is useful for performing initialization tasks, setting up data, or invoking certain operations.

<br/>

**Q6. How can you save multiple entities using the `saveAll` method?** <br/>
A6. The `saveAll` method accepts an iterable (e.g., a list) of entities and persists them to the database in a single batch operation. It reduces the number of database round-trips, improving performance.

<br/>

**Q7. What is the difference between the `fetchIdAndNames` and `getAllEmpNames` methods?** <br/>
A7. The `fetchIdAndNames` method retrieves both the `empId` and `empName` columns for all employees and returns a list of `Object[]`, where each array contains the selected column values. In contrast, the `getAllEmpNames` method retrieves only the `empName` column as a list of strings.

<br/>

**Q8. What happens when an entity is saved using the `save` method in Spring Data JPA?** <br/>
A8. When an entity is saved using the `save` method in Spring Data JPA, it is either inserted or updated in the database based on its primary key value. If the entity has a null primary key, it is treated as a new entity and inserted. Otherwise, if the primary key exists, the entity is updated.

<br/>

**Q9. What does the `orElseThrow` method do in the context of `Optional`?** <br/>
A9. The `orElseThrow` method is used in conjunction with `Optional` to retrieve the value contained within the `Optional`. If the `Optional` is empty, meaning it doesn't contain a value, the `orElseThrow` method throws an exception specified as its argument.

<br/>

**Q10. What is the purpose of the `@Query`

 annotation in Spring Data JPA?** <br/>
A10. The `@Query` annotation is used to define custom queries in Spring Data JPA. By providing the JPQL or HQL query within the `@Query` annotation, you can execute complex database operations that are not easily achieved using the default query methods provided by Spring Data JPA.

<br/>

**Q11. How does the `fetchEmployeeById` method work in the `EmployeeRepository` interface?** <br/>
A11. The `fetchEmployeeById` method in the `EmployeeRepository` interface is a custom query method that fetches an employee based on their ID. It uses the `@Query` annotation to define the JPQL query, which selects the employee entity from the database table based on the specified ID parameter.

<br/>

**Q12. What happens if the `getEmpNameById` method in the `EmployeeRepository` interface returns an empty `Optional`?** <br/>
A12. If the `getEmpNameById` method in the `EmployeeRepository` interface returns an empty `Optional`, it means that no employee with the specified ID was found in the database. In such cases, you can handle the absence of data by using the `isPresent()` method to check if the `Optional` contains a value before accessing it.

<br/>

**Q13. How can you retrieve only specific columns using a custom query in Spring Data JPA?** <br/>
A13. You can retrieve only specific columns using a custom query in Spring Data JPA by defining the query in the `@Query` annotation and selecting the desired columns in the query. In the repository method's return type, you can use `List<DataType>` to retrieve a single column or `List<Object[]>` to retrieve multiple columns.

<br/>

**Q14. What is the purpose of the `CommandLineRunner` interface in Spring Boot?** <br/>
A14. The `CommandLineRunner` interface in Spring Boot allows you to write code that runs after the Spring application context is initialized. It provides a convenient way to perform any necessary initialization tasks or to execute certain operations before the application starts processing requests.

<br/>

**Q15. How does the `saveAll` method in the `EmployeeRepository` interface work?** <br/>
A15. The `saveAll` method in the `EmployeeRepository` interface accepts a collection of employee entities and saves them to the database. It iterates over the collection and performs either an insert or an update operation for each entity, based on its primary key value.

<br/>

**Q16. What happens if the `fetchEmployeeById` method in the `EmployeeRepository` interface doesn't find an employee with the specified ID?** <br/>
A16. If the `fetchEmployeeById` method in the `EmployeeRepository` interface doesn't find an employee with the specified ID, it returns an empty `Optional`. You can use the `orElseThrow` method to throw a custom exception if the `Optional` is empty, providing a way to handle the case when an employee is not found.

<br/>

**Q17. How can you retrieve specific columns from the database using custom queries in Spring Data JPA?** <br/>
A17. You can retrieve specific columns from the database using custom queries in Spring Data JPA by specifying the desired columns in the `SELECT` clause of the query. By returning a `List<Object[]>` or using projections, you can fetch only the selected columns and map them to the desired result format.

<br/>

**Q18. What is the purpose of the `Entity` annotation in the `Employee` class?** <br/>
A18. The `Entity` annotation in the `Employee` class is from JPA

 and is used to mark the class as an entity, representing a table in the database. It signifies that instances of the `Employee` class can be persisted to the database, and their state will be synchronized with the corresponding table.

<br/>

**Q19. What are the advantages of using the `Optional` class in Java?** <br/>
A19. The `Optional` class in Java provides several advantages, including:

- It helps in writing more expressive code by explicitly handling the absence of a value.
- It helps to avoid `NullPointerExceptions` by providing a safe way to handle nullable values.
- It encourages a more functional programming style by using methods like `map`, `filter`, and `orElse` for value transformations and fallback scenarios.
- It improves code readability by making it clear when a value may be absent and how to handle that situation.

<br/>

**Q20. What is the purpose of the `@Transactional` annotation in Spring?** <br/>
A20. The `@Transactional` annotation in Spring is used to define the boundaries of a transactional method or class. It ensures that the annotated method or all methods within the annotated class are executed within a transactional context. Transactions provide atomicity, consistency, isolation, and durability (ACID) properties to ensure data integrity.