package com.learning.ark.training;

import com.learning.ark.training.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TrainingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inside run");
		//System.out.println(employeeRepository.findAll());
	}
}
