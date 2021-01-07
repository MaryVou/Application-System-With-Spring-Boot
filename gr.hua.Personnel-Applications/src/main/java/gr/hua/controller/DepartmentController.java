package gr.hua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.entity.Department;
import gr.hua.entity.DepartmentResponse;
import gr.hua.service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	public DepartmentService departmentService;	
	
	@GetMapping("/departments")
	public String retrieveAllDepartments(Model model){
		List<DepartmentResponse> departments = departmentService.retrieveDepartments();
		model.addAttribute("departments", departments);
		return "viewDepartments";
	}
	
	/*
	//CREATE NEW DEPARTMENT AND RPOMOTE AN EMPLOYEE TO SUPERVISOR OR USE AN EXISTING ONE AND PROMOTE ANOTHER EMPLOYEE TO TAKE THEIR PLACE
	@PostMapping("/departments/new")
	public void createDepartment(@RequestParam(name = "name") String name,@RequestParam(name = "supervisor") int superId) {
		departmentService.createNewDepartment(name, superId);
	}*/
	
	@GetMapping("/departments/delete/{id}")
	public String deleteDepartment(@RequestParam(name = "old") String old_dep_name,@RequestParam(name = "new") String new_dep_name) {
		departmentService.deleteDepartment(old_dep_name, new_dep_name);
		return "redirect:/departments";
	}
}
