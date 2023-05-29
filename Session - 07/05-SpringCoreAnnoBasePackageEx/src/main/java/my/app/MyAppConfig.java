package my.app;

import org.springframework.context.annotation.ComponentScan;



//@ComponentScan({"com.app","test.one","abc.xyz"})
//@ComponentScan(basePackages={"com.app","test.one","abc.xyz"})
@ComponentScan(basePackages = {"my.one","my.app"})
public class MyAppConfig {

}
