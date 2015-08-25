package elementos.jardines;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.model.UserException;

import elementos.Zombie;
import elementos.jardines.Jardin;
import elementos.plantas.Planta;




public class JardinTest {

	Jardin jardin;
	Planta plantaA;
	Planta plantaB;
	Planta plantaC;
	Zombie zombie;
		
	
	@Before
	public void before(){
		//el jardin tiene 6 de alto y 5 de ancho por default
		jardin=new Jardin();
		plantaA=new Planta();
		plantaB=new Planta();
		plantaC=new Planta();
		
	}
	
	@Test
	public void siPlantar(){
		plantaA.setEsDeAgua(false);
		jardin.plantar(plantaA, 0, 0);
		assertFalse(jardin.getSurcos().get(0).getMacetas().get(0).estaVacio());
	}
	
	@Test
	public void noPlantar(){
		//planta a plantaA en un surco incompatible
		plantaA.setEsDeAgua(false);
		try{
			jardin.plantar(plantaA, 9, 9);
			fail("La planta pudo plantarse en un surco que no corresponde");
			}catch(UserException e){
				
			}
		
	}
	
	@Test
	public void noPlantarFueraDeRangoPositivo(){
		plantaA.setEsDeAgua(false);
		try{
		jardin.plantar(plantaA, 9, 9);
		fail("La planta pudo plantarse fuera de rango(Positivo)");
		}catch(UserException e){
			
		}
		
	}
	
	@Test
	public void noPlantarFueraDeRangoNegativo(){
		//la planta cae fuera del rango del tamaño del jardin
		try{
		plantaA.setEsDeAgua(false);
		jardin.plantar(plantaA,-1, -9);
		fail("La planta pudo plantarse fuera de rango(Negativo)");
		}catch(UserException e){
			
		}
		
	}
	
	@Test
    public void validacionCorrecta(){
		//el jardin tiene 6 de alto y 5 de ancho por default
		//por lo tanto no salta la exception
    	jardin.validarPosicion(3, 4);
    }
	
	@Test
    public void validacionInorrectaConCoordNeg(){
		//el jardin tiene 6 de alto y 5 de ancho por default
		//por lo tanto no salta la exception
		try{
    	jardin.validarPosicion(-1, 4);
    	fail("La validacion paso coord negativa en x");
		}catch(UserException e){
			
		}
    	
		try{
    	jardin.validarPosicion(1,-2);
    	fail("La validacion paso coord negativa en y");
	    }catch(UserException e){
		
	    }
		
		try{
	        jardin.validarPosicion(-1,-2);
	    	fail("La validacion paso coord negativa en y e x");
		}catch(UserException e){
			
		}
    }
	
	
	@Test
    public void validacionInorrectaConCoordPos(){
		//el jardin tiene 6 de alto y 5 de ancho por default
		//por lo tanto no salta la exception
		try{
    	jardin.validarPosicion(7, 4);
    	fail("La validacion paso coord positiva x mayor al ancho del jardin");
		}catch(UserException e){
			
		}
    	
		try{
    	jardin.validarPosicion(1,6);
    	fail("La validacion paso coord positiva y mayor al ancho del jardin" );
	    }catch(UserException e){
		
	    }
		
		try{
	        jardin.validarPosicion(-1,-2);
	    	fail("La validacion paso coord positiva en y e x");
		}catch(UserException e){
			
		}
    }

}
