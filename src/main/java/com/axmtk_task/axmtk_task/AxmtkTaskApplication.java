package com.axmtk_task.axmtk_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.axmtk_task"})
@EntityScan("com.axmtk_task")
public class AxmtkTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxmtkTaskApplication.class, args);
	}

}
