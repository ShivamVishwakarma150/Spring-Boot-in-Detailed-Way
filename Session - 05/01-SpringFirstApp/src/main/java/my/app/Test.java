package my.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("config.xml");
		OracleCon con = (OracleCon) ac.getBean("con");
		
		System.out.println(con);
		
	}
}
