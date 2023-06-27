Here's the requested format with separate code snippets, explanations, and a conclusion for the ActiveMQ setup, producer application, and consumer application.

**ActiveMQ Setup:**

1. Download ActiveMQ:
   Download the ActiveMQ 5 package from [https://activemq.apache.org/activemq-5016005-release](https://activemq.apache.org/activemq-5016005-release). Click on "apache-activemq-5.16.5-bin.zip" and extract it to a folder.

2. Run ActiveMQ:
   Go to the folder where you extracted ActiveMQ, navigate to the "bin\win64" directory, and run the "activemq.bat" script. Access the Admin Console using the URL [http://localhost:8161/admin/](http://localhost:8161/admin/) with the username "admin" and password "admin".

   Note: The ActiveMQ server runs on port 61616 for TCP communication and 8161 for the Admin Console using HTTP.

**Producer Application:**

Name: SpringCloudMqProducerEx
Dependency: ActiveMQ5

1. application.properties:
   Configure the connection to ActiveMQ by setting the broker URL, username, and password. Also, specify the communication type as Point-to-Point (P2P) and provide a destination name for the queue.
   ```properties
   spring.activemq.broker-url=tcp://localhost:61616
   spring.activemq.user=admin
   spring.activemq.password=admin
   spring.jms.pub-sub-domain=false
   my.app.desti-name=my-q-abc1
   ```

2. ProducerService:
   Create a service class that uses the JmsTemplate to send messages to the specified destination queue.
   ```java
   @Component
   public class ProducerService {

       @Autowired
       private JmsTemplate jt;

       @Value("${my.app.desti-name}")
       private String destination;

       public void sendMessage(String message) {
           jt.send(destination, session -> session.createTextMessage(message));
           System.out.println("MESSAGE SENT FROM PRODUCER " + message);
       }
   }
   ```

3. TestSenderRunner:
   Create a runner class or use the @Scheduled annotation to send messages at a scheduled interval using the ProducerService.
   ```java
   @Component
   public class TestSenderRunner {

       @Autowired
       private ProducerService service;

       @Scheduled(cron = "*/10 * * * * *")
       public void sendMsgTest() throws Exception {
           service.sendMessage("HELLO " + new Date());
       }
   }
   ```
   Make sure to annotate the main class with @EnableScheduling to enable the scheduling functionality.

**Consumer Application:**

Name: SpringCloudMqConsumerEx
Dependency: ActiveMQ5

1. application.properties:
   Configure the connection to ActiveMQ using the same broker URL, username, and password as the producer application. Set the communication type as P2P and provide the destination name for the queue.
   ```properties
   spring.activemq.broker-url=tcp://localhost:61616
   spring.activemq.user=admin
   spring.activemq.password=admin
   spring.jms.pub-sub-domain=false
   my.app.desti-name=my-q-abc1
   ```

2. ConsumerService:
   Create a service class with a JmsListener annotation that listens to messages from the specified destination queue.
   ```java
   @Component
   public class ConsumerService {

       @JmsListener(destination = "${my.app.desti-name}")
       public void readMsg(String message) {
           System.out.println(message);
       }
   }
   ```

**Execution Order:**

1. Start ActiveMQ by running the "activemq.bat" script.
2. Run the consumer and producer applications.

In this setup, the ActiveMQ server acts as the message broker between the producer and consumer applications. The producer application sends messages to the specified destination queue, and the consumer application listens to that queue and processes the received messages.

Please note that the code provided is a simplified example for demonstrating the usage of ActiveMQ with Spring applications. In a real-world scenario, you may need to handle exceptions, configure additional settings, and implement more complex logic based on your requirements.

