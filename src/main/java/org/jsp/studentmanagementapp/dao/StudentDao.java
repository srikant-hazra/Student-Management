package org.jsp.studentmanagementapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.studentmanagementapp.dto.Student;
import org.jsp.studentmanagementapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
	@Autowired
	private StudentRepository studentRepository;

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	
	public Optional<Student> findById(int id) {
		return studentRepository.findById(id);
	}

	

	public Optional<Student> findByPhoneAndPassword(long phone, String password) {
		return studentRepository.findByPhoneAndPassword(phone, password);
	}
	public Optional<Student> findByEmailAndPassword(String email, String password) {
		return studentRepository.findByEmailAndPassword(email, password);
	}

	
	
}