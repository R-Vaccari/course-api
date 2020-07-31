package com.rvapp.courseapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvapp.courseapi.domain.Teacher;
import com.rvapp.courseapi.exceptions.ObjectNotFoundException;
import com.rvapp.courseapi.repositories.TeacherRepository;


@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository repository;
	
	public List<Teacher> findAll() {
		return repository.findAll();
	}
	
	public Teacher findById(String id) {
		Optional<Teacher> teacher = repository.findById(id);
		return teacher.orElseThrow(() -> new ObjectNotFoundException("Requested id not found."));
	}
	
	public void deleteById(String id) {
		repository.deleteById(id);
	}
	
	public void post(Teacher teacher) {
		repository.insert(teacher);
	}
	
	public void update(Teacher source, Teacher target) {
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
