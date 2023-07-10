# Scheduling in Spring Boot

1. **Automated task:** Scheduling in Spring Boot allows you to automate the execution of tasks without manual intervention. These tasks can be any piece of code or method that you want to run at a specific time or at regular intervals.

2. **Repeated on a time gap:** With scheduling, you can configure tasks to be executed repeatedly with a specified time gap between each execution. This means you can define the frequency at which a task should run, such as every few seconds, minutes, hours, or days.

3. **Examples:** The examples you provided are instances where scheduling can be useful. For example, generating bank statements monthly or weekly, generating credit card bills every 30 days, or scheduling the payment of bills on a specific day every month.

4. **Period of Time: Time gap:** The period of time refers to the time gap between consecutive executions of a scheduled task. You can specify this time gap using various units such as milliseconds, seconds, minutes, hours, or days. For example, a time gap of 4 hours would execute the task every 4 hours.

5. **Point of Time: Exact start time:** The point of time refers to the specific start time at which a scheduled task should begin execution. You can provide the exact date and time when the task should start. For example, you can schedule a task to start at 9:00:00 AM on January 1st.

6. **@EnableScheduling:** This annotation is used at the main class of your Spring Boot application. It enables scheduling capabilities for your application by activating the Spring scheduling infrastructure.

7. **@Scheduled annotation:** This annotation is used to mark a method as a scheduled task. You can place this annotation on any method in a Spring component (such as a service or a bean) that you want to schedule. The @Scheduled annotation provides various attributes to configure the scheduling behavior, such as fixed delay, fixed rate, cron expressions, etc.

8. **fixedDelay=(mill sec):** This is one of the attributes of the @Scheduled annotation. It specifies the delay between the completion of the previous execution and the start of the next execution of a task. The value is specified in milliseconds. For example, if you set fixedDelay=1000, the task will be executed every 1 second, regardless of how long the task execution takes.

By using the combination of @EnableScheduling and @Scheduled annotations, you can easily implement scheduled tasks in your Spring Boot application. The scheduling infrastructure provided by Spring Boot will take care of executing the tasks based on the specified time gaps or start times, allowing you to automate various repetitive tasks in your application.

<br/>
<br/>

# Here's the code for the first class, along with its explanation:

```java
package com.app.shivam.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportService {

    @Scheduled(fixedDelay = 60000) // 1000 milliseconds = 1 second
    public void printReport() {
        System.out.println("REPORT " + new Date());
    }
}
```

Explanation:
- The class `ReportService` is a Spring component annotated with `@Component`, making it a bean managed by the Spring framework.
- The method `printReport()` is marked with the `@Scheduled` annotation, indicating that it should be executed as a scheduled task.
- The `fixedDelay` attribute of `@Scheduled` is set to 60000, which means the task will be executed with a delay of 60,000 milliseconds (or 1 minute) after the completion of the previous execution.
- Inside the `printReport()` method, the current date is printed along with the text "REPORT" using `System.out.println()`.

Now, let's move on to the second class:

```java
package com.app.shivam.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    @Scheduled(cron = "0 0 8 * * ?") // Executes every day at 8:00 AM
    public void sendNotification() {
        System.out.println("Sending notification...");
    }
}
```

Explanation:
- The class `NotificationService` is also a Spring component annotated with `@Component`.
- The method `sendNotification()` is annotated with `@Scheduled` to indicate that it should be executed as a scheduled task.
- The `cron` attribute of `@Scheduled` is set to `"0 0 8 * * ?"`, which is a cron expression. This expression represents the schedule to execute the task every day at 8:00 AM.
- Inside the `sendNotification()` method, a message is printed, indicating that a notification is being sent.

In conclusion, the code provided showcases two examples of scheduled tasks in Spring Boot. The `ReportService` class executes the `printReport()` method every 1 minute (60,000 milliseconds) after the completion of the previous execution, while the `NotificationService` class sends a notification every day at 8:00 AM using the specified cron expression. These examples demonstrate how you can leverage the `@Scheduled` annotation and Spring Boot's scheduling infrastructure to automate and repeat tasks at specific intervals or times.

<br/>
<br/>


1. **cron Expression: Date and Time (from Linux)**: A cron expression is a string consisting of six or seven fields that represent different components of a schedule. These fields correspond to seconds, minutes, hours, days of the month, months, and days of the week. Linux systems commonly use cron expressions to define the schedules for recurring tasks.

2. **Symbols**: 
   - `*` (asterisk): Represents all possible values. For example, `*` in the minutes field means "any minute."
   - `?` (question mark): Represents any value or no specific value. It is typically used in the day of the month or day of the week fields when the other field (month or weekday) is specified. For example, `?` in the day of the week field means "any weekday."
   - `,` (comma): Specifies multiple values for a field. For example, `1,3,5` in the hours field means "at 1 AM, 3 AM, and 5 AM."
   - `-` (hyphen): Defines a range of values. For example, `1-5` in the days of the month field means "from the 1st to the 5th day of the month."
   - `/` (forward slash): Specifies increments or periods. For example, `*/10` in the minutes field means "every 10 minutes."

3. **Examples**:
   - Example 1: `0 0 9 * * *` executes a task every day at 9:00:00 AM.
   - Example 2: `0 0 18 * * *` executes a task every day at 6:00:00 PM.
   - Example 3: `0 0 6,18 * * *` executes a task every day at 6:00:00 AM and 6:00:00 PM.
   - Example 4: `0 0 8-10 * * *` executes a task every day at 8:00:00 AM, 9:00:00 AM, and 10:00:00 AM.
   - Example 5: `59 59 23 31 12 SUN-SAT` executes a task every year on December 31st at 11:59:59 PM. The day of the week can be any weekday.
   - Example 6: `0 0 0 * * *` executes a task every day at midnight, 12:00:00 AM.
   - Example 7: `0 0 * * * *` executes a task every hour at the 0th minute and 0th second.
   - Example 8: `0 10 * * * *` executes a task every hour at the 10th minute and 0th second.
   - Example 9: `10 * * * * *` executes a task every minute at the 10th second, not with a 10-second gap.
   - Example 10: `*/10 * * * * *` executes a task every 10 seconds.
   - Example 11: `* * * * * *` (equivalent to `*/1 * * * * *`) executes a task every second.

By understanding the symbols and their usage in cron expressions, you can create precise schedules for executing tasks at specific dates, times, and intervals. These examples demonstrate different scenarios, from daily and hourly schedules to more specific time intervals, using a combination of symbols and values for each cron expression field.

<br/>
<br/>

# Here's the code for the first class, along with its explanation:

```java
package com.app.shivam.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportService {

    //@Scheduled(fixedDelay = 60000) //1000mill sec = 1 sec
    //@Scheduled(cron = "0 0 9 * * *")
    //@Scheduled(cron = "10 * * * * *")
    //@Scheduled(cron = "*/10 * * * * *")
    //@Scheduled(cron = "* * * * * *")
    @Scheduled(cron = "@hourly") //0 0 * * * *
    public void printReport() {
        System.out.println("REPORT " + new Date());
    }
}
```

Explanation:
- The class `ReportService` is a Spring component annotated with `@Component`, making it a bean managed by the Spring framework.
- The method `printReport()` is marked with the `@Scheduled` annotation, indicating that it should be executed as a scheduled task.
- The `cron` attribute of `@Scheduled` is set to various values in the commented lines. You can uncomment each line to test different scheduling configurations.
- The last line, `@Scheduled(cron = "@hourly")`, uses a predefined cron expression, `@hourly`, which is equivalent to `0 0 * * * *`. This expression executes the task every hour at the 0th minute and 0th second.

In conclusion, the provided code showcases different examples of scheduling configurations using the `@Scheduled` annotation in Spring Boot. By uncommenting the desired `@Scheduled` annotation line, you can test different scheduling scenarios, such as fixed delays, specific cron expressions, or predefined cron expressions. The `printReport()` method is called at the specified intervals, and it simply prints the current date along with the text "REPORT" using `System.out.println()`.