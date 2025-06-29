package org.jsp.studentmanagementapp.dao;

import java.util.List;

import org.jsp.studentmanagementapp.dto.Admin;
import org.jsp.studentmanagementapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {
	@Autowired
	private AdminRepository adminRepository;

	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}

	public List<Admin> findByName(String name) {
		return adminRepository.findByName(name);
	}

	public List<Admin> findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	public List<Admin> findByStudentId(int student_id) {
		return adminRepository.findByStudentId(student_id);
	}



	public List<Admin> findAll() {
		return adminRepository.findAll();
	}
	
	
}