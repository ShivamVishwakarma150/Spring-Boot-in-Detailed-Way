# Here are the detailed explanations for each point you mentioned:

1. **@Query**: 
   - This annotation is used in Spring Data JPA to define custom queries.
   - It supports both SELECT and NON-SELECT operations.
   - By default, when you use `@Query` without any additional annotations, Spring Data JPA treats it as a SELECT operation.

2. **NON-SELECT Operations**:
   - The NON-SELECT operations referred to in the context of `@Query` are UPDATE and DELETE operations.
   - INSERT operations are not supported directly by `@Query`. For INSERT operations, you typically use the repository's `save` or `persist` methods.

3. **@Modifying + @Query**:
   - When you combine the `@Modifying` annotation with `@Query`, it indicates that the query is intended for a NON-SELECT operation (UPDATE or DELETE).
   - `@Modifying` is used to notify Spring Data JPA that the query modifies the data in the database.

4. **@Query as SELECT operation**:
   - If you use `@Query` without `@Modifying`, Spring Data JPA interprets it as a SELECT operation.
   - It expects the query to return results, either a single entity or a collection of entities.

5. **@Modifying for UPDATE and DELETE**:
   - When you use `@Modifying` with `@Query`, it informs Spring Data JPA that the query is intended for data modification, such as UPDATE or DELETE.
   - This combination is used when you want to perform bulk updates or deletions based on certain criteria.

6. **Behavior of @Query annotations**:
   - `@Query` on its own is suitable for SELECT operations, where you want to retrieve data from the database.
   - `@Modifying` + `@Query` is used for non-select operations, such as updating or deleting data.
   - The `@Modifying` annotation is required for non-select operations to ensure that the query is recognized as a modification query by Spring Data JPA.

It's important to note that the behavior of `@Query` and `@Modifying` can vary depending on the underlying database and its specific syntax and rules for queries. Make sure to use the appropriate combination of annotations based on the desired operation and the database requirements.

<br/>
<br/>
<br/>

# Transaction Management:

1. **Buffer Memory**:
   - When a client (CLI/GUI/App) sends a query (UPDATE/DELETE/INSERT) to the database, the database system does not immediately execute the query against the actual data stored on disk.
   - Instead, the query is executed in a temporary area of memory known as the buffer memory or cache.
   - The buffer memory holds the changes made by the query until they are committed or rolled back.

2. **Commit**:
   - Commit is an operation that makes the changes in the buffer memory permanent by applying them to the actual data stored in the database.
   - When a commit is called, the changes made by the query are written to the database, and the data is updated accordingly.
   - Once the changes are committed, they become durable and will persist even if there is a system failure or restart.

3. **Rollback**:
   - Rollback is an operation that cancels the changes made by the query and discards them from the buffer memory.
   - If a rollback is called, the changes made by the query are not applied to the database, and the data remains unchanged.
   - Rollback is typically used when there is an error or an exceptional condition, and it allows you to revert the database to its previous state.

4. **Transaction Management**:
   - Transaction management refers to the process of ensuring the integrity and consistency of data when multiple database operations are executed together as a single logical unit.
   - A transaction is a sequence of database operations that should be treated as a single atomic unit of work.
   - Transaction management includes managing the execution, commit, and rollback of these operations.

5. **ACID Properties**:
   - Transaction management follows the principles of ACID (Atomicity, Consistency, Isolation, Durability) properties to ensure reliable and predictable database operations.
   - Atomicity ensures that a transaction is treated as an indivisible unit, either fully completed or fully rolled back.
   - Consistency ensures that the database remains in a valid state before and after the transaction.
   - Isolation ensures that the intermediate state of a transaction is not visible to other concurrent transactions.
   - Durability ensures that the committed changes are permanent and will survive any system failures.

Transaction management is crucial for maintaining data integrity and ensuring reliable database operations. It allows you to group related database operations together and ensure they are executed consistently. By properly managing transactions, you can control the outcome of database operations and handle exceptional conditions effectively.

<br/>
<br/>
<br/>

# The code snippets here provided demonstrate the transaction management approach in both JDBC and JPA/Hibernate. Let's break down each part and explain their significance:

**JDBC Transaction Management:**

```java
Class.forName("DriverClassName");
Connection con = DriverManager.getConnection(url, un, pwd);
con.setAutoCommit(false); // Disable AutoCommit
try {
   // Statements, executeUpdate...
   
   con.commit();
} catch (SQLException e) {
   sysout(e);
   con.rollback();
} finally {
   if (con != null) {
      // Inside try-catch
      con.close();
   }
}
```

- `Class.forName("DriverClassName")`: This line loads the JDBC driver class for the specified database.
- `DriverManager.getConnection(url, un, pwd)`: Establishes a connection to the database using the provided URL, username, and password.
- `con.setAutoCommit(false)`: Disables the auto-commit mode of the connection, which means changes made in the database will not be automatically committed.
- `try` block: Contains the statements or queries that modify the database.
- `con.commit()`: Commits the changes made in the transaction and makes them permanent in the database.
- `catch` block: Handles any exceptions that occur during the execution of the transaction.
- `con.rollback()`: Rolls back the changes made in the transaction if an exception occurs, reverting the database to its previous state.
- `finally` block: Closes the connection to the database, ensuring it is released even if an exception occurs.

**JPA/Hibernate Transaction Management:**

```java
EntityTransaction tx = null;
try {
   tx = em.getTransaction();
   tx.begin();
   // Some operation

   tx.commit();
} catch (Exception e) {
   tx.rollback();
}
```

- `em.getTransaction()`: Retrieves the current transaction associated with the EntityManager.
- `tx.begin()`: Starts a new transaction.
- `try` block: Contains the statements or queries that modify the database using JPA/Hibernate.
- `tx.commit()`: Commits the changes made in the transaction and makes them permanent in the database.
- `catch` block: Handles any exceptions that occur during the execution of the transaction.
- `tx.rollback()`: Rolls back the changes made in the transaction if an exception occurs, reverting the database to its previous state.

In both approaches, transactions are essential for ensuring data integrity and consistency. They allow you to group related database operations together and ensure they are either fully committed or fully rolled back in case of an error or exception.

It's important to note that JPA/Hibernate provides higher-level abstractions and simplifies transaction management compared to traditional JDBC code. JPA's EntityManager and Hibernate's Session handle many aspects of transaction management automatically, such as caching, dirty checking, and automatic flushing.

<br/>
<br/>
<br/>

**Spring Framework:**
The `@Transactional` annotation is used in the Spring Framework to manage transactions. It ensures that all queries or one query within a method are executed successfully. If all the operations are successful, the transaction is committed, and the changes are saved in the database. If any operation fails, the transaction is rolled back, and all the changes made within that transaction are discarded.

To enable transaction management, you need to apply the `@Transactional` annotation manually over the methods that involve database operations. It is recommended to use `@Transactional` for non-select operations, such as insert, update, and delete, where data modification is involved.

**Spring Boot:**
In Spring Boot with Spring Data JPA, most common database operations (such as CRUD operations) are provided out of the box by the framework. You can simply define repository interfaces and Spring Data JPA will generate the necessary queries based on method names or annotations.

However, if you need to define custom non-select operations (e.g., complex update or delete queries), you can use the `@Modifying` annotation in combination with the `@Query` annotation. The `@Modifying` annotation indicates that the query is an update or delete operation rather than a select operation.

In addition, it is recommended to apply the `@Transactional` annotation alongside `@Modifying` and `@Query` annotations when performing custom non-select operations. The `@Transactional` annotation ensures that the operation is executed within a transaction, allowing for proper transactional behavior like commit or rollback based on the success or failure of the operation.

So, to perform custom non-select operations in Spring Boot with Data JPA, you need to use the following annotations:
- `@Query`: to define the custom query using JPQL or SQL.
- `@Modifying`: to indicate that the query is an update or delete operation.
- `@Transactional`: to manage the transaction and handle commit or rollback based on the outcome of the operation.

<br/>
<br/>

**Q) What annotations are required for custom NON-SELECT operations using Data JPA? and Why?**<br/>

**A)** Three annotations are required for custom non-select operations using Data JPA.

i. `@Query`: The `@Query` annotation is used to define custom queries using JPQL (Java Persistence Query Language) or HQL (Hibernate Query Language). By annotating a method with `@Query`, you can specify the custom query to be executed for the method.

ii. `@Modifying`: The `@Modifying` annotation is used to indicate that the query is an update or delete operation rather than a select operation. When combined with the `@Query` annotation, it signals to the JPA provider that the query modifies the data in the database.

iii. `@Transactional`: The `@Transactional` annotation is used to manage the transactional behavior of the custom non-select operation. By applying `@Transactional` to a method, you ensure that the method is executed within a transaction context. If the method completes successfully, the transaction is committed, and the changes made by the operation are persisted in the database. On the other hand, if an exception occurs during the method execution, the transaction is rolled back, and any changes made within the transaction are discarded.

These three annotations work together to enable custom non-select operations in Data JPA. The `@Query` annotation defines the query, the `@Modifying` annotation indicates that it is a non-select operation, and the `@Transactional` annotation ensures proper transactional behavior for the operation, handling commit or rollback based on the success or failure of the operation.

<br/>
<br/>


#   Usecase#2 of `@Transactional`

```
    Usecase#2 of @Transactional

            Acc101         Acc201
            bal=500         bal=1500
                -500           +500
                q1            q2
            UPDATE             UPDATE
```

In the given use case, we have two accounts (`Acc101` and `Acc201`) with initial balances of 500 and 1500 respectively. We want to transfer 500 from `Acc101` to `Acc201` using two update queries. Let's analyze the scenario with the `@Transactional` annotation.

```java
@Transactional
methodTx() {
    UPDATE Account SET bal=bal-500 WHERE ac="Acc101"; // executed
    UPDATE Account SET bal=bal+500 WHERE ac="Acc201"; // failed
}
```


1. `@Transactional` Annotation: By applying the `@Transactional` annotation to the `methodTx()` method, we ensure that the entire method is executed within a single transaction. It provides transactional behavior, including committing the changes if all operations succeed or rolling back the changes if any operation fails.

2. TransactionRequiredException: If we miss writing the `@Transactional` annotation for the `UPDATE` or `DELETE` operations, Data JPA will throw a `TransactionRequiredException`. This exception indicates that the operation requires a transaction context, and without the `@Transactional` annotation, the operation cannot be executed.

3. `@Modifying` Annotation: The `@Modifying` annotation is used to specify that the query is a non-select operation, such as an update or delete. It is required when executing update or delete queries with Data JPA.

4. QueryExecutionRequestException: If we miss writing the `@Modifying` annotation for the update or delete query, Data JPA will throw a `QueryExecutionRequestException`. This exception indicates that the query execution request is not supported for DML (Data Manipulation Language) operations like update or delete, and the `@Modifying` annotation is necessary for such operations.

In the given use case, when the `methodTx()` is executed, the first update query is executed successfully, deducting 500 from `Acc101`. However, the second update query fails, as it tries to add 500 to `Acc201`. Since the transaction is managed by the `@Transactional` annotation, the changes made by the first update query will be rolled back, and the balances of both accounts will remain unchanged. This ensures data consistency and integrity in the case of a failure during the transaction.

By using the `@Transactional` annotation, we can encapsulate multiple database operations into a single transaction and handle the commit or rollback automatically based on the success or failure of the transaction.

<br/>
<br/>

# Here's the breakdown of the provided example:

1. Entity (Product.java)
```java
@Entity
@Table(name = "product_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@Column(name = "pid")
	private Integer prodId;
	
	@Column(name = "pname")
	private String prodName;
	
	@Column(name = "pcost")
	private Double prodCost;
}
```
Explanation:
- This is an entity class representing a Product.
- It is annotated with `@Entity` to mark it as a persistent entity in JPA.
- The `@Table` annotation specifies the name of the database table associated with the entity.
- The class uses Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) to generate getters, setters, constructors, and other common methods.

2. Repository Interface (ProductRepository.java)
```java
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Transactional
	@Modifying
	@Query("UPDATE Product SET prodName=:pn WHERE prodId=:pid")
	int updateNameById(@Param("pn") String pn, @Param("pid") Integer pid);
	
	@Transactional
	@Modifying
	@Query("DELETE Product WHERE prodId=:pid")
	int removeById(@Param("pid") Integer pid);
}
```
Explanation:
- This is a Spring Data JPA repository interface for the Product entity.
- It extends the `JpaRepository` interface, which provides CRUD operations for the entity.
- The `@Transactional` annotation is used to indicate that the methods in this repository interface should be executed within a transaction.
- The `@Modifying` annotation indicates that the methods are performing non-select operations (update and delete).
- The `@Query` annotation defines custom JPQL queries for updating the product name by ID and deleting a product by ID.
- The `@Param` annotation is used to map the method parameters to named parameters in the JPQL queries.

3. Runner class (TestDataRunner.java)
```java
@Component
public final class TestDataRunner implements CommandLineRunner {
	@Autowired
	private ProductRepository repo;
	
	public void run(String... args) throws Exception {
		repo.save(new Product(101, "AA", 200.0));
		System.out.println("___________________________");
		int count = repo.updateNameById("PEN", 101);
		System.out.println(count);
		int count2 = repo.removeById(101);
		System.out.println(count2);
	}
}
```
Explanation:
- This is a Spring Boot `CommandLineRunner` component that runs on application startup.
- It demonstrates the usage of the `ProductRepository` by saving a new product, updating its name, and then deleting it.
- The `@Autowired` annotation injects an instance of the `ProductRepository`.
- Inside the `run` method, a new `Product` object is created and saved using the repository's `save` method.
- The `updateNameById` method updates the product name by calling the custom query defined in the repository.
- The `removeById` method deletes the product by calling the custom delete query defined in the repository.

Conclusion:
- The provided example showcases the usage of Spring Data JPA with the `@Modifying` and `@Query` annotations to perform custom non-select operations (update and delete) on the `Product` entity.
- The `@Transactional` annotation ensures that the operations are executed within a transaction, providing atomicity and consistency.
- By using these annotations and Spring Data JPA, database operations can be simplified and performed efficiently in a Spring Boot application.

<br/>
<br/>
<br/>

# Here are all FAQ's

1. **What is the purpose of the `@Transactional` annotation in Spring Boot?**
   <br/>The `@Transactional` annotation is used to define the scope of a transaction in a Spring Boot application. It ensures that a series of database operations either complete successfully and are committed, or they are rolled back if any operation fails.

2. **When should the `@Transactional` annotation be applied in a Spring Boot application?**
   <br/>The `@Transactional` annotation should be applied to methods that involve database operations and require transactional management. It is typically used for non-select operations like update, delete, or insert to maintain data consistency.

3. **What is the significance of the `@Modifying` annotation in the repository interface?**
   <br/>The `@Modifying` annotation is used in conjunction with the `@Query` annotation to indicate that the query is an update or delete operation, rather than a select operation. It informs Spring Data JPA that the query modifies the data in the database.

4. **Why is the `@Query` annotation used in the repository interface?**
   <br/>The `@Query` annotation is used to define custom queries in the repository interface. It allows you to write JPQL (Java Persistence Query Language) or HQL (Hibernate Query Language) queries to retrieve or manipulate data from the database.

5. **How does the `@Query` annotation differ when used with and without the `@Modifying` annotation?**
   <br/>When used without the `@Modifying` annotation, the `@Query` annotation is used for select operations. It expects the query to return a result set. When used with the `@Modifying` annotation, it indicates a non-select operation such as update or delete, and the query is expected to modify the data in the database.

6. **What exceptions can occur if the `@Transactional` annotation is missing for an update or delete operation?**
   <br/>If the `@Transactional` annotation is missing for an update or delete operation, a `TransactionRequiredException` can occur. This exception is thrown to indicate that a transaction is required to perform the update or delete operation.

7. **What exceptions can occur if the `@Modifying` annotation is missing for an update or delete operation?**
   <br/>If the `@Modifying` annotation is missing for an update or delete operation, a `QueryExecutionRequestException` can occur. This exception is thrown to indicate that the query execution request is not supported for DML (Data Manipulation Language) operations like update or delete.

8. **How does Spring Data JPA handle transaction management for the methods annotated with `@Transactional`?**
   <br/>When a method is annotated with `@Transactional`, Spring Data JPA intercepts the method call and starts a new transaction. It manages the transaction by automatically committing the changes if the method execution is successful, or rolling back the changes if an exception occurs during the method execution.

9. **What is the purpose of the `CommandLineRunner` interface in Spring Boot?**
   <br/>The `CommandLineRunner` interface in Spring Boot is used to run the code block or perform specific actions when the application is started. It allows you to execute custom logic or initialize data before the application is fully up and running.

10. **How is dependency injection achieved in the `TestDataRunner` class using the `@Autowired` annotation?**
    <br/>Dependency injection in the `TestDataRunner` class is achieved using the `@Autowired` annotation. By annotating the `ProductRepository` field with `@Autowired`, Spring Boot automatically injects an instance of the `ProductRepository` into the `TestDataRunner` class, allowing it to access and use the repository methods.

11. **Explain the role of the `ProductRepository` interface and how it interacts with the database.**
    <br/>The `ProductRepository` interface acts as a bridge between the application and the database. It extends the `JpaRepository` interface, which provides CRUD (Create, Read, Update, Delete) operations and other database-related methods. The `ProductRepository` interface defines additional custom methods using the `@Query` annotation to perform specific database operations like updating the product name or deleting a product by its ID.

12. **How is the `Product` entity mapped to the database table using annotations?**
    <br/>The `Product` entity is mapped to the database table using annotations. The `@Entity` annotation is used to mark the `Product` class as an entity, and the `@Table` annotation specifies the name of the corresponding database table. The `@Column` annotation is used to map each field to the corresponding column in the table.

13. **What is the purpose of the `@Id` and `@Column` annotations in the `Product` entity?**
    <br/>The `@Id` annotation is used to denote the primary key field of the `Product` entity, which is `prodId`. It signifies that this field uniquely identifies each record in the table. The `@Column` annotation is used to define the mapping between the `prodName` and `prodCost` fields of the `Product` entity and their respective columns in the database table. Additional attributes of `@Column` can be used to specify constraints like uniqueness, nullable, length, and more.

14. **How is the `ProductRepository` interface able to perform custom update and delete operations?**
    <br/>The `ProductRepository` interface can perform custom update and delete operations by defining the corresponding methods with the `@Modifying` and `@Query` annotations. The `@Modifying` annotation indicates that the method performs a non-select operation, and the `@Query` annotation is used to define the custom update or delete query. The method signature should match the query parameters and return type appropriately.

15. **What are the advantages of using Spring Data JPA over traditional JDBC code?**
    <br/>Some advantages of using Spring Data JPA over traditional JDBC code include:
    - Reduction of boilerplate code by providing ready-to-use CRUD operations and query methods.
    - Simplified data access layer with automatic query generation based on method names or annotations.
    - Improved productivity and development speed by leveraging Spring Boot's auto-configuration and dependency injection features.
    - Built-in transaction management, allowing declarative transaction demarcation with `@Transactional` annotation.
    - Integration with Hibernate ORM for powerful and flexible object-relational mapping capabilities.
    - Enhanced support for database abstraction and easier switch between different database vendors.