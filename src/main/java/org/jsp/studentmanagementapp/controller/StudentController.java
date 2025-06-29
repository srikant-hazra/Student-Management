package org.jsp.studentmanagementapp.controller;

import java.util.List;

import org.jsp.studentmanagementapp.dto.Student;
import org.jsp.studentmanagementapp.dto.ResponseStructure;
import org.jsp.studentmanagementapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<Student> saveStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Student>>> findAll() {
		return studentService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Student>> findById(@PathVariable(name = "id") int id) {
		return studentService.findById(id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student student) {
		return studentService.updateStudent(student);
	}



	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<Student>> verify(@RequestParam(name = "phone") long phone,
			@RequestParam(name = "password") String password) {
		return studentService.verify(phone, password);
	}
	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<Student>> verify(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		return studentService.verify(email, password);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteStudent(@PathVariable int id) {
		return studentService.deleteStudent(id);
	}
}
