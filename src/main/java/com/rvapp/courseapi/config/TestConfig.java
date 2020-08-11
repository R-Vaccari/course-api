package com.rvapp.courseapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rvapp.courseapi.domain.ClassGroup;
import com.rvapp.courseapi.domain.Course;
import com.rvapp.courseapi.domain.Student;
import com.rvapp.courseapi.domain.Teacher;
import com.rvapp.courseapi.domain.enums.ClassLevel;
import com.rvapp.courseapi.domain.enums.CourseType;
import com.rvapp.courseapi.repositories.ClassGroupRepository;
import com.rvapp.courseapi.repositories.CourseRepository;
import com.rvapp.courseapi.repositories.StudentRepository;
import com.rvapp.courseapi.repositories.TeacherRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired private StudentRepository studentRepository;
	
	@Autowired private TeacherRepository teacherRepository;
	
	@Autowired private ClassGroupRepository classRepository;
	
	@Autowired private CourseRepository courseRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		studentRepository.deleteAll();
		teacherRepository.deleteAll();
		classRepository.deleteAll();
		courseRepository.deleteAll();
		
		Teacher teacher01 = new Teacher(null, "Teacher", "01", "teacher01@gmail.com", "+999999");
		Teacher teacher02 = new Teacher(null, "Teacher", "02", "teacher02@gmail.com", "+999999");

		teacherRepository.saveAll(Arrays.asList(teacher01, teacher02));
		
		Student student01 = new Student(null, "Student", "01","student01@gmail.com", "+777777");
		Student student02 = new Student(null, "Student", "02","student02@gmail.com", "+777777");
		Student student03 = new Student(null, "Student", "03","student03@gmail.com", "+777777");
		Student student04 = new Student(null, "Student", "04","student04@gmail.com", "+777777");
		Student student05 = new Student(null, "Student", "05","student05@gmail.com", "+777777");
		Student student06 = new Student(null, "Student", "06","student06@gmail.com", "+777777");
		Student student07 = new Student(null, "Student", "07","student07@gmail.com", "+777777");
		Student student08 = new Student(null, "Student", "08","student08@gmail.com", "+777777");
		
		studentRepository.saveAll(Arrays.asList(student01, student02, student03, student04, student05, student06, student07, student08));
		
		ClassGroup class01 = new ClassGroup(null, "Class EF001", ClassLevel.INTERMEDIATE, teacher01);
		class01.getStudents().addAll(Arrays.asList(student01, student02, student03, student04));
		
		ClassGroup class02 = new ClassGroup(null, "Class EF002", ClassLevel.BUSINESS, teacher02);
		class02.getStudents().addAll(Arrays.asList(student05, student06, student07, student08));
		
		classRepository.saveAll(Arrays.asList(class01, class02));
		studentRepository.saveAll(Arrays.asList(student01, student02, student03, student04, student05, student06, student07, student08));
		
		Course course01 = new Course(null, "English Course", CourseType.ENGLISH);
		Course course02 = new Course(null, "Curso de Português", CourseType.PORTUGUESE);
		Course course03 = new Course(null, "Deutsche Kurs", CourseType.GERMAN);
		Course course04 = new Course(null, "Corso d'Italiano", CourseType.ITALIAN);
		
		course01.getClasses().addAll(Arrays.asList(class01, class02));
		
		courseRepository.saveAll(Arrays.asList(course01, course02, course03, course04));
	}

}
