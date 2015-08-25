package elementos.jardines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.model.UserException;

import elementos.plantas.Planta;

public class SurcoTest {

	Surco surco;
	Planta plantaA;
	Planta plantaB;
	Planta plantaC;

	
	@Before
	public void before(){
		//Surco de tierra
		surco=new Surco(false,5);
		
		//COnfigurando las planta con un nombre y de tipo tierra
		plantaA=new Planta();
		plantaA.setNombre("Rosa");
		plantaA.setEsDeAgua(false);
		plantaB=new Planta();
		plantaB.setNombre("Ruby");
		plantaB.setEsDeAgua(false);
		plantaC=new Planta();
		plantaC.setNombre("Planta");
		plantaC.setEsDeAgua(false);
		
		
	}
	
	@Test
	public void noHayPlantas(){
		//todavia no se planto ninguna planta
		assertFalse(surco.hayPlantas());
	}
	
	@Test
	public void siHayPlantas(){
		//plantando una planta
		surco.plantar(new Planta(), 2);
		
		//ahora hay una planta
		assertTrue(surco.hayPlantas());
	}
	
	@Test
	public void sinPlantaMasCerca(){

		//No hay plantas por lo tanto retorna null
		assertNull(surco.plantaMasCerca());
	}
	
	@Test
	public void ConPlantaMasCerca(){

		//esta seria la planta mas cerca
		surco.plantar(plantaA, 3);
		
		//esta no es la mas cerca
		surco.plantar(plantaB, 2);
		
		assertEquals(surco.plantaMasCerca(),plantaA);
		
		//ahora esta es la mas cerca
		surco.plantar(plantaC, 4);
		
		assertEquals(surco.plantaMasCerca(),plantaC);
		
	}
	
	@Test
	public void sacarPlanta(){
		
		surco.plantar(plantaA, 1);	
		surco.plantar(plantaB, 2);
		
		//sacando plantas
		surco.sacarPlanta(plantaA);
		surco.sacarPlanta(plantaB);
		
		//las plantas fueron eliminadas
		assertTrue(surco.getMacetas().get(1).estaVacio());
		assertTrue(surco.getMacetas().get(2).estaVacio());
	}
	
	@Test
	public void PlantarCompatibles(){
		
		surco.plantar(plantaA, 2);
		
		assertFalse(surco.getMacetas().get(2).estaVacio());
		
		surco.plantar(plantaB, 4);
		
		assertFalse(surco.getMacetas().get(4).estaVacio());
	}
	
	@Test
	public void plantarIncompatibles(){
		plantaA.setEsDeAgua(true);
		
		try{
		surco.plantar(plantaA, 1);
		fail("la planta se pudo plantar el tipos diferentes");
		}catch(UserException e){
			
		}
		
	}

}
