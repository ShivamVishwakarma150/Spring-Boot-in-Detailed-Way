package com.app.shivam.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Department;
import com.app.shivam.entity.Employee;
import com.app.shivam.repo.DepartmentRepository;
import com.app.shivam.repo.EmployeeRepository;

@Component
public class TestDataInsertRunner implements CommandLineRunner {

	@Autowired
	private DepartmentRepository drepo;
	
	@Autowired
	private EmployeeRepository erepo;
	
	public void run(String... args) throws Exception {

		Department d1 = new Department(1054,"DEV","AJAY");
		Department d2 = new Department(1055,"QA","SAM");
		Department d3 = new Department(1056,"BA","KUMAR");
		drepo.save(d1);
		drepo.save(d2);
		drepo.save(d3);
		
		Employee e1 = new Employee(10, "SAM", 500.0, d1);
		Employee e2 = new Employee(11, "RAM", 600.0, d2);
		Employee e3 = new Employee(12, "SYED", 700.0, d1);
		Employee e4 = new Employee(13, "AJAY", 700.0, null);
		
		erepo.save(e1);
		erepo.save(e2);
		erepo.save(e3);
		erepo.save(e4);
		
		
	}

}
