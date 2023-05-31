# Let's dive into the detailed information about LookUp Method Injection (LMI) when dealing with a dependency bean that is a prototype and a dependent bean that is a singleton.

1. Setter Injection (SI):
   Setter Injection is a way of providing dependencies to a bean by calling setter methods. It involves creating an object using the default constructor and then setting the required data using setter methods. This is a common approach for dependency injection in Spring.

   Example:
   ```java
   public class MyClass {
      private Dependency dependency;

      // Setter method for dependency
      public void setDependency(Dependency dependency) {
         this.dependency = dependency;
      }
      
      // Other methods...
   }
   ```

2. Constructor Injection (CI):
   Constructor Injection is another approach for providing dependencies to a bean by passing them as parameters in the constructor. It involves creating an object using a parameterized constructor and providing the required data directly during object creation.

   Example:
   ```java
   public class MyClass {
      private Dependency dependency;

      // Constructor Injection
      public MyClass(Dependency dependency) {
         this.dependency = dependency;
      }
      
      // Other methods...
   }
   ```

Now let's discuss the issue that arises when the dependency bean is a prototype and the dependent bean is a singleton.

The Problem:
If the dependent bean is a singleton and the dependency bean is a prototype, the expected behavior may not be achieved. This is because when the singleton bean is created by the Spring container, it creates an instance of the prototype bean and establishes the dependency. However, when the prototype bean is accessed again, a new instance is created, but the link between the singleton bean and the new prototype instance is not updated.

This means that subsequent calls to retrieve the prototype bean will return a new instance, but the singleton bean will still hold a reference to the old prototype instance, leading to unexpected behavior and inconsistencies.

To overcome this issue, Spring provides a mechanism called LookUp Method Injection (LMI).

LookUp Method Injection (LMI):
LookUp Method Injection is a feature provided by Spring to handle the scenario where the dependency bean is a prototype and the dependent bean is a singleton. It allows the dependent singleton bean to retrieve a new instance of the prototype bean every time it needs it.

To implement LookUp Method Injection, follow these steps:

1. Mark the dependent bean's method with the `@Lookup` annotation.
2. Specify the name or the type of the prototype bean to be retrieved by the LookUp method.

Example:
```java
public abstract class PrototypeBean {
   // ...
}

@Component
@Scope("singleton")
public class SingletonBean {
   @Lookup("prototypeBean") // or @Lookup(type = PrototypeBean.class)
   public abstract PrototypeBean getPrototypeBean();
}
```

In the example above, `SingletonBean` is a singleton bean that has a dependency on the `PrototypeBean`. By using the `@Lookup` annotation on the `getPrototypeBean()` method, Spring will generate a runtime implementation of this method that retrieves a new instance of the `PrototypeBean` every time it is called.

Note that the `PrototypeBean` class should be an abstract class or an interface, as Spring generates a subclass at runtime to implement the lookup logic.

By utilizing LookUp Method Injection, the dependent singleton bean will always get a new instance of the prototype bean, ensuring consistent behavior and avoiding issues caused by retaining references to outdated prototype instances.

This approach allows for greater flexibility when dealing with prototype-scoped dependencies in singleton beans and ensures that the desired object graph is maintained.

<br/>
<br/>
<br/>

# Here's the code again, followed by the explanation, conclusion, and solution:

```java
// Token.java
package com.app.shivam.service;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Token {

    private int code;

    public Token() {
        super();
        code = Math.abs(new Random().nextInt());
    }

    @Override
    public String toString() {
        return "Token [code=" + code + "]";
    }
}


// TokenService.java
package com.app.shivam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class TokenService {

    @Autowired
    private Token token;

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "TokenService [token=" + token + "]";
    }
}


// AppConfig.java
package com.app.shivam.config;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.app.shivam")
public class AppConfig {
}


// Test.java
package com.app.shivam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.app.shivam.config.AppConfig;
import com.app.shivam.service.Token;
import com.app.shivam.service.TokenService;

public class Test {
	
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		// Reading first time
		TokenService ts1 = ac.getBean("tokenService", TokenService.class);
		Token tk1 = ts1.getToken();
		System.out.println(tk1);

		// Reading 2nd time
		TokenService ts2 = ac.getBean("tokenService", TokenService.class);
		Token tk2 = ts2.getToken();
		System.out.println(tk2);

		// Reading 3rd time
		TokenService ts3 = ac.getBean("tokenService", TokenService.class);
		Token tk3 = ts3.getToken();
		System.out.println(tk3);
	}
}
```

Explanation:
In the given code, we have a TokenService bean with a singleton scope and a Token bean with a prototype scope.

The Token class represents a token object. It is marked with the `@Component` annotation to be detected by component scanning. The `@Scope("prototype")` annotation sets its scope to prototype, indicating that a new instance will be created every time it is requested. The TokenService class represents a token service with a dependency on the Token bean. It is also marked with the `@Component` annotation and has a singleton scope by default. The TokenService class autowires the Token bean using the `@Autowired` annotation.

In the Test class, an ApplicationContext is created using the AnnotationConfigApplicationContext and the AppConfig class. The TokenService bean is retrieved from the container using the `getBean()` method. The Token instance is obtained from the TokenService using the `getToken()` method, and its details are printed. This process is repeated three times to demonstrate the issue with prototype and singleton scopes.

Conclusion:
The issue in this code arises because the TokenService bean is singleton, while the Token bean is prototype. When the TokenService bean is created, it is injected with a Token bean instance. However, subsequent requests for the Token bean result in a new instance being created because of its prototype scope. As a result, the TokenService bean retains a reference to the initial Token instance, and subsequent calls to `getToken()` return different Token instances.

Solution:
To resolve this issue, we can use the LookUp Method Injection (LMI) approach. By using

 LMI, we can explicitly indicate that the Token dependency should be looked up every time it is needed, ensuring that a new instance is obtained from the container. This can be achieved by introducing a lookup method in the TokenService bean that retrieves the Token instance from the container.

Let's explain the code for the LookUp Method Injection (LMI) problem application in detail:

1. Spring Beans:
   - Token: This is a prototype-scoped bean representing a token. It has a random code generated during object creation.
   - TokenService: This is a singleton-scoped bean representing a token service. It has a dependency on the Token bean.

2. Spring Config class:
   - AppConfig: This is the Spring configuration class that enables component scanning for the specified package.

3. Test class:
   - In the main method, an ApplicationContext is created using the AnnotationConfigApplicationContext and the AppConfig class.
   - A TokenService bean is retrieved from the container using the getBean() method.
   - The Token instance is obtained from the TokenService using the getToken() method and its details are printed.
   - This process is repeated three times to demonstrate the issue with prototype and singleton scopes.

Explanation:
When running the Test class, we are retrieving the TokenService bean from the Spring container. On the first retrieval, a new Token instance is created, and its details are printed. However, on subsequent retrievals, instead of creating a new Token instance, the same instance is returned.

The problem arises because the TokenService bean is a singleton, and its dependency, the Token bean, is a prototype. The singleton TokenService bean is created once and maintains a reference to the initial Token instance created during its creation. When we request the Token bean from the TokenService bean again, a new Token instance is created as per the prototype scope, but the TokenService bean still holds a reference to the initial Token instance.

As a result, when we print the Token instance, we see that it is the same instance being returned for each retrieval of the TokenService bean, even though we expect a new instance every time.

To solve this issue using LookUp Method Injection, we need to modify the TokenService class as follows:

```java
@Component
@Scope("singleton")
public class TokenService {

   private Token token;

   public Token getToken() {
      return token;
   }

   @Lookup("token") // Lookup method to retrieve a new Token instance
   protected Token createToken() {
      return null;
   }

   @PostConstruct
   public void init() {
      token = createToken(); // Initializing the Token instance using the Lookup method
   }

   @Override
   public String toString() {
      return "TokenService [token=" + token + "]";
   }
}
```

In the modified TokenService class, we added a protected createToken() method and annotated it with @Lookup("token"). This method acts as a lookup method and is responsible for retrieving a new instance of the Token bean.

In the init() method, which is annotated with @PostConstruct, we initialize the token field by calling the createToken() method. This ensures that the Token instance is retrieved using the Lookup method and a new instance is obtained for each initialization of the TokenService bean.

By utilizing LookUp Method Injection, we can ensure that the TokenService bean always gets a new instance of the Token bean, even though the Token bean is a prototype and the TokenService bean is a singleton.

I hope this explanation clarifies the usage of LookUp Method Injection and how it can address the problem with prototype and singleton scopes.

<br/>
<br/>

# Here's a detailed explanation of the solution using `LookUp` Method Injection `(LMI)`:

1. Define a method that returns the child/dependency class:
   In the parent/dependent class (TokenService in this case), you need to define a method that returns the child/dependency class (Token). This method will be used for the lookup.

```java
@Lookup
public Token getMyToken() {
    return null; // Return null as the actual instantiation will be handled by Spring framework
}
```

2. Add the @Lookup annotation:
   Annotate the `getMyToken()` method with the `@Lookup` annotation. This annotation indicates to the Spring framework that the method should be overridden to provide the lookup functionality.

3. Call the lookup method:
   Modify the dependent class's methods, such as the getter or the `toString()` method, to call the lookup method (`getMyToken()`) instead of directly accessing the dependency.

```java
public Token getToken() {
    token = getMyToken();
    return token;
}
```

By following these steps, you enable LookUp Method Injection in Spring. The `@Lookup` annotation instructs the Spring framework to handle the creation and injection of the dependency bean instance, ensuring that it aligns with the desired scope.

The lookup method will return a new instance of the dependency bean whenever it is called, even if the dependent bean is a singleton. This solves the problem of having a prototype-scoped dependency within a singleton-scoped dependent bean.

It's important to note that the actual creation and management of the dependency bean instance are handled by the Spring framework. As a programmer, you cannot directly control memory-related operations in Spring/Java. Instead, you rely on Spring's container to handle the instantiation and injection of beans.

Here's the modified code that implements LookUp Method Injection (LMI) to resolve the issue with the prototype and singleton scopes:

```java
// TokenService.java
package com.app.raghu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class TokenService {

    @Autowired
    private Token token;

    public Token getToken() {
        token = getMyToken();
        return token;
    }

    @Lookup
    public Token getMyToken() {
        return null; // Returning null here, as the actual instantiation will be handled by the Spring framework
    }

    @Override
    public String toString() {
        return "TokenService [token=" + token + "]";
    }
}
```

Explanation:
In the modified code, we have made changes to the TokenService class. We added a new method called `getMyToken()` annotated with `@Lookup`. This method will be used to retrieve the Token instance from the container.

Inside the `getToken()` method, instead of directly returning the token instance, we call the `getMyToken()` method, which will perform a lookup in the container to obtain a new instance of the Token bean. This ensures that each call to `getToken()` will retrieve a fresh Token instance, regardless of the singleton scope of the TokenService bean.

By using the `@Lookup` annotation, we instruct the Spring framework to handle the creation and injection of the Token bean instance, ensuring that it aligns with the prototype scope.

Now, when the Test class is executed, the TokenService bean will use the lookup method to retrieve a new instance of the Token bean each time `getToken()` is called, solving the issue with the mismatched scopes.

I hope this explanation helps! Let me know if you have any further questions.

<br/>
<br/>
<br/>
<br/>

# ------------ FAQs -----------------

**Q) What is the Reflection API?** <br/>
A) The Reflection API in Java provides a set of classes and interfaces that enable you to examine, introspect, and manipulate the structure and behavior of classes, interfaces, fields, methods, and constructors at runtime. It allows you to analyze and modify the properties of a class dynamically, without having to know the class structure at compile time. The Reflection API is widely used in frameworks and libraries that require runtime analysis and manipulation of classes.

**Q) Write a Java code that takes one class name as input and outputs the following information about the class: number of variables, number of constructors, number of methods, and number of inner classes.**
<br/>

```java
import java.lang.reflect.*;

public class ClassAnalyzer {
    public static void analyzeClass(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);

        int variableCount = clazz.getDeclaredFields().length;
        int constructorCount = clazz.getDeclaredConstructors().length;
        int methodCount = clazz.getDeclaredMethods().length;
        int innerClassCount = clazz.getDeclaredClasses().length;

        System.out.println("Number of variables: " + variableCount);
        System.out.println("Number of constructors: " + constructorCount);
        System.out.println("Number of methods: " + methodCount);
        System.out.println("Number of inner classes: " + innerClassCount);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String className = "com.example.MyClass"; // Replace with your class name
        analyzeClass(className);
    }
}
```
Replace `"com.example.MyClass"` with the fully qualified name of the class you want to analyze.

**Q) What is an Annotation and how does it work with Annotation Processor class?** <br/>
A) Annotations are a form of metadata that can be added to Java classes, methods, fields, and other program elements. They provide additional information about the elements to the compiler, runtime, or other tools. Annotations are defined using the `@interface` keyword and can include parameters.

Annotation Processor is a part of the Java Compiler API that processes annotations at compile-time. It allows you to define custom logic to process and generate code based on the presence of specific annotations in the source code.

Here's how the Annotation Processor class works:
1. You define a custom annotation, let's say `@CustomAnnotation`, using the `@interface` keyword.
2. You write an Annotation Processor class that implements the `javax.annotation.processing.Processor` interface.
3. In the Annotation Processor class, you define the logic to process the annotations and generate code or perform other actions.
4. During the compilation process, the Java compiler scans the source code for annotations and triggers the Annotation Processor if the annotated elements match the conditions defined in the Annotation Processor.
5. The Annotation Processor then processes the annotations, executes the defined logic, and generates the desired output.

**Q) What is LookUp Method Injection (LMI) and when should it be used?** <br/>
A) LookUp Method Injection (LMI) is a feature provided by the Spring framework that allows the injection of a prototype-scoped bean into a singleton-scoped bean. It solves the problem of having a singleton bean dependent on a prototype bean, where the prototype bean needs to be created each time it is accessed.

LMI should be used when you have a scenario where a dependent bean (singleton) needs to have a dependency on another bean (prototype), and you want to ensure that each time the dependency is accessed, a new instance of the prototype bean is provided.

**Q) What are the steps to implement LookUp Method Injection (LMI)?** <br/>
A) The steps to implement LookUp Method Injection (LMI) in a Spring bean are as follows:

1. Define a method in the dependent bean (singleton) that returns the dependency bean (prototype). The method should have no body and simply return null.

2. Annotate the defined method with the `@Lookup` annotation provided by Spring. This annotation instructs the Spring framework to override the method and provide the lookup functionality.

3. Modify the dependent bean's methods, such as the getter or the `toString()` method, to call the lookup method instead of directly accessing the dependency.

**Q) How does LookUp Method Injection (LMI) work internally?** <br/>
A) Internally, Spring framework works at the memory level to implement LookUp Method Injection (LMI). When you use LMI, Spring generates bytecode using CGLIB (Code Generator Library) to create a subclass of the singleton bean with overridden methods. These overridden methods perform the lookup of the dependency bean and ensure that a new instance is provided each time the dependency is accessed.

The lookup process is handled by the Spring framework's Lookup Service, which is responsible for creating and managing the instances of the dependency beans and ensuring that they align with the desired scope (prototype in this case).


<br/>
<br/>
<br/>

# Some Important point regarding `@Autowired`

When the `@Autowired` annotation is applied to an interface variable in Spring, the container attempts to find an implementation class of that interface and injects it into the variable.

This process is known as Spring Container or Spring Inversion of Control (IOC). The container creates an object of the implementation class and establishes the necessary linkage between the independent class and the dependent class.

The `@Autowired` annotation can be used in three different scenarios: field injection, constructor injection, and setter injection.

1. Field Injection:
   In field injection, the `@Autowired` annotation is placed directly above the interface variable declaration. The container scans the class, looks for the implementation class of the interface, creates an instance of it, and injects it into the variable.

   Example:
   ```java
   @Autowired
   private Token token;
   ```

2. Setter Injection:
   In setter injection, the `@Autowired` annotation is applied to the setter method of the variable. The container identifies the interface variable's implementation class, creates an instance of it, and invokes the setter method to inject the dependency.

   Example:
   ```java
   @Autowired
   public void setToken(Token token) {
       this.token = token;
   }
   ```

3. Constructor Injection:
   In constructor injection, the `@Autowired` annotation is used to annotate the constructor of the class. The container resolves the interface variable's implementation class, creates an instance of it, and passes it as an argument to the constructor during object creation.

   Example:
   ```java
   @Autowired
   public TokenService(Token token) {
       this.token = token;
   }
   ```

By using `@Autowired` in any of these ways, you allow the Spring container to handle the dependency injection process automatically, making your code more modular and flexible.

Note that to use `@Autowired`, you need to enable component scanning in your Spring configuration. Component scanning allows the container to automatically detect and instantiate the necessary beans.

