package gr.hua.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int id;

	@Column(name = "f_name")
	private String fname;

	@Column(name = "l_name")
	private String lname;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	private Date birth_date;

	@Column(name = "hire_date")
	@Temporal(TemporalType.DATE)
	private Date hire_date;

	@Column(name = "works_since")
	@Temporal(TemporalType.DATE)
	private Date works_since;

	@Column(name = "days")
	private int days;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "dep_id_fk")
	private Department department;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username_fk")
	private User user;

	public Employee() {

	}
	
	public Employee(String fname, String lname, String email, String phone, String address, Date birth_date,
			Date hire_date, Date works_since) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.birth_date = birth_date;
		this.hire_date = hire_date;
		this.works_since = works_since;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", birth_date=" + birth_date + ", hire_date=" + hire_date + ", works_since="
				+ works_since + ", department=" + department + ", user=" + user + "]";
	}

}
