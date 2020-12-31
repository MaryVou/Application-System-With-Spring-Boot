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
	
}