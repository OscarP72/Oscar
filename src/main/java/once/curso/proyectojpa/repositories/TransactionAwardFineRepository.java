package once.curso.proyectojpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import once.curso.proyectojpa.entities.TransactionAwardFine;

@Repository
public interface TransactionAwardFineRepository extends CrudRepository<TransactionAwardFine, Integer> {

}
