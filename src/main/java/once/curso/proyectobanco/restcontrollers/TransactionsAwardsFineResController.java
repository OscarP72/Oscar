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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import once.curso.proyectobanco.entities.TransactionsAwardsFine;
import once.curso.proyectobanco.models.TransactionsAwardsFineAssember;
import once.curso.proyectobanco.services.TransactionsAwardsFineService;

@Data
@RestController
@RequestMapping("/once")
public class TransactionsAwardsFineResController {
	
	@Autowired
	private  TransactionsAwardsFineAssember  transactionsAwardsFineAssember;
	
	@Autowired
	private PagedResourcesAssembler<TransactionsAwardsFine> pagedResourcesAssembler ;
	
	@Autowired
	private TransactionsAwardsFineService transactionsAwardsFineService;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/transactionsAwardsFineService")
	
	public PagedModel<EntityModel<TransactionsAwardsFine>> findAll( @RequestParam(defaultValue = "0")int size, @RequestParam(defaultValue = "0")int page,
			@RequestParam(required = false)String sort){
		if(size==0) {
			size=(int) getTransactionsAwardsFineService().count();
		}
		
		
		Sort orden = Sort.by("id");
		if(sort !=null) {
			orden=Sort.by(sort);
			StringTokenizer stringTokenizer= new StringTokenizer(sort,",");
			
			String campo=stringTokenizer.nextToken();
			String tipoOrden=stringTokenizer.nextToken();
			
			if(tipoOrden.equals("asc"))
				orden=Sort.by(campo).ascending();
			else
				orden=Sort.by(campo).descending();
		}
		
		Pageable pageable=PageRequest.of(page,size,orden);
		Page<TransactionsAwardsFine> transactionsAwardsFine = getTransactionsAwardsFineService().findAll(pageable);
	
	
		return getPagedResourcesAssembler().toModel(transactionsAwardsFine,getTransactionsAwardsFineAssember());
	}
	
	@GetMapping(value="/transactionsAwardsFineService/{id}")
	
	public EntityModel<TransactionsAwardsFine> findById(@PathVariable Integer id){
		 TransactionsAwardsFine transactionsAwardsFine =getTransactionsAwardsFineService().findById(id).get();
		 transactionsAwardsFine.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TransactionsAwardsFineResController.class)
				.findById(transactionsAwardsFine.getId())).withSelfRel());
			
		return EntityModel.of(transactionsAwardsFine);
}
	@PostMapping("/transactionsAwardsFineService")
		
	public TransactionsAwardsFine save(@RequestBody TransactionsAwardsFine transactionsAwardsFine) {
		return getTransactionsAwardsFineService().save(transactionsAwardsFine);
	
	}
	
	@PutMapping(value="/transactionsAwardsFineService")
	public List<TransactionsAwardsFine> saveAll(@RequestBody List<TransactionsAwardsFine> transactionsAwardsFines){
		return (List<TransactionsAwardsFine>)getTransactionsAwardsFineService().saveAll(transactionsAwardsFines);
	}
	
	@DeleteMapping("/transactionsAwardsFineService")
	public void deleteById(@PathVariable Integer id) {
		getTransactionsAwardsFineService().deleteById(id);
	}



	
}