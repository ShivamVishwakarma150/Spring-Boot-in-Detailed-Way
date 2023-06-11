**Q) What is Cascading in JPA/Hibernate?**<br/>
**A)** Cascading in JPA/Hibernate refers to the automatic persistence operation propagation from one entity to associated entities. It allows you to define how the state changes of an entity should be cascaded to its related entities. For example, if you have a parent entity that has a collection of child entities, cascading enables you to automatically persist, update, or delete the child entities when the parent ``entity is persisted, updated, or deleted.``

Cascading can save you from writing repetitive code to handle the persistence operations for associated entities. By specifying the appropriate cascade type, you can ensure that the related entities are managed consistently and their state changes are synchronized with the parent entity.

The cascade types available in JPA/Hibernate include:

- `CascadeType.PERSIST`: When a parent entity is persisted, any new child entities associated with it will also be persisted.
- `CascadeType.MERGE`: When a parent entity is merged (updated), any modifications made to the child entities associated with it will also be merged.
- `CascadeType.REMOVE`: When a parent entity is removed, any associated child entities will also be removed.
- `CascadeType.REFRESH`: When a parent entity is refreshed, any associated child entities will also be refreshed.
- `CascadeType.DETACH`: When a parent entity is detached, any associated child entities will also be detached.
- `CascadeType.ALL`: All of the above cascade types are applied.

By configuring cascading appropriately, you can ensure the integrity and consistency of your entity relationships without manually handling each associated entity.

**Q) What is FetchType in JPA/Hibernate?**<br/>
**A)** FetchType in JPA/Hibernate is used to define how data is fetched from the database when accessing an association between entities. It determines the timing and strategy of fetching associated entities. There are two main fetch types:

- `FetchType.LAZY`: With lazy fetching, associated entities are not loaded from the database until they are explicitly accessed. This means that when you fetch an entity, its associated entities are not automatically loaded, and database queries to retrieve the associated entities are deferred until they are accessed through getter methods. Lazy fetching is useful when you want to optimize performance by loading only the required data when it is actually needed.

- `FetchType.EAGER`: With eager fetching, associated entities are immediately loaded along with the owner entity. When you fetch an entity, its associated entities are automatically loaded from the database. Eager fetching is useful when you frequently access the associated entities and want to avoid lazy loading overhead.

The choice between `FetchType.LAZY` and `FetchType.EAGER` depends on the specific use case and performance considerations. Lazy loading can help improve performance by reducing unnecessary database queries, while eager loading can simplify the code by ensuring that all required data is fetched upfront.

**Q) What is the use of SQL Joins?**<br/>
**A)** SQL Joins are used to combine rows from two or more tables based on a related column between them. Joins allow you to retrieve data from multiple tables in a single query, creating a virtual table that includes columns from the joined tables.

The primary purpose of using SQL Joins is to establish relationships between tables and retrieve data that is spread across different tables. By specifying join conditions, you can define how the tables are related and which columns should be used for joining.

Some common types of SQL Joins include:

- `INNER JOIN`: Returns only the matching rows from both tables based on the join condition.
- `LEFT JOIN` (or `LEFT OUTER JOIN`): Returns all rows from the left table and the matching rows from the right table based on the join condition. If there is no match, NULL values are returned for the columns of the right table.
- `RIGHT JOIN` (or

 `RIGHT OUTER JOIN`): Returns all rows from the right table and the matching rows from the left table based on the join condition. If there is no match, NULL values are returned for the columns of the left table.
- `FULL JOIN` (or `FULL OUTER JOIN`): Returns all rows from both tables. If there is a match, the result set includes the matched rows. If there is no match, NULL values are returned for the columns of the non-matched table.

SQL Joins allow you to combine data from related tables, enabling you to query and analyze data across multiple tables in a relational database system. They are essential for handling complex data retrieval scenarios involving multiple entities and relationships.