package carent.model;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int userCode;
	private String email;
	private String passwd;
	private String role;
	private String name;
	private String surname;
	private String phone;
	

	public UserBean (int u , String e, String p, String r, String n, String s, String ph) {
		this.userCode=u;
		this.email=e;
		this.passwd=p;
		this.role=r;
		this.name=n;
		this.surname=s;
		this.phone=ph;
	}
	
	

	public UserBean() {
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getUserCode () {
		return this.userCode;
	}
	
	public void setUserCode (int userCode) {
		this.userCode=userCode;
	}
	
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String toString () {
		return email+" - "+passwd+" - "+role;
	}
	
	
}
