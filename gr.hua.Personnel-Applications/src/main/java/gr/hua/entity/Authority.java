package gr.hua.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "username")
	private User user;

	@Column(name = "authority", nullable = false)
	private String authority;
	
	@Id
	@Column(name = "ix_auth_username")
	private String ix_auth_username;

	public Authority() {

	}

	public Authority(User user, String authority) {
		super();
		this.user = user;
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authority [user=" + user + ", authority=" + authority + "]";
	}

}
