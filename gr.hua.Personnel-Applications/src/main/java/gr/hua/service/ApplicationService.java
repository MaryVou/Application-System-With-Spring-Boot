package gr.hua.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gr.hua.entity.Application;
import gr.hua.entity.ApplicationResponse;
import gr.hua.entity.Employee;
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
	
	public int daysCalculator(Date works_since, Date hire_date) {
		
		int days = -1;
		
		LocalDate works_since_ld = new java.sql.Date(works_since.getTime()).toLocalDate();
		LocalDate hire_date_ld = new java.sql.Date(hire_date.getTime()).toLocalDate();
		
		Duration diff = Duration.between(hire_date_ld.atStartOfDay(), LocalDate.now().atStartOfDay());
		long years_here = diff.toDays();	//years working in this company
		
		Duration diff2 = Duration.between(works_since_ld.atStartOfDay(), LocalDate.now().atStartOfDay());
		long years_active = diff2.toDays();		//years working anywhere
		
		if(years_here<365)	//1 year here
			days = 1;
		if(years_here<730 && years_here>=365)	//between 1 and 2 years here
			days = 21;
		if(years_here>=730 && years_here<3650)	//between 2 and 10 years here
			days = 22;
		if((years_here>=3650 || years_active>=4380) && years_active<9125)		//between (10 years here or 12 anywhere) AND 25 years  
			days = 25;
		if(years_active>=9125)	//more than 25 years anywhere
			days = 26;
		return days;
	}
	
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
	
	public ResponseEntity addApplication(Application application, String username) {
		int emp_id = employeeService.findIdByUsername(username);
		Optional<Employee> emp = employeeService.retrieveEmployeeById(emp_id);
		
		int daysAsked = daysCalculator(application.getStart_date(),application.getLast_date());
		
		if(emp.get().getDays() >= daysAsked) {
			
			Application app = applicationRepository.save(application);
			applicationRepository.updateEmployeeId(employeeService.findIdByUsername(username),app.getId());
			
			employeeService.updateDaysLeft(emp.get().getDays()-daysAsked, emp_id);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	public List<ApplicationResponse> findPersonalApplications(String username){
		int emp_id = employeeService.findIdByUsername(username);
		return applicationRepository.findPersonalApplications(emp_id);
	}
}