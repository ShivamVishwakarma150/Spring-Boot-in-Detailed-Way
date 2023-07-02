#  Spring Boot Security (OAuth 2.x)


1. OAuth 2.x Means Open Authorization using "Auth and Resource Server" (3rd Party services) to access end-user data by a client application:
   - OAuth 2.x is a protocol that allows a client application to access end-user data stored on 3rd party services, such as social media platforms or other web services.
   - The protocol involves interaction between two key components: the Authorization Server (Auth Server) and the Resource Server.
   - The Auth Server handles user authentication and provides authorization for the client application to access the user's data.
   - The Resource Server stores the user's data and responds to requests from the client application to access that data.

2. OAuth is a standard Protocol for User Identity and verify concept using client application service:
   - OAuth is a widely adopted standard protocol used for user authentication and authorization in client-server applications.
   - It enables a client application to verify the user's identity and obtain the necessary permissions to access specific resources on the user's behalf.
   - The OAuth protocol employs various authentication mechanisms, such as username/password or social media login, to authenticate the user and establish their identity.

3. OAuth 2.x is recommended for Day-to-Day business applications (Internal network applications):
   - OAuth 2.x is well-suited for everyday business applications that operate within internal networks or within trusted environments.
   - It provides a secure and standardized approach for handling user authentication and authorization within such applications.
   - Examples of popular business applications that utilize OAuth 2.x include MMT, Carwale, Zomato, and BookMyShow.

4. It is not recommended for finance/banking applications (e.g., ICIC Bank, Credit Card service):
   - Due to the sensitivity and high-security requirements of finance and banking applications, OAuth 2.x is generally not recommended for such use cases.
   - Finance and banking applications often have stricter regulatory and compliance standards, which may require more specialized authentication and authorization mechanisms.

5. Three devices involved in OAuth 2.x flow:
   - The OAuth 2.x flow typically involves three devices: the Auth & Resource Server, the Client Application (server), and the End Customer Device (Browser).
   - The Auth & Resource Server is responsible for handling user authentication, issuing access tokens, and storing user data.
   - The Client Application acts as the intermediary between the user and the Auth & Resource Server. It requests access to the user's data and handles the authentication process.
   - The End Customer Device, usually a web browser, is used by the end customer to interact with the Client Application and provide necessary authentication and authorization.

6. AuthService Providers (e.g., Google, Facebook, Github, LinkedIn, Twitter):
   - AuthService Providers are 3rd party services that provide the authentication infrastructure necessary for OAuth 2.x.
   - Examples of popular AuthService Providers include Google, Facebook, Github, LinkedIn, and Twitter.
   - These providers offer developer platforms and APIs that allow client applications to integrate OAuth 2.x authentication and authorization functionality.

In summary, OAuth 2.x is a standard protocol for user authentication and authorization, facilitating the secure access of end-user data by client applications. It involves interaction between an Auth Server, a Resource Server, and the client application, with the support of AuthService Providers. OAuth 2.x is commonly used in day-to-day business applications but not recommended for high-security finance/banking applications.

<br/>
<br/>

**1. Register Client application with Auth&Resource Server:**
   - To integrate OAuth 2.x into a client application, you need to register the application with both the Auth Server and the Resource Server.
   - The registration process typically involves providing information about the client application, such as its name, redirect URLs, and scopes.
   - Scopes define the specific permissions or access rights that the client application requests from the Auth Server to access the user's data on the Resource Server.
   - Once the registration is complete, the client application is assigned a unique client ID and client secret, which are used during the authentication process.

**2. Create End Customer account at client application and login:**
   - In order for users to access the client application, they need to create an account within the application itself.
   - The account creation process typically involves providing personal information, such as username, email, and password.
   - After creating an account, the user can log in using their credentials, which will authenticate them within the client application.

**3. Get Access Token by a client+user from Auth Server to access resource from Resource Server:**
   - To access the user's data on the Resource Server, the client application needs to obtain an access token from the Auth Server.
   - The client application initiates the authentication process by redirecting the user to the Auth Server's login page.
   - The user enters their credentials (e.g., username and password) on the Auth Server's login page to authenticate themselves.
   - If the authentication is successful, the Auth Server generates an authorization code and redirects the user back to the client application's redirect URL.
   - The client application then exchanges the authorization code with the Auth Server for an access token.
   - The access token is a credential that represents the user's authorization to access their data on the Resource Server.
   - The client application can use this access token to make authenticated requests to the Resource Server on behalf of the user.
   - The Resource Server validates the access token to ensure its authenticity and authorization, and then provides the requested user data to the client application.

In summary, the stages you mentioned involve registering the client application with the Auth&Resource Server, creating an end customer account within the client application, and obtaining an access token from the Auth Server to access the user's data on the Resource Server. These steps are essential for establishing the necessary authentication and authorization mechanisms required for OAuth 2.x.

<br/>
<br/>


**1. End Customer tries to make a Login request to the client application:**
   - The end customer, who is using a device like a browser, initiates the login process by accessing the client application.
   - The client application presents a login interface to the end customer, typically asking for credentials such as username and password.

**2. Client app will ask the user to login first and allow access request (called Grant):**
   - The client application prompts the end customer to enter their login credentials, verifying their identity.
   - After successful authentication, the client application presents an access request to the end customer, explaining the permissions or data access it requires.
   - This step is known as the authorization grant, where the client application asks for the end customer's permission to access their data.

**3. If the user has given the Grant to the client application:**
   - If the end customer grants permission to the client application, the client application proceeds with the authentication process.

**4. The client makes a request to the Auth Server for an Access Token using the client ID, secret, and User Grant:**
   - The client application sends a request to the Auth Server, providing its client ID, client secret, and the authorization grant obtained from the end customer.
   - The client ID and client secret authenticate the client application to the Auth Server, ensuring its legitimacy.
   - The authorization grant is included to validate that the end customer has granted permission to the client application.

**5. The Auth Server validates the data and provides an access token to the client app:**
   - The Auth Server verifies the client application's credentials, such as the client ID and client secret.
   - The Auth Server also validates the authorization grant to ensure it matches the previously granted permission.
   - If the data is valid, the Auth Server generates an access token specific to the client application and the authenticated end customer.
   - The access token represents the client application's authority to access protected resources on behalf of the end customer.

**6. The client makes a request to the Resource Server to read end user data using the Access Token:**
   - With the obtained access token, the client application sends authenticated requests to the Resource Server.
   - The client application includes the access token in each request to demonstrate its authorization to access protected resources.
   - The Resource Server examines and verifies the access token to ensure its authenticity and validity.

**7. The Resource Server validates the access token and provides user data:**
   - The Resource Server validates the access token by checking its integrity, expiration, and scope.
   - If the access token is deemed valid, the Resource Server responds to the client application's request by providing the requested user data.
   - The user data may include information such as the end customer's profile details, preferences, or any other authorized data.

**8. The Client App stores user data and makes the user login success, redirecting to the homepage:**
   - Once the client application receives the user data from the Resource Server, it can store this data locally.
   - Storing user data allows the client application to personalize the user experience, provide relevant information, or perform other application-specific operations.
   - After storing the user data, the client application considers the login process as successful and redirects the end customer to the homepage or a designated landing page within the application.

In summary, the process involves the end customer logging in, granting permissions to the client application, the client application obtaining an access token from the Auth Server, accessing user data from the Resource Server using the access token, and finally storing the data locally and redirecting the user to the homepage. These steps ensure secure authentication, authorization, and seamless access to the end customer's data.