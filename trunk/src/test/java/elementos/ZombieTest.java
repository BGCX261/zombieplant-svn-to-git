package elementos;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;

import elementos.jardines.Surco;
import elementos.plantas.Planta;

public class ZombieTest {

	Planta planta;
	Zombie zombie;
	Juego juego;
	Resultado resultado;
	Surco surco;
	DarPremio premio;
	private AlmanaqueDeZombies almanaque;
	
	@Before
	public void before(){
		almanaque = new AlmanaqueDeZombies();
		surco=mock(Surco.class);
		resultado=mock(Resultado.class);
		juego=mock(Juego.class);
		zombie=new Zombie();
		zombie.setAtaque(20);
		zombie.setDefensa(30);
		planta=mock(Planta.class);
		when(planta.getAtaque()).thenReturn(30);
		when(planta.getDefensa()).thenReturn(30);
		premio=mock(DarPremio.class);
	    when(juego.getPremio()).thenReturn(premio);
	    when(juego.getAlmanaque()).thenReturn(almanaque);
	}
	
	@Test
	public void atacarA(){
		zombie.atacarA(planta,resultado);
		verify(planta).teAtaca(zombie, resultado);
	}
	
	@Test
	public void teAtaca(){
		zombie.teAtaca(planta,juego,resultado);
		assertEquals(zombie.getDefensa(),0);
		verify(juego).getPremio();
	}

}
