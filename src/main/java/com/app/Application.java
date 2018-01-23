package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目启动
 * @author 二更
 *
 */

@SpringBootApplication
@ComponentScan("com.app")

public class Application{
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

}

