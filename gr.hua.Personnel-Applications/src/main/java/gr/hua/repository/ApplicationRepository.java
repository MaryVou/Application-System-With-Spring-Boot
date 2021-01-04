package gr.hua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gr.hua.entity.Application;
import gr.hua.entity.ApplicationResponse;

public interface ApplicationRepository extends JpaRepository<Application,Integer>{
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a")
	public List<ApplicationResponse> findAllApplications();
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a, Employee e, User u, Authority auth "
			+ "where a.employee.id=e.id and "
			+ "a.super_sig=null and "
			+ "e.user.username=u.username and "
			+ "u.username=auth.user.username and "
			+ "((auth.authority='ROLE_EMPLOYEE') or "
			+ "(auth.authority='ROLE_PDEMPLOYEE'))")
	public List<ApplicationResponse> findApplicationsForSupervisor();
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a, Employee e, User u, Authority auth "
			+ "where a.employee.id=e.id and "
			+ "a.pd_sig=null and "
			+ "((a.super_sig=1) or "
			+ "(a.mgr_sig=1)) and "
			+ "e.user.username=u.username and "
			+ "u.username=auth.user.username and "
			+ "((auth.authority='ROLE_EMPLOYEE') or "
			+ "(auth.authority='ROLE_SUPERVISOR'))")
	public List<ApplicationResponse> findApplicationsForPDEmployee();
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a, Employee e, User u, Authority auth "
			+ "where a.employee.id=e.id and "
			+ "a.mgr_sig=null and "
			+ "e.user.username=u.username and "
			+ "u.username=auth.user.username and "
			+ "auth.authority='ROLE_SUPERVISOR'")
	public List<ApplicationResponse> findApplicationsForManager();
}
