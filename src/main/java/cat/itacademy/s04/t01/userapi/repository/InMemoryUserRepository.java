package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository{
	private final List<User> userList = new ArrayList<>();

	public List<User> findAll(){
		return userList;
	}
	public User save(User newUser){
		userList.add(newUser);
		return newUser;
	}

	public Optional<User> getUserById(String id){
		return userList.stream()
				.filter(user -> user.id().toString().equals(id))
				.findFirst();
	}

	public List<User> getUserByName(String name){
		return userList.stream()
				.filter(user -> user.name().toLowerCase().contains(name.toLowerCase()))
				.toList();
	}

	public boolean existsByEmail(String email){
		return userList.stream()
				.anyMatch(user->user.email().equals(email));
	}
	public void clearList(){
		userList.clear();
	}
}
