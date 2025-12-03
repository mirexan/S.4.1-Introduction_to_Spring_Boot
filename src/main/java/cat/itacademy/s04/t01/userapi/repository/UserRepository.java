package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.NewUserDTO;
import cat.itacademy.s04.t01.userapi.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
	List<User> findAll();
	User save(User user);
	Optional<User> getUserById(String id);
	List<User> getUserByName(String name);
	boolean existsByEmail(String email);
}
