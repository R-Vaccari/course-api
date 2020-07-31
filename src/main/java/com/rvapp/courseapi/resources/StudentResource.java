package com.rvapp.courseapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rvapp.courseapi.domain.Student;
import com.rvapp.courseapi.services.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Students")
@RestController
@RequestMapping(value = "/students")
public class StudentResource {
	
	@Autowired
	private StudentService service;
	
	@ApiOperation(value = "Finds all students")
	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		List<Student> students = service.findAll();
		return ResponseEntity.ok().body(students);
	}
	
	@ApiOperation(value = "Finds a student by his id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Student> findById(@PathVariable String id) {
		Student student = service.findById(id);
		return ResponseEntity.ok().body(student);
	}
	
	@ApiOperation(value = "Registers a new student")
	@PostMapping
	public ResponseEntity<Student> post(@RequestBody Student student) {
		service.post(student);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(student.getId()).toUri();
		return ResponseEntity.created(uri).body(student);
	}
	
	@ApiOperation(value = "Excludes a student")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Updates a student's informations")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student source) {
		Student target = service.findById(id);
		service.update(source, target);
		return ResponseEntity.noContent().build();
	}

}
