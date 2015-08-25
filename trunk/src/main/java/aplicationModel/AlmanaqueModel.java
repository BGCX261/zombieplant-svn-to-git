package aplicationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import elementos.Juego;
import elementos.Zombie;
//public class AlmanaqueModel {

@Observable
public class AlmanaqueModel implements Serializable{


	private List<Zombie> todosLosZombies ;
	private Zombie zombieSeleccionado=new Zombie();
	private List<Zombie> zombies = new ArrayList<Zombie>();
	private Juego juego;
	private String busqueda = "";  
	
	public AlmanaqueModel(Juego juego) {
		super();
		this.zombies.addAll(juego.getAlmanaque().getZombies());
		this.todosLosZombies = juego.getAlmanaque().getZombies();
		this.juego = juego;
	}

	public String getBusqueda() {
	   return busqueda != null ? busqueda : "";
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public AlmanaqueModel(){
		
	}

	
	public Zombie getZombieSeleccionado() {
		return zombieSeleccionado;
	}
	public void setZombieSeleccionado(Zombie zombie) {
		this.zombieSeleccionado = zombie;
	}
	public List<Zombie> getZombies() {
		return zombies;
	}
	public void setZombies(List<Zombie> zombies) {
		this.zombies = zombies;
	}
	
	public void buscar(){
		
		List<Zombie> zombiesSeleccionados = new ArrayList<Zombie>();
		for(Zombie z :this.todosLosZombies){
			if (z.getNombre().contains(getBusqueda())){
				zombiesSeleccionados.add(z);
			}
		}
		this.setZombies(zombiesSeleccionados);
	
	}
	
	
}