package org.jsp.studentmanagementapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.studentmanagementapp.dao.StudentDao;
import org.jsp.studentmanagementapp.dto.Student;
import org.jsp.studentmanagementapp.repository.StudentRepository;
import org.jsp.studentmanagementapp.dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;

	public ResponseStructure<Student> saveStudent(Student student) {
		ResponseStructure<Student> structure = new ResponseStructure<>();
		structure.setMessage("Student saved");
		structure.setData(studentDao.saveStudent(student));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<List<Student>>> findAll() {
		ResponseStructure<List<Student>> structure = new ResponseStructure<>();
		structure.setData(studentDao.findAll());
		structure.setMessage("List of All Student");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Student>> findById(int id) {
		ResponseStructure<Student> structure = new ResponseStructure<>();
		Optional<Student> recStudent = studentDao.findById(id);
		if (recStudent.isPresent()) {
			structure.setData(recStudent.get());
			structure.setMessage("Student Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
		}
		structure.setData(null);
		structure.setMessage("Student Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudent(Student student) {
		ResponseStructure<Student> structure = new ResponseStructure<>();
		Optional<Student> recStudent = studentDao.findById(student.getId());
		if (recStudent.isPresent()) {
			Student dbStudent = recStudent.get();
			dbStudent.setName(student.getName());
			dbStudent.setEmail(student.getEmail());
			dbStudent.setGrade(student.getGrade());
			dbStudent.setPassword(student.getPassword());
			dbStudent.setPhone(student.getPhone());
			structure.setData(studentDao.saveStudent(dbStudent));
			structure.setMessage("Student Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.ACCEPTED);
		}
		structure.setData(null);
		structure.setMessage("Cannot Update Student as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Student>> verify(long phone, String password) {
		ResponseStructure<Student> structure = new ResponseStructure<>();
		Optional<Student> recStudent = studentDao.findByPhoneAndPassword(phone, password);
		if (recStudent.isPresent()) {
			structure.setData(recStudent.get());
			structure.setMessage("Student Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
		}
		structure.setData(null);
		structure.setMessage("Studen Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Student>> verify(String email, String password) {
		ResponseStructure<Student> structure = new ResponseStructure<>();
		Optional<Student> recStudent = studentDao.findByEmailAndPassword(email, password);
		if (recStudent.isPresent()) {
			structure.setData(recStudent.get());
			structure.setMessage("Studentt Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
		}
		structure.setData(null);
		structure.setMessage("Student Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.NOT_FOUND);
	}

	private StudentRepository studentRepository;

	public ResponseEntity<ResponseStructure<String>> deleteStudent(int id) {
		if (studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			ResponseStructure<String> response = new ResponseStructure<>();
			response.setMessage("Student deleted successfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			ResponseStructure<String> response = new ResponseStructure<>();
			response.setMessage("Student not found");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}
