package gr.hua.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.entity.Employee;
import gr.hua.service.EmployeeService;

@RestController
public class EmployeeRest {
	
	@Autowired
	private EmployeeService employeeService; 
	
	@GetMapping("/api/employees/profile")
	public Optional<Employee> retrieveProfile() {
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = employeeService.findIdByUsername(connected_user);
		return employeeService.retrieveEmployeeById(id);
	}
	
	
}
