package com.cts.student.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByStudentName(String studentName);

	List<Student> deleteByStudentName(String studentName);

	@Query(value = "select * from student where book_limit<=2", nativeQuery = true)
	List<Student> studentsWithUnreturnedBooks();

	@Query(value = "select student_id from student where student_id=?1", nativeQuery = true)
	Long checkForId(Long studentId);

	@Query(value = "select password from student where student_id = ?1", nativeQuery = true)
	String getPassword(Long studentId);

	@Query(value = "select 1 from student where book_limit <=3 and book_limit>0 and student_id = ?1", nativeQuery = true)
	Integer checkStudentBookLimit(Long studentId);

	@Query(value = "select book_limit from student where student_id=?1", nativeQuery = true)
	int studentBookLimit(Long studentId);

	@Query(value = "update student set book_limit = book_limit+1 where student_id =?1", nativeQuery = true)
	@Transactional
	@Modifying
	void incrementBookLimit(Long studentId);

	@Query(value = "update student set book_limit=book_limit-1 where student_id=?1", nativeQuery = true)
	@Transactional
	@Modifying
	void decrementBookLimit(Long studentId);

	@Query(value = "select true from student where studrnt_id=?1", nativeQuery = true)
	boolean checkStudentId(Long studentId);

	@Query(value = "select student_id from student where email=?1", nativeQuery = true)
	Long getStudentId(String email);

	@Query(value = "select * from student where email=?1", nativeQuery = true)
	Student findByEmail(String email);
}
