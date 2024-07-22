package org.jsp.studentmanagementapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.studentmanagementapp.dao.StudentDao;
import org.jsp.studentmanagementapp.dao.AdminDao;
import org.jsp.studentmanagementapp.dto.Student;
import org.jsp.studentmanagementapp.dto.Admin;
import org.jsp.studentmanagementapp.dto.ResponseStructure;
import org.jsp.studentmanagementapp.exception.StudentNotFoundException;
import org.jsp.studentmanagementapp.repository.AdminRepository;
import org.jsp.studentmanagementapp.exception.AdminNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private StudentDao studentDao;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin, int student_id) {
		Optional<Student> recStudent = studentDao.findById(student_id);
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		if (recStudent.isPresent()) {
			Student dbStudent = recStudent.get();
			dbStudent.getAdmins().add(admin);
			admin.setStudent(dbStudent);
			structure.setData(adminDao.save(admin));
			structure.setMessage("Product added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
		}
		throw new StudentNotFoundException("Invalid Student Id");
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findByName(String name) {
		List<Admin> admins = adminDao.findByName(name);
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
		if (admins.isEmpty()) {
			throw new AdminNotFoundException("No Admins found with entered name");
		}
		structure.setData(admins);
		structure.setMessage("List of Admins for entered name");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findByEmail(String email) {
		List<Admin> admins = adminDao.findByEmail(email);
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
		if (admins.isEmpty()) {
			throw new AdminNotFoundException("No admins with entered email");
		}
		structure.setData(admins);
		structure.setMessage("List of admins for entered email");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
	}



	public ResponseEntity<ResponseStructure<List<Admin>>> findByStudentId(int student_id) {
		List<Admin> admins = adminDao.findByStudentId(student_id);
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
		if (admins.isEmpty()) {
			throw new AdminNotFoundException("No admins found for entered Student Id");
		}
		structure.setData(admins);
		structure.setMessage("List of admins for entered Student Id");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findAll() {
		List<Admin> admins = adminDao.findAll();
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
		structure.setData(admins);
		structure.setMessage("List of All admins");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
	}
	
	 private AdminRepository adminRepository;

	    public ResponseEntity<ResponseStructure<String>> delete(int id) {
	        if (adminRepository.existsById(id)) {
	            adminRepository.deleteById(id);
	            ResponseStructure<String> response = new ResponseStructure<>();
	            response.setMessage("Admin deleted successfully");
	            response.setStatusCode(HttpStatus.OK.value());
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            ResponseStructure<String> response = new ResponseStructure<>();
	            response.setMessage("Admin not found");
	            response.setStatusCode(HttpStatus.NOT_FOUND.value());
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    }
	}
