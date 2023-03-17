package profesor;

import org.springframework.stereotype.Component;

import exception.SinClaseException;
import lombok.Data;

@Data
@Component
public class Profesor implements ProfesorInterface {

	private boolean explicar;

	@Override
	public void entrarEnClase() throws SinClaseException {
		System.out.println("Buenos d�as, entramos en clase");
		comenzarLaClase();
	}

	@Override
	public void comenzarLaClase() throws SinClaseException {
		if (!isExplicar())
			throw new SinClaseException();
		System.out.println("Chicos, estoy af�nico");
	}

	@Override
	public boolean salirDeClase(boolean x) {
		setExplicar(x);
		return isExplicar();
	}

}
