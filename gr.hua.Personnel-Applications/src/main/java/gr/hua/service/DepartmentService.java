package gr.hua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.controller.EmployeeNotFoundException;
import gr.hua.entity.Department;
import gr.hua.entity.DepartmentResponse;
import gr.hua.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	public EmployeeService employeeService;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<DepartmentResponse> retrieveDepartments(){
		return departmentRepository.findAllDepartments();
	}
	
	public void createNewDepartment(String name, int superId) {
		if(employeeService.employeeExists(superId)) {
			departmentRepository.create(name, superId);
			int dep_id = departmentRepository.findIdByName(name);
			employeeService.promoteToSupervisor(superId,dep_id);
		}else throw new EmployeeNotFoundException("id-" + superId);
	}
	
	public void deleteDepartment(String old_dep, String new_dep) {
		int old_id = departmentRepository.findIdByName(old_dep);
		int new_id = departmentRepository.findIdByName(new_dep);
		
		departmentRepository.moveEmployeesTo(old_id,new_id);
		
		int super_id = departmentRepository.findSupervisorById(old_id);
		employeeService.supervisorDegradation(super_id);
				
		departmentRepository.deleteDepartmentById(old_id);

	}
	
	public int findIdByUsername(String username) {
		return departmentRepository.findIdByUsername(username);
	}
	
}
