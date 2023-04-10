package once.curso.proyectobancojpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import once.curso.proyectobancojpa.entities.AwardFineType;

@Repository
public interface AwardFineTypeRepository extends CrudRepository<AwardFineType, Integer> {

}
