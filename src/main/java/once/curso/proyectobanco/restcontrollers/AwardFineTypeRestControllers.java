package once.curso.proyectobanco.restcontrollers;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import once.curso.proyectobanco.entities.AwardsFinesType;
import once.curso.proyectobanco.models.AwardsFinesTypeModelAssembler;
import once.curso.proyectobanco.services.AwardsFinesTypeServices;

@RestController
@RequestMapping("/once")
@Data
public class AwardFineTypeRestControllers {
	
	@Autowired
	private AwardsFinesTypeModelAssembler awardsFinesTypeModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<AwardsFinesType> PagedResourcesAssembler;

	@Autowired
	private AwardsFinesTypeServices awardFineTypeServices;

	@GetMapping(value = "/awardsFinesTypes/{id}")
	public EntityModel<AwardsFinesType> findById(@PathVariable int id) {
		AwardsFinesType awardsFinesType = getAwardFineTypeServices().findById(id).get();

			awardsFinesType
					.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AwardFineTypeRestControllers.class)
							.findById(awardsFinesType.getId())).withSelfRel());
		
		return EntityModel.of(awardsFinesType);
	}

	@GetMapping(value = "/awardsFinesTypes")
	public PagedModel<EntityModel<AwardsFinesType>> findAll(@RequestParam(defaultValue = "0") int size, @RequestParam(defaultValue = "0")int page,
			@RequestParam(required = false)String sort){
		if(size==0) {
			size=(int) getAwardFineTypeServices().count();
		}
		
		Sort orden=Sort.by("id");
		if(sort !=null) {
			orden=Sort.by(sort);
			StringTokenizer stringTokenizer = new StringTokenizer(sort,",");
			
			String campo=stringTokenizer.nextToken();
			String tipoOrden=stringTokenizer.nextToken();
			
			if(tipoOrden.equals("asc"))
				orden=Sort.by(campo).ascending();
			else 
				orden=Sort.by(campo).descending();
			
		}
		Pageable pageable=PageRequest.of(page, size, orden);
		Page<AwardsFinesType> awardsFinesType = getAwardFineTypeServices().findAll(pageable);
		
		return getPagedResourcesAssembler().toModel(awardsFinesType, getAwardsFinesTypeModelAssembler());
	}

	@PostMapping(value = "/awardsFinesTypes")
	public AwardsFinesType save(@RequestBody AwardsFinesType awardFineType) {
		return getAwardFineTypeServices().save(awardFineType);
	}

	@PutMapping(value = "/awardsFinesTypes")
	public List<AwardsFinesType> saveAll(@RequestBody List<AwardsFinesType> awardFineTypes) {
		return (List<AwardsFinesType>) getAwardFineTypeServices().saveAll(awardFineTypes);
	}

	@DeleteMapping(value = "/awrdFineType")
	public void deleteById(@PathVariable int id) {
		 getAwardFineTypeServices().deleteById(id);
	}

	
}
