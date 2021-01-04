package gr.hua.repository;

import gr.hua.entity.Employee;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Modifying
	@Query(value="delete from Employee where emp_id = ?1", nativeQuery=true)
	public void deleteEmployeeById(int id);
	
	@Modifying
	@Query(value="update Employee set dep_id_fk = ?1 where emp_id = ?2", nativeQuery=true)
	public void linkWithDepartment(int dep_id, int emp_id);		//links employee table to department table
	
	@Modifying
	@Query(value="insert into user(username,password,emp_id) values(?1, ?2, ?3)", nativeQuery=true)
	public void linkWithUser(String username, String password, int emp_id);		//links employee table to user table
	
	@Modifying
	@Query(value="update Employee set username_fk = ?1 where emp_id = ?2", nativeQuery=true)
	public void updateUsername(String username, int emp_id);
	
	@Modifying
	@Query(value="insert into authorities values(?1, 'ROLE_EMPLOYEE')", nativeQuery=true)
	public void linkWithAuthorities(String username);	//links employee table to authorities table
	
	@Query(value="select username from user where emp_id = ?1", nativeQuery=true)
	public String findUsernameById(int id);	//links employee table to authorities table
	
	@Modifying
	@Query(value="update authorities set authority = 'ROLE_SUPERVISOR' where username = ?1", nativeQuery=true)
	public void promoteToSupervisor(String username);	//links employee table to authorities table

	@Modifying
	@Query(value="update employee set dep_id_fk = ?2 where emp_id = ?1", nativeQuery=true)
	public void updateWorkingDepartment(int emp_id, int dep_id);	//links employee table to authorities table

	@Modifying
	@Query(value="update authorities set authority = 'ROLE_EMPLOYEE' where username = ?1", nativeQuery=true)
	public void supervisorDegradation(String username);
}