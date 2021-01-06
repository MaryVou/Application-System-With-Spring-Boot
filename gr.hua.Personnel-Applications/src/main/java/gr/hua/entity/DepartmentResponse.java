package gr.hua.entity;

public class DepartmentResponse {

	private int id;
	private String name;
	private int super_id;
	private String super_fname;
	private String super_lname;
	private String super_email;

	public DepartmentResponse(int id, String name, int super_id, String super_fname, String super_lname,
			String super_email) {
		super();
		this.id = id;
		this.name = name;
		this.super_id = super_id;
		this.super_fname = super_fname;
		this.super_lname = super_lname;
		this.super_email = super_email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSuper_id() {
		return super_id;
	}

	public void setSuper_id(int super_id) {
		this.super_id = super_id;
	}

	public String getSuper_fname() {
		return super_fname;
	}

	public void setSuper_fname(String super_fname) {
		this.super_fname = super_fname;
	}

	public String getSuper_lname() {
		return super_lname;
	}

	public void setSuper_lname(String super_lname) {
		this.super_lname = super_lname;
	}

	public String getSuper_email() {
		return super_email;
	}

	public void setSuper_email(String super_email) {
		this.super_email = super_email;
	}

	@Override
	public String toString() {
		return "DepartmentResponse [id=" + id + ", name=" + name + ", super_id=" + super_id + ", super_fname="
				+ super_fname + ", super_lname=" + super_lname + ", super_email=" + super_email + "]";
	}

}
