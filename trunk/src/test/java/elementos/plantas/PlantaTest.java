package elementos.plantas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import elementos.Juego;
import elementos.Resultado;
import elementos.Zombie;
import elementos.jardines.Surco;

public class PlantaTest {

	Planta planta;
	Zombie zombie;
	Juego juego;
	Resultado resultado;
	Surco surco;
	
	@Before
	public void before(){
		
		surco=mock(Surco.class);
		resultado=mock(Resultado.class);
		juego=mock(Juego.class);
		planta=new Planta();
		planta.setAtaque(20);
		planta.setDefensa(30);
		zombie=mock(Zombie.class);
		when(zombie.getAtaque()).thenReturn(30);
		when(zombie.getDefensa()).thenReturn(30);
		surco=mock(Surco.class);
		planta.setSurco(surco);
	
	}
	
	@Test
	public void atacarA(){
		planta.atacarA(zombie,juego,resultado);
		verify(zombie).teAtaca(planta, juego, resultado);
	}
	
	@Test
	public void teAtaca(){
		planta.teAtaca(zombie,resultado);
		assertEquals(planta.getDefensa(),0);
	}

}
