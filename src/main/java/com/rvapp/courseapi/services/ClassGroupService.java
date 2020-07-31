package com.rvapp.courseapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvapp.courseapi.domain.ClassGroup;
import com.rvapp.courseapi.domain.enums.ClassLevel;
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
		return classGroup.orElseThrow(() -> new ObjectNotFoundException("Requested id not found."));
	}
	
	public List<ClassGroup> findByLevel(String text) {
		try { 
			return repository.findByClassLevel(ClassLevel.valueOf(text));
		} catch (EnumConstantNotPresentException e) {
			throw new ObjectNotFoundException(e.getMessage());
		}
	}
	
	public void deleteById(String id) {
		repository.deleteById(id);
	}
	
	public void post(ClassGroup classGroup) {
		repository.insert(classGroup);
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
