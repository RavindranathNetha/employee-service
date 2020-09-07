package com.learning.ark.training.resource;

import static com.learning.ark.training.util.Constants.BLANK;
import static com.learning.ark.training.util.Constants.DIAGNOSTIC_ID;
import static com.learning.ark.training.util.Constants.INVALID;
import static com.learning.ark.training.util.Constants.METHOD_END;
import static com.learning.ark.training.util.Constants.METHOD_START;
import static com.learning.ark.training.util.Constants.REQUEST_NULL;
import static com.learning.ark.training.util.Constants.VALIDATION_ERROR;
import static com.learning.ark.training.util.Constants.VALIDATION_MISSING_ERROR;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.ark.training.dto.AddEmployee;
import com.learning.ark.training.dto.Employee;
import com.learning.ark.training.dto.UpdateEmployee;
import com.learning.ark.training.exception.TrainingException;
import com.learning.ark.training.service.EmployeeService;
import com.learning.ark.training.util.Utils;

import io.swagger.annotations.Api;

/**
 * @author Ravi Kumar Annepu
 */
@RestController
@Api("Employee Resource")
@RequestMapping("/employees")
public class EmployeeResource {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeResource.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@Validated @RequestBody AddEmployee employee) {
		MDC.put(DIAGNOSTIC_ID, Optional.ofNullable(MDC.get(DIAGNOSTIC_ID)).orElse(UUID.randomUUID().toString()));
		validate(employee);
		return ResponseEntity.ok(employeeService.addEmployee(employee));
	}

	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		MDC.put(DIAGNOSTIC_ID, Optional.ofNullable(MDC.get(DIAGNOSTIC_ID)).orElse(UUID.randomUUID().toString()));
		return ResponseEntity.ok(employeeService.getEmployees());

	}

	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(name = "id", required = true) String id) {
		MDC.put(DIAGNOSTIC_ID, Optional.ofNullable(MDC.get(DIAGNOSTIC_ID)).orElse(UUID.randomUUID().toString()));
		return ResponseEntity.ok(employeeService.getEmployee(id));

	}

	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id", required = true) String id,
			@Validated @RequestBody UpdateEmployee employee) {
		MDC.put(DIAGNOSTIC_ID, Optional.ofNullable(MDC.get(DIAGNOSTIC_ID)).orElse(UUID.randomUUID().toString()));
		LOG.info(METHOD_START);
		validateUpdate(employee);
		if (!StringUtils.equalsIgnoreCase(id, employee.getId())) {
			throw new TrainingException(String.format(INVALID, "id"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(employeeService.updateEmployee(employee));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id", required = true) String id) {
		MDC.put(DIAGNOSTIC_ID, Optional.ofNullable(MDC.get(DIAGNOSTIC_ID)).orElse(UUID.randomUUID().toString()));
		LOG.info(METHOD_START);
		employeeService.deleteEmployee(id);
		LOG.info(METHOD_END);
		return ResponseEntity.ok("Success");
	}

	private void validate(AddEmployee dto) {
		Optional.ofNullable(dto).orElseThrow(() -> new TrainingException(REQUEST_NULL, HttpStatus.BAD_REQUEST));
		dto.sanitize();
		StringBuffer sb = new StringBuffer(BLANK);
		if (StringUtils.isBlank(dto.getName())) {
			sb.append("name").append(",");
		}
		if (sb.toString().isEmpty()) {
			Utils.rejectIfExceedsMaxLength("name", dto.getName(), 128);

			if (StringUtils.isNotBlank(dto.getDescription())) {
				Utils.rejectIfExceedsMaxLength("description", dto.getDescription(), 512);
			}

		} else {
			throw new TrainingException(VALIDATION_ERROR + VALIDATION_MISSING_ERROR
					+ sb.toString().substring(0, sb.toString().length() - 1), HttpStatus.BAD_REQUEST);
		}
	}

	private void validateUpdate(UpdateEmployee dto) {
		Optional.ofNullable(dto).orElseThrow(() -> new TrainingException(REQUEST_NULL, HttpStatus.BAD_REQUEST));
		dto.sanitize();
		if (StringUtils.isNotBlank(dto.getDescription())) {
			Utils.rejectIfExceedsMaxLength("description", dto.getDescription(), 512);
		}
	}
}
