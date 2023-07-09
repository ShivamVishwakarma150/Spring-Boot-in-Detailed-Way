## **`Elasticsearch:`**
Elasticsearch is a highly scalable and distributed NoSQL database that is built on top of the Lucene search engine. It is designed for storing, searching, and analyzing large volumes of data in near real-time. Elasticsearch uses a document-oriented approach, where data is stored as JSON documents and organized into indices. Here are some key points about Elasticsearch:

- **Full-Text Search:** Elasticsearch provides powerful full-text search capabilities, allowing you to search and retrieve relevant documents based on keywords, phrases, or complex queries.
- **Distributed and Scalable:** Elasticsearch is designed to be distributed across multiple nodes, enabling horizontal scalability and high availability. It automatically distributes data and queries across the nodes, providing efficient data processing and fault tolerance.
- **Near Real-Time:** Elasticsearch offers near real-time search and analytics, meaning that data is indexed and made available for searching within a short time frame (usually milliseconds).
- **RESTful API:** Elasticsearch provides a RESTful API that allows you to interact with the database, perform CRUD operations, execute searches, and manage the cluster and indices.
- **Aggregations:** Elasticsearch supports aggregations, which allow you to perform complex analytics on your data, including metrics, statistical calculations, and grouping operations.

## **`Logstash`:**
Logstash is a flexible and extensible log pipeline tool used for collecting, transforming, and enriching data from various sources. It acts as a data processing engine, enabling you to ingest data from diverse inputs, apply filters and transformations, and send the processed data to multiple destinations. Key features of Logstash include:

- **Data Collection:** Logstash accepts data inputs from various sources such as log files, databases, message queues, and network streams. It supports numerous input plugins that enable you to fetch data from different systems.
- **Data Transformation:** Logstash allows you to apply filters and transformations to the incoming data. Filters can parse and structure the data, remove or modify fields, enrich the data with additional information, and perform operations like geolocation or anonymization.
- **Extensible Plugin Ecosystem:** Logstash has a vast plugin ecosystem, offering a wide range of input, filter, and output plugins. These plugins allow you to customize Logstash's behavior, integrate with different systems, and extend its functionality according to your specific requirements.
- **Pipeline Configuration:** Logstash uses a pipeline configuration file to define the sequence of inputs, filters, and outputs in a data processing pipeline. The configuration file is written in a simple and human-readable syntax.
- **Integration with Elasticsearch:** Logstash integrates seamlessly with Elasticsearch, allowing you to send the processed data directly to Elasticsearch for indexing and analysis.

## **`Kibana:`**
Kibana is a web-based visualization and exploration tool that sits on top of Elasticsearch. It provides a user-friendly interface for searching, analyzing, and visualizing the data stored in Elasticsearch indices. Key features of Kibana include:

- **Data Exploration:** Kibana allows you to explore and navigate through the data indexed in Elasticsearch using a query language called Elasticsearch Query DSL. You can execute searches, apply filters, and drill down into specific data subsets.
- **Visualizations:** Kibana provides a wide range of visualization options, such as bar charts, line charts, pie charts, maps, and more. You can create interactive visualizations to gain insights and understand patterns in your data.
- **Dashboards:** Kibana allows you to build dashboards by combining multiple visualizations, saved searches, and other elements into a single view. Dashboards provide a consolidated overview of your data and enable you to monitor key metrics and trends.
- **Real-Time Monitoring:** Kibana offers real-time monitoring capabilities, allowing you to create visualizations and alerts based on real-time data streams. You can track metrics, monitor logs, and set up alerts for specific conditions or thresholds.
- **Security and Access Control:** Kibana provides security features to control user access, manage roles, and secure the data and visualizations. You can authenticate users, define roles with granular permissions, and restrict access to specific indices or dashboards.

By integrating Elasticsearch, Logstash, and Kibana, you can establish a powerful log management and analysis system. Logstash collects and processes logs from various sources, Elasticsearch indexes and stores the data, and Kibana provides an intuitive interface for searching, visualizing, and analyzing the logs. This centralized logging solution enables you to efficiently search, monitor, and gain valuable insights from your application logs.

<br/>
<br/>
<br/>


**ElkDemoController:**

```java
package com.app.shivam.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElkDemoController {
	
	private static final Logger logger = LogManager.getLogger(ElkDemoController.class);

	@GetMapping(path = "/welcome")
	public ResponseEntity<String> welcome() {
		logger.debug("Welcome to ELK demo service");
		return ResponseEntity.ok("Hello ELK Integration!!!");
	}

	@GetMapping(path = "/users/{name}")
	public ResponseEntity<String> getUserByName(@PathVariable String name) {
		if (name.equals("ADMIN")) {
			logger.debug("Access by ADMIN triggered");
			return ResponseEntity.ok("Access Granted to " + name);
		} else {
			logger.error("Access denied for: " + name);
			return new ResponseEntity<>("Access Denied for " + name, HttpStatus.BAD_REQUEST);
		}
	}
}
```

**Explanation:**
The `ElkDemoController` is a Spring Boot controller class that contains two REST endpoints. The `welcome()` method handles a GET request to `/welcome` and returns a response with the message "Hello ELK Integration!!!". The `getUserByName()` method handles a GET request to `/users/{name}` and checks if the provided name is "ADMIN". If it is, the method logs a debug message and returns an "Access Granted" response. Otherwise, it logs an error message and returns an "Access Denied" response with a `HttpStatus.BAD_REQUEST` status.

**Logging Configuration (log4j2.xml):**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="DEBUG">
	<Properties>
		<Property name="LOG_PATTERN">
            [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n
        </Property>
		<Property name="BASE_PATH">D:/elk/logs</Property>
	</Properties>
	<Appenders>
		<Console name="ConsoleAppender" target="SYSTEM_OUT" follow="true">
			<PatternLayout pattern="${LOG_PATTERN}" />
		</Console>
		<RollingFile name="FileAppender" fileName="${BASE_PATH}/elkdemo.log" filePattern="${BASE_PATH}/elkdemo-%d{yyyy-MM-dd}.log">
			<PatternLayout>
				<Pattern>${LOG_PATTERN}</Pattern>
			</PatternLayout>
			<Policies>
				<SizeBasedTriggeringPolicy size="10MB" />
			</Policies>
			<DefaultRolloverStrategy max="10" />
		</RollingFile>
	</Appenders>
	<Loggers>
		<Logger name="com.app.shivam" level="DEBUG" additivity="false">
			<AppenderRef ref="FileAppender" />
			<AppenderRef ref="ConsoleAppender" />
		</Logger>
		<Root level="DEBUG">
			<AppenderRef ref="FileAppender" />
		</Root>
	</Loggers>
</Configuration>
```

**Explanation:**
The logging configuration file `log4j2.xml` defines the log patterns, file paths, and log appenders for the application. It specifies two appenders: `ConsoleAppender`, which logs messages to the console, and `FileAppender`, which logs messages to a rolling log file. The log file is created with a base path of `D:/elk/logs/elkdemo.log`, and when the file size reaches 10MB, a new log file is created with a timestamp appended to the name. The loggers section defines the logging behavior for the `com.app.shivam` package and sets the logging level to DEBUG.

**pom.xml:**
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<exclusions>
		<exclusion>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

**Explanation:**
The `pom.xml` file contains the dependencies required for the Spring Boot application. The first dependency excludes the default logging framework provided by Spring Boot (`spring-boot-starter-logging`). By excluding this, we can use Log4j2 for logging. The second dependency (`spring-boot-starter-log4j2`) includes the Log4j2 framework in the application.

**Logging Configuration with Logstash (logstash.conf):**
```
input {
  file {
    path => "D:/elk/logs/elkdemo.log"
    start_position => "beginning"
  }
}
output {
  stdout {
    codec => rubydebug
  }
  elasticsearch {
    hosts => ["localhost:9200"]
    index => "elkdemo"
  }
}
```

**Explanation:**
The `logstash.conf` file is the Logstash configuration file that specifies the input and output plugins for Logstash. In this configuration, the `file` input plugin is used to read logs from the `D:/elk/logs/elkdemo.log` file. The `start_position` parameter is set to "beginning" to read the entire log file from the beginning. The `stdout` output plugin logs the processed events to the console using the `rubydebug` codec. The `elasticsearch` output plugin sends the processed events to Elasticsearch, which is running on `localhost` at port `9200`. The index name for the events is set to "elkdemo".

**Execution Order:**
1. Start the Spring Boot application and access the following URLs to test the application:
   - http://localhost:8080/welcome
   - http://localhost:8080/users/sam

2. Start Elasticsearch:
   - Navigate to the Elasticsearch installation directory and run `elasticsearch.bat`.

3. Start Kibana:
   - Open the Kibana configuration file at `D:/ELK/kibana-7.14.1-windows-x86_64/config/kibana.yml`.
   - Uncomment the line `elasticsearch.hosts: ["http://localhost:9200"]`.
   - Run `kibana.bat` from the Kibana installation directory.
   - Access Kibana at http://localhost:5601/.

4. Configure and Start Logstash:
   - Create a file named `logstash.conf` with the provided Logstash configuration.
   - Start Logstash by running `logstash.bat -f ../config/logstash.conf` from the Logstash installation directory.

5. Configure Index Pattern in Kibana:
   - Go to Management > Stack Management > Kibana > Index Patterns page in the Kibana UI.
   - Create an index pattern for "elkdemo".
   - Go to the "Discover" page in the Kib

**Conclusion:**
In this setup, we have a Spring Boot application with logging implemented using Log4j2. The logs generated by the application are stored in a log file specified in the `log4j2.xml` configuration file. The Logstash tool is used to collect and process these logs. It reads the log file, applies any necessary transformations, and sends the processed logs to Elasticsearch.

Elasticsearch indexes the logs and stores them in a scalable and distributed manner. It allows for efficient searching and analysis of the logs. Kibana, the visualization and exploration tool, sits on top of Elasticsearch and provides a user-friendly interface to search, analyze, and visualize the logged data. With Kibana, you can create dashboards, perform real-time monitoring, and gain valuable insights from the logs.

By integrating Spring Boot, Log4j2, Elasticsearch, Logstash, and Kibana, you can achieve centralized logging and efficient log analysis for your application. This setup enables you to monitor application behavior, troubleshoot issues, and extract valuable information from the logs to improve your application's performance and reliability.