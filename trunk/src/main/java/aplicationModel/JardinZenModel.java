package aplicationModel;

import java.io.Serializable;

import org.uqbar.commons.utils.Observable;

import elementos.Jugador;
import elementos.jardines.JardinZen;
import elementos.jardines.Zona;
import elementos.plantas.Plantin;

@Observable
public class JardinZenModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private JardinZen jardinZen;
	private Zona zonaVisible;
	private Plantin plantinSeleccionado;
	private Jugador jugador;
	
	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public JardinZenModel(JardinZen jardinZen, Jugador jugador) {
		this.jardinZen = jardinZen;
		this.jugador = jugador;
		this.zonaVisible = jardinZen.getTerrestre();
	}

	public JardinZen getJardinZen() {
		return jardinZen;
	}

	public void setJardinZen(JardinZen jardinZen) {
		this.jardinZen = jardinZen;
	}

	public void setZonaVisible(Zona zonaVisible) {
		this.zonaVisible = zonaVisible;
	}

	public Plantin getPlantinSeleccionado() {
		return plantinSeleccionado;
	}

	public void setPlantinSeleccionado(Plantin plantinSeleccionado) {
		this.plantinSeleccionado = plantinSeleccionado;
	}

	public Zona getZonaVisible() {
		return zonaVisible;
	}

	public void cambiarZona() {
		if (this.zonaVisible.getEsDeAgua()) {
			this.setZonaVisible(this.jardinZen.getTerrestre());
		} else {
			this.setZonaVisible(this.jardinZen.getAcuatico());
		}
	}

}
