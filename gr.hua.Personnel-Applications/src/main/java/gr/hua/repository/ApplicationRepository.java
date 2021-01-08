package gr.hua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gr.hua.entity.Application;
import gr.hua.entity.ApplicationResponse;

@Repository
@Transactional
public interface ApplicationRepository extends JpaRepository<Application,Integer>{
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a")
	public List<ApplicationResponse> findAllApplications();
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a, Employee e, User u, Authority auth "
			+ "where a.employee.id=e.id and "
			+ "a.super_sig=null and "
			+ "e.user.username=u.username and "
			+ "e.department.id = ?1 and "
			+ "u.username=auth.user.username and "
			+ "((auth.authority='ROLE_EMPLOYEE') or "
			+ "(auth.authority='ROLE_PDEMPLOYEE'))")
	public List<ApplicationResponse> findApplicationsForSupervisor(int dep_id);
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a, Employee e, User u, Authority auth "
			+ "where a.employee.id=e.id and "
			+ "a.pd_sig=null and "
			+ "((a.type='recovery') or "
			+ "(a.type='strike')) and "
			+ "((a.super_sig=1) or "
			+ "(a.mgr_sig=1)) and "
			+ "e.user.username=u.username and "
			+ "e.id != ?1 and "
			+ "u.username=auth.user.username and "
			+ "((auth.authority='ROLE_EMPLOYEE') or "
			+ "(auth.authority='ROLE_SUPERVISOR'))")
	public List<ApplicationResponse> findApplicationsForPDEmployee(int dep_id);
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a, Employee e, User u, Authority auth "
			+ "where a.employee.id=e.id and "
			+ "a.mgr_sig=null and "
			+ "e.user.username=u.username and "
			+ "u.username=auth.user.username and "
			+ "auth.authority='ROLE_SUPERVISOR'")
	public List<ApplicationResponse> findApplicationsForManager();
	
	@Modifying
	@Query(value="Update application set mgr_sig=1 where app_id=?1", nativeQuery=true)
	public void managerAcceptsApplication(int id);
	
	@Modifying
	@Query(value="Update application set super_sig=1 where app_id=?1", nativeQuery=true)
	public void supervisorAcceptsApplication(int id);
	
	@Modifying
	@Query(value="Update application set pd_sig=1 where app_id=?1", nativeQuery=true)
	public void pdAcceptsApplication(int id);
	
	@Modifying
	@Query(value="Update application set mgr_sig=0 where app_id=?1", nativeQuery=true)
	public void managerRejectsApplication(int id);
	
	@Modifying
	@Query(value="Update application set super_sig=0 where app_id=?1", nativeQuery=true)
	public void supervisorRejectsApplication(int id);
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a where"
			+ "((a.mgr_sig=0) or "
			+ "(a.mgr_sig=1)) or "
			+ "((a.super_sig=0) or "
			+ "(a.super_sig=1))")
	public List<ApplicationResponse> findHistoryForManager();
	
	@Query(value="select new gr.hua.entity.ApplicationResponse(a.id, a.type, a.days, a.start_date, a.last_date, a.req_papers, a.super_sig, a.pd_sig"
			+ ",a.mgr_sig, a.employee.id) from Application a, Department d where"
			+ "((a.super_sig=0) or "
			+ "(a.super_sig=1)) and "
			+ "d.id = ?1")
	public List<ApplicationResponse> findHistoryForSupervisor(int dep_id);
}

