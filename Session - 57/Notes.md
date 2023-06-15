# Notes

Bootstrap is a popular CSS framework developed by Twitter. It provides a collection of pre-defined CSS classes and components that can be used to style HTML elements and create visually appealing web pages. Here are some key points related to Bootstrap and its usage:

1. CSS Styling:
   - CSS (Cascading Style Sheet) is used to define the visual appearance of HTML elements.
   - Bootstrap provides a set of CSS classes that can be applied to HTML tags to achieve specific styling effects.
   - To use Bootstrap CSS classes, you need to add the corresponding class names to the `class` attribute of the HTML tags.
   - For example:
     ```html
     <input class="form-control" />
     <a class="btn btn-primary" />
     <p class="lead" />
     <body class="bg-dark text-white" />
     <select class="custom-select" />
     ```

2. Bootstrap Documentation:
   - The official Bootstrap documentation provides detailed information about the available CSS classes, components, and their usage.
   - You can refer to the official documentation to learn more about Bootstrap and explore its features.
   - Here are some relevant links:
     - Bootstrap Components: [https://getbootstrap.com/docs/4.6/components/alerts/](https://getbootstrap.com/docs/4.6/components/alerts/)
     - W3Schools Bootstrap Tutorial: [https://www.w3schools.com/bootstrap4/](https://www.w3schools.com/bootstrap4/)

3. Bootstrap CSS CDN:
   - Bootstrap CSS can be included in your web page by adding a link to the Bootstrap CSS file hosted on a CDN (Content Delivery Network).
   - The link is typically added within the `<head>` section of your HTML file.
   - Here's an example of including Bootstrap CSS from the CDN:
     ```html
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
     ```

4. **Conditional Rendering with Thymeleaf:**
   - The `th:if` attribute in Thymeleaf is used to conditionally render HTML elements based on a condition.
   - When `th:if` is used with `${message}`, it means that the associated HTML element will be displayed only if the `message` variable is not null or not empty.
   - For example:
     ```html
     <div th:if="${message}">
         <!-- Render HTML content if message is present -->
     </div>
     ```

By leveraging Bootstrap's CSS classes and components, you can easily enhance the visual presentation of your web pages and create responsive and modern user interfaces.

<br/>
<br/>

# Let's go through each HTML file and provide a separate code explanation for each one:

1. EmployeeRegister.html:
```html
<html xmlns:th="https://www.thymeleaf.org/">
<head>
	<title>WELCOME TO EMPLOYEE REGISTER PAGE</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white text-center">
				<h2>WELCOME TO EMPLOYEE REGISTER PAGE</h2>
			</div>
			<div class="card-body">
				<form th:action="@{/employee/save}" method="POST">
					NAME <input type="text" name="empName" class="form-control" />
					GENDER <br/>
					<input type="radio" name="empGen" value="Male"/> Male
					<input type="radio" name="empGen" value="Female"/> Female <br/>
					SALARY <input type="text" name="empSal" class="form-control" />
					DOJ <input type="date" name="empDate" class="form-control" />
					DEPARTMENT <select name="empDept" class="form-control">
						<option value="">--SELECT--</option>
						<option th:each="dob:${deptList}" th:value="${dob}" th:text="${dob}"></option>
					</select>
					ADDRESS
					<textarea name="empAddr" class="form-control"></textarea>
					<button type="submit" class="btn btn-success">Create</button>
				</form>
			</div>
			<div th:if="${message}" class="card-footer bg-info text-white text-center">
				<b>[[${message}]]</b>
			</div>
		</div>
	</div>
</body>
</html>
```
- This HTML file represents the registration form for an employee.
- It includes the necessary Bootstrap CSS link in the `<head>` section to apply Bootstrap styles.
- The form's action is set to `@{/employee/save}`, indicating that it will be submitted to the `/employee/save` endpoint.
- The form contains input fields for employee details such as name, gender, salary, date of joining, department, and address.
- The gender is captured using radio buttons with the `name` attribute set as `empGen` and corresponding values for Male and Female.
- The department is selected using a dropdown menu generated using a Thymeleaf loop (`th:each`) to populate the options.
- The form has a "Create" button to submit the employee registration.
- If a `message` variable exists (not null or empty), it will be displayed as a card footer with an info background.

2. EmployeeData.html:
```html
<html xmlns:th="https://www.thymeleaf.org/">
<head>
	<title>WELCOME TO DATA PAGE</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white text-center">
				<h2>WELCOME TO EMPLOYEE DATA PAGE</h2>
			</div>
			<div class="card-body">
				<table class="table table-hover">
					<tr class="bg-info text-white">
						<th>ID</th>
						<th>NAME</th>


						<th>GENDER</th>
						<th>DOJ</th>
						<th>SALARY</th>
						<th>HRA/TA</th>
						<th>DEPT</th>
						<th>ADDRESS</th>
						<th>OPERATIONS</th>
					</tr>
					<tr th:each="ob:${list}">
						<td>[[${ob.empId}]]</td>
						<td>[[${ob.empName}]]</td>
						<td>[[${ob.empGen}]]</td>
						<td>[[${ob.empDate}]]</td>
						<td>[[${ob.empSal}]]</td>
						<td>[[${ob.empHra}]] / [[${ob.empTa}]]</td>
						<td>[[${ob.empDept}]]</td>
						<td>[[${ob.empAddr}]]</td>
						<td>
							<a class="btn btn-danger" th:href="@{/employee/delete(id=${ob.empId})}">DELETE</a>
							<a class="btn btn-warning" th:href="@{/employee/edit(id=${ob.empId})}">EDIT</a>
						</td>
					</tr>
				</table>
			</div>
			<div th:if="${message}" class="card-footer bg-success text-white text-center">
				<b>[[${message}]]</b>
			</div>
		</div>
	</div>
</body>
</html>
```
- This HTML file represents the employee data display page.
- It includes the necessary Bootstrap CSS link in the `<head>` section to apply Bootstrap styles.
- The employee data is displayed in a table format using the Bootstrap table classes (`table` and `table-hover`).
- The table header row is styled with a blue background (`bg-info`) and white text (`text-white`).
- The employee data is iterated over using a Thymeleaf loop (`th:each`) to populate the table rows.
- Each table row displays various employee details such as ID, name, gender, date of joining, salary, HRA/TA, department, address, and operations.
- The operations column contains "DELETE" and "EDIT" buttons, which are styled as red and yellow buttons respectively using Bootstrap classes (`btn btn-danger` and `btn btn-warning`).
- If a `message` variable exists (not null or empty), it will be displayed as a card footer with a success background.

3. EmployeeEdit.html:
```html
<html xmlns:th="https://www.thymeleaf.org/">
<head>
	<title>WELCOME TO EMPLOYEE EDIT PAGE</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white text-center">
				<h2>WELCOME TO EMPLOYEE EDIT PAGE</h2>
			</div>
			<div class="card-body">
				<form th:action="@{/employee/update}" method="POST" th:object="${employee}">
					ID <input type="text" th:field="*{empId}" class="form-control" readonly="true"/>
					NAME <input type="text" th:field="*{empName}" class="form-control" />
					GENDER <br/>
					<input type="radio" th:field="*{empGen}" value="Male"/> Male
					<input type="radio" th

:field="*{empGen}" value="Female"/> Female <br/>
					SALARY <input type="text" th:field="*{empSal}" class="form-control" />
					DOJ <input type="date" th:field="*{empDate}" class="form-control" />
					DEPARTMENT <select th:field="*{empDept}" class="form-control">
						<option value="">--SELECT--</option>
						<option th:each="dob:${deptList}" th:value="${dob}" th:text="${dob}"></option>
					</select>
					ADDRESS
					<textarea th:field="*{empAddr}" class="form-control"></textarea>
					<button type="submit" class="btn btn-success">UPDATE</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
```
- This HTML file represents the employee edit page.
- It includes the necessary Bootstrap CSS link in the `<head>` section to apply Bootstrap styles.
- The form's action is set to `@{/employee/update}`, indicating that it will be submitted to the `/employee/update` endpoint.
- The form is pre-populated with employee data using Thymeleaf's `th:object` attribute, which is set to `${employee}` (assuming the `employee` object is available in the model).
- The employee data fields, such as ID, name, gender, salary, date of joining, department, and address, are displayed as input fields with their values bound to the corresponding fields of the `employee` object using the `th:field` attribute.
- The gender is captured using radio buttons with the `th:field` attribute set to `*{empGen}` and corresponding values for Male and Female.
- The department is selected using a dropdown menu generated using a Thymeleaf loop (`th:each`) to populate the options.
- The form has an "UPDATE" button to submit the edited employee details.

<br/>
<br/>
<br/>

# Pagination using Spring boot Data JPA + Thymeleaf

1. URL examples:
   - The URLs demonstrate how to access different pages of the employee data:
     - `http://localhost:9696/employee/all?page=1` - Retrieves the second page of employee data.
     - `http://localhost:9696/employee/all?page=0&size=5` - Retrieves the first page of employee data with a page size of 5.

2. Data Page:
   - This section shows how to create pagination links on the data page using Thymeleaf.
   - The links are wrapped within `<nav>` and `<ul>` elements to provide a navigation interface.
   - The page links are generated dynamically using a Thymeleaf loop (`th:each`) that iterates from 0 to `page.getTotalPages() - 1`.
   - Each link is created with the respective page number and is associated with the `/employee/all` endpoint.
   - The "First" link redirects to the first page, "Previous" redirects to the previous page, "Next" redirects to the next page, and "Last" redirects to the last page.
   - The `th:if` conditionals are used to show/hide the appropriate links based on the current page and available pages.

3. Service interface:
   - The service interface includes the `getAllEmployees` method that returns a `Page<Employee>` using `Pageable` as the method parameter.
   - The `Pageable` parameter is responsible for combining the page number and page size.

4. Service implementation:
   - The service implementation retrieves the `Page<Employee>` by calling the `findAll` method of the repository with the provided `Pageable`.
   - The `Page<Employee>` is returned.

5. Controller method:
   - The new controller method `showData` handles the request to display the paginated employee data.
   - The method accepts the `Model` object, `Pageable` object with default values (`@PageableDefault`), and an optional `message` parameter.
   - The `getAllEmployees` method is called from the service, passing the `Pageable` object to retrieve the paginated data.
   - The retrieved `Page<Employee>` is added to the model as `list` and `page` attributes.
   - The `message` parameter is also added to the model if it exists.
   - The method returns the "EmployeeData" view.

<br/>

## Here's the complete code for implementing pagination using Spring Boot Data JPA and Thymeleaf:

**Controller:**
```java
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public String showData(
            Model model,
            @PageableDefault(page = 0, size = 3) Pageable pageable,
            @RequestParam(value = "message", required = false) String message) {
        
        Page<Employee> page = employeeService.getAllEmployees(pageable);
        model.addAttribute("list", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        return "EmployeeData";
    }
}
```

**Service Interface:**
```java
public interface EmployeeService {
    Page<Employee> getAllEmployees(Pageable pageable);
}
```

**Service Implementation:**
```java
@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
```

**EmployeeData.html (Thymeleaf template):**
```html
<html xmlns:th="https://www.thymeleaf.org/">

<head>
    <title>WELCOME TO DATA PAGE</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
</head>

<body>
    <div class="container">
        <div class="card">
            <div class="card-header bg-primary text-white text-center">
                <h2>WELCOME TO EMPLOYEE DATA PAGE </h2>
            </div>
            <div class="card-body">
                <table class="table table-hover">
                    <tr class="bg-info text-white">
                        <th>ID</th>
                        <th>NAME</th>
                        <th>GENDER</th>
                        <th>DOJ</th>
                        <th>SALARY</th>
                        <th>HRA/TA</th>
                        <th>DEPT</th>
                        <th>ADDRESS</th>
                        <th>OPERATIONS</th>
                    </tr>
                    <tr th:each="employee : ${list}">
                        <td th:text="${employee.empId}"></td>
                        <td th:text="${employee.empName}"></td>
                        <td th:text="${employee.empGen}"></td>
                        <td th:text="${employee.empDate}"></td>
                        <td th:text="${employee.empSal}"></td>
                        <td th:text="${employee.empHra} + ' / ' + ${employee.empTa}"></td>
                        <td th:text="${employee.empDept}"></td>
                        <td th:text="${employee.empAddr}"></td>
                        <td>
                            <a class="btn btn-danger" th:href="@{/employee/delete(id=${employee.empId})}">DELETE</a>
                            <a class="btn btn-warning" th:href="@{/employee/edit(id=${employee.empId})}">EDIT</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div th:if="${message}" class="card-footer bg-success text-white text-center">
                <b>[[${message}]]</b>
            </div>
        </div>
        
        <!-- Pagination -->
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" th:if="${!page.first}" th:href="@{/employee/all

(page=0)}">First</a></li>
                <li class="page-item"><a class="page-link" th:if="${page.hasPrevious()}" th:href="@{/employee/all(page=${page.number-1})}">Previous</a></li>
                <th:block th:each="i: ${#numbers.sequence(0, page.totalPages-1)}">
                    <li class="page-item">
                        <a class="page-link" th:href="@{/employee/all(page=${i})}" th:text="${i+1}"></a>
                    </li>
                </th:block>
                <li class="page-item"><a class="page-link" th:if="${page.hasNext()}" th:href="@{/employee/all(page=${page.number+1})}">Next</a></li>
                <li class="page-item"><a class="page-link" th:if="${!page.last}" th:href="@{/employee/all(page=${page.totalPages-1})}">Last</a></li>
            </ul>
        </nav>
    </div>
</body>

</html>
```

The code includes the controller, service interface, service implementation, and the Thymeleaf template.

In the Thymeleaf template, the table displays the employee data. The pagination section is added at the bottom, which generates pagination links dynamically using the `th:each` loop. The links are associated with the `/employee/all` endpoint and use the `page` object to determine the current page number.

Please note that you would need to create the `Employee` entity, `EmployeeRepository` interface, and configure the database connection accordingly.

<br/>
<br/>

# I'll explain each code section separately, starting with the controller.

**Controller:**
```java
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public String showData(
            Model model,
            @PageableDefault(page = 0, size = 3) Pageable pageable,
            @RequestParam(value = "message", required = false) String message) {
        
        Page<Employee> page = employeeService.getAllEmployees(pageable);
        model.addAttribute("list", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        return "EmployeeData";
    }
}
```
The `EmployeeController` class is annotated with `@Controller` to indicate that it's a Spring MVC controller. The `@RequestMapping` annotation sets the base URL for all mappings defined in the controller.

The `showData` method handles the GET request for displaying employee data. It accepts parameters such as `Model`, `Pageable`, and `message`. The `Model` is used to pass data to the Thymeleaf template.

Inside the method, it retrieves a page of employee data using the `employeeService.getAllEmployees(pageable)` method. The `Page` object contains the content of the current page as well as pagination information.

The employee data, page object, and optional message are added to the model using `model.addAttribute()` and then returns the "EmployeeData" view.

**Service Interface:**
```java
public interface EmployeeService {
    Page<Employee> getAllEmployees(Pageable pageable);
}
```
The `EmployeeService` interface defines a contract for retrieving employee data. It declares a single method `getAllEmployees` that returns a `Page` object of `Employee` entities.

**Service Implementation:**
```java
@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
```
The `EmployeeServiceImpl` class implements the `EmployeeService` interface. It is annotated with `@Service` to indicate that it's a Spring service bean.

In the `getAllEmployees` method, it uses the `employeeRepository` (assumed to be an instance of `EmployeeRepository`) to retrieve a page of employee data using the `findAll` method with the provided `Pageable` object.

**EmployeeData.html (Thymeleaf template):**
```html
<html xmlns:th="https://www.thymeleaf.org/">

<head>
    <title>WELCOME TO DATA PAGE</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
</head>

<body>
    <div class="container">
        <div class="card">
            <div class="card-header bg-primary text-white text-center">
                <h2>WELCOME TO EMPLOYEE DATA PAGE </h2>
            </div>
            <div class="card-body">
                <table class="table table-hover">
                    <!-- Employee table content -->
                </table>
            </div>
            <div th:if="${message}" class="card-footer bg-success text-white text-center">
                <b>[[${message}]]</b>
            </div>
        </div>
        
        <!-- Pagination -->
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" th:if="${!page.first}" th:href="@{/employee/all

(page=0)}">First</

a></li>
                <li class="page-item"><a class="page-link" th:if="${page.hasPrevious()}" th:href="@{/employee/all(page=${page.number-1})}">Previous</a></li>
                <th:block th:each="i: ${#numbers.sequence(0, page.totalPages-1)}">
                    <li class="page-item">
                        <a class="page-link" th:href="@{/employee/all(page=${i})}" th:text="${i+1}"></a>
                    </li>
                </th:block>
                <li class="page-item"><a class="page-link" th:if="${page.hasNext()}" th:href="@{/employee/all(page=${page.number+1})}">Next</a></li>
                <li class="page-item"><a class="page-link" th:if="${!page.last}" th:href="@{/employee/all(page=${page.totalPages-1})}">Last</a></li>
            </ul>
        </nav>
    </div>
</body>

</html>
```
The Thymeleaf template file starts with the HTML declaration and the `xmlns:th` attribute to enable Thymeleaf namespace.

Inside the `<head>` section, there is a `<title>` tag and a link to the Bootstrap CSS file for styling.

The content within the `<body>` tag is divided into three parts: the card header, card body, and pagination section.

- The card header contains a heading.
- The card body includes a table that displays the employee data. This section is left empty in the provided code snippet but should be populated with the appropriate Thymeleaf expressions to display the employee information.
- The card footer is conditionally displayed when there is a `message` parameter. It shows a success message.

The pagination section is enclosed within a `<nav>` tag. It consists of an unordered list (`<ul>`) with pagination links generated dynamically using the `th:each` loop.

- The "First" link is displayed only if the current page is not the first page.
- The "Previous" link is displayed only if there is a previous page available.
- The page numbers are generated using the `th:each` loop, iterating over a sequence from 0 to `page.totalPages-1`. Each page number is displayed as a link.
- The "Next" link is displayed only if there is a next page available.
- The "Last" link is displayed only if the current page is not the last page.

The `th:href` attribute sets the URL for each pagination link, and the `th:text` attribute displays the page number.

Please note that the provided code assumes that the employee data is available and properly populated. You may need to modify and adapt the code to match your specific entity, repository, and database configuration.