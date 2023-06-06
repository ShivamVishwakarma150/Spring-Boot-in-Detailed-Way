# Here's a detailed explanation of the points you mentioned about `Optional<T>` in JDK 1.8:

1. `Optional<T>` is used to avoid null problems, specifically NullPointerExceptions.
   - In traditional programming, if you access a variable or invoke a method on a null reference, it results in a NullPointerException.
   - `Optional<T>` provides a solution to this problem by wrapping the actual data and allowing you to handle the absence of data more gracefully.

2. `Optional<T>` acts as a wrapper object or a container object for the actual data of type `T`.
   - It allows you to explicitly express that a value may or may not be present.
   - This helps in writing more expressive and safer code, as it forces you to handle the absence of data explicitly.

3. `Optional<T>` can return actual data if it is present, or it can return empty data.
   - The `Optional<T>` class provides methods to access the value if it is present, such as `get()` and `orElse()`.
   - If the `Optional<T>` contains a value, the `get()` method returns the actual value. However, you should use it with caution to avoid potential NoSuchElementExceptions.
   - The `orElse()` method allows you to provide a default value to be returned if the `Optional<T>` is empty.

4. `Optional<T>` provides methods to check if a value is present or not.
   - The `isPresent()` method returns `true` if the `Optional<T>` contains a value, and `false` if it is empty.
   - This allows you to conditionally perform operations based on the presence or absence of data.

5. `Optional<T>` promotes better coding practices and makes the code more readable and maintainable.
   - By using `Optional<T>`, you make it clear in the code that a value may or may not be present, which improves code readability and avoids unexpected null pointer exceptions.
   - It encourages you to handle the absence of data explicitly, promoting better error handling and reducing potential bugs.

6. `Optional<T>` encourages the use of functional programming constructs.
   - `Optional<T>` provides various methods like `map()`, `filter()`, and `flatMap()`, which allow you to perform operations on the optional value in a functional way.
   - These methods enable you to chain multiple operations together and apply transformations to the value while keeping the code concise and expressive.

In conclusion, `Optional<T>` in JDK 1.8 is a wrapper object that helps you handle the absence of data and avoid NullPointerExceptions. It provides a safer and more expressive way to represent values that may or may not be present. By using `Optional<T>`, you can write more readable and maintainable code while promoting better error handling and encouraging functional programming principles.

<br/>

Sure! Here's the code snippet and a detailed explanation of `Optional<T>` in JDK 1.8:

```java
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        String name = "John";
        Optional<String> optionalName = Optional.of(name);
        
        // Accessing the value if present
        if (optionalName.isPresent()) {
            String actualName = optionalName.get();
            System.out.println("Actual Name: " + actualName);
        } else {
            System.out.println("Name is not present.");
        }
        
        // Providing a default value using orElse
        String defaultName = optionalName.orElse("Default");
        System.out.println("Default Name: " + defaultName);
        
        // Creating an empty optional
        Optional<String> emptyOptional = Optional.empty();
        
        // Handling empty optional using orElseGet
        String value = emptyOptional.orElseGet(() -> {
            System.out.println("Getting default value...");
            return "Default";
        });
        System.out.println("Value: " + value);
    }
}
```

Explanation:
1. In the code above, we import the `Optional` class from the `java.util` package.

2. We create an `Optional<String>` object named `optionalName` by wrapping the actual name in `Optional.of(name)`.
   - `Optional.of()` is used when the value is known to be non-null. If the value passed to `Optional.of()` is `null`, it will throw a `NullPointerException`.

3. We check if the value is present using the `isPresent()` method.
   - If the value is present, we retrieve it using the `get()` method and assign it to `actualName`.
   - Otherwise, we print a message indicating that the name is not present.

4. We provide a default value using the `orElse()` method.
   - If the value is present, it returns the actual value.
   - If the value is not present, it returns the provided default value.

5. We create an empty `Optional<String>` named `emptyOptional` using the `Optional.empty()` method.
   - This represents a case where the value is not present.

6. We handle the empty optional using the `orElseGet()` method.
   - If the value is present, it returns the actual value.
   - If the value is not present, it executes the lambda expression passed to `orElseGet()` to provide a default value.

Conclusion:
`Optional<T>` in JDK 1.8 is a wrapper object or container object for the actual data. It helps avoid null problems, specifically NullPointerExceptions. By using `Optional<T>`, you can safely handle scenarios where a value may or may not be present. It promotes better coding practices by explicitly expressing the presence or absence of data and encourages the use of functional programming constructs. With `Optional<T>`, you can write more readable and maintainable code while ensuring null safety.


<br/>
<br/>
<br/>

# `findById(id)` :

```java
public Optional<T> findById(ID id)
```

This code represents the `findById` method, which is a part of the `CrudRepository` interface in Spring Data JPA. It is used to fetch a single row of data from the database based on the provided primary key (`id`).

The method takes in the primary key as a parameter (`id`) and returns an `Optional` object wrapping the result. The `Optional<T>` signifies that the method can either return the actual data if it is present or an empty result if it is not.

In the context of the provided SQL statement:

```sql
select * from product where id=10;
```

The `findById` method internally generates a query similar to the above SQL statement, replacing the `10` with the provided `id` value. It fetches the row from the `product` table where the `id` column matches the given `id` value.

The returned result is wrapped in an `Optional` object. If a matching row is found, the `Optional` object will contain the fetched data. If no matching row is found, the `Optional` object will be empty.

Using `Optional` in this context helps to handle the scenario where the requested row may or may not exist in the database. By returning an `Optional` instead of `null`, it avoids potential `NullPointerException` errors, as developers can safely check if the result is present or not before accessing the data.

In summary, the `findById` method allows you to retrieve a single row of data from the database based on the provided primary key. It returns an `Optional` object that contains the fetched data if it exists, or an empty result if it doesn't. This helps to avoid potential `NullPointerException` errors when handling the fetched data.


<br/>
<br/>
<br/>

# Here's the explanation for the mentioned lines:

1. `<X extends Throwable>` and `Supplier<? extends X>`:
   - In the context of exception handling, `<X extends Throwable>` is a type parameter declaration, specifying that `X` can be any subclass of `Throwable` (direct or indirect).
   - `Supplier<? extends X>` is a functional interface representing a supplier of results, where the result is a subclass of `X`.
   - The combination of these two declarations allows for a flexible exception handling mechanism. It means that the method can throw any exception (`X` or its subclass) that is specified during its invocation, and the supplier can provide an exception object of that type.

2. `public static <T, K, U> Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper)`:
   - This is a method declaration in the `Collector` class, introduced in JDK 1.5 as part of the Java Generics feature.
   - It defines a static method named `toMap` that takes two function parameters: `keyMapper` and `valueMapper`.
   - `<T, K, U>` are type parameters for the method, representing the types of the elements being collected (`T`), the keys (`K`), and the values (`U`) in the resulting map.
   - `Function<? super T, ? extends K> keyMapper` represents a function that maps an element of type `T` (or its super type) to a key of type `K` (or its sub type). It uses wildcard characters (`?`) to allow flexibility in the types of the elements and keys.
   - `Function<? super T, ? extends U> valueMapper` represents a similar mapping function for values.
   - The method returns a `Collector` object that collects the elements into a `Map` using the provided key and value mappers.

In summary, the first line with `<X extends Throwable>` and `Supplier<? extends X>` allows for flexible exception handling, while the second line with `<T, K, U> Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper)` defines a generic method `toMap` that collects elements into a map using key and value mappers.

These features of JDK 1.5 and later, such as generics and wildcard types, enhance the flexibility, type safety, and reusability of code, enabling developers to write more generic and expressive methods and classes.

<br/>
<br/>
<br/>



1. ```java
   public Iterable<T> findAllById(Iterable<ID> ids)
   ```

   The `findAllById` method is used to fetch multiple rows of data from the database based on a collection of provided IDs. It takes an iterable of IDs (`idsAsList`) as a parameter and returns an `Iterable` object containing the fetched data.

   In the example SQL statement:

   ```sql
   select * from product_tab where pid in (11,23,45,67,89);
   ```

   The `findAllById` method internally generates a query similar to the above SQL statement, using the `in` operator to match the provided IDs. It fetches all the rows from the `product_tab` table where the `pid` column matches any of the given IDs.

   The returned result is wrapped in an `Iterable` object, which allows you to iterate over the fetched data.

   This method is useful when you want to retrieve multiple rows of data based on a list of IDs. It simplifies the query construction by using the `in` operator, and the returned `Iterable` provides easy access to the fetched data.

2. ```java
   public void deleteById(ID id)
   ```

   The `deleteById` method is used to delete a single row of data from the database based on the provided ID. It internally calls the `findById` method to check if the object with the given ID exists. If the object is present, it proceeds to delete it using the `delete` method. If the object is not found, it throws an exception (such as `EmptyResultDataAccessException`).

3. ```java
   void delete(T entity)
   ```

   The `delete` method is a standard operation in JPA for deleting an object. It takes an object (`entity`) as input and attempts to delete it from the database.

4.  ```java
    void deleteAllById(Iterable<? extends ID> ids)
    ```

    The `deleteAllById` method calls the `deleteById` method in a loop for every ID provided in the iterable (`idsAsList`). It deletes rows from the database based on each ID in the loop.

5.  ```java
    void deleteAll()
    ```

    The `deleteAll` method fetches all rows of data from the database using the `findAll` method and, for each object, calls the `delete` method to delete it.

6.  ```java
    void deleteAll(Iterable<? extends T> entities)
    ```

    The `deleteAll` method takes a list of objects (`listOfObjects`) as input and, using a for-each loop, calls the `delete` method to delete each object from the database.

In summary, the methods `findAllById`, `deleteById`, `delete`, `deleteAllById`, `deleteAll`, and `deleteAll` are operations provided by the `CrudRepository` interface in Spring Data JPA.

- `findAllById` retrieves multiple rows of data based on a collection of IDs.
- `deleteById` deletes a single row of data based on the provided ID.
- `delete` deletes a given object from the database.
- `deleteAllById` deletes multiple rows of data based on a collection of IDs.
- `deleteAll` deletes all rows of data from the database, either by fetching and deleting objects one by one or by directly deleting the rows based on the provided objects.

These methods provide convenient ways to interact with the database and perform common CRUD (Create, Read, Update, Delete) operations using Spring Data JPA.

<br/>
<br/>
<br/>

Sure! Here's the breakdown of the code and its explanations:

1. Entity class (Product):
```java
package com.app.Shivam.entity;

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
@Table(name="product_tab")
public class Product {
	
	@Id
	@Column(name="pid")
	private Integer prodId;
	
	@Column(name="pname")
	private String prodName;
	
	@Column(name="pcost")
	private Double prodCost;
}
```
The `Product` class represents an entity mapped to the database table `product_tab`. It includes the necessary annotations (`@Entity`, `@Table`, `@Id`, and `@Column`) for JPA mapping. The Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) generate getters, setters, constructors, and other boilerplate code.

2. Repository interface (ProductRepository):
```java
package com.app.Shivam.repo;

import org.springframework.data.repository.CrudRepository;

import com.app.Shivam.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
```
The `ProductRepository` interface extends the `CrudRepository` interface provided by Spring Data JPA. It specifies the entity type (`Product`) and the primary key type (`Integer`). This interface provides pre-defined CRUD operations and other common database operations.

3. Runner class (TestOperationsRunner):
```java
package com.app.Shivam.runner;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.Shivam.entity.Product;
import com.app.Shivam.exception.ProductNotFoundException;
import com.app.Shivam.repo.ProductRepository;

@Component
public class TestOperationsRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;

	public void run(String... args) throws Exception {
		// Save some products
		Product p1 = new Product(10, "P2", 300.0);
		Product p2 = new Product(11, "P3", 400.0);
		Product p3 = new Product(12, "P4", 500.0);
		repo.saveAll(Arrays.asList(p1, p2, p3));

		// Fetch and print all products using different methods
		Iterable<Product> data = repo.findAll();
		for (Product pob : data) {
			System.out.println(pob);
		}
		System.out.println("--------------");
		data.forEach(ob -> System.out.println(ob));
		System.out.println("*********");
		data.forEach(System.out::println);

		// Check if a product with ID 11 exists
		System.out.println(repo.existsById(11)); // true
		System.out.println(repo.existsById(55)); // false

		// Count the number of products
		System.out.println(repo.count());

		// Fetch a product by ID
		Optional<Product> opt = repo.findById(11);
		if (opt.isPresent()) {
			Product p = opt.get();
			System.out.println(p);
		} else {
			System.out.println("DATA NOT FOUND");
		}

		// Fetch a product by ID and handle exception if not found
		Product p = repo.findById(11).orElseThrow(() -> new ProductNotFoundException("NOT EXIST"));
		System.out.println(p);

		// Fetch multiple products by IDs
		Iterable<Product> list = repo.findAllById(Arrays.asList(11, 22, 45, 56, 85, 65));
		list.forEach(System.out::println

);

		// Delete a product by ID
		repo.delete(
			repo.findById(77).orElseThrow(() -> new ProductNotFoundException(
				String.format(" -- %s NOT HAVING %d --", Product.class.getName(), 77)
			))
		);

		// Delete multiple products by IDs
		repo.deleteAllById(Arrays.asList(10, 11));

		// Delete all products
		repo.deleteAll();
	}
}
```
The `TestOperationsRunner` class is a Spring `CommandLineRunner` component responsible for executing code when the application starts. It demonstrates various operations using the `ProductRepository`. It saves some products, performs fetch operations, checks for product existence, counts the number of products, deletes products, and handles exceptions.

4. Custom Exception (ProductNotFoundException):
```java
package com.app.Shivam.exception;

public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -7326311665490135408L;

	public ProductNotFoundException() {
		super();
	}
	
	public ProductNotFoundException(String message) {
		super(message);
	}
}
```
The `ProductNotFoundException` class extends the `RuntimeException` class and represents a custom exception for product not found scenarios. It provides constructors for creating the exception object with or without a custom error message.

5. application.yml:
```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/boot7am
    username: root
    password: root

  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
```
The `application.yml` file contains the configuration properties for the Spring Data JPA and database connection. It specifies the database URL, username, password, and additional JPA settings.

In conclusion, the provided code showcases a simple Spring Boot application that utilizes Spring Data JPA to perform CRUD operations on the `Product` entity. It demonstrates the usage of entity mapping, repository interfaces, custom exceptions, and a runner class to execute database operations and handle exceptions.


<br/>
<br/>
<br/>

#  Here's the code for the Runner class (TestOperationsRunner) along with a detailed explanation:

```java
package com.app.Shivam.runner;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.Shivam.entity.Product;
import com.app.Shivam.exception.ProductNotFoundException;
import com.app.Shivam.repo.ProductRepository;

@Component
public class TestOperationsRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;

	public void run(String... args) throws Exception {
		// Save some products
		Product p1 = new Product(10, "P2", 300.0);
		Product p2 = new Product(11, "P3", 400.0);
		Product p3 = new Product(12, "P4", 500.0);
		repo.saveAll(Arrays.asList(p1, p2, p3));

		// Fetch and print all products using different methods
		Iterable<Product> data = repo.findAll();
		for (Product pob : data) {
			System.out.println(pob);
		}
		System.out.println("--------------");
		data.forEach(ob -> System.out.println(ob));
		System.out.println("*********");
		data.forEach(System.out::println);

		// Check if a product with ID 11 exists
		System.out.println(repo.existsById(11)); // true
		System.out.println(repo.existsById(55)); // false

		// Count the number of products
		System.out.println(repo.count());

		// Fetch a product by ID
		Optional<Product> opt = repo.findById(11);
		if (opt.isPresent()) {
			Product p = opt.get();
			System.out.println(p);
		} else {
			System.out.println("DATA NOT FOUND");
		}

		// Fetch a product by ID and handle exception if not found
		Product p = repo.findById(11).orElseThrow(() -> new ProductNotFoundException("NOT EXIST"));
		System.out.println(p);

		// Fetch multiple products by IDs
		Iterable<Product> list = repo.findAllById(Arrays.asList(11, 22, 45, 56, 85, 65));
		list.forEach(System.out::println);

		// Delete a product by ID
		repo.delete(
			repo.findById(77).orElseThrow(() -> new ProductNotFoundException(
				String.format(" -- %s NOT HAVING %d --", Product.class.getName(), 77)
			))
		);

		// Delete multiple products by IDs
		repo.deleteAllById(Arrays.asList(10, 11));

		// Delete all products
		repo.deleteAll();
	}
}
```

Explanation:

1. The `TestOperationsRunner` class implements the `CommandLineRunner` interface, which allows the application to execute specific code when it starts.

2. The class is annotated with `@Component`, indicating that it is a Spring component and will be automatically detected and instantiated by the Spring container.

3. The `ProductRepository` is autowired into the class using the `@Autowired` annotation, enabling access to the database operations defined in the repository.

4. The `run` method is overridden from the `CommandLineRunner` interface and contains the logic to be executed when the application starts.

5. Three instances of the `Product` class are created with different values and saved into the database using the `repo.saveAll` method.

6. The `findAll` method is used to fetch all products from the database, and they are printed using a traditional for loop and a lambda expression.

7. The `existsById` method is used to check if a product with a specific ID exists in the database. It prints `true` if the product exists and `false` otherwise.

8. The `count` method is used to get the total number of products in the database.

9. The `findById` method is used to fetch a product by its ID. If the product is present, it is printed; otherwise, the message "DATA NOT FOUND" is displayed.

10. The `orElseThrow` method is used to handle the case where a product with ID 11 is not found. It throws a custom `ProductNotFoundException` with a specified error message.

11. The `findAllById` method is used to fetch multiple products based on a list of IDs. The retrieved products are printed using the `forEach` method.

12. The `delete` method is used to delete a product from the database. It first finds the product by ID (e.g., ID 77) and then deletes it. If the product is not found, a `ProductNotFoundException` is thrown.

13. The `deleteAllById` method is used to delete multiple products by their IDs, passed as a list.

14. The `deleteAll` method is used to delete all products from the database.

In conclusion, the `TestOperationsRunner` class demonstrates various database operations using the `ProductRepository` interface. It performs CRUD (Create, Read, Update, Delete) operations, handles exceptions, and showcases the usage of different methods provided by the Spring Data JPA framework.



<br/>
<br/>
<br/>


# Here are some frequently asked questions (FAQs) related to the provided code:

Q1: What is the purpose of the `@Entity` annotation in the `Product` class?<br/>
A1: The `@Entity` annotation is used to mark the `Product` class as an entity in JPA. It indicates that instances of this class will be persisted into the database.

Q2: How are the primary keys defined in the `Product` class?<br/>
A2: The primary key is defined using the `@Id` annotation on the `prodId` field. It specifies that the `prodId` field is the primary key for the `Product` entity.

Q3: What is the purpose of the `@Table` annotation in the `Product` class?<br/>
A3: The `@Table` annotation is used to specify the name of the table associated with the `Product` entity. In this case, it sets the table name to "product_tab".

Q4: What is the purpose of the `ProductRepository` interface?<br/>
A4: The `ProductRepository` interface extends the `CrudRepository` interface, which provides standard CRUD (Create, Read, Update, Delete) operations for the `Product` entity. It allows you to perform database operations on `Product` objects, such as saving, deleting, and finding.

Q5: How are the `Product` objects saved into the database in the `TestOperationsRunner` class?<br/>
A5: The `saveAll` method is used to save multiple `Product` objects into the database. In the provided code, three `Product` objects (`p1`, `p2`, `p3`) are created and saved using the `repo.saveAll(Arrays.asList(p1, p2, p3))` statement.

Q6: How can you retrieve all `Product` objects from the database?<br/>
A6: The `findAll` method in the `ProductRepository` interface is used to retrieve all `Product` objects from the database. In the `TestOperationsRunner` class, the `repo.findAll()` method is called, and the returned `Iterable` is then iterated over to print each `Product` object.

Q7: How can you find a `Product` object by its ID?<br/>
A7: The `findById(id)` method in the `ProductRepository` interface is used to find a `Product` object by its ID. In the `TestOperationsRunner` class, the `repo.findById(11)` method is called, and an `Optional` object is returned. The `Optional` can be used to check if the `Product` object is present and retrieve its value using the `get()` method.

Q8: How can you delete a `Product` object from the database?<br/>
A8: The `delete` method in the `ProductRepository` interface is used to delete a `Product` object from the database. In the provided code, a `Product` object is deleted by calling the `repo.delete` method and passing the object as an argument. Alternatively, you can use the `deleteById` method to delete a `Product` object by its ID.

Q9: What happens if the `findById` method doesn't find a `Product` object with the specified ID?<br/>
A9: If the `findById` method doesn't find a `Product` object with the specified ID, it returns an empty `Optional`. In the `TestOperationsRunner` class, the code uses the `orElseThrow` method to throw a `ProductNotFoundException` if the `Optional` is empty.

Q10: How can you perform custom database operations in Spring Boot Data JPA?<br/>
A10: Spring Boot Data JPA provides various methods in the repository interfaces for common database operations. However, you can also define custom query methods using method naming conventions or using `@Query` annotations to write JPQL or native SQL queries.





<br/>
<br/>
<br/>



# Here are some possible interview questions related to Spring Boot Data JPA, along with their answers:

1. What is the purpose of the `Optional<T>` class in JDK 1.8 used in Spring Boot Data JPA?<br/>
   Answer: The `Optional<T>` class is used to avoid null problems, specifically NullPointerExceptions. It acts as a wrapper object or container for actual data and provides a way to handle scenarios where the data may be absent. It returns the actual data if present (using the `isPresent()` method) or an empty data object if the data is not available.

2. Explain the usage of the `findById(id)` method in Spring Boot Data JPA.<br/>
   Answer: The `findById(id)` method is used to fetch one row of data from the database based on the primary key (ID). It performs an SQL query similar to `select * from product where id=10;` to retrieve the data. It returns an `Optional<T>` object containing the fetched data if it exists, or an empty `Optional<T>` object if the data is not found.

3. What does `<X extends Throwable>` mean in the method signature `orElseThrow(Supplier<? extends X>)`?<br/>
   Answer: In this method signature, `<X extends Throwable>` indicates that `X` can be any subclass of the `Throwable` class, which includes both direct and indirect subclasses. The `?` represents a wildcard character and can be any subtype of `X` or even the same type as `X`. It allows the method to throw a custom exception (`X`) or its subclass.

4. Explain the purpose of the `findAllById(idsAsList)` method in Spring Boot Data JPA.<br/>
   Answer: The `findAllById(idsAsList)` method is used to fetch data from the database using the `in` operator in an SQL query. For example, it can execute an SQL statement like `select * from product_tab where pid in (11,23,45,67,89);` to retrieve the data for the given list of IDs. It returns an `Iterable<T>` object containing the fetched data.

5. What happens when the `deleteById(ID id)` method is called in Spring Boot Data JPA?<br/>
   Answer: The `deleteById(ID id)` method is used to delete one row of data from the database based on the given ID. Internally, it first checks if the object exists using the `findById(id)` method. If the object is present, it calls the `delete(obj)` method to delete that specific object. If the object is not found, it throws an exception like `EmptyResultDataAccessException`.

6. Explain the difference between the `delete(T entity)` and `deleteAll(listOfObjects)` methods in Spring Boot Data JPA.<br/>
   Answer: The `delete(T entity)` method takes an object as input and attempts to delete that specific object from the database. On the other hand, the `deleteAll(listOfObjects)` method iterates over a list of objects and calls `delete(obj)` in a loop, deleting each object one by one.

1. What is the purpose of the `save` and `saveAll` methods in Spring Boot Data JPA?<br/>
   Answer: The `save` method is used to save a single object into the database, while the `saveAll` method is used to save a collection of objects. These methods persist the objects into the database or update them if they already exist.

2. Explain the usage of the `existsById(id)` method in Spring Boot Data JPA.<br/>
   Answer: The `existsById(id)` method checks whether an object with the given ID exists in the database or not. It returns `true` if an object with the specified ID is found, and `false` otherwise.

3. What is the purpose of the `count` method in Spring Boot Data JPA?<br/>
   Answer: The `count` method returns the total number of rows or entities present in the specified table or repository. It can be used to get the count of records based on certain conditions or to check the overall size of the table.

4. What is the significance of the `@Id` annotation in an entity class in Spring Boot Data JPA?<br/>
   Answer: The `@Id` annotation is used to mark a field as the primary key in an entity class. It identifies the unique identifier for each entity object and is essential for performing operations such as fetching, updating, or deleting specific records from the database.

5. Explain the purpose of the `@Entity` annotation in Spring Boot Data JPA.<br/>
   Answer: The `@Entity` annotation is used to mark a class as an entity. It signifies that instances of this class will be persisted into the database. Each entity class maps to a database table, and the fields within the class represent the table columns.

6. What is the role of the `@Table` annotation in Spring Boot Data JPA?<br/>
   Answer: The `@Table` annotation is used to specify the name of the table associated with an entity class. It allows you to override the default table name derived from the class name. By using this annotation, you can define a custom table name or map the entity to an existing table in the database.

