package com.ssafy.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy	// aop 설정
@SpringBootApplication
public class HwFramework7Application {

	public static void main(String[] args) {
		SpringApplication.run(HwFramework7Application.class, args);
	}

}
