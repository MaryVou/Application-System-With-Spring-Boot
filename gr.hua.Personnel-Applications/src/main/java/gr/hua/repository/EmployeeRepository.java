package gr.hua.repository;

import gr.hua.entity.Employee;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Transactional
	@Modifying
	@Query(value="delete from Employee where emp_id = ?1", nativeQuery=true)
	public void deleteEmployeeById(int id);
	
	@Transactional
	@Modifying
	@Query(value="update Employee set dep_id_fk = ?1 where emp_id = ?2", nativeQuery=true)
	public void linkWithDepartment(int dep_id, int emp_id);		//links employee table to department table
	
	@Transactional
	@Modifying
	@Query(value="insert into user(username,password,emp_id) values(?1, ?2, ?3)", nativeQuery=true)
	public void linkWithUser(String username, String password, int emp_id);
	
	@Transactional
	@Modifying
	@Query(value="update Employee set username_fk = ?1 where emp_id = ?2", nativeQuery=true)
	public void updateUsername(String username, int emp_id);
}