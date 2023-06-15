package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Employee;

public interface EmployeeRepository
	extends JpaRepository<Employee, Integer>
{

}
