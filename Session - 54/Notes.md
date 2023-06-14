## **In the provided code snippet, a simple calculation is performed in the `saveEmployee` method of the service layer. The calculation involves calculating the House Rent Allowance (HRA) and Travel Allowance (TA) based on the employee's salary.**

Here's a breakdown of the code:

```java
double hra = e.getEmpSal() * (12.0/100);
double ta = e.getEmpSal() * (4.0/100);
e.setEmpHra(hra);
e.setEmpTa(ta);
```

1. `double hra = e.getEmpSal() * (12.0/100);`:
   - This line calculates the HRA by multiplying the employee's salary (`e.getEmpSal()`) by 12.0% (0.12). The result is stored in the `hra` variable.

2. `double ta = e.getEmpSal() * (4.0/100);`:
   - This line calculates the TA by multiplying the employee's salary (`e.getEmpSal()`) by 4.0% (0.04). The result is stored in the `ta` variable.

3. `e.setEmpHra(hra);`:
   - This line sets the calculated HRA value (`hra`) to the employee's `empHra` attribute using the `setEmpHra` method.

4. `e.setEmpTa(ta);`:
   - This line sets the calculated TA value (`ta`) to the employee's `empTa` attribute using the `setEmpTa` method.

Overall, these calculations determine the HRA and TA amounts based on the employee's salary and update the respective attributes in the `Employee` object (`e`). This allows you to store and retrieve the calculated values along with other employee information in the database.

<br/>
<br/>
<br/>

# **Fecth DB Data to `UI (View Page)`**

**Step #1: JpaRepository's findAll() Method**

```java
// EmployeeRepository.java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
```
Explanation:
- The `EmployeeRepository` interface extends `JpaRepository<Employee, Integer>`, where `Employee` is the entity class and `Integer` is the type of the entity's primary key.
- Since `JpaRepository` is a generic interface, it provides a variety of CRUD methods, including `findAll()`.
- The `findAll()` method retrieves all records from the `emptab` table in the database and returns a `List` of `Employee` objects.

**Step #2: Controller Method to Retrieve Data from Service Layer**

```java
// EmployeeController.java
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;
	
	@GetMapping("/all")
	public String showData(Model model) {
		List<Employee> list = service.getAllEmployees();
		model.addAttribute("list", list);
		return "EmployeeData";
	}
}
```
Explanation:
- The `EmployeeController` class is annotated with `@Controller` to indicate that it is a Spring MVC controller.
- The `@RequestMapping` annotation sets the base URL mapping for all the methods in this controller to `/employee`.
- The `showData()` method handles the GET request for displaying all employee data.
- The `Model` object is passed as a parameter to the method, allowing you to add attributes that can be accessed in the View.
- Inside the method, `service.getAllEmployees()` retrieves a list of all employees from the service layer.
- The obtained list is then added to the `Model` using `model.addAttribute("list", list)`, where "list" is the attribute name and `list` is the actual list of employees.
- Finally, the method returns the name of the View page, "EmployeeData", which will be rendered by the View resolver.

**Step #3: Displaying List Data in HTML Table Format Using th:each**

```html
<!-- EmployeeData.html -->
<html xmlns:th="https://www.thymeleaf.org/">
	<head>
		<title>WELCOME TO DATA PAGE</title>
	</head>
	<body>
		<h2>EMPLOYEE DATA PAGE </h2>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>SALARY</th>
				<th>HRA/TA</th>
				<th>DEPT</th>
				<th>OPERATIONS</th>
			</tr>
			<tr th:each="ob:${list}">
				<td th:text="${ob.empId}"></td>
				<td th:text="${ob.empName}"></td>
				<td th:text="${ob.empSal}"></td>
				<td th:text="${ob.empHra} + ' / ' + ${ob.empTa}"></td>
				<td th:text="${ob.empDept}"></td>
				<td>
					<a href="#">DELETE</a> | <a href="#">EDIT</a>
				</td>
			</tr>
		</table>
	</body>
</html>
```
Explanation:
- The HTML page, `EmployeeData.html`, is a Thymeleaf template where you can access the list of employees passed from the controller.
- The `xmlns:th="https://www.thymeleaf.org/"` declaration at the beginning allows the usage of Thymeleaf directives.
- Inside the `<table>` element, you define the table structure with the appropriate table headers.
- The `th:each` directive is used to iterate over the `list` attribute, which contains the list of employees.
- For each iteration, a new `<tr>` element is created to represent an employee's data.
- Inside the `<tr>` element, you use `th:text` to display the values of specific properties for each employee.
- The `${ob.propertyName}` syntax is used to access the properties of each employee, where `ob` is the loop variable representing an individual employee object, and `propertyName` is the specific property you want to display.
- In this case, the employee's ID, name, salary, HRA/TA, department, and operation links are displayed in different `<td>` elements.
- Note that the HRA/TA field uses `${ob.empHra} + ' / ' + ${ob.empTa}` to display the HRA and TA values separated by a slash (/).
- You can customize the HTML and CSS as needed to enhance the appearance of the table.

These steps and code snippets work together to fetch the database data from the service layer and display it in an HTML table format on the View page using Thymeleaf's template engine.


<br/>
<br/>
<br/>

# URL - Rewriting

URL rewriting is a technique used to create a URL that combines both static and dynamic components. In the given example, the static path is `/employee/delete?id=` and the dynamic path is represented by `<id>`. 

In Thymeleaf, you can perform URL rewriting using the `th:href` attribute to generate the desired URL. Here's the modified code snippet with an explanation:

```html
<a th:href="@{
   '/employee/delete'          <!-- Static path -->
   + '?id=' + ${ob.empId}     <!-- Dynamic path (Request Param) -->
}">
   DELETE
</a>
```

Explanation:
- The `th:href` attribute is used to specify the URL for the anchor `<a>` tag.
- Inside the attribute value, you start with a single quote (`'`) to indicate a string literal.
- Next, you concatenate the static path `/employee/delete` using the `+` operator.
- After that, you add the dynamic path component `?id=` followed by `${ob.empId}`, which represents the ID of the employee.
- The `${ob.empId}` expression retrieves the value of the `empId` property from the current employee object in the iteration.
- The resulting URL will be something like `/employee/delete?id=1`, where `1` is the value of the `empId` for a specific employee.
- When the user clicks on the "DELETE" link, it will send a request to the corresponding URL with the specific employee ID as a parameter.

By using URL rewriting in this way, you can create dynamic URLs with variable components to perform actions specific to individual entities or records.


<br/>
<br/>

# This session Code and Their Explanation

---------------------------
**EmployeeController**

```java
package com.app.shivam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.shivam.entity.Employee;
import com.app.shivam.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService service;

	@GetMapping("/register")
	public String showRegPage() {
		return "EmployeeRegister";
	}
	
	@PostMapping("/save")
	public String saveFormData(
			@ModelAttribute Employee employee,
			Model model
	) {
		Integer id = service.saveEmployee(employee);
		String message = new StringBuffer().append("EMPLOYEE '")
				.append(id).append("' CREATED").toString();
		
		model.addAttribute("message", message);
		return "EmployeeRegister";
	}
	
	@GetMapping("/all")
	public String showData(Model model) {
		List<Employee> list = service.getAllEmployees();
		model.addAttribute("list", list);
		return "EmployeeData";
	}
	
	// Remaining methods for delete, edit, and update
}
```

**Explanation:**

- The `EmployeeController` class is a Spring MVC controller responsible for handling HTTP requests related to employee operations.
- The controller is mapped to the base path `/employee` using the `@RequestMapping` annotation.
- The `showRegPage()` method is mapped to the `/register` endpoint with the `GET` method. It returns the view name "EmployeeRegister" to display the registration page.
- The `saveFormData()` method is mapped to the `/save` endpoint with the `POST` method. It takes an `Employee` object as a `@ModelAttribute` and saves it using the `service.saveEmployee()` method. It adds a success message to the model and returns the view name "EmployeeRegister".
- The `showData()` method is mapped to the `/all` endpoint with the `GET` method. It retrieves all employees from the service using `service.getAllEmployees()`, adds the list of employees to the model, and returns the view name "EmployeeData".

---------------------------
**EmployeeData.html**

```html
<html xmlns:th="https://www.thymeleaf.org/">
	<head>
		<title>WELCOME TO DATA PAGE</title>
	</head>
	<body>
		<h2>EMPLOYEE DATA PAGE</h2>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>SALARY</th>
				<th>HRA/TA</th>
				<th>DEPT</th>
				<th>OPERATIONS</th>
			</tr>
			<tr th:each="ob:${list}">
				<td>[[${ob.empId}]]</td>
				<td>[[${ob.empName}]]</td>
				<td>[[${ob.empSal}]]</td>
				<td>[[${ob.empHra}]] / [[${ob.empTa}]]</td>
				<td>[[${ob.empDept}]]</td>
				<td>
					<a th:href="@{/employee/delete(id=${ob.empId})}">DELETE </a> 
					| <a th:href="@{/employee/edit(id=${ob.empId})}"> EDIT</a>
				</td>
			</tr>
		</

table>
	</body>
</html>
```

**Explanation:**

- The `EmployeeData.html` file is an HTML view template for displaying employee data in a table format.
- It uses Thymeleaf attributes (`th:`) to dynamically generate the table rows based on the list of employees received from the controller.
- The `th:each` attribute iterates over each employee object in the `list` and generates a table row for each employee.
- Inside the table row, data from each employee object is displayed in the respective table cells using the Thymeleaf expression syntax (`[[${...}]]`).
- The "DELETE" and "EDIT" links are generated dynamically using Thymeleaf's URL rewriting syntax. The `th:href` attribute constructs the URL with the employee ID as a path parameter.

---------------------------
**Conclusion:**

In this example, we have a Spring MVC controller `EmployeeController` that handles employee-related requests. The `/register` endpoint displays a registration page, the `/save` endpoint saves employee data, and the `/all` endpoint retrieves all employees and sends them to the "EmployeeData" view.

The "EmployeeData" view uses Thymeleaf to iterate over the list of employees and generate an HTML table. It also provides dynamic links for deleting and editing individual employee records using URL rewriting.

By following this approach, you can effectively fetch data from the database, pass it to the view layer, and render it in a user-friendly format for display.