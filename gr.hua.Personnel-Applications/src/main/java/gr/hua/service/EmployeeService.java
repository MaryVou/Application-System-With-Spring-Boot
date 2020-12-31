package gr.hua.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.entity.Employee;
import gr.hua.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> retrieveEmployees(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> retrieveEmployeeById(int id) {
		return employeeRepository.findById(id);
	}
	
	public void deleteEmployee(int id){
		employeeRepository.deleteEmployeeById(id);
	}
	
	public Employee createOrUpdateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
}
