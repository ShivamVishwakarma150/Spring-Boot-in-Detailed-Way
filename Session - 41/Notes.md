# JPQL/HQL Joins:

1. **We do not write the ON clause:** In JPQL/HQL, unlike in SQL, you do not explicitly write the ON clause to specify the join condition. Instead, the join condition is implicitly determined based on the relationship mapping between the entities/classes.

2. **We do not use the child class name in the query:** In JPQL/HQL, you do not directly reference the child class name in the query. Instead, you use the reference variable of the child class, which represents the association between the parent and child classes.

3. **We use the child class reference variable name:** In JPQL/HQL, you use the reference variable of the child class to represent the association between the parent and child classes. This reference variable behaves like the link in the ON clause of an SQL join, connecting the parent and child entities.

4. **Syntax:**
    ```sql
            Syntax:
        SELECT <column>
        FROM ParentClass p
            [Join Type]
        p.childClsVariable C
        WHERE <condition>
    ```
   - SELECT <column>: You specify the columns or attributes you want to retrieve from the parent and child entities.
   - FROM ParentClass p: You specify the parent entity/class and assign it a reference variable "p".
   - [Join Type]: You specify the type of join you want to perform.
   - p.childClsVariable C: You use the reference variable "C" to represent the association between the parent and child entities.
   - WHERE <condition>: You can specify additional conditions to filter the data based on specific criteria.

To summarize, JPQL/HQL joins are implemented to fetch data from class/objects (multiple tables/rows) based on their relationships. The join condition is determined implicitly by the relationship mapping between the entities. You use the reference variable of the child class to represent the association between the parent and child entities, instead of explicitly writing the ON clause as in SQL.

<br/>
<br/>

#  Here's an explanation of the points you mentioned:

**Q) Is Full Outer Join Supported By All DBs?**<br/><br/>
A) No. Full outer join is not supported by all databases. Not all databases provide native support for full outer join operations. Full outer join retrieves all records from both tables, including matched and unmatched records. However, fetching all records from a database without any specific criteria or conditions may not have a meaningful purpose in many cases.

**Q) Which one is better to use? @Query("SELECT e FROM Employee e) [or] repo.findAll()**<br/>
A) It is generally recommended to use pre-defined methods provided by the repository (such as `findAll()`) rather than writing custom queries using `@Query` annotations. The reason is that pre-defined methods are already implemented and optimized by the underlying framework (e.g., Spring Data JPA) based on standard conventions. These pre-defined methods provide a higher level of abstraction and handle common CRUD (Create, Read, Update, Delete) operations efficiently.

Custom methods should be used when the required functionality is not provided by the pre-defined methods. However, it's important to note that custom methods require the generation of new method definitions with dynamic code, which can impact performance and maintainability. Therefore, it is generally recommended to use pre-defined methods first and resort to custom methods only when necessary.

To summarize, while full outer join is not supported by all databases, it is advisable to use pre-defined methods provided by the repository rather than writing custom queries whenever possible. Pre-defined methods are optimized and provide a higher level of abstraction, ensuring efficient and standard implementation of common database operations. Custom methods should be used sparingly, when the desired functionality is not available through pre-defined methods.

<br/>
<br/>

# Here's the explanation of the code:

**Employee Entity:**
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
@Table(name = "emptab")
public class Employee {
    @Id
    @Column(name = "eid")
    private Integer empId;

    @Column(name = "ename")
    private String empName;

    @Column(name = "esal")
    private Double empSal;

    @ManyToOne
    @JoinColumn(name = "didFk")
    private Department dob;
}
```

Explanation:
- The `Employee` class represents an entity for employees.
- It is annotated with `@Entity` to specify that it is a JPA entity.
- The `@Table(name = "emptab")` annotation defines the table name as "emptab".
- The class has attributes such as `empId`, `empName`, `empSal`, and a many-to-one relationship with `Department`.
- The `@Id` annotation specifies the primary key attribute.
- The `@Column` annotation is used to map the attributes to their respective columns in the table.
- The `@ManyToOne` annotation specifies the many-to-one relationship with the `Department` entity.
- The `@JoinColumn(name = "didFk")` annotation defines the foreign key column name.

**Department Entity:**
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
@Table(name = "depttab")
public class Department {
    @Id
    @Column(name = "did")
    private Integer deptId;

    @Column(name = "dname")
    private String deptName;

    @Column(name = "dadmin")
    private String deptAdmin;
}
```

Explanation:
- The `Department` class represents an entity for departments.
- It is annotated with `@Entity` to specify that it is a JPA entity.
- The `@Table(name = "depttab")` annotation defines the table name as "depttab".
- The class has attributes such as `deptId`, `deptName`, and `deptAdmin`.
- The `@Id` annotation specifies the primary key attribute.
- The `@Column` annotation is used to map the attributes to their respective columns in the table.

**EmployeeRepository Interface:**
```java
package com.app.shivam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.shivam.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT emp.empName, dept.deptName FROM Employee emp INNER JOIN emp.dob as dept")
    List<Object[]> getEnameDeptNames();

    @Query("SELECT emp.empName, dept.deptName FROM Employee emp LEFT OUTER JOIN emp.dob as dept WHERE dept IS NULL")
    List<Object[]> getAllEnamesAndDeptNamesIfExist();

    @Query("SELECT emp.empName, dept.deptName FROM Employee emp RIGHT OUTER JOIN emp.dob as dept WHERE emp IS NULL")
    List<Object[]> getAllDeptsAndEnamesIfExist();

    @Query("SELECT emp.empName, dept.deptName FROM Employee emp FULL JOIN emp.dob as dept")
    List<Object[]> getAllData();

    @Query("SELECT emp.empName, dept.deptName FROM Employee emp INNER JOIN emp.dob as dept WHERE dept.deptName=:deptName")
    List<Object[]> getEnameDeptNames(String deptName);
}
```

Explanation:
- The `EmployeeRepository` interface extends the `JpaRepository` interface, which provides generic CRUD operations for the `Employee` entity.
- It also defines additional custom queries using the `@Query` annotation.
- The `getEnameDeptNames()` method retrieves the employee names and department names using an inner join between the `Employee` and `Department` entities.
- The `getAllEnamesAndDeptNamesIfExist()` method retrieves the employee names and department names using a left outer join and filters the result to include only employees with a null department.
- The `getAllDeptsAndEnamesIfExist()` method retrieves the employee names and department names using a right outer join and filters the result to include only departments with no associated employees.
- The `getAllData()` method retrieves all employee names and department names using a full join between the `Employee` and `Department` entities.
- The `getEnameDeptNames(String deptName)` method retrieves employee names and department names for a specific department name by using a parameterized query.

**DepartmentRepository Interface:**
```java
package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
```

Explanation:
- The `DepartmentRepository` interface extends the `JpaRepository` interface for the `Department` entity.
- It provides generic CRUD operations for the `Department` entity.

**TestDataInsertRunner Class:**
```java
package com.app.shivam.runner;

import java.util.List;

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
    private DepartmentRepository drepo;

    @Autowired
    private EmployeeRepository erepo;

    public void run(String... args) throws Exception {

        Department d1 = new Department(1054, "DEV", "AJAY");
        Department d2 = new Department(1055, "QA", "SAM");
        Department d3 = new Department(1056, "BA", "KUMAR");
        drepo.save(d1);
        drepo.save(d2);
        drepo.save(d3);

        Employee e1 = new Employee(10, "SAM",

 500.0, d1);
        Employee e2 = new Employee(11, "RAM", 600.0, d2);
        Employee e3 = new Employee(12, "SYED", 700.0, d1);
        Employee e4 = new Employee(13, "AJAY", 700.0, null);

        erepo.save(e1);
        erepo.save(e2);
        erepo.save(e3);
        erepo.save(e4);

        List<Object[]> list1 = erepo.getEnameDeptNames();
        for (Object[] ob : list1) {
            System.out.println(ob[0] + " --IS WORKING ON DEPT-- " + ob[1]);
        }

        List<Object[]> list2 = erepo.getAllEnamesAndDeptNamesIfExist();
        for (Object[] ob : list2) {
            System.out.println(ob[0] + " -- ******** -- " + ob[1]);
        }

        List<Object[]> list3 = erepo.getAllDeptsAndEnamesIfExist();
        for (Object[] ob : list3) {
            System.out.println(ob[0] + " --#######-- " + ob[1]);
        }

        List<Object[]> list4 = erepo.getEnameDeptNames("DEV");
        for (Object[] ob : list4) {
            System.out.println(ob[0] + " ---------- " + ob[1]);
        }
    }
}
```

Explanation:
- The `TestDataInsertRunner` class is a command-line runner that inserts test data into the database and performs various queries using the `EmployeeRepository`.
- It is annotated with `@Component` to indicate that it is a Spring component and should be automatically detected during component scanning.
- The class has dependencies on `DepartmentRepository` and `EmployeeRepository`, which are automatically injected using the `@Autowired` annotation.
- In the `run()` method, it creates instances of `Department` and `Employee` entities and saves them using the repository methods.
- It then executes several queries using the `EmployeeRepository` methods and prints the results.

**Conclusion:**
In this code, we have defined two entities, `Employee` and `Department`, using JPA annotations. The `Employee` entity has a many-to-one relationship with the `Department` entity. The `EmployeeRepository` interface provides custom queries to retrieve various combinations of employee and department data using different types of joins. The `TestDataInsertRunner` class inserts test data into the database and executes the custom queries to demonstrate the functionality.

<br/>
<br/>
<br/>

# To fetch one row data with multiple columns using JPQL/HQL, you can modify your code as follows:

**DepartmentRepository:**
```java
@Query("SELECT d.deptAdmin, d.deptName FROM Department d WHERE d.deptId=:did")
Object[] getSomeData(Integer did);
```

Explanation:
- The modified `getSomeData()` method returns an `Object[]` array instead of a single `Object` to hold multiple column values.
- The JPQL query selects the `deptAdmin` and `deptName` columns from the `Department` entity where the `deptId` matches the provided parameter `did`.

**Runner Class:**
```java
Object[] arr = drepo.getSomeData(1054);
System.out.println(arr[0] + "-" + arr[1]);
```

Explanation:
- The modified code retrieves the result of the `getSomeData()` query into an `Object[]` array.
- Each element in the array corresponds to a selected column value.
- The values can be accessed using array indexes (e.g., `arr[0]` and `arr[1]`).
- The code then prints the retrieved column values.

This way, you can fetch one row of data with multiple columns using JPQL/HQL.

<br/>
<br/>
<br/>

# Some Imp FAQ's JPQL/HQL

1. **What is JPQL (Java Persistence Query Language)?**<br/>
   JPQL (Java Persistence Query Language) is a platform-independent object-oriented query language used to perform database operations on entities in a Java Persistence API (JPA) environment. It is similar to SQL but operates on entity objects instead of database tables.

2. **What is HQL (Hibernate Query Language)?**<br/>
   HQL (Hibernate Query Language) is a powerful object-oriented query language provided by the Hibernate ORM (Object-Relational Mapping) framework. It is similar to JPQL but specific to Hibernate and supports additional features provided by Hibernate.

3. **How do JPQL and HQL differ from SQL?**<br/>
   JPQL and HQL differ from SQL in the following ways:
   - JPQL and HQL operate on entity objects, while SQL operates on database tables.
   - JPQL and HQL use entity class names and property names instead of table names and column names.
   - JPQL and HQL are object-oriented and support inheritance, polymorphism, and associations between entities.
   - JPQL and HQL are portable across different databases, while SQL queries may need to be modified for different database systems.

4. **How are JPQL and HQL used in ORM frameworks like Hibernate?**<br/>
   JPQL and HQL are used in ORM frameworks like Hibernate to query and manipulate entity objects. They provide a convenient and abstracted way to perform database operations without directly interacting with the underlying database.

5. **What is the syntax for writing a basic JPQL/HQL query?**<br/>
   The basic syntax for writing a JPQL/HQL query is as follows:
   ```
   SELECT <select_expression>
   FROM <entity_name>
   [WHERE <condition>]
   ```

6. **How do you specify the SELECT clause in JPQL/HQL?**<br/>
   The SELECT clause in JPQL/HQL is used to specify the properties or expressions that you want to retrieve. It follows the SELECT keyword and is followed by the FROM clause. For example:
   ```
   SELECT e.name FROM Employee e
   ```

7. **How do you specify the FROM clause in JPQL/HQL?**<br/>
   The FROM clause in JPQL/HQL is used to specify the entity or entities from which you want to retrieve data. It follows the FROM keyword and is followed by the optional WHERE clause. For example:
   ```
   SELECT e FROM Employee e
   ```

8. **How do you perform joins in JPQL/HQL?**<br/>
   Joins in JPQL/HQL are performed using the JOIN keyword followed by the association path between entities. For example, to perform an inner join between `Employee` and `Department` entities on the `department` association:
   ```
   SELECT e FROM Employee e JOIN e.department d
   ```

9. **Can you provide examples of different types of joins in JPQL/HQL?**<br/>
   - Inner Join:
     ```
     SELECT e FROM Employee e JOIN e.department d
     ```
   - Left Join:
     ```
     SELECT e FROM Employee e LEFT JOIN e.department d
     ```
   - Right Join:
     ```
     SELECT e FROM Employee e RIGHT JOIN e.department d
     ```
   - Cross Join:
     ```
     SELECT e FROM Employee e CROSS JOIN e.department d
     ```

10. **How do you specify conditions in the WHERE clause of JPQL/HQL?**<br/>
    Conditions in the WHERE clause of JPQL/HQL are specified using comparison operators, logical operators, and entity property names. For example:
    ```
    SELECT e FROM Employee e WHERE e.salary > 50000 AND e.department = 'IT'
    ```

11. **What is the difference between INNER JOIN and LEFT JOIN in JPQL/HQL?**<br/>
    - INNER JOIN returns only the matching records from both tables/entities.
    - LEFT JOIN returns all records from the left table/entity and the matching records from the right table/entity. If there are no matching records, the result will contain null values for the right table/entity.

12. **Is it possible to perform a FULL OUTER JOIN in JPQL/HQL?**<br/>
    No, JPQL/HQL does not support a full outer join. However, you can simulate a full outer join using a combination of left join and right join in the query.

13. **Which databases support FULL OUTER JOIN?**<br/>
    Not all databases support FULL OUTER JOIN. Some databases that support FULL OUTER JOIN include PostgreSQL, Oracle, and Microsoft SQL Server.

14. **When should you use pre-defined methods versus custom methods in JPQL/HQL?**<br/>
    It is recommended to use pre-defined methods provided by the ORM framework (e.g., JpaRepository methods) whenever possible. These methods are more efficient and promote code reuse. Custom methods should be used when there is no suitable pre-defined method available for the required query.

15. **How do you retrieve specific columns from entities using JPQL/HQL?**<br/>
    To retrieve specific columns from entities, you can specify the desired properties or expressions in the SELECT clause of the JPQL/HQL query. For example:
    ```
    SELECT e.name, e.salary FROM Employee e
    ```

16. **Can you provide an example of fetching data from multiple tables using JPQL/HQL?**<br/>
    ```
    SELECT e.name, d.departmentName
    FROM Employee e
    JOIN e.department d
    ```

17. **How do you handle one-to-many or many-to-one relationships in JPQL/HQL?**<br/>
    One-to-many or many-to-one relationships can be handled in JPQL/HQL by using the association path between the entities. For example, to fetch all employees in a department:
    ```
    SELECT e FROM Department d JOIN d.employees e
    ```

18. **What is the purpose of the ON clause in SQL JOINs, and how is it different from JPQL/HQL?**<br/>
    The ON clause in SQL JOINs is used to specify additional join conditions. In JPQL/HQL, the ON clause is not used explicitly. Instead, join conditions are specified as part of the association path in the JOIN keyword.

19. **How do you pass parameters to JPQL/HQL queries?**<br/>
    Parameters can be passed to JPQL/HQL queries by using named parameters or positional parameters. Named parameters are prefixed with a colon (:) followed by a parameter name, while positional parameters are represented by a question mark (?) followed by the parameter index.

20. **Can you provide an example of a parameterized JPQL/HQL query?**<br/>
    ```
    SELECT e FROM Employee e WHERE e.salary > :minSalary AND e.department = :deptName
    ```

21. **What is the difference between getResultList() and getSingleResult() methods in JPQL/HQL?**<br/>
    - getResultList(): Returns a list of results based on the query. If no results are found, an empty list is returned.
    - getSingleResult(): Returns a single result based on the query. If no result is found or multiple results are found, a `NoResultException` or `NonUniqueResultException` is thrown, respectively.

22. **How do you handle pagination in JPQL/HQL queries?**<br/>
    Pagination can be achieved in JPQL/HQL queries by using the `setFirstResult()` and `setMaxResults()` methods. `setFirstResult()` sets the index of the first result to retrieve, and `setMaxResults()` limits the maximum number

 of results to retrieve.

23. **What are the limitations of JPQL/HQL compared to native SQL queries?**<br/>
    - JPQL/HQL may not support all database-specific features and functions.
    - JPQL/HQL queries may be less efficient than native SQL queries for complex operations or when working with large datasets.
    - JPQL/HQL may have limited support for database-specific optimizations.

24. **Can you provide examples of aggregate functions in JPQL/HQL?**<br/>
    - COUNT:
    ```
    SELECT COUNT(e) FROM Employee e
    ```
    - SUM:
    ```
    SELECT SUM(e.salary) FROM Employee e
    ```
    - AVG:
    ```
    SELECT AVG(e.salary) FROM Employee e
    ```
    - MAX:
    ```
    SELECT MAX(e.salary) FROM Employee e
    ```
    - MIN:
    ```
    SELECT MIN(e.salary) FROM Employee e
    ```

25. **How do you handle null values in JPQL/HQL queries?**<br/>
    Null values can be handled in JPQL/HQL queries using the `IS NULL` or `IS NOT NULL` conditions. For example:
    ```
    SELECT e FROM Employee e WHERE e.manager IS NULL
    ```

26. **Is it possible to perform subqueries in JPQL/HQL?**<br/>
    Yes, subqueries are supported in JPQL/HQL. They can be used in the SELECT, FROM, WHERE, or HAVING clauses of a query.

27. **How do you sort query results in JPQL/HQL?**<br/>
    Query results can be sorted in JPQL/HQL using the ORDER BY clause followed by the properties or expressions to sort by. For example:
    ```
    SELECT e FROM Employee e ORDER BY e.name ASC
    ```

28. **What is the purpose of the DISTINCT keyword in JPQL/HQL?**<br/>
    The DISTINCT keyword in JPQL/HQL is used to eliminate duplicate rows from the query results. It ensures that each result in the result set is unique.

29. **How do you handle entity inheritance in JPQL/HQL?**<br/>
    JPQL/HQL queries can handle entity inheritance by querying on the parent entity and using the `TYPE` operator to filter results based on the specific subclass. For example:
    ```
    SELECT e FROM Employee e WHERE TYPE(e) = FullTimeEmployee
    ```

30. **What are the best practices for writing efficient JPQL/HQL queries?**<br/>
    - Use indexed properties or columns in the WHERE clause for better performance.
    - Minimize the use of subqueries and complex expressions.
    - Consider using pagination to limit the number of retrieved results.
    - Use appropriate caching strategies to optimize repeated queries.
    - Profile and analyze the generated SQL queries to identify potential optimizations.