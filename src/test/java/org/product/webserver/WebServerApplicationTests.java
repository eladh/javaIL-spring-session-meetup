package org.product.webserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebServerApplicationTests {


	@Autowired
	private MockMvc mockMvc;


	@Test
	public void accessProtected() throws Exception {
		this.mockMvc.perform(get("/"))
				.andExpect(unauthenticated())
				.andExpect(status().isUnauthorized());
	}

//	@Test
	public void loginUser() throws Exception {
		this.mockMvc.perform(get("/")
				.with(httpBasic("user", "password")))
				.andExpect(status().isOk());
	}

}
