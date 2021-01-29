package gr.hua.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;

public class EmployeeRequest {

	private String fname;
	private String lname;
	private String email;
	private String phone;
	
	@JsonIgnore
	private String address;
	
	@DateTimeFormat(iso = ISO.DATE)
	@JsonIgnore
	private Date birth_date;
	
	@DateTimeFormat(iso = ISO.DATE)
	@JsonIgnore
	private Date hire_date;
	
	@DateTimeFormat(iso = ISO.DATE)
	@JsonIgnore
	private Date works_since;
	
	@JsonIgnore
	private String username;
	
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	private String dep_name;
	
	@JsonIgnore
	private String authority;

	public EmployeeRequest() {
		super();
	}

	public EmployeeRequest(String fname, String lname, String email, String phone) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}

	public EmployeeRequest(String fname, String lname, String email, String phone, String address, Date birth_date,
			Date hire_date, Date works_since, String username, String password, String dep_name) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.birth_date = birth_date;
		this.hire_date = hire_date;
		this.works_since = works_since;
		this.username = username;
		this.password = password;
		this.dep_name = dep_name;
	}

	public EmployeeRequest(String fname, String lname, String email, String phone, String address, Date birth_date,
			Date hire_date, Date works_since, String username, String password, String dep_name, String authority) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.birth_date = birth_date;
		this.hire_date = hire_date;
		this.works_since = works_since;
		this.username = username;
		this.password = password;
		this.dep_name = dep_name;
		this.authority = authority;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public Date getWorks_since() {
		return works_since;
	}

	public void setWorks_since(Date works_since) {
		this.works_since = works_since;
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

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "EmployeeRequest [fname=" + fname + ", lname=" + lname + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", birth_date=" + birth_date + ", hire_date=" + hire_date + ", works_since="
				+ works_since + ", username=" + username + ", password=" + password + ", dep_name=" + dep_name + "]";
	}

}