package gr.hua.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.entity.Employee;
import gr.hua.service.EmployeeService;

@RestController
public class EmployeeRest {
	
	@Autowired
	private EmployeeService employeeService; 
	
	@GetMapping("/api/employees/test")
	public List<Employee> retrieveAllUsers() {
		List<Employee> employees = employeeService.retrieveEmployees();
		return employees;
	}
}
