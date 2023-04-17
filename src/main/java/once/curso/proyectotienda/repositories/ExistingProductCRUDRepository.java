package once.curso.proyectotienda.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import once.curso.proyectotienda.entities.ExistingProduct;

@Repository
public interface ExistingProductCRUDRepository extends CrudRepository<ExistingProduct, Integer> {

}
