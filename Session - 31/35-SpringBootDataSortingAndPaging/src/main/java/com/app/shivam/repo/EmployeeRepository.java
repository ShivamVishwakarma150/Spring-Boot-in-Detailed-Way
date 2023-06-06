package com.app.shivam.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.shivam.entity.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

}
