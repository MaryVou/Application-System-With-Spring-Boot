package gr.hua.controller;

import java.util.List;
import java.util.Optional;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.hua.entity.Employee;
import gr.hua.entity.EmployeeRequest;
import gr.hua.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees/view")
	//@Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_SUPERVISOR"})
	public List<Employee> retrieveAllEmployees() {
		return employeeService.retrieveEmployees();
	}

	@GetMapping("/employees/view/{id}")
	//@Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_SUPERVISOR"})
	public Employee retrieveEmployee(@PathVariable int id) {
		Optional<Employee> Employee = employeeService.retrieveEmployeeById(id);

		if (!Employee.isPresent())
			throw new EmployeeNotFoundException("id-" + id);

		return Employee.get();
	}

	@DeleteMapping("/employees/delete/{id}")
	//@Secured("ROLE_ADMIN")
	public void deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
	}

	@PostMapping("/employees/new")
	//@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
		Employee savedEmployee = employeeService.createEmployee(employeeRequest);
		System.out.println("Employee id " + savedEmployee.getId());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEmployee.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/employees/update/{id}")
	//@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee Employee, @PathVariable int id) {

		Optional<Employee> EmployeeOptional = employeeService.retrieveEmployeeById(id);

		if (!EmployeeOptional.isPresent())
			return ResponseEntity.notFound().build();

		Employee.setId(id);

		//employeeService.createOrUpdateEmployee(Employee);

		return ResponseEntity.noContent().build();
	}

}