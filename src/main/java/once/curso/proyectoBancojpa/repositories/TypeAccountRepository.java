package once.curso.proyectobancojpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import once.curso.proyectobancojpa.entities.TypeAccount;


@Repository
public interface TypeAccountRepository extends CrudRepository<TypeAccount, Integer> {

}
