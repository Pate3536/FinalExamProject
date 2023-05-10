package ca.sheridancollege.pate3536.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import ca.sheridancollege.pate3536.database.DatabaseAccess;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class TestController {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private DatabaseAccess da;
	

	@Test
	public void testLoadingIndex() throws Exception {

		this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}
	
	@Test
	public void testFindCompetition() throws Exception {
		LinkedMultiValueMap<String,String> competition = new LinkedMultiValueMap<>();
		
	competition.add("projectName", "demo");
	competition.add("description", "abc");
	competition.add("developer", "prince");
	competition.add("rate", "5");
		
		
		int origSize = da.findAll().size();
		
		mockMvc.perform(post("/insertCompetition")
				.params(competition))	
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/")).andDo(print());
		int newSize = da.findAll().size();
		assertEquals(newSize, origSize + 1);
	}
}


