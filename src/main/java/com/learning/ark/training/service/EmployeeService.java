package com.learning.ark.training.service;

import java.util.List;

import com.learning.ark.training.dto.AddEmployee;
import com.learning.ark.training.dto.Employee;
import com.learning.ark.training.dto.UpdateEmployee;

public interface EmployeeService {

	List<Employee> getEmployees();

	Employee getEmployee(String id);

	boolean exists(String id);

	Employee addEmployee(AddEmployee dto);

	Employee updateEmployee(UpdateEmployee dto);

	void deleteEmployee(String id);

}
