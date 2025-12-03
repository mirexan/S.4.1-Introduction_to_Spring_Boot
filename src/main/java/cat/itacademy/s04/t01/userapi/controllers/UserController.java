package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.models.NewUserDTO;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repository.InMemoryUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
public class UserController {
	private final InMemoryUserRepository userRepository;
	public UserController(InMemoryUserRepository userRepository){
		this.userRepository = userRepository;
	}

	@PostMapping("/users")
	public User addUserController(@RequestBody NewUserDTO userRequest){
		User newUser = new User(
				UUID.randomUUID(),
				userRequest.name(),
				userRequest.email()
		);
		return userRepository.save(newUser);
	}

	@GetMapping("/users/{id}")
	public User getUserByIdController(@PathVariable String id){
		return userRepository.getUserById(id)
				.orElseThrow(() -> new UserNotFoundException("User with id :"
				+ id + " not found"));
	}

	@GetMapping("/users")
	public List<User> getUserByNameController(@RequestParam(value = "name", required = false) String name){
		if(name == null)
			return userRepository.findAll();
		return userRepository.getUserByName(name);
	}
	@GetMapping("/users/check-email")
	public boolean existsByEmailController(String email){
		return userRepository.existsByEmail(email);
	}
}
