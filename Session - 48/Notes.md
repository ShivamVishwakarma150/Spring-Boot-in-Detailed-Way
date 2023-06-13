# **Spring Boot Web MVC `HTML Forms`  [Part-1]**
		         

**Html Forms:**
HTML forms are used to collect user input data on a web page. They provide a way for users to enter information and submit it to a server for processing. Forms can include various types of input fields such as text fields, radio buttons, checkboxes, dropdown lists, and more.

**Model:**
In the context of Spring Boot web MVC, the model represents the data that is sent from the controller to the view. It holds the data that will be displayed or used by the view to render the HTML content. The model can be populated with data in the controller and then accessed in the view using expressions.

**Form:**
The form is an essential element in HTML that allows users to input data and submit it to the server. It acts as a container for various input fields and includes attributes such as `action` (the URL where the form data should be submitted) and `method` (the HTTP method to be used, typically GET or POST).

When a user submits a form, the data entered in the input fields is sent as a request to the server. The server-side application (in this case, the Spring Boot controller) receives the form data and processes it accordingly.

The flow of data in a typical HTML form scenario is as follows:
1. The form is displayed to the user on a web page.
2. The user enters data into the input fields within the form.
3. The user submits the form by clicking a submit button.
4. The form data is sent as a request to the server.
5. The server-side application (the Spring Boot controller) receives the form data.
6. The controller processes the data and performs the necessary operations.
7. The controller may update the model with any relevant data.
8. The controller returns a response, which could be a rendered view or a JSON/XML response, depending on the application's requirements.
9. The view (HTML template) renders the response, including any updated data from the model.
10. The updated view is sent back to the user's browser, where it is displayed.

In Spring Boot web MVC, the form data can be bound to model objects in the controller, making it easier to handle and process the submitted data. This data binding can be achieved using various annotations such as `@RequestParam`, `@ModelAttribute`, or `@RequestBody`, depending on the specific requirements of the application.

Overall, HTML forms play a crucial role in gathering user input, and in the context of Spring Boot web MVC, they allow for seamless communication between the user, the server, and the application's model and controller components.

<br/>
<br/>

# Notes

When an end user enters data and submits a form in a Spring Boot web MVC application, the Spring container is responsible for creating a form object and setting the submitted data to that object. This process involves several steps:

1. **Creating the form object**: The Spring container creates an instance of the form class using its default constructor. This object serves as a container to hold the form data submitted by the user.

2. **Reading form data**: The container reads the form data submitted by the user. It retrieves the values of each input field in the form using the `req.getParameter`(used in Advance Java) method. This method allows the container to access the submitted data based on the input field's name attribute.

3. **Parsing data (if required)**: In some cases, the submitted form data may need to be parsed to a specific data type. For example, if the form includes a field for an integer value, the container may need to convert the string value from the form to an actual integer using methods like `Integer.parseInt`.

4. **Setting data to variables**: Once the form data has been read and parsed (if necessary), the container sets the data to the corresponding variables in the form object using setter methods. For example, if the form includes a field named "id", the container would call the `setId` method on the form object and pass the submitted value as an argument (`obj.setId(10)`).

It is important to note that the programmer needs to define a separate class for each form in the application. This class represents the structure and fields of the form. The form class typically contains private variables representing the form fields, corresponding getter and setter methods, and any additional methods or annotations required for form processing.

By following this approach, the Spring container handles the creation of form objects, reading and parsing form data, and setting the data to the form object automatically. This simplifies the process of handling form submissions and allows developers to focus on implementing the business logic related to the form data within the controller.

```
    1. Write one public class with any name
    2. Define variables (no.of inputs = no.of variables) with setters.
    3. Form Input name must match with variable Name
    <input type="" name="<variableName>" .../>
    <select        name="<variableName>"   ...
    <textarea      name="<variableName>" ...

```


1. **Write one public class with any name:**
```java
public class RegistrationForm {
    // Class members will be added here
}
```

2. **Define variables (no. of inputs = no. of variables) with setters:**
```java
public class RegistrationForm {
    private String firstName;
    private String lastName;
    private int age;
    
    // Setters for each variable
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
```

In this step, we define the variables that correspond to the inputs in the form. Each variable is private and belongs to the `RegistrationForm` class. We also create setter methods for each variable, allowing us to set the values of these variables.

3. **Form input name must match with variable name:**
```html
<form>
    <input type="text" name="firstName" .../>
    <input type="text" name="lastName" .../>
    <input type="number" name="age" .../>
    ...
</form>
```

To ensure that the form data is correctly bound to the `RegistrationForm` object, the `name` attribute of each input field in the HTML form should match the variable name in the `RegistrationForm` class. This allows the Spring framework to automatically map the form data to the corresponding variables.

For example, in the code snippet above, the `name` attribute of the first input field is "firstName," which matches the variable name `firstName` in the `RegistrationForm` class. Similarly, the other input fields' names match their respective variable names (`lastName` and `age`).

By following this naming convention, the Spring framework can map the form data to the corresponding variables in the `RegistrationForm` object automatically.

Overall, by creating a class with variables and setters, and ensuring that the form input names match the variable names, you can establish a connection between the form inputs and the `RegistrationForm` object. This allows the Spring framework to handle the form data binding for you.


<br/>
<br/>
<br/>

# Notes

1. **Object name will be className first letter lowercase:**
When you use a class as a model attribute in Spring MVC, the object name conventionally follows the camelCase style, starting with a lowercase letter. For example, if your class name is `Employee`, the object name would be `employee`.

2. **This object is called as Model Attribute:**
The object created for the form data binding is referred to as a model attribute. It represents the data submitted from the form and is created for every request. The model attribute remains in the Spring container until the response is committed.

3. **Reading the model attribute inside the controller method:**
To access the model attribute inside a controller method, you can use one of two syntaxes:

   Syntax 1:
   ```java
   @RequestMapping(value = "/processForm", method = RequestMethod.POST)
   public String processForm(@ModelAttribute("employee") Employee emp) {
       // Access the model attribute "employee" using the local variable "emp"
       // Perform necessary operations with the form data
       return "result";
   }
   ```

   Syntax 2:
   ```java
   @RequestMapping(value = "/processForm", method = RequestMethod.POST)
   public String processForm(@ModelAttribute Employee employee) {
       // Access the model attribute "employee" directly
       // Perform necessary operations with the form data
       return "result";
   }
   ```

   In both syntaxes, the `@ModelAttribute` annotation is used to indicate that the parameter is bound to a model attribute. The model attribute name is specified within the annotation as "employee". This allows Spring to identify and bind the form data to the corresponding object.

   The model attribute can then be accessed and used within the controller method to perform any required operations with the form data.

By following these conventions and utilizing the `@ModelAttribute` annotation, you can effectively read and handle the model attribute (form data) within your controller methods in Spring MVC.

<br/>
<br/>

# Here's the complete code with explanations:

1. Bean class (`Employee`):
```java
package com.app.shivam.bean;

import lombok.Data;

@Data
public class Employee {

	private Integer empId;
	private String empName;
	private Double empSal;
	
	private String empPwd;
	private String empDept;
	private String empAddr;
}
```
Explanation:
- The `Employee` class is a simple POJO (Plain Old Java Object) that represents an employee.
- It uses Lombok's `@Data` annotation to generate getters, setters, `toString()`, and other useful methods automatically.

2. Controller class (`EmployeeController`):
```java
package com.app.shivam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.shivam.bean.Employee;

@Controller
public class EmployeeController {

	@GetMapping("/show")
	public String showForm() {
		return "EmpRegister";
	}
	
	@PostMapping("/register")
	public String readData(
			@ModelAttribute Employee employee,
			Model model
			) 
	{
		System.out.println(employee);
		model.addAttribute("obj", employee);
		return "EmpData";
	}
}
```
Explanation:
- The `EmployeeController` class is a Spring MVC controller responsible for handling HTTP requests related to the employee registration form.
- The `showForm()` method is mapped to the `/show` URL path and handles GET requests. It returns the view name `"EmpRegister"`, which corresponds to the `EmpRegister.jsp` file.
- The `readData()` method is mapped to the `/register` URL path and handles POST requests. It takes the submitted form data as a `@ModelAttribute` named `employee`. It prints the employee object to the console and adds it as an attribute named `"obj"` to the `Model`. Finally, it returns the view name `"EmpData"`, which corresponds to the `EmpData.jsp` file.

3. UI Pages:
- `EmpRegister.jsp`:
```html
<html>
	<head>
		<title>WELCOME TO APP</title>
	</head>
	<body>
		<pre>
		<h2>WELCOME TO REGISTER FORM </h2>
		<form action="register" method="POST">
			ID  : <input type="text" name="empId"/>
			NAME: <input type="text" name="empName"/>
			SAL : <input type="text" name="empSal"/>
			PWD : <input type="password" name="empPwd"/>
			DEPT: <select name="empDept">
					<option value="DEV">DEV</option>
					<option value="QA">QA</option>
					<option value="BA">BA</option>
				  </select>
		    ADDR: <textarea name="empAddr"></textarea>
			<input type="submit" value="CREATE"/>
		</form>
		</pre>
	</body>
</html>
```
- `EmpData.jsp`:
```html
<html>
	<head>
		<title>WELCOME TO APP</title>
	</head>
	<body>
		<pre>
		<h2>WELCOME TO DATA  : ${obj} </h2>
		</pre>
	</body>
</html>
```
Explanation:
- `EmpRegister.jsp` is the HTML form page that displays the registration form to the user. It includes input fields for employee ID, name, salary, password, department (select dropdown), and address (textarea). The form's action is set to `/register`, and the method is

 `POST`.
- `EmpData.jsp` is the page that displays the submitted employee data. It retrieves the employee object from the `Model` using the attribute name `"obj"` and displays it using `${obj}`.

4. `application.properties`:
```properties
# Port number details
server.port=9090

# View Resolver details
# ---prefix must start with / and end with /
spring.mvc.view.prefix=/WEB-INF/pages/
spring.mvc.view.suffix=.jsp
```
Explanation:
- The `application.properties` file contains the configuration properties for the Spring Boot application.
- `server.port` sets the port number for the application to run on, in this case, `9090`.
- `spring.mvc.view.prefix` and `spring.mvc.view.suffix` define the prefix and suffix for the view resolver. In this case, the prefix is `/WEB-INF/pages/` and the suffix is `.jsp`. This configuration ensures that JSP files located in the specified directory are used as views.

To run the application, you can access the following URL in your browser: `http://localhost:9090/show`. This will display the employee registration form. After filling in the details and submitting the form, the data will be printed to the console, and the employee data page will be displayed with the submitted data.

In conclusion, this code demonstrates a basic example of handling a form submission in Spring Boot using Spring MVC. It shows how to create a form, bind form data to a bean class, and display the submitted data on a separate page.

<br/>
<br/>

# Some FAQ's

**Q) Who will create the Form class object when we submit the Form?** <br/>
A) The Spring container is responsible for creating the Form class object when we submit the Form. It follows a series of steps to create the object.

**Q) Do we need to define a Form class in the application?** <br/>
A) Yes, we need to define a Form class in the application. For each Form, we must create a corresponding class. This class represents the structure and fields of the Form data.

**Q) How can we bind variable names with Form inputs?** <br/>
A) We can bind variable names with Form inputs by using the `name` attribute. The `name` attribute of the input elements in the HTML form should match the variable names in the Form class.

**Q) How can we read the Form object in the controller?** <br/>
A) We can read the Form object in the controller using the `@ModelAttribute` annotation. By annotating a parameter of a controller method with `@ModelAttribute`, Spring binds the submitted form data to an instance of the corresponding Form class.

**Q) What if we miss data binding (i.e., variable with Form names)?** <br/>
A) If we miss the data binding, i.e., the variable names in the Form class do not match the names of the form inputs, there will be no error or exception. However, the data will not be successfully bound, and the variables in the Form object will hold their default values.

For POST requests, the data is sent using the Request Body (hidden) to the URL `http://localhost:9090/register`.

For GET requests, the data is sent using the URL parameters to the URL `http://localhost:9090/register?empId=10&empName=AA&empSal=200&empPwd=ABC&empDept=QA&empAddr=HYD`.

In summary, the Spring container creates the Form class object when we submit the form, and it follows the data binding rules based on the variable names and form input names. By using the `@ModelAttribute` annotation, we can easily read the form object in the controller. Missing data binding will not throw an error, but the data will not be successfully bound.