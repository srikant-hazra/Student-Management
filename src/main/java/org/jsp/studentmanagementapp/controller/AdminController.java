package org.jsp.studentmanagementapp.controller;

import java.util.List;

import org.jsp.studentmanagementapp.dto.Admin;
import org.jsp.studentmanagementapp.dto.ResponseStructure;
import org.jsp.studentmanagementapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("/{student_id}")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin,
			@PathVariable int student_id) {
		return adminService.saveAdmin(admin, student_id);
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Admin>>> findByName(@PathVariable String name) {
		return adminService.findByName(name);
	}

	@GetMapping("/find-by-email/{email}")
	public ResponseEntity<ResponseStructure<List<Admin>>> findByEmail(@PathVariable String email) {
		return adminService.findByEmail(email);
	}


	@GetMapping("/{student_id}")
	public ResponseEntity<ResponseStructure<List<Admin>>> findByStudentId(@PathVariable int student_id) {
		return adminService.findByStudentId(student_id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Admin>>> findAll() {
		return adminService.findAll();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(@PathVariable(name = "id") int id) {
		return adminService.delete(id);
	}
}