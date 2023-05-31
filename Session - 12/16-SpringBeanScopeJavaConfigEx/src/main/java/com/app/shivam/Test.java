package com.app.shivam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        
        Token ta1 = ac.getBean("t1", Token.class);
        System.out.println(ta1);
        
        Token ta2 = ac.getBean("t1", Token.class);
        System.out.println(ta2);
    }
}