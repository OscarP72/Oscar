package once.curso.proyectotienda.restcontrollers;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import once.curso.proyectotienda.beans.Login;
import once.curso.proyectotienda.dtos.UserDto;
import once.curso.proyectotienda.entities.Rol;
import once.curso.proyectotienda.entities.User;
import once.curso.proyectotienda.services.UserDetailLoginService;

@Data
//@RequestMapping("/once")
@RestController
public class LoginRestController {
	
	@Autowired
	private UserDetailLoginService userDetailLoginService;
	
	@PostMapping("/login")
	@CrossOrigin(origins = "*")
	public ResponseEntity<UserDto> verLogin(@RequestBody Login login) {
	    UserDto userDto = new UserDto();

	    User user = userDetailLoginService.getUser(login.getUsuario());
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);

	    if (user != null && encoder.matches(login.getClave(), user.getPassword())) {
	        userDto.setUser(user.getUser());
	        Set<Rol> roles = new HashSet<>();
	        roles.add(user.getRol());
	        userDto.setRoles(roles);
	        userDto.setToken(getToken(user.getUser(), roles));
	        return ResponseEntity.ok(userDto);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}

	private String getToken(String user, Set<Rol> roles) {
	    String securityKey = "onceBanco";
	    String token = Jwts.builder()
	            .setId(securityKey)
	            .setSubject(user)
	            .claim("roles", roles)
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + 60000))
	            .signWith(SignatureAlgorithm.HS512, securityKey.getBytes())
	            .compact();

	    return "Bearer " + token;
	}

}
