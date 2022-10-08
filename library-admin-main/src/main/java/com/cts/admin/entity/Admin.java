package com.cts.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Admin {

	@Id
	@GeneratedValue
	private Long adminId;

	private String adminName;

	@Column(unique = true)
	private String email;

	private String Password;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private long phoneNumber;

	private String address;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Long adminId, String adminName, Date dob, long phoneNumber, String address) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public Long getAdminId() {
		return adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", email=" + email + ", Password=" + Password
				+ ", dob=" + dob + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}

}
