package com.jspiders.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jspiders.springrest.pojo.StudentPOJO;
import com.jspiders.springrest.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;

	public StudentPOJO addStudent(StudentPOJO pojo) {
		StudentPOJO student = repository.addStudent(pojo);
		return student;
	}

	public StudentPOJO searchStudent(int id) {
		StudentPOJO student = repository.searchStudent(id);
		return student;
	}

	public StudentPOJO deleteStudent(int id) {
		// TODO Auto-generated method stub
		StudentPOJO student = repository.deleteStudent(id);
		return student;
	}

	public StudentPOJO updateStudent(StudentPOJO pojo) {
		// TODO Auto-generated method stub
		StudentPOJO student = repository.updateStudent(pojo);
		return student;
	}

}