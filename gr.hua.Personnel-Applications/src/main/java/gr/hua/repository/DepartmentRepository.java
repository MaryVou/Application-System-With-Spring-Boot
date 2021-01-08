package gr.hua.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gr.hua.entity.Department;
import gr.hua.entity.DepartmentResponse; 

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	@Query(value="select new gr.hua.entity.DepartmentResponse(d.id, d.name, d.supervisor.id, d.supervisor.fname, d.supervisor.lname, d.supervisor.email) from Department d")
	public List<DepartmentResponse> findAllDepartments();
	
	@Query(value="select dep_id from department where dep_name = ?1", nativeQuery=true)
	public int findIdByName(String dep_name);

	@Modifying
	@Query(value="update department set super_id_fk = ?1 where dep_id = ?2", nativeQuery=true)
	public void assignSupervisor(int supervisorId, int dep_id);
	
	@Modifying
	@Query(value="insert into department(dep_name,super_id_fk) values (?1,?2)", nativeQuery=true)
	public void create(String name, int id);
	
	@Modifying
	@Query(value="update employee set dep_id_fk = ?2 where dep_id_fk = ?1", nativeQuery=true)
	public void moveEmployeesTo(int old_id, int new_id);
	
	@Modifying
	@Query(value="delete from department where dep_id = ?1", nativeQuery=true)
	public void deleteDepartmentById(int id);
	
	@Query(value="select super_id_fk from department where dep_id = ?1", nativeQuery=true)
	public int findSupervisorById(int id);
	
	@Query(value="select d.dep_id from department d, employee e where e.username_fk=?1 and e.dep_id_fk=d.dep_id", nativeQuery=true)
	public int findIdByUsername(String username);
}
