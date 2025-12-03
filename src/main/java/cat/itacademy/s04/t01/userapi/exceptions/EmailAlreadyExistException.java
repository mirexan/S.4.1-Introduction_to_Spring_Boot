package cat.itacademy.s04.t01.userapi.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
	public EmailAlreadyExistException(String message) {
		super(message);
	}
}
