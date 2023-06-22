# **Spring Boot Rest - Unit Testing**

Here's a detailed explanation of the points you mentioned regarding Spring Boot Rest unit testing:

**1. Spring Boot Starter for Testing Dependencies:**

Spring Boot provides a convenient way to include all the required dependencies for unit testing by using the `spring-boot-starter-test` starter. This starter includes the necessary JARs for testing, such as JUnit and Mockito, along with other testing-related utilities provided by Spring Boot.

**2. Setting Up Mock Request Execution:**

With Spring Boot's testing framework, you can create mock requests and execute them within a simulated environment without involving the actual client/browser. This allows you to test your RESTful endpoints without making real network calls.

**3. Mocking the Container Setup:**

When performing unit testing for Spring Boot REST applications, the testing framework creates a mocked container setup. This means that the actual server container is not started, but a simulated environment is created for executing the tests. This ensures that the tests are isolated and do not rely on the presence of a running server.

**4. Checking Response with Assert API:**

After executing the mock request, you can use the assertion methods provided by the testing framework to validate the response. The assert API allows you to compare the actual response against the expected outcome, asserting the correctness of the RESTful endpoint behavior.

In summary, Spring Boot's testing framework simplifies the process of unit testing RESTful endpoints. The `spring-boot-starter-test` dependency provides all the necessary testing dependencies, including JUnit and Mockito. You can create mock requests and execute them within a simulated environment without involving a running server. Finally, you can use the assertion methods provided by the testing framework to verify the correctness of the response.


<br/>
<br/>

# Here's a detailed explanation of the steps you mentioned for creating a test environment using mocking and testing REST methods with mock requests:

**1. Create Test Environment using Mocking:**

- `@SpringBootTest(webEnvironment = WebEnvironment.MOCK)`: This annotation is used to activate the server/container setup to start the application in a mocked environment. It simulates the behavior of the actual server without starting it. It allows you to test your application as if it were running in a real server environment.

- `@AutoConfigureMockMvc`: This annotation is used to activate the MVC/HTTP protocol and annotations such as `@RequestBody` and `@RestController`. It configures the `MockMvc` instance, which is a powerful tool for testing Spring MVC controllers. It provides a way to send mock HTTP requests and receive responses, enabling you to test your RESTful endpoints.

- `@TestPropertySource("classpath:application.properties")`: This annotation is used to load an external properties file for testing purposes. By specifying the file's classpath location, you can override specific properties used during testing. If not provided, the default `application.properties` file will be loaded.

**2. Test each Rest Method using Mock Request:**

To test individual REST methods, you create mock requests and execute them within the test environment. Here's an example of how to create a mock request:

- `MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/product/create")`: This creates a mock request of type `HttpServletRequest` using the builder pattern. In this example, a POST request is created with the URL "/product/create".

- `.contentType("application/json")`: This sets the content type of the request to "application/json". It specifies that the request body will be in JSON format.

- `.content("{\"pname\":\"A\",\"cost\":200}")`: This sets the content of the request body. In this example, a JSON object is provided as the request body content.

- `MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/product/all")`: This creates a mock request for a GET method with the URL "/product/all".

- `.accept("application/xml")`: This sets the accept header of the request to "application/xml". It specifies that the client expects the response in XML format.

Once you have created the mock request, you can execute it using the `MockMvc` instance, which is internally mocked through the `webAppContextSetup` method.

In summary, the steps involve setting up the test environment using mocking with annotations such as `@SpringBootTest`, `@AutoConfigureMockMvc`, and `@TestPropertySource`. Then, you create mock requests using `MockMvcRequestBuilders` to simulate HTTP requests. Finally, you can execute the mock requests using `MockMvc` and perform assertions on the responses to test your REST methods.

<br/>
<br/>

# Here's the modified code with explanations for each method:


```java
package com.app.shivam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.properties")
public class SpringBootRestCrudMySqlExApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Disabled
	@DisplayName("TESTING STUDENT SAVE OPERATIONS")
	@Order(1)
	public void testCreateStudent() throws Exception {
		// 1. CREATE MOCKED REQUEST
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("http://localhost:9690/v1/api/student/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"stdName\":\"AJAY\",\"stdGen\":\"Male\",\"stdCourse\":\"JAVA\",\"stdAddr\":\"BOM IND\"}");

		// 2. EXECUTE IT AND READ RESULT (REQUEST + RESPONSE + EXCEPTION)
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3. READ RESPONSE FROM RESULT
		MockHttpServletResponse response = result.getResponse();

		// 4. ASSERT RESULT USING JUNIT
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		if (!response.getContentAsString().contains("CREATED")) {
			fail("STUDENT NOT CREATED!!");
		}
	}

	@Test
	@DisplayName("TESTING STUDENT SAVE OPERATIONS IN SHORT")
	@Order(1)
	public void testCreateStudentShort() throws Exception {
		mockMvc.perform(post("http://localhost:9690/v1/api/student/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"stdName\":\"AJAY\",\"stdGen\":\"Male\",\"stdCourse\":\"JAVA\",\"stdAddr\":\"HYD IND\"}"))
				.andExpect(status().isCreated());
	}
}
```

Explanation of each method:

1. `testCreateStudent()`:
   - This method tests the save operation for a student.
   - It creates a mocked request using `MockMvcRequestBuilders.post()` with the URL, content type, and request body.
   - The request is sent using `mockMvc.perform()` and the result is stored in `MvcResult`.
   - The response is extracted from `MvcResult` using `getResponse()`.
   - Assertions are made using JUnit assertions (`assertEquals()` and `assertNotNull()`) to verify the response status and content.
   - If the response does not contain "CREATED", the test fails with a custom failure message.

2. `testCreateStudentShort()`:
   - This method is a shorter version of the previous test.
   - It uses method chaining with `mockMvc.perform()` and `andExpect()` to create the request, set the content type and body, and assert the response status in a single line.
  

 - The `status().isCreated()` assertion verifies that the response has a status of HTTP 201 (Created).

Both methods are annotated with `@Test` to indicate that they are test methods. The `@Disabled` annotation is used to disable the `testCreateStudent()` method if needed. The `@DisplayName` annotation provides a custom display name for the test methods. The `@Order` annotation sets the execution order of the test methods if required.

These test methods use `MockMvc` to simulate HTTP requests and receive responses. By using `MockMvc`, you can test the functionality of your RESTful endpoints without the need for an actual server.