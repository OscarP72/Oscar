package once.curso.ejemplojpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import once.curso.ejemplojpa.entityes.MaritalStatus;

@Repository
public interface MaritalStatusCRUDRepository extends CrudRepository<MaritalStatus, Integer> {

}