package gr.hua.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.entity.Department;
import gr.hua.service.DepartmentService;
import gr.hua.service.EmployeeService;

@RestController
public class DepartmentController {
	
	@Autowired
	public DepartmentService departmentService;	
	
	@GetMapping("/departments")
	public List<Department> retrieveAllDepartments(){
		return departmentService.retrieveDepartments();
	}
	
	//CREATE NEW DEPARTMENT AND RPOMOTE AN EMPLOYEE TO SUPERVISOR OR USE AN EXISTING ONE AND PROMOTE ANOTHER EMPLOYEE TO TAKE THEIR PLACE
	
	@PostMapping("/departments/new")
	public void createDepartment(@RequestParam(name = "name") String name,@RequestParam(name = "supervisor") int superId) {
		departmentService.createNewDepartment(name, superId);
	}
	
	@DeleteMapping("/departments/delete")
	public void deleteDepartment(@RequestParam(name = "old") String old_dep_name,@RequestParam(name = "new") String new_dep_name) {
		departmentService.deleteDepartment(old_dep_name, new_dep_name);
	}
	
}
