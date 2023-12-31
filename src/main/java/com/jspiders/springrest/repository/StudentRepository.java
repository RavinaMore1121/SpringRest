package com.jspiders.springrest.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.jspiders.springrest.pojo.StudentPOJO;

@Repository
public class StudentRepository {
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	private static void openConnection() {
		factory = Persistence.createEntityManagerFactory("REST");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}
	
	private static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction != null) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public StudentPOJO addStudent(StudentPOJO pojo) {
		openConnection();
		transaction.begin();
		
		manager.persist(pojo);
		
		transaction.commit();
		closeConnection();
		return pojo;
	}

	public StudentPOJO searchStudent(int id) {
		openConnection();
		transaction.begin();
		
		StudentPOJO student = manager.find(StudentPOJO.class, id);
		
		transaction.commit();
		closeConnection();
		return student;
	}

	public StudentPOJO deleteStudent(int id) {
		// TODO Auto-generated method stub
		openConnection();
		transaction.begin();
		
		StudentPOJO student = manager.find(StudentPOJO.class, id);
		manager.remove(student);
		
		transaction.commit();
		closeConnection();
		return student;
	}

	public StudentPOJO updateStudent(StudentPOJO pojo) {
		// TODO Auto-generated method stub
		openConnection();
		transaction.begin();
		
		StudentPOJO student = manager.find(StudentPOJO.class,pojo.getId());
		student.setName(pojo.getName());
		student.setEmail(pojo.getEmail());
		student.setContact(pojo.getContact());
		student.setAddress(pojo.getAddress());
		
		manager.persist(student);

		
		transaction.commit();
		closeConnection();
		return null;
	}

}