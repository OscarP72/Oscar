package bancoRepositories;

import org.springframework.data.repository.CrudRepository;

import bancoEntityes.Description;

public interface DescriptionRepository extends CrudRepository<Description, Integer> {

}
