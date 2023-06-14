# Here's the detailed explanation for performing the "DELETE EMPLOYEE" operation in the given code:

**Step 1: Call Delete method in Service**
In the service layer, call the repository method `deleteById(id)` to delete the employee with the specified ID from the database.

```java
public void deleteEmployeeById(Integer id) {
    repo.deleteById(id);
}
```

**Step 2: Call service Method in Controller and pass "id"**
In the controller, define a method to handle the delete operation. Use the `@RequestParam` annotation to read the `id` parameter from the request URL.

```java
@GetMapping("/delete")
public String deleteEmployee(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
    service.deleteEmployeeById(id);
    redirectAttributes.addAttribute("message", "Employee " + id + " Deleted");
    return "redirect:/employee/all";
}
```

- The `deleteEmployee()` method is mapped to the `/delete` endpoint using the `GET` method.
- The `id` parameter is obtained from the request URL using the `@RequestParam` annotation. It represents the ID of the employee to be deleted.
- The `RedirectAttributes` parameter allows us to pass attributes to the redirected URL.
- After deleting the employee, an attribute "message" is added to the redirectAttributes with a success message.
- The method returns "redirect:/employee/all" to redirect the user to the `/all` endpoint, which displays all employee data.

**URL and Redirect Explanation:**
- Initially, to view all employee rows, the URL is `http://localhost:9696/employee/all`.
- After deleting an employee, the URL becomes `http://localhost:9696/employee/all?message=Employee%20101%20Deleted`. The message parameter is added to the URL as a query parameter, indicating that the employee with ID 101 has been deleted.
- The message will be displayed on the redirected page, providing feedback to the user about the successful deletion.

**Conclusion:**
To delete an employee, the controller receives the ID as a request parameter, passes it to the service layer, which in turn deletes the corresponding employee from the database. After successful deletion, a redirect is performed to the `/all` endpoint, where the user can view all employee data, along with a success message indicating the deleted employee's ID.

<br/>
<br/>



# Here's a detailed explanation of handling exceptions in the scenario where a user manually enters a URL for delete:

```
    Problem: If end user / any enters URL for delete manually
    ex: http://localhost:9696/employee/delete?id=3
```
## Solution
**Step 1: Define a custom exception**
You can create a custom exception class to represent the specific scenario when an employee is not found.

```java
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
```

In this example, `EmployeeNotFoundException` is a custom exception class that extends `RuntimeException`.

**Step 2: Throw an exception at the service layer**
Inside the service layer, when an employee is not found for deletion, you can throw the `EmployeeNotFoundException`.

```java
public void deleteEmployee(Integer id) {
    Employee employee = repository.findById(id);
    if (employee == null) {
        throw new EmployeeNotFoundException("Employee not found with ID: " + id);
    }
    // Perform the delete operation
}
```

If the employee is not found in the repository, the service layer will throw the `EmployeeNotFoundException` with an appropriate error message.

**Step 3: Catch the exception at the controller**
In the controller, you can catch the `EmployeeNotFoundException` and handle it accordingly. You can return an error message to the user interface or redirect to an error page.

```java
@ExceptionHandler(EmployeeNotFoundException.class)
public String handleEmployeeNotFoundException(EmployeeNotFoundException ex, Model model) {
    model.addAttribute("errorMessage", ex.getMessage());
    return "errorPage";
}
```

Here, `@ExceptionHandler` is used to handle the `EmployeeNotFoundException` specifically. The error message from the exception is added to the model and then returned to the appropriate error page.

**Conclusion:**
By following these steps, you can handle the scenario where a user manually enters a URL for delete and an employee is not found. By throwing a custom exception in the service layer and catching it in the controller, you can provide an appropriate error message to the user interface or redirect to an error page, ensuring a graceful error handling experience.


<br/>
<br/>


# Here's the code for handling the delete operation and its detailed explanation:

**1. Custom Exception: EmployeeNotFoundException**
This class represents a custom exception that will be thrown when an employee is not found.

```java
package com.app.shivam.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
```

**2. Service Method: deleteEmployee**
This method in the service layer is responsible for deleting an employee. If the employee is not found, it throws the `EmployeeNotFoundException`.

```java
public void deleteEmployee(Integer id) {
	repo.delete(
		repo.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException("EMPLOYEE '" + id + "' NOT FOUND"))
	);
}
```

The `deleteEmployee` method uses the `findById` method to check if the employee exists. If the employee is present, it is deleted. Otherwise, it throws the `EmployeeNotFoundException`.

**3. Controller Methods: showData and deleteData**
These two methods are present in the controller to handle the request for displaying all data and deleting an employee, respectively.

```java
@GetMapping("/all")
public String showData(
	Model model,
	@RequestParam(value = "message", required = false) String message
) {
	List<Employee> list = service.getAllEmployees();
	model.addAttribute("list", list);
	model.addAttribute("message", message);
	return "EmployeeData";
}

@GetMapping("/delete")
public String deleteData(
	@RequestParam("id") Integer empId,
	RedirectAttributes attributes
) {
	String msg = null;
	try {
		service.deleteEmployee(empId);
		msg = "Employee '" + empId + "' Deleted";
	} catch (EmployeeNotFoundException e) {
		e.printStackTrace();
		msg = e.getMessage();
	}
	attributes.addAttribute("message", msg);
	return "redirect:all";
}
```

The `showData` method retrieves all employees from the service layer and adds them to the model. It also accepts an optional message parameter, which can be used to display success or error messages on the UI.

The `deleteData` method handles the delete operation. It receives the employee ID as a request parameter and attempts to delete the employee using the service layer. If the `EmployeeNotFoundException` is caught, it retrieves the error message and adds it to the redirect attributes. Finally, it redirects to the `showData` method with the updated message.

**Conclusion:**
By implementing the `EmployeeNotFoundException` and handling it in the controller, you can gracefully handle the scenario where an employee is not found during the delete operation. The custom exception provides a way to differentiate this specific error, and the controller methods ensure proper error handling and message passing to the UI.

<br/>
<br/>
<br/>

## **To modify the exception type to `Exception` in the code, you need to update the `EmployeeNotFoundException` class declaration to extend the `Exception` class instead of `RuntimeException`. Here's the modified code:**

```java
package com.app.shivam.exception;

public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
```

<br/>
<br/>

# Let's create the error and exception pages in the Spring Boot app:

**Step 1: Create an `error` folder under the `templates` directory**
Create a new folder named `error` under the `templates` directory. This folder will contain the HTML files for different error codes.

**Step 2: Create an HTML file for the specific error code**
Create an HTML file for the desired error code under the `error` folder. For example, to create a custom 404 error page, create a file named `404.html` with the following content:

```html
<html xmlns:th="https://www.thymeleaf.org/">
	<head>
		<title>WELCOME TO DATA PAGE</title>
	</head>
	<body>
		<h3> SERVER FAILED TO PROCESS YOUR REQUEST </h3>
		<img th:src="@{/images/error_image.png}"></img>
	</body>
</html>
```

You can customize the content of the error page as per your requirements. The `<img>` tag is used to display an error image, and you can replace it with your desired image.

Make sure you have the error image file (`error_image.png`) located under the `images` folder in the static resources directory.

By following these steps, you have created a custom error page for the 404 error code. You can create similar HTML files for other error codes, such as 500, 403, etc., and place them under the `error` folder.