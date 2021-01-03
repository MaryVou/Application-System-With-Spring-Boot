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
		
		String dep_name = employeeRequest.getDepartment().getName();
		int dep_id = departmentRepository.findIdByName(dep_name);
		employeeRepository.linkWithDepartment(dep_id, emp_id);
		
		String username = employeeRequest.getUser().getUsername();
		String password = employeeRequest.getUser().getPassword();

		employeeRepository.linkWithUser(username,password,emp_id);
		employeeRepository.updateUsername(username, emp_id);
		
		employeeRepository.linkWithAuthorities(username);
		
		return emp;
	}
	
	public Boolean employeeExists(int id) {
		return employeeRepository.existsById(id);
	}
	
	public void promoteToSupervisor(int emp_id, int dep_id) {
		String username = employeeRepository.findUsernameById(emp_id);
		employeeRepository.promoteToSupervisor(username);
		employeeRepository.updateWorkingDepartment(emp_id,dep_id);
	}
	
	public void supervisorDegradation(int id) {
		String username = employeeRepository.findUsernameById(id);
		employeeRepository.supervisorDegradation(username);
	}
	
}
