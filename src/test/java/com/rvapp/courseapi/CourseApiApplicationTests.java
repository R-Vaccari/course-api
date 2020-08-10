package com.rvapp.courseapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rvapp.courseapi.domain.Student;
import com.rvapp.courseapi.resources.StudentResource;
import com.rvapp.courseapi.services.StudentService;

@SpringBootTest
class CourseApiApplicationTests {
	



	@Mock private StudentService serviceStudent;
	@InjectMocks private StudentResource studentResource;
	
	MockMvc mockMvc;


	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentResource).build();
	}
	
	@Test
	void addToDatabase() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Student student = new Student(null, "Student", "01","student01@gmail.com", "+777777");
		ResponseEntity<Student> response = studentResource.post(student);
		
		assertEquals(response.getStatusCodeValue(), 201);
	}

}
