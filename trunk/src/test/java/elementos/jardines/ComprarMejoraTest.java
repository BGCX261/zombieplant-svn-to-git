package elementos.jardines;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.model.UserException;

import elementos.Jugador;

public class ComprarMejoraTest {

	private Jugador jugador;

	@Before
	public void setup() {
		this.jugador = new Jugador();
		this.jugador.setPuntos(400);
	}

	@Test
	public void comprar() {
		this.jugador.comprarMejora(300);
		assertEquals(100, this.jugador.getPuntos(), 0.001);

	}

	@Test(expected = UserException.class)
	public void comprarSinPlata() {
		this.jugador.comprarMejora(500);

	}

	@Test
	public void comprarSinPlata2() {
		try {
			this.jugador.comprarMejora(500);
			fail();
		} catch (UserException e) {
		//se esperaba esta exception
		}
	}

}
