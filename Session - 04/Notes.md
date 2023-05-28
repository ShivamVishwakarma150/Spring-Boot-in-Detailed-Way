Spring Container, also known as the Spring IoC (Inversion of Control) container, is a core component of the Spring Framework that manages the lifecycle of objects and their dependencies. It follows the principle of Inversion of Control, where the control of object creation and dependency injection is shifted to the container.

The Spring Container performs the following tasks:

1. Find/Scan classes: The container scans the application's classpath to find the classes that are configured as Spring beans. It identifies the classes that need to be managed by the container.

2. Create objects: The container creates instances of the identified classes (Spring beans) using their default constructors or parameterized constructors, depending on the configuration. It manages the lifecycle of these objects, including their creation, initialization, and destruction.

3. Provide data and link objects: The container injects dependencies into the created objects. It provides the required data and links the objects together according to the defined relationships. This is achieved through various methods of dependency injection, such as setter injection, constructor injection, lookup method injection, and interface injection.

   - Setter Injection: The container calls the setter methods of the objects and passes the required dependencies.
   - Constructor Injection: The container calls the parameterized constructor of the objects and passes the required dependencies.
   - Lookup Method Injection: The container provides an instance of the dependency through a lookup method defined in the bean.
   - Interface Injection: The container injects the dependency by implementing an interface defined in the bean.

4. Destroy the objects/container: The container manages the destruction of the objects when they are no longer needed. It can invoke the destruction callback methods defined in the beans or perform any necessary cleanup operations. In some cases, the entire container can be shut down, leading to the destruction of all managed objects.

The Spring Container can be configured using different approaches:

- XML Configuration: The container can be configured using XML-based configuration files. The beans, their dependencies, and other configuration details are defined in the XML file.

- Java Configuration: The container can be configured using Java-based configuration classes. Annotations such as `@Configuration`, `@Bean`, and others are used to define the beans and their dependencies.

- Annotation Configuration: The container can also be configured using annotations. Annotations such as `@Component`, `@Autowired`, and others are used to mark the beans and their dependencies.

By using the Spring Container, developers can achieve loose coupling between components, improve modularization, and simplify the management of object dependencies. It provides a flexible and powerful mechanism for managing object lifecycles and dependency injection in Spring applications.

<br/>
<br/>
<br/>

The Spring container takes two inputs to manage the beans and their dependencies:

A) Spring Bean (class + rules):
The Spring Bean represents a class that needs to be managed by the Spring container. It is a Java class that follows certain rules imposed by the Spring framework. These rules include providing default constructors or parameterized constructors, defining setter methods for dependencies, implementing interfaces for injection, and following naming conventions.

To make a class a Spring Bean, you can annotate it with `@Component` or its specialized annotations like `@Service`, `@Repository`, or `@Controller`. Alternatively, you can configure the beans using XML or Java-based configuration.

B) Spring Configuration File (XML / Java / Annotation):
The Spring Configuration File is used to provide metadata about the beans and their dependencies to the Spring container. It specifies how the beans should be created, configured, and wired together. There are three common ways to provide the configuration:

1. XML Configuration: In XML configuration, you define the beans and their dependencies in an XML file. The `<bean>` tag is used to define a bean, and various attributes and child elements are used to configure the bean properties, dependencies, and other settings.

2. Java Configuration: With Java-based configuration, you use Java classes annotated with `@Configuration` to define the beans and their dependencies. The `@Bean` annotation is used to mark methods that create and configure the beans. The dependencies between beans are expressed using method calls.

3. Annotation Configuration: Annotation-based configuration allows you to use annotations to configure the beans and their dependencies. You can annotate the bean classes with annotations like `@Component`, `@Autowired`, `@Value`, and others to define the beans and their relationships.

Both Java and Annotation configurations provide a more type-safe and code-centric approach compared to XML configuration.

The Spring container reads the configuration information from the specified configuration files or classes and uses it to create and manage the beans and their dependencies accordingly.

<br/>
<br/>
<br/>

The XML configuration in Spring provides several tags for defining beans and configuring their dependencies. Here's a detailed explanation of the commonly used XML configuration tags:

1. `<bean>`:
   - The `<bean>` tag is used to define a bean in the Spring configuration file.
   - It is responsible for creating an instance of the bean class and managing its lifecycle.
   - Attributes such as `id` and `class` are used to specify the bean's identifier and the fully qualified class name, respectively.

2. `<property>`:
   - The `<property>` tag is used to set the values of bean properties or invoke setter methods.
   - It is a child element of the `<bean>` tag and is used to configure dependencies for the bean.
   - Attributes such as `name` and `value` are used to specify the property name and its value, respectively.

3. `<constructor-arg>`:
   - The `<constructor-arg>` tag is used to provide the arguments required for the constructor-based dependency injection.
   - It is a child element of the `<bean>` tag and is used to configure constructor arguments for the bean.
   - Attributes such as `index` and `value` are used to specify the argument index and its value, respectively.

4. `<value>`:
   - The `<value>` tag is used to provide a literal value for a property or constructor argument.
   - It can be used within the `<property>` or `<constructor-arg>` tags.
   - It is mainly used for injecting primitive types or String values.

5. `<list>`, `<set>`, `<map>`, `<props>`:
   - These tags are used to define collection types (List, Set, Map, and Properties) as property values.
   - `<list>` is used for defining a list of values.
   - `<set>` is used for defining a set of unique values.
   - `<map>` is used for defining key-value pairs.
   - `<props>` is used for defining key-value pairs as Java Properties.

6. `<ref/>`:
   - The `<ref/>` tag is used to create a reference to another bean.
   - It is used within the `<property>` or `<constructor-arg>` tags to specify the dependency relationship between beans.
   - It is typically used to wire dependencies between beans.

These XML configuration tags provide a flexible way to define beans and configure their dependencies in the Spring framework.