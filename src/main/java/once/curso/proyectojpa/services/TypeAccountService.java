package once.curso.proyectojpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import once.curso.proyectojpa.entities.TypeAccount;
import once.curso.proyectojpa.repositories.TypeAccountRepository;

@Service
@Data
public class TypeAccountService {

	@Autowired
	private TypeAccountRepository typeAccountRepository;

	public void dameTipoDeCuentas() {

		Iterable<TypeAccount> TypeAccounts = getTypeAccountRepository().findAll();
		for (TypeAccount typeAccount : TypeAccounts) {
			System.out.println(typeAccount.getDescription());
		}
	}

	public <S extends TypeAccount> S save(S entity) {

		return getTypeAccountRepository().save(entity);
	}

	public <S extends TypeAccount> Iterable<S> saveAll(Iterable<S> entities) {

		return getTypeAccountRepository().saveAll(entities);
	}

	public Optional<TypeAccount> findById(Integer id) {

		return getTypeAccountRepository().findById(id);
	}

	public boolean existsById(Integer id) {

		return getTypeAccountRepository().existsById(id);
	}

	public Iterable<TypeAccount> findAll() {

		return getTypeAccountRepository().findAll();
	}

	public Iterable<TypeAccount> findAllById(Iterable<Integer> ids) {

		return getTypeAccountRepository().findAllById(ids);
	}

	public long count() {

		return getTypeAccountRepository().count();
	}

	public void deleteById(Integer id) {
		getTypeAccountRepository().deleteById(id);

	}

	public void delete(TypeAccount entity) {
		getTypeAccountRepository().delete(entity);

	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		getTypeAccountRepository().deleteAllById(ids);

	}

	public void deleteAll(Iterable<? extends TypeAccount> entities) {
		getTypeAccountRepository().deleteAll(entities);

	}

	public void deleteAll() {
		getTypeAccountRepository().deleteAll();

	}
}
