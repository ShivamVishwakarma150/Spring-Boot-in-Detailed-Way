package com.app.config;

import org.springframework.context.annotation.ComponentScan;

// no default base package is given by Spring Core
@ComponentScan(basePackages="com.app")
public class MyAppConfig {

}
