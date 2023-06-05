package once.curso.proyectotienda.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
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
import once.curso.proyectotienda.entities.Rol;
import once.curso.proyectotienda.entities.User;
import once.curso.proyectotienda.model.UserModelAssembler;
import once.curso.proyectotienda.services.UserService;

@RestController
@RequestMapping("/once")
@Data
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PagedResourcesAssembler<User> pagedResourcesAssembler;
	
	@Autowired
	private UserModelAssembler userMvodelAssembler;
	
	@GetMapping("/users")
	@CrossOrigin(origins = "*")
	public CollectionModel<User> dameUser(){
		 Iterable<User> users = getUserService().findAll();
		 users.forEach(u->{
			 u.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RolRestController.class).findById(u.getRol().getId())).withRel("rol"));
			 u.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserRestController.class).findById(u.getId())).withSelfRel());
		 });
		 return CollectionModel.of(users);

	}
	
	@GetMapping("/users/{id}")
	@CrossOrigin(origins = "*")
	public EntityModel<User> findById(@PathVariable Integer id) {
		 User user = getUserService().findById(id).get();
		 user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RolRestController.class).findById(user.getRol().getId())).withRel("rol"));
		 user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserRestController.class).findById(user.getId())).withSelfRel());
		 return EntityModel.of(user);
	}
	
	@GetMapping("/user")
	@CrossOrigin(origins = "*")
	public Iterable<User> findAll(){
		return getUserService().findAll();
	}
	
	@PostMapping("/users")
	@CrossOrigin(origins = "*")
	public User save(@RequestBody User user) {
		return getUserService().save(user);
	}
	
	@PutMapping("/users")
	@CrossOrigin(origins = "*")
	public List<User> saveAll (@RequestBody List<User> users){
		return (List<User>) getUserService().saveAll(users);
	}
	
	@DeleteMapping("/user/{id}")
	@CrossOrigin(origins = "*")
	public void deleteById(@PathVariable int id) {
		getUserService().deleteById(id);
	}
	
	public PagedModel<EntityModel<Rol>> findAllPaginado(@RequestParam int size, 
			@RequestParam int page, @RequestParam String sort){
		
		return null;
	}
}
