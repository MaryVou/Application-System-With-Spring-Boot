package gr.hua.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Column(name = "enabled")
	@JsonIgnore
	private Boolean enabled;

	@Column(name = "emp_id")
	@JsonIgnore
	private int emp_id;

	public User() {

	}

	public User(String username, String password, Boolean enabled, int emp_id) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.emp_id = emp_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

}
