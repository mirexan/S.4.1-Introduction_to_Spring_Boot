package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.exceptions.EmailAlreadyExistException;
import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.models.NewUserDTO;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repository.InMemoryUserRepository;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(NewUserDTO newUserDTO) {
		if (userRepository.existsByEmail(newUserDTO.email())) {
			throw new EmailAlreadyExistException("Email already exists");
		}
		User newUser = new User(
				UUID.randomUUID(),
				newUserDTO.name(),
				newUserDTO.email()
		);
		return userRepository.save(newUser);
	}

	public User getUserByIdService(String id) {
		return userRepository.getUserById(id)
				.orElseThrow(() -> new UserNotFoundException("User with id :"
						+ id + " not found"));
	}

	public List<User> getUserByNameService(String name) {
		if(name == null)
			return userRepository.findAll();
		return userRepository.getUserByName(name);
	}
	public boolean existsByEmailService(String email) {
		return userRepository.existsByEmail(email);
	}
}
