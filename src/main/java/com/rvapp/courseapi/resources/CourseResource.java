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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rvapp.courseapi.domain.Course;
import com.rvapp.courseapi.resources.util.URL;
import com.rvapp.courseapi.services.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Courses")
@RestController
@RequestMapping(value = "/courses")
public class CourseResource {
	
	@Autowired
	private CourseService service;
	
	@ApiOperation(value = "Finds all courses")
	@GetMapping
	public ResponseEntity<List<Course>> findAll() {
		List<Course> courses = service.findAll();
		return ResponseEntity.ok().body(courses);
	}
	
	@ApiOperation(value = "Finds a course by it's id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Course> findById(@PathVariable String id) {
		Course course = service.findById(id);
		return ResponseEntity.ok().body(course);
	}
	
	@ApiOperation(value = "Finds a course by it's type")
	@GetMapping(value = "/typesearch")
	public ResponseEntity<List<Course>> findByType(@RequestParam(value="type") String text) {
		text = URL.decodeParam(text);
		List<Course> courses = service.findByType(text);
		return ResponseEntity.ok().body(courses);
	}
	
	@ApiOperation(value = "Registers a new course")
	@PostMapping
	public ResponseEntity<Course> post(@RequestBody Course course) {
		service.post(course);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(course.getId()).toUri();
		return ResponseEntity.created(uri).body(course);
	}
	
	@ApiOperation(value = "Excludes a course")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Updates a course's informations")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Course> update(@PathVariable String id, @RequestBody Course source) {
		Course target = service.findById(id);
		service.update(source, target);
		return ResponseEntity.noContent().build();
	}

}
