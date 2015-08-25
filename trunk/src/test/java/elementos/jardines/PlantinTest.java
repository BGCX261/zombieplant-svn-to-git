package elementos.jardines;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import elementos.plantas.Plantin;
import elementos.mejoras.MejoraOfensiva;

public class PlantinTest {

		
	private Plantin plantinA;
	private Plantin plantinT;
	private String nombre;
	private boolean esDeAgua;
	private int defensa;
	private int ataque;
	private MejoraOfensiva mejora;
		
	@Before
	public void before(){
//		this.plantinA = new Plantin(true);
//		this.plantinT = new Plantin(false);
		this.esDeAgua= true;
		this.defensa = 50;
		this.ataque = 80;
		this.mejora = new MejoraOfensiva();
		
	}

	@Test
	public void contructor(){
	this.plantinA = new Plantin(this.nombre, this.esDeAgua, this.defensa,  this.ataque);
	
	assertEquals(this.plantinA.getNombre(),this.nombre);
	assertEquals(this.plantinA.getEsDeAgua(), this.esDeAgua);
	assertEquals(this.plantinA.getDefensa(),this.defensa);
	assertEquals(this.plantinA.getAtaque(),this.ataque);
	
	}
	
	@Test
	public void agregarMejora(){
		this.plantinA = new Plantin(this.nombre, this.esDeAgua, this.defensa,  this.ataque);
		this.plantinA.agregarMejora(this.mejora);
		
		assertTrue(this.plantinA.getMejorasCompradas().contains(this.mejora));
		
	}
	
	
	
	
}
