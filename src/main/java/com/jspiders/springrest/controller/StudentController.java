package com.jspiders.springrest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrest.pojo.StudentPOJO;
import com.jspiders.springrest.response.StudentResponse;
import com.jspiders.springrest.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping(path = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> addStudent
					(@RequestBody StudentPOJO pojo) {
		
		StudentPOJO student = service.addStudent(pojo);
		
		//SUCCESS
		if (student != null) {
			return new ResponseEntity<StudentResponse>
				(new StudentResponse
				("Data added successfully. ",
				student, null),
				HttpStatus.ACCEPTED);
		}
		
		//FAILURE
		return new ResponseEntity<StudentResponse>
				(new StudentResponse
						("Data not added. ",
						null, null),
						HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping(path = "/search/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> searchStudent(@PathVariable int id){
		StudentPOJO student = service.searchStudent(id);
		
		//SUCCESS
		if (student != null) {
			return new ResponseEntity<StudentResponse>(new StudentResponse("Data found successfully. ", student, null), HttpStatus.FOUND);
		}
		
		//FAILURE
		return new ResponseEntity<StudentResponse>(new StudentResponse("Data not found. ", student, null), HttpStatus.NOT_FOUND);
	}
	@PostMapping(path ="/delete/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> deleteStudent(@PathVariable int id) {
		StudentPOJO student = service.deleteStudent(id);
		//SUCCESS
		if(student != null) {
			return new ResponseEntity<StudentResponse>(new StudentResponse("Data deleted successfully. ", student, null), HttpStatus.ACCEPTED);
		}
		//FAILURE
		return new ResponseEntity<StudentResponse>(new StudentResponse("Data not deleted. ",null,null),HttpStatus.NOT_EXTENDED);
	}
	
//	@PostMapping(path ="/update/{id}",
//			consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<StudentResponse> updateStudent(@PathVariable int id){
//		StudentPOJO student = service.updateStudent(id);
//		//SUCCESS
//		if(student != null) {
//			return new ResponseEntity<StudentResponse>(new StudentResponse("Data updated successfully. ", student, null), HttpStatus.ACCEPTED);
//		}
//		//Failure
//		return new ResponseEntity<StudentResponse>(new StudentResponse("Data not deleted. ",null,null),HttpStatus.NOT_EXTENDED);
//	}
	
	@PostMapping(path ="/update",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> updateStudent(@RequestBody StudentPOJO pojo){
//		StudentPOJO student = service.updateStudent(id);
		//SUCCESS
		if(pojo != null) {
			service.updateStudent(pojo);
			return new ResponseEntity<StudentResponse>(new StudentResponse("Data updated successfully. ", pojo, null), HttpStatus.ACCEPTED);
		}
		//Failure
		return new ResponseEntity<StudentResponse>(new StudentResponse("Data not updated. ",null,null),HttpStatus.NOT_EXTENDED);
	}

}