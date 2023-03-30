package once.curso.ejemplojpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import once.curso.ejemplojpa.entityes.Employee;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
