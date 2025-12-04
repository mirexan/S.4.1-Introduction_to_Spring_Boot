package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.exceptions.EmailAlreadyExistException;
import cat.itacademy.s04.t01.userapi.models.NewUserDTO;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
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
	void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
		String falseEmail = "falseEmail@test.com";
		NewUserDTO newUserDTO = new NewUserDTO("Testeo",falseEmail);
		when(userRepository.existsByEmail(falseEmail)).thenReturn(true);
		Assertions.assertThrows(EmailAlreadyExistException.class,() -> userService.createUser(newUserDTO));
		  verify(userRepository,never()).save(any());
	}
	@Test
	void createUser_shouldGenerateUUIDAndSaveUserWhenEmailDoesNotExists() {
		String falseEmail = "falseEmail@test.com";
		NewUserDTO newUserDTO = new NewUserDTO("Testeo", falseEmail);
		when(userRepository.existsByEmail(falseEmail)).thenReturn(false);
		userService.createUser(newUserDTO);
		verify(userRepository).save(argThat(user ->  user.id() != null &&
				user.name().equals("Testeo") &&
				user.email().equals(falseEmail)
		));
	}
}
