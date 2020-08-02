package com.rvapp.courseapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rvapp.courseapi.domain.ClassGroup;
import com.rvapp.courseapi.domain.enums.ClassLevel;
import com.rvapp.courseapi.exceptions.DuplicateObjectException;
import com.rvapp.courseapi.exceptions.ObjectNotFoundException;
import com.rvapp.courseapi.repositories.ClassGroupRepository;


@Service
public class ClassGroupService {
	
	@Autowired
	private ClassGroupRepository repository;
	
	public List<ClassGroup> findAll() {
		return repository.findAll();
	}
	
	public ClassGroup findById(String id) {
		Optional<ClassGroup> classGroup = repository.findById(id);
		return classGroup.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<ClassGroup> findByLevel(String text) {
		try { 
			return repository.findByClassLevel(ClassLevel.valueOf(text));
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
	
	public void post(ClassGroup classGroup) {
		try {
			repository.insert(classGroup);
		} catch (RuntimeException e) {  // accuses MongoWriteException but won't catch it
			throw new DuplicateObjectException(classGroup.getId());
		}
	}
	
	public void update(ClassGroup source, ClassGroup target) {
		if (source.getClassName() != null) {
			target.setClassName(source.getClassName());
		} if (source.getClassLevel() != null) {
			target.setClassLevel(source.getClassLevel());
		} if (source.getTeacher() != null) {
			target.setTeacher(source.getTeacher());
		} repository.save(target);
	}

}
