# CrudRepository methods

**1. save(obj): obj**
This method is used to save an object to the database table. It checks if the given object is already present in the table based on its primary key (PK) value. It performs the following steps:

1. Performs a SELECT query on the table using the PK value of the object:
   ```sql
   SELECT * FROM TABLE WHERE PK_COL = VAL;
   ```

2. If a record is found with the given PK value, it updates the corresponding row in the table with the object's data.

3. If no record is found with the given PK value, it inserts a new row into the table with the object's data.

4. If the object data is already present in the database, the `save()` method does nothing and doesn't perform an update or insert operation.

**2. saveAll(Iterable):**
This method allows you to save multiple objects at once. It takes a collection of objects (Iterable) as input and performs the same operations as the `save()` method for each object in the collection.

**3. findAll(): Iterable<T>**
The `findAll()` method is used to retrieve all rows from the database table associated with the repository. It returns an Iterable object that allows you to iterate over the fetched rows. It performs the following step:

1. Executes the following SQL query to retrieve all rows from the table:
   ```sql
   SELECT * FROM tablename;
   ```

**4. existsById(id): boolean**
The `existsById(id)` method checks if a row with the given ID (primary key) exists in the database table. It returns a boolean value indicating whether the row exists or not. It performs the following step:

1. Executes the following SQL query to check if the row with the given ID exists:
   ```sql
   SELECT * FROM TABLE WHERE PK_COL = id;
   ```

2. If a row is found with the given ID, it returns `true`; otherwise, it returns `false`.

**5. count(): long**
The `count()` method returns the total number of rows present in the database table associated with the repository. It performs the following step:

1. Executes the following SQL query to count the number of rows in the table:
   ```sql
   SELECT COUNT(*) FROM tablename;
   ```

2. Returns the count as a `long` value.

These methods are part of the `CrudRepository` interface in Spring Data JPA, which provides convenient methods for performing CRUD (Create, Read, Update, Delete) operations on entities. The implementation of these methods may vary depending on the underlying database and ORM (Object-Relational Mapping) framework used.

<br/>
<br/>
<br/>

# Here are some frequently asked questions (FAQs) related to the `CrudRepository` methods and their answers:

**Q1: What is the difference between `save(obj)` and `saveAll(Iterable)` methods in `CrudRepository`?**
- `save(obj)` method is used to save a single object to the database table. It checks if the object is already present in the table based on its primary key and performs an update or insert operation accordingly.
- `saveAll(Iterable)` method is used to save multiple objects at once. It takes a collection of objects as input and performs the same operations as `save(obj)` method for each object in the collection.

**Q2: How does the `findAll()` method work in `CrudRepository`?**
- The `findAll()` method retrieves all rows from the database table associated with the repository and returns them as an Iterable object. It executes a SELECT query without any conditions, fetching all rows from the table.

**Q3: What does the `existsById(id)` method do in `CrudRepository`?**
- The `existsById(id)` method checks if a row with the given ID (primary key) exists in the database table associated with the repository. It returns a boolean value (`true` if the row exists, `false` otherwise).

**Q4: How does the `count()` method work in `CrudRepository`?**
- The `count()` method returns the total number of rows present in the database table associated with the repository. It executes a SELECT COUNT(*) query, which counts the number of rows in the table, and returns the count as a `long` value.

**Q5: Does the `save(obj)` method update the existing object in the database if it already exists?**
- Yes, the `save(obj)` method performs an update operation if the object with the same primary key value already exists in the database. It updates the corresponding row with the object's data.

**Q6: Can I use the `saveAll(Iterable)` method to save a mix of new and existing objects to the database?**
- Yes, you can use the `saveAll(Iterable)` method to save a collection of objects to the database, including both new objects and existing ones. It will perform updates for the existing objects and inserts for the new objects.

**Q7: What happens if I call the `save(obj)` method with an object that is already present in the database?**
- If the object data is already present in the database, the `save(obj)` method does nothing and doesn't perform an update or insert operation. It checks the existence of the object based on its primary key value.

These FAQs should provide a better understanding of the `CrudRepository` methods and their usage in Spring Data JPA. Remember that the behavior may vary depending on the specific implementation and the underlying database system.

<br/>
<br/>
<br/>

Sure! Here's the code provided with separate explanations for each class:

**1. Application (Main Class):**

```java
package com.app.Shivam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }
}
```

**Explanation:**
- This class serves as the entry point for your Spring Boot application.
- The `@SpringBootApplication` annotation is a meta-annotation that includes the following annotations: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
- It enables auto-configuration and component scanning in your application.

**2. application.yml (Configuration File):**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/boot
    username: root
    password: shivam@123
    
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create
```

**Explanation:**
- This YAML configuration file contains properties related to your application's data source and JPA configuration.
- The `spring.datasource` section specifies the database URL, username, and password for connecting to MySQL.
- The `spring.jpa` section configures JPA-related properties, such as enabling SQL logging (`show-sql`) and specifying the Hibernate DDL behavior (`ddl-auto`).

**3. Entity Class:**

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
@Table(name = "product_tab")
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

**Explanation:**
- This class represents an entity in your application, mapped to the `product_tab` table in the database.
- It uses Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) to generate getters, setters, constructors, and other common methods.
- The `@Entity` annotation marks the class as a persistent entity.
- The `@Table` annotation specifies the name of the database table associated with the entity.
- The `@Id` annotation designates the primary key field of the entity.
- The `@Column` annotation is used to map entity fields to table columns.

**4. Repository Interface:**

```java
package com.app.Shivam.repo;

import org.springframework.data.repository.CrudRepository;

import com.app.Shivam.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
```

**Explanation:**
- This interface defines a repository for performing CRUD (Create, Read, Update, Delete) operations on `Product` entities.
- It extends the `CrudRepository` interface, which provides basic CRUD methods such as `save()`, `findAll()`, `existsById()`, etc.
- The `ProductRepository` interface specifies the entity type (`Product`) and the type of the primary key (`Integer`).

**5. Runner Class:**

```java
package com.app.Shivam.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.Shivam.entity.Product;
import com.app.Shivam.repo.ProductRepository;

@Component
public class TestOperationsRunner implements CommandLineRunner {

    @Autowired
    private ProductRepository repo;

    public void run

(String... args) throws Exception {
        Product p1 = new Product(10, "P2", 300.0);
        Product p2 = new Product(11, "P3", 400.0);
        Product p3 = new Product(12, "P4", 500.0);

        repo.saveAll(Arrays.asList(p1, p2, p3));

        Iterable<Product> data = repo.findAll();

        // JDK 1.5 forEach Loop
        for (Product product : data) {
            System.out.println(product);
        }
        System.out.println("--------------");

        // JDK 1.8 Default method + Lambda Expression
        data.forEach(ob -> System.out.println(ob));

        System.out.println("*********");

        // JDK 1.8 Default method + Method References
        data.forEach(System.out::println);

        System.out.println(repo.existsById(11)); // true
        System.out.println(repo.existsById(55)); // false

        System.out.println(repo.count()); // long -- no.of rows
    }
}
```

**Explanation:**
- This class implements the `CommandLineRunner` interface, allowing it to run specific code when the application starts.
- It demonstrates various operations using the `ProductRepository`.
- In the `run()` method, it creates three `Product` objects and saves them using the `saveAll()` method of the repository.
- It then fetches all products using the `findAll()` method and prints them using different iteration approaches.
- The code also demonstrates the usage of `existsById()` to check if a product with a specific ID exists, and `count()` to get the total number of products.

**Conclusion:**
This code showcases a basic setup of a Spring Boot application with Spring Data JPA. It includes the main application class, configuration file, entity class, repository interface, and a runner class to perform CRUD operations using the `CrudRepository` methods. The runner class demonstrates the usage of these methods with a sample `Product` entity.

<br/>
<br/>
<br/>

# Here are some frequently asked questions related to the code provided and their corresponding answers:

**FAQ 1: What is the purpose of the `SpringDataJpaApplication` class?**
- The `SpringDataJpaApplication` class serves as the entry point for the Spring Boot application.
- It enables auto-configuration and component scanning in the application.
- It starts the Spring Boot application by calling the `SpringApplication.run()` method.

**FAQ 2: What is the purpose of the `application.yml` configuration file?**
- The `application.yml` file contains application-specific configurations, such as database connection details and JPA settings.
- In this code, it specifies the MySQL database URL, username, and password for connecting to the database.
- It also enables SQL logging (`show-sql: true`) and sets the Hibernate DDL auto-creation behavior (`ddl-auto: create`).

**FAQ 3: What is the `Product` class used for?**
- The `Product` class is an entity class representing a product in the application.
- It is mapped to the `product_tab` table in the database.
- The class uses Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) to generate getters, setters, constructors, and other common methods automatically.

**FAQ 4: What is the purpose of the `ProductRepository` interface?**
- The `ProductRepository` interface defines a repository for performing CRUD operations on `Product` entities.
- It extends the `CrudRepository` interface, which provides basic CRUD methods such as `save()`, `findAll()`, `existsById()`, etc.
- The `ProductRepository` interface specifies the entity type (`Product`) and the type of the primary key (`Integer`).

**FAQ 5: What does the `TestOperationsRunner` class do?**
- The `TestOperationsRunner` class implements the `CommandLineRunner` interface, allowing it to run specific code when the application starts.
- In the `run()` method, it demonstrates various operations using the `ProductRepository`.
- It creates three `Product` objects and saves them using the `saveAll()` method of the repository.
- It fetches all products using the `findAll()` method and prints them using different iteration approaches.
- The code also demonstrates the usage of `existsById()` to check if a product with a specific ID exists and `count()` to get the total number of products.

**FAQ 6: How does the application connect to the database and perform database operations?**
- The application uses Spring Data JPA, which provides abstractions for interacting with the database.
- The `ProductRepository` interface extends the `CrudRepository` interface, which includes methods for CRUD operations.
- Spring Boot automatically generates the implementation of the repository interface at runtime based on the configuration and entity classes.
- The repository implementation utilizes the underlying database connection and ORM (Object-Relational Mapping) capabilities provided by Spring Data JPA to execute the operations.

**FAQ 7: What is the purpose of the `@Component` and `@Autowired` annotations in the `TestOperationsRunner` class?**
- The `@Component` annotation marks the `TestOperationsRunner` class as a Spring-managed component.
- It allows the class to be automatically detected and registered as a bean in the Spring application context.
- The `@Autowired` annotation is used to inject an instance of the `ProductRepository` into the `TestOperationsRunner` class.
- It enables dependency injection, allowing the repository to be used within the class without explicitly instantiating it.

**FAQ 8: How does the code demonstrate different iteration approaches when printing the products?**
- The code uses different iteration approaches to print the products fetched from the database using the `findAll()` method.
- It demonstrates the JDK 1.5 forEach Loop, JDK 1.8 Default method with Lambda Expression, and JDK 1.8 Default method with Method References.
- Each approach achieves the same result of printing the products but with different syntax and techniques.

**FAQ 9: What are the expected outputs of the code?**
- The code saves three `Product` objects to the database using the `saveAll()` method.
- After that, it fetches all products using the `findAll()` method and prints them using different iteration approaches.
- The expected output is the details of the saved products printed in the console, followed by the results of the `existsById()` and `count()` operations.

**FAQ 10: How can I modify this code to work with a different database or table?**
- To work with a different database, you need to update the database URL, username, and password in the `application.yml` configuration file.
- If you want to work with a different table, you can modify the `@Table` annotation in the `Product` entity class to specify the desired table name.

Please note that these FAQs and answers are based on the provided code, and additional questions may arise depending on the context and interviewer.