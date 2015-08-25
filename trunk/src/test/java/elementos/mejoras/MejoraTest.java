package elementos.mejoras;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import elementos.plantas.Plantin;

public class MejoraTest {

	Mejora mejora;
	Plantin plantin;
	
	@Before
	public void before(){
		plantin=new Plantin("Agrillos",true,85,35);
	}
	
	
	@Test
	public void mejoraDefensiva(){
		MejoraDefensiva md=new MejoraDefensiva(20, null, 50);
		md.mejorar(plantin);
		
		assertEquals(plantin.getDefensa(),105);
	}
	
	
	@Test
	public void mejoraOfensiva(){
		MejoraOfensiva md=new MejoraOfensiva(30, null, 50);
		md.mejorar(plantin);
		assertEquals(plantin.getAtaque(),65);
	}
}
