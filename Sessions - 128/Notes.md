# **Log4j 2.x**:

First, let's start with the code and configuration files:<br/>
Name: log4j2.xml

```xml
<Configuration>
	<Appenders>
		<Console .../> <!-- Configuration for Console Appender -->
		<File .../>    <!-- Configuration for File Appender -->
	</Appenders>
	<Loggers>
		<!-- Root Level - DEBUG/INFO/WARN... -->
	</Loggers>
</Configuration>
```

This is the configuration file for Log4j 2.x. It starts with the `<Configuration>` tag and contains two sections: `<Appenders>` and `<Loggers>`.

Under `<Appenders>`, you have two appenders defined:
- `<Console .../>`: This appender is configured to log messages to the console output. You can specify the layout for formatting log messages.
- `<File .../>`: This appender is configured to log messages to a file. You can specify the layout and the file path.

Under `<Loggers>`, you can define loggers for different packages or classes in your application. You can specify the log level for each logger, such as DEBUG, INFO, WARN, etc. The root logger represents the default logger for the entire application.

---

**Step#1: Create one simple Maven Project**

To create a Maven project, you need to follow these steps:
- Set the Group ID to `com.app.shivam`
- Set the Artifact ID to `LoggingApp`
- Set the Version to `1.0`

This step is crucial for setting up the project structure and dependencies.

---

**Step#2: Add in pom.xml**

In this step, you need to add the necessary dependencies to the project's `pom.xml` file. These dependencies include:

- `log4j-api`: The Log4j API dependency.
- `log4j-core`: The Log4j Core implementation dependency.
- `javax.mail`: Dependency for sending email notifications (optional).
- `commons-dbcp2`: Dependency for database connection pooling (optional).
- `mysql-connector-j`: Dependency for MySQL database connectivity (optional).
- `jackson-dataformat-xml`: Dependency for working with XML data format using Jackson library (optional).

By adding these dependencies to the `pom.xml`, Maven will automatically download and manage the required JAR files for your project.

---

**Step#3: Define log4j2.xml file under src/main/resources folder**

Create a file named `log4j2.xml` and place it under the `src/main/resources` folder of your project. This is the configuration file we discussed earlier. It will be automatically picked up by Log4j 2.x at runtime.

---

**Step#4: Define one test class**

```java
package com.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

	private static final Logger LOG = LogManager.getLogger(Test.class);
	
	public static void main(String[] args) {
		LOG.debug("FROM DEBUG");
		LOG.info("FROM INFO");
		LOG.warn("FROM WARN");
		LOG.error("FROM ERROR");
		LOG.fatal("FROM FATAL");
	}
}
```

In this step, you define a test class named `Test`. The class imports the necessary Log4j classes. It also declares a logger instance named `LOG` using `LogManager.getLogger(Test.class)`.

The `main` method demonstrates logging at different log levels:
- `LOG.debug()`: Logs a debug message.
- `LOG.info()`: Logs an informational message.
- `LOG.warn()`: Logs a warning message.
- `LOG.error()`: Logs an error message.
- `LOG.fatal()`: Logs a fatal message.

These log messages will be written to the appenders defined in the `log4j2.xml` configuration file.

---

**Conclusion:**

In this code and configuration setup, you have configured Log4j 2.x for your Maven project. The `log4j2.xml` file defines the appenders and loggers configuration. The Maven dependencies in the `pom.xml` ensure that the required Log4j libraries are available for your project.

In the `Test` class, you utilize the Log4j logger to log messages at different log levels. These messages will be output to the console and/or a log file based on the appender configurations in `log4j2.xml`.

By following these steps and understanding the code and configuration, you can easily integrate Log4j into your Maven project for effective logging and monitoring.

<br/>
<br/>

To write log messages to a database and send log messages via email using Log4j 2.x, you need to follow these steps:

**Step 1: Add MySQL Connector/J Dependency**
To interact with the MySQL database, you need to add the MySQL Connector/J dependency to your project's `pom.xml` file. Here's the dependency you need to include:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.31</version>
</dependency>
```

This dependency allows your application to connect to the MySQL database.

**Step 2: Create the EVENT_LOGS Table**
In your MySQL console, you need to create a table named `EVENT_LOGS`. This table will store the log messages. You can use the following SQL commands:

```sql
CREATE DATABASE boot;
USE boot;

CREATE TABLE EVENT_LOGS (
    ID varchar(50) primary key,
    DATE_TIME timestamp,
    CLASS varchar(100),
    LEVEL varchar(10),
    MESSAGE TEXT
);
```

This SQL script creates a database named `boot`, switches to that database, and creates the `EVENT_LOGS` table with columns for `ID`, `DATE_TIME`, `CLASS`, `LEVEL`, and `MESSAGE`.

**Step 3: Add SMTP Configuration for Email Appender**
To send log messages via email, you need to configure the SMTP appender in the `log4j2.xml` file. Here's an example SMTP appender configuration:

```xml
<SMTP name="LogToMail" subject="Error Log From Log4j" 
    from="javabyshivam@gmail.com" 
    to="javabyshivam@gmail.com" 
    smtpHost="smtp.mailgun.org" 
    smtpPort="587" 
    smtpUsername="____.mailgun.org" 
    smtpPassword="____" 
    bufferSize="100">
</SMTP>
```

In this configuration, you need to replace `from`, `to`, `smtpHost`, `smtpUsername`, and `smtpPassword` with your own email server details. The example uses Mailgun as the SMTP server with Gmail as the sender and recipient email addresses.

You also need to add the `javax.mail` dependency to your `pom.xml` file for sending emails. Here's the dependency:

```xml
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>javax.mail</artifactId>
    <version>1.6.2</version>
</dependency>
```

**Conclusion:**

By following these additional steps, you can enhance your Log4j 2.x setup to write log messages to a MySQL database and send log messages via email. The MySQL Connector/J dependency and the `EVENT_LOGS` table allow you to store log messages in a database for later analysis. The SMTP appender configuration and the `javax.mail` dependency enable you to send log messages via email for immediate notification.

Make sure to customize the SMTP appender configuration and provide the appropriate email server details to suit your specific setup.

With these enhancements, you can have more comprehensive logging capabilities in your application, ensuring that log messages are stored in a database and critical log events are immediately sent via email.

<br/>
<br/>
<br/>

# Here's the code for each file, followed by a detailed explanation:

**log4j2.xml**:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="INFO">
    <Appenders>
        <Console name="LogToConsole" target="SYSTEM_OUT">
            <PatternLayout>
                <Pattern>%d{ddMMMyy HHmmss} -- %C %M (%t) - [%p] : %L: %m %n </Pattern>
            </PatternLayout>
        </Console>
        <File name="LogToFile" fileName="logs/myapp.log">
            <PatternLayout>
                <Pattern>%d{dd/MMM/yy--HHmmss} -- %C %M (%p)%L~%m %n</Pattern>
            </PatternLayout>
        </File>

        <RollingFile name="LogToRollingFile" fileName="logs/app-info.html" filePattern="logs/app-info-%d{yyyy-MM-dd}.html.gz">
            <!--<XMLLayout />-->
            <HTMLLayout charset="UTF-8" title="HTML LOGS" locationInfo="true" />
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
                <SizeBasedTriggeringPolicy size="1 KB" />
            </Policies>
        </RollingFile>

        <JDBC name="LogToDb" tableName="EVENT_LOGS">
            <ConnectionFactory class="com.app.shivam.LogsStoreConnectionFactory" method="getConnection" />
            <Column name="ID" pattern="%u" />
            <Column name="DATE_TIME" isEventTimestamp="true" />
            <Column name="CLASS" pattern="%logger" />
            <Column name="LEVEL" pattern="%level" />
            <Column name="MESSAGE" pattern="%message" />
        </JDBC>
        <SMTP name="LogToMail" subject="Error Log From Log4j" 
            from="javabyshivam@gmail.com" to="javabyshivam@gmail.com" 
            smtpHost="smtp.mailgun.org" smtpPort="587" 
            smtpUsername="postmaster@sandbox79ddd5dc958545dbaae485b2ae467b96.mailgun.org" 
            smtpPassword="e197b1c631f3ac37aab4207b0111f86b-2de3d545-3c77c8bf" 
            bufferSize="100">
        </SMTP>
    </Appenders>
    <Loggers>
        <Logger level="debug" name="com.app.shivam" additivity="false">
            <AppenderRef ref="LogToRollingFile" />
            <AppenderRef ref="LogToConsole" />
            <AppenderRef ref="LogToFile" />
            <AppenderRef ref="LogToDb" />
            <AppenderRef ref="LogToMail" />
        </Logger>
        <Root level="trace">
            <AppenderRef ref="LogToConsole" />
        </Root>
    </Loggers>
</Configuration>
```

**Explanation - log4j2.xml**:
The `log4j2.xml` file is the configuration file for Log4j 2.x. It specifies various appenders, loggers, and their configurations. Let's break down the code:

- `<Configuration status="INFO">`: This line indicates the configuration status, and in this case, it is set to `INFO`. This means that the log4j2 configuration itself will log at the `INFO` level.
  
- `<Appenders>`: This section defines the different appenders used for logging.
  - `<Console>`: This appender logs messages to the console output. The `name` attribute is set to "LogToConsole," and the `target` attribute is set to "SYSTEM_OUT" to indicate logging to the standard output stream. The `PatternLayout` element specifies the pattern of the log messages displayed on the console.
  - `<File>`: This appender logs messages to a file specified by the `fileName` attribute. The `PatternLayout` element defines the pattern for the log messages written to the file.
  - `<RollingFile>`: This appender logs messages to a rolling file. The `fileName` attribute specifies the initial file name, and the `filePattern` attribute defines the pattern for the rolled log files. The `<HTMLLayout>` element specifies that the log messages should be formatted as HTML. The `<Policies>` section configures the triggering policies for rolling the log files based on time and size.
  - `<JDBC>`: This appender logs messages to a database table named "EVENT_LOGS." The `ConnectionFactory` element specifies the custom connection factory class (`com.app.shivam.LogsStoreConnectionFactory`) and its method to establish a database connection. The `<Column>` elements define the mapping between log event data and database columns.
  - `<SMTP>`: This appender sends log messages via email. It specifies the SMTP server details, email subject, sender, recipient, and other relevant information.

- `<Loggers>`: This section defines the loggers and their configurations.
  - `<Logger level="debug" name="com.app.shivam" additivity="false">`: This logger is specifically configured for the `com.app.shivam` package. The `level` attribute is set to `debug`, which means that log events with a level of `debug` and above will be logged. The `additivity` attribute is set to `false` to prevent log events from being propagated to parent loggers.
  - `<AppenderRef>`: These elements reference the appenders defined earlier and specify which appenders should be associated with the logger.
  - `<Root level="trace">`: This is the root logger, which acts as the default logger for the entire application. The `level` attribute is set to `trace`, meaning that log events with any level will be logged. The `<AppenderRef>` element refers to the console appender, indicating that log events should be logged to the console by default.

This configuration sets up various logging destinations, formats log messages, and specifies which log events should be logged and where.

**LogsStoreConnectionFactory.java**:
```java
package com.app.shivam;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class LogsStoreConnectionFactory {

    private static BasicDataSource dataSource;

    private LogsStoreConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/boot");
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("Shivam@123");
        }
        return dataSource.getConnection();
    }
}
```

**Explanation - LogsStoreConnectionFactory.java**:
The `LogsStoreConnectionFactory` class provides a method for obtaining a connection to the database using Apache Commons DBCP2. Here's a breakdown of the code:

- The class is marked as `public` and belongs to the `com.app.shivam` package.
- The class has a private constructor to prevent instantiation, as it provides only static methods.
- The `getConnection` method is marked as `public` and returns a `Connection` object. It throws a `SQLException`.
- Inside the `getConnection` method, a check is made to see if the `dataSource` instance is null. If it is null, a new `BasicDataSource` object is created.
- The `setUrl` method is called to set the URL of the MySQL database connection.
- The `setDriverClassName` method sets the driver class for MySQL.
- The `setUsername` and `setPassword` methods are used to provide the credentials for connecting to the database.
- Finally, the `getConnection` method of the `dataSource` object is called to obtain a connection to the database, which is then returned.

This class encapsulates the logic for establishing a database connection using Apache Commons DBCP2.

**Schema.sql**:
```sql
create table EVENT_LOGS (
    ID varchar(50) primary key,
    DATE_TIME timestamp,
    CLASS varchar(100),
    LEVEL varchar(10),
    MESSAGE TEXT
);
```

**Explanation - Schema.sql**:
The `Schema.sql` file contains an SQL script to create the `EVENT_LOGS` table in the database. Here's an explanation:

- The `create table` statement creates a table named `EVENT_LOGS`.
- The table has five columns: `ID`, `DATE_TIME`, `CLASS`, `LEVEL`, and `MESSAGE`.
- The `ID` column is defined as `varchar(50)` and is set as the primary key.
- The `DATE_TIME` column is of the `timestamp` data type.
- The `CLASS` column is defined as `varchar(100)`.
- The `LEVEL` column is defined as `varchar(10)`.
- The `MESSAGE` column is of the `TEXT` data type.

This table will be used to store the log messages generated by the application.

**Test.java**:
```java
package com.app.shivam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

    private static final Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {

        logger.trace("FROM TRACE");
        logger.debug("FROM DEBUG");
        try {
            System.out.println("DONE");
            logger.info("FROM INFO");
            logger.warn("FROM WARN");
            System.out.println(getData());
        } catch (IllegalArgumentException e) {
            logger.error("FROM ERROR");
            logger.error("{}", e.getMessage());
        }
        logger.fatal("FROM FATAL");
    }

    private static int getData() throws IllegalArgumentException {
        throw new IllegalArgumentException("Sorry IllegalArgumentException!");
    }
}
```

**Explanation - Test.java**:
The `Test` class is a demonstration of how the configured logging can be used in your application. Here's an explanation of the code:

- The class is marked as `public` and belongs to the `com.app.shivam` package.
- The class imports the necessary Log4j classes and defines a logger instance named `logger` for the class, using `LogManager.getLogger(Test.class)`.
- The `main` method is the entry point of the application.
- Various log statements are made at different log levels:
  - `logger.trace("FROM TRACE")`: Logs a message at the `trace` level.
  - `logger.debug("FROM DEBUG")`: Logs a message at the `debug` level.
  - `logger.info("FROM INFO")`: Logs a message at the `info` level.
  - `logger.warn("FROM WARN")`: Logs a message at the `warn` level.
- An exception is intentionally thrown from the `getData` method using `throw new IllegalArgumentException("Sorry IllegalArgumentException!")`.
- The exception is caught in a `catch` block, and Log4j is used to log the error message.
  - `logger.error("FROM ERROR")`: Logs a message at the `error` level.
  - `logger.error("{}", e.getMessage())`: Logs the exception message using parameterized logging.
- Finally, `logger.fatal("FROM FATAL")` logs a message at the `fatal` level.

This class showcases the usage of the configured logging in your application, allowing you to log messages at different levels and handle exceptions.

**Conclusion**:
In conclusion, the provided code and configurations demonstrate the setup and usage of Log4j 2.x for logging in your application. The `log4j2.xml` file defines appenders and loggers, specifying logging destinations, log levels, and log message formats. The `LogsStoreConnectionFactory` class establishes a database connection using Apache Commons DBCP2. The `Schema.sql` file contains an SQL script to create the necessary database table. The `Test` class showcases the usage of the logging setup with log statements at different levels and exception handling.

By understanding and implementing these code snippets and configurations, you can effectively configure logging in your application, log messages to various destinations, and control log levels and formats according to your requirements.