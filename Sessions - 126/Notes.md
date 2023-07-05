
**1. Apache Tomcat:**
   - Download and Configure: Download the Apache Tomcat version 9.0.72 from the official website. Extract the downloaded file to a suitable location on your system.
   - Set Path and JAVA_HOME: Set the `PATH` environment variable to include the Tomcat bin directory and set the `JAVA_HOME` environment variable to the location of your Java installation.
   - Startup Tomcat: Navigate to the Tomcat folder and then to the "bin" directory. Run the `startup.bat` file to start the Tomcat server.
   - Access Tomcat: Open a web browser and enter the URL `http://localhost:8080/` to access the Tomcat server. This URL will display the default Tomcat homepage if the server is running correctly.

**2. Web Application:**
   - Create Maven Project: In your IDE, go to "File" > "New" > "Maven" > "Maven Project". Ensure that the "Create a simple project (skip archetype selection)" checkbox is unchecked.
   - Select Web Archetype: Choose the internal Maven archetype named "maven-archetype-webapp" from the dropdown list.
   - Enter Project Details: Enter the GroupId, ArtifactId, and Version for your web application.
   - Finish: Click "Finish" to create the Maven project with the specified details. The IDE will generate a basic web application structure.

**3. WAR Plugin:**
   - Configure Maven WAR Plugin: Open the `pom.xml` file of your Maven project. Add the following configuration for the Maven WAR Plugin within the `<plugins>` section:

```xml
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.3.1</version>
    </plugin>
</plugins>
```

**4. Configure Server in IDE for API Access:**
   - In your IDE, go to "Window" > "Preferences" > "Servers" > "Runtime Environments".
   - Add Apache Tomcat: Click "Add" and select "Apache" > "Tomcat 9". Specify the location of the Tomcat installation on your system and click "Next" and then "Finish".
   - Apply and Close: Click "Apply and Close" to save the server configuration.

**5. Add Server to Project:**
   - Right-click on your project and go to "Build Path" > "Configure Build Path".
   - Go to the "Libraries" tab and click "Add Library".
   - Select "Server Runtime" and choose "Apache Tomcat". Click "Finish".

**6. Remove DTD Lines from web.xml:**
   - Open the `web.xml` file located in the `WEB-INF` directory of your project.
   - Remove the top three lines of the file, which reference the DTD (Document Type Definition).

**7. Run Maven Build:**
   - Right-click on your project and go to "Run As" > "Maven Build".
   - In the "Goals" field, enter "clean package" and click "Apply" and then "Run".
   - This will build your Maven project, generate the WAR file, and package it with the necessary resources.

**8. Deploy the Web Application:**
   - Refresh your project to ensure the newly generated WAR file is visible.
   - Copy the WAR file from the `target` directory of your project.
   - Paste the WAR file inside the `webapps` directory of your Tomcat installation.
   - Start or restart Tomcat server.
   - Access the Web Application: Open a web browser and enter the URL `http://localhost:8080/FirstwebApp/` to access your deployed web application.

By following these steps, you will be able to download and configure Apache Tomcat, create a web application using Maven, configure the server in your IDE, build the project, and deploy it to Tomcat.

<br/>
<br/>

**1. Parent Maven Project (Parent Project) type: pom:**
   - By creating a Maven project with the type "pom," we can define a parent project that can be used by multiple child projects.
   - The parent project acts as a central point for managing common configurations, dependencies, and plugins shared among the child projects.
   - It allows for efficient dependency management and promotes consistency across multiple projects.

**2. Creating the Parent Project:**
   - In your IDE, go to "File" > "New" > "Maven" > "Maven Project."
   - Select "Create a simple project (skip archetype selection)" and click "Next."
   - Enter the project details, such as GroupId and ArtifactId. Set the packaging type as "pom."
   - Click "Finish" to create the parent project.

**3. Specifying Dependencies in Parent Project:**
   - Open the `pom.xml` file of the parent project.
   - Use the `<dependencyManagement>` tag to define dependencies and their versions. This section is used for dependency version management.
   - Specify the dependencies within the `<dependencies>` section under `<dependencyManagement>`, using the `<dependency>` tags.
   - Include the `groupId`, `artifactId`, and `version` for each dependency.

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.25</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

**4. Creating the Child Project:**
   - In your IDE, go to "File" > "New" > "Maven" > "Maven Project."
   - Select "Create a simple project (skip archetype selection)" and click "Next."
   - Enter the project details for the child project.
   - Specify the parent project details within the `<parent>` tag in the child project's `pom.xml`.

```xml
<parent>
    <groupId>___</groupId>
    <artifactId>___</artifactId>
    <version>__</version>
</parent>
```

**5. Accessing Parent Project Dependencies:**
   - By specifying the parent project in the child project's `pom.xml`, you inherit the dependencies defined in the parent project.
   - In the child project's `pom.xml`, you can directly declare the dependencies without specifying the version. The version will be inherited from the parent project's `<dependencyManagement>` section.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
    </dependency>
</dependencies>
```

By following these steps, you can create a parent Maven project of type "pom" to manage shared configurations, dependencies, and plugins. Child projects can then inherit and utilize the dependencies and configurations defined in the parent project. This approach promotes consistency and simplifies dependency management across multiple projects.