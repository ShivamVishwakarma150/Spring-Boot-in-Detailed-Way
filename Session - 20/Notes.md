# Let's go through each point and provide a detailed explanation:

1. If a key is not found, the `@ConfigurationProperties` annotation allows you to provide default values for the corresponding variables. If a key is missing in the properties file or the configuration source, the variable will be initialized with the default value specified in the annotation.

2. When using `@ConfigurationProperties`, setter methods are not required for the variables. The framework uses reflection to directly set the values of the variables, so you don't need to write explicit setter methods for each property.

3. The `prefix` attribute in `@ConfigurationProperties` allows you to specify a prefix for the properties that should be bound to the annotated class. Only properties with keys that start with the specified prefix will be mapped to the class variables. If the prefix is not matched, the properties will be ignored.

Now, let's move on to the different types of variables that can be used with `@ConfigurationProperties`:

- Collection Variables:
   - List/Array: To bind properties to a list or an array, you need to specify the index of the element in the property key. For example, if you have a property key like `prefix.variable[0]=value`, it will bind the value to the first element of the list or array.
   - Set: The same syntax applies to set variables as well. You can specify the index in the property key, and the corresponding value will be bound to the set at that index.
   - Map/Properties: To bind properties to a map or properties object, you need to use the syntax `prefix.variable.mapKey=mapVal`. Here, `mapKey` represents the key of the map or properties, and `mapVal` represents the corresponding value.

- Reference Type Variables:
   - If you have a reference type variable within your configuration class, you can bind its properties using the `prefix.refvariable.variable=value` syntax. Here, `refvariable` represents the name of the reference variable, and `variable` represents the property of the referenced object that you want to bind.

Finally, let's discuss why you should not use `@Component` over a child class and `@Autowired` over a HAS-A variable when using `@ConfigurationProperties`:

- When you annotate a class with `@ConfigurationProperties`, the framework creates an instance of that class and binds the properties to its variables based on the prefix and property keys. However, if you also annotate the child class with `@Component`, it will create another instance of the child class separately, leading to unexpected behavior and duplicate instances.

- Similarly, you should avoid using `@Autowired` over a HAS-A (composition) variable within a `@ConfigurationProperties` annotated class. The framework automatically initializes the object and binds the properties to its variables. Using `@Autowired` on a HAS-A variable can lead to conflicts and unexpected behavior.

By following these guidelines, you ensure that the `@ConfigurationProperties` annotation functions as expected, creating a single instance of the configuration class and properly binding the properties to its variables.

<br/>
<br/>
<br/>

```java
    ======Ex:2 =======================
    1. Spring Bean
    package com.app.shivam;

    import java.util.List;
    import java.util.Map;
    import java.util.Set;

    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.stereotype.Component;

    @Component
    @ConfigurationProperties("my.app")
    public class MyServiceData {

        private int id;
        private String code;
        private boolean active;
        
        //private List<String> data; //ArrayList
        private Set<String> data; //LinkedHashSet
        //private String[] data; 
        
        private Map<String,String> info; //LinkedHashMap
        
        private Process pob;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public Set<String> getData() {
            return data;
        }

        public void setData(Set<String> data) {
            this.data = data;
        }

        public Map<String, String> getInfo() {
            return info;
        }

        public void setInfo(Map<String, String> info) {
            this.info = info;
        }

        public Process getPob() {
            return pob;
        }

        public void setPob(Process pob) {
            this.pob = pob;
        }

        @Override
        public String toString() {
            return "MyServiceData [id=" + id + ", code=" + code + ", active=" + active + ", data=" + data + ", info=" + info
                    + ", pob=" + pob + "]";
        }
        
        
    }
    -------------
    package com.app.shivam;

    public class Process {

        private int pid;
        private String pcode;
        
        public int getPid() {
            return pid;
        }
        public void setPid(int pid) {
            this.pid = pid;
        }
        public String getPcode() {
            return pcode;
        }
        public void setPcode(String pcode) {
            this.pcode = pcode;
        }
        @Override
        public String toString() {
            return "Process [pid=" + pid + ", pcode=" + pcode + "]";
        }
        
        
    }

    -------------------

    2. application.properties
    my.app.id=20
    my.app.code=MODEL
    my.app.active=true

    my.app.data[0]=A
    my.app.data[1]=B
    my.app.data[2]=C
    #my.app.data=A,B,C

    my.app.info.C1=M1
    my.app.info.C2=M2

    my.app.pob.pid=11
    my.app.pob.pcode=AAA

    3. Runner class
    package com.app.shivam;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.stereotype.Component;

    @Component
    public class ReadDataRunner implements CommandLineRunner {
        @Autowired
        private MyServiceData ob;
        
        public void run(String... args) throws Exception {
            System.out.println(ob);
        }

    }
```

Let's go through each point and provide a detailed explanation:

1. In the code snippet, we have a class named `MyServiceData` annotated with `@Component` and `@ConfigurationProperties("my.app")`. This indicates that the class is a Spring bean and its properties will be bound to the configuration properties with the prefix "my.app".

2. The `MyServiceData` class has several properties, including `id`, `code`, `active`, `data`, `info`, and `pob`. These properties will be populated with values from the configuration properties file.

3. The `id`, `code`, and `active` properties are of primitive types (int, String, and boolean, respectively). The framework will automatically bind these properties based on their names and data types. For example, the property "my.app.id" will be bound to the `id` property of the `MyServiceData` class.

4. The `data` property is of type `Set<String>`, which represents a collection of unique string values. In the `application.properties` file, we provide multiple values for the `my.app.data` property using indices in the format `my.app.data[0]=A`, `my.app.data[1]=B`, `my.app.data[2]=C`. The framework will bind these values to the `data` property as a set of strings.

5. The `info` property is of type `Map<String, String>`, representing a key-value pair collection. In the `application.properties` file, we provide values for the `my.app.info` property using the syntax `my.app.info.C1=M1`, `my.app.info.C2=M2`. The framework will populate the `info` map with the corresponding key-value pairs.

6. The `pob` property is of type `Process`, which is a custom class defined separately. The properties for the `Process` class are nested within the `my.app.pob` prefix in the `application.properties` file. The values for the `pid` and `pcode` properties of `pob` are specified as `my.app.pob.pid=11` and `my.app.pob.pcode=AAA`, respectively.

7. The `ReadDataRunner` class is annotated with `@Component` and implements `CommandLineRunner`. It is responsible for running the code that prints the `MyServiceData` object after it has been populated with the values from the configuration properties.

8. In the `run` method of `ReadDataRunner`, the `MyServiceData` object `ob` is autowired. The Spring framework will inject the populated instance of `MyServiceData` into the `ob` variable.

9. Finally, in the `run` method, the `MyServiceData` object is printed using `System.out.println(ob)`. This will display the values of the properties in the `MyServiceData` object, including the bound configuration properties.

By following this setup, the `MyServiceData` object will be instantiated and its properties will be populated with values from the `application.properties` file. The `ReadDataRunner` class ensures that the populated object is available and can be accessed in the `run` method.


<br/>
<br/>
<br/>

# YAML :  `YAML Ain't Markup Language`

1. YAML stands for "YAML Ain't Markup Language." It is a human-readable data serialization format commonly used for configuration files.
2. YAML files in Spring Boot typically use the `.yml` or `.yaml` extension and are used to represent data/input for pre-defined configurations.
3. YAML has several advantages over properties files, including better memory utilization, faster processing time, and a more readable format.

Now let's examine the structure and syntax of YAML:

1. No Duplicate Levels in YAML file: YAML does not allow duplicate keys at the same level within a YAML document. Each key must be unique.

2. Every level should end with either `:<NextLine>` or `:<oneSpace><value>` YAML uses indentation and colons to define the structure of the data. Each level of indentation should end with either a colon followed by a new line or a colon followed by a single space and a value.

3. Every new level start must have the same space count (indentation): YAML relies on consistent indentation to define nested levels. Each level within the document should have the same number of spaces for proper interpretation.

4. In YAML, lists, sets, and arrays are represented using the `-` (dash) symbol: To define a list, set, or array in YAML, you use the `-` symbol followed by a space and the value. Each element in the list is represented on a new line with the same indentation.

5. In YAML, maps or properties are represented using the key-value notation: To define a map or properties in YAML, you use the `key: value` notation. The key and value are separated by a colon followed by a space.

6. In YAML, reference types are represented using the key-value notation as well: If you need to define a reference type, such as nested objects or complex data structures, you can use the same key-value notation to represent them.

7. YAML replaces equals and dot symbols from properties files with a colon symbol: In YAML, the equals symbol `=` and dot symbol `.` used in properties files are replaced with a colon `:` symbol to represent key-value pairs.

The provided examples demonstrate the conversion of properties files to YAML format:

- The `application.properties` file contains properties such as `spring.datasource.driver-class-name`, `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password`.

- The equivalent representation in YAML format (`application.yml`) shows how the properties are organized into a hierarchical structure using indentation and colons.

By using YAML instead of properties files, the configuration becomes more readable and easier to manage. YAML's indentation-based structure allows for clear visualization of the hierarchy and relationships between the properties.

<br/>
<br/>
<br/>

For the provided YAML examples, let's go through each one and explain the representation in both `application.properties` and `application.yml` formats:

1. Example 1: `application.properties` vs `application.yml`
   - `application.properties`:
     ```
     spring.datasource.url=jdbc-oracle
     spring.datasource.user=sample
     spring.mail.host=gmail
     spring.mail.port=998
     ```
   - `application.yml`:
     ```yaml
     spring:
       datasource:
         url: jdbc-oracle
         user: sample
       mail:
         host: gmail
         port: 998
     ```

   In this example, both formats represent the same configuration properties related to the datasource and mail settings. The YAML format provides a more structured and readable representation with hierarchical indentation.

2. Example 2: `application.properties` vs `application.yml`
   - `application.properties`:
     ```
     my.app.id=12
     spring.model.test=OK
     my.app.code=A
     spring.format.code=A
     spring.model.active=true
     my.app.version=3.3
     spring.format.grade=MN
     ```
   - `application.yml`:
     ```yaml
     my:
       app:
         id: 12
         code: A
         version: 3.3
     spring:
       model:
         test: OK
         active: true
       format:
         code: A
         grade: MN
     ```

   In this example, both formats represent different configuration properties. The `my.app` properties in `application.properties` are structured in a flat format, whereas the `application.yml` version provides a more nested and organized representation. The YAML format allows for better visualization of the hierarchy and relationships between the properties.

By using YAML, the configuration files become more readable and maintainable, especially when dealing with complex configurations and hierarchical structures. YAML's indentation-based structure helps to maintain a clear and structured representation of the configuration data.

I hope this explanation helps you understand the representation of properties in both `application.properties` and `application.yml` formats. 
