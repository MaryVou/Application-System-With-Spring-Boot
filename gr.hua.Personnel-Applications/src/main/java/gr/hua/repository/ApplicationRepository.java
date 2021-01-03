package gr.hua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.hua.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application,Integer>{

}
