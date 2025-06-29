package org.jsp.studentmanagementapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.studentmanagementapp.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByName(String name);

	Optional<Student> findByPhone(long phone);

	Optional<Student> findByPhoneAndPassword(long phone, String password);

	Optional<Student> findByEmailAndPassword(String email, String password);

}
