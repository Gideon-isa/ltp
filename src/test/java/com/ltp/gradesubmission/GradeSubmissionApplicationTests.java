package com.ltp.gradesubmission;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.ExecutionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ltp.gradesubmission.controller.GradeController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GradeSubmissionApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void testShoeGradeForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");

		mockMvc.perform(request)
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("form"))
		.andExpect(model().attributeExists("formObject"));

	}

	@Test
	public void testSuccessfullSubmission() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
		.param("name", "Gideon")
		.param("subject", "Mathematics")
		.param("score", "A+");

		mockMvc.perform(request)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/grades"));
	}

	@Test
	public void testUnsuccessfullSubmission() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
		.param("name", "    ")
		.param("subject", "    ")
		.param("score", "R+");

		mockMvc.perform(request)
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("form"));


	}

	@Test
	public void testGetGrades() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/grades");

		mockMvc.perform(request)
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("grades"))
		.andExpect(model().attributeExists("grad"));

	}
}
