package elementos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.model.UserException;

import aplicationModel.JuegoModel;
import aplicationModel.ResultadoDePelea;
import elementos.jardines.Maceta;
import elementos.plantas.Planta;

public class JuegoTest {

	Juego juego;
	Jugador jugador;
	Planta plantaA;
	Planta plantaB;
	Planta plantaC;
	Zombie zombie;
	Resultado resultado;
	
	
	
	@Before
	public void before(){
		juego=new Juego();
		jugador=new Jugador();
		juego.setJugador(jugador);
		plantaA=new Planta();
		plantaA.setAtaque(30);
		plantaA.setDefensa(90);
		
		
		plantaB=new Planta();
		plantaB.setDefensa(90);
		plantaB.setAtaque(25);
		
		
		plantaC=new Planta();
		plantaC.setAtaque(10);
		plantaC.setDefensa(30);
		
		zombie=new Zombie();
		zombie.setAtaque(35);
		zombie.setDefensa(60);
		
		resultado=new ResultadoDePelea(new JuegoModel());
	}
	
	@Test
	public void atacarYAsesinarZombie(){
		plantaA.setEsDeAgua(false);
		plantaB.setEsDeAgua(false);
		plantaC.setEsDeAgua(false);
		juego.plantar(plantaA, 1, 0);
		juego.plantar(plantaB, 2, 0);
		juego.plantar(plantaC, 3, 0);
		
		
		juego.atacar(zombie,juego.getJardin().getSurcos().get(0),resultado);
	
		//el zombie ha muerto
		assertTrue(zombie.getDefensa()<=0);
		//le resto 35
		assertEquals(plantaB.getDefensa(),55);
        
		//no le resta porque el zombie ya murio
		assertEquals(plantaA.getDefensa(),90);
	}
	
	
	@Test
	public void atacarYAsesinarZombieUnaPlanta(){
		plantaA.setEsDeAgua(false);
		plantaA.setDefensa(5);
		plantaA.setAtaque(5);
	    Maceta maceta=juego.getJardin().getSurcos().get(0).getMacetas().get(0);
	    maceta.setPlanta(plantaA);
	    plantaA.setSurco(juego.getJardin().getSurcos().get(0));
		//juego.plantar(plantaA, 1, 0);
		System.out.println(juego.getJardin().getSurcos().get(0).hayPlantas());
		System.out.println(juego.getJardin().getSurcos().get(0).plantaMasCerca());
		System.out.println(juego.getJardin().getSurcos().get(0).hayPlantas());
		System.out.println(juego.getJardin().getSurcos().get(0).plantaMasCerca());
		System.out.println(juego.getJardin().getSurcos().get(0).hayPlantas());
		System.out.println(juego.getJardin().getSurcos().get(0).plantaMasCerca());
	
		
		
		
		juego.atacar(zombie,juego.getJardin().getSurcos().get(0),resultado);
	
		
	}
	
	@Test
	public void siAgregarPlanta(){
		plantaC.setEsDeAgua(true);
		
		juego.plantar(plantaC, 2, 1);
		
		//no esta vacio porque se agrego correctamente
		assertFalse(juego.getJardin().getSurcos().get(1).
				getMacetas().get(2).estaVacio());
	}
	
	@Test
	public void noAgregarPlantaIncopatible(){
		//lanza exception ya que ela planta y el surco son incompatibles
		plantaC.setEsDeAgua(false);
		
		try{
		juego.plantar(plantaC, 2, 1);
		fail();
		}catch(UserException e){
			
		}
		
	}
	
	@Test
	public void noAgregarPlantaMacetaOcupada(){
		//se planta correctamente
		plantaC.setEsDeAgua(true);
		juego.plantar(plantaC, 2, 1);
		
		try{
			//lanza exception porque la maceta esta ocupada
		juego.plantar(plantaC, 2, 1);
		fail();
		}catch(UserException e){
			
		}
		
	}
	
	@Test
	public void noAgregarPlantaCoordXFueraDeRango(){
		//se planta correctamente
		plantaC.setEsDeAgua(true);
		
		try{
			//lanza exception porque x esta fuera de rango
		juego.plantar(plantaC, 6, 1);
		fail();
		}catch(UserException e){
			
		}
		
		try{
			//lanza exception porque x esta fuera de rango
		juego.plantar(plantaC, -1, 1);
		fail();
		}catch(UserException e){
			
		}
	}
	
	
	@Test
	public void noAgregarPlantaCoordYFueraDeRango(){
		//se planta correctamente
		plantaC.setEsDeAgua(true);
		
		try{
			//lanza exception porque y esta fuera de rango
		juego.plantar(plantaC, 3, 6);
		fail();
		}catch(UserException e){
			
		}
		
		try{
			//lanza exception porque y esta fuera de rango
		juego.plantar(plantaC, 0, -1);
		fail();
		}catch(UserException e){
			
		}
	}
	

}
