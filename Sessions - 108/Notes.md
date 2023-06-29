# Spring Boot Reactive Programming

Sure, here is a detailed explanation of the points you mentioned regarding Spring Boot Reactive Programming:

**Spring Boot Web (MVC and REST):**
- Spring Boot provides a variety of starters, including `spring-boot-starter-web`, which enables building web applications.
- Annotations like `@Controller`, `@RestController`, `@RequestMapping`, `@GetMapping`, etc., are used to define endpoints and handle HTTP requests.
- The `@ModelAttribute` annotation is used to bind request parameters to model objects, and the `Model` class is used to pass data to views.

**Server (Tomcat, Undertow, Jetty):**
- Spring Boot allows you to choose different embedded servers such as Tomcat, Undertow, or Jetty to run your web application.
- By default, Tomcat is used as the embedded server in Spring Boot applications.
- The server is responsible for allocating threads to process incoming requests.

**Blocking Model:**
- In the blocking model, each thread allocated by the server is dedicated to processing a single request at a time.
- If a thread is waiting for a blocking I/O operation (e.g., reading from a file or a database), it remains idle and cannot be used to process other requests.
- This can result in inefficient resource utilization when there are a large number of concurrent requests.

**Non-Blocking Model:**
- The non-blocking model aims to maximize the utilization of server threads by avoiding thread idle time.
- When a thread encounters a non-blocking I/O operation (e.g., reading from a non-blocking socket), it can be freed to handle other requests while waiting for the I/O operation to complete.
- Once the I/O operation is finished, the same thread or a different one can continue processing the response.

**Spring Boot WebFlux:**
- Spring Boot WebFlux is a reactive programming model provided by Spring 5.x.
- It is designed to handle a large number of concurrent requests with a small number of threads, using an event-loop model.
- WebFlux uses Netty as the default embedded server, which is known for its non-blocking I/O capabilities.
- Reactive programming in Spring Boot WebFlux is well-suited for applications that require high scalability and responsiveness.

**NoSQL DB and Reactive Support:**
- In the context of reactive programming, NoSQL databases like MongoDB, Redis, etc., are often used.
- These databases are well-suited for handling large amounts of data and provide reactive APIs for asynchronous, non-blocking operations.
- They store data in formats like JSON documents, which align well with the reactive programming model.

**Output Types:**
- In reactive programming, the output of a request is represented by `Mono<T>` (zero or one element) or `Flux<T>` (zero to many elements).
- `Mono` and `Flux` are part of the Reactor project, which is a reactive library used in Spring Boot WebFlux.
- `Mono` represents a stream of zero or one elements, while `Flux` represents a stream of zero to many elements.
- These types allow for processing data asynchronously and reactively, providing flexibility and scalability.

In summary, Spring Boot Reactive Programming with Spring Boot WebFlux and reactive APIs enables non-blocking, asynchronous handling of requests. By utilizing a non-blocking model and reactive data stores, applications can achieve high scalability and responsiveness. The use of `Mono` and `Flux` types in reactive programming allows for efficient processing of data streams.

<br/>
<br/>

# Here is a detailed explanation of the steps you mentioned for setting up MongoDB:

**Step 1: Download MongoDB**
- Visit the MongoDB website at https://www.mongodb.com/try/download/community.
- Fill in the required details and choose the appropriate version of MongoDB to download.

**Step 2: Run the Executable**
- Once the download is complete, locate the downloaded executable file and run it.
- Follow the installation instructions provided by the MongoDB installer.

**Step 3: Create Data Directory**
- After installing MongoDB, create a directory on your system where MongoDB will store its data.
- For example, you can create a folder named `C:/data/db` to serve as the data directory.

**Step 4: Start MongoDB Server**
- Open a command prompt or terminal and navigate to the MongoDB installation directory.
- Run the command `mongod` to start the MongoDB server.
- By default, the server will start on port 27017.

**Step 5: Start MongoDB Client**
- Open a new command prompt or terminal window.
- Run the command `mongo` to start the MongoDB client and connect to the MongoDB server.

**Step 6: MongoDB Commands**
- Once the MongoDB client is started, you can enter MongoDB commands to interact with the database.
- Here are some commonly used commands:
   - `show dbs;`: Lists all available databases.
   - `use <database-name>;`: Switches to the specified database or creates it if it doesn't exist.
   - `show collections;`: Lists all collections in the current database.
   - `db.<collection-name>.insert(<document>);`: Inserts a document into the specified collection.
   - `db.<collection-name>.find();`: Retrieves all documents from the specified collection.
   - `db.<collection-name>.find().pretty();`: Retrieves and displays all documents from the specified collection in a formatted manner.

In summary, the process of setting up MongoDB involves downloading and installing MongoDB, creating a data directory, starting the MongoDB server, and using the MongoDB client to interact with the database. The MongoDB client allows you to execute commands for managing databases, collections, and documents within MongoDB.