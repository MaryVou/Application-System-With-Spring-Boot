package gr.hua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gr.hua.entity.Department; 

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	@Query(value="select dep_id from department where dep_name = ?1", nativeQuery=true)
	public int findIdByName(String dep_name);
}
