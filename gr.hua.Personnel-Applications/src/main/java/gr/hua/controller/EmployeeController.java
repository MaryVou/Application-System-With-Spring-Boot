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
import gr.hua.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository EmployeeRepository;

	@GetMapping("/employees")
	public List<Employee> retrieveAllEmployees() {
		return EmployeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public Employee retrieveEmployee(@PathVariable int id) {
		Optional<Employee> Employee = EmployeeRepository.findById(id);

		if (!Employee.isPresent())
			throw new EmployeeNotFoundException("id-" + id);

		return Employee.get();
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable int id) {
		EmployeeRepository.deleteById(id);
	}

	@PostMapping("/employees")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee Employee) {
		Employee savedEmployee = EmployeeRepository.save(Employee);
		System.out.println("Employee id " + savedEmployee.getId());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEmployee.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee Employee, @PathVariable int id) {

		Optional<Employee> EmployeeOptional = EmployeeRepository.findById(id);

		if (!EmployeeOptional.isPresent())
			return ResponseEntity.notFound().build();

		Employee.setId(id);

		EmployeeRepository.save(Employee);

		return ResponseEntity.noContent().build();
	}

}