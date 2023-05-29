package my.app;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Test {
	public static void main(String[] args) {
		
	 AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyAppConfig.class);
//			ac.scan("my.app");
//			ac.scan("com.shivam");
//			ac.refresh();
			Object ob1=ac.getBean("eobj"); 
			Object ob2=ac.getBean("cob"); 
			Object ob3=ac.getBean("vr"); 
			System.out.println(ob1);
			System.out.println(ob2);
			System.out.println(ob3);
		
	}
	
}
