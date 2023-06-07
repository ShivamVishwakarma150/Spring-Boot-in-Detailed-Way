# Here's a detailed explanation of the concepts you mentioned:

1. findBy:
The `findBy` method is an abstract method defined in the repository interface that generates SQL queries for the SELECT operation based on the method name and provided parameters. It allows you to define WHERE conditions using reserved words like `Is`, `Equals`, `LessThan`, `And`, `Or`, etc.

Syntax:
```java
ReturnType findByVariableKeyword(DataType Params);
```

- `ReturnType`: It specifies the return type of the query, such as `List<T>` or `Optional<T>`.
- `VariableKeyword`: It is used to specify the condition using reserved words to filter the results.
- `DataType Params`: These are the parameters used in the query to filter the results.

By using the `findBy` method, you can easily generate queries without writing explicit SQL statements. The method name is derived from the attribute names of the entity class, and it is case-sensitive.

2. @Query:
The `@Query` annotation is used to provide your own custom query using JPQL (Java Persistence Query Language) or HQL (Hibernate Query Language). It allows you to write more complex queries, including both SELECT and non-SELECT operations like UPDATE or DELETE.

Syntax:
```java
@Query("JPQL or HQL query")
ReturnType methodName(Params);
```

- `JPQL or HQL query`: It is a string value that represents the custom query written in JPQL or HQL syntax.
- `ReturnType`: It specifies the return type of the query, such as `List<T>` or `Optional<T>`.
- `Params`: These are the parameters required for the query.

With `@Query`, you have more flexibility to write specific queries tailored to your application's needs. It is particularly useful for complex or non-standard queries that cannot be easily expressed using the `findBy` method convention.

Using custom queries with `@Query` allows you to take advantage of the power and expressiveness of JPQL or HQL. You can join multiple entities, perform aggregations, apply complex filtering conditions, and more.

Overall, the combination of `findBy` methods and `@Query` annotation provides you with the ability to handle a wide range of database operations in a concise and efficient manner.

In Spring Boot Data JPA, you can use custom queries to perform more complex and specific database operations. There are two main approaches for custom queries: using the `findBy` keyword and using the `@Query` annotation.

1. findBy:
The `findBy` method is an abstract method that you define in the repository interface, and it generates a SQL query for the SELECT operation based on the method name and the provided parameters.

Syntax:
```java
ReturnType findByVariableKeyword(DataType params);
```

- `ReturnType`: It specifies the return type of the query, such as `List<T>` or `Optional<T>`.
- `VariableKeyword`: It is used to specify the condition using reserved words like `Is`, `Equals`, `LessThan`, `And`, `Or`, etc.
- `DataType`: It represents the data type of the parameter used in the query.

Example queries:

1. Fetch books having author name exactly 3 characters (use underscore 3 times):
```java
List<Book> findByAuthorLike(String author);
```

2. Fetch books having author name starts with "A":
```java
List<Book> findByAuthorStartingWith(String author);
```

3. Fetch books having author name ends with "S":
```java
List<Book> findByAuthorEndingWith(String author);
```

4. Fetch books having author name contains "R":
```java
List<Book> findByAuthorContaining(String author);
```

5. Fetch books having author name not "AJAY":
```java
List<Book> findByAuthorNot(String author);
```

Note that the method names are derived from the attribute names of the entity class (`Book` in this case) and are case-sensitive.

2. @Query:
The `@Query` annotation allows you to provide your own custom query using JPQL (Java Persistence Query Language) or HQL (Hibernate Query Language). It supports both SELECT and non-SELECT operations (such as UPDATE or DELETE).

Example query:
```java
@Query("SELECT b FROM Book b WHERE b.bookCost > ?1 AND b.author IS NOT NULL OR b.bookType LIKE %?2%")
List<Book> findBooksByCostGreaterThanAndAuthorNotNullOrBookTypeLike(Double cost, String bookType);
```

In the above example, `@Query` specifies a custom SELECT query that fetches books based on the provided conditions. The `?1` and `?2` are parameter placeholders that correspond to the `cost` and `bookType` parameters respectively.

Using custom queries with `@Query` gives you more flexibility and allows you to write complex queries that may not be achievable with the simple `findBy` methods.

It's important to note that custom queries can be more suitable for complex or lengthy queries, as the `findBy` approach may become cumbersome or impractical in such cases. Custom queries provide more control and allow you to write specific SQL or HQL statements tailored to your application's requirements.


<br/>
<br/>
<br/>

Certainly! Here's the breakdown of the provided code:

1. Entity class (Book.java):
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
- This class represents the `Book` entity, which is mapped to the `booktab` table in the database.
- It includes the necessary annotations such as `@Entity` and `@Table` for entity mapping.
- The class also includes the attributes for the `Book` entity, along with their respective annotations such as `@Id` and `@Column` for mapping to table columns.
- Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) are used to generate boilerplate code for getters, setters, constructors, and other common methods.

2. Repository Interface (BookRepository.java):
```java
package com.app.shivam.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByBookCostLessThanEqual(Double bookCost);
	List<Book> findByAuthorIsNull();
	List<Book> findByAuthorLike(String expression);
	List<Book> findByAuthorLikeOrderByBookNameDesc(String expression);
	List<Book> findByAuthorStartingWith(String input);
	List<Book> findByAuthorEndingWith(String input);
	List<Book> findByAuthorContaining(String input);
	List<Book> findByBookIdBetween(Integer id1, Integer id2);
	List<Book> findByBookIdLessThanEqualOrBookTypeLike(Integer bookId, String input);
	Optional<Book> findByBookIdIs(Integer id);
}
```
- This interface extends the `JpaRepository` interface, which provides CRUD operations for the `Book` entity.
- It declares several `findBy` methods with different parameter names to generate queries based on different conditions.
- The methods follow a naming convention: `findBy` + attribute name + (optional condition keywords).
- These methods provide SELECT operations and return the corresponding results as a `List` or `Optional` object.

3. Runner class (TestDataRunner.java):
```java
package com.app.shivam.runner;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Book;
import com.app.shivam.repo.BookRepository;

@Component
public class TestDataRunner implements CommandLineRunner {

	@Autowired
	private BookRepository repo;

	@Override
	public void run(String... args) throws Exception {
		repo.saveAll(Arrays.asList(
			new Book(101, "SBMS", "shivam", 300.0, "Backend"),
			new Book(102, "CORE", "shivam", 200.0, "Backend"),
			new Book(103, "ADV", "vikash", 400.0, "Backend"),
			new Book(104, "REACT", null, 500.0, "Frontend"),
			new Book(105, "HTML", "shivam", 600.0, "Frontend"),
			new Book(106, "CSS", null, 300.0, "Frontend"),
			new Book(107,

 "ANGULAR", "vikash", 800.0, "Frontend"),
			new Book(108, "SQL", "shivam", 200.0, "Database")
		));

		repo.findByBookIdBetween(101, 105).forEach(System.out::println);

		Optional<Book> opt = repo.findByBookIdIs(1050);
		if (opt.isPresent())
			System.out.println(opt.get());
		else
			System.out.println("No Data Found");
	}
}
```
- This class is annotated with `@Component` to indicate that it is a Spring bean.
- It implements the `CommandLineRunner` interface, which allows the class to execute at application startup.
- The class is autowiring the `BookRepository` interface for database operations.
- In the `run` method, sample `Book` data is created and saved to the database using the `saveAll` method of the repository.
- Various `findBy` methods are called to demonstrate different querying options.
- The results are printed to the console using the `forEach` method.

**Summary:**
The provided code demonstrates the usage of the Spring Data JPA `findBy` method to query the `Book` entity based on different conditions such as book cost, author name, and book type. It shows how to retrieve books with a specific book cost, books where the author is null or not null, books with author names starting with a specific letter, and books with author names containing a specific character or string. It also demonstrates additional options such as ordering the results and using the `between` keyword for range-based queries. Finally, the code shows an example of retrieving a single book using the `findByBookIdIs` method.

<br/>
<br/>

# Here's the code for the `BookRepository` interface:

```java
package com.app.shivam.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByBookCostLessThanEqual(Double bookCost);
    List<Book> findByAuthorIsNull();
    List<Book> findByAuthorLike(String expression);
    List<Book> findByAuthorLikeOrderByBookNameDesc(String expression);
    List<Book> findByAuthorStartingWith(String input);
    List<Book> findByAuthorEndingWith(String input);
    List<Book> findByAuthorContaining(String input);
    List<Book> findByBookIdBetween(Integer id1, Integer id2);
    List<Book> findByBookIdLessThanEqualOrBookTypeLike(Integer bookId, String input);
    Optional<Book> findByBookIdIs(Integer id);
}
```

Explanation:

1. The `BookRepository` interface extends the `JpaRepository` interface, which is a part of Spring Data JPA. It provides basic CRUD operations for the `Book` entity.
2. The interface specifies the entity type `Book` and the primary key type `Integer`.
3. The interface declares several methods that follow the naming convention of `findBy` + attribute name + (optional condition keywords). These methods generate queries based on the provided conditions and return the matching results.
4. Here's a breakdown of the methods and their purposes:

- `findByBookCostLessThanEqual(Double bookCost)`: Retrieves a list of books with a book cost less than or equal to the specified `bookCost`.
- `findByAuthorIsNull()`: Retrieves a list of books where the author is `null`.
- `findByAuthorLike(String expression)`: Retrieves a list of books where the author name matches the provided `expression`, which can include wildcard characters for pattern matching.
- `findByAuthorLikeOrderByBookNameDesc(String expression)`: Retrieves a list of books where the author name matches the provided `expression` and orders the results by book name in descending order.
- `findByAuthorStartingWith(String input)`: Retrieves a list of books where the author name starts with the provided `input`.
- `findByAuthorEndingWith(String input)`: Retrieves a list of books where the author name ends with the provided `input`.
- `findByAuthorContaining(String input)`: Retrieves a list of books where the author name contains the provided `input`.
- `findByBookIdBetween(Integer id1, Integer id2)`: Retrieves a list of books with book IDs between `id1` and `id2`, inclusive.
- `findByBookIdLessThanEqualOrBookTypeLike(Integer bookId, String input)`: Retrieves a list of books where the book ID is less than or equal to the specified `bookId` or the book type matches the provided `input`.
- `findByBookIdIs(Integer id)`: Retrieves an optional `Book` object with the specified `id`.

Conclusion:
The `BookRepository` interface provides a set of methods that allow you to query the `Book` entity based on various conditions. These methods generate queries dynamically based on the method names and return the matching results. By utilizing this interface, you can easily perform database operations on the `Book` entity without having to write custom queries manually.

<br/>
<br/>

# Here's the code for the `TestDataRunner` class:

```java
package com.app.shivam.runner;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Book;
import com.app.shivam.repo.BookRepository;

@Component
public class TestDataRunner implements CommandLineRunner {

    @Autowired
    private BookRepository repo;

    @Override
    public void run(String... args) throws Exception {
        repo.saveAll(Arrays.asList(
            new Book(101, "SBMS", "shivam", 300.0, "Backend"),
            new Book(102, "CORE", "shivam", 200.0, "Backend"),
            new Book(103, "ADV", "vikash", 400.0, "Backend"),
            new Book(104, "REACT", null, 500.0, "Frontend"),
            new Book(105, "HTML", "shivam", 600.0, "Frontend"),
            new Book(106, "CSS", null, 300.0, "Frontend"),
            new Book(107, "ANGULAR", "vikash", 800.0, "Frontend"),
            new Book(108, "SQL", "shivam", 200.0, "Database")
        ));

        repo.findByBookIdBetween(101, 105).forEach(System.out::println);

        Optional<Book> opt = repo.findByBookIdIs(1050);
        if (opt.isPresent())
            System.out.println(opt.get());
        else
            System.out.println("No Data Found");
    }
}
```

Explanation:

1. The `TestDataRunner` class is annotated with `@Component` to indicate that it is a Spring bean to be managed by the Spring container.
2. The class implements the `CommandLineRunner` interface, which allows the `run` method to be executed when the Spring Boot application starts.
3. The `@Autowired` annotation is used to inject an instance of the `BookRepository` interface into the class.
4. In the `run` method, a list of `Book` objects is created using the `Arrays.asList` method and initialized with sample data.
5. The `saveAll` method of the `BookRepository` is called to save all the `Book` objects to the database.
6. The `findByBookIdBetween(101, 105)` method is invoked on the repository to retrieve all books with IDs between 101 and 105 (inclusive).
   - The results are then printed using `System.out::println`.
7. The `findByBookIdIs(1050)` method is called to retrieve a book with the ID 1050 (which doesn't exist in the sample data).
   - The result is stored in an `Optional<Book>` object, and if it is present, the book is printed; otherwise, the message "No Data Found" is printed.

Conclusion:

The `TestDataRunner` class serves as an entry point to execute code when the Spring Boot application starts. In the `run` method, sample `Book` objects are created and saved to the database using the `BookRepository`. Then, a couple of queries are performed using the repository's methods: retrieving books with IDs between 101 and 105 and attempting to retrieve a book with ID 1050. The class demonstrates how to use the repository methods to interact with the database and retrieve data based on specific criteria.


<br/>
<br/>

# Here are some possible FAQs related to the code you provided and their corresponding answers:

1. **Q: What is the purpose of the `TestDataRunner` class in this code?**<br/>
   - A: The `TestDataRunner` class is a Spring component that implements the `CommandLineRunner` interface. It is responsible for executing code when the Spring Boot application starts. In this case, it initializes sample data and performs various queries using the `BookRepository` to demonstrate different querying capabilities.

2. **Q: What is the purpose of the `BookRepository` interface?**<br/>
   - A: The `BookRepository` interface extends the `JpaRepository` interface provided by Spring Data JPA. It defines various query methods used for retrieving `Book` entities from the database. These methods utilize the method naming conventions of Spring Data JPA, allowing queries to be generated automatically based on method names.

3. **Q: What is the purpose of the `findByBookCostLessThanEqual` method in the `BookRepository` interface?**<br/>
   - A: The `findByBookCostLessThanEqual` method retrieves a list of books whose `bookCost` is less than or equal to the specified `bookCost` value. It demonstrates the usage of a simple comparison operator (`<=`) in a query method.

4. **Q: How does the `repo.saveAll` method work in the `TestDataRunner` class?**<br/>
   - A: The `repo.saveAll` method saves a list of `Book` objects to the database. In the provided code, a list of `Book` objects is created using the `Arrays.asList` method and then passed to `saveAll` to persist the data in the database.

5. **Q: How does the `findByBookIdBetween` method work in the `BookRepository` interface?**<br/>
   - A: The `findByBookIdBetween` method retrieves a list of books whose `bookId` falls between the specified `id1` and `id2` values (inclusive). It demonstrates the usage of the `between` keyword in a query method.

6. **Q: What happens when the `repo.findByBookIdIs(1050)` method is called in the `TestDataRunner` class?**<br/>
   - A: The `repo.findByBookIdIs(1050)` method attempts to find a book with the ID 1050. If a book with that ID exists in the database, it will be returned as an `Optional<Book>` object. If no book is found, the `Optional` will be empty.

7. **Q: How can I retrieve books whose author is null using the `BookRepository` interface?**<br/>
   - A: You can use the `findByAuthorIsNull` method provided by the `BookRepository` interface. It returns a list of books where the `author` field is null.

8. **Q: How can I retrieve books whose author starts with a specific string using the `BookRepository` interface?**<br/>
   - A: You can use the `findByAuthorStartingWith` method provided by the `BookRepository` interface. It returns a list of books where the `author` field starts with the specified string.

9. **Q: How can I retrieve books whose author contains a specific string using the `BookRepository` interface?**<br/>
   - A: You can use the `findByAuthorContaining` method provided by the `BookRepository` interface. It returns a list of books where the `author` field contains the specified string.

10. **Q: How can I retrieve books sorted by author name in descending order using the `BookRepository` interface?**<br/>
    - A: You can use the `findByAuthorLikeOrderByBookNameDesc` method provided by the `BookRepository` interface. It returns a list of books where the `author` field matches the specified expression, sorted in descending order by `bookName`.

These are just a few examples of possible FAQs and answers based on the code you provided. Feel free to ask about any specific aspect or query method if you'd like more details.

<br/>
<br/>

# Let's break down the points you mentioned about `SQL` and `JPQL/HQL` and provide a detailed explanation for each:

1. **SQL** (Structured Query Language):
   - SQL queries are executed at the database level and are specific to the underlying database system.
   - SQL queries are written using tables and columns, and the syntax may vary depending on the database vendor.
   - SQL queries are case-insensitive for keywords like SELECT, FROM, and WHERE.
   - The same SQL query executed on one database (e.g., DB1) may or may not be executed on another database (e.g., DB2), as the schema and data structures can differ.

2. **JPQL/HQL** (Java Persistence Query Language / Hibernate Query Language):
   - JPQL and HQL are object-oriented query languages used in Java Persistence frameworks like JPA (Java Persistence API) and Hibernate.
   - JPQL and HQL queries are written using class names and variable names, which represent entities and their attributes, instead of tables and columns.
   - JPQL and HQL are designed to work with entities and their relationships, providing an object-oriented approach to querying data.
   - The syntax of JPQL and HQL is similar to SQL but not identical. They have some differences and additional features specific to the object-oriented nature of these languages.
   - JPQL and HQL queries are case-sensitive when it comes to Java identifiers like class names and variable names, but case-insensitive for keywords like SELECT, FROM, and WHERE.

3. **Object-Oriented Query Concept**:
   - JPQL and HQL leverage the object-oriented paradigm to write queries that align with the structure and relationships of entities in the Java domain model.
   - Instead of directly querying database tables and columns, JPQL/HQL queries operate on entities and their attributes, treating them as objects.
   - This approach allows developers to think in terms of the object model rather than database structures when querying data, providing a more intuitive and flexible querying mechanism.

4. **Dialect-Generated SQL Queries**:
   - JPQL and HQL queries are converted into equivalent SQL queries at runtime by the database dialect provided by the persistence framework (e.g., Hibernate).
   - The dialect handles the translation of JPQL/HQL queries to the appropriate SQL syntax based on the underlying database system.
   - This abstraction allows developers to write database-agnostic queries using JPQL/HQL, and the persistence framework takes care of generating the appropriate SQL statements specific to the database being used.

In summary, SQL is a database-specific query language executed at the database level, while JPQL/HQL are object-oriented query languages used in Java Persistence frameworks. JPQL/HQL provide a more abstract and object-oriented way of querying data by operating on entities and their attributes. The queries written in JPQL/HQL are then translated into equivalent SQL queries by the persistence framework's dialect at runtime, allowing developers to work with the object model while still interacting with the database.


<br/>
<br/>
<br/>

# Here is a detailed explanation of the internal flow and key points mentioned:

**Internal Flow:**
1. We define an abstract method in the repository interface, following the naming conventions of Spring Data JPA.
2. Spring Boot Data JPA generates the implementation code for the abstract method, using the provided method name and parameter types.
3. Hibernate, the underlying ORM framework, handles the execution of the generated code.
4. Hibernate internally calls the Dialect, which is responsible for generating the appropriate SQL query based on the target database.
5. The Dialect converts the abstract query into the specific SQL format supported by the database.
6. Finally, the generated SQL query is executed at the database.

**Explanation:**
- When we define an abstract method in the repository interface, we are essentially declaring a query method that follows a specific naming convention. Spring Boot Data JPA uses this method declaration to generate the corresponding implementation code.
- The generated code is responsible for executing the query and fetching the results from the database.
- Hibernate, as the underlying ORM framework, handles the execution of the generated code and interacts with the database.
- Hibernate internally utilizes a Dialect, which is a database-specific component responsible for translating the abstract query into the appropriate SQL format supported by the target database.
- The Dialect knows how to convert the abstract query into valid SQL syntax, considering the specific rules and syntax of the database being used.
- Once the SQL query is generated, it is executed at the database, and the results are returned.
- The type of the returned results depends on the columns being fetched:
  - When fetching all columns, the result can be represented as a `List<T>`, where `T` is the entity class.
  - When fetching only one column, the result can be represented as a `List<DataType>`, where `DataType` corresponds to the type of the fetched column.
  - When fetching more than one column but not all, the result can be represented as a `List<Object[]>`, where each array element represents a row of fetched values.

By following this flow, Spring Boot Data JPA and Hibernate provide a convenient way to interact with the database by writing abstract query methods in the repository interface, while internally handling the translation to SQL and database-specific operations.


<br/>
<br/>
<br/>

# Here's a detailed explanation of the three ways a SELECT query can return output based on columns:

1. `List<T>`: For fetching all columns
- When the SELECT query retrieves all columns of a table, the result can be represented as a `List<T>`, where `T` represents the entity class associated with the table.
- Each element in the list corresponds to a row in the result set, and the properties of the entity class are populated with the fetched column values.
- This approach is useful when you need to retrieve complete entity objects with all their associated attributes.

2. `List<DataType>`: For fetching one column
- In cases where the SELECT query retrieves only a single column from a table, the result can be represented as a `List<DataType>`, where `DataType` represents the data type of the fetched column.
- Each element in the list represents a value of the fetched column.
- This approach is suitable when you are interested in extracting a specific column's values without the need for complete entity objects.

3. `List<Object[]>`: For fetching more than one column (but not all)
- When the SELECT query retrieves multiple columns from a table, but not all of them, the result can be represented as a `List<Object[]>`.
- Each element in the list is an array (`Object[]`), where each array element corresponds to a column value in the order they appear in the query's SELECT clause.
- This approach allows you to retrieve a subset of columns from a table without the need for complete entity objects or specifying custom DTOs (Data Transfer Objects) explicitly.

These different representations provide flexibility in handling query results based on your specific requirements. By choosing the appropriate return type, you can effectively retrieve the necessary data from the database and process it accordingly in your application.

# Here are code examples illustrating the three ways a SELECT query can return output based on columns:

## **1. `List<T>`: For fetching all columns**

Suppose we have an entity class named `Employee` representing an employee table with multiple columns:

```java
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String department;
    private int salary;
    
    // Constructors, getters, and setters
}
```

To fetch all columns using Spring Data JPA, the repository method can be defined as follows:

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAll();
}
```

Now you can use the repository method to retrieve all employees:

```java
List<Employee> employees = employeeRepository.findAll();
for (Employee employee : employees) {
    System.out.println(employee.getName() + " - " + employee.getSalary());
}
```

## **2. `List<DataType>`: For fetching one column**

Let's say we want to fetch only the names of employees. In this case, the repository method can be defined as follows:

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<String> findAllNames();
}
```

Now you can retrieve only the names of employees:

```java
List<String> employeeNames = employeeRepository.findAllNames();
for (String name : employeeNames) {
    System.out.println(name);
}
```

## **3. `List<Object[]>`: For fetching more than one column (but not all)**

Suppose we want to fetch the names and salaries of employees. The repository method can be defined as follows:

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Object[]> findAllNamesAndSalaries();
}
```

Now you can retrieve the names and salaries of employees:

```java
List<Object[]> namesAndSalaries = employeeRepository.findAllNamesAndSalaries();
for (Object[] row : namesAndSalaries) {
    String name = (String) row[0];
    int salary = (int) row[1];
    System.out.println(name + " - " + salary);
}
```

These code examples demonstrate how to use the different return types to fetch the desired columns from a table using Spring Data JPA.