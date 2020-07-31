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

import com.rvapp.courseapi.domain.ClassGroup;
import com.rvapp.courseapi.resources.util.URL;
import com.rvapp.courseapi.services.ClassGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Class Groups")
@RestController
@RequestMapping(value = "/classes")
public class ClassGroupResource {
	
	@Autowired
	private ClassGroupService service;
	
	@ApiOperation(value = "Finds all classes")
	@GetMapping
	public ResponseEntity<List<ClassGroup>> findAll() {
		List<ClassGroup> classes = service.findAll();
		return ResponseEntity.ok().body(classes);
	}
	
	@ApiOperation(value = "Finds a class by it's id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClassGroup> findById(@PathVariable String id) {
		ClassGroup classGroup = service.findById(id);
		return ResponseEntity.ok().body(classGroup);
	}
	
	@ApiOperation(value = "Finds classes by their level")
	@GetMapping(value = "/levelsearch")
	public ResponseEntity<List<ClassGroup>> findByLevel(@RequestParam(value="level") String text) {
		text = URL.decodeParam(text);
		List<ClassGroup> classes = service.findByLevel(text);
		return ResponseEntity.ok().body(classes);
	}
	
	@ApiOperation(value = "Registers a new class")
	@PostMapping
	public ResponseEntity<ClassGroup> post(@RequestBody ClassGroup classGroup) {
		service.post(classGroup);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(classGroup.getId()).toUri();
		return ResponseEntity.created(uri).body(classGroup);
	}
	
	@ApiOperation(value = "Excludes a class")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Updates a class' informations")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClassGroup> update(@PathVariable String id, @RequestBody ClassGroup source) {
		ClassGroup target = service.findById(id);
		service.update(source, target);
		return ResponseEntity.noContent().build();
	}
	

}
