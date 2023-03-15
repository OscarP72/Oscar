package juglar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Aspect

public class Juglar {
	@Pointcut("execution(void caballero.Caballero.embarcar(..))")
	public void vendeHumos() {
	}

	/*@Before("vendeHumos()")
	public void antesDeEmbarcar() {
		System.out.println("Como el caballero ten�a la picha tiesa \n Se fue a rescatar a la princesa");
	}

	@AfterReturning("vendeHumos()")
	public void vueltaGloriosa() {
		System.out.println("El caballero se port� como un campe�n \n y la princesa le regal� un buen jam�n");
	}

	@AfterThrowing("vendeHumos()")
	public void dragonChetado() {
		System.out.println("Como el caballero fue comido por el drag�n \n Se quedo sin su cacho de jam�n");
	}*/
	@Around("vendeHumos()")
	public void hacerTodo(ProceedingJoinPoint joinPoint) {
		
		try {
			System.out.println("Como el caballero ten�a la picha tiesa \n Se fue a rescatar a la princesa");
			joinPoint.proceed();
			System.out.println("El caballero se port� como un campe�n \n y la princesa le regal� un buen jam�n");

		} catch (Throwable e) {
			
			System.out.println("Como el caballero fue comido por el drag�n \n Se quedo sin su cacho de jam�n");

		}
		
	}

}