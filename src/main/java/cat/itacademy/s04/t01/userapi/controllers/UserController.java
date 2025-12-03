package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.NewUserDTO;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.service.UserService;
import cat.itacademy.s04.t01.userapi.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
	private final UserService userService;
	public UserController(UserServiceImpl userService){
		this.userService = userService;
	}
	@PostMapping("/users")
	public User addUserController(@RequestBody NewUserDTO userRequest){
		return userService.createUser(userRequest);
	}

	@GetMapping("/users/{id}")
	public User getUserByIdController(@PathVariable String id){
		return userService.getUserByIdService(id);
	}

	@GetMapping("/users")
	public List<User> getUserByNameController(@RequestParam(value = "name", required = false) String name){
		return userService.getUserByNameService(name);
	}
	@GetMapping("/users/check-email")
	public boolean existsByEmailController(String email){
		return userService.existsByEmailService(email);
	}
}
