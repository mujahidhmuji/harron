package com.cts.student.controller;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.student.entity.Student;
import com.cts.student.repository.StudentRepository;

@CrossOrigin
@RestController
public class StudentContoller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager em;

	@GetMapping("/student/{studentId}")
	public Optional<Student> findStudentById(@PathVariable Long studentId) throws Exception {
		checkStudentId(studentId);
		Optional<Student> s1 = repository.findById(studentId);
		return s1;
	}

	@GetMapping("/student/check/{studentId}")
	public boolean checkStudentIdAndLimit(@PathVariable Long studentId) throws Exception {
		boolean result = false;
		Long isPresent = repository.checkForId(studentId);
		if (isPresent != null) {
			result = true;
		} else {
			throw new Exception("Student Not available");
		}
		Integer checkBookLimit = repository.checkStudentBookLimit(studentId);
		logger.info("{}", checkBookLimit);
		if (checkBookLimit == null) {
			throw new Exception("Student Limit Exceeded");
		}
		return result;
	}

	@GetMapping("/check/student/{studentId}")
	public boolean checkStudentId(@PathVariable Long studentId) throws Exception {
		boolean result = false;
		Long checkForId = repository.checkForId(studentId);
		if (checkForId != null) {
			result = true;
		} else {
			throw new Exception("This Student is not available in this Library");
		}
		return result;
	}

	@GetMapping("/check/{studentId}")
	public String checkId(@PathVariable Long studentId) throws Exception {
		String str = "False";
		boolean b;
		b = checkStudentIdAndLimit(studentId);
		logger.info("Check -> ");
		if (b) {
			str = "True";
		}
		return str;
	}

	@PostMapping("/student/login")
	public boolean authentication(@RequestBody Student student) {
		boolean result = false;
		String email = student.getEmail();
		Long id = repository.getStudentId(email);
		String password = student.getPassword();
		String dbPassword = repository.getPassword(id);
		if (password.equalsIgnoreCase(dbPassword)) {
			result = true;
		}
		return result;
	}

	@GetMapping("/student/incrementlimit/{studentId}")
	public void incrementStudentBookLimit(@PathVariable Long studentId) throws Exception {
		repository.incrementBookLimit(studentId);
	}

	@GetMapping("/student/decrementlimit/{studentId}")
	public void decrementStudentBookLimit(@PathVariable Long studentId) throws Exception {
		repository.decrementBookLimit(studentId);
		// return true;
	}

	@PostMapping("/student/add")
	public Student addStudent(@RequestBody Student student) {
		try {
			repository.save(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Student result = repository.findByEmail(student.getEmail());
		return result;
	}
	
	@GetMapping("/student/email/{email}")
	public Student searchByEmail(@PathVariable String email) throws Exception {
		return repository.findByEmail(email);
	}

}
