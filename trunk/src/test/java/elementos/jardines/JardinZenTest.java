package elementos.jardines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import elementos.plantas.Plantin;

public class JardinZenTest {

	private JardinZen jz;
	private Plantin plantinA;
	private Plantin plantinT;

	
	@Before
	public void before(){
		this.jz = new JardinZen();
		this.plantinA = new Plantin(true);
		this.plantinT = new Plantin(false);
	}
	
	@Test
	public void contructor(){
		
		assertEquals(this.jz.getTerrestre().getLugaresDisponibles(),17);
		assertEquals(this.jz.getAcuatico().getLugaresDisponibles(),17);
		assertFalse(this.jz.getTerrestre().getEsDeAgua());
		assertTrue(this.jz.getAcuatico().getEsDeAgua());
		
	}
	
	@Test 
	public void plantines(){
		assertTrue(this.plantinA.getEsDeAgua());
		assertFalse(this.plantinT.getEsDeAgua());
	}
	
	
	@Test
	public void plantar(){
		this.jz.plantar(this.plantinT);
		this.jz.plantar(this.plantinA);

		assertTrue(this.jz.getAcuatico().getPlantines().contains(this.plantinA));
		assertTrue(this.jz.getTerrestre().getPlantines().contains(this.plantinT));

	}
	
}
