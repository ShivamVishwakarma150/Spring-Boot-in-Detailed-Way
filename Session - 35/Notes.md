# Let's break down the points you mentioned and provide a detailed explanation for each:

1. "If we use Optional as ReturnType": The `Optional` type in many programming languages is used to indicate that a value may or may not be present. It is often used when a method or operation could potentially return a null or empty result. By using `Optional` as the return type, you're signaling that the result of the query may or may not have a value.

2. "Query may return Multiple rows output": This means that the query you execute can potentially return more than one row of data as a result. In a database context, a query is used to retrieve information from a database, and if the query matches multiple rows based on the specified criteria, all those rows will be returned as output.

3. "NonUniqueResultException: query did not return a unique result: 5 may be raised": This exception typically occurs when you expect a single result from a query, but the query actually returns multiple rows. The "5" mentioned here is an example value indicating that the query returned five rows. Since the expectation was to have a unique (single) result, the exception is raised to indicate that the query did not meet this expectation.

In summary, if you use `Optional` as the return type for a query that can potentially return multiple rows of data, you may encounter a `NonUniqueResultException` if the query indeed returns more than one row. This exception is raised to indicate that the expected uniqueness of the result was violated.

To handle this situation, you have a few options:

1. Adjust the query: Modify the query to include additional criteria or constraints that ensure a unique result is returned. This could involve adding more specific conditions to the query or using aggregate functions to combine multiple rows into a single result.

2. Revise the return type: Instead of using `Optional`, you can change the return type to a collection or list that can hold multiple results. This way, you can handle and process all the returned rows effectively.

3. Implement appropriate error handling: If you expect a unique result and receiving multiple rows is considered an exceptional scenario, you can catch the `NonUniqueResultException` and handle it accordingly. You may choose to log an error, throw a custom exception, or take any other appropriate action based on your application's requirements.

It's important to consider the specific programming language, database system, and frameworks you're using, as the exact implementation and error handling may vary.



<br/>
<br/>
<br/>

# Let's provide a detailed explanation for each point you mentioned:

1. "**`List`** can store any output (1 row or multiple rows)": A `List` is a data structure that can hold multiple elements of the same type. It provides an ordered collection, meaning that elements are stored in a specific sequence. In the context of querying a database, you can use a `List` to store the result of a query that may return either one row or multiple rows. Each row of data will typically be represented as an object, and these objects will be added to the `List`.

For example, if you execute a query to retrieve customer information from a database, the query might return multiple rows, where each row represents a customer. You can store these rows in a `List` of customer objects, allowing you to access and process each individual customer record.

2. "**`Optional`** can store single object (1 row data)": `Optional` is a type that represents an object that may or may not be present. It is commonly used when the result of an operation or method can be null or empty. In the context of querying a database, if you expect a query to return a single row of data, you can use `Optional` as the return type to indicate that the result may or may not have a value.

For example, if you execute a query to retrieve a specific customer's information by their ID, the query is expected to return only one row. You can use `Optional` as the return type, indicating that the customer object will be wrapped in an `Optional`. If the query finds a matching customer, the `Optional` will contain the customer object. If the query doesn't find a matching customer, the `Optional` will be empty (no value present).

Using `Optional` allows you to explicitly handle the case when a result is not found, instead of returning a null value, which could lead to null pointer exceptions if not handled properly.

To summarize, `List` is a collection that can store multiple elements, making it suitable for storing query results with multiple rows. `Optional` is used to wrap a single object, providing a way to handle scenarios where a query may or may not have a result. By using `List` or `Optional` appropriately, you can effectively handle the different outcomes of your database queries and process the data accordingly.

<br/>
<br/>
<br/>


# Let's go through each point and provide a detailed explanation:

1. "**`Positional Parameters`** (?1, ?2, etc.)": Positional parameters are placeholders used in a query to indicate inputs at runtime. These placeholders are typically represented by question marks followed by a number, such as ?1, ?2, and so on. When using positional parameters, the inputs are provided to the query in the order of their appearance in the query string.

In your example query, the positional parameters ?1, ?2, and ?3 are used to represent the author, bookCost, and bookType values respectively. When executing the query, you would need to provide the corresponding values in the same order as the positional parameters appear in the query.

2. "**`Named Parameters`** (:abc, :empId, :myIds, :names, etc.)": Named parameters are another way of passing or identifying parameters in a query based on their names. Instead of using positional placeholders, named parameters use a colon followed by a name, such as :abc, :empId, and so on. Named parameters provide a more explicit and readable way to specify input values in a query.

When using named parameters, you can associate values with the parameter names using either the `@Param` annotation or by naming the method parameters the same as the named parameters in the query. This allows the framework to match the provided values with the correct named parameters in the query.

3. "Finally converted to SQL Parameters (? ? ? ? style)": The framework, such as JPA, internally converts the named or positional parameters to SQL parameters using the specific syntax of the underlying database. The exact syntax may vary depending on the database system you are using. In your case, the positional parameters ?1, ?2, and ?3 would be converted to SQL parameters using the question mark style, where each question mark represents a parameter.

4. "Number of parameters in the query and method parameter must be the same": When using named or positional parameters, it's important to ensure that the number of parameters provided at runtime matches the number of parameters defined in the query string. If the counts don't match, an `IllegalArgumentException` will be thrown. This is to ensure that all parameters required by the query are provided to avoid any ambiguity.

In your example, the method `getBooksAuthorCost` expects three parameters: `String author`, `Double bookCost`, and `String bookType`. Therefore, you should provide three corresponding values when executing the method.

Regarding the MySQL examples you provided, the query selects rows from the `booktab` table based on the condition specified. The `like` operator is used to match patterns in the `bauth` column. In MySQL, the `like` operator is case-insensitive by default, so both `'r%'` and `'R%'` would yield the same result, matching any rows where the `bauth` value starts with either 'r' or 'R'.

<br/>
<br/>

## **Q) Do we need to provide @Repository on Data JPA Repository interface?** <br/><br/>

**1.** "In Spring, we need to define interface and implementation class for Repository. On the Impl class, we need to define @Repository": In Spring Framework, when working with repositories, it is common to define an interface that represents the repository contract and an implementation class that provides the actual implementation of the repository methods. The interface typically extends a Spring Data JPA-specific interface, such as `JpaRepository`.

Conventionally, when you create the implementation class for the repository, you would annotate it with `@Repository` to indicate that it is a Spring-managed repository bean. The `@Repository` annotation is a specialization of the `@Component` annotation and serves as a marker for Spring to identify and handle repositories appropriately. <br/><br/>


**2.** "In Data JPA Dynamic Proxy (Impl class + object) is given by Spring Boot. So directly you use it where you want (@Autowired)": With Spring Data JPA, you have the option to let Spring Boot automatically generate the implementation class for your repository interface. This is achieved through a feature called dynamic proxying.

When you create a repository interface that extends a Spring Data JPA-specific interface, such as `JpaRepository`, Spring Boot automatically generates a proxy implementation of that interface during runtime. This proxy implementation includes the necessary CRUD methods and other repository-related functionality. The proxy implementation is then made available as a bean that can be injected using the `@Autowired` annotation.

By leveraging this dynamic proxying feature, you can directly use the repository interface without explicitly implementing the repository class or annotating it with `@Repository`. Spring Boot takes care of generating the implementation for you behind the scenes, allowing you to autowire and use the repository interface wherever you need it in your code.

To summarize, when using Spring Data JPA, it is not required to annotate the repository interface with `@Repository`. Spring Boot automatically generates the implementation class for the repository interface using dynamic proxying. This generated implementation can be directly used by autowiring the repository interface without the need for a separate implementation class.

<br/>
<br/>
<br/>

# Here is the code you provided:

1. Entity:
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

The `Book` class is an entity class representing a book stored in the database. It is annotated with `@Entity` to indicate that it is an entity class, and the `@Table` annotation specifies the name of the corresponding database table. The class uses Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) to generate getter and setter methods, constructors, and other common methods automatically.

2. Runner class:
```java
package com.app.shivam.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Book;
import com.app.shivam.repo.BookRepository;

@Component
public class TestDataRunner implements CommandLineRunner {
    @Autowired
    private BookRepository repo;
    
    public void run(String... args) throws Exception {
        repo.saveAll(
                Arrays.asList(
                        new Book(101, "SBMS", "shivam", 300.0, "Backend"),
                        new Book(102, "CORE", "shivam", 200.0, "Backend"),
                        new Book(103, "ADV", "vikash", 400.0, "Backend"),
                        new Book(104, "REACT", null, 500.0, "Frontend"),
                        new Book(105, "HTML", "shivam", 600.0, "Frontend"),
                        new Book(106, "CSS", null, 300.0, "Frontend"),
                        new Book(107, "ANGULAR", "vikash", 800.0, "Frontend"),
                        new Book(108, "SQL", "shivam", 200.0, "Database")
                    )
            );
        
        repo.getBooksByAuthor("shivam").forEach(System.out::println);
        //repo.getBooksAuthorCost("r%", 200.0).forEach(System.out::println);
        //repo.getBooksIds(Arrays.asList(101,106,108,111,134)).forEach(System.out::println);
    }
}
```

The `TestDataRunner` class is a command-line runner that executes some code when the Spring Boot application starts. It is annotated with `@Component` to indicate that it is a Spring-managed component. In the `run` method, the code saves a list of books to the database using the `BookRepository`. Then, it demonstrates using the repository's `getBooksByAuthor` method to retrieve books by author and prints them.

3. Repository class:
```java
package com.app.shivam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.shivam.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.author = :abc")
    List<Book> getBooksByAuthor(@Param("abc

")String author);
    
    @Query("SELECT b FROM Book b WHERE b.author like ?1 OR b.bookCost > ?2 OR b.bookType=?3")
    List<Book> getBooksAuthorCost(String author, Double bookCost);
    
    @Query("SELECT b FROM Book b WHERE b.bookId in (:inputs)")
    List<Book> getBooksIds(List<Integer> inputs);
}
```

The `BookRepository` interface extends `JpaRepository`, which provides the basic CRUD operations for the `Book` entity. It also defines three custom query methods using Spring Data JPA's query creation mechanism.

- The `getBooksByAuthor` method retrieves books based on the author's name using a named parameter `:abc` in the query.
- The `getBooksAuthorCost` method retrieves books based on the author's name, book cost, and book type using positional parameters (?1, ?2, ?3) in the query.
- The `getBooksIds` method retrieves books based on a list of book IDs using a named parameter `:inputs` in the query.

Now, let's summarize:

- The `Book` class is the entity representing a book in the database.
- The `TestDataRunner` class demonstrates the usage of the `BookRepository` by saving books to the database and querying them based on the author's name.
- The `BookRepository` interface extends `JpaRepository` and defines custom query methods to retrieve books based on different criteria.

This setup allows you to perform basic CRUD operations on the `Book` entity and also define custom queries as needed.

<br/>

# Here is a separate explanation for each line of code in the `BookRepository` interface:

```java
package com.app.shivam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.shivam.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
```

- The `BookRepository` interface is defined as a Spring Data JPA repository by extending the `JpaRepository` interface. It provides generic CRUD operations for the `Book` entity, with the entity type `Book` and the ID type `Integer`.

```java
    @Query("SELECT b FROM Book b WHERE b.author = :abc")
    List<Book> getBooksByAuthor(@Param("abc")String author);
```

- The `getBooksByAuthor` method is a custom query method defined in the repository. It retrieves a list of books based on the author's name. The `@Query` annotation is used to specify the JPQL query. In this case, the query selects books (`b`) from the `Book` entity where the author (`b.author`) is equal to the parameter `:abc`. The `@Param` annotation is used to map the method parameter `author` to the named parameter `:abc` in the query.

```java
    @Query("SELECT b FROM Book b WHERE b.author like ?1 OR b.bookCost > ?2 OR b.bookType=?3")
    List<Book> getBooksAuthorCost(String author, Double bookCost,String bookType);
```

- The `getBooksAuthorCost` method is another custom query method. It retrieves a list of books based on the author's name, book cost, and book type. The query uses positional parameters (`?1`, `?2`, `?3`) to represent the method parameters `author`, `bookCost`, and `bookType`, respectively. The `%` wildcard is used in the `like` expression to match any substring for the author name.

```java
    @Query("SELECT b FROM Book b WHERE b.bookId in (:inputs)")
    List<Book> getBooksIds(List<Integer> inputs);
```

- The `getBooksIds` method is a custom query method that retrieves a list of books based on a list of book IDs. The query uses a named parameter `:inputs` and the `in` operator to match books whose `bookId` is contained in the provided `inputs` list.

```java
}
```

- The closing brace denotes the end of the `BookRepository` interface.

