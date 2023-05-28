# Abstract Notes of Today Class

1. DI (Dependency Injection) vs. IoC (Inversion of Control):
   - Dependency Injection (DI) is a design pattern in which the dependencies of a class are provided externally, rather than the class itself creating or managing its dependencies. It enables loose coupling between classes, as the class doesn't need to be aware of how its dependencies are created or obtained.
   - Inversion of Control (IoC) is a principle that emphasizes shifting the control of object creation and dependency management from the class itself to an external entity, often referred to as a container or framework. The container is responsible for creating and managing objects and their dependencies, thus inverting the control of object creation.

2. Types of Dependencies:
   - a) Primitive Type Dependency (PTD): It refers to a variable created using primitive data types such as byte, short, int, long, float, double, boolean, char, and String. These variables hold simple values.
   Example:
      ```java
      int age;
      String name;
      ```

   - b) Collection Type Dependency (CTD): It refers to a variable created using collection types from the Java.util package, such as List, Set, Map, and Properties. These variables hold multiple values or key-value pairs.
   Example:
      ```java
      List<String> names;
      Map<Integer, String> studentMap;
      ```

   - c) Reference Type Dependency (RTD): It refers to a variable created using a class or interface as the data type. These variables hold references to objects of other classes or interfaces.
   Example:
        ```java
        MyClass obj;
        MyInterface interfaceObj;
        ```

3. Injection:
   Injection is the process of providing data or dependencies to variables or objects after their creation.

   a) Setter Injection (SI): Setter injection is a type of dependency injection where dependencies are injected using setter methods. The object is first created using a default constructor, and then the dependencies are provided using the setter methods.
   Example:
   ```java
   class A {
     int id;
     void setId(int id) {
       this.id = id;
     }
   }

   A obj = new A();
   obj.setId(10);
   ```

   b) Constructor Injection (CI): Constructor injection is a type of dependency injection where dependencies are provided through a parameterized constructor. The object is created by passing the required dependencies as constructor arguments.
   Example:
   ```java
   class A {
     int id;
     A(int id) {
       this.id = id;
     }
   }

   A obj = new A(10);
   ```

   c) LookUp Method Injection (LMI) and Interface Injection (II) are less commonly used forms of injection in Spring. LookUp Method Injection involves defining abstract methods in a class that subclasses override to provide the dependency instances. Interface Injection involves implementing an interface to provide the dependency instances.

4. Software:
   - JDK (Java Development Kit): JDK is required for developing Java applications. JDK 17 is the latest version (you can also use versions 8 or 11). You can download the JDK from the Oracle website and install it on your machine.
   - STS (Spring Tool Suite): STS is an integrated development environment (IDE) based on Eclipse, specifically designed for developing Spring applications. It provides tools and features that make it easier to work with Spring projects. You can download STS from the Spring website, install it, and set up your workspace to start developing Spring applications.


<br/>
<br/>
<br/>

In the given code snippet, let's define the types of dependencies for the `Product` class:

1. Primitive Type Dependency (PTD):
   - `pid` is a primitive type dependency since it is declared as an `int` variable.
   - `pcode` is a primitive type dependency since it is declared as a `String` variable.

2. Collection Type Dependency (CTD):
   - `models` is a collection type dependency since it is declared as a `List<String>`, which is a collection type from the `java.util` package.
   - `colors` is a collection type dependency since it is declared as a `Set<String>`, which is a collection type from the `java.util` package.

3. Reference Type Dependency (RTD):
   - `vob` is a reference type dependency since it is declared as an object of the `Vendor` class, which is a reference type.
   - `gob` is a reference type dependency since it is declared as an object of an interface `GstInfo`, which is a reference type.

To summarize:

- PTD: `pid` (int), `pcode` (String)
- CTD: `models` (List<String>), `colors` (Set<String>)
- RTD: `vob` (Vendor), `gob` (GstInfo)

Note: It seems there is a typo in the code snippet where `List<Sring>` should be corrected to `List<String>` for the `models` variable.

<br/>
<br/>
<br/>

# Dependency Injection

Dependency Injection (DI) is a design pattern commonly used in Spring Boot to achieve loose coupling and facilitate the creation and management of object dependencies. DI allows the objects to be independent of each other by providing their dependencies from an external source rather than creating them internally.

In Spring Boot, DI is achieved through the Spring IoC (Inversion of Control) container. The container is responsible for creating objects (beans) and wiring them together by injecting the required dependencies.

Let's explain DI in Spring Boot with a code example:

1. Define a Dependency
First, we define a class that represents a dependency, let's call it `DependencyClass`:

```java
public class DependencyClass {
    public void doSomething() {
        System.out.println("Doing something in the dependency class");
    }
}
```

2. Create a Dependent Class
Next, we create a class that depends on the `DependencyClass` and needs it to perform its tasks, let's call it `DependentClass`:

```java
public class DependentClass {
    private DependencyClass dependency;

    public DependentClass(DependencyClass dependency) {
        this.dependency = dependency;
    }

    public void doTask() {
        System.out.println("Performing a task in the dependent class");
        dependency.doSomething();
    }
}
```

The `DependentClass` has a dependency on the `DependencyClass` and it is injected through the constructor.

3. Configure Dependency Injection in Spring Boot
To enable DI in Spring Boot, we need to configure the dependencies in a configuration class. Let's create a configuration class called `AppConfig`:

```java
@Configuration
public class AppConfig {
    @Bean
    public DependencyClass dependencyClass() {
        return new DependencyClass();
    }

    @Bean
    public DependentClass dependentClass(DependencyClass dependency) {
        return new DependentClass(dependency);
    }
}
```

In the `AppConfig` class, we define two `@Bean` methods. The first method creates an instance of `DependencyClass` and makes it available for injection. The second method creates an instance of `DependentClass` and injects the `DependencyClass` through its constructor.

4. Run the Application
Finally, we run the Spring Boot application by creating a main class and annotating it with `@SpringBootApplication`:

```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

5. Test Dependency Injection
We can now test the dependency injection by invoking the `doTask()` method of the `DependentClass`:

```java
@Component
public class MyComponent implements CommandLineRunner {
    private DependentClass dependentClass;

    public MyComponent(DependentClass dependentClass) {
        this.dependentClass = dependentClass;
    }

    @Override
    public void run(String... args) throws Exception {
        dependentClass.doTask();
    }
}
```

In the `MyComponent` class, we inject the `DependentClass` through its constructor. The `CommandLineRunner` interface allows us to execute code when the application starts. In the `run()` method, we invoke the `doTask()` method of the `DependentClass`.

When we run the Spring Boot application, the `DependencyClass` and `DependentClass` beans are created and wired together by the Spring IoC container. The `doTask()` method is called, and it internally calls the `doSomething()` method of the `DependencyClass`.

This is how Dependency Injection works in Spring Boot. The container takes care of creating the objects and injecting the required dependencies, making the code more modular, flexible, and easy to test.

# Example of DI

   ```java
        class Service{
        Repo rob;
        }
        class Repo{
        }
   ```

In the given code, the `Service` class depends on the `Repo` class. This means that the `Service` class is dependent on the `Repo` class.

To create the objects, we need to follow the dependency hierarchy. In this case, since the `Service` class depends on the `Repo` class, we need to create the `Repo` object first and then the `Service` object.

Here's the sequence of object creation:

1. Create the `Repo` object:
   ```java
   Repo repo = new Repo();
   ```

2. Create the `Service` object and inject the `Repo` object:
   ```java
   Service service = new Service();
   service.setRepo(repo); // Assuming a setter method to inject the dependency
   ```

By following this sequence, we ensure that the dependency (`Repo` object) is created first and then injected into the dependent class (`Service` object). This allows the `Service` object to access the required functionality provided by the `Repo` object.