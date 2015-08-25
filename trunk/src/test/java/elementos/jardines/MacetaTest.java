package elementos.jardines;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import elementos.jardines.Maceta;
import elementos.plantas.Planta;

public class MacetaTest {
	

	Maceta maceta;
	
	@Before
	public void before(){
		maceta=new Maceta();
	}
	
	@Test
	public void siEstaVacio(){
		assertTrue(maceta.estaVacio());
	}
	
	@Test
	public void noEstaVacio(){
		maceta.setPlanta(new Planta());
		assertFalse(maceta.estaVacio());
	}

}
