package com.learning.ark.training.repository;


import com.learning.ark.training.dto.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ravi Kumar Annepu
 */
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
