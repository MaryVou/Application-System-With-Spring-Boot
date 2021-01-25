package gr.hua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.entity.Application;
import gr.hua.entity.ApplicationResponse;
import gr.hua.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	public List<ApplicationResponse> retrieveApplications(String username) {
		List<ApplicationResponse> applications = null;
		String authority = userService.findAuthorityByUsername(username);
		
		if(authority.equals("ROLE_ADMIN")) {
			return applicationRepository.findAllApplications();
		}else if(authority.equals("ROLE_MANAGER")) {
			return applicationRepository.findApplicationsForManager();
		}else if(authority.equals("ROLE_SUPERVISOR")) {
			int dep_id = departmentService.findIdByUsername(username);
			return applicationRepository.findApplicationsForSupervisor(dep_id);
		}else if(authority.equals("ROLE_PDEMPLOYEE")) {
			int emp_id = employeeService.findIdByUsername(username);
			return applicationRepository.findApplicationsForPDEmployee(emp_id);
		}
		return applications;	
	}
	
	public void acceptApplication(int id, String username) {
		String authority = userService.findAuthorityByUsername(username);

		if(authority.equals("ROLE_MANAGER")) {
			applicationRepository.managerAcceptsApplication(id);
		}else if(authority.equals("ROLE_SUPERVISOR")) {
			applicationRepository.supervisorAcceptsApplication(id);
		}else if(authority.equals("ROLE_PDEMPLOYEE")) {
			applicationRepository.pdAcceptsApplication(id);
		}
		
	}
	
	public void rejectApplication(int id, String username) {
		String authority = userService.findAuthorityByUsername(username);

		if(authority.equals("ROLE_MANAGER")) {
			applicationRepository.managerRejectsApplication(id);
		}else if(authority.equals("ROLE_SUPERVISOR")) {
			applicationRepository.supervisorRejectsApplication(id);
		}
		
	}
	
	public List<ApplicationResponse> retrieveHistory(String username){
		String authority = userService.findAuthorityByUsername(username);
		List<ApplicationResponse> applications = null;

		if(authority.equals("ROLE_MANAGER")) {
			applications = applicationRepository.findHistoryForManager();
		}else if(authority.equals("ROLE_SUPERVISOR")) {
			int dep_id = departmentService.findIdByUsername(username);
			applications = applicationRepository.findHistoryForSupervisor(dep_id);
		}
		
		return applications;
	}
	
	public void addApplication(Application application) {
		applicationRepository.save(application);
	}
	
	public List<ApplicationResponse> findPersonalApplications(String username){
		int emp_id = employeeService.findIdByUsername(username);
		return applicationRepository.findPersonalApplications(emp_id);
	}
}