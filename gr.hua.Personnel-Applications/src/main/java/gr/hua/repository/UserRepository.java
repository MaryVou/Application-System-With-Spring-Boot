package gr.hua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gr.hua.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
	
	@Query(value="select authority from authorities a where a.username=?1",nativeQuery=true)
	public String findAuthorityByUsername(String username);
}
