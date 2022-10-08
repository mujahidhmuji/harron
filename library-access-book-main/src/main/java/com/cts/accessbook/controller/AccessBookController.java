package com.cts.accessbook.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.accessbook.entity.AccessBook;
import com.cts.accessbook.feign.BookClient;
import com.cts.accessbook.feign.StudentClient;
import com.cts.accessbook.repository.AccessBookRepository;

@CrossOrigin
@RestController
public class AccessBookController {

	@Autowired
	AccessBookRepository repository;

	@Autowired
	private StudentClient studentProxy;

	@Autowired
	private BookClient bookProxy;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/entry")
	public void bookEntry(@RequestBody AccessBook accessBook) throws Exception {
		Long bookId = accessBook.getBookId();
		Long studentId = accessBook.getStudentId();
		Date issueDate = new Date();
		Date dueDate;
		Instant instant = issueDate.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate issue = zdt.toLocalDate();
		LocalDate due = issue.plusDays(15L);
		Instant instant1 = Instant.from(due.atStartOfDay(ZoneId.of("GMT")));
		dueDate = Date.from(instant1);
		accessBook.setIssueDate(issueDate);
		accessBook.setDueDate(dueDate);

		boolean checkStudent = studentProxy.checkStudentIdAndLimit(studentId);
		boolean checkBook = bookProxy.checkBookExistAndAvailable(bookId);
		Integer bookTaken = repository.alreadyTeaken(studentId, bookId);

		if (checkBook == true) {
			if (checkStudent == true) {
				if (bookTaken == null) {
					repository.save(accessBook);
					studentProxy.decrementStudentBookLimit(studentId);
					bookProxy.decrementBookAvailableCopies(bookId);
				} else {
					throw new Exception("You have already taken this book");
				}
			} else {
				logger.info("Student not available");
			}
		} else {
			logger.info("Book not available");
			logger.info("Hi");
		}
	}

	@PostMapping("/calculatefine")
	public AccessBook calculateFine(@RequestBody AccessBook accessBook) throws Exception {
		Long fine = 0L;
		Date date = new Date();
		Long bookId = accessBook.getBookId();
		Long studentId = accessBook.getStudentId();
		studentProxy.checkStudentId(studentId);
		bookProxy.checkBookId(bookId);
		AccessBook result = repository.getTakenBookData(studentId, bookId);
		Integer bookTaken = repository.alreadyTeaken(studentId, bookId);
		if (bookTaken == null) {
			throw new Exception("You have not taken the book yet");
		}
		Date dueDate = result.getDueDate();
		if (date.after(dueDate)) {
			long difference_In_Time = date.getTime() - dueDate.getTime();
			long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
			logger.info("{}", difference_In_Days);
			fine = difference_In_Days * 2;
		}
		result.setFineAmount(fine);
		return result;
	}

	@PostMapping("/return")
	public void bookReturn(@RequestBody AccessBook accessBook) throws Exception {
		accessBook.setReturnedDate(new Date());
		accessBook.setActiveStatus(false);
		try {
			repository.save(accessBook);
			studentProxy.incrementStudentBookLimit(accessBook.getStudentId());
			bookProxy.incrementBookAvailableCopies(accessBook.getBookId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/unreturned/book/{bookId}")
	public List<AccessBook> unreturnedStudentsForBook(@PathVariable Long bookId) throws Exception {
		List<AccessBook> unreturnedStudentForBookId = null;
		boolean checkBookId = bookProxy.checkBookId(bookId);
		if (checkBookId) {
			unreturnedStudentForBookId = repository.unReturnedStudentForBookId(bookId);
			if (unreturnedStudentForBookId == null) {
				throw new Exception("No Book for this " + bookId + " is taken");
			}
		} else {
			throw new Exception("This book is not available in this Library");
		}
		return unreturnedStudentForBookId;
	}

	@GetMapping("/unreturned/student/{studentId}")
	public List<AccessBook> unreturnedBooksForStudent(@PathVariable Long studentId) throws Exception {
		List<AccessBook> unreturnedBooksForStudentId = null;
		boolean checkStudentId = studentProxy.checkStudentId(studentId);
		if (checkStudentId) {
			unreturnedBooksForStudentId = repository.unReturnedBookForStudentId(studentId);
			for (AccessBook ab : unreturnedBooksForStudentId) {
				calculateFine(ab);
			}
		} else {
			throw new Exception("This Student is not available in this Library");
		}
		if (unreturnedBooksForStudentId.isEmpty()) {
			throw new Exception("You haven't taken any book");
		}
		return unreturnedBooksForStudentId;
	}

	@GetMapping("/check/{studentId}")
	public void checkStudent(@PathVariable Long studentId) throws Exception {
		// boolean result;
		try {
			boolean result = studentProxy.checkStudentIdAndLimit(studentId);
			logger.info("{} ->", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/check")
	public void check(@RequestBody AccessBook accessBook) {
		Long bookId = accessBook.getBookId();
		Long studentId = accessBook.getStudentId();
		Integer res = repository.alreadyTeaken(studentId, bookId);
		logger.info("Res : {}", res);
	}
}
