Association Mapping (HAS-A)
===========================

Database Concept: Multiplicity(4)
---------------------------------

1. **Car HAS-A Engine (Multiplicity: 1...1)**<br/>
   In this example, we have a "Car" class that has a one-to-one association with the "Engine" class. It means that each instance of the "Car" class is associated with exactly one instance of the "Engine" class, and vice versa. This type of association mapping is represented by the multiplicity 1...1, indicating a mandatory relationship on both sides.<br/><br/>

2. **Person HAS-A Address (Multiplicity: 1...*)**<br/>
   Here, we have a "Person" class that has a one-to-many association with the "Address" class. It means that each instance of the "Person" class can have multiple instances of the "Address" class associated with it, while each instance of the "Address" class is associated with only one instance of the "Person" class. This type of association mapping is represented by the multiplicity 1...*, indicating a mandatory relationship on the "Person" side and a zero or more relationship on the "Address" side.<br/><br/>

3. **Book HAS-A Author (Multiplicity:** **\*....\*)** <br/>
   In this case, we have a "Book" class that has a many-to-many association with the "Author" class. It means that each instance of the "Book" class can have multiple instances of the "Author" class associated with it, and each instance of the "Author" class can be associated with multiple instances of the "Book" class. This type of association mapping is represented by the multiplicity \*...\*, indicating a zero or more relationship on both sides.<br/><br/>

4. **Product HAS-A GstInfo (Multiplicity: 1...1)**<br/>
   Here, we have a "Product" class that has a one-to-one association with the "GstInfo" class. It means that each instance of the "Product" class can have one instance of the "GstInfo" class associated with it, and each instance of the "GstInfo" class is associated with only one instance of the "Product" class. This type of association mapping is represented by the multiplicity 1...1.<br/><br/>

5. **Employee HAS-A Department (Multiplicity: \*...\*)**<br/>
   In this example, we have an "Employee" class that has a many-to-many association with the "Department" class. It means that each instance of the "Employee" class can be associated with multiple instances of the "Department" class, and each instance of the "Department" class can be associated with multiple instances of the "Employee" class. This type of association mapping is represented by the multiplicity \*...\*, indicating a zero or more relationship on both sides.<br/><br/>

6. **Student HAS-A Course (Multiplicity: \*...\*)**<br/>
   Here, we have a "Student" class that has a many-to-many association with the "Course" class. It means that each instance of the "Student" class can be associated with multiple instances of the "Course" class, and each instance of the "Course" class can be associated with multiple instances of the "Student" class. This type of association mapping is represented by the multiplicity \*...\*, indicating a zero or more relationship on both sides.<br/><br/>

Association mapping is a way to establish relationships between classes or entities in a database. It helps define how instances of one class are connected to instances of another class. Multiplicity defines the cardinality or number of instances that can be associated with each other.

By understanding the association mapping and multiplicity, you can design your database schema or object-oriented model more accurately and effectively.


<br/>
<br/>
<br/>

# Detailed explanation of connecting tables using primary key (PK) and foreign key (FK) columns, considering the two types: non-collection and collection.

Two tables connected using PK-FK columns Concept
------------------------------------------------

In database design, tables can be connected or related to each other through primary key and foreign key columns. The primary key (PK) uniquely identifies each record in a table, while the foreign key (FK) establishes a relationship between two tables by referencing the primary key of another table.

Hint: FK column is created at Many Side
---------------------------------------

When connecting two tables, the foreign key column is typically created on the "many" side of the relationship. This means that the table that can have multiple associated records will contain the foreign key column.

In case of 1...* or *...1, FK column is created at the many Side
--------------------------------------------------------------

For a one-to-many (1...*) or many-to-one (*...1) relationship, the foreign key column is created on the "many" side. It means that the table that can have multiple associated records (the "many" side) will include the foreign key column referencing the primary key of the other table (the "one" side).

For example, if we have a relationship where one "Department" has many "Employees," the "Employee" table will include a foreign key column that references the primary key of the "Department" table. This foreign key column in the "Employee" table represents the relationship between the two tables.

In case of \*...\*, One extra table is created called the JoinTable
--------------------------------------------------------------

For a many-to-many (\*...\*) relationship, an additional table called the JoinTable is created. The JoinTable serves as an intermediary table that connects the two main tables involved in the relationship. This table includes two foreign key columns, known as the JoinColumn and the inverseJoinColumn.

The JoinColumn references the primary key of one table, while the inverseJoinColumn references the primary key of the other table. These two foreign key columns together establish the many-to-many relationship between the main tables.

For example, if we have a many-to-many relationship between "Students" and "Courses," the JoinTable will include a JoinColumn referencing the primary key of the "Student" table and an inverseJoinColumn referencing the primary key of the "Course" table. This JoinTable allows multiple students to be associated with multiple courses.

Summary:
---------

- In a one-to-many or many-to-one relationship (1...* or *...1), the foreign key column is created on the "many" side.
- In a many-to-many relationship (\*...\*), an additional JoinTable is created with two foreign key columns (JoinColumn and inverseJoinColumn) that reference the primary keys of the main tables involved.

Understanding these concepts helps establish proper relationships between tables in a database, enabling efficient data retrieval and maintenance.


<br/>
<br/>
<br/>

## `1)Multiplicity is a Database Concept`

Multiplicity is a concept in database design that describes the relationship or cardinality between entities or classes in a database. It defines the number of instances that can be associated with each other in a particular relationship.

## `2)It is implemented using HAS-A Relation / Association`

Multiplicity is typically implemented using the HAS-A relation or association between entities or classes. The HAS-A relation signifies that one entity or class has another entity or class as a part or component. It represents the association between two entities, where one entity possesses or includes the other.

## `3)It has two types based on child relation: non-collection and collection`

The type of multiplicity is determined based on the relationship with the child entity or class.

### `a) Non-collection type`
If the child entity or class has a multiplicity of 1, it is considered a non-collection type. This means that the parent entity or class can have only one instance associated with it.

### `b) Collection type`
If the child entity or class has a multiplicity of *, it is considered a collection type. This means that the parent entity or class can have multiple instances associated with it.

In the non-collection type, the relationship indicates a one-to-one or one-to-many association. The parent entity has a single child entity or multiple child entities associated with it.

In the collection type, the relationship indicates a many-to-many association. The parent entity can have multiple instances associated with multiple instances of the child entity.

Understanding multiplicity helps define the nature of relationships between entities or classes in a database. It provides information about the cardinality and constraints of these relationships, allowing for accurate modeling and efficient data management.


<br/>

## 4. **`JPA Annotations:`**
-------------------

- **@ManyToOne + @Unique => 1...1 (@OneToOne):**
  This annotation combination represents a one-to-one relationship between entities. It is used when one entity has a single associated entity, and the associated entity is unique. The `@Unique` annotation ensures that the associated entity is unique in the relationship.

- **@OneToMany => 1...*:**
  The `@OneToMany` annotation is used to define a one-to-many relationship between entities. It indicates that one entity has multiple associated entities. In this relationship, the source entity holds a collection of target entities.

- **@ManyToOne =>** ***...1:**
  The `@ManyToOne` annotation is used to define a many-to-one relationship between entities. It indicates that multiple instances of the source entity can be associated with a single target entity. In this relationship, the source entity holds a reference to the target entity.

- **@ManyToMany => \*...\*:**
  The `@ManyToMany` annotation is used to define a many-to-many relationship between entities. It indicates that multiple instances of both the source and target entities can be associated with each other. In this relationship, both the source and target entities hold references to each other.

## 5. **`Additional JPA Annotations:`**
------------------------------

- **@JoinColumn:**
  The `@JoinColumn` annotation is used to specify the foreign key column in the source entity that maps to the primary key column in the target entity. It allows you to provide the name of the foreign key column and customize its properties.

- **@JoinTable (\*...\*):**
  The `@JoinTable` annotation is used in many-to-many relationships to define the join table that connects the source and target entities. It creates an intermediary table to represent the many-to-many association. The join table includes foreign key columns that reference the primary keys of the source and target entities.

Understanding and using these JPA annotations helps in mapping relationships between entities in an object-relational mapping (ORM) framework like JPA. These annotations provide a way to define the multiplicity and association between entities and specify the database mapping details, such as foreign key column names and join tables.

<br/>
<br/>

# Here's a detailed explanation of the coding steps you mentioned:

## **1. Define two classes:**
-----------------------

- In order to implement the HAS-A relationship, we need to define two classes. One will be the parent class, and the other will be the child class. The parent class will have a reference to the child class, establishing the association between them.

## **2. Apply HAS-A Relation:**
------------------------
- To apply the HAS-A relationship, we create a variable in the parent class of the type corresponding to the child class. This variable represents the association between the parent and child classes. It allows the parent class to access the child class and vice versa.

## **3. Check for Collection/Non-Collection type:**
--------------------------------------------
- Next, we need to determine whether the relationship is of a collection type or a non-collection type. If the child class has a multiplicity of 1, it is a non-collection type, indicating a one-to-one or one-to-many relationship. If the child class has a multiplicity of *, it is a collection type, indicating a many-to-many relationship.

## **4. Apply Mapping Annotation and Fk Columns:**
--------------------------------------------
- Based on the type of relationship, we apply the appropriate mapping annotations and foreign key columns to establish the association between the parent and child classes.

- For non-collection type (1...1 or *...1):
  - If it is a one-to-one relationship (1...1), we use the `@OneToOne` annotation in the parent class and specify a foreign key column using `@JoinColumn` to reference the child class.
  - If it is a many-to-one relationship (*...1), we use the `@ManyToOne` annotation in the parent class and specify a foreign key column using `@JoinColumn` to reference the child class.

- For collection type (1...* or \*...\*):
  - If it is a one-to-many relationship (1...*), we use the `@OneToMany` annotation in the parent class to indicate a collection of child class instances.
  - If it is a many-to-many relationship (\*...\*), we use the `@ManyToMany` annotation in both the parent and child classes, and we create a join table using `@JoinTable` to represent the association.


## **5. Draw two tables with sample columns:**
----------------------------------------
- To better understand the relationship and mapping, it's helpful to draw the two tables involved in the association. Each table represents a class, and the columns in the tables correspond to the attributes or properties of the respective classes.

- The table for the parent class will include columns for its own attributes as well as a foreign key column if necessary. The table for the child class will have its own attributes.

- By following these steps, you can successfully implement the HAS-A relationship between two classes and establish the appropriate mapping annotations and foreign key columns based on the type of relationship.

Please note that providing a specific code example would require a specific programming language and framework, as well as a more detailed description of the classes and their attributes.

<br/>
<br/>
<br/>

# Here's the revised response with separate code blocks and explanations for each class:

**1. Entity class:**
- `Department.java`
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
@Table(name="depttab")
public class Department {
	@Id
	@Column(name="did")
	private Integer deptId;
	@Column(name="dname")
	private String deptName;
	@Column(name="dadmin")
	private String deptAdmin;
}
```
Explanation:
- The `Department` class represents the entity for the "depttab" table in the database.
- It includes attributes such as `deptId`, `deptName`, and `deptAdmin`.
- The `@Entity` annotation marks this class as an entity, and the `@Table` annotation specifies the corresponding database table name.
- The `@Id` annotation marks the `deptId` attribute as the primary key column.

- `Employee.java`
```java
package com.app.shivam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name="didFk")
	private Department department;
}
```
Explanation:
- The `Employee` class represents the entity for the "emptab" table in the database.
- It includes attributes such as `empId`, `empName`, `empSal`, and a reference to the `Department` class called `department`.
- The `@ManyToOne` annotation indicates a many-to-one relationship between `Employee` and `Department`.
- The `@JoinColumn` annotation specifies the foreign key column name `didFk` that references the primary key of the `Department` table.

**2. Repository Interfaces:**
- `DepartmentRepository.java`
```java
package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
```
Explanation:
- The `DepartmentRepository` interface extends the `JpaRepository` interface, providing CRUD and other database operations for the `Department` entity.
- It uses generics to specify the entity type (`Department`) and the type of the primary key (`Integer`).

- `EmployeeRepository.java`
```java
package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
```
Explanation:
- The `EmployeeRepository` interface extends the `JpaRepository` interface for performing database operations on the `Employee` entity.
- It also uses generics to specify the entity type (`Employee`) and the type of the primary key (`Integer`).

**3. Runner class:**
- `TestDataInsertRunner.java`
```java
package com.app.shivam.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Department;
import com.app.shivam.entity.Employee;
import com.app.shivam.repo.DepartmentRepository;
import com.app.shivam.repo.EmployeeRepository;

@Component
public class TestDataInsertRunner implements CommandLineRunner {

	@Autowired


	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void run(String... args) throws Exception {
		Department department = new Department(1054,"DEV","AJAY");
		departmentRepository.save(department);
		
		Employee employee1 = new Employee(10, "SAM", 500.0, department);
		Employee employee2 = new Employee(11, "RAM", 600.0, department);
		Employee employee3 = new Employee(12, "SYED", 700.0, department);
		
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
	}

}
```
Explanation:
- The `TestDataInsertRunner` class implements the `CommandLineRunner` interface to execute code during application startup.
- It is responsible for inserting test data into the database using the repository interfaces.
- It demonstrates the creation of a `Department` instance and three `Employee` instances associated with the department.
- The `departmentRepository.save()` method saves the `Department` entity in the database.
- The `employeeRepository.save()` method saves the `Employee` entities in the database.

**4. application.yml:**
- The `application.yml` file contains the configuration properties for the application, including the database connection details and JPA settings.

This code example demonstrates the implementation of the entities, repositories, a runner class, and the application configuration for a Spring Boot application. It showcases the use of JPA annotations, entity associations, and repository interfaces for CRUD operations.

<br/>
<br/>
<br/>

# Here are the `FAQs` along with their answers:

**Basic Level:**

1. **Question:** What is the purpose of the `@Entity` annotation in JPA?<br/>
   **Answer:** The `@Entity` annotation in JPA is used to mark a Java class as an entity, indicating that it is mapped to a database table.

2. **Question:** What is the difference between a primary key and a foreign key?<br/>
   **Answer:** A primary key is a column or a combination of columns that uniquely identifies each record in a table. A foreign key is a column that establishes a relationship between two tables by referencing the primary key of another table.

3. **Question:** Explain the purpose of the `@Table` annotation in JPA.<br/>
   **Answer:** The `@Table` annotation in JPA is used to specify the name of the database table to which an entity is mapped.

4. **Question:** What is the role of the `@Column` annotation in JPA?<br/>
   **Answer:** The `@Column` annotation in JPA is used to specify the mapping of a class attribute to a database column. It allows you to define the column name, data type, length, and other properties.

5. **Question:** What is the significance of the `@Id` annotation in JPA?<br/>
   **Answer:** The `@Id` annotation in JPA is used to mark a class attribute as the primary key of an entity.

**Intermediate Level:**

1. **Question:** What is the purpose of the `JpaRepository` interface in Spring Data JPA?<br/>
   **Answer:** The `JpaRepository` interface in Spring Data JPA provides built-in methods for performing CRUD operations and other common database operations on entities.

2. **Question:** Explain the `@ManyToOne` annotation and its role in establishing a relationship between entities.<br/>
   **Answer:** The `@ManyToOne` annotation establishes a many-to-one relationship between two entities. It indicates that multiple instances of one entity class are associated with a single instance of another entity class.

3. **Question:** What is the significance of the `@JoinColumn` annotation in JPA?<br/>
   **Answer:** The `@JoinColumn` annotation in JPA is used to specify the foreign key column when defining a relationship between entities. It defines the name of the foreign key column and its mapping to the primary key of the associated entity.

4. **Question:** How does the `CommandLineRunner` interface work in Spring Boot? What is its purpose in the provided code?<br/>
   **Answer:** The `CommandLineRunner` interface in Spring Boot allows you to run code when the application starts. It provides a run method where you can write initialization or setup logic for your application.

5. **Question:** Explain the concept of CRUD operations and how they are implemented using the repository interfaces.<br/>
   **Answer:** CRUD operations refer to Create, Read, Update, and Delete operations performed on data in a database. The repository interfaces generated by Spring Data JPA provide methods for these operations, allowing you to easily perform database operations on entities.

**Advanced Level:**

1. **Question:** What is the difference between a one-to-one and a one-to-many relationship in database design?<br/>
   **Answer:** In a one-to-one relationship, each record in one table is associated with exactly one record in another table. In a one-to-many relationship, each record in one table can be associated with multiple records in another table.

2. **Question:** How would you implement a many-to-many relationship between two entities using JPA?<br/>
   **Answer:** To implement a many-to-many relationship between two entities using JPA, you can use the `@ManyToMany` annotation. It creates a join table that contains the foreign keys from both entities.

3. **Question:** Explain the purpose of the `@Autowired` annotation in Spring framework.<br/>
   **Answer:** The `@Autowired` annotation in Spring is used for automatic dependency injection. It automatically wires the dependencies by matching the data type of the bean with the dependency present in the Spring container.

4. **Question:** What is the purpose of the `@Component` annotation in Spring framework?<br/>
   **Answer:** The `@Component` annotation in Spring is used to mark a class as a Spring component. It tells the Spring framework to scan and detect this class as a candidate for auto-detection as a Spring bean.

5. **Question:** Describe the transaction management mechanism used in the provided code example.<br/>
   **Answer:** Transaction management in the provided code example is handled by Spring's default transaction management mechanism. The `@Transactional` annotation can be used on methods or classes to define transactional behavior. Spring manages transactions using AOP (Aspect-Oriented Programming) proxies and integrates with the underlying JPA provider for transactional operations.

These questions and answers should provide you with a good foundation for discussing the concepts related to the provided code example during an interview.

