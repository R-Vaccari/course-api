package com.rvapp.courseapi.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rvapp.courseapi.domain.ClassGroup;
import com.rvapp.courseapi.domain.enums.ClassLevel;

@Repository
public interface ClassGroupRepository extends MongoRepository<ClassGroup, String> {
	
	List<ClassGroup> findByClassLevel(ClassLevel classLevel);

}
