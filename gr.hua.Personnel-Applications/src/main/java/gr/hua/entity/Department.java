package gr.hua.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "department")
public class Department{

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "dep_id")
	private int id;

	@Column(name = "dep_name", nullable = false)
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "super_id_fk",nullable = true)
	private Employee supervisor;

	public Department() {

	}

	public Department(String name) {
		super();
		this.name = name;
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
	
	public int getSupervisor() {
		return supervisor.getId();
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employee=" + supervisor.getId() + "]";
	}

}
