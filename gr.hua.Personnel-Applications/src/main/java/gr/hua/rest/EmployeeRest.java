package gr.hua.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.entity.Employee;
import gr.hua.entity.EmployeeRequest;
import gr.hua.service.EmployeeService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

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
	
	@PostMapping("/api/employees/profile/update-phone")
	public int updatePhone(@RequestBody String jsonString) {
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		JSONParser parser = new JSONParser(); 
		try {
			JSONObject json = (JSONObject) parser.parse(jsonString);
			employeeService.updatePhone(json.getAsString("phone"), connected_user);
			return 200;
		} catch (ParseException e) {
			e.printStackTrace();
			return 400;
		}
	}
	
	@PostMapping("/api/employees/profile/update-address")
	public int updateAddress(@RequestBody String jsonString) {
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		JSONParser parser = new JSONParser(); 
		try {
			JSONObject json = (JSONObject) parser.parse(jsonString);
			employeeService.updateAddress(json.getAsString("address"), connected_user);
			return 200;
		} catch (ParseException e) {
			e.printStackTrace();
			return 400;
		}
	}
	
	@PostMapping("/api/employees/profile/update-password")
	public int updatePassword(@RequestBody String jsonString) {
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		JSONParser parser = new JSONParser(); 
		try {
			JSONObject json = (JSONObject) parser.parse(jsonString);
			employeeService.updatePassword(json.getAsString("password"), connected_user);
			return 200;
		} catch (ParseException e) {
			e.printStackTrace();
			return 400;
		}
	}
	
	@GetMapping("/api/employees/contact")
	public List<EmployeeRequest> retrieveContacts(){
		String connected_user = SecurityContextHolder.getContext().getAuthentication().getName();
		return employeeService.findContacts(connected_user);
	}
	
}
