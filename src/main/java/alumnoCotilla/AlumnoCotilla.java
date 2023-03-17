package alumnoCotilla;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AlumnoCotilla {
	
	@Pointcut("execution (void profesor.Profesor.entrarClase(..))")
	public void controlador() {
		
	}
	public void hacerTodo(ProceedingJoinPoint joinPoint) {
		
		try {
			System.out.println("Buenos d�as chicos entramos en clase");
			joinPoint.proceed();
			System.out.println("Muy bien chicos, nos emos ma�ana");
		} catch (Throwable e) {
			System.out.println("Lo siento estoy af�nico y no podemos dar la clase");
		}
		finally {
			System.out.println("Este es un d�a normal de clase");
		}
	}

}
