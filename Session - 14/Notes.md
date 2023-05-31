# Some Important Question

Q) What is coupling? How many types are there?

A) Coupling refers to the degree of interdependence between different modules or components in a system. It represents how closely one component is connected to, or dependent on, another component. In other words, it measures the level of knowledge and interaction that one component has with another.

There are two types of coupling:

1. Tight Coupling:
   Tight coupling occurs when two components or modules are highly dependent on each other. In this type of coupling, changes in one component may require corresponding changes in the other component. It leads to a lack of flexibility and makes the system more difficult to modify or maintain. Tight coupling can result in code that is hard to test, reuse, and understand.

2. Loose Coupling:
   Loose coupling refers to a situation where components are independent and have minimal knowledge of each other. Each component can operate independently, and changes in one component do not affect the other components significantly. This promotes flexibility, modularity, and reusability in the system. Loose coupling allows for easier maintenance and modifications as components can be replaced or modified without impacting the entire system.

Q) Why Coupling?

A) Coupling is an important concept in software engineering because it directly affects the maintainability, flexibility, and scalability of a system. The level of coupling between components determines how easily changes can be made to the system without causing ripple effects throughout the codebase. By managing and reducing coupling, we can improve the overall design and quality of the software.

Q) What is tight coupling?

A) Tight coupling refers to a high level of interdependence between components or modules in a system. It occurs when one component depends heavily on the internal details, implementation, or specific interfaces of another component. In tightly coupled systems, changes made to one component often require corresponding changes in other components. This results in a system that is difficult to modify, maintain, and test.

Tight coupling can lead to several issues:

- Lack of flexibility: Modifying one component may require changes in multiple other components, making the system inflexible and resistant to change.
- Low modularity: Components cannot be easily separated or reused since they are highly dependent on each other.
- Reduced testability: Testing individual components becomes challenging as they rely on the presence and behavior of other tightly coupled components.
- Decreased maintainability: It is harder to understand, debug, and fix issues in tightly coupled systems, increasing the maintenance effort.

Q) What is loose coupling?

A) Loose coupling refers to a low level of interdependence between components or modules in a system. It means that components have minimal knowledge of each other and are independent entities. In loosely coupled systems, each component can function independently and changes made to one component have minimal impact on other components.

Benefits of loose coupling include:

- Increased flexibility: Components can be modified or replaced without affecting other components, allowing for easier system changes and updates.
- Improved modularity: Components are designed to be independent and can be reused in different contexts or systems.
- Enhanced testability: Individual components can be tested independently, promoting easier unit testing and isolation of issues.
- Better maintainability: With reduced dependencies, it is easier to understand, debug, and maintain the codebase.

By promoting loose coupling, software systems become more flexible, modular, and easier to maintain and evolve over time.

<br/>
<br/>
<br/>

# Here's the code along with the explanations:

Certainly! Here's the code along with the explanations:

1. Spring Bean:

```java
public interface IProcess {
    void getProcesCode();
}
```
- The `IProcess` interface defines the `getProcesCode()` method.

```java
@Component
public class AdvProcessImpl implements IProcess {
    @Override
    public void getProcesCode() {
        System.out.println("P2");
    }
}
```
- `AdvProcessImpl` is a concrete implementation of the `IProcess` interface.
- It is annotated with `@Component`, making it eligible for dependency injection.
- The `getProcesCode()` method prints "P2".

```java
@Component
@Primary
public class ProcessImpl implements IProcess {
    @Override
    public void getProcesCode() {
        System.out.println("P1");
    }
}
```
- `ProcessImpl` is another concrete implementation of the `IProcess` interface.
- It is also annotated with `@Component` and `@Primary`.
- The `getProcesCode()` method prints "P1".

2. `Format` Class:

```java
@Component
public class Format {
    @Autowired
    private IProcess pob;
    
    public void printFormat() {
        pob.getProcesCode();
        System.out.println("FROM FORMAT");
    }
}
```
- `Format` is a Spring bean that has a dependency on the `IProcess` interface.
- The `pob` field is annotated with `@Autowired`, indicating that Spring should inject an instance of `IProcess`.
- The `printFormat()` method calls the `getProcesCode()` method on the `pob` dependency and prints "FROM FORMAT".

3. Spring Config class:

```java
@ComponentScan("com.app.shivam")
public class AppConfig {
}
```
- The `AppConfig` class is a Spring configuration class.
- It uses `@ComponentScan` to specify the package to scan for components.

4. Test class:

```java
public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Format fob = ac.getBean("format", Format.class);
        fob.printFormat();
    }
}
```
- The `Test` class is the entry point of the application.
- It creates an instance of `AnnotationConfigApplicationContext` using the `AppConfig` class.
- It retrieves an instance of the `Format` bean using `getBean()`.
- Finally, it calls the `printFormat()` method on the `Format` bean.

Explanation:

- The `Format` class has a dependency on the `IProcess` interface.
- The `@Autowired` annotation on the `pob` field enables Spring to inject an instance of `IProcess` into it.
- During runtime, Spring identifies the available implementations of `IProcess` (i.e., `AdvProcessImpl` and `ProcessImpl`).
- Since `ProcessImpl` is annotated with `@Primary`, it becomes the preferred implementation for autowiring.
- When the `printFormat()` method is called, it invokes the `getProcesCode()` method on the `pob` dependency.
- As a result, "P1" (from `ProcessImpl`) is printed, followed by "FROM FORMAT".

Conclusion:

- The code demonstrates the usage of `@Autowired` in a complex scenario with multiple implementations of an interface.
- By annotating the implementations with `@Component` and using `@Primary`, Spring can handle the dependency injection automatically.
- This approach promotes loose coupling between components and reduces manual effort for dependency instantiation and wiring.
- The `@Autowired` annotation simplifies configuration and

 management of dependencies in Spring applications.
- It improves code readability, flexibility, and maintainability by removing the need for explicit dependency instantiation and wiring.
- Overall, `@Autowired` helps in achieving cleaner and more modular code by allowing Spring to handle dependency injection.

<br/>
<br/>
<br/>

## In Spring, when there are multiple bean definitions for the same type, you can use one of the following mechanisms to specify which bean should be injected:

1. **@Qualifier:** The `@Qualifier` annotation is used along with `@Autowired` to specify a specific bean by its qualifier value. Qualifiers provide a way to differentiate between beans of the same type. For example, if you have multiple implementations of an interface, you can use `@Qualifier` to specify which implementation to inject.

2. **@Primary:** The `@Primary` annotation is used to indicate a primary bean when there are multiple bean definitions of the same type. It marks a bean as the primary choice for autowiring when no specific qualifier is specified. If there are multiple beans of the same type and no `@Qualifier` is used, the one marked with `@Primary` will be injected.

3. **Name Matching:** In some cases, you can rely on the default bean name matching to determine which bean to inject. When using `@Autowired` without any explicit qualifier, Spring will try to match the name of the dependency variable with the name of the bean. If the names match, Spring will inject that bean. However, relying solely on name matching can be error-prone and less flexible compared to using `@Qualifier` or `@Primary`.

It's important to note that if there are multiple bean definitions for the same type and no mechanism is used to specify the desired bean, Spring will throw a `ConflictingBeanDefinitionException` indicating that there is a conflict and it cannot determine which bean to inject.

By using `@Qualifier`, `@Primary`, or name matching, you can explicitly specify which bean should be injected in cases where there are multiple beans of the same type. This helps in resolving ambiguity and ensuring that the correct bean is injected based on your requirements.