package gr.hua.controller;

import java.util.List;
import java.util.Optional;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.hua.entity.Employee;
import gr.hua.entity.EmployeeRequest;
import gr.hua.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees") // ADMIN , MANAGER, SUPERVISOR
	public String retrieveAllUsers(Model model) {
		List<Employee> employees = employeeService.retrieveEmployees();
		model.addAttribute("employees", employees);
		return "viewEmployees";
	}

	/*
	 * @GetMapping("/employees/{id}")
	 * 
	 * @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_SUPERVISOR"}) public Employee
	 * retrieveEmployee(@PathVariable int id) { Optional<Employee> Employee =
	 * employeeService.retrieveEmployeeById(id);
	 * 
	 * if (!Employee.isPresent()) throw new EmployeeNotFoundException("id-" + id);
	 * 
	 * return Employee.get(); }
	 */

	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id, Model model) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}

	@GetMapping("/employees/new")
	public String showEmployeeForm(Model model) {
		model.addAttribute("data", new EmployeeRequest());
		return "NewEmployee";
	}

	@PostMapping("/employees/new")
	public String createEmployee(@ModelAttribute EmployeeRequest employeeRequest) {
		System.out.println(employeeRequest.toString());
		Employee savedEmployee = employeeService.createEmployee(employeeRequest);
		System.out.println("Employee id " + savedEmployee.getId());
		return "redirect:/employees";
	}

}