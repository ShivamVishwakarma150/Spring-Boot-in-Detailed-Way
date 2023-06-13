# UI(User Interface) / View

JSP (JavaServer Pages) is a server-side view technology that allows developers to create dynamic web pages by combining static HTML content with Java code. When a JSP file is requested, it is internally converted into a Servlet by the Servlet container.

JSP is considered a heavyweight UI technology because it involves the compilation and execution of Java code. Here's an overview of the process:

1. JSP Compilation: When a JSP file is accessed for the first time, the Servlet container (such as Tomcat or Jetty) translates the JSP code into a Java Servlet class. This conversion is done automatically behind the scenes.

2. Servlet Class Generation: The Servlet container generates a Servlet class that extends the `javax.servlet.jsp.HttpJspBase` class. This generated Servlet class contains the equivalent Java code for the JSP file.

3. Compilation of Generated Servlet: The generated Servlet class is then compiled into bytecode by the Java compiler.

4. Servlet Instance Creation: An instance of the Servlet class is created, and it becomes responsible for handling the requests and generating the dynamic content.

5. Servlet Execution: When a client requests the corresponding URL mapped to the JSP, the Servlet container invokes the `service()` method of the generated Servlet instance. The Servlet code dynamically generates the response based on the logic and content defined in the JSP file.

Regarding your point about static and dynamic content in JSP, you're correct that even static content defined in a JSP file, such as HTML tags like `<p>` or `<div>`, is converted into Java code during the JSP compilation process. The generated Servlet code will contain calls to the `out.write()` method to output the static content.

Dynamic content in JSP, on the other hand, is typically expressed using JSP tags and expressions, such as `<c:forEach>`. These tags allow you to iterate over collections, conditionally render content, and more. The dynamic parts are evaluated and executed during the request processing phase when the JSP is transformed into the Servlet and the corresponding Java code is executed.

Overall, JSP provides a convenient way to combine static HTML content with dynamic Java code, allowing developers to build dynamic web pages easily.

<br/>
<br/>

# Thymeleaf

Thymeleaf is a lightweight Java-based UI engine that is used for designing dynamic web pages in web applications. It provides a simpler alternative to JSP (JavaServer Pages) and is known for its ease of use and reduced memory footprint.

Key points about Thymeleaf:

1. Lightweight: Thymeleaf is considered lightweight compared to JSP because it does not rely on the conversion of JSP files into servlets. Thymeleaf operates directly on simple Java code, making it more efficient in terms of memory usage.

2. HTML-based: Thymeleaf templates are standard HTML files that can contain both static HTML tags and Thymeleaf-specific tags. Static tags represent standard HTML elements, while Thymeleaf tags enable the insertion of dynamic content into the HTML template.

3. Java code execution: Thymeleaf tags are converted into Java code, executed, and the result is placed back into the same HTML file. This process is known as rendering. The dynamic behavior of Thymeleaf templates is achieved through the execution of Java code embedded within the templates.

4. Thymeleaf namespace: The Thymeleaf namespace is defined using the `xmlns:th` attribute in the HTML file. This namespace declaration indicates that the subsequent attributes or tags prefixed with `th` are processed by the Thymeleaf engine.

5. Predefined tags and attributes: Thymeleaf provides a set of predefined tags and attributes that can be used to perform various operations, such as conditionals, loops, variable manipulations, and data binding. These tags and attributes are defined in the Thymeleaf server namespace (`https://www.thymeleaf.org/`).

6. File extension: Thymeleaf templates typically use the `.html` file extension to differentiate them from regular HTML files. This extension indicates that the HTML file contains Thymeleaf-specific tags and attributes that need to be processed by the Thymeleaf engine.

By using Thymeleaf, developers can easily create dynamic web pages by leveraging the simplicity of HTML and the power of Java code execution. Thymeleaf provides an intuitive way to integrate dynamic data into HTML templates, making it a popular choice for web application development.

<br/>
<br/>


# Here's an explanation of the symbols used in Thymeleaf:

1. `$` (Dollar Symbol):
   - The dollar symbol `$` is used to read data from the model, model map, or map.
   - It is used within Thymeleaf expressions to access and display data in the HTML template.
   - For example, `th:text="${variableName}"` or `[[${variableName}]]` is used to display the value of `variableName` from the model.

2. `@` (At Symbol):
   - The at symbol `@` is used to refer to URLs, paths, or locations within Thymeleaf.
   - It is used for URL mapping, linking external resources, or defining URL-based attributes.
   - For example, `th:href="@{/css/styles.css}"` is used to reference a CSS file located at `/css/styles.css`.

3. `*` (Asterisk Symbol):
   - The asterisk symbol `*` is used specifically for forms in Thymeleaf.
   - It is used for input name binding, allowing form input values to be bound to corresponding variables in the model.
   - For example, `<input type="text" th:field="*{username}" />` is used to bind the input value of the `username` field to a variable in the model.

These symbols play a crucial role in the Thymeleaf template engine to interact with the model, define URLs, and handle form data binding.

<br/>
<br/>

# Here are the code snippets with their explanations:

1. `th:text="${emp.empName}"` or `[[ ${emp.empName} ]]`:
   ```html
   <p th:text="${emp.empName}"></p>
   <!-- or -->
   <p>[[${emp.empName}]]</p>
   ```
   Explanation: This code is used to display the value of the `empName` property from the `emp` object. The Thymeleaf expression `th:text` or `[[...]]` is used to evaluate the expression `${emp.empName}` and display the result as text within the `<p>` element.

2. `<form th:action="@{/employee/save}"`:
   ```html
   <form th:action="@{/employee/save}" method="post">
       <!-- form elements -->
   </form>
   ```
   Explanation: This code sets the action attribute of a form element. The `th:action` attribute specifies the URL where the form data should be submitted. In this case, the form data will be submitted to the `/employee/save` URL using the HTTP POST method.

3. `<script type="text/javascript" th:src="@{/myjs/sample.js}"`:
   ```html
   <script type="text/javascript" th:src="@{/myjs/sample.js}"></script>
   ```
   Explanation: This code is used to include an external JavaScript file in the HTML page. The `th:src` attribute specifies the URL of the JavaScript file. In this case, the JavaScript file `sample.js` will be included from the `/myjs` path.

4. `<tr th:each="ob:${list}">`:
   ```html
   <table>
       <tbody>
           <tr th:each="ob:${list}">
               <td th:text="${ob.property}"></td>
               <!-- more table cells -->
           </tr>
       </tbody>
   </table>
   ```
   Explanation: This code is used to iterate over a collection (`list`) and generate multiple table rows (`<tr>`). The `th:each` attribute specifies the collection `${list}` to iterate over. Inside the loop, the current element of the collection is referred to as `ob`. You can access properties of the `ob` object using `th:text` or `[[...]]` within the table cells.

In conclusion, these code snippets demonstrate the usage of Thymeleaf expressions and attributes for dynamic content rendering, form submission, including external resources, and iterating over collections.

<br/>
<br/>
<br/>

# Here's a detailed explanation based on the points you mentioned:

1. `ctrl+shift+T: ThymeleafProperties`:
   Pressing `ctrl+shift+T` (or any similar shortcut) in your IDE allows you to quickly navigate to the ThymeleafProperties class. This class provides configuration properties for Thymeleaf in a Spring Boot application. You can use it to customize the behavior of Thymeleaf.

2. Thymeleaf Auto-Configuration:
   When you add the Thymeleaf dependency (`spring-boot-starter-thymeleaf`) to your project, Spring Boot automatically provides auto-configuration for Thymeleaf. This means that Thymeleaf-related settings and features are automatically detected, loaded, and executed without requiring explicit configuration.

3. Default Prefix and Suffix:
   By default, Spring Boot configures Thymeleaf to use a specific folder structure and file extension for your Thymeleaf templates. The default prefix for template files is `/templates/`, which is located in the classpath of your project. The default suffix for template files is `.html`. This means that Thymeleaf will look for HTML templates inside the `/templates/` folder and treat them as Thymeleaf templates.

4. Project Structure:
   In a standard Spring Boot project structure, the `src/main/resources` directory is the location where you store resources that are accessible at runtime. Within this directory, you typically have a `templates` folder where you store your Thymeleaf templates. This is the default location that Thymeleaf will look for templates based on its auto-configuration.

   Here's an example project structure:
   ```
   [ProjectName]
   |
   |- src/main/resources
      |- static       --> Contains static resources (images, CSS files, JavaScript files)
      |- templates    --> Contains Thymeleaf templates
         |- Home.html --> Example Thymeleaf template file
   ```

In conclusion, the Thymeleaf auto-configuration in Spring Boot simplifies the setup and usage of Thymeleaf in your application. By default, it assumes a specific project structure with a `templates` folder for Thymeleaf templates, and it allows you to customize the behavior through properties such as the prefix and suffix for template files.

<br/>

# NOTES

1. `<div th:text="${keyName}">`:
   This Thymeleaf expression is used to display the value of the `keyName` variable inside a `<div>` element. The `th:text` attribute is used to assign the value of the `keyName` variable as the text content of the `<div>` element. When the template is rendered, Thymeleaf will replace this expression with the actual value of the `keyName` variable.

2. `<span th:text="${keyName}">`:
   This Thymeleaf expression is similar to the previous one, but it is used to display the value of the `keyName` variable inside a `<span>` element. The `th:text` attribute is used to assign the value of the `keyName` variable as the text content of the `<span>` element. Thymeleaf will replace this expression with the actual value of the `keyName` variable when rendering the template.

In both cases, the `th:text` attribute allows you to dynamically insert the value of the `keyName` variable into the HTML template. This is useful when you want to display dynamic data retrieved from a model or other data source.

In conclusion, the Thymeleaf expressions `<div th:text="${keyName}">` and `<span th:text="${keyName}">` are used to display the value of the `keyName` variable within `<div>` and `<span>` elements, respectively, by replacing the expressions with the actual variable value during template rendering.

<br/>
<br/>

# Here's the code snippets you provided with their explanations:

1. Controller class (ProductController.java):
```java
package com.app.shivam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/data")
	public String showData(Model model) {
		model.addAttribute("prodCode", "SAMPLE ABCD!");
		return "ProductData";
	}
}
```
Explanation:
- This is a controller class named `ProductController` annotated with `@Controller`.
- It handles requests related to the `/product` URL path.
- The `showData` method is mapped to the `/data` endpoint using the `@GetMapping` annotation.
- It takes a `Model` object as a parameter to add attributes for the view.
- Inside the method, it adds a `prodCode` attribute with the value `"SAMPLE ABCD!"` to the model.
- Finally, it returns the name of the view template, which is "ProductData".

2. UI Page: ProductData.html:
```html
<html xmlns:th="https://www.thymeleaf.org/">
	<head>
		<title> WEB APP </title>
		<!--To Link a CSS File -->
		<link rel="stylesheet" th:href="@{/mycss/design.css}"></link>
		<!--To Link a JS File -->
		<script type="text/javascript" th:src="@{/myjs/setup.js}"></script>
	</head>
	<body>
		<!--To Display Image -->
		<img th:src="@{/images/doctor.png}" width="100" height="150"/>
		<h2>WELCOME TO SPRING BOOT + THYMELEAF EXAMPLE </h2>
		
		<p class="mydesign"> SAMPLE CSS </p>
		
		<!--To Read and Print Model Data -->
		<p> This is product code <span th:text="${prodCode}"></span> </p>
		<p> This is product code [[${prodCode}]] </p>
	</body>
</html>
```
Explanation:
- This is the HTML template file named "ProductData.html".
- It uses the `xmlns:th` attribute to declare the Thymeleaf namespace.
- The template contains various HTML elements and Thymeleaf expressions.
- It includes a CSS file using the `th:href` attribute and a JavaScript file using the `th:src` attribute.
- An image is displayed using the `th:src` attribute with the path to the image file.
- It contains heading, paragraph, and span elements to demonstrate printing data from the model using Thymeleaf expressions.

3. CSS File (design.css):
```css
.mydesign {
	color: green;
	background-color: yellow;
}
```
Explanation:
- This is a CSS file named "design.css".
- It defines a CSS class `.mydesign` with properties for text color and background color.

4. JS File (setup.js):
```javascript
alert("WELCOME TO ALL!!!");
```
Explanation:
- This is a JavaScript file named "setup.js".
- It contains a simple JavaScript code that displays an alert message.

5. Place one image under static folder:
- The image file "doctor.png" should be placed under the `static` folder in the project's resources directory.

6. application.properties:
```
server.port=9090
```
Explanation:
- This is the application properties file.
- It sets the server port to 9090 for the application to run on.

In conclusion, the provided code represents a Spring Boot web application with Thymeleaf integration. The `ProductController` handles requests for the `/product/data` URL and adds a `prodCode` attribute to the model. The "ProductData.html" template displays the image, headings, CSS-styled paragraph, and prints the `prodCode` attribute value using Thymeleaf expressions. The CSS and JavaScript files add styling and functionality to the web page.
