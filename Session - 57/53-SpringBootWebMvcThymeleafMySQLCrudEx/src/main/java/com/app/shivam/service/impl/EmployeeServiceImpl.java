package com.app.shivam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.shivam.entity.Employee;
import com.app.shivam.exception.EmployeeNotFoundException;
import com.app.shivam.repo.EmployeeRepository;
import com.app.shivam.service.IEmployeeService;
import com.app.shivam.util.EmployeeUtil;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;

	//@Autowired
	//private EmployeeUtil util;
	
	public Integer saveEmployee(Employee e) {
		//util.commonCal(e);
		EmployeeUtil.commonCal(e);
		e = repo.save(e);
		return e.getEmpId();
	}

	public void updateEmployee(Employee e) {
		//util.commonCal(e);
		EmployeeUtil.commonCal(e);
		repo.save(e);
	}

	public void deleteEmployee(Integer id)
			throws EmployeeNotFoundException
	{
		//repo.deleteById(id);
		/*Optional<Employee> opt = repo.findById(id);
		if(opt.isPresent()) {
			repo.delete(opt.get());
		} else {
			throw new EmployeeNotFoundException("EMPLOYEE '"+id+"' NOT FOUND");
		}*/
		/*repo.delete(
				repo.findById(id)
				.orElseThrow(
						()->new EmployeeNotFoundException("EMPLOYEE '"+id+"' NOT FOUND"))
				);*/
		repo.delete(this.getOneEmployee(id));
	}

	public Employee getOneEmployee(Integer id) 
			throws EmployeeNotFoundException
	{
		/*Optional<Employee> opt = repo.findById(id);
		return opt.get();*/
		return repo.findById(id)
				.orElseThrow(
						()->new EmployeeNotFoundException("EMPLOYEE '"+id+"' NOT FOUND")
				);
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = repo.findAll();
		return list;
	}
	
	public Page<Employee> getAllEmployees(Pageable pageable) {
		Page<Employee> pages = repo.findAll(pageable);
		return pages;
	}

}
