package my.app.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import my.app.MyAppConfig;

public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyAppConfig.class);
		Object ob = ac.getBean("con");
		System.out.println(ob);
	}
}
