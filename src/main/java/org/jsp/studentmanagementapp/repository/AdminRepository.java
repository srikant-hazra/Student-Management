package org.jsp.studentmanagementapp.repository;

import java.util.List;

import org.jsp.studentmanagementapp.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	List<Admin> findByName(String name);

	List<Admin> findByEmail(String email);


	@Query("select a from Admin a where a.student.id=?1")
	List<Admin> findByStudentId(int student_id);
}