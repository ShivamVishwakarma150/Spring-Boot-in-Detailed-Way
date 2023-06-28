**Spring Boot + Apache Kafka Integration Example**

In order to integrate Apache Kafka with a Spring Boot application, you need to follow certain steps. Let's go through each point mentioned and provide a detailed explanation:

1. **Add Kafka API using Spring Boot in pom.xml:**
To use the Spring Kafka library in your Spring Boot project, you need to add the following dependency in your `pom.xml` file:

```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

This dependency ensures that the necessary Kafka-related classes and functionality are available in your Spring Boot application.

2. **EcoSystem creates a connection with the Producer application:**
In this context, "EcoSystem" refers to the Spring Boot application that acts as the Kafka producer. The EcoSystem application establishes a connection with the Kafka broker by providing certain properties. These properties include the address of the Kafka broker, serialization/deserialization settings, and other configuration options. The connection allows the EcoSystem to send data to Kafka using the `KafkaTemplate<K,V>` class provided by the Spring Kafka library.

3. **If a consumer is also connected with EcoSystem:**
If there is a consumer application connected to the same Kafka ecosystem, it can consume messages from the topics. When a consumer is connected, a Message Broker is assigned to read data from the topic. The Message Broker handles the coordination and distribution of messages among the consumers in a consumer group. Additionally, the Kafka ecosystem utilizes message replication, creating multiple copies (replicas) of the actual message to ensure fault tolerance and availability.

For example, if there are four consumers in a consumer group, a Message Broker will receive the messages from the topic and distribute them to the consumers in the group. Each message is replicated (in this case, four copies) to ensure that multiple consumers can process the messages independently.

The `@KafkaListener` annotation is used in the consumer application to specify the topic name and the consumer group ID. By using this annotation, the consumer can read data from the Kafka ecosystem.

4. **I'm using RestController and MessageStore additionally:**
To demonstrate the integration, the Spring Boot application uses a `RestController` to expose REST endpoints. This allows you to send data to the Kafka ecosystem by making HTTP requests to these endpoints.

The `MessageStore` (assuming it's a custom class) is likely used to store the messages received from the Kafka ecosystem. It could be a data structure or a persistent storage mechanism where the application can store and retrieve messages as needed.

By combining the `RestController`, `MessageStore`, and Kafka integration, you can send data to the Kafka ecosystem through the REST API, have it processed by the Kafka consumers, and store the received messages in the `MessageStore`.

In summary, the points mentioned describe the integration of Apache Kafka with a Spring Boot application. The Spring Kafka library provides the necessary APIs and functionality to establish a connection with Kafka and enables the exchange of messages between producers and consumers. The `@KafkaListener` annotation and the `KafkaTemplate<K,V>` class play a crucial role in consuming and producing messages, respectively. The RestController and MessageStore are additional components used to facilitate data sending and storage in the context of the integration example.

<br/>
<br/>

# Here is the code with separate explanations for each class:

**1. application.properties**

```properties
# Server port
server.port=8686

# Producer properties
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

# Consumer properties
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.group-id=abcd

# TopicName
my.topic.name=TEST-SAMPLE

# Database Properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/boot
spring.datasource.username=root
spring.datasource.password=Shivam@123

# JPA Properties
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=create
```

The `application.properties` file contains various configurations for the Spring Boot application, including server port, Kafka producer and consumer properties, topic name, database properties, and JPA properties.

**2. Entity**

```java
package com.app.shivam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="stocktab")
public class StockInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sid")
	private Integer stkId;
	
	@Column(name="scode")
	private String stkCode;
	
	@Column(name="scost")
	private Double stkCost;
}
```

The `StockInfo` class represents the entity that will be stored in the database. It uses Lombok annotations (`@Data`) to automatically generate getters, setters, and other common methods. The class is annotated with JPA annotations to define the mapping between the entity and the corresponding database table.

**3. Repository**

```java
package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.StockInfo;

public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {

}
```

The `StockInfoRepository` interface extends the `JpaRepository` interface, which provides basic CRUD operations for the `StockInfo` entity. It allows you to perform database operations without writing explicit queries.

**4. JSONUTIL**

```java
package com.app.shivam.util;

import com.app.shivam.entity.StockInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static StockInfo convertToObj(String message) {
		try {
			return new ObjectMapper().readValue(message, StockInfo.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertToString(StockInfo si) {
		try {
			return new ObjectMapper().writeValueAsString(si);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
```

The `JsonUtil` class provides utility methods to convert between JSON strings and `StockInfo` objects. It uses the `ObjectMapper` class from the Jackson library to perform the serialization and deserialization.

**5. MessageStore**

```java
package com.app.shivam.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.StockInfo;
import com

.app.shivam.repo.StockInfoRepository;
import com.app.shivam.util.JsonUtil;

@Component
public class MessageStore {
	
	@Autowired
	private StockInfoRepository repo;

	public void add(String message) {
		StockInfo si = JsonUtil.convertToObj(message);
		repo.save(si);
	}

	public List<StockInfo> getAll() {
		return repo.findAll();
	}
}
```

The `MessageStore` class is a component responsible for storing messages received from the Kafka consumer into the database. It uses the `StockInfoRepository` to save and retrieve `StockInfo` objects. The `add` method converts the JSON message to a `StockInfo` object using `JsonUtil` and then saves it in the database. The `getAll` method retrieves all `StockInfo` objects from the database.

**6. ConsumerService**

```java
package com.app.shivam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.app.shivam.db.MessageStore;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerService {
	
	@Autowired
	private MessageStore store;

	@KafkaListener(topics = "${my.topic.name}", groupId = "abcd")
	public void readMessage(String message) {
		log.info("MESSAGE AT CONSUMER: {}", message);
		store.add(message);
	}
}
```

The `ConsumerService` class is a Spring component that acts as a Kafka consumer. It uses the `@KafkaListener` annotation to specify the topic name and consumer group ID. When a message is received from the Kafka topic, the `readMessage` method is invoked. It logs the received message and delegates the responsibility of storing the message to the `MessageStore` component.

**7. ProducerService**

```java
package com.app.shivam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProducerService {

	@Autowired
	private KafkaTemplate<String, String> template;
	
	@Value("${my.topic.name}")
	private String topicName;
	
	public void sendMessage(String message) {
		log.info("MESSAGE IS AT PRODUCER SERVICE");
		template.send(topicName, message);
	}
}
```

The `ProducerService` class is a Spring component responsible for sending messages to the Kafka topic. It uses the `KafkaTemplate` provided by Spring Kafka to send messages. The `sendMessage` method accepts a message and sends it to the configured topic using the `template.send` method.

**8. RestController**

```java
package com.app.shivam.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.db.MessageStore;
import com.app.shivam.entity.StockInfo;
import com.app.shivam.service.ProducerService;
import com.app.shivam.util.JsonUtil;

@RestController
@RequestMapping("/api/v1/kafka")
public class StockRestController {
	
	@Autowired
	private ProducerService service;
	
	@Autowired
	private MessageStore store;

	@GetMapping("/send")
	public String readMessage(
			@RequestParam String code,
			@RequestParam Double cost
			) 
	{
		StockInfo si = new StockInfo();
		si.setStkCode(code);
		si.setStkCost(cost);
		
		String message = JsonUtil.convertToString(si);
		
		service

.sendMessage(message);
		
		return "SENT";
	}
	
	@GetMapping("/all")
	public List<StockInfo> fetchAll() {
		return store.getAll();
	}
}
```

The `StockRestController` class is a Spring RestController that exposes REST endpoints for sending messages and retrieving all stored messages. The `/send` endpoint accepts code and cost as request parameters, creates a `StockInfo` object, converts it to a JSON message using `JsonUtil`, and then calls the `sendMessage` method of `ProducerService` to send the message to the Kafka topic. The `/all` endpoint retrieves all stored `StockInfo` objects from the `MessageStore` component and returns them as a response.

**Execution Order:**

To run the application and test the functionality, follow these steps in order:

1. Start ZooKeeper server: Run the following command in the command prompt:
   ```
   .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
   ```

2. Start Kafka server: Run the following command in the command prompt:
   ```
   .\bin\windows\kafka-server-start.bat .\config\server.properties
   ```

3. Run the Spring Boot application `SpringBootKafkaServiceEx`.

4. Access the following URLs to interact with the application:

   - Sending a message:
     ```
     http://localhost:8686/api/v1/kafka/send?code=A&cost=50.0
     ```
     This will send a message with stock code "A" and cost 50.0 to the Kafka topic.

   - Fetching all messages:
     ```
     http://localhost:8686/api/v1/kafka/all
     ```
     This will retrieve all the stored stock information messages from the database.

**Conclusion:**

The provided code demonstrates the integration of Spring Boot with Apache Kafka. It includes a Kafka producer (`ProducerService`) that sends messages to a Kafka topic and a Kafka consumer (`ConsumerService`) that listens to the topic, reads the messages, and stores them in a database using the `MessageStore` component. The `StockRestController` exposes REST endpoints to send messages and retrieve stored messages. The application leverages Spring Kafka and Spring Data JPA for Kafka integration and database operations, respectively.