package com.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MyAppConfig.class);
        Object dm = ac.getBean("conObj");
        System.out.println(dm);
    }
}
