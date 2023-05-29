package my.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

// you can write annotation in any order
@ComponentScan(basePackages="my.app")
@PropertySource("classpath:myapp.properties")
public class MyAppConfig {

}
