package aplicationModel;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import elementos.AlmanaqueDeZombies;
import elementos.Juego;
import elementos.Jugador;
import elementos.Zombie;
import elementos.jardines.JardinZen;
import elementos.jardines.Maceta;
import elementos.jardines.Surco;
import elementos.plantas.Plantin;

/**
 * model apliccation
 * del.class,"getPlantines"),this.getMetodoDe(JuegoModel.class,"setZombieAAtacar
 * 
 */
@SuppressWarnings("serial")
@Observable
public class JuegoModel implements Serializable {

	private Juego juego;
	private List<Surco> surcos;
	private Surco surcoSeleccionado;
    
	private List<String> acciones;

	// plantar
	private PlantarModel plantarModel;

	// atacar
	private AtacarModel atacarModel;

	
	public JuegoModel() {
		this.configurarJuego();
	}
	

	// *****************
	// implementacion
	// *****************

	/**
	 * Configura el modero,tambien sirava para que pueda ser reiniciado
	 */
	public void configurarJuego() {
		this.setJuego(new Juego(new Jugador()));
		this.acciones = new LinkedList<String>();
		this.acciones.add("****Resultados de peleas****");
		this.surcos=this.juego.getJardin().getSurcos();
		this.plantarModel=new PlantarModel();
		this.atacarModel=new AtacarModel(this);
		this.setPlantines(new LinkedList<Plantin>());
		this.setZombies(AlmanaqueDeZombies.getZombiesPrototipos());
		this.getJuego().getJugador().setPuntos(500);
	}


	/**
	 * Agrega una nueva planta del plantinAPlantar al jardin si cumple con los
	 * requisitos
	 */

	public void plantar() {
		this.plantarModel.plantar(this.surcoSeleccionado);
		this.surcoSeleccionado = null;
	}
	
	/**
	 * el zombieAAtacar ataca a surcoSelect
	 */
	public void atacar() {
		this.atacarModel.atacar(surcoSeleccionado, juego);
	}

	/**
	 * elige los plantines segun el tipo del surco seleccionado
	 */
	
	
	
	// *****************
	// getters y setters
	// *****************
	public List<Surco> getSurcos() {
		return surcos;
	}

	public void setSurcos(List<Surco> surcos) {
		this.surcos = surcos;
	}

	public PlantarModel getPlantarModel() {
		return plantarModel;
	}

	public void setPlantarModel(PlantarModel plantarModel) {
		this.plantarModel = plantarModel;
	}

	public AtacarModel getAtacarModel() {
		return atacarModel;
	}

	public void setAtacarModel(AtacarModel atacarModel) {
		this.atacarModel = atacarModel;
	}

	public void setSurcoSeleccionado(Surco surcoSeleccionado) {
		this.surcoSeleccionado = surcoSeleccionado;
		this.plantarModel.elegirPlantines(juego, surcoSeleccionado);
	}

	
	public List<Zombie> getZombies() {
		return this.atacarModel.getZombies();
	}
	
	public void setZombies(List<Zombie> zombies) {
		this.atacarModel.setZombies(zombies);
	}
	
	public Zombie getZombieAAtacar() {
		return this.atacarModel.getZombieAAtacar();
	}
	
	public void setZombieAAtacar(Zombie zombieAAtacar) {
		this.atacarModel.setZombieAAtacar(zombieAAtacar);
	}
	

	public List<String> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<String> acciones) {
		this.acciones = acciones;
	}
	

	public Surco getSurcoSeleccionado() {
		return surcoSeleccionado;
	}


	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}


	public JardinZen getJardinZen() {
		return this.juego.getJugador().getJardinZen();
	}

	public Jugador getJugador() {
		return this.juego.getJugador();
	}
	
	
	public List<Plantin> getPlantines() {
		return this.plantarModel.getPlantines();
	}
	
	public void setPlantines(List<Plantin> plantines) {
		this.plantarModel.setPlantines(plantines);
	}
	
	public Plantin getPlantinAPlantar() {
		return this.plantarModel.getPlantinAPlantar();
	}
	
	public void setPlantinAPlantar(Plantin plantinAPlantar) {
		this.plantarModel.setPlantinAPlantar(plantinAPlantar);
	}
	
	public void setMacetaSeleccionada(Maceta macetaSeleccionada) {
		this.plantarModel.setMacetaSeleccionada(macetaSeleccionada);
	}


	public boolean esMacetaSeleccionada(Maceta maceta) {
		return this.plantarModel.getMacetaSeleccionada() == maceta;
	}


}
