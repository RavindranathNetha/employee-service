package com.learning.ark.training.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.ark.training.dto.AddEmployee;
import com.learning.ark.training.dto.Employee;
import com.learning.ark.training.dto.UpdateEmployee;
import com.learning.ark.training.exception.TrainingException;
import com.learning.ark.training.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees() {
		List<Employee> list = (List<Employee>) employeeRepository.findAll();
		if (CollectionUtils.isEmpty(list)) {
			throw new TrainingException("", HttpStatus.NO_CONTENT);
		}
		return list;
	}

	@Override
	public Employee getEmployee(String id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new TrainingException("Employee not found", HttpStatus.NOT_FOUND));
	}

	@Override
	public boolean exists(String id) {
		return employeeRepository.findById(id).isPresent();
	}

	@Override
	public Employee addEmployee(AddEmployee dto) {
		Employee emp = new Employee();
		BeanUtils.copyProperties(dto, emp);
		emp.setId(UUID.randomUUID().toString());
		return employeeRepository.save(emp);
	}

	@Override
	public Employee updateEmployee(UpdateEmployee dto) {
		Employee emp = employeeRepository.findById(dto.getId())
				.orElseThrow(() -> new TrainingException("Employee not found", HttpStatus.NOT_FOUND));
		emp.setDescription(dto.getDescription());
		return employeeRepository.save(emp);
	}

	@Override
	public void deleteEmployee(String id) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new TrainingException("Employee not found", HttpStatus.NOT_FOUND));
		employeeRepository.delete(emp);
	}

}
