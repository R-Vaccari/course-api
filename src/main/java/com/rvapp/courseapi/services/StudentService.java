package com.rvapp.courseapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rvapp.courseapi.domain.Student;
import com.rvapp.courseapi.exceptions.DuplicateObjectException;
import com.rvapp.courseapi.exceptions.ObjectNotFoundException;
import com.rvapp.courseapi.repositories.StudentRepository;


@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	public List<Student> findAll() {
		return repository.findAll();
	}
	
	public Student findById(String id) {
		Optional<Student> student = repository.findById(id);
		return student.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public void deleteById(String id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		}
	}
	
	public void post(Student student) {
		try {
			repository.insert(student);
		} catch (RuntimeException e) {  // accuses MongoWriteException but won't catch it
			throw new DuplicateObjectException(student.getId());
		}
	}
	
	public void update(Student source, Student target) {
		if (source.getFirstName() != null) {
			target.setFirstName(source.getFirstName());
		} if (source.getLastName() != null) {
			target.setLastName(source.getLastName());
		} if (source.getEmail() != null) {
			target.setEmail(source.getEmail());
		} if (source.getPassword() != null) {
			target.setPassword(source.getPassword());
		} if (source.getTelephone() != null) {
			target.setTelephone(source.getTelephone());
		} repository.save(target);
	}
}
