package elementos;


import java.io.Serializable;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import elementos.jardines.JardinZen;
import elementos.jardines.Zona;
import elementos.plantas.Plantin;
import exceptions.NoPudoComprarException;

@Observable
public class Jugador implements Serializable{

	private String nombre;
	private double puntos;
	private JardinZen jardinZen;

	public Jugador() {
		this.configurarJugador();
	}

	// *****************
	// getters y setters
	// *****************

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		this.validarNombre();
	}

	public double getPuntos() {
		return puntos;
	}

	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}

	public JardinZen getJardinZen() {
		return jardinZen;
	}

	public void setJardinZen(JardinZen jardinZen) {
		this.jardinZen = jardinZen;
	}

	public void validarNombre() {
		if (this.getNombre().length() > 12) {
			throw new UserException("Nombre demasiado largo(4-12)");
		}
		if (this.getNombre().length() < 4) {
			throw new UserException("nombre demasiado corto(4-12)");
		}

	}

	// *****************
	// implementacion
	// *****************

	public void configurarJugador() {
		this.setPuntos(500);
		this.setJardinZen(new JardinZen());
		this.setNombre("Nombre");
	}

	public void comprarMejora(int puntos) {
		if (this.puntos >= puntos) {
			this.setPuntos(this.getPuntos() - puntos);
		} else {
			throw new NoPudoComprarException("No tienes puntos suficientes");
		}
	}
	
	public Plantin obtenerPlantin(String nombre){
		Plantin plantin=this.getJardinZen().obtenerPlantinEnTodoJardin(nombre);
		if(plantin == null){
			throw new UserException("El plantin "+nombre+" no existe");
		}
		return plantin;
	}

	public Zona getZonaTerrestre() {
		return this.getJardinZen().getTerrestre();
	}
	
	public Zona getZonaAcuatica() {
		return this.getJardinZen().getAcuatico();
	}

}
