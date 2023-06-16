# Spring Boot Restful Webservices

**Point 1: Webservices are used to link two apps that run on different servers.**

Webservices enable communication and data exchange between two applications that may be developed using different technologies and run on separate servers. These applications can be located anywhere on the internet and can interact with each other by sending and receiving data over standard protocols such as HTTP.

Webservices facilitate interoperability by allowing applications to communicate and share data seamlessly, regardless of the programming languages or platforms they are built upon. This makes it possible to integrate systems and exchange information in a standardized manner.

**Point 2: Webservices - SOAP (XML Based / Protocol), ReST (Architecture/Client-Server) [XML/JSON/Text]**

Webservices can be categorized into two main types: SOAP and RESTful.

SOAP (Simple Object Access Protocol) is a protocol and messaging format that uses XML (eXtensible Markup Language) for communication between applications. It defines a set of rules for structuring messages and supports various web service standards such as WSDL (Web Services Description Language) for service definition, and WS-Security for securing the messages.

REST (Representational State Transfer), on the other hand, is an architectural style for designing networked applications. It leverages the existing infrastructure of the World Wide Web and uses standard HTTP methods (GET, POST, PUT, DELETE) to perform operations on resources. RESTful webservices can use different data formats, including XML, JSON (JavaScript Object Notation), and plain text, for representing and exchanging data.

**Point 3: REST - Representation State Transfer**

REST (Representational State Transfer) is an architectural style that defines a set of principles and constraints for building distributed systems. It emphasizes a stateless, client-server communication model where the server provides resources, and clients can perform actions on those resources using standard HTTP methods.

The key principles of REST include:

- **Resource-Oriented**: RESTful webservices expose resources (e.g., entities, data, services) as the fundamental building blocks. Each resource is uniquely identified by a URI (Uniform Resource Identifier).

- **Stateless**: The server does not maintain any client state between requests. Each request from the client must contain all the necessary information for the server to process it.

- **Client-Server**: The client and server are separate entities that communicate over a network. The server provides resources, while the client interacts with those resources using standard HTTP methods.

- **Uniform Interface**: RESTful webservices follow a uniform interface that defines a set of well-defined and standardized methods (GET, POST, PUT, DELETE) for interacting with resources. These methods provide a consistent and predictable way to manipulate resources.

**Point 4: Two apps: Producer and Consumer**

In the context of webservices, the terms "producer" and "consumer" are commonly used to refer to the two applications involved in the communication.

The producer application is responsible for providing the resources or services that the consumer application needs. It exposes the functionalities through a well-defined API and makes them available for consumption by other applications.

The consumer application, on the other hand, is the client that consumes or uses the resources or services provided by the producer. It interacts with the producer application by sending requests and receiving responses.

The producer and consumer can be developed independently using different technologies and programming languages. They communicate over the network using standard protocols, such as HTTP, and exchange data in a format agreed upon by both parties, such as XML or JSON.

The producer-consumer model allows applications to be loosely coupled, enabling flexibility, scalability, and interoperability between different systems.

<br/>
<br/>



## **Case #1: Restful Webservices - Linking/Integrating Two Different Applications**

In this case, the goal is to establish communication and integration between two different applications that are running on separate servers. This is achieved using the HTTP protocol and a global data format, such as XML or JSON, for exchanging data between the applications.

The integration involves two main components: the producer application and the consumer application.

**Producer Application: Service Provider Code / Skeleton / API**
The producer application is responsible for providing the resources or services that the consumer application needs. It exposes these functionalities through a well-defined API. In the context of Restful Webservices, the producer application typically uses the `@RestController` annotation in Spring Boot to define and expose the RESTful endpoints.

The producer application's API acts as the skeleton or blueprint for the services it offers. It specifies the endpoints, request/response formats, and behavior of the services. The API defines the contract that the consumer application should adhere to when accessing these services.

**Consumer Application: Service Consumer Code / Stub / Caller**
The consumer application is the client application that consumes or uses the resources or services provided by the producer application. It interacts with the producer application's API by sending requests and receiving responses.

To communicate with the producer application, the consumer application typically uses a library or framework such as RestTemplate in Spring Boot. The RestTemplate provides a convenient way to make HTTP requests to the producer application's API and handle the responses.

The consumer application acts as the caller or stub that invokes the services provided by the producer application. It makes requests to the producer's RESTful endpoints, passes the required data, and receives the corresponding responses.

The consumer application's code should be designed to handle the interactions with the producer application's API, including making the appropriate HTTP requests, handling errors, and processing the received data.

**Advantages of Restful Webservices**
Restful Webservices offer several advantages, including:

1. **Standardization**: Restful Webservices use widely adopted standards like HTTP and global data formats (such as XML or JSON), making it easier to integrate and communicate between different applications and platforms.

2. **Loose Coupling**: The producer and consumer applications are loosely coupled, meaning they can be developed independently and evolve separately. Changes in one application do not necessarily affect the other, as long as the API contract is maintained.

3. **Scalability**: Restful Webservices can handle high loads and scale horizontally by adding more instances of the producer application. This scalability is possible because each request is stateless, and the server does not maintain any client-specific state.

4. **Interoperability**: Restful Webservices allow applications to be developed using different technologies and programming languages. As long as they adhere to the HTTP protocol and the agreed-upon data format, they can communicate seamlessly.

5. **Flexibility**: Restful Webservices offer flexibility in terms of choosing the appropriate HTTP methods (GET, POST, PUT, DELETE) for different operations on resources. This allows for a clean and intuitive API design.

By utilizing Restful Webservices, applications can establish robust and flexible communication channels, enabling them to work together efficiently and leverage each other's capabilities.<br/>

**Controller in Web MVC Apps**
In traditional web MVC (Model-View-Controller) applications, the controller is responsible for handling user interactions, processing requests, and providing responses. It is connected with the view layer, which represents the user interface (UI). The controller receives input from the UI, interacts with the model layer (data/business logic), and prepares the data to be displayed in the view. The data exchanged between the UI and the controller is typically in the form of objects or model attributes.

**RestController in Rest-Based Apps**
In Restful applications, the RestController is used instead of the traditional controller. RestController is a specialized version of the controller that is specifically designed for building REST-based APIs. Unlike web MVC apps, Restful apps do not have a view layer or UI concept. Instead, they focus on providing services or resources to be consumed by client applications.

The RestController is responsible for handling incoming requests and returning responses in a format suitable for RESTful APIs, such as XML or JSON. It maps HTTP methods (GET, POST, PUT, DELETE) to specific methods in the controller, allowing clients to perform CRUD (Create, Read, Update, Delete) operations on resources.

The data exchanged in Restful APIs is typically in XML or JSON format, making it easy for client applications to consume the services by parsing the data received from the server.

## **Differences Between Controller and RestController**

1. **Connected with View vs. No View Concept**: In web MVC apps, the controller is connected with the view layer, which represents the UI. RestControllers, on the other hand, do not have a view concept as they focus on providing services/resources for client consumption.

2. **Data Exchange**: In web MVC apps, the data is exchanged between the UI and the controller using objects or model attributes. In Restful apps, data is exchanged with client applications using XML or JSON formats, allowing for more flexible and standardized data representation.

3. **Full Web-App Development vs. Service Provider**: Web MVC apps are designed as full-fledged web applications that handle user interactions and provide UI components. Restful apps, on the other hand, act as service providers that offer services/resources to be consumed by client applications. They are not limited to a specific language and can be used by clients developed in any language.

By using RestControllers in Restful applications, developers can build robust APIs that follow the principles of REST and enable easy integration and consumption by client applications.

<br/>
<br/>

# Here are some examples of web services:

1. **Payment Service**: A payment service web service enables secure and convenient online payment transactions. It allows users to make payments for products or services using various payment methods, such as credit cards, digital wallets, or bank transfers. The web service handles the processing of payment requests, authentication, and encryption to ensure the security of the transactions.

2. **OTP (One-Time Password) Service**: An OTP service web service provides the functionality to generate and validate one-time passwords. OTPs are commonly used for authentication and security purposes, where users receive a unique password that can only be used once for a specific transaction or login session. The web service generates and sends OTPs to users' registered devices, and it validates the received OTPs during the authentication process.

3. **Card Payments**: Card payment web services facilitate online transactions involving credit or debit cards. These web services handle the communication and authorization processes between the merchant's website or application and the payment gateway or acquiring bank. They ensure secure transmission of cardholder information, perform authentication and verification, and process the payment transactions.

These are just a few examples of the various types of web services that exist. Web services play a crucial role in enabling communication and integration between different systems, applications, or platforms, allowing them to exchange data and functionality seamlessly over the internet.

<br/>
<br/>

## **case#2 Fullstack Application design.**

In a fullstack application design, the overall application is divided into two main components: the frontend and the backend.

**Frontend Applications (Client Apps)**:
Frontend applications are responsible for presenting the user interface and interacting with the users. They are typically built using technologies like Angular, ReactJS, Android, iOS, or other frameworks and libraries. These applications run on the client-side, i.e., on the user's device (web browser, mobile device, etc.), and are focused on delivering a smooth and intuitive user experience.

Frontend applications rely on the backend applications to handle business logic, perform transactions, and retrieve or store data. They communicate with the backend using HTTP requests and consume the APIs provided by the backend. The frontend applications send requests to the backend, receive responses, and update the user interface accordingly. They often handle user input validation, user authentication, and presentation logic.

**Backend Applications (Provider)**:
Backend applications, also known as the provider, are responsible for handling the business logic, data processing, and data storage. They receive requests from the frontend applications and provide the necessary services or data. Backend applications are built using server-side technologies such as Java, Node.js, Python, or other frameworks.

The backend applications expose APIs (Application Programming Interfaces) that the frontend applications can use to communicate with them. These APIs define the contract between the frontend and the backend, specifying the available endpoints, request/response formats (XML/JSON), and authentication/authorization mechanisms.

Backend applications handle the processing of requests, execute the required operations (e.g., database queries, computations, integrations with other services), and generate responses that are sent back to the frontend. They ensure the security, reliability, and scalability of the application's core functionality.

The integration between the frontend and the backend is typically based on the HTTP protocol, and the data exchanged between them is often in a global format like XML or JSON. This allows the frontend and backend components to be developed independently, using different technologies and frameworks, while still being able to interact seamlessly.

In summary, a fullstack application design combines the frontend and backend components, where the frontend focuses on delivering a user-friendly interface and the backend handles the core business logic and data processing. The two components communicate through APIs over HTTP, allowing for a decoupled and scalable architecture.

<br/>
<br/>

## **case#3 Microservices Design (Backend in new way)**

Microservices design is a software architectural approach where a large-scale application is divided into smaller, independent, and deployable components called microservices. Each microservice is responsible for a specific business capability and can be developed, deployed, and scaled independently. Here are some key concepts related to microservices design:

1. Independent deployable components: In microservices architecture, each microservice is developed as a separate project or module. This allows for independent deployment, scaling, and management of each microservice without affecting the others. It provides flexibility and agility in development and deployment processes.

2. Auto-scale: Microservices architecture allows for automatic scaling of individual microservices based on demand. When the load on a particular microservice increases, additional instances of that microservice can be automatically created to handle the increased load. This dynamic scaling ensures efficient resource utilization and optimal performance.

3. Load balancing: Microservices architecture typically employs load balancing techniques to distribute incoming requests across multiple instances of a microservice. Load balancers ensure that requests are evenly distributed to available instances, improving performance, scalability, and fault tolerance.

4. Service communication: Microservices communicate with each other through well-defined APIs, usually using lightweight protocols such as HTTP/REST or messaging protocols like RabbitMQ or Apache Kafka. This enables loose coupling between microservices and allows them to work together to fulfill complex business requirements.

5. Less downtime/zero downtime: Microservices architecture aims to minimize downtime or achieve zero downtime during deployments or updates. Since each microservice can be deployed independently, updates or bug fixes can be rolled out to one microservice at a time without affecting the availability of the entire application.

By implementing every module as a separate project or microservice, organizations can achieve better maintainability, scalability, and fault tolerance. Microservices architecture enables teams to work independently on different modules, promotes technology diversity, and allows for continuous delivery and deployment practices.

<br/>
<br/>

## **Q) Explain these terms?**

Here's an explanation of each term:

**Compile**:
Compilation is the process of converting source code written in a high-level programming language (such as Java, C++, or Python) into machine-readable code or bytecode. This process involves translating the human-readable code into a format that can be executed by the computer's processor. The compiler checks the syntax and semantics of the code and generates an executable file or intermediate code.

**Build**:
Building refers to the process of creating a deployable version of an application by combining the necessary source code files, libraries, and dependencies. It involves tasks such as compiling the code, resolving dependencies, and packaging the application into a distributable format (such as a JAR file in Java or an executable file in C++). The build process typically includes steps like compiling, linking, and creating artifacts required for deployment.

**Deploy**:
Deployment is the process of making a software application available and operational on a target environment or platform. It involves installing, configuring, and running the application on the intended infrastructure, such as servers or cloud platforms. Deployment may include tasks like setting up databases, configuring network settings, and starting the application in a production or testing environment.

**Instance**:
In the context of software systems, an instance refers to a single running copy of an application or a component. It represents a specific execution environment that hosts the application and serves requests from users or other systems. Each instance runs independently and can handle a subset of the overall workload. Multiple instances of an application can be created to distribute the load and improve performance.

**Scale (Horizontal/Vertical)**:
Scaling refers to the process of adjusting the capacity of a system to handle varying workloads or user demands. There are two common scaling approaches:

- Horizontal Scaling: Also known as scaling out, it involves adding more instances or nodes to the system to distribute the workload. In this approach, multiple copies of the application or component are running simultaneously, and incoming requests are divided among them. Horizontal scaling improves the system's capacity and allows for better load distribution.

- Vertical Scaling: Also known as scaling up, it involves increasing the resources (such as CPU, memory, or storage) of a single instance or node. This approach aims to enhance the performance and capacity of the existing instance by providing more resources to handle increased workload or user demands.

**Downtime**:
Downtime refers to the period during which a system or service is not available or operational. It is the time when the system experiences an interruption or outage, rendering it inaccessible to users or clients. Downtime can occur due to various reasons, including system maintenance, software updates, hardware failures, or network issues. Minimizing downtime is crucial for ensuring high availability and reliability of applications and services.

**Load balancing**:
Load balancing is the process of distributing incoming network traffic across multiple instances, servers, or resources to optimize resource utilization, improve performance, and ensure high availability. Load balancers act as intermediaries between clients and servers, receiving requests and directing them to the most suitable instance or resource based on factors like current load, response time, or availability. Load balancing helps prevent overloading of individual resources, enhances scalability, and improves fault tolerance.

**IP Address (private/public/elastic)**:
- Private IP Address: A private IP address is used within a private network, such as a local area network (LAN) or an organization's internal network. Private IP addresses are not routable over the internet and are used for internal communication among devices within the network.

- Public IP Address: A public IP address is assigned to a device or network interface and can be accessed over the internet. It allows communication between devices on the public internet. Public IP addresses are unique and globally routable, enabling access to resources from anywhere on the internet.

- Elastic IP Address: Elastic IP addresses are public IP addresses that can be dynamically assigned to cloud resources in cloud computing environments. Unlike traditional public IP addresses, which may change when resources are stopped or restarted, elastic IP addresses remain associated with the resource, providing a consistent and static IP address for accessing the resource.

**Network/firewall/routing**:
- Network: In the context of computing, a network refers to a collection of interconnected devices, such as computers, servers, or IoT devices, that can communicate and share resources. Networks can be local (LAN) or wide-area (WAN) and can be connected via physical cables or wireless connections.

- Firewall: A firewall is a network security device that monitors and controls incoming and outgoing network traffic based on predetermined security rules. It acts as a barrier between a trusted internal network and untrusted external networks, preventing unauthorized access, threats, and attacks.

- Routing: Routing is the process of determining the path that network packets should take from the source to the destination across a network infrastructure. Routers, which are network devices, use routing protocols and algorithms to forward packets based on network addresses, such as IP addresses. Routing ensures efficient and reliable data transmission between different networks or subnetworks.