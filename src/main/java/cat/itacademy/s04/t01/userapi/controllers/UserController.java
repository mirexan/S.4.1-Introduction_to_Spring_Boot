package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class UserController {
	static List<User> userList;
	public UserController(){
		userList = new ArrayList<>();
	}
	public void addUser(User newUser){
		userList.add(newUser);
	}
	@GetMapping("/users")
	public List<User> getUserList(){
		return userList;
	}
}
