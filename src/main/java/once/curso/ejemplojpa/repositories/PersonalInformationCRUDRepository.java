package once.curso.ejemplojpa.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import once.curso.ejemplojpa.entities.PersonalInformation;

@Repository
public interface PersonalInformationCRUDRepository extends CrudRepository<PersonalInformation, Integer> {	
}