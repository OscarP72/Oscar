package once.curso.proyectobancojpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import once.curso.proyectobancojpa.entities.User;
import once.curso.proyectobancojpa.repositories.UserRepository;

@Service
@Data
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void dameUser() {

		Iterable<User> users = getUserRepository().findAll();
		for (User user : users) {
			System.out.println(user.getUser());
			System.out.println(user.getPassword());
			System.out.println(user.getRol());
			System.out.println(user.isEnabled());
		}

	}

	public <S extends User> S save(S entity) {

		return getUserRepository().save(entity);
	}

	public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {

		return getUserRepository().saveAll(entities);
	}

	public Optional<User> findById(Integer id) {

		return getUserRepository().findById(id);
	}

	public boolean existsById(Integer id) {

		return getUserRepository().existsById(id);
	}

	public Iterable<User> findAll() {

		return getUserRepository().findAll();
	}

	public Iterable<User> findAllById(Iterable<Integer> ids) {

		return getUserRepository().findAllById(ids);
	}

	public long count() {

		return getUserRepository().count();
	}

	public void deleteById(Integer id) {
		getUserRepository().deleteById(id);

	}

	public void delete(User entity) {
		getUserRepository().delete(entity);

	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		getUserRepository().deleteAllById(ids);

	}

	public void deleteAll(Iterable<? extends User> entities) {
		getUserRepository().deleteAll(entities);

	}

	public void deleteAll() {
		getUserRepository().deleteAll();

	}
}
