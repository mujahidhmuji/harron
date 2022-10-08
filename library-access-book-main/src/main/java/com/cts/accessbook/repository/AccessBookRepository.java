package com.cts.accessbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.accessbook.entity.AccessBook;

public interface AccessBookRepository extends JpaRepository<AccessBook, Long> {

	@Query(value = "select * from access_book where student_id=?1 and book_id=?2 and active_status=true", nativeQuery = true)
	public AccessBook getTakenBookData(Long studentId, Long BookId);

	@Query(value = "select 1 from access_book where student_id=?1 and book_id=?2 and active_status=true", nativeQuery = true)
	public Integer alreadyTeaken(Long studentId, Long BookId);

	@Query(value = "select * from access_book where book_id=?1 and active_status=true", nativeQuery = true)
	public List<AccessBook> unReturnedStudentForBookId(Long bookId);

	@Query(value = "select * from access_book where student_id=?1 and active_status=true", nativeQuery = true)
	public List<AccessBook> unReturnedBookForStudentId(Long studentId);

}
