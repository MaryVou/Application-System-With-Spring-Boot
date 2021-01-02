package gr.hua.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.entity.Employee;
import gr.hua.entity.EmployeeRequest;
import gr.hua.repository.DepartmentRepository;
import gr.hua.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired 
	private DepartmentRepository departmentRepository;
	
	public List<Employee> retrieveEmployees(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> retrieveEmployeeById(int id) {
		return employeeRepository.findById(id);
	}
	
	public void deleteEmployee(int id){
		employeeRepository.deleteEmployeeById(id);
	}
	
	public Employee createEmployee(EmployeeRequest employeeRequest) {
		Employee emp = employeeRepository.save(employeeRequest.getEmployee());
		int emp_id = emp.getId();
		System.out.println(emp_id);
		
		String dep_name = employeeRequest.getDepartment().getName();
		int dep_id = departmentRepository.findIdByName(dep_name);
		System.out.println(dep_id);
		employeeRepository.linkWithDepartment(dep_id, emp_id);
		
		String username = employeeRequest.getUser().getUsername();
		String password = employeeRequest.getUser().getPassword();

		employeeRepository.linkWithUser(username,password,emp_id);
		employeeRepository.updateUsername(username, emp_id);
		
		return emp;
	}
}
