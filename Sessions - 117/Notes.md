# Spring Boot Security using ORM

## **1. HttpSession:**
   HttpSession is an object that represents a user's session on the server. It is created once a user logs in successfully and persists for a certain period of time, typically from login to logout. The session object stores user-specific data that can be accessed across multiple requests.

   -- Internal Servlets code --<br/>
   **a. Creating a new session:**
      When a user logs in, the server creates a new session for that user. This can be done using the following code:
      ```java
      HttpSession session = request.getSession(true);
      ```
      The `getSession(true)` method retrieves the current session associated with the request. If a session does not exist, it creates a new one.

   **b. Reading an existing session:**
      To read an existing session, you can use the following code:
      ```java
      HttpSession session = request.getSession(false);
      ```
      The `getSession(false)` method retrieves the current session associated with the request. If a session does not exist, it returns `null`.

   Note: The code `HttpSession session = request.getSession();` reads the old session if it exists, and if not, creates a new session.

## **2. Adding/Modifying/Removing/Reading data in the session:**
   Once the session is created, you can add, modify, remove, and read data stored within the session using key-value pairs.

   **a. Adding data to the session:**
      To add data to the session, you can use the `setAttribute` method:
      ```java
      session.setAttribute("userId", 102345);
      ```
      This code adds the data "102345" with the key "userId" to the session.

   **b. Modifying data in the session:**
      To modify data in the session, you can again use the `setAttribute` method:
      ```java
      session.setAttribute("userId", 11111);
      ```
      This code modifies the value associated with the key "userId" in the session to "11111".

   **c. Reading data from the session:**
      To retrieve data from the session, you can use the `getAttribute` method:
      ```java
      int id = (Integer) session.getAttribute("userId");
      ```
      This code retrieves the value associated with the key "userId" from the session and casts it to an `Integer`.

   **d. Removing data from the session:**
      To remove data from the session, you can use the `removeAttribute` method:
      ```java
      session.removeAttribute("userId");
      ```
      This code removes the value associated with the key "userId" from the session.

## **3. Deleting the session on logout:**
   When a user logs out, it is important to invalidate and delete the session to ensure the user is no longer authenticated.

   ```java
   session.invalidate();
   ```
   The `invalidate` method is called to invalidate the session and remove it from the server.

In summary, HttpSession provides a mechanism to store user-specific data across multiple requests within a session. It allows you to add, modify, remove, and read data associated with a user's session. By invalidating the session on logout, you ensure that the user is no longer authenticated.
