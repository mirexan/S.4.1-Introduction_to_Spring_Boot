package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.NewUserDTO;
import cat.itacademy.s04.t01.userapi.models.User;

import cat.itacademy.s04.t01.userapi.repository.InMemoryUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	InMemoryUserRepository userRepository;
	@BeforeEach
	void setUp() {
		userRepository.clearList();
	}

	@Test
	public void getUsers_returnsEmptyListInitially() throws Exception {
		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

	@Test
	public void createUser_returnsUserWithId() throws Exception {
		NewUserDTO newUser = new NewUserDTO("Paquita", "PSalas@PSManagement.com");
		String newUserJson = objectMapper.writeValueAsString(newUser);
		mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(newUserJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.name").value("Paquita"));
	}

	@Test
	void getUserById_returnsCorrectUser() throws Exception {
		NewUserDTO newUser = new NewUserDTO("Magwi", "MagMartinez@PSManagement.com");
		String answer = mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(newUser)))
						.andExpect(status().isOk())
						.andReturn().getResponse().getContentAsString();
		User user = objectMapper.readValue(answer, User.class);
		mockMvc.perform(get("/users/" + user.id().toString()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Magwi"))
				.andExpect(jsonPath("$.id").value(user.id().toString()));
	}

	@Test
	void getUserById_returnsNotFoundIfMissing() throws Exception {
		mockMvc.perform(get("/users/" + UUID.randomUUID().toString()))
				.andExpect(status().isNotFound());
	}

	@Test
	void getUsers_withNameParam_returnsFilteredUsers() throws Exception {
		createNewTestUser("Paquita", "PSalas@PSManagement.com");
		createNewTestUser("Magwi", "MagMartinez@PSManagement.com");
		mockMvc.perform(get("/users?name=Paquita"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Paquita"))
				.andExpect(jsonPath("$",hasSize(1)));
	}
	private void createNewTestUser(String name, String email) throws Exception {
		NewUserDTO newUser = new NewUserDTO(name, email);
		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newUser)));
	}
}
