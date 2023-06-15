package com.app.shivam.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.shivam.entity.Employee;
import com.app.shivam.exception.EmployeeNotFoundException;

public interface IEmployeeService {

	Integer saveEmployee(Employee e);
	void updateEmployee(Employee e);
	void deleteEmployee(Integer id)
			throws EmployeeNotFoundException;
	Employee getOneEmployee(Integer id)
			throws EmployeeNotFoundException;
	List<Employee> getAllEmployees();
	Page<Employee> getAllEmployees(Pageable pageable);
}
