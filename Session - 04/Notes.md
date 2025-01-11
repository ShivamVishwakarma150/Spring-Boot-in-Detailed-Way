## **Spring Container (Spring IoC) - Inversion of Control**

The **Spring Container** is the core of the Spring Framework. It follows the principle of **Inversion of Control (IoC)**, where the framework takes control of object creation and dependency management, instead of the application manually doing it. Here's what the Spring Container does:

### **Steps Performed by the Container**
1. **Find/Scan Classes**:  
   The container scans the project for Spring Beans (classes that meet the required configuration) based on the configuration (XML, Java, or Annotations).

2. **Create Objects**:  
   The container creates objects (beans) of these classes as defined in the configuration file.

3. **Provide Data and Link Objects**:  
   The container injects data into the objects' dependencies using **Setter Injection (SI)**, **Constructor Injection (CI)**, or other supported methods. It also establishes relationships (links) between beans.

4. **Destroy the Objects/Container**:  
   Once the container is no longer required, it cleans up by destroying the objects and releasing resources.

---

### **Inputs to the Spring Container**

The Spring Container requires **two inputs**:

1. **Spring Bean**:  
   A class that serves as a blueprint for creating objects. A Spring Bean must follow these rules:
   - Have a default constructor (for setter injection).
   - Use proper getters and setters for dependency injection.

2. **Spring Configuration File**:  
   This file specifies how objects should be created and linked. It can be provided in three ways:
   - **XML Configuration**: Traditional method using XML tags.
   - **Java Configuration**: Modern approach using Java classes.
   - **Annotation Configuration**: Simplified approach using annotations like `@Component`, `@Configuration`, etc.

---

### **XML Configuration Tags**

These are the primary tags used for defining beans and their dependencies in an XML-based Spring configuration:

#### 1. `<bean>`  
   - Used to define and create a bean (object) in the Spring Container.  
   - Example:  
     ```xml
     <bean id="myBean" class="com.example.MyClass" />
     ```

#### 2. `<property>`  
   - Used for **Setter Injection**. It calls the setter method of the bean to inject data.  
   - Example:  
     ```xml
     <bean id="myBean" class="com.example.MyClass">
         <property name="propertyName" value="someValue" />
     </bean>
     ```

#### 3. `<constructor-arg>`  
   - Used for **Constructor Injection**. It injects data into the bean's constructor parameters.  
   - Example:  
     ```xml
     <bean id="myBean" class="com.example.MyClass">
         <constructor-arg value="someValue" />
     </bean>
     ```

#### 4. `<value>`  
   - Used to assign **Primitive Type Dependencies (PTD)**.  
   - Example:  
     ```xml
     <property name="id">
         <value>123</value>
     </property>
     ```

#### 5. `<list>, <set>, <map>, <props>`  
   - Used to define **Collection Type Dependencies (CTD)**.  
   - Examples:  
     - **List**:  
       ```xml
       <property name="models">
           <list>
               <value>Model1</value>
               <value>Model2</value>
           </list>
       </property>
       ```
     - **Set**:  
       ```xml
       <property name="colors">
           <set>
               <value>Red</value>
               <value>Blue</value>
           </set>
       </property>
       ```
     - **Map**:  
       ```xml
       <property name="details">
           <map>
               <entry key="key1" value="value1" />
               <entry key="key2" value="value2" />
           </map>
       </property>
       ```
     - **Properties**:  
       ```xml
       <property name="config">
           <props>
               <prop key="username">admin</prop>
               <prop key="password">1234</prop>
           </props>
       </property>
       ```

#### 6. `<ref>`  
   - Used to inject **Reference Type Dependencies (RTD)** by linking one bean to another.  
   - Example:  
     ```xml
     <bean id="vendorBean" class="com.example.Vendor" />
     <bean id="productBean" class="com.example.Product">
         <property name="vendor" ref="vendorBean" />
     </bean>
     ```

---

### **Creating a Spring Project in STS**

To create a Spring project using the **Spring Tool Suite (STS)** IDE:

1. Open STS and go to `File > New > Spring Starter Project`.
2. Click **Next**.
3. Search for the **Spring Web** dependency in the dependencies list and select it.
4. Click **Next**, and then **Finish**.  
   - This will create a new Spring Boot project with the necessary dependencies for building a web application.

---

### **STS Shortcuts**

These shortcuts can save time while working in STS:

| Shortcut           | Description                                                                 |
|--------------------|-----------------------------------------------------------------------------|
| **Ctrl + Shift + T** | Opens pre-defined Spring classes/interfaces (e.g., `ApplicationContext`). |
| **Ctrl + O**        | Shows all members (methods, fields) of the current class. Press again to include parent class members. |
| **Ctrl + L**        | Jumps to a specific line number in the current file.                      |
| **Ctrl + Shift + R**| Opens any class/file in the workspace or project.                         |

---

### **Summary**

1. **Spring Container**: Manages the lifecycle of beans (objects) and handles dependency injection (SI, CI).
2. **XML Configuration**: Tags like `<bean>`, `<property>`, `<constructor-arg>`, `<value>`, `<ref>` are used to define beans and their dependencies.
3. **STS IDE**: Provides an easy way to create and manage Spring projects with built-in tools and shortcuts.

This explanation should give you a clear understanding of Spring IoC, configuration, and practical use in STS. Let me know if you need further details!