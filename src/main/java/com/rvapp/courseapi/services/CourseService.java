package com.rvapp.courseapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rvapp.courseapi.domain.Course;
import com.rvapp.courseapi.domain.enums.CourseType;
import com.rvapp.courseapi.exceptions.DuplicateObjectException;
import com.rvapp.courseapi.exceptions.ObjectNotFoundException;
import com.rvapp.courseapi.repositories.CourseRepository;


@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repository;
	
	public List<Course> findAll() {
		return repository.findAll();
	}
	
	public Course findById(String id) {
		Optional<Course> course = repository.findById(id);
		return course.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<Course> findByType(String text) {
		try { 
			return repository.findByCourseType(CourseType.valueOf(text));
		} catch (IllegalArgumentException e) {
			throw new ObjectNotFoundException(text);
		}
	}
	
	public void deleteById(String id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		}
	}
	
	public void post(Course course) {
		try {
			repository.insert(course);
		} catch (RuntimeException e) {  // accuses MongoWriteException but won't catch it
			throw new DuplicateObjectException(course.getId());
		}
	}
	
	public void update(Course source, Course target) {
		if (source.getCourseName() != null) {
			target.setCourseName(source.getCourseName());
		} if (source.getType() != null) {
			target.setType(source.getType());
		} repository.save(target);
	}

}
