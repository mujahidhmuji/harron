package com.cts.book.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long bookId;

	private String bookName;

	private String author;

	private int edition;

	@Temporal(TemporalType.DATE)
	private Date publishedDate;

	private Long pages;

	private int totalCopies;

	private int availableCopies;

	public Book() {
	}

	public Book(Long bookId, String bookName, String author, int edition, Date publishedDate, Long pages,
			int totalCopies, int availableCopies) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.edition = edition;
		this.publishedDate = publishedDate;
		this.pages = pages;
		this.totalCopies = totalCopies;
		this.availableCopies = availableCopies;
	}

	public Long getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Long getPages() {
		return pages;
	}

	public void setPages(Long pages) {
		this.pages = pages;
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	@Override
	public String toString() {
		return "Book [id=" + bookId + ", bookName=" + bookName + ", author=" + author + ", edition=" + edition
				+ ", publishedDate=" + publishedDate + ", pages=" + pages + ", totalCopies=" + totalCopies
				+ ", availableCopies=" + availableCopies + "]";
	}

}
