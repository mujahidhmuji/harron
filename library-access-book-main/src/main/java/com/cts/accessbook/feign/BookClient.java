package com.cts.accessbook.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-library", url = "${book.uri}")
public interface BookClient {

	@GetMapping("/book/check/{bookId}")
	public boolean checkBookExistAndAvailable(@PathVariable("bookId") Long bookId);

	@GetMapping("/book/incrementAvailableCopies/{bookId}")
	public void incrementBookAvailableCopies(@PathVariable("bookId") Long bookId) throws Exception;

	@GetMapping("/book/decrementAvailableCopies/{bookId}")
	public void decrementBookAvailableCopies(@PathVariable("bookId") Long bookId) throws Exception;

	@GetMapping("/check/book/{bookId}")
	public boolean checkBookId(@PathVariable("bookId") Long bookId) throws Exception;
}
