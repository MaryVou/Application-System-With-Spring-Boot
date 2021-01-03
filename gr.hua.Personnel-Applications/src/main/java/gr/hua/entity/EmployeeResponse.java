package gr.hua.entity;

import java.util.Date;

public class EmployeeResponse {

	public int id;
	public String first_name;
	public String last_name;
	public String email;
	public String phone;
	public String address;
	public Date birth_date;
	public Date hire_date;
	public String department;
	public String username;

	public EmployeeResponse(int id, String first_name, String last_name, String email, String phone, String address,
			Date birth_date, Date hire_date, String department, String username) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.birth_date = birth_date;
		this.hire_date = hire_date;
		this.department = department;
		this.username = username;
	}
	
}
