package com.rvapp.courseapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rvapp.courseapi.domain.Student;
import com.rvapp.courseapi.exceptions.ObjectNotFoundException;
import com.rvapp.courseapi.resources.CourseResource;
import com.rvapp.courseapi.resources.StudentResource;
import com.rvapp.courseapi.services.CourseService;
import com.rvapp.courseapi.services.StudentService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CourseApiApplicationTests {
	
	@Autowired TestRestTemplate restTemplate;
	@MockBean private StudentService studentService;
	@Autowired private StudentResource studentResource;
	@MockBean private CourseService courseService;
	@Autowired private CourseResource courseResource;
	@Autowired MockMvc mockMvc;


	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentResource).build();
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}
	
	@Test
	@DisplayName("Correct http response for posting")
	void addToDatabase() {
		Student student = new Student(null, "Student", "01","student01@gmail.com", "+777777");
		ResponseEntity<Student> response = studentResource.post(student);
		
		assertEquals(response.getStatusCodeValue(), 201);
	}
	
	@Test
	@DisplayName("Correct http responses for posting and deleting")
	void addAndDelete() {
	
		Student student01 = new Student(null, "Student", "01","student01@gmail.com", "+777777");
		ResponseEntity<Student> response01 = studentResource.post(student01);
		
		verify(studentService).post(student01);

		ResponseEntity<Void> response02 = studentResource.deleteById(student01.getId());
		
		verify(studentService).deleteById(student01.getId());
		
		assertAll(
		() -> assertEquals(response01.getStatusCodeValue(), 201),
		() -> assertEquals(response02.getStatusCodeValue(), 204));
	}
	
	@Test
	@DisplayName("Correct http responses for posting and updating, correct updated instance")
	void addAndUpdate() {
	
		Student student01 = new Student(null, "Student", "01","student01@gmail.com", "+777777");
		ResponseEntity<Student> response01 = studentResource.post(student01);
		
		verify(studentService).post(student01);
		
		Student student02 = new Student(null, "Student", "Test","studentTest@gmail.com", "+999999");

		ResponseEntity<Student> response02 = studentResource.update(student01.getId(), student02);

		assertAll(
		() -> assertEquals(response01.getStatusCodeValue(), 201),
		() -> assertEquals(response02.getStatusCodeValue(), 204),
		() -> assertThat(student01.getLastName().equals(student02.getLastName())),
		() -> assertThat(student01.getEmail().equals(student02.getEmail())),
		() -> assertThat(student01.getTelephone().equals(student02.getTelephone())));	
	}
	

	@Test
	void falseIdTest() throws Exception {
		
		ResponseEntity<Student> response01 = studentResource.findById("test");
		
		when(studentResource.findById("test")).thenThrow(ObjectNotFoundException.class);
		
		// mockMvc.perform(MockMvcRequestBuilders.get("/testpage")).andReturn().getResponse();
		
		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, 
				() -> studentResource.findById("test")); 
	}
}
