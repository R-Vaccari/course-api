package com.rvapp.courseapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rvapp.courseapi.domain.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

}
