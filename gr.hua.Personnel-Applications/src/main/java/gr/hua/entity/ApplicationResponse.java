package gr.hua.entity;

import java.sql.Blob;
import java.util.Date;

public class ApplicationResponse {

	private int id;
	private String type;
	private String category;
	private int days;
	private Date start_date;
	private Date last_date;
	private Blob req_papers;
	private Boolean super_sig;
	private Boolean pd_sig;
	private Boolean mgr_sig;
	private int emp_id;

	public ApplicationResponse(int id, String type, String category, int days, Date start_date, Date last_date, Blob req_papers,
			Boolean super_sig, Boolean pd_sig, Boolean mgr_sig, int emp_id) {
		super();
		this.id = id;
		this.type = type;
		this.category = category;
		this.days = days;
		this.start_date = start_date;
		this.last_date = last_date;
		this.req_papers = req_papers;
		this.super_sig = super_sig;
		this.pd_sig = pd_sig;
		this.mgr_sig = mgr_sig;
		this.emp_id = emp_id;
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

	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

}
