package juglar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Juglar {
	@Pointcut("execution(void caballero.Knight.embarcar(..))")
	public void cuentaCuento() {}
	
/*	@Before ("cuentaCuento()")
	public void antesDeEmbarcar() {
		System.out.println("El caballero Knight se embarc� camino a rescatar a Fiona");
	}
	@AfterReturning ("cuentaCuento()")
	public void despuesDeEmbarcar() {
		System.out.println("El caballero Knight volvi� glorioso con su princesa Fiona");
	}
	@AfterThrowing("cuentaCuento()")
	public void finalTragico() {
		System.out.println("El Drag�n se comi� al caballero por no llevar la espada");
	}*/
	
@Around ("cuentaCuento()")
public void hacerTodo(ProceedingJoinPoint joinPoint) {
	
	try {
		System.out.println("El caballero Knight se embarc� camino a rescatar a Fiona");
		joinPoint.proceed();
		System.out.println("El caballero Knight volvi� glorioso con su princesa Fiona");
	} catch (Throwable e) {
		System.out.println("El Drag�n se comi� al caballero por no llevar la espada");
	}
	
	
}
}
