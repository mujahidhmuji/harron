package com.cts.student.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long studentId;

	private String studentName;

	@Column(unique = true)
	private String email;

	private String password;

	// @JsonFormat(pattern = "dd-mm-yyyy")
	@Temporal(TemporalType.DATE)
	private Date dob;

	private String department;

	@JsonFormat(pattern = "yyyy")
	// @Temporal(TemporalType.DATE)
	private int yearOfJoin;

	@JsonFormat(pattern = "yyyy")
	private int yearOfPass;

	private long phoneNumber;

	private int bookLimit = 3;

	public Student() {
		super();
	}

	public Long getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getBookLimit() {
		return bookLimit;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getYearOfJoin() {
		return yearOfJoin;
	}

	public void setYearOfJoin(int yearOfJoin) {
		this.yearOfJoin = yearOfJoin;
	}

	public int getYearOfPass() {
		return yearOfPass;
	}

	public void setYearOfPass(int yearOfPass) {
		this.yearOfPass = yearOfPass;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", email=" + email + ", password="
				+ password + ", dob=" + dob + ", department=" + department + ", yearOfJoin=" + yearOfJoin
				+ ", yearOfPass=" + yearOfPass + ", phoneNumber=" + phoneNumber + ", bookLimit=" + bookLimit + "]";
	}

}
