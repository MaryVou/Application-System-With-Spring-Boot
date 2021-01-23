package gr.hua.PersonnelApplications;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan("WebSecurityConfig")
@EnableTransactionManagement
@EntityScan("gr.hua.entity")
@ComponentScan(basePackages={"gr.hua.controller","gr.hua.service","gr.hua.rest","gr.hua.jwt"})
@EnableJpaRepositories("gr.hua.repository")
public class PersonnelApplicationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnelApplicationsApplication.class, args);
	}

}
