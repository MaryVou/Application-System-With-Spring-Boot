package gr.hua.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "application")
public class Application {

	@Id
	@Column(name = "app_id")
	private int id;

	@Column(name = "type")
	private String type;

	@Column(name = "days")
	private int days;

	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date start_date;

	@Column(name = "last_date")
	@Temporal(TemporalType.DATE)
	private Date last_date;

	@Column(name = "req_papers")
	private Blob req_papers = null;

	@Column(name = "super_sig")
	private Boolean super_sig = null;

	@Column(name = "pd_sig")
	private Boolean pd_sig = null;

	@Column(name = "mgr_sig")
	private Boolean mgr_sig = null;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "emp_id_fk")
	private Employee employee;

	public Application() {

	}

	public Application(String type, int days, Date start_date, Date last_date, Blob req_papers, Boolean super_sig,
			Boolean pd_sig, Boolean mgr_sig, Employee employee) {
		super();
		this.type = type;
		this.days = days;
		this.start_date = start_date;
		this.last_date = last_date;
		this.req_papers = req_papers;
		this.super_sig = super_sig;
		this.pd_sig = pd_sig;
		this.mgr_sig = mgr_sig;
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getLast_date() {
		return last_date;
	}

	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}

	public Blob getReq_papers() {
		return req_papers;
	}

	public void setReq_papers(Blob req_papers) {
		this.req_papers = req_papers;
	}

	public Boolean getSuper_sig() {
		return super_sig;
	}

	public void setSuper_sig(Boolean super_sig) {
		this.super_sig = super_sig;
	}

	public Boolean getPd_sig() {
		return pd_sig;
	}

	public void setPd_sig(Boolean pd_sig) {
		this.pd_sig = pd_sig;
	}

	public Boolean getMgr_sig() {
		return mgr_sig;
	}

	public void setMgr_sig(Boolean mgr_sig) {
		this.mgr_sig = mgr_sig;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", type=" + type + ", days=" + days + ", start_date=" + start_date
				+ ", last_date=" + last_date + ", req_papers=" + req_papers + ", super_sig=" + super_sig + ", pd_sig="
				+ pd_sig + ", mgr_sig=" + mgr_sig + ", employee=" + employee.getId() + "]";
	}

}
