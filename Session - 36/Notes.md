# `Spring Data JPA: Collections (with Primitives)`

## The statement you provided is a simplified explanation of Object-Relational Mapping (ORM) concepts in the context of mapping Java classes to database tables. Let's break down each component:

1. 1 class = 1 table:
   In ORM, each Java class typically corresponds to a database table. The attributes (variables) of the class are mapped to columns in the table, and instances of the class represent rows in the table.

2. 1 Primitive variable = 1 column:
   For primitive variables (e.g., `int`, `boolean`, `double`, etc.) in a Java class, each variable is mapped to a separate column in the corresponding database table. The value of the variable is stored in that column for each row.

3. 1 collection variable = 1 child table (2/3 columns):
   When a Java class contains a collection variable (e.g., `List`, `Set`, `Map`, etc.), the elements of the collection are typically stored in a separate child table. This child table has a foreign key column that links it to the primary key of the parent table (the table corresponding to the parent class). In addition, there may be additional columns in the child table to represent the index or position of the elements in the collection.

   For example, if you have a parent class `Parent` with a collection variable of type `List` or `Set`, each element of the collection would be stored in a separate row in the child table. The child table would have a foreign key column to reference the primary key of the parent table, and it may also have additional columns to represent the index or other data associated with each element.

This simplified explanation outlines the basic principles of how classes, primitive variables, and collection variables are mapped to database tables in ORM. It's important to note that in practice, there can be more complex mappings and considerations depending on the ORM framework and specific use cases.

<br/>
<br/>
<br/>

# Here is a detailed explanation of the different types of collection mappings in Spring Data JPA:

1. Index Based Collection:
   - In index-based collections, such as `List` and `Map`, the elements are stored in a child table with three columns.
   - The first column is the Key Column, which serves as a foreign key (FK) or join column linking the child table to the parent table.
   - The second column is the Index Column, which represents the index or position of each element in the collection. This allows for ordering and retrieval of elements based on their index.
   - The third column is the Element Column, which stores the actual data or value of each element in the collection.

   Example: If you have a parent entity `Parent` and it has a `List` of child entities `Child`, each child entity will be stored in a separate row in the child table. The Key Column will link each child entity to its parent entity, the Index Column will represent the index of each child entity in the `List`, and the Element Column will store the data of each child entity.

2. Non-Index Based Collection:
   - In non-index based collections, such as `Set`, the elements are stored in a child table with two columns.
   - The first column is the Key Column, which serves as a foreign key (FK) or join column linking the child table to the parent table.
   - The second column is the Element Column, which stores the actual data or value of each element in the collection.
   
   Example: If you have a parent entity `Parent` and it has a `Set` of child entities `Child`, each child entity will be stored in a separate row in the child table. The Key Column will link each child entity to its parent entity, and the Element Column will store the data of each child entity.

These collection mappings allow you to represent relationships between entities where a parent entity has a collection of child entities. By using appropriate annotations and configurations in Spring Data JPA, you can seamlessly persist and retrieve these collections.

It's important to note that these mappings provide a way to represent collections with primitives, where each element in the collection corresponds to a column in the child table.

<br/>
<br/>
<br/>

# Notes

1. `Table name and column names can be anything`:
   When mapping Java classes to database tables, you have the flexibility to choose any valid names for the table and column names. These names can be descriptive and meaningful, following the naming conventions of your choice.

2. `Columns need not be in the same order always`:
   The order of columns in the database table doesn't necessarily need to match the order of attributes in the corresponding Java class. The mapping between columns and attributes is usually determined by annotations or configuration settings provided by the ORM framework.

3. `Every Child Table is created with FK (Foreign Key) Column connected with PK (Primary Key) column in the Parent table`:
   When a child table is created for a collection variable in the Java class, it typically includes a foreign key column. This foreign key column establishes a relationship with the primary key column in the parent table. It allows the child table to reference the corresponding parent table and maintain the association between them.

4. `PK (Primary Key) is unique and not null, while FK (Foreign Key) allows duplicates and null values`:
   Primary key columns in the parent table must have unique values and cannot be null. On the other hand, foreign key columns in the child table can have duplicate values and can also contain null values. The primary key and foreign key columns must have the same data type to establish a valid relationship.

5. `@ElementCollection annotation`:
   The `@ElementCollection` annotation is used to mark a collection variable in the Java class that contains primitive types. This annotation tells the ORM framework to create separate tables to store the elements of the collection.

6. `Optional annotations`:
   - `@CollectionTable`: This annotation is used to specify the name of the child table created for the collection variable. It allows you to customize the table name as desired.
   - `joinColumns = @JoinColumn`: This annotation specifies the foreign key column name in the child table that connects to the primary key column in the parent table.
   - `@OrderColumn`: This annotation is used for `List` collections to specify the name of the index column in the child table. The index column represents the position of each element in the `List`.
   - `@Column`: This annotation is used to specify the name of the element column in the child table. It represents the actual data or value of each element in the collection.
   - `@MapKeyColumn`: This annotation is used for `Map` collections to specify the name of the index column in the child table. The index column corresponds to the keys of the `Map`.

By using these optional annotations, you can further customize the mapping of the collection variables and their corresponding child tables in the database.

<br/>
<br/>
<br/>

# Here's the code for the `Product` entity class:

```java
package com.app.shivam.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="prodtab")
public class Product {
    @Id
    @Column(name="pid")
    private Integer prodId;
    
    @Column(name="pcode")
    private String prodCode;
    
    @ElementCollection
    @CollectionTable(
            name="product_colors",
            joinColumns = @JoinColumn(name="pidFk")
    )
    @OrderColumn(name="pos") //index
    @Column(name="data")
    private List<String> colors;
    
    @ElementCollection
    @CollectionTable(
            name="product_models",
            joinColumns = @JoinColumn(name="pidFk")
    )
    @Column(name="model")
    private Set<String> models;
    
    @ElementCollection //must 
    @CollectionTable(
            name="product_details",//table name
            joinColumns = @JoinColumn(name="pidFk")//Fk column
    )
    @MapKeyColumn(name="code") //index
    @Column(name="details") //element column
    private Map<String,String> details;
}
```

Explanation:

1. `@Data`: This annotation is provided by Lombok library and automatically generates getter and setter methods, `equals()`, `hashCode()`, and `toString()` methods for all fields in the class.

2. `@Entity`: This annotation is from the JPA (Java Persistence API) and marks the class as an entity, indicating that it is mapped to a database table.

3. `@Table(name="prodtab")`: This annotation specifies the name of the table in the database to which the entity is mapped. In this case, the table name is "prodtab".

4. `@Id`: This annotation is used to declare the field as the primary key of the entity.

5. `@Column(name="pid")`: This annotation is used to specify the mapping between the field and the corresponding column in the database table. In this case, the field "prodId" is mapped to the "pid" column.

6. `@ElementCollection`: This annotation indicates that the field represents a collection of simple values or embeddable objects.

7. `@CollectionTable(name="product_colors", joinColumns = @JoinColumn(name="pidFk"))`: This annotation is used to specify the name of the table that will be created to store the elements of the collection. In this case, the table name is "product_colors", and the join column "pidFk" is used to establish a relationship with the parent entity.

8. `@OrderColumn(name="pos")`: This annotation is used to specify the name of the column that will hold the index or position of the elements in the collection. It is applicable only for ordered collections like lists.

9. `@Column(name="data")`: This annotation is used to specify the mapping between the element value and the corresponding column in the collection table. In this case, the element value is mapped to the "data" column.

10. `@Column(name="model")`: This annotation is similar to the above `@Column` annotation, but it is used for the `models` field, which represents a set of strings.

11. `@MapKeyColumn(name="code")`: This annotation is used to specify the name of the column that will hold the keys of the map. In this case, the keys will be stored in the "code" column.

12. `@Column(name="details")`: This annotation is used to specify the mapping between the element values of the map and the corresponding column in the collection table. In this case, the element values are mapped to the "details" column.

By using these

 annotations, the `Product` class is mapped to the database tables `prodtab`, `product_colors`, `product_models`, and `product_details`, and the respective columns are defined to store the values of the fields `prodId`, `prodCode`, `colors`, `models`, and `details`. The relationships between the tables are established through the foreign key column `pidFk`.

Here's the code for the `ProductRepository` interface:

```java
package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
```

Explanation:
- The `ProductRepository` interface extends the `JpaRepository` interface, providing CRUD (Create, Read, Update, Delete) operations for the `Product` entity.
- Since it extends `JpaRepository<Product, Integer>`, it automatically provides methods such as `save()`, `findById()`, `findAll()`, and more.

Here's the code for the `ProductDataRunner` class:

```java


package com.app.shivam.runner;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Product;
import com.app.shivam.repo.ProductRepository;

@Component
public class ProductDataRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;

	public void run(String... args) throws Exception {
		Product pob = new Product();
		pob.setProdId(10);
		pob.setProdCode("PEN");

		pob.setColors(Arrays.asList("RE", "GR", "BL"));

		Set<String> set = new LinkedHashSet<>();
		set.add("M1");
		set.add("LA");
		set.add("SF");

		pob.setModels(set);

		Map<String, String> map = new LinkedHashMap<>();
		map.put("D1", "ABC");
		map.put("D2", "XYZ");

		pob.setDetails(map);

		repo.save(pob);
	}
}
```

Explanation:
- The `ProductDataRunner` class is a Spring `CommandLineRunner` component used for initializing and inserting data into the database.
- It autowires the `ProductRepository` to perform CRUD operations on the `Product` entity.
- In the `run()` method, a `Product` object (`pob`) is created and populated with sample data.
- The `colors` attribute is set with a `List` of colors.
- The `models` attribute is set with a `Set` of models.
- The `details` attribute is set with a `Map` of details.
- Finally, the `pob` object is saved to the database using the `repo.save(pob)` method.

In conclusion, the provided code demonstrates the mapping of a `Product` entity class with various collections (`colors`, `models`, and `details`) using Spring Data JPA and Hibernate. These collections are stored in separate child tables (`product_colors`, `product_models`, and `product_details`) with the necessary foreign key and index/column mappings.


<br/>
<br/>
<br/>

# Here's a detailed explanation for each point you mentioned:

1. Child Table Name:
The child table name is derived from the entity class name and the collection variable name. It follows the pattern "classname_variableName". For example, if the entity class is `Product` and the collection variable is `colors`, the child table name would be `product_colors`.

2. FK Column Name (Key Column):
The FK column name, also known as the Key Column, is created in the child table to establish a foreign key relationship with the parent table. The name of this column follows the pattern "className_PKColumnName". For example, if the entity class is `Product` and the primary key column name is `pid`, the FK column name would be `Product_pid`.

3. Element Column Name:
The Element Column represents the data column in the child table that stores the values of the collection elements. Its name is derived from the variable name of the collection. For example, if the collection variable is `colors`, the element column name would be `colors`.

4. Map Key Column (Index):
The Map Key Column represents the index column in the child table for a `Map` collection. It stores the keys of the map entries. The name of this column follows the pattern "VariableName_key". For example, if the collection variable is `details`, the map key column name would be `details_key`.

5. List Key Column (Index):
In the case of a `List` collection, no separate column is created for the index. The index is implicitly maintained using the position of the elements in the list.

6. Bag in Hibernate/JPA:
A Bag in Hibernate/JPA refers to a collection mapping strategy where a `List` collection is used, and the corresponding table is created without an index column. In a bag mapping, only two columns are created in the child table: the Key Column (FK column) and the Element Column. The bag mapping allows duplicate and unordered elements.

7. Adding Columns to Collection with Primitives:
No, you cannot add additional columns to the child tables created for `@ElementCollection` with primitives. The structure of these tables is pre-defined with two or three columns (Key Column, Index Column for List, and Element Column). You cannot add extra columns to this structure.

8. PK and FK Columns in Child Tables:
For `@ElementCollection` of `List` and `Map`, child tables contain PK and FK columns as a composite primary key. The combination of the Key Column (FK column) and the Index Column forms the composite primary key. However, for `@ElementCollection` of `Set`, no PK and FK columns are created because sets do not allow duplicate data.

9. Creation of Child Table and Columns:
The `@ElementCollection` annotation is responsible for creating the child table and columns. It must be applied to the collection variable in the entity class. This annotation signifies that the variable represents a collection that should be mapped to a separate table.

In summary, the child tables and columns in Hibernate/JPA are created based on the naming conventions and annotations used. The structure of these tables is predefined for collections with primitives, and additional columns cannot be added. The `@ElementCollection` annotation is essential for creating the child table, and other annotations can be used to customize the names of the tables and columns.


<br/>
<br/>
<br/>

# Let's go through each FAQ in detail and provide explanations along with code snippets where applicable:

**Q) What is Bag in Hibernate/JPA?**<br/>
A) A Bag in Hibernate/JPA refers to a collection mapping strategy where a `List` collection is used, and the corresponding table is created without an index column. In other words, a Bag represents an unordered collection that allows duplicate elements. In terms of table structure, a `List` has three columns: Key Column (FK column), Index Column, and Element Column. However, a Bag eliminates the need for an Index Column and only has two columns: Key Column and Element Column. Here's an example:

```java
@ElementCollection
@CollectionTable(
    name = "product_colors",
    joinColumns = @JoinColumn(name = "pidFk")
)
@Column(name = "color")
private List<String> colors;
```

In this example, the `colors` collection is mapped as a Bag. The `product_colors` table will be created with two columns: `pidFk` (FK column) and `color` (Element Column).

**Q) Can we add few more columns in Collection with Primitives at child tables?**<br/>
A) No, the structure of the child tables created for collections with primitives is predefined and consists of two or three columns. It is not possible to add additional columns to these tables.

**Q) Does the child table contain PK and FK columns created for @ElementCollection?**<br/>
A) Yes, for collections of type `List` and `Map`, the child table contains PK and FK columns as a composite primary key. The composite primary key is formed by combining the Key Column (FK column) and the Index Column. However, for collections of type `Set`, no PK and FK columns are created because sets do not allow duplicate data.

**Q) Does every child table contain an FK column?**<br/>
A) Yes, every child table created for an `@ElementCollection` must contain an FK (Foreign Key) column. The FK column establishes a relationship with the PK (Primary Key) column of the parent table. It ensures that the child table's data is associated with the correct parent entity.

**Q) Which annotation creates child table and columns?**<br/>
A) The `@ElementCollection` annotation is responsible for creating the child table and columns. It must be applied to the collection variable in the entity class. This annotation indicates that the variable represents a collection that should be mapped to a separate table. Here's an example:

```java
@ElementCollection
@CollectionTable(
    name = "product_colors",
    joinColumns = @JoinColumn(name = "pidFk")
)
@Column(name = "color")
private List<String> colors;
```

In this example, the `@ElementCollection` annotation creates the child table `product_colors` with columns `pidFk` (FK column) and `color` (Element Column).

The other annotations such as `@CollectionTable`, `@JoinColumn`, `@Column`, `@OrderColumn`, and `@MapKeyColumn` are used to provide custom names for the child table and columns, control the order of elements in a `List`, and specify the index column for a `Map` collection.

These explanations should help you understand the concepts and usage of Bag collections, the structure of child tables, and the annotations involved in creating them.


<br/>
<br/>
<br/>

# TASK 

Sure! Here's the updated code with the package name replaced and additional examples for max, avg, min, and count queries:

1. Entity class - Employee.java:
```java
package com.shivam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "emp_id")
    private int empId;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_sal")
    private int empSal;

    @Column(name = "emp_dept")
    private String empDept;

    // Constructors, getters, and setters
    // ...
}
```

2. Insert data:
```java
Employee emp1 = new Employee(10, "A", 300, "DEV");
Employee emp2 = new Employee(11, "B", 400, "DEV");
Employee emp3 = new Employee(12, "C", 300, "QA");
Employee emp4 = new Employee(13, "D", 400, "QA");

employeeRepository.saveAll(Arrays.asList(emp1, emp2, emp3, emp4));
```

3. Repository interface - EmployeeRepository.java:
```java
package com.shivam.repository;

import com.shivam.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT empDept, COUNT(empDept) FROM Employee GROUP BY empDept")
    List<Object[]> getCountByDept();

    @Query("SELECT MIN(empSal) FROM Employee")
    long getMinSal();

    @Query("SELECT MAX(empSal) FROM Employee")
    long getMaxSal();

    @Query("SELECT AVG(empSal) FROM Employee")
    double getAvgSal();

    @Query("SELECT COUNT(empId) FROM Employee")
    long getCount();

    // Add more queries based on your requirements

}
```

In the repository interface, I've added additional methods for retrieving the maximum salary (`getMaxSal()`), average salary (`getAvgSal()`), and count of employees (`getCount()`). You can add more queries as per your needs.
