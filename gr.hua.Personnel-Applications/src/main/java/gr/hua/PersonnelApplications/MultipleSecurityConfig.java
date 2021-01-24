package gr.hua.PersonnelApplications;

import javax.sql.DataSource;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gr.hua.jwt.JwtAuthenticationEntryPoint;
import gr.hua.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MultipleSecurityConfig {

	@Configuration
	@Order(1)
	public static class RestWebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

		@Autowired
		public UserDetailsService jwtUserDetailsService;

		@Autowired
		public JwtRequestFilter jwtRequestFilter;

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
		
		@Bean
		public static PasswordEncoder passwordEncoder() {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			// configure AuthenticationManager so that it knows from where to load
			// user for matching credentials
			// Use BCryptPasswordEncoder
			auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().requestMatchers().antMatchers("/authenticate","/api/**").and()
					// dont authenticate this particular request
					.authorizeRequests()// all other requests need to be authenticated
					.antMatchers("/authenticate").permitAll()
					.antMatchers("/api/**").hasAnyRole("ADMIN", "SUPERVISOR", "PDEMPLOYEE", "EMPLOYEE")
					.anyRequest().authenticated().and().
					// make sure we use stateless session; session won't be used to
					// store user's state.
					exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			// Add a filter to validate the tokens with every request
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		}
	}

	@Configuration
	@Order(2)
	public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		public DataSource dataSource;

		@Bean
		public static PasswordEncoder passwordEncoder() {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}
		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {

			auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
					.usersByUsernameQuery("select username,password, enabled from user where username=?")
					.authoritiesByUsernameQuery("select username, authority from authorities where username=?");

		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().and().formLogin().defaultSuccessUrl("/index").permitAll().and().authorizeRequests()
					.antMatchers("/actuator/**", "/swagger-ui/**").permitAll()
					.antMatchers("/index/**")
					.hasAnyRole("ADMIN", "MANAGER", "SUPERVISOR", "PDEMPLOYEE").anyRequest().authenticated().and()
					.logout().permitAll();
		}
	}

}
