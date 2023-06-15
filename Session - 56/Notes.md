# Operation #4 Edit and Update data

**Stage#1: `Fetch DB Data to Thymeleaf Edit Form (DB to UI)`**

1. To fetch data from the database and display it in a Thymeleaf edit form, we need to use the HTML `<form>` tags with Thymeleaf attributes.

```html
<form th:object="${employee}" method="POST" action="/employee/update">
    <input type="hidden" th:field="*{empId}" />
    <label>Name:</label>
    <input type="text" th:field="*{empName}" />
    <label>Salary:</label>
    <input type="text" th:field="*{empSal}" />
    <label>HRA/TA:</label>
    <input type="text" th:field="*{empHra}" />
    <label>Department:</label>
    <input type="text" th:field="*{empDept}" />
    <button type="submit">Update</button>
</form>
```

In the above code, we bind the form to the `employee` object using `th:object="${employee}"`. Each input field is bound to a corresponding attribute of the `employee` object using `th:field="*{attributeName}"`. The `th:field` attribute simplifies the binding process.

2. In the service layer, use the `findById(id)` method to retrieve the data from the database based on the specified ID.

```java
public Employee getEmployeeById(Integer id) {
    Optional<Employee> employee = repository.findById(id);
    return employee.orElse(null);
}
```

3. In the controller, handle the GET request to display the edit form and pass the retrieved employee object to the Thymeleaf template.

```java
@GetMapping("/edit")
public String showEditForm(@RequestParam("id") Integer empId, Model model) {
    Employee employee = service.getEmployeeById(empId);
    model.addAttribute("employee", employee);
    return "EditEmployeeForm";
}
```

**Stage#2: `Update Form data back to Database (UI to DB)`**

1. When the user submits the form, it triggers a POST request to the specified URL (`/employee/update`).

2. In the controller, handle the POST request to update the employee data in the database.

```java
@PostMapping("/update")
public String updateEmployee(@ModelAttribute Employee employee) {
    service.updateEmployee(employee);
    return "redirect:/employee/all";
}
```

3. In the service layer, implement the `updateEmployee(employee)` method to update the employee data in the database.

```java
public void updateEmployee(Employee employee) {
    // Perform update operation using the repository
    repository.save(employee);
}
```

By following these steps, you can fetch the data from the database and display it in an edit form using Thymeleaf. When the form is submitted, the updated data will be sent back to the server and persisted in the database.

<br/>
<br/>

**Q: What is the advantage of using Thymeleaf forms over traditional HTML forms?**<br/>
A: Thymeleaf forms provide several advantages over traditional HTML forms:

1. Bi-directional Data Binding: Thymeleaf forms support bi-directional data binding, allowing seamless mapping of form fields to object attributes and vice versa. This means that when rendering the form, Thymeleaf can automatically populate the form fields with the existing data from the object. Similarly, when submitting the form, Thymeleaf can map the form field values back to the corresponding object attributes.

2. Expressive Templating: Thymeleaf forms leverage the power of Thymeleaf's templating engine, enabling dynamic content generation and manipulation. With Thymeleaf, you can easily incorporate conditional rendering, loops, and variable expressions within the form template, making it more flexible and adaptable to different scenarios.

3. Integration with Thymeleaf Features: Thymeleaf forms seamlessly integrate with other features provided by Thymeleaf, such as validation, internationalization, and formatting. You can utilize Thymeleaf's validation capabilities to validate form input, apply internationalization to localize form labels and messages, and use formatting functions to format form data appropriately.

**Q: Can Thymeleaf forms be used for both registration and editing?**<br/>
A: Yes, Thymeleaf forms can be used for both registration and editing purposes. The same form template can be utilized for both scenarios by dynamically populating the form fields with the existing data when editing an object. Thymeleaf's bi-directional data binding allows you to easily bind the form fields to the object attributes, making it convenient to display and modify the object data in the form. Whether it is creating a new record or updating an existing one, Thymeleaf forms provide a consistent and efficient approach for handling both registration and editing operations.

<br/>
<br/>

# Let's go through the code and explain each method one by one.

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.shivam.entity.Employee;
import com.app.shivam.exception.EmployeeNotFoundException;
import com.app.shivam.service.IEmployeeService;
import com.app.shivam.util.EmployeeUtil;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService service;

	@GetMapping("/register")
	public String showRegPage(Model model) {
		EmployeeUtil.createDeptList(model);
		return "EmployeeRegister";
	}
}
```

Explanation:
- The `showRegPage` method is mapped to the `/register` URL with a GET request.
- This method is responsible for displaying the registration page for employees.
- It takes a `Model` object as a parameter to pass data to the view.
- Inside the method, `EmployeeUtil.createDeptList(model)` is called to create a list of department names and add it to the model. This list will be used to populate a dropdown menu in the registration form.
- Finally, it returns the name of the view file, "EmployeeRegister", which will be rendered by the view resolver.

Next, let's move on to the next method:

```java
@PostMapping("/save")
public String saveFormData(
        @ModelAttribute Employee employee,
        Model model
        ) 
{
    Integer id = service.saveEmployee(employee);
    String message = new StringBuffer().append("EMPLOYEE '")
            .append(id).append("' CREATED").toString();
    
    model.addAttribute("message", message);
    EmployeeUtil.createDeptList(model);
    return "EmployeeRegister";
}
```

Explanation:
- The `saveFormData` method is mapped to the `/save` URL with a POST request.
- This method is responsible for handling the form submission for employee registration.
- It takes an `Employee` object as a `@ModelAttribute` from the submitted form data and a `Model` object to pass data to the view.
- The `service.saveEmployee(employee)` method is called to save the employee data and it returns the generated employee ID.
- A message is created with the employee ID indicating the successful creation of the employee.
- The message is added to the model using `model.addAttribute("message", message)`.
- The `EmployeeUtil.createDeptList(model)` is called to generate the department list for the dropdown.
- Finally, it returns the name of the view file, "EmployeeRegister", which will be rendered by the view resolver.

Continuing with the next method:

```java
@GetMapping("/all")
public String showData(
        Model model,
        @RequestParam(value = "message", required = false) String message
        ) 
{
    List<Employee> list = service.getAllEmployees();
    model.addAttribute("list", list);
    model.addAttribute("message", message);
    return "EmployeeData";
}
```

Explanation:
- The `showData` method is mapped to the `/all` URL with a GET request.
- This method is responsible for displaying all the employee records in a table format.
- It takes a `Model` object and a request parameter named "message" as inputs.
- The `service.getAllEmployees()` method is called to fetch all the employee records from the database.
- The retrieved list of employees is added to the model using `model.addAttribute("list", list)`.
- The "message" parameter, if provided in the request, is also added to the model.
- Finally, it returns the name of the view file, "EmployeeData", which will be rendered by the view resolver.

Next, let's look at the following method:

```java
@GetMapping("/delete")
public String deleteData(
        @RequestParam("id")Integer empId,
        RedirectAttributes attributes 
        ) 
{
    String msg = null;
    try {
        service.deleteEmployee(empId);
        msg = "Employee '"+empId+"' Deleted";
    } catch (EmployeeNotFoundException e) {
        e.printStackTrace();
        msg = e.getMessage();
    }
    attributes.addAttribute("message", msg);
    return "redirect:all";
}
```

Explanation:
- The `deleteData` method is mapped to the `/delete` URL with a GET request.
- This method is responsible for deleting an employee record based on the provided employee ID.
- It takes the employee ID as a request parameter using `@RequestParam("id")`.
- The `service.deleteEmployee(empId)` method is called to delete the employee record.
- If the deletion is successful, a success message is created with the employee ID.
- If an `EmployeeNotFoundException` occurs during the deletion process, the exception message is stored as the error message.
- The error/success message is added as a request parameter using `attributes.addAttribute("message", msg)`.
- Finally, it returns a redirect to the `/all` URL, which triggers the `showData` method to display all employees with the provided message.

Let's proceed to the next method:

```java
@GetMapping("/edit")
public String showEdit(
        @RequestParam("id") Integer empId,
        Model model,
        RedirectAttributes attributes
        ) 
{
    String page = null;
    try {
        Employee employee = service.getOneEmployee(empId);
        model.addAttribute("employee", employee);
        EmployeeUtil.createDeptList(model);
        page = "EmployeeEdit";
    } catch (EmployeeNotFoundException e) {
        e.printStackTrace();
        attributes.addAttribute("message", e.getMessage());
        page = "redirect:all";
    }
    return page;
}
```

Explanation:
- The `showEdit` method is mapped to the `/edit` URL with a GET request.
- This method is responsible for displaying the employee record in an editable form for editing purposes.
- It takes the employee ID as a request parameter using `@RequestParam("id")`.
- The `service.getOneEmployee(empId)` method is called to retrieve the employee record based on the provided ID.
- If the employee is found, the employee object is added to the model using `model.addAttribute("employee", employee)`.
- The `EmployeeUtil.createDeptList(model)` is called to generate the department list for the dropdown.
- If an `EmployeeNotFoundException` occurs, the exception message is added as a request parameter using `attributes.addAttribute("message", e.getMessage())`.
- In case of an exception, it redirects to the `/all` URL, triggering the `showData` method to display all employees with the error message.
- If the employee is found, it returns the name of the view file, "EmployeeEdit", which will be rendered by the view resolver.

Finally, let's explore the last method:

```java
@PostMapping("/update")
public String updateData(
        @ModelAttribute Employee employee,
        RedirectAttributes attributes
        ) 
{
    service.updateEmployee(employee);
    attributes.addAttribute("message", "Employee '"+employee.getEmpId()+"' Updated!");
    return "redirect:all";
}
```

Explanation:
- The `updateData` method is mapped to the `/update` URL with a POST request.
- This method is responsible for updating the employee record with the modified data from the form.
- It takes the updated employee object as a `@ModelAttribute` from the submitted form data and `RedirectAttributes` to store the success message.
- The `service.updateEmployee(employee)` method is called to update the employee record in the database.
- A success message is created with the employee ID indicating the successful update.
- The success message is added as a request parameter using `attributes.addAttribute("message", "Employee '"+employee.getEmpId()+"' Updated!")`.
- Finally, it returns a redirect to the `/all` URL, which triggers the `showData` method to display all employees with the success message.

That covers the explanation of each method in the `EmployeeController` class.


<br/>
<br/>
<br/>


# The provided code is an HTML template file named "`EmployeeEdit.html`". This file is used to render the employee edit page in the web application. Here's an explanation of the code:

```html
<html xmlns:th="https://www.thymeleaf.org/">
<head>
    <title>WELCOME TO EMPLOYEE EDIT PAGE</title>
</head>
<body>
    <h2>EMPLOYEE EDIT PAGE </h2>
    <form th:action="@{/employee/update}" method="POST" th:object="${employee}">
        <pre>
            ID    : <input type="text" th:field="*{empId}" readonly="true"/>
            NAME  : <input type="text" th:field="*{empName}"/>
            SALARY: <input type="text" th:field="*{empSal}"/>
            DEPT  : <select th:field="*{empDept}">
                        <option value="">--SELECT--</option>
                        <option th:each="dob:${deptList}" 
                                th:value="${dob}" th:text="${dob}"></option>
                   </select>
            <button type="submit">UPDATE</button>                
        </pre>
    </form>
</body>
</html>
```

Explanation:
- The `th` namespace is declared in the `<html>` tag using the `xmlns:th` attribute. It enables the usage of Thymeleaf expressions in the HTML file.
- The `<head>` section contains the title of the page, which is "WELCOME TO EMPLOYEE EDIT PAGE".
- The `<body>` section contains the content of the page.
- The `<h2>` heading displays the text "EMPLOYEE EDIT PAGE".
- The `<form>` tag defines a form that will be submitted to the "/employee/update" URL using the HTTP POST method.
- The `th:action` attribute specifies the URL where the form data will be submitted.
- The `th:object` attribute binds the form to the employee object provided in the `${employee}` model attribute.
- Inside the `<pre>` element, there are input fields and a dropdown select field to edit employee information.
- The "ID" field is read-only (`readonly="true"`) and displays the value of the `empId` property of the employee object.
- The "NAME" field is an input field (`<input type="text">`) bound to the `empName` property of the employee object.
- The "SALARY" field is an input field bound to the `empSal` property of the employee object.
- The "DEPT" field is a dropdown select box (`<select>`) bound to the `empDept` property of the employee object.
  - The first `<option>` element is a default option with the value of an empty string and displays "--SELECT--".
  - The subsequent `<option>` elements are generated using a Thymeleaf iteration (`th:each="dob:${deptList}"`).
  - Each option has a value and text that corresponds to a department value from the `deptList` model attribute.
- The `<button>` element with `type="submit"` is used to submit the form when clicked.
- The closing `</pre>` and `</form>` tags end the respective sections.

This HTML template provides a form for editing employee information. The form is pre-populated with the existing employee data, and the user can make changes and submit the form to update the employee record.