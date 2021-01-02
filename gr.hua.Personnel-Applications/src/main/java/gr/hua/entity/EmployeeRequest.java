package gr.hua.entity;

public class EmployeeRequest {

	private Employee employee;
	private Department department;
	private User user;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public EmployeeRequest(Employee employee, Department department, User user) {
		super();
		this.employee = employee;
		this.department = department;
		this.user = user;
	}

	@Override
	public String toString() {
		return "EmployeeRequest [employee=" + employee + ", department=" + department + ", user=" + user + "]";
	}

}
