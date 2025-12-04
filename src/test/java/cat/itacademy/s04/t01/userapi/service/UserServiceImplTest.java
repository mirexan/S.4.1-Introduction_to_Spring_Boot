package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.exceptions.EmailAlreadyExistException;
import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.models.NewUserDTO;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userService;

	@Test
	@DisplayName("Test if Email already exists")
	void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
		String falseEmail = "falseEmail@test.com";
		NewUserDTO newUserDTO = new NewUserDTO("Testeo",falseEmail);
		when(userRepository.existsByEmail(falseEmail)).thenReturn(true);
		Assertions.assertThrows(EmailAlreadyExistException.class,() -> userService.createUser(newUserDTO));
		  verify(userRepository,never()).save(any());
	}
	@Test
	@DisplayName("Test creation and store of new user")
	void createUser_shouldGenerateUUIDAndSaveUserWhenEmailDoesNotExists() {
		String falseEmail = "falseEmail@test.com";
		NewUserDTO newUserDTO = new NewUserDTO("Testeo",falseEmail);
		when(userRepository.existsByEmail(falseEmail)).thenReturn(false);
		userService.createUser(newUserDTO);
		verify(userRepository).save(argThat(user ->  user.id() != null &&
				user.name().equals("Testeo") &&
				user.email().equals(falseEmail)
		));
	}
	@Test
	@DisplayName("Test user not found exception")
	void getUserById_shouldThrowUserNotFoundExceptionWhenIdDoesNotExists(){
		String falseId = "1";
		Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserByIdService(falseId));
	}
	@Test
	@DisplayName("Test List all users")
	void getUserByName_shouldListAllUsersWhenNameIsNull(){
		userService.getUserByNameService(null);
		verify(userRepository).findAll();
		verify(userRepository,never()).getUserByName(any());
	}
	@Test
	@DisplayName("Test get user by name")
	void getUserByName_shouldListUserByName(){
		String name = "Paquita";
		userService.getUserByNameService(name);
		verify(userRepository).getUserByName(name);
		verify(userRepository, never()).findAll();
	}
}
