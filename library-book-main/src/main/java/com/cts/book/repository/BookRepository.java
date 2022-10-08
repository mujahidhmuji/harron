package com.cts.book.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value = "select * from book where book_name LIKE  ?1%", nativeQuery = true)
	List<Book> findByBookName(String bookName);

	// List<Book> findByAuthorName(String author);

	List<Book> deleteByBookName(String bookName);

	@Query(value = "select book_id from book where book_id=?1", nativeQuery = true)
	Long checkForId(Long bookId);

	@Query(value = "update book set total_copies = total_copies + ?1 where book_id = ?2", nativeQuery = true)
	void updatingBookCopies(int copies, Long id);

	@Query(value = "select available_copies from book where book_id=?1", nativeQuery = true)
	int availableCopies(Long bookId);

	@Query(value = "update book set available_copies = available_copies+1 where book_id =?1", nativeQuery = true)
	@Transactional
	@Modifying
	void incrementAvailableCopies(Long bookId);

	@Query(value = "update book set available_copies=available_copies-1 where book_id=?1", nativeQuery = true)
	@Transactional
	@Modifying
	void decrementAvailableCopies(Long bookId);

	@Query(value = "update book set available_copies=available_copies+?2  where book_id=?1", nativeQuery = true)
	@Transactional
	@Modifying
	void updateAvailableCopies(Long bookId, int count);

	@Query(value = "update book set total_copies=total_copies+?2  where book_id=?1", nativeQuery = true)
	@Transactional
	@Modifying
	void updateTotalCopies(Long bookId, int count);
}
