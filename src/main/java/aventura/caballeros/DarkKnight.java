package aventura.caballeros;

import org.springframework.stereotype.Component;

import aventura.excepciones.SinEspadaException;
import lombok.Data;

@Data
@Component
public class DarkKnight implements KnightInterface{
	
	private boolean espada;

	public void embarcar() throws SinEspadaException {
		if ((!isEspada())) {
			throw new SinEspadaException();
		}
		matarAlDragon();
		
	}

	public void matarAlDragon() {

		System.out.println("El "+this.getClass().getSimpleName()+ " derrot� al temible drag�n,\n celebremos su victoria con esta canci�n");
	}

	public boolean comprobarEspada() {
		return isEspada();
	}
	public boolean entregarEspada(boolean x) {
		setEspada(x);
		return isEspada();
	}

}
