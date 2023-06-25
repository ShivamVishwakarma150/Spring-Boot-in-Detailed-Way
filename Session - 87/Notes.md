
# **Spring Cloud `FAQ's`**

**Q) Why is Spring Cloud used?**<br/>
A) Spring Cloud is used to implement applications using the microservices architectural style in Java. It provides a set of tools and frameworks that help in building, deploying, and managing distributed systems composed of microservices. Spring Cloud offers various features such as service discovery, distributed configuration management, circuit breakers, intelligent routing, and load balancing, which simplify the development and deployment of microservices-based applications.

**Q) How is Spring Cloud different from Spring Boot?**<br/>
A) Spring Cloud is built on top of Spring Boot. Spring Boot is a framework that simplifies the configuration and deployment of Spring-based applications by providing auto-configuration and opinionated defaults. It is primarily used for developing web applications, data access with JPA, security, and other common enterprise application functionalities. On the other hand, Spring Cloud focuses on providing additional features and tools specifically designed for building microservices architectures, such as service discovery, distributed configuration management, and API gateways.

**Q) How is our app connected with both Spring Cloud and Spring Boot (using Maven)?**<br/>
A) To connect our application with both Spring Cloud and Spring Boot using Maven, we need to configure the appropriate dependencies in the project's `pom.xml` file. Spring Boot dependencies are added using the `<parent>` tag in the `pom.xml`, which sets the Spring Boot version and includes its default configurations. For Spring Cloud dependencies, we can use the `<dependencyManagement>` tag in the `pom.xml` to manage the versions of Spring Cloud artifacts. This ensures that the required Spring Cloud components are compatible with the Spring Boot version used in the project.

**Q) What is an instance and why is it important in microservices architecture?**<br/>
A) In the context of microservices architecture, an instance refers to a running copy of a microservice application deployed on a server. Each instance represents an independent unit of the microservice, capable of handling requests and providing services to users. Multiple instances of a microservice can be deployed to handle increased load and improve scalability. Instances play a crucial role in achieving fault tolerance, parallel processing, and distributing the workload across multiple servers.

**Q) What is a register and discovery server? How is it different from a web server like Tomcat?**<br/>
A) A register and discovery server is a component in a microservices architecture that facilitates service registration and discovery. It maintains a registry of available microservices instances and their network locations (IP addresses and ports). Examples of register and discovery servers are Eureka (Spring Cloud Netflix) and Consul. These servers allow microservices to register themselves with their metadata, such as service name, instance ID, IP address, and port. Other microservices can then discover and communicate with the registered services by querying the register and discovery server. 

In contrast, a web server like Tomcat is responsible for hosting and serving web applications or APIs. It listens for incoming HTTP requests and responds with the appropriate content. Web servers handle the execution of web applications but do not provide service registration and discovery functionality. Register and discovery servers are specifically designed to facilitate communication and interaction between microservices in a distributed system.

<br/>
<br/>

## **Register and Discovery Server example: Netflix Eureka Server, Apache ZooKeeper**<br/>
Register and discovery servers are components in a microservices architecture that facilitate service registration and discovery. Two popular examples of register and discovery servers are Netflix Eureka Server and Apache ZooKeeper.

- Netflix Eureka Server: It is a server-side component from Netflix's Spring Cloud Netflix suite. Eureka Server allows microservices to register themselves and provides a registry of available instances. Microservices can query the Eureka Server to discover other services and establish communication.

- Apache ZooKeeper: ZooKeeper is a distributed coordination service that can be used as a register and discovery server in a microservices architecture. It provides a hierarchical namespace and maintains a distributed registry of available services and their metadata. ZooKeeper enables reliable service discovery and coordination across distributed systems.

## **Webservers are used to run .war files (webapps). Register and Discovery Server for MS# apps.**<br/>
Web servers, such as Apache Tomcat, are primarily used to host and run web applications or web APIs in the form of `.war` files. These web servers receive HTTP requests from clients, handle the request processing, and return the appropriate responses. They are specifically designed to handle web-related functionalities, such as serving static content, handling HTTP methods, and managing session data.

On the other hand, register and discovery servers, like Netflix Eureka Server and Apache ZooKeeper, are dedicated components used for managing microservices in a distributed system. They provide functionality for service registration, where microservices can register themselves along with their metadata, such as service name, instance ID, and network location. The register and discovery servers maintain a registry of available microservice instances and expose APIs for other microservices to discover and communicate with the registered services.

While web servers are focused on running web applications and serving web-related content, register and discovery servers are specialized tools for managing the dynamic nature of microservices architecture, enabling efficient communication and service discovery within the distributed system.

<br/>
<br/>



**Q: How can we expose our MS# or Register? How can we enable getting other MS# data from Register?**<br/>

To expose a microservice (MS#) and enable communication with other microservices through a register, you can follow these steps:

1. Add Spring Cloud Eureka Discovery Client Dependency: In your microservice application, include the Spring Cloud Eureka Discovery Client dependency along with the necessary web dependencies.

2. Enable Eureka Client: In the main class of your microservice application, annotate it with `@EnableEurekaClient`. This annotation enables the microservice to act as a Eureka client and participate in service registration and discovery.

3. Configure Eureka Client Properties: In the application properties or YAML file, provide the following properties:

   - `eureka.client.service-url.defaultZone`: This property specifies the URL of the Eureka server where the microservice should register itself and discover other services.
   - `eureka.client.register-with-eureka`: Set this property to `true` to enable the microservice to register itself with the Eureka server.
   - `eureka.client.fetch-registry`: Set this property to `true` to enable the microservice to fetch the registry of other services from the Eureka server.

By configuring these properties, your microservice will register itself with the Eureka server and be able to discover and communicate with other registered microservices.

**Q: In MS# apps, does `register-with-eureka=true` and `fetch-registry=true` need to be given?**<br/>

No, it is not necessary to explicitly provide `register-with-eureka=true` and `fetch-registry=true` in your MS# applications. By default, Spring Cloud provides default values for these properties as `true`. This means that the microservice will automatically register itself with the Eureka server and fetch the registry of other services.

However, if you want to override the default behavior and set the properties to `false`, you can explicitly provide them in your application properties or YAML file.

**Q: Why do we need to provide `register-with-eureka=false` and `fetch-registry=false` at the Eureka Server?**<br/>

For the Eureka server itself, providing `register-with-eureka=false` and `fetch-registry=false` means that the server will not attempt to register itself with another Eureka server or fetch the registry of other services. This configuration is useful when you have a standalone Eureka server that only serves as a registry for microservices but doesn't need to participate in the service discovery process itself.

By setting these properties to `false` at the Eureka server, you can save resources and prevent unnecessary self-registration and registry fetching.

**Q: What is the default value for `defaultZone` if we did not provide it in MS#?**<br/>

If you don't provide the `defaultZone` property in your MS# application, Spring Cloud will use the default value provided by the `EurekaClientConfigBean`. The default URL is `http://localhost:8761/eureka`, assuming that the Eureka server is running on the local machine with the default port `8761`.

**Q: Kubernetes (K8s): What is Deployment and ReplicaSets?**<br/>

In Kubernetes (K8s), the deployment and ReplicaSets are concepts related to managing and scaling containerized applications:

- Deployment: A Deployment is a higher-level Kubernetes object that provides declarative updates and management for a set of ReplicaSets. It ensures that a specified number of replica Pods are running at all times by creating or deleting Pods as needed. Deployments are used to define the desired state of the application and handle scaling, rolling updates, and rollbacks.

- ReplicaSets: A ReplicaSet is a Kubernetes object that ensures a specified number of identical Pods are running at the same time. It helps maintain the desired number of replicas, and if a Pod fails or is terminated, the ReplicaSet replaces it with a new Pod. ReplicaSets are used to manage and scale the number of instances (replicas) of a specific Pod template.

In the context of microservices running in containers, the microservice is packaged into a container image, which is then deployed as a Pod. The ReplicaSet manages the desired number of Pods, ensuring high availability and scalability. The Deployment object provides a higher-level abstraction for managing ReplicaSets and allows for easy updates and rollbacks of the application.

