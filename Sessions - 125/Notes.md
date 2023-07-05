# Let's go through each point and provide a detailed explanation for better understanding.

**1. When we add any `<dependency>` in `pom.xml`, it may add/download either a single dependency or a group of dependencies (multiple child jars):**
   In Apache Maven, the `pom.xml` file is used to manage dependencies for a project. When you add a `<dependency>` block to the `pom.xml`, Maven resolves and downloads the specified artifact(s) along with their transitive dependencies. This means that a single `<dependency>` declaration can result in the addition of multiple child jars if they are required to fulfill the dependencies of the declared artifact.

**2. Avoid/exclude one child dependency (jar) from the parent dependency chain is called Dependency Exclusion:**
   Dependency exclusion allows you to exclude specific child dependencies from being included when resolving the parent dependency. This can be useful when you want to avoid conflicts or unnecessary dependencies in your project. By excluding a child dependency, you remove it from the dependency chain, ensuring it is not downloaded or included in your project.

   In the provided example, the `<dependency>` block specifies the `org.springframework:spring-context:5.3.25` artifact. However, it includes an `<exclusions>` section, which contains an `<exclusion>` element to exclude the `org.springframework:spring-aop` dependency. This exclusion ensures that when Maven resolves the `spring-context` dependency, it will not include the `spring-aop` artifact as a transitive dependency.

   This exclusion mechanism allows you to fine-tune your project's dependencies and control the specific jars that are included in your project's classpath.

By understanding and utilizing the concept of dependency exclusion in Maven, you can manage and refine the dependencies of your project more effectively. It helps you avoid conflicts, unnecessary dependencies, and tailor the project's dependency chain according to your specific requirements.

```xml
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>5.3.25</version>
			<exclusions>
				<exclusion>
					<groupId>org.springframework</groupId>
					<artifactId>spring-aop</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
```

**Q) How can we move from Tomcat Server to any other server in Spring boot?**<br/>

1. How can we move from Tomcat Server to any other server in Spring Boot?
   In Spring Boot, you can switch the underlying server used in your application from Tomcat to another server (such as Jetty or Undertow) by leveraging the Maven Dependency Exclusion concept. This involves excluding the Tomcat dependency from the `spring-boot-starter-web` dependency and manually adding the desired server dependency.

2. First, we need to exclude the Tomcat dependency from the `spring-boot-starter-web` dependency:
   In the provided example, the `<dependency>` block includes the `org.springframework.boot:spring-boot-starter-web` artifact. To exclude the Tomcat server, an `<exclusions>` section is added within the `<dependency>` block. Inside the `<exclusions>` section, an `<exclusion>` element specifies the `org.springframework.boot:spring-boot-starter-tomcat` dependency to be excluded. This exclusion ensures that when Maven resolves the `spring-boot-starter-web` dependency, it will not include the Tomcat server as a transitive dependency.

3. Next, we need to add the desired server dependency manually:
   After excluding the Tomcat server, you can add the desired server dependency to your project. In the given example, a new `<dependency>` block is added for the `org.springframework.boot:spring-boot-starter-jetty` artifact. By including this dependency, Maven will download and include the Jetty server as a transitive dependency in your project.

By following these steps, you can effectively switch from using the Tomcat server to another server (such as Jetty) in your Spring Boot application. The Maven dependency exclusion mechanism allows you to control the dependencies and choose the server that best suits your needs.

This approach provides flexibility in selecting the underlying server, allowing you to adapt to specific requirements or take advantage of the features provided by different servers in your Spring Boot application.

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
```

<br/>
<br/>

# `Dependency Scopes` : When and which Jar is used?


**1. compile scope:**
   If no scope is specified, `compile` is the default scope. JARs with `compile` scope are required during both compilation and runtime. Framework dependencies, such as Hibernate Core and Spring Context, are typically specified with the `compile` scope. These dependencies are needed during compilation to resolve classes and interfaces and are also required at runtime.

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.4.32.Final</version>
    <scope>compile</scope>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.5</version>
    <scope>compile</scope>
</dependency>
```

**2. runtime scope:**
   JARs with `runtime` scope are required during runtime but not during compilation. Database dependencies, such as the MySQL connector, are often specified with the `runtime` scope. These dependencies are needed at runtime to establish a connection to the database or perform database-related operations.

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.26</version>
    <scope>runtime</scope>
</dependency>
```

**3. test scope:**
   JARs with `test` scope are required only during unit testing. Testing frameworks like JUnit, TestNG, and mocking frameworks are typically specified with the `test` scope. These dependencies are needed for writing and executing unit tests but are not required during the application's runtime.

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

**4. provided scope:**
   JARs with `provided` scope are expected to be provided by the target runtime environment, such as a server or container. Dependencies like the Servlet API (`javax.servlet-api`) or Lombok are often specified with the `provided` scope. These dependencies are not bundled with the application because they are already provided by the runtime environment.

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.20</version>
    <scope>provided</scope>
</dependency>
```

**5. system scope:**
   JARs with `system` scope are not retrieved from a remote repository but are instead taken from the local system. The JAR's path is specified using the `<systemPath>` element. This scope is rarely used and should be avoided if possible, as it bypasses Maven's dependency management. It allows you to specify JARs present in your local system drives that are not available in the Maven repository.

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>sample-library</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${basedir}/lib/sample-library.jar</systemPath>
</dependency>
```

By utilizing the appropriate dependency scopes in your Maven configuration, you can ensure that the required JARs are included in your project at the right stages of development and deployment.

<br/>
<br/>
<br/>

# WEB Application

To create a web application using Maven, follow these steps:

1. Open your preferred Integrated Development Environment (IDE) and navigate to "File" > "New" > "Maven" > "Maven Project".

2. In the Maven Project wizard, make sure the "Create a simple project (skip archetype selection)" checkbox is unchecked, and click "Next".

3. In the next screen, select "Internal" from the drop-down list, and then search for "web" in the search bar.

4. Choose the "maven-archetype-webapp" archetype from the list, and click "Next".

5. Enter the following details for the project:
   - GroupId: com.app
   - ArtifactId: FirstwebApp
   - Version: 1.0

6. Click "Next", review the project configuration, and click "Finish".

The IDE will create a new Maven project with the specified details and a basic web application structure. You can now start building your web application within this project.