package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.models.NewUserDTO;
import cat.itacademy.s04.t01.userapi.models.User;

import java.util.List;

public interface UserService {
	User createUser(NewUserDTO  newUserDTO);
	User getUserByIdService(String id);
	List<User> getUserByNameService(String name);
	boolean existsByEmailService(String email);
}
