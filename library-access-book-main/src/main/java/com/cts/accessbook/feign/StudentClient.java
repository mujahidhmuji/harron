package com.cts.accessbook.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student-library", url = "${student.uri}")
public interface StudentClient {

	@GetMapping("/student/check/{studentId}")
	public boolean checkStudentIdAndLimit(@PathVariable("studentId") Long studentId) throws Exception;

	@GetMapping("/student/incrementlimit/{studentId}")
	public void incrementStudentBookLimit(@PathVariable("studentId") Long studentId) throws Exception;

	@GetMapping("/student/decrementlimit/{studentId}")
	public void decrementStudentBookLimit(@PathVariable("studentId") Long studentId) throws Exception;

	@GetMapping("/check/student/{studentId}")
	public boolean checkStudentId(@PathVariable("studentId") Long studentId) throws Exception;
}
