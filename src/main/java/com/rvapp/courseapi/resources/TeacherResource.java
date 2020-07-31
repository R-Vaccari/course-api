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

import com.rvapp.courseapi.domain.Teacher;
import com.rvapp.courseapi.services.TeacherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Teachers")
@RestController
@RequestMapping(value = "/teachers")
public class TeacherResource {
	
	@Autowired
	private TeacherService service;
	
	@ApiOperation(value = "Finds all teachers")
	@GetMapping
	public ResponseEntity<List<Teacher>> findAll() {
		List<Teacher> teachers = service.findAll();
		return ResponseEntity.ok().body(teachers);
	}
	
	@ApiOperation(value = "Finds a teacher by his id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Teacher> findById(@PathVariable String id) {
		Teacher teacher = service.findById(id);
		return ResponseEntity.ok().body(teacher);
	}
	
	@ApiOperation(value = "Registers a new teacher")
	@PostMapping
	public ResponseEntity<Teacher> post(@RequestBody Teacher teacher) {
		service.post(teacher);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(teacher.getId()).toUri();
		return ResponseEntity.created(uri).body(teacher);
	}
	
	@ApiOperation(value = "Excludes a teacher")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Updates a teacher's informations")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Teacher> update(@PathVariable String id, @RequestBody Teacher source) {
		Teacher target = service.findById(id);
		service.update(source, target);
		return ResponseEntity.noContent().build();
	}

}
