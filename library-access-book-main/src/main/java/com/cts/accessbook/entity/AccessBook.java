package com.cts.accessbook.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AccessBook {

	@Id
	@GeneratedValue
	private Long id;

	private Long bookId;

	private Long StudentId;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date issueDate;

	@Temporal(TemporalType.DATE)
	private Date dueDate;

	private Date returnedDate;

	private Long fineAmount;

	private boolean activeStatus = true;

	public AccessBook() {
		super();
	}

	public AccessBook(Long bookId, Long studentId, Date issueDate, Date dueDate, Date returnedDate, Long fineAmount) {
		super();
		this.bookId = bookId;
		this.StudentId = studentId;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.returnedDate = returnedDate;
		this.fineAmount = fineAmount;
	}

	public Long getId() {
		return id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getStudentId() {
		return StudentId;
	}

	public void setStudentId(Long studentId) {
		StudentId = studentId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Long getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(Long fineAmount) {
		this.fineAmount = fineAmount;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		return "AccessBook [id=" + id + ", bookId=" + bookId + ", StudentId=" + StudentId + ", takenDate=" + issueDate
				+ ", dueDate=" + dueDate + ", returnedDate=" + returnedDate + ", fineAmount=" + fineAmount + "]";
	}

}
