package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.models.NewUserDTO;
import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
	static List<User> userList;
	public UserController(){
		userList = new ArrayList<>();
	}
	@PostMapping("/users")
	public User addUser(@RequestBody NewUserDTO userRequest){
		User newUser = new User(
				UUID.randomUUID(),
				userRequest.name(),
				userRequest.email()
		);
		userList.add(newUser);
		return newUser;
	}
	@GetMapping("/users")
	public List<User> getUserList(){
		return userList;
	}
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable String id){
			return userList.stream()
					.filter(user -> user.id().toString().equals(id))
					.findFirst()
					.orElseThrow(() -> new UserNotFoundException("User with id :"
				+ id + " not found"));
	}
}
