package com.cts.book.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.book.entity.Book;
import com.cts.book.repository.BookRepository;

@CrossOrigin
@RestController
public class BookController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BookRepository repository;

	@GetMapping("/book/check/{bookId}")
	public boolean checkBookIExistAndAvailable(@PathVariable Long bookId) throws Exception {
		boolean result = false;
		Long isPresent = repository.checkForId(bookId);
		if (isPresent != null) {
			result = true;
		} else {
			throw new Exception("Book Not available");
		}
		int avbCopies = repository.availableCopies(bookId);
		if (avbCopies == 0) {
			throw new Exception("This book is now not available in this Library");
		}
		return result;
	}

	@GetMapping("/check/book/{bookId}")
	public boolean checkBookId(@PathVariable Long bookId) throws Exception {
		boolean result = false;
		Long checkForId = repository.checkForId(bookId);
		if (checkForId != null) {
			result = true;
		} else {
			throw new Exception("This book is not available in this Library");
		}
		return result;
	}

	@GetMapping("/books")
	public List<Book> displayAllBooks() {
		List<Book> books = repository.findAll();
		return books;
	}

	@GetMapping("/book/{bookId}")
	public Optional<Book> searchById(@PathVariable Long bookId) throws Exception {
		checkBookId(bookId);
		Optional<Book> bookForId = repository.findById(bookId);
		return bookForId;
	}

	@GetMapping("/book/name/{bookName}")
	public List<Book> searchByName(@PathVariable String bookName) throws Exception {
		List<Book> book = repository.findByBookName(bookName);
		if (book.isEmpty()) {
			throw new Exception("No Book available for this name");
		}
		return book;
	}

	@PostMapping("/book/add")
	public void addNewBook(@RequestBody Book book) {
		repository.save(book);
	}

	@PostMapping("/book/updatecopies")
	public Optional<Book> updateBookCopies(@RequestBody Book book) throws Exception {
		Long bookId = book.getBookId();
		checkBookId(bookId);
		int count = book.getAvailableCopies();
		repository.updateTotalCopies(bookId, count);
		repository.updateAvailableCopies(bookId, count);
		Optional<Book> bookForId = repository.findById(bookId);
		return bookForId;
	}

	@GetMapping("/book/incrementAvailableCopies/{bookId}")
	public void incrementBookAvailableCopies(@PathVariable Long bookId) throws Exception {
		repository.incrementAvailableCopies(bookId);
	}

	@GetMapping("/book/decrementAvailableCopies/{bookId}")
	public void decrementBookAvailableCopies(@PathVariable Long bookId) throws Exception {
		repository.decrementAvailableCopies(bookId);
	}

}
