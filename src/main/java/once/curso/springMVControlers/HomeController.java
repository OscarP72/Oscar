package once.curso.springMVControlers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import once.curso.beans.Persona;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET) //La url es la root / y har� un forward a home.jsp
	//http://localhost:8080/springMVC/  ---> home.jsp
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home"; //es como hacer un forward a home.jsp, en la home le diremos por html que muestre la variable serverTime
		//no le decimos home.jsp porque el suffix ya lo definimos en el context.xml
	}
	
	
	//--------------------------------------------------------------------
	
	//a�adimos otro reques, otra url
	@RequestMapping(value = "/prueba") //har� un forward a prueba.jsp 
	//por defecto entra en GET y por seguiridad se cre� directamente las anotaciones @GetMapping y @PostMapping
	//pero requieren version de Spring 5.3.26, lo podemos cambiar en el pom
	//http://localhost:8080/springMVC/prueba.jsp
	public String prueba(@RequestParam String textoRecibido, Model model) {
		
		String saludo = "Hola "+textoRecibido+" tu nombre tiene "+textoRecibido.length()+" letras";
		
		model.addAttribute("saludar", saludo );
		
		return "prueba"; //es como hacer un forward a home.jsp, en la home le diremos por html que muestre la variable serverTime
	}//la direcci�n ser�a algo como: http://localhost:8080/springMVC/prueba?textoRecibido=Paco
	
	//---------------------------------------------------------------------
	
	
	
	
	
	@RequestMapping(value = "/listado")
	public String lista (Model model) {
		
		List<String> lista = new ArrayList<String>();
		lista.add("uno");
		lista.add("dos");
		lista.add("tres");
		lista.add("cuatro");
		lista.add("cinco");
		lista.add("seis");
		lista.add("siete");
		lista.add("ocho");
		lista.add("nueve");
		lista.add("diez");
				
		model.addAttribute("propiedadLista", lista);
		
		return "listado";
	}
	
	
	//---------------------------------------------------------------------
	
	//pasamos variables de dos formas, una es:
	
	@RequestMapping("/listadoConPersonas/{numeroPersonas}")
	public String grupoDePersonasMetodo (@PathVariable int numeroPersonas, Model model) {
	
	//es una forma de pasar una variable y conseguimos una url "friendly"
	//ejem:  http://localhost:8080/springMVC/listadoConPersonas/50
	
	//segunda forma:
	//@RequestMapping(value = "/listadoConPersonas")
	//public String grupoDePersonasMetodo (@RequestParam int numeroPersonas, Model model) {
		//ejem:  http://localhost:8080/springMVC/listadoConPersonas?numeroPersonas=50
		//el return del m�todo siempre es un String xq es un forward al nombre de una vista (jsp)
		
		List<Persona> grupoDePersonas = new ArrayList<Persona>();
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < numeroPersonas; i++) {
			String nombreNuevo = "Persona"+String.valueOf(i);
			String dniNuevo = "0000000"+Integer.toString(i) + letras.charAt(i%letras.length());
			if (i>9)
			dniNuevo = dniNuevo.substring(Integer.toString(i).length()-1);
			String telefonoNuevo = Integer.toString(600000000 + i);
			grupoDePersonas.add(new Persona(nombreNuevo,dniNuevo,telefonoNuevo));
		}
						
		model.addAttribute("propiedadGrupoPersonas", grupoDePersonas);		
		return "listadoConPersonas";
	}
	
	
	//---------------------------------------------------------------------
	//m�s moderno y simple es juntar Vista (view) y modelo usando ModelAndView como objeto retornado e inyectado:
		
	@RequestMapping("/listadoConPersonasModelAndView/{numeroPersonas}")
	public ModelAndView grupoPersonasMetodoConMAV (@PathVariable int numeroPersonas, ModelAndView modelAndView) {
		//ejem:  http://localhost:8080/springMVC/listadoConPersonasModelAndView/50
			
		List<Persona> grupoDePersonas = new ArrayList<Persona>();
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < numeroPersonas; i++) {
			String nombreNuevo = "Persona"+String.valueOf(i);
			String dniNuevo = "0000000"+Integer.toString(i) + letras.charAt(i%letras.length());
			if (i>9)
			dniNuevo = dniNuevo.substring(Integer.toString(i).length()-1);
			String telefonoNuevo = Integer.toString(600000000 + i);
			grupoDePersonas.add(new Persona(nombreNuevo,dniNuevo,telefonoNuevo));
		}
						
		modelAndView.addObject("propiedadGrupoPersonas", grupoDePersonas);
		modelAndView.setViewName("listadoConPersonas"); //as� le especificamos otro view al return,
		//uno que ya tenemos creado y asi no creamos otro..
		return modelAndView;//por defecto en el modelandview, si no especificamos coge el mismo view en que estamos
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//----------------------------------------------------------------------
	//@GetMapping("/home2")
	//Por defecto nos carga la versi�n 3 pero esta no incluye el @GetMapping:
	//<org.springframework-version>3.1.1.RELEASE</org.springframework-version>
	//Si queremos usar GetMapping, hay que subir la versi�n a la	5.3.26
	public String home2(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home"; //es como hacer un forward a home.jsp, en la home le diremos por html que muestre la variable serverTime
	}
}
