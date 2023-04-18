package once.curso.proyectotienda.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import once.curso.proyectotienda.entities.Configuration;
import once.curso.proyectotienda.repositories.ConfigurationCRUDRepository;

@Data
@Service
public class ConfigurationService {

	@Autowired
	private ConfigurationCRUDRepository configurationCRUDRepository;

	public void dameConfiguraciones() {

		Iterable<Configuration> dameConfiguraciones = getConfigurationCRUDRepository().findAll();
		for (Configuration configuration : dameConfiguraciones) {
			System.out.println(configuration.getId());
			System.out.println(configuration.getStockAlarm());
		}
	}

	public <S extends Configuration> S save(S entity) {
		return getConfigurationCRUDRepository().save(entity);
	}

	public <S extends Configuration> Iterable<S> saveAll(Iterable<S> entities) {
		return getConfigurationCRUDRepository().saveAll(entities);
	}

	public Optional<Configuration> findById(Integer id) {
		return getConfigurationCRUDRepository().findById(id);
	}

	public boolean existsById(Integer id) {
		return getConfigurationCRUDRepository().existsById(id);
	}

	public Iterable<Configuration> findAll() {
		return getConfigurationCRUDRepository().findAll();
	}

	public Iterable<Configuration> findAllById(Iterable<Integer> ids) {
		return getConfigurationCRUDRepository().findAllById(ids);

	}

	public long count() {
		return getConfigurationCRUDRepository().count();
	}

	public void deleteById(Integer id) {
		getConfigurationCRUDRepository().deleteById(id);
	}

	public void delete(Configuration entity) {
		getConfigurationCRUDRepository().delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		getConfigurationCRUDRepository().deleteAllById(ids);
	}

	public void deleteAll(Iterable<? extends Configuration> entities) {
		getConfigurationCRUDRepository().deleteAll();
	}

	public void deleteAll() {
		getConfigurationCRUDRepository().deleteAll();
	}

}
