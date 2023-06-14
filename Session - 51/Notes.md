# Thymeleaf Syntax Explained

Thymeleaf is a Java-based templating engine commonly used in web development. Let's go through each point you mentioned and provide a detailed explanation:

1. Light weight (Less Memory):
Thymeleaf is designed to be lightweight, meaning it consumes fewer system resources such as memory compared to other templating engines. This makes it suitable for various environments, including resource-constrained systems or applications with high concurrency requirements.

2. Uses prefix - th:
In Thymeleaf, the "th" prefix is used as a marker for Thymeleaf-specific attributes. These attributes are recognized by the Thymeleaf engine and processed accordingly. By using this prefix, Thymeleaf can differentiate between its own attributes and regular HTML attributes.

3. Connects to namespace -- xmlns:th="https://www.thymeleaf.org/":
To enable Thymeleaf attributes in your HTML files, you need to declare the "th" namespace in the root element of the HTML file. The namespace declaration looks like `xmlns:th="https://www.thymeleaf.org/"`. This tells the parser that the "th" prefix corresponds to the Thymeleaf namespace, allowing you to use Thymeleaf-specific attributes.

4. Symbols used:
Thymeleaf uses specific symbols for various purposes:

- \$: The dollar sign (\$) is used to read data from the model. In Thymeleaf, you can access data stored in the model (server-side data) using expressions like `${variableName}`. Thymeleaf will evaluate the expression and replace it with the corresponding value during rendering.

- @: The at symbol (@) is used for URLs or locations. In Thymeleaf, you can create dynamic URLs by using expressions like `@{URLPath}`. Thymeleaf will handle URL encoding and appending parameters, if necessary, based on the expression provided.

- \*: The asterisk (\*) is used for form inputs. Thymeleaf provides a shorthand notation for generating form input fields. You can define form fields using expressions like `*{fieldName}`. Thymeleaf will generate appropriate HTML input fields with the name and value based on the expression provided.

These symbols are integral to Thymeleaf's syntax and help you work with data, URLs, and form inputs in a concise and efficient manner.

By understanding these key aspects of Thymeleaf, you'll be able to leverage its features effectively and enhance your web development projects.

<br/>
<br/>

# Bootstrap

Bootstrap is a popular CSS framework that provides a collection of pre-defined classes and components to streamline the process of designing user interfaces (UI) for web pages. Initially developed by Twitter, Bootstrap offers a set of responsive grid systems, typography styles, CSS classes, and JavaScript plugins, making it easier to create visually appealing and mobile-friendly websites.

The link you provided, https://www.w3schools.com/bootstrap4/bootstrap_colors.asp, specifically refers to the Bootstrap 4 documentation on color classes. This documentation page showcases the various color utility classes available in Bootstrap 4, which can be used to style elements with different colors. Let's explore some key aspects of Bootstrap:

1. CSS API:
Bootstrap provides a CSS API (Application Programming Interface) that consists of a set of predefined CSS classes. These classes can be applied to HTML elements in your web page to achieve desired styling and layout effects. By utilizing the Bootstrap CSS API, you can easily control the appearance of your UI elements without having to write extensive custom CSS code.

2. Pre-defined classes:
Bootstrap comes with a wide range of pre-defined classes that cover various aspects of UI design. These classes offer consistent styling across different browsers and devices, ensuring a cohesive look and feel for your website. By applying these classes to your HTML elements, you can quickly achieve a professional and polished UI without starting from scratch.

3. Responsive grid system:
One of the key features of Bootstrap is its responsive grid system. It allows you to create responsive layouts by dividing the webpage into rows and columns. With Bootstrap's grid classes, you can specify how elements should behave and rearrange themselves on different screen sizes, making your website adaptable to various devices and screen resolutions.

4. Typography styles:
Bootstrap provides a set of typography styles that enable you to easily define headings, paragraphs, lists, and other text elements with consistent and visually appealing styles. These typography classes help maintain readability and hierarchy within your content.

5. JavaScript plugins:
Bootstrap also includes a collection of JavaScript plugins that can enhance the functionality and interactivity of your website. These plugins cover various aspects such as carousels, modals, dropdowns, tooltips, and more. By incorporating Bootstrap's JavaScript plugins into your project, you can add dynamic elements and user-friendly features without having to write complex JavaScript code from scratch.

Overall, Bootstrap simplifies the process of designing UI pages by providing a comprehensive set of CSS classes, components, and JavaScript plugins. It promotes rapid development, responsiveness, and a consistent visual style across different devices. By referring to the official documentation and exploring the available features, you can leverage Bootstrap effectively to create attractive and functional web pages.

<br/>
<br/>

# Let's delve into the notes you provided and expand on each point:

1. `th:each="ob:${list}`"
   Syntax: th:each="tempvariable:\${CollectionName}"
   Here, th:each acts like a forEach loop. It allows you to iterate over a collection (such as a list or an array) in Thymeleaf templates. The syntax follows the pattern `th:each="tempvariable:${CollectionName}"`. In this case, "ob" is the temporary variable that represents each item in the "list" collection. You can use this variable to access the properties of each object within the collection.

   The "list" represents the key name that is sent from the Controller class. In the Controller, you would typically add the "list" object to the model, making it available in the Thymeleaf template. By using th:each, you can loop through the elements of the "list" collection and perform actions or display data accordingly.

2. th:text="\${ob.bookId}"
   In Thymeleaf, the th:text attribute is used to print data on a web page. It assigns the value of the specified expression to the content of the HTML element to which it is applied. In this case, ${ob.bookId} is the expression that reads data from the model.

   \${ob.bookId} accesses the "bookId" property of the "ob" object. The "ob" object represents an individual item within the collection (as defined in the th:each loop). Thymeleaf evaluates this expression and replaces the content of the HTML element with the value of "ob.bookId" during rendering. This allows you to dynamically display data from the model in your web page.

By using th:each and th:text together, you can iterate over a collection, access its properties using a temporary variable, and display the desired data within your Thymeleaf template. This combination enables you to dynamically generate content based on the data provided by the Controller.

<br/>
<br/>
<br/>

## Let's break down the provided code into separate sections and provide explanations for each part. We'll start with the Bean class and its explanation, followed by the Controller class and its explanation, and so on. At the end, we'll summarize the key points.

1. Bean (Book.java):

```java
package com.app.shivam.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Integer bookId;
    private String bookName;
    private String bookAuth;
    private Double bookCost;
}
```

Explanation:
- The `Book` class represents a bean or a model object for a book entity. It contains properties such as `bookId`, `bookName`, `bookAuth`, and `bookCost`.
- Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) are used to generate boilerplate code for getter/setter methods, constructors, and other common methods.

2. Controller class (BookController.java):

```java
package com.app.shivam.controller;

// Import statements omitted for brevity

@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping("/show")
    public String showData(Model model) {
        Book b1 = new Book(101, "Core Java", "shivam", 200.0);
        model.addAttribute("bob", b1);
        return "BookData";
    }

    @GetMapping("/list")
    public String showColl(Model model) {
        List<Book> list = Arrays.asList(
                new Book(10, "AA", "XYZ", 200.0),
                new Book(11, "AB", "XYZ", 300.0),
                new Book(12, "AC", "XYZ", 400.0),
                new Book(13, "AD", "MNO", 500.0)
        );
        model.addAttribute("list", list);
        return "BookList";
    }
}
```

Explanation:
- The `BookController` class serves as the controller for handling book-related requests.
- It is annotated with `@Controller` to indicate that it's a Spring MVC controller.
- The base URL for book-related requests is specified as `/book` using `@RequestMapping`.
- The `showData` method handles the GET request for `/book/show` URL. It creates a `Book` object `b1` and adds it to the model with the attribute name "bob". It returns the view name "BookData".
- The `showColl` method handles the GET request for `/book/list` URL. It creates a list of `Book` objects and adds it to the model with the attribute name "list". It returns the view name "BookList".

3. UI Pages:

a. BookData.html:

```html
<html xmlns:th="https://www.thymeleaf.org/">
<head>
    <title> WELCOME </title>
</head>
<body>
    <h3>DATA IS </h3>
    <span th:text="${bob}"></span> <br/>
    <span th:text="${bob.bookId}"></span> <br/>
    [[${bob}]],[[${bob.bookName}]]
</body>
</html>
```

Explanation:
- This Thymeleaf HTML template is used to display book data.
- The `th` namespace is declared with `xmlns:th="https://www.thymeleaf.org/"`.
- The `span` elements with `th:text` attributes display the values of `bob` and `bob.bookId` from the model.

b. BookList.html (No UI Design):

```html
<html xmlns:th="https://www.thymeleaf.org

/">
<head>
    <title> WELCOME </title>
</head>
<body>
    <h3> BOOKS DETAILS </h3>
    <table>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>AUTHOR</th>
            <th>COST</th>
        </tr>
        <tr th:each="ob:${list}">
            <td th:text="${ob.bookId}"></td>
            <td th:text="${ob.bookName}"></td>
            <td>[[${ob.bookAuth}]]</td>
            <td>[[${ob.bookCost}]]</td>
        </tr>
    </table>
</body>
</html>
```

Explanation:
- This Thymeleaf HTML template is used to display a list of books.
- The `th` namespace is declared with `xmlns:th="https://www.thymeleaf.org/"`.
- The `tr` element with `th:each` iterates over each book in the `list` model attribute.
- The `td` elements with `th:text` display the book properties from the model.

c. BookList.html (Using Bootstrap UI):

```html
<html xmlns:th="https://www.thymeleaf.org/">
<head>
    <title> WELCOME </title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h3 class="bg-info text-white text-center"> BOOKS DETAILS </h3>
    <table class="table table-hover">
        <tr class="bg-primary text-white">
            <th>ID</th>
            <th>NAME</th>
            <th>AUTHOR</th>
            <th>COST</th>
            <th>OPERATIONS</th>
        </tr>
        <tr th:each="ob:${list}">
            <td th:text="${ob.bookId}"></td>
            <td th:text="${ob.bookName}"></td>
            <td>[[${ob.bookAuth}]]</td>
            <td>[[${ob.bookCost}]]</td>
            <td>
                <button class="btn btn-danger">DELETE</button> |
                <button class="btn btn-info">EDIT</button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
```

Explanation:
- This Thymeleaf HTML template is similar to the previous `BookList.html` but includes Bootstrap CSS styles.
- The Bootstrap CSS is imported from the CDN (`https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css`).
- The table and button elements are styled using Bootstrap classes to provide a visually appealing UI.

4. application.properties:
```
server.port=9090
```

Explanation:
- The `application.properties` file specifies the server port to be used when running the application.

5. Conclusion:
- The provided code showcases the use of Spring Boot, Thymeleaf, and Bootstrap to create a simple book management application.
- The `Book` class serves as the model object representing a book entity.
- The `BookController` class handles requests related to books and uses Thymeleaf templates to display book data.
- The Thymeleaf templates (`BookData.html` and `BookList.html`) demonstrate how to access and display data from the model in HTML.
- The `BookList.html` template also shows how to enhance the UI using Bootstrap styles.

By running the application and accessing the specified URLs (`http://localhost:9090

/book/show` and `http://localhost:9090/book/list`), you can see the rendered views with book data. The first URL displays individual book data, while the second URL shows a table of books with enhanced UI using Bootstrap styles.