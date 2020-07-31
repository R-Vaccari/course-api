package com.rvapp.courseapi.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rvapp.courseapi.domain.Course;
import com.rvapp.courseapi.domain.enums.CourseType;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
	
	List<Course> findByCourseType(CourseType courseType);

}
